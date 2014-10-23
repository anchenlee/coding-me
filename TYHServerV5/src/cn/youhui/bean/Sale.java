package cn.youhui.bean;

public class Sale {
	
	private String id;
	private String parentId = "0";
	private String date = "";
	private String title = "";
	private String description = "";
	private String memo = "";
	private String icon = "";
	private Action action;
	private long startTime = 0;
	private long endTime = 0;
	private long rank = 0;
	private String show;
	
	public Sale(){}


	
	
	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
		this.rank = rank;
	}
	
	public String toXML(){
		if(this.getShow() == null || "".equals(this.getShow()))
		{
			this.setShow("fl");
		}
			
		return new StringBuffer().append("<sale>")
				.append("<id>").append(id).append("</id>")
				.append("<date><![CDATA[").append(date).append("]]></date>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<description><![CDATA[").append(description).append("]]></description>")
				.append("<memo><![CDATA[").append(memo).append("]]></memo>")
				.append("<show><![CDATA[").append(this.getShow()).append("]]></show>")
				.append("<icon><![CDATA[").append(icon).append("]]></icon>")
				.append("<start_time>").append(startTime).append("</start_time>")
				.append("<end_time>").append(endTime).append("</end_time>")
				.append(action.toXML())
				.append("</sale>").toString();
	}
	
}
