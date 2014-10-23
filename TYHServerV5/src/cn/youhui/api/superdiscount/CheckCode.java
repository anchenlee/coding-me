package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.AntiAttack;
import cn.youhui.cacher.dao.AntiAttackCacher;
import cn.youhui.dao.CheckCodeDAO;
import cn.youhui.utils.ParamUtil;

/**
 * 商户来验证串码可用性
 * jiangjun
 */
@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckCode() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = ParamUtil.getParameter(request, "code");
		String itemId = ParamUtil.getParameter(request, "item_id");
		
		
		String ip=getRemoteAddress(request);
		AntiAttack ak=new AntiAttack();
		ak.setIp(ip);
		ak.setItemId(itemId);
		if(AntiAttackCacher.getInstance().getAntiAttack(ip)==null){
			AntiAttackCacher.getInstance().addAntiAttack(ip,ak);
			response.getWriter().print(CheckCodeDAO.verifyCode(code,itemId));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	   public String getRemoteAddress(HttpServletRequest request) {
	        String ip = request.getHeader("x-forwarded-for");  
	        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	            ip = request.getHeader("Proxy-Client-IP");  
	        }
	        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }
	        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	            ip = request.getRemoteAddr();  
	        }
	        return ip;  
	    }
	  
	    public String getMACAddress(String ip) {
	        String str = "";  
	        String macAddress = "";  
	        try {
	            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
	            InputStreamReader ir = new InputStreamReader(p.getInputStream());  
	            LineNumberReader input = new LineNumberReader(ir);  
	            for (int i = 1; i < 100; i++) {
	                str = input.readLine();  
	                if (str != null) {  
	                    if (str.indexOf("MAC Address") > 1) {
	                        macAddress = str.substring(  
	                                str.indexOf("MAC Address") + 14, str.length());  
	                        break;  
	                    }
	                }
	            }
	        } catch (IOException e) {  
	            e.printStackTrace(System.out);  
	        }
	        return macAddress;  
	    }
	    
}
