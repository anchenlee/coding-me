package cn.youhui.bean;

public class JFBRankBean {

	private String uid;
	
	private String taobaoNick;
	
	private double jfbNum;
	
	private int rank;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTaobaoNick() {
		return taobaoNick;
	}

	public void setTaobaoNick(String taobaoNick) {
		this.taobaoNick = taobaoNick;
	}

	public double getJfbNum() {
		return jfbNum;
	}

	public void setJfbNum(double jfbNum) {
		this.jfbNum = jfbNum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<user>");
		sb.append("<taobao_nick><![CDATA[").append(taobaoNick).append("]]></taobao_nick>");
		sb.append("<uid>").append(uid).append("</uid>");
		sb.append("<rank>").append(rank).append("</rank>");
		sb.append("<amount>").append(jfbNum).append("</amount>");
		sb.append("</user>");
		return sb.toString();
	}
}
