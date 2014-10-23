package com.netting.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.JobDetialBean;
import com.netting.bean.TagToItemBean;
import com.netting.bean.Tag_Bean;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_JobDetail_DAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.dao.admin.TaobaoAPI_DAO;
import com.netting.util.FetchTaobaoPrice;
import com.taobao.api.domain.TaobaokeItem;

public class AddTagItemJob extends Thread 
{
	private static Log logger = LogFactory.getLog(AddTagItemJob.class);
	
	public String ids;
	
	public String tag_name;
	
	public String tag_id;
	
	private String admin_username;
	
	public AddTagItemJob(String ids, String tag_name, String tag_id, String admin_username) 
	{
		this.ids = ids;
		this.tag_name = tag_name;
		this.tag_id = tag_id;
		this.admin_username = admin_username;
	}

	@Override
	public void run() 
	{
		String nowTime = String.valueOf(System.currentTimeMillis());
		JobDetialBean jobDetail = new JobDetialBean(admin_username + "_" + nowTime, tag_name + " 批量添加商品", admin_username, nowTime);
		jobDetail.setSuccessNum(0);
		jobDetail.setRemark("");
		jobDetail.setStatus(0);
		// 记录任务至数据库
		jobDetail.setAllNum(ids.split(",").length);
		Admin_JobDetail_DAO.addJobDetial(jobDetail);
		
		// 添加失败的商品ID
		String fail_ids = ids;
		// 添加成功的数目
		int success_num = 0;
		
		Tag_Bean tag_bean = Admin_Tag_DAO.getTagBean(tag_id);
		ArrayList<TaobaokeItem> items = TaobaoAPI_DAO.getItemListWithOutSDK(ids);
		if (items != null && items.size() > 0)
		{
			for (int i = 0; i < items.size(); i++)
			{
				TaobaokeItem item = items.get(i);
				
				boolean success_flag = processTbkItem(item, tag_bean);
				if (success_flag)
				{
					Admin_OPT_Log_DAO.addOpt_Log(admin_username, "1", 
							"添加标签下商品，标签ID[" + tag_id + "]，标签名称[" + tag_name + "], 商品ID[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"http://b17.cn/item?itemid=" + item.getNumIid() + "\">" + item.getNumIid() + "</a>]", 
							"1");
					
					// 删除失败ID中已经成功的ID
					fail_ids = fail_ids.replaceAll((item.getNumIid()+ ""), "");
					fail_ids = fail_ids.replaceAll(",,", ",");
					
					// 成功数目+1
					success_num++;
				}
			}
			
			// 重新锁住前N个
	    	reLockHeaderItem(tag_bean);
		}
		
		jobDetail.setSuccessNum(success_num);
		jobDetail.setStatus(1);
		String remark = "添加商品:" + ids;
		if (success_num != jobDetail.getAllNum())
		{
			remark = remark + "; 失败ID:" + fail_ids + ";失败原因：无法获取到淘宝客商品或者该商品没有返利！";
		}
		jobDetail.setRemark(remark);
		Admin_JobDetail_DAO.updateJobDetial(jobDetail);
	}
	
	public static boolean processTbkItem(TaobaokeItem item, Tag_Bean tag_bean)
	{
		long time = System.currentTimeMillis();
		if (item != null)
		{
			TeJiaGoodsBean tjBean = Admin_Tag_Item_DAO.getDiscountProduct(String.valueOf(item.getNumIid()));
			Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", tag_bean.getTag_id(), "before getitem from taobao:", (System.currentTimeMillis()-time));
			TeJiaGoodsBean bean = tbkitemtoTejiaGoods(item, tag_bean.getTag_name());
			Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", tag_bean.getTag_id(), "after getitem from taobao:", (System.currentTimeMillis()-time));
			if(tjBean != null && tjBean.getIsUpdateed() ==1 && tjBean.getStatus() == 0 )
			{
//				bean.setPrice_low(tjBean.getPrice_low());
				bean.setPic_url(tjBean.getPic_url());
				bean.setIsUpdateed(tjBean.getIsUpdateed());
				bean.setRate(tjBean.getRate());
			}
			else
			{
				String tag_jfb_rate_str = tag_bean.getJfb_rate();
				if (tag_jfb_rate_str != null && !tag_jfb_rate_str.equals("") && !tag_jfb_rate_str.equalsIgnoreCase("null"))
				{
					bean.setRate(Double.parseDouble(tag_jfb_rate_str));
				}
			}
			Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", tag_bean.getTag_id(), "before fetch catId from baidu:", (System.currentTimeMillis()-time));
			String catID = TaobaoAPI_DAO.getCatID(item.getNumIid() + "");
			Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", tag_bean.getTag_id(), "fetch catId from baidu:", (System.currentTimeMillis()-time));
//			logger.error("fetch catId from baidu:"+(System.currentTimeMillis()-time));
			String catid = "";
			int baoyou = 0;
			if(catID != null && !"".equals(catID)){
				String[] s = catID.split(",");
				if(s.length > 0) catid = s[0];
				if(s.length > 1) {
					String baoyouStr = s[1];
					try {
						baoyou = Integer.parseInt(baoyouStr);
					} catch (Exception e) {
						baoyou = 0;
					}
				}
			}
			bean.setCatID(catid);
			bean.setBaoyou(baoyou);
			if (catid == null || catid.equals(""))
			{
				String tag_jfb_rate_str = tag_bean.getJfb_rate();
				if (tag_jfb_rate_str != null && !tag_jfb_rate_str.equals("") && !tag_jfb_rate_str.equalsIgnoreCase("null"))
				{
					bean.setRate(Double.parseDouble(tag_jfb_rate_str));
				}
			}
			else
			{
				// 检查该商品所属分类的集分宝比例是否为0
				double catRate = Admin_Tag_Item_DAO.getCatRate(bean.getCatID());
				if (catRate == 0)
				{
					bean.setRate(0);
				}
			}
			// 添加(更新)商品至数据库和REDIS
			Admin_Tag_Item_DAO.addItem(bean);
			bean.setUpdate_time(System.currentTimeMillis()+"");
			Admin_Tag_Item_Cache_DAO.add_update_Item(bean);
			Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", tag_bean.getTag_id(), "refresh TagItem :", (System.currentTimeMillis()-time));
//			logger.error("refresh TagItem :"+(System.currentTimeMillis()-time));
			// 添加商品与该标签的关系归属
			int rank =  Admin_Tag_Item_DAO.addItem2Tag(String.valueOf(item.getNumIid()), tag_bean.getTag_id());
			Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_bean.getTag_id(), String.valueOf(item.getNumIid()), rank);
			Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", tag_bean.getTag_id(), "refresh Tag2Item :", (System.currentTimeMillis()-time));
//			logger.error("refresh Tag2Item :"+(System.currentTimeMillis()-time));
			//添加商品至子标签后，同步商品归属至父标签
			String parent_tag_id = tag_bean.getParent_tag_id();
			if (parent_tag_id != null && !parent_tag_id.equals("") && !parent_tag_id.equalsIgnoreCase("null")
					&& !parent_tag_id.equals("538"))
			{
				int parent_rank =  Admin_Tag_Item_DAO.addItem2Tag(String.valueOf(item.getNumIid()), parent_tag_id);
				Tag_Bean parent_tag_bean = Admin_Tag_DAO.getTagBean(parent_tag_id);
				Admin_Tag_Item_Cache_DAO.addItem2Tag(parent_tag_id, String.valueOf(item.getNumIid()), parent_rank);
				reLockHeaderItem(parent_tag_bean);
			}
			Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", tag_bean.getTag_id(), "refresh ParentTag2Item :", (System.currentTimeMillis()-time));
//			logger.error("refresh ParentTag2Item :"+(System.currentTimeMillis()-time));
			// 如果是新进商品(或者之前的商品已经被删除)，记录商品的集分宝比例历史记录(如果该商品之前就已经存在，不作此操作)
//			System.out.println("gfgfg  "+bean.getRate()+"  kkjjkjk  "+tjBean.getRate());
//			boolean flag = Admin_Tag_Item_DAO.isExit(bean.getItem_id());//商品集分宝表是否存在记录
//			if (!flag)
//			{
			Admin_Tag_Item_DAO.addItemJfbRateHistory(bean.getItem_id(), String.valueOf(bean.getRate()));
//			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 重新锁住前面的商品
	 * @return
	 */
	public static void reLockHeaderItem(Tag_Bean tag_bean)
	{
		// 重新锁住前N个
    	List<TagToItemBean> list = Admin_Tag_Item_DAO.getRankItemList(tag_bean.getTag_id());
    	if (list != null && list.size() > 0)
    	{
    		for(TagToItemBean bean : list) 
        	{
        		Admin_Tag_Item_Cache_DAO.addItem2Tag(tag_bean.getTag_id(), bean.getItemid(), bean.getRank());
        	}
    	}
	}
	
	/**
     * 淘宝接口获取数据转化为TeJiaGoodsBean
     * @param itemList
     * @param parent
     * @return
     */
	public static TeJiaGoodsBean tbkitemtoTejiaGoods(TaobaokeItem item, String parent)
    {
		TeJiaGoodsBean bean = new TeJiaGoodsBean();
		
		bean.setItem_id(item.getNumIid() + "");
		bean.setKeyword("");
		bean.setClickURL(item.getClickUrl());
		bean.setPrice_high(item.getPrice());
//		long time = System.currentTimeMillis();
//		double promoPriceDouble = FetchTaobaoPrice.getPriceItem(item.getNumIid()+"");
//		Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", "tag_id", "first getitem from taobao:", (System.currentTimeMillis()-time));
//		
//		if(promoPriceDouble == -1 || promoPriceDouble == 0){
//			promoPriceDouble = FetchTaobaoPrice.getPromoPriceFromWeb(item.getNumIid()+"");
//		}
//		Admin_Tag_Item_DAO.insertAddItemLogs(item.getNumIid() + "", "tag_id", "second getitem from taobao:", (System.currentTimeMillis()-time));
//		
//		String promoPrice = promoPriceDouble +"";
		String promoPrice = TaobaoAPI_DAO.getPromoPrice(item.getNumIid() + ""); 
		if(promoPrice == null|| "".equals(promoPrice)) 
		{
			promoPrice = item.getPrice();
		}
		bean.setPrice_low(promoPrice);
		
		bean.setDiscount("" + Float.valueOf(promoPrice)/Float.valueOf(item.getPrice())*100);  
		bean.setPic_url(item.getPicUrl());
		bean.setCommission(item.getCommission());
		bean.setCommission_rate(item.getCommissionRate());
		bean.setTitle(item.getTitle());
//		System.out.println(bean.getPrice_low()+"hghghgh");
		return bean;
	}
}
