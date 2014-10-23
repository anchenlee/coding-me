package cn.youhuiWeb.api.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhuiWeb.ramdata.HotRecommendCacher;

@WebServlet("/youhuiWeb/hotRecommend")
public class GetHotRecommend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{			
			
			//从redis获取数据
			String content = HotRecommendCacher.getHotRecommendView();

			StringBuffer data = new StringBuffer();

			if(content != null ){
				data.append("<hot_recommend_view>");
				data.append("<![CDATA[").append(content).append("]]>");
				data.append("</hot_recommend_view>");				

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