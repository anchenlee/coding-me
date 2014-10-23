package cn.youhui.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TPool extends ThreadPoolExecutor implements RejectedExecutionHandler {

	private static TPool ins ;
	
	/**
	 * 
	 * @param corePoolSize： 线程池维护线程的最少数量
		@param maximumPoolSize：线程池维护线程的最大数量
		@param keepAliveTime： 线程池维护线程所允许的空闲时间
		@param unit： 线程池维护线程所允许的空闲时间的单位
		@param workQueue： 线程池所使用的缓冲队列
		@param handler： 线程池对拒绝任务的处理策略
	 * */
	public TPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
			TimeUnit unit, BlockingQueue<Runnable> workQueue , RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}
	
	public static TPool getInstance(){
		return ins == null ? (ins = new TPool(100,5000,3,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(6000),new ThreadPoolExecutor.DiscardPolicy())) : ins ;
	}
	
	public void doit(Runnable r){
		try{
			this.getQueue().offer(r);
		}catch(Exception e){
			System.out.println("----Thread pool error !");
		}			
	//		this.execute(r);
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// TODO Auto-generated method stub
		this.execute(r) ;
	}

	public void shutdown(){
		this.shutdownNow() ;
	}
}
