package cn.youhui.api.checkcode;

import java.util.Random;

import cn.youhui.common.X;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.JedisListManager;
import cn.youhui.utils.StringUtils;

/**
 * 验证码处理
 * @author lijun
 * @since 2014-6-22
 */
public class CheckCodeManager {

	private static final String CHECK_CODE_KEY = X.CachePrefix.CHECK_CODE;         //验证码
	private static final String SID_2_CHECK_CODE_KEY = X.CachePrefix.SID_2_CHECK_CODE;   
	
	private static final String SEP = ",";
	private static final int expireTime = 1000;    

	
	private static String getRandCheckCode(){
		long size = new JedisListManager(CHECK_CODE_KEY).size();
		System.out.println(size);
		long rand = new Random().nextInt((int)size);
		return new JedisListManager(CHECK_CODE_KEY).get(rand);
	}
	
	public static String getCheckCodeImg(String sid){
		String checkCode = getRandCheckCode();
		String key = SID_2_CHECK_CODE_KEY + sid;
		if(new JedisKeyManager(key).set(checkCode) != null){
			new JedisKeyManager(key).expire(expireTime);
			String[] code = checkCode.split(SEP);
			if(code != null && code.length == 2){
				return code[1];
			}
		} 
		return null;
	}
	
	/**
	 * 验证码是否匹配
	 * @return
	 */
	public static boolean checkCode(String sid, String code){
		String key = SID_2_CHECK_CODE_KEY + sid;
		String checkCode = new JedisKeyManager(key).get();
		if(!StringUtils.isEmpty(checkCode)){
			String[] codes = checkCode.split(SEP);
			if(codes != null && codes.length == 2 && code.equalsIgnoreCase(codes[0])){
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new JedisListManager(CHECK_CODE_KEY).clean());
		System.out.println(new JedisListManager(CHECK_CODE_KEY).add("2,http://static.etouch.cn/suishou/ad_img/1iqumc9nhg6.jpg"));
		System.out.println(new JedisListManager(CHECK_CODE_KEY).add("8,http://static.etouch.cn/suishou/ad_img/1iqumca6uza.jpg"));
		System.out.println(new JedisListManager(CHECK_CODE_KEY).add("3,http://static.etouch.cn/suishou/ad_img/1iqvrt57pba.jpg"));
		System.out.println(new JedisListManager(CHECK_CODE_KEY).add("3,http://static.etouch.cn/suishou/ad_img/1iqumca70dq.jpg"));
//		System.out.println(new JedisListManager(CHECK_CODE_KEY).getRange(0, 0));
		
		System.out.println(getRandCheckCode());
		
//		System.out.println(checkCode("123", "2907"));
	}
	
	
}
