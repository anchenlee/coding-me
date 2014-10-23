package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.Tagid2TagnameCacher;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.RespStatusBuilder;
/**
 * @category 每日特惠
 * @author leejun
 * @since 2013-03-25
 */

@WebServlet("/tyh2/tehui")
public class YHGetTehui extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public YHGetTehui() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		if(uid == null)
			uid = "";
		page = page == null ? "1" : page;
		int pageInt = Integer.parseInt(page);
		int pageSize = 15;
		String tagId = Tagid2TagnameCacher.getInstance().getIdbyName("每日特惠");
		int itemPageCount = Tag2ItemCacher.getInstance().getTotalPage(tagId, pageSize);
		if(itemPageCount == 0) {
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(pageInt < 1) pageInt = 1;
		else if(pageInt > itemPageCount) pageInt = itemPageCount;
		List<TeJiaGoodsBean> itemlist = Tag2ItemCacher.getInstance().getItemsByTagid(tagId, pageInt, pageSize);
		if(itemlist != null && itemlist.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", itemPageCount, pageInt, changetoXml(itemlist, uid, platform)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	private String changetoXml(List<TeJiaGoodsBean> itemlist, String uid, String platform){
		StringBuffer sb = new StringBuffer();
		if(itemlist != null && itemlist.size() > 0){
			sb.append("<items>");
			for(TeJiaGoodsBean bean : itemlist){
				String outerCode = OuterCode.getOuterCode(uid, platform, bean.getRate() + "", "8", "");
				bean.setClickURL(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + bean.getItem_id());
				sb.append(bean.toXML());
			}
			sb.append("</items>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
