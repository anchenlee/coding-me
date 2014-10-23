package cn.youhuiWeb.api.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.WebUtil;
import cn.youhuiWeb.bean.TurnplateResult;
import cn.youhuiWeb.manager.TurnplateManager;

/**
 * 转盘抽奖
 * @author liuxj
 * @since 2014-8-19
 */
@WebServlet("/weblogin/turnplate")
public class Turnplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		TurnplateResult tr = new TurnplateResult();
		try{
			String ip = WebUtil.getIP(request);
			String uid = ParamUtil.getParameter(request, "uid", true);
			int result[] = TurnplateManager.getInstance().turn(uid, ip);
			if(result[0] == 1){
				if(!TurnplateManager.getInstance().sendGift(uid, result[1])){
					result[0] = 0; //失败
				}
			}
			
			tr.setStatus(result[0]);
			tr.setResultCode(result[1]);
			tr.setResultStr("");
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
