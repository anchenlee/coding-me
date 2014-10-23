package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.jfbad.JFBAd;
import cn.youhui.jfbad.JFBAdManager;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 获取集分宝广告列表
 * @author lijun
 * @since 2014-02-26
 */
@WebServlet("/tyh3/jfbad")
public class GetJFBAd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String uid = ParamUtil.getParameter(request, "uid");
			List<JFBAd> list = JFBAdManager.getInstance().getAdListByUid(uid);
			if(list != null && list.size() > 0){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,"",toResult(list)).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT,"").toString());
			}
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}
	
	private String toResult(List<JFBAd> list){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() >0){
			sb.append("<jfb_ads>");
			for(JFBAd ad : list){
				sb.append(ad.toXml());
			}
			sb.append("</jfb_ads>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
