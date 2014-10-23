package cn.youhui.api.activity;

import cn.youhui.manager.ActivityExceptionManager;
import cn.youhui.manager.SignInManager;

/**
 * 签到活动执行者
 * @author lijun
 * @since 2013-12-14
 */
public class SignInActivityExecutor extends ActivityExecutor{
	
	private static final int LIMIT_JFB_NUM = 30;   //最多领取集分宝数量
	
	@Override
	public void isException() {
		super.isException();
		if(SignInManager.getInstance().isLimitToday(request.getUid()) || request.getJfbNum() > LIMIT_JFB_NUM){
			ActivityExceptionManager.getInstance().addException(request, activity);
		}
	}
	
}
