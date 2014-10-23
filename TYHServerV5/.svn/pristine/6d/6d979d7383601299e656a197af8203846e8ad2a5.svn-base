package cn.youhui.bean;

public class IpadFeedbackBean {

	private String id;
	
	private String uid;
	
	private String msg;
	
	private int type;
	
	private String platform;
	
	private String version;
	
	private long createTime;
	
	private long updateTime;
	
	private String createDate;
	
	private String updateDate;
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
	public String toXML(){
		StringBuffer xml = new StringBuffer();
		xml.append("<feedback><id>").append(id).append("</id>")
			 .append("<uid>").append(uid).append("</uid>")
			 .append("<msg><![CDATA[").append(msg).append("]]></msg>")
			 .append("<type>").append(type).append("</type>")
			 .append("<platform>").append(platform).append("</platform>")
			 .append("<version>").append(version).append("</version>")
			 .append("<create_date>").append(createDate).append("</create_date>")
			 .append("</feedback>");
		return xml.toString();
	}
	
}
