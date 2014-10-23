package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.SecondKill;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.SQL;

public class SecondKillDAO {

	private static SecondKillDAO instance=null;
	public static SecondKillDAO getInstance(){
		if(instance == null){
			instance=new SecondKillDAO();
		}
		return instance;
	}
	private SecondKillDAO(){}
	
	public List<SecondKill> getAll(){
		Connection conn=null;
		List<SecondKill> list=new ArrayList<SecondKill>();
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select * from youhui_discount.second_kill ;";
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				SecondKill sk=new SecondKill();
				sk.setId(rs.getInt("id"));
				sk.setType(rs.getString("type"));
				sk.setTypeId(rs.getInt("type_id"));
				sk.setKillNum(rs.getInt("kill_num"));
				sk.setKillPrice(rs.getInt("kill_price"));
				sk.setRemainNum(rs.getInt("remain_num"));
				sk.setKillStartTimestamp(rs.getLong("kill_start_timestamp"));
				sk.setKillEndTimestamp(rs.getLong("kill_end_timestamp"));
				list.add(sk);
			}
			
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return list;
	}
	
	public SecondKill getInfo(int typeId,String type){
		Connection conn=null;
		SecondKill sk=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select * from youhui_discount.second_kill where type_id=? and type=? ;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,typeId);
			st.setString(2,type);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				sk=new SecondKill();
				sk.setKillNum(rs.getInt("kill_num"));
				sk.setKillPrice(rs.getInt("kill_price"));
				sk.setRemainNum(rs.getInt("remain_num"));
				sk.setKillStartTimestamp(rs.getLong("kill_start_timestamp"));
				sk.setKillEndTimestamp(rs.getLong("kill_end_timestamp"));
			}
			
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return sk;
	}
	
	public boolean update(SecondKill sk,int id){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_discount.second_kill set kill_price=?,kill_num=?,kill_start_timestamp=?,kill_end_timestamp=? where type_id=? and type=?;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setDouble(1,sk.getKillPrice());
			st.setInt(2,sk.getKillNum());
			st.setLong(3,sk.getKillStartTimestamp());
			st.setLong(4,sk.getKillEndTimestamp());
			st.setInt(5,id);
			st.setString(6,param.secondkilltype);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return false;
	}
	
	public boolean del(int typeId){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="delete from youhui_discount.second_kill where type_id=? and type=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,typeId);
			st.setString(2, param.secondkilltype);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return false;
	}
	
	public boolean insertInfo(SecondKill sk){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="insert into youhui_discount.second_kill (type_id,type,kill_price,kill_num,kill_start_timestamp,kill_end_timestamp,remain_num) values (?,?,?,?,?,?,?);";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,sk.getTypeId());
			st.setString(2,sk.getType());
			st.setDouble(3,sk.getKillPrice());
			st.setInt(4,sk.getKillNum());
			st.setLong(5,sk.getKillStartTimestamp());
			st.setLong(6,sk.getKillEndTimestamp());
			st.setInt(7,sk.getRemainNum());
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return false;
	}
	
	public boolean setNum(int num,String type,int typeId){
		Connection conn=null;
		try{
			conn= SQL.getInstance().getConnection();
			String sql="update youhui_discount.second_kill set num=? where type_id=? and type=?; ";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,num);
			st.setInt(2,typeId);
			st.setString(3,type);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return false;
	}
	public void clean(){
		Connection conn=null;
		try{
			conn= SQL.getInstance().getConnection();
			String sql="delete from youhui_discount.second_kill ; ";
			PreparedStatement st=conn.prepareStatement(sql);
			st.executeUpdate();
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
	}
	
}
