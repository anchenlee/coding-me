package com.netting.message;

import java.util.Map;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import com.netting.bean.PushMessage;
import com.netting.cache.dao.Admin_IphoneDevToken_Cache_DAO;
import com.netting.conf.SysConf;
import com.netting.dao.admin.Admin_PushMessage_DAO;

/**
 * IOS平台消息发送(按条件发送)
 * @author YAOJIANBO
 *
 */
public class IOS_Msg_Manager_New 
{
	public static String p12File_Etouch = "WEB-INF/aps_taoyouhui_etouch_release.p12";
	public static String p12File_Taogeili = "WEB-INF/aps_taoyouhui_taogeili_release.p12";
	public static String p12File_Ipad = "WEB-INF/aps_taoyouhui_pad_release.p12";
	
	private static String host = "gateway.push.apple.com";
	
	private static int port = 2195;
	
	private static String password = "19880109";
	
	public static PushNotificationManager pushManager = PushNotificationManager.getInstance();
	
	
	public static void sendMsg(Map<String, String> msgvalue, PushMessage msg, String platform) throws Exception
	{
		String p12File = SysConf.project_abs_path + p12File_Taogeili;
		if(platform.indexOf("etouch") > -1)
		{
			p12File = SysConf.project_abs_path + p12File_Etouch;
		}
		else if(platform.indexOf("ipad") > -1)
		{
			p12File = SysConf.project_abs_path + p12File_Ipad;
		}
		
		pushManager.initializeConnection(host, port, p12File, password, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
		
		for (Map.Entry<String, String> m : msgvalue.entrySet()) 
		{ 
			String devToken = Admin_IphoneDevToken_Cache_DAO.getDevToken(m.getKey());
			if(devToken != null)
			{
				msg.changeContent(m.getValue());
				adduid(msg, m.getKey());
				
				PayLoad payLoad = new PayLoad();
				
				payLoad.addAlert(msg.getContent());//push的内容
				payLoad.addBadge(1);
				payLoad.addSound("default");//铃音
				payLoad.addCustomDictionary("code", msg.getCode());
				payLoad.addCustomDictionary("actiontype", msg.getAction().getActionType());
				payLoad.addCustomDictionary("actionvalue", msg.getAction().getActionValue());
				
				pushManager.addDevice("iphone", devToken);
				Device device = pushManager.getDevice("iphone");
				pushManager.sendNotification(device, payLoad);
				pushManager.removeDevice("iphone"); 
				
				Admin_PushMessage_DAO.addPushState(msg.getpId(), msg.getTitle(), devToken, "iphone", 1);
			}
		}
		
		pushManager.stopConnection();	    
	}
	
	/**
	 * 为话费充值增加unid参数，便于充话费返集分宝
	 */
	private static void adduid(PushMessage msg, String uid)
	{
		if(msg != null && msg.getAction() != null 
				&& msg.getAction().getActionValue().indexOf("http:") > -1)
		{
			if( msg.getAction().getActionValue().indexOf("?") > -1)
			{
				msg.getAction().setActionValue(msg.getAction().getActionValue() + "&unid=" + uid);
			}
			else
			{
				msg.getAction().setActionValue(msg.getAction().getActionValue() + "?unid=" + uid);
			}
		}
	}
}
