package com.netting.bean;

import java.util.ArrayList;
import java.util.List;

public class KeyCategoryBean 
{
	private String id;
	private String name;
	private String icon;
	private int sex;
	private int rank;
	private ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
	private int fxShowIndex ;
	private int parentCategoryId ;
	
	/**
	 * @return the fxShowIndex
	 */
	public int getFxShowIndex() {
		return fxShowIndex;
	}

	/**
	 * @param fxShowIndex the fxShowIndex to set
	 */
	public void setFxShowIndex(int fxShowIndex) {
		this.fxShowIndex = fxShowIndex;
	}

	/**
	 * @return the parentCategoryBean
	 */
	public int getParentCategoryId() {
		return parentCategoryId;
	}

	/**
	 * @param parentCategoryId the parentCategoryBean to set
	 */
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
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

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public class KeywordBean 
	{
		private String id;
		private int sex;
		private String keyword;
		private String bgcolor;
		private String categoryId;
		private String searchTimes;
		private String rank;
		private String icon;
		private int shareCount;
		private int itemCount;
		private String itemids = "";
		private int fxShowIndex ;
		private int clientShowIndex;
		private int parentCategoryId ;
		private String parentCategoryName;
		private String description = "";
		private String heat = "normal";
		private String show = "fl";
		private Action action = new Action();
		private List<KeywordBean> childKeywords = new ArrayList<KeywordBean>();
		
		private String chaye_icon;
		private Action chayeAction = new Action();
		private String chayeIconSize;
		
		// 是否在手机端展示"全部"的子标签
		private String hasSonTagAll;
		// 手机端默认的子标签的ID
		private String defaultSonTagId;
		
		private long startTimestamp;
		

		public long getStartTimestamp() {
			return startTimestamp;
		}

		public void setStartTimestamp(long startTimestamp) {
			this.startTimestamp = startTimestamp;
		}

		public String getHasSonTagAll() {
			return hasSonTagAll;
		}

		public void setHasSonTagAll(String hasSonTagAll) {
			this.hasSonTagAll = hasSonTagAll;
		}

		public String getDefaultSonTagId() {
			return defaultSonTagId;
		}

		public void setDefaultSonTagId(String defaultSonTagId) {
			this.defaultSonTagId = defaultSonTagId;
		}

		public String getParentCategoryName() {
			return parentCategoryName;
		}

		public void setParentCategoryName(String parentCategoryName) {
			this.parentCategoryName = parentCategoryName;
		}

		public int getParentCategoryId() {
			return parentCategoryId;
		}

		public void setParentCategoryId(int parentCategoryId) {
			this.parentCategoryId = parentCategoryId;
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

		public String getItemids() {
			return itemids;
		}

		public void setItemids(String itemids) {
			this.itemids = itemids;
		}

		public List<KeywordBean> getChildKeywords() {
			return childKeywords;
		}

		public void setChildKeywords(List<KeywordBean> childKeywords) {
			this.childKeywords = childKeywords;
		}

		public String getHeat() {
			return heat;
		}

		public void setHeat(String heat) {
			this.heat = heat;
		}
		
		public String getShow() {
			return show;
		}

		public void setShow(String show) {
			this.show = show;
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
		
		public String getBgcolor() {
			return bgcolor;
		}

		public void setBgcolor(String bgcolor) {
			this.bgcolor = bgcolor;
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

		public void setShareCount(int shareCount) {
			this.shareCount = shareCount;
		}

		public int getShareCount() {
			return shareCount;
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

		public void setClientShowIndex(int clientShowIndex) {
			this.clientShowIndex = clientShowIndex;
		}

		public int getClientShowIndex() {
			return clientShowIndex;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
}
