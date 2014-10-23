package cn.youhui.bean;

/**
 * 交易bean
 *
 */
public class GoodsTrade 
{
	//主键id
	private int id;
	
	//淘宝订单id
	private String orderId = "";
	
	//商品id
	private String itemId = "";
	
	//用户id
	private String UID;
	
	//交易完成时间
	private Long completeTime;
	
	//商品标题
	private String goodsTitle;
	
	//创建时间
	private long createTime;
	
	//更新时间
	private long updateTime;
	
	//图片
	private String picUrl;
	
	//集分宝
	private double jfb;
	
	//原价
	private double costPrice;
	
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public double getJfb() {
		return jfb;
	}

	public void setJfb(double jfb) {
		this.jfb = jfb;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public Long getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Long completeTime) {
		this.completeTime = completeTime;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
