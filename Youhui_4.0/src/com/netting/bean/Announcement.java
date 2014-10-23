package com.netting.bean;


/**
 * 公告
 * @author belonghu
 *
 */
public class Announcement {
	

	private int status;

	private String id;
	
	private String title;
	
	private String contentUrl;
	
	private long startTime;
	
	private String startDate;
	
	private long endTime;
	
	private String endDate;
	
	/**启动后延迟展示时间*/
	private long delayTime;
	
	
	/**隐藏的类型
	 * 左侧按钮点击        右侧按钮点击
	 * 0:不再弹出            点击不再弹出
	 * 1:仍然每次弹出     点击不再弹出
	 * 2:仍然每次弹出     点击每次必谈
	 * 3:不再弹出           点击每次必谈
	 * */
	private int showType;
	
	/**
	 * 是否可以删除（用来如强制升级等等..）
	 */
	private int delType;
	
	private ButtonBean leftButton;
	private ButtonBean rightButton;
	
	
	public Announcement(String title,String contentUrl,int showType ,String leftName,String leftActionType,String leftActionValue,
			String rightName,String rightActionType,String rightActionValue,long startTime,long endTime,long delayTime){
		this.title = title;
		this.contentUrl = contentUrl;
		this.showType = showType;
		this.leftButton = new ButtonBean(leftName, new Action(leftActionType, leftActionValue));
		this.rightButton = new ButtonBean(rightName, new Action(rightActionType, rightActionValue));
		this.startTime = startTime;
		this.endTime = endTime;
		this.delayTime = delayTime;
	}
	
	public Announcement( ){
		
	}
		
	public int getDelType() {
		return delType;
	}

	public void setDelType(int delType) {
		this.delType = delType;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
	}
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public ButtonBean getLeftButton() {
		return leftButton;
	}
	public void setLeftButton(ButtonBean leftButton) {
		this.leftButton = leftButton;
	}
	public ButtonBean getRightButton() {
		return rightButton;
	}
	public void setRightButton(ButtonBean rightButton) {
		this.rightButton = rightButton;
	}	
	
	
	
}
