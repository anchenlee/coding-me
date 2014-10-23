package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.Assess;
/**
 * 
 * @author 
 */
public class Admin_Assess_DAO
{
	/**
	 * 增加反馈意见
	 * @param Assess
	 * @return
	 */
	public static  Boolean addAssessBean(Assess as)
	{
		Boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_datamining.tyh_assess(type,orderId,itemId,UID,complete_time,goods_title,assess_list,assess,user_contact,create_time,update_time,superdiscount_date) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, as.getType());
			ps.setString(2, as.getOrderId());
			ps.setString(3, as.getItemId());
			ps.setString(4, as.getUID());
			ps.setString(5, as.getCompleteTime());
			ps.setString(6, as.getGoodsTitle());
			ps.setString(7, as.getAssessList());
			ps.setString(8, as.getAssess());
			ps.setString(9, as.getUserContact());
			ps.setLong(10, as.getCreateTime());
			ps.setLong(11, as.getUpdateTime());
			ps.setLong(12, as.getSuperDiscountDate());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release( conn);
		}
		return flag;
	}
	
	public static  List<Assess> getAssessListByItemIdAndDate(String itemId,String date)
	{
		Connection conn=null;
		Assess assess= null;
		List<Assess> list = new ArrayList<Assess>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date dt = sdf.parse(date);
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="select * from youhui_datamining.tyh_assess where  item_id = '"+itemId+"' and date=" + String.valueOf(dt.getTime());
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				assess=new Assess();
				assess.setId(rs.getInt("id"));
				
				list.add(assess);
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
}
