package cn.youhui.acapi.newuser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;

/**
 * 领取奖品
 * @author lijun
 * @since 2014-7-8
 */
@WebServlet("/getgift")
public class GetGiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		TurnplateResult tr = new TurnplateResult();
		try{
			String imei = ParamUtil.getParameter(request, "imei", true);
			String uid = ParamUtil.getParameter(request, "uid", true);
			int result[] = TurnplateManager.getInstance().getGift(imei, uid);
			tr.setStatus(result[0]);
			tr.setResultCode(result[1]);
			response.getWriter().print(tr.toJson());
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(tr.toJson());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
