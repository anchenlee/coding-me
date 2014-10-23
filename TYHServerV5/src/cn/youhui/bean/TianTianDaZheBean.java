package cn.youhui.bean;

public class TianTianDaZheBean {
	public TianTianDaZheBean() {

	}

	String title = "";
	String startime = "";
	String content = "";
	private String productname = "";
	private String newprice = "0";
	private String originalPrice;
	private String discount;
	private String startdate;
	private String enddate;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartime() {
		return startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	public String getContent() {
		StringBuffer sb = new StringBuffer();
		sb.append("<item>");
		sb.append("<title><![CDATA[" + title + "]]></title>");
		sb.append("<productname><![CDATA[" + productname + "]]></productname>");
//		if(picurl!=null){
//		if(picurl.indexOf("_m.jpg")==-1){
////			picurl = picurl.replace("_m.jpg", "_b.jpg");
////			picurl +="_m.jpg";
//		}
//		}else {
////			picurl = "";
//		}
		
		sb.append("<picurl>" + picurl + "</picurl>");


		sb.append("<clickurl><![CDATA[" + clickUrl + "]]></clickurl>");
		sb.append("<itemid >" + itemid + "</itemid >");
		sb.append("<newprice>" + newprice + "</newprice>");
		sb.append("<originalPrice>" + originalPrice + "</originalPrice>");

		sb.append("<discount>" + discount + "</discount>");
		sb.append("<startdate>" + startdate + "</startdate>");
		sb.append("<enddate>" + enddate + "</enddate>");
		sb.append("</item>");
		return sb.toString();
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getNewprice() {
		return newprice;
	}

	public void setNewprice(String newprice) {
		this.newprice = newprice;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	String picurl;

	public void setContent(String content) {
		this.content = content;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	String clickUrl = "";

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	private String itemid = "";
}