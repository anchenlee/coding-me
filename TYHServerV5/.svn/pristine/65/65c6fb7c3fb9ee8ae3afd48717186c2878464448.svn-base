package cn.youhui.api.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.youhui.bean.AdBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.AdManager;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh/ads")
public class YHGetAds extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(YHGetAds.class);
	
	private static final String apptagstyle = "tagStyleAppPage";
	
	public YHGetAds() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String access = request.getParameter("access_token");
		String platform = request.getParameter("platform");
		String version_code = request.getParameter("version_code");
		if (access == null) {
			access = "";
		}
		uid = uid == null ? "" : uid;
		if(platform ==null||platform.equals("")){
			platform = "android";
		}
		String result = getXMLAds(access,platform, uid, version_code);
		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String getXMLAds(String access,String platform, String uid, String version_code) {
		List<AdBean> list = null;
		StringBuffer data = new StringBuffer();
		String adIdIP = "9624";
		String adIdAnd = "9571";
		if("android".equalsIgnoreCase(platform)){
			list = AdManager.getInstance().getAd(adIdAnd, platform, uid, version_code);
		}else{
			list = AdManager.getInstance().getAd(adIdIP, platform, uid, version_code);
		}
		if(list != null && list.size() > 0){
			data.append("<ads>");
			for(AdBean ad : list){
				data.append(ad.toXML());
			}
			data.append("</ads>");
			return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,access,data.toString()).toString();
		}else{
			return RespStatusBuilder.message(ActionStatus.SERVER_ERROR,access).toString();
		}
	}
	
	private static boolean isIpadAd(String adId, String platform){
		if("ipad".equalsIgnoreCase(platform)){
			if("1381739655".equals(adId)){
				return true;				
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	
	private static boolean isZHWNLAd(String adId, String platform){
		String adIdIP = "1378723634";  //1378717248
		String adIdAnd = "1378723611";  //1378717107
		if(adIdIP.equals(adId) && "iphone".equalsIgnoreCase(platform)){
			return true;
		}else if(adIdAnd.equals(adId) && "android".equalsIgnoreCase(platform)){
			return true;
		}else {
			return false;
		}
	}
	
	private String getXMLAdsold(String access,String platform) {
		StringBuffer data = new StringBuffer();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			long now = System.currentTimeMillis();
			String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE (`platform`='"+platform+"' or `platform` = 'all') and `start_time` < '"+now+"' AND `end_time` > '"+now+"';";
			ResultSet rs = s.executeQuery(sql);
			data.append("<ads>");
			while(rs.next()){
				data.append("<ad>");
				data.append("<id>"+rs.getString("id")+"</id>");
				data.append("<type>"+rs.getString("ad_type")+"</type>");
				data.append("<content><![CDATA["+rs.getString("ad_content")+"]]></content>");
				data.append("</ad>");
			}
			data.append("</ads>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,access,data.toString()).toString();
	}
	
	/**
	 * 老版本，删除action为系统页面的
	 */
	private void clear(List<AdBean> list){
		if(list != null && list.size() > 0){
			for(int i = 0; i<list.size(); i++){
				AdBean ad = list.get(i);
				if(apptagstyle.equalsIgnoreCase(ad.getAction().getActionType())){
					list.remove(i);
				}
			}
		}
	}

}
