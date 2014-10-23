package cn.youhui.bean;

import com.google.gson.Gson;


public class CouponOrder{
	private int id;
	private String userid;
	private String couponid;
	private int number;
	private int status;
	private long createtime;
	private String orderid; 
	private long expireTime;
	
	
	
	
	
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCouponid() {
		return couponid;
	}
	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public String toXML() {
		StringBuffer sb =  new StringBuffer();
		sb.append("<order>")
		.append("<orderid>").append(orderid).append("</orderid>")
		.append("<createtime><![CDATA[").append(createtime).append("]]></createtime>")
		.append("<number><![CDATA[").append(number).append("]]></number>");
		sb.append("</order>");
		return sb.toString();
	}
	

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this, CouponOrder.class);
		return json;
	}
}
