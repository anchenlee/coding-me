package cn.youhui.api.servlet2.professor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ProfRecomVote;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ProfRecomVoteDAO;
import cn.youhui.manager.ProfRecommendDAO;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

/**
 * 给达人推荐信息投票
 * @author lijun
 * @since 2013-10-28
 */
@WebServlet("/tyh2/voteprof")
public class YHVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String recommendId = ParamUtil.getParameter(request, "recommend_id", true);
			String uid = ParamUtil.getParameter(request, "uid", true);
			int voteValue = ParamUtil.getParameterInt(request, "vote_value");
			String taobaoNick = UserManager.getInstance().getUserNick(uid);
			if(!StringUtils.isEmpty(taobaoNick)){
				ProfRecomVote vote = new ProfRecomVote(recommendId, uid, voteValue);
				
				int oldVoteValue = ProfRecomVoteDAO.getInstance().getVote(recommendId, uid);
				
				int voteType = 0;
				if(oldVoteValue == -1){
					if(voteValue == ProfRecomVoteDAO.VOTE_GOOD){
						voteType = 1;
					}else if(voteValue == ProfRecomVoteDAO.VOTE_BAD){
						voteType = 2;
					}
				}else if(oldVoteValue == ProfRecomVoteDAO.VOTE_BAD){
					if(voteValue == ProfRecomVoteDAO.VOTE_GOOD){
						voteType = 3;
					}
				}else if(oldVoteValue == ProfRecomVoteDAO.VOTE_GOOD){
					if(voteValue == ProfRecomVoteDAO.VOTE_BAD){
						voteType = 4;
					}
				}
				if(voteType != 0){
					ProfRecommendDAO.getInstance().updateVote(recommendId, voteType);
				}
				
				boolean flag = ProfRecomVoteDAO.getInstance().addVote(vote);
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
