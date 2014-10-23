package cn.youhui.bean;


public class ProfRecom {
	
	private String id;
	
	/**
	 * 达人ID
	 */
	private String professorId;
	
	/**
	 * 商品ID
	 */
	private String itemId;
	
	/**
	 * 商品标题
	 */
	private String itemTitle;
	
	/**
	 * 描述
	 */
	private String itemDesc;
	
	/**
	 * 价格
	 */
	private double itemPrice;
	
	/**
	 * 折扣价格
	 */
	private double itemPromPrice;
	
	/**
	 * 推荐原因
	 */
	private String recommendReaSon;
	
	/**
	 * 商品封面图
	 */
	private String itemImg;
	
	/**
	 * 图片大小
	 */
	private String imgSize;
	
	/**
	 * 跳转方式
	 */
	private String actionType;
	
	/**
	 * 跳转值
	 */
	private String actionValue;
	
	/**
	 * 更新时间
	 */
	private long timestamp;
	
	private String timestampStr;
	
	/**
	 * 值的数量
	 */
	private int voteGoodNum = 0;
	
	/**
	 * 不值的数量
	 */
	private int voteBadNum = 0;
	
	/**
	 * 用户点值或不值
	 */
	private int userVote = 0;
	
	private long rank ;
	
	/**
	 * 是否为商品   1为商品
	 */
	private int isItem = 1;
	
	/**
	 * 0不包邮，1包邮
	 */
	private int baoyou = 0;
	
	private SuiShouAction action;
	
	private String status;
	
	private int favNum = 0;
	
	
	public int getBaoyou() {
		return baoyou;
	}

	public void setBaoyou(int baoyou) {
		this.baoyou = baoyou;
	}

	public int getFavNum() {
		return favNum;
	}

	public void setFavNum(int favNum) {
		this.favNum = favNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	public int getVoteGoodNum() {
		return voteGoodNum;
	}

	public void setVoteGoodNum(int voteGoodNum) {
		this.voteGoodNum = voteGoodNum;
	}

	public int getVoteBadNum() {
		return voteBadNum;
	}

	public void setVoteBadNum(int voteBadNum) {
		this.voteBadNum = voteBadNum;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public double getItemPromPrice() {
		return itemPromPrice;
	}

	public void setItemPromPrice(double itemPromPrice) {
		this.itemPromPrice = itemPromPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getRecommendReaSon() {
		return recommendReaSon;
	}

	public void setRecommendReaSon(String recommendReaSon) {
		this.recommendReaSon = recommendReaSon;
	}

	public String getItemImg() {
		return itemImg;
	}

	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}

	public String getImgSize() {
		return imgSize;
	}

	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}
	
	public int getUserVote() {
		return userVote;
	}

	public void setUserVote(int userVote) {
		this.userVote = userVote;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getIsItem() {
		return isItem;
	}

	public void setIsItem(int isItem) {
		this.isItem = isItem;
	}
	public SuiShouAction getAction() {
		return action;
	}

	public void setAction(SuiShouAction action) {
		this.action = action;
	}
	
	
	public String getTimestampStr() {
		return timestampStr;
	}

	public void setTimestampStr(String timestampStr) {
		this.timestampStr = timestampStr;
	}

	public String toXML(){
		StringBuffer sb = new StringBuffer();
		String imgWidth = imgSize.substring(0, imgSize.indexOf("x"));
		String imgHeight = imgSize.substring(imgSize.indexOf("x")+1, imgSize.length());
		sb.append("<prof_recom>")
		.append("<is_item>").append(isItem).append("</is_item>")
		.append("<id>").append(id).append("</id>")
		.append("<img width='"+ imgWidth +"' height='" + imgHeight + "' ><![CDATA[").append(itemImg).append("]]></img>")
		.append("<recommend_reason><![CDATA[").append(recommendReaSon).append("]]></recommend_reason>")
		.append(action.toXML());
		sb.append("<item>");
		sb.append("<item_id>" + itemId + "</item_id>");
		sb.append("<jfb_rate>" + 2.0 +"</jfb_rate>");
		sb.append("<title><![CDATA[" + itemTitle.replaceAll("<[^>]+>", "") + "]]></title>");
		sb.append("<price>"+itemPromPrice+"</price>");
		sb.append("</item>");
		
		sb.append("</prof_recom>").toString();
		return sb.toString();
    }



}
