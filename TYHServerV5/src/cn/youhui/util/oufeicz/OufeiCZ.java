package cn.youhui.util.oufeicz;

public class OufeiCZ {

	private String ordreId;
	
	private String timeStr;
	
	private String phoneNum;
	
	private int num;
	
	private int status;
	
	private String orderCash = "";
	
	private String cpOrderId = "";
	
	private int cpStatus;
	
	private String acId = "";
	
	private String acUniqueId = "";
	
	public int getCpStatus() {
		return cpStatus;
	}

	public void setCpStatus(int cpStatus) {
		this.cpStatus = cpStatus;
	}

	public String getOrderCash() {
		return orderCash;
	}

	public void setOrderCash(String orderCash) {
		this.orderCash = orderCash;
	}

	public String getCpOrderId() {
		return cpOrderId;
	}

	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}

	public String getOrdreId() {
		return ordreId;
	}

	public void setOrdreId(String ordreId) {
		this.ordreId = ordreId;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAcId() {
		return acId;
	}

	public void setAcId(String acId) {
		this.acId = acId;
	}

	public String getAcUniqueId() {
		return acUniqueId;
	}

	public void setAcUniqueId(String acUniqueId) {
		this.acUniqueId = acUniqueId;
	}
	
}
