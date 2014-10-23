package cn.youhui.bean;

public class TaobaoShop {
	
	private String sellerNick;
	
	/**
	 * 用户店铺ID
	 */
	private String userId;
	private String shopTitle;
	private String clickUrl;
	private double commissionRate;
	private String sellerCredit;
	private String shopType;
	
	/**
	 * 累计推广量
	 */
	private String totalAuction;
	
	/**
	 * 店铺商品总数
	 */
	private long auction_count;

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getShopTitle() {
		return shopTitle;
	}

	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getSellerCredit() {
		return sellerCredit;
	}

	public void setSellerCredit(String sellerCredit) {
		this.sellerCredit = sellerCredit;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getTotalAuction() {
		return totalAuction;
	}

	public void setTotalAuction(String totalAuction) {
		this.totalAuction = totalAuction;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public long getAuction_count() {
		return auction_count;
	}

	public void setAuction_count(long auction_count) {
		this.auction_count = auction_count;
	}
	
	
   public String toXML(){
		return new StringBuffer().append("<shop>")
				.append("<shop_id>").append(userId).append("</shop_id>")
				.append("<shop_title><![CDATA[").append(shopTitle).append("]]></shop_title>")
				.append("<click_url><![CDATA[").append(clickUrl).append("]]></click_url>")
				.append("<commission_rate>").append(commissionRate).append("</commission_rate>")
				.append("<shop_type>").append(shopType).append("</shop_type>")
				.append("<seller_credit>").append(sellerCredit).append("</seller_credit>")
				.append("</shop>").toString();
	}
}
