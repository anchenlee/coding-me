/**
 * 
 */
package cn.youhui.tuiguang;

import cn.youhui.utils.NetManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 多盟回调
 * @author lujiabin
 * @since 2014-9-22
 */
public class DomobCallback implements TuiGuang{
	
	public boolean callback(String idfa,long activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		String appid = "852838221";
		String key = "f5517517d6e29f895e356980b07f0818";
		try{
			String[] info = TuiGuangDao.getInstance().getDomobInfo(idfa);
			String mac = parseMac(info[0]);
			String sign = getDomobSign(appid, mac, "", idfa, "", key);
			
			String url = "http://e.domob.cn/track/ow/api/postback?appId="+appid+"&udid="+mac+"&ifa="+idfa+"&acttime="+parseTime(Long.toString(activatetime))+"&sign="+sign+"&ip="+activateip+"&appVersion="+code+"&clktime="+parseTime(info[1]);
			String ret = NetManager.getInstance().getContent(url);
			System.out.println("domob return:::::::: " + ret);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 获取多盟签名
	 * @param appid
	 * @param udid
	 * @param ma
	 * @param ifa
	 * @param oid
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getDomobSign (String appid, String udid, String ma, String ifa, String oid, String key) throws NoSuchAlgorithmException {
		char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f' };
		String s = appid + "," + udid + "," + ma + "," + ifa + "," + oid + "," + key;
		byte[] strTemp = s.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		int j = md.length;
		char[] str = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[(k++)] = HEX_DIGITS[(byte0 >>> 4 & 0xF)];
			str[(k++)] = HEX_DIGITS[(byte0 & 0xF)];
		}
		return new String(str);
	}
	
	public static String parseMac(String mac){
		return mac.substring(0, 2)+":"+mac.substring(2, 4)+":"+mac.substring(4, 6)+":"+mac.substring(6, 8)+":"+mac.substring(8, 10)+":"+mac.substring(10, 12);
	}
	
	public static String parseTime(String time){
		return time.substring(0, time.length()-3);
	}
}
