package cn.youhui.bean;

import net.sf.json.JSONObject;

public class SuperDiscountForWebBean {

	public int id;
	public String title;
	public String imgSmall;
	public String imgBig;
	public String itemId;
	public int isSecondKill;
	public double priceLow;
	public double priceHigh;
	public String color;
	
	
	
	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public double getPriceLow() {
		return priceLow;
	}



	public void setPriceLow(double priceLow) {
		this.priceLow = priceLow;
	}



	public double getPriceHigh() {
		return priceHigh;
	}



	public void setPriceHigh(double priceHigh) {
		this.priceHigh = priceHigh;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getImgSmall() {
		return imgSmall;
	}



	public void setImgSmall(String imgSmall) {
		this.imgSmall = imgSmall;
	}



	public String getImgBig() {
		return imgBig;
	}



	public void setImgBig(String imgBig) {
		this.imgBig = imgBig;
	}



	public String getItemId() {
		return itemId;
	}



	public void setItemId(String itemId) {
		this.itemId = itemId;
	}



	public int getIsSecondKill() {
		return isSecondKill;
	}



	public void setIsSecondKill(int isSecondKill) {
		this.isSecondKill = isSecondKill;
	}

}
