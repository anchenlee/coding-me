package com.netting.bean;

/**
 * 礼物类型
 * @author YAOJIANBO
 *
 */
public class Gift_Bean 
{
	private String id;
	
	private String title;
	
	private String description;
	
	private String updatetime;
	
	/**
	 * 按钮名称
	 */
	private String clickname;
	
	private String img;
	
	private String imgIpad;
	
	private String actionType;
	
	private String actionValue;
	
	/**
	 * 类型
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
	
	/**
	 * 点击地址转换之后的key
	 */
	private String urlKey;

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	public Gift_Bean() {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getClickname() {
		return clickname;
	}

	public void setClickname(String clickname) {
		this.clickname = clickname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImgIpad() {
		return imgIpad;
	}

	public void setImgIpad(String imgIpad) {
		this.imgIpad = imgIpad;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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
	
	public String converToRedisJson()
	{
		String json = "{\"id\":\"" + this.getId() + "\"," 
					+ "\"type\":\"" + this.getType() + "\","
					+ "\"title\":\"" + this.getTitle() + "\","
					+ "\"desc\":\"" + this.getDescription() + "\","
					+ "\"img\":\"" + this.getImg() + "\","
					+ "\"img_ipad\":\"" + this.getImgIpad() + "\","
					+ "\"clickname\":\"" + this.getClickname() + "\","
					+ "\"actiontype\":\"" + this.getActionType() + "\","
					+ "\"actionvalue\":\"" + this.getActionValue() + "\""
					+ "}";
		
		return json;
	}
}
