package cn.youhui.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


public class YHTimer {
	
	private long delay = 60*1000;
	private long firstTime = 0;
	private java.util.Timer timer = new java.util.Timer();
	private List<TimerAction> actionList = new ArrayList<TimerAction>();
	
	public void schedule(){
		timer.schedule(new Task(), firstTime, delay);
	}
	
	public void addAction(TimerAction action){
		this.actionList.add(action);
	}
	
	class Task extends TimerTask{
		@Override
		public void run() {
			if(actionList != null && actionList.size()>0)
			   for(TimerAction action : actionList){
				   action.fire();
			   }
		}
	}
	
	public void setDelay(long delay){
		this.delay = delay;
	}
	
	public void setFirstTime(long firstTime){
		this.firstTime = firstTime;
	}
}
