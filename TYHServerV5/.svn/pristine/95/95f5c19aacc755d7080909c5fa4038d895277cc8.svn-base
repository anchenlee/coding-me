package cn.youhui.bean;

import java.util.HashMap;
import java.util.Map;

import cn.youhui.common.Config;
import cn.youhui.utils.AES256;
import cn.youhui.utils.StringUtils;
import cn.youhui.utils.UserUtil;

public class UserBean {
	
	private String uid ;
	private String taobaoNick ="";
	private String phoneNum = "";
	private String password;
	private String taobaoUid;
	private String imei;
	private String imsi;
	private String channel;
	private String platform;
	private long activeTime;
	private boolean isNewUser = false;
		
	public UserBean(){
	}
	
	public UserBean(String uid, String taobaoNick, String taobaoUid,  String phoneNum,
			String password, String imei, String imsi,
			String channel, String platform) {
		super();
		this.uid = uid;
		this.taobaoNick = taobaoNick;
		this.phoneNum = phoneNum;
		this.password = password;
		this.taobaoUid = taobaoUid;
		this.imei = imei;
		this.imsi = imsi;
		this.channel = channel;
		this.platform = platform;
	}

	public String getTaobaoUid() {
		return taobaoUid;
	}
	public void setTaobaoUid(String taobaoUid) {
		this.taobaoUid = taobaoUid;
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
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTaobaoNick() {
		return taobaoNick;
	}
	public void setTaobaoNick(String taobaoNick) {
		this.taobaoNick = taobaoNick;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(long activeTime) {
		this.activeTime = activeTime;
	}
	
	
	public String getCheckcode(){
		try {
			return AES256.encrypt(Config.AES_PWD, uid+password);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	public String getIcon() {
		if(StringUtils.isEmpty(taobaoUid)){
			return "http://static.etouch.cn/suishou/ad_img/taobao_icon.png";
		}else{
			return "http://wwc.taobaocdn.com//avatar/getAvatar.do?userId=" + taobaoUid + "&width=160&height=160&type=sns";
		}
	}
	
	public Map<String,String> trans2Map(){
		Map<String,String> map = new HashMap<String,String>() ;
		map.put("uid", this.uid) ;
		map.put("taobaoNick", this.taobaoNick) ;
		
		return map ;
	}
	
	public static UserBean fromMap(Map<String,String> map){
		UserBean user = new UserBean();
		user.setUid(map.get("uid")) ;
		user.setTaobaoNick(map.get("taobaoNick")) ;
		return user ;
	}
	
	public String toXML(){
		return new StringBuffer().append("<user>")
				.append("<uid>").append(uid).append("</uid>")
				.append("<taobao_nick><![CDATA[").append(taobaoNick == null ? "" : taobaoNick).append("]]></taobao_nick>")
				.append("<phone_num>").append(UserUtil.jmPhoneNum(phoneNum == null ? "" : phoneNum)).append("</phone_num>")
				.append("<icon><![CDATA[").append(getIcon()).append("]]></icon>")
				.append("<checkcode><![CDATA[").append(getCheckcode()).append("]]></checkcode>")
				.append("</user>").toString();
	}

	public boolean isNewUser() {
		return isNewUser;
	}

	public void setNewUser(boolean isNewUser) {
		this.isNewUser = isNewUser;
	}

}
