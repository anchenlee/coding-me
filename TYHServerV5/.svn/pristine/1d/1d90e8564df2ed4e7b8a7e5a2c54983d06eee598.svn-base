package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.youhui.bean.SuiShouAction;
import cn.youhui.bean.SuperDays;
import cn.youhui.utils.SuiShouActionUtil;

public class SuperDaysDAO {

	public static int STATUS_IN_USE2=2;//还未展示预告
	public static int STATUS_IN_USE=1;//已经展示预告
	public static int STATUS_OLD=0;
	
	private static SuperDaysDAO instance=null;
	public static SuperDaysDAO getInstance(){
		if(instance ==null){
			instance =new SuperDaysDAO();
		}
		return instance;
	}
	private SuperDaysDAO(){}
	
	public SuperDays getInfo(String platform,int status){
		
		SuperDays sd=null;
		Connection conn=null;
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="select * from youhui_discount.super_days where status=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,status);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				sd=new SuperDays();
				sd.setChayeIcon(rs.getString("chaye_icon"));
				SuiShouAction chaye=new SuiShouAction();
				SuiShouAction yugao=new SuiShouAction();
				chaye.setUrl(SuiShouActionUtil.getSuiShouActionUrl(platform,rs.getString("chaye_action_type"), rs.getString("chaye_action_val")));
				sd.setChayeSuiShouAction(chaye);
				sd.setChayeWhRatio(rs.getString("chaye_wh_ratio"));
				sd.setYugaoDisountingTips(rs.getString("yugao_disounting_tips"));
				sd.setYugaoDisountingTitle(rs.getString("yugao_disounting_title"));
				sd.setYugaoIcon(rs.getString("yugao_icon"));
				yugao.setUrl(rs.getString("yugao_ss_action_url"));
				sd.setYugaoSuiShouAction(yugao);
				sd.setYugaoWhRatio(rs.getString("yugao_wh_ratio"));
				sd.setStatus(rs.getInt("status"));
			}
			
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return sd;
	}
	
}
