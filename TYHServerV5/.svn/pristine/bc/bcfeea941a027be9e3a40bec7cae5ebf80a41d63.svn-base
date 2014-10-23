package cn.youhui.jfbad;

/**
 * 将老的签到方式记录到新的签到记录中
 * @author lijun
 * @since 2014-4-23
 */
public class SignInRecordThread extends Thread{

	private SignIn sign = null;
	
	public SignInRecordThread(SignIn sign){
		this.sign = sign;
	}
	
	public void run() {
		SignInManager.getInstance().addFromOld(sign);
	}
}
