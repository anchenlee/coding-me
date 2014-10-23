package cn.youhui.cache.dao;


import java.util.List;

import net.sf.json.JSONArray;
import cn.youhui.platform.config.param;
import cn.youhui.platform.jedis.JedisListManager;


/**
 * 验证码处理
 * @author lijun
 * @since 2014-6-22
 */
public class CheckCodeManager {

	private static final String CHECK_CODE_KEY = param.CHECK_CODE;         //验证码
	
	public static void add(String val){
		System.out.println("--------------->>>>"+new JedisListManager(CHECK_CODE_KEY).add(val));//xx:xxxxxxx.jpg
	}
	
	public static String getAll(){
		JSONArray ja=new JSONArray();
		List<String> l=new JedisListManager(CHECK_CODE_KEY).getAll();
		for(String s:l){
			ja.add(s);
		}
		return ja.toString();
	}
	
	public static void del(String val){
		new JedisListManager(CHECK_CODE_KEY).del(val);
	}
}