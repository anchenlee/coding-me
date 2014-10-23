package cn.youhui.api.huafei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import cn.youhui.util.oufeicz.PhoneNumAttributionUtil;
import cn.youhui.util.oufeicz.TelephoneCZValidationUtil;

/**
 * 手机验证接口
 * @author l
 * @since 2014-02-18
 */
@WebServlet("/telval")
public class TelephoneValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String phoneNum = request.getParameter("tel");
		String price = request.getParameter("price");
		JSONObject jo = new JSONObject();
		try {
			jo.put("tel", phoneNum);
			jo.put("price", price);
			String attr = PhoneNumAttributionUtil.getPhoneNumAttribution(phoneNum);
			jo.put("attr", attr);
			String retCode = TelephoneCZValidationUtil.CZGoodsQuery(phoneNum, price);
			if ("1".equals(retCode)) {
				jo.put("ret_code", "1");
			}else {
				jo.put("ret_code", "0");
				String errorMsg = "";
				if ("11".equals(retCode)) {// 11 : 运营商地区维护，暂不能充值
					errorMsg = "库存不足";
				}else if ("319".equals(retCode)) {//  319 : 充值的手机号不正确
					errorMsg = "充值手机号不正确";
				}else {
					errorMsg = "该地区暂不能充值";
				}
				jo.put("error_msg", errorMsg);    
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().print(jo.toString());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
