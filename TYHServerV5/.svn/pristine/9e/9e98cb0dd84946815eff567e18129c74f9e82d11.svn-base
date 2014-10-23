package cn.youhui.utils;

import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt {

	public static String decode(String str){
		BASE64Decoder base64 = new BASE64Decoder();
		String content = "";
		try {
			str = new String(base64.decodeBuffer(str));
			content = str.substring(2, str.length() - 2);
			content = new String(base64.decodeBuffer(content), "UTF-8");
		} catch (Exception e1) {
			return "";
		}
		return content;
	}
	
	
	public static String encode(String content) {
		try {
			BASE64Encoder base64 = new BASE64Encoder();
			String rand = getRand(4);
			content = content + "#" + rand;
			content = base64.encode(content.getBytes());
			content = rand.substring(0, 2) + content + rand.substring(2,4);
			content = base64.encode(content.getBytes());
		} catch (Exception e) {
		}
		return content;
	}
	
	public static String encode2(String content) {
		try {
			BASE64Encoder base64 = new BASE64Encoder();
			String rand = getRand(4);
			// content = content + "#" + rand;
			content = base64.encode(content.getBytes());
			content = rand.substring(0, 2) + content + rand.substring(2,4);
			content = base64.encode(content.getBytes());
		} catch (Exception e) {
		}
		return content;
	}
	
    private static String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private static String getRand(int i){
		StringBuffer sb = new StringBuffer();
		Random rm = new Random();
		while(i > 0){
			i--;
			int index = (int)(rm.nextFloat() * key.length());
			index = Math.min(index, key.length()-1);
			index = Math.max(0, index);
			sb.append(key.charAt(index));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(Encrypt.encode("#82127280##leo52fn1"));
		
//		System.out.println(Encrypt.encode("#108612506#nick#李军2324"));
		
		System.out.println(Encrypt.decode("NjNJemd6T0RJd016ZzRJM0JvYjI1bEl6RTFNaW9xS2lvek5EazRJell6TVRNPTEz"));
	}
}
