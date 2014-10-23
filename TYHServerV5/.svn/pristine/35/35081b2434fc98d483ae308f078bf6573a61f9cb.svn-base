package cn.youhui.api.servlet2.professor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfUserProfessorDAO;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

/**
 * 修改订阅的达人
 * @author lijun
 * @since 2013-10-11
 */
@WebServlet("/tyh2/addprofessors")
public class YHAddProfessors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String addProfessorIds = ParamUtil.getParameter(request, "add_professor_ids");
			String delProfessorIds = ParamUtil.getParameter(request, "del_professor_ids");
			String uid = ParamUtil.getParameter(request, "uid", true);
			String taobaoNick = UserManager.getInstance().getUserNick(uid);
			if(StringUtils.isEmpty(taobaoNick) || !check(addProfessorIds) || !check(delProfessorIds)){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
				return;
			}
			boolean flag = ProfUserProfessorDAO.getInstance().updateUserProf(uid, addProfessorIds, delProfessorIds);
			if(flag){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
			}
		}catch(BadParameterException e){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}
	
	private boolean check(String professorIds){
		if(professorIds == null || "".equals(professorIds)){
			return true;
		}
		String[] professorIdss = professorIds.split(",");
		for(String professorId : professorIdss){
			if(!StringUtils.isNumeric(professorId)){
				return false;
			}
		}
		return true;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
