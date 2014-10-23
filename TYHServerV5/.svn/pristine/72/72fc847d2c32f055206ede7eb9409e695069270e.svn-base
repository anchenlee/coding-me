package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.JuhuasuanBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.JUhuasuan_newGetTB;
import cn.youhui.ramdata.JuHuaSuanCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.Tagid2TagnameCacher;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh2/juhuasuan")
public class YHGetJuhuansuan extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int page_count = 12;
    public static Long lastupdatetime = 0L;

    public YHGetJuhuansuan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		String uid = request.getParameter("uid");
		String category = request.getParameter("category");
		String platform = request.getParameter("platform");
//		if (System.currentTimeMillis() - lastupdatetime > 30 * 60 * 1000) {
//			lastupdatetime = System.currentTimeMillis();
//			new Thread() {
//				public void run() {
//					new JUhuasuan_newGetTB();
//					JUhuasuan_newGetTB.getJuhuasuanBeanFromNet();
//				}
//			}.start();
//		}
		if(category==null)
		{
			category = "-1";
		}
		int a = 0;
		try 
		{
			a = Integer.parseInt(page);
		} 
		catch (Exception e)
		{
			a = 1;
		}
		List<JuhuasuanBean> searchResult = JuHuaSuanCacher.getInstance().getList(category, a , page_count);
		
		long total = JuHuaSuanCacher.getInstance().getTotal(category);
		
		int totalpage =(int) total / page_count; 
		if(total % page_count > 0)
		{
			totalpage ++;
		}
		
		StringBuffer sb = new StringBuffer();
		// String jhsTagId = Tagid2TagnameCacher.getInstance().getIdbyName("聚划算");
		String jhsTagId = "589";
		KeywordBean jhsbean = TagCacher.getInstance().getTag(jhsTagId);
		if(jhsbean != null && jhsbean.getChaye_icon() != null && !"".equals(jhsbean.getChaye_icon()) && jhsbean.getChayeAction() != null)
		{
			sb.append(jhsbean.chayeXML());
		}
		sb.append(listToXML(searchResult, platform, uid));
		response.getWriter().println( RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", totalpage, a, sb.toString()).toString());
	}


	public static String listToXML(List<JuhuasuanBean> list, String platform, String uid)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<juhuasuan>");
		if(list != null)
		{			
			for (JuhuasuanBean bean : list) {
				String outerCode = OuterCode.getOuterCode(uid, platform, "0", "7", "589");
				String clickurl = Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + bean.getItemid();
				bean.setClickurl(clickurl);
				sb.append(bean.getContent());
			}
		}
		sb.append("</juhuasuan>");
		
		return sb.toString();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
