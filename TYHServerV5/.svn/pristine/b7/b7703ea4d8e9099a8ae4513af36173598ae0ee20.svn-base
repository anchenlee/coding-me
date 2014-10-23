package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.youhui.api.servlet2.UserRankServlet;
import cn.youhui.dao.MySQLDBIns;

public class UserRankManager {
	
	private static final Logger log = Logger.getLogger(UserRankServlet.class);
	private DecimalFormat df = new DecimalFormat("0.00");
	
	private static final int  firstCount = 30;            
	private static final int  befandafer = 5;
	
	private static String RankList_Fanli_all = "RankList_Fanli_all";
	private static String RankHash_today_Fanli_all = "RankHash_today_Fanli_all";
	private static String RankHash_yester_Fanli_all = "RankHash_yester_Fanli_all";
	private static String RankList_Fanli_month = "RankList_Fanli_month";
	private static String RankHash_today_Fanli_month = "RankHash_today_Fanli_month";
	private static String RankHash_yester_Fanli_month = "RankHash_yester_Fanli_month";
	private static String RankList_Waihui_all = "RankList_Waihui_all";
	private static String RankHash_today_Waihui_all = "RankHash_today_Waihui_all";
	private static String RankHash_yester_Waihui_all = "RankHash_yester_Waihui_all";
	private static String RankList_Waihui_month = "RankList_Waihui_month";
	private static String RankHash_today_Waihui_month = "RankHash_today_Waihui_month";
	private static String RankHash_yester_Waihui_month = "RankHash_yester_Waihui_month";
	
	
	private static UserRankManager instance = null;
	
	private UserRankManager(){
	}
	
	public static UserRankManager getInstance(){
		if(instance == null)
			instance = new UserRankManager();
		return instance;
	}

	
	/**
	 * 更新排名数据
	 */
	public void updatedata(){	
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			for(int i=0; i<2;i++){
				for(int j=0;j<2; j++){
					int how =i;
					int when = j;
					String s = "";
//	    		    String str = " and to_days(now())-to_days(addtime)<=30 ";
					String st = "";
					String str = "";
					JedisListManager jlm = null;
					JedisHashManager jhmtoday = null;
					JedisHashManager jhmyester = null;
					if(how == 0 && when == 0) { 
						jlm = new JedisListManager(RankList_Fanli_all); 
						jhmtoday = new JedisHashManager(RankHash_today_Fanli_all);
						jhmyester = new JedisHashManager(RankHash_yester_Fanli_all);
						s = " not "; 
						st = "table2.outer_code";
					}
					if(how == 0 && when == 1) {
						jlm = new JedisListManager(RankList_Fanli_month); 
						jhmtoday = new JedisHashManager(RankHash_today_Fanli_month);
						jhmyester = new JedisHashManager(RankHash_yester_Fanli_month);
						s = " not "; 
						str = " year(now())=year(pay_time) and month(now())=month(pay_time) and ";
						st = "table2.outer_code";
					}
//					if(how == 1 && when == 0) {
//						jlm = new JedisListManager(RankList_Waihui_all);
//						jhmtoday = new JedisHashManager(RankHash_today_Waihui_all);
//						jhmyester = new JedisHashManager(RankHash_yester_Waihui_all);
//						st = "REPLACE(table2.outer_code,'B','')";
//					}
//					if(how == 1 && when == 1) {
//						jlm = new JedisListManager(RankList_Waihui_month); 
//						jhmtoday = new JedisHashManager(RankHash_today_Waihui_month);
//						jhmyester = new JedisHashManager(RankHash_yester_Waihui_month);   
//						str = " year(now())=year(pay_time) and month(now())=month(pay_time) and ";
//						st = "REPLACE(table2.outer_code,'B','')";
//					}		
					jlm.clean();    
					Map<String, String> ymap = jhmtoday.getAll(); 
					if(ymap != null && ymap.size()>0){
						jhmyester.clean();
						jhmyester.setAll(jhmtoday.getAll());
					}
					jhmtoday.clean();
					
					Statement sta = conn.createStatement();
					String sql = "select table1.taobao_nick, table2.* from (SELECT outer_code, sum(fxje) as je FROM youhui_cn_fanli.duoduo_tradelist where "+ str +" outer_code "+ s +"like 'b%' group by outer_code order by sum(fxje) desc) as table2 join youhui_v3.`user`  as table1 on table1.uid = "+st;
					ResultSet rs = sta.executeQuery(sql);
					Map<String, String> mytoday = new HashMap<String, String>();
					int a = 0;
					while (rs.next()) {
						a++;
						RankMessage rm = new RankMessage();
						rm.setTaobao_nick(rs.getString(1));
						String uid = "";
						if(how==1)
							uid = rs.getString(2).substring(1);
						else uid = rs.getString(2);
						rm.setUid(uid);
						if("".equals(rs.getString(3))||"null".equals(rs.getString(3)))
							rm.setJe(0.0);
						else rm.setJe(Double.parseDouble(df.format(rs.getDouble(3))));
						rm.setRank(a);
						jlm.add(rm.toXmlString());
						mytoday.put(uid, ""+a);  
					}
					if(mytoday.size() > 0){
						jhmtoday.setAll(mytoday);
					}
					rs.close();
					sta.close();
					
				}
			}
			conn.close();
			MySQLDBIns.getInstance().release(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
	/**
	 * 今天数据是否已备份
	 * @return 已备份返回true，否则false
	 */
	public boolean isSaveTd(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(System.currentTimeMillis());
		String tablename = "rankHistory"+dateString;
		String sql = " SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_NAME='"+tablename+"';";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		int count = 0;
		try {
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()){
				count = rs.getInt(1);
			}
			sta.close();
			conn.close();
			MySQLDBIns.getInstance().release(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		if(count>0) return true;
		else return false;
	}
	
	/**
	 * 从备份数据恢复到redis
	 */
	public void updateFormSave(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(System.currentTimeMillis());
		String yesterString = formatter.format(System.currentTimeMillis()-24*60*60*1000);
		String tablename = "youhui_snsData.rankHistory"+dateString;
		String yestertn = "youhui_snsData.rankHistory"+yesterString;
		String flalltn = "youhui_snsData.rankFanliAll"+dateString;
		String flmontn = "youhui_snsData.rankFanliMon"+dateString;
		String whalltn = "youhui_snsData.rankWaihuiAll"+dateString;
		String whmontn = "youhui_snsData.rankWaihuiMon"+dateString;
		String sql = "select * from "+tablename +";";
		String sqlyes = "select * from "+yestertn+";";
		String sqlflall = "select * from "+flalltn +";";
		String sqlflmon = "select * from "+flmontn +";";
		String sqlwhall = "select * from "+whalltn +";";
		String sqlwhmon = "select * from "+whmontn +";";
		
		JedisHashManager jhmtodayflall = new JedisHashManager(RankHash_today_Fanli_all);
		JedisHashManager jhmtodayflmon = new JedisHashManager(RankHash_today_Fanli_month);
		JedisHashManager jhmtodaywhall = new JedisHashManager(RankHash_today_Waihui_all);
		JedisHashManager jhmtodaywhmon = new JedisHashManager(RankHash_today_Waihui_month);
		
		JedisHashManager jhmyesterflall = new JedisHashManager(RankHash_yester_Fanli_all);
		JedisHashManager jhmyesterflmon = new JedisHashManager(RankHash_yester_Fanli_month);
		JedisHashManager jhmyesterwhall = new JedisHashManager(RankHash_yester_Waihui_all);
		JedisHashManager jhmyesterwhmon = new JedisHashManager(RankHash_yester_Waihui_month);
		
		JedisListManager jlmflall = new JedisListManager(RankList_Fanli_all); 
		JedisListManager jlmflmon = new JedisListManager(RankList_Fanli_month); 
		JedisListManager jlmwhall = new JedisListManager(RankList_Waihui_all); 
		JedisListManager jlmwhmon = new JedisListManager(RankList_Waihui_month); 
		
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement sta = conn.createStatement();
			
			Map<String, String> mytodayflall = new HashMap<String, String>();
			Map<String, String> mytodayflmon = new HashMap<String, String>();
			Map<String, String> mytodaywhall = new HashMap<String, String>();
			Map<String, String> mytodaywhmon = new HashMap<String, String>();
			
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				String uid = rs.getString("uid");
				String flall = rs.getString("rank_fanli_all");
				if(!"0".equals(flall)){
					mytodayflall.put(uid, flall);
				}
				String flmon = rs.getString("rank_fanli_month");
				if(!"0".equals(flmon)){
					mytodayflmon.put(uid, flmon);
				}
				String whall = rs.getString("rank_waihui_all");
				if(!"0".equals(whall)){   
					mytodaywhall.put(uid, whall);
				}
				String whmon = rs.getString("rank_waihui_month");
				if(!"0".equals(whmon)){   
					mytodaywhmon.put(uid, whmon);
				}
			}
			jhmtodayflall.clean();
			jhmtodayflmon.clean();
			jhmtodaywhall.clean();
			jhmtodaywhmon.clean();
			jhmtodayflall.setAll(mytodayflall);
			jhmtodayflmon.setAll(mytodayflmon);
			jhmtodaywhall.setAll(mytodaywhall);
			jhmtodaywhmon.setAll(mytodaywhmon);
			
		
			Map<String, String> myyesterflall = new HashMap<String, String>();
			Map<String, String> myyesterflmon = new HashMap<String, String>();
			Map<String, String> myyesterwhall = new HashMap<String, String>();
			Map<String, String> myyesterwhmon = new HashMap<String, String>();
			rs = sta.executeQuery(sqlyes);
			while (rs.next()) {
				String uid = rs.getString("uid");
				String flall = rs.getString("rank_fanli_all");
				if(!"0".equals(flall)){
					myyesterflall.put(uid, flall);
				}
				String flmon = rs.getString("rank_fanli_month");
				if(!"0".equals(flmon)){
					myyesterflmon.put(uid, flmon);
				}
				String whall = rs.getString("rank_waihui_all");
				if(!"0".equals(whall)){  
					myyesterwhall.put(uid, whall);
				}
				String whmon = rs.getString("rank_waihui_month");
				if(!"0".equals(whmon)){  
					myyesterwhmon.put(uid, whmon);
				}
			}
			jhmyesterflall.clean();
			jhmyesterflmon.clean();
			jhmyesterwhall.clean();
			jhmyesterwhmon.clean();
			jhmyesterflall.setAll(myyesterflall);
			jhmyesterflmon.setAll(myyesterflmon);
			jhmyesterwhall.setAll(myyesterwhall);
			jhmyesterwhmon.setAll(myyesterwhmon);
			jlmflall.clean();
			rs = sta.executeQuery(sqlflall);
			while (rs.next()) {
				jlmflall.add(rs.getString("detail"));
			}
			
			jlmflmon.clean();
			rs = sta.executeQuery(sqlflmon);
			while (rs.next()) {
				jlmflmon.add(rs.getString("detail"));
			}
			
			jlmwhall.clean();
			rs = sta.executeQuery(sqlwhall);
			while (rs.next()) {
				jlmwhall.add(rs.getString("detail"));
			}
			
			jlmwhmon.clean();
			rs = sta.executeQuery(sqlwhmon);
			while (rs.next()) {
				jlmwhmon.add(rs.getString("detail"));
			}
			rs.close();
			sta.close();
			conn.close();
			MySQLDBIns.getInstance().release(conn);
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
	/**
	 * 保存排名记录
	 */
	public void saveDatabase(){
		if(!isSaveTd()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String dateString = formatter.format(System.currentTimeMillis());
			String tablename = "youhui_snsData.rankHistory"+dateString;
			String flalltn = "youhui_snsData.rankFanliAll"+dateString;
			String flmontn = "youhui_snsData.rankFanliMon"+dateString;
			String whalltn = "youhui_snsData.rankWaihuiAll"+dateString;
			String whmontn = "youhui_snsData.rankWaihuiMon"+dateString;
			String sqlcreate = "create  table "+ tablename +"(uid varchar(255) , rank_fanli_all int default '0', rank_fanli_month int default '0', rank_waihui_all int default '0', rank_waihui_month int default '0');";
			String sqlflall = "create table "+flalltn+"(rank int default '0', detail varchar(200))ENGINE=MyISAM DEFAULT CHARSET=utf8;";
			String sqlflmon = "create table "+flmontn+"(rank int default '0', detail varchar(200))ENGINE=MyISAM DEFAULT CHARSET=utf8;";
			String sqlwhall = "create table "+whalltn+"(rank int default '0', detail varchar(200))ENGINE=MyISAM DEFAULT CHARSET=utf8;";
			String sqlwhmon = "create table "+whmontn+"(rank int default '0', detail varchar(200))ENGINE=MyISAM DEFAULT CHARSET=utf8;";
			
			String sqlinsert;
			
			Connection conn = MySQLDBIns.getInstance().getConnection();
			try {
				Statement sta = conn.createStatement();
				sta.execute(sqlcreate);
				List<String> fluidList = new JedisHashManager(RankHash_today_Fanli_all).getAllField(); 
				List<String> whuidList = new JedisHashManager(RankHash_today_Waihui_all).getAllField(); 
				Map<String, String> flAllMap = new JedisHashManager(RankHash_today_Fanli_all).getAll();
				Map<String, String> flMonMap = new JedisHashManager(RankHash_today_Fanli_month).getAll();
				Map<String, String> whAllMap = new JedisHashManager(RankHash_today_Waihui_all).getAll();
				Map<String, String> whMonMap = new JedisHashManager(RankHash_today_Waihui_month).getAll();
				
				for(String uid : fluidList){
					String flall = flAllMap.get(uid);
					String flmon = flMonMap.get(uid);
					String whall = whAllMap.get(uid);
					String whmon = whMonMap.get(uid);
					if(flall == null) flall = "0";
					if(flmon == null) flmon = "0";
					if(whall == null) whall = "0";
					if(whmon == null) whmon = "0";
					sqlinsert ="insert into "+ tablename +" values('"+ uid +"','"+flall+"','"+ flmon+"','"+whall+"','"+ whmon +"');";
					sta.addBatch(sqlinsert);
				} 
				for(String uid : whuidList){
					if(!fluidList.contains(uid)){
						String whall = whAllMap.get(uid);
						String whmon = whMonMap.get(uid);
						if(whall == null) whall = "0";
						if(whmon == null) whmon = "0";
					    sqlinsert ="insert into "+ tablename +" values('"+ uid +"','0','0','"+whall+"','"+ whmon +"');";
					    sta.addBatch(sqlinsert);
					}
				}
				sta.executeBatch();
				
				int a=0;
				sta.execute(sqlflmon);
				List<String> flmonList = new JedisListManager(RankList_Fanli_month).getAll();
				for(String detail : flmonList){
					a++;
					sqlinsert ="insert into "+ flmontn +" values("+ a +",'"+detail+"');";
					sta.addBatch(sqlinsert);
				} 
				sta.executeBatch();
				loginSql("fanlimon has write.");
				
				sta.execute(sqlflall);
				List<String> flallList = new JedisListManager(RankList_Fanli_all).getAll();
				a=0;
				for(String detail : flallList){
					a++;
					sqlinsert ="insert into "+ flalltn +" values("+ a +",'"+detail+"');";
					sta.addBatch(sqlinsert);
				} 
				sta.executeBatch();
				
				loginSql("fanliall has write.");
				
				sta.execute(sqlwhall);
				loginSql("has creat waihuiall");
				List<String> whallList = new JedisListManager(RankList_Waihui_all).getAll();
				a=0;
				for(String detail : whallList){
					a++;
					sqlinsert ="insert into "+ whalltn +" values("+ a +",'"+detail+"');";
					sta.addBatch(sqlinsert);
				} 
				sta.executeBatch();
				
				loginSql("waihuiall has write.");
				
				sta.execute(sqlwhmon);
				List<String> whmonList = new JedisListManager(RankList_Waihui_month).getAll();
				a=0;
				for(String detail : whmonList){
					a++;
					sqlinsert ="insert into "+ whmontn +" values("+ a +",'"+detail+"');";
					sta.addBatch(sqlinsert);
				} 
				sta.executeBatch();
				loginSql("waihuimon has write.");
				sta.close();
				conn.close();
				MySQLDBIns.getInstance().release(conn);
			} catch (SQLException e) {
				log.error(e);
				loginSql(e.toString());
			} finally {
				MySQLDBIns.getInstance().release(conn);
			}
		}
	}
	
	private void loginSql(String exce){
		Connection con = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "insert into youhui_snsData.snslog(`exception`,`timestamp`) values(?,?)";
			PreparedStatement sta = con.prepareStatement(sql);
			sta.setString(1, exce);
			sta.setLong(2, System.currentTimeMillis());
			sta.executeUpdate();
		} catch (SQLException e) {
		}finally {
			MySQLDBIns.getInstance().release(con);
		}
	}
	
	/**
     * 将taobao_nick改为X**X
     */
	private String changenick(String s) {
		if (s == null || "".equals(s) || "null".equals(s))
			return "**";
		if (s.length() < 2)
			return s;
		int length = s.length();
		String s1 = "**";
		String des = new StringBuffer().append("<![CDATA[").append(s.substring(0, 1)).append(s1).append(s.substring(length - 1, length)).append("]]>").toString();
		return des;
	}
	
	/**
	 * 
	 * @param how 0表示返利,1表示外惠
	 * @param when 0表示全部，1表示本月
	 * @param where 0表示总榜，1表示我的
	 * @param uid
	 * @return  查到的排名信息
	 */
	private List<String> getRankList(int how, int where, int when, String uid){
		JedisListManager jlm = null ;
		JedisHashManager jhmtoday = null;
		int rank = 0;
		if(how == 0 && when == 0) { 
			jlm = new JedisListManager(RankList_Fanli_all); 
			jhmtoday = new JedisHashManager(RankHash_today_Fanli_all);
		}
		if(how == 0 && when == 1) {
			jlm = new JedisListManager(RankList_Fanli_month); 
			jhmtoday = new JedisHashManager(RankHash_today_Fanli_month);
		}
		if(how == 1 && when == 0) {
			jlm = new JedisListManager(RankList_Waihui_all);
			jhmtoday = new JedisHashManager(RankHash_today_Waihui_all);
		}
		if(how == 1 && when == 1) {
			jlm = new JedisListManager(RankList_Waihui_month); 
			jhmtoday = new JedisHashManager(RankHash_today_Waihui_month);   
		}	
		List<String> list = new ArrayList<String>();
		if(where == 0) list = jlm.getRange(0, firstCount-1);
		else {
			if(uid != null && !"".equals(uid)){
				String rankStr = jhmtoday.get(uid);
				if(rankStr != null){
					rank = Integer.valueOf(rankStr);
					list =  jlm.getRange(Math.max(0, rank - befandafer -1 ), Math.min(rank + befandafer - 1, jlm.size()));
				}
				else return null;
			}
			else return null;
		}
		List<String> ret = new ArrayList<String>();
		if(list != null && list.size()>0)
			for(String rms : list){
				if(rms.contains("<uid>"+uid+"</uid>")) {
					rms = rms.replaceAll("<isme>0</isme>", "<isme>1</isme>");
				}else {
					int first = rms.indexOf("<taobao_nick>");
					int last = rms.indexOf("</taobao_nick>");
					String taobao_nick = rms.substring(first+"<taobao_nick>".length(), last);
					String taobao_nick_new = changenick(taobao_nick);
					rms = rms.replaceAll("<taobao_nick>.*</taobao_nick>", "<taobao_nick>"+taobao_nick_new+"</taobao_nick>");
				}
				rms = rms.replaceAll("<uid>.*</uid>", "");
				ret.add(rms);
			}
		return ret;
	}
	
	/**
	 * 获取排名的上升或下降情况
	 * @param how 0表示返利,1表示外惠
	 * @param when 0表示全部，1表示本月
	 * @param uid
	 * @return 
	 */
	private int getRise(int how, int when, String uid){
		JedisHashManager jhmtoday = null;
		JedisHashManager jhmyester = null;
		if(how == 0 && when == 0) { 
			jhmtoday = new JedisHashManager(RankHash_today_Fanli_all);
			jhmyester = new JedisHashManager(RankHash_yester_Fanli_all);
		}
		if(how == 0 && when == 1) {
			jhmtoday = new JedisHashManager(RankHash_today_Fanli_month);
			jhmyester = new JedisHashManager(RankHash_yester_Fanli_month);
		}
		if(how == 1 && when == 0) {	
			jhmtoday = new JedisHashManager(RankHash_today_Waihui_all);
			jhmyester = new JedisHashManager(RankHash_yester_Waihui_all);
		}
		if(how == 1 && when == 1) {
			jhmtoday = new JedisHashManager(RankHash_today_Waihui_month);   
			jhmyester = new JedisHashManager(RankHash_yester_Waihui_month);   
		}	
		int ret = 0;  
		if(uid !=null && !"".equals(uid)&& jhmtoday.get(uid) !=null && jhmyester.get(uid) != null ){
			ret = Integer.valueOf(jhmyester.get(uid)) - Integer.valueOf(jhmtoday.get(uid));
		}
		return ret;
	}
	
	/**
	 * @return 排名的xml格式数据
	 */
	public String getResult(String uid){
		Rank rank = new Rank();
		
		int riseFanliAll = getRise(0, 0, uid);
		int riseFanliMonth = getRise(0, 1, uid);
		int riseWaihuiAll = getRise(1, 0, uid);
		int riseWaihuiMonth = getRise(1, 1, uid);
		
		List<String> fanliFirstAll = getRankList(0,0,0,uid);
		List<String> fanliFirstMonth = getRankList(0,0,1,uid);
		List<String> fanliMyAll = getRankList(0,1,0,uid);
		List<String> fanliMyMonth = getRankList(0,1,1,uid);
//		List<String> waihuiFirstAll = getRankList(1,0,0,uid);
//		List<String> waihuiFirstMonth = getRankList(1,0,1,uid);
//		List<String> waihuiMyAll = getRankList(1,1,0,uid);
//		List<String> waihuiMyMonth = getRankList(1,1,1,uid);
		
		How howFanli = new How("返利");
		Where whereFanliMy = new Where("我的排名");
		
		When whenFanliMyMonth;
		String raflmon = new JedisHashManager(RankHash_today_Fanli_month).get(uid);
		if(raflmon==null||"null".equals(raflmon))  raflmon = ""; 
		whenFanliMyMonth = new When("本月返利榜  "+raflmon, riseFanliMonth);
		whenFanliMyMonth.setRmList(fanliMyMonth);
		whereFanliMy.getWhenList().add(whenFanliMyMonth);
		
		When whenFanliMyAll ;
		String raflall = new JedisHashManager(RankHash_today_Fanli_all).get(uid);
		if(raflall==null||"null".equals(raflall))  raflall = ""; 
		whenFanliMyAll = new When("累计返利榜  "+raflall, riseFanliAll);
		whenFanliMyAll.setRmList(fanliMyAll);
		whereFanliMy.getWhenList().add(whenFanliMyAll);
		howFanli.getWhereList().add(whereFanliMy);
		
		
		
		Where whereFanliFirst = new Where("榜首淘友");
		When whenFanliFirstMonth = new When("本月返利榜  "+raflmon, riseFanliMonth);
		whenFanliFirstMonth.setRmList(fanliFirstMonth);
		whereFanliFirst.getWhenList().add(whenFanliFirstMonth);
		When whenFanliFirstAll = new When("累计返利榜  "+raflall, riseFanliAll);
		whenFanliFirstAll.setRmList(fanliFirstAll);
		whereFanliFirst.getWhenList().add(whenFanliFirstAll);
		howFanli.getWhereList().add(whereFanliFirst);
		rank.getHowList().add(howFanli);
		
		How howWaihui = new How("外惠");
		Where whereWaihuiMy = new Where("我的排名");
//		
		When whenWaihuiMyMonth;
		String rawhmon = new JedisHashManager(RankHash_today_Waihui_month).get(uid);
		if(rawhmon==null||"null".equals(rawhmon))  rawhmon = ""; 
		
		rawhmon = "";
		riseWaihuiMonth = 0;            //外惠不显示
		
		whenWaihuiMyMonth = new When("本月外惠榜  "+rawhmon, riseWaihuiMonth);
//		whenWaihuiMyMonth.setRmList(waihuiMyMonth);	
		whereWaihuiMy.getWhenList().add(whenWaihuiMyMonth);
		When whenWaihuiMyAll ;
		String rawhall = new JedisHashManager(RankHash_today_Waihui_all).get(uid);
		if(rawhall==null||"null".equals(rawhall))  rawhall = ""; 
		
		rawhall = "";
		riseWaihuiAll = 0;              //外惠不显示
		
		whenWaihuiMyAll = new When("累计外惠榜  "+rawhall, riseWaihuiAll);
//		whenWaihuiMyAll.setRmList(waihuiMyAll);
		whereWaihuiMy.getWhenList().add(whenWaihuiMyAll);
//		
		howWaihui.getWhereList().add(whereWaihuiMy);
//		
		Where whereWaihuiFirst = new Where("榜首淘友");
		When whenWaihuiFirstMonth = new When("本月外惠榜  "+rawhmon, riseWaihuiMonth);
//		whenWaihuiFirstMonth.setRmList(waihuiFirstMonth);
		whereWaihuiFirst.getWhenList().add(whenWaihuiFirstMonth);
		When whenWaihuiFirstAll = new When("累计外惠榜  "+rawhall, riseWaihuiAll);
//		whenWaihuiFirstAll.setRmList(waihuiFirstAll);
		whereWaihuiFirst.getWhenList().add(whenWaihuiFirstAll);
		howWaihui.getWhereList().add(whereWaihuiFirst);
		rank.getHowList().add(howWaihui);
		
		StringBuffer result = new StringBuffer();
		StringBuffer sb;
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<resp>");
		try {
			sb = new StringBuffer();
			sb.append("<head><status>1000</status><desc><![CDATA[OK]]></desc></head>");
			sb.append("<data>");
			sb.append(rank.toXmlString());
			sb.append("</data>");
		} catch (Exception e) {
			sb = new StringBuffer();
			sb.append("<head><status>1005</status><desc><![CDATA["
					+ e.getMessage() + "]]></desc></head>");
		}
		result.append(sb);
		result.append("</resp>");
		return result.toString();
	}
	

	
	class RankMessage {
		private String uid;
		private int rank;
		private String taobao_nick;
		private double je;
		
		public int getRank() {
			return rank;
		}
		
		public void setRank(int rank) {
			this.rank = rank;
		}
		
		public String getTaobao_nick() {
			return taobao_nick;
		}
		
		public void setTaobao_nick(String taobao_nick) {
			this.taobao_nick = taobao_nick;
		}
		
		public String getUid() {
			return uid;
		}
		
		public void setUid(String uid) {
			this.uid = uid;
		}
		
		public double getJe() {
			return je;
		}
		
		public void setJe(double je) {
			this.je = je;
		}
		
		public String toXmlString(){
			StringBuffer sb = new StringBuffer();
			sb.append("<user><uid>"+uid+"</uid><isme>0</isme><taobao_nick>" +taobao_nick+ "</taobao_nick><rank>"+ rank +"</rank><amount>"+ je +"</amount>");
			sb.append("</user>");
			return sb.toString();
		}
		
	}
	
	class Rank{
		List<How> howList = new ArrayList<How>();
		public Rank(){
		}
		public  String toXmlString(){
			StringBuffer sb = new StringBuffer();
			sb.append("<ranks>");
			if(howList != null && howList.size()>0)
				for(How h :howList)
					sb.append(h.toXmlString());
			sb.append("</ranks>");
			return sb.toString();
		} 
		public List<How> getHowList(){
			return this.howList;
		}
	}
	class How{
		
		private List<Where> whereList = new ArrayList<Where>();
		private String howtitle;
		public How(String howtitle){
			this.howtitle = howtitle;
		}
		public  String toXmlString(){
			StringBuffer sb = new StringBuffer();
			sb.append("<how type=\""+ howtitle +"\">");
			if(whereList != null && whereList.size() >0)
				for(Where wh :whereList)
					sb.append(wh.toXmlString());
			sb.append("</how>");
			return sb.toString();
		} 
		public List<Where> getWhereList(){
			return this.whereList;
		}
	}
	class Where{
		private List<When> whenList = new ArrayList<When>();
		private String wheretitle;
		public Where(String wheretitle){
			this.wheretitle = wheretitle;
		}
		public  String toXmlString(){
			StringBuffer sb = new StringBuffer();
			sb.append("<where type=\""+ wheretitle +"\">");
			if(whenList != null && whenList.size() > 0)
				for(When wh :whenList)
					sb.append(wh.toXmlString());
			sb.append("</where>");
			return sb.toString();
		} 
		public List<When>   getWhenList(){
			return this.whenList;
		}
		
	}
	class When{
		private List<String> rmList = new ArrayList<String>();
		private int rise;
		private String whentitle;
		public When(String whentitle){
			this.whentitle = whentitle;
		}
		
		public When(String whentitle, int rise){
			this.whentitle = whentitle;
			this.rise = rise;
		}
		
		public  String toXmlString(){
			StringBuffer sb = new StringBuffer();
			sb.append("<when type=\""+ whentitle +"\" rise=\""+ rise +"\">");
			if(rmList !=null && rmList.size()>0)
				for(String s :rmList)
					sb.append(s);
			sb.append("</when>");
			return sb.toString();
		} 
		public void setRmList(List<String> rmList){
			this.rmList = rmList;
		}
		
		public void setRise(int rise){
			this.rise = rise;
		}
	}
}
