package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.Assess;
import cn.youhui.platform.db.SQL;
/**
 * 
 * @author 
 */
public class AdminAssessDAO
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
			conn = SQL.getInstance().getConnection();
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
			SQL.getInstance().release(null,conn);
		}
		return flag;
	}
	
	public static  List<Assess> getAssessListByItemIdAndDate(String itemId,String date,String type)
	{
		Connection conn=null;
		Assess assess= null;
		List<Assess> list = new ArrayList<Assess>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try{
			conn=SQL.getInstance().getConnection();
			String sql = "";
			if ("1".equals(type)) {
				sql= "select * from youhui_datamining.tyh_assess where  item_id = '"+itemId+"' and superdiscount_date=" + date + " and type = 1;";
			}else {
				sql= "select * from youhui_datamining.tyh_assess where  type = 2;";
			}
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				assess=new Assess();
				assess.setId(rs.getInt("id"));
				assess.setType(rs.getString("type"));
				assess.setOrderId(rs.getString("order_id"));
				assess.setItemId(rs.getString("item_id"));
				assess.setUID(rs.getString("uid"));
				String ct = rs.getString("complete_time");
				assess.setCompleteTime(("".equals(ct) || ct == null) ? "" : sdf.format(new Date(Long.parseLong(ct))));
				assess.setGoodsTitle(rs.getString("goods_title"));
				assess.setAssessList(rs.getString("assess_list") == null ? "" : rs.getString("assess_list"));
				assess.setAssess(rs.getString("assess")  == null ? "" : rs.getString("assess"));
				assess.setUserContact(rs.getString("user_contact")  == null ? "" : rs.getString("user_contact"));
				assess.setSuperDiscountDate(rs.getLong("superdiscount_date"));
				assess.setUpdateTime(rs.getLong("update_time"));
				assess.setCreateTime(rs.getLong("create_time"));
				assess.setIsRead(rs.getString("is_read"));
				list.add(assess);
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return list;
	}
	
	public static  int getAssessCountByItemIdAndDate(String itemId,String date,Connection conn)
	{
		int cnt = 0;
		try{
			String sql="select count(1) as cnt from youhui_datamining.tyh_assess where  item_id = '"+itemId+"' and superdiscount_date=" + date;
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				cnt = rs.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return cnt;
	}
	
	public static  List<Assess> getAllAssessList()
	{
		Connection conn=null;
		Assess assess= null;
		List<Assess> list = new ArrayList<Assess>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try{
			conn=SQL.getInstance().getConnection();
			String sql= "select * from youhui_datamining.tyh_assess;";
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				assess=new Assess();
				assess.setId(rs.getInt("id"));
				assess.setType(rs.getString("type"));
				assess.setOrderId(rs.getString("order_id"));
				assess.setItemId(rs.getString("item_id"));
				assess.setUID(rs.getString("uid"));
				String ct = rs.getString("complete_time");
				assess.setCompleteTime(("".equals(ct) || ct == null) ? "" : sdf.format(new Date(Long.parseLong(ct))));
				assess.setGoodsTitle(rs.getString("goods_title"));
				assess.setAssessList(rs.getString("assess_list") == null ? "" : rs.getString("assess_list"));
				assess.setAssess(rs.getString("assess")  == null ? "" : rs.getString("assess"));
				assess.setUserContact(rs.getString("user_contact")  == null ? "" : rs.getString("user_contact"));
				assess.setSuperDiscountDate(rs.getLong("superdiscount_date"));
				assess.setUpdateTime(rs.getLong("update_time"));
				assess.setCreateTime(rs.getLong("create_time"));
				assess.setIsRead(rs.getString("is_read"));
				list.add(assess);
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return list;
	}
	
	public static  Boolean changeAssessReadStatusById(String id,String status)
	{
		Boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			String sql = "update youhui_datamining.tyh_assess set is_read = ? ,update_time = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, String.valueOf(System.currentTimeMillis()));
			ps.setString(3, id);
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
			SQL.getInstance().release(null,conn);
		}
		return flag;
	}
	
}
