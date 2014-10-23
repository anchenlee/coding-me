package cn.youhui.system;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.ramdata.UserJFBAccountCacher;
import cn.youhui.utils.ParamUtil;

/**
 * 更新用户集分宝帐户缓存信息
 * @author lijun
 * @since 2014-02-10
 */
@WebServlet("/upjfbaccount")
public class UpUserJFBAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);
			String type = ParamUtil.getParameter(request, "type", true);
			int jfbNum = ParamUtil.getParameterInt(request, "jfbnum");
			jfbNum = Math.abs(jfbNum);
			if("addjfb".equals(type)){
				UserJFBAccountCacher.getInstance().addGainNum(uid, jfbNum);
			}else if("addfanlijfb".equals(type)){
				UserJFBAccountCacher.getInstance().addGainNum(uid, jfbNum);
				UserJFBAccountCacher.getInstance().addFLNum(uid, jfbNum);
			}else if("txjfb".equals(type)){
				UserJFBAccountCacher.getInstance().addTxNum(uid, jfbNum);
			}else if("suctxjfb".equals(type)){
				UserJFBAccountCacher.getInstance().addSucTxNum(uid, jfbNum);
			}else if("failtxjfb".equals(type)){
				UserJFBAccountCacher.getInstance().addFailTxNum(uid, jfbNum);
			}
			response.getWriter().print("success");
		}catch(Exception e){
			response.getWriter().print("param is error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
