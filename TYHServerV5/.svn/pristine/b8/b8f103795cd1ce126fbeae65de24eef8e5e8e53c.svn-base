package cn.youhui.bean;

public class CouponsShop {

	private String id;
	
	private String shopId;
	
	private String shopName;
	
	private String shopUrl;
	
	private String lat;
	
	private String lon;
	
	private String mobile;
	
	private String address;
	
	private String shopPic;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShopPic() {
		return shopPic;
	}

	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<coupons_shop>");
		sb.append("<shop_id>").append(shopId).append("</shop_id>");
		sb.append("<shop_name>").append(shopName).append("</shop_name>");
		sb.append("<shop_url>").append(shopUrl).append("</shop_url>");
		sb.append("<lat>").append(lat).append("</lat>");
		sb.append("<lon>").append(lon).append("</lon>");
		sb.append("<tel>").append(mobile).append("</tel>");
		sb.append("<address>").append(address).append("</address>");
		sb.append("<shop_pic>").append(shopPic).append("</shop_pic>");
		sb.append("</coupons_shop>");
		return sb.toString();
	}
}
