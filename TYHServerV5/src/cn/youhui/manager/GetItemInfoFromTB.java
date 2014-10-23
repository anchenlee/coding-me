package cn.youhui.manager;

import cn.youhui.common.ParamConfig;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TbkItem;
import com.taobao.api.request.TbkItemsDetailGetRequest;
import com.taobao.api.response.TbkItemsDetailGetResponse;

public class GetItemInfoFromTB {

	private static GetItemInfoFromTB instance=null;
	public static GetItemInfoFromTB getInstance(){
		if(instance==null){
			instance=new GetItemInfoFromTB();
		}
		return instance;
	}
	
	public TbkItem getItemInfo(String itemId){
		TaobaoClient client=new DefaultTaobaoClient(ParamConfig.AppReqUrl, ParamConfig.AppKey, ParamConfig.AppSecret);
		TbkItemsDetailGetRequest req=new TbkItemsDetailGetRequest();
		req.setFields("num_iid,seller_id,nick,title,price,volume,pic_url,item_url,shop_url");
		req.setNumIids(itemId);
		try {
			TbkItemsDetailGetResponse rsp= client.execute(req);
			if(rsp.getTbkItems().size()>0){
				TbkItem item = rsp.getTbkItems().get(0);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
