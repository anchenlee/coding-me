package cn.youhuiWeb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.AES256;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 需登陆的接口参数校验
 * @author liuxj
 * @since 2014-8-19
 */
@WebFilter("/weblogin/*")
public class LoginFilter implements Filter {

	public void destroy() {}
	public void init(FilterConfig fConfig) throws ServletException {}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws  ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		String uid = ParamUtil.getParameter(req, "uid", true);		
		String checkcode = ParamUtil.getParameter(req, "checkcode", true).replaceAll(" ", "+");
		String password = UserManager.getInstance().getUserByUid(uid).getPassword();
		try {
			if(AES256.decrypt(Config.AES_PWD, checkcode).equals(uid+password)){
				chain.doFilter(request, response);
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
		}
	}

	
}
