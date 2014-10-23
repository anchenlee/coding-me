package cn.youhui.bean;

public class SearchItem {

	private String itemUrl;
	
	private String nick;
	
	private String numiid;
	
	private String picUrl;
	
	private String price;
	
	private String sellerId;
	
	private String title;
	
	private String volume;

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNumiid() {
		return numiid;
	}

	public void setNumiid(String numiid) {
		this.numiid = numiid;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	public String toJson(){
		StringBuffer sb = new StringBuffer();
		sb.append("{")
		.append("\"item_url\":\"").append(itemUrl).append("\",")
		.append("\"nick\":\"").append(nick).append("\",")
		.append("\"num_iid\":\"").append(numiid).append("\",")
		.append("\"pic_url\":\"").append(picUrl).append("\",")
		.append("\"price\":\"").append(price).append("\",")
		.append("\"seller_id\":\"").append(sellerId).append("\",")
		.append("\"title\":\"").append(title).append("\",")
		.append("\"volume\":\"").append(volume).append("\"")
		.append("}");
		return sb.toString();
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<tbk_item>")
				.append("<item_url><![CDATA[").append(itemUrl).append("]]></item_url>")
				.append("<nick><![CDATA[").append(nick).append("]]></nick>")
				.append("<num_iid>").append(numiid).append("</num_iid>")
				.append("<pic_url><![CDATA[").append(picUrl).append("]]></pic_url>")
				.append("<price>").append(price).append("</price>")
				.append("<seller_id>").append(sellerId).append("</seller_id>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<volume>").append(volume).append("</volume>")				
				.append("</tbk_item>");
		return sb.toString();
	}
}
