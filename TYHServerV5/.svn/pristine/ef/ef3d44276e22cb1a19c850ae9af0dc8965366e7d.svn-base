package cn.youhui.bean;

public class MMixBean {

	private String id;
	
	private String item_id;
	
	private String p_id;
	
	private String type;
	
	private String is_Levels;
	
	private String tag_id;
	
	private int rank;
	
	private String status;

	private String pic;
	
	private SuiShouAction ssac;
	
	private String proportion;
	
	
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public SuiShouAction getSsac() {
		return ssac;
	}

	public void setSsac(SuiShouAction ssac) {
		this.ssac = ssac;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIs_Levels() {
		return is_Levels;
	}

	public void setIs_Levels(String is_Levels) {
		this.is_Levels = is_Levels;
	}

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<item>");
		/**
		 * 
		 
		sb.append("<id>").append(id).append("</id>");
		sb.append("<item_id>").append(item_id).append("</item_id>");
		*/
		sb.append("<pic><![CDATA[").append(pic).append("]]></pic>");
		sb.append(ssac.toXML());
		/*8
		 * 
		 
		sb.append("<rank>").append(rank).append("</rank>");
		sb.append("<tag_id>").append(tag_id).append("</tag_id>");
		sb.append("<type>").append(type).append("</type>");
		*/
		sb.append("</item>");
		return sb.toString();
	}
	
}
