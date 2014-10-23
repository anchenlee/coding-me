package cn.youhui.api.servlet3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.ProfRecomCacher;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 获取首页推荐商品更新条数
 * @author lijun
 * @since 
 */
@WebServlet("/tyh3/upamount")
public class GetUpRecomdAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String format = ParamUtil.getParameter(request, "format");
			long lastTime = ParamUtil.getParameterLong(request, "last_time");
			int amount = ProfRecomCacher.getInstance().getUpNum(lastTime);
			if("json".equalsIgnoreCase(format)){
				response.getWriter().print(RespStatusBuilder.messageJson(ActionStatus.NORMAL_RETURNED, "", getJsonResult(amount)));
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,"",getResult(amount)).toString());
			}
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}
	
	private String getJsonResult(int amount){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"amount\":");
		sb.append(amount);
		sb.append("}");
		return sb.toString();
	}
	
	private String getResult(int amount){
		StringBuffer sb = new StringBuffer();
		sb.append("<amount>");
		sb.append(amount);
		sb.append("</amount>");
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
