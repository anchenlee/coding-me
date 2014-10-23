package cn.youhui.common;

public class Enums {

	public enum ActionStatus {

		NORMAL_RETURNED(1000, "OK","成功"),
		NORMAL_RETURNED_BINDPHONE(1000, "OK", "绑定成功"),
		REQUEST_TYPE_ERROR(1001,"Request Type Error"), 
		PARAMAS_ERROR(1002, "Parameters Error"), 
		DATABASE_ERROR(1003, "Database Error"), 
		NOT_LOGIN(1004, "NOT Login"), 
		FORBIDDEN(1005, "Access Forbidden"), 
		UNKNOWN(1006, "Unknow Error"), 
		SERVER_ERROR(1007, "Server Error"), 
		NO_RESULT(1009,"Did not return a result"),
		SIGN_FAIL(1010,"Sign fail"),         //参数校验失败
		SIGN_TIME_OUT(1011, "Sign time out"),   //检验时间错误
		NOT_EXITS_PLATFORM(1012,"Not exits platform"),//不存在的平台类型
		
		
	    SERVICE_SUSPEND(2000,"Server Busy,Try Later!"),
		
//		GLB_API_CLOSED(2000,"API closed forever"),
//		GLB_API_TEMP_CLOSED(2001,"API closed temp"),	
		
		NOT_ENOUGH_YUE(3001,"Not enough yue"),
		SELL_OUT(3002,"Item is sell out"),
		
		HAS_SIGNIN(3003,"Has signin"),
		JFB_YUE_NOT_ENOUGH(3004,"Jfb yue not enough"),
		BRING_JFB_FAIL(3005,"Bring jfb fail"),
		USER_NOT_LOGIN(3006,"User not login"),
		
		PHONENUM_EXIST(3007, "PhoneNum is exist", "介个手机号码已经绑定了哦"),
		PHONENUM_NOT_EXIST(3008, "PhoneNum is not exist", "介个手机号码还没绑定过哦"),
		AUTH_CODE_ERROR(3009, "Auth code is error", "验证码不对哦"),
		REGISTER_FAIL(3010, "Register fail" ,"注册失败"),               //注册或绑定失败
		LOGIN_FAIL(3011, "Login fail", "登陆失败"),
		PASSWORD_ERROR(3012, "Password error", "密码输错拉"),
		PASSWORD_FORMAT_ERROR(3013, "Password format error", "密码格式好像不符合规范哦"),
		
		TAOBAONICK_PHONENUM_BINDING(3014, "TaobaoNick_PhoneNum is binding", "该淘宝帐号已经绑定手机咯"),     //淘宝nick 已经绑定手机号
		
		ACTIVITY_NOT_START(4001,"Activity not start", "参与成功"),
		ACTIVITY_HAS_END(4002,"Activity has end", "参与成功"),
		ACTIVITY_HAS_JOIN(4003,"Activity has join", "参与成功"),
		ACTIVITY_NOT_ALLOW(4004,"Activity not allow", "参与成功"),
		
		ACTIVITY_UNIQUEID_EXSIT(4007,"Activity uniqueid is exsit", "参与成功"),
		
		SHARE_MCD_SUCCESS(5001, "Share mcd success", "恭喜你获得一次抽奖机会"),  //获得一次麦当劳抽奖机会
		
		NOT_ON_TIME(6000, "can not view tomorrow");   //还没到5点 ，不能查看明日预告
		
		

		int s = -1;
		String desc = null;
		String showDesc = "";

		public int value() {
			return this.s;
		}

		public int inValue() {
			return this.s;
		}

		public String toString() {
			return this.desc;
		}

		public String getDescription() {
			return this.desc;
		}

		public String getShowDesc() {
			return showDesc;
		}

		public void setShowDesc(String showDesc) {
			this.showDesc = showDesc;
		}

		ActionStatus(int status, String description) {
			this.s = status;
			this.desc = description;
		}
		
		ActionStatus(int status, String description, String showDesc) {
			this.s = status;
			this.desc = description;
			this.showDesc = showDesc;
		}
	}

}
