package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.JFBRankBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.UserJFBRankManager;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/UserJFBRankServlet")
public class UserJFBRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserJFBRankServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String type = request.getParameter("type");
		List<JFBRankBean> allMonth = UserJFBRankManager.getAllMonthJFBRank();
		List<JFBRankBean> allTotal = UserJFBRankManager.getAllTotalJFBRank();
		List<JFBRankBean> userTotal = new ArrayList<JFBRankBean>();
		List<JFBRankBean> userMonth = new ArrayList<JFBRankBean>();
		if(uid != null && !"".equals(uid)){			
			userTotal = UserJFBRankManager.getUserTotalJFBRank(uid);
			userMonth = UserJFBRankManager.getUserMonthJFBRank(uid);
		}
		if(type != null && !"".equals(type)){
			long time = System.currentTimeMillis();
			if(type.equals("refreshTable")){
				UserJFBRankManager.updateJFBRank();	//更新数据，并且新建表存入数据
			}else if(type.equals("refresh")){
				UserJFBRankManager.updateJFBRankTable();	//从新建并且插入数据的表更新数据
			}
			response.getWriter().print("更新成功，用时："+(System.currentTimeMillis()-time));
			return;
		}
		response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,getResult(allMonth, allTotal, userMonth, userTotal)).toString());
	}


	public static String getResult(List<JFBRankBean> allMonth,List<JFBRankBean> allTotal,List<JFBRankBean> userMonth,List<JFBRankBean> userTotal ){
		StringBuffer sb = new StringBuffer();
		sb.append("<ranks>");
		sb.append("<where type=\"我的排名\">");
		sb.append("<when type=\"本月返利榜\">");
		if(userMonth != null && userMonth.size() >0){
			for(JFBRankBean bean :userMonth){
				sb.append(bean.toXML());
			}
		}
		sb.append("</when>");
		sb.append("<when type=\"累计返利榜\">");
		if(userTotal != null && userTotal.size() >0){
			for(JFBRankBean bean :userTotal){
				sb.append(bean.toXML());
			}
		}
		sb.append("</when>");
		sb.append("</where>");
		
		sb.append("<where type=\"榜首淘友\">");
		sb.append("<when type=\"本月返利榜\">");
		if(allMonth != null && allMonth.size() >0){
			for(JFBRankBean bean :allMonth){
				sb.append(bean.toXML());
			}
		}
		sb.append("</when>");
		sb.append("<when type=\"累计返利榜\">");
		if(allTotal != null && allTotal.size() >0){
			for(JFBRankBean bean :allTotal){
				sb.append(bean.toXML());
			}
		}
		sb.append("</when>");
		sb.append("</where>");
		sb.append("</ranks>");
		return sb.toString();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
