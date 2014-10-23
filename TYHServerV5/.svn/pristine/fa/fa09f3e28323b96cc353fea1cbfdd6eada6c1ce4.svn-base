package cn.youhui.api.servlet3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.jfbad.JFBAdManager;
import cn.youhui.jfbad.JFbAdCallBackResponse;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.ParamUtil;

/**
 * 用户老版本领取集分宝广告   回调
 * @author lijun
 * @since 2014-5-8
 */
@WebServlet("/jfbadcallbackforold")
public class JFBAdCallbackForOld extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String data = ParamUtil.getParameter(request, "data", true);
			data = data.replaceAll(" ", "+");
			data = Encrypt.decode(data);
			String param[] = data.split("#");
			if(param.length !=5){
				response.getWriter().print("params error");
				return;
			}
			String uid = param[1];
			String adId = param[2];
			if(!UserManager.getInstance().isExsitUid(uid)){
				response.getWriter().print("user error");
				return;
			}
			
			String key = JFBAdManager.getInstance().getKey(uid, adId);
			
			JFbAdCallBackResponse rsp =  JFBAdManager.getInstance().callBack(key);
			
			if(rsp.isSuccess() && rsp.getJfbNum() > 0){
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
			
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print("param error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
