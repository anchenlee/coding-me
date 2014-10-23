package cn.youhui.api.servlet2.professor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.Professor;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfessorDAO;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 获取达人详情
 * @author lijun
 * @since 2031-10-21
 */
@WebServlet("/tyh2/professor")
public class YHGetProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			response.setCharacterEncoding("UTF-8");
			String professorId = ParamUtil.getParameter(request, "professor_id", true);
			Professor prof = ProfessorDAO.getInstance().getProfessor(professorId);
			if(prof != null){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, prof.toXML()));
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			}
		}catch(BadParameterException e){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
