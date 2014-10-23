package com.netting.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.PushMessage;
import com.netting.cache.dao.Admin_IphoneDevToken_Cache_DAO;
import com.netting.cache.dao.Admin_Message_Android_Cache_DAO;
import com.netting.dao.admin.Admin_PushMessage_DAO;
import com.netting.message.ChooseUserManager;
import com.netting.message.IOS_Msg_Manager;
import com.netting.message.IOS_Msg_Manager_New;

/**
 * 消息发送任务类
 * @author YAOJIANBO
 *
 */
public class SendMsgJob extends Thread
{
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( SendMsgJob.class );
	
	/**
	 * 是否按条件发送0:全部发送
	 */
	private String range;
	
	private PushMessage msg;
	
	private String platform;
	
	private String formula;
	
	private String query;
	
	public SendMsgJob(String range, PushMessage msg, String platform,
			String formula, String query) 
	{
		this.range = range;
		this.msg = msg;
		this.platform = platform;
		this.formula = formula;
		this.query = query;
	}

	@Override
	public void run() 
	{
		Map<String, String> msgvalue = null;
    	if("0".equals(range))
		{
			if(isIos(platform))
			{
				Map<String, String> devTokenlist = Admin_IphoneDevToken_Cache_DAO.getAllDevTokenWithUID();
				if (platform.indexOf("iphone") > -1)
				{
					IOS_Msg_Manager.sendAll(devTokenlist, msg, "iphone_etouch");
					IOS_Msg_Manager.sendAll(devTokenlist, msg, "iphone_taogeili");
				}
				else if (platform.indexOf("ipad") > -1)
				{
					IOS_Msg_Manager.sendAll(devTokenlist, msg, platform);
				}
			}
			else if(isAndroid(platform))
			{
				Admin_Message_Android_Cache_DAO.addMsgToAll(msg);
			}
			else
			{
				Map<String, String> devTokenlist = Admin_IphoneDevToken_Cache_DAO.getAllDevTokenWithUID();
				IOS_Msg_Manager.sendAll(devTokenlist, msg, platform);
				Admin_Message_Android_Cache_DAO.addMsgToAll(msg);
			}
			
			msgvalue = new HashMap<String, String>();
			msgvalue.put("00000000", "");
			Admin_PushMessage_DAO.addUidToMsg(msg.getpId(), msgvalue, msg.getStarttime());
		}
		else
		{
			try
			{
				msgvalue = ChooseUserManager.getResult(formula, query);
				if(msgvalue != null && msgvalue.size() > 0)
				{
					if(isIos(platform)) 
					{
						if (platform.indexOf("iphone") > -1)
						{
							IOS_Msg_Manager_New.sendMsg(msgvalue, msg, "iphone_etouch");
							IOS_Msg_Manager_New.sendMsg(msgvalue, msg, "iphone_taogeili");
						}
						else if (platform.indexOf("ipad") > -1)
						{
							IOS_Msg_Manager_New.sendMsg(msgvalue, msg, platform);
						}
					}
					else if(isAndroid(platform))
					{
						Admin_Message_Android_Cache_DAO.addMsgWithValue(msg, msgvalue);
					}
					else
					{
						IOS_Msg_Manager_New.sendMsg(msgvalue, msg, platform);
						Admin_Message_Android_Cache_DAO.addMsgWithValue(msg, msgvalue);
					}
					Admin_PushMessage_DAO.addUidToMsg(msg.getpId(), msgvalue, msg.getStarttime());
				}
				ChooseUserManager.ubeanmap.clear();
			}
			catch (Exception e)
			{
				logger.error("Admin_Message_Manager_Action.sendMsg() error:", e);
			}
		}
    	
	}
	
	private static boolean isIos(String platform)
    {
		if(platform != null && ( platform.indexOf("iphone") > -1 || platform.indexOf("ipad") > -1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private static boolean isAndroid(String platform)
	{
		if(platform != null && platform.indexOf("android") > -1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
}
