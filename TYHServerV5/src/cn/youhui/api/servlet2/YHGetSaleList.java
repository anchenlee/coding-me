package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.Action;
import cn.youhui.bean.Sale;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.SaleDateCache;
import cn.youhui.utils.ActionChangeUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

/**
 * 特卖
 * @author leejun
 * @since 2013-04-25
 */
@WebServlet("/tyh2/sale")
public class YHGetSaleList extends HttpServlet {     
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String version_code = request.getParameter("version_code");
		
		if(uid == null)
		{
			uid = "";
		}
		
		int pageint = 0;
		if(StringUtils.isNumeric(page))
		{
			pageint = Integer.parseInt(page);
		}
//		if(SaleDateCache.getInstance().isSaleNeedFresh())
//		{
//			SaleDateCache.getInstance().refreshSale();
//			SaleDateCache.getInstance().addSaleFreshSign(System.currentTimeMillis());
//		}
		List<Sale> list = null;
		if(pageint < 0)
		{
			list = SaleDateCache.getInstance().getAfterSale(pageint);
		}
		else 
		{
			list = SaleDateCache.getInstance().getBeforeSale(pageint);
		}
		// 低版本检查，并转换Action
		list = checkLowVersionAction(list, uid, platform, version_code);
		
		StringBuffer data = new StringBuffer();
		if(list != null && list.size() > 0)
		{
			data.append("<sales>");
			data.append("<currentpage>"+ pageint +"</currentpage>");
			data.append("<not_start_num>"+ SaleDateCache.getInstance().getNotStartNum() +"</not_start_num>");
			data.append("<beforepage>"+ SaleDateCache.getInstance().getSaleBeforeSize() +"</beforepage>");
			data.append("<afterpage>"+ SaleDateCache.getInstance().getSaleAfterSize() + "</afterpage>");
			for(Sale sale : list)
			{
				data.append(sale.toXML());
			}
			data.append("</sales>");
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,"",data.toString()).toString());
		}
		else
		{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT,"").toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private List<Sale> checkLowVersionAction(List<Sale> sales, String uid, String platform, String version_code)
	{
		List<Sale> result = new ArrayList<Sale>();
		for (Sale sale : sales)
		{
			Action action = ActionChangeUtil.lowVersionItemAction(sale.getId(), uid, platform, "8", 
					sale.getAction().getActionType(), sale.getAction().getActionValue(), version_code);
			sale.setAction(action);
			
			result.add(sale);
		}
		
		return result;
	}
}
