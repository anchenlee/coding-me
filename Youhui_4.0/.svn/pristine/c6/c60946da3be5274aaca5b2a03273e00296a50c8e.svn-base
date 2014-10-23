package com.netting.job;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.TeJiaGoodsBean;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.dao.admin.TaobaoAPI_DAO;

/**
 * 更新标签下所有商品的最低价格
 * @author YAOJIANBO
 *
 */
public class UpdateTagItemPriceJob extends Thread 
{
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( UpdateTagItemPriceJob.class );
	
	private String tag_id;
	
	public UpdateTagItemPriceJob(String tag_id) 
	{
		this.tag_id = tag_id;
	}

	@Override
	public void run() 
	{
		ArrayList<TeJiaGoodsBean> oldItemList = Admin_Tag_Item_DAO.getTagItemByTagID(tag_id);
    	ArrayList<TeJiaGoodsBean> updateList = new ArrayList<TeJiaGoodsBean>();
    	
    	for (TeJiaGoodsBean bean : oldItemList)
    	{
    		String promoPrice = TaobaoAPI_DAO.getPromoPrice(bean.getItem_id());
    		if(promoPrice != null && !bean.getPrice_low().equals(promoPrice))
			{
    			// System.out.println("变价：" + bean.getItem_id());
    			bean.setPrice_low(promoPrice);
    			bean.setDiscount("" + Float.valueOf(promoPrice) / Float.valueOf(bean.getPrice_high())*100);
    			
    			updateList.add(bean);
    			Admin_Tag_Item_Cache_DAO.add_update_Item(bean);
			}
    	}
    	
    	if (updateList != null && updateList.size() > 0)
    	{
    		Admin_Tag_Item_DAO.updateTagItemPrice(updateList);
    	}
	}
	
}
