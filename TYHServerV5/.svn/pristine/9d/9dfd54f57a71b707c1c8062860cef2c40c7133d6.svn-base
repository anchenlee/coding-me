package cn.youhui.api.servlet3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.youhui.bean.TradeOrder;
import cn.youhui.dao.TradeOrderInfoDAO;
import cn.youhui.utils.ParamUtil;

/**
 * 接收客户端的订单数据
 * @author lujiabin
 * @since 2014-9-19
 */
@WebServlet("/trade")
public class SaveTradeOrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SaveTradeOrderInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			String str = ParamUtil.getParameter(request, "data");
			
			if(!str.equals("")){
				JSONObject jso = new JSONObject(str);
				JSONArray array = (JSONArray) jso.get("orderList");
				for(int i=0;i<array.length();i++){
					JSONObject object = array.getJSONObject(i);
					TradeOrder bean = new TradeOrder();
					bean.setImgUrl(object.getString("imgUrl"));
					bean.setItemId(object.getString("itemId"));
					bean.setNum(object.getString("num"));
					bean.setOrderId(object.getString("orderId"));
					bean.setPrice(object.getString("price"));
					bean.setShopUrl(object.getString("shopUrl"));
					bean.setStatus(object.getString("status"));
					bean.setTitle(object.getString("title"));
					
					if(TradeOrderInfoDAO.getInstance().isOrderExist(bean.getOrderId())){
						//订单存在，更新状态
						TradeOrderInfoDAO.getInstance().update(bean.getOrderId(), bean.getStatus());
					}else{
						//订单不存在，存入记录
						TradeOrderInfoDAO.getInstance().saveTradeOrder(bean);
					}
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().print("400");
			return;
		}
		response.getWriter().print("200");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
