package cn.youhui.stat.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cn.youhui.stat.util.Config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

public class StatMongoModel {
	
	private static StatMongoModel instance = null;
	public StatMongoModel() {}
	public static StatMongoModel getInstance(){
		if(instance == null) instance = new StatMongoModel();
		return instance;
	}
	
	public String FetchPidStatMongo(String pid) {
		MongoClient client = null;
		JsonArray ja = new JsonArray();
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection itemrecord=db.getCollection("pidfreq");
			BasicDBObject query = new BasicDBObject("pid",pid);
			BasicDBObject sort = new BasicDBObject("month",-1);
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
	
	public String FetchKeywordStatMongo(String keyword, String day) {
		MongoClient client = null;
		JsonArray ja = new JsonArray();
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection kwfreqstat=db.getCollection("kwfreqstat");
			BasicDBObject query = new BasicDBObject("key", new BasicDBObject("$regex",keyword)).append("day", day);
			BasicDBObject sort = new BasicDBObject("count",-1);
			DBCursor cursor = kwfreqstat.find(query).sort(sort);
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
	
	public String GetCurDbLoc(String dbName){
		String loc = "0";
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection lastrecord=db.getCollection("lastrecord");
			BasicDBObject query = new BasicDBObject("db_name",dbName);
			DBObject dbo = lastrecord.findOne(query);
			if (dbo != null) {
				loc = dbo.get("rid").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return loc;
	}
	
	public String getSourcePvUvMongo(String source, String start, String end, String channel, String platform) {
		JsonArray ja = new JsonArray();
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection sourcepvuv=db.getCollection("sourcepvuv");
			BasicDBObject query = new BasicDBObject("source",source).append("day", new BasicDBObject("$gte", start).append("$lte", end));
			DBCursor cursor = sourcepvuv.find(query).sort(new BasicDBObject("day",1));
			while (cursor.hasNext()) {
				DBObject dbo = (DBObject) cursor.next();
				JsonParser jp = new JsonParser();
				JsonObject cujo = jp.parse(dbo.toString()).getAsJsonObject();
				if (channel.equals("") && platform.equals("")) {
					cujo.remove("_id");
					ja.add(cujo);
				}else {
					int pv = 0;
					int uv = 0;
					JsonArray data = cujo.get("data").getAsJsonArray();
					for(JsonElement cje : data){
						JsonObject cjo = cje.getAsJsonObject();
						boolean flag = true;
						//System.out.println(channel+":"+platform+":"+cjo.get("channel")+":"+cjo.get("platform"));
						if (!channel.equals("") && !cjo.get("channel").getAsString().equals(channel)) {
							flag = false;
						}
						if (!platform.equals("") && !cjo.get("platform").getAsString().equals(platform)) {
							flag = false;
						}
						if (flag == true) {
							pv += cjo.get("pv").getAsDouble();
							uv += cjo.get("uv").getAsDouble();
						}
					}
					JsonObject jo = new JsonObject();
					jo.addProperty("day", cujo.get("day").getAsString());
					jo.addProperty("source", cujo.get("source").getAsString());
					jo.addProperty("pv", pv);
					jo.addProperty("uv", uv);
					ja.add(jo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return ja.toString();
	}
	
	public String getSourcePvUvMongo(String source, String day, String channel, String platform) {
		JsonObject jo = new JsonObject();
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection sourcepvuv=db.getCollection("sourcepvuv");
			BasicDBObject query = new BasicDBObject("source",source).append("day", day);
			DBObject dbo = sourcepvuv.findOne(query);
			if (dbo != null) {
				JsonParser jp = new JsonParser();
				if (channel.equals("") && platform.equals("")) {
					jo.add("pv", jp.parse(dbo.get("pvs").toString()));
					jo.add("uv", jp.parse(dbo.get("uvs").toString()));
				}else{
					int pv = 0;
					int uv = 0;
					JsonArray data = jp.parse(dbo.get("data").toString()).getAsJsonArray();
					for(JsonElement cje : data){
						JsonObject cjo = cje.getAsJsonObject();
						boolean flag = true;
						if (!channel.equals("") && !cjo.get("channel").getAsString().equals(channel)) {
							flag = false;
						}
						if (!platform.equals("") && !cjo.get("platform").getAsString().equals(platform)) {
							flag = false;
						}
						if (flag == true) {
							pv += cjo.get("pv").getAsDouble();
							uv += cjo.get("uv").getAsDouble();
						}
					}
					jo.addProperty("pv", pv);
					jo.addProperty("uv", uv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		return jo.toString();
	}
	
	public String getSourceClickMongo(String source, String start, String end, int size, int page, String channel, String platform) {
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		long tpages = 0;
		MongoClient client = null;
		try {
			client = new MongoClient(Config.MONGO_SERVER , 27017);
			DB db=client.getDB("logstat");
			DBCollection sourceclick=db.getCollection("sourceclick");
			BasicDBObject query = new BasicDBObject("source",source).append("day", new BasicDBObject("$gte", start).append("$lte", end));
			String map = "";
			if (channel.equals("") && platform.equals("")) {
				map = "function(){"
					+ "emit(this.pid,this.tclick);"
					+ "}";
			}else if(platform.equals("")){
				map = "function(){"
						+ "var total = 0;"
						+ "for(var i = 0; i < this.data.length; i++){"
						+ "    if(this.data[i].channel == '"+channel+"'){"
								+ "total += this.data[i].tclick"
							+ "}"
						+ "}"
						+ "emit(this.pid,total);"
						+ "}";
			}else if(channel.equals("")){
				map = "function(){"
						+ "var total = 0;"
						+ "for(var i = 0; i < this.data.length; i++){"
						+ "    if(this.data[i].platform == '"+platform+"'){"
								+ "total += this.data[i].tclick"
							+ "}"
						+ "}"
						+ "emit(this.pid,total);"
						+ "}";
			}else{
				map = "function(){"
						+ "var total = 0;"
						+ "for(var i = 0; i < this.data.length; i++){"
						+ "    if(this.data[i].channel == '"+channel+"' && this.data[i].platform == '"+platform+"'){"
								+ "total += this.data[i].tclick"
							+ "}"
						+ "}"
						+ "emit(this.pid,total);"
						+ "}";
			}
			String reduce = "function(key, values){"
					+ "var total=0;"
					+ "for(var i=0;i<values.length;i++) "
					+ "    total+=values[i]; "
					+ "return total;}";
			MapReduceCommand cmd = new MapReduceCommand(sourceclick, map, reduce, null, MapReduceCommand.OutputType.INLINE, query);
			MapReduceOutput out = sourceclick.mapReduce(cmd);
			ArrayList<JsonObject> dbos = new ArrayList<JsonObject>();
			for(DBObject dbo: out.results()){
				JsonObject cujo = new JsonObject();
				cujo.addProperty("pid", dbo.get("_id").toString());
				cujo.addProperty("count", Double.parseDouble(dbo.get("value").toString()));
				dbos.add(cujo);
			}
			Collections.sort(dbos, new Comparator<JsonObject>() {
				@Override
				public int compare(JsonObject o1, JsonObject o2) {
					int flag = (new Double(o1.get("count").getAsDouble()).compareTo(new Double(o2.get("count").getAsDouble())));
					return -flag;
				}
			});
			for (int i = (page-1)*size; i < dbos.size() && i < page*size; i++) {
				ja.add(dbos.get(i));
			}
			int tpids = dbos.size();
			if (tpids < size) {
				tpages = 1;
			}else if (tpids % size == 0) {
				tpages = tpids/size;
			}else {
				tpages = tpids/size + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
		jo.add("data", ja);
		jo.addProperty("totalpage", tpages);
		jo.addProperty("cupage", page);
		return jo.toString();
		
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(StatMongoModel.getInstance().getSourceClickMongo("SSYH", "2014-08-25", "2014-08-25", 2, 3, null, null));
	}
	
}
