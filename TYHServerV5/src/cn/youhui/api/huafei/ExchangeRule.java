package cn.youhui.api.huafei;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRule {

	private static Map<Integer, Integer> rmap = new HashMap<Integer, Integer>();

	static{
		rmap.put(10, 900);
		rmap.put(20, 1800);
		rmap.put(30, 2700);
		rmap.put(50, 4500);
		rmap.put(100, 9000);
//		rmap.put(10, 880);
//		rmap.put(20, 1760);
//		rmap.put(30, 2640);
//		rmap.put(50, 4400);
//		rmap.put(100, 8800);
	}
	
	/**
	 * 获取需要的集分宝个数
	 * @param num
	 * @return
	 * @throws Exception 
	 */
	public static int getNeedJfbNum(int num){
		int ret = -1;
		Integer re = rmap.get(num);
		if(re == null){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			ret = re;
		}
		return ret;
	}
}
