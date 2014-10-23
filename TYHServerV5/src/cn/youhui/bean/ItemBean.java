package cn.youhui.bean;

public class ItemBean {
	private String num_iid = "";
	private String title = "";
	private String pic_url="";
	private String price = "";
	private String click_url = "";
	
	private String keyword = "";
	private String price_high = "";
	private String price_low = "";
	private String discount = "";
	
	private String nick = "";

	private String commission="0";
	private String commission_rate="0";
	private String commission_num="0";
	private String commission_volume="0";
	private String shop_click_url="";
	
	public String getNum_iid() {
		return num_iid;
	}
	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getClick_url() {
		return click_url;
	}
	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getPrice_high() {
		return price_high;
	}
	public void setPrice_high(String price_high) {
		this.price_high = price_high;
	}
	public String getPrice_low() {
		return price_low;
	}
	public void setPrice_low(String price_low) {
		this.price_low = price_low;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getCommission_rate() {
		return commission_rate;
	}
	public void setCommission_rate(String commission_rate) {
		this.commission_rate = commission_rate;
	}
	public String getCommission_num() {
		return commission_num;
	}
	public void setCommission_num(String commission_num) {
		this.commission_num = commission_num;
	}
	public String getCommission_volume() {
		return commission_volume;
	}
	public void setCommission_volume(String commission_volume) {
		this.commission_volume = commission_volume;
	}
	public String getShop_click_url() {
		return shop_click_url;
	}
	public void setShop_click_url(String shop_click_url) {
		this.shop_click_url = shop_click_url;
	}

	public String toXML(){
		StringBuffer sb = new StringBuffer();
		click_url = "http://b17.cn/item?itemid=" + num_iid;
		if(pic_url != null){
			pic_url = pic_url.replaceAll(" ", "");
		}
		sb.append("<item>")
		        .append("<itemid>").append(num_iid).append("</itemid>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<price>").append(price_low).append("</price>")
				.append("<price_high>").append(price_high).append("</price_high>")
				.append("<picurl><![CDATA[").append(pic_url).append("]]></picurl>")
				.append("<clickurl><![CDATA[").append(click_url).append("]]></clickurl>");
				sb.append("</item>");
		return sb.toString();
	}
	
	public String toXML4GuessYouLike(int is_ad){
		StringBuffer sb = new StringBuffer();
		if(null==discount){
			discount="";
		}
		sb.append("<item>")
		        .append("<itemid>").append(num_iid).append("</itemid>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<price>").append(price_low).append("</price>")
				.append("<price_high>").append(price_high).append("</price_high>")
				.append("<zk>").append(discount).append("</zk>")
				.append("<jfb_rate>").append(commission_rate).append("</jfb_rate>")
				.append("<picurl><![CDATA[").append(pic_url).append("]]></picurl>")
				.append("<clickurl><![CDATA[").append(click_url).append("]]></clickurl>")
				.append("<is_ad>").append(is_ad).append("</is_ad>")//是否是 广告 0不是 1是
				.append("</item>");
		return sb.toString();
	}
	
	
}
