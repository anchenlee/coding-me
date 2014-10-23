package cn.youhui.api.activity;

/**
 * 活动返回结果
 * @author lijun
 * @since 2013-12-14
 */
public class ActivityConfig {
	
	public static final int ACTIVITY_EXCEPTION = 0;
	public static final int ACTIVITY_NOT_START = 1;
	public static final int ACTIVITY_HAS_END = 2;
	public static final int ACTIVITY_HAS_JOIN = 3;
	public static final int ACTIVITY_NOT_ALLOW = 4;
	public static final int ACTIVITY_JOIN_SUCCESS = 5;
	
	public static final int ACTIVITY_SERVER_ERROR = 6;
	
	public static final int ACTIVITY_UNIQUEID_EXSIT = 7;         //uniqueid  已经存在
	
	public static final int ACTIVITY_CHECK_PASS = 8;    //check通过
}
