package cn.youhui.util.oufeicz;

import cn.youhui.utils.NetManager;

public class PhoneNumAttributionUtil {

	public static String getPhoneNumAttribution(String phoneNum){
		String attr = "";
		if(phoneNum == null || phoneNum.length() < 7){
			return attr;
		}
		String apiUrl = "http://api2.ofpay.com/mobinfo.do";
		String paramStr = "mobilenum=" + phoneNum.substring(0, 7);
		try {
			String out = NetManager.getInstance().sendGB2312(apiUrl,paramStr);
			if(out != null && !"暂时不支持此类号码".equals(out) && out.length() > 8){
				attr = out.substring(8, out.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attr;
	}
	
	public static void main(String[] args) {
		new PhoneNumAttributionUtil();
		System.out.println(getPhoneNumAttribution("15850673417"));
	}
}
