package com.netting.cache.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netting.api.AddItemsServlet;
import com.netting.bean.MixStylePageBean;
import com.netting.bean.Tag_Bean;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.redis.executor.JedisHashManager;
import com.netting.redis.executor.JedisKeyManager;
import com.netting.redis.executor.JedisSetManager;
import com.netting.redis.executor.JedisSortedSetManager;

/**
 * 商品管理---REDIS缓存更新操作类
 * @author YAOJIANBO
 *
 */
public class Admin_Tag_Item_Cache_DAO 
{
	private static final String TAG_PRODUCTS = "youhui.cn.tag.products.";
	
	private static final String TAG_ITEMS4TAG = "tag.items4tag";
	
	private static final String TAG_TO_ITEM = "tag.tag2item";
	
	private static final String PREFIX = "youhui.cn.tag.m_mix_pagetype.";
	
	private static final String TAG_ITEMS = "styletag.items4tag";
	
	/**
	 * 添加或者更新缓存REDIS中的商品数据，key=youhui.cn.tag.products.
	 * @param bean
	 */
	public static void add_update_Item(TeJiaGoodsBean bean)
	{
		Gson gson = new Gson();
		String json = gson.toJson(bean);
		JedisHashManager.set(TAG_PRODUCTS, bean.getItem_id(), json);
	}
	
	/**
	 * 删除标签下的商品数据
	 * @param keyword
	 */
	public static void delTagFaceItem(String tag_name, String item_id)
	{
		JedisSortedSetManager.delete(TAG_ITEMS4TAG + tag_name, item_id);
		
		// 检查该商品是否是标签的封面商品，若是，删除封面
		String faceItemID = getTagFaceItem(tag_name);
		if (faceItemID == null)
		{
			TeJiaGoodsBean bean = Admin_Tag_Item_DAO.getTagFaceItem(tag_name);
			if (bean != null)
			{
				faceItemID = bean.getItem_id();
			}
		}
		if (faceItemID != null && faceItemID.equals(item_id))
		{
			JedisHashManager.delete(TAG_PRODUCTS + "show_index", tag_name);
		}
	}
	
	/**
	 * 从redis里面删除商品的数据
	 * @param item_id
	 */
	public static void delItem(String item_id)
	{
		JedisHashManager.delete(TAG_PRODUCTS, item_id);
	}
	
	/**
	 * 删除商品与标签的对应关系,sortedset类型，key=tag.tag2item
	 * @param tag_id
	 * @param item_id
	 */
	public static void delItem2Tag(String tag_id, String item_id)
	{
		JedisSortedSetManager.delete(TAG_TO_ITEM + tag_id, item_id);
	}
	
	
	public static void addItem2Tag(String tag_id, String item_id, double rank)
	{
		JedisSortedSetManager.add(TAG_TO_ITEM + tag_id, rank, item_id);
	}
	
	public static void delTagItems(String tag_id)
	{
		JedisKeyManager.del(TAG_TO_ITEM + tag_id);
	}
	
	
	public static int getSmallRank(String tagId){
		int rank = 0;
		Map<String, Double> map = JedisSortedSetManager.getRangeWithScores(TAG_TO_ITEM+tagId, 0, 0);
//		System.out.println(map.size()+"map");
		for(Map.Entry<String, Double> m : map.entrySet()){
//			String itemId = m.getKey();
			double rankb = m.getValue();
			rank = (int)(rankb);
		}
		return rank-1;
	}
	
	/**
	 * 获取标签的封面商品的ID,hash类型，key=youhui.cn.tag.products.show_index
	 * @param tag_name
	 * @return
	 */
	public static String getTagFaceItem(String tag_name)
	{
		String faceItemID = JedisHashManager.get(TAG_PRODUCTS + "show_index", tag_name);
		if (null == faceItemID || "".equals(faceItemID))
		{
			return null;
		}
		else
		{
			return faceItemID;
		}
	}
	
	/**
	 * 添加混排信息
	 * @param bean
	 */
	public static void addStyleProduct(MixStylePageBean bean) {
			Gson gson = new Gson();
			String json = gson.toJson(bean);
			JedisHashManager.set(PREFIX,bean.getItem_ids(),json);
			JedisSortedSetManager.add(TAG_ITEMS+ bean.getTag_id(), Admin_Tag_Item_DAO.getMaxPosition() - 1,bean.getItem_ids());
	}
	
	public static void delStyleProduct(String tagid,String itemids) {

		JedisHashManager.delete(PREFIX, itemids);
		JedisSortedSetManager.delete(TAG_ITEMS + tagid, itemids);
}
	/**
	 * 修改混排信息
	 * @param bean
	 * @param oldPids
	 */
	public static void updateStyleProduct(MixStylePageBean bean,String oldPids) {

			Gson gson = new Gson();
			String json = gson.toJson(bean);
			JedisHashManager.set(PREFIX,bean.getItem_ids(),json);
			JedisSortedSetManager.add(TAG_ITEMS+ bean.getTag_id(),Double.parseDouble(bean.getRank()),bean.getItem_ids());
			
			JedisHashManager.delete(PREFIX, oldPids);
			JedisSortedSetManager.delete(TAG_ITEMS + bean.getTag_id(), oldPids);
			
	}
	
	public static String getItem(String itemId){
		String json = JedisHashManager.get(TAG_PRODUCTS, itemId);
		return json;
	}
	
	public static boolean reloadCache(String tagid) {

		boolean result = false;
	
			Set<String> sets =  JedisSortedSetManager.getAll(TAG_ITEMS + tagid) ;  
			for (String key : sets) {
				JedisSortedSetManager.delete(TAG_ITEMS + tagid, key);
			}
//			JedisHashManager.clean(PREFIX);
			List<MixStylePageBean> list = Admin_Tag_Item_DAO.getAllMixStylePageByTagid(tagid);
			for(MixStylePageBean bean:list) {
		
				Gson gson = new Gson();
				String json = gson.toJson(bean);
				
				JedisHashManager.set(PREFIX, bean.getItem_ids(), json);
				JedisSortedSetManager.add(TAG_ITEMS + bean.getTag_id(), Double.parseDouble( bean.getRank()), bean.getItem_ids());
				result  = true;
	}
			return result;
	}
	
	public static long getTotalPage(String tagid){
		long total = 0;
		total = JedisSortedSetManager.size(TAG_TO_ITEM+tagid);		
		if(total % 20 != 0){
			total = total/20 + 1;
		}else {
			total = total/20;
		}
		return total;
	}
	
	public static String getItemsByTag(String tagid){
//		Set<String> idset = new JedisSortedSetManager(TAG_TO_ITEM + tagid).
//		zrange((page - 1)*pageSize, (page * pageSize - 1)); 
		Set<String> idset = JedisSortedSetManager.zrange(TAG_TO_ITEM + tagid, 0, -1);
		Iterator<String> it = idset.iterator(); 
		String itemids = "";
		while (it.hasNext()) {  
		  String str = it.next();
		  itemids = itemids + str+",";
//		  System.out.println(str);  
		} 
		return itemids;
	}
	
	public static String obtainUncheckProducts(){
		ArrayList<String> list = new ArrayList<String>();
		String cats[] = {"713","863","615"} ;
		for (int i = 0; i < cats.length; i++) {
			JedisSetManager manager =  new JedisSetManager(TAG_TO_ITEM +".imperct." + cats[i]);
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
	
	public static void addTag2Items(String tagId, String data){
		JsonParser jp = new JsonParser();
		JsonArray ob = jp.parse(data).getAsJsonArray();
		JedisSetManager manager =  new JedisSetManager(TAG_TO_ITEM+".imperct." + tagId);
		try {
			for (JsonElement e : ob) {
				JsonObject o = e.getAsJsonObject();
				Gson gson = new Gson();
				String json = Admin_Tag_Item_Cache_DAO.getItem(o.get("pid").getAsString());
				TeJiaGoodsBean bean = null;
				if (json != null) {
					bean = gson.fromJson(json, TeJiaGoodsBean.class);
				}
				if (bean == null) {
					bean = new TeJiaGoodsBean();
					bean.setItem_id(o.get("pid").getAsString());
					bean.setPrice_high(o.get("price").getAsString());
					bean.setPrice_low(o.get("price").getAsString());
					bean.setTitle(o.get("title").getAsString());
					bean.setPic_url(o.get("img").getAsString());
				}
				bean.setCatID(o.get("cid").getAsString());
				bean.setCategory_id(Integer.parseInt(tagId));
				Admin_Tag_Item_Cache_DAO.add_update_Item(bean);
				manager.add(bean.getItem_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void perfTag2Items(String data){
		JsonParser jp = new JsonParser();
		JsonArray ob = jp.parse(data).getAsJsonArray();
		for (JsonElement e : ob) {
			JsonObject o = e.getAsJsonObject();
			String json = Admin_Tag_Item_Cache_DAO.getItem(o.get("pid").getAsString());
			Gson gson = new Gson();
			TeJiaGoodsBean bean = null;
			if (json != null) {
				bean = gson.fromJson(json, TeJiaGoodsBean.class);
			}
			if (bean != null) {
				bean.setRate(o.get("rate").getAsDouble());
				int tagId = bean.getCategory_id();
				Tag_Bean tag_bean_p = Admin_Tag_DAO.getTagBean(tagId+"");
				String llTag = bean.getCatID();
				Tag_Bean tag_bean_s = Admin_Tag_DAO.getTagBean(llTag);
				AddItemsServlet.addTagItem(tag_bean_p, bean);
				AddItemsServlet.addTagItem(tag_bean_s, bean);
				Admin_Tag_Item_Cache_DAO.add_update_Item(bean);
				JedisSetManager manager =  new JedisSetManager(TAG_TO_ITEM+".imperct." + tagId);
				manager.rem(bean.getItem_id());
			}else {
				removeImperfectItem(o.get("pid").getAsString());
			}
		}
	}
	
	public static void removeImperfectItem(String item){
		String cats[] = {"713","863","615"} ;
		for (int i = 0; i < cats.length; i++) {
			JedisSetManager manager =  new JedisSetManager(TAG_TO_ITEM+".imperct." + cats[i]);
			manager.rem(item);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getTotalPage("569"));
	}
}
