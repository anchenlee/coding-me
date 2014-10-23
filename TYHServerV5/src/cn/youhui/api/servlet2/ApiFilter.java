package cn.youhui.api.servlet2;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.youhui.bean.VisitRecord;
import cn.youhui.bean.VisitRecordInterface;
import cn.youhui.bean.VisitRecordUid;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.VisitRecordInterfaceDAO;
import cn.youhui.manager.VisitRecordManager;
import cn.youhui.manager.VisitRecordUidDAO;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

@WebFilter("/tyh2/*")
public class ApiFilter implements Filter {

	private static ExecutorService ThreadPool = Executors.newFixedThreadPool(20);
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if(Config.paramDeBug){
			String uid = request.getParameter("uid");
			String platform = request.getParameter("platform");
			String channel = request.getParameter("channel");
			String version = request.getParameter("version");
			String imei = request.getParameter("imei");
			String imsi = request.getParameter("imsi");
			String data = request.getParameter("data");       //data里面都有uid
			if((uid==null && StringUtils.isEmpty(data))|| StringUtils.isEmpty(platform) || StringUtils.isEmpty(channel) || StringUtils.isEmpty(version) || StringUtils.isEmpty(imei) || StringUtils.isEmpty(imsi)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
		}
		
//		ThreadPool.execute(new VisitRecordThread(request));
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	class SaveVisitRecordThread extends Thread{
		private ServletRequest request;
		
		protected SaveVisitRecordThread(ServletRequest request){
			this.request = request;
		}
		
		public void run(){
			String params = getParams(request.getParameterMap());
			HttpServletRequest req = (HttpServletRequest) request;
			String a =  req.getRequestURI();
			String interfaceName = "";
			String[] t = a.split("/");
			if (t != null && t.length > 0) {
				interfaceName = t[t.length - 1];
			}
			String ip = getIp(req);
			String tagId = "";
			if("tagitems".equals(interfaceName)){
				tagId = request.getParameter("tagid");
			}
			String uid = request.getParameter("uid");
			String platform = request.getParameter("platform");
			String channel = request.getParameter("channel");
			String version = request.getParameter("version");
			String imei = request.getParameter("imei");
			String imsi = request.getParameter("imsi");
			VisitRecord vr = new VisitRecord(interfaceName, uid, platform, channel, version, imei, imsi, ip, params, tagId, "", "");
			VisitRecordManager.getInstance().add(vr);
		}
		
		private String getIp(HttpServletRequest request){  
	        String ip = request.getHeader("X-Real-IP");   
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){   
	        	ip = request.getHeader("Proxy-Client-IP");    
	        }    
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){    
	        	ip = request.getHeader("WL-Proxy-Client-IP");    
	        }   
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){   
	        	ip = request.getRemoteAddr();    
	        }    
	        return ip;    
		} 
		
		private String getParams(Map<String, String[]> pMap){
			StringBuffer sb = new StringBuffer();
			if(pMap != null && !pMap.isEmpty()){
				for(Map.Entry<String, String[]> entry : pMap.entrySet()){
					sb.append(entry.getKey() + "=" + entry.getValue()[0] + "&");
				}
			}
			return sb.toString();
		}
	}
	
	class VisitRecordThread extends Thread{
		private final DateFormat ft = new SimpleDateFormat("yyyyMMdd");
		private final DateFormat fthour = new SimpleDateFormat("yyyyMMddHH");
		
		private ServletRequest request;
		
		protected VisitRecordThread(ServletRequest request){
			this.request = request;
		}
		
		public void run(){
			HttpServletRequest req = (HttpServletRequest) request;
			String a =  req.getRequestURI();
			String interfaceName = "";
			String[] t = a.split("/");
			if (t != null && t.length > 0) {
				interfaceName = t[t.length - 1];
			}
			
			String uid = request.getParameter("uid");
			String platform = request.getParameter("platform");
			String version = request.getParameter("version");
			String imei = request.getParameter("imei");
			String imsi = request.getParameter("imsi");
			
			if(uid != null && !"".equals(uid)){
				VisitRecordUid vru = new VisitRecordUid(uid, platform, version, imei, imsi, interfaceName, ft.format(new Date()));
				VisitRecordUidDAO.getInstance().add(vru);
				if("ads".equals(interfaceName)){
					VisitRecordUidDAO.getInstance().addAds(vru);
				}
			}
			VisitRecordInterface vrf = new VisitRecordInterface(fthour.format(new Date()), interfaceName);
			VisitRecordInterfaceDAO.getInstance().add(vrf);
		}
	}
}
