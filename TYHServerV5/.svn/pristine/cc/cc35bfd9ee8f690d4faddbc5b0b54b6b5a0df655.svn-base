package cn.youhui.bean;

import cn.youhui.bean.KeyCategoryBean.KeywordBean;

public class IpadTagBean {
	
	private KeywordBean tag;
	
	private String x;
	
	private String y;
	
	public KeywordBean getTag() {
		return tag;
	}

	public void setTag(KeywordBean tag) {
		this.tag = tag;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String toXML(String platform){
		String str = "<p_tag>";
		str += tag.toXMLNew(platform);
		str += "<position><x>" + x + "</x><y>" + y + "</y></position>";
		str += "</p_tag>";
		return str;
	}
}
