package cn.youhui.bean;

import java.sql.Connection;
import java.util.List;

import net.sf.json.JSONArray;

import cn.youhui.platform.db.SQL;

import com.google.gson.JsonObject;

public class SuperDay {

	public String title;
	public String tips;
	public int status;
	public List<SuperDiscountBean> list;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<SuperDiscountBean> getList() {
		return list;
	}
	public void setList(List<SuperDiscountBean> list) {
		this.list = list;
	}

	public String toXML(){
		StringBuffer data=new StringBuffer();
		data.append("<super_day>");
		data.append("<title>").append(title).append("</title>");
		data.append("<tips><![CDATA[").append(tips).append("]]></tips>");
		data.append("<status>").append(status).append("</status>");
		data.append("<items>");
		for(SuperDiscountBean sb:list){
			data.append(sb.toXML());
		}
		data.append("</items>");
		data.append("</super_day>");
		return data.toString();
	}
	
	public String toJson(){
		
		StringBuffer sbf=new StringBuffer();
		sbf.append("\"title\":\""+title+"\",\"tips\":\""+tips+"\",\"status\":\""+status+"\",\"items\":[");
		
		StringBuffer ss=new StringBuffer();
		
		Connection conn=SQL.getInstance().getConnection();
		for(SuperDiscountBean sb:list){
			ss.append(sb.toJson(conn)+",");
		}
		SQL.getInstance().release(null, conn);
		
		String str=ss.toString();
		if(str.length()>0){
			str=str.substring(0,str.length()-1);
		}
		sbf.append(str+"]");
		
		
		return sbf.toString();
	}
	
}
