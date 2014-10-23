package cn.youhui.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.cacher.dao.SuperDaysCacher;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.manager.FrameTitleManager;
import cn.youhui.manager.GiftManager;
import cn.youhui.manager.JUhuasuan_newGetTB;
import cn.youhui.manager.JuHuaSuanManager;
import cn.youhui.manager.JuhuasuanBrandManager;
import cn.youhui.manager.JuhuasuanNewManager;
import cn.youhui.manager.TianTianDazhe;
import cn.youhui.manager.TianTianTeJiaManager;
import cn.youhui.manager.UserAccountManager;
import cn.youhui.ramdata.ActivityCacher;
import cn.youhui.ramdata.AdCacher;
import cn.youhui.ramdata.AllIpadTagCacher;
import cn.youhui.ramdata.AllTagCacher;
import cn.youhui.ramdata.AppConfigCacher;
import cn.youhui.ramdata.ClientMenuCacher;
import cn.youhui.ramdata.ExchItemCacher;
import cn.youhui.ramdata.FrameTitleCache;
import cn.youhui.ramdata.LikeItemCacher;
import cn.youhui.ramdata.ProfRecomCacher;
import cn.youhui.ramdata.RecomCacher;
import cn.youhui.ramdata.SaleCache;
import cn.youhui.ramdata.SaleDateCache;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.Tag2TagCacher;
import cn.youhui.ramdata.Tag4UserCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.TagInIpadCacher;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.ramdata.TagStatsCacher;
import cn.youhui.ramdata.TagStyleItemCacher;
import cn.youhui.ramdata.Tagid2TagnameCacher;
import cn.youhui.ramdata.UserJFBAccountCacher;
import cn.youhui.ramdata.UserTagCacher;
import cn.youhui.ramdata.UserTagInIpadCacher;
import cn.youhuiWeb.ramdata.ADCacher;
import cn.youhuiWeb.ramdata.CatagoryADCacher;
import cn.youhuiWeb.ramdata.HotRecommendCacher;
import cn.youhuiWeb.ramdata.HotSearchCacher;

@WebServlet("/CacheServlet")
public class CacheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CacheServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if (type == null || type.equals("")) {
			response.getWriter().println("parameter error.");
			return;
		}
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			if(type.equalsIgnoreCase("tags")) {
				TagCacher.getInstance().reload();
				Tag4UserCacher.getInstance().reload();
				AllTagCacher.getInstance().reload();
				Tagid2TagnameCacher.getInstance().reload();
//				JuHuaSuanManager.updateBrand();
			}else if(type.equalsIgnoreCase("ipadtags")){
				TagInIpadCacher.getInstance().reload();
				AllIpadTagCacher.getInstance().reload();
			} else if (type.equalsIgnoreCase("tags4sex")){
				Tag4UserCacher.getInstance().reload();
			} else if (type.equalsIgnoreCase("tag4stats")){
				TagStatsCacher.getInstance().reload();
			} else if(type.equals("tagitem")){
				TagItemCacher.getInstance().reload();
//				Tag4ItemCacher.getInstance().reload();Tag2ItemCacher.getInstance().reload();
			}else if(type.equals("exchitem")){
				ExchItemCacher.getInstance().reload();
			}else if(type.equals("styletag4item")){
//				TagStyleItemCacher.getInstance().reload();
			}else if(type.equals("usertag")){
				UserTagCacher.getInstance().reload();
			}else if(type.equals("usertagipad")){
				UserTagInIpadCacher.getInstance().reload();
			}else if(type.equals("tag2item")){
				Tag2ItemCacher.getInstance().reload();
			}else if(type.equals("sale")){
				SaleCache.getInstance().reload();
				SaleDateCache.getInstance().reload();
			}else if(type.equals("recom")){
				RecomCacher.getInstance().reload();
			}else if(type.equals("recom_refresh")){
				RecomCacher.getInstance().refresh();
			}else if(type.equals("ppt")){
				JuhuasuanBrandManager.updateBrand();
//				JuHuaSuanManager.updateBrand();
			}else if(type.equals("gift")){
				GiftManager.getInstance().reload();
			}else if(type.equals("ad")){
				AdCacher.getInstance().reload();
			}else if(type.equals("framtitle")){
				FrameTitleCache.reload();
			}else if(type.equals("tttj")){
				TianTianTeJiaManager.updateTejia();
//				TianTianDazhe.initFromWebsite();
			}else if(type.equals("jhs")){
				JuhuasuanNewManager.updateJuhuasuan();
//				JUhuasuan_newGetTB.getJuhuasuanBeanFromNet();
			}else if(type.equals("activity")){
				ActivityCacher.getInstance().reload();
			}else if(type.startsWith("jfbaccount_")){
				String []ts =type.split("_");
				int uidpre = 0;
				try {
					uidpre = Integer.parseInt(ts[1]);
				} catch (Exception e) {
					// TODO: handle exception
					uidpre = 0;
				}
				UserJFBAccountCacher.getInstance().reload(uidpre);
			}else if(type.startsWith("jfbaccountresult")){
				response.getWriter().println(UserJFBAccountCacher.getInstance().result());
			}else if(type.equals("jfbaccount")){
				UserJFBAccountCacher.getInstance().reload();
			}else if(type.equals("precom")){
				ProfRecomCacher.getInstance().reload();
			}else if(type.equals("useraccount")){
				UserAccountManager.getInstance().updateUserAccount();
			}else if(type.equals("useraccountcheck")){
				UserAccountManager.getInstance().check();
			}else if(type.equals("webad")){
				ADCacher.getInstance().reload();
			}else if(type.equals("webcatad")){
				CatagoryADCacher.getInstance().reloadAll();
			}else if(type.equals("webhotsearch")){
				HotSearchCacher.getInstance().reloadAll();
			}else if(type.equals("webhotrecommend")){
				HotRecommendCacher.getInstance().reload();
			}else if(type.equals("reloadall")){
				TagCacher.getInstance().reload();			
				Tag4UserCacher.getInstance().reload();
				
				TagStatsCacher.getInstance().reload();
				
				TagItemCacher.getInstance().reload();

//				Tag4ItemCacher.getInstance().reload();
				
				GiftManager.getInstance().reload();
				// JFB_Act_Manager.getInstance().reload();
				
				Tag2TagCacher.getInstance().reload();
				Tag2ItemCacher.getInstance().reload();
				Tagid2TagnameCacher.getInstance().reload();
				
				AllTagCacher.getInstance().reload();
				
				JuHuaSuanManager.updateBrand();
				
				UserTagCacher.getInstance().reload();
				UserTagInIpadCacher.getInstance().reload();
				
//				TagStyleItemCacher.getInstance().reload();

//				Tag4StyleItemCacher.getInstance().reload();
				
				AppConfigCacher.getInstance().reload();
				ClientMenuCacher.getInstance().reload();
				
				TagInIpadCacher.getInstance().reload();
				AllIpadTagCacher.getInstance().reload();
				
				LikeItemCacher.getInstance().reload();
				
				SaleCache.getInstance().reload();
				SaleDateCache.getInstance().reload();
				RecomCacher.getInstance().reload();
				
				ActivityCacher.getInstance().reload();
				
				//for youhui web				
				ADCacher.getInstance().reload();
				CatagoryADCacher.getInstance().reloadAll();
				HotSearchCacher.getInstance().reloadAll();
				HotRecommendCacher.getInstance().reload();
			}else if(type.equals("superdays")){
				SuperDaysCacher.getInstance().reload();
			}else if(type.equals("appconfig")){
				AppConfigCacher.getInstance().reload();
			}
			
			response.getWriter().println(type + " cache was reload done.");
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			e.printStackTrace();
			JedisDBIns.getInstance().release(jedis);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
