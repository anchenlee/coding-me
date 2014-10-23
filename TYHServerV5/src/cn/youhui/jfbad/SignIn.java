package cn.youhui.jfbad;

import cn.youhui.bean.SuiShouAction;
import cn.youhui.manager.SignInManager;
import cn.youhui.utils.SuiShouActionUtil;
import cn.youhui.utils.TextUtil;

/**
 * 签到
 * @author lijun
 * @since 2014-02-24
 */
public class SignIn {
	
	private String uid;
	
	
	/**
	 * 签到日期
	 */
	private String date;
	
	/**
	 * 连续签到时间
	 */
	private int runningDays = 0;
	
	/**
	 * 获得集分宝个数
	 */
	private int jfbNum = 0;
	
	/**
	 * 0未签到    1 签到还未点击广告   2 已点击广告
	 */
	private int status;
	
	private long createTime;
	
	private JFBAd jfbad = null;

	public SignIn(){}
	
	public SignIn(String uid, String date, int runningDays, int jfbNum) {
		super();
		this.uid = uid;
		this.date = date;
		this.runningDays = runningDays;
		this.jfbNum = jfbNum;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRunningDays() {
		return runningDays;
	}

	public void setRunningDays(int runningDays) {
		this.runningDays = runningDays;
	}

	public int getJfbNum() {
		return jfbNum;
	}

	public void setJfbNum(int jfbNum) {
		this.jfbNum = jfbNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public JFBAd getJfbad() {
		return jfbad;
	}

	public void setJfbad(JFBAd jfbad) {
		this.jfbad = jfbad;
	}
	
	public String toXml(){
		String signstr;
		String tip = "点击浏览商品";
		if(status == 4){
			signstr = "每日签到九点开始，九点后再试";
			status = 0;
		}else{
			if(status == 0){
				signstr = "立即签到可领取集分宝jfb个";            
			}else if(status == 1){
				signstr = "今日已签到获得集分宝"+jfbNum+"个但未领取";
				tip = "点击浏览商品，即可领取";
			}else{
				signstr = "明日签到可领jfb个";
			}
			String cjfb = SignInManager.getInstance().getNextJFBNum(uid, runningDays+1);
//			String cangetjfb;
//			if(runningDays < 4){
//				cangetjfb = runningDays + 1 + "";
//			}else{
//				cangetjfb = "5 - 15";
//			}
			signstr = signstr.replaceAll("jfb", cjfb);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<signin>")
		.append("<status>").append(status).append("</status>")
		.append("<days>").append(runningDays).append("</days>")
		.append("<permit_desc><![CDATA[").append(signstr).append("]]></permit_desc>")  
		.append("<tips_desc><![CDATA[").append(TextUtil.addColor(tip, "red")).append("]]></tips_desc>");
		if(jfbad != null){
			sb.append(jfbad.toXml());
		}
		sb.append("</signin>").toString();
		return sb.toString();
	}

	
}
