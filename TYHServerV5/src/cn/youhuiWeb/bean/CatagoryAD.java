package cn.youhuiWeb.bean;

public class CatagoryAD 
{
	private String id;
	
	private String title;
	
	private String description;
	
	private String updatetime;
	
	private String img;
	
	private String actionType;
	
	private String actionValue;

	
	/**
	 * 类型：tag/url
	 */
	private String type;

	private int rank;

	private String start_time;

	private String end_time;

	private int status;
	
	private String catId;
	
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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


	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String toXML(){
		return new StringBuffer().append("<ad>")
				.append("<id>").append(id).append("</id>")
				.append("<title><![CDATA[").append(title).append("]]></title>")				
				.append("<description><![CDATA[").append(description).append("]]></description>")
				.append("<updatetime><![CDATA[").append(updatetime).append("]]></updatetime>")
				.append("<img><![CDATA[").append(img).append("]]></img>")
				.append("<actionType><![CDATA[").append(actionType).append("]]></actionType>")
				.append("<actionValue><![CDATA[").append(actionValue).append("]]></actionValue>")
				.append("<type><![CDATA[").append(type).append("]]></type>")
				.append("<rank><![CDATA[").append(rank).append("]]></rank>")
				.append("<start_time><![CDATA[").append(start_time).append("]]></start_time>")
				.append("<end_time><![CDATA[").append(end_time).append("]]></end_time>")
				.append("<cat_id><![CDATA[").append(catId).append("]]></cat_id>")
				.append("</ad>").toString();
	}
}
