package cn.youhui.api.servlet3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.jfbad.JFBAdManager;
import cn.youhui.jfbad.JFbAdCallBackResponse;
import cn.youhui.tuiguang.ParamBean;
import cn.youhui.tuiguang.TuiGuangThread;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TPool;
import cn.youhui.utils.WebUtil;

/**
 * 集分宝广告加载完成后回调
 * @author lijun
 * @since 2014-02-25
 */
@WebServlet("/tyh3/jfbcallback")
public class JFBAdCallBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String data = ParamUtil.getParameter(request, "data", true);
			
			JFbAdCallBackResponse rsp =  JFBAdManager.getInstance().callBack(data);
			if(rsp.isSuccess()){
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", rsp.toXml()).toString());
				if(rsp.isSignAd()){
					String uid = ParamUtil.getParameter(request, "uid");
					ParamBean bean = new ParamBean();
					bean.setActivateip(WebUtil.getIpAddr(request));
					bean.setIdfa(ParamUtil.getParameter(request, "idfa"));
					bean.setCode(ParamUtil.getParameter(request, "version"));
					bean.setOpenudid(ParamUtil.getParameter(request, "openudid"));
					TPool.getInstance().execute(new TuiGuangThread(bean,System.currentTimeMillis(), uid,3));
				}
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
			}
			
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
