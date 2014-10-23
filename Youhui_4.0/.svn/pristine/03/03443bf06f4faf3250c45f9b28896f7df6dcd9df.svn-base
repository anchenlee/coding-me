package com.netting.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.netting.bean.TagToItemBean;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.bean.KeyCategoryBean.KeywordBean;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.redis.executor.JedisSortedSetManager;



public class GetTagItemFromRedis {

	private static String cacheKey = "tag.tag2item";
	
	public static int getTotalPage(String tagid, int pageSize)
	{
//		long size = new JedisSortedSetManager(cacheKey + tagid).size();
		long size = JedisSortedSetManager.size(cacheKey);
		int ys = (int) (size % pageSize);
		int ret = (int) ((size - ys) / pageSize);
		if(ys > 0)
		{
			ret++;
		}
		return ret;
	}
	
	public static List<TeJiaGoodsBean> getItemsByTagid(String tagid, int page, int pageSize)
	{
		List<TeJiaGoodsBean> ret = new ArrayList<TeJiaGoodsBean>();
//		Set<String> idset = new JedisSortedSetManager(cacheKey + tagid).
//				zrange((page - 1)*pageSize, (page * pageSize - 1)); 
//		Set<String> idset = JedisSortedSetManager.zrange(cacheKey + tagid, (page - 1)*pageSize, (page * pageSize - 1));
//		
//		if(idset != null && idset.size() > 0)
//		{
//			for(String id : idset)
//			{
			ret = getItems(tagid,(page - 1)*pageSize,(page * pageSize - 1));
//				if(bean != null)
//				{
//					ret.add(bean);
//				}
//			}
			return ret;
//		}
//		else
//		{
//			return null;
//		}
	}
	
	
	public static List<TeJiaGoodsBean> getItems(String tagid,int start,int end) {
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
//			Gson gson = new Gson();
//			String json = JedisHashManager.get(cacheKey, itemId);
//			if (json != null) {
//				bean = gson.fromJson(json, TeJiaGoodsBean.class);
//				Map<String, Double> map = JedisSortedSetManager.getRangeWithScores(cacheKey, start, end);
//				for(Map.Entry<String, Double> m : map.entrySet()){
//					String j = m.getKey();
//					int rank = (int)(m.getValue());
//					
//					TeJiaGoodsBean bean = Product.getP();
//					bean.setRank(rank);
//					
//				}
//			}
//			if (bean == null) {
//				bean  = Admin_Tag_Item_DAO.getTagItemBean(itemId);
//				
//			}
			
			
			Map<String, Double> map = JedisSortedSetManager.getRangeWithScores(cacheKey+tagid, start, end);
//			System.out.println(map.size()+"map");
			for(Map.Entry<String, Double> m : map.entrySet()){
				String itemId = m.getKey();
				double rankb = m.getValue();
				int rank = (int)(rankb);
				Gson gson = new Gson();
				String json = Admin_Tag_Item_Cache_DAO.getItem(itemId);
				if (json != null) {
				TeJiaGoodsBean bean = gson.fromJson(json, TeJiaGoodsBean.class);
//				TeJiaGoodsBean bean = JedisSortedSetManager.g
				bean.setRank(rank);
				try {
//					System.out.println(bean.getUpdate_time());
					String updateTime = CodeUtil.getDateTimeStr(Long.parseLong(bean.getUpdate_time()));
					bean.setUpdate_time(updateTime);
				} catch (Exception e) {
					// TODO: handle exception
				}
				String discount = bean.getDiscount();
				try {
					double dis = Double.parseDouble(discount) / 10;
					DecimalFormat df = new DecimalFormat("#.00");
			        bean.setDiscount(df.format(dis));
				} catch (Exception e) {
					// TODO: handle exception
				}
				list.add(bean);
				}
			}
		return list;
	}
	
	public static List<TagToItemBean> getTagItemList(String tagid,int total){
		int begin = Integer.parseInt(Admin_Tag_Item_DAO.isLocked(tagid));
		List<TagToItemBean> list = new ArrayList<TagToItemBean>();
		Map<String, Double> map = JedisSortedSetManager.getRangeWithScores(cacheKey+tagid, begin, total-1);
//		System.out.println(map.size()+"map");
		for(Map.Entry<String, Double> m : map.entrySet()){
			String itemId = m.getKey();
			double rankb = m.getValue();
			int rank = (int)(rankb);
			TagToItemBean bean = new TagToItemBean();
			bean.setRank(rank);
			bean.setItemid(itemId);
			bean.setTagid(tagid);
			list.add(bean);
		}
		return list;
	}
	
	public static List<KeywordBean> getCtags(){
		List<KeywordBean> list = new ArrayList<KeywordBean>();
		
		return list;
	}

	public static void main(String[] args) {
//		List<TagToItemBean> list = getTagItemList("569",40);
//		System.out.println(list.size());
//		for(TagToItemBean bean : list){
//			System.out.println(bean.getItemid()+"   "+bean.getRank());
//		}
		Map<String, Double> map = JedisSortedSetManager.getRangeWithScores(cacheKey+"569", 0, 0);
//		System.out.println(map.size()+"map");
		for(Map.Entry<String, Double> m : map.entrySet()){
			String itemId = m.getKey();
			double rankb = m.getValue();
			int rank = (int)(rankb);
			System.out.println(rank+"    "+itemId);
		}
	}
}
