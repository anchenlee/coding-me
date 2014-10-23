package cn.youhui.acapi.newuser;

/**
 * 抽奖结果
 * @author lijun
 * @since 2014-7-6
 */
public class TurnplateResult {
	
	private int status = 0;

	private int resultCode = 0;
	
	private String resultStr = "";

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String toJson(){
		StringBuffer sb =  new StringBuffer().append("{\"turnplate_result\":")
				.append("{\"status\":\"").append(status).append("\",")
				.append("\"result_code\":\"").append(resultCode).append("\",")
				.append("\"result_str\":\"").append(resultStr).append("\"")
				.append("}}");
				return sb.toString();
	}
}
