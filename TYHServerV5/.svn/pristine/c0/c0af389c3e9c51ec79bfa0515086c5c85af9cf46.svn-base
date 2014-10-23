package cn.youhui.bean.view;

import cn.youhui.bean.SuiShouAction;
import cn.youhui.utils.Util;
import net.sf.json.JSONObject;

public class UserViewRecord {
	private String productId;
	private long time;
	private String img;
	private String title;
	private String price;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public JSONObject toJson(){
		JSONObject j = new JSONObject();
		j.put("productId", productId);
		j.put("title", title);
		j.put("img", img);
		j.put("time", time);
		j.put("price", price);
		return j;
	}
	public String toXML(){
		StringBuffer ret = new StringBuffer();
		ret.append("<record>")
		.append("<item_id><![CDATA[").append(productId).append("]]></item_id>")
		.append("<title><![CDATA[").append(title).append("]]></title>")
		.append("<img><![CDATA[").append(Util.getSimpleImg(img, "170x170")).append("]]></img>")
		.append("<time><![CDATA[").append(time).append("]]></time>")
		.append("<price><![CDATA[").append(price).append("]]></price>")
		.append(new SuiShouAction("suishou://app.youhui.cn/TagStyleItemPage?item_id=" + productId).toXML())
		.append("</record>");
		
		return ret.toString();
	}
}
