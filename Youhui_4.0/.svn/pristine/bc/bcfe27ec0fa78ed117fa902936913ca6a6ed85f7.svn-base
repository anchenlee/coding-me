package com.netting.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.Action;
import com.netting.bean.PushMessage;
import com.netting.cache.dao.Admin_IphoneDevToken_Cache_DAO;
import com.netting.cache.dao.Admin_Message_Android_Cache_DAO;
import com.netting.conf.SysCache;
import com.netting.conf.SysConf;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_PushMessage_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.job.JobExecutor;
import com.netting.job.SendMsgJob;
import com.netting.message.ChooseUserManager;
import com.netting.message.IOS_Msg_Manager;
import com.netting.message.IOS_Msg_Manager_New;
import com.netting.util.CodeUtil;
import com.netting.util.NetManager;

/**
 * 消息推送模块管理
 * @author YAOJIANBO
 * @since 2013-10-29
 */
@WebServlet("/ad/message_manager")
public class Admin_Message_Manager_Action extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_Message_Manager_Action.class );
	
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
			if (methodName.equals("showUpdateMessageJSP"))
			{
				showUpdateMessageJSP(request, response);
			}
			else if (methodName.equals("updateMessage"))
			{
				updateMessage(request, response);
			}
			else if (methodName.equals("showAddMessageJSP"))
			{
				showAddMessageJSP(request, response);
			}
			else if (methodName.equals("addMessage"))
			{
				addMessage(request, response);
			}
			else if (methodName.equals("showMessageList"))
			{
				showMessageList(request, response);
			}
			else if (methodName.equals("delMessage"))
			{
				delMessage(request, response);
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
	public void showUpdateMessageJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String pid = request.getParameter("pid");
		String page = request.getParameter("page");
		String platform = request.getParameter("platform");
		
		PushMessage bean = Admin_PushMessage_DAO.getPushMessageBean(pid);
		
		request.setAttribute("Message", bean);
		request.setAttribute("page", page);
		request.setAttribute("platform", platform);
		
		request.setAttribute("MessagePlatformMap", SysCache.MessagePlatformMap);
		request.setAttribute("MessageVersionMap", SysCache.MessageVersionMap);
		request.setAttribute("MessageModeMap", SysCache.MessageModeMap);
		request.setAttribute("MessageCodeMap", SysCache.MessageCodeMap);
		request.setAttribute("MessageRangeMap", SysCache.MessageRangeMap);
		
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/pushmessage_manager/pushmessage_update.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 编辑提交
	 * @throws Exception
	 */
	public void updateMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		SysConf.project_abs_path = request.getSession().getServletContext().getRealPath("/");
		
		String pid = request.getParameter("pid");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String platform = request.getParameter("platform");
		String clientVersion = request.getParameter("clientVersion");
		String mode = request.getParameter("mode");
		String code = request.getParameter("code");
		String range = request.getParameter("range");
		
		String formula = "";
		String query = "";
		if (range.equals("1"))// 按条件发送
		{
			formula = request.getParameter("formula");
			query = request.getParameter("query");
			if (formula == null || formula.equals(""))
			{
				// 数据库更新失败，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			if (query == null)
			{
				query = "";
			}
		}
		
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		String img = request.getParameter("img");
		
		if(platform.equals("android")) 
		{
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
		}
		else if(platform.equals("iphone") || platform.equals("ipad"))
		{
			start_time = "0";
			end_time = "0";
		}
		
		if(actionType.endsWith("tagStyleWeb") || actionType.equals("systemWeb")) 
		{
			try 
			{
				actionValue = NetManager.convertToPageViewUrl(actionValue, title, title, "push_" + pid);
				if (actionValue == null || actionValue.equals(""))
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
		
		PushMessage bean = new PushMessage();
		
		bean.setpId(pid);
		bean.setTitle(title);
		bean.setContent(content);
		bean.setPlatform(platform);
		bean.setClientVersion(clientVersion);
		bean.setMode(Integer.parseInt(mode));
		bean.setCode(Integer.parseInt(code));
		bean.setRange(Integer.parseInt(range));
		bean.setFormula(formula);
		bean.setQuery(query);
		bean.setStarttime(start_time);
		bean.setEndtime(end_time);
		bean.setAction(new Action(actionType, actionValue));
		bean.setIcon(img);
		
		// sendMsg(range, bean, platform, formula, query);
		
		JobExecutor.JobExecutor.execute(new SendMsgJob(range, bean, platform, formula, query));
    	
		boolean flag = Admin_PushMessage_DAO.updatePushMessage(bean);
		if (flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "7", 
					"修改并发送消息 [<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/message_manager?actionmethod=showUpdateMessageJSP&pid=" + pid + "\">" + title + "</a>]",
					"3");
			
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0"));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：1
    		response.getWriter().print(getRespJSONString("1"));
    		return;
		}
	}
	
	/**
	 * 访问新增页面
	 * @throws Exception
	 */
	public void showAddMessageJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String page = request.getParameter("page");
		String platform = request.getParameter("platform");
		
		request.setAttribute("page", page);
		request.setAttribute("platform", platform);
		
		request.setAttribute("MessagePlatformMap", SysCache.MessagePlatformMap);
		request.setAttribute("MessageVersionMap", SysCache.MessageVersionMap);
		request.setAttribute("MessageModeMap", SysCache.MessageModeMap);
		request.setAttribute("MessageCodeMap", SysCache.MessageCodeMap);
		request.setAttribute("MessageRangeMap", SysCache.MessageRangeMap);
		
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("XTYM_Map", SysCache.XTYM_Map);
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		request.getRequestDispatcher("/admin/pushmessage_manager/pushmessage_add.jsp").forward(request, response);
		return;
	}
	
	/**
	 * 新增提交
	 * @throws Exception
	 */
	public void addMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		SysConf.project_abs_path = request.getSession().getServletContext().getRealPath("/");
		
		String pid = String.valueOf(System.currentTimeMillis());
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String platform = request.getParameter("platform");
		String clientVersion = request.getParameter("clientVersion");
		String mode = request.getParameter("mode");
		String code = request.getParameter("code");
		String range = request.getParameter("range");
		
		String formula = "";
		String query = "";
		if (range.equals("1"))// 按条件发送
		{
			formula = request.getParameter("formula");
			query = request.getParameter("query");
			if (formula == null || formula.equals(""))
			{
				// 数据库更新失败，返回：1
	    		response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			if (query == null)
			{
				query = "";
			}
		}
		
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String actionType = request.getParameter("action_type");
		String actionValue = request.getParameter("action_value");
		String img = request.getParameter("img");
		
		if(platform.equals("android")) 
		{
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
		}
		else if(platform.equals("iphone") || platform.equals("ipad"))
		{
			start_time = "0";
			end_time = "0";
		}
		
		if(actionType.endsWith("tagStyleWeb") || actionType.equals("systemWeb")) 
		{
			try 
			{
				actionValue = NetManager.convertToPageViewUrl(actionValue, title, title, "push_" + pid);
				if (actionValue == null || actionValue.equals(""))
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
		
		PushMessage bean = new PushMessage();
		
		String id = Admin_PushMessage_DAO.getMessageNextID();
		bean.setId(id);
		bean.setpId(pid);
		bean.setTitle(title);
		bean.setContent(content);
		bean.setPlatform(platform);
		bean.setClientVersion(clientVersion);
		bean.setMode(Integer.parseInt(mode));
		bean.setCode(Integer.parseInt(code));
		bean.setRange(Integer.parseInt(range));
		bean.setFormula(formula);
		bean.setQuery(query);
		bean.setStarttime(start_time);
		bean.setEndtime(end_time);
		bean.setAction(new Action(actionType, actionValue));
		bean.setIcon(img);
		
		// sendMsg(range, bean, platform, formula, query);
		
		JobExecutor.JobExecutor.execute(new SendMsgJob(range, bean, platform, formula, query));
    	
		boolean flag = Admin_PushMessage_DAO.addPushMessage(bean);
		if (flag)
		{
			Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "7", 
					"新增并发送消息 [<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/message_manager?actionmethod=showUpdateMessageJSP&pid=" + pid + "\">" + title + "</a>]",
					"1");
			
			// 更新成功，返回：0
    		response.getWriter().print(getRespJSONString("0"));
    		return;
		}
		else
		{
			// 数据库更新失败，返回：1
    		response.getWriter().print(getRespJSONString("1"));
    		return;
		}
	}

    /**
     * 管理员访问消息列表页面
     */
    public void showMessageList(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String keyword = request.getParameter("keyword");
    	String page_str = request.getParameter("page");
    	String platform = request.getParameter("platform");
    	
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    	}
    	
    	if (platform == null || "".equals(platform))
    	{
    		platform = "all";
    	}
    	
    	ArrayList<PushMessage> messageList = Admin_PushMessage_DAO.getPushMessageList(page, keyword, platform);
    	int totalPage = Admin_PushMessage_DAO.getPushMessageListTotal(keyword, platform);
    	
    	request.setAttribute("MessageList", messageList);
    	request.setAttribute("MessagePlatformMap", SysCache.MessagePlatformMap);
    	request.setAttribute("MessageRangeMap", SysCache.MessageRangeMap);
    	request.setAttribute("platform", platform);
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/pushmessage_manager/pushmessage_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 删除礼物
     * @throws Exception
     */
    public void delMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String pid = request.getParameter("pid");
    	String title = request.getParameter("title");
    	boolean flag = Admin_PushMessage_DAO.deletePushMessage(pid);
    	
    	if (flag)
    	{
    		Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "7", 
					"删除消息 [" + title + "]", "2");
    		
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
    
    public static void sendMsg(String range, PushMessage msg, String platform, String formula, String query)
    {
    	Map<String, String> msgvalue = null;
    	if("0".equals(range))
		{
			if(isIos(platform))
			{
				Map<String, String> devTokenlist = Admin_IphoneDevToken_Cache_DAO.getAllDevTokenWithUID();
				if (platform.indexOf("iphone") > -1)
				{
					IOS_Msg_Manager.sendAll(devTokenlist, msg, "iphone_etouch");
					IOS_Msg_Manager.sendAll(devTokenlist, msg, "iphone_taogeili");
				}
				else if (platform.indexOf("ipad") > -1)
				{
					IOS_Msg_Manager.sendAll(devTokenlist, msg, platform);
				}
			}
			else if(isAndroid(platform))
			{
				Admin_Message_Android_Cache_DAO.addMsgToAll(msg);
			}
			else
			{
				Map<String, String> devTokenlist = Admin_IphoneDevToken_Cache_DAO.getAllDevTokenWithUID();
				IOS_Msg_Manager.sendAll(devTokenlist, msg, platform);
				Admin_Message_Android_Cache_DAO.addMsgToAll(msg);
			}
			
			msgvalue = new HashMap<String, String>();
			msgvalue.put("00000000", "");
			Admin_PushMessage_DAO.addUidToMsg(msg.getpId(), msgvalue, msg.getStarttime());
		}
		else
		{
			try
			{
				msgvalue = ChooseUserManager.getResult(formula, query);
				if(msgvalue != null && msgvalue.size() > 0)
				{
					if(isIos(platform)) 
					{
						if (platform.indexOf("iphone") > -1)
						{
							IOS_Msg_Manager_New.sendMsg(msgvalue, msg, "iphone_etouch");
							IOS_Msg_Manager_New.sendMsg(msgvalue, msg, "iphone_taogeili");
						}
						else if (platform.indexOf("ipad") > -1)
						{
							IOS_Msg_Manager_New.sendMsg(msgvalue, msg, platform);
						}
					}
					else if(isAndroid(platform))
					{
						Admin_Message_Android_Cache_DAO.addMsgWithValue(msg, msgvalue);
					}
					else
					{
						IOS_Msg_Manager_New.sendMsg(msgvalue, msg, platform);
						Admin_Message_Android_Cache_DAO.addMsgWithValue(msg, msgvalue);
					}
					Admin_PushMessage_DAO.addUidToMsg(msg.getpId(), msgvalue, msg.getEndtime());
				}
				ChooseUserManager.ubeanmap.clear();
			}
			catch (Exception e)
			{
				logger.error("Admin_Message_Manager_Action.sendMsg() error:", e);
			}
		}
    }
    
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
    
    private static boolean isIos(String platform)
    {
		if(platform != null && ( platform.indexOf("iphone") > -1 || platform.indexOf("ipad") > -1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private static boolean isAndroid(String platform)
	{
		if(platform != null && platform.indexOf("android")>-1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
