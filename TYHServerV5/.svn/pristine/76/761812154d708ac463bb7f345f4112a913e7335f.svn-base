package cn.youhui.jfbad;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.common.Config;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.DES;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.StringUtils;

public class JFBAdManager {
	
	private static DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	private static final int MostJFBAdNum = 5;    //最多展示广告个数
	private static final int InitJFBAdNum = 3;    //初始展示广告个数
	private static final int MostHasJFBAdNum = 3;   //最多有送集分宝广告个数
	
	private static final String DESKEY = "99817749";
	
	
	private static JFBAdManager instance = null;
	
	private JFBAdManager(){}
	
	public static JFBAdManager getInstance(){
		if(instance == null){
			instance = new JFBAdManager();
		}
		return instance;
	}

	/**
	 * 集分宝广告跳转
	 * @param uid
	 * @param adId
	 * @param isFromSign
	 * @return
	 */
	public String skip(String uid, String adId, int isFromSign){
		String url = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			JFBAd ad = JFBAdDAO.getInstance().getAd(adId, uid, conn);
			long now = System.currentTimeMillis();
			String nickorphone = UserManager.getInstance().getNickOrPhoneWithConn(uid, conn);
			if(!StringUtils.isEmpty(nickorphone)){
				String key = getKey(uid, adId);
				int status = JFBAdClickDAO.getInstance().getStatus(uid, adId, conn);
				if(status == 0){    //未点击
					JFBAdClick adck = new JFBAdClick(uid, nickorphone, adId, ad.getPerNum(), now, df.format(new Date(now)), isFromSign, getUniqueId(adId, uid, ad.getType()), key);
					if(JFBAdClickDAO.getInstance().add(adck, conn)){
						if(JFBAdDAO.getInstance().addClick(adId, conn) && ad.getPerNum() > 0){
							JFBAdClickDAO.getInstance().upHasJFB(uid, adId, conn);
						}
						url = Config.JFBAdMidSkip + key;
					}
				}else if(JFBAdClickDAO.STATUS_CLICK == status){       //已点击未领取
					url = Config.JFBAdMidSkip + key;
				}else if(-1 == status){
					url = Config.JFBAdMidSkip + key;
				}else{
					url = ad.getClickUrl();
				}
				if(ad.getPerNum() == 0 && (JFBAdDAO.TYPE_AD == ad.getType() || JFBAdDAO.TYPE_L_AD == ad.getType())){
					url = ad.getClickUrl();
				}
			}else{
				url = ad.getClickUrl();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return url;
	}
	
	private String getUniqueId(String adId, String uid, int adType){
		if(StringUtils.isEmpty(adId) || StringUtils.isEmpty(uid)){
			return "";
		}
		int c = 8 - adId.length();
		int no = c>0 ? c:0;
		if(JFBAdDAO.TYPE_L_AD == adType || JFBAdDAO.TYPE_L_SIGN == adType){
			return getO(no) + adId + uid + df.format(new Date());
		}else{
			return getO(no) + adId + uid;
		}
	}
	
	/**
	 * 返回num个0
	 * @param num
	 * @return
	 */
	private String getO(int num){
		if(num <= 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<num; i++){
			sb.append("0");
		}
		return sb.toString();
	}
	
	/**
	 * 获取广告跳转url
	 * @param key
	 * @return
	 */
	public String getAdUrl(String key){
		String url = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String[] dkey = deKey(key);
			String uid = dkey[0];
			String adId = dkey[1];
			if(adId != null && !"".equals(adId)){
				JFBAd ad = JFBAdDAO.getInstance().getAd(adId, uid, conn);
				if(ad != null){
					url = ad.getClickUrl();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return url;
	}
	
	/**
	 * 生成key
	 * @param uid
	 * @param adId
	 * @return
	 */
	public String getKey(String uid, String adId){
		return DES.encode(DESKEY, uid + "#" +adId);
	}
	
	/**
	 * 解析key
	 * @param key
	 * @return
	 */
	private String[] deKey(String key){
		String s = DES.decode(DESKEY, key);
		return s.split("#");
	}
	
	/**
	 * 加载广告成功后 回调
	 * @param uid
	 * @param adId
	 * @param isFromSign
	 * @return
	 */
	public JFbAdCallBackResponse callBack(String key){
		JFbAdCallBackResponse rsp = new JFbAdCallBackResponse();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String[] s = deKey(key);
			if(s != null && s.length == 2){
				String uid = s[0];
				String adId = s[1];
				if(JFBAdClickDAO.getInstance().upHasLoad(uid, adId, conn)){
					
					if(JFBAdDAO.getInstance().isSignAd(adId, conn)){
						rsp = SignInManager.getInstance().clickSignAd(uid, adId, conn);
						rsp.setSignAd(true);
					}else{
						rsp = JFBAdClickDAO.getInstance().fafangJFB(uid, adId, conn);
					}
					if(rsp.isSuccess()){
						JFBAdClickDAO.getInstance().upHasFF(uid, adId, conn);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return rsp;
	}
	
	/**
	 * 获取点击跳转url
	 * @param uid
	 * @param adId
	 * @param isFromSign
	 * @return
	 */
	public String getSkipUrl(String uid, String adId, int isFromSign){
		String s = "#" + uid + "#" + adId + "#" + isFromSign;
		String param = Encrypt.encode(s);
		return Config.JFBAdSkipUrl + param;
	}
	
	/**
	 * 获取用户的集分宝广告列表
	 * @param uid
	 * @return
	 */
	public List<JFBAd> getAdListByUid(String uid){
		Connection conn = null;
		List<JFBAd> list = new ArrayList<JFBAd>();
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String[] hcs = JFBAdClickDAO.getInstance().getHasClick(uid, conn);
			if(hcs != null && hcs.length ==3){
				int hasCliNum = Integer.parseInt(hcs[0]);
				int hasCJfbNum = Integer.parseInt(hcs[1]);
				if(hcs[2] != null && !"".equals(hcs[2])){
					list = JFBAdDAO.getInstance().getAdList(uid, hcs[2].split(","));
				}
				int num = 0;
				if(hasCliNum < MostJFBAdNum && hasCJfbNum < MostHasJFBAdNum){
					if(hasCliNum < InitJFBAdNum){
						num = InitJFBAdNum - hasCliNum;
					}else{
						num = 1;
					}
					JFBAdDAO.getInstance().addAds(list, uid, num, MostHasJFBAdNum - hasCJfbNum);
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(JFBAdManager.getInstance().getKey("12499184", "411"));
	}
}
