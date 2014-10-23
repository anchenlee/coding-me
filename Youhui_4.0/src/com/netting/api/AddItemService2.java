package com.netting.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.Tag_Bean;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.dao.admin.TaobaoAPI_DAO;
import com.netting.job.AddTagItemJob;
import com.taobao.api.domain.TaobaokeItem;

/**
 * 外部接口，提供 亲包邮 添加商品的接口服务</p>
 * 	0：成功
 *	1：参数错误
 *	2：访问淘宝接口无法获取商品（或者商品不支持返利）
 *	3：执行过程失败（数据库操作异常等）
 *  4：IP限制
 * @author YAOJIANBO
 *
 */
@WebServlet("/additem2")
public class AddItemService2 extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( AddItemService2.class );

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		// System.out.println(CodeUtil.getDateTimeStr());
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String ip = getRequestIP(request);
		ip = ip.trim();
		
		if (!checkIP(ip))
		{
			// 参数错误，返回：4
			response.getWriter().print(getRespJSONString("4", "no way!"));
			return;
		}
		
		try
		{
			// 用户ID uid
			String uid = request.getParameter("uid");
			if (uid == null || uid.equals("") || uid.equalsIgnoreCase("null"))
			{
				// 参数错误，返回：1
				response.getWriter().print(getRespJSONString("1", "params error"));
				return;
			}
						
			// 商品ID 序列
			String itemIDs = request.getParameter("itemids");
			if (itemIDs == null || itemIDs.equals("") || itemIDs.equalsIgnoreCase("null"))
			{
				// 参数错误，返回：1
				response.getWriter().print(getRespJSONString("1", "params error"));
				return;
			}
			
			int successNum = 0;
			String tagID = "569" + uid;
			
			/**
			 * 删除上次添加的商品数据
			 */
			String sonTagIDList = Admin_Tag_DAO.getSonTagID("569");
			sonTagIDList = sonTagIDList + "," + "569";
			String[] sonTagIDArray = sonTagIDList.split(",");
			for (String sonTagIDStr : sonTagIDArray)
			{
				if (sonTagIDStr != null && !"".equals(sonTagIDStr) && !"null".equalsIgnoreCase(sonTagIDStr))
				{
					Admin_Tag_Item_DAO.del569Item(sonTagIDStr + uid);
					Admin_Tag_Item_Cache_DAO.delTagItems(sonTagIDStr + uid);
				}
			}
			
			Tag_Bean tag_bean = Admin_Tag_DAO.getTagBean("569");
			
			String[] itemIDArray = itemIDs.split(",");
			for (String itemID_Rate : itemIDArray)
			{
				if (null != itemID_Rate && !"".equals(itemID_Rate) && !"null".equalsIgnoreCase(itemID_Rate))
				{
					String itemID = null;
					double jfb_rate = 0.0;
					String catID = null;
					
					String[] id_rate = itemID_Rate.split(":");
					if (id_rate != null && id_rate.length >= 2)
					{
						itemID = id_rate[0];
						String rate_str = id_rate[1];
						
						if (itemID == null || itemID.equals("") || itemID.equalsIgnoreCase("null")
								|| rate_str == null || rate_str.equals("") || rate_str.equalsIgnoreCase("null"))
						{
							continue;
						}
						
						try
						{
							jfb_rate = Double.parseDouble(rate_str);
						}
						catch (Exception en)
						{
							continue;
						}
					}
					else
					{
						continue;
					}
					
					catID = TaobaoAPI_DAO.getCatID(itemID);
					
					TeJiaGoodsBean tjBean = Admin_Tag_Item_DAO.getDiscountProduct(itemID);
					double priceLow = 0;
					double priceHigh = 0;
					if(tjBean != null){						
						try {
							priceLow = Double.parseDouble(tjBean.getPrice_low());
							priceHigh = Double.parseDouble(tjBean.getPrice_high());
						} catch (Exception e) {
							priceLow = 0;
							priceHigh = 0;
						}
					}
					if (tjBean != null && priceLow < priceHigh)
					{
						double oldJfbRate = tjBean.getRate();
						
						tjBean.setCatID(catID);
						if (catID == null || catID.equals(""))
						{
							String tag_jfb_rate_str = tag_bean.getJfb_rate();
							if (tag_jfb_rate_str != null && !tag_jfb_rate_str.equals("") && !tag_jfb_rate_str.equalsIgnoreCase("null"))
							{
								tjBean.setRate(Double.parseDouble(tag_jfb_rate_str));
							}
						}
						else
						{
							// 检查该商品所属分类的集分宝比例是否为0
							double catRate = Admin_Tag_Item_DAO.getCatRate(tjBean.getCatID());
							if (catRate == 0)
							{
								tjBean.setRate(0);
							}
							else
							{
								tjBean.setRate(jfb_rate);
							}
						}
						
						if (oldJfbRate != tjBean.getRate())
						{
							Admin_Tag_Item_DAO.addItemJfbRateHistory(tjBean.getItem_id(), String.valueOf(tjBean.getRate()));
						}
					}
					else
					{
						// 访问淘宝接口，获取商品数据
						TaobaokeItem tbkItem = TaobaoAPI_DAO.getItemWithOutSDK(itemID);
						if (tbkItem == null)
						{
							continue;
						}
						else
						{
							tjBean = AddTagItemJob.tbkitemtoTejiaGoods(tbkItem, "");
							
							tjBean.setCatID(catID);
							if (catID == null || catID.equals(""))
							{
								String tag_jfb_rate_str = tag_bean.getJfb_rate();
								if (tag_jfb_rate_str != null && !tag_jfb_rate_str.equals("") && !tag_jfb_rate_str.equalsIgnoreCase("null"))
								{
									tjBean.setRate(Double.parseDouble(tag_jfb_rate_str));
								}
							}
							else
							{
								// 检查该商品所属分类的集分宝比例是否为0
								double catRate = Admin_Tag_Item_DAO.getCatRate(tjBean.getCatID());
								if (catRate == 0)
								{
									tjBean.setRate(0);
								}
								else
								{
									tjBean.setRate(jfb_rate);
								}
							}
							
							Admin_Tag_Item_DAO.addItemJfbRateHistory(tjBean.getItem_id(), String.valueOf(tjBean.getRate()));
						}
					}
					
					// 更新数据中的商品数据
					Admin_Tag_Item_DAO.addItem(tjBean);
					// 添加(更新)商品至REDIS
					Admin_Tag_Item_Cache_DAO.add_update_Item(tjBean);
					
					// 添加商品与该标签的关系归属
					int rank =  Admin_Tag_Item_DAO.addItem2Tag569(itemID, tagID);
					Admin_Tag_Item_Cache_DAO.addItem2Tag(tagID, itemID, rank);
					
					// 添加商品与亲包邮其下的子标签的关系归属
					String sonTagID = Admin_Tag_Item_DAO.getSonTagID(catID);
					int rankSonTag =  Admin_Tag_Item_DAO.addItem2Tag569(itemID, sonTagID + uid);
					Admin_Tag_Item_Cache_DAO.addItem2Tag(sonTagID + uid, itemID, rankSonTag);
					
					successNum++;
				}
				else
				{
					continue;
				}
			}
			
			HashMap<String, String> respMap = new HashMap<String, String>();
			respMap.put("success_num", String.valueOf(successNum));
			respMap.put("content", "execute successfully......");
			
			// System.out.println(CodeUtil.getDateTimeStr());
			
			// 执行成功，返回：0
			response.getWriter().print(getRespJSONStringWithParam("0", respMap));
			return;
		}
		catch (Exception e)
		{
			logger.error("wrong", e);
			// 执行过程异常，返回：3
			response.getWriter().print(getRespJSONString("3", "execute fail....."));
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public static String getRequestIP(HttpServletRequest request)
	{
		String ip = request.getHeader("X-Forwarded-For");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
	    {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
	    {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
	    {
	        ip = request.getRemoteAddr();
	    }
	    
	    return ip;
	}
	
	public static String getRespJSONString(String result, String content)
    {
		try
		{
			JSONObject respOBJ = new JSONObject();
			respOBJ.put("result",  result);
			if (content != null)
			{
				respOBJ.put("content",  content);
			}
			return respOBJ.toString();
		}
		catch (Exception e)
		{
			return "{\"content\":\"execute fail.....\",\"result\":\"3\"}";
		}
    }
	
	public static String getRespJSONStringWithParam(String result, HashMap<String, String> params) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		if (params != null && params.size() > 0)
		{
			Set<Entry<String, String>> set = params.entrySet();
			for (Entry<String, String> entry : set)
			{
				respOBJ.put(entry.getKey(), entry.getValue());
			}
		}
		return respOBJ.toString();
    }
	
	public static boolean checkIP(String ip)
	{
		if (ip == null || ip.equals("") || ip.contains("10.0.254.11"))
		{
			return false;
		}
		
		if (ip.contains("192.168."))
		{
			return true;
		}
		else if (ip.contains("10.0."))
		{
			return true;
		}
		else if(ip.contains("172.16")){
			return true;
		}
		else
		{
			return false;
		}
	}
}
