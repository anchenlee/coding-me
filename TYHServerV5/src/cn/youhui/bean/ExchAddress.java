package cn.youhui.bean;

public class ExchAddress {
	  
	  private String addressId = "";
	  private String uid = "";
	  private String recName = "";
	  private String recTel = "";
	  private String postCode = "";
	  private String province = "";
	  private String city = "";
	  private String district = "";
	  private String address = "";
	  
	  
	public String toXML(){
		return new StringBuffer().append("<address>")
				.append("<recname>").append(recName).append("</recname>")
				.append("<rectel>").append(recTel).append("</rectel>")
				.append("<postcode>").append(postCode).append("</postcode>")
				.append("<province>").append(province).append("</province>")
				.append("<city>").append(city).append("</city>")
				.append("<district>").append(district).append("</district>")
				.append("<addre>").append(address).append("</addre>")
				.append("</address>").toString();
	}
	
	@Override
	public String toString() {
		return "ExchAddress [addressId=" + addressId + ", uid=" + uid
				+ ", recName=" + recName + ", recTel=" + recTel + ", postCode="
				+ postCode + ", province=" + province + ", city=" + city
				+ ", district=" + district + ", address=" + address
				+ "]";
	}
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public String getRecTel() {
		return recTel;
	}
	public void setRecTel(String recTel) {
		this.recTel = recTel;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}  
}
