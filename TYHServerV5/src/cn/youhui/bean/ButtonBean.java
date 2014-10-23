package cn.youhui.bean;


/**
 *公告按钮 
 * @author belonghu
 *
 */
public class ButtonBean {

	private String name;
	
	/**
	 * action为“”代表不做任何操作 只隐藏公告
	 */
	private SuiShouAction ssac;

	private Action action;
	
	
	public ButtonBean(String name,SuiShouAction ssac){
		this.name = name;
		this.ssac = ssac;
	}
	
	public ButtonBean(String name,SuiShouAction ssac,Action action){
		this.name = name;
		this.ssac = ssac;
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SuiShouAction getSsac() {
		return ssac;
	}

	public void setSsac(SuiShouAction ssac) {
		this.ssac = ssac;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<button>")
		.append("<button_name>").append(name).append("</button_name>")
		.append(ssac.toXML())
		.append("</button>");
		return sb.toString();
	}
	
}