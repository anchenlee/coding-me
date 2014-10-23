package cn.youhui.api.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ExchItem;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.ExchItemCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 获取兑换商品列表
 * @author leejun
 * @since 2012-11-25
 */
@WebServlet("/tyh/exchitem")
public class YHGetExchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHGetExchItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String accessToken = request.getParameter("access_token");
		String page = request.getParameter("page");
		if(page == null || "".equals(page))
			page="1";
		int pageCount =  ExchItemCacher.getInstance().getTotal();
		int pa = Integer.parseInt(page);
		if(pa>pageCount) pa = pageCount;
		
		accessToken = accessToken == null ? "" : accessToken;
		List<ExchItem> ilist = ExchItemCacher.getInstance().getItemList(pa);
		 if(ilist == null || ilist.size() == 0){
			 ExchItemCacher.getInstance().reload();
			 ilist = ExchItemCacher.getInstance().getItemList(pa);
		 }
	      if(ilist == null || ilist.size() == 0){
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
				return;
			}else{
				StringBuffer re = new StringBuffer();
				re.append("<exchitems>");
				for(ExchItem i : ilist){
					re.append(i.toXML());
				}
				re.append("</exchitems>");
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, accessToken, pageCount, pa, re.toString()).toString());
			}       
	}

	public static String test(){
		List<ExchItem> ilist = ExchItemCacher.getInstance().getItemList(1);
				StringBuffer re = new StringBuffer();
				re.append("<exchitems>");
				for(ExchItem i : ilist){
					re.append(i.toXML());
				}
				re.append("</exchitems>");
				return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", re.toString()).toString();   
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
