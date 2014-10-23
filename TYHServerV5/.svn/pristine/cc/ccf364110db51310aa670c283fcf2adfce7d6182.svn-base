package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ItemMiddle;
import cn.youhui.bean.ItemView;
import cn.youhui.cacher.dao.Switch4JfbCacher;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.LikeItemManager;
import cn.youhui.manager.SaveItemViewThread;
import cn.youhui.manager.TaobaoManager;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.JfbRateUtil;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TPool;

/**
 * 商品中间页面的各种信息
 * @author lijun
 * @since 2013-12-09
 */
@WebServlet("/tyh2/getitemmid")
public class YHGetItemMiddle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String uid = ParamUtil.getParameter(request, "uid");
			String itemId = ParamUtil.getParameter(request, "itemid", true);
			String fromChannel = ParamUtil.getParameter(request, "from_channel", true);
			String fromValue = ParamUtil.getParameter(request, "from_value", true);
			String imei = ParamUtil.getParameter(request, "imei");
			ItemMiddle im = new ItemMiddle();
			im.setItemId(itemId);
			im.setIsLikeItem(LikeItemManager.getInstance().isLikeItem(uid, itemId) ? 1 : 0);
			double rate = 0;
			long t1 = System.currentTimeMillis();
			if(TaobaoManager.isSupportFanli(itemId)){
				long t2 = System.currentTimeMillis();
//				System.out.println("support take..." + (t2 - t1));
				rate = JfbRateUtil.getRate(itemId);
				long t3 = System.currentTimeMillis();
//				System.out.println("rate take..." + (t3 - t2));
				im.setJfbRate(rate);
			}
			long t4 = System.currentTimeMillis();
			TPool.getInstance().doit(new SaveItemViewThread(new ItemView(uid, imei, itemId, fromChannel, fromValue, rate)));
			long t5 = System.currentTimeMillis();
//			System.out.println("addto take..." + (t5 - t4));
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, im.toXML()).toString());
		}catch(BadParameterException e){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
