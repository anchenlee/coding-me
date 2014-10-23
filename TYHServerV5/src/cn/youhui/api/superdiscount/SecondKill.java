package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.youhui.api.checkcode.CheckCodeManager;
import cn.youhui.cacher.dao.secondKillCacher;
import cn.youhui.common.ParamConfig;
import cn.youhui.common.YouHuiLogger;
import cn.youhui.dao.SecondKillDAO;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.ParamSignUtil;

/**
 * jiangjun
 */
@WebServlet("/super/secondKill")
public class SecondKill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SecondKill() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		String method=request.getParameter("method");
		
		if(method!=null){
			if(method.equals("set_num")){//购买后回调设置商品剩余量
				String num=request.getParameter("num");
				String couponId=request.getParameter("coupon_id");
				if(num==null||couponId==null||"".equals(num)||"".equals(couponId)){
					response.getWriter().print("paramException");
					return;
				}
				String type=couponId.substring(0,3);
				String typeId=couponId.substring(3,couponId.length());
				cn.youhui.bean.SecondKill sk=secondKillCacher.getInstance().getSecondKillById(ParamConfig.secondKillType+typeId);
				sk.setRemainNum(Integer.parseInt(num));
				secondKillCacher.getInstance().addSecondKill(type+typeId, sk);
				
				if(SecondKillDAO.getInstance().setNum(Integer.parseInt(num),type, Integer.parseInt(typeId))){
					response.getWriter().print("success");
				}else{
					response.getWriter().write("fail");
				}
			}else if(method.equals("buy")){//验证码
				if(request.getParameter("code")==null||"".equals(request.getParameter("code"))||request.getParameter("id")==null||"".equals(request.getParameter("id"))||request.getParameter("uid")==null||"".equals(request.getParameter("uid"))){
					response.getWriter().print("ParamException");
					return;
				}
				String code=request.getParameter("code");
				String uid=request.getParameter("uid");
				int id=Integer.parseInt(request.getParameter("id"));
				
				
				Cookie[] cookies = request.getCookies();
				String sid = "";
				if (cookies== null || cookies.length <= 0 ) {
//					response.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.NO_COOKIE), format));
				}else {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("sid")) {
							sid = cookie.getValue();
						}
					}
				}
				if(!CheckCodeManager.checkCode(sid, code)){
					response.getWriter().print("codeerror");
					YouHuiLogger.logToFile(id+"", uid, sid, code, "codeerror");
					return;
				}
				
				//检查是否已经开卖
				if(new Date().getTime()<secondKillCacher.getInstance().getSecondKillById(ParamConfig.secondKillType+id).getKillStartTimestamp()){
					response.getWriter().write("not_on_time");
					YouHuiLogger.logToFile(id+"", uid, sid, code, "not_on_time");
					return;
				}
				
				//检查是否已经结束
				if(new Date().getTime()>secondKillCacher.getInstance().getSecondKillById(ParamConfig.secondKillType+id).getKillEndTimestamp()){
					response.getWriter().write("out_of_time");
					YouHuiLogger.logToFile(id+"", uid, sid, code, "out_of_time");
					return;
				}
				
				//验证uid是否存在
				if(!UserManager.getInstance().isExsitUid(uid)){
					response.getWriter().write("no_such_uid");
					YouHuiLogger.logToFile(id+"", uid, sid, code, "no_such_uid");
					return;
				}
				
				Map<String,String> map=new HashMap<String,String>();
				map.put("coupon", ParamConfig.secondKillType+id);
				map.put("user", uid);
				map.put("format", "json");
				System.out.println("====================================");
				String str=ParamSignUtil.getSign(map, ParamConfig.MD5Key);
				System.out.println("====================================");
				String bac=NetManager.getInstance().send(ParamConfig.secondkillbuy,"user="+uid+"&coupon="+ParamConfig.secondKillType+id+"&sign="+str+"&format=json");
				YouHuiLogger.logToFile(id+"", uid, sid, code,bac);
				response.getWriter().print(bac);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

  
	
}
