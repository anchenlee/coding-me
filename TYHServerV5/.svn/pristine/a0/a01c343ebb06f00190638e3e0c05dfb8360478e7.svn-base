package cn.youhui.acapi.newuser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.youhui.common.Config;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamSignUtil;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.StringUtils;
import cn.youhui.utils.WebUtil;
import cn.youhui.acapi.newuser.DES;

/**
 * 新用户转盘抽奖
 * @author lijun
 * @since 2014-7-6
 */
@WebServlet("/turnplate")
public class NewUserTurnplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		TurnplateResult tr = new TurnplateResult();
		try{
			Map<String, String[]> paramsMap = request.getParameterMap();
			Map<String, String> params = new HashMap<String, String>();
			for(Map.Entry<String, String[]> m : paramsMap.entrySet()){
				params.put(m.getKey(), m.getValue()[0]);
			}
			
			String sign = ParamUtil.getParameter(request, "sign", true);
			System.out.println("signnnnn :" + sign);
			System.out.println("parms  :" + ParamSignUtil.paramsToStrs(paramsMap));
			if(!ParamSignUtil.check(params, Config.SIGN_KEY, sign)){
				response.getWriter().print(tr.toJson());
				return;
			}
			
			String imei = ParamUtil.getParameter(request, "tyh_web_imei", true);
			String imsi = ParamUtil.getParameter(request, "tyh_web_imsi");
			String platform = ParamUtil.getParameter(request, "tyh_web_platform");
			String ip = WebUtil.getIpAddr(request);
			saveLuckDeviceInfo(request,imei,imsi,ip,platform);
			
			boolean isException = !checkDevice(request);
			if("android".equalsIgnoreCase(platform)){
				tr.setStatus(10);   //安卓活动结束
			}else{
				int result[] = TurnplateManager.getInstance().turn(imei, imsi, ip, isException, platform);
				tr.setStatus(result[0]);
				tr.setResultCode(result[1]);
				tr.setResultStr("");
			}
			response.getWriter().print(tr.toJson());
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(tr.toJson());
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private boolean isIphone(HttpServletRequest request){
		boolean flag = false;
		String platform = ParamUtil.getParameter(request, "tyh_web_platform");
		String version = ParamUtil.getParameter(request, "tyh_web_version");
		String versionCode = ParamUtil.getParameter(request, "tyh_web_version_code");  
		String channel = ParamUtil.getParameter(request, "tyh_web_channel");
		String sche = ParamUtil.getParameter(request, "sche");
		if("iphone".equals(platform) && "4.0.5".equals(version) && "50".equals(versionCode) && "App Store".equals(channel) && "taobaotosuishou".equals(sche)){
			flag = true;
		}
		return flag;
	}
	
	private boolean checkDevice(HttpServletRequest request){
		String userAgents = request.getHeader("user-agent").replaceAll("\\(|\\)", "");
		boolean flag = true;
		if(userAgents.indexOf("BlueStacks") > -1 || userAgents.indexOf("Lan779") > -1 || userAgents.indexOf("Lan998") > -1 || userAgents.indexOf("Lan789") > -1 ){
			flag = false;
		}
		Pattern p1 = Pattern.compile("Mozilla/5.0 (Linux; U; Android 2.3.4;.*Build/GRJ22) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1/.*".replaceAll("\\(|\\)", ""));
		Pattern p2 = Pattern.compile("Mozilla/5.0 (Linux; U; Android 4.0.4;.* Build/IMM76L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30/.*".replaceAll("\\(|\\)", ""));
		Pattern p3 = Pattern.compile("Mozilla/5.0 (Linux; U; Android 4.3;.*Build/JLS36G) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30/.*".replaceAll("\\(|\\)", ""));
		if(p1.matcher(userAgents).matches() || p2.matcher(userAgents).matches() || p3.matcher(userAgents).matches()){
			flag = false;
		}
		return flag;
	}
	
	public boolean saveLuckDeviceInfo(HttpServletRequest request, String imei, String imsi,String ip, String platform){
		boolean flag = false;
		try {
			String tyhWebDevice = request.getParameter("tyh_web_device");
			if(!StringUtils.isEmpty(tyhWebDevice)){
			String userAgent = request.getHeader("user-agent");
			String decodeTWD = new DES("67s$df%sJMjf^K&*o9i45EN8").decrypt(tyhWebDevice, "UTF-8");
			String data [] = decodeTWD.replace("{", "").split("}");
			flag = TurnplateManager.getInstance().luckDeviceInfoSave(imei, imsi, userAgent, ip, data, platform);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static void main(String[] args) {
		Pattern p1 = Pattern.compile("Mozilla/5.0 (Linux; U; Android 2.3.4;.*Build/GRJ22) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1/.*".replaceAll("\\(|\\)", ""));
		Pattern p2 = Pattern.compile("Mozilla/5.0 (Linux; U; Android 4.0.4;.* Build/IMM76L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30/.*".replaceAll("\\(|\\)", ""));
		Pattern p3 = Pattern.compile("Mozilla/5.0 (Linux; U; Android 4.3;.*Build/JLS36G) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30/.*".replaceAll("\\(|\\)", ""));
		
		String s = "Mozilla/5.0 (Linux; U; Android 4.0.4; zh-cn; Lan779 Build/IMM76L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30/SuishouAndroid_4.0.5_anzhi".replaceAll("\\(|\\)", "");
		
		System.out.println(p2.matcher(s).matches());
	}
}
