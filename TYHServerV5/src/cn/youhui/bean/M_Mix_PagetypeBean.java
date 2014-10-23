package cn.youhui.bean;

import java.util.List;

import cn.youhui.common.Config;
import cn.youhui.utils.OuterCode;

public class M_Mix_PagetypeBean {

	private String id = "";
	private String title = "";
	private String tag_id = "";
	private String typeid = "";
	private String item_ids = "";
	private String rank = "";
	private String updatetime = "";
	private String keyword = "";
	private String status = "";
	private List<TeJiaGoodsBean> tjGoodsBeanList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getItem_ids() {
		return item_ids;
	}
	public void setItem_ids(String item_ids) {
		this.item_ids = item_ids;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public List<TeJiaGoodsBean> getTjGoodsBeanList() {
		return tjGoodsBeanList;
	}
	public void setTjGoodsBeanList(List<TeJiaGoodsBean> tjGoodsBeanList) {
		this.tjGoodsBeanList = tjGoodsBeanList;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String  toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<itemspage style=\"style"+typeid+"\">");
		if(tjGoodsBeanList.size()>0) {
		for(int i=0;i<tjGoodsBeanList.size();i++) {
			sb.append(tjGoodsBeanList.get(i).toXML());
		}
		}
		sb.append("</itemspage>");
		 return sb.toString();
	}

	public String  toXML(String uid, String platform){
		StringBuffer sb = new StringBuffer();
		sb.append("<itemspage style=\"style"+typeid+"\">");
		if(tjGoodsBeanList.size()>0) {
		for(int i=0;i<tjGoodsBeanList.size();i++) {
			String outerCode = OuterCode.getOuterCode(uid, platform, tjGoodsBeanList.get(i).getRate() + "", "7", tag_id);
			tjGoodsBeanList.get(i).setClickURL(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + tjGoodsBeanList.get(i).getItem_id());
			sb.append(tjGoodsBeanList.get(i).toXML());
		}
		}
		sb.append("</itemspage>");
		 return sb.toString();
	}
}
