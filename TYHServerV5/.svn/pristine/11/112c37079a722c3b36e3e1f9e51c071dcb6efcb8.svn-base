package cn.youhui.api.servlet2.professor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.Professor;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfUserProfessorDAO;
import cn.youhui.manager.ProfessorDAO;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 获取达人列表
 * @author lijun
 * @since 2013-10-10
 */
@WebServlet("/tyh2/professorlist")
public class YHGetProfessorList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = ParamUtil.getParameter(request, "uid");
		int page = ParamUtil.getParameterInt(request, "page", 1);
		
		List<Professor> list = ProfessorDAO.getInstance().getList(page);
		int total = ProfessorDAO.getInstance().getTotalPage();
		String ownProfessors = ProfUserProfessorDAO.getInstance().getProfessorIds(uid);
		
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED , "", total, page ,toResult(list, ownProfessors)).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	private String toResult(List<Professor> list, String professors){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0){
			sb.append("<professors>");
			for(Professor bean : list){
				if(professors != null && professors.contains(bean.getId())){
					bean.setOwn(1);
				}
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
