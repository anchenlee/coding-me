package cn.youhui.bean;

public class ProfRecomEvaluate {
	
	private String id;
	
	/**
	 * 推荐信息ID
	 */
	private String recommendId;
	
	/**
	 * 评论人uid
	 */
	private String uid;
	
	/**
	 * 评论人淘宝昵称
	 */
	private String taobaoNick;
	
	/**
	 * 评论
	 */
	private String evaluate;
	
	/**
	 * 评论时间
	 */
	private String time;

	public ProfRecomEvaluate(){}
	
	
	public ProfRecomEvaluate(String recommendId, String uid, String taobaoNick,
			String evaluate) {
		super();
		this.recommendId = recommendId;
		this.uid = uid;
		this.taobaoNick = taobaoNick;
		this.evaluate = evaluate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
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

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
	  sb.append("<evaluate>")
			.append("<id>").append(id).append("</id>")
			.append("<nick><![CDATA[").append(changeTaobaoNick(taobaoNick)).append("]]></nick>")
			.append("<desc><![CDATA[").append(evaluate).append("]]></desc>")
			.append("</evaluate>").toString();
			return sb.toString();
    }
	
	private String changeTaobaoNick(String taobaoNick){
		return taobaoNick.substring(0,1) + "**" + taobaoNick.substring(taobaoNick.length() - 1, taobaoNick.length());
	}
}
