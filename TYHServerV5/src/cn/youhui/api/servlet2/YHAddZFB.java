package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh2/addzfb")
public class YHAddZFB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHAddZFB() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String zfb = request.getParameter("zfb");
//		JiFenBaoTradeManager.getInstance().grantJFB(uid, zfb);
		boolean ret = UserManager.getInstance().addzfb(uid, zfb);
		if(ret == false){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
