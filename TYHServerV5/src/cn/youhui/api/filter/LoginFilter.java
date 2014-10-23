package cn.youhui.api.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.utils.MD5;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 登陆接口参数校验
 * @author lijun
 * @since 2013-12-13
 */
@WebFilter("/login/*")
public class LoginFilter implements Filter {

	private static final String KEY = "77499981";

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		Map<String, String[]> paramMap = req.getParameterMap();
		String sign = req.getParameter("sign");
		String time = req.getParameter("t");
		if(sign == null || paramMap == null || paramMap.size() == 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		if(!checkTime(time)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.SIGN_TIME_OUT).toString());
			return;
		}
		boolean flag = false;
		try{
			flag = MD5.check(KEY + paramsToStr(paramMap) + KEY, sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			chain.doFilter(request, response);
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.SIGN_FAIL).toString());
		}
	}

	/**
	 * 将全部请求参数按字母顺序拼接成字符串
	 * @param paramMap
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private String paramsToStr(Map<String, String[]> paramMap) throws UnsupportedEncodingException{
		StringBuffer sb = new StringBuffer();
		TreeMap<String, String[]> newmap = new TreeMap<String, String[]>();
		newmap.putAll(paramMap);
		Iterator<String> keys = newmap.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			if(!"sign".equals(key)){
				String param = paramMap.get(key)[0];
				sb.append(key).append("=").append(param).append("&");
			}
		}
		return sb.substring(0, sb.length()-1);
	}
	
	private boolean checkTime(String time){
		boolean flag = false;
		try{
			long timel = Long.parseLong(time);
			long now = System.currentTimeMillis();
			if(Math.abs(now - timel) < 10*60*60*1000){
				flag = true;
			}
		}catch(Exception e){
		}
		return flag;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
