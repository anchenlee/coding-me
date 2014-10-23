package cn.youhui.bean;

public class ItemShop extends BaseBean{

	private ItemBean item = null;
	private ShopInfo shop = null;
	
	public ItemBean getItem() {
		return item;
	}

	public void setItem(ItemBean item) {
		this.item = item;
	}

	public ShopInfo getShop() {
		return shop;
	}

	public void setShop(ShopInfo shop) {
		this.shop = shop;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<item_shop>");
		sb.append(item.toXML());
		if(shop != null){			
			sb.append(shop.toXML());
		}
		sb.append("</item_shop>");
		return sb.toString();
	}
	
	
}
