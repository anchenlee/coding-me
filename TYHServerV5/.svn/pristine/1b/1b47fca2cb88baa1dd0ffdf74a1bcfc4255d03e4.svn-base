package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.youhui.bean.Fenhong;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.FenHongUtil;

public class FenhongManager {

	private static FenhongManager instance = null;
	
	public static FenhongManager getInstance(){
		if(instance == null){
			instance = new FenhongManager();
		}
		return instance;
	}
	
	private FenhongManager(){}
	
	/**
	 * 获取分红历史列表
	 * @param uid
	 * @return
	 */
	public Map<String ,List<Fenhong>> getFenhong(String uid){
		Map<String ,List<Fenhong>> fhhis = new LinkedHashMap<String, List<Fenhong>>();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.fenhong where uid = ? order by `update_time` desc;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String year = rs.getString("year");
				Fenhong fenhong = new Fenhong();
				fenhong.setYear(year);
				fenhong.setMonth(rs.getString("month"));
				fenhong.setFhJfbNum(rs.getInt("fh_jfb_num"));
				fenhong.setFhRate(rs.getDouble("fh_rate"));
				fenhong.setLevelRate(rs.getDouble("level_rate"));
				fenhong.setJfbNum(rs.getInt("jfb_num"));
				fenhong.setOuterAcId(rs.getString("outer_ac_id"));
				fenhong.setStatus(rs.getInt("status"));
				fenhong.setUid(rs.getString("uid"));
				fenhong.setCreateTime(rs.getLong("create_time"));
				fenhong.setUpdateTime(rs.getLong("update_time"));
				fenhong.setIcon(getLevelIcon(fenhong.getLevelRate()));
				
				if(!fhhis.containsKey(year)){
					fhhis.put(year, new ArrayList<Fenhong>());
				}
				fhhis.get(year).add(fenhong);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return fhhis;
	}
	
	public static Fenhong getFenhong(String uid,String year,String month){
		Fenhong fenhong = null;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.fenhong where uid = ? and year=? and month like ? ;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.setString(2, year);
			pstmt.setString(3, "%"+month);
//			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				fenhong = new Fenhong();
				fenhong.setYear(year);
				fenhong.setMonth(rs.getString("month"));
				fenhong.setFhJfbNum(rs.getInt("fh_jfb_num"));
				fenhong.setFhRate(rs.getDouble("fh_rate"));
				fenhong.setLevelRate(rs.getDouble("level_rate"));
				fenhong.setJfbNum(rs.getInt("jfb_num"));
				fenhong.setOuterAcId(rs.getString("outer_ac_id"));
				fenhong.setStatus(rs.getInt("status"));
				fenhong.setUid(rs.getString("uid"));
				fenhong.setCreateTime(rs.getLong("create_time"));
				fenhong.setUpdateTime(rs.getLong("update_time"));
//				fenhong.setIcon(getLevelIcon(fenhong.getLevelRate()));
				
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return fenhong;
	}
	
	
	public List<Fenhong> getFenhongList(String uid){
		List<Fenhong> fhhis = new ArrayList<Fenhong>();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.fenhong where uid = ? order by `update_time` desc;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String year = rs.getString("year");
				Fenhong fenhong = new Fenhong();
				fenhong.setYear(year);
				fenhong.setMonth(rs.getString("month"));
				fenhong.setFhJfbNum(rs.getInt("fh_jfb_num"));
				fenhong.setFhRate(rs.getDouble("fh_rate"));
				fenhong.setLevelRate(rs.getDouble("level_rate"));
				fenhong.setJfbNum(rs.getInt("jfb_num"));
				fenhong.setOuterAcId(rs.getString("outer_ac_id"));
				fenhong.setStatus(rs.getInt("status"));
				fenhong.setUid(rs.getString("uid"));
				fenhong.setCreateTime(rs.getLong("create_time"));
				fenhong.setUpdateTime(rs.getLong("update_time"));
				fenhong.setIcon(getLevelIcon(fenhong.getLevelRate()));
				
				fhhis.add(fenhong);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return fhhis;
	}
	
	
	public static String getUniqueId(String tradeId){
		String uniqueId = "";
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT activity_unique_id FROM youhui_cn_fanli.tyh_activity_join where id=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tradeId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				uniqueId = rs.getString("activity_unique_id");				
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return uniqueId;
		
	}
	
	public static Fenhong getFenhongFromOuterAcId(String outerAcId){
		Fenhong fenhong = null;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.fenhong where outer_ac_id = ? ;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, outerAcId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				String year = rs.getString("year");
				fenhong = new Fenhong();
				fenhong.setYear(year);
				fenhong.setMonth(rs.getString("month"));
				fenhong.setFhJfbNum(rs.getInt("fh_jfb_num"));
				fenhong.setFhRate(rs.getDouble("fh_rate"));
				fenhong.setLevelRate(rs.getDouble("level_rate"));
				fenhong.setJfbNum(rs.getInt("jfb_num"));
				fenhong.setOuterAcId(rs.getString("outer_ac_id"));
				fenhong.setStatus(rs.getInt("status"));
				fenhong.setUid(rs.getString("uid"));
				fenhong.setCreateTime(rs.getLong("create_time"));
				fenhong.setUpdateTime(rs.getLong("update_time"));
//				fenhong.setIcon(getLevelIcon(fenhong.getLevelRate()));
				
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return fenhong;
	}
	
	public String getLevelIcon(double levelRate){
		int level = FenHongUtil.getLevelByRate((int)levelRate);
		String icon = "";
		if(level == 0){
			icon = "yi@2x.png";
		}else if(level == 1){
			icon = "er@2x.png";
		}else if(level == 2){
			icon = "san@2x.png";
		}else if(level == 3){
			icon = "si@2x.png";
		}else if(level == 4){
			icon = "wu@2x.png";
		}else if(level == 5){
			icon = "liu@2x.png";
		}
		return icon;
	}
	
	public static void main(String[] args) {
	}
}
