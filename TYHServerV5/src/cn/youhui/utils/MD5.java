package cn.youhui.utils;

import java.security.MessageDigest;

public class MD5 {
	
	public static String digest(String content){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(content.getBytes("UTF-8"));
			byte[] b = md5.digest();
			StringBuffer buf = new StringBuffer(""); 
			int i;
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if(i<0) i+= 256; 
				if(i<16) 
					buf.append("0"); 
				buf.append(Integer.toHexString(i)); 
			} 
			return buf.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean check(String content, String endecodeCon){
		return endecodeCon.equalsIgnoreCase(digest(content));
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.digest("77499981suishouyouhuiotherid=102966939&time=1408702569124&tyh_web_channel=own&tyh_web_device=fd000260e9f460b136a90780e4c7c6b229b6b61a466015e313374b2af6c4951c81b702430ed8a3be74f2b4fa175c51e952d04b18e74ff1ca2d0710c51dd047611f43db70f2311452c75154c8c62a0a250ac32a8e99ddc0ba581114c8b5e383d724614e41913c68e52f03051c2bd89a82a1c5c19ae10592992e413e3fe990fac40a022440fc315a81631b4562e3baa964&tyh_web_imei=867064013299092&tyh_web_imsi=&tyh_web_platform=android&tyh_web_taobaonick=刘敏19900913&tyh_web_uid=102966939&tyh_web_version=4.0.577499981"));
	}
}
