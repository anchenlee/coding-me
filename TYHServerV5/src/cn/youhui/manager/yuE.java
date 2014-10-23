package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import cn.youhui.dao.MySQLDBIns;

public class yuE {
	
	private static Logger log = Logger.getLogger(yuE.class);
	public yuE instance=null;
	
	public static yuE getInstance(){
		return new yuE();
	}
	public  void insertYuE(){
		ActivityMingxiManager am=ActivityMingxiManager.getInstance();
		List<String> list =searchUid();
		for(int i=0;i<list.size();i++){
			insertMid(list.get(i),am.getAll(list.get(i))[3]);
		}
	}
	
	public void insertMid(String uid,int yuE){
		Connection conn= MySQLDBIns.getInstance().getConnection();
		try {
			String sql="create table if not exists youhui_cn_fanli.yuE (`id` int(11) NOT NULL auto_increment, `uid` varchar(45) ,`yuE` int(11) ,PRIMARY KEY  (`id`) ) CHARSET=utf8";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			
			String sql2="insert into youhui_cn_fanli.yuE (uid,yuE) values ( '"+uid+"',"+yuE+" )";
			PreparedStatement statement2=conn.prepareStatement(sql2);
			statement2.executeUpdate();
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("Sql Execution Error!", e);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<String>  searchUid(){
		List<String> list=new ArrayList<String>();
		Set<String> set =new TreeSet<String>();
		Connection conn= MySQLDBIns.getInstance().getConnection();
		try {
			String sql="SELECT uid FROM youhui_cn_fanli.tyh_jifenbao_tradelist";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				if(!rs.getString("uid").equals("")){
					set.add(rs.getString("uid"));
				}
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("Sql Execution Error!", e);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		list.addAll(set);
		return list;
	}
	
	public static void main(String args[]){
		yuE.getInstance().insertYuE();
	}
}
