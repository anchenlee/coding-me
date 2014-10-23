package cn.youhui.jfbad;

public class JFBAdClick {

	private String id;
	
	/**
	 * 用户ID
	 */
	private String uid;
	
	/**
	 * 用户淘宝昵称
	 */
	private String taobaoNick;
	
	/**
	 * 集分宝广告ID
	 */
	private String adId;
	
	/**
	 * 获得集分宝数
	 */
	private int jfbNum;
	
	/**
	 * 点击广告时间
	 */
	private long clickTime;

	/**
	 * 点击广告日期
	 */
	private String date;
	
	/**
	 * 1点击   2加载完成
	 */
	private int status;
	
	/**
	 * 是否来自签到
	 */
	private int isFromSign;
	
	/**
	 * 活动唯一标识
	 */
	private String acUniqueId;
	
	/**
	 * 活动key
	 */
	private String acKey;

	
	public JFBAdClick(String uid, String taobaoNick, String adId, int jfbNum,
			long clickTime, String date, int isFromSign,
			String acUniqueId, String acKey) {
		super();
		this.uid = uid;
		this.taobaoNick = taobaoNick;
		this.adId = adId;
		this.jfbNum = jfbNum;
		this.clickTime = clickTime;
		this.date = date;
		this.isFromSign = isFromSign;
		this.acUniqueId = acUniqueId;
		this.acKey = acKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}
	

	public int getJfbNum() {
		return jfbNum;
	}

	public void setJfbNum(int jfbNum) {
		this.jfbNum = jfbNum;
	}

	public long getClickTime() {
		return clickTime;
	}

	public void setClickTime(long clickTime) {
		this.clickTime = clickTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsFromSign() {
		return isFromSign;
	}

	public void setIsFromSign(int isFromSign) {
		this.isFromSign = isFromSign;
	}

	public String getAcUniqueId() {
		return acUniqueId;
	}

	public void setAcUniqueId(String acUniqueId) {
		this.acUniqueId = acUniqueId;
	}

	public String getAcKey() {
		return acKey;
	}

	public void setAcKey(String acKey) {
		this.acKey = acKey;
	}
	
	
}
