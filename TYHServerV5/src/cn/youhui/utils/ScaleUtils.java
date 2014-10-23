package cn.youhui.utils;

public class ScaleUtils {
	
	 private static char[] encodeChars = new char[]{
         'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
         'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
         'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
         'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
         'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	 
	 private static byte[] decodeChars = new byte[]{
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
         50, 51, 52, 53, 54, 55, 56, 57, 58, 59, -1, -1, -1, -1, -1, -1,
         -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,15, 16, 17,
         18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,-1, 
         26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
         41, 42, 43, 44, 45, 46, 47, 48, 49, -1, -1, -1, -1, -1};
	
	 /**
	  * 将10进制数转换为60进制, 仅限于int能表示的十进制
	  */
	 public static String f10t60(int i)throws Exception{
		 try{
			 String r = "";
			 int len = encodeChars.length;
			 int t = i % len;
			 int c = i / len;
			 r = encodeChars[t] + "";
			 while(c > 0){
				 t = c % len;
				 c = c / len;
				 r = encodeChars[t] + r;
			 }
			 
			 return r;
		 }catch(Exception e){
			 throw new Exception(e);
		 }
	 }
	 
	 /**
	  * 将60进制转换为10进制
	  */
	 public static int f60t10(String s)throws Exception{
		 try{
			 int ret = 0;
			 if(s == null || "".equals(s)){
				 return ret;
			 }
			 
			 char[] c = s.toCharArray();
			 for(int i = c.length-1; i>=0; i--){
				 ret += Math.pow(encodeChars.length, (c.length-1 - i))*decodeChars[c[i]];
			 }
			 return ret;
		 }catch(Exception e){
			 throw new Exception(e);
		 }
	 }

}
