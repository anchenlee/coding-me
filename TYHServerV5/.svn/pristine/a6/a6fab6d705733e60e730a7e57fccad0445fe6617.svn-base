package cn.youhui.bean;

import java.util.List;

public class SaleNew {
	
	private String id;
	/**
	 * 活动位置，在头部或下面
	 */
	private int positioin;
	
	/**
	 * 类型，商品或主题
	 */
	private int type;
	private String img;
	private String title;
	private String desc;
	
	/**
	 * 打折字段
	 */
	private String saleDesc;
	private String itemId;
	private double price;
	private double promPrice;
	private double commission;
	private double commissionRate;
	
	private Action action;
	
	private List<SaleExplain> explains;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPositioin() {
		return positioin;
	}

	public void setPositioin(int positioin) {
		this.positioin = positioin;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSaleDesc() {
		return saleDesc;
	}

	public void setSaleDesc(String saleDesc) {
		this.saleDesc = saleDesc;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPromPrice() {
		return promPrice;
	}

	public void setPromPrice(double promPrice) {
		this.promPrice = promPrice;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public List<SaleExplain> getExplains() {
		return explains;
	}

	public void setExplains(List<SaleExplain> explains) {
		this.explains = explains;
	}
	
	public String toXML(){
			StringBuffer sb = new StringBuffer();
		  sb.append("<sale>")
				.append("<id>").append(id).append("</id>")
				.append("<type>").append(id).append("</type>")
				.append("<img><![CDATA[").append(img).append("]]></img>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<desc><![CDATA[").append(desc).append("]]></desc>")
				.append("<sale_desc><![CDATA[").append(saleDesc).append("]]></sale_desc>")
				.append("<item_id>").append(itemId).append("</item_id>")
				.append("<price>").append(price).append("</price>")
				.append("<prom_price>").append(promPrice).append("</prom_price>")
				.append("<commission>").append(commission).append("</commission>")
				.append("<commission_rate>").append(commissionRate).append("</commission_rate>")
				.append(action.toXML());
				if(explains != null && explains.size() >0){
					sb.append("<explains>");
					for(SaleExplain ex : explains){
						sb.append(ex.toXML());
					}
					sb.append("</explains>");
				}
				sb.append("</sale>").toString();
				return sb.toString();
	}
}
