package cn.youhui.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.youhui.utils.SuiShouActionUtil;

/**
 * 礼物管理 BEAN
 * @author yaojianbo
 *
 */
public class GiftBean 
{
	String gift_id = "";

	String title;
	
	String type = "";
	
	String description;
	
	String img;
	
	String imgIpad;

	String actiontype;
	
	String actionvalue;
	
	String updatetime;
	
	String clickname;
	
	int hasGet = 1;
	
//	SuiShouAction ssac;
	
	

//	public SuiShouAction getSsac() {
//		return ssac;
//	}
//
//	public void setSsac(SuiShouAction ssac) {
//		this.ssac = ssac;
//	}

	
	public String getClickname() {
		return clickname;
	}

	public GiftBean(){}
	
	public GiftBean(String title, String description, String img,
			String imgIpad, String actiontype, String actionvalue,
			String clickname, int hasGet) {
		super();
		this.title = title;
		this.description = description;
		this.img = img;
		this.imgIpad = imgIpad;
		this.actiontype = actiontype;
		this.actionvalue = actionvalue;
		this.clickname = clickname;
		this.hasGet = hasGet;
	}

	public void setClickname(String clickname) {
		this.clickname = clickname;
	}

	public String getGift_id() {
		return gift_id;
	}

	public void setGift_id(String gift_id) {
		this.gift_id = gift_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	public String getImgIpad() {
		return imgIpad;
	}

	public void setImgIpad(String imgIpad) {
		this.imgIpad = imgIpad;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public int getHasGet() {
		return hasGet;
	}

	public void setHasGet(int hasGet) {
		this.hasGet = hasGet;
	}

	public String getUpdatetime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date(Long.parseLong(updatetime));

		this.updatetime = df.format(d);
		return updatetime;
	}

	public void setUpdatetime(String updatetime) 
	{
		this.updatetime = updatetime;
	}
	
	public String toXML(){
		return new StringBuffer().append("<gift>")
				.append("<id>").append(this.getGift_id()).append("</id>")
				.append("<type><![CDATA[").append(this.getType()).append("]]></type>")
				.append("<title><![CDATA[").append(this.getTitle()).append("]]></title>")
				.append("<desc><![CDATA[").append(this.getDescription()).append("]]></desc>")
				.append("<img><![CDATA[").append(this.getImg()).append("]]></img>")
				.append("<img_ipad><![CDATA[").append(this.getImgIpad()).append("]]></img_ipad>")
				.append("<clickname><![CDATA[").append(this.getClickname()).append("]]></clickname>")
				.append("<hasGet>").append(this.getHasGet()).append("</hasGet>")
				.append("<action>")
				.append("<actiontype>").append(this.getActiontype()).append("</actiontype>")
				.append("<actionvalue><![CDATA[").append(this.getActionvalue()).append("]]></actionvalue>")
				.append("</action>")
				.append("</gift>").toString();
	}
	
//	public String toXMLNew(){
//		StringBuffer sb = new StringBuffer();
//		sb.append("<gift>")
//				.append("<id>").append(this.getGift_id()).append("</id>")
//				.append("<type><![CDATA[").append(this.getType()).append("]]></type>")
//				.append("<title><![CDATA[").append(this.getTitle()).append("]]></title>")
//				.append("<desc><![CDATA[").append(this.getDescription()).append("]]></desc>")
//				.append("<img><![CDATA[").append(this.getImg()).append("]]></img>")
//				.append("<img_ipad><![CDATA[").append(this.getImgIpad()).append("]]></img_ipad>")
//				.append("<clickname><![CDATA[").append(this.getClickname()).append("]]></clickname>")
//				.append("<hasGet>").append(this.getHasGet()).append("</hasGet>");
//		if(ssac == null){
//			ssac = new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(new Action(actiontype,actionvalue)));
//		}
////				.append("<action>")
////				.append("<actiontype>").append(this.getActiontype()).append("</actiontype>")
////				.append("<actionvalue><![CDATA[").append(this.getActionvalue()).append("]]></actionvalue>")
////				.append("</action>")
//		sb.append(ssac.toXML());
//				sb.append("</gift>");
//		return sb.toString();
//	}
	
}
