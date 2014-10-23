package cn.youhui.shareapp;

import cn.youhui.bean.Activity;
import cn.youhui.manager.ActivityJoinManager;




/**
 * 分享软件活动
 * @author lijun
 * @since 2014-1-11
 */
public class ShareAppActivityThread extends Thread{
	
	public static final String tgActivityId = "20";
	
	private static final String tgActivityName = "分享软件成功，送50集分宝";                //老用户活动
	
	private static final int tgJfbNum = 50;
	
	public static final String newActivityId = "22";
	
	private static final String newActivityName = "分享软件产生的新用户";            //新用户活动
	
	private static final int newJfbNum = 50;
	
	

	private String uid ;
	
	private String fromUid;
	
	
	public ShareAppActivityThread(String uid, String fromUid){
		this.uid = uid;
		this.fromUid = fromUid;
	}
	
	public void run(){
		
		long start = 0l;
		long end = 2000000000000l;
		if(System.currentTimeMillis() > end || System.currentTimeMillis() < start){    
			return;
		}
		
		Activity tgActivity = new Activity(tgActivityId,tgActivityName,tgJfbNum);
		if(ActivityJoinManager.getInstance().join(tgActivity, fromUid, uid, 5) == 1){
			ShareRecordManager.getInstance().setHasGet(uid, tgJfbNum);
		}
		
        Activity newActivity = new Activity(newActivityId,newActivityName,newJfbNum);
		ActivityJoinManager.getInstance().join(newActivity, uid, 5);
		
	}
	
}
