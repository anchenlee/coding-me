package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ShareItemDAO {
	private static ShareItemDAO instance=null;
	public static ShareItemDAO getInstance(){
		if(instance==null){
			instance=new ShareItemDAO();
		}
		return instance;
	}
	
	public Map<String,String> getByDiscountId(int discountId){
		Map<String,String> map=null;
		Connection conn=null;
		String str="";
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String  sql="select * from  youhui_discount.share_item where discount_id=?;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,discountId);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				map=new HashMap<String, String>();
				String title=rs.getString("title");
				String desc=rs.getString("desc");
				map.put("title", title);
				map.put("desc",desc);
				System.out.println(title);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release( conn);
		}
		return map;
	}
	
}
