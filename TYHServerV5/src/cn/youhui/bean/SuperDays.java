package cn.youhui.bean;

import java.util.List;
import cn.youhui.common.ParamConfig;
import cn.youhui.utils.SuiShouActionUtil;
public class SuperDays {

	public String chayeIcon;
	public String chayeWhRatio;
	public SuiShouAction chayeSuiShouAction;
	
	public String yugaoDisountingTitle;
	public String yugaoDisountingTips;
	public String yugaoIcon;
	public String yugaoWhRatio;
	public SuiShouAction yugaoSuiShouAction;
	public String userCodes;//订单列表跳转地址
	public int status;
	
	public List<SuperDay> list;
	
	
	
	public SuiShouAction getChayeSuiShouAction() {
		return chayeSuiShouAction;
	}
	public void setChayeSuiShouAction(SuiShouAction chayeSuiShouAction) {
		this.chayeSuiShouAction = chayeSuiShouAction;
	}
	public SuiShouAction getYugaoSuiShouAction() {
		return yugaoSuiShouAction;
	}
	public void setYugaoSuiShouAction(SuiShouAction yugaoSuiShouAction) {
		this.yugaoSuiShouAction = yugaoSuiShouAction;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getChayeIcon() {
		return chayeIcon;
	}
	public void setChayeIcon(String chayeIcon) {
		this.chayeIcon = chayeIcon;
	}
	public String getChayeWhRatio() {
		return chayeWhRatio;
	}
	public void setChayeWhRatio(String chayeWhRatio) {
		this.chayeWhRatio = chayeWhRatio;
	}
	public String getYugaoDisountingTitle() {
		return yugaoDisountingTitle;
	}
	public void setYugaoDisountingTitle(String yugaoDisountingTitle) {
		this.yugaoDisountingTitle = yugaoDisountingTitle;
	}
	public String getYugaoDisountingTips() {
		return yugaoDisountingTips;
	}
	public void setYugaoDisountingTips(String yugaoDisountingTips) {
		this.yugaoDisountingTips = yugaoDisountingTips;
	}
	public String getYugaoIcon() {
		return yugaoIcon;
	}
	public void setYugaoIcon(String yugaoIcon) {
		this.yugaoIcon = yugaoIcon;
	}
	public String getYugaoWhRatio() {
		return yugaoWhRatio;
	}
	public void setYugaoWhRatio(String yugaoWhRatio) {
		this.yugaoWhRatio = yugaoWhRatio;
	}
	public List<SuperDay> getList() {
		return list;
	}
	public void setList(List<SuperDay> list) {
		this.list = list;
	}
	
	
	public String toXML(String platform){
		
		userCodes=SuiShouActionUtil.getSuishouWebUrl(platform,ParamConfig.userCodes);
		if(status==2){
			yugaoSuiShouAction.setUrl(SuiShouActionUtil.getSuishouWebUrl(platform,ParamConfig.outTimePre));
		}else{
			yugaoSuiShouAction.setUrl(SuiShouActionUtil.getSuishouWebUrl(platform,ParamConfig.tomorrowPre));
		}
		
		StringBuffer data=new StringBuffer();
		data.append("<super_days>");
		data.append("<chaye>" +
				"<icon>" +
					"<![CDATA["+chayeIcon+"]]>" +
				"</icon>" +
				"<wh_ratio>" +
					"<![CDATA["+chayeWhRatio+"]]>" +
				"</wh_ratio>" +
				chayeSuiShouAction.toXML()+
			"</chaye>");
data.append("<yugao>" +
				"<disounting_title> <![CDATA["+yugaoDisountingTitle+"]]></disounting_title>" +
				"<disounting_tips> <![CDATA["+yugaoDisountingTips+"]]></disounting_tips>" +
				"<icon>" +
					yugaoIcon +
				"</icon>" +
				"<wh_ratio>" +
					"<![CDATA["+yugaoWhRatio+"]]>" +
				"</wh_ratio>" +
				yugaoSuiShouAction.toXML()+
			"</yugao>");
data.append("<user_codes_url>");
data.append(userCodes);
data.append("</user_codes_url>");
		for(SuperDay sd:list){
			data.append(sd.toXML(platform));
		}
		data.append("</super_days>");
		
		return data.toString();
	}
	
	
	public String toJson(String platform){
		
		userCodes=SuiShouActionUtil.getSuiShouActionUrl(platform, "tagStyleWeb", ParamConfig.userCodes);//用户订单列表url
//		if(status==2){
//			yugaoSuiShouAction.setUrl(SuiShouActionUtil.getSuishouWebUrl(ParamConfig.outTimePre));
//		}else{
//			yugaoSuiShouAction.setUrl(SuiShouActionUtil.getSuishouWebUrl(ParamConfig.tomorrowPre));
//		}
		yugaoSuiShouAction.setUrl(SuiShouActionUtil.getSuishouWebUrl(platform,ParamConfig.tomorrowPre));//不管时间到否都去明日预告页面 具体在该页判断
		StringBuffer sb=new StringBuffer();
		sb.append("{\"chaye\":{\"icon\":\""+chayeIcon+"\",\"wh_ratio\":\""+chayeWhRatio+"\","+chayeSuiShouAction.toJson()+"},");
		sb.append("\"yugao\":{\"disounting_title\":\""+yugaoDisountingTitle+"\",\"disounting_tips\":\""+yugaoDisountingTips+"\",\"icon\":\""+yugaoIcon+"\",\"wh_ratio\":\""+yugaoWhRatio+"\","+yugaoSuiShouAction.toJson()+"},");
		sb.append("\"status\":\""+status+"\",");
		sb.append("\"platform\":\""+platform+"\",");
		sb.append("\"user_codes_url\":\""+userCodes+"\",");
		if(list.size()>0){
			sb.append("\"super_day\":[");
		}
		for(SuperDay sd:list){
			sb.append("{"+sd.toJson(platform)+"},");
		}
		String str=sb.toString();
		if(str.length()>0){
			str=str.substring(0,str.length()-1);
		}
		if(list.size()>0){
			str=str+"]}";
		}else{
			str=str+"}";
		}
		
		return str;
	}
	
}
