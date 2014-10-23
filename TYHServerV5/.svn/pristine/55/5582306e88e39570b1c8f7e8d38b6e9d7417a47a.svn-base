package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SignInBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.SignInManager;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 获取签到记录
 * @author leejun
 * @since 2013-3-7
 */
@WebServlet("/tyh2/signinhis")
public class YHGetSignInHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHGetSignInHistory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String page = request.getParameter("page");
		if(page == null || "".equals(page)){
			page = "1";
		}
		int pageInt = Integer.parseInt(page);
		int pageNum = SignInManager.getInstance().getPageNum(uid);
		List<SignInBean> ret = SignInManager.getInstance().getSiginHistory(uid, page);
		String rets = changeToXml(ret);
		if(ret == null || ret.size() == 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "",pageNum,pageInt, rets));
		}
	}

	private String changeToXml(List<SignInBean> dates){
		StringBuffer ret = new StringBuffer();
		ret.append("<signins>");
		if(dates != null && dates.size() > 0)
		for(SignInBean bean :dates){
			ret.append(bean.toXmlDate());
		}
		else return null;
		ret.append("</signins>");
		return ret.toString();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
