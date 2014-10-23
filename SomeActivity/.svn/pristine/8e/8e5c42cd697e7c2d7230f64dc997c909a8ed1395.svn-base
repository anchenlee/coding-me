package cn.suishou.some.response;

public class Enums {

	public enum ActionStatus {

		NORMAL_RETURNED(100, "OK"), 
		PARAMAS_ERROR(101, "Parameters Error"), 
		FORBIDDEN(102, "Access Forbidden"), 
		SERVER_ERROR(103, "Server Error"), 
		NO_RESULT(104,"No result"),
		SIGN_FAIL(105,"Sign fail"),
		SIGN_TIME_OUT(106, "Sign time out"),
		UNKNOWN(107, "Unknow Error"); 
		
		
		int code = -1;
		String desc = "";
		String showDesc = "";

		public int getCode() {
			return this.code;
		}

		public String getDesc() {
			return this.desc;
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

		ActionStatus(int status, String description, String showDesc) {
			this.code = status;
			this.desc = description;
			this.showDesc = showDesc;
		}
		
		ActionStatus(int status, String description) {
			this.code = status;
			this.desc = description;
		}
	}

}
