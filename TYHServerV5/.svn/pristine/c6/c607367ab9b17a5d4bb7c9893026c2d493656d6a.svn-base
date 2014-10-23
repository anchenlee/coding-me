package cn.youhui.jfbhis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.List;
import cn.youhui.bean.ActivityJoin;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.JiFenBaoMXManager;
import cn.youhui.utils.FenHongUtil;
import cn.youhui.utils.StringUtils;


/**
 * 得到集分宝历史记录 标题信息
 * @author hufan
 * @since 2014-9-17
 */
public class JFBHisTitleDao {
	private DecimalFormat ddf = new DecimalFormat("0.00");
	
	public static void main(String[] args) {

	}

	private static JFBHisTitleDao instance=null;
	public static JFBHisTitleDao getInstance(){
		if(instance==null){
			instance=new JFBHisTitleDao();
		}
		return instance;
	}
	
	/**
	 * 通过uid 得到 nickname
	 * @param uid
	 * @return
	 */
	public String getNicknameByUid(String uid){
		String nickname=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySQLDBIns.getInstance().getConnection();
			String sql="select `taobao_nick` from `youhui_v3`.`user` where `uid`=? ;";
			ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			rs=ps.executeQuery();
			if(rs.next()){
				nickname=rs.getString("taobao_nick");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		return nickname;
	}
	/**
	 * 通过uid 得到 头像
	 * @param uid
	 * @return
	 */
	public String getImgByUid(String uid){
		String taobaoUid="";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySQLDBIns.getInstance().getConnection();
			String sql="select `taobao_uid` from `youhui_v3`.`user` where `uid`=? ;";
			ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			rs=ps.executeQuery();
			if(rs.next()){
				taobaoUid=rs.getString("taobao_uid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		if(StringUtils.isEmpty(taobaoUid)){
			return "http://static.etouch.cn/suishou/ad_img/taobao_icon.png";
		}else{
			return "http://wwc.taobaocdn.com//avatar/getAvatar.do?userId=" + taobaoUid + "&width=160&height=160&type=sns";
		}
	}
	/**
	 * 得到等级
	 * @param uid
	 * @return
	 */
	public int getLevel(String uid){
		int level=-1;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=MySQLDBIns.getInstance().getConnection();
			String sql="select `jfb_num` from `youhui_v3`.`user_last_jfb` where `uid`=? ;";
			ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			rs=ps.executeQuery();
			if(rs.next()){
				level=FenHongUtil.getLevelImgByJFB(rs.getInt("jfb_num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		return level;
	}
	
	/**
	 * 通过uid 得到 等级图片
	 * @param uid
	 * @return
	 */
	public String getLevelImgByUid(String uid){
		int level=-1;
		level=JFBHisTitleDao.getInstance().getLevel(uid);
		switch(level){
		case 0:return JFBConfig.level_0;
		case 1:return JFBConfig.level_1;
		case 2:return JFBConfig.level_2;
		case 3:return JFBConfig.level_3;
		case 4:return JFBConfig.level_4;
		case 5:return JFBConfig.level_5;
		}
		return null;
	}
	/**
	 * 得到累计省钱数
	 * @return
	 */
	public double getAllSave(String uid){
		double ans=0;
		int totalJFBNum=0;
		List<ActivityJoin> jlist = JiFenBaoMXManager.getInstance().getGainJFB(uid);
		for(ActivityJoin j : jlist){
			totalJFBNum=totalJFBNum+j.getJfbNum();
		}
		ans=(double)totalJFBNum/100;
		return Double.parseDouble(ddf.format(ans));
	}
	
	
	/**
	 * 通过uid得到当月购物奖励
	 * @param uid
	 * @return
	 */
	public int getMonthTradeAwardNum(String uid){
		int tradeAward=0;
		Connection con=null;
		try {
			con=MySQLDBIns.getInstance().getConnection();
			tradeAward=JiFenBaoMXManager.getInstance().getMonthGainJfbNum(uid, con);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(con);
		}
		return tradeAward;
	}
	
	/**
	 *  通过 uid 得到分红比例
	 * @param uid
	 * @return
	 */
	public int getFenhongRatio(String uid){
		int level=JFBHisTitleDao.getInstance().getLevel(uid);
		int fhRatio=FenHongUtil.getRateByLevel(level);
		return fhRatio;
	}
	
	/**
	 * 通过 uid 得到加送比例
	 * @param uid
	 * @return
	 */
	public int getSendRatio(String uid){
		int jfbNum=JFBHisTitleDao.getInstance().getFlJFBNum(uid);
		int sendRatio=FenHongUtil.getRateByFanli(jfbNum);
		return sendRatio;
	}
	
	/**
	 * 得到返利的集分宝总数
	 * @param uid
	 * @return
	 */
	public int getFlJFBNum(String uid){
		int num=0;
		List<ActivityJoin> jlist = JiFenBaoMXManager.getInstance().getGainJFB(uid);
		for(ActivityJoin j : jlist){
			String type = j.getActivityId();
			if("fanli".equals(type) ){
				num+=j.getJfbNum();
			}
		}
		return num;
	}
}
