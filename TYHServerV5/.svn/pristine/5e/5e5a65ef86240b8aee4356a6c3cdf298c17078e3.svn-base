package cn.youhui.api.servlet2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.ActivityMingxi;
import cn.youhui.bean.JiFenBaoTrade;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.ActivityMingxiManager;
import cn.youhui.manager.JiFenBaoTradeManager;
import cn.youhui.manager.UserManager;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

/**
 * 提取集分宝     停用   换到和提现现金一起了
 * @author leejun
 * @since 2013-05-21
 */
@WebServlet("/tyh2/bringjfb")
public class YHBringJFB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	private static String memo = "随手优惠送集分宝";
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String data = request.getParameter("data");
		if(data == null || "".equals(data)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		data = data.replaceAll(" ", "+");
		data = Encrypt.decode(data);
		String param[] = data.split("#");
		if(param.length !=8){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		String uid = param[1];
		String taobaoNick = param[2];
		String zfb = param[3];
		String jfbNum = param[4];
//		String uid = request.getParameter("uid");
//		String taobaoNick = request.getParameter("taobao_nick");
//		String zfb = request.getParameter("zfb");
//		String jfbNum = request.getParameter("jfb_num");
		if(StringUtils.isEmpty(uid) ||StringUtils.isEmpty(taobaoNick) ||StringUtils.isEmpty(zfb) ||StringUtils.isNumeric(jfbNum) || !UserManager.getInstance().isUserExist(uid, taobaoNick)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		int jfbint = Integer.parseInt(jfbNum);
		if(jfbint <= 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		if(jfbint > ActivityMingxiManager.getInstance().getYue(uid)){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.JFB_YUE_NOT_ENOUGH).toString());
			return;
		}
		String tradeId = getTradeId(uid);
		JiFenBaoTrade trade = new JiFenBaoTrade();
		trade.setJiFenBaoNum(jfbint);
		trade.setMemo(memo);
		trade.setStatus(JiFenBaoTradeManager.Status_Init);
		trade.setTradeId(tradeId);
		trade.setUid(uid);
		trade.setZfb(zfb);
		boolean flag = false;
//		JiFenBaoTradeManager.getInstance().add(trade);
		if(flag){
			ActivityMingxi bean = new ActivityMingxi();
			bean.setEvent("tixian");
			bean.setJfbNum(-jfbint);
			bean.setUid(uid);
			bean.setTradeId(tradeId);
			bean.setStatus(ActivityMingxiManager.Status_Disposeing);
			ActivityMingxiManager.getInstance().add(bean);
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.BRING_JFB_FAIL).toString());
		}
	}
	
	private String getTradeId(String uid){
		String today = df.format(new Date());
		int i = 10 - uid.length();
		String uuid = uid;
		if(i >= 0){
			for(;i>0;i--){
				uuid = "0"+uuid;
			}
		}else{
			uuid = uuid.substring(-i, uuid.length());
		}
		return today + uuid;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
