package cn.youhui.bean;

/**
 * 特惠活动说明
 * @author lijun
 *
 */
public class SaleExplain {
	
	private String id;
	private int type;
	private String desc;
	private String img;
	private String url;
	private String items;
	private String shops;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getShops() {
		return shops;
	}
	public void setShops(String shops) {
		this.shops = shops;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String toXML(){
		
		return new StringBuffer().append("<explain>")
				.append("<id>").append(id).append("</id>")
				.append("<type>").append(type).append("</type>")
				.append("<desc><![CDATA[").append(desc).append("]]></desc>")
				.append("<img><![CDATA[").append(img).append("]]></img>")
				.append("<url><![CDATA[").append(url).append("]]></url>")
				.append("</explain>").toString();
	}
}
