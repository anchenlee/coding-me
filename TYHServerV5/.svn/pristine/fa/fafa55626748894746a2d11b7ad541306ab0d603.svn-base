package cn.youhui.bean;

public class Activity {
	
	private String id ;
	private String name = "";
	private String description = "";
	private int frequency;
	private int jfbNum;
	private String rule;
	private String key;
	private long startTime;
	private long endTime;
	private long timestamp;
	private String img = "";
	
	public Activity(){}
	
	public Activity(String id, String name,  int jfbNum) {
		super();
		this.setId(id);
		this.name = name;
		this.jfbNum = jfbNum;
	}
	
	public Activity(String id, String name, String description, int frequency, int jfbNum, String rule, long startTime, long endTime) {
		super();
		this.setId(id);
		this.name = name;
		this.description = description;
		this.frequency = frequency;
		this.jfbNum = jfbNum;
		this.rule = rule;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public int getJfbNum() {
		return jfbNum;
	}
	public void setJfbNum(int jfbNum) {
		this.jfbNum = jfbNum;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
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

	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<activity>")
		.append("<id>").append(id).append("</id>")
		.append("<name><![CDATA[").append(name).append("]]></name>")
		.append("<frequency>").append(frequency).append("</frequency>")
		.append("<jfbNum>").append(jfbNum).append("</jfbNum>")
		.append("<img><![CDATA[").append(img).append("]]></img>")
		.append("<desc><![CDATA[").append(description).append("]]></desc>");
		sb.append("</activity>");
		return sb.toString();
	}
	
}
