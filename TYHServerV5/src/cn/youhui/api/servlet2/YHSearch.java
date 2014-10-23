package cn.youhui.api.servlet2;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.manager.TaobaoManager;
import cn.youhui.ramdata.Keyword2ItemCacher;
import cn.youhui.utils.ParamUtil;

/**
 * 搜索
 * @author lijun
 *
 */
@WebServlet("/tyh2/search")
public class YHSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//type:item;itemid;shop;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String type = ParamUtil.getParameter(request, "type");
		String keyword = ParamUtil.getParameter(request, "keyword");
		System.out.println(keyword);
		System.out.println(new String(keyword.getBytes("ISO-8859-1"), "UTF-8"));
		String pageNo = ParamUtil.getParameter(request, "page_no");
		String pageSize = ParamUtil.getParameter(request, "page_size");
		String startCredit = ParamUtil.getParameter(request, "start_credit");
		String endCredit = ParamUtil.getParameter(request, "end_credit");
		
		String platform = ParamUtil.getParameter(request, "platform");
		 
		String method = ParamUtil.getParameter(request, "method");
		
		String outerCode = ParamUtil.getParameter(request, "outer_code");
		String ret = "";
		if(method != null && method.indexOf("shop") > -1){
			boolean onlyMall = ParamUtil.getParameterBoolean(request, "only_mall");
			if("android".equalsIgnoreCase(platform)){
				ret = TaobaoManager.searchShop(keyword, onlyMall, startCredit, endCredit, pageNo, pageSize, "json");
			}else{
				ret = TaobaoManager.searchShop(keyword, onlyMall, startCredit, endCredit, pageNo, pageSize, "xml");
			}
		}else{
			String mallItem = ParamUtil.getParameter(request, "mall_item");
			String startPrice = ParamUtil.getParameter(request, "start_price");
			String endPrice = ParamUtil.getParameter(request, "end_price");
			String sort = ParamUtil.getParameter(request, "sort");
			if("android".equalsIgnoreCase(platform)){
				ret = Keyword2ItemCacher.getSearch(keyword, pageNo,"json");
				if(ret == null || "".equals(ret)){
					pageNo = Keyword2ItemCacher.getTruePage(keyword, pageNo);//将page页数减去redis缓存页数
					ret = TaobaoManager.searchItem(keyword, mallItem, startPrice, endPrice, startCredit, endCredit, pageNo, pageSize, sort, "json");
				}
			}else if("pc".equalsIgnoreCase(platform)){
				keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8"); // 客户端get方法请求，需要转码
				ret = Keyword2ItemCacher.getSearch(keyword, pageNo,"xml");
				if(ret == null || "".equals(ret)){
					pageNo = Keyword2ItemCacher.getTruePage(keyword, pageNo);	//将page页数减去redis缓存页数
					ret = TaobaoManager.searchItem(keyword, mallItem, startPrice, endPrice, startCredit, endCredit, pageNo, pageSize, sort, "xml");
				}
			}else{
				ret = Keyword2ItemCacher.getSearch(keyword, pageNo,"xml");
				if(ret == null || "".equals(ret)){
					pageNo = Keyword2ItemCacher.getTruePage(keyword, pageNo);	//将page页数减去redis缓存页数
					ret = TaobaoManager.searchItem(keyword, mallItem, startPrice, endPrice, startCredit, endCredit, pageNo, pageSize, sort, "xml");
				}
			}
		}
//		if("android".equalsIgnoreCase(platform)){
//			ret = ret.replaceAll("taobaoke_mobile_items_get_response", "tbk_items_get_response").replaceAll("taobaoke_mobile_shops_get_response", "tbk_shops_get_response");
//		}else{
//			ret = ret.replaceAll("taobaoke_mobile_items_get_response", "taobaoke_items_get_response").replaceAll("taobaoke_mobile_shops_get_response", "taobaoke_shops_get_response");
//		}
		response.getWriter().write(ret);
//		response.getWriter().write(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
