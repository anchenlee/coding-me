package cn.youhui.api.activity;

import cn.youhui.manager.ActivityExceptionManager;
import cn.youhui.manager.GiftActivityManager;

public class GiftActivityExecutor extends ActivityExecutor{
	
	private static final int LIMIT_JFB_NUM = 50;            //最多领取集分宝数量
	
	@Override
	public void isException() {
		super.isException();
		if(GiftActivityManager.getInstance().isLimitToday(request.getUid()) || request.getJfbNum() > LIMIT_JFB_NUM){
			ActivityExceptionManager.getInstance().addException(request, activity);
		}
	}
}
