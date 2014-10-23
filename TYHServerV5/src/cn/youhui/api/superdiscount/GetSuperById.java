package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.google.gson.JsonObject;

import cn.youhui.bean.CouponOrder;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.cacher.dao.secondKillCacher;
import cn.youhui.cacher.dao.superDiscountCacher;
import cn.youhui.common.Config;
import cn.youhui.common.ParamConfig;
import cn.youhui.dao.CouponOrderDAO;
import cn.youhui.dao.SecondKillDAO;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Type;
import cn.youhui.manager.LogManager;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.Util;

/**
 * 获取商品详情
 * jiangjun
 */
@WebServlet("/super/GetSuperById")
public class GetSuperById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSuperById() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("getsuper.......................................");
		if(request.getParameter("id")==null||"".equals(request.getParameter("id"))){
			response.getWriter().print("paramException");
			return;
		}
		int id=Integer.parseInt(request.getParameter("id"));
		String uid=request.getParameter("uid");
		String platform=request.getParameter("platform");
		
		
		
		
		long t=System.currentTimeMillis();
		SuperDiscountBean sb= superDiscountCacher.getInstance().getSuperDiscountById(id+"");
		long tt=System.currentTimeMillis();
		
		//订单详情相关信息start------------------------->
				String str="";
				if(sb.getIsSecondKill()==1){
					try{
						String orderid=CouponOrderDAO.getInstance().getCouponOrderid(ParamConfig.secondKillType+request.getParameter("id"), uid);
						
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
						str= "{\"order\":"+str1+",\"secondkill\":"+str2+",\"price\":\""+price+"\",\"paid_status\":\""+paid+"\",\"click_url\":\""+clickURL+"\"}";
					}catch(Exception e){
					}
				}
			//end---------------------------->
		
		sb.setImg(Util.getCustomImg(sb.getImg(), "600x600"));//设置大图
		//获得商品点击地址
		String outerCode = OuterCode.getOuterCode(uid, "", "0", "17", ""+id);
		String clickURL=Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" +sb.getItemId() ;
		sb.setClickUrl(clickURL);
		Cookie cookie = new Cookie("sid", ObjectId.get().toString());
		cookie.setMaxAge(3600);   //一个小时有效期
		cookie.setPath("/");
		response.addCookie(cookie);
		
		long ttt=System.currentTimeMillis();
		
		String dat=sb.toJson(platform);
		
		long tttt=System.currentTimeMillis();
		
		
		if(sb!=null){
			//订单详情相关信息start------------------------->   
			if(str.equals("")){
				dat=dat.substring(0,dat.length()-1)+",\"order_info\":\""+str+"\"}";
			}else{
				dat=dat.substring(0,dat.length()-1)+",\"order_info\":"+str+"}";
			}
			
			response.getWriter().write(dat);
			String message = "{\"title\":\"" + sb.getTitle().replaceAll("\\s*|\t|\r|\n|\"", "") + "\"}";
			//LogManager.addlog(uid,ParamConfig.FROM_CHANNEL_SUPER, "", "100", sb.getItemId(), "1", sb.getItemId(), message);
			//TPool.getInstance().doit(new VisitThread(uid, sb.getItemId(), message));		
			LogManager.addlog(uid, Event.VIEW, Type.PRODUCT, String.valueOf(id));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
//	class VisitThread extends Thread{
//			
//			private String uid = "";
//			private String itemId = "";
//			private String message = "";
//			
//			public VisitThread(String uid, String itemId, String message) {
//				super();
//				this.uid = uid;
//				this.itemId = itemId;
//				this.message = message;
//			}
//			
//			public void run(){
//				LogManager.addlog(uid,ParamConfig.FROM_CHANNEL_SUPER, "", "100", itemId, "1", itemId, message);
//			}
//			
//	}

}