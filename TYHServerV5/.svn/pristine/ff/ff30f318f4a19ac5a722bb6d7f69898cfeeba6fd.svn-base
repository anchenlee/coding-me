package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.SaleSecretManager;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 获取特惠活动中的密语
 * @author lijun
 * @since 2013-09-28
 */
@WebServlet("/tyh2/salesecret")
public class YHGetSaleSecret extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		if(data == null || "".equals(data)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		data = data.replaceAll(" ", "+");
		data = Encrypt.decode(data);
		String param[] = data.split("#");
		if(param.length !=5){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		String uid = param[1];
		String taobaoNick = param[2];
		String saleId = param[3];
		if(uid == null || "".equals(uid) || !UserManager.getInstance().isUserExist(uid, taobaoNick)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
			return;
		}
		
		//是否判断saleid的存在性
		
		String secret = SaleSecretManager.getInstance().getSecret(saleId, uid);
		if(secret == null || "".equals(secret)){
			secret = "获取失败，请重试！";
		}
		response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, toResult(secret)).toString());
	}
	
	private String toResult(String secret){
		return "<sale_secret>" + secret + "</sale_secret>";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
