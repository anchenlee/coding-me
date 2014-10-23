package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;


import cn.youhui.bean.GiftBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.jfbad.JFBAd;
import cn.youhui.manager.GiftManager;
import cn.youhui.manager.JFB_Act_Manager;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh2/gifts22")
public class YHGetGift extends HttpServlet 
{
//	private static final Logger log = Logger.getLogger(YHGetGift.class);
	
	private static final long serialVersionUID = 1L;

	public YHGetGift() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
//		String uri = request.getRequestURI();
//		String url = request.getRequestURL().toString();
//		String contextPath = request.getContextPath();
//		
//		log.info("uri:" + uri);
//		log.info("url:" + url);
//		log.info("contextPath:" + contextPath);
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String access = request.getParameter("access_token");
		String platform = request.getParameter("platform");
		String version_code = request.getParameter("version_code");
		
		if (access == null) 
		{
			access = "";
		}
		if(platform ==null || platform.equals(""))
		{
			platform = "android";
		}
		if(uid ==null || uid.equals(""))
		{
			uid = "";
		}
		
		String result = "";
		try 
		{
			result = getXMLGifts(access, request, uid, platform, version_code);
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	private static String getXMLGifts(String access, HttpServletRequest request, String uid, String platform, String version_code) throws JSONException 
	{
		List<GiftBean> resultList = new ArrayList<GiftBean>();
		List<GiftBean> compareList = new ArrayList<GiftBean>();
		
		// 分享位置
		List<GiftBean> list_FX = GiftManager.getInstance().getGiftList("活动详情", compareList, platform, uid, version_code);
		compareList.addAll(list_FX);
		
		// 淘金币位置
		List<GiftBean> list_TJB = GiftManager.getInstance().getGiftList("淘金币", compareList, platform, uid, version_code);
		compareList.addAll(list_TJB);
		
		// 转换集分宝
		List<GiftBean> list_ZHJFB = GiftManager.getInstance().getGiftList("转换集分宝", compareList, platform, uid, version_code);
		compareList.addAll(list_ZHJFB);
		
		List<GiftBean> list1 = JFB_Act_Manager.getInstance().get_JFB_Act_List(request, uid);
		if (list1.size() < 3)
		{
			int _len = 3 - list1.size();
			List<GiftBean> list2 = GiftManager.getInstance().getGiftList(null, compareList, platform, uid, version_code);
			
			if (list2.size() > _len)
			{
				list2 = list2.subList(0, _len);
			}
			
			list1.addAll(list2);
		}
		
		if("android".equalsIgnoreCase(platform))
		{
			if (list_FX != null && list_FX.size() >= 1)
			{
				resultList.add(list_FX.get(0));
			}
		}
		
		resultList.addAll(list1);
		
		// 如果不是ipad终端，跟淘金币
		if(!"ipad".equals(platform))
		{
			if (list_TJB != null && list_TJB.size() >= 1)
			{
				resultList.add(list_TJB.get(0));
			}
		}
		
		// 所有终端，跟转换集分宝
		if (list_ZHJFB != null && list_ZHJFB.size() >= 1)
		{
			resultList.add(list_ZHJFB.get(0));
		}
		
		StringBuffer data = new StringBuffer();
		if(resultList != null && resultList.size() > 0)
		{
			data.append("<gifts>");
			for(GiftBean gift : resultList)
			{
				data.append(gift.toXML());
			}
			data.append("</gifts>");
			return RespStatusBuilder.messageGift(ActionStatus.NORMAL_RETURNED ,access,data.toString()).toString();
		}
		else
		{
			return RespStatusBuilder.messageGiftError(ActionStatus.NORMAL_RETURNED,access).toString();
		}
	}
}
