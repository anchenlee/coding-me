package cn.youhui.bean;

public class SearchKeywords {

	private String id = "";
	
	private String name = "";
	
	private String icon = "";
	
	private Action action;
	

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<cat>")
		.append("<id>"+id+"</id>")
		.append("<name><![CDATA["+name+"]]></name>")
		.append("<icon><![CDATA["+icon+"]]></icon>")
		.append("</cat>");
		return sb.toString();
	}
}
