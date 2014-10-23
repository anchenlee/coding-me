package cn.youhui.api.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import cn.youhui.common.Enums;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TPool;

/**
 * Servlet implementation class GetFavoriteList
 * 
 * get users' favorite list by uid
 */
@WebServlet("/taobao/favlist")
public class TBGetFavoriteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TBGetFavoriteList.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TBGetFavoriteList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String reqType = request.getMethod();

		try {
			String uid = request.getParameter("uid");
			String nick = request.getParameter("nick");
			String type = request.getParameter("type");
			String access = request.getParameter("access_token");
			String pageStr = request.getParameter("page");
			int page = 1;
			nick = nick == null ? "" : nick;
			access = access == null ? "" : access;
			type = type == null ? "" : type;
			uid = uid == null ? "" : uid;
			pageStr = pageStr == null ? "1" : pageStr;

			if (reqType.equalsIgnoreCase("GET"))
				nick = new String(nick.getBytes("iso-8859-1"), "utf-8");
			try {
				page = Integer.parseInt(pageStr);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (uid.equals("") || access.equals("")) {
				response.getWriter().print(
						RespStatusBuilder
								.message(Enums.ActionStatus.PARAMAS_ERROR));
			} else {
//				String result = "";
//				if (type.equals("shop")) {
//					// 返回收藏的商铺
//					updateFavShop(Long.parseLong(uid), nick, access);
//					result = getXmlShopFormat(uid, nick, page, access);
//				} else {
//					// 返回收藏的商品
//					updateFavItem(Long.parseLong(uid), nick, access);
//					
//					result = getXmlItemFormat(uid, nick, page, access);
//				}
//				response.getWriter().print(result);
				
				response.getWriter().print(
						RespStatusBuilder
								.message(Enums.ActionStatus.NO_RESULT));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			response.getWriter().print(
					RespStatusBuilder.message(
							Enums.ActionStatus.SERVER_ERROR.inValue(),
							e.toString()));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

//	public String getXmlShopFormat(String uid, String nick, int page,
//			String access_token) throws Exception {
//		FavUser2ShopsCacher user2Shopcacher = new FavUser2ShopsCacher(
//				Long.parseLong(uid));
//		FavAllShopsCacher shopsCacher = FavAllShopsCacher.getInstance();
//
//		StringBuffer sb = new StringBuffer();
//		int countPage = user2Shopcacher.getPageCount();
//		
//		if (countPage != 0){
//			page = page > countPage ? countPage : page;
//		}
//		sb.append("<favlist>");
//		ArrayList<String> list = user2Shopcacher.getShops(page);
//		if (page!=1&&list != null && list.size() > 0) {
//			for (String itemId : list) {
//				sb.append("<fitem>");
//				ShopBean item = shopsCacher.getItem(itemId);
//				sb.append("<id>" + item.getShop_id() + "</id>");
//				sb.append("<icon><![CDATA[" + item.getPic_url() + "]]></icon>");
//				sb.append("<title><![CDATA[" + item.getTitle() + "]]></title>");
//				sb.append("<click><![CDATA[" + item.getClick_url()
//						+ "]]></click>");
//				sb.append("<commission rate=\"" + item.getCommission_rate()
//						+ "\"/>");
//				sb.append("</fitem>");
//			}
//		} else {
//			try {
//				int total = TaobaokeManager.getFavCount(nick, access_token,
//						"SHOP");
//				countPage = (int) (total % 20 == 0 ? total / 20
//						: total / 20 + 1);
//				ArrayList<ShopBean> shopList = TaobaokeManager.getFavShops(
//						nick, page, access_token);
//
//				for (ShopBean item : shopList) {
//					sb.append("<fitem>");
//					sb.append("<id>" + item.getShop_id() + "</id>");
//					sb.append("<icon><![CDATA[" + item.getPic_url()
//							+ "]]></icon>");
//					sb.append("<title><![CDATA[" + item.getTitle()
//							+ "]]></title>");
//					sb.append("<click><![CDATA[" + item.getClick_url()
//							+ "]]></click>");
//					sb.append("<commission rate=\"" + item.getCommission_rate()
//							+ "\"/>");
//					sb.append("</fitem>");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				log.error(e, e);
//				return RespStatusBuilder.message(
//						ActionStatus.SERVER_ERROR.inValue(), e.toString())
//						.toString();
//			}
//
//		}
//		sb.append("</favlist>");
//
//		return RespStatusBuilder.messageAccess(
//				Enums.ActionStatus.NORMAL_RETURNED.inValue(),
//				Enums.ActionStatus.NORMAL_RETURNED.getDescription(),
//				access_token, countPage, page, sb.toString()).toString();
//
//	}
//
//	public String getXmlItemFormat(String uid, String nick, int page,
//			String access_token) throws Exception {
//		FavUser2ItemsCacher user2ItemsCacher = new FavUser2ItemsCacher(
//				Long.parseLong(uid));
//		FavAllItemsCacher itemCacher = FavAllItemsCacher.getInstance();
//
//		StringBuffer sb = new StringBuffer();
//		sb.append("<favlist>");
//		int countPage = user2ItemsCacher.getPageCount();
//		if (countPage != 0){
//			page = page > countPage ? countPage : page;
//		}
//		ArrayList<String> list = user2ItemsCacher.getItems(page);
//		
//		if (page!=1&&list != null && list.size() > 0) {
//			for (String itemId : list) {
////				UMPItemBean bean = FavUMPCacher.getInstance().
////				getUMPInfo(Long.parseLong(itemId));
////				if(bean==null){
////					bean = new UMPItemBean();
////				}
//				sb.append("<fitem>");
//				ItemBean item = itemCacher.getItem(itemId);
//				sb.append("<id>" + item.getNum_iid() + "</id>");
//				sb.append("<icon><![CDATA[" + item.getPic_url() + "]]></icon>");
//				sb.append("<title><![CDATA[" + item.getTitle() + "]]></title>");
//				sb.append("<click><![CDATA[" + item.getClick_url()
//						+ "]]></click>");
//				sb.append("<price phi=\"" + item.getPrice_high() + "\" plow=\""
//						+ item.getPrice_low() + "\" off=\""
//						+ item.getDiscount() + "\"/>");
//				sb.append("<commission value=\"" + item.getCommission()
//						+ "\" rate=\"" + item.getCommission_rate() + "\"/>");
////				sb.append("<umpname>"+bean.getPromoName()+"</umpname>");
////				sb.append("<newprice>"+bean.getPromoPrice()+"</newprice>");
//				sb.append("</fitem>");
//			}
//		} else {
//			if(log.isInfoEnabled()){
//				log.info("in taobao api-----");
//			}
//			try {
//				int total = TaobaokeManager.getFavCount(nick, access_token,
//						"ITEM");
//				countPage = (int) (total % 20 == 0 ? total / 20
//						: total / 20 + 1);
//				ArrayList<ItemBean> itemList = TaobaokeManager.getFavItems(
//						nick, page, access_token);
//				for (ItemBean item : itemList) {
////					UMPItemBean bean = FavUMPCacher.getInstance().
////						getUMPInfo(Long.parseLong(item.getNum_iid()));
////					if(bean==null){
////						bean = new UMPItemBean();
////					}
//					sb.append("<fitem>");
//					sb.append("<id>" + item.getNum_iid() + "</id>");
//					sb.append("<icon><![CDATA[" + item.getPic_url()
//							+ "]]></icon>");
//					sb.append("<title><![CDATA[" + item.getTitle()
//							+ "]]></title>");
//					sb.append("<click><![CDATA[" + item.getClick_url()
//							+ "]]></click>");
//					sb.append("<price phi=\"" + item.getPrice_high()
//							+ "\" plow=\"" + item.getPrice_low() + "\" off=\""
//							+ item.getDiscount() + "\"/>");
//					sb.append("<commission value=\"" + item.getCommission()
//							+ "\" rate=\"" + item.getCommission_rate() + "\"/>");
////					sb.append("<umpname>"+bean.getPromoName()+"</umpname>");
////					sb.append("<newprice>"+bean.getPromoPrice()+"</newprice>");
//					sb.append("</fitem>");
//				}
//			} catch (Exception e) {
//				log.error(e, e);
//				e.printStackTrace();
//				return RespStatusBuilder.message(
//						ActionStatus.SERVER_ERROR.inValue(), e.toString())
//						.toString();
//			}
//		}
//		sb.append("</favlist>");
//
//		return RespStatusBuilder.messageAccess(
//				Enums.ActionStatus.NORMAL_RETURNED.inValue(),
//				Enums.ActionStatus.NORMAL_RETURNED.getDescription(),
//				access_token, countPage, page, sb.toString()).toString();
//
//	}
//
//	/**
//	 * 更新商品收藏夹
//	 * **/
//	public void updateFavItem(final long uid, final String nick,
//			final String access) {
//		final FavLockCacher lockCacher = FavLockCacher.getInstance();
//		final String key = X.CachePrefix.FAV_USER_2_ITEMS + X.DOT + uid;
//		boolean isRun = lockCacher.isRefrush(key);
//		Runnable run = new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//
//				lockCacher.runRefrush(key);
//				log.info("thread update fav item start");
//				UserFavListDetectModel model = new UserFavListDetectModel();
//				model.updateCacherItemList(uid, nick, access, "ITEM");
//				log.info("thread update fav item end");
//				lockCacher.destoryRefrush(key);
//
//			}
//		};
//		if (!isRun) {
//			TPool.getInstance().doit(run);
//		}
//	}
//
//	/**
//	 * 更新店铺收藏夹
//	 * **/
//	public void updateFavShop(final long uid, final String nick,
//			final String access) {
//		final FavLockCacher lockCacher = FavLockCacher.getInstance();
//		final String key = X.CachePrefix.FAV_USER_2_SHOPS + X.DOT + uid;
//		boolean isRun = lockCacher.isRefrush(key);
//
//		Runnable run = new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				lockCacher.runRefrush(key);
//				log.info("thread update fav shop start");
//				UserFavListDetectModel model = new UserFavListDetectModel();
//				model.updateCacherItemList(uid, nick, access, "SHOP");
//				log.info("thread update fav shop end");
//				lockCacher.destoryRefrush(key);
//
//			}
//		};
//		if (!isRun) {
//			TPool.getInstance().doit(run);
//		}
//	}

}
