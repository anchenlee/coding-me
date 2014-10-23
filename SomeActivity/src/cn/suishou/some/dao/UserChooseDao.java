package cn.suishou.some.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.suishou.some.bean.UserChooseBean;
import cn.suishou.some.db.SQL;


/**
 * 管理用户选择表
 * @author hufan
 * @since 2014-9-17
 */
public class UserChooseDao {
	private static UserChooseDao userChoose=null;
	public static UserChooseDao getUserChooseDao(){
		if(userChoose==null){
			userChoose=new UserChooseDao();
		}
		return userChoose;
	}
	
	/**
	 * 添加用户选择记录
	 * @param ucBean
	 * @return
	 */
	public boolean addUserChoose(UserChooseBean ucBean){
		boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=SQL.getInstance().getConnection();
			String sql="insert into `weixin`.`user_choose` (`openid`,`weixin_nickname`,`choose`,`ip`,`time`) values(?,?,?,?,?);";
			ps=con.prepareStatement(sql);
			ps.setString(1, ucBean.getOpenId());
			ps.setString(2, ucBean.getNickName());
			ps.setString(3, ucBean.getChoose());
			ps.setString(4, ucBean.getIp());
			ps.setLong(5, System.currentTimeMillis());
			if(ps.executeUpdate()>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, con);
		}
		return flag;
	}
	
	/**
	 * 得到用户选择，0 没有选择 ；1 左边 ； 2 右边
	 * @param openId
	 * @return
	 */
	public int getUserChoose(String openId){
		int choose=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=SQL.getInstance().getConnection();
			String sql="select `choose` from `weixin`.`user_choose` where `openid`=?;";
			ps=con.prepareStatement(sql);
			ps.setString(1, openId);
			rs=ps.executeQuery();
			if(rs.next()){
				if("l".equals(rs.getString("choose"))){
					choose=1;
				}else if("r".equals(rs.getString("choose"))){
					choose=2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, con);
		}
		return choose;
	}
	
	
	public List<UserChooseBean> getWeixinUser(){
		List<UserChooseBean> ucbList=new ArrayList<UserChooseBean>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=SQL.getInstance().getConnection();
			String sql="select `weixin`.`weixin_user`.`openid`,`nickname`,`headimgurl`,`choose`,`time` from `weixin`.`weixin_user`,`weixin`.`user_choose` where `weixin`.`weixin_user`.openid=`weixin`.`user_choose`.openid order by time desc;";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				UserChooseBean ucb=new UserChooseBean();
				ucb.setOpenId(rs.getString("openid"));
				ucb.setNickName(rs.getString("nickname"));
				ucb.setImg(rs.getString("headimgurl"));
				String choose=rs.getString("choose");
				if("l".equals(choose)){
					ucb.setChoose("选择左");
				}else if("r".equals(choose)){
					ucb.setChoose("选择右");
				}else{
					ucb.setChoose("没有选择");
				}
				ucbList.add(ucb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, con);
		}
		return ucbList;
	}
}
