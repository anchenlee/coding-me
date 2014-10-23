package cn.suishou.some.talk.bean;

import java.io.Serializable;

/**
 * 活动实体类
 * @author weifeng
 *
 */
public class Activity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3089230422865056080L;
	private Integer id;
	private String actName;
	private String actTopic;
	private String startTime;
	private String endTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActTopic() {
		return actTopic;
	}
	public void setActTopic(String actTopic) {
		this.actTopic = actTopic;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", actName=" + actName + ", actTopic="
				+ actTopic + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
	
}
