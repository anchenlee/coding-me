package cn.suishou.some.luckac;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

import cn.suishou.some.bean.User;
import cn.suishou.some.config.Config;
import cn.suishou.some.dao.TradeReportDAO;
import cn.suishou.some.dao.UserDAO;
import cn.suishou.some.db.SQL;
import cn.suishou.some.util.NetManager;
import cn.suishou.some.util.ParamSignUtil;
import cn.suishou.some.util.StringUtils;

/**
 * 抽奖活动
 * @author lijun
 * @since 2014-6-9
 */
public class McdActivityManager {

	private static final int LIMIT_UID_TIMES = 2;       //每个用户限制的中奖次数
	private static final int LIMIT_IMEI_TIMES = 2;      //每个设备限制的中奖次数
	private static final int LIMIT_IP_TIMES = 5;         //每个IP限制的中奖次数
	
	/**
	 * 参加 麦当劳10元券 抽奖活动
	 * @param uid
	 * @return
	 */
	public static int joinMcdLuckAc(String uid, String ip){
		int flag = 0;
		Connection conn = null;
		try{
			conn = SQL.getInstance().getConnection();
			int hasTimes = LuckAcTimeDAO.getInstance().hasEnoughTime(uid, LuckActivity.MCD10, conn);
			if(hasTimes == 0){
				User user = UserDAO.getInstance().getUser(uid, conn);
				boolean isOldUser = false;
				long t140510 = 1399651200000l;
				if(user.getActiveTime() < t140510){      //老用户
					isOldUser = true;
				}
				boolean iszj = isMcdZJ(user, ip, isOldUser, conn);
				LuckAcRecordDAO.getInstance().add(uid, LuckActivity.MCD10, iszj, ip,user.getImei(),isOldUser, conn);
				addFavCoupon(uid, "165");
				if(iszj){
					if(purchase(uid, LuckActivity.MCD10.couponId, 1)){
						flag = 2;						
					}
				}else{
					flag = 3;
				}
			}else{
				flag = 100 + hasTimes;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return flag;
	}
	
	/**
	 * 参加活动，是否中奖
	 * @param user
	 * @param ip
	 * @param conn
	 * @return
	 */
	private static boolean isMcdZJ(User user, String ip, boolean isOld, Connection conn){
		boolean flag = false;
		if(StringUtils.isEmpty(user.getImei())){
			return flag;
		}
		int haszjtime = LuckAcRecordDAO.getInstance().getZJTime(user.getUid(), LuckActivity.MCD10.activityId, conn);
		if(haszjtime >= LIMIT_UID_TIMES){
			return flag;
		}
		double rate = 0.2;
		if(isOld){   
			rate = rate * 0.1;
		}
		rate = rate / Math.pow(2, haszjtime);
		if(isZJ(rate)){        //先抽一盘，中了再进行检验
			
			if(isOld && !TradeReportDAO.getInstance().hasTrade(user.getUid(), conn)){            //没有订单的老用户  不中奖
				return flag;
			}
			
			int iptimes = LuckAcRecordDAO.getInstance().getIPZJTime(ip, LuckActivity.MCD10.activityId, conn);
			if(iptimes >= LIMIT_IP_TIMES){
				return flag;
			}
			int imeitimes = LuckAcRecordDAO.getInstance().getImeiZJTime(user.getImei(), LuckActivity.MCD10.activityId, conn);
			if(imeitimes >= LIMIT_IMEI_TIMES){
				return flag;
			}
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 在rate概率下是否中奖
	 * @param rate
	 * @return
	 */
	private static boolean isZJ(double rate){
		int i = (int)(1/rate);
		return new Random().nextInt(i) == 1;
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
		String url = Config.getPurchaseURL();
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", uid);
		params.put("coupon", couponId);
		params.put("number", num+"");
		params.put("format", "json");
		params.put("sign", ParamSignUtil.getSign(params, Config.MD5Key));
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
	private static void addFavCoupon(String uid, String brandID){
		String url = Config.getAddFavBrandUrl() + "uid="+uid+"&brand_id="+brandID+"&type=add";
		try {
			NetManager.getInstance().getContent(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(McdActivityManager.isZJ(0.5));
//		System.out.println(Math.pow(2, 0));
	}
	
}
