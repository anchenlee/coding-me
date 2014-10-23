package cn.youhui.cache.dao;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import cn.youhui.admin.dao.AdminTagItemDAO;
import cn.youhui.bean.TagBean;
import cn.youhui.bean.TagToItemBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.platform.config.param;
import cn.youhui.platform.jedis.JedisHashManager;
import cn.youhui.platform.jedis.JedisSortedSetManager;
import cn.youhui.platform.util.TimeUtil;



public class TagItemCacheDAO {

	private static final String TAG_TO_ITEM = "tag.tag2item";
	
	private static final String TAG_PRODUCTS = "youhui.cn.tag.products.";
	
	
	
	public static void addItem2Tag(String tagId, String itemId, double rank)
	{
		new JedisSortedSetManager(TAG_TO_ITEM + tagId).add(rank, itemId);
	}
	
	public static void addOrUpdateItem(TeJiaGoodsBean bean)
	{
		Gson gson = new Gson();
		String json = gson.toJson(bean);
		new JedisHashManager(TAG_PRODUCTS).add(bean.getItem_id(), json);
	}
	
	public static void delItem2Tag(String tag_id, String item_id)
	{
		new JedisSortedSetManager(TAG_TO_ITEM + tag_id).delete(item_id);
	}
	
	public static void delItem(String item_id)
	{
		new JedisHashManager(TAG_PRODUCTS).delete(item_id);
	}
	
	public static List<TeJiaGoodsBean> getItemsByTagid(String tagid, int page, int pageSize)
	{
		List<TeJiaGoodsBean> ret = new ArrayList<TeJiaGoodsBean>();
		ret = getItems(tagid,(page - 1)*pageSize,(page * pageSize));
		return ret;
	}
	public static void main(String[] args) {
		System.out.println(getItemsByTagid("632", 1, 10));
	}
	
	public static long getTotalPage(String tagid){
		long total = 0;
		total = new JedisSortedSetManager(TAG_TO_ITEM+tagid).size();		
		if(total % param.numPerPage != 0){
			total = total/20 + 1;
		}else {
			total = total/20;
		}
		return total;
	}
	
	public static List<TeJiaGoodsBean> getItems(String tagid,int start,int end) {
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
			
			
			Map<String, Double> map = new JedisSortedSetManager(TAG_TO_ITEM+tagid).getRangeWithScores( start, end);
			for(Map.Entry<String, Double> m : map.entrySet()){
				String itemId = m.getKey();
				double rankb = m.getValue();
				int rank = (int)(rankb);
				Gson gson = new Gson();
				String json = getItem(itemId);
				if (json != null) {
				TeJiaGoodsBean bean = gson.fromJson(json, TeJiaGoodsBean.class);
				bean.setRank(rank);
				try {
					String updateTime = TimeUtil.getDateTimeStr(Long.parseLong(bean.getUpdate_time()));
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
	
	public static String getItem(String itemId){
		String json = new JedisHashManager(TAG_PRODUCTS).get(itemId);
		return json;
	}
	
	
	
	
	/**
	 * 重新锁住前面的商品
	 * @return
	 */
	public static void reLockHeaderItem(TagBean tagBean,Connection conn)
	{
		// 重新锁住前N个
    	List<TagToItemBean> list = AdminTagItemDAO.getRankItemList(tagBean.getTag_id(),conn);
    	if (list != null && list.size() > 0)
    	{
    		for(TagToItemBean bean : list) 
        	{
        		TagItemCacheDAO.addItem2Tag(tagBean.getTag_id(), bean.getItemid(), bean.getRank());
        	}
    	}
	}
	
}
