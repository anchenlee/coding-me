package cn.youhui.bean;

public class SuiShouAction{
	
	private String url = "";
	
	public SuiShouAction(){
		
	}
	
	public SuiShouAction(String url){
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toXML(){
		return new StringBuffer().append("<ss_action>")
				.append("<url><![CDATA[").append(url).append("]]></url>")
				.append("</ss_action>").toString();
	}
	
	public String toJson(){
		return new StringBuffer().append("\"action\":{")
				.append("\"action_url\":\"").append(url)
				.append("\"}").toString();
	}
}
