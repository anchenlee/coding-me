package com.netting.action.admin;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.Action;
import com.netting.bean.KeyCategoryBean;
import com.netting.bean.Tag_Bean;
import com.netting.bean.KeyCategoryBean.KeywordBean;
import com.netting.cache.dao.Admin_Tag_Cache_DAO;
import com.netting.conf.SysCache;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.job.JobExecutor;
import com.netting.job.SynchronizeAllUserTagJob;
import com.netting.job.UpdateDiscountProductCacheJob;
import com.netting.util.CodeUtil;

/**
 * Servlet implementation class AdminTehuiAction
 */
@WebServlet("/ad/tehui")
public class AdminTehuiAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminTehuiAction() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("actionmethod");
		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try{
			if (methodName.equals("showTehui"))
			{
				showTehui(request, response);
			}else if (methodName.equals("showTagListToAdmin"))
			{
				showTagListToAdmin(request, response);
			}else if (methodName.equals("updateTag"))
			{
				updateTag(request, response);
			}else if (methodName.equals("addTag"))
			{
				addTag(request, response);
			}else if (methodName.equals("showUpdateTagJSP"))
			{
				showUpdateTagJSP(request, response);
			}else if (methodName.equals("showAddTagJSP"))
			{
				showAddTagJSP(request, response);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

    public void showTehui(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	Tag_Bean tehui = Admin_Tag_DAO.getTagBean("1073");
    	
    	
    	request.setAttribute("tag", tehui);
    	request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
    	request.setAttribute("TagSmallShowMap", SysCache.TagSmallShowMap);
		request.getRequestDispatcher("/admin/tehui_manager/tehui.jsp").forward(request, response);
		return;
    }
    
    public void showTagListToAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String status_str = request.getParameter("status");
    	String isPadStr = request.getParameter("ispad");
    	String page_str = request.getParameter("page");
    	String keyword = request.getParameter("keyword");
    	String parent_str = request.getParameter("parent");
    	
    	if (keyword == null) 
		{
			keyword = "";
		}
    	else 
		{
    		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
    	
    	// 默认第一页
    	int page = 1;
    	if (page_str != null && !"".equals(page_str))
    	{
    		page = Integer.parseInt(page_str);
    	}
    	
    	// 默认为：显示1
    	int status = 1;
    	if (status_str != null && !"".equals(status_str))
    	{
    		status = Integer.parseInt(status_str);
    	}
    	
    	// 默认为：非PAD标签
    	int ispad = 0;
    	if (isPadStr != null && !"".equals(isPadStr))
    	{
    		ispad = Integer.parseInt(isPadStr);
    	}
    	
    	// 父节点默认为：538
    	int parent_id = 538;
    	if (parent_str != null && !"".equals(parent_str))
    	{
    		parent_id = Integer.parseInt(parent_str);
    	}
    	List<Tag_Bean> tagList = new ArrayList<Tag_Bean>();
    	int totalPage = 1;
    	if(parent_str == null || "".equals(parent_str)){    		
    		Tag_Bean tehui = Admin_Tag_DAO.getTagBean("1073");
    		tagList.add(tehui);
    	}else{    		
    		tagList = Admin_Tag_DAO.getTagList(page, status, keyword, ispad, parent_id);
    		totalPage = Admin_Tag_DAO.getTagListTotal(status, keyword, ispad, parent_id);
    	}    	
    	
    	request.setAttribute("tagList", tagList);
		request.setAttribute("status", status);
		request.setAttribute("ispad", ispad);
		request.setAttribute("parent", parent_str);
		
		request.setAttribute("TagStatusMap", SysCache.TagStatusMap);
		request.setAttribute("IsPadTagMap", SysCache.IsPadTagMap);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("TagSmallShowMap", SysCache.TagSmallShowMap);
		
		request.setAttribute("page", page);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("/admin/tehui_manager/tehui.jsp").forward(request, response);
		return;
    }
	
    public void updateTag(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tag_id = request.getParameter("tag_id");;
    	String parent_id = request.getParameter("parent_id");
    	
    	String tag_name = request.getParameter("tag_name");
    	String oldTagName = Admin_Tag_DAO.getTagName(tag_id);
		if (oldTagName != null && tag_name != null && !tag_name.equals(oldTagName))
		{
			boolean repeatName = Admin_Tag_DAO.checkTagNameRepeat(tag_name, parent_id);
	    	if (repeatName)
	    	{
	    		// 标签名重复
	    		response.getWriter().print(getRespJSONString("2"));
	    		return;
	    	}
		}
    	
    	String description = request.getParameter("description");
    	String bgcolor = request.getParameter("bgcolor");
    	String sex = request.getParameter("sex");
    	String small_show = request.getParameter("small_show");
    	String status = request.getParameter("status");
    	String isPadTag = request.getParameter("isPadTag");
    	String heat = request.getParameter("heat");
    	String action_type = request.getParameter("action_type");
    	String action_value = request.getParameter("action_value");
    	String startTime = request.getParameter("start_time");
    	long startTimestamp = 0;
    	if(startTime != null && !"".equals(startTime)){
    		startTimestamp = CodeUtil.getTimeMillis_2(startTime);
    		startTime = CodeUtil.getTimeMillis_2(startTime)+"";
    	}
    	if (action_value == null)
    	{
    		action_value = "";
    	}
    	else if (action_value.equals("default"))
    	{
    		action_value = tag_id;
    	}
    	String phone_icon = request.getParameter("phone_icon");
    	String pad_icon = request.getParameter("pad_icon");
    	String chaye_action_type = request.getParameter("chaye_action_type");
    	String chaye_action_value = request.getParameter("chaye_action_value");
    	if (chaye_action_value == null)
    	{
    		chaye_action_value = "";
    	}
    	else if (chaye_action_value.equals("default"))
    	{
    		chaye_action_value = tag_id;
    	}
    	String chaye_icon = request.getParameter("chaye_icon");
    	String chaye_icon_size = request.getParameter("chaye_icon_size");
    	if (chaye_icon_size == null || chaye_icon_size.equals(""))
    	{
    		if (chaye_icon != null && !chaye_icon.equals(""))
        	{
    			chaye_icon_size = CodeUtil.getImgSizeProportion(chaye_icon);
        	}
    	}
    	
    	// 是否在手机端展示"全部"的子标签
    	String has_son_tag_all = request.getParameter("has_son_tag_all");
    	// 手机端默认的子标签的ID
    	String default_son_tag_id = request.getParameter("default_son_tag_id");
    	if (default_son_tag_id == null)
    	{
    		default_son_tag_id = "";
    	}
    	
    	// 集分宝返利比例
    	String jfb_rate = request.getParameter("jfb_rate");
    	String jfb_rate_update = request.getParameter("jfb_rate_update");
    	// 旧的集分宝比例
    	String old_jfb_rate = Admin_Tag_DAO.getTagJfbRate(tag_id);
    	
    	String position = request.getParameter("position");
    	String isTongbu = request.getParameter("tongbu");
    	String isEffective = request.getParameter("isEffective");
    	if(isEffective == null || "".equals(isEffective)){
    		isEffective = "0";
    	}
    	Tag_Bean tag= new Tag_Bean();
    	
    	tag.setTag_id(tag_id);
    	tag.setParent_tag_id(parent_id);
    	tag.setTag_name(tag_name);
    	tag.setDescription(description);
    	tag.setBgcolor(bgcolor);
    	tag.setSex(Integer.parseInt(sex));
    	tag.setSmall_show(small_show);
    	tag.setStatus(Integer.parseInt(status));
    	tag.setIsPadTag(Integer.parseInt(isPadTag));
    	tag.setHeat(heat);
    	tag.setTag_action(new Action(action_type, action_value));
    	tag.setPhone_icon(phone_icon);
    	tag.setPad_icon(pad_icon);
    	tag.setChaye_action(new Action(chaye_action_type, chaye_action_value));
    	tag.setChaye_icon(chaye_icon);
    	tag.setChaye_icon_size(chaye_icon_size);
    	tag.setHas_son_tag_all(has_son_tag_all);
    	tag.setDefault_son_tag_id(default_son_tag_id);
    	tag.setJfb_rate(jfb_rate);
    	tag.setIsEffective(isEffective);
    	tag.setStartTime(startTime);
    	tag.setStartTimestamp(startTimestamp);
    	
    	boolean flag = Admin_Tag_DAO.updateTag(tag);
    	
    	if (!flag)
    	{
    		// 操作失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    	
    	if (jfb_rate_update != null && jfb_rate_update.equals("1"))
    	{
        	// 标签的集分宝比例修改，同时更新该标签下的所有商品的集分宝比例
        	if (!jfb_rate.equals(old_jfb_rate))
        	{
        		JobExecutor.JobExecutor.execute(new UpdateDiscountProductCacheJob(tag_id, jfb_rate, old_jfb_rate));
        	}
    	}
    	
    	// 设置Redis缓存数据
    	KeywordBean keyword = new KeyCategoryBean().new KeywordBean();
    	
    	keyword.setId(tag_id);
    	keyword.setKeyword(tag_name);
    	keyword.setDescription(description);
    	keyword.setSex(Integer.parseInt(sex));
    	keyword.setIcon(phone_icon);
		keyword.setHeat(heat);
		keyword.setShow(small_show);
		keyword.setBgcolor(bgcolor);
		keyword.setAction(new Action(action_type, action_value));
		keyword.setChayeAction(new Action(chaye_action_type, chaye_action_value));
		keyword.setChaye_icon(chaye_icon);
		keyword.setChayeIconSize(chaye_icon_size);
		keyword.setHasSonTagAll(has_son_tag_all);
		keyword.setDefaultSonTagId(default_son_tag_id);
		keyword.setStartTimestamp(startTimestamp);
		
		if (oldTagName != null && tag_name != null && !tag_name.equals(oldTagName))
		{
			Admin_Tag_DAO.updateProductTagName(oldTagName, tag_name);
			Admin_Tag_Cache_DAO.setTag_ID_NAME(keyword);
		}
		
		Admin_Tag_Manager_Action.updateTagStatus(keyword, parent_id, tag_id, status, isPadTag, pad_icon);
		Admin_Tag_Cache_DAO.update_Tag_Version();
    	
    	if (flag && parent_id.equals("538") && status.equals("1") && isTongbu.equals("1")) 
    	{
    		//同步所有用户标签数据
    		List<String> allUserList = Admin_Tag_DAO.getAllUser("0");
			int rank = Admin_Tag_DAO.getMinAllUserTagRank("0", position);
			
			// String threadName_0 = "SynchronizeAllUserTag_" + tag_id + "_0";
			// JobExecutor.jobDetailMap.put(threadName_0, new JobDetialBean(threadName_0, tag_name + " 同步到所有用户", AdminLoginAction.getAdminUserFromCookie(request).getUsername()));
			JobExecutor.JobExecutor.execute(new SynchronizeAllUserTagJob(allUserList, tag_id, "0", rank));
			
			// PAD标签
			if (isPadTag.equals("1"))
			{
				List<String> allUserList_pad = Admin_Tag_DAO.getAllUser("1");
    			int rank_pad = Admin_Tag_DAO.getMinAllUserTagRank("1", position);
    			
    			// String threadName_1 = "SynchronizeAllUserTag_" + tag_id + "_1";
    			// JobExecutor.jobDetailMap.put(threadName_1, new JobDetialBean(threadName_1, tag_name + " 同步到所有用户", AdminLoginAction.getAdminUserFromCookie(request).getUsername()));
    			JobExecutor.JobExecutor.execute(new SynchronizeAllUserTagJob(allUserList_pad, tag_id, "1", rank_pad));
			}
    	}
    	
    	Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
				"修改标签，标签ID[" + tag_id + "]，标签名称[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=" + tag_id + "&tag_name=" + tag_name + "\">" + tag_name + "</a>]", 
				"3");
    	
    	// 修改成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    public void addTag(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tag_id = null;
    	
    	String tag_name = request.getParameter("tag_name");
    	String parent_id = request.getParameter("parent_id");
    	boolean repeatName = Admin_Tag_DAO.checkTagNameRepeat(tag_name, parent_id);
    	if (repeatName)
    	{
    		// 标签名重复
    		response.getWriter().print(getRespJSONString("2"));
    		return;
    	}
    	
    	String description = request.getParameter("description");
    	String bgcolor = request.getParameter("bgcolor");
    	String sex = request.getParameter("sex");
    	String small_show = request.getParameter("small_show");
    	String status = request.getParameter("status");
    	String isPadTag = request.getParameter("isPadTag");
    	String heat = request.getParameter("heat");
    	String action_type = request.getParameter("action_type");
    	String action_value = request.getParameter("action_value");
    	String position = request.getParameter("position");
    	String isTongbu = request.getParameter("tongbu");
    	String startTime = request.getParameter("start_time");
    	long startTimestamp = 0;
    	if(startTime != null && !"".equals(startTime)){
    		startTimestamp = CodeUtil.getTimeMillis_2(startTime);
    		startTime = CodeUtil.getTimeMillis_2(startTime)+"";
    	}
    	if (action_value == null)
    	{
    		action_value = "";
    	}
    	String phone_icon = request.getParameter("phone_icon");
    	String pad_icon = request.getParameter("pad_icon");
    	String chaye_action_type = request.getParameter("chaye_action_type");
    	String chaye_action_value = request.getParameter("chaye_action_value");
    	if (chaye_action_value == null)
    	{
    		chaye_action_value = "";
    	}
    	String chaye_icon = request.getParameter("chaye_icon");
    	String chaye_icon_size = request.getParameter("chaye_icon_size");
    	if (chaye_icon_size == null || chaye_icon_size.equals(""))
    	{
    		if (chaye_icon != null && !chaye_icon.equals(""))
        	{
    			chaye_icon_size = CodeUtil.getImgSizeProportion(chaye_icon);
        	}
    	}
    	String jfb_rate = request.getParameter("jfb_rate");
    	
    	Tag_Bean tag= new Tag_Bean();
    	
    	tag.setStartTime(startTime);
    	tag.setStartTimestamp(startTimestamp);
    	tag.setParent_tag_id(parent_id);
    	tag.setTag_name(tag_name);
    	tag.setDescription(description);
    	tag.setBgcolor(bgcolor);
    	tag.setSex(Integer.parseInt(sex));
    	tag.setSmall_show(small_show);
    	tag.setStatus(Integer.parseInt(status));
    	tag.setIsPadTag(Integer.parseInt(isPadTag));
    	tag.setHeat(heat);
    	tag.setTag_action(new Action(action_type, action_value));
    	tag.setPhone_icon(phone_icon);
    	tag.setPad_icon(pad_icon);
    	tag.setChaye_action(new Action(chaye_action_type, chaye_action_value));
    	tag.setChaye_icon(chaye_icon);
    	tag.setChaye_icon_size(chaye_icon_size);
    	tag.setJfb_rate(jfb_rate);
    	
    	// 提交至数据库 返回tag_id
    	tag_id = Admin_Tag_DAO.addTag(tag);
    	if (tag_id == null)
    	{
    		// 操作失败
    		response.getWriter().print(getRespJSONString("1"));
    		return;
    	}
    	
    	if (action_value.equals("default"))
    	{
    		action_value = tag_id;
    	}
    	if (chaye_action_value.equals("default"))
    	{
    		chaye_action_value = tag_id;
    	}
    	
    	// 设置Redis缓存数据
    	KeywordBean keyword = new KeyCategoryBean().new KeywordBean();
    	
    	keyword.setId(tag_id);
    	keyword.setKeyword(tag_name);
    	keyword.setDescription(description);
    	keyword.setSex(Integer.parseInt(sex));
    	keyword.setIcon(phone_icon);
		keyword.setHeat(heat);
		keyword.setShow(small_show);
		keyword.setBgcolor(bgcolor);
		keyword.setAction(new Action(action_type, action_value));
		keyword.setChayeAction(new Action(chaye_action_type, chaye_action_value));
		keyword.setChaye_icon(chaye_icon);
		keyword.setChayeIconSize(chaye_icon_size);
		keyword.setHasSonTagAll("0");
		keyword.setDefaultSonTagId("");
		keyword.setStartTimestamp(startTimestamp);
		
		Admin_Tag_Manager_Action.updateTagStatus(keyword, parent_id, tag_id, status, isPadTag, pad_icon);
		
		Admin_Tag_Cache_DAO.setTag_ID_NAME(keyword);
		Admin_Tag_Cache_DAO.update_Tag_Version();
    	
		
    	if (tag_id != null && parent_id.equals("538") && status.equals("1") && isTongbu.equals("1")) 
    	{
    		//同步所有用户标签数据
    		List<String> allUserList = Admin_Tag_DAO.getAllUser("0");
			int rank = Admin_Tag_DAO.getMinAllUserTagRank("0", position);
			
			// String threadName_0 = "SynchronizeAllUserTag_" + tag_id + "_0";
			// JobExecutor.jobDetailMap.put(threadName_0, new JobDetialBean(threadName_0, tag_name + " 同步到所有用户", AdminLoginAction.getAdminUserFromCookie(request).getUsername()));
			JobExecutor.JobExecutor.execute(new SynchronizeAllUserTagJob(allUserList, tag_id, "0", rank));
			
			// PAD标签
			if (isPadTag.equals("1"))
			{
				List<String> allUserList_pad = Admin_Tag_DAO.getAllUser("1");
    			int rank_pad = Admin_Tag_DAO.getMinAllUserTagRank("1", position);
    			
    			// String threadName_1 = "SynchronizeAllUserTag_" + tag_id + "_1";
    			// JobExecutor.jobDetailMap.put(threadName_1, new JobDetialBean(threadName_1, tag_name + " 同步到所有用户", AdminLoginAction.getAdminUserFromCookie(request).getUsername()));
    			JobExecutor.JobExecutor.execute(new SynchronizeAllUserTagJob(allUserList_pad, tag_id, "1", rank_pad));
			}
    	}
    	
    	Admin_OPT_Log_DAO.addOpt_Log(AdminLoginAction.getAdminUserFromCookie(request).getUsername(), "1", 
				"新增标签，标签ID[" + tag_id + "]，标签名称[<a target=\"_blank\" style=\"color: #1F92FF\" href=\"" + request.getContextPath() + "/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=" + tag_id + "&tag_name=" + tag_name + "\">" + tag_name + "</a>]",
				"1");
    	
    	// 新增成功
		response.getWriter().print(getRespJSONString("0"));
		return;
    }
    
    public void showUpdateTagJSP(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
    	String tag_id = request.getParameter("tag_id");
    	String page = request.getParameter("page");
    	String parent = request.getParameter("parent");
    	String status = request.getParameter("status");
    	String ispad = request.getParameter("ispad");
    	
    	Tag_Bean bean = Admin_Tag_DAO.getTagBean(tag_id);
    	
    	request.setAttribute("page", page);
    	request.setAttribute("status", status);
    	request.setAttribute("ispad", ispad);
    	request.setAttribute("parent", parent);
    	
    	request.setAttribute("tagbean", bean);
    	request.setAttribute("IsPadTagMap", SysCache.IsPadTagMap);
    	// 手机是否展示《全部》子标签
    	request.setAttribute("Has_Son_Tag_All_Map", SysCache.Has_Son_Tag_All_Map);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("TagStatusMap", SysCache.TagStatusMap);
		request.setAttribute("TagSmallShowMap", SysCache.TagSmallShowMap);
		request.setAttribute("TagSexMap", SysCache.TagSexMap);
		request.setAttribute("TagHeatMap", SysCache.TagHeatMap);
		request.setAttribute("Tag_XTYM_Map", SysCache.XTYM_Map);
		
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map(bean.getParent_tag_id());
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		// 手机展示的默认子标签
		ArrayList<HashMap<String, String>> Son_Tag_Map = Admin_Tag_DAO.getTagActionType_Value_Map(bean.getTag_id());
		request.setAttribute("Son_Tag_Map", Son_Tag_Map);
		
    	request.getRequestDispatcher("/admin/tehui_manager/tehui_update.jsp").forward(request, response);
    	return;
    }
    
    /**
     * 管理员访问“新增标签”页面
     */
    public void showAddTagJSP(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
    	String parent_str = request.getParameter("parent");
    	String page = request.getParameter("page");
    	String status = request.getParameter("status");
    	String ispad = request.getParameter("ispad");
    	
    	request.setAttribute("page", page);
    	request.setAttribute("status", status);
    	request.setAttribute("ispad", ispad);
    	
    	request.setAttribute("parent", parent_str);
    	request.setAttribute("IsPadTagMap", SysCache.IsPadTagMap);
		request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
		request.setAttribute("TagStatusMap", SysCache.TagStatusMap);
		request.setAttribute("TagSmallShowMap", SysCache.TagSmallShowMap);
		request.setAttribute("TagSexMap", SysCache.TagSexMap);
		request.setAttribute("TagHeatMap", SysCache.TagHeatMap);
		request.setAttribute("Tag_XTYM_Map", SysCache.XTYM_Map);
		
		ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map(parent_str);
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
    	request.getRequestDispatcher("/admin/tehui_manager/tehui_add.jsp").forward(request, response);
    	return;
    }
    
    
    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
    
}
