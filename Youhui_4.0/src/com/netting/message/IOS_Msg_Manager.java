package com.netting.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javapns.Push;
import javapns.back.PushNotificationManager;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.PayloadPerDevice;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;

import com.netting.bean.PushMessage;
import com.netting.conf.SysConf;
import com.netting.dao.admin.Admin_PushMessage_DAO;

/**
 * IOS平台消息发送
 * @author YAOJIANBO
 *
 */
public class IOS_Msg_Manager 
{
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( IOS_Msg_Manager.class );
	
	public static String p12File_Etouch = "WEB-INF/aps_taoyouhui_etouch_release.p12";
	public static String p12File_Taogeili = "WEB-INF/aps_taoyouhui_taogeili_release.p12";
	public static String p12File_Ipad = "WEB-INF/aps_taoyouhui_pad_release.p12";
	
	private static String password = "19880109";
	
	private static boolean production = true;
	
	private static int threads = 30;
	
	PushNotificationManager pushManager = PushNotificationManager.getInstance();
	
	/**
	 * 将消息发送至所有用户终端
	 * @param deviceTokens
	 * @param msg
	 * @param platform
	 */
	public static void sendAll(Map<String, String> deviceTokens, PushMessage msg, String platform)
	{  
		if(deviceTokens == null || deviceTokens.size() <= 0)
		{
			return;
		}
		else
		{
			List<PayloadPerDevice>	payloadDevicePairs = new ArrayList<PayloadPerDevice>();
			for (Map.Entry<String, String> m : deviceTokens.entrySet())
			{
				Device device = new BasicDevice();  
				device.setToken(m.getValue().replaceAll(" *", ""));
				PushNotificationPayload payload = PushNotificationPayload.alert(msg.getContent()); 
				try 
				{
					payload.addBadge(1);
					payload.addSound("default");
					payload.addCustomDictionary("code", msg.getCode());
					payload.addCustomDictionary("actiontype", msg.getAction().getActionType());
					payload.addCustomDictionary("actionvalue", getAddUID(msg, m.getKey()));	
				} 
				catch (JSONException e)
				{
					continue;
				}
				
				PayloadPerDevice ppd = new PayloadPerDevice(payload, device);
				payloadDevicePairs.add(ppd);
			}
			
			String p12File = SysConf.project_abs_path + p12File_Taogeili;
			if(platform.indexOf("etouch") > -1)
			{
				p12File = SysConf.project_abs_path + p12File_Etouch;
			}
			else if(platform.indexOf("ipad") > -1)
			{
				p12File = SysConf.project_abs_path + p12File_Ipad;
			}
			
			try 
			{
				PushedNotifications nts = Push.payloads(p12File, password, production, threads, payloadDevicePairs);
				
				List<PushedNotification> notifications = nts.getSuccessfulNotifications();
				for (PushedNotification notification : notifications)
				{
					String deviceToken = notification.getDevice().getToken();
					Admin_PushMessage_DAO.addPushState(msg.getpId(), msg.getTitle(), deviceToken, "iphone", 1);
				}
				
				notifications = nts.getFailedNotifications();
				for (PushedNotification notification : notifications)
				{
					String deviceToken = notification.getDevice().getToken();
					Admin_PushMessage_DAO.addPushState(msg.getpId(), msg.getTitle(), deviceToken, "iphone", 2);
				}
			} 
			catch (Exception e)
			{
				logger.error("IOS_Msg_Manager.sendAll() error:", e);
			}  
		}  
	}
	
	/**
	 * 为话费充值增加unid参数，便于充话费返集分宝
	 */
	private static String getAddUID(PushMessage msg, String uid)
	{
		if(msg != null && msg.getAction() != null && msg.getAction().getActionValue().indexOf("http:") > -1)
		{
			if( msg.getAction().getActionValue().indexOf("?") > -1)
			{
				return msg.getAction().getActionValue()+ "&unid=" + uid;
			}
			else
			{
				return msg.getAction().getActionValue()+ "?unid=" + uid;
			}
		}
		else
		{
			return msg.getAction().getActionValue();
		}
	}
	
}
