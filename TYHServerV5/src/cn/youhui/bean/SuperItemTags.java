package cn.youhui.bean;


public class SuperItemTags {

	public int id;
	public String name,img;
	public String desc;
	public long timestamp;
	
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String toJson(){
		
		
		StringBuffer sb=new StringBuffer();
		sb.append("{\"id\":\""+id+"\",");
		sb.append("\"name\":\""+name+"\",");
		sb.append("\"color_img\":\""+img+"\",");
		sb.append("\"desc\":\""+desc+"\"}");
		return sb.toString();
	}
	
	public String toXML(){
		
		StringBuffer sb=new StringBuffer();
		sb.append("<id>"+id+"</id>");
		sb.append("<name>"+name+"</name>");
		sb.append("<color_img>"+img+"</color_img>");
		sb.append("<desc>"+desc+"</desc>");
		return sb.toString();
	}
	
	
}
