/**
 * 
 */
package cn.youhui.tuiguang;

import java.net.URLDecoder;

import cn.youhui.utils.NetManager;

/**
 * 易积分回调
 * @author lujiabin
 * @since 2014-9-24
 */
public class YiJiFenCallback implements TuiGuang{
	public boolean callback(String idfa,long activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = TuiGuangDao.getInstance().getCallback(idfa);
			String ret = NetManager.getInstance().getContent(URLDecoder.decode(url,"UTF-8"));
			System.out.println("yijifen return:::::::: " + ret);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
