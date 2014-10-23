package cn.youhui.bean;

/**
 * 达人类别
 * @author YAOJIANBO
 *
 */
public class ProfCategory 
{
	private String id;
	
	private String name;
	
	private String desc;
	
	private int sort;
	
	private int status;
	
	public ProfCategory() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toXML()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<category>");
		sb.append("<id>").append(this.getId()).append("</id>");
		sb.append("<name>").append(this.getName()).append("</name>");
		sb.append("<desc>").append(this.getDesc()).append("</desc>");
		sb.append("</category>");
		
		return sb.toString();
	}

}
