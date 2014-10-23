package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SaleNew;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.SaleNewManager;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 特惠新接口
 * @author lijun
 * @since 2013-09-26
 */
@WebServlet("/tyh2/salenew")
public class YHGetSalesNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int page = ParamUtil.getParameterInt(request, "page", 1);
		List<SaleNew> bsales = SaleNewManager.getInstance().getBodySaleItems(page);
		List<SaleNew> hsales = null;
		if(page ==1){
			hsales = SaleNewManager.getInstance().getHeadSaleItems();
		}
		if(bsales != null && bsales.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED , "", 2, page,toResult(hsales, bsales)).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	private String toResult(List<SaleNew> hlist, List<SaleNew> blist){
		StringBuffer sb = new StringBuffer();
		if(hlist != null && hlist.size() > 0){
			sb.append("<head_sales>");
			for(SaleNew sale : hlist){
				sb.append(sale.toXML());
			}
			sb.append("</head_sales>");
		}
		if(blist != null && blist.size() > 0){
			sb.append("<sales>");
			for(SaleNew sale : blist){
				sb.append(sale.toXML());
			}
			sb.append("</sales>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
