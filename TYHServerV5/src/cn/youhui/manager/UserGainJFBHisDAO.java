package cn.youhui.manager;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.ActivityJoin;
import cn.youhui.bean.UserAccount;
import cn.youhui.bean.UserBean;
import cn.youhui.bean.UserGainJFBHis;
import cn.youhui.bean.UserGainJFBHis.YearHis;
import cn.youhui.bean.UserGainJFBHis.YearHis.OnceHis;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.ramdata.UserJFBAccountCacher;
import cn.youhui.utils.FenHongUtil;

public class UserGainJFBHisDAO {
	
	private static final String TYPE_FANLI = "fanli";    //购物返利
	private static final String TYPE_SIGN = "sign";       //签到
	private static final String TYPE_FH = "fenhong";      //分红
	private static final String TYPE_OTHER = "other";      //其他
	
	DateFormat df = new SimpleDateFormat("M月");
	
	private DecimalFormat ddf = new DecimalFormat("0.00");
	
	private static UserGainJFBHisDAO instance = null;
	
	private UserGainJFBHisDAO(){}
	
	public static UserGainJFBHisDAO getInstance(){
		if(instance == null){
			instance = new UserGainJFBHisDAO();
		}
		return instance;
	}
	
	public UserGainJFBHis getHis(String uid){
		UserGainJFBHis his = new UserGainJFBHis();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getReadConnection();
//			int jfbNum = UserJFBAccountCacher.getInstance().get(uid).getGainNum();
//			float fanli = MoneyManager.getInstance().getFanli(uid, conn);
//			float all = Float.parseFloat(ddf.format(fanli + ((float)jfbNum /100)));
			UserAccount ua = UserAccountManager.getInstance().getUserAccount(uid);
			his.setAllGain(Double.parseDouble(ddf.format(ua.getFlXJ() + ((double)(ua.getFlAddNum() + ua.getAcAddNum()) /100))));
		
		UserBean user = UserManager.getInstance().getUser(uid, conn);
		his.setMyImg(user.getIcon());
		his.setStartTime(user.getActiveTime());
//		int tradeJFBNum = MoneyManager.getInstance().getTradeFanli(uid, conn);
//		int tradeJFBNum = UserJFBAccountCacher.getInstance().get(uid).getFlNum();
		
//		float tradeAll = Float.parseFloat(ddf.format(fanli + ((float)tradeJFBNum /100)));
		his.setLevel(ua.getLevel());
		
		List<ActivityJoin> jlist = JiFenBaoMXManager.getInstance().getGainJFB(uid);
		int signCount = 0;
		int gouwuCount = 0;
		for(ActivityJoin j : jlist){
			String type = getType(j.getActivityId());
			if(TYPE_FANLI.equals(type) ){
				gouwuCount++;
			}else if(TYPE_SIGN.equals(type) ){
				signCount++;
			}
		}
		
		int signC= 0;
		int gouwuC = 0;
		for(ActivityJoin j : jlist){
			String year = getYear(j.getTimestamp());
			YearHis yhis = null;
			if(his.getHisList() == null || his.getHisList().size() == 0 || !his.getHisList().containsKey(year)){
				yhis = his.new YearHis();
				his.getHisList().put(year, yhis);
			}else{
				yhis = his.getHisList().get(year);
			}
			addJFBtoYear(j.getActivityId(), j.getJfbNum(), yhis);
			yhis.setYear(year);
			
			String month = getMonth(j.getTimestamp());
			String type = getType(j.getActivityId());
			String mkey =  month + type;
			OnceHis ohis = null;
			if(yhis.getList().containsKey(mkey)){
				ohis = yhis.getList().get(mkey);
			}else{
				ohis = yhis.new OnceHis();
				yhis.getList().put(mkey, ohis);
			}
			if(TYPE_FANLI.equals(type)){
				gouwuC++;
				if(gouwuC == gouwuCount){					
					ohis.setFirst(true);
				}
			}else if(TYPE_SIGN.equals(type) ){
				signC++;
				if(signC == signCount){				
					ohis.setFirst(true);
				}
			}
			
			ohis.setType(type);
			ohis.setMonth(month);
			ohis.setTime(getTime(j.getTimestamp()));
			ohis.setJfbNum(ohis.getJfbNum() + j.getJfbNum());
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return his;
	}
	

	private String getType(String activityId){
		if("fanli".equals(activityId)){
			return TYPE_FANLI;
		}else if("1".equals(activityId)){
			return TYPE_SIGN;
		}else if("18".equals(activityId)){
			return TYPE_FH;
		}else{
			return TYPE_OTHER;
		}
	}
	
	private void addJFBtoYear(String activityId, int jfbNum, YearHis yhis){
		if("fanli".equals(activityId)){
			yhis.setFanliJFB(jfbNum + yhis.getFanliJFB());
		}else if("1".equals(activityId)){
			yhis.setSignJFB(jfbNum + yhis.getSignJFB());
		}else{
			yhis.setOtherJFB(jfbNum + yhis.getOtherJFB());
		}
	}
	
	private String getYear(long time){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return "" + c.get(Calendar.YEAR);
	}
	
	private String getMonth(long time){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return "" + ( 1+ c.get(Calendar.MONTH));
	}

	private String getTime(long time){
		return df.format(new Date(time));
	}
	
	public static void main(String[] args) {
//		long s = System.currentTimeMillis();
//		System.out.println(UserGainJFBHisDAO.getInstance().getHis("21172545").getHisList().get("2014").getList().get("3fenhong").getJfbNum());
//		System.out.println("ok:" + (System.currentTimeMillis() - s));
	}
}
