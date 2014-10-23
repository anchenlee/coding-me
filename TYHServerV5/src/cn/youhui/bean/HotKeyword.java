package cn.youhui.bean;

public class HotKeyword {

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String toXML(){
		StringBuffer sb =new StringBuffer();
		
		sb.append("<hot_keyword>");
		sb.append("<keyword><![CDATA[").append(keyword).append("]]></keyword>");
		sb.append("</hot_keyword>");
		return sb.toString();
	}
}
