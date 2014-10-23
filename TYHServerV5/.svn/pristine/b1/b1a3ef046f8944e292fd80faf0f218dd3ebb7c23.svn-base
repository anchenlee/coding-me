/**
 * 
 */
package cn.youhui.tuiguang;

import cn.youhui.utils.NetManager;

/**
 * 安沃回调
 * @author lujiabin
 * @since 2014-9-10
 */
public class AdWoCallback implements TuiGuang{
	
	public boolean callback(String idfa,long activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = "http://offer.adwo.com/iofferwallcharge/ia?adalias="+adalias+"&code="+code+"&idfa="+idfa+"&openudid="+openudid+"&activateip="+activateip+"&acts="+activatetime;
			String ret = NetManager.getInstance().getContent(url);
			System.out.println("adwo return:::::::: " + ret);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
