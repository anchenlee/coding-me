package cn.suishou.some.talk.service.impl;

import cn.suishou.some.talk.dao.ActivityDao;
import cn.suishou.some.talk.dao.impl.ActivityDaoImpl;
import cn.suishou.some.talk.bean.Activity;
import cn.suishou.some.talk.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {

	ActivityDao actDao = new ActivityDaoImpl();
	
	@Override
	public void saveActivity(Activity act) {
		// TODO Auto-generated method stub
		this.actDao.saveActivity(act);
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
