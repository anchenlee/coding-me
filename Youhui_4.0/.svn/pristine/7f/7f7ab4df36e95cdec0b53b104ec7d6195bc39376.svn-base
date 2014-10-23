package com.netting.bean;

public class Action 
{
	private String actionType = "";
	private String actionValue = "";
	
	public Action()
	{		
		super();
	}
	
	public Action(String actionType, String actionValue)
	{
		this.actionType = actionType;
		this.actionValue = actionValue;
	}
	
	public String getActionType() {
		return actionType;
	}
	
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	public String getActionValue() {
		return actionValue;
	}
	
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
	
	public String toXML()
	{
		return new StringBuffer().append("<action>")
				.append("<actiontype>").append(actionType).append("</actiontype>")
				.append("<actionvalue><![CDATA[").append(actionValue).append("]]></actionvalue>")
				.append("</action>").toString();
	}
	
	public String toJson()
	{
		return new StringBuffer().append("\"action\":{")
				.append("\"actiontype\":\"").append(actionType)
				.append("\",\"actionvalue\":\"").append(actionValue)
				.append("\"}").toString();
	}
}
