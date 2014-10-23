package cn.youhui.bean;

public class AdDown {

	private String id = "";
	
	private String img;
	
	private String title;
	
	private String actionType;
	
	private String actionValue;
	
	private int rank;
	
	private long createtime;
	
	private long updateTime;
	
	private int status;
	
	private SuiShouAction action;

	public AdDown(){
	}
	
	public AdDown(String img,String title,String actionType,String actionValue){
		this.img = img;
		this.title = title;
		this.actionType = actionType;
		this.actionValue = actionValue;
	}
	
	public SuiShouAction getAction() {
		return action;
	}

	public void setAction(SuiShouAction action) {
		this.action = action;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String toXML(){
		return new StringBuffer().append("<ad_down>")
				.append("<id>").append(id).append("</id>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<img><![CDATA[").append(img).append("]]></img>")
				.append(action.toXML())
				.append("</ad_down>").toString();
	}
	
	
}
