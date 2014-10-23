package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.LikeItemManager;
import cn.youhui.ramdata.LikeItemCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 删除喜欢商品
 * @author leejun
 * @since 20130514
 */
@WebServlet("/tyh2/dellike")
public class YHDelLikeItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String itemId = request.getParameter("itemid");
		boolean ret =  LikeItemManager.getInstance().delLikeItem(uid, itemId);
		boolean retc = false;
		if(ret)
			retc = LikeItemCacher.getInstance().delLikeItem(uid, itemId);
		if(retc == false){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
