package cn.youhui.api.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.youhui.bean.ProductPageStyleBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.Tagid2TagnameCacher;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh/taggoods")
public class YHGetTagGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String tagStyleList = "tagStyleList";
	private static final String tagStyleWaterflow = "tagStyleWaterflow";
	private static final String tagStyleGrid = "tagStyleGrid";
	private static final String tagStyleMix = "tagStyleMix";
	private static final String tagStyleWeb = "tagStyleWeb";
	private static final String tagStyleSingle = "tagStyleSingle";
    
    public YHGetTagGoods() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String access = request.getParameter("access_token");
		String keyword = request.getParameter("keyword");
		String page = request.getParameter("page");
		page = page == null ? "1" : page;
		int pageInt = Integer.parseInt(page);
		String platform = request.getParameter("platform");
		String type = request.getParameter("type");
		if (access == null) {
			access = "";
		}
		if (keyword == null || "".equals(keyword)) {
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
		if (request.getMethod().equals("GET")) {
			keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
		}
		String tagId = Tagid2TagnameCacher.getInstance().getIdbyName(keyword);
		if (tagId == null || "".equals(tagId)) {
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
		int pageSize = 15;
		if("ipad".equalsIgnoreCase(platform)){	
			pageSize = 16;
		}else{
//			if(tagStyleMix.equals(type)) {
//				pageSize = 10;
//				int total = Tag4StyleItemCacher.getInstance().getTotalNum(keyword, pageSize);
//				if(total > 0){
//					ArrayList<ProductPageStyleBean> list = Tag4StyleItemCacher.getInstance().getProductByKeyword(keyword, pageSize, page);
//					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, total, Integer.parseInt(page), changetoXml1(list,keyword)));
//					return;
//				}
//			} else 
//			{
				if(tagStyleList.equals(type)) {
					pageSize = 15;
				} else if(tagStyleWaterflow.equals(type)) {
					pageSize = 30;
				} else if(tagStyleGrid.equals(type) || tagStyleSingle.equals(type)) {
					pageSize = 20;
				} else if(tagStyleWeb.equals(type)) {
					pageSize = 10;
				}
//			}
		}
		int itemPageCount = Tag2ItemCacher.getInstance().getTotalPage(tagId, pageSize);
		if(itemPageCount == 0) 
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(pageInt < 1) 
		{
			pageInt = 1;
		}
		else if(pageInt > itemPageCount) pageInt = itemPageCount;
		List<TeJiaGoodsBean> itemlist = Tag2ItemCacher.getInstance().getItemsByTagid(tagId, pageInt, pageSize);
		if(itemlist != null && itemlist.size() > 0)
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, itemPageCount, Integer.parseInt(page), changetoXml(itemlist,keyword)));
		}
		else
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}

	private String changetoXml(List<TeJiaGoodsBean> itemlist,String keyword){
		StringBuffer sb = new StringBuffer();
		if(itemlist != null && itemlist.size() > 0){
			sb.append("<tag name=\"" + keyword + "\">");
			for(TeJiaGoodsBean bean : itemlist){
				sb.append(bean.toXMLold());
			}
			sb.append("</tag>");
		}
		return sb.toString();
	}

	private String changetoXml1(List<ProductPageStyleBean> itemlist,String keyword){
		StringBuffer sb = new StringBuffer();
		sb.append("<tag name=\"" + keyword + "\">");
		if(itemlist != null && itemlist.size() > 0){
			for (ProductPageStyleBean bean : itemlist) {
				if (bean != null){
					sb.append(bean.toXMLold());
				} 
			}
		}
			sb.append("</tag>");
			return sb.toString();
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
