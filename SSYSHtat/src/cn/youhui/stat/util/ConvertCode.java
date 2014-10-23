package cn.youhui.stat.util;

public class ConvertCode {

	public static String convert(String str){
		String xx="";
		try{
			byte[] b=null;
			b=str.getBytes("iso8859-1");
			xx= new String(b,"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return xx;
	}
}
