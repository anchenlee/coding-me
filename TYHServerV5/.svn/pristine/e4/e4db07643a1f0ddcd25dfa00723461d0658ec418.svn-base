package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ActivityJoin;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ActivityJoinManager;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh2/getjoins2")
public class YHGetActivityJoinList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String access = request.getParameter("access_token");
		String page = request.getParameter("page");
		String data = request.getParameter("data");
		if(data == null || "".equals(data)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		data = data.replaceAll(" ", "+");
		data = Encrypt.decode(data);
		String param[] = data.split("#");
		if(param.length !=4){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		String uid = param[1];
		String taobaoNick = param[2];
		if(uid == null || "".equals(uid) || !UserManager.getInstance().isUserExist(uid, taobaoNick)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
			return;
		}
//		String uid = request.getParameter("uid");
		if(access == null)
			access = "";
		if(page == null || "".equals(page))
			page = "1";
		int pageInt = Integer.parseInt(page);
		int pageCount = ActivityJoinManager.getInstance().getPageNum(uid);
		if(pageCount == 0) {
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(pageInt < 1) pageInt = 1;
		else if(pageInt > pageCount) pageInt = pageCount;
		List<ActivityJoin> list = ActivityJoinManager.getInstance().getList(uid, pageInt);
		int allJfbNum = ActivityJoinManager.getInstance().getGainJfbNum(uid);
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, pageCount, pageInt, changetoXml(list,allJfbNum)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	private String changetoXml(List<ActivityJoin> list, int allJfb){
		StringBuffer sb = new StringBuffer();
		sb.append("<all_jfb>"+ allJfb +"</all_jfb>");
		if(list != null && list.size() > 0){
			sb.append("<join_list>");
			for(ActivityJoin join : list){
				sb.append(join.toXML());
			}
			sb.append("</join_list>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
