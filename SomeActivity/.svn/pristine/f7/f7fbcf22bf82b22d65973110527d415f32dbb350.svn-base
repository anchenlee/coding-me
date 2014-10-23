package cn.suishou.some.response;

import cn.suishou.some.response.Enums.ActionStatus;


public class HeaderBean extends BaseBean{
	private ActionStatus actionStatus;
	
	public HeaderBean(ActionStatus actionStatus){
		this.actionStatus = actionStatus;
	}
	
	public ActionStatus getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(ActionStatus actionStatus) {
		this.actionStatus = actionStatus;
	}

	@Override
	public String toXML() {
		return "<head>" +
				"<status>" +
				actionStatus.getCode() +
				"</status>" +
				"<desc>" +
				actionStatus.getDesc() +
				"</desc>" +
				"</head>";
	}
	
	public String toJson(){
		return new StringBuffer().append("\"head\":{\"status\":\"").append(actionStatus.getCode()).append("\",")
			.append("\"desc\":\"").append(actionStatus.getDesc()).append("\"}").toString();
	}
	
}
