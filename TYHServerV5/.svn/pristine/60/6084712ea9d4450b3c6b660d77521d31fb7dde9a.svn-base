package cn.youhui.api.servlet3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.City;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.CityManager;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.CityUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.WebUtil;

/**
 * 获取用户所在city
 * @author lijun
 * @since 2014-4-2
 */
@WebServlet("/tyh3/getcity")
public class GetCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String ip = WebUtil.getIpAddr(request);
			String bdCityId = CityUtil.getBaiduCityCode(ip);
			if(bdCityId != null && !"".equals(bdCityId)){
				City city = CityManager.getInstance().getCityByBdCityId(bdCityId);
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,"",city.toXML()).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
			}
			
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
