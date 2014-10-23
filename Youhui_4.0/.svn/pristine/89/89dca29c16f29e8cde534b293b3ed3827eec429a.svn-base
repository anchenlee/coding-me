package com.netting.util;


import java.math.BigInteger;
import java.util.Random;


public class OuterCode 
{
	
	private static char[] encodeChars = new char[]
	{
         'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
         'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
         'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
         'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
         'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
	 
	private static byte[] decodeChars = new byte[]
	{
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
         50, 51, 52, 53, 54, 55, 56, 57, 58, 59, -1, -1, -1, -1, -1, -1,
         -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,15, 16, 17,
         18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,-1, 
         26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
         41, 42, 43, 44, 45, 46, 47, 48, 49, -1, -1, -1, -1, -1
    };
	 
	 
	 
	public static String getOuterCode(String uid, String platform, String jfbRate, String fromChannel, String fromValue)
	{
		 try 
		 {
			 int uidi = 0;
			 if(uid != null && !uid.equals(""))
			 {
				 uidi = Integer.parseInt(uid);
			 }
			 if(jfbRate == null || jfbRate.equals(""))
			 {
				 jfbRate = "0";
			 }
			 if(fromChannel == null || fromChannel.equals(""))
			 {
				 fromChannel = "0";
			 }
			 if(fromValue == null || fromValue.equals(""))
			 {
				 fromValue = "0";
			 }
			 int jfbRatei = (int)(Double.parseDouble(jfbRate) * 10);
			 int fromChanneli = Integer.parseInt(fromChannel);
			 int fromValuei = Integer.parseInt(fromValue);
			 int isFanli = 0;
			 if(jfbRatei > 0)
			 {
				 isFanli = 1;
			 }
			 
			 String o = OuterCode.encode(jfbRatei, uidi, fromChanneli, fromValuei, isFanli);
			 String plat = "A";
			 if("android".equalsIgnoreCase(platform))
			 {
				 plat = "C";
			 }
			 else if("iphone".equalsIgnoreCase(platform))
			 {
				 plat = "D";
			 }
			 else if("ipad".equalsIgnoreCase(platform))
			 {
				 plat = "E";
			 }
			 return plat + o;
		 } 
		 catch (Exception e) 
		 {
			 e.printStackTrace();
			 return "";
		 }
	 }

	 /**
	  * 编码
	  * @param rate 返利比
	  * @param platform 平台
	  * @param uid
	  * @param from  来源
	  * @param fromId 来源ID
	  * @param isFanli 是否返利   0,没有   1,有
	  * @return
	  * @throws Exception
	  */
	public static String encode(int rate, int uid, int from, int fromId, int isFanli) throws Exception{
		if(rate > 255 || from > 31 || fromId > 1048575 || isFanli > 1){
			throw new Exception("rate ,platform or from is over limit.");
		}
		BigInteger ratebi = new BigInteger(rate + "");
		BigInteger uidbi = new BigInteger(uid + "");
		BigInteger frombi = new BigInteger(from + "");
		BigInteger frIdbi = new BigInteger(fromId + "");
		BigInteger isFanlibi = new BigInteger(isFanli + "");
		
		String uid2str = fillZero(uidbi.toString(2), 30);
		String rate2str = fillZero(ratebi.toString(2), 8);
		String fro2str = fillZero(frombi.toString(2), 5);
		String froId2str = fillZero(frIdbi.toString(2), 20);
		String isfan2str = fillZero(isFanlibi.toString(2), 1);
		
		String re = uid2str + rate2str + fro2str + froId2str + isfan2str;
		return f2t60(move(re));
	}
	
	/**
	 * 获取s个0，1随机数
	 */
	public static String getRand(int s){
		Random rd = new Random();
		String ss = new BigInteger(Math.abs(rd.nextInt())+"",10).toString(2);
		if(ss.length() >= s){
			ss = ss.substring(0, s);
		}else{
			ss = fillZero(ss, s);
		}
		return ss;
	}

	/**
	 * 解码
	 * @param 待解码的字符串
	 * @return 0 rate; 1 uid; 2 from; 3 fromid; 4 isfanli
	 */
	public static int[] decode(String s) throws Exception{
		if(s == null || "".equals(s)){
			return null;
		}
		
		String er = f60t2(s.substring(0));
		
		er = move(fillZero(er, 64));
		
		String uid2str = er.substring(0, 30);
		String rate2str = er.substring(30, 38);
		String fro2str = er.substring(38, 43);
		String froid2str = er.substring(43, 63);
		String isfanli2str = er.substring(63, 64);
		
		
		int rate = new BigInteger(rate2str, 2).intValue();
		int uid = new BigInteger(uid2str, 2).intValue();
		int from = new BigInteger(fro2str, 2).intValue();
		int fromId = new BigInteger(froid2str, 2).intValue();
		int isFanli = new BigInteger(isfanli2str, 2).intValue();
		
		return new int[]{rate, uid, from, fromId, isFanli};
	}
	
	/**
	 * 
	 * @param s 要填满0 的二进制字符串
	 * @param i 填充到的位数
	 * @return
	 */
	private static String fillZero(String s, int i){
		int n = i - s.length(); 
		StringBuffer zero = new StringBuffer();
		while(n -- > 0){
			zero.append("0");
		}
		return zero.toString() + s;
	}
	
	/**
	 * 变换二进制字符串的位置，以4位为步长，调换前后位置
	 */
	private static String move(String s){ 
		StringBuffer sb = new StringBuffer();
		int i = s.length() / 4;
		for(int j = i; j>0; j--){
			sb.append(s.substring(4*(j-1), 4*j));
		}
		return sb.toString();
	}
	
	/**
	 * 二进制转换为60进制数
	 */
	private static String f2t60(String er) throws Exception{
		if(er.length() == 64){
			String er1 = er.substring(0, 29);
			String er2 = er.substring(29, er.length());
			return filly(f2t60(er1), 5) + filly(f2t60(er2), 6);
		}
		
		BigInteger erb = new BigInteger(er, 2);
		
		try{
			 String r = "";
			 int len = encodeChars.length;
			 BigInteger lenb = new BigInteger(len + "");
			 BigInteger[] re = erb.divideAndRemainder(lenb);
			 BigInteger t = re[1];
			 BigInteger c = re[0];
			 r = encodeChars[t.intValue()] + "";
			 while(c.compareTo(new BigInteger("0")) == 1){
				 re = c.divideAndRemainder(lenb);
				 t = re[1];
				 c = re[0];
				 r = encodeChars[t.intValue()] + r;
			 }
			 
			 return r;
		 }catch(Exception e){
			 throw new Exception(e);
		 }
	}
	
	/**
	  * 将60进制转换为2进制
	  */
	 private static String f60t2(String s)throws Exception{
		 try{
			 if(s.length() == 11){
				 String s1 = s.substring(0, 5);
				 String s2 = s.substring(5, s.length());
				 return fillZero(f60t2(s1), 29) + fillZero(f60t2(s2), 35);
			 }
			 BigInteger ret = new BigInteger("0");
			 if(s == null || "".equals(s)){
				 return null;
			 }
			 
			 char[] c = s.toCharArray();
			 for(int i = c.length-1; i>=0; i--){
				  long js = (long) (decodeChars[c[i]] * Math.pow(encodeChars.length, (c.length-1 - i)));
				  ret = ret.add(new BigInteger(js + ""));
			 }
			 return ret.toString(2);
		 }catch(Exception e){
			 throw new Exception(e);
		 }
	 }
	 
	 /**
	  * 将60进制数前面补齐
	  * @param s 需要填充的60进制数
	  * @param i 填充到的位数
	  * @return
	  */
	 private static String filly(String s, int i){
		    int n = i - s.length(); 
			StringBuffer zero = new StringBuffer();
			while(n -- > 0){
				zero.append(encodeChars[0]);
			}
			return zero.toString() + s;
	 }
	
	 public static void main(String[] args) throws Exception {
		int r[] = decode("AAAGYAAAAAA");
		System.out.println(r[0]);
		System.out.println(r[1]);
		System.out.println(r[2]);
		System.out.println(r[3]);
		System.out.println(r[4]);
	}
}
