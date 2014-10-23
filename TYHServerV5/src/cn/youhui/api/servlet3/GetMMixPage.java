package cn.youhui.api.servlet3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.MMixWhole;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.MMixPageManager;
import cn.youhui.utils.RespStatusBuilder;


/**
 * 混排页面
 * 2014-03-21
 * @author belong
 *
 */

@WebServlet("/tyh3/getmixpage")
public class GetMMixPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String mixTagId = "569";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String p = request.getParameter("page");
		String tag_id = request.getParameter("tagid");
//		tag_id = mixTagId;
		if(tag_id == null || "".equals(tag_id)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		int page = 1;
		try {
			page = Integer.parseInt(p);
		} catch (Exception e) {
			page = 1;
		}
		List<MMixWhole> list = MMixPageManager.getMMixWholeList(page,tag_id);
		int pageCount = MMixPageManager.getListPage(tag_id);
		if(list == null || list.size() == 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED,"",pageCount,page,listToString(list)).toString());
			return;
		}
	}

	public static String listToString(List<MMixWhole> list){
		StringBuffer sb = new StringBuffer();
		sb.append("<whole_list>");
		if(list != null && list.size() > 0){
			for(MMixWhole bean : list){
				sb.append("<whole postion=\""+bean.getSign()+"\" proportion=\""+bean.getProportion()+"\" ");
				if(bean.getHeight() != 0 && bean.getWidth() != 0){
					sb.append("width=\""+bean.getWidth()+"\" height=\""+bean.getHeight()+"\"");
				}
				sb.append(">");
				sb.append("<title><![CDATA[").append(bean.getTitle()).append("]]></title>");
				sb.append("<bg_color><![CDATA[").append(bean.getBgColor()).append("]]></bg_color>");
				sb.append("<title_ico_url><![CDATA[").append(bean.getTitleIconUrl()).append("]]></title_ico_url>");
				sb.append(bean.toXML());
				sb.append("</whole>");
			}
		}
		sb.append("</whole_list>");
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(URLEncoder.encode("商品列表", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
