package cn.youhui.api.servlet2;

import java.io.IOException;
import java.text.NumberFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.TaobaoManager;
import cn.youhui.manager.TeJiaGoodsManager;
import cn.youhui.manager.LikeItemManager;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.ramdata.LikeItemCacher;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh2/addlike")
public class YHAddLikeItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHAddLikeItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String itemId = request.getParameter("itemid");
		if(uid == null || "".equals(uid) || itemId == null || "".equals(itemId)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		boolean ret = LikeItemCacher.getInstance().addLikeItem(uid, itemId);
		boolean ret1 = false;
		if(ret)
			ret1 = LikeItemManager.getInstance().addLikeItem(uid, itemId);
		if(itemId != null && !"".equals(itemId)){
			boolean isexist = TeJiaGoodsManager.getInstance().findItem(itemId);
			if(!isexist){
				TeJiaGoodsBean bean = getItemBean(itemId);
				if(bean != null){
					TeJiaGoodsManager.getInstance().addItem(bean);
					TagItemCacher.getInstance().addProduct(bean);
				}
			}
		}
		if(ret1 == false){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
		}
	}

	
	public static TeJiaGoodsBean getItemBean(String itemid){
		TeJiaGoodsBean bean = TaobaoManager.getItem(itemid);
			
			//获取折扣价	
		if(bean != null){			
			String price = getPrice(itemid);
			if(price != null && !"".equals(price)){
				bean.setPrice_low(price);
			}
			else{
				bean.setPrice_low(bean.getPrice_high());
			}			
		}
		//从网页抓取
		else {
			String content = "";
			try {
				content = NetManager.getInstance().getContent("http://tbitem.duapp.com/item?itemid="+itemid);
			} catch (Exception e) {
				return null;
			}
			bean = getItem(content);
			}
		return bean;
		}

	
	public static String getPrice(String itemid){
		String priceContent = "";
		try {
			priceContent = NetManager.getInstance().getContent("http://tbitem.duapp.com/price?itemids="+itemid);
		} catch (Exception e1) {
			return null;
		}
		String price= "";
			try {
				JSONObject jso = new JSONObject(priceContent);
				if(jso.has(itemid)){
					double d = jso.getDouble(itemid);
					java.text.NumberFormat nf = NumberFormat.getInstance();
					price = nf.format(d)+"";
					price = price.replaceAll(",", "");

				}
			} catch (JSONException e) {
				return null;
		}
			return price;
	}
	
	public static TeJiaGoodsBean getItem(String content ){
		TeJiaGoodsBean item = null;
		if(content == null){
			return null;
		}
		try {
			JSONObject jso = new JSONObject(content);
			item = new TeJiaGoodsBean();
			item.setItem_id(jso.getString("item_id"));
			item.setTitle(jso.getString("title"));
			item.setPic_url(jso.getString("pic_url"));
			item.setPrice_low(jso.getString("price"));
			item.setClickURL(jso.getString("click_url"));
			if(jso.has("orign_price")){				
				item.setPrice_high(jso.getString("orign_price"));
			}
			else {
				item.setPrice_high(jso.getString("price"));
			}
			item.setCommission("0");
			item.setCommission_rate("0");
		} catch (JSONException e) {
			return null;
		}
		return item;
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
