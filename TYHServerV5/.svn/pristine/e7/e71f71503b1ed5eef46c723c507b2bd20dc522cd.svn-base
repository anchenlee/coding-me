package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.PushMsgManager;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh2/delpushmsg")
public class YHDelUserPushMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public YHDelUserPushMessage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		boolean flag = false;
		String uid = request.getParameter("uid");
		String pid = request.getParameter("pid");
		if(uid==null||"".equals(uid)||pid==null||"".equals(pid)) {
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}	
		flag = PushMsgManager.getInstance().delUserPushMsg(uid, pid);
		if(flag) response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
		else response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
