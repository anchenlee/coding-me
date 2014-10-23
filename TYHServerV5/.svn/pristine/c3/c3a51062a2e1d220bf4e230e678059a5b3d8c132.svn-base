package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.IpadFeedbackBean;
import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.AdminFeedbackDAO;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh3/getFeedback")
public class GetFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		if ("".equals(method) || method == null) {
			response.getWriter().print("method could not be null");
			return;
		}
		if ("addFeedback".equals(method)) {
			addFeedback(request,response);
		}else if("getFeedbackByUid".endsWith(method)){
			getFeedbackByUid(request,response);
		}
	}
	
	public void addFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String version = request.getParameter("version");
		String msg = request.getParameter("msg");
		String type = request.getParameter("type"); //消息类型  0 客服  1用户
		if (uid == null || "".equals(uid) || "".equals(platform) || platform ==null || "".equals(version)
				|| version == null || "".equals(msg) || msg == null || "".equals(type) || type == null) {
			return;
		}
//		msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
		
		IpadFeedbackBean ifb = new IpadFeedbackBean();
		ifb.setUid(uid);
		ifb.setMsg(msg);
		ifb.setType(Integer.parseInt(type));
		ifb.setPlatform(platform);
		ifb.setVersion(version);
		ifb.setCreateTime(System.currentTimeMillis());
		ifb.setUpdateTime(System.currentTimeMillis());
		Boolean flag = AdminFeedbackDAO.addIpadFeedbackBean(ifb);
		if (flag) {
			response.getWriter().println(RespStatusBuilder.message(Enums.ActionStatus.NORMAL_RETURNED));
		}else{
			response.getWriter().println(RespStatusBuilder.message(Enums.ActionStatus.NO_RESULT));
		}
	}

	public void getFeedbackByUid(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String uid = request.getParameter("uid");
		String pageStr = request.getParameter("page");
		int page = 1;
		if ("".equals(pageStr) || pageStr == null) {
			page = 1;
		}else {
			page = Integer.parseInt(pageStr);
		}
		List<IpadFeedbackBean> list = AdminFeedbackDAO.getIpadFeedbacksByUid(uid,page);
		int count = AdminFeedbackDAO.getIpadFeedbacksCount(uid);
		int ys = count%AdminFeedbackDAO.pageSize;
		int pageCount = (count-ys)/AdminFeedbackDAO.pageSize;
		if(ys > 0)
			pageCount ++;
		if(list == null || list.size() == 0){
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.NO_RESULT));
			return;
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", pageCount, page, listToString(list)));
			return;
		}
	}
	
	private static String listToString(List<IpadFeedbackBean> list){
		StringBuffer sb = new StringBuffer();
		sb.append("<feedback_list>");
		if(list != null && list.size() > 0){
			for(IpadFeedbackBean bean : list){
				sb.append(bean.toXML());
			}
		}
		sb.append("</feedback_list>");
		return sb.toString();
	}
}
