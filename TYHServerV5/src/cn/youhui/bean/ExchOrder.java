package cn.youhui.bean;

public class ExchOrder {
	  
	  private String orderId = "";
	  private String uid = "";
	  private String itemId = "";
	  private int itemVersion = 0;
	  private int count = 0;
	  private long creatTime = 0;
	  private float price = 0;
	  private float promPrice = 0;
	  private String virtualType = "";
	  private String virtualNumber = "";
	  private String addressId = "0";
	  private int status = 0;
	  private String remark = "";
	  private String addinfo = "";
	  private ExchItem item = new ExchItem();
	  private ExchAddress addre = new ExchAddress();
	  
	  public String toXML(){
		  return new StringBuffer().append("<order>")
				  .append("<orderid>").append(orderId).append("</orderid>")
				  .append("<count>").append(count).append("</count>")
				  .append("<virtualtype>").append(virtualType).append("</virtualtype>")
				  .append("<virtualnumber>").append(virtualNumber).append("</virtualnumber>")
				  .append("<addinfo>").append(addinfo).append("</addinfo>")
				  .append("<remark>").append(remark).append("</remark>")
				  .append("<orderstatus>").append(status).append("</orderstatus>")
				  .append("<time>").append(creatTime).append("</time>")
				  .append(item.toSimXML())
				  .append(addre.toXML())
				  .append("</order>").toString();
	  }
	@Override
	public String toString() {
		return "ExchOrder [orderId=" + orderId + ", uid=" + uid + ", itemId="
				+ itemId + ", count=" + count + ", creatTime=" + creatTime
				+ ", price=" + price + ", promPrice=" + promPrice
				+ ", virtualType=" + virtualType + ", virtualNumber="
				+ virtualNumber + ", addressId=" + addressId + ", status="
				+ status + ", remark=" + remark + ", addinfo=" + addinfo + "]";
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(long creatTime) {
		this.creatTime = creatTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getPromPrice() {
		return promPrice;
	}
	public void setPromPrice(float promPrice) {
		this.promPrice = promPrice;
	}
	public String getVirtualNumber() {
		return virtualNumber;
	}
	public void setVirtualNumber(String virtualNumber) {
		this.virtualNumber = virtualNumber;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVirtualType() {
		return virtualType;
	}

	public void setVirtualType(String virtualType) {
		this.virtualType = virtualType;
	}
	public String getAddinfo() {
		return addinfo;
	}
	public void setAddinfo(String addinfo) {
		this.addinfo = addinfo;
	}
	public ExchAddress getAddre() {
		return addre;
	}
	public void setAddre(ExchAddress addre) {
		this.addre = addre;
	}
	public ExchItem getItem() {
		return item;
	}
	public void setItem(ExchItem item) {
		this.item = item;
	}
	public int getItemVersion() {
		return itemVersion;
	}
	public void setItemVersion(int itemVersion) {
		this.itemVersion = itemVersion;
	}
	  
	  
}
