package cn.youhui.acapi.newuser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;


import cn.youhui.common.Config;
import cn.youhui.common.ParamConfig;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.ParamSignUtil;

/**
 * 抽奖活动
 * @author lijun
 * @since 2014-6-9
 */
public class McdActivityManager {
	
	public static final String MCD_COUPON_ID = "1641";
	public static final String MCD_BRAND_ID = "165";
	
	public static boolean purchaseMCD(String uid){
		boolean flag = purchase(uid, MCD_COUPON_ID, 1);
		if(flag){
			addFavCoupon(uid, MCD_COUPON_ID);
			addFavBrand(uid, MCD_BRAND_ID);
		}
		return flag;
	}
	
	/**
	 * 购买优惠券
	 * @param uid
	 * @param conponId
	 * @param num
	 * @return
	 */
	private static boolean purchase(String uid, String couponId, int num){
		boolean flag = false;
		String url = cn.youhui.common.Config.PURCHASE_URL;
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", uid);
		params.put("coupon", couponId);
		params.put("number", num+"");
		params.put("format", "json");
		params.put("sign", ParamSignUtil.getSign(params, ParamConfig.MD5Key));
		try {
			String ret = NetManager.getInstance().getContent(url, params);
			JSONObject jo = new JSONObject(ret);
			if(jo.has("resp")){
				JSONObject resp = jo.getJSONObject("resp");
				if(resp.has("head")){
					JSONObject head = resp.getJSONObject("head");
					if(head.has("status")){
						String s = head.getString("status");
						if("1000".equals(s)){
							flag = true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 自动关注品牌
	 * @param uid
	 * @param conponId
	 * @return
	 */
	private static void addFavBrand(String uid, String brandID){
		String url = Config.AddFavBrand_URL + "uid="+uid+"&brand_id="+brandID+"&type=add";
		try {
			NetManager.getInstance().getContent(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 自动收藏优惠信息
	 * @param uid
	 * @param conponId
	 * @return
	 */
	private static void addFavCoupon(String uid, String couponID){
		String url = Config.AddFavCoupon_URL + "uid="+uid+"&coupon_id="+couponID+"&type=add";
		try {
			String re = NetManager.getInstance().getContent(url);
			System.out.println(uid + "  addfavcoupon:::" + re);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 是否还有充足的优惠券
	 * @param conn
	 * @return
	 */
	public static boolean hasMcdCoupon(Connection conn){
		boolean flag = false;
		java.sql.PreparedStatement ps = null;
		try{
			String sql = "SELECT `number` FROM `youhui_purchase`.`coupon_info` where `couponid` =? and `tag` = 1;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, MCD_COUPON_ID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int num = rs.getInt("number");
				if(num > 0){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
}
