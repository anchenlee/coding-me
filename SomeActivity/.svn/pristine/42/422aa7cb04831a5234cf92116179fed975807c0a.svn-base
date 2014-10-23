package cn.suishou.some.autumnitem;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/f")
public class ipServlet extends HttpServlet {  
  
	public ipServlet(){
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doPost(request, response);  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        String ip = getRemoteAddress(request);  
        System.out.println(ip);  
        System.out.println("mac:"+getMACAddress(ip));  
  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        out  
                .println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");  
        out.println("<HTML>");  
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");  
        out.println("  <BODY>");  
        out.print("    This is ");  
        out.print(this.getClass());  
        out.println(", using the POST method");  
        out.println("  </BODY>");  
        out.println("</HTML>");  
        out.flush();  
        out.close();  
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
  
//    public String getMACAddress(String ip) {  
//        String str = "";  
//        String macAddress = "";  
//        try {  
//            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
//            InputStreamReader ir = new InputStreamReader(p.getInputStream());  
//            LineNumberReader input = new LineNumberReader(ir);  
//            for (int i = 1; i < 100; i++) {  
//                str = input.readLine();  
//                if (str != null) {  
//                    if (str.indexOf("MAC Address") > 1) {  
//                        macAddress = str.substring(  
//                                str.indexOf("MAC Address") + 14, str.length());  
//                        break;  
//                    }  
//                }  
//            }  
//        } catch (IOException e) {  
//            e.printStackTrace(System.out);  
//        }  
//        return macAddress;  
//    }  
    public String getMACAddress(String ip) {
        String str = "";
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -a " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //if (str.indexOf("MAC Address") > 1) {
                    if (str.indexOf("MAC") > 1) {
                        macAddress = str.substring(
                                str.indexOf("=") + 2, str.length());
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