package cn.youhui.api.servlet2.professor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ProfRecomEvaluate;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfRecomEvaluateDAO;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

/**
 * 添加评论信息
 * @author lijun
 * @since 2013-10-11
 */
@WebServlet("/tyh2/addprofevaluate")
public class YHAddEvaluate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String recommendId = ParamUtil.getParameter(request, "recommend_id", true);
			String uid = ParamUtil.getParameter(request, "uid", true);
			String evaluate = ParamUtil.getParameter(request, "evaluate", true);
			String taobaoNick = UserManager.getInstance().getUserNick(uid);
			if(!StringUtils.isEmpty(taobaoNick)){
				ProfRecomEvaluate eval = new ProfRecomEvaluate(recommendId, uid, taobaoNick, evaluate);
				boolean flag = ProfRecomEvaluateDAO.getInstance().addEvaluate(eval);
				if(flag){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
				}else{
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
				}
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			}
		}catch(BadParameterException e){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
