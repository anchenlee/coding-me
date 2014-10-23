package cn.youhui.bean;

public class FanliBean 
{
	private String orderid;
	
	private String name;
	
	private String price;
	
	private String num;
	
	private String fanli;
	
	private String time;
	
	private String state;

	public FanliBean() 
	{
		super();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getFanli() {
		return fanli;
	}

	public void setFanli(String fanli) {
		this.fanli = fanli;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String toXML()
	{
		  return new StringBuffer().append("<item>")
				  .append("<orderid>").append(this.getOrderid()).append("</orderid>")
				  .append("<name><![CDATA[").append(this.getName()).append("]]></name>")
				  .append("<price>").append(this.getPrice()).append("</price>")
				  .append("<num>").append(this.getNum()).append("</num>")
				  .append("<fanli>").append(this.getFanli()).append("</fanli>")
				  .append("<time>").append(this.getTime()).append("</time>")
				  .append("<state><![CDATA[").append(this.getState()).append("]]></state>")
				  .append("</item>").toString();
	}
}
