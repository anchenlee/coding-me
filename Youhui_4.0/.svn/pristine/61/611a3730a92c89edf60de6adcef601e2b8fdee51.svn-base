package com.netting.dao.admin;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.ActivityMingxi;
import com.netting.bean.Activity_Bean;
import com.netting.bean.TaoBaoUserBean;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


/**
 * 发送JFB数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_SendJFB_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_SendJFB_DAO.class);
	
	/**
	 * 获取终端登录的淘宝用户信息
	 * @param tag_id
	 * @return
	 */
	public static TaoBaoUserBean getTaoBaoUserBean(String nick)
	{
		TaoBaoUserBean bean = null;
		String sql = "SELECT * FROM `youhui_v3`.`user` WHERE `taobao_nick` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, nick);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new TaoBaoUserBean();
				
				bean.setId(rs.getString("uid"));
				bean.setImei(rs.getString("imei"));
				bean.setImsi(rs.getString("imsi"));
				bean.setTaobao_nick(rs.getString("taobao_nick"));
				bean.setTaobao_uid(rs.getString("taobao_uid"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SendJFB_DAO.getTaoBaoUserBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 检查用户是否能够参加这个活动
	 * @param uid
	 * @param activityId
	 * @return
	 */
	public static int checkUserToActivity(String uid, String activity_id)
	{
		int resp = 0;
		String sql = "SELECT * FROM `youhui_cn_fanli`.`tyh_activity` WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, activity_id);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				long startTime = rs.getLong("start_time");
				long endTime = rs.getLong("end_time");
				
				// 检查活动时间
				long nowTime = System.currentTimeMillis();
				if(nowTime < startTime)
				{
					resp = 1;          //活动未开始
					return resp;
				}
				if(nowTime > endTime)
				{
					resp = 2;            //活动已结束
					return resp;
				}
				
				// 检查活动规则
				int frequency = rs.getInt("frequency");
				long lastJoinTime = getLastJoinTime(uid, activity_id);
				boolean allow = false;
				if(frequency == 1)
				{
					//只能参加一次
					if(lastJoinTime == 0)
					{
						allow = true;
					}
				}
				else if(frequency == 2)
				{     //一天参加一次
					if(checkDay(lastJoinTime))
					{
						allow = true;
					}
				}
				else if(frequency == 3)
				{
					if(checkWeek(lastJoinTime))
					{  //一周参加一次
						allow = true;
					}
				}
				else if(frequency == 4)
				{      //每次都可以参加
					allow = true;
				}
				
				if(!allow)
				{
					resp = 3;            //已参加过
				}
				else
				{
					String rule = rs.getString("rule");
					if(!checkRule(uid, rule))
					{
						resp = 4;       //不满足条件
					}
					else
					{
						resp = 5;
					}
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SendJFB_DAO.checkUserToActivity error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return resp;
	}
	
	/**
	 * 获取用户上次参加活动的时间
	 * @param uid
	 * @param activityId
	 * @return
	 */
	public static long getLastJoinTime(String uid, String activity_id)
	{
		long lastTime = 0l;
		String sql = "SELECT `timestamp` FROM `youhui_cn_fanli`.`tyh_activity_join` WHERE `uid` = ? AND `activity_id` = ? order by `timestamp` desc;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, activity_id);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				lastTime = rs.getLong("timestamp");
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SendJFB_DAO.getLastJoinTime error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return lastTime;
	}
	
	/**
	 * 用户参加活动，向用户发放集分宝
	 * @param uid
	 * @param activityId
	 * @return
	 */
	public static boolean joinActivity(Activity_Bean activity, String uid, int runningDay)
	{
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_cn_fanli`.`tyh_activity_join` (`id`,`uid`,`date`,`running_days`,`timestamp`,`jfb_num`,`activity_id`,`activity_name`,`status`) " +
						" VALUES (?,?,?,?,?,?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String today = sdf.format(new Date());
			
			String tradeId = null;
			int resp = -1;
			int n = 0;
			while (resp < 0 && n++ < 10)
			{
				tradeId = getTradeId(uid);
				ps.setString(1, tradeId);
				ps.setString(2, uid);
				ps.setString(3, today);
				ps.setInt(4, runningDay);
				ps.setLong(5, System.currentTimeMillis());
				ps.setInt(6, activity.getJfbNum());
				ps.setString(7, activity.getId());
				ps.setString(8, activity.getName());
				ps.setInt(9, 2);
				
				resp = ps.executeUpdate();
			}
			
			if (resp > 0)
			{
				ActivityMingxi bean = new ActivityMingxi();
				
				bean.setActivityId(activity.getId());
				bean.setEvent(activity.getName());
				bean.setJfbNum(activity.getJfbNum());
				bean.setUid(uid);
				bean.setTradeId(tradeId);
				bean.setStatus(5);
				
				if (insertJoinActivityMinxi(bean))
				{
					flag = true;
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SendJFB_DAO.joinActivity error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 插入参加活动获取的集分宝的发放明细
	 * @param uid
	 * @param activityId
	 * @return
	 */
	public static boolean insertJoinActivityMinxi(ActivityMingxi bean)
	{
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_cn_fanli`.`tyh_activity_mingxi` (`uid`,`jfb_num`,`event`,`activity_id`,`trade_id`,`timestamp`,`status`) " +
						" VALUES (?,?,?,?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getUid());
			ps.setInt(2, bean.getJfbNum());
			ps.setString(3, bean.getEvent());
			ps.setString(4, bean.getActivityId());
			ps.setString(5, bean.getTradeId());
			ps.setLong(6, System.currentTimeMillis());
			ps.setInt(7, bean.getStatus());
			
			int i = ps.executeUpdate();
			if(i > 0)
			{
				flag = true;
			}
			
		}
		catch (Exception e) 
		{
			logger.error("Admin_SendJFB_DAO.insertActivityMinxi error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	
	/**
	 * 检测lastTime是否在今天,在则返回false,不在则返回true
	 * @param lastTime
	 * @return
	 * @throws ParseException 
	 */
	public static boolean checkDay(long lastTime) throws ParseException
	{
		long diffDays = CodeUtil.differ(new Date(), new Date(lastTime));
		if(diffDays == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 检测lastTime是否在本周,在则返回false,不在则返回true
	 * @param lastTime
	 * @return
	 * @throws ParseException 
	 */
	public static boolean checkWeek(long lastTime) throws ParseException
	{
		if (CodeUtil.differ(new Date(), new Date(lastTime)) >= 7)
		{
			return true;
		}
		else 
		{
		    return false;
		}
	}
	
	/**
	 * 检查该用户是否符合该活动规则
	 * @param uid
	 * @param rule
	 * @return
	 */
	public static boolean checkRule(String uid, String rule)
	{
		if("all".equals(rule))
		{
			return true;
		}
		else
		{
			//根据不同的rule检测uid是否满足条件
			return false;
		}
	}
	
	public static String getTradeId(String uid)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		int i = 8 - uid.length();
		String uuid = uid;
		if(i >= 0)
		{
			for(;i>0;i--)
			{
				uuid = "0"+uuid;
			}
		}
		else
		{
			uuid = uuid.substring(-i, uuid.length());
		}
		String cuid = "";
		for(int j = 0; j < (uuid.length() / 2); j++)
		{
			int s = Integer.parseInt(String.valueOf(uuid.charAt(j)));
			int e = Integer.parseInt(String.valueOf(uuid.charAt(uuid.length() - j - 1)));
			cuid += (s + e) % 10;
		}
		String rank = getRankNum(8);
		return today + rank + cuid;
	}
	
	public static String getRankNum(int size)
	{
		StringBuffer sb = new StringBuffer();
		Random rm = new Random();
		for(int i = 0;i < size; i++)
		{
			sb.append(rm.nextInt(10));
		}
		return sb.toString();
	}
	
	/**
	 * 批量发送集分宝
	 * @return
	 */
	public static String sendMultJFB(InputStream fi)
	{
		String respContent = "";
		
		try
		{
			Workbook workbook = Workbook.getWorkbook(fi);
			Sheet[] sheets = workbook.getSheets();
			
			if(sheets == null || sheets.length < 2)
			{
				respContent ="表格有误！";
			}
			
			ArrayList<Admin_SendJFB_DAO.User_JFB> User_JFB_List = Admin_SendJFB_DAO.getUser_JFBs(sheets[0]);
			Activity_Bean activity = Admin_SendJFB_DAO.getActivity(sheets[1]);
			if (User_JFB_List != null && activity != null)
			{
				String notExistTaobaoNick = "";
				String failTaobaoNick = "";
				String okTaobaoNick = "";
				for (Admin_SendJFB_DAO.User_JFB ujfb : User_JFB_List)
				{
					String uid = null;
					TaoBaoUserBean user = Admin_SendJFB_DAO.getTaoBaoUserBean(ujfb.getTaobaoNick());
					if (null != user)
					{
						uid = user.getId();
					}
					if (uid != null && !"".equals(uid))
					{
						activity.setJfbNum(ujfb.getJfbNum());
						
						int checkResp = Admin_SendJFB_DAO.checkUserToActivity(uid, activity.getId());
						
						if(checkResp == 5)
						{
							boolean flag = Admin_SendJFB_DAO.joinActivity(activity, uid, 0);
		    		    	if(!flag)
		    		    	{
		    		    		failTaobaoNick = failTaobaoNick + "," + ujfb.getTaobaoNick();
		    		    	}
		    		    	else
		    		    	{
		    		    		okTaobaoNick = okTaobaoNick + "," + ujfb.getTaobaoNick();
		    		    	}
						}
						else if(checkResp == 1)
						{
							respContent = "活动还未开始;";
							break;
						}
						else if(checkResp == 2)
						{
							respContent = "活动已结束;";
							break;
						}
						else if(checkResp == 3)
						{
							respContent = respContent + ";" + ujfb.getTaobaoNick() + " 已经参加过此活动";
							continue;
						}
						else if(checkResp == 4)
						{
							respContent = respContent + ";" + ujfb.getTaobaoNick() + " 不被允许参加此活动";
							continue;
						}
					}
					else
					{
						notExistTaobaoNick = notExistTaobaoNick + "," + ujfb.getTaobaoNick();
						continue;
					}
				}
				
				if (!okTaobaoNick.equals(""))
				{
					respContent = respContent + ";发送成功的淘宝用户:" + okTaobaoNick + ";";
				}
				if (!failTaobaoNick.equals(""))
				{
					respContent = respContent + ";发送失败的淘宝用户:" + failTaobaoNick + ";";
				}
				if (!notExistTaobaoNick.equals(""))
				{
					respContent = respContent + "不存在的淘宝用户:" + notExistTaobaoNick + ";";
				}
			}
		}
		catch (Exception e)
		{
			respContent = "文件有误！";
		}
		finally
		{
			if (fi != null) 
			{
				try 
				{
					fi.close();
				} 
				catch (IOException e) 
				{
					respContent = "文件有误！";
				}
			}
		}
		
		return respContent;
	}
	
	/**
	 * 读取excel文件，获取用户与集分宝数的对应关系列表
	 * @param sheet
	 * @return
	 */
	public static ArrayList<User_JFB> getUser_JFBs(Sheet sheet)
	{
		ArrayList<User_JFB> list = new ArrayList<User_JFB>();
		if(sheet != null)
		{
			int row = sheet.getRows();
			for(int i = 1;i < row; i++)
			{        
				//第一行不读
				User_JFB uj = new Admin_SendJFB_DAO().new User_JFB();
				String userNick = CodeUtil.getCellValue(sheet.getCell(0, i));
				if(userNick != null && !userNick.equals(""))
				{
					uj.setTaobaoNick(userNick);
					uj.setJfbNum(Integer.parseInt(CodeUtil.getCellValue(sheet.getCell(1, i))));
					list.add(uj);
				}
			}
		}
		else
		{
			list = null;
		}
		
		return list;
	}
	
	/**
	 * 读取excel文件，获取此次发放集分宝的活动数据
	 * @param sheet
	 * @return
	 */
	public static Activity_Bean getActivity(Sheet sheet)
	{
		Activity_Bean activity = null;
		if(sheet != null && sheet.getRows() == 2)
		{
			activity = new Activity_Bean();
			activity.setId(CodeUtil.getCellValue(sheet.getCell(1, 0)));
			activity.setName(CodeUtil.getCellValue(sheet.getCell(1, 1)));
		}
		return activity;
	}
	
	/**
	 * 批量发送集分宝时，用户与集分宝数的对应关系
	 * @author YAOJIANBO
	 *
	 */
	public class User_JFB
	{
		private String taobaoNick;
		private int jfbNum;
		
		public User_JFB() {
			super();
		}
		
		public String getTaobaoNick() {
			return taobaoNick;
		}
		public void setTaobaoNick(String taobaoNick) {
			this.taobaoNick = taobaoNick;
		}
		public int getJfbNum() {
			return jfbNum;
		}
		public void setJfbNum(int jfbNum) {
			this.jfbNum = jfbNum;
		}
	}
}
