package cn.youhui.api.huafei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.api.activity.ActivityClient;
import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.bean.UserAccount;
import cn.youhui.manager.UserAccountManager;
import cn.youhui.manager.UserManager;
import cn.youhui.util.oufeicz.OfPayUtil;
import cn.youhui.util.oufeicz.TelephoneCZValidationUtil;
import cn.youhui.utils.ParamUtil;

/**
 * 验证密码，充值
 * @author lijun
 * @since 2014-8-30
 */
@WebServlet("/checkcz")
public class CheckCZServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TIME_OUT = 10*60*1000;
	private static final String HF_AC_KEY = "e14zm7u9";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ret = 0;
		try{
			String password = ParamUtil.getParameter(request, "password", true);
			String tradeId = ParamUtil.getParameter(request, "tid", true);
			HuafeiCZ hf = HuafeiCZDAO.getInstance().getHuafeiCZbyTradeId(tradeId);
			long now = System.currentTimeMillis();
			if(now - hf.getCreateTime() > TIME_OUT){
				ret = 5;   //超时
			}else{
				double yue = TelephoneCZValidationUtil.countMoneyQuery();
				boolean hasGoods = TelephoneCZValidationUtil.hasGoods(hf.getPhoneNum(), hf.getCzNum());  //是否有库存
				if(yue < 100 || !hasGoods){
					ret = 6;
				}else{
					int userYue = 0;
					UserAccount ua = UserAccountManager.getInstance().getUserAccount(hf.getUid());
					if(ua != null){
						userYue = ua.getYuE();
					}
					if(userYue < ExchangeRule.getNeedJfbNum(hf.getCzNum())){
						ret = 3; 
					}else{
						if(UserManager.getInstance().checkUidPassword(hf.getUid(), password)){
							String[] re = HuafeiCZManager.buyandpay(hf.getUid(), hf.getCzNum());
							if("1".equals(re[0])){
								if(OfPayUtil.huafeiCZ(hf.getPhoneNum(), hf.getCzNum(), "1", re[1])){
									ActivityRequest acrequest = new ActivityRequest(hf.getUid(), HF_AC_KEY, hf.getUid() + System.currentTimeMillis(), hf.getCzNum(), 0);
									ActivityClient.execut(acrequest);
									ret = 1;    //成功
								}else{
									ret = 4;
								}
							}else if("2".equals(re[0])){
								ret = 3;    //余额不足
							}
						}else{
							ret = 2;  // 密码错误
						}
					}
				}
			}
			if(ret == 1){
				HuafeiCZDAO.getInstance().setSuccess(tradeId);
			}else if(ret != 2){
				HuafeiCZDAO.getInstance().setFail(tradeId, "" + ret);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		response.getWriter().print(ret);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	
	public static void main(String[] args) {
		System.out.println("104845595"+System.currentTimeMillis());
	}
}
