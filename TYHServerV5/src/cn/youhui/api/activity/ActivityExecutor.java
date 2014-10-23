package cn.youhui.api.activity;

import cn.youhui.bean.Activity;
import cn.youhui.manager.ActivityJoinManager;
import cn.youhui.manager.ActivityManager;
import cn.youhui.ramdata.UserJFBAccountCacher;

/**
 * 活动执行者父类
 * @author lijun
 * @since 2013-12-14
 */
public class ActivityExecutor {
	
	Activity activity = null;
	ActivityRequest request = null;
	
	public int execut(ActivityRequest request){
		if(request == null){
			return ActivityConfig.ACTIVITY_EXCEPTION;
		}
		this.request = request;
		this.activity = ActivityManager.getInstance().getActivityByKey(request.getActivityKey());
		int ret = check();
		if(ActivityConfig.ACTIVITY_CHECK_PASS == ret){
			ret = join();
			if(ActivityConfig.ACTIVITY_JOIN_SUCCESS == ret){
				UserJFBAccountCacher.getInstance().addGainNum(request.getUid(), activity.getJfbNum());
				isException();
			}
		}
		return ret;
	}
	
	public int check(){
		return ActivityManager.getInstance().joinActivity(request.getActivityKey(), request.getUid());
	}
	
	public int join(){
		if(activity.getJfbNum() == 0){
			activity.setJfbNum(request.getJfbNum());
		}
		int r = ActivityJoinManager.getInstance().join(activity, request.getUid(), request.getRunningDays(), request.getActivityUniqueId());
		if(r == 1){
			return ActivityConfig.ACTIVITY_JOIN_SUCCESS; 
		}else if(r == 2){
			return ActivityConfig.ACTIVITY_UNIQUEID_EXSIT;
		}else{
			return ActivityConfig.ACTIVITY_SERVER_ERROR;
		}
	}
	
	public void isException(){}
}
