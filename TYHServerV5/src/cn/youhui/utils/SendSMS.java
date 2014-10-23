package cn.youhui.utils;

public class SendSMS {
	
	private static String sendmsgPath = "http://121.199.16.178/webservice/sms.php?method=Submit";

	private static boolean sendCode(String phone, String content){
		String params = "&account=cf_suishou&password=19880109";
		params = params + "&mobile=" + phone + "&content=" + content;
		String ret = null;
		try {
			ret = NetManager.getInstance().send(sendmsgPath, params);
		} catch (Exception e) {
			e.printStackTrace();
			ret = null;
		}
		System.out.println(ret);
		if(ret != null && ret.indexOf("<code>2</code>") > -1) return true;
		     //<code>4081</code>       同一手机号码一分钟之内发送频率不能超过1条
		     //
		else return false;
	}
	
	public static boolean sendForgCode(String phone, String code){
		String content = "亲，欢迎使用随 手 优 惠！您正在重置您的随 手 优 惠账户提现密码，验证码："+ code +"如非本人操作请检查账户安全。";
		return sendCode(phone, content);
	}
	
	public static boolean sendForgCodeForPC(String phone, String code){
		String content = "验证码 【"+code+"】,您正在使用随手科技产品，需要进行验证码校验。【为保证账号安全请勿将此验证码提供给他人】";
		return sendCode(phone, content);
	}
	
	public static boolean sendRegCode(String phone, String code){
		String content = "亲，欢迎使用随 手 优 惠！您正在将本手机号绑定为您的随 手 优 惠密保手机，验证码："+code+"如非本人操作请忽略。";
		return sendCode(phone, content);
	}
	
	public static boolean sendRegCodeForPC(String phone, String code){
		String content = "验证码 【"+code+"】,您正在使用随手科技产品，需要进行验证码校验。【为保证账号安全请勿将此验证码提供给他人】";
		return sendCode(phone, content);
	}
	
	public static void main(String[] args) {
		System.out.println(sendRegCode("15850673417", "123456"));
	}
}
