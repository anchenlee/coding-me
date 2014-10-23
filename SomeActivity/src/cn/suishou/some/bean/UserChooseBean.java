package cn.suishou.some.bean;

/**
 * 记录用户选择bean
 * @author hufan
 * @since 2014-9-17
 */
public class UserChooseBean {

	private String openId="";
	private String nickName="";
	private String ip="";
	private String choose="";
	private String img="";
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
