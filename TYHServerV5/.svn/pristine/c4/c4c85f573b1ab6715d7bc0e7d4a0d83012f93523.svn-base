package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.X;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisSetManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhui.utils.JfbRateUtil;

public class Tag2ItemCacher {

	private static String cacheKey = X.CachePrefix.TAG_TO_ITEM;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static final Logger log = Logger.getLogger(Tag2ItemCacher.class);
	private static Tag2ItemCacher instance = null;

	private Tag2ItemCacher() {
	}

	public static Tag2ItemCacher getInstance() {
		return instance == null ? (instance = new Tag2ItemCacher()) : instance;
	}
	
	public long getItemsSizeByTagid(String tagid)
	{
		long size = new JedisSortedSetManager(cacheKey + tagid).size();
		return size;
	}
	
	public List<TeJiaGoodsBean> getItemsByTagid(String tagid, int page, int pageSize)
	{
		List<TeJiaGoodsBean> ret = new ArrayList<TeJiaGoodsBean>();
		Set<String> idset = new JedisSortedSetManager(cacheKey + tagid).
				zrange((page - 1)*pageSize, (page * pageSize - 1)); 
		
		if(idset != null && idset.size() > 0)
		{
			for(String id : idset)
			{
				TeJiaGoodsBean bean = TagItemCacher.getInstance().getProduct(id);
				if(tagid.equals("1017")){
					System.out.println("------------->>>>>>>>>>>>>>>"+bean);
				}
				if(bean != null)
				{
					ret.add(bean);
				}
			}
			return ret;
		}
		else
		{
			return null;
		}
	}
	
	private double getrand(){
		return new Random().nextDouble()*2;
	}

	public List<TeJiaGoodsBean> getTag569ItemsByUid(String tagid, String uid, int pageCount, int page, int pageSize)
	{
		if (page < 1)
		{
			page = 1;
		}
		else if (page > pageCount)
		{
			page = pageCount;
		}
		
		List<TeJiaGoodsBean> ret = new ArrayList<TeJiaGoodsBean>();
		Set<String> idset = null;
		
		int tag569PageCount = this.getTotalPage(tagid + uid, pageSize);
		if (tag569PageCount >= 1)
		{
			if (page <= tag569PageCount)
			{
				idset = new JedisSortedSetManager(cacheKey + tagid + uid).zrange((page - 1) * pageSize, (page * pageSize - 1));
				if (idset != null && idset.size() > 0 && idset.size() < pageSize)
				{
					idset.addAll(new JedisSortedSetManager(cacheKey + tagid).zrange(0, (pageSize - idset.size() - 1)));
				}
			}
			else
			{
				long size1 = new JedisSortedSetManager(cacheKey + tagid + uid).size();
				
				int startPosition = (int) ((page - 1) * pageSize - size1);
				int endPosition = startPosition + pageSize -1;
				
				idset = new JedisSortedSetManager(cacheKey + tagid).zrange(startPosition, endPosition); 
			}
		}
		else
		{
			idset = new JedisSortedSetManager(cacheKey + tagid).zrange((page - 1)*pageSize, (page * pageSize - 1));
		}
		
		if(idset != null && idset.size() > 0)
		{
			for(String id : idset)
			{
				TeJiaGoodsBean bean = TagItemCacher.getInstance().getProduct(id);
				if(bean != null)
				{
					ret.add(bean);
				}
			}
			return ret;
		}
		else
		{
			return null;
		}
	}
	
	public int getTotalPage(String tagid, int pageSize)
	{
		long size = new JedisSortedSetManager(cacheKey + tagid).size();
		int ys = (int) (size % pageSize);
		int ret = (int) ((size - ys) / pageSize);
		if(ys > 0)
		{
			ret++;
		}
		return ret;
	}
	
	public int getTag569TotalPageByUid(String tagid, String uid, int pageSize)
	{
		long size = 0l;
		
		long size1 = new JedisSortedSetManager(cacheKey + tagid).size();
		long size2 = new JedisSortedSetManager(cacheKey + tagid + uid).size();
		
		size = size1 + size2;
		
		int ys = (int) (size % pageSize);
		int ret = (int) ((size - ys) / pageSize);
		if(ys > 0)
		{
			ret++;
		}
		return ret;
	}
	
	/**
	 * 更新tag下商品
	 * @param tagId
	 * @param itemIds
	 */
	public void refreshTag2Item(String tagId, List<TeJiaGoodsBean> items){
		if(items != null && items.size() > 0)
		{
			int rank = 0;
			new JedisKeyManager(cacheKey + tagId).del();
			for(TeJiaGoodsBean item : items)
			{
				new JedisSortedSetManager(cacheKey + tagId).add(rank++, item.getItem_id());
			}
		}
	}
	
	public String obtainUncheckProducts(){
		ArrayList<String> list = new ArrayList<String>();
		String cats[] = {"713","863","615","909"} ;
		for (int i = 0; i < cats.length; i++) {
			JedisSetManager manager =  new JedisSetManager(cacheKey+".imperct." + cats[i]);
			Set<String> pros = manager.getAll();
			list.addAll(pros);
		}
		String buffer = "";
		for (int i = 0; i < list.size()-1; i++) {
			buffer += list.get(i)+","; 
		}
		if (list.size() > 0) {
			buffer += list.get(list.size()-1);
		}
		//System.out.println(buffer);
		return buffer;
	}
	
	public void addTag2Items(String tagId, String data){
		JsonParser jp = new JsonParser();
		JsonArray ob = jp.parse(data).getAsJsonArray();
		JedisSetManager manager =  new JedisSetManager(cacheKey+".imperct." + tagId);
		for (JsonElement e : ob) {
			JsonObject o = e.getAsJsonObject();
			TeJiaGoodsBean bean = TagItemCacher.getInstance().getProduct(o.get("pid").getAsString());
			if (bean == null) {
				bean = new TeJiaGoodsBean();
				bean.setItem_id(o.get("pid").getAsString());
				bean.setPrice_high(o.get("price").getAsString());
				bean.setPrice_low(o.get("price").getAsString());
				bean.setTitle(o.get("title").getAsString());
				bean.setPic_url(o.get("img").getAsString());
			}else {
				bean.setTagId(tagId+"-"+o.get("cid").getAsString());
				bean.setPrice_high(o.get("price").getAsString());
				bean.setPrice_low(o.get("price").getAsString());
				bean.setTitle(o.get("title").getAsString());
			}
			TagItemCacher.getInstance().addProduct(bean);
			manager.add(bean.getItem_id());
		}
	}
	
	public void perfTag2Items(String data){
		JsonParser jp = new JsonParser();
		JsonArray ob = jp.parse(data).getAsJsonArray();
		for (JsonElement e : ob) {
			JsonObject o = e.getAsJsonObject();
			TeJiaGoodsBean bean = TagItemCacher.getInstance().getProduct(o.get("pid").getAsString());
			if (bean != null) {
				bean.setRate(o.get("rate").getAsDouble());
				String tagId = bean.getTagId();
				String ids[] = tagId.split("-");
				if (ids.length != 2) {
					continue;
				}
				TagItemCacher.getInstance().removeProduct(o.get("pid").getAsString());
				TagItemCacher.getInstance().addProduct(bean);
				JedisSetManager manager =  new JedisSetManager(cacheKey+".imperct." + ids[0]);
				JedisSortedSetManager manager1 =  new JedisSortedSetManager(cacheKey + ids[0]);
				manager.rem(bean.getItem_id());
				Double rank = Tag2ItemCacher.getInstance().getSmallScore(ids[0]);
				manager1.add(--rank, bean.getItem_id());
				JedisSortedSetManager manager2 =  new JedisSortedSetManager(cacheKey + ids[1]);
				Double rank2 = Tag2ItemCacher.getInstance().getSmallScore(ids[1]);
				manager2.add(--rank2, bean.getItem_id());
			}else {
				removeImperfectItem(o.get("pid").getAsString());
			}
		}
	}
	
	public Double getSmallScore(String tagId){
		JedisSortedSetManager manager =  new JedisSortedSetManager(cacheKey+ tagId);
		Double score = 0.0;
		Set<String> a = manager.zrange(0, 0);
		//System.out.println(a.toString());
		if (a != null && a.size()>0) {
			String member = "";
			for (String string : a) {
				member = string;
			}
			score = manager.getScore(member);
		}
		return score;
	}
	
	public void removeImperfectItem(String item){
		String cats[] = {"713","863","615"} ;
		for (int i = 0; i < cats.length; i++) {
			JedisSetManager manager =  new JedisSetManager(cacheKey+".imperct." + cats[i]);
			manager.rem(item);
		}
	}
	
	public boolean init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			public Boolean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				jedis.del(cacheLock);
				String cacheStatus = jedis.get(cacheLock);
				if (!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					log.info("tag2itemcacher init............");
				    jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
				try{
					Statement s = conn.createStatement();
					String sql = "SELECT * FROM `youhui_datamining`.`m_tagtoitem` where `rel_status` = 1 ORDER BY `rank` asc;";
					ResultSet rs = s.executeQuery(sql);
					String tagids = "";
					while (rs.next()){
						if(!tagids.contains(rs.getString("tag_id"))){		
							tagids += rs.getString("tag_id");
							jedis.del(cacheKey + rs.getString("tag_id"));
						}
						jedis.zadd(cacheKey + rs.getString("tag_id"), rs.getDouble("rank"), rs.getString("item_id"));
					}
				}catch(Exception e){
					log.error("tag2itemcacher init exception  " +e.getMessage());
				}finally{
					jedis.del(cacheLock);
				}
				}
				return true;
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		return e.exe(r);
	}
	
	public void reload(){
		init();
	}
	
	public static void main(String[] args){
		//String tagId = "863";
		//new JedisKeyManager(cacheKey + tagId).del();
		//long rank = new JedisSortedSetManager(cacheKey + tagId).size();
		//System.out.println("================1=============================>"+rank);
		//String data = "[{\"pid\": \"17130960796\", \"price\": \"19.00\", \"cid\": 8404, \"title\": \"\\u62c9\\u6bd4\\u5706\\u68a6\\u5b9d \\u8d85\\u8584\\u77ed\\u8896\\u77ed\\u88e4\\u5957\\u88c5\", \"img\": \"http://img.taobaocdn.com/bao/uploaded/T1qREzFTBaXXb1upjX.jpg_210x210.jpg\"}, {\"pid\": \"13955910598\", \"price\": \"58.60\", \"cid\": 8404, \"title\": \"\\u5a74\\u513f\\u793c\\u76d2\\u5957\\u88c5\", \"img\": \"http://img.taobaocdn.com/bao/uploaded/T1bRUnFRJbXXb1upjX.jpg_210x210.jpg\"}, {\"pid\": \"38732930571\", \"price\": \"29.00\", \"cid\": 8404, \"title\": \"5\\u53cc\\u7537\\u7ae5\\u5973\\u7ae5\\u7eaf\\u68c9\\u889c\\u5b50\", \"img\": \"http://img.taobaocdn.com/bao/uploaded/T1swfmFCdgXXb1upjX.jpg_210x210.jpg\"}, {\"pid\": \"25115504673\", \"price\": \"29.70\", \"cid\": 8404, \"title\": \"\\u590f\\u5b63\\u900f\\u6c14\\u7ae5\\u978b\", \"img\": \"http://img.taobaocdn.com/bao/uploaded/T1DKgAFMXaXXb1upjX.jpg_210x210.jpg\"}, {\"pid\": \"38952067653\", \"price\": \"49.00\", \"cid\": 8404, \"title\": \"\\u590f\\u88c5\\u97e9\\u7248\\u513f\\u7ae5\\u4f11\\u95f2\\u77ed\\u88e4\\u8fd0\\u52a8\\u5957\\u88c5\", \"img\": \"http://img.taobaocdn.com/bao/uploaded/T1XS7zFL8dXXb1upjX.jpg_210x210.jpg\"}]";
		//Tag2ItemCacher.getInstance().addTag2Items(tagId, data);
		String tagId = "713";
		Double rank = Tag2ItemCacher.getInstance().getSmallScore(tagId+"");
		System.out.println(rank);
	}
}
