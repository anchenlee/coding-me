package cn.youhui.api.servlet2.professor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ProfRecommend;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfRecommendDAO;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 根据商品ID 获取推荐此商品的达人列表
 * @author lijun
 * @since 2013-10-11
 */
@WebServlet("/tyh2/itemprofs")
public class YHGetItemProfs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int page = ParamUtil.getParameterInt(request, "page", 1);
			String itemId = ParamUtil.getParameter(request, "item_id", true);
			
			List<ProfRecommend> list = ProfRecommendDAO.getInstance().getListByItem(itemId, page);
			int total = ProfRecommendDAO.getInstance().getTotalPageByItem(itemId);
			
			if(list != null && list.size() > 0){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED , "", total, page ,toResult(list)).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			}
		}catch(BadParameterException e){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}
	
	private String toResult(List<ProfRecommend> list){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0){
			sb.append("<prof_items>");
			for(ProfRecommend bean : list){
				sb.append(bean.toXML());
			}
			sb.append("</prof_items>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
