package com.netting.cache.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.Announcement;
import com.netting.dao.admin.AdminAnnouncementDAO;
import com.netting.dao.admin.AdminAppconfigDAO;
import com.netting.redis.executor.JedisKeyManager;

public class AdminAppconfigCacheDAO {

	private static String APPCONFIG_DATA = "client.appconfig";
	
	public static void reloadAppconfig(){
		String content = "";
		content = AdminAppconfigDAO.getAppconfig();
		content = convertAppconfig(content);
		JedisKeyManager.set(APPCONFIG_DATA, content);
	}
	
	public static String convertAppconfig(String content){		
		List<Announcement> list = AdminAnnouncementDAO.getAllAnnouncementList();
		try {
			JSONArray jsa = new JSONArray();
			JSONObject configJSO = new JSONObject("{"+content+"}");
			JSONObject appcon = configJSO.getJSONObject("appconfig");
			int i = 0;
			for(Announcement bean : list){			
				JSONObject jso = new JSONObject();
				jso.put("item_id", bean.getId());
				jso.put("title", bean.getTitle());
				jso.put("contentUrl", bean.getContentUrl());
				jso.put("startTime", bean.getStartTime());
				jso.put("endTime", bean.getEndTime());
				jso.put("delayTime", bean.getDelayTime());
				jso.put("dismiss_type", bean.getShowType());
				jso.put("isCanDismiss", bean.getDelType());
				jso.put("leftButtonName", bean.getLeftButton().getName());
				jso.put("rightButtonName", bean.getRightButton().getName());
				jso.put("leftButtonAction", bean.getLeftButton().getSsac().getUrl());
				jso.put("rightButtonAction", bean.getRightButton().getSsac().getUrl());
				jsa.put(i,jso);
			}
			appcon.put("announcement", jsa);
			configJSO.put("appconfig", appcon);
			return configJSO.toString().substring(1,configJSO.toString().length()-1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	public static void main(String[] args) {
		System.out.println(convertAppconfig(""));
	}
	
}
