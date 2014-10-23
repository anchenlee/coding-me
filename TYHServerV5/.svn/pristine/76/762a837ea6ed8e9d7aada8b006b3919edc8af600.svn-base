package cn.youhui.api.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ExchAddress;
import cn.youhui.bean.ExchItem;
import cn.youhui.bean.ExchOrder;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ExchAddreManager;
import cn.youhui.manager.ExchItemManager;
import cn.youhui.manager.ExchOrderManager;
import cn.youhui.manager.MoneyManager;
import cn.youhui.manager.TradeDetailManager;
import cn.youhui.ramdata.ExchItemCacher;
import cn.youhui.utils.RespStatusBuilder;

import sun.misc.BASE64Decoder;

/**
 * @category 兑换
 * @author leejun
 * @since 2012-11-26
 */
@WebServlet("/tyh/exchange")
public class YHExchange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHExchange() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String data = request.getParameter("data");
		String content = decode(data);
		//content = "#0#32870501#1000000009#0#3#10.0#10.0#addin#lijun#110#201012#江苏省#南京市#雨花台区#花神大道17号#";
		response.getWriter().print(getResult(content));
	}
	
	public String getResult(String content){
		try {
			String[] s = content.split("#");
			if(!(s.length == 16 || s.length == 11)){
				return RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString();
			}
			float yue = MoneyManager.getInstance().getYue(s[2]);
			float promPrice = Float.parseFloat(s[7]);
			ExchItem item = ExchItemCacher.getInstance().getExchItem(s[3]);
			if(item == null || item.getPromPrice() != promPrice){
				return RespStatusBuilder.message(ActionStatus.FORBIDDEN).toString();
			}
			if(yue > promPrice) {
				ExchOrder order = new ExchOrder();
				order.setUid(s[2]);
				order.setItemId(s[3]);
				order.setItemVersion(Integer.parseInt(s[4]));
				order.setCount(Integer.parseInt(s[5]));
				order.setCreatTime(System.currentTimeMillis());
				order.setPrice(Float.parseFloat(s[6]));
				order.setPromPrice(Float.parseFloat(s[7]));
				order.setAddinfo(s[8]);
				if("0".equals(s[1]) && s.length == 16) {
					ExchAddress addre = new ExchAddress();
					addre.setUid(s[2]);
					addre.setRecName(s[9]);
					addre.setRecTel(s[10]);
					addre.setPostCode(s[11]);
					addre.setProvince(s[12]);
					addre.setCity(s[13]);
					addre.setDistrict(s[14]);
					addre.setAddress(s[15]);
					String addId = ExchAddreManager.getInstance().add(addre);
					if(addId != null)
						order.setAddressId(addId);
				}
				if("1".equals(s[1]) && s.length == 11) {
					order.setVirtualType(s[9]);
					order.setVirtualNumber(s[10]);
				}
				if(ExchItemManager.getInstance().buy(s[3], Integer.parseInt(s[5]))){
					String orderId = ExchOrderManager.getInstance().add(order);
					if(orderId != null){
						float afterYue = MoneyManager.getInstance().getYue(s[2]);
						if(afterYue < 0){                                       //避免一个id同时购买
							ExchOrderManager.getInstance().del(orderId);
							return RespStatusBuilder.message(ActionStatus.NOT_ENOUGH_YUE).toString();
						}
						TradeDetailManager.getInstance().addtomingxi(s[2], s[3], Integer.parseInt(s[5]), Float.parseFloat(s[7]), orderId);
					}else{
						return RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString();
					}
				}else{
					return RespStatusBuilder.message(ActionStatus.SELL_OUT).toString();
				}
				return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString();
			} else {
				return RespStatusBuilder.message(ActionStatus.NOT_ENOUGH_YUE).toString();
			}
			
		} catch (Exception e) {
			return RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString();
		}
	}
	
	public static String decode(String str) {
		BASE64Decoder base64 = new BASE64Decoder();
		String content = "";
		try {
			str = new String(base64.decodeBuffer(str),"utf-8");
			content = str.substring(2, str.length() - 2);
			content = new String(base64.decodeBuffer(content),"utf-8");
			content = content.substring(0, content.length()-4);
		} catch (Exception e1) {
			return "";
		}
		return content;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
