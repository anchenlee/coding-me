package cn.youhui.api.login;

import cn.youhui.manager.UserDevtokenDAO;
import cn.youhui.ramdata.IphoneDevTokenCacher;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.VersionUtil;

/**
 * 注册后推送签到页面
 * @author lijun
 * @since 2014-9-16
 */
public class PushAfRegisterThread extends Thread{
	
	private String uid = "";
	
	private String openudid = "";
	
	private String version = "";
	
	public PushAfRegisterThread(String uid, String openudid, String version){
		this.uid = uid;
		this.openudid = openudid;
		this.version = version;
	}

	public void run() {
		if(openudid == null || "".equals(openudid)){
			return;
		}
		String devtoken = UserDevtokenDAO.getInstance().getDevtokenByOpenudid(openudid);
		if(devtoken != null && !"".equals(devtoken)){                  //存储 devtoken
			IphoneDevTokenCacher.getInstance().addDevToken(devtoken);
			if(VersionUtil.isHigher("3.4.0", version)){
				IphoneDevTokenCacher.getInstance().addDevTokenv2(devtoken);
			}
			if(uid != null && !"".equals(uid)){
				IphoneDevTokenCacher.getInstance().add(uid, devtoken);
				if(VersionUtil.isHigher("3.4.0", version)){
					IphoneDevTokenCacher.getInstance().addv2(uid, devtoken);
				}
				String url = "http://172.16.71.53:8080/MSGPush/MSGPushById?id=1410785324181&formula=uid=" + uid;
				try {
					NetManager.getInstance().getContent(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
