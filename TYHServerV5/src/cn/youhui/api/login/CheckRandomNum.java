package cn.youhui.api.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.UserManager;
import cn.youhui.ramdata.SMSRandomCacher;
import cn.youhui.utils.AES256;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 验证随机码是否正确
 * @author lijun
 * @since 2013-09-07
 */
@WebServlet("/login/checkrand")
public class CheckRandomNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);
			String phoneNum = ParamUtil.getParameter(request, "phone_num");
			String randomNum = ParamUtil.getParameter(request, "random", true);
			if(phoneNum == null || "".equals(phoneNum)){
				phoneNum = UserManager.getInstance().getPhone(uid);
			}else{
				try{
					phoneNum = phoneNum.replaceAll(" ", "+");
					phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum);
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
					return;
				}
			}
			String randomNCa = SMSRandomCacher.getInstance().getRandomNum(phoneNum);
			if(randomNCa != null && randomNCa.equals(randomNum)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
			}else {
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.AUTH_CODE_ERROR).toString());
			}
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
