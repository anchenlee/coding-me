package com.netting.ItemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.netting.action.admin.AdminLoginAction;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.util.CodeUtil;

/**
 * 商品删除操作
 * @author belonghu
 *
 */
public class DelItemOperation {

	/**
	 * 删除标签以及相关下商品
	 * @param map
	 * @return
	 */
	public static String delTagItem(HashMap<String,String> map){
		String itemIds = map.get("itemId");
		itemIds = itemIds.trim();
    	
    	String tag_id = map.get("tagId");
    	String parentId = map.get("parentId");
    	
    	//获取标签下的子标签或者父标签，同时删除父标签或子标签与商品的关系
    	List<String> tagList = Admin_Tag_Item_DAO.getKeywordsForPaixu(tag_id);
    	tagList.add(parentId);
    	tagList.add(tag_id);
    	
    	String[] itemids = itemIds.split(",");
    	for (String id : itemids){
    		if (null == id || "".equals(id)){
    			continue;
    		}
    		//删除操作时，将m_discount_products 集分宝比例修改为0，同时将item_jfb_rate当前比例修改为0
    		Admin_Tag_Item_DAO.addItemJfbRateHistory(id, "0");
//    		Admin_Tag_Item_Cache_DAO.delTagFaceItem(tag_name, id);
    		for(String ctagid : tagList){  			
    			Admin_Tag_Item_DAO.delItem2Tag(id, ctagid);
    			Admin_Tag_Item_Cache_DAO.delItem2Tag(ctagid, id);
    		}
		}
		return CodeUtil.getRespJSONString("1","");
		
	}
	
	/**
	 * 将商品完全删除(删除与所有标签的归属关系)
	 * @param map
	 * @return
	 */
	public static String delTagItemAll(HashMap<String,String> map){
		String itemIds = map.get("itemId");
		itemIds = itemIds.trim();
    	
    	String[] itemids = itemIds.split(",");
    	for (String id : itemids){
    		if (null == id || "".equals(id)){
    			continue;
    		}
    		ArrayList<String> tagid_list = Admin_Tag_Item_DAO.getTagListByItemID(id);
    		//删除操作时，将m_discount_products 集分宝比例修改为0，同时将item_jfb_rate当前比例修改为0
    		Admin_Tag_Item_DAO.addItemJfbRateHistory(id, "0");
    		if (null != tagid_list && tagid_list.size() > 0){
    			for (String tagID : tagid_list){
        			if (tagID == null || tagID.equals("") || tagID.equalsIgnoreCase("null")){
        				continue;
        			}
        			Admin_Tag_Item_Cache_DAO.delItem2Tag(tagID, id);
        		}
    		}
    		
    		Admin_Tag_Item_DAO.delItem(id);
    		Admin_Tag_Item_Cache_DAO.delItem(id);
    		
		}
		return CodeUtil.getRespJSONString("1","");
	}
}
