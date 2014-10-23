/**
 * 
 */
package cn.youhui.api.login;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import cn.youhui.dao.AdwoThreadDao;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.WebUtil;

/**
 * 
 * @author lujiabin
 * @since 2014-8-27
 */
public class AdwoThread2 implements Runnable{
	private String ssuid;
	private long activatetime;
	private HttpServletRequest request;
	
	public AdwoThread2(HttpServletRequest request,long activatetime,String ssuid) {
		this.request = request;
		this.activatetime = activatetime;
		this.ssuid = ssuid;
	}
	
	public void run() {
		try {
//			String adalias = "suishouyouhuiiphonejfq"; 
			String idfa = ParamUtil.getParameter(request, "idfa");
//			String activateip = WebUtil.getIpAddr(request);
//			String code = ParamUtil.getParameter(request, "version");
			String openudid = ParamUtil.getParameter(request, "openudid");
			
			if(idfa == null || "".equals(idfa)) {
				return ;
			}
			
			boolean flag =  AdwoThreadDao.getInstance().getMacAddress(idfa);
			System.out.println("openddd:" + openudid + "...........idfa:" + idfa);
			//status说明：0：超时，1：调了安沃接口，2：信息不匹配，3：未登录,4：未调安沃借口
			if(flag) {
				if(isOntime(idfa, activatetime)) {
					AdwoThreadDao.getInstance().update(activatetime, ssuid, 3, idfa);
				} else {
					AdwoThreadDao.getInstance().update(activatetime, ssuid, 0, idfa);
				}
			}
			
			
			boolean flag2 =  AdwoThreadDao.getInstance().getMacAddress4midi(idfa);
			if(flag2){
				if(isOntime4midi(idfa, activatetime)) {
					AdwoThreadDao.getInstance().update4midi(activatetime, ssuid, 3, idfa);
				} else {
					AdwoThreadDao.getInstance().update4midi(activatetime, ssuid, 0, idfa);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isOntime(String idfa,long activatetime) {
		long createTime = AdwoThreadDao.getInstance().getCreateTime(idfa);
		if((activatetime >= createTime) && (activatetime-createTime <= 86400000*3)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isOntime4midi(String idfa,long activatetime) {
		long createTime = AdwoThreadDao.getInstance().getCreateTime4midi(idfa);
		if((activatetime >= createTime) && (activatetime-createTime <= 86400000*3)) {
			return true;
		} else {
			return false;
		}
	}

//	public void adwo(String idfa,String adalias,String code,String openudid,String activateip){
//		try{
//			int number = new Random().nextInt(10)+1;
//			//status说明：0：超时，1：调了安沃接口，2：信息不匹配，3：未登录,4：未调安沃借口
//			if(number > 8) {
//				AdwoThreadDao.getInstance().update2(activatetime, ssuid, 4, idfa);
//			}else{
//				boolean isSuccess = AdwoThreadDao.getInstance().update2(activatetime, ssuid, 1, idfa);
//				if(isSuccess) {
//					String url = "http://offer.adwo.com/iofferwallcharge/ia?adalias="+adalias+"&code="+code+"&idfa="+idfa+"&openudid="+openudid+"&activateip="+activateip+"&acts="+activatetime;
//					String ret = NetManager.getInstance().getContent(url);
//					System.out.println("adwo return:::::::: " + ret);
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	public void midi(String idfa){
//		int number = new Random().nextInt(10)+1;
//		//status说明：0：超时，1：调了安沃接口，2：信息不匹配，3：未登录,4：未调安沃借口
//		if(number > 8) {
//			AdwoThreadDao.getInstance().update24midi(activatetime, ssuid, 4, idfa);
//		}else{
//			boolean isSuccess2 = AdwoThreadDao.getInstance().update24midi(activatetime, ssuid, 1, idfa);
//			if(isSuccess2) {
//				String url = AdwoThreadDao.getInstance().getCallback(idfa);
//				if(!url.equals("")){
//					String ret = NetManager.getInstance().getContent(url);
//					System.out.println("midi return:::::::: " + ret);
//				}
//			}
//		}
//	}
	
}
