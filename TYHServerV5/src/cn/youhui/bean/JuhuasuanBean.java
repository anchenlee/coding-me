package cn.youhui.bean;

public class JuhuasuanBean {
	public JuhuasuanBean() {

	}

	Long parentCat=0L;
	


	public Long getParentCat() {
		return parentCat;
	}

	public void setParentCat(Long parentCat) {
		this.parentCat = parentCat;
	}

	



	String startime = "";
	String cantuanrenshu = "";
	String content = "";
	String title = "";
	String itemid = "";
	String clickurl = "";
	String enddate = "";
	String newprice = "";
	String originalPrice = "";
	String discount = "";
	String productname = "";

	// //////////////////
	public String getContent() {
		StringBuffer sb = new StringBuffer();
		sb.append("<item>");
		sb.append("<title><![CDATA[" + title + "]]></title>");
		sb.append("<productname><![CDATA[" + productname + "]]></productname>");
		sb.append("<picurl><![CDATA[" + picurl + "_b.jpg" + "]]></picurl>");
		sb.append("<clickurl><![CDATA[" + clickurl + "]]></clickurl>");
		sb.append("<itemid >" + itemid + "</itemid >");
		sb.append("<newprice>" + newprice + "</newprice>");
		sb.append("<originalPrice>" + originalPrice + "</originalPrice>");
		sb.append("<discount>" + discount + "</discount>");
		sb.append("<startdate>" + startime + "</startdate>");
		sb.append("<cantuanrenshu>" + cantuanrenshu + "</cantuanrenshu>");
		sb.append("<enddate>" + enddate + "</enddate>");
		sb.append("</item>");
		return sb.toString();
	}

	// ////////////////
	public String getClickurl() {
		return clickurl;
	}

	public void setClickurl(String clickurl) {
		this.clickurl = clickurl;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
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

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	String picurl = "";

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

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getCantuanrenshu() {
		return cantuanrenshu;
	}

	public void setCantuanrenshu(String cantuanrenshu) {
		this.cantuanrenshu = cantuanrenshu;
	}
}