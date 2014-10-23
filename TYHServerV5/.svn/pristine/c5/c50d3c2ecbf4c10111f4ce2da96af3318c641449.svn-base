package cn.youhui.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.youhui.bean.Announcement;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.dao.MySQLDBIns;


public class AppconfigUtils {

	public static String convertAppconfig(String content){		
		List<Announcement> list = getAllAnnouncementList();
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
				jso.put("dismiss_type", bean.getButtonControl());
				jso.put("isCanDismiss", bean.getIsCanDisMiss());
				jso.put("leftButtonName", bean.getLeftButtonName());
				jso.put("rightButtonName", bean.getRightButtonName());
				jso.put("leftButtonAction", new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(bean.getLeftButtonType(), String.valueOf(bean.getId()))).getUrl());
				jso.put("rightButtonAction", new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(bean.getRightButtonType(), String.valueOf(bean.getId()))).getUrl());
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
	
	
	public static List<Announcement> getAllAnnouncementList(){
		List<Announcement> list = new ArrayList<Announcement>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long time = System.currentTimeMillis();
		String sql = "SELECT * FROM youhui_datamining.tyh_announcement where  status = ? and end_time >"+time+"  ";

		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Announcement ac = new Announcement();
				ac = new Announcement();
				ac.setId(rs.getInt("id"));
				ac.setTitle(rs.getString("title"));
				ac.setContentUrl(rs.getString("content_url"));
				ac.setCreateTime(rs.getLong("create_time"));
				ac.setUpdateTime(rs.getLong("update_time"));
				ac.setStartTime(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				ac.setEndTime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				ac.setStatus(rs.getInt("status"));
				ac.setDelayTime(rs.getLong("delay_time"));
				ac.setLeftDismissType(rs.getInt("left_dismiss_type"));
				ac.setRightDismissType(rs.getInt("right_dismiss_type"));
				ac.setIsCanDisMiss(rs.getInt("isCanDismiss"));
				ac.setLeftButtonName(rs.getString("left_button_name"));
				ac.setRightButtonName(rs.getString("right_button_name"));
				ac.setLeftButtonAction(rs.getString("left_button_action"));
				ac.setRightButtonAction(rs.getString("right_button_action"));
				ac.setLeftButtonType(rs.getString("left_button_type"));
				ac.setRightButtonType(rs.getString("right_button_type"));	
				ac.setButtonControl(rs.getString("button_control"));
				list.add(ac);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static String getAppconfig(){
		String content = "";
		String sql = "select * from youhui_datamining.tyh_config where ckey = 'appconfig';";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				content = rs.getString("value");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return content;
	}
	
	public static String getDateTimeStr(long mills)
	{
		if (mills > 0l)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(new Date(mills));
			return dateStr;
		}
		else
		{
			return "";
		}
	}
	
}
