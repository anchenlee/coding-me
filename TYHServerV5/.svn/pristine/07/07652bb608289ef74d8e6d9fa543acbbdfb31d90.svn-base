package cn.youhui.bean;

import cn.youhui.utils.SuiShouActionUtil;
import cn.youhui.utils.Util;

public class SuperCouponBean {

	public int id;
	public String itemId;
	public String itemTitle;
	public String couponAddress;
	public String yhqTitle;
	public int status;
	
	
	
	public String getYhqTitle() {
		return yhqTitle;
	}
	public void setYhqTitle(String yhqTitle) {
		this.yhqTitle = yhqTitle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getCouponAddress() {
		return couponAddress;
	}
	public void setCouponAddress(String couponAddress) {
		this.couponAddress = couponAddress;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String toJson(String platform){
		StringBuffer sbf=new StringBuffer();
		sbf.append("{");
		sbf.append("\"id\":\""+id+"\",");
		sbf.append("\"item_id\":\""+itemId+"\",");
		sbf.append("\"item_title\":\""+itemTitle+"\",");
		sbf.append("\"yhq_title\":\""+yhqTitle+"\",");
		sbf.append("\"coupon_address\":\""+SuiShouActionUtil.getSuishouWebUrl(platform, couponAddress)+"\",");
		sbf.append("\"status\":\""+status+"\"");
		sbf.append("}");
		return sbf.toString();
	}
	
	
}
