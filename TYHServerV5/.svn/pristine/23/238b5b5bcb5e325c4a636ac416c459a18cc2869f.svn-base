package cn.youhui.bean;

/**
 * 商铺信息
 * @author YAOJIANBO
 * @since 2013-06-07
 */
public class ShopInfo 
{
	/**
	 * 店铺ID
	 */
	private String shopID;
	
	/**
	 * 描述相符
	 */
	private String msxf = "";
	
	/**
	 * 描述相符 与同行比较
	 */
	private String msxf_bj = "";
	
	/**
	 * 描述相符 高低中
	 */
	private String msxf_status = "";
	
	/**
	 * 服务态度
	 */
	private String fwtd = "";
	
	/**
	 * 服务态度 与同行比较
	 */
	private String fwtd_bj ="";
	
	/**
	 * 服务态度 高低中
	 */
	private String fwtd_status = "";
	
	/**
	 * 发货速度
	 */
	private String fhsd ="";
	
	/**
	 * 发货速度 与同行比较
	 */
	private String fhsd_bj ="";
	
	public String getMsxf_status() {
		return msxf_status;
	}

	public void setMsxf_status(String msxf_status) {
		this.msxf_status = msxf_status;
	}

	public String getFwtd_status() {
		return fwtd_status;
	}

	public void setFwtd_status(String fwtd_status) {
		this.fwtd_status = fwtd_status;
	}

	public String getFhsd_status() {
		return fhsd_status;
	}

	public void setFhsd_status(String fhsd_status) {
		this.fhsd_status = fhsd_status;
	}

	/**
	 * 发货速度 高低中
	 */
	private String fhsd_status = "";
	
	/**
	 * 卖家信用
	 */
	private String mjxy ="";
	
	/**
	 * 卖家好评率
	 */
	private String mjhpl ="";
	
	/**
	 * 掌柜昵称
	 */
	private String sellerNick="";
	
	/**
	 * 掌柜的ID
	 */
	private String sellerID="";
	
	/**
	 * 收藏人气
	 */
	private String scrq="";
	
	/**
	 * 主营
	 */
	private String zy ="";
	
	/**
	 * 地区
	 */
	private String area="";
	
	/**
	 * 电话
	 */
	private String tel="";
	
	public String getMjhpl() {
		return mjhpl;
	}

	public void setMjhpl(String mjhpl) {
		this.mjhpl = mjhpl;
	}

	public String getMsxf_bj() {
		return msxf_bj;
	}

	public void setMsxf_bj(String msxf_bj) {
		this.msxf_bj = msxf_bj;
	}

	public String getFhsd_bj() {
		return fhsd_bj;
	}

	public void setFhsd_bj(String fhsd_bj) {
		this.fhsd_bj = fhsd_bj;
	}

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public String getMsxf() {
		return msxf;
	}

	public void setMsxf(String msxf) {
		this.msxf = msxf;
	}

	public String getFhsd() {
		return fhsd;
	}

	public void setFhsd(String fhsd) {
		this.fhsd = fhsd;
	}

	public String getMjxy() {
		return mjxy;
	}

	public void setMjxy(String mjxy) {
		this.mjxy = mjxy;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getSellerID() {
		return sellerID;
	}

	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}

	public String getScrq() {
		return scrq;
	}

	public void setScrq(String scrq) {
		this.scrq = scrq;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFwtd() {
		return fwtd;
	}

	public void setFwtd(String fwtd) {
		this.fwtd = fwtd;
	}

	public String getFwtd_bj() {
		return fwtd_bj;
	}

	public void setFwtd_bj(String fwtd_bj) {
		this.fwtd_bj = fwtd_bj;
	}
	
	private String shop_title ="";
	private String pic_url = "";
	private String shop_url = "";

	public String getShop_title() {
		return shop_title;
	}

	public void setShop_title(String shop_title) {
		this.shop_title = shop_title;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getShop_url() {
		return shop_url;
	}

	public void setShop_url(String shop_url) {
		this.shop_url = shop_url;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<shop>")
        .append("<shopID>").append(shopID).append("</shopID>")
        .append("<msxf>").append(msxf).append("</msxf>")
		.append("<msxf_bj status=\"").append(msxf_status).append("\">").append("<![CDATA[").append(msxf_bj).append("]]></msxf_bj>")
		 .append("<fwtd>").append(fwtd).append("</fwtd>")
		 .append("<fwtd_bj  status=\"").append(fwtd_status).append("\">").append("<![CDATA[").append(fwtd_bj).append("]]></fwtd_bj>")
		  .append("<fhsd>").append(fhsd).append("</fhsd>")
		 .append("<fhsd_bj status=\"").append(fhsd_status).append("\">").append("<![CDATA[").append(fhsd_bj).append("]]></fhsd_bj>")
		 .append("<mjxy>").append(mjxy).append("</mjxy>")
		 .append("<mjhpl>").append(mjhpl).append("</mjhpl>")
		 .append("<sellerNick><![CDATA[").append(sellerNick).append("]]></sellerNick>")
		 .append("<sellerID>").append(sellerID).append("</sellerID>")
		  .append("<scrq>").append(scrq).append("</scrq>")
		  .append("<zy><![CDATA[").append(zy).append("]]></zy>")
		  .append("<area><![CDATA[").append(area).append("]]></area>")
		  .append("<tel>").append(tel).append("</tel>")
		  .append("<pic_url><![CDATA[").append(pic_url).append("]]></pic_url>");
		sb.append("</shop>");
		return sb.toString();
	}
	
	public String toJson(){
		StringBuffer sb = new StringBuffer();
		sb.append("\"shop\":");
		sb.append("{");
		sb.append("\"shopID\":").append("\"").append(shopID).append("\",")
		.append("\"msxf\":").append("\"").append(msxf).append("\",")
		.append("\"msxf_bj\":").append("\"").append(msxf_bj).append("\",")
		.append("\"fwtd\":").append("\"").append(fwtd).append("\",")
		.append("\"fwtd_bj\":").append("\"").append(fwtd_bj).append("\",")
		.append("\"fhsd\":").append("\"").append(fhsd).append("\",")
		.append("\"fhsd_bj\":").append("\"").append(fhsd_bj).append("\",")
		.append("\"mjxy\":").append("\"").append(mjxy).append("\",")
		.append("\"mjhpl\":").append("\"").append(mjhpl).append("\",")
		.append("\"sellerNick\":").append("\"").append(sellerNick).append("\",")
		.append("\"sellerID\":").append("\"").append(sellerID).append("\",")
		.append("\"scrq\":").append("\"").append(scrq).append("\",")
		.append("\"zy\":").append("\"").append(zy).append("\",")
		.append("\"area\":").append("\"").append(area).append("\",")
		.append("\"tel\":").append("\"").append(tel).append("\",")
		.append("\"shop_title\":").append("\"").append(shop_title).append("\",")
		.append("\"pic_url\":").append("\"").append(pic_url).append("\",")
		.append("\"shop_url\":").append("\"").append(shop_url).append("\"");
		sb.append("}");
		sb.append("}");
		return sb.toString();	
	}
	
}
