package cn.suishou.some.free;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.some.dao.UserDAO;
import cn.suishou.some.util.ParamUtil;


/**
 * 用户评价活动商品
 * @author hufan
 * @since 2014-8-15
 */
@WebServlet("/freeactivity")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String user_estimate=ParamUtil.getParameter(request, "pingjia");
		String attitude=ParamUtil.getParameter(request, "interest");
		String activityId=ParamUtil.getParameter(request, "activity_id");
		String uid=ParamUtil.getParameter(request, "uid");
		int star_num; 
		if(attitude.equals("like")){
			star_num=1;
		}else {
			star_num=0;
		}
		UserEstimate uem=new UserEstimate();
		long time=System.currentTimeMillis();
		uem.setUid(uid);
		uem.setEstimate(user_estimate);
		uem.setTaobaoNick(UserDAO.getInstance().getTBNick(uid));
		uem.setTime(time);
		uem.setStarNum(star_num);
		uem.setActivityId(activityId);
		if(UserEstimateManager.addUserEstimamteByUidItemId(uem)){
			response.getWriter().print("1");
		}else{
			response.getWriter().print("2");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
