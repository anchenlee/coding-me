package cn.youhuiWeb.api.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.common.X;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhuiWeb.bean.CatagoryAD;
import cn.youhuiWeb.manager.CatagoryAdManager;
import cn.youhuiWeb.ramdata.CatagoryADCacher;

/**
 * 获取首页分类广告
 */
@WebServlet("/youhuiWeb/catagoryAd")
public class GetCatagoryAd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			
			String catagoryId =  X.CachePrefix.CatagoryKeyMap.get(request.getParameter("catagory"));
			
			//从redis获取数据
			List<CatagoryAD> list = CatagoryADCacher.getAdList(catagoryId);
			if(list == null || list.size() == 0){
				CatagoryADCacher.getInstance().reload(catagoryId);
				list = CatagoryAdManager.getInstance().getAds(catagoryId);
			}
			StringBuffer data = new StringBuffer();

			if(list != null && list.size() > 0){
				data.append("<ads>");
				for(CatagoryAD ad : list){
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
