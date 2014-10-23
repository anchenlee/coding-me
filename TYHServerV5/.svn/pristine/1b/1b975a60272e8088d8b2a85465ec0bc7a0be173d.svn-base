package cn.youhui.utils;

public class VersionUtil {
	
	private static int iphoneNormalVersion = Integer.parseInt("3.4.0".replaceAll("\\.", ""));    //iphone的正常最低版本
	private static int ipadNormalVersion = Integer.parseInt("3.4.0".replaceAll("\\.", ""));   //ipad的正常最低版本
	private static int androidNormalVersion = Integer.parseInt("3.3.2".replaceAll("\\.", ""));   //android的正常最低版本

	/**
	 * 判断是否为低版本
	 * @return
	 */
	public static boolean isLowVersion(String platform, String version){
		boolean flag = true;
		int verint = Integer.parseInt(version.replaceAll("\\.", ""));
		if("iphone".equalsIgnoreCase(platform) && verint >= iphoneNormalVersion){
			flag = false;
		}else if("android".equalsIgnoreCase(platform) && verint >= androidNormalVersion){
			flag = false;
		}else if("ipad".equalsIgnoreCase(platform) && verint >= ipadNormalVersion){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * version是否达到ev
	 * @param ev
	 * @param version
	 * @return
	 */
	public static boolean isHigher(String ev, String version){
		try{
		int verint = Integer.parseInt(version.replaceAll("\\.", ""));
		int evint = Integer.parseInt(ev.replaceAll("\\.", ""));
		return verint >= evint;
		}catch(Exception e){
			return false;
		}
	}
}
