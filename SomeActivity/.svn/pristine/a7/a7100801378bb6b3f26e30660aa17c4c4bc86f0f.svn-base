package cn.suishou.some.fenhong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.suishou.some.db.SQL;

public class FenhongDAO {
	
	private static FenhongDAO instance;
	
	private FenhongDAO(){}

	public static FenhongDAO getInstance(){
		if(instance == null){
			instance = new FenhongDAO();
		}
		return instance;
	}

	/**
	 * 获取分红历史
	 * @param uid
	 * @return
	 */
	public Fenhong getFenhong(String uid, String year, String month){
		Fenhong fenhong = new Fenhong();
		Connection conn = null;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.fenhong where uid = ? and year = ? and month = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.setString(2, year);
			pstmt.setString(3, month);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				fenhong.setYear(rs.getString("year"));
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
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(null, conn);
		}
		return fenhong;
	}
	
	private String getLevelIcon(double levelRate){
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
}
