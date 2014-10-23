package cn.youhui.bean;

/**
 * 公告
 * @author 
 *
 */
public class Announcement {
	
	private int id;
	
	private String title;
	
	private String contentUrl;
	
	private long createTime;
	
	private long updateTime;
	
	private String startTime;
	
	private String endTime;
	
	private int status;
	
	private long delayTime;
	
	private int leftDismissType;
	
	private int rightDismissType;
	
	private int isCanDisMiss;
	
	private String leftButtonName;
	
	private String rightButtonName;
	
	private String leftButtonAction;
	
	private String rightButtonAction;
	
	private String leftButtonType;
	
	private String rightButtonType;
	
	private String buttonControl;
	
	public Announcement()
	{
		
	}
	
	
	public Announcement(String title,String contentUrl,long createTime,long updateTime,String startTime,String endTime,int status,long delayTime,int leftDismissType,
			int rightDismissType,int isCanDisMiss,String leftButtonName,String rightButtonName,String leftButtonAction,String rightButtonAction,
			String leftButtonType,String rightButtonType,String buttonControl)
	{
		this.title = title;
		this.contentUrl = contentUrl;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.delayTime = delayTime;
		this.leftDismissType = leftDismissType;
		this.rightDismissType = rightDismissType;
		this.isCanDisMiss = isCanDisMiss;
		this.leftButtonName = leftButtonName;
		this.rightButtonName = rightButtonName;
		this.leftButtonAction = leftButtonAction;
		this.rightButtonAction = rightButtonAction;
		this.leftButtonType = leftButtonType;
		this.rightButtonType = rightButtonType;
		this.buttonControl = buttonControl;
	}
	
	public String getButtonControl() {
		return buttonControl;
	}


	public void setButtonControl(String buttonControl) {
		this.buttonControl = buttonControl;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
	}

	public int getLeftDismissType() {
		return leftDismissType;
	}

	public void setLeftDismissType(int leftDismissType) {
		this.leftDismissType = leftDismissType;
	}

	public int getRightDismissType() {
		return rightDismissType;
	}

	public void setRightDismissType(int rightDismissType) {
		this.rightDismissType = rightDismissType;
	}

	public int getIsCanDisMiss() {
		return isCanDisMiss;
	}

	public void setIsCanDisMiss(int isCanDisMiss) {
		this.isCanDisMiss = isCanDisMiss;
	}

	public String getLeftButtonName() {
		return leftButtonName;
	}

	public void setLeftButtonName(String leftButtonName) {
		this.leftButtonName = leftButtonName;
	}

	public String getRightButtonName() {
		return rightButtonName;
	}

	public void setRightButtonName(String rightButtonName) {
		this.rightButtonName = rightButtonName;
	}

	public String getLeftButtonAction() {
		return leftButtonAction;
	}

	public void setLeftButtonAction(String leftButtonAction) {
		this.leftButtonAction = leftButtonAction;
	}

	public String getRightButtonAction() {
		return rightButtonAction;
	}

	public void setRightButtonAction(String rightButtonAction) {
		this.rightButtonAction = rightButtonAction;
	}

	public String getLeftButtonType() {
		return leftButtonType;
	}

	public void setLeftButtonType(String leftButtonType) {
		this.leftButtonType = leftButtonType;
	}

	public String getRightButtonType() {
		return rightButtonType;
	}

	public void setRightButtonType(String rightButtonType) {
		this.rightButtonType = rightButtonType;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<announcement>");
		sb.append("<title><![CDATA[").append(title).append("]]></title>")
		.append("<id>").append(id).append("</id>")
		.append("<content_url><![CDATA[").append(contentUrl).append("]]></content_url>")
		.append("<start_time>").append(startTime).append("</start_time>")
		.append("<end_time>").append(endTime).append("</end_time>")
		.append("<delay_time>").append(delayTime).append("</delay_time>")
		.append("<button_control>").append(buttonControl).append("</button_control>")
		.append("<left_button><button><button_name>").append(leftButtonName).append("</button_name>")
		.append("<ss_action><url><![CDATA[").append(leftButtonAction).append("]]></url></ss_action>")
		.append("</button></left_button>")
		.append("<right_button><button><button_name>").append(rightButtonName).append("</button_name>")
		.append("<ss_action><url><![CDATA[").append(rightButtonAction).append("]]></url></ss_action>")
		.append("</button></right_button>");
		sb.append("</announcement>");
		return sb.toString();
	}
	
}
