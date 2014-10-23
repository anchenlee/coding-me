package cn.youhui.jfbhis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.DateUtil;


/**
 * 操作表：youhui_luckac.jfbhis
 * @author hufan
 * @since 2014-9-16
 */
public class JFBHisManager {
	
	private static JFBHisManager instance=null;
	public static JFBHisManager getInstance(){
		if(instance==null){
			instance=new JFBHisManager();
		}
		return instance;
	}
	
	/**
	 * 通过uid 得到集分宝历史记录
	 * @param uid
	 * @return
	 */
	public List<JFBMingxi> getJFBMingxi(String uid){
		List<JFBMingxi> jfbmxList=new ArrayList<JFBMingxi>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String type;
		try {
			con=MySQLDBIns.getInstance().getConnection();
			String sql="select `id`,`type` from `youhui_v3`.`jfbhis` where `uid`=? ;";
			ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			rs=ps.executeQuery();
			while(rs.next()){
				JFBMingxi mingxi=new JFBMingxi();
				type=rs.getString("type");
				if(type.equals(JFBConfig.DAY)){
					DayMingxi dmx=getDayMingxi(rs.getString("id"));
					mingxi.setType(JFBConfig.DAY);
					mingxi.setDayMingxi(dmx);
					jfbmxList.add(mingxi);
				}else if(type.equals(JFBConfig.TONGJI)){
					TongjiMingxi tmx=getTongjiMingxi(rs.getString("id"));
					mingxi.setType(JFBConfig.TONGJI);
					mingxi.setTongjiMingxi(tmx);
					jfbmxList.add(mingxi);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		return jfbmxList;
	}

	
	/**
	 * 通过id 得到daymingxi
	 * @param uid
	 * @return
	 */
	public DayMingxi getDayMingxi(String id){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		DayMingxi dmx=new DayMingxi();
		try {
			con=MySQLDBIns.getInstance().getConnection();
			String sql="select `id`,`describe`,`img`,`time` from `youhui_v3`.`jfbhis` where `id`=? ;";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				dmx.setId(rs.getString("id"));
				dmx.setDate(DateUtil.getMonthday(rs.getLong("time")));
				dmx.setDescribe(rs.getString("describe"));
				dmx.setImg(rs.getString("img"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		return dmx;
	}
	
	/**
	 * 通过id 得到TongjiMingxi
	 * @param uid
	 * @return
	 */
	public TongjiMingxi getTongjiMingxi(String id){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		TongjiMingxi tmx=null;
		try {
			con=MySQLDBIns.getInstance().getConnection();
			String sql="select `id`,`sign_num`,`fenhong_num`,`trade_num`,`time`,`describe`,`img` from `youhui_v3`.`jfbhis` where `id`=? ;";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				tmx=new TongjiMingxi();
				int total=rs.getInt("sign_num")+rs.getInt("fenhong_num")+rs.getInt("trade_num");
				TongjiBean tjb=new TongjiBean();
				tjb.setSign(JFBConfig.SIGN);
				tjb.setSignNum(rs.getInt("sign_num"));
				tjb.setFenhong(JFBConfig.FENHONG);
				tjb.setFenhongNum(rs.getInt("fenhong_num"));
				tjb.setTrade(JFBConfig.TRADE);
				tjb.setTradeNum(rs.getInt("trade_num"));
					
				tmx.setId(rs.getString("id"));
				tmx.setTotalNum(total);
				tmx.setDate(DateUtil.getMonth(rs.getLong("time")));
				tmx.setDescribe(tjb);
				tmx.setTishi(rs.getString("describe"));
				tmx.setImg(rs.getString("img"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		return tmx;
	}
	
	/**
	 * 添加 DayMingxi 记录
	 * @param uid
	 * @param dmx
	 * @return
	 */
	public boolean addDayMingxi(String uid,DayMingxi dmx){
		boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=MySQLDBIns.getInstance().getConnection();
			String sql="insert into `youhui_v3`.`jfbhis` (`uid`,`type`,`describe`,`img`,`time`) values(?,?,?,?,?);";
			ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, JFBConfig.DAY);
			ps.setString(3, dmx.getDescribe());
			ps.setString(4, dmx.getImg());
			ps.setLong(5, System.currentTimeMillis());
			if(ps.executeUpdate()>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		
		return flag;
	}
	
	/**
	 * 添加 TongjiMingxi 记录
	 * @param uid
	 * @param dmx
	 * @return
	 */
	public boolean addTongjiMingxi(String uid,TongjiMingxi tmx){
		boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=MySQLDBIns.getInstance().getConnection();
			String sql="insert into `youhui_v3`.`jfbhis` (`uid`,`type`,`time`,`sign_num`,`fenhong_num`,`trade_num`,`describe`,`img`) values(?,?,?,?,?,?,?,?);";
			ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, JFBConfig.TONGJI);
			ps.setLong(3, System.currentTimeMillis());
			ps.setInt(4, tmx.getDescribe().getSignNum());
			ps.setInt(5, tmx.getDescribe().getFenhongNum());
			ps.setInt(6, tmx.getDescribe().getTradeNum());
			ps.setString(7, tmx.getTishi());
			ps.setString(8, tmx.getImg());
			if(ps.executeUpdate()>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		return flag;
	}
}
