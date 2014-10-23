package cn.youhuiWeb.api.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhuiWeb.bean.AD;
import cn.youhuiWeb.manager.AdManager;
import cn.youhuiWeb.ramdata.ADCacher;

/**
 * 获取首页广告
 */
@WebServlet("/youhuiWeb/ad")
public class GetAd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			//从redis获取数据
			List<AD> list = ADCacher.getAdList();
			if(list == null || list.size() == 0){
				ADCacher.getInstance().reload();
				list = AdManager.getInstance().getAds();
			}
			StringBuffer data = new StringBuffer();

			if(list != null && list.size() > 0){
				data.append("<ads>");
				for(AD ad : list){
					data.append(ad.toXML());	
				}
				data.append("</ads>");				

				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,"",data.toString()).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR,"").toString());
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
