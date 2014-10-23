package cn.youhui.api.servlet2.professor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ProfRecomEvaluate;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfRecomEvaluateDAO;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 获取对达人推荐的评论列表
 * @author lijun
 * @since 2013-10-11
 */
@WebServlet("/tyh2/profevaluates")
public class YHGetEvaluateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int page = ParamUtil.getParameterInt(request, "page", 1);
			String recommendId = ParamUtil.getParameter(request, "recommend_id", true);
			
			List<ProfRecomEvaluate> list = ProfRecomEvaluateDAO.getInstance().getList(recommendId, page);
			int total = ProfRecomEvaluateDAO.getInstance().getTotalPage(recommendId);
			
			if(list != null && list.size() > 0){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED , "", total, page ,toResult(list)).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			}
		}catch(BadParameterException e){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}
	
	private String toResult(List<ProfRecomEvaluate> list){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0){
			sb.append("<professors>");
			for(ProfRecomEvaluate bean : list){
				sb.append(bean.toXML());
			}
			sb.append("</professors>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
