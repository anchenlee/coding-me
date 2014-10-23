package com.netting.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.dao.admin.Admin_Tag_Item_DAO;

/**
 * 刷新内存里面的标签下商品数据
 * @author YAOJIANBO
 *
 */
public class UpdateDiscountProductCacheJob extends Thread 
{
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( UpdateDiscountProductCacheJob.class );
	
	private String tag_id;
	
	private String new_jfb_rate;
	
	private String old_jfb_rate;
	
	public UpdateDiscountProductCacheJob(String tag_id, String new_jfb_rate, String old_jfb_rate) 
	{
		this.tag_id = tag_id;
		this.new_jfb_rate = new_jfb_rate;
		this.old_jfb_rate = old_jfb_rate;
	}

	@Override
	public void run() 
	{
		Admin_Tag_Item_DAO.updateDiscountProductJfbRate(tag_id, this.new_jfb_rate, this.old_jfb_rate);
		Admin_Tag_Item_DAO.updateDiscountProductCacheJfbRate(tag_id);
	}
	
}
