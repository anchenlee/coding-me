package cn.suishou.some.talk.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.suishou.some.response.BaseBean;

/**
 * 消息实体类
 * @author weifeng
 *
 */
public class Message extends BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Timestamp generateTime;
	private String message;
	private String ip;
	private String platform;
	private String version;
	private String u_nick;
	private String uid;
	private Integer aid;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Timestamp getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(Timestamp generateTime) {
		this.generateTime = generateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", generateTime=" + generateTime
				+ ", message=" + message + ", ip=" + ip + ", platform="
				+ platform + ", version=" + version + ", u_nick=" + u_nick
				+ ", uid=" + uid + ", aid=" + aid + "]";
	}
	
	public String toJson(){
		return new StringBuffer().append("{")
				.append("\"nick\":\"").append(u_nick)
				.append("\",\"generateTime\":\"").append(generateTime)
				.append("\",\"msg\":\"").append(message)
				.append("\"}").toString();
	}
	
}
