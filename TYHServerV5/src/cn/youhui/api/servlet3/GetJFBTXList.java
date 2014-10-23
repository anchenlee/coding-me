package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.JiFenBaoTrade;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.JiFenBaoMXManager;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 获取集分宝提现列表
 * @author leejun
 * @since 2014-02-17
 */
@WebServlet("/tyh3/jfbtxlist")
public class GetJFBTXList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
//		if(uid == null || "".equals(uid) || !UserManager.getInstance().isUserExist(uid, taobaoNick)){
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.USER_NOT_LOGIN).toString());
//			return;
//		}
		if(access == null){
			access = "";
		}
		if(page == null || "".equals(page)){
			page = "1";
		}
		int pageInt = Integer.parseInt(page);
		int pageCount = JiFenBaoMXManager.getInstance().getJFBTXTradePageNum(uid);
		if(pageCount == 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(pageInt < 1){
			pageInt = 1;
		}else if(pageInt > pageCount){
			pageInt = pageCount;
		}
		List<JiFenBaoTrade> list = JiFenBaoMXManager.getInstance().getJFBTXTradeList(uid, pageInt);
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, pageCount, pageInt, changetoXml(list)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}

	private String changetoXml(List<JiFenBaoTrade> list){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0){
			sb.append("<trade_list>");
			for(JiFenBaoTrade trade : list){
				sb.append(trade.toXML());
			}
			sb.append("</trade_list>");
		}
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
