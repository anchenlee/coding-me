package cn.youhui.jfbhis;


/**
 * 每月统计实体类
 * @author hufan
 * @since 2014-9-15
 */
public class TongjiBean {

	private String sign="";
	private String fenhong="";
	private String trade="";
	private int signNum=0;
	private int fenhongNum=0;
	private int tradeNum=0;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getFenhong() {
		return fenhong;
	}
	public void setFenhong(String fenhong) {
		this.fenhong = fenhong;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public int getSignNum() {
		return signNum;
	}
	public void setSignNum(int signNum) {
		this.signNum = signNum;
	}
	public int getFenhongNum() {
		return fenhongNum;
	}
	public void setFenhongNum(int fenhongNum) {
		this.fenhongNum = fenhongNum;
	}
	public int getTradeNum() {
		return tradeNum;
	}
	public void setTradeNum(int tradeNum) {
		this.tradeNum = tradeNum;
	}
	
}
