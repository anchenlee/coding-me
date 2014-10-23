package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.HotKeyword;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.HotKeywordManager;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh3/hotkeywords")
public class GetHotKeyword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetHotKeyword() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<HotKeyword> list = HotKeywordManager.getHotKeywordList();
		response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED,listToString(list)).toString());
	}


	private static String listToString(List<HotKeyword> list){
		StringBuffer sb = new StringBuffer();
		sb.append("<hot_keyword_list>");
		if(list != null && list.size() > 0){
			for(HotKeyword bean : list){
				sb.append(bean.toXML());
			}
		}
		sb.append("</hot_keyword_list>");
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
