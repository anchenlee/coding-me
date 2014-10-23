package cn.youhui.bean;


public class Coupons {

	private String id;
	
	private String title;
	
	private String picUrl;
	
	private double orginPrice;
	
	private double nowPrice;
	
	private int gouMaiRenShu;
	
	private double lat;	//经度
	
	private double lon;	//纬度
	
	private String clickUrl;
	
	private String shopName;
	
	private SuiShouAction action;
	
	private String categoryId;

	private String isFaved = "0";
	
	private String attention = "";
	
	private String detail_pic;
	
	private String desc;
	
	private String shopID;
	
	private long startTime;
	
	private long endTime;
	
	private String status;
	
	private String isSingle;
	
	
	public String getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public String getDetail_pic() {
		return detail_pic;
	}

	public void setDetail_pic(String detail_pic) {
		this.detail_pic = detail_pic;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getIsFaved() {
		return isFaved;
	}

	public void setIsFaved(String isFaved) {
		this.isFaved = isFaved;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public double getOrginPrice() {
		return orginPrice;
	}

	public void setOrginPrice(double orginPrice) {
		this.orginPrice = orginPrice;
	}

	public double getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public int getGouMaiRenShu() {
		return gouMaiRenShu;
	}

	public void setGouMaiRenShu(int gouMaiRenShu) {
		this.gouMaiRenShu = gouMaiRenShu;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public SuiShouAction getAction() {
		return action;
	}

	public void setAction(SuiShouAction action) {
		this.action = action;
	}

	
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String toXML(){
		return new StringBuffer().append("<coupons>")
				.append("<id>").append(id).append("</id>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<img><![CDATA[").append(picUrl).append("]]></img>")
				.append("<now_price>").append(nowPrice).append("</now_price>")
				.append("<orgin_price>").append(orginPrice).append("</orgin_price>")
				.append("<buy_count>").append(gouMaiRenShu).append("</buy_count>")
				.append("<distance><![CDATA[").append("1KM").append("]]></distance>")
				.append("<is_faved>").append(isFaved).append("</is_faved>")
				.append("<attention><![CDATA[").append(attention).append("]]></attention>")
				.append("<detail_pic><![CDATA[").append(detail_pic).append("]]></detail_pic>")
				.append("<desc><![CDATA[").append(desc).append("]]></desc>")
				.append("<shop_id>").append(shopID).append("</shop_id>")
				.append("<is_signal>").append(isSingle).append("</is_signal>")
				.append(action.toXML())
				.append("</coupons>").toString();
	}
	
}
