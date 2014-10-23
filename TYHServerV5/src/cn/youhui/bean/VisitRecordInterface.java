package cn.youhui.bean;


/**
 * 记录接口被访问的情况    一小时一条记录
 * @author lijun
 *
 */
public class VisitRecordInterface {
	
	/**
	 * 时间   精确到小时
	 */
	private String time = "";
	
	/**
	 * 接口名
	 */
	private String interfaceName = "";
	
	/**
	 * 次数
	 */
	private int times = 1;

	public VisitRecordInterface(String time, String interfaceName) {
		super();
		this.time = time;
		this.interfaceName = interfaceName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	
	
	
}
