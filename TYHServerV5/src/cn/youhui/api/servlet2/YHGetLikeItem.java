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
import cn.youhui.ramdata.LikeItemCacher;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 获取喜欢商品列表
 * @author leejun
 * @since 2013-03-18
 */
@WebServlet("/tyh2/getlikeitem")
public class YHGetLikeItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHGetLikeItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");
		String page = request.getParameter("page");
		String platform = request.getParameter("platform");
		if(uid == null)
			uid = "";
		int pageint = 1;
		if(page != null && !"".equals(page)){
			pageint = Integer.parseInt(page);
		}
		int pageCount = LikeItemCacher.getInstance().getLikeItemTotalByUid(uid);
		if(pageCount == 0) {
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(pageint < 1) pageint = 1;
		List<TeJiaGoodsBean> list = LikeItemCacher.getInstance().getLikeItemsByUid(uid, pageint);
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", pageCount, pageint,changetoXml(list, uid, platform)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}

	private String changetoXml(List<TeJiaGoodsBean> list, String uid, String platform){
		if(list != null){
			StringBuffer sb = new StringBuffer();
			sb.append("<items>");
			for(TeJiaGoodsBean bean:list){
				String outerCode = OuterCode.getOuterCode(uid, platform, bean.getRate()+"", "4", "");
				bean.setClickURL(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + bean.getItem_id());
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
