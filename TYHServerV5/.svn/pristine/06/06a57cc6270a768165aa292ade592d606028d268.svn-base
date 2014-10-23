package cn.youhui.jfbad;

public class JFbAdCallBackResponse {
	
	boolean isSignAd = false;

	boolean success = false;
	
	int jfbNum = 0;
	
	public boolean isSignAd() {
		return isSignAd;
	}

	public void setSignAd(boolean isSignAd) {
		this.isSignAd = isSignAd;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getJfbNum() {
		return jfbNum;
	}

	public void setJfbNum(int jfbNum) {
		this.jfbNum = jfbNum;
	}
	
	public String toXml(){
		StringBuffer sb = new StringBuffer();
		sb.append("<ja_cb_rsp>");
		if(jfbNum > 0){
			sb.append("<desc><![CDATA[成功领取" + jfbNum + "个集分宝]]></desc>");
		}else{
			sb.append("<desc><![CDATA[你来的太迟拉，集分宝已经被领光咯]]></desc>");
		}
		sb.append("</ja_cb_rsp>");
		return sb.toString();
	}
	
}
