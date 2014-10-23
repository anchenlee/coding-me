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
import cn.youhui.manager.ProfRecomVoteDAO;
import cn.youhui.manager.ProfRecommendDAO;
import cn.youhui.manager.ProfUserProfessorDAO;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

/**
 * 获取达人推荐商品列表
 * @author lijun
 * @since 2013-10-11
 */
@WebServlet("/tyh2/profitems")
public class YHGetProfItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = ParamUtil.getParameter(request, "uid");
		String professorId = ParamUtil.getParameter(request, "professor_id");
		int page = ParamUtil.getParameterInt(request, "page", 1);
		
		List<ProfRecommend> list = null;
		int total = 0;
		if(!StringUtils.isEmpty(professorId)){
			list = ProfRecommendDAO.getInstance().getListByProf(professorId, page);
			total = ProfRecommendDAO.getInstance().getTotalPage(professorId);
		}else{
			String userProfIds = "";
			if(!StringUtils.isEmpty(uid)){
				userProfIds = ProfUserProfessorDAO.getInstance().getProfessorIds(uid);
			}
			if(userProfIds == null || "".equals(userProfIds)){
				list = ProfRecommendDAO.getInstance().getList(page);
			}else{
				list = ProfRecommendDAO.getInstance().getListByProfs(userProfIds, page);
			}
			total = ProfRecommendDAO.getInstance().getTotalPage();
		}
		
		String voteGoodRecoms = ProfRecomVoteDAO.getInstance().getGoodVoteRecoms(uid);
		String voteBadRecoms = ProfRecomVoteDAO.getInstance().getBadVoteRecoms(uid);
		
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED , "", total, page ,toResult(list, voteGoodRecoms, voteBadRecoms)).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	private String toResult(List<ProfRecommend> list, String voteGoodRecoms, String voteBadRecoms){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0){
			sb.append("<prof_items>");
			for(ProfRecommend bean : list){
				if(voteGoodRecoms.indexOf(bean.getId() + ",") > -1){
					bean.setUserVote(1);
				}else if(voteBadRecoms.indexOf(bean.getId() + ",") > -1){
					bean.setUserVote(2);
				}
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
