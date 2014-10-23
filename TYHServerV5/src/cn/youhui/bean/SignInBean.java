package cn.youhui.bean;

import cn.youhui.manager.SignInManager;
import cn.youhui.utils.StringUtils;


public class SignInBean {
	private int jifenbaoNum = 0;
	private int runningDays = 0;
	private String date;
	private int status;
	private String uid;
	private String desc = "";
	private boolean isIphoneNew = false;
	
	
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getJifenbaoNum() {
		return jifenbaoNum;
	}
	public void setJifenbaoNum(int jifenbaoNum) {
		this.jifenbaoNum = jifenbaoNum;
	}
	public int getRunningDays() {
		return runningDays;
	}
	public void setRunningDays(int runningDays) {
		this.runningDays = runningDays;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isIphoneNew() {
		return isIphoneNew;
	}
	public void setIphoneNew(boolean isIphoneNew) {
		this.isIphoneNew = isIphoneNew;
	}
	
	public String toXmlDate(){
		return new StringBuffer().append("<signin>")
				.append("<date>").append(date).append("</date>")
				.append("<jfbnum>").append(jifenbaoNum).append("</jfbnum>")
				.append("</signin>").toString();
	}
	
//	public String toXml(){
//		return new StringBuffer().append("<signin>")
//				.append("<jfbnum>").append(jifenbaoNum).append("</jfbnum>")
//				.append("<days>").append(runningDays).append("</days>")
//				.append("</signin>").toString();
//	}
	
	public String toXmlDetail(int jfbNum, int[] jfb){
		String signstr;
		if(!StringUtils.isEmpty(desc)){
			signstr = desc;
			if(status == 4){
				status = 0;
			}
		}else 
		if(status == 4){
			signstr = "每日签到九点开始，九点后再试";
			status = 0;
		}else{
			if(status == 0){
				signstr = "立即签到可领取集分宝";          //立即签到可领取集分宝jfb个
			}else{
				signstr = "明日签到可领更多集分宝";         //明日签到可领jfb个
			}
			String cjfb = SignInManager.getInstance().getNextJFBNum(uid, runningDays+1);
//			String cangetjfb;
//			if(runningDays < 4){
//				cangetjfb = runningDays + 1 + "";
//			}else{
//				cangetjfb = "5 - 15";
//			}
//			signstr = signstr.replaceAll("jfb", cangetjfb);
			
		}
		return new StringBuffer().append("<signin>")
				.append("<jfbnum>").append(jfbNum).append("</jfbnum>")
				.append("<status>").append(status).append("</status>")
				.append("<days>").append(runningDays).append("</days>")
				.append("<alljfbnum>").append(jifenbaoNum).append("</alljfbnum>")
				.append("<permit_desc><![CDATA[").append(signstr).append("]]></permit_desc>")
				.append("<jfb_all>" + jfb[1] + "</jfb_all>")
		        .append("<jfb_tixian>" + jfb[0] + "</jfb_tixian>")
			    .append("<jfb_inreview>" + jfb[2] + "</jfb_inreview>")
			    .append("<jfb_yue>" + jfb[3] + "</jfb_yue>")
				.append("</signin>").toString();
	}


}
