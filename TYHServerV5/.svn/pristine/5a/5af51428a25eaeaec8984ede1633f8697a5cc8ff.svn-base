package cn.youhui.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.youhui.utils.SuiShouActionUtil;

public class AdBean {
	String ad_id;

	String app;
	String description;
	String img;
	String actiontype;
	String actionvalue;
	String ad_title;
	String ad_image;
	String ad_desc;
	String ad_type;
	String ad_details;
	String starttime;
	String endtime;
	String ad_click_num;
	String addtime;
	String ad_platform = "";
	Action action = new Action();
	SuiShouAction ssac ;
	
	
	public SuiShouAction getSsac() {
		return ssac;
	}

	public void setSsac(SuiShouAction ssac) {
		this.ssac = ssac;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_title() {
		return ad_title;
	}

	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}

	public String getAd_image() {
		return ad_image;
	}

	public void setAd_image(String ad_image) {
		this.ad_image = ad_image;
	}

	public String getAd_desc() {
		return ad_desc;
	}

	public void setAd_desc(String ad_desc) {
		this.ad_desc = ad_desc;
	}

	public String getAd_type() {
		return ad_type;
	}

	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}

	public String getAd_details() {
		return ad_details;
	}

	public void setAd_details(String ad_details) {
		this.ad_details = ad_details;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getAd_click_num() {
		return ad_click_num;
	}

	public void setAd_click_num(String ad_click_num) {
		this.ad_click_num = ad_click_num;
	}

	public String getAddtime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date(Long.parseLong(addtime));

		this.addtime = df.format(d);
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	public String getAd_platform() {
		return ad_platform;
	}

	public void setAd_platform(String ad_platform) {
		this.ad_platform = ad_platform;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getActiontype() {
		return actiontype;
	}

	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}

	public String getActionvalue() {
		return actionvalue;
	}

	public void setActionvalue(String actionvalue) {
		this.actionvalue = actionvalue;
	}
	
	public String toXML(){
		return new StringBuffer().append("<ad>")
				.append("<id>").append(ad_id).append("</id>")
				.append("<type><![CDATA[").append(ad_type).append("]]></type>")
				.append("<title><![CDATA[").append(ad_title).append("]]></title>")
				.append("<content><![CDATA[").append(ad_desc).append("]]></content>")
				.append(action.toXML())
				.append("</ad>").toString();
	}
	
	public String toXMLNew(){
		StringBuffer sb = new StringBuffer();
		sb.append("<ad>")
				.append("<id>").append(ad_id).append("</id>")
				.append("<type><![CDATA[").append(ad_type).append("]]></type>")
				.append("<title><![CDATA[").append(ad_title).append("]]></title>")
				.append("<content><![CDATA[").append(ad_desc).append("]]></content>");
				if(ssac == null){
					ssac = new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(ad_platform,action.getActionType(),new String[]{action.getActionValue(),ad_title}));
				}
				sb.append(ssac.toXML());
//				.append(action.toXML())
				sb.append("</ad>").toString();
		return sb.toString();
	}
}
