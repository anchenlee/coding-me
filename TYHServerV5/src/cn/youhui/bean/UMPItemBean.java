package cn.youhui.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  API URL : http://gw.api.taobao.com/router/rest?sign=F2664AA969E71CA32CA62A648FD3D37D&timestamp=2012-04-24+16%3A46%3A10&v=2.0&app_key=12129701&method=taobao.ump.promotion.get&partner_id=top-apitools&format=xml&item_id=13032074048
 * 
 * **/
public class UMPItemBean {
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the itemId
	 */
	public long getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the itemTitle
	 */
	public String getItemTitle() {
		return itemTitle;
	}
	/**
	 * @param itemTitle the itemTitle to set
	 */
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	/**
	 * @return the originalPrice
	 */
	public String getOriginalPrice() {
		return originalPrice;
	}
	/**
	 * @param originalPrice the originalPrice to set
	 */
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	/**
	 * @return the promotionId
	 */
	public String getPromotionId() {
		return promotionId;
	}
	/**
	 * @param promotionId the promotionId to set
	 */
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	/**
	 * @return the promoPrice
	 */
	public String getPromoPrice() {
		return promoPrice;
	}
	/**
	 * @param promoPrice the promoPrice to set
	 */
	public void setPromoPrice(String promoPrice) {
		this.promoPrice = promoPrice;
	}
	/**
	 * @return the promoName
	 */
	public String getPromoName() {
		return promoName;
	}
	/**
	 * @param promoName the promoName to set
	 */
	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}
	/**
	 * @return the otherNeed
	 */
	public String getOtherNeed() {
		return otherNeed;
	}
	/**
	 * @param otherNeed the otherNeed to set
	 */
	public void setOtherNeed(String otherNeed) {
		this.otherNeed = otherNeed;
	}
	/**
	 * @return the otherSend
	 */
	public String getOtherSend() {
		return otherSend;
	}
	/**
	 * @param otherSend the otherSend to set
	 */
	public void setOtherSend(String otherSend) {
		this.otherSend = otherSend;
	}
	private long itemId = 0 ; //淘宝商品Id
	private String itemTitle = null ; //淘宝商品标题
	private String originalPrice = "" ; //淘宝原价
	private String desc = "" ;//UMP
	private Date endTime = null ; //UMP
	private String promotionId = null ; //UMP
	private String promoPrice = "-" ; //UMP
	private String promoName = null ; ////UMP 名品会、限时折扣、今日团购 之类
	private String otherNeed = null ; //UMP
	private String otherSend = null ; //UMP
	
	public Map<String,String> trans2Map(){
		Map<String,String> map = new HashMap<String,String>() ;
		map.put("desc", this.desc)	 ;
		map.put("itemTitle", this.itemTitle)	 ;
		map.put("originalPrice", this.originalPrice)	 ;
		map.put("otherNeed", this.otherNeed)	 ;
		map.put("otherSend", this.otherSend)	 ;
		map.put("promoName", this.promoName)	 ;
		map.put("promoPrice", this.promoPrice)	 ;
		map.put("promotionId", this.promotionId)	 ;
		map.put("endTime", Long.toString(this.endTime.getTime()))	 ;
		map.put("itemId", Long.toString(this.itemId))	 ;
		return map ;
	}
	
	public static UMPItemBean fromMap(Map<String,String> map){
		UMPItemBean bean = new UMPItemBean() ;
		bean.desc = map.get("desc") ;
		bean.itemTitle = map.get("itemTitle") ;
		bean.originalPrice = map.get("originalPrice") ;
		bean.otherNeed = map.get("otherNeed") ;
		bean.otherSend = map.get("otherSend") ;
		bean.promoName = map.get("promoName") ;
		bean.promoPrice = map.get("promoPrice") ;
		bean.promotionId = map.get("promotionId") ;
		bean.endTime = new Date(Long.parseLong(map.get("endTime")));
		bean.itemId = Long.parseLong(map.get("itemId")) ;
		return bean ;
	}
}
