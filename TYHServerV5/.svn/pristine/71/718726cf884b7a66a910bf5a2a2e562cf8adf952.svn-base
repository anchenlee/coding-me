package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.TaobaoShop;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.SaleExplainManager;
import cn.youhui.ramdata.SaleExplainCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 特惠活动说明中的商品，店铺列表
 * @author lijun
 * @since 2013-09-28
 */
@WebServlet("/tyh2/saleexplainitems")
public class YHGetSaleExplainItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		if(uid == null || "".equals(uid)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
//		String saleId = request.getParameter("sale_id");
		String explainId = request.getParameter("explain_id");
		List<TeJiaGoodsBean> itemlist = SaleExplainCacher.getInstance().getItemsByExplain(explainId);
		List<TaobaoShop> shoplist = SaleExplainManager.getInstance().getShopsByExplainId(explainId);
		if((itemlist != null && itemlist.size()>0) || (shoplist != null && shoplist.size() >0)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, toResult(itemlist,shoplist, uid)).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}
	
	private String toResult(List<TeJiaGoodsBean> itemlist, List<TaobaoShop> shoplist, String uid){
		StringBuffer sb = new StringBuffer();
		if(itemlist != null && itemlist.size() > 0){
			sb.append("<items>");
			for(TeJiaGoodsBean bean : itemlist){
				sb.append(bean.toXML());
			}
			sb.append("</items>");
		}
		
		if(shoplist != null && shoplist.size() > 0){
			sb.append("<shops>");
			for(TaobaoShop bean : shoplist){
				sb.append(bean.toXML());
			}
			sb.append("</shops>");
		}
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
