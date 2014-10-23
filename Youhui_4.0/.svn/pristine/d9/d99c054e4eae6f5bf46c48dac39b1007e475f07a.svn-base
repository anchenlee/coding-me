package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.JobDetialBean;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;

/**
 * 后台任务管理数据操作类
 * @author YAOJIANBO
 *
 */
public class Admin_JobDetail_DAO 
{
	private static int pageSize = 10;
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_JobDetail_DAO.class);
	
	/**
	 * 管理员页面查询历史任务记录
	 * @param username
	 * @param page
	 * @return
	 */
	public static JobDetialBean getJobDetialBean(String id) 
	{
		JobDetialBean jobDetail = null;
		
		String sql = "SELECT * FROM `youhui_v4`.`job_detail` WHERE `id` = '" + id + "';";
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			rtst = prestmt.executeQuery();
			if (rtst.next()) 
			{
				jobDetail = new JobDetialBean();
				
				jobDetail.setId(rtst.getString("id"));
				jobDetail.setJobName(rtst.getString("jobname"));
				jobDetail.setUserName(rtst.getString("username"));
				jobDetail.setSuccessNum(rtst.getInt("success_num"));
				jobDetail.setAllNum(rtst.getInt("all_num"));
				jobDetail.setCreate_time(CodeUtil.getDateTimeStr(rtst.getLong("create_time")));
				jobDetail.setRemark(rtst.getString("remark"));
				jobDetail.setStatus(rtst.getInt("status"));
			}
		} 
		catch (Exception e) 
		{
			logger.error("Admin_JobDetail_DAO.getJobDetialBean error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rtst, prestmt, conn);
		}
		return jobDetail;
	}
	
	/**
	 * 管理员页面查询历史任务记录列表
	 * @param username
	 * @param page
	 * @return
	 */
	public static ArrayList<JobDetialBean> getJobDetialBeanList(String username, int page) 
	{
		ArrayList<JobDetialBean> jobDetail_List = new ArrayList<JobDetialBean>();
		
		String sql = "SELECT * FROM `youhui_v4`.`job_detail` ";
		if (username != null && !"".equals(username) && !username.equals("all")) 
		{
			sql = sql + " WHERE `username` = '" + username + "' ";
		}
		sql = sql + " ORDER BY `create_time` DESC  limit ?,?;";
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setInt(1, pageSize * (page - 1));
			prestmt.setInt(2, pageSize);	
			
			rtst = prestmt.executeQuery();
			while (rtst.next()) 
			{
				JobDetialBean jobDetail = new JobDetialBean();
				
				jobDetail.setId(rtst.getString("id"));
				jobDetail.setJobName(rtst.getString("jobname"));
				jobDetail.setUserName(rtst.getString("username"));
				jobDetail.setSuccessNum(rtst.getInt("success_num"));
				jobDetail.setAllNum(rtst.getInt("all_num"));
				jobDetail.setCreate_time(CodeUtil.getDateTimeStr(rtst.getLong("create_time")));
				jobDetail.setRemark(rtst.getString("remark"));
				jobDetail.setStatus(rtst.getInt("status"));
				
				jobDetail_List.add(jobDetail);
			}
		} 
		catch (Exception e) 
		{
			logger.error("Admin_JobDetail_DAO.getJobDetialBeanList error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rtst, prestmt, conn);
		}
		return jobDetail_List;
	}
	
	/**
	 * 管理员页面查询历史任务记录列表总页数
	 * @param username
	 * @param page
	 * @return
	 */
	public static int getJobDetialBeanListTotalPage(String username) 
	{
		int totalPage = 0;

		String sql = "SELECT count(`id`) AS count FROM `youhui_v4`.`job_detail` ";
		if (username != null && !"".equals(username) && !username.equals("all")) 
		{
			sql = sql + " WHERE `username` = '" + username + "' ";
		}
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			rtst = prestmt.executeQuery();
			
			int count = 0;
			if (rtst.next())
			{
				count = rtst.getInt("count");
			}
			
			totalPage = count / pageSize;
			int ys = count % pageSize;
			if(ys > 0)
			{
				totalPage++;
			}
		} 
		catch (Exception e) 
		{
			logger.error("Admin_JobDetail_DAO.getJobDetialBeanListTotalPage error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rtst, prestmt, conn);
		}
		return totalPage;
	}
	
	/**
	 * 任务结束，更新任务进展
	 * @param jobDetail
	 * @return
	 */
	public static boolean updateJobDetial(JobDetialBean jobDetail) 
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_v4`.`job_detail` SET `success_num` = ?,`remark` = ?,`status` = ? where `id` = ?;";
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setInt(1, jobDetail.getSuccessNum());
			prestmt.setString(2, jobDetail.getRemark());
			prestmt.setInt(3, jobDetail.getStatus());
			prestmt.setString(4, jobDetail.getId());
			
			int i = prestmt.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_JobDetail_DAO.updateJobDetial error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rtst, prestmt, conn);
		}
		return flag;
	}
	
	/**
	 * 新增任务单
	 * @param jobDetail
	 * @return
	 */
	public static boolean addJobDetial(JobDetialBean jobDetail) 
	{
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_v4`.`job_detail`(`id`,`jobname`,`username`,`success_num`,`all_num`,`create_time`,`remark`,`status`) VALUES (?,?,?,?,?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setString(1, jobDetail.getId());
			prestmt.setString(2, jobDetail.getJobName());
			prestmt.setString(3, jobDetail.getUserName());
			prestmt.setInt(4, jobDetail.getSuccessNum());
			prestmt.setInt(5, jobDetail.getAllNum());
			prestmt.setString(6, jobDetail.getCreate_time());
			prestmt.setString(7, jobDetail.getRemark());
			prestmt.setInt(8, jobDetail.getStatus());
			
			int i = prestmt.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_JobDetail_DAO.addJobDetial error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rtst, prestmt, conn);
		}
		return flag;
	}
	
	/**
	 * 删除任务单
	 * @return
	 */
	public static boolean delJobDetial(String job_id)
	{
		boolean flag = false;
		
		String sql = "DELETE FROM `youhui_v4`.`job_detail`  WHERE `id` = '" + job_id + "';";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_JobDetail_DAO.delJobDetial error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
}
