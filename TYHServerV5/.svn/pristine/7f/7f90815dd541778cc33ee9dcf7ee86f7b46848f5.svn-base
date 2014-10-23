package cn.youhui.api.superdiscount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.ParamConfig;

/**
 * jiangjun
 * 返回用户验证接口（验证余额是否足够和密保是否已经绑定）
 */
@WebServlet("/GetVerifyBallanceStatusUrl")
public class GetVerifyBallanceStatusUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetVerifyBallanceStatusUrl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print(ParamConfig.VerifyBallanceStatus);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
