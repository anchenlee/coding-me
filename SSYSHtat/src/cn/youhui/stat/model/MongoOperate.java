package cn.youhui.stat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.youhui.stat.util.Config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoOperate {
	private static MongoOperate instance = null;
	
	private static final int SIZE = 6;
	public static MongoOperate getInstance(){
		if(instance == null) instance = new MongoOperate();
		return instance;
	}
	public MongoOperate() {
		// TODO Auto-generated constructor stub
	}
	
	public String getProSug(String pid, String title) {
		List<String> list = getItemSug(title);
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("colsuggest");
			DBCollection viewrecord=db.getCollection("sugpros");
			BasicDBObject query = new BasicDBObject("pid",pid);
			DBObject record = viewrecord.findOne(query);
			if (record == null) {
				query = new BasicDBObject("pid",'0');
				record = viewrecord.findOne(query);
			}
			BasicDBList fixList = (BasicDBList) record.get("fixps");
			fixList.addAll((BasicDBList) record.get("ps"));
			int i = 0;
			int count = list.size();
			while (count < SIZE && i < fixList.size()) {
				String cP = (String)fixList.get(i);
				if (!cP.equals(pid)) {
					list.add((String)fixList.get(i));
					count ++;
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return list.toString();
	}
	
	private List<String> getItemSug(String title){
		ArrayList<String> list = new ArrayList<String>();
		if (title == null || title.equals("")) {
			return list;
		}
		JsonParser jp = new JsonParser();
		JsonArray ja = jp.parse(obtainSugItem()).getAsJsonArray();
		for (JsonElement je : ja) {
			JsonObject jo = je.getAsJsonObject();
			String pid = jo.get("pid").getAsString();
			String keys = jo.get("keys").getAsString();
			Pattern pat = Pattern.compile(keys);
			Matcher mat = pat.matcher(title);
			if (mat.find() == true) {
				list.add(pid);
			}
		}
		return list;
		
	}
	
	public boolean insertSugItem(String pid, String keys, long time, int status) {
		MongoClient client = null;
		boolean flag = false;
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("colsuggest");
			DBCollection itemrecord=db.getCollection("itempros");
			BasicDBObject query = new BasicDBObject("pid",pid);
			DBObject record = itemrecord.findOne(query);
			BasicDBObject item = new BasicDBObject("pid",pid).append("keys", keys).append("time", time).append("status", status);
			if (record == null) {
				itemrecord.insert(item).toString();
			}else {
				itemrecord.update(query,item).toString();
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return flag;
	}
	
	public String obtainSugItem() {
		MongoClient client = null;
		JsonArray ja = new JsonArray();
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("colsuggest");
			DBCollection itemrecord=db.getCollection("itempros");
			long time = System.currentTimeMillis();
			System.out.println(time);
			BasicDBObject query = new BasicDBObject("status",0).append("time", new BasicDBObject("$gt",time));
			DBCursor cursor = itemrecord.find(query);
			while (cursor.hasNext()) {
				DBObject dbo = (DBObject) cursor.next();
				JsonObject jo = new JsonObject();
				jo.addProperty("pid", (String) dbo.get("pid"));
				jo.addProperty("keys", (String) dbo.get("keys"));
				jo.addProperty("t", (long) dbo.get("time"));
				ja.add(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return ja.toString();
	}
	
	public static void main(String[] args) {
		String pid = "123";
		String title = "我的老家就住在这个屯";
		System.out.println(System.currentTimeMillis());
		System.out.println(MongoOperate.getInstance().getItemSug(title));
		System.out.println(MongoOperate.getInstance().getProSug(pid, title));
		
	}
	
}
