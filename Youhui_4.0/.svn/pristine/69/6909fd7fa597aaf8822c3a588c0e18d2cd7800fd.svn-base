package com.netting.bean;

public class TongjiBean {

	private String title;
	private String value;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String formatToJson(){
		StringBuffer sb  = new StringBuffer();
		sb.append("[\"");
		sb.append(title);
		sb.append("\",");
		sb.append(value);
		sb.append("]");
		return sb.toString();
	}
}
