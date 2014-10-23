package cn.youhui.api.superdiscount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.youhui.dao.SecondKillDAO;
import cn.youhui.utils.ParamUtil;

/**
 * 给商户用，设置串码已被使用
 */
@WebServlet("/SetUseSecondKill")
public class SetUseSecondKill extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SetUseSecondKill() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=ParamUtil.getParameter(request, "code");
		if(code==null||"".equals(code)){
			response.getWriter().print("paramException");
			return;
		}
		response.getWriter().print(SecondKillDAO.getInstance().useSecondKillCode(code));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
