package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.acapi.newuser.TurnplateManager;
import cn.youhui.bean.Ad;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.AdManager;
import cn.youhui.manager.AdNewManager;
import cn.youhui.ramdata.AdCacher;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.StringUtils;

/**
 * 获取首页广告
 * @author lijun
 * @since 2014-02-20
 */
@WebServlet("/tyh3/ad")
public class GetAd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String favAdTagId = "607";        //收藏夹广告tagid
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String uid =  ParamUtil.getParameter(request, "uid");
			String platform = ParamUtil.getParameter(request, "platform");
			String imei = ParamUtil.getParameter(request, "imei");
			
			if(platform ==null||platform.equals("")){
				platform = "android";
			}
//			List<Ad> list = AdNewManager.getInstance().getAds(platform);
			//从redis获取数据
			if(platform == null || "".equals(platform)){
				platform = "all";
			}
			List<Ad> list = AdCacher.getAdList(platform.toLowerCase());
			long t0827 = 1409068800000l;
			if(System.currentTimeMillis() < t0827 && !StringUtils.isEmpty(imei) && TurnplateManager.getInstance().isNogetHuafei(imei, uid)){
				Ad ad = AdNewManager.getInstance().getAd(platform,"11090");
				if(ad != null){
					ad.getAction().setUrl(Config.TURNPLATE2_HF_URL);
					list.add(ad);
				}
			}
			
//			List<Ad> list = AdCacher.getAdIds(platform);
			
			
//			if(list == null || list.size() == 0){
//				AdCacher.getInstance().reload();
//			List<Ad> list = AdNewManager.getInstance().getAds(platform);
//			}
			StringBuffer data = new StringBuffer();
//		if("50676962".equals(uid)){       //倩影同志的Uid的时候
//		}
			if(list != null && list.size() > 0){
				data.append("<ads>");
				for(Ad ad : list){
					System.out.println(ad.getId());
					data.append(ad.toXML());	
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
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED ,"",data.toString()).toString());
			}else{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.SERVER_ERROR,"").toString());
			}
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public static void main(String[] args) {
//		List<Ad> list = AdCacher.getAdList("all");
//		for(Ad ad : list){
//			System.out.println(ad.getId()+"  "+ad.getTitle());
//		}
		
	}

}
