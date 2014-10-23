package cn.youhui.bean;

import cn.youhui.utils.SuiShouActionUtil;

public class FrameTitle {

	private String id = "";
	
	private String img;
	
	private String hiddenImg;
	
	private String title = "";
	
	private String actionType;
	
	private String actionValue;
	
	private SuiShouAction ssac;
	
	private int rank;
	
	private long createtime;
	
	private long updateTime;
	
	private String updateDate;
	
	private int status;
	
	private String showText = "";
	
	private String version = "";
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHiddenImg() {
		return hiddenImg;
	}

	public void setHiddenImg(String hiddenImg) {
		this.hiddenImg = hiddenImg;
	}

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public SuiShouAction getSsac() {
		return ssac;
	}

	public void setSsac(SuiShouAction ssac) {
		this.ssac = ssac;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String toXML(String platform){
		StringBuffer sb = new StringBuffer();
		sb.append("<frame_title>");
		sb.append("<id>").append(id).append("</id>");
		sb.append("<show_text><![CDATA[").append(showText).append("]]></show_text>");
		sb.append("<title><![CDATA[").append(title).append("]]></title>");
		sb.append("<img><![CDATA[").append(img).append("]]></img>");
		sb.append("<hidden_img><![CDATA[").append(hiddenImg == null ? "" : hiddenImg).append("]]></hidden_img>");
		if ("tagStyleCategory".equals(actionType)) {
			actionType = "tagStyleGrid";
		}
		String url = SuiShouActionUtil.getSuiShouActionUrl(platform , actionType , actionValue);
		ssac = new SuiShouAction(url);
		sb.append(ssac.toXML());
		sb.append("</frame_title>");
		return sb.toString();
	}
	
}
