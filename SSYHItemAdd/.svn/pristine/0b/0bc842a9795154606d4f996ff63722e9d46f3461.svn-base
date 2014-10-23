package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.Assess;
import cn.youhui.bean.AssessOption;
import cn.youhui.platform.db.SQL;
/**
 * 
 * @author 
 */
public class AdminAssessOptionDAO
{
	
	private final static String OPTION_STATUS_NORMAL = "1";
	private final static String OPTION_STATUS_DELETE = "0";
	
	/**
	 * 增加反馈意见选项
	 * @param AssessOption
	 * @return
	 */
	public static  Boolean addAssessOption(AssessOption ao)
	{
		Boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			String sql = "insert into youhui_datamining.tyh_assess_option(content,is_show,enable,create_time,update_time) values (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ao.getContent());
			ps.setString(2, ao.getIsShow());
			ps.setString(3, ao.getEnable());
			ps.setLong(4, ao.getCreateTime());
			ps.setLong(5, ao.getUpdateTime());
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
	
	public static  AssessOption getAssessOptionById(String id)
	{
		Connection conn=null;
		AssessOption ao = null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select id,content,is_show,enable,create_time,update_time from youhui_datamining.tyh_assess_option where  id = ?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				ao = new AssessOption();
				ao.setId(rs.getInt("id"));
				ao.setContent(rs.getString("content"));
				ao.setIsShow(rs.getString("is_show"));
				ao.setEnable(rs.getString("enable"));
				ao.setCreateTime(rs.getLong("create_time"));
				ao.setUpdateTime(rs.getLong("update_time"));
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return ao;
	}
	
	public static  Boolean updateAssessOption(AssessOption ao)
	{
		Boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			String sql = "update youhui_datamining.tyh_assess_option set content = ?, is_show = ? ,update_time = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ao.getContent());
			ps.setString(2, ao.getIsShow());
			ps.setLong(3, ao.getUpdateTime());
			ps.setInt(4, ao.getId());
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
	
	public static  Boolean deleteAssessOption(String id)
	{
		Boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			String sql = "update youhui_datamining.tyh_assess_option set enable = ?,update_time = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, OPTION_STATUS_DELETE);
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
	
	public static  List<AssessOption> getAssessOptionList()
	{
		Connection conn=null;
		AssessOption ao = null;
		List<AssessOption> list = new ArrayList<AssessOption>();
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select id,content,is_show,enable,create_time,update_time from youhui_datamining.tyh_assess_option where enable = 1";
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				ao = new AssessOption();
				ao.setId(rs.getInt("id"));
				ao.setContent(rs.getString("content"));
				ao.setIsShow(rs.getString("is_show"));
				ao.setEnable(rs.getString("enable"));
				ao.setCreateTime(rs.getLong("create_time"));
				ao.setUpdateTime(rs.getLong("update_time"));
				list.add(ao);
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return list;
	}
}
