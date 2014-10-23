package cn.youhui.api.servlet3;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SuperDays;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.bean.TomorrowForecastBean;
import cn.youhui.cacher.dao.superDiscountCacher;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.common.ParamConfig;
import cn.youhui.dao.SuperDaysDAO;
import cn.youhui.dao.SuperDiscountDAO;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuiShouActionUtil;
import cn.youhui.utils.SuperTools;

/**
 * jiangjun
 */
@WebServlet("/tyh3/tomorrow_forecast")
public class TomorrowForecast extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TomorrowForecast() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid=request.getParameter("uid");
		String platform=request.getParameter("platform");
		List<SuperDiscountBean> list=superDiscountCacher.getInstance().getSuperDisCountListByDate(SuperTools.getTomorrow());
		if(list.size()==0){//如果明日没有商品看看是否后天有预告（周末）
			list=superDiscountCacher.getInstance().getSuperDisCountListByDate(SuperTools.getTomorrow2());
		}
		if(list.size()==0){//如果明日没有商品看看是否大后天有预告（周末）
			list=superDiscountCacher.getInstance().getSuperDisCountListByDate(SuperTools.getTomorrow3());
		}
		for(int i=0;i<list.size();i++){
			SuperDiscountBean sb=list.get(i);
			if(sb.getIsHide()==1&&!uid.equals(SuperDiscountDAO.ADMIN_UID)){
				list.remove(sb);
				i--;
			}else{
				sb.setClickUrl(SuiShouActionUtil.getSuishouWebUrl(platform,ParamConfig.tomorrow_start_rush_url+"?id="+sb.getId()));
			}
		}
		System.out.println("....................................."+list.size());
		if(list.size()>0){
			TomorrowForecastBean tmb=new TomorrowForecastBean();
			tmb.setList(list);
			tmb.setTips(ParamConfig.tomorrowTips);
			tmb.setTitle(ParamConfig.tomorrowTitle);
			
			SuperDays sds=SuperDaysDAO.getInstance().getInfo(platform,SuperDaysDAO.STATUS_IN_USE);
			tmb.setChayeIcon(sds.getChayeIcon());
			tmb.setChayeSsActionUrl(sds.getChayeSuiShouAction());
			tmb.setChayeWhRatio(sds.getChayeWhRatio());
			
			
			if(!SuperTools.ifOnTime()){
				StringBuffer sb=new StringBuffer();
				if(request.getParameter("type")!=null&&"json".equals(request.getParameter("type"))){
					response.getWriter().write("not_on_time");
				}else{
					sb.append("<url>"+ParamConfig.outTimePre+"</url>");
					response.getWriter().write(RespStatusBuilder.message(ActionStatus.NOT_ON_TIME ,"",sb.toString()).toString());
				}
				return;
			}
			
//			if(request.getParameter("c")!=null){
//				StringBuffer sb=new StringBuffer();
//				if(request.getParameter("type")!=null&&"json".equals(request.getParameter("type"))){
//					response.getWriter().write("not_on_time");
//				}else{
//					sb.append("<url>"+ParamConfig.outTimePre+"</url>");
//					response.getWriter().write(RespStatusBuilder.message(ActionStatus.NOT_ON_TIME ,"",sb.toString()).toString());
//				}
//				return;
//			}
			
			if(request.getParameter("type")!=null&&"json".equals(request.getParameter("type"))){
				
				response.getWriter().print(RespStatusBuilder.messageJson(ActionStatus.NORMAL_RETURNED,"",1,1,tmb.toJson(platform)).toString());
				return;
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED,"",1,1,tmb.toXml(platform)).toString());
				return;
			}
		}
		response.getWriter().write("not_on_time");
//		response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT ,"",data.toString()).toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public static String dat() {
		SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
		String date=sdf.format(new Date().getTime()+Long.parseLong("86400000"));
		if(date.substring(0,1).equals("0")){
			date=date.substring(1,date.length());
		}
		return date;
	}
	
}
