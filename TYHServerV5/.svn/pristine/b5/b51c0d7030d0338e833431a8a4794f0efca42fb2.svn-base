package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import cn.youhui.bean.JFBRankBean;
import cn.youhui.dao.MySQLDBIns;

public class UserJFBRankManager {

	public static String Today_Month_Hash_JFBRank = "Today_Month_Hash_JFBRank";
	public static String Today_Total_Hash_JFBRank = "Today_Total_Hash_JFBRank";
	public static String Today_Month_Sort_JFBRank = "Today_Month_Sort_JFBRank";
	public static String Today_Total_Sort_JFBRank = "Today_Total_Sort_JFBRank";
	
	
	public static void updateJFBRank(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<JFBRankBean> monthList = new ArrayList<JFBRankBean>();
		List<JFBRankBean> totalList = new ArrayList<JFBRankBean>();
		JedisHashManager todayMonthHash = null;
		JedisHashManager todayTotalHash = null;
		JedisSortedSetManager todayMonthSort = null;
		JedisSortedSetManager todayTotalSort = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(System.currentTimeMillis());
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		long start = getUnixTimestemp(formatter1.format(System.currentTimeMillis()).substring(0,8)+"01"+" 00:00:00");
		long end = getUnixTimestemp(formatter1.format(System.currentTimeMillis())+" 23:59:59");
		String sql = "SELECT b.*,a.taobao_nick FROM  (SELECT uid,count(commission) as count  FROM youhui_fanli.tyh_report_trade where pay_timestamp > '"+start+"' and pay_timestamp <= '"+end+"'  group by uid order by count desc) as b join youhui_v3.`user`  as a  on a.uid = b.uid  ;";	//查询月排名
		String sql1 = "SELECT b.*,a.taobao_nick FROM  (SELECT uid,count(commission) as count  FROM youhui_fanli.tyh_report_trade  group by uid order by count desc) as b join youhui_v3.`user`  as a  on a.uid = b.uid   ;";	//查询累计排名
		try {
			todayMonthHash = new JedisHashManager(Today_Month_Hash_JFBRank);
			todayTotalHash = new JedisHashManager(Today_Total_Hash_JFBRank);
			todayMonthSort = new JedisSortedSetManager(Today_Month_Sort_JFBRank);
			todayTotalSort = new JedisSortedSetManager(Today_Total_Sort_JFBRank);
			int a = 0;
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			todayMonthSort.deleteKey();
			while(rs.next()){
				JFBRankBean bean = new JFBRankBean();
				bean.setJfbNum(rs.getDouble("count"));
				bean.setRank(a);
				bean.setTaobaoNick(rs.getString("taobao_nick"));
				bean.setUid(rs.getString("uid"));
				monthList.add(bean);
				Gson gson = new Gson();
				String context = gson.toJson(bean);
				todayMonthSort.add(a, context);	
				todayMonthHash.add(rs.getString("uid"), a+"");
				a++;
			}
			insertDailyData(monthList,"jfb_month_rank"+dateString,conn);
			
			todayTotalSort.deleteKey();
			int b = 0;
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			while(rs.next()){
				JFBRankBean bean = new JFBRankBean();
				bean.setJfbNum(rs.getDouble("count"));
				bean.setRank(b);
				bean.setTaobaoNick(rs.getString("taobao_nick"));
				bean.setUid(rs.getString("uid"));
				totalList.add(bean);
				Gson gson = new Gson();
				String context = gson.toJson(bean);
				todayTotalSort.add(b, context);	
				todayTotalHash.add(rs.getString("uid"), b+"");
				b++;
			}
			insertDailyData(totalList,"jfb_total_rank"+dateString,conn);
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		
	}
	
	public static void updateJFBRankTable(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		List<JFBRankBean> monthList = new ArrayList<JFBRankBean>();
//		List<JFBRankBean> totalList = new ArrayList<JFBRankBean>();
		JedisHashManager todayMonthHash = null;
		JedisHashManager todayTotalHash = null;
		JedisSortedSetManager todayMonthSort = null;
		JedisSortedSetManager todayTotalSort = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(System.currentTimeMillis());
		String sql = "SELECT * FROM daily_jfb_rank.jfb_month_rank"+dateString+" order by rank asc;";	//查询月排名
		String sql1 = "SELECT * FROM daily_jfb_rank.jfb_total_rank"+dateString+" order by rank asc ;";	//查询累计排名
		try {
			todayMonthHash = new JedisHashManager(Today_Month_Hash_JFBRank);
			todayTotalHash = new JedisHashManager(Today_Total_Hash_JFBRank);
			todayMonthSort = new JedisSortedSetManager(Today_Month_Sort_JFBRank);
			todayTotalSort = new JedisSortedSetManager(Today_Total_Sort_JFBRank);
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			todayMonthSort.deleteKey();
			while(rs.next()){
				JFBRankBean bean = new JFBRankBean();
				bean.setJfbNum(rs.getDouble("jfb_num"));
				bean.setRank(rs.getInt("rank"));
				bean.setTaobaoNick(rs.getString("taobao_nick"));
				bean.setUid(rs.getString("uid"));
//				monthList.add(bean);
				Gson gson = new Gson();
				String context = gson.toJson(bean);
				todayMonthSort.add(rs.getInt("rank"), context);	
				todayMonthHash.add(rs.getString("uid"), rs.getString("rank"));
			}
			
			todayTotalSort.deleteKey();
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			while(rs.next()){
				JFBRankBean bean = new JFBRankBean();
				bean.setJfbNum(rs.getDouble("jfb_num"));
				bean.setRank(rs.getInt("rank"));
				bean.setTaobaoNick(rs.getString("taobao_nick"));
				bean.setUid(rs.getString("uid"));
//				totalList.add(bean);
				Gson gson = new Gson();
				String context = gson.toJson(bean);
				todayTotalSort.add(rs.getInt("rank"), context);	
				todayTotalHash.add(rs.getString("uid"), rs.getString("rank"));
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		
	}
	
	public static boolean insertDailyData(List<JFBRankBean> list,String tableName,Connection conn){
		boolean flag = false;
//		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "CREATE  TABLE IF NOT EXISTS `daily_jfb_rank`.`"+tableName+"` (`id` INT NOT NULL AUTO_INCREMENT ,`uid` VARCHAR(25) NOT NULL ,`taobao_nick` VARCHAR(45) NOT NULL ,`jfb_num` VARCHAR(45) NOT NULL ,"
				+"`rank` BIGINT NOT NULL ,PRIMARY KEY (`id`) ,UNIQUE INDEX `uid_UNIQUE` (`uid` ASC) ,UNIQUE INDEX `taobao_nick_UNIQUE` (`taobao_nick` ASC) )ENGINE = MyISAMDEFAULT CHARACTER SET = utf8 ;";
		String sql1 = "insert into `daily_jfb_rank`.`"+tableName+"`(uid,taobao_nick,jfb_num,rank) values(?,?,?,?)";
		try {
//			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.execute();
			
			ps = conn.prepareStatement("TRUNCATE TABLE `daily_jfb_rank`.`"+tableName+"`");
			ps.execute();
			
			ps = conn.prepareStatement(sql1);
			for(JFBRankBean bean : list){
				ps.setString(1, bean.getUid());
				ps.setString(2, bean.getTaobaoNick());
				ps.setDouble(3, bean.getJfbNum());
				ps.setInt(4, bean.getRank());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	public static List<JFBRankBean> getUserMonthJFBRank(String uid){
		List<JFBRankBean> list = new ArrayList<JFBRankBean>();
		JedisHashManager hash = new JedisHashManager(Today_Month_Hash_JFBRank);
		String ranks = hash.get(uid);
		int rank = -1;
		if(ranks != null && !"".equals(ranks)){		
			rank = Integer.parseInt(ranks);
		}
		if(rank == -1){
			return list;
		}
		JedisSortedSetManager sort = new JedisSortedSetManager(Today_Month_Sort_JFBRank);
		Set<String> set = sort.zrange(Math.max(0, rank-5), Math.min(Integer.parseInt(sort.size1()+""),rank + 5));
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {
			Gson g = new Gson();
			JFBRankBean bean = g.fromJson(it.next(), JFBRankBean.class);
			list.add(bean);
		}  
		return list;
	}
	
	public static List<JFBRankBean> getUserTotalJFBRank(String uid){
		List<JFBRankBean> list = new ArrayList<JFBRankBean>();
		JedisHashManager hash = new JedisHashManager(Today_Total_Hash_JFBRank);
		String ranks = hash.get(uid);
		int rank = -1;
		if(ranks != null && !"".equals(ranks)){		
			rank = Integer.parseInt(ranks);
		}
		if(rank == -1){
			return list;
		}
		JedisSortedSetManager sort = new JedisSortedSetManager(Today_Total_Sort_JFBRank);
		Set<String> set = sort.zrange(Math.max(0, rank-5), Math.min(Integer.parseInt(sort.size()+""),rank + 5));
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {
			Gson g = new Gson();
			JFBRankBean bean = g.fromJson(it.next(), JFBRankBean.class);
			list.add(bean);
		} 
		return list;
	}
	
	public static List<JFBRankBean> getAllMonthJFBRank(){
		List<JFBRankBean> list = new ArrayList<JFBRankBean>();
		JedisSortedSetManager sort = new JedisSortedSetManager(Today_Month_Sort_JFBRank);
		Set<String> set = sort.zrange(0, 10);
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {
			Gson g = new Gson();
			JFBRankBean bean = g.fromJson(it.next(), JFBRankBean.class);
			list.add(bean);
		} 
		return list;
	}
	
	public static List<JFBRankBean> getAllTotalJFBRank(){
		List<JFBRankBean> list = new ArrayList<JFBRankBean>();
		JedisSortedSetManager sort = new JedisSortedSetManager(Today_Total_Sort_JFBRank);
		Set<String> set = sort.zrange(0, 10);
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {
			Gson g = new Gson();
			JFBRankBean bean = g.fromJson(it.next(), JFBRankBean.class);
			list.add(bean);
		} 
		return list;
	}
	
	
	public static String getNextMonth(String month) {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DATE, -1);    //得到前一天
		calendar.add(Calendar.MONTH, -1);    //得到前一个月
		String validityDate=df.format(calendar.getTime()); 
		System.out.println(validityDate);
		
		String des = "";
		String year = month.substring(0, 4);
		if(month.length()==7) {
			if(month.substring(5,7).equals("12")) des = (Integer.parseInt(year) + 1) + "-" + "01" ;
			else if(Integer.parseInt(month.substring(5, 7)) >= 9) 
			{
				// 开始时间月份数大于9时，结束时间月份+1
				des = month.substring(0,4) + "-" + (Integer.parseInt(month.substring(5, 7)) + 1)  ;
			}
			else 
			{
				// 开始时间月份数<=9时，结束时间月份+1后前置0，如：2013-02-01
				des = month.substring(0,4) + "-0" + (Integer.parseInt(month.substring(5, 7)) + 1)  ;
			}
		}
		return des;
	}
	
	public static long getUnixTimestemp(String date){
		long time = 0;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			Date sDt7 = sf.parse(date);
			time = sDt7.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		updateJFBRank();
//		updateJFBRankTable();
//		getUserTotalJFBRank("101694904");
//		JedisSortedSetManager sort = new JedisSortedSetManager(Today_Total_Sort_JFBRank);
//		sort.deleteKey();
		System.out.println(System.currentTimeMillis()-time);
		

	}
}
