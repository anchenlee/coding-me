package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums;
import cn.youhui.manager.KeywordCountManager;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

/**
 * 关键字搜索统计
 * @author lijun
 * @since 2013-09-13
 */
@WebServlet("/tyh2/keycount")
public class YHKeywordCountNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("search_type");
		String keyword = request.getParameter("search_key");
		String uid = request.getParameter("uid");
		if(StringUtils.isEmpty(keyword)){
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
		if(uid == null){
			uid = "";
		}
		KeywordCountManager.getInstance().add(keyword, type, uid);
		response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.NORMAL_RETURNED));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
