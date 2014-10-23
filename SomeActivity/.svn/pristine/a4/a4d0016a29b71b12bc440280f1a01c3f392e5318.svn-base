package cn.suishou.some.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String digest(String content){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(content.getBytes());
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
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean check(String content, String endecodeCon){
		return endecodeCon.equalsIgnoreCase(digest(content));
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.digest("654321"));
	}
}
