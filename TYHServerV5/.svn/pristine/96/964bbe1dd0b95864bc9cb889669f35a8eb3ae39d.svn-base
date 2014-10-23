package cn.youhui.bean;

public class CouponsCategory {

	private String id;
	
	private String name;
	
	private String icon;

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
		return new StringBuffer().append("<coupons_category>")
				.append("<id>").append(id).append("</id>")
				.append("<name><![CDATA[").append(name).append("]]></name>")
				//.append("<icon><![CDATA[").append(icon).append("]]></icon>")
				.append("</coupons_category>").toString();
	}
}
