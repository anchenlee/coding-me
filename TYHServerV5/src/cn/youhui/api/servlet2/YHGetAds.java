package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.youhui.bean.AdBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.AdManager;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh2/ads")
public class YHGetAds extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String favAdTagId = "607";        //收藏夹广告tagid

	public YHGetAds() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String access = request.getParameter("access_token");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String version = request.getParameter("version");
		String channel = request.getParameter("channel");
		String version_code = request.getParameter("version_code");
		if (access == null) {
			access = "";
		}
		uid = uid == null ? "" : uid;
		if(platform ==null||platform.equals("")){
			platform = "android";
		}
		String result = getXMLAds(access,platform,channel,version, uid, version_code);
		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static String getXMLAds(String access,String platform, String channel,String version, String uid, String version_code) {
		List<AdBean> list = AdManager.getInstance().getAds(platform, uid, version_code);
		StringBuffer data = new StringBuffer();
		String adIdIP = "9624";
		String adIdAnd = "9571";
		if("android".equalsIgnoreCase(platform) && isLowAd(platform, version)){
			list = AdManager.getInstance().getAd(adIdAnd, platform, uid, version_code);
		}else if("iphone".equalsIgnoreCase(platform)&& isLowAd(platform, version)){
			list = AdManager.getInstance().getAd(adIdIP, platform, uid, version_code);
		}
		if("50676962".equals(uid)){       //倩影同志的Uid的时候
			list = AdManager.getInstance().getAdsForQy(platform, uid, version_code);
		}
		if(list != null && list.size() > 0){
			data.append("<ads>");
			for(AdBean ad : list){
				if(isLowAd(platform, version) || (!adIdIP.equals(ad.getAd_id()) && !adIdAnd.equals(ad.getAd_id()))){ 
					data.append(ad.toXML());	
				}
			}
			data.append("</ads>");
			List<TeJiaGoodsBean> itemlist = Tag2ItemCacher.getInstance().getItemsByTagid(favAdTagId, 1, 10);
			if(itemlist != null && itemlist.size() > 0){
				data.append("<items>");
				for(TeJiaGoodsBean bean : itemlist){
					String outerCode = OuterCode.getOuterCode(uid, platform, "0", "7", "607");
					bean.setClickURL(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + bean.getItem_id());
					data.append(bean.toXML());
				}
				data.append("</items>");
			}
			return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,access,data.toString()).toString();
		}else{
			return RespStatusBuilder.message(ActionStatus.SERVER_ERROR,access).toString();
		}
	}
	
	/**
	 * 是否为不需要显示低版本广告的
	 */
	private static boolean isLowAd(String adId, String platform, String version){
		String adIdIP = "9624";
		String adIdAnd = "9571";
		
		try{
		if(adIdIP.equals(adId) && "iphone".equalsIgnoreCase(platform) && (max(version, "3.2.1"))){
			return true;
		}else if(adIdAnd.equals(adId) && "android".equalsIgnoreCase(platform) && ("33".equals(version) || max(version, "3.2.4"))){
			return true;
		}else {
			return false;
		}
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 是否为低版本
	 * @return
	 */
	private static boolean isLowAd(String platform, String version){
		
		try{
		if( "iphone".equalsIgnoreCase(platform) && (max(version, "3.2.2"))){
			return false;
		}else if("android".equalsIgnoreCase(platform) && (max(version, "3.2.4"))){
			return false;
		}else {
			return true;
		}
		}catch(Exception e){
			return true;
		}
	}
	
	private static boolean max(String version1, String version2){
		try{
			if(version1 == null || version2 == null){
				return false;
			}
		    version1 = version1.replaceAll("\\.", "");
		    version2 = version2.replaceAll("\\.", "");
			return Integer.parseInt(version1) >= Integer.parseInt(version2);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
