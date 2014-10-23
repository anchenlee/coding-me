package cn.youhui.bean;

import cn.youhui.utils.SuiShouActionUtil;


public class PushMessage {
   
	private String id = "";
	private String pId = "";
	private String title ="";
	private int code =0;
	private String content="";
	private long starttime;
	private long endtime;
	private int mode = 0;   //是否多次发送
	private String platform = "";
	private String clientVersion ;
	private int range = 0;  //发送给所有或按条件发送
	private String formula ="";
	private String query = "";
	private String icon = "";
	private Action action = new Action();
	private SuiShouAction ssac = new SuiShouAction();
	

	public PushMessage(){}
	
	public PushMessage(String pId, String title, int code, String content, long starttime,long endtime, int mode, String platform, String clientVersion, int range,
			String formula, String query, String icon, Action action) {
		super();
		this.pId = pId;
		this.title = title;
		this.code = code;
		this.content = content;
		this.starttime = starttime;
		this.endtime = endtime;
		this.mode = mode;
		this.platform = platform;
		this.clientVersion = clientVersion;
		this.range = range;
		this.formula = formula;
		this.query = query;
		this.icon = icon;
		this.action = action;
		
		ssac = new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,action.getActionType(),new String[]{action.getActionValue(),title}));
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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
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


	public long getStarttime() {
		return starttime;
	}


	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}


	public long getEndtime() {
		return endtime;
	}


	public void setEndtime(long endtime) {
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	@Override
	public String toString() {
		return "PushMessage [title=" + title + ", code=" + code + ", content="
				+ content + ", starttime=" + starttime + ", endtime=" + endtime
				+ ", mode=" + mode + ", clientVersion=" + clientVersion
				+ ", range=" + range + ", formula=" + formula + ", query="
				+ query + "]";
	}
	
	public String toJson(){
		return new StringBuffer().append("{")
				.append("\"code\":\"").append(code)
				.append("\",\"title\":\"").append(title)
				.append("\",\"content\":\"").append(content)
				.append("\",").append(action.toJson())
				.append("},").toString();
	}
	
	public String toJsonNew(){
		ssac = new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,action.getActionType(),new String[]{action.getActionValue(),title}));
		return new StringBuffer().append("{")
				.append("\"code\":\"").append(code)
				.append("\",\"title\":\"").append(title)
				.append("\",\"content\":\"").append(content)
				.append("\",").append(ssac.toJson())
				.append("},").toString();
	}
	
	public String toJson(String value){
		String cont = convertContent(content, value);
		return new StringBuffer().append("{")
				.append("\"code\":\"").append(code)
				.append("\",\"title\":\"").append(title)
				.append("\",\"content\":\"").append(cont)
				.append("\",").append(action.toJson())
				.append("},").toString();
	}
	
	public String toJsonNew(String value){
		ssac = new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,action.getActionType(),new String[]{action.getActionValue(),title}));
		
		String cont = convertContent(content, value);
		return new StringBuffer().append("{")
				.append("\"code\":\"").append(code)
				.append("\",\"title\":\"").append(title)
				.append("\",\"content\":\"").append(cont)
				.append("\",").append(ssac.toJson())
				.append("},").toString();
	}

	public String convertContent(String content, String value){
		String cont = content;
		if(value == null || "".equals(value))
			return cont;
		String[] va = value.split(";");
		for(int i = 0; i<va.length; i++){
			cont = cont.replaceAll("#"+i+"#", va[i]);
		}
		return cont;
	}
	
	public String toXML(){
		return new StringBuffer().append("<pushmsg>")
				.append("<pid>").append(id).append("</pid>")
				.append("<code>").append(code).append("</code>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<icon><![CDATA[").append(icon).append("]]></icon>")
				.append("<content><![CDATA[").append(content).append("]]></content>")
				.append("<starttime>").append(starttime).append("</starttime>")
				.append(action.toXML())
				.append("</pushmsg>").toString();
	}
	
	public String toXMLNew(){
		StringBuffer sb = new StringBuffer();
		sb.append("<pushmsg>")
				.append("<pid>").append(id).append("</pid>")
				.append("<code>").append(code).append("</code>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<icon><![CDATA[").append(icon).append("]]></icon>")
				.append("<content><![CDATA[").append(content).append("]]></content>")
				.append("<starttime>").append(starttime).append("</starttime>");
				ssac = new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,action.getActionType(),new String[]{action.getActionValue(),title}));
				sb.append(ssac.toXML());
				sb.append("</pushmsg>").toString();
		return sb.toString();
	}
	
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
