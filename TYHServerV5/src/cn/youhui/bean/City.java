package cn.youhui.bean;

public class City {

	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toXML(){
		return new StringBuffer().append("<city>")
				.append("<id>").append(id).append("</id>")
				.append("<name><![CDATA[").append(name).append("]]></name>")
				.append("</city>").toString();
	}
}
