package cn.suishou.some.talk.dao;

import java.sql.SQLException;

import cn.suishou.some.talk.bean.Activity;

public interface ActivityDao {

	void saveActivity(Activity act);
	
	Activity getActById(Integer id);
	
	/**
	 * 根据活动的名称和开始、结束时间查询已经有活动的记录
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws SQLException
	 */
	Integer getActCountByNameAndTime(String name, String startTime, String endTime);
}
