package cn.youhui.bean;

import cn.youhui.utils.DateUtil;

/**
 * 记录活动参加的情况
 * @author leejun
 *
 */
public class ActivityJoin {
	
	private String id;
	private String uid;
	private String date;
	private int jfbNum;
	private String activityId = ""; 
	private String activityName;
	private long timestamp;
	private int status;
	private String zfb;
	
	public String getZfb() {
		return zfb;
	}
	public void setZfb(String zfb) {
		this.zfb = zfb;
	}

	/**
	 * 审核到期时间
	 */
	private long expireTime = 0;    
	
	/**
	 * 图标
	 */
	private String icon;
	
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getJfbNum() {
		return jfbNum;
	}
	public void setJfbNum(int jfbNum) {
		this.jfbNum = jfbNum;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String toXML(){
		String statusStr = "";
		if(expireTime > 0){
			statusStr = "请您稍候" + DateUtil.getRestTimeCH(expireTime);
		}
		if(statusStr == null || "".equals(statusStr)){
			statusStr = "请您稍候";
		}
		if(status == 4){
			statusStr = "失败";
		}else if(status == 2){
			statusStr = "OK";
		}
		
		expireTime = 0;       //不给审核时间
		return new StringBuffer().append("<activity_join>")
				.append("<id>").append(id).append("</id>")
				.append("<activity_id><![CDATA[").append(activityId).append("]]></activity_id>")
				.append("<activity_name><![CDATA[").append(activityName).append("]]></activity_name>")
				.append("<jfbnum>").append(jfbNum).append("</jfbnum>")
				.append("<time>").append(timestamp).append("</time>")
				.append("<status_code>").append(status).append("</status_code>")
				.append("<status><![CDATA[").append(statusStr).append("]]></status>")
				.append("<icon><![CDATA[").append(icon).append("]]></icon>")
				.append("<expire_time>").append(expireTime).append("</expire_time>")
				.append("<tx_zfb><![CDATA[").append(zfb).append("]]></tx_zfb>")
				.append("</activity_join>").toString();
	}
	
}
