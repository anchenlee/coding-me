package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.manager.SaleExplainManager;

public class SaleExplainCacher {
	
    private static SaleExplainCacher instance;
	
	private SaleExplainCacher() {
	}
	
	public static SaleExplainCacher getInstance(){
		if (instance == null){
			instance = new SaleExplainCacher();
		}
		return instance; 
	}
	
	public List<TeJiaGoodsBean> getItemsByExplain(String explainId){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		String items = SaleExplainManager.getInstance().getItemsStr(explainId);
		if(items == null || "".equals(items)){
			list = null;
		}else{
			String[] ids = items.split(",");
			for(String id : ids){
				TeJiaGoodsBean bean = TagItemCacher.getInstance().getProduct(id);
				if(bean != null){
					list.add(bean);
				}
			}
		}
		return list;
	}
}
