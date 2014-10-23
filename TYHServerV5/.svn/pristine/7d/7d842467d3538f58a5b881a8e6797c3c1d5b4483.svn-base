package cn.youhui.bean;

import java.text.DecimalFormat;
import java.util.Random;

import cn.youhui.common.Config;

public class ProfRecommend {
	
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
	 * 商品标题
	 */
	private String itemTitle;
	
	/**
	 * 价格
	 */
	private double itemPrice;
	
	/**
	 * 折扣价格
	 */
	private double itemPromPrice;
	
	/**
	 * 达人信息
	 */
	private Professor professor;
	
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
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
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		String imgWidth = imgSize.substring(0, imgSize.indexOf("x"));
		String imgHeight = imgSize.substring(imgSize.indexOf("x")+1, imgSize.length());
	      sb.append("<prof_item>")
			.append("<id>").append(id).append("</id>")
			.append("<style>").append((int)(new Random().nextFloat() * 2)).append("</style>")
			.append("<img width='"+ imgWidth +"' height='" + imgHeight + "' ><![CDATA[").append(itemImg).append("]]></img>")
			.append("<recommend_reason><![CDATA[").append(recommendReaSon).append("]]></recommend_reason>")
	        .append("<vote_good_num>").append(voteGoodNum).append("</vote_good_num>")
	        .append("<vote_bad_num>").append(voteBadNum).append("</vote_bad_num>")
	        .append("<user_vote>").append(userVote).append("</user_vote>");
			if(professor != null){
				sb.append(professor.toXML());
			}
				sb.append("<item>");
				sb.append("<item_id>" + itemId + "</item_id>");
				sb.append("<jfb_rate>" + 2.0 +"</jfb_rate>");
				sb.append("<title><![CDATA[" + itemTitle.replaceAll("<[^>]+>", "") + "]]></title>");
				sb.append("<price phi=\"" + itemPrice * 100 + "\" plow=\"" + itemPromPrice*100 + "\" off=\""
						+ new DecimalFormat("0.00").format(itemPromPrice * 10000 / itemPrice) + "\"></price>");
				sb.append("<click><![CDATA["+Config.SKIP_URL+"itemid="+ itemId + "]]></click>");
				sb.append("</item>");
			sb.append("</prof_item>").toString();
			return sb.toString();
    }

	public int getUserVote() {
		return userVote;
	}

	public void setUserVote(int userVote) {
		this.userVote = userVote;
	}
	
}
