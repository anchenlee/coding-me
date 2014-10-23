package cn.youhui.bean;

import cn.youhui.utils.FenHongUtil;

/**
 * 用户帐户信息
 * @author lijun
 * @since 2014-7-3
 */
public class UserAccount {
	
	/**
	 * uid
	 */
	private String uid = "";
	
	/**
	 * 活动获得的
	 */
	private int acAddNum = 0;
	
	/**
	 * 购物奖励获得的
	 */
	private int flAddNum = 0;
	
	/**
	 * 购物奖励审核中的
	 */
	private int flCheckNum = 0;
	
	/**
	 * 花费的（抽奖，秒杀等）
	 */
	private int payNum = 0;
	
	/**
	 * 提现（包括审核中的）
	 */
	private int txNum = 0;
	
	/**
	 * 提现处理中的
	 */
	private int txCheckNum = 0;
	
	/**
	 * 集分宝余额
	 */
	private int yuE = 0;
	
	/**
	 * 总返利现金
	 */
	private double flXJ = 0;
	

	@Override
	public String toString() {
		return "UserAccount [acAddNum=" + acAddNum + ", flAddNum=" + flAddNum
				+ ", flCheckNum=" + flCheckNum + ", payNum=" + payNum
				+ ", txNum=" + txNum + ", txCheckNum=" + txCheckNum + ", yuE="
				+ yuE + ", flXJ=" + flXJ + "]";
	}

	public int getAcAddNum() {
		return acAddNum;
	}

	public void setAcAddNum(int acAddNum) {
		this.acAddNum = acAddNum;
	}

	public int getFlAddNum() {
		return flAddNum;
	}

	public void setFlAddNum(int flAddNum) {
		this.flAddNum = flAddNum;
	}

	public int getFlCheckNum() {
		return flCheckNum;
	}

	public void setFlCheckNum(int flCheckNum) {
		this.flCheckNum = flCheckNum;
	}

	public int getPayNum() {
		return payNum;
	}

	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}

	public int getTxNum() {
		return txNum;
	}

	public void setTxNum(int txNum) {
		this.txNum = txNum;
	}

	public int getTxCheckNum() {
		return txCheckNum;
	}

	public void setTxCheckNum(int txCheckNum) {
		this.txCheckNum = txCheckNum;
	}

	public int getYuE() {
		return yuE;
	}

	public void setYuE(int yuE) {
		this.yuE = yuE;
	}

	public double getFlXJ() {
		return flXJ;
	}

	public void setFlXJ(double flXJ) {
		this.flXJ = flXJ;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public int getLevel(){
		int level = 0;
		double fanli = flXJ + (double)flAddNum/100;
		level = FenHongUtil.getLevel(fanli);
		return level;
	}
	
	public int getGainJFB(){
		return flAddNum + acAddNum;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserAccount){
			UserAccount ua1 = (UserAccount)obj;
			if(ua1 != null && ua1.uid.equals(this.uid) && ua1.acAddNum == this.acAddNum && ua1.flAddNum == this.flAddNum && ua1.flCheckNum == this.flCheckNum && ua1.payNum == this.payNum && ua1.txCheckNum == this.txCheckNum && ua1.txNum == this.txNum && ua1.yuE == this.yuE){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
