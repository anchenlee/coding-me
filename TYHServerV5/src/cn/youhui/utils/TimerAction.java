package cn.youhui.utils;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class TimerAction {
	
	protected long delay = 0;
	protected long nextExecutionTime = 0;

	public TimerAction(){
		this.nextExecutionTime = System.currentTimeMillis();
	}
	
	private boolean isTimeUp(){
		if(System.currentTimeMillis()>=nextExecutionTime)
		{
			this.nextExecutionTime =System.currentTimeMillis() + this.delay;			
			return true;
		}
		return false;
	}
	
	public void fire(){
		if(isTimeUp()){
			execut();
		}
	}
	
	public void execut(){
	}
	
	public void setDelay(long delay){
		this.delay = delay;
	}
	
	public long getDelay(){
		return this.delay;
	}
	
	public long getNextExecutionTime() {
		return nextExecutionTime;
	}

	public void setNextExecutionTime(long nextExecutionTime) {
		this.nextExecutionTime = nextExecutionTime;
	}

}
