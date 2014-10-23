/**
 * 
 */
package cn.youhui.tuiguang;

import cn.youhui.utils.NetManager;

/**
 * 米迪回调
 * @author lujiabin
 * @since 2014-9-10
 */
public class MiDiCallback implements TuiGuang{
	public boolean callback(String idfa,long activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = TuiGuangDao.getInstance().getMiDiCallback(idfa);
			String ret = NetManager.getInstance().getContent(url);
			System.out.println("midi return:::::::: " + ret);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
