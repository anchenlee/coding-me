package com.netting.action;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.netting.conf.SysConf;
import com.netting.db.DataSourceFactory;
import com.netting.redis.RedisConnectionPool;


/**
 * 系统启动监听
 * @author YAOJIANBO
 *
 */
@WebListener
public class ContextInitListener implements ServletContextListener 
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog( ContextInitListener.class );
	
	// private static ExecutorService ThreadExecutePool = Executors.newFixedThreadPool(5);
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
//		SysConf.init("conf.properties");
		
		RedisConnectionPool.init();
		logger.info("Youhui4.0 Redis连接建立......");
		
//		DataSourceFactory.init_dataSource();
//		logger.info("Youhui4.0 数据库连接池建立......");
		
		/*
		ThreadExecutePool.execute(new SQLProcessThread());
		logger.info("DB更新SQL语句批量提交线程启动......");
		
		
		startJob();
		logger.info("启动定时任务......");
		*/
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		RedisConnectionPool.destoryPool();
		DataSourceFactory.destory();
	}

	/**
	 * 启动定时任务
	 */
	public static void startJob()
	{
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler;
		try
		{
			scheduler = schedulerFactory.getScheduler();
			
			/*
	        //装载用户集分宝领取状态更新定时任务,每15秒触发
			JobDetail releaseJFB_Job_JobDetail = new JobDetail("releaseJFB", ReleaseJFB_Job.class);
	        // String writeToDB_cronExpression = "0,15,30,45 * * * * ?";
	        CronTrigger releaseJFB_Trigger =
	            new CronTrigger("releaseJFB_Job", Scheduler.DEFAULT_GROUP, releaseJFB_Job_JobDetail.getName(), Scheduler.DEFAULT_GROUP);
	        releaseJFB_Trigger.setCronExpression(SysConf.releaseJFB_JobCronExpression);
	        scheduler.scheduleJob(releaseJFB_Job_JobDetail, releaseJFB_Trigger);
	        
	        //装载<集分宝返还至商户>定时任务,每天凌晨0点触发
			JobDetail return_JFB_Job_JobDetail = new JobDetail("return_JFB_Job", Return_JFB_Job.class);
	        CronTrigger return_JFB_Trigger =
	            new CronTrigger("return_JFB_Job", Scheduler.DEFAULT_GROUP, return_JFB_Job_JobDetail.getName(), Scheduler.DEFAULT_GROUP);
	        return_JFB_Trigger.setCronExpression(SysConf.return_JFB_JobCronExpression);
	        scheduler.scheduleJob(return_JFB_Job_JobDetail, return_JFB_Trigger);
	        */
	        scheduler.start();
		}
		catch (Exception e)
		{
			logger.error( "定时任务启动有误", e );
		}
	}
}
