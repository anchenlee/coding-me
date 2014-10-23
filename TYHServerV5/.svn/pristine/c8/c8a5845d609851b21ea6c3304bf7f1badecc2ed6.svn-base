package cn.youhui.api.servlet3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import sun.misc.BASE64Encoder;
import cn.youhui.acapi.newuser.TurnplateManager;
import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.AdminTurnplatAction;
import cn.youhui.dao.AdminUmengDeviceStatusDao;
import cn.youhui.manager.FavBrandDAO;
import cn.youhui.ramdata.AppConfigCacher;
import cn.youhui.ramdata.IphoneDevTokenCacher;
import cn.youhui.tuiguang.ParamBean;
import cn.youhui.tuiguang.TuiGuangThread;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;
import cn.youhui.utils.TPool;
import cn.youhui.utils.VersionUtil;
import cn.youhui.utils.WebUtil;

/**
 * 提供app参数
 * @author lijun
 * @since 2014-4-21
 */
@WebServlet("/tyh3/appconfig")
public class GetAppconfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String platform = ParamUtil.getParameter(request, "platform");
			String uid = ParamUtil.getParameter(request, "uid");
			String imei = ParamUtil.getParameter(request, "imei");
			String devtoken = ParamUtil.getParameter(request, "devtoken");
			String version = ParamUtil.getParameter(request, "version");
			String result = "";
			
			ParamBean bean = new ParamBean();
			bean.setActivateip(WebUtil.getIpAddr(request));
			bean.setIdfa(ParamUtil.getParameter(request, "idfa"));
			bean.setCode(ParamUtil.getParameter(request, "version"));
			bean.setOpenudid(ParamUtil.getParameter(request, "openudid"));
			TPool.getInstance().execute(new TuiGuangThread(bean,System.currentTimeMillis(), uid,1));
			
			if("iphone".equalsIgnoreCase(platform) || "ipad".equalsIgnoreCase(platform)){
				if(devtoken != null && !"".equals(devtoken)){
					IphoneDevTokenCacher.getInstance().addDevToken(devtoken);
					if(uid != null && !"".equals(uid)){
						IphoneDevTokenCacher.getInstance().add(uid, devtoken);
					}
				}
			}
			String appconfig = AppConfigCacher.getInstance().getData();
			if(appconfig == null){
				AppConfigCacher.getInstance().reload();
				appconfig = AppConfigCacher.getInstance().getData();
			}
			
			JSONObject configJSO = new JSONObject("{" + appconfig + "}");
			JSONObject appcon = configJSO.getJSONObject("appconfig");
			JSONArray announcementArray = null;
			JSONArray specialUrlArray = null;
			JSONArray loadingAdArray = null;
			
			String anncouncement = null;
			String loadingAd = AppConfigCacher.getInstance().getLoadingAdData();
			String specialUrl = AppConfigCacher.getInstance().getSpecialUrlData();
			
			if (VersionUtil.isHigher("4.0.5", version)) {
				long t818 = 1408291200000l;
				boolean canResponseData = AdminTurnplatAction.isHasGet(imei,t818);
//					boolean isNoGetHuafei = TurnplateManager.getInstance().isNogetHuafei(imei, uid);
				if (!canResponseData ) {   //|| isNoGetHuafei
					anncouncement = AppConfigCacher.getInstance().getAnnouncementData();
				}
			}
			if (anncouncement != null) {
				announcementArray = new JSONArray(anncouncement);
				appcon.put("announcement", announcementArray);
			}else {
				appcon.put("announcement", new JSONArray());
			}
			if (loadingAd != null) {
				loadingAdArray = new JSONArray(loadingAd);
				appcon.put("loading_ad", loadingAdArray);
			}else {
				appcon.put("loading_ad", new JSONArray());
			}
			if (specialUrl != null) {
				specialUrlArray = new JSONArray(specialUrl);
				appcon.put("announcement_for_specialurl", specialUrlArray);
			}else {
				appcon.put("announcement_for_specialurl", new JSONArray());
			}
			appconfig = configJSO.toString().substring(1,configJSO.toString().length()-1);
			
			boolean hasBrandNew = FavBrandDAO.getInstance().hasNewBrand(uid);
			result = "{" + appconfig + ",\"has_brand_new\":" + (hasBrandNew ? 1:0) +"}";
			BASE64Encoder base = new BASE64Encoder();
			result = base.encode(result.getBytes("UTF-8"));
			result = "\"" + result + "\""; 
			
			if(!StringUtils.isEmpty(result)){
				response.getWriter().println(RespStatusBuilder.messageJson(Enums.ActionStatus.NORMAL_RETURNED, "", result));	
		   	}else{
				response.getWriter().println(RespStatusBuilder.messageJson(Enums.ActionStatus.SERVER_ERROR, ""));
			}
		} catch (Exception e){
			e.printStackTrace();
			response.getWriter().println(RespStatusBuilder.messageJson(ActionStatus.PARAMAS_ERROR, e.toString()));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
