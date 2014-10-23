package cn.youhui.bean;

public class SecondKill {

	public int id;
	public int typeId;
	public String type;
	public int killPrice;
	public int killNum;
	public long killStartTimestamp;
	public long killEndTimestamp;
	public int remainNum;
	
	
	
	public int getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getKillNum() {
		return killNum;
	}
	public void setKillNum(int killNum) {
		this.killNum = killNum;
	}
	public long getKillStartTimestamp() {
		return killStartTimestamp;
	}
	public void setKillStartTimestamp(long killStartTimestamp) {
		this.killStartTimestamp = killStartTimestamp;
	}
	public long getKillEndTimestamp() {
		return killEndTimestamp;
	}
	public void setKillEndTimestamp(long killEndTimestamp) {
		this.killEndTimestamp = killEndTimestamp;
	}
	public int getKillPrice() {
		return killPrice;
	}
	public void setKillPrice(int killPrice) {
		this.killPrice = killPrice;
	}
	
	
}
