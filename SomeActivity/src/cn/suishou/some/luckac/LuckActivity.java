package cn.suishou.some.luckac;

public enum LuckActivity {

	MCD10(1000 ,"9集分宝赢麦当劳10元现金券",1, "1131");
	
	/**
	 * 活动ID
	 */
	int activityId;
	
	/**
	 * 活动简介
	 */
	String activityDesc;
	
	/**
	 * 初始抽奖次数
	 */
	int initJoinTime = 0;
	
	/**
	 * 优惠券ID
	 */
	String couponId;
	
	LuckActivity(int id, String desc, int time, String couponId){
		this.activityId = id;
		this.activityDesc = desc;
		this.initJoinTime = time;
		this.couponId = couponId;
	}
}
