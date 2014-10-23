/**
 * 
 */
package cn.youhui.tuiguang;

import cn.youhui.utils.NetManager;

/**
 * 有米回调
 * @author lujiabin
 * @since 2014-9-16
 */
public class YouMiCallback implements TuiGuang{
	public boolean callback(String idfa,long activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = TuiGuangDao.getInstance().getCallback(idfa);
			if(url != null && !"".equals(url)){
				String ret = NetManager.getInstance().getContent(url);
				System.out.println("YouMi return:::::::: " + ret);
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
