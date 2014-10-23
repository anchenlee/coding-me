package cn.youhui.manager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TbkItem;
import com.taobao.api.request.TbkItemsDetailGetRequest;
import com.taobao.api.response.TbkItemsDetailGetResponse;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Config;
import cn.youhui.utils.NetManager;

public class GetItemManager {

	public static TeJiaGoodsBean getGoodsByItemid(String itemid){
		TeJiaGoodsBean bean = null;
		TaobaoClient client=new DefaultTaobaoClient(Config.Fanli_url, Config.Fanli_appkey, Config.Fanli_secret);
		TbkItemsDetailGetRequest req=new TbkItemsDetailGetRequest();
		req.setTrackIids("value1,value2,value3");
		req.setFields("num_iid,seller_id,nick,title,price,volume,pic_url,item_url,shop_url");
		req.setNumIids(itemid);
		try {
			TbkItemsDetailGetResponse response = client.execute(req);
			List<TbkItem> items = response.getTbkItems();
			
			if (null != items && items.size() > 0)
			{
				for (TbkItem item : items)
				{
					if(item != null)
					{
						 bean = new TeJiaGoodsBean();
						 bean.setItem_id(item.getNumIid()+"");
						 bean.setTitle(item.getTitle().replaceAll("\"", "").replaceAll("'", ""));
						 bean.setPrice_low(item.getPrice());
						 bean.setPic_url(item.getPicUrl()); 
						 bean.setClickURL("http://a.m.taobao.com/i"+item.getNumIid()+".htm");						
					}
				}
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public static TeJiaGoodsBean getItemFromWeb1111(String itemId) {
		TeJiaGoodsBean bean = null;
		String url = "http://a.m.taobao.com/i" + itemId + ".htm?v=1";
		String content = "";
		try {
			content = NetManager.getInstance().getContent(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		String price = "-1";
		Matcher m_new_price = Pattern.compile("<strong class=\"oran\">.*?</strong>").matcher(content);
		if (m_new_price.find()) {
			String content1 = m_new_price.group();
			Pattern pattern1 = Pattern.compile(">.*-");
			Matcher m_item = pattern1.matcher(content1);
			if (m_item.find()) {
				String precontent = m_item.group().replaceAll(">", "");
				price = (precontent.replaceAll("-", ""));
			} else
				price = (m_new_price.group().replaceAll(
						"[^0-9.]", ""));
		}
		if(price.equals("-1")){
			return null;
		}
		bean = new TeJiaGoodsBean();
		bean.setPrice_low(price);		
		bean.setItem_id(itemId);
		String title= "";
		String pa = "";
		Matcher mat = Pattern.compile("<p><img alt=\".*?</p>").matcher(content);
		if(mat.find()){			
			pa = mat.group();
		}
		Matcher m_title = Pattern.compile("<p><img alt=\".*?src").matcher(pa);
		if(m_title.find()){
			title = m_title.group();
			title = title.replaceAll("<p><img alt=\"", "").replaceAll("src", "").replaceAll("\"", "");
			bean.setTitle(title);
		}
		String pic = "";
		Matcher m_pic = Pattern.compile("src.*?/>").matcher(pa);
		if(m_pic.find())
		{
			pic = m_pic.group();
			pic = pic.replaceAll("src=\"", "").replaceAll("\"", "").replaceAll("/>", "").replaceAll("_170x170.jpg", "");
			bean.setPic_url(pic);
		}
		 bean.setClickURL("http://a.m.taobao.com/i"+itemId+".htm");				
		return bean;
	}
	
	public static void main(String[] args) {
		System.out.println(getGoodsByItemid("25831336117"));
	}
}
