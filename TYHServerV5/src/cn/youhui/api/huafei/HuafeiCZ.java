package cn.youhui.api.huafei;

import java.util.Date;

import cn.youhui.common.ParamConfig;
import cn.youhui.utils.DateUtil;
import cn.youhui.utils.TimeUtil;

public class HuafeiCZ {

	/**
	 * tradeId
	 */
	private String tradeId = "";
	
	/**
	 * uid
	 */
	private String uid = "";
	
	/**
	 * 电话号码
	 */
	private String phoneNum = "";
	
	/**
	 * 充值金额
	 */
	private int czNum = 0;
	
	/**
	 * 创建时间
	 */
	private long createTime = 0;

	public HuafeiCZ(){}
	
	public HuafeiCZ(String uid, String phoneNum, int czNum) {
		super();
		this.uid = uid;
		this.phoneNum = phoneNum;
		this.czNum = czNum;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getCzNum() {
		return czNum;
	}

	public void setCzNum(int czNum) {
		this.czNum = czNum;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public String toJson(){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"id\":\""+tradeId+"\",");
		sb.append("\"phone_num\":\""+phoneNum+"\",");
		sb.append("\"create_time\":\""+TimeUtil.getDateTime(createTime)+"\",");
		sb.append("\"cz_num\":\""+czNum+"\",");
		sb.append("\"jfb_num\":\"" +ExchangeRule.getNeedJfbNum(czNum)+ "\"");
		sb.append("}");
		return sb.toString();
	}
	
}
