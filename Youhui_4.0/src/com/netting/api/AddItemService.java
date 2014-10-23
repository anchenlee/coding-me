package com.netting.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.netting.bean.TeJiaGoodsBean;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.dao.admin.TaobaoAPI_DAO;
import com.netting.job.AddTagItemJob;
import com.taobao.api.domain.TaobaokeItem;

/**
 * 外部接口，提供添加商品(带集分宝比例)的接口服务</p>
 * 	0：成功
 *	1：参数错误
 *	2：访问淘宝接口无法获取商品（或者商品不支持返利）
 *	3：执行过程失败（数据库操作异常等）
 * 
 * @author YAOJIANBO
 *
 */
@WebServlet("/additem")
public class AddItemService extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( AddItemService.class );

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String ip = getRequestIP(request);
		ip = ip.trim();
		logger.info("additem ip:" + ip);
		if (!AddItemService2.checkIP(ip))
		{
			// 参数错误，返回：4
			response.getWriter().print(getRespJSONString("4", "no way!"));
			return;
		}
		
		try
		{
			// 商品ID
			String itemID = request.getParameter("itemid");
			if (itemID == null || itemID.equals("") || itemID.equalsIgnoreCase("null"))
			{
				// 参数错误，返回：1
				response.getWriter().print(getRespJSONString("1", "params error"));
				return;
			}
			
			// 集分宝比例
			String jfbRate_str = request.getParameter("jfbrate");
			if (jfbRate_str == null || jfbRate_str.equals("") || jfbRate_str.equalsIgnoreCase("null"))
			{
				// 参数错误，返回：1
				response.getWriter().print(getRespJSONString("1", "params error"));
				return;
			}
			double jfbRate = 0.0;
			try
			{
				jfbRate = Double.parseDouble(jfbRate_str);
			}
			catch (NumberFormatException ne)
			{
				// 参数错误，返回：1
				response.getWriter().print(getRespJSONString("1", "params error"));
				return;
			}
			
			
			TeJiaGoodsBean tjBean = Admin_Tag_Item_DAO.getDiscountProduct(itemID);
			if (tjBean != null)
			{
				double oldJfbRate = tjBean.getRate();
				
				String item_catid = TaobaoAPI_DAO.getCatID(itemID);
				tjBean.setCatID(item_catid);
				if (item_catid == null || item_catid.equals("") || item_catid.equalsIgnoreCase("null"))
				{
					tjBean.setRate(jfbRate);
				}
				else
				{
					// 检查该商品所属分类的集分宝比例是否为0
					double catRate = Admin_Tag_Item_DAO.getCatRate(item_catid);
					if (catRate == 0)
					{
						tjBean.setRate(0);
					}
					else
					{
						tjBean.setRate(jfbRate);
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
					// 无法获取商品或者商品不支持返利，返回：2
					response.getWriter().print(getRespJSONString("2", "NO Item!!!"));
					return;
				}
				else
				{
					tjBean = AddTagItemJob.tbkitemtoTejiaGoods(tbkItem, "");
					
					String catID = TaobaoAPI_DAO.getCatID(tbkItem.getNumIid() + "");
					tjBean.setCatID(catID);
					
					if (catID == null || catID.equals("") || catID.equalsIgnoreCase("null"))
					{
						tjBean.setRate(jfbRate);
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
							tjBean.setRate(jfbRate);
						}
					}
					
					Admin_Tag_Item_DAO.addItemJfbRateHistory(tjBean.getItem_id(), String.valueOf(tjBean.getRate()));
				}
			}
			
			// 更新数据中的商品数据
			Admin_Tag_Item_DAO.addItem(tjBean);
			// 添加(更新)商品至REDIS
			Admin_Tag_Item_Cache_DAO.add_update_Item(tjBean);
			
			// 执行成功，返回：0
			response.getWriter().print(getRespJSONString("0", "execute successfully......"));
			return;
		}
		catch (Exception e)
		{
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
}
