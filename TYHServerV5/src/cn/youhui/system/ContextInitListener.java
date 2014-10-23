package cn.youhui.system;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cn.youhui.dao.JedisDBIns;
import cn.youhui.manager.GiftManager;
import cn.youhui.manager.JuHuaSuanManager;
import cn.youhui.manager.JuhuasuanBrandManager;
import cn.youhui.manager.JuhuasuanNewManager;
import cn.youhui.manager.TianTianTeJiaManager;
import cn.youhui.manager.UserRankManager;
import cn.youhui.ramdata.AllIpadTagCacher;
import cn.youhui.ramdata.AllTagCacher;
import cn.youhui.ramdata.AppConfigCacher;
import cn.youhui.ramdata.ClientMenuCacher;
import cn.youhui.ramdata.LikeItemCacher;
import cn.youhui.ramdata.RecomCacher;
import cn.youhui.ramdata.SaleCache;
import cn.youhui.ramdata.SaleDateCache;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.Tag2TagCacher;
import cn.youhui.ramdata.Tag4UserCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.TagInIpadCacher;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.ramdata.TagStatsCacher;
import cn.youhui.ramdata.Tagid2TagnameCacher;
import cn.youhui.ramdata.UserJFBAccountCacher;
import cn.youhui.ramdata.UserTagCacher;
import cn.youhui.ramdata.UserTagInIpadCacher;
import cn.youhui.thread.UpdateSignStatusThread;
import cn.youhui.utils.TPool;
import cn.youhui.utils.TimerAction;
import cn.youhui.utils.YHTimer;

@WebListener
public class ContextInitListener implements ServletContextListener {

	static Logger log = Logger.getLogger(ContextInitListener.class) ;

	public ContextInitListener() {
		// TODO Auto-generated constructor stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		String rootPath = arg0.getServletContext().getRealPath("/");
		PropertyConfigurator.configure(rootPath + "WEB-INF/log4j.properties");	
		
		JedisDBIns.getInstance() ;
//		TPool.getInstance().doit(initRamdata());	
		
//		UpdateJFBStatusThread.getInstance().start();
		
		
		YHTimer timer = new YHTimer();
		
////		timer.addAction(new UserRankTimerAction());
		timer.addAction(new JuHuaSuanBrandTimerAction());
//		timer.addAction(new UpUserJfbAccountTimerAction());
		
		timer.schedule();
		
//		timer.addAction(new RefreshRecomTimerAction());   //怎么没运行呢 
		
		/*if(Config.RUNNING_AS.equals(Config.RUNTP_RPC_JOB_SERVER)){
			QuartzFactory.getInstance().startQuartzScheduler();
			UMPDetectRPCService.getInstance().start() ;
		} else if(Config.RUNNING_AS.equals(Config.RUNTP_CLIENT_API_SERVER)){
			UMPDetectRPCClient.getInstance().start() ;
		}*/
	}
	
	class UserRankTimerAction extends TimerAction{
		{
			this.delay = 12*60*60*1000;
		}
		public void execut(){
			UserRankManager.getInstance().updatedata();
		}
	}
	
	
	class JuHuaSuanBrandTimerAction extends TimerAction{
		{
			this.delay = 60*60*1000;
		}
		public void execut(){
			
			JuhuasuanNewManager.updateJuhuasuanByTime();
			TianTianTeJiaManager.updateTejiaByTime();
			JuhuasuanBrandManager.updateBrandByTime();
//			JuHuaSuanManager.updateBrand();
		}
	}
	
	
	class RefreshRecomTimerAction extends TimerAction{
		{
			this.delay = 2*60*1000;
		}
		public void execut(){
			RecomCacher.getInstance().refresh();
		}
	}
	
	/**
	 * 更新集分宝帐户数据
	 * @author lijun
	 *
	 */
	class UpUserJfbAccountTimerAction extends TimerAction{
		{
			this.delay = 1000;
		}
		public void execut(){
			if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 3){           //每天一点过后更新
				System.out.println(System.currentTimeMillis());
//				UserJFBAccountCacher.getInstance().reload();
				try {
					String hostName = InetAddress.getLocalHost().getHostName();
					if(hostName.indexOf("71.57") > 0){        //只57一台更新
						UserJFBAccountCacher.getInstance().reload(0);
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		TPool.getInstance().shutdown();
		JedisDBIns.getInstance().destory();
		UpdateSignStatusThread.getInstance().destory();
		
		/*if(Config.RUNNING_AS.equals(Config.RUNTP_RPC_JOB_SERVER)){
			QuartzFactory.getInstance().stopQuzrtzScheduler();
			UMPDetectRPCService.getInstance().shutdown() ;
		} else if(Config.RUNNING_AS.equals(Config.RUNTP_CLIENT_API_SERVER)){
			UMPDetectRPCClient.getInstance().shutdown() ;
		}*/
	}
	
	protected Runnable initRamdata(){
		
		return new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					TagCacher.getInstance().reload();			
					Tag4UserCacher.getInstance().reload();
					
					TagStatsCacher.getInstance().reload();
					
					TagItemCacher.getInstance().reload();

//					Tag4ItemCacher.getInstance().reload();
					
					GiftManager.getInstance().reload();
					// JFB_Act_Manager.getInstance().reload();
					
					Tag2TagCacher.getInstance().reload();
					Tag2ItemCacher.getInstance().reload();
					Tagid2TagnameCacher.getInstance().reload();
					
					AllTagCacher.getInstance().reload();
					
					JuhuasuanBrandManager.updateBrand();
//					JuHuaSuanManager.updateBrand();
					
					UserTagCacher.getInstance().reload();
					UserTagInIpadCacher.getInstance().reload();
					
//					TagStyleItemCacher.getInstance().reload();

//					Tag4StyleItemCacher.getInstance().reload();
					
					AppConfigCacher.getInstance().reload();
					ClientMenuCacher.getInstance().reload();
					
					TagInIpadCacher.getInstance().reload();
					AllIpadTagCacher.getInstance().reload();
					
					LikeItemCacher.getInstance().reload();
					
					SaleCache.getInstance().reload();
					SaleDateCache.getInstance().reload();
					RecomCacher.getInstance().reload();
				}catch (Exception e){
					log.error(e, e) ;
				}
			}
		} ;
	}
	
	public static void main(String[] args) {
		Properties p = System.getProperties();
		System.out.println(p);
	}
}
