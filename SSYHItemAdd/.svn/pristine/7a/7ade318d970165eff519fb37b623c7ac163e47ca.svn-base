package cn.youhui.admin.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.youhui.bean.TagBean;
import cn.youhui.cache.dao.RecoItemCacheDAO;
import cn.youhui.cache.dao.SearchKeywordCacheDAO;
import cn.youhui.cache.dao.TagItemCacheDAO;


public class DelItemUtil {

	/**
	 * 删除商品信息（同时删除子、父标签对应信息）
	 * @param tagId
	 * @param item_ids
	 * @return
	 */
	public static boolean delTagItem(String tagId,String item_ids){
		boolean flag = false;
		List<String> tagList = AdminTagItemDAO.getKeywordsForPaixu(tagId);
		tagList.add(tagId);
		TagBean partentTag = AdminTagDAO.getTagBean(tagId);
		if(partentTag != null && !"".equals(partentTag.getParent_tag_id())){
			tagList.add(partentTag.getParent_tag_id());
		}
		
		String[] itemids = item_ids.split(",");
    	for (String id : itemids){
    		if (null == id || "".equals(id)){
    			continue;
    		}
    		//删除操作时，将m_discount_products 集分宝比例修改为0，同时将item_jfb_rate当前比例修改为0
    		AdminTagItemDAO.addItemJfbRateHistory(id, "0");
//    		Admin_Tag_Item_Cache_DAO.delTagFaceItem(tag_name, id);
    		for(String ctagid : tagList){
    			boolean isExit = AdminTagItemDAO.isExit(id, ctagid);
    			if(isExit){   				
    				flag = AdminTagItemDAO.delItem2Tag(id, ctagid);
    				TagItemCacheDAO.delItem2Tag(ctagid, id);
    			}
    		}
		}
		return flag;
	}
	
	/**
	 * 删除商品信息（删除所有标签下商品信息）
	 * @param item_ids
	 * @return
	 */
	public static boolean delAllTagItem(String item_ids){
		boolean flag = false;
		String[] itemids = item_ids.split(",");
    	for (String id : itemids)
    	{
    		if (null == id || "".equals(id))
    		{
    			continue;
    		}
    		ArrayList<String> tagid_list = AdminTagItemDAO.getTagListByItemID(id);
    		//删除操作时，将m_discount_products 集分宝比例修改为0，同时将item_jfb_rate当前比例修改为0
    		AdminTagItemDAO.addItemJfbRateHistory(id, "0");
    		if (null != tagid_list && tagid_list.size() > 0)
    		{
    			for (String tagID : tagid_list)
        		{
        			if (tagID == null || tagID.equals("") || tagID.equalsIgnoreCase("null"))
        			{
        				continue;
        			}
        			TagItemCacheDAO.delItem2Tag(tagID, id);
        		}
    		}
    		
    		AdminTagItemDAO.delItem(id);
    		TagItemCacheDAO.delItem(id);
    		flag = true;
		}
    	return flag;
	}
	
	/**
	 * 删除关键词对应的itemid
	 * @param itemid
	 * @param keywordId
	 * @return
	 */
	public static boolean delKeywordItem(String itemid,String keywordId){
		boolean flag = false;
		String itemids[] = itemid.split(",");
		for(String id : itemids){
			flag = AdminSearchKeyWordDAO.delKeywordItem(keywordId, id);
			SearchKeywordCacheDAO.delItem2Keyword(keywordId, id);
		}
		return flag;
	}
	
	public static boolean delRecoItem(String itemid){
		boolean flag = false;
		long rank = AdminRecoItemDAO.getItemRank(itemid);
		boolean flag1 = AdminRecoItemDAO.delRecoItem(itemid);
		if(rank != 0 && flag1){
			Map<String ,Double> map = RecoItemCacheDAO.getMap(rank, rank);
			String member = "";
			Iterator<Entry<String, Double>> iter = map.entrySet().iterator();

			Entry<String, Double> entry;

			if (iter.hasNext()) {
				entry = iter.next();
				member = entry.getKey();
				RecoItemCacheDAO.delRecoItem(member);
				flag = true;
			}
		}
		return flag;
	}
	
	//通过商品id删除所有下面商品信息
	public static boolean delItemAll(String itemid){
		boolean flag = false;
		try {			
			//删除标签商品
			flag = delAllTagItem(itemid);
			
			//删除关键词商品
			List<String> keywordList = AdminSearchKeyWordDAO.getAllKeywordByItemid(itemid);
			if(keywordList != null && keywordList.size() > 0){
				for(String keywordId : keywordList){				
					delKeywordItem(itemid, keywordId);
				}
			}		
			
			//删除推荐商品
			delRecoItem(itemid);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
	}
	
	
	
}
