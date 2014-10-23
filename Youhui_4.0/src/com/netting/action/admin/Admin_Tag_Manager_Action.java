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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.netting.bean.Action;
import com.netting.bean.FixedTagBean;
import com.netting.bean.KeyCategoryBean;
import com.netting.bean.TagIcon;
import com.netting.bean.TagToItemBean;
import com.netting.bean.KeyCategoryBean.KeywordBean;
import com.netting.bean.Tag_Bean;
import com.netting.cache.dao.Admin_Tag_Cache_DAO;
import com.netting.conf.SysCache;
import com.netting.dao.admin.Admin_OPT_Log_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.job.JobExecutor;
import com.netting.job.SynchronizeAllUserTagJob;
import com.netting.job.UpdateDiscountProductCacheJob;
import com.netting.redis.executor.JedisHashManager;
import com.netting.util.ChaojiHuiUtils;
import com.netting.util.CodeUtil;
import com.netting.util.NetManager;

/**
 * 标签(类目)管理
 * @author YAOJIANBO
 * @since 2013-09-24
 */
@WebServlet("/ad/tag_manager")
public class Admin_Tag_Manager_Action extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private static final String saleTagId = "1073";
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_Tag_Manager_Action.class );

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String methodName = request.getParameter("actionmethod");
		if (null == methodName || "".equals(methodName)
				|| "null".equals(methodName)) {
			response.getWriter()
					.print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try {
			if (methodName.equals("showUpdateTagJSP")) {
				showUpdateTagJSP(request, response);
			} else if (methodName.equals("showAddTagJSP")) {
				showAddTagJSP(request, response);
			} else if (methodName.equals("showTagListToAdmin")) {
				showTagListToAdmin(request, response);
			} else if (methodName.equals("updateTag")) {
				updateTag(request, response);
			} else if (methodName.equals("addTag")) {
				addTag(request, response);
			} else if (methodName.equals("MovePosition")) {
				MovePosition(request, response);
			} else if (methodName.equals("showTagIcoList")) {
				showTagIcoList(request, response);
			} else if (methodName.equals("showAddTagIconJSP")) {
				showAddTagIconJSP(request, response);
			} else if (methodName.equals("AddTagIcon")) {
				AddTagIcon(request, response);
			} else if (methodName.equals("delTagIcon")) {
				delTagIcon(request, response);
			} else if (methodName.equals("showFixedTag")) {
				showFixedTag(request, response);
			} else if (methodName.equals("getFixedTag")) {
				getFixedTag(request, response);
			} else if (methodName.equals("dealTagStatus")) {
				dealTagStatus(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter()
					.print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		this.doGet(req, resp);
	}
	
    /**
     * 管理员点击标签的"编辑"访问编辑页面
     */
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
		if(parent != null && parent.equals("1073")){
			Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		}
		request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
		
		// 手机展示的默认子标签
		ArrayList<HashMap<String, String>> Son_Tag_Map = Admin_Tag_DAO.getTagActionType_Value_Map(bean.getTag_id());
		request.setAttribute("Son_Tag_Map", Son_Tag_Map);
		
    	request.getRequestDispatcher("/admin/tag_manager/tag_update.jsp").forward(request, response);
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
		
    	request.getRequestDispatcher("/admin/tag_manager/tag_add.jsp").forward(request, response);
    	return;
    }
    
    /**
     * 管理员访问标签(类目)列表信息
     */
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
    	
    	List<Tag_Bean> tagList = Admin_Tag_DAO.getTagList(page, status, keyword, ispad, parent_id);
    	int totalPage = Admin_Tag_DAO.getTagListTotal(status, keyword, ispad, parent_id);
    	
    	ArrayList<HashMap<String, String>> headTagList_1 = Admin_Tag_DAO.getHeaderTagList(1, parent_id);
    	ArrayList<HashMap<String, String>> headTagList_2 = Admin_Tag_DAO.getHeaderTagList(2, parent_id);
    	
    	if (headTagList_1 != null && headTagList_1.size() > 0)
    	{
    		request.setAttribute("headTagList_1", headTagList_1);
    	}
    	if (headTagList_2 != null && headTagList_2.size() > 0)
    	{
    		ArrayList<HashMap<String, String>> chaojiHuiList = ChaojiHuiUtils.getChaojihuiList(headTagList_2);
    		ArrayList<HashMap<String, String>> yuList = ChaojiHuiUtils.getYuList(headTagList_2);
    		request.setAttribute("yuList", yuList);
    		request.setAttribute("chaojiHuiList", chaojiHuiList);
    		request.setAttribute("headTagList_2", headTagList_2);
    	}
    	
    	
    	String tagStatus = JedisHashManager.get("tagstatus", "value");
    	
    	request.setAttribute("tagStatus", tagStatus);
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
		
		request.getRequestDispatcher("/admin/tag_manager/tag_list.jsp").forward(request, response);
		return;
    }
    
    /**
     * 编辑修改标签并提交</p>
     * @throws Exception
     */
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
		
		updateTagStatus(keyword, parent_id, tag_id, status, isPadTag, pad_icon);
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
    
    /**
     * 新增标签并提交</p>
     * @throws Exception
     */
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
		
		updateTagStatus(keyword, parent_id, tag_id, status, isPadTag, pad_icon);
		
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
    
    /**
	 * 修改标签状态的同时更新数据库和缓存(Redis)的相关数据
	 * @param keyword
	 * @param parentID
	 * @param tagID
	 * @param status
	 */
	public static void updateTagStatus(KeyCategoryBean.KeywordBean keyword, String parentID, 
			String tagID, String status, String isPadTag, String pad_icon)
	{
		if (status.equals("1"))// 显示
		{
			if (keyword != null)
			{
				Admin_Tag_Cache_DAO.addTag2All_Tags(keyword);
				Admin_Tag_Cache_DAO.addTag2Tag(parentID, tagID);
			}
			
			if (Admin_Tag_Cache_DAO.TYH_TAG_ID.equalsIgnoreCase(parentID))
			{
				Admin_Tag_Cache_DAO.addTag2AllTags(parentID, tagID);
				Admin_Tag_DAO.addDefaultUserTag(tagID);
				Admin_Tag_Cache_DAO.addDefaultUserTagCache(tagID);
			}
			
			// 如果是PAD标签
			if (isPadTag.equals("1") && parentID.equals("538"))
			{
				keyword.setIcon(pad_icon);
				Admin_Tag_DAO.addDefaultUserIpadTag(tagID);
				Admin_Tag_Cache_DAO.addDefaultUserIpadTagCache(tagID);
				Admin_Tag_Cache_DAO.addTag2All_Tags_Pad(keyword);
				Admin_Tag_Cache_DAO.addTag2Tag_Pad(parentID, tagID);
			}
			else if (isPadTag.equals("0") && parentID.equals("538"))
			{
				Admin_Tag_DAO.delDefaultUserIpadTag(tagID);
				Admin_Tag_Cache_DAO.delDefaultUserIpadTagCache(tagID);
				Admin_Tag_Cache_DAO.delTag2All_Tags_Pad(tagID);
				Admin_Tag_Cache_DAO.delTag2Tag_Pad(parentID, tagID);
			}
		}
		else if (status.equals("2"))// 隐藏
		{
			if (keyword != null)
			{
				Admin_Tag_Cache_DAO.addTag2All_Tags(keyword);
				if (isPadTag.equals("1"))
				{
					Admin_Tag_Cache_DAO.addTag2All_Tags_Pad(keyword);
				}
				
				/*
				Admin_Tag_Cache_DAO.addTag2Tag(parentID, tagID);
				if (isPadTag.equals("1"))
				{
					Admin_Tag_Cache_DAO.addTag2Tag_Pad(parentID, tagID);
				}
				*/
			}
			
			Admin_Tag_DAO.delDefaultUserTag(tagID);
			Admin_Tag_Cache_DAO.delTag2Tag(parentID, tagID);
			Admin_Tag_Cache_DAO.delTag2AllTags(parentID, tagID);
			Admin_Tag_Cache_DAO.delDefaultUserTagCache(tagID);
			
			if (isPadTag.equals("1") && parentID.equals("538"))
			{
				Admin_Tag_DAO.delDefaultUserIpadTag(tagID);
				Admin_Tag_Cache_DAO.delDefaultUserIpadTagCache(tagID);
				Admin_Tag_Cache_DAO.delTag2Tag_Pad(parentID, tagID);
			}
		}
		else if (status.equals("3"))// 删除
		{
			Admin_Tag_DAO.delDefaultUserTag(tagID);
			Admin_Tag_Cache_DAO.delTag2All_Tags(tagID);
			Admin_Tag_Cache_DAO.delDefaultUserTagCache(tagID);
			Admin_Tag_Cache_DAO.delTag2Tag(parentID, tagID);
			Admin_Tag_Cache_DAO.delTag2AllTags(parentID, tagID);
			
			if (isPadTag.equals("1") && parentID.equals("538"))
			{
				Admin_Tag_DAO.delDefaultUserIpadTag(tagID);
				Admin_Tag_Cache_DAO.delDefaultUserIpadTagCache(tagID);
				Admin_Tag_Cache_DAO.delTag2All_Tags_Pad(tagID);
				Admin_Tag_Cache_DAO.delTag2Tag_Pad(parentID, tagID);
			}
		}
	}
    
	/**
	 * 标签排序
	 * @throws Exception
	 */
	public void MovePosition(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String status = request.getParameter("status");
		String parent = request.getParameter("parent");
		String id = request.getParameter("id");
		String movetype = request.getParameter("movetype");
		String position = request.getParameter("position");
		String isPad = request.getParameter("ispad");
		boolean flag = false;
		
		if(id == null||"".equals(id)||movetype == null||"".equals(movetype)||parent == null||"".equals(parent)||position == null||"".equals(position))
		{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		if(isPad == null||"".equals(isPad))
		{
			isPad = "0";
		}
		Tag_Bean nowBean = Admin_Tag_DAO.getTagBean(id);
		TagToItemBean userTag = null;
		String ids = "";
		if(status.equals("1")&&parent.equals("538"))
		{			
			userTag = Admin_Tag_DAO.getUserTag(id,isPad);
		}
		if(nowBean == null)
		{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		
		if(status.equals("1")&&parent.equals("538"))
		{
			if(userTag == null)
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
		}
		if(movetype.equals("1"))	//上移
		{
			Tag_Bean bean = Admin_Tag_DAO.getPreTag_Bean(status, parent, isPad, position);
			TagToItemBean preUserTag = null;
			if(status.equals("1")&&parent.equals("538"))
			{
				preUserTag = Admin_Tag_DAO.getPreUserTag(userTag.getRank(),isPad);
			}
			if(bean == null)
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			if(status.equals("1")&&parent.equals("538"))
			{
				if(preUserTag == null)
				{
					response.getWriter().print(getRespJSONString("1"));
		    		return;
				}
			}
			int rank0 = nowBean.getRank();
			int rank1 = bean.getRank();
			nowBean.setRank(rank1);
			bean.setRank(rank0);
			boolean flag1 = Admin_Tag_DAO.updateRank(rank1, id);
			boolean flag2 = Admin_Tag_DAO.updateRank(rank0, bean.getTag_id());
			flag = flag1&&flag2;

			if(status.equals("1")&&parent.equals("538"))
			{								
					int rank2 = userTag.getRank();
					int rank3 = preUserTag.getRank();
					boolean flag3 = Admin_Tag_DAO.updateDefalutTagId(userTag.getTagid(), rank3,isPad);
					boolean flag4 = Admin_Tag_DAO.updateDefalutTagId(preUserTag.getTagid(), rank2,isPad);
					flag = flag1&&flag2&&flag3&&flag4;
			}
			
			if(flag)
			{
				if(parent.equals(saleTagId)){
					ids = Admin_Tag_DAO.getIdsByParentIDSale(status, isPad, parent);
				}else{					
					ids = Admin_Tag_DAO.getIdsByParentID(status, isPad, parent);
				}
				if(isPad.equals("0"))
				{					
					Admin_Tag_Cache_DAO.setTag2Tag(parent, ids);
					if(parent.equals("538"))
					{					
						Admin_Tag_Cache_DAO.updateDefalutCache(ids);
						Admin_Tag_Cache_DAO.setTag2AllTag(parent, ids);
					}
				}
				else if(isPad.equals("1"))
				{
					Admin_Tag_Cache_DAO.setTag2AllIpadTag(parent, ids);
					if(parent.equals("538"))
					{						
						Admin_Tag_Cache_DAO.updateDefalutCacheInIpad(ids);
					}
				}
				response.getWriter().print(getRespJSONString("0"));
				return;
			}
		}
		else if(movetype.equals("2"))	//下移
		{
			Tag_Bean bean = Admin_Tag_DAO.getNextTag_Bean(status, parent, isPad, position);
			TagToItemBean nextUserTag = null;
			
			if(status.equals("1")&&parent.equals("538"))
			{
				nextUserTag = Admin_Tag_DAO.getNextUserTag(userTag.getRank(),isPad);
				if(nextUserTag == null)
				{
					response.getWriter().print(getRespJSONString("1"));
		    		return;
				}
			}
			if(bean == null)
			{
				response.getWriter().print(getRespJSONString("1"));
	    		return;
			}
			int rank0 = nowBean.getRank();
			int rank1 = bean.getRank();
			nowBean.setRank(rank1);
			bean.setRank(rank0);
			boolean flag1 = Admin_Tag_DAO.updateRank(rank1, id);
			boolean flag2 = Admin_Tag_DAO.updateRank(rank0, bean.getTag_id());
			flag = flag1&&flag2;
			if(status.equals("1")&&parent.equals("538"))
			{								
					int rank2 = userTag.getRank();
					int rank3 = nextUserTag.getRank();
					boolean flag3 = Admin_Tag_DAO.updateDefalutTagId(userTag.getTagid(), rank3,isPad);
					boolean flag4 = Admin_Tag_DAO.updateDefalutTagId(nextUserTag.getTagid(), rank2,isPad);
					flag = flag1&&flag2&&flag3&&flag4;
			}
			
			if(flag)
			{
				if(parent.equals(saleTagId)){
					ids = Admin_Tag_DAO.getIdsByParentIDSale(status, isPad, parent);
				}else{					
					ids = Admin_Tag_DAO.getIdsByParentID(status, isPad, parent);
				}
				if(isPad.equals("0"))
				{					
					Admin_Tag_Cache_DAO.setTag2Tag(parent, ids);
					if(parent.equals("538"))
					{					
						Admin_Tag_Cache_DAO.updateDefalutCache(ids);
						Admin_Tag_Cache_DAO.setTag2AllTag(parent, ids);
					}
				}
				else if(isPad.equals("1"))
				{
					Admin_Tag_Cache_DAO.setTag2AllIpadTag(parent, ids);
					if(parent.equals("538"))
					{						
						Admin_Tag_Cache_DAO.updateDefalutCacheInIpad(ids);
					}
				}
				response.getWriter().print(getRespJSONString("0"));
				return;
			}
		}
		else if(movetype.equals("3"))	//移到首位
		{
			int rank0 = Admin_Tag_DAO.getMinRank(status, parent, isPad);
			nowBean.setRank(rank0);
			boolean flag1 = Admin_Tag_DAO.updateRank(rank0, id);
			flag = flag1;
			if(status.equals("1")&&parent.equals("538"))
			{				
				int rank1 = Admin_Tag_DAO.getMinUserTagRank(isPad);
				userTag.setRank(rank1);					
				boolean flag2 = Admin_Tag_DAO.updateDefalutTagId(userTag.getTagid(), rank1,isPad);
				flag = flag1&&flag2;
			}
			if(flag)
			{
				if(parent.equals(saleTagId)){
					ids = Admin_Tag_DAO.getIdsByParentIDSale(status, isPad, parent);
				}else{					
					ids = Admin_Tag_DAO.getIdsByParentID(status, isPad, parent);
				}
				if(isPad.equals("0"))
				{					
					Admin_Tag_Cache_DAO.setTag2Tag(parent, ids);
					if(parent.equals("538"))
					{					
						Admin_Tag_Cache_DAO.updateDefalutCache(ids);
						Admin_Tag_Cache_DAO.setTag2AllTag(parent, ids);
					}
				}
				else if(isPad.equals("1"))
				{
					Admin_Tag_Cache_DAO.setTag2AllIpadTag(parent, ids);
					if(parent.equals("538"))
					{						
						Admin_Tag_Cache_DAO.updateDefalutCacheInIpad(ids);
					}
				}
				response.getWriter().print(getRespJSONString("0"));
				return;
			}
		}
		else if(movetype.equals("4"))	//移到末尾
		{
			int rank0 = Admin_Tag_DAO.getMaxRank(status, parent, isPad);
			nowBean.setRank(rank0);
			boolean flag1 = Admin_Tag_DAO.updateRank(rank0, id);
			flag = flag1;
			if(status.equals("1")&&parent.equals("538"))
			{				
				int rank1 = Admin_Tag_DAO.getMaxUserTagRank(isPad);
				userTag.setRank(rank1);					
				boolean flag2 = Admin_Tag_DAO.updateDefalutTagId(userTag.getTagid(), rank1,isPad);
				flag = flag1&&flag2;
			}
			if(flag)
			{
				if(parent.equals(saleTagId)){
					ids = Admin_Tag_DAO.getIdsByParentIDSale(status, isPad, parent);
				}else{					
					ids = Admin_Tag_DAO.getIdsByParentID(status, isPad, parent);
				}
				if(isPad.equals("0"))
				{					
					Admin_Tag_Cache_DAO.setTag2Tag(parent, ids);
					if(parent.equals("538"))
					{					
						Admin_Tag_Cache_DAO.updateDefalutCache(ids);
						Admin_Tag_Cache_DAO.setTag2AllTag(parent, ids);
					}
				}
				else if(isPad.equals("1"))
				{
					Admin_Tag_Cache_DAO.setTag2AllIpadTag(parent, ids);
					if(parent.equals("538"))
					{						
						Admin_Tag_Cache_DAO.updateDefalutCacheInIpad(ids);
					}
				}
				response.getWriter().print(getRespJSONString("0"));
				return;
			}
		}
		
		response.getWriter().print(getRespJSONString("1"));
		return;
	}
	
	public void showTagIcoList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String tagId = request.getParameter("tag_id");
		String tagName = request.getParameter("tag_name");
		if(tagName!= null && !"".equals(tagName)){
    		tagName = new String(tagName.getBytes("iso-8859-1"), "utf-8");
    	}
		List<TagIcon> list = Admin_Tag_DAO.getTagIconList(tagId);
		int parent_id = 538;
    	if (tagId != null && !"".equals(tagId))
    	{
    		parent_id = Integer.parseInt(tagId);
    	}
		ArrayList<HashMap<String, String>> headTagList_1 = Admin_Tag_DAO.getHeaderTagList(1, parent_id);
    	ArrayList<HashMap<String, String>> headTagList_2 = Admin_Tag_DAO.getHeaderTagList(2, parent_id);
    	if (headTagList_1 != null && headTagList_1.size() > 0)
    	{
    		request.setAttribute("headTagList_1", headTagList_1);
    	}
    	if (headTagList_2 != null && headTagList_2.size() > 0)
    	{
    		request.setAttribute("headTagList_2", headTagList_2);
    	}
    	request.setAttribute("tag_id", tagId);
		request.setAttribute("tag_name", tagName);
    	request.setAttribute("tagList", list);
		
		request.getRequestDispatcher("/admin/tag_manager/tag_ico_list.jsp").forward(request, response);
		return;
		
	}
	
	public void showAddTagIconJSP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String id = request.getParameter("id");
		String tagId = request.getParameter("tag_id");
		String tagName = request.getParameter("tag_name");
		TagIcon bean = null;
		if(id != null && !id.equals("")){
			bean = Admin_Tag_DAO.getTagIcon(id);
		}
		if(bean != null){
			request.setAttribute("tagIconBean", bean);
		}
		request.setAttribute("tag_id", tagId);
		request.setAttribute("tag_name", tagName);
		request.getRequestDispatcher("/admin/tag_manager/tag_icon_update.jsp").forward(request, response);
		return;
	}
		
	public void AddTagIcon(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String startTime =request.getParameter("start_time");
		String endTime = request.getParameter("end_time");
		String img = request.getParameter("img");
		String tagId = request.getParameter("tag_id");
		try
		{
			startTime = String.valueOf(CodeUtil.getTimeMillis_2(startTime));
			endTime = String.valueOf(CodeUtil.getTimeMillis_2(endTime));
		}
		catch (Exception e)
		{
			// 开始日期和结束日期格式错误，返回：2
    		response.getWriter().print(getRespJSONString("2"));
    		return;
		}
		TagIcon bean = new TagIcon();
		bean.setBeginTime(startTime);
		bean.setEndTime(endTime);
		bean.setTagId(tagId);
		bean.setIcon(img);
		boolean flag = false;
		if(id == null || "".equals(id)){
			boolean isExist = Admin_Tag_DAO.checkExitTagIcon(tagId, Long.parseLong(startTime), Long.parseLong(endTime));
			if(!isExist){
				response.getWriter().print(getRespJSONString("3"));
	    		return;
			}
			flag = Admin_Tag_DAO.addTagIcon(bean);
		}else {
			bean.setId(id);
			flag = Admin_Tag_DAO.updateTagIcon(bean);
		}
		if(flag){
			response.getWriter().print(getRespJSONString("0"));
    		return;
		}else{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		
	}
	
	public void delTagIcon(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		if(id == null || "".equals(id)){
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		
		boolean flag = Admin_Tag_DAO.delTagIcon(id);
		if(flag){
			response.getWriter().print(getRespJSONString("0"));
    		return;
		}else{
			response.getWriter().print(getRespJSONString("1"));
    		return;
		}
		
	}
	
	/**
	 * 增加tag时，同步所有用户tag
	 * @param tagid
	 * @param rank
	 */
	public static  boolean synchronizeAllUserTag(String tagid,String ispad,String position) 
	{
		boolean flag = false;
		List<String> allUserList = Admin_Tag_DAO.getAllUser(ispad);
		int rank = Admin_Tag_DAO.getMinAllUserTagRank(ispad,position);
		//数据库同步			
		flag = Admin_Tag_DAO.synchronizeAllUserTag(allUserList, tagid, rank,ispad);
		if(flag)	//调用tyhapi接口同步缓存
		{
			try 
			{			
				NetManager.getContent("");
				flag = true;
			} catch (Exception e) {
				logger.error("Admin_Tag_Manager_Action.synchronizeAllUserTag error :",e);
				flag = false;
			}
		}
		return flag;
	}
	
	public void showFixedTag(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String img = request.getParameter("img");
		FixedTagBean fixedTag = new FixedTagBean();
		fixedTag.setId(id);
		fixedTag.setTitle(title);
		fixedTag.setImg(img);
		fixedTag.setCreateTime(System.currentTimeMillis());
		fixedTag.setUdpateTime(System.currentTimeMillis());
		Gson gs = new Gson();
		new JedisHashManager();
		JedisHashManager.set("fixedtag", "value", gs.toJson(fixedTag).toString());
	}
	
	public void getFixedTag(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		new JedisHashManager();
		String str = JedisHashManager.get("fixedtag", "value");
		FixedTagBean fixedTag = null;
		if (str == null || "".equals(str)) {
			request.getRequestDispatcher("/admin/tag_manager/fixed_tag.jsp").forward(request, response);
			return;
		}
		Gson gs = new Gson();
		fixedTag = gs.fromJson(str, FixedTagBean.class);
		request.setAttribute("value", fixedTag);
		request.getRequestDispatcher("/admin/tag_manager/fixed_tag.jsp").forward(request, response);
	}
	
	
	public void dealTagStatus(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String status = request.getParameter("status");
		new JedisHashManager();
		JedisHashManager.set("tagstatus", "value", status);
	}

    public static String getRespJSONString(String result) throws JSONException
    {
    	JSONObject respOBJ = new JSONObject();
		respOBJ.put("result",  result);
		return respOBJ.toString();
    }
}
