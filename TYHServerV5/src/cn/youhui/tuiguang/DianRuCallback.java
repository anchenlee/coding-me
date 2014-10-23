/**
 * 
 */
package cn.youhui.tuiguang;

import cn.youhui.utils.NetManager;

/**
 * 点入回调
 * @author lujiabin
 * @since 2014-9-11
 */
public class DianRuCallback implements TuiGuang{
	public boolean callback(String idfa,long activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = TuiGuangDao.getInstance().getCallback(idfa);
			if(url != null && !"".equals(url)){
				String ret = NetManager.getInstance().getContent(url);
				System.out.println("DianRu return:::::::: " + ret);
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
