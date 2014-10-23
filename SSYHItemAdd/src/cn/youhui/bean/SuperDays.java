package cn.youhui.bean;

import java.util.List;

import com.google.gson.JsonObject;


public class SuperDays {

	
	public int id;
	public String chayeIcon;
	public String chayeWhRatio;
	public SuiShouAction chayeSuiShouAction;
	
	public String yugaoDisountingTitle;
	public String yugaoDisountingTips;
	public String yugaoIcon;
	public String yugaoWhRatio;
	public SuiShouAction yugaoSuiShouAction;
	
	public int status;
	
	public List<SuperDay> list;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	
	public String toXML(){
		
		SuiShouAction action1 = chayeSuiShouAction;
		SuiShouAction action2 = yugaoSuiShouAction;
		
		StringBuffer data=new StringBuffer();
		data.append("<super_days>");
		data.append("<chaye>" +
				"<icon>" +
					"<![CDATA["+chayeIcon+"]]>" +
				"</icon>" +
				"<wh_ratio>" +
					"<![CDATA["+chayeWhRatio+"]]>" +
				"</wh_ratio>" +
				action1.toXML()+
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
				action2.toXML()+
			"</yugao>");
		for(SuperDay sd:list){
			data.append(sd.toXML());
		}
		data.append("</super_days>");
		
		return data.toString();
	}
	
	
	public String toJson(){
		
		SuiShouAction action1 = chayeSuiShouAction;
		SuiShouAction action2 = yugaoSuiShouAction;
		
		StringBuffer sb=new StringBuffer();
		sb.append("{\"chaye\":{\"icon\":\""+chayeIcon+"\",\"wh_ratio\":\""+chayeWhRatio+"\","+action1.toJson()+"},");
		sb.append("\"yugao\":{\"disounting_title\":\""+yugaoDisountingTitle+"\",\"disounting_tips\":\""+yugaoDisountingTips+"\",\"icon\":\""+yugaoIcon+"\",\"wh_ratio\":\""+yugaoWhRatio+"\","+action2.toJson()+"},");
		sb.append("\"status\":\""+status+"\",");
		sb.append("\"super_day\":[");
		for(SuperDay sd:list){
			sb.append("{"+sd.toJson()+"},");
		}
		String str=sb.toString();
		if(str.length()>0){
			str=str.substring(0,str.length()-1);
		}
		str=str+"]}";
		return str;
	}
	
}
