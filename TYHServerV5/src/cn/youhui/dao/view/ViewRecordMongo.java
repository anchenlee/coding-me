package cn.youhui.dao.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import cn.youhui.bean.view.UserViewRecord;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.YouhuiLog;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * deal with mongo interfaces
 * @author suishou
 *
 */
public class ViewRecordMongo {
	
	private static ViewRecordMongo instance = null;
	
	private ViewRecordMongo(){}
	
	public static ViewRecordMongo getInstance(){
		if(instance == null) instance = new ViewRecordMongo();
		return instance;
	}
	/**
	 * get view record of specific user
	 * @param userId
	 * @param phoneId
	 * @param page
	 * @return
	 */
	public static String obtainViewRecord(String userId, String phoneId, int page){
		int count = 10;
		String buffer = "";
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("youhui");
			DBCollection viewrecord=db.getCollection("viewrecord");
			BasicDBObject query;
			if (userId.equals("")) {
				query = new BasicDBObject("phoneid",phoneId);
			}else{
				query = new BasicDBObject("userid",userId);
			}
			DBCursor records = viewrecord.find(query).sort(new BasicDBObject("month", -1));
			int cc = 0;
			int prec = 0;
			int total = 0;
			ArrayList<UserViewRecord> viewRecords = new ArrayList<UserViewRecord>();
			while (records.hasNext()) {
				BasicDBObject dbObject = (BasicDBObject) records.next();
				BasicDBList products = (BasicDBList) dbObject.get("products");
				int i = 0;
				while (i < products.size()) {
					BasicDBObject cp = (BasicDBObject) products.get(i);
					int status = cp.getInt("status");
					if (cc < count && status == 1 && prec >= (page-1)*count) {
						UserViewRecord r = new UserViewRecord();
						r.setProductId(cp.getString("productid"));
						r.setTime(cp.getLong("time"));
						r.setImg(cp.getString("img"));
						r.setTitle(cp.getString("title"));
						r.setPrice(cp.getString("price"));
						viewRecords.add(r);
						cc++;
					}
					if (status == 1) {
						total++;
						prec++;
					}
					i++;
				}
			}
			
			String s = "<viewrecords>";
			if (viewRecords.size() == 0) {
				buffer = RespStatusBuilder.message(ActionStatus.NO_RESULT).toString();
			}else {
				for (int i = 0; i < viewRecords.size(); i++) {
					System.out.println(viewRecords.get(i).getTime() + " "+viewRecords.get(i).getProductId());
					s += viewRecords.get(i).toXML();
				}
				s+= "</viewrecords>";
				int totalPage = 0;
				if (total % count == 0) {
					totalPage = total/count;
				}else {
					totalPage = total/count + 1;
				}
				buffer = RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", totalPage, page, s).toString();
			}
		} catch (Exception e) {
			buffer = RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString();
		}finally{
			client.close();
		}
		return buffer;
	}
	
	/**
	 * add a new view record to the mongodb
	 * @param userId
	 * @param phoneId
	 * @param productId
	 * @param title
	 * @param img
	 * @param price
	 * @param time
	 * @return 1 for success, 0 for failed
	 */
	public static int addNewViewRecord(String userId, String phoneId, String productId, String title, String img, String price, long time) {
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER, 27017);
			DB db=client.getDB("youhui");
			DBCollection viewrecord=db.getCollection("viewrecord");
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(time);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
			System.out.println(year + "  "+month + " "+time +" "+System.currentTimeMillis());
			BasicDBObject query;
			if (userId.equals("")) {
				query = new BasicDBObject("phoneid", phoneId).append("month", year*100+month);
			}else {
				query = new BasicDBObject("userid",userId).append("month", year*100+month);
			}
			BasicDBObject prd = new BasicDBObject("title",title).append("productid", productId)
					.append("img", img).append("price", price).append("time", time).append("status", 1);
			DBObject record = viewrecord.findOne(query);
			Comparator<BasicDBObject> comparator = new Comparator<BasicDBObject>(){
				@Override
				public int compare(BasicDBObject o1, BasicDBObject o2) {
					int result = -1;
					if (o1.getLong("time") < o2.getLong("time")) {
						result = 1;
					}
					//YouhuiLog.getInstance().logYouhui(o1.getLong("time")+":"+o2.getLong("time")+" result:"+result);
					return result;
				}
			};
			if (record == null) {
				ArrayList<BasicDBObject> ps = new ArrayList<BasicDBObject>();
				ps.add(prd);
				BasicDBObject doc = new BasicDBObject("userid", userId).append("month", year*100+month)
						.append("phoneid", phoneId).append("products", ps);
				viewrecord.insert(doc);
			}else {
				ArrayList<BasicDBObject> ps = (ArrayList<BasicDBObject>) record.get("products");
				boolean flag = false;
				for (int i = 0; i < ps.size(); i++) {
					if (ps.get(i).getString("productid").equals(prd.getString("productid"))) {
						BasicDBObject p = ps.get(i);
						flag = true;
						p.append("time", time);
						ps.set(i, p);
					}
				}
				if (flag == false) {
					ps.add(prd);
				}
//				String s_pre = "pre: ";
//				for (int i = 0; i < ps.size(); i++) {
//					s_pre += ps.get(i).getLong("time")+" ";
//				}
//				YouhuiLog.getInstance().logYouhui(s_pre);
				Collections.sort(ps, comparator);
//				String s_aft = "aft: ";
//				for (int i = 0; i < ps.size(); i++) {
//					s_aft += ps.get(i).getLong("time")+" ";
//				}
//				YouhuiLog.getInstance().logYouhui(s_aft);
				viewrecord.update(query, new BasicDBObject("$set", new BasicDBObject("products", ps)));
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			client.close();
		}
	}
	/**
	 * remove the view records from the mongodb
	 * @param userId
	 * @param phoneId
	 * @param productId
	 * @return result string
	 */
	public static String removeViewRecord(String userId, String phoneId, String productId) {
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER, 27017);
			DB db=client.getDB("youhui");
			DBCollection viewrecord=db.getCollection("viewrecord");
			BasicDBObject query;
			if (userId.equals("")) {
				query = new BasicDBObject("phoneid", phoneId);
			}else {
				query = new BasicDBObject("userid",userId);
			}
			DBCursor records = viewrecord.find(query);
			while (records.hasNext()) {
				DBObject dbObject = (DBObject) records.next();
				BasicDBList products = (BasicDBList) dbObject.get("products");
				boolean change = false;
				for (int i = 0; i < products.size(); i++) {
					BasicDBObject p = (BasicDBObject) products.get(i);
					if (p.getString("productid").equals(productId)) {
						change = true;
						p.append("status", 0);
						products.set(i, p);
					}
				}
				if (change == true) {
					int month = (Integer) dbObject.get("month");
					BasicDBObject update = new BasicDBObject("userid",userId).append("month", month);;
					viewrecord.update(update, new BasicDBObject("$set", new BasicDBObject("products", products)));
				}
			}
			return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString();
		} catch (Exception e) {
			return RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString();
		}finally{
			client.close();
		}
	}
	
	public static String removeAllViewRecord(String userId, String phoneId) {
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER, 27017);
			DB db=client.getDB("youhui");
			DBCollection viewrecord=db.getCollection("viewrecord");
			BasicDBObject query;
			if (userId.equals("")) {
				query = new BasicDBObject("phoneid", phoneId);
			}else {
				query = new BasicDBObject("userid",userId);
			}
			DBCursor records = viewrecord.find(query);
			while (records.hasNext()) {
				DBObject dbObject = (DBObject) records.next();
				BasicDBList products = (BasicDBList) dbObject.get("products");
				boolean change = false;
				for (int i = 0; i < products.size(); i++) {
					BasicDBObject p = (BasicDBObject) products.get(i);
					if (p.getInt("status") == 1) {
						change = true;
						p.append("status", 0);
						products.set(i, p);
					}
				}
				if (change == true) {
					int month = (Integer) dbObject.get("month");
					BasicDBObject update = new BasicDBObject("userid",userId).append("month", month);;
					viewrecord.update(update, new BasicDBObject("$set", new BasicDBObject("products", products)));
				}
			}
			
			return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString();
		} catch (Exception e) {
			return RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString();
		}finally{
			client.close();
		}
	}
	
	public static void main(String[] args) {
//		addNewViewRecord("64844839", "357070058541345", "36852754291", "test", "test", "test", System.currentTimeMillis());
		//obtainViewRecord("64844839", "357070058541345",1);
		
		for(int i = 0; i< 100000; i++){
			MongoClient client = null;
		try {
			
			Thread.sleep(1);
			
			client = new MongoClient(Config.MONGO_SERVER, 27017);
			DB db=client.getDB("youhui");
			DBCollection viewrecord=db.getCollection("viewrecord");
			BasicDBObject query = new BasicDBObject("phoneid", "123456");
			DBCursor records = viewrecord.find(query);
			while (records.hasNext()) {
			}
			System.out.println(client.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			client.close();
		}
		
		
		}
	}
}
