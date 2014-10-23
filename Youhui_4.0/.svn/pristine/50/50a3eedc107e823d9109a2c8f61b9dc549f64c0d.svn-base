package com.netting.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.Gift_Bean;
import com.netting.cache.dao.Admin_Gift_Cache_DAO;
import com.netting.conf.SysCache;
import com.netting.conf.SysConf;
import com.netting.dao.admin.Admin_Gift_DAO;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.util.CodeUtil;
import com.netting.util.NetManager;

/**
 * 礼物模块管理
 * @author YAOJIANBO
 * @since 2013-10-15
 */
@WebServlet("/ad/gift_manager")
public class Admin_Gift_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_Gift_Manager_Action.class );
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("actionmethod");
		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try
		{
			if (methodName.equals("showUpdateGiftJSP"))
			{
				showUpdateGiftJSP(request, response);
			}
			else if (methodName.equals("updateGift"))
			{
				updateGift(request, response);
			}
			else if (methodName.equals("showAddGiftJSP"))
			{
				showAddGiftJSP(request, response);
			}
			else if (methodName.equals("addGift"))
			{
				addGift(request, response);
			}
			else if (methodName.equals("showGiftList"))
			{
				showGiftList(request, response);
			}
			else if (methodName.equals("delGift"))
			{
				delGift(request, response);
			}
			else if (methodName.equals("useGift"))
			{
				useGift(request, response);
			}
			else if (methodName.equals("movePosition"))
			{
				movePosition(request, response);
			}
		}
		catch (Exception e)
		{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
		
		return;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		this.doGet(req, resp);
	}
	
	/**
	 * 访问编辑页面
	 * @throws Exception
	 */
	public void showUpdateGiftJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String gift_id = request.getParameter("gift_id");
		String page = request.getParameter("page");
		Gift_Bean gift = Admin_Gift_DAO.getGiftBean(gift_id);
		
		request.setAttribute("GiftBean", gift);
		request.setAttribute("page", page);
		
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/gift_manager/gift_update.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 编辑礼物提交
	 * @throws Exception
	 */
	public void updateGift(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String gift_id = request.getParameter("gift_id");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String clickname = request.getParameter("clickname");
		String img = request.getParameter("img");
		String imgIpad = request.getParameter("imgIpad");
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String rank = request.getParameter("rank");
		String status = request.getParameter("status");
		
		String type = "tag";
		
		try
		{
			start_time = String.valueOf(CodeUtil.getTimeMillis_2(start_time));
			end_time = String.valueOf(CodeUtil.getTimeMillis_2(end_time));
		}
		catch (Exception e)
		{
			// 开始日期和结束日期格式错误，返回：2
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
		
		if(actionType.endsWith("tagStyleWeb") || actionType.equals("systemWeb")) 
		{
			type = "url";
			try 
			{
				actionValue = NetManager.convertToPageViewUrl(actionValue, title, description, "gift_" + gift_id);
				if (actionValue.indexOf(SysConf.hostUrl) < 0)
				{
					// 地址转换失败，操作错误，返回：1
		    		response.getWriter().print(getRespJSONString("1"));
		    		return;
				}
			} 
			catch (Exception e) 
			{
				// 地址转换失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
		}
		
		Gift_Bean gift = new Gift_Bean();
		gift.setId(gift_id);
		gift.setTitle(title);
		gift.setDescription(description);
		gift.setClickname(clickname);
		gift.setImg(img);
		gift.setImgIpad(imgIpad);
		gift.setActionType(actionType);
		gift.setActionValue(actionValue);
		gift.setStart_time(start_time);
		gift.setEnd_time(end_time);
		gift.setRank(Integer.parseInt(rank));
		gift.setStatus(Integer.parseInt(status));
		gift.setType(type);
		gift.setUpdatetime(String.valueOf(System.currentTimeMillis()));
		
		boolean flag = Admin_Gift_DAO.updateGift(gift);
		if (flag)
		{
			try
			{
				Admin_Gift_Cache_DAO.setGfit(gift);
				Admin_Gift_Cache_DAO.updateRecomRank(gift);
			}
			catch (Exception e)
			{
				// 缓存更新失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("3"));
	    		return;
			}
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "3", 
					"修改礼物[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/gift_manager?actionmethod=showUpdateGiftJSP&gift_id=" + gift_id + "\">" + title + "</a>]", 
					"3");
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0"));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：1
    		response.getWriter().print(getRespJSONString("4"));
    		return;
		}
		
	}
	
	/**
	 * 访问新增礼物页面
	 * @throws Exception
	 */
	public void showAddGiftJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String page = request.getParameter("page");
		String status = request.getParameter("status");
		
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/gift_manager/gift_add.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 新增礼物提交
	 * @throws Exception
	 */
	public void addGift(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String gift_id = Admin_Gift_DAO.getGiftNextID();
		
		String status = request.getParameter("status");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String clickname = request.getParameter("clickname");
		String img = request.getParameter("img");
		String imgIpad = request.getParameter("imgIpad");
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String type = "tag";
		
		try
		{
			start_time = String.valueOf(CodeUtil.getTimeMillis_2(start_time));
			end_time = String.valueOf(CodeUtil.getTimeMillis_2(end_time));
		}
		catch (Exception e)
		{
			// 开始日期和结束日期格式错误，返回：2
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
		
		if(actionType.endsWith("tagStyleWeb") || actionType.equals("systemWeb")) 
		{
			type = "url";
			try 
			{
				actionValue = NetManager.convertToPageViewUrl(actionValue, title, description, "gift_" + gift_id);
				if (actionValue.indexOf(SysConf.hostUrl) < 0)
				{
					// 地址转换失败，操作错误，返回：1
		    		response.getWriter().print(getRespJSONString("1"));
		    		return;
				}
			} 
			catch (Exception e) 
			{
				// 地址转换失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
		}
		
		Gift_Bean gift = new Gift_Bean();
		
		gift.setId(gift_id);
		gift.setStatus(Integer.parseInt(status));
		gift.setTitle(title);
		gift.setDescription(description);
		gift.setClickname(clickname);
		gift.setImg(img);
		gift.setImgIpad(imgIpad);
		gift.setActionType(actionType);
		gift.setActionValue(actionValue);
		gift.setStart_time(start_time);
		gift.setEnd_time(end_time);
		gift.setType(type);
		gift.setUpdatetime(String.valueOf(System.currentTimeMillis()));
		
		gift = Admin_Gift_DAO.addGift(gift);
		if (gift != null)
		{
			try
			{
				Admin_Gift_Cache_DAO.setGfit(gift);
				Admin_Gift_Cache_DAO.updateRecomRank(gift);
			}
			catch (Exception e)
			{
				// 缓存更新失败，操作错误，返回：1
	    		response.getWriter().print(getRespJSONString("3"));
	    		return;
			}
			
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "3", 
					"新增礼物[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/gift_manager?actionmethod=showUpdateGiftJSP&gift_id=" + gift_id + "\">" + title + "</a>]", 
					"1");
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0"));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：1
    		response.getWriter().print(getRespJSONString("4"));
    		return;
		}
	}

    /**
     * 管理员访问礼物列表页面
     */
    public void showGiftList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String keyword = request.getParameter("keyword");
    	String page_str = request.getParameter("page");
    	String status_str = request.getParameter("status");
    	String timeStatus = request.getParameter("timeStatus");
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    	}
    	
    	int status = 1;
    	if (status_str != null && !"".equals(status_str))
    	{
    		status = Integer.parseInt(status_str);
    	}
    	
    	if(timeStatus == null || "".equals(timeStatus))
    	{
    		timeStatus = "4";
    	}
    	ArrayList<Gift_Bean> giftList = Admin_Gift_DAO.getGiftList(page, keyword, status,timeStatus);
    	int totalPage = Admin_Gift_DAO.getGiftListTotal(keyword, status,timeStatus);
    	
    	request.setAttribute("GiftList", giftList);
    	request.setAttribute("GiftStatusMap", SysCache.GiftStatusMap);
    	request.setAttribute("timeStatusMap", SysCache.ADStatusMap);
    	request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
    	request.setAttribute("status", status);
    	request.setAttribute("timeStatus", timeStatus);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/gift_manager/gift_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 删除礼物
     * @throws Exception
     */
    public void delGift(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String gift_id = request.getParameter("gift_id");
    	String gift_title = request.getParameter("gift_title");
    	boolean flag = Admin_Gift_DAO.updateGiftStatus(gift_id, 0);
    	
    	if (flag)
    	{
    		try
			{
    			Admin_Gift_Cache_DAO.delGift(gift_id);
    			Admin_Gift_Cache_DAO.delGiftRank(gift_id);
			}
			catch (Exception e)
			{
				// 缓存更新失败，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "3", 
    				"失效礼物，ID:[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/gift_manager?actionmethod=showUpdateGiftJSP&gift_id=" + gift_id + "\">" + gift_title + "</a>]", 
    				"2");
    		// 删除成功
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else
    	{
    		// 删除失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
     * 启用礼物
     * @throws Exception
     */
    public void useGift(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String gift_id = request.getParameter("gift_id");
    	String gift_title = request.getParameter("gift_title");
    	boolean flag = Admin_Gift_DAO.updateGiftStatus(gift_id, 1);
    	if (flag)
    	{
    		try
			{
    			Gift_Bean gift = Admin_Gift_DAO.getGiftBean(gift_id);
    			gift.setStart_time(String.valueOf(CodeUtil.getTimeMillis_2(gift.getStart_time())));
    			gift.setEnd_time(String.valueOf(CodeUtil.getTimeMillis_2(gift.getEnd_time())));
    			
    			Admin_Gift_Cache_DAO.setGfit(gift);
				Admin_Gift_Cache_DAO.updateRecomRank(gift);
			}
			catch (Exception e)
			{
				// 缓存更新失败，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "3", 
    				"重新启用礼物，ID:[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/gift_manager?actionmethod=showUpdateGiftJSP&gift_id=" + gift_id + "\">" + gift_title + "</a>]", 
    				"4");
    		// 操作成功
    		response.getWriter().print(getRespJSONString("0"));
    		return;
    	}
    	else
    	{
    		// 操作失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    }
    
    /**
     * 移动操作
     * @throws Exception
     */
    public void movePosition(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id = request.getParameter("id");
		String movetype = request.getParameter("movetype");
		String position = request.getParameter("position");
		String status = request.getParameter("status");
		if(id==null||"".equals(id)||movetype==null||"".equals(movetype)||position==null||"".equals(movetype))
		{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		Gift_Bean nowGift = Admin_Gift_DAO.getGiftBean(id);
		nowGift.setStart_time(String.valueOf(CodeUtil.getTimeMillis_2(nowGift.getStart_time())));
		nowGift.setEnd_time(String.valueOf(CodeUtil.getTimeMillis_2(nowGift.getEnd_time())));
		if(movetype.equals("1"))	//	上移
		{
			Gift_Bean gift = Admin_Gift_DAO.getPreGift_Bean(position, status);
			if(gift == null)
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			else
			{
				int rank0 = gift.getRank();
				int rank1 = nowGift.getRank();
				boolean flag = Admin_Gift_DAO.updateGiftRank(id, rank0);
				flag = Admin_Gift_DAO.updateGiftRank(gift.getId(), rank1);
				if(flag) 
				{					
					gift.setRank(rank1);
					nowGift.setRank(rank0);
					
					Admin_Gift_Cache_DAO.setGfit(gift);
					Admin_Gift_Cache_DAO.updateRecomRank(gift);
					Admin_Gift_Cache_DAO.setGfit(nowGift);
					Admin_Gift_Cache_DAO.updateRecomRank(nowGift);
					
					response.getWriter().print(getRespJSONString("0"));
					return;
				}
			}
		}
		else if(movetype.equals("2"))	//	下移
		{
			Gift_Bean gift = Admin_Gift_DAO.getNextGift_Bean(position, status);
			if(gift == null)
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			else 
			{
				int rank0 = gift.getRank();
				int rank1 = nowGift.getRank();
				boolean flag = Admin_Gift_DAO.updateGiftRank(id, rank0);
				flag = Admin_Gift_DAO.updateGiftRank(gift.getId(), rank1);
				if(flag) 
				{					
					gift.setRank(rank1);
					nowGift.setRank(rank0);
					
					Admin_Gift_Cache_DAO.setGfit(gift);
					Admin_Gift_Cache_DAO.updateRecomRank(gift);
					Admin_Gift_Cache_DAO.setGfit(nowGift);
					Admin_Gift_Cache_DAO.updateRecomRank(nowGift);
					
					response.getWriter().print(getRespJSONString("0"));
					return;
				}
			}
		}
		else if(movetype.equals("3"))	//	移到首位
		{
			int minRank = Admin_Gift_DAO.getGiftMinRank();
			if(minRank == 999999999) 
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			else 
			{
				boolean flag = Admin_Gift_DAO.updateGiftRank(id, minRank);
				if(flag) 
				{					
					nowGift.setRank(minRank);
					Admin_Gift_Cache_DAO.setGfit(nowGift);
					Admin_Gift_Cache_DAO.updateRecomRank(nowGift);
					
					response.getWriter().print(getRespJSONString("0"));
					return;
				}
			}
		}
		else if(movetype.equals("4"))	//	移到末尾
		{
			int maxRank = Admin_Gift_DAO.getGiftMaxRank();
			if(maxRank == 999999999) 
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			else 
			{
				boolean flag = Admin_Gift_DAO.updateGiftRank(id, maxRank);
				if(flag)
				{					
					nowGift.setRank(maxRank);
					Admin_Gift_Cache_DAO.setGfit(nowGift);
					Admin_Gift_Cache_DAO.updateRecomRank(nowGift);
					
					response.getWriter().print(getRespJSONString("0"));
					return;
				}
			}
		}
    }
    
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
}
