package cn.youhui.thread;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.JiFenBaoTradeManager;
import cn.youhui.manager.SignInManager;


public class UpdateSignStatusThread extends Thread{

	private static final Logger log = Logger.getLogger(UpdateSignStatusThread.class);
	private Connection conn = MySQLDBIns.getInstance().getConnection();
	
	private boolean isRun = true;
	
	private static UpdateSignStatusThread updateSignThread = null;
	
	private UpdateSignStatusThread(String name){
		super(name);
	}
	
	public static UpdateSignStatusThread getInstance(){
		if(updateSignThread == null)
			updateSignThread = new UpdateSignStatusThread("updateSign-Thread");
		return updateSignThread;
	}
	
	public void run(){
		log.info( Thread.currentThread().getName() + " starting.... ");
		while(isRun){
			try{
				List<String> list = SignInManager.getInstance().getNotSuccess(conn);
				if(list != null && list.size()>0){
					Map<String, Integer> map = JiFenBaoTradeManager.getInstance().getStatusold(list, SignInManager.code, conn);
					SignInManager.getInstance().updateStatus(map, conn);
					Thread.sleep(500);
				}else{
					Thread.sleep(3000);
				}
			}catch (Exception  e){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					log.error("updateSign Fail. " + e.getMessage(), e);
				}
				log.error("updateSign Fail. " + e.getMessage(), e);
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
