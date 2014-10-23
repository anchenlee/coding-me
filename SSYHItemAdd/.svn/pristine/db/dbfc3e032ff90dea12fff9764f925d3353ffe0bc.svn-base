package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.youhui.platform.db.SQL;

public class ShareItemDAO {

	private static ShareItemDAO instance=null;
	public static ShareItemDAO getInstance(){
		if(instance==null){
			instance=new ShareItemDAO();
		}
		return instance;
	}
	
	public boolean add(String title,String desc,int discountId){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="insert into youhui_discount.share_item (`title`,`desc`,`discount_id`) values (?,?,?);";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,title);
			st.setString(2,desc);
			st.setInt(3,discountId);
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
	
	public boolean update(String title,String desc,int discountId){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_discount.share_item set `title`=?,`desc`=? where `discount_id`=? ;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,title);
			st.setString(2,desc);
			st.setInt(3,discountId);
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
	public String getByDiscountId(int discountId,String format,Connection conn){
		String str="";
		try{
			String sql="select * from youhui_discount.share_item  where `discount_id`=? ;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,discountId);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				String title=rs.getString("title");
				String desc=rs.getString("desc");
				if(format.equals("json")){
					str="{\"title\":\""+title+"\",\"desc\":\""+desc+"\"}";
				}else if(format.equals("xml")){
					str="<title>"+title+"</title><desc>"+desc+"</desc>";
				}
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
		
		return str;
	}
	public void clean(){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="delete from youhui_discount.share_item ;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
	}
}
