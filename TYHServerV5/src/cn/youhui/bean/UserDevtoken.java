package cn.youhui.bean;

/**
 * iphone 设备信息  
 * @author lijun
 * @since 2014-9-16
 */
public class UserDevtoken {

	private String openudid = "";
	
	private String imei = "";
	
	private String imsi = "";
	
	private String idfa = "";
	
	private String macMD5 = "";
	
	private String osVersion = "";
	
	private String version = "";
	
	private String uid = "";
	
	private String devtoken = "";
	
	@Override
	public String toString() {
		return "UserDevtoken [openudid=" + openudid + ", imei=" + imei
				+ ", imsi=" + imsi + ", idfa=" + idfa + ", macMD5=" + macMD5
				+ ", osVersion=" + osVersion + ", version=" + version
				+ ", uid=" + uid + ", devtoken=" + devtoken + "]";
	}

	public UserDevtoken(String openudid, String imei, String imsi, String idfa,
			String macMD5, String osVersion, String version, String uid,
			String devtoken) {
		super();
		this.openudid = openudid;
		this.imei = imei;
		this.imsi = imsi;
		this.idfa = idfa;
		this.macMD5 = macMD5;
		this.osVersion = osVersion;
		this.version = version;
		this.uid = uid;
		this.devtoken = devtoken;
	}

	public String getOpenudid() {
		return openudid;
	}

	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getMacMD5() {
		return macMD5;
	}

	public void setMacMD5(String macMD5) {
		this.macMD5 = macMD5;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDevtoken() {
		return devtoken;
	}

	public void setDevtoken(String devtoken) {
		this.devtoken = devtoken;
	}
	
}
