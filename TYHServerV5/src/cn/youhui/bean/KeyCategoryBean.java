package cn.youhui.bean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import cn.youhui.api.servlet2.YHGetAllTags;
import cn.youhui.utils.DateUtil;
import cn.youhui.utils.SuiShouActionUtil;
import cn.youhui.utils.Util;



public class KeyCategoryBean {
	private int rank;
	private String id = "";
	private String name = "";
	private String icon = "";
	private int sex;
	private ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();

	public String toXML(){
		StringBuffer ret = new StringBuffer();
		ret.append("<category>")
		.append("<name>").append(name).append("</name>")
		.append("<icon><![CDATA[").append(icon).append("]]></icon>")
		.append("<tags>");
		
		for(KeywordBean k : list){
			if(k.getShowStyle() != 2){
				ret.append(k.toXML());
			}
		}
		return ret.append("</tags>").append("</category>").toString();
	}
	
	private static String chongzhiId = "591";
	private static String MJBDId = "832";
	private static String DDLQId = "831";
	
	public String toMoreXML(String platform){
		StringBuffer ret = new StringBuffer();
//		ret.append("<category>")
//		.append("<name>").append(name).append("</name>")
//		.append("<icon><![CDATA[").append(icon).append("]]></icon>")
		ret.append("<tags>");
		
		for(KeywordBean k : list){
			if(k.getId().equals(chongzhiId) || k.getId().equals(MJBDId) || k.getId().equals(DDLQId)){	
				if(MJBDId.equals(k.getId())){
					k.setIcon("http://static.etouch.cn/suishou/ad_img/1rxf0dyzxzy.png");
				}else if(DDLQId.equals(k.getId())){
					k.setIcon("http://static.etouch.cn/suishou/ad_img/1rxfkutfm12.png");
				}else if(chongzhiId.equals(k.getId())){
					k.setIcon("http://static.etouch.cn/suishou/ad_img/1rxfq05k3by.png");
				}
				ret.append(k.toXMLNew(platform));
			}
		}
		return ret.
				append("</tags>")
				.toString();
//				.append("</category>")
	}
	
	
	public String toXML3(){
		StringBuffer sb = new StringBuffer();
		sb.append("<cat>")
		.append("<id>"+id+"</id>")
		.append("<name><![CDATA["+name+"]]></name>")
		.append("<icon><![CDATA["+icon+"]]></icon>")
		.append("</cat>");
		return sb.toString();
	}
	
	
	public String toXMLNew(String platform){
		StringBuffer ret = new StringBuffer();
		ret.append("<category>")
		.append("<name>").append(name).append("</name>")
		.append("<icon><![CDATA[").append(icon).append("]]></icon>")
		.append("<tags>");
		
		for(KeywordBean k : list){
			if(k.getShowStyle() != 2){
				ret.append(k.toXMLNew(platform));
			}
		}
		return ret.append("</tags>").append("</category>").toString();
	}
	
	public String toXML_1(){
		StringBuffer ret = new StringBuffer();
		ret.append("<category>")
		.append("<name>").append(name).append("</name>")
		.append("<icon><![CDATA[").append(icon).append("]]></icon>")
		.append("<tags>");
		
		for(KeywordBean k : list){
			if (k.getId().equals("609") || YHGetAllTags.localLifeTagId.equals(k.getId()))
			{
				continue;
			}
			ret.append(k.toXML());
		}
		return ret.append("</tags>").append("</category>").toString();
	}
	
	public void addKeyword(KeywordBean bean) {
		list.add(bean);
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

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<KeywordBean> getList() {
		return list;
	}

	public void setList(ArrayList<KeywordBean> list) {
		this.list = list;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getSex() {
		return sex;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public class KeywordBean 
	{
		private List<KeywordBean> childKeywords = new ArrayList<KeywordBean>();
		private int parentCategoryId ;
		private String parentCategoryName;
		private int fxShowIndex ;
		private String id = "";
		private int sex;
		private String keyword = "";
		private String description = "";
		private String categoryId = "0";
		private String searchTimes = "";
		private String rank = "";
		private String icon = "";
		private String bgcolor = "";
		private int clientShow ;
		private int sharedCount ;
		private int itemCount;
		private int showStyle = 0;
		private int own = 0;
		private String heat = "normal";              //三个可选，new;noraml;hot
		private String show = "fl";                  //红色小条显示，返利、折扣
		private Action action = new Action();
		
		private String chaye_icon;
		private Action chayeAction = new Action();
		private String chayeIconSize;
		private SuiShouAction ssac ;
		private SuiShouAction chayessac ;
		
		
		
	    private String hasSonTagAll;       // 是否在手机端展示"全部"的子标签, 0 ,不显示  1,显示
				
		private String defaultSonTagId;    // 手机端默认的子标签的ID
		
		private long startTimestamp = 0;
		
		public KeywordBean() {
			super();
		}

		public KeywordBean(List<KeywordBean> childKeywords,
				int parentCategoryId, String parentCategoryName,
				int fxShowIndex, String id, int sex, String keyword,
				String description, String categoryId, String searchTimes,
				String rank, String icon, String bgcolor, int clientShow,
				int sharedCount, int itemCount, int showStyle, int own,
				String heat, String show, Action action, String chaye_icon,
				Action chayeAction, String chayeIconSize) 
		{
			this.childKeywords = childKeywords;
			this.parentCategoryId = parentCategoryId;
			this.parentCategoryName = parentCategoryName;
			this.fxShowIndex = fxShowIndex;
			this.id = id;
			this.sex = sex;
			this.keyword = keyword;
			this.description = description;
			this.categoryId = categoryId;
			this.searchTimes = searchTimes;
			this.rank = rank;
			this.icon = icon;
			this.bgcolor = bgcolor;
			this.clientShow = clientShow;
			this.sharedCount = sharedCount;
			this.itemCount = itemCount;
			this.showStyle = showStyle;
			this.own = own;
			this.heat = heat;
			this.show = show;
			this.action = action;
			this.chaye_icon = chaye_icon;
			this.chayeAction = chayeAction;
			this.chayeIconSize = chayeIconSize;
		}

		@Override
		public String toString() {
			return "KeywordBean [childKeywords=" + childKeywords
					+ ", parentCategoryId=" + parentCategoryId
					+ ", parentCategoryName=" + parentCategoryName
					+ ", fxShowIndex=" + fxShowIndex + ", id=" + id + ", sex="
					+ sex + ", keyword=" + keyword + ", description="
					+ description + ", categoryId=" + categoryId
					+ ", searchTimes=" + searchTimes + ", rank=" + rank
					+ ", icon=" + icon + ", bgcolor=" + bgcolor
					+ ", clientShow=" + clientShow + ", sharedCount="
					+ sharedCount + ", itemCount=" + itemCount + ", showStyle="
					+ showStyle + ", own=" + own + ", heat=" + heat + ", show="
					+ show + ", action=" + action + ", chaye_icon="
					+ chaye_icon + ", chayeAction=" + chayeAction
					+ ", chayeIconSize=" + chayeIconSize + ", ssac=" + ssac
					+ ", chayessac=" + chayessac + ", hasSonTagAll="
					+ hasSonTagAll + ", defaultSonTagId=" + defaultSonTagId
					+ ", startTimestamp=" + startTimestamp + "]";
		}

		public String toJson(){
			Gson g = new Gson();
			return g.toJson(this);
		}
		
		public String getChaye_icon() {
			return chaye_icon;
		}

		public void setChaye_icon(String chaye_icon) {
			this.chaye_icon = chaye_icon;
		}

		public Action getChayeAction() {
			return chayeAction;
		}

		public void setChayeAction(Action chayeAction) {
			this.chayeAction = chayeAction;
		}

		public String getBgcolor() {
			return bgcolor;
		}
		public void setBgcolor(String bgcolor) {
			this.bgcolor = bgcolor;
		}
		public String getHeat() {
			return heat;
		}
		public void setHeat(String heat) {
			this.heat = heat;
		}
		public Action getAction() {
			return action;
		}
		public void setAction(Action action) {
			this.action = action;
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public String getBgColor() {
			return bgcolor;
		}

		public void setBgColor(String bgcolor) {
			this.bgcolor = bgcolor;
		}

		public String getShow() {
			return show;
		}
		public void setShow(String show) {
			this.show = show;
		}
		public String getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}

		public String getSearchTimes() {
			return searchTimes;
		}

		public void setSearchTimes(String searchTimes) {
			this.searchTimes = searchTimes;
		}

		public void setRank(String rank) {
			this.rank = rank;
		}

		public String getRank() {
			return rank;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public int getSex() {
			return sex;
		}

		public void setSharedCount(int sharedCount) {
			this.sharedCount = sharedCount;
		}

		public int getSharedCount() {
			return sharedCount;
		}

		public void setItemCount(int itemCount) {
			this.itemCount = itemCount;
		}

		public int getItemCount() {
			return itemCount;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String getIcon() {
			return icon;
		}

		public void setClientShow(int clientShow) {
			this.clientShow = clientShow;
		}

		public int getClientShow() {
			return clientShow;
		}

		public int getShowStyle() {
			return showStyle;
		}

		public void setShowStyle(int showStyle) {
			this.showStyle = showStyle;
		}
		public int getOwn() {
			return own;
		}
		public void setOwn(int own) {
			this.own = own;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<KeywordBean> getChildKeywords() {
			return childKeywords;
		}
		public void setChildKeywords(List<KeywordBean> childKeywords) {
			this.childKeywords = childKeywords;
		}
		public int getParentCategoryId() {
			return parentCategoryId;
		}
		public void setParentCategoryId(int parentCategoryId) {
			this.parentCategoryId = parentCategoryId;
		}
		public String getParentCategoryName() {
			return parentCategoryName;
		}
		public void setParentCategoryName(String parentCategoryName) {
			this.parentCategoryName = parentCategoryName;
		}
		public int getFxShowIndex() {
			return fxShowIndex;
		}
		public void setFxShowIndex(int fxShowIndex) {
			this.fxShowIndex = fxShowIndex;
		}
		
		public String getChayeIconSize() {
			return chayeIconSize;
		}

		public void setChayeIconSize(String chayeIconSize) {
			this.chayeIconSize = chayeIconSize;
		}


		public String toXMLNew(String platform){
			String sh = show;
			if(sh == null || "".equals(sh))
			{
				sh = "fl";
			}
			
			StringBuffer xml = new StringBuffer();
			xml.append("<tag>")
					.append("<own>").append(own).append("</own>")
					.append("<id>").append(id).append("</id>")
					.append("<icon><![CDATA[").append(Util.getCustomImg(icon, "400x400")).append("]]></icon>")
					.append("<name><![CDATA[").append(keyword).append("]]></name>")
					.append("<bgcolor>").append(bgcolor).append("</bgcolor>")
					.append("<description><![CDATA[").append(description).append("]]></description>")
					.append("<heat><![CDATA[").append(heat).append("]]></heat>")
					.append("<start_time><![CDATA[").append(DateUtil.changeCH(startTimestamp)).append("]]></start_time>")
					.append("<show>").append(sh).append("</show>");
		
			ssac = new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,action.getActionType(),new String[]{action.getActionValue(),keyword}));
			xml.append(ssac.toXML());
			
			xml.append("</tag>");
			
			return xml.toString();
		}

		public String toXML(){
			String sh = show;
			if(sh == null || "".equals(sh))
			{
				sh = "fl";
			}
			
			StringBuffer xml = new StringBuffer();
			xml.append("<tag>")
					.append("<own>").append(own).append("</own>")
					.append("<id>").append(id).append("</id>")
					.append("<icon><![CDATA[").append(Util.getCustomImg(icon, "400x400")).append("]]></icon>")
					.append("<name><![CDATA[").append(keyword).append("]]></name>")
					.append("<bgcolor>").append(bgcolor).append("</bgcolor>")
					.append("<description><![CDATA[").append(description).append("]]></description>")
					.append("<heat><![CDATA[").append(heat).append("]]></heat>")
					.append("<start_time><![CDATA[").append(DateUtil.changeCH(startTimestamp)).append("]]></start_time>")
					.append("<show>").append(sh).append("</show>");
			
			if (null != action)
			{
				
				xml.append(action.toXML());
			}
			else
			{
				xml.append("<action>")
					.append("<actiontype>").append("").append("</actiontype>")
					.append("<actionvalue><![CDATA[").append(id).append("]]></actionvalue>")
					.append("</action>");
			}
			
			xml.append("</tag>");
			
			return xml.toString();
		}
		
		public String chayeXML()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("<chaye>");
			sb.append("<icon><![CDATA[").append(chaye_icon).append("]]></icon>");
			sb.append("<wh_ratio><![CDATA[").append(chayeIconSize).append("]]></wh_ratio>");
			sb.append(chayeAction.toXML());
			sb.append("</chaye>");
			return sb.toString();
		}
		
		public String chayeXMLNew(String platform)
		{
			StringBuffer sb = new StringBuffer();
			sb.append("<chaye>");
			sb.append("<icon><![CDATA[").append(chaye_icon).append("]]></icon>");
			sb.append("<wh_ratio><![CDATA[").append(chayeIconSize).append("]]></wh_ratio>");
			if(chayessac == null){
				chayessac = new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,chayeAction));
			}
//			sb.append(chayeAction.toXML());
			sb.append(chayessac.toXML());
			
			sb.append("</chaye>");
			return sb.toString();
		}
		
		public String getDefaultSonTagId() {
			return defaultSonTagId;
		}

		public void setDefaultSonTagId(String defaultSonTagId) {
			this.defaultSonTagId = defaultSonTagId;
		}

		public String getHasSonTagAll() {
			return hasSonTagAll;
		}

		public void setHasSonTagAll(String hasSonTagAll) {
			this.hasSonTagAll = hasSonTagAll;
		}

		public long getStartTimestamp() {
			return startTimestamp;
		}

		public void setStartTimestamp(long startTimestamp) {
			this.startTimestamp = startTimestamp;
		}

	}
}
