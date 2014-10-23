package cn.youhui.bean;

public class JiFenBaoTrade {
	
	private String tradeId;
	private int jiFenBaoNum = 0;
	private String uid;
	private String zfb = "";
	private String memo = "";
	private String from;
	private String fromId;
	private int status;
	private String fromAlipayId = "";
	private String alipayOrderNo = "";
	private String error = "";
	private long timestamp;
	private int type =0;
	
	
	
	@Override
	public String toString() {
		return "JiFenBaoTrade [tradeId=" + tradeId + ", jiFenBaoNum="
				+ jiFenBaoNum + ", uid=" + uid + ", zfb=" + zfb + ", memo="
				+ memo + ", from=" + from + ", fromId=" + fromId + ", status="
				+ status + ", fromAlipayId=" + fromAlipayId
				+ ", alipayOrderNo=" + alipayOrderNo + ", error=" + error
				+ ", timestamp=" + timestamp + "]";
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getFromAlipayId() {
		return fromAlipayId;
	}
	public void setFromAlipayId(String fromAlipayId) {
		this.fromAlipayId = fromAlipayId;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getZfb() {
		return zfb;
	}
	public void setZfb(String zfb) {
		this.zfb = zfb;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public int getJiFenBaoNum() {
		return jiFenBaoNum;
	}
	public void setJiFenBaoNum(int jiFenBaoNum) {
		this.jiFenBaoNum = jiFenBaoNum;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAlipayOrderNo() {
		return alipayOrderNo;
	}
	public void setAlipayOrderNo(String alipayOrderNo) {
		this.alipayOrderNo = alipayOrderNo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String toXML(){
		String statusStr;
		if(status == 1){
			statusStr = "成功";
		}else if(status == 2){
			statusStr = "失败";
		}else{
			statusStr = "处理中";
//			statusStr = "系统升级待28号处理";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<trade>")
		.append("<type>").append(type).append("</type>")
		.append("<je>").append((double)jiFenBaoNum/100).append("</je>")
		.append("<status><![CDATA[").append(statusStr).append("]]></status>")
		.append("<zfb>").append(zfb).append("</zfb>")
		.append("<time>").append(timestamp).append("</time>");
		if(type == 2){
			if(status == 2){
				String why = error;
				if("USER_NOT_EXIST".equalsIgnoreCase(why)){
					why = "支付宝错误";
				}else if("OUT_BIZ_NO_EXIST".equalsIgnoreCase(why)){
					why = "重复提交";
				}else {
					why = "";
				}
				sb.append("<error><![CDATA[").append(why).append("]]></error>");
			}
		}else{
			if(status == 2){
				sb.append("<error><![CDATA[").append(error).append("]]></error>");
			}
		}
		sb.append("</trade>").toString();
		return sb.toString();
	}
	
	private static boolean isInSpring(){
		long t0128 = 1390881600000l;
		if(System.currentTimeMillis() > t0128){
			return true;
		}
		return false;
	}

}
