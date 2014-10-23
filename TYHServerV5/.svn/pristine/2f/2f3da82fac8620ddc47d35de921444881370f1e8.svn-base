package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.ViewItemCacher;
import cn.youhui.utils.RespStatusBuilder;
/**
 * @category 获取浏览商品列表
 * @author leejun
 * @since 2013-03-18
 */

@WebServlet("/tyh2/getviewitem")
public class YHGetViewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHGetViewItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");
		String page = request.getParameter("page");
		if(uid == null)
			uid = "";
		int pageint = 1;
		if(page != null && !"".equals(page)){
			pageint = Integer.parseInt(page);
		}
		int pageCount = ViewItemCacher.getInstance().getViewItemTotalByUid(uid);
		if(pageCount == 0) {
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(pageint < 1) pageint = 1;
		else if(pageint > pageCount) pageint = pageCount;
		List<TeJiaGoodsBean> list = ViewItemCacher.getInstance().getViewItemsByUid(uid, pageint);
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", pageCount, pageint,changetoXml(list, uid)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}

	private String changetoXml(List<TeJiaGoodsBean> list, String uid){
		if(list != null){
			StringBuffer sb = new StringBuffer();
			sb.append("<items>");
			for(TeJiaGoodsBean bean:list){
				sb.append(bean.toXML());
			}
			sb.append("</items>");
			return sb.toString();
		}else return null;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
