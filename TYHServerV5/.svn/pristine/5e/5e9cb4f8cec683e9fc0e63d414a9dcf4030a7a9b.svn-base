package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SuperDay;
import cn.youhui.bean.SuperDays;
import cn.youhui.cacher.dao.SuperDaysCacher;
import cn.youhui.cacher.dao.superDiscountCacher;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.SuperDaysDAO;
import cn.youhui.dao.SuperDiscountDAO;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Type;
import cn.youhui.manager.LogManager;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuperTools;

/**
 * jiangjun
 */
@WebServlet("/tyh3/super_discount")
public class GetSuperDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSuperDiscount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long time1=System.currentTimeMillis();
		
		String type=request.getParameter("type");
		String uid=request.getParameter("uid");
		String platform=request.getParameter("platform");
		System.out.println("platform************:" + platform);
		if(uid==null){
			uid="";
		}
		if(platform==null){
			platform="";
		}
		
		int pagNow=1;
		if(request.getParameter("page")!=null&&!"".equals(request.getParameter("page"))){
			int tmp=Integer.parseInt(request.getParameter("page"));
			if(tmp>0){
				pagNow=tmp;
			} 
		}
		
		try{
			SuperDays sds=null;
			if(SuperTools.ifOnTime()){//判断是否到时间 给看下期预告
				sds=SuperDaysCacher.getInstance().getSuperDays(SuperDaysDAO.STATUS_IN_USE+"");
			}else{
				sds=SuperDaysCacher.getInstance().getSuperDays(SuperDaysDAO.STATUS_IN_USE2+"");
			}
			
			String tomorrow=SuperTools.getTomorrow();//获得明日的时间戳
			
			
			List<SuperDay> list=SuperDiscountDAO.getInstance().getInfoFromRedis(pagNow, uid, platform,tomorrow);
			
			
			sds.setList(list);
			String data="";
			if(type!=null&&"json".equalsIgnoreCase(type)){
				data=sds.toJson(platform);
				response.getWriter().print(RespStatusBuilder.messageJson(ActionStatus.NORMAL_RETURNED,"",superDiscountCacher.getInstance().getTotalPags(tomorrow),pagNow,data).toString());
			}else{
				data=sds.toXML(platform);
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED,"",superDiscountCacher.getInstance().getTotalPags(tomorrow),pagNow,data).toString());
			}
			
		//	LogManager.addlog(uid, "2", "11");//首页浏览统计
			LogManager.addlog(uid, Event.VIEW, Type.TAG, "11");
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	
}
