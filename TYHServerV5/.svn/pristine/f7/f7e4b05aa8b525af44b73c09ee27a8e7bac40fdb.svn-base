/**
 * 
 */
package cn.youhui.tuiguang;

import cn.youhui.utils.NetManager;

/**
 * 掌上互动回调
 * @author lujiabin
 * @since 2014-10-15
 */
public class ZSHDCallback implements TuiGuang{
	public boolean callback(String idfa,long activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = TuiGuangDao.getInstance().getCallback(idfa);
			if(url != null && !"".equals(url)){
				String ret = NetManager.getInstance().getContent(url);
				System.out.println("ZSHD return:::::::: " + ret);
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
