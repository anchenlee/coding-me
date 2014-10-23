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
 * 现金提现列表
 * @author lijun
 * @since 2014-03-05
 */
@WebServlet("/tyh3/xjtxlist")
public class GetXJTXList extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
		if(page == null || "".equals(page)){
			page = "1";
		}
		int pageInt = Integer.parseInt(page);
		int pageCount = JiFenBaoMXManager.getInstance().getXJTXTradePageNum(uid);
		if(pageCount == 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			return;
		}
		if(pageInt < 1){
			pageInt = 1;
		}else if(pageInt > pageCount){
			pageInt = pageCount;
		}
		List<JiFenBaoTrade> list = JiFenBaoMXManager.getInstance().getXJTXTradeList(uid, pageInt);
		if(list != null && list.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", pageCount, pageInt, changetoXml(list)));
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
