package cn.suishou.some.talk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.suishou.some.db.SQL;
import cn.suishou.some.talk.dao.ActivityDao;
import cn.suishou.some.talk.bean.Activity;

public class ActivityDaoImpl implements ActivityDao {
	
	private static Logger logger = Logger.getLogger(ActivityDaoImpl.class);

	@Override
	public void saveActivity(Activity act) {
		Connection conn = SQL.getInstance().getConnection();
		String sql = "insert into youhui_talk.activity(name,topic,startTime,endTime) values(?,?,?,?) ";
		String sql2 = "select count(1) as c from youhui_talk.activity where name=? and topic=? and startTime=? and endTime=?";
		PreparedStatement preStat = null;
		
		try {
			preStat = conn.prepareStatement(sql2);
			preStat.setString(1,act.getActName());
			preStat.setString(2,act.getActTopic());
			preStat.setString(3, act.getStartTime());
			preStat.setString(4, act.getEndTime());
			ResultSet result = preStat.executeQuery();
			int num = 0;
			while (result.next()){
				num = result.getInt("c");
			}
			
			if (!(num > 0)){
				preStat = conn.prepareStatement(sql);
				preStat.setString(1, act.getActName());
				preStat.setString(2, act.getActTopic());
				preStat.setString(3, act.getStartTime());
				preStat.setString(4, act.getEndTime());
				preStat.execute();
			}else {
				logger.info(act.toString() + "，此活动已存在");
			}
		} catch (SQLException e) {
			logger.error(act);
			e.printStackTrace();
		} finally {
			SQL.getInstance().release(preStat, conn);
		}
	}

	@Override
	public Activity getActById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getActCountByNameAndTime(String name, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

}