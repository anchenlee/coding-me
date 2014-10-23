package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import cn.youhui.bean.CouponOrder;
import cn.youhui.cacher.dao.secondKillCacher;
import cn.youhui.common.Config;
import cn.youhui.common.ParamConfig;
import cn.youhui.dao.CouponOrderDAO;
import cn.youhui.dao.SecondKillDAO;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.ParamUtil;

/**
 * 获取订单详情
 */
@WebServlet("/SecondKillOrderDetail")
public class GetSecondKillOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSecondKillOrderDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String orderid=ParamUtil.getParameter(request, "orderid");
		String from=request.getParameter("from");
		if(from !=null&&"startRushbuy".equals(from)){
			String couponid=ParamConfig.secondKillType+request.getParameter("id");
			String uid=request.getParameter("uid");
			orderid=CouponOrderDAO.getInstance().getCouponOrderid(couponid, uid);
			if(orderid.equals("")){
				response.getWriter().print("notfond");
				return;
			}
		}else{
			if(orderid==null||"".equals(orderid)){
				response.getWriter().print("paramException");
				return;
			}
		}
		
		JsonObject jo=SecondKillDAO.getInstance().getSecondKillOrderByOid(orderid);
		CouponOrder co=CouponOrderDAO.getInstance().getCouponOrderById(orderid);
		String str1="";
		if(co!=null){
			str1=co.toJson();
		}
		String str2=jo.toString();
		String itemid="";
		String couponid=jo.get("couponid").getAsString();
		int  paid=jo.get("paid").getAsInt();
		long experitetime=co.getExpireTime();
		if(paid==0&&new Date().getTime()>experitetime){
			paid=3;
		}
		if(jo!=null){
			itemid=jo.get("pid").getAsString();
		}
		int  price=secondKillCacher.getInstance().getSecondKillPrice(couponid);
		String outerCode = OuterCode.getOuterCode(co.getUserid(), "", "0", "7", "607");
		String clickURL=Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" +itemid ;
		response.getWriter().print( "{\"order\":"+str1+",\"secondkill\":"+str2+",\"price\":\""+price+"\",\"paid_status\":\""+paid+"\",\"click_url\":\""+clickURL+"\",\"pay_url\":\""+ParamConfig.VerifyBallanceStatus+"\"}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
