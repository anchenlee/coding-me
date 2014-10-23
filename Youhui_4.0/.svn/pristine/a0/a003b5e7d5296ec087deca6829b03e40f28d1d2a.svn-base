package com.netting.ItemUtils;

import java.util.HashMap;

import com.netting.action.admin.AdminLoginAction;
import com.netting.bean.TagToItemBean;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.util.CodeUtil;

/**
 * 商品修改操作
 * @author belonghu
 *
 */
public class UpdateItemOperation {

	/**
	 * 商品添加到子标签
	 * @return
	 */
	public static String addItemToCtag(HashMap<String,String> map){
		String ctagid = map.get("ctagId");
		String itemid = map.get("itemId");  
		String rank = map.get("rank");
		String flag = "";
		
		flag = Admin_Tag_Item_DAO.addTagtoItem(ctagid, itemid, rank);
		if(!"".equals(flag)) 
		{			
			Admin_Tag_Item_Cache_DAO.addItem2Tag(ctagid, itemid, Integer.parseInt(flag));			
			return CodeUtil.getRespJSONString("0","");
    	}
    	else
    	{
    		return CodeUtil.getRespJSONString("1","");
    	}
	}
	
	/**
	 * 商品取消对应的子标签
	 * @return
	 */
	public static String delItemToCtag(HashMap<String,String> map){
		String ctagid = map.get("ctagId");
		String itemid = map.get("itemId");    
		boolean flag = false;
		
		flag = Admin_Tag_Item_DAO.delTagtoItem(ctagid, itemid);
		
		if(flag){
			Admin_Tag_Item_Cache_DAO.delItem2Tag(ctagid, itemid);
			
			return CodeUtil.getRespJSONString("0","");
    	}else{
    		return CodeUtil.getRespJSONString("1","");
    	}
	}
	
	/**
	 * 商品锁定个数
	 * @return
	 */
	public static String lockTagItem(HashMap<String,String> map){
		String tagid = map.get("tagId");
    	String statue = map.get("statue");
    	
    	boolean flag = false;
    	
    	if (tagid == null || "".equals(tagid)) {
    		return CodeUtil.getRespJSONString("1","");
    		
    	}else{
    		flag = Admin_Tag_Item_DAO.unlock(tagid, statue);
    	}
    	
    	if (flag){
    		return CodeUtil.getRespJSONString("0","");
    	}else 
    	{
    		return CodeUtil.getRespJSONString("1","");
    	}
	}
	
	/**
	 * 商品位置移动
	 * @return
	 */
	public static String movePosition(HashMap<String,String> map){
		
		String tag_id = map.get("tagId");
    	String itemid0 = map.get("itemId");
    	String rank0 = map.get("rank");
    	String itemid1 = "";
    	String rank1 = "";
    	String type = map.get("type");
    	boolean flag = false;
//    	System.out.println(rank0+itemid0);
		if(itemid0 == null ||rank0 == null ||tag_id == null)
		{
			return CodeUtil.getRespJSONString("1","");
		}
		TagToItemBean bean = null;
			
		if(type.equals("1")){ //上移
			bean = Admin_Tag_Item_DAO.getNextRankBean(rank0,tag_id);				
		}
		else if(type.equals("2")){	//下移
			bean = Admin_Tag_Item_DAO.getPreRankBean(rank0,tag_id);
		}else if(type.equals("3")) {	//首位
			bean = Admin_Tag_Item_DAO.getMinRankBean(tag_id);
		}else 	if(type.equals("4")){	//末尾
			bean = Admin_Tag_Item_DAO.getMaxRankBean(tag_id);
		}else if(type.equals("5")){
			//移动到本页首位
			itemid1 = map.get("itemId1");
			rank1 = map.get("rank1");
			bean = new TagToItemBean();
			bean.setItemid(itemid1);
			bean.setRank(Integer.parseInt(rank1));
		}
		else if(type.equals("6")){//移动到本页末
			itemid1 = map.get("itemId1");
			rank1 = map.get("rank1");
			bean = new TagToItemBean();
			bean.setItemid(itemid1);
			bean.setRank(Integer.parseInt(rank1));
		}
		if(bean!=null) {
			itemid1 = bean.getItemid();
			rank1 = bean.getRank()+"";
		}else if(bean == null){
			return CodeUtil.getRespJSONString("1","");
		}
		flag = Admin_Tag_Item_DAO.moveProduct(tag_id, itemid0, Integer.parseInt(rank0), itemid1,Integer.parseInt(rank1), type);
		if(flag) {
			if(type.equals("3")){	//首位
				Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_id, itemid0, Integer.parseInt(rank1)-1);
			}else if(type.equals("4")){	//末尾
				Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_id, itemid0, Integer.parseInt(rank1)+1);
			}else{
				Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_id, itemid0, Integer.parseInt(rank1));
				Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_id, itemid1, Integer.parseInt(rank0));
			}
		}
			
		if(flag){
			return CodeUtil.getRespJSONString("0","");
		}else{
			return CodeUtil.getRespJSONString("1","");
		}
		
	}
	
	
}
