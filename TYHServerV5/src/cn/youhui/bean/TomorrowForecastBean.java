package cn.youhui.bean;

import java.util.List;

import cn.youhui.common.ParamConfig;

public class TomorrowForecastBean {

	public String title;
	public String tips;
	public List<SuperDiscountBean> list;
	public String chayeIcon;
	public String chayeWhRatio;
	public SuiShouAction chayeSsActionUrl;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public List<SuperDiscountBean> getList() {
		return list;
	}
	public void setList(List<SuperDiscountBean> list) {
		this.list = list;
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
	public SuiShouAction getChayeSsActionUrl() {
		return chayeSsActionUrl;
	}
	public void setChayeSsActionUrl(SuiShouAction chayeSsActionUrl) {
		this.chayeSsActionUrl = chayeSsActionUrl;
	}
	public String toXml(String platform){
		StringBuffer data=new StringBuffer();
		SuiShouAction action1 = chayeSsActionUrl;
		data.append("<chaye>" +
				"<icon>" +
					"<![CDATA["+chayeIcon+"]]>" +
				"</icon>" +
				"<wh_ratio>" +
					"<![CDATA["+chayeWhRatio+"]]>" +
				"</wh_ratio>" +
				action1.toXML()+
			"</chaye>");
		data.append("<title><![CDATA[").append(title).append("]]></title>");
		data.append("<tips><![CDATA[").append(tips).append("]]></tips>");
		data.append("<items>");
		for(int i=0;i<list.size();i++){
			SuperDiscountBean bean=list.get(i);
			data.append(bean.toXML(platform));
		}
		data.append("</items>");
		
		return data.toString();
	}
	
	public String toJson(String platform){
		StringBuffer data=new StringBuffer();
		SuiShouAction action1 = chayeSsActionUrl;
		data.append("{\"chaye\":{\"icon\":\""+chayeIcon+"\",\"wh_ratio\":\""+chayeWhRatio+"\","+action1.toJson()+"},\"title\":\""+title+"\",");
		data.append("\"tips\":\""+tips+"\",");
		data.append("\"items\":[");
		for(int i=list.size()-1;i>=0;i--){
			SuperDiscountBean bean=list.get(i);
			data.append(bean.toJson(platform)+",");
		}
		String str=data.toString();
		if(list.size()>0){
			str=str.substring(0,str.length()-1);
		}
		str=str+"]}";
		return str;
	}
	
}
