package cn.youhui.utils;

public class PasswordUtil {
	
	/**
	 * 检验密码格式
	 * @param pass
	 * @return
	 */
	public static boolean checkPass(String pass){
		if(pass == null || "".equals(pass)){
			return false;
		}
		if(pass.length() > 16 || pass.length() < 6){
			return false;
		}
		return pass.matches("[0-9a-zA-Z]*");
	}
}
