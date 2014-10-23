package com.netting.job;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.JobDetialBean;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_JobDetail_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.dao.admin.TaobaoAPI_DAO;
import com.taobao.api.domain.TaobaokeItem;

/**
 * 刷新商品列表(删除已经不能存在或下架的商品)
 * @author YAOJIANBO
 *
 */
public class RefreshTagItemJob extends Thread 
{
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( RefreshTagItemJob.class );
	
	private String tag_id;
	
	private String tag_name;
	
	private String admin_username;
	
	public RefreshTagItemJob(String tag_id, String tag_name, String admin_username) 
	{
		this.tag_id = tag_id;
		this.tag_name = tag_name;
		this.admin_username = admin_username;
	}

	@Override
	public void run() 
	{
		// 记录任务至数据库
    	String nowTime = String.valueOf(System.currentTimeMillis());
    	JobDetialBean jobDetail = new JobDetialBean(admin_username + "_" + nowTime, tag_name + " 刷新商品列表", admin_username, nowTime);
		jobDetail.setSuccessNum(0);
		jobDetail.setAllNum(1);
		jobDetail.setStatus(0);
		jobDetail.setRemark("");
		Admin_JobDetail_DAO.addJobDetial(jobDetail);
		
		
		ArrayList<TeJiaGoodsBean> oldItemList = Admin_Tag_Item_DAO.getTagItemByTagID(tag_id);
    	ArrayList<String> del_ItemId_List = new ArrayList<String>();
    	
    	for (TeJiaGoodsBean bean : oldItemList)
    	{
    		TaobaokeItem tb_Item = TaobaoAPI_DAO.getItemWithOutSDK(bean.getItem_id());
    		if (tb_Item == null)
    		{
    			del_ItemId_List.add(bean.getItem_id());
    		}
    	}
    	
    	if (del_ItemId_List != null && del_ItemId_List.size() > 0)
    	{
    		for (String item_id : del_ItemId_List)
        	{
        		ArrayList<String> tagid_list = Admin_Tag_Item_DAO.getTagListByItemID(item_id);
        		if (null != tagid_list && tagid_list.size() > 0)
        		{
        			for (String tagID : tagid_list)
            		{
            			if (tagID == null || tagID.equals("") || tagID.equalsIgnoreCase("null"))
            			{
            				continue;
            			}
            			logger.info("删除商品与标签对应关系：标签<" + tagID + ">,商品<" + item_id + ">");
            			Admin_Tag_Item_Cache_DAO.delItem2Tag(tagID, item_id);
            		}
        		}
        		
        		// 删除REDIS中的商品数据
        		Admin_Tag_Item_Cache_DAO.delItem(item_id);
        	}
    	}
    	
    	Admin_Tag_Item_DAO.delTagItemByTag(del_ItemId_List);
    	
		// 更新任务状态
		String remark = tag_name + " 刷新商品列表完成";
		if (del_ItemId_List != null && del_ItemId_List.size() > 0)
    	{
			String del_itemid_str = "";
			for (String item_id : del_ItemId_List)
        	{
				del_itemid_str = del_itemid_str + item_id + ",";
        	}
			if (!del_itemid_str.equals(""))
			{
				remark = remark + ",清除商品：" + del_itemid_str;
			}
			jobDetail.setRemark(remark);
		}
		else
		{
			jobDetail.setRemark(remark);
		}
		jobDetail.setSuccessNum(1);
		jobDetail.setStatus(1);
		Admin_JobDetail_DAO.updateJobDetial(jobDetail);
	}
}
