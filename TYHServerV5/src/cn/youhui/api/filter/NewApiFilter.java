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

import cn.youhui.api.servlet3.YHGetTagItems;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Type;
import cn.youhui.manager.LogManager;
import cn.youhui.manager.RecomModelStat;
import cn.youhui.utils.MD5;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;
import cn.youhui.utils.TPool;

/**
 * api接口参数校验
 * @author lijun
 * @since 2014-02-18
 */
@WebFilter("/tyh3/*")
public class NewApiFilter implements Filter {
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		HttpServletRequest req = (HttpServletRequest) request;
//		Map<String, String[]> paramMap = req.getParameterMap();
//		String sign = req.getParameter("sign");
//		String time = req.getParameter("t");
//		if(sign == null || paramMap == null || paramMap.size() == 0){
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
//			return;
//		}
//		if(!checkTime(time)){
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.SIGN_TIME_OUT).toString());
//			return;
//		}
//		boolean flag = false;
//		try{
//			
//			flag = MD5.check(Config.SIGN_KEY + paramsToStr(paramMap) + Config.SIGN_KEY, sign);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	    boolean flag = true;
		if(flag){
			String uid = ParamUtil.getParameter(request, "uid");
			String tagId = ParamUtil.getParameter(request, "tagid");			
			TPool.getInstance().doit(new VisitThread(uid,tagId));			
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
	
	
//	class VisitRecordThread extends Thread{
//		
//		private ServletRequest request;
//		
//		protected VisitRecordThread(ServletRequest request){
//			this.request = request;
//		}
//		
//		public void run() {
//			HttpServletRequest req = (HttpServletRequest) request;
//			String a =  req.getRequestURI();
//			String interfaceName = "";
//			String[] t = a.split("/");
//			if (t != null && t.length > 0) {
//				interfaceName = t[t.length - 1];
//			}
//			String uid = request.getParameter("uid");
//			if(StringUtils.isEmpty(uid)){
//				return;
//			}
//			if("ad".equals(interfaceName)){
//				RecomModelStat.addStat(uid, RecomModelStat.VISIT_APP);
//			}else if("tagitems".equals(interfaceName)){
//				String tagId = request.getParameter("tagid");
//				RecomModelStat.addStat(uid, RecomModelStat.VISIT_TAG, tagId);
//			}
//		}
//		
//	}

//	class VisitThread extends Thread{
//		
//		private ServletRequest request;
//		
//		protected VisitThread(ServletRequest request){
//			this.request = request;
//		}
//		
//		public void run() {
//			HttpServletRequest req = (HttpServletRequest) request;
//			String a =  req.getRequestURI();
//			String interfaceName = "";
//			String[] t = a.split("/");
//			if (t != null && t.length > 0) {
//				interfaceName = t[t.length - 1];
//			}
//			String uid = ParamUtil.getParameter(req, "uid");
//			if("tagitems".equals(interfaceName)){
//				String tagId = ParamUtil.getParameter(req, "tagid");
//				if(!StringUtils.isEmpty(tagId)){
//					LogManager.addlog(uid, "1", tagId);
//				}
//				
//				String type = ParamUtil.getParameter(req, "type");
//				if(YHGetTagItems.tagStyleSale.equals(type)){    //特惠
//					LogManager.addlog(uid, "2", "2");
//				}
//			}else if("alltag".equals(interfaceName)){          //主题
//				LogManager.addlog(uid, "2", "1");
//			}
//		}
//		
//	}
	
	
	class VisitThread extends Thread{		
		private String uid;		
		private String tagId;
		protected VisitThread(String uid, String tagId){
			this.uid = uid;
			this.tagId = tagId;
		}
		
		public void run() {
			LogManager.addlog(uid, Event.VIEW, Type.TAG, tagId);			
		}	
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
