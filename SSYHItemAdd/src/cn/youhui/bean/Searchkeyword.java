package cn.youhui.bean;

import java.util.List;

/**
 * 搜索关键字
 * @author YAOJIANBO
 *
 */
public class Searchkeyword 
{
	private String id;
	
	private String name;
	
	private String parent_id;
	
	private String icon;
	
	private String action_type;
	
	private String action_value;
	
	private String version;
	
	private String rank;
	
	private String status;

	private List<Searchkeyword> cList;
	
	
	public List<Searchkeyword> getcList() {
		return cList;
	}

	public void setcList(List<Searchkeyword> cList) {
		this.cList = cList;
	}

	public Searchkeyword() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public String getAction_value() {
		return action_value;
	}

	public void setAction_value(String action_value) {
		this.action_value = action_value;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
}
