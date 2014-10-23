package cn.youhui.api.huafei;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cn.youhui.common.Config;
import cn.youhui.common.ParamConfig;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.ParamSignUtil;

/**
 * 进行兑换 充值操作
 * @author lijun
 * @since 2014-8-30
 */
public class HuafeiCZManager {

	private static Map<Integer, String> rmap = new HashMap<Integer, String>();

	static{
		rmap.put(10, "10000001");
		rmap.put(20, "10000002");
		rmap.put(30, "10000003");
		rmap.put(50, "10000004");
		rmap.put(100, "10000005");
	}
	
	/**
	 * 充值扣钱   
	 * @param uid
	 * @param num
	 * @return  0异常失败  1成功  2余额不足  
	 */
	public static String[] buyandpay(String uid, int num){
		String ret[] = {"0",""};
		Map<String,String> map=new HashMap<String,String>();
		String coup = rmap.get(num);
		if(coup == null){
			return ret;
		}
		map.put("coupon", coup);
		map.put("user", uid);
		map.put("format", "json");
		map.put("number", "1");
		String sign = ParamSignUtil.getSign(map, ParamConfig.MD5Key);
		map.put("sign", sign);
		try {
			String re = NetManager.getInstance().getContent(Config.PURCHASE_URL, map);
			if(re != null){
				JSONObject rejo = new JSONObject(re);
				if(rejo.has("resp")){
					JSONObject resp = rejo.getJSONObject("resp");
					if(resp.has("head")){
						JSONObject head = resp.getJSONObject("head");
						if(head.has("status")){
							String status = head.getString("status");
							if("1013".equals(status)){
								System.out.println("10000");
							}else if("1000".equals(status)){
								ret[0] = "1";
								if(resp.has("data")){
									JSONObject data = resp.getJSONObject("data");
									if(data.has("orderid")){
										String orderId = data.getString("orderid");
										if(orderId != null && !"".equals(orderId)){
											ret[1] = orderId;
										}
									}
								}
							}else if("1019".equals(status)){
								ret[0] = "2";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(buyandpay("108612506", 10)[1]);
	}
}
