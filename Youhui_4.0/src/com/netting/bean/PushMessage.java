package com.netting.bean;

/**
 * 推送消息
 * @author YAOJIANBO
 *
 */
public class PushMessage 
{
	private String id;
	/**
	 * 消息ID
	 */
	private String pId = "";
	/**
	 * 消息标题
	 */
	private String title ="";
	/**
	 * 消息方式0直接通知;1弹出对话框;2强制推送;
	 */
	private int code =0;
	/**
	 * 消息内容
	 */
	private String content ="";
	/**
	 * 开始时间
	 */
	private String starttime;
	/**
	 * 结束时间
	 */
	private String endtime;
	/**
	 * 0只发送一次；1发送多次
	 */
	private int mode = 0;
	private String clientVersion ;
	
	/**
	 * 终端平台类型
	 */
	private String platform = "";
	/**
	 * 发送范围:0发送给所有;1按条件发送
	 */
	private int range = 1;
	/**
	 * 发送条件
	 */
	private String formula ="";
	/**
	 * 显示数据
	 */
	private String query = "";
	/**
	 * 图标
	 */
	private String icon = "";
	/**
	 *地址 转换之后的key
	 */
	private String urlKey = "";
	
	private Action action = new Action();
	
	public PushMessage(){
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public String getFormula() {
		return formula;
	}
	
	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void changeContent(String value)
	{
		if(value == null || "".equals(value))
		{
			return;
		}
		String[] va = value.split(";");
		for(int i = 0; i < va.length; i++)
		{
			this.content = this.content.replaceAll("#" + i + "#", va[i]);
		}
	}

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}
}
