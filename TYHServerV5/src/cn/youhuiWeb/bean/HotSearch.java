package cn.youhuiWeb.bean;

public class HotSearch {
	private String id;
	private String catId;
	private String keyword;
	private String isHot;
	private int rank;
	private String timestamp;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getIsHot() {
		return isHot;
	}
	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}
	
	public String toXML(){
		return new StringBuffer().append("<hot_search>")
				.append("<id>").append(id).append("</id>")
				.append("<keyword><![CDATA[").append(keyword).append("]]></keyword>")				
				.append("<isHot><![CDATA[").append(isHot).append("]]></isHot>")
				.append("<timestamp><![CDATA[").append(timestamp).append("]]></timestamp>")
				.append("<rank><![CDATA[").append(rank).append("]]></rank>")
				.append("<catId><![CDATA[").append(catId).append("]]></catId>")
				.append("</hot_search>").toString();
	}
	
}
