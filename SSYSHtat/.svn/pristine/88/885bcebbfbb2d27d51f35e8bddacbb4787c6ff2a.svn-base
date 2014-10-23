package cn.youhui.stat.model;

import cn.youhui.stat.util.Config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TbkItem;
import com.taobao.api.request.TbkItemsDetailGetRequest;
import com.taobao.api.response.TbkItemsDetailGetResponse;

public class PidMongoModel {
	private static PidMongoModel instance = null;
	public PidMongoModel() {}
	public static PidMongoModel getInstance(){
		if(instance == null) instance = new PidMongoModel();
		return instance;
	}
	/**
	 * add product to mongodb for suggest
	 * @param pid
	 * @param title
	 * @param img
	 * @param price
	 * @return
	 */
	public String AddPidMongo(String pidsStr) {
		MongoClient client = null;
		long timestamp = System.currentTimeMillis();
		JsonObject jo = new JsonObject();
		jo.addProperty("ret", false);
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection piddb=db.getCollection("piddb");
			String[] pids = pidsStr.split(",");
			for(String pid:pids){
				BasicDBObject query = new BasicDBObject("pid",pid);
				TbkItem item = getPidInfo(pid);
				if (item != null) {
					BasicDBObject pidItem = new BasicDBObject("pid",pid).append("title", item.getTitle())
																		.append("img", item.getPicUrl())
																		.append("price", item.getPrice())
																		.append("t", timestamp);
					DBObject dbo = piddb.findOne(query);
					if (dbo != null) {
						piddb.update(query, pidItem);
					}else {
						piddb.insert(pidItem);
					}
				}
			}
			jo.addProperty("ret", true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return jo.toString();
	}
	
	private static TbkItem getPidInfo(String pid){
		TbkItem item = null;
		TaobaoClient client=new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "12347692", "c62deb79ce636f9cbac7c074269ab1d6");
		TbkItemsDetailGetRequest req=new TbkItemsDetailGetRequest();
		req.setFields("title,price,pic_url");
		req.setNumIids(pid);
		try {
			TbkItemsDetailGetResponse rsp= client.execute(req);
			if(rsp.getTbkItems().size()>0){
				item = rsp.getTbkItems().get(0);
			}
		} catch (Exception e) {
		}
		return item;
	}
	
	public String FetchPidsMongo() {
		MongoClient client = null;
		JsonArray ja = new JsonArray();
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection itemrecord=db.getCollection("piddb");
			BasicDBObject query = new BasicDBObject();
			BasicDBObject sort = new BasicDBObject("t",-1);
			DBCursor cursor = itemrecord.find(query).sort(sort);
			while (cursor.hasNext()) {
				DBObject dbo = (DBObject) cursor.next();
				JsonParser jp = new JsonParser();
				JsonObject jo = jp.parse(dbo.toString()).getAsJsonObject();
				jo.remove("_id");
				ja.add(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return ja.toString();
	}
	public String RemovePidMongo(String pids) {
		MongoClient client = null;
		String[] pidStrings = pids.split(",");
		JsonObject jo = new JsonObject();
		jo.addProperty("ret", false);
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection piddb=db.getCollection("piddb");
			for (String pid: pidStrings) {
				BasicDBObject query = new BasicDBObject("pid",pid);
				piddb.remove(query);
			}
			jo.addProperty("ret", true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return jo.toString();
	}
	
	public static void main(String[] args) {
		String pid = "123";
		String[] pidStrings = pid.split(",");
		for(String t:pidStrings){
			System.out.println(t);
		}
	}
}
