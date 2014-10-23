package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.UserMsgBean;
import com.netting.db.DataSourceFactory;


public class UserMsg_DAO 
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(UserMsg_DAO.class);
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	
	public static Map<String, UserMsgBean> getUser()
	{
		Map<String, UserMsgBean> map = new HashMap<String, UserMsgBean>();

		String sql = "SELECT * FROM `youhui_v3`.`user` where (taobao_nick is not null or taobao_nick != '')";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				UserMsgBean bean = new UserMsgBean();
				
				bean.setUid(rs.getString("uid"));
				bean.setTaobao_nick(rs.getString("taobao_nick"));
				bean.setSex(rs.getString("sex") == null ? "" : rs.getString("sex"));
				bean.setCity(rs.getString("location_city") == null ? "" : rs.getString("location_city"));
				bean.setProvince(rs.getString("location_province") == null ? "" : rs.getString("location_province"));
				
				map.put(rs.getString("uid"), bean);
			}

		} 
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getUser error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}

		return map;
	}

	public static Map<String, UserMsgBean> getWaihuiMsg(Map<String, UserMsgBean> map, int date) 
	{
		String sql = "SELECT ddusername, sum(`je`) as waihui FROM `youhui_cn_fanli`.`duoduo_mingxi` where `iswaihui`='1'";
		if(date > 0)
		{
			sql += " and date(now()) -" + date +" < date(addtime)";
		}
		sql += " group by ddusername";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				if(map.get(rs.getString("ddusername")) != null)
				{
					map.get(rs.getString("ddusername")).setWaihui(Double.parseDouble(df.format(rs.getDouble("waihui"))));
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getWaihuiMsg error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}

		return map;
	}

	public static Map<String, UserMsgBean> getFanliMsg(Map<String, UserMsgBean> map, int date) 
	{
		String sql = "SELECT ddusername, sum(`je`) as fanli FROM `youhui_cn_fanli`.`duoduo_mingxi` where `iswaihui`='0' and `istixian`='0'";
		if(date > 0)
		{
			sql += " and date(now()) -"+ date +" < date(addtime)";
		}
        sql += " group by ddusername";
        
        Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(map.get(rs.getString("ddusername")) != null)
				{
					map.get(rs.getString("ddusername")).setFanli(Double.parseDouble(df.format(rs.getDouble("fanli"))));
				}
			}
		} 
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getFanliMsg error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}

		return map;
	}
	
	public static Map<String, UserMsgBean> getTixianMsg(Map<String, UserMsgBean> map, int date)
	{
		String sql = "SELECT ddusername, sum(`je`) as tixian FROM `youhui_cn_fanli`.`duoduo_mingxi` where `istixian`='1'";
		if(date > 0)
		{
			sql += " and date(now()) -" + date + " < date(addtime)";
		}
		sql += " group by ddusername";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				if(map.get(rs.getString("ddusername")) != null)
				{
					map.get(rs.getString("ddusername")).setTixian(-Double.parseDouble(df.format(rs.getDouble("tixian"))));
				}
			}
		} 
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getTixianMsg error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return map;
	}
	
	public static Map<String, UserMsgBean> getYueMsg(Map<String, UserMsgBean> map, int date) 
	{
		String sql = "SELECT ddusername, sum(`je`) as yue FROM youhui_cn_fanli.duoduo_mingxi";
		if(date > 0)
		{
			sql += " where date(now()) -"+ date +" < date(addtime)";
		}
		sql += " group by ddusername";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(map.get(rs.getString("ddusername")) != null)
				{
					map.get(rs.getString("ddusername")).setYue(Double.parseDouble(df.format(rs.getDouble("yue"))));
				}
			}
		} 
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getYueMsg error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return map;
	}
	
	public static Map<String, UserMsgBean> getWaihuiMsg1(Map<String, UserMsgBean> map, int date) 
	{
		String sql = "SELECT outer_code, sum(`je`) as waihui from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code like 'B%'";
		if(date > 0)
		{
			sql += " and date(now()) -" + date + " < date(pay_time)";
		}
		sql += " group by outer_code";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(map.get(rs.getString("outer_code")) != null)
				{
					map.get(rs.getString("outer_code")).setWaihui(rs.getDouble("waihui"));
				}
			}
		} 
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getWaihuiMsg1 error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}

		return map;
	}

	public static Map<String, UserMsgBean> getFanliMsg1(Map<String, UserMsgBean> map, int date)
	{
		String sql = "SELECT outer_code, sum(`je`) as fanli from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code not like 'B%'";
		if(date > 0)
		{
			sql += " and date(now()) -" + date + " < date(pay_time)";
		}
        sql += " group by outer_code";
        
        Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
            
			while(rs.next())
			{
				if(map.get(rs.getString("outer_code")) != null)
				{
					map.get(rs.getString("outer_code")).setFanli(rs.getDouble("fanli"));
				}
			}
		} 
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getFanliMsg1 error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return map;
	}
	
	public static Map<String, UserMsgBean> getRankMsg(Map<String, UserMsgBean> map) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(System.currentTimeMillis());
		
		String sql = "SELECT * FROM `youhui_snsData`.`rankHistory`"+dateString;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(map.get(rs.getString("uid")) != null)
				{	
					map.get(rs.getString("uid")).setAllFanliRank(rs.getInt("rank_fanli_all"));
					map.get(rs.getString("uid")).setMonFanliRank(rs.getInt("rank_fanli_month"));
					map.get(rs.getString("uid")).setAllWaihuiRank(rs.getInt("rank_waihui_all"));
					map.get(rs.getString("uid")).setMonWaihuiRank(rs.getInt("rank_waihui_month"));
				}
			}

		} 
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getRankMsg error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return map;
	}
	
	public static String getTaobaonickByUid(String uid) 
	{
		String taobao_nick = "";
		
		String sql = "select taobao_nick from youhui_v3.user where uid=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			
			if(rs.next()) 
			{
				taobao_nick = rs.getString("taobao_nick");
			}
		} 
		catch (Exception e) 
		{
			logger.error("UserMsg_DAO.getTaobaonickByUid error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return taobao_nick;
	}
	
}
