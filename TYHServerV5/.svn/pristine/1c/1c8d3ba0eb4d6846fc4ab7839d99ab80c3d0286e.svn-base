package cn.youhui.acapi.newuser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.util.oufeicz.OfPayUtil;
import cn.youhui.util.oufeicz.PhoneNumAttributionUtil;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;

/**
 * 转盘活动领话费
 * @author lijun
 * @since 2014-7-31
 */
@WebServlet("/turnplate/huafei")
public class GetHuafeiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		TurnplateResult tr = new TurnplateResult();
		try{
			String phoneNum = ParamUtil.getParameter(request, "phone_num", true);
			String uid = ParamUtil.getParameter(request, "uid", true);
			String imei = ParamUtil.getParameter(request, "imei", true);
			String imsi = ParamUtil.getParameter(request, "imsi");
			String ret = "0";
			
			String attr = PhoneNumAttributionUtil.getPhoneNumAttribution(phoneNum);
			if(attr != null && attr.indexOf("广东") > -1 && attr.indexOf("联通") > -1){      //检测是否为广东联通
				response.getWriter().print("10");
				return;
			}
			
			if(TurnplateManager.getInstance().isLuck5(imei, uid)){
				//检查请该imei和imsi是否被已被使用过
				boolean flag = HuafeiManager.getInstance().checkIfVal(imei,imsi);
				if (flag) {
					ret = "4"; //此手机已参与过活动
				}else{
					boolean f = HuafeiManager.getInstance().checkIfValPhoneNum(phoneNum); //检查手机号是否已被充值过
					if (f) {
						ret = "2"; //此手机号码已参与过活动
					}
					if (!f && !flag) {
						//获取手机号归属地
						//新增待充值记录
						boolean addFlag = HuafeiManager.getInstance().addRecord(imei, imsi, uid, phoneNum,attr);
						if(addFlag && OfPayUtil.huafeiCZ(phoneNum, 10)){
							ret = "1"; //充值成功
						}else{
							ret = "3";//充值失败
						}
					}			
				}
			}else{
			}
			response.getWriter().print(ret);
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(tr.toJson());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
