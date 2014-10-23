package cn.youhui.bean;

public class Professor {
	
	private String id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 头像
	 */
	private String icon;
	
	/**
	 * 介绍
	 */
	private String desc;
	
	/**
	 * 分类
	 */
	private String category;
	
	/**
	 * 是否订阅   1 是；0否
	 */
	private int own = 0;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getOwn() {
		return own;
	}

	public void setOwn(int own) {
		this.own = own;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
	  sb.append("<professor>")
			.append("<id>").append(id).append("</id>")
			.append("<name><![CDATA[").append(name).append("]]></name>")
			.append("<own>").append(own).append("</own>")
			.append("<icon><![CDATA[").append(icon).append("]]></icon>")
			.append("<desc><![CDATA[").append(desc).append("]]></desc>")
			.append("</professor>").toString();
			return sb.toString();
    }
}
