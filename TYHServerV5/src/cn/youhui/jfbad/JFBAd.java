package cn.youhui.jfbad;

import java.io.Serializable;

import cn.youhui.bean.SuiShouAction;
import cn.youhui.dao.SuperCouponDAO;
import cn.youhui.dao.SuperDiscountDAO;
import cn.youhui.utils.SuiShouActionUtil;

/**
 * 集分宝广告
 * @author lijun
 *
 */
public class JFBAd implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 广告ID
	 */
	private String adId;
	
	/**
	 * 广告名称
	 */
	private String adName;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 广告图片
	 */
	private String img;
	
	/**
	 * ipad广告图片
	 */
	private String ipadImg;
	
	/**
	 * 点击链接
	 */
	private String clickUrl;
	
	/**
	 * 点击获得集分宝
	 */
	private int perNum;
	
	/**
	 * 1 正常  2 失效  3 已发完
	 */
	private int status;
	
	/**
	 * 1签到    2广告
	 */
	private int type;

	/**
	 * 开始时间
	 */
	private long startTime;
	
	/**
	 * 按钮字符
	 */
	private String button = "";
	
	/**
	 * 领取介绍
	 */
	private String gainInfo = "";
	
	
	private SuiShouAction action;
	
	/**
	 * 是否需要回调
	 */
	private boolean isNeedCallBack = false;
	
	private String itemId;

	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public int getPerNum() {
		return perNum;
	}

	public void setPerNum(int perNum) {
		this.perNum = perNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public SuiShouAction getAction() {
		return action;
	}

	public void setAction(SuiShouAction action) {
		this.action = action;
	}
	
	public String getGainInfo() {
		return gainInfo;
	}

	public void setGainInfo(String gainInfo) {
		this.gainInfo = gainInfo;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String toXml(){
		StringBuffer sb = new StringBuffer();
		sb.append("<jfb_ad>")
		.append("<ad_id>"+ adId +"</ad_id>")
		.append("<img><![CDATA[").append(img).append("]]></img>")
		.append("<title><![CDATA[").append(adName).append("]]></title>")
		.append("<need_callbakc>" + (isNeedCallBack?1:0) + "</need_callbakc>");
		if(button != null && !"".equals(button)){
		sb.append("<button><![CDATA[").append(button).append("]]></button>")
		 .append("<gain_info><![CDATA[").append(gainInfo).append("]]></gain_info>");
		}
		if(clickUrl!=null&&!"".equals(clickUrl)){
			clickUrl=clickUrl.replaceAll("%26", "&");
			action.setUrl(clickUrl);
		}
		
		if(itemId!=null&&!"".equals(itemId)&&SuperCouponDAO.getInstance().ifIn(itemId)){
			int id=SuperDiscountDAO.getInstance().getInfoById(itemId);
//			String url=SuiShouActionUtil.getSuishouWebUrl("iphone","http://v2.api.njnetting.cn/SCheap/startRushbuy.html?id="+id);//这里的platform写死的 。。。-----蒋军 2014-09-28
			String url="http://v2.api.njnetting.cn/SCheap/startRushbuy.html?id="+id;
			action.setUrl(url);
		}
		if(action != null){
			sb.append(action.toXML());
		}
		sb.append("</jfb_ad>");
		return sb.toString();
	}

	
	@Override
	public boolean equals(Object ad){
		if(this.adId != null && !"".equals(this.adId) && ad instanceof JFBAd && this.adId.equals(((JFBAd)ad).getAdId())){
			return true;
		}else{
			return false;
		}
	}

	public boolean isNeedCallBack() {
		return isNeedCallBack;
	}

	public void setNeedCallBack(boolean isNeedCallBack) {
		this.isNeedCallBack = isNeedCallBack;
	}

	public String getIpadImg() {
		return ipadImg;
	}

	public void setIpadImg(String ipadImg) {
		this.ipadImg = ipadImg;
	}
	
}
