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

@WebServlet("/tyh2/resetsex")
public class YHResetUserSex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHResetUserSex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String sex = request.getParameter("sex");
		boolean flag = false;
		if (uid==null || uid.equals("")) {
			response.getWriter().write(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		if("m".equals(sex) || "f".equals(sex)){
			flag = UserManager.getInstance().resetUserSex(uid, sex);
			if(flag) 	response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
			else 	response.getWriter().write(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
		}
		else response.getWriter().write(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
