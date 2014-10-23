package com.netting.bean;

/**
 * 后台管理员的操作日志
 * @author YAOJIANBO
 *
 */
public class Opt_Log 
{
	private String id;
	
	private String username;
	
	private String menu_id;
	
	private String content;
	
	private String opt_time;
	
	private String opt_type;

	public Opt_Log() {
		super();
	}

	public String getOpt_type() {
		return opt_type;
	}

	public void setOpt_type(String opt_type) {
		this.opt_type = opt_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOpt_time() {
		return opt_time;
	}

	public void setOpt_time(String opt_time) {
		this.opt_time = opt_time;
	}
}
