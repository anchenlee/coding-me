package cn.youhui.bean;

import java.util.ArrayList;

public class ProductPageStyleBean {
	private int id;
	private String keyword;
	private String style;
	private String pids;//逗号隔开，关联m_discount_products主键
	private int position;
	private String addtime;
	private String updatetime;
	private String status;
	private String pics;//逗号隔开，图片地址集合
	private String titles;
	private ArrayList<TeJiaGoodsBean> tjGoodsBeanList;

	public ProductPageStyleBean() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public ArrayList<TeJiaGoodsBean> getTjGoodsBeanList() {
		return tjGoodsBeanList;
	}

	public void setTjGoodsBeanList(ArrayList<TeJiaGoodsBean> tjGoodsBeanList) {
		this.tjGoodsBeanList = tjGoodsBeanList;
	}

	public String  toXML(String uid, String tagId){
		StringBuffer sb = new StringBuffer();
		sb.append("<itemspage style=\"style"+style+"\">");
		if(tjGoodsBeanList.size()>0) {
		for(int i=0;i<tjGoodsBeanList.size();i++) {
			sb.append(tjGoodsBeanList.get(i).toXML());
		}
		}
		sb.append("</itemspage>");
		 return sb.toString();
	}
	
	public String  toXML(String uid){
		return toXML(uid, "");
	}
	
	public String  toXMLold(){
		StringBuffer sb = new StringBuffer();
		sb.append("<itemspage style=\"style"+style+"\">");
		if(tjGoodsBeanList.size()>0) {
		for(int i=0;i<tjGoodsBeanList.size();i++) {
			sb.append(tjGoodsBeanList.get(i).toXMLold());
		}
		}
		sb.append("</itemspage>");
		 return sb.toString();
	}
}
