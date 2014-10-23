/**
 * 
 */
package cn.suishou.some.autumnitem;

import com.google.gson.Gson;

/**
 * 商品bean
 * @author lujiabin
 * @since 2014-9-2
 */
public class ItemBean {
	private String itemId = "";
	
	private String title = "";
	
	private int clickNum;
	
	private String clickUrl = "";
	
	private String picUrl = "";
	
	private String priceNow = "";
	
	private String priceOriginal = "";
	
	//点赞按钮状态
	private int status;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPriceNow() {
		return priceNow;
	}

	public void setPriceNow(String priceNow) {
		this.priceNow = priceNow;
	}

	public String getPriceOriginal() {
		return priceOriginal;
	}

	public void setPriceOriginal(String priceOriginal) {
		this.priceOriginal = priceOriginal;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toJson() {
		Gson g = new Gson();
		return g.toJson(this);
	}
}
