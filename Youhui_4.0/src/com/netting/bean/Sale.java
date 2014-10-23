package com.netting.bean;

/**
 * 推荐
 * @author YAOJIANBO
 *
 */
public class Sale 
{
	private String id;
	private String parentId = "0";
	private String date = "";
	private String title = "";
	private String description = "";
	private String show;
	private String memo = "";
	private String icon = "";
	private Action action;
	private String startTime = "0";
	private String endTime = "0";
	private long rank = 0;
	private String urlKey = "";
	
	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	public Sale(){}
	
	public Sale(String id, String parentId, String date, String title,
			String description, String memo, String icon, Action action, String startTime, String endtime,String show) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.date = date;
		this.title = title;
		this.description = description;
		this.memo = memo;
		this.icon = icon;
		this.action = action;
		this.startTime = startTime;
		this.endTime = endtime;
		this.show = show;
	}
	
	public Sale( String parentId, String date, String title,
			String description, String memo, String icon, Action action, String startTime,String endtime,String show) {
		super();
		this.parentId = parentId;
		this.date = date;
		this.title = title;
		this.description = description;
		this.memo = memo;
		this.icon = icon;
		this.action = action;
		this.startTime = startTime;
		this.endTime = endtime;
		this.show = show;
	}

	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
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
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
		this.rank = rank;
	}
	
}
