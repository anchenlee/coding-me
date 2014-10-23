package cn.youhui.utils;

public class UserUtil {

	/**
	 * 手机号加***
	 * @param phoneNum
	 * @return
	 */
	public static String jmPhoneNum(String phoneNum){
		if(phoneNum == null || "".equals(phoneNum) || phoneNum.length() != 11){
			return "";
		}else{
			return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11);
		}
	}
}
