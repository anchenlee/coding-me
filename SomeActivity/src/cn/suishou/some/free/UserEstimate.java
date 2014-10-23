package cn.suishou.some.free;


/**
 *用户评价类
 * @author hufan
 * @since 2014-8-14
 */
public class UserEstimate {

	private String uid;
	private String taobaoNick;
	private String activityId;
	private String estimate;//用户评价内容
	private int starNum;//用户态度：1 代表用户喜欢；  0 代表用户不喜欢
	private long time;
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


	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getEstimate() {
		return estimate;
	}
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	public int getStarNum() {
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
