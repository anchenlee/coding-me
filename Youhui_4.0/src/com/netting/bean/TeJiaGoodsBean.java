package com.netting.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class TeJiaGoodsBean 
{
	//private String id = "";
	private String item_id = "";
	private String title = "";
	private int category_id = 0;
	private String keyword = "";
	private int show_index;
	private String price_high = "";
	private String price_low ="";
	private String discount = "";
	private String discountType = "" ;
	private String pic_url = "";
	private int status = 0 ;
	private String recent_sell = "";
	private String taobao_time_limit = "";
	private long start_time = 0;
	private long end_time = 0; 
	private String sellerNick = "" ;
	private String clickURL = "" ;
	private String update_time = "0";
	private int width_b =0;
	private int height_b=0;
	private int width_m=0;
	private int height_m=0;
	private int width_310=0;
	private int height_310=0;
	private int width_170=0;
	private int height_170=0;
	private String commission = "";
	private String commission_rate = "";
	private int rank;
	private int isUpdateed = 0;
	
	private double rate = 0.0;
	
	private String catID;
	
	private String recoReason;
	
	private String tagId = "";
	
	private int baoyou = 0; 
	
	
	public int getBaoyou() {
		return baoyou;
	}
	public void setBaoyou(int baoyou) {
		this.baoyou = baoyou;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getRecoReason() {
		return recoReason;
	}
	public void setRecoReason(String recoReason) {
		this.recoReason = recoReason;
	}
	public String getCatID() {
		return catID;
	}
	public void setCatID(String catID) {
		this.catID = catID;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	/**
	 * @return the clickURL
	 */
	public String getClickURL() {
		return clickURL;
	}
	/**
	 * @param clickURL the clickURL to set
	 */
	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}
	/**
	 * @return the sellerNick
	 */
	public String getSellerNick() {
		return sellerNick;
	}
	/**
	 * @param sellerNick the sellerNick to set
	 */
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	/**
	 * @return the discountType
	 */
	public String getDiscountType() {
		return discountType;
	}
	/**
	 * @param discountType the discountType to set
	 */
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRecent_sell() {
		return recent_sell;
	}
	public void setRecent_sell(String recent_sell) {
		this.recent_sell = recent_sell;
	}
	public String getTaobao_time_limit() {
		return taobao_time_limit;
	}
	public void setTaobao_time_limit(String taobao_time_limit) {
		this.taobao_time_limit = taobao_time_limit;
	}
	public long getStart_time() {
		return start_time;
	}
	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}
	public long getEnd_time() {
		return end_time;
	}
	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
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
	public String toJson(){
		Gson g = new Gson();
		return g.toJson(this);
	}
	public void setShow_index(int show_index) {
		this.show_index = show_index;
	}
	public int getShow_index() {
		return show_index;
	}
	public void setWidth_b(int width_b) {
		this.width_b = width_b;
	}
	public int getWidth_b() {
		return width_b;
	}
	public void setHeight_b(int heigh_b) {
		this.height_b = heigh_b;
	}
	public int getHeight_b() {
		return height_b;
	}
	public void setWidth_m(int width_m) {
		this.width_m = width_m;
	}
	public int getWidth_m() {
		return width_m;
	}
	public void setHeight_m(int heigh_m) {
		this.height_m = heigh_m;
	}
	public int getHeight_m() {
		return height_m;
	}
	public void setWidth_310(int width_310) {
		this.width_310 = width_310;
	}
	public int getWidth_310() {
		return width_310;
	}
	public void setHeight_310(int heigh_310) {
		this.height_310 = heigh_310;
	}
	public int getHeight_310() {
		return height_310;
	}
	public int getHeight_170() {
		return height_170;
	}
	public void setHeight_170(int height_170) {
		this.height_170 = height_170;
	}
	public int getWidth_170() {
		return width_170;
	}
	public void setWidth_170(int width_170) {
		this.width_170 = width_170;
	}
	public int getIsUpdateed() {
		return isUpdateed;
	}
	public void setIsUpdateed(int isUpdateed) {
		this.isUpdateed = isUpdateed;
	}
	
	public String toJson1(){
		JSONObject jso = new JSONObject();
		try {
			jso.put("item_id", item_id);
			jso.put("clickURL", clickURL);
			jso.put("pic_url", pic_url);
		} catch (JSONException e) {
			return jso.toString();
		}
		return jso.toString();
	}
}
