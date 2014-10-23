package cn.youhui.api.activity;

/**
 * 活动执行客户端
 * @author lijun
 * @since 2013-12-14
 */
public class ActivityClient {
	public static int execut(ActivityRequest request) throws Exception{
		String key = request.getActivityKey();
		ActivityExecutor executor = ActivityFactory.getExecutor(key);
		return executor.execut(request);
	}
}