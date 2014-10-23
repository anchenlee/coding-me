package cn.youhui.bean;

public class Fenhong {
	
	private String uid;
	
	private String year;
	
	private String month;
	
	private int fhJfbNum = 0;
	
	private int jfbNum = 0;
	
	private double fhRate;
	
	private double levelRate;
	
	private String icon;
	
	private int status;
	
	private String outerAcId;
	
	private long createTime;
	
	private long updateTime;
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getFhJfbNum() {
		return fhJfbNum;
	}

	public void setFhJfbNum(int fhJfbNum) {
		this.fhJfbNum = fhJfbNum;
	}

	public int getJfbNum() {
		return jfbNum;
	}

	public void setJfbNum(int jfbNum) {
		this.jfbNum = jfbNum;
	}

	public double getFhRate() {
		return fhRate;
	}

	public void setFhRate(double fhRate) {
		this.fhRate = fhRate;
	}

	public double getLevelRate() {
		return levelRate;
	}

	public void setLevelRate(double levelRate) {
		this.levelRate = levelRate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOuterAcId() {
		return outerAcId;
	}

	public void setOuterAcId(String outerAcId) {
		this.outerAcId = outerAcId;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		String statusStr = "审核中";
		if(status == 2){
			statusStr = "成功";
		}
		sb.append("<fh>")
		.append("<time><![CDATA[").append(year + "年" + month + "月").append("]]></time>")
		.append("<jfb_num>").append(jfbNum).append("</jfb_num>")
		.append("<fh_rate>").append(fhRate).append("</fh_rate>")
		.append("<level_rate>").append(levelRate).append("</level_rate>")
		.append("<fh_jfb_num>").append(fhJfbNum).append("</fh_jfb_num>")
		.append("<status><![CDATA[").append(statusStr).append("]]></status>")
		.append("</fh>").toString();
		return sb.toString();
	}
}
