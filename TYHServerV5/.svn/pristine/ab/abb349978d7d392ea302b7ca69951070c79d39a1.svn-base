package cn.youhui.bean;

public class Ad {

	private String id;
	private String description;
	private String title;
	private String img;
	private SuiShouAction action;		
	
	private String platform;
	
	private String content;
	
	private String updatetime;
	
	
	private String img1;
	
	private String img2;
	
	private String actionType;
	
	private String actionValue;
	
	private String urlkey;
	
	/**
	 * 类型：tag/url
	 */
	private String type;
	
	/**
	 * 排序
	 */
	private int rank;
	
	/**
	 * 礼物有效开始时间
	 */
	private String start_time;
	
	/**
	 * 礼物失效时间
	 */
	private String end_time;
	
	/**
	 * 是否有效 0:无效;1:有效;
	 */
	private int status;

	
	
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
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

	public String getUrlkey() {
		return urlkey;
	}

	public void setUrlkey(String urlkey) {
		this.urlkey = urlkey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public SuiShouAction getAction() {
		return action;
	}

	public void setAction(SuiShouAction action) {
		this.action = action;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String toXML(){
		return new StringBuffer().append("<ad>")
				.append("<id>").append(id).append("</id>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<img><![CDATA[").append(img).append("]]></img>")
				.append(action.toXML())
				.append("</ad>").toString();
	}
}
