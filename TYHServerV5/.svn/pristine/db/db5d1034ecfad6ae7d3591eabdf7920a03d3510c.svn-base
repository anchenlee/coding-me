package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.TaobaoManager;
import cn.youhui.manager.TeJiaGoodsManager;
import cn.youhui.manager.ViewItemManager;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.ramdata.ViewItemCacher;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh2/addview")
public class YHAddViewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHAddViewItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String itemId = request.getParameter("itemid");
		boolean ret = ViewItemCacher.getInstance().addViewItem(uid, itemId);
		boolean ret1 = false;
		if(ret)
			ret1 = ViewItemManager.getInstance().addViewItem(uid, itemId);
		if(itemId != null && !"".equals(itemId)){
			boolean isexist = TeJiaGoodsManager.getInstance().findItem(itemId);
			if(!isexist){
				TeJiaGoodsBean bean = TaobaoManager.getItem(itemId);
				if(bean != null){
					TeJiaGoodsManager.getInstance().addItem(bean);
					TagItemCacher.getInstance().addProduct(bean);
				}
			}
		}
		if(ret1 == false){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
