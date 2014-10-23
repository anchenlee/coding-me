package cn.youhui.api.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.UserBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.TaobaoManager;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.AES256;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 先注册手机号后，绑定淘宝帐户
 * @author lijun
 * @since 2013-09-07
 */
@WebServlet("/login/bindingtb")
public class BindingTaobao extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String taobaoUid = ParamUtil.getParameter(request, "taobao_uid");
			String token = ParamUtil.getParameter(request, "token", true);
			String taobaoNick = ParamUtil.getParameter(request, "taobao_nick", true);
			String uid = ParamUtil.getParameter(request, "uid", true);
			String phoneNum = ParamUtil.getParameter(request, "phone_num", true);
			
			phoneNum = phoneNum.replaceAll(" ", "+");
			phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum);
		
			if(!UserManager.getInstance().checkUidPhone(phoneNum, uid)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			taobaoNick = TaobaoManager.decodeTBNick(taobaoNick);
			if("-1".equals(taobaoNick) || !taobaoNick.equals(TaobaoManager.getNickByToken(token))){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			
			if(UserManager.getInstance().isTBNickBindPhone(taobaoNick)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.TAOBAONICK_PHONENUM_BINDING).toString());
				return;
			}
			
			UserBean user = UserManager.getInstance().bindingByTB(taobaoNick, taobaoUid, uid, phoneNum);
			if(user == null || null == user.getUid() || "".equals(user.getUid())){
				response.getWriter().println(RespStatusBuilder.message(ActionStatus.REGISTER_FAIL).toString());
			}else{
				response.getWriter().println(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, user.toXML()).toString());
			}
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
