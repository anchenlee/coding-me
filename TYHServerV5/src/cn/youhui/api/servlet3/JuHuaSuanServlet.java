package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.JuhuasuanBean;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.JUhuasuan_newGetTB;
import cn.youhui.manager.JuhuasuanNewManager;
import cn.youhui.ramdata.JuHuaSuanCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuiShouActionUtil;



@WebServlet("/tyh3/juhuasuan")
public class JuHuaSuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final String String = null;
	static int page_count = 12;
	
	public JuHuaSuanServlet() {
		super();
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		String channel = request.getParameter("channel");
		String platform = request.getParameter("platform");
		if ("".equals(platform) || platform == null) {
			platform = "android";
		}
		
		if(channel==null){
			channel = "";
		}

		String category = request.getParameter("category");
		if(category==null){
			category = "-1";
		}
		int a = 0;
		try {
			a = Integer.parseInt(page);
		} catch (Exception e) {
			a = 1;
		}
		long total = JuHuaSuanCacher.getInstance().getTotal(category);
		
		int totalpage =(int) total / page_count; 
		if(total % page_count > 0){
			totalpage ++;
		}
		String result = JuhuasuanNewManager.getResult(platform,page, category);
//		List<JuhuasuanBean> list = JUhuasuan_newGetTB.getResult(page, category);
		response.getWriter().println(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", totalpage, a, result).toString());

	}

	private static String listToString(List<JuhuasuanBean> list,String platform){
		StringBuffer sb = new StringBuffer();
		String jhsTagId = "589";
		KeywordBean jhsbean = TagCacher.getInstance().getTag(jhsTagId);
		if(jhsbean != null && jhsbean.getChaye_icon() != null && !"".equals(jhsbean.getChaye_icon()) && jhsbean.getChayeAction() != null){
			sb.append("<chaye>");
			sb.append("<icon><![CDATA[").append(jhsbean.getChaye_icon()).append("]]></icon>");
			sb.append("<wh_ratio><![CDATA[").append(jhsbean.getChayeIconSize()).append("]]></wh_ratio>");
			sb.append(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,jhsbean.getChayeAction())).toXML());
			sb.append("</chaye>");
		}
		sb.append("<juhuasuan>");
		for (JuhuasuanBean bean : list) {
			sb.append(bean.getContent());
		}
		sb.append("</juhuasuan>");
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
