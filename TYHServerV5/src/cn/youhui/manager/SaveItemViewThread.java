package cn.youhui.manager;

import org.json.JSONObject;

import cn.youhui.bean.ItemView;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.dao.view.ViewRecordMongo;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Type;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.utils.NetManager;

/**
 * 保存商品浏览记录
 * @author lijun
 * @since 2013-12-20
 */
public class SaveItemViewThread extends Thread{
	
	ItemView itemView = null;
	
	public SaveItemViewThread(ItemView itemView){
		this.itemView = itemView;
	}
	
	public void run(){
		if(itemView != null){
			ItemViewManager.getInstance().add(itemView);
			TeJiaGoodsBean item = TagItemCacher.getInstance().getProduct(itemView.getItemId());
			if(item == null){
				item = getItem(itemView.getItemId());
			}
			if(item != null){
				ViewRecordMongo.addNewViewRecord(itemView.getUid(), itemView.getImei(), itemView.getItemId(), item.getTitle(), item.getPic_url(), item.getPrice_low(), System.currentTimeMillis());
				String message = "{\"title\":\"" + item.getTitle().replaceAll("\\s*|\t|\r|\n|\"", "") + "\"}";
			//	LogManager.addlog(itemView.getUid(), itemView.getFromChannel(), itemView.getFromValue(), "100", itemView.getItemId(), "1", itemView.getItemId(), message);
				LogManager.addlog(itemView.getUid(), Event.VIEW, Type.PRODUCT, itemView.getItemId());
			}
		}
	}
	
	public static TeJiaGoodsBean getItem(String itemid){
		TeJiaGoodsBean bean = null;
		try {
			String content = NetManager.getInstance().getContent("http://b17.cn/getitem?itemid="+itemid);
			if(content != null && !"".equals(content)){				
				JSONObject jso = new JSONObject(content);
				bean = new TeJiaGoodsBean();
				bean.setPrice_low(jso.getString("price"));
				bean.setTitle(jso.getString("title"));
				bean.setPic_url(jso.getString("pic_url"));
			}
		} catch (Exception e) {
			return null;
		}
		return bean;
	}
	
}