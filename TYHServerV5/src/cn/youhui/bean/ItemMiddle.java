package cn.youhui.bean;

import cn.youhui.cacher.dao.Switch4JfbCacher;

public class ItemMiddle {
	
	/**
	 * 是否用户喜欢的商品
	 */
	private int isLikeItem = 0;
	
	/**
	 * 商品奖励集分宝比例
	 */
	private double jfbRate = 0;
	
	/**
	 * 商品ID
	 */
	private String itemId;
	
	/**
	 * 获取商品详情地址
	 */
	private String getItemDescURL = "http://hws.alicdn.com/cache/mtop.wdetail.getItemDescx/4.1/?data=%7B%22item_num_id%22%3A%22" +itemId+ "%22%7D";
	
	/**
	 * 展示商品详情地址
	 */
	private String showItemDescURL = "http://b17.cn/skip/do/item_desc.jsp?";
	
	private String clickURL = "http://b17.cn/skip/item?";
	

	public int getIsLikeItem() {
		return isLikeItem;
	}

	public void setIsLikeItem(int isLikeItem) {
		this.isLikeItem = isLikeItem;
	}
	
	public double getJfbRate() {
		return jfbRate;
	}

	public void setJfbRate(double jfbRate) {
		this.jfbRate = jfbRate;
	}
	

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
		this.getItemDescURL = "http://hws.alicdn.com/cache/mtop.wdetail.getItemDescx/4.1/?data=%7B%22item_num_id%22%3A%22" +itemId+ "%22%7D";
		this.clickURL = "http://b17.cn/skip/item?itemid=" + itemId;
	}

	public String toXML(){
		String jfbSwitch=Switch4JfbCacher.getInstance().getSwitch();
		if(jfbSwitch.endsWith("0")){
			jfbRate=0;
		}
		
		return new StringBuffer().append("<item_mid>")
				.append("<item_id>").append(itemId).append("</item_id>")
				.append("<islike>").append(isLikeItem).append("</islike>")
				.append("<jfb_rate>").append(jfbRate).append("</jfb_rate>")
				.append("<get_desc_url><![CDATA[").append(getItemDescURL).append("]]></get_desc_url>")
				.append("<show_desc_url><![CDATA[").append(showItemDescURL).append("]]></show_desc_url>")
				.append("<click_url><![CDATA[").append(clickURL).append("]]></click_url>")
				.append("</item_mid>").toString();
	}
	
	public String toXML4GuessYouLike(){
		return new StringBuffer().append(jfbRate).toString();
	}

	public String getClickURL() {
		return clickURL;
	}

	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}


}
