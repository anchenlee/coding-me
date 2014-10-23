package cn.youhui.thread;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.ActivityMingxiManager;
import cn.youhui.manager.JiFenBaoTradeManager;

public class UpdateJFBStatusThread extends Thread{

	private static final Logger log = Logger.getLogger(UpdateJFBStatusThread.class);
	private Connection conn = MySQLDBIns.getInstance().getConnection();
	
	private boolean isRun = true;
	
	private static UpdateJFBStatusThread updateSignThread = null;
	
	private UpdateJFBStatusThread(String name){
		super(name);
	}
	
	public static UpdateJFBStatusThread getInstance(){
		if(updateSignThread == null)
			updateSignThread = new UpdateJFBStatusThread("updateJfbSta-Thread");
		return updateSignThread;
	}
	
	public void run(){
		log.info( Thread.currentThread().getName() + " starting.... ");
		while(isRun){
			try{
				List<String> list = ActivityMingxiManager.getInstance().getDisposeingList(conn);
				if(list != null && list.size()>0){
					Map<String, Integer> map = JiFenBaoTradeManager.getInstance().getStatus(list, conn);
					ActivityMingxiManager.getInstance().updateStatus(map, conn);
					Thread.sleep(500);
				}else{
					Thread.sleep(3000);
				}
			}catch (Exception  e){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					log.error("updateJfb Fail. " + e.getMessage(), e);
				}
				log.error("updateJfb Fail. " + e.getMessage(), e);
			}
		}
		MySQLDBIns.getInstance().release(conn);
		log.info(Thread.currentThread().getName() + " end. Variable:is_run=" + isRun);
	}
	
	public void kill(){
		isRun = false;
	}
	
	public void destory(){
		kill();
		MySQLDBIns.getInstance().release(conn);
		updateSignThread = null;
	}
}
