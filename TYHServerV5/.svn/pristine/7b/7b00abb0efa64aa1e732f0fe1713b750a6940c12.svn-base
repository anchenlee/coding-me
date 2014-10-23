package cn.youhui.api.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.youhui.common.Enums;
import cn.youhui.model.storage.TBFavListSyncModel;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/taobao/commitfav")
public class TBCommitFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String uidStr = request.getParameter("uid");
			String favList = request.getParameter("favs");
			String type = request.getParameter("type");
			if (type == null) { 
				type = "item";
			}
			if (uidStr == null || uidStr.isEmpty() || favList == null || favList.isEmpty()) {
				// 返回错误
				response.getWriter().print(
						RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			} else {

				// 判断是否有数据 有就更新到缓存
				try {
					if (type.equals("item")) {
//						boolean isExist = TBFavListSyncModel.getInstance()
//								.isUidExistItem(uidStr);
//						if (!isExist) {
//							updateUserItem(Long.parseLong(uidStr),favList);
//						}
						
						JSONArray array = new JSONArray(favList);
						List<String> numIids = new ArrayList<String>();
						String itemids = "";
						for (int i = 0; i < array.length(); i++) {
							String numiid = array.getString(i);
							if(numiid != null && !"".equals(numiid)){
								numIids.add(numiid);
								itemids += numiid + ",";
							}
						}
						TBFavListSyncModel.getInstance().adduid2Items(uidStr, numIids);
						String content = new StringBuffer().append("uid=" + uidStr).append("&itemids=" + itemids).append("&type=3").toString();
//						NetManager.getInstance().send(Config.ItemRecommend_AddItems, content);      //添加用户收藏商品到商品推荐系统
						response.getWriter() .print(RespStatusBuilder.message(Enums.ActionStatus.NORMAL_RETURNED));

					} else if (type.equals("shop")) {
//						boolean isExist = TBFavListSyncModel.getInstance()
//								.isUidExistShop(uidStr);
//						if(!isExist){
//							updateUserShop(Long.parseLong(uidStr),favList);
//						}
						JSONArray array = new JSONArray(favList);
						List<String> shopids = new ArrayList<String>();
						for (int i = 0; i < array.length(); i++) {
							JSONObject obj = array.getJSONObject(i);
							String shopid = obj.getString("id");
							if(shopid != null && !"".equals(shopid))
							  shopids.add(shopid);
						}
						TBFavListSyncModel.getInstance().adduid2Shop(uidStr, shopids);
						response.getWriter().print(RespStatusBuilder .message(Enums.ActionStatus.NORMAL_RETURNED));
					}

				} catch (Exception e) {
//					e.printStackTrace();
					response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(
					RespStatusBuilder.message(Enums.ActionStatus.SERVER_ERROR));
		}
	}

/*	public void updateToCacher(String uidStr, String type, String favList) {
		try {
			long uid = Long.parseLong(uidStr);

			if (type.equals("item")) {

				FavUser2ItemsCacher cacher = new FavUser2ItemsCacher(uid);
				FavItems4UsersCacher i4uCacher = FavItems4UsersCacher
						.getInstance();

				if (!cacher.isExist()) {
					JSONArray array = new JSONArray(favList);
					long[] a = new long[array.length()];

					for (int i = 0; i < array.length(); i++) {
						long num_iid = Long.parseLong(array.getString(i));
						a[i] = num_iid;

					}

					updateFavItem(uid, a, i4uCacher);

					cacher.addItemId(a);

				}
				// response.getWriter()
				// .print(RespStatusBuilder
				// .message(Enums.ActionStatus.NORMAL_RETURNED));

			} else if (type.equals("shop")) {

				FavUser2ShopsCacher cacher = new FavUser2ShopsCacher(uid);

				if (!cacher.isExite()) {
					// favList = new String(favList.getBytes("iso-8859-1"),
					// "utf-8");
					JSONArray array = new JSONArray(favList);

					List<FavShopBean> list = new ArrayList<FavShopBean>();
					long[] a = new long[array.length()];
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						FavShopBean bean = new FavShopBean();
						bean.num_iid = obj.getLong("id");
						bean.title = obj.getString("title");

						a[i] = bean.num_iid;
						list.add(bean);
					}
					updateFavShop(uid, list);
					cacher.addItemId(a);

				}
				// response.getWriter()
				// .print(RespStatusBuilder
				// .message(Enums.ActionStatus.NORMAL_RETURNED));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*// 更新收藏商品信息
	public void updateFavItem(final long uid, final long[] a,
			final FavItems4UsersCacher i4uCacher) {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FavAllItemsCacher allItemCacher = FavAllItemsCacher
						.getInstance();
				for (long num_iid : a) {
					boolean flag = allItemCacher.isExist(num_iid + "");
					if (!flag) {
						boolean isNull = getgoodsInfo(num_iid);
						if (!isNull) {
							i4uCacher.addUid2Item(Long.toString(uid),
									Long.toString(num_iid));
						}
					} else {
						i4uCacher.addUid2Item(Long.toString(uid),
								Long.toString(num_iid));
					}
				}

				// 更新数据到数据库
				updateUserItem(uid);
			}
		};
		TPool.getInstance().doit(run);
	}*/

	/*// 更新收藏商店信息
	public void updateFavShop(final long uid, final List<FavShopBean> a) {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				FavAllShopsCacher allShopCacher = FavAllShopsCacher
						.getInstance();

				for (FavShopBean fbean : a) {

					long num_iid = fbean.num_iid;
					String title = fbean.title;
					boolean flag = allShopCacher.isExist(num_iid + "");

					if (!flag) {
						ShopBean bean = new ShopBean();
						bean.setShop_id(num_iid + "");
						bean.setTitle(title);

						TaobaokeShop shop = TaobaokeManager.getBatchTBKShops(
								num_iid + "").get(num_iid + "");

						if (shop != null) {
							bean.setClick_url(shop.getClickUrl());
							bean.setCommission_rate(shop.getCommissionRate());
							bean.setTitle(shop.getShopTitle());
						} else {
							bean.setClick_url("http://shop.m.taobao.com/shop/shop_index.htm?shop_id="
									+ num_iid);

						}
						allShopCacher.addItem(bean);
					}

				}
				// 更新数据到数据库
				updateUserShop(uid);
			}
		};
		TPool.getInstance().doit(run);
	}*/

	
//	public boolean getgoodsInfo(long num_iid) {
//		boolean flag = true;// true 代表是
//		try {
//
//			TaobaoClient client = new DefaultTaobaoClient(Config.Taobao_url,
//					Config.Fanli_appkey, Config.Fanli_secret);
//			ItemGetRequest req = new ItemGetRequest();
//			req.setFields("detail_url,num_iid,title,nick,type,cid,seller_cids,props,input_pids,input_str,desc,pic_url,num,valid_thru,list_time,delist_time,stuff_status,location,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,has_invoice,has_warranty,has_showcase,modified,increment,approve_status,postage_id,product_id,auction_point,property_alias,item_img,prop_img,sku,video,outer_id,is_virtual");
//			req.setNumIid(num_iid);
//			ItemGetResponse response = client.execute(req);
//			Item item = response.getItem();
//			if (item != null) {
//				ItemBean bean = new ItemBean();
//				bean.setNum_iid(item.getNumIid() + "");
//				bean.setTitle(item.getTitle());
//				bean.setPic_url(item.getPicUrl());
//				bean.setPrice(item.getPrice());
//				bean.setClick_url(item.getDetailUrl());
//				bean.setPrice_high(item.getPrice());
//				bean.setPrice_low(item.getPrice());
//				bean.setDiscount("0");
//
//				TaobaokeItem titem = TaobaokeManager.getBatchTBKConvert(
//						item.getNumIid() + "").get(item.getNumIid() + "");
//				if (titem != null) {
//					bean.setCommission(titem.getCommission());
//					bean.setCommission_rate(titem.getCommissionRate());
//					bean.setCommission_num(titem.getCommissionNum());
//					bean.setCommission_volume(titem.getCommissionVolume());
//					bean.setClick_url(titem.getClickUrl());
//				}
//				FavAllItemsCacher cacher = FavAllItemsCacher.getInstance();
//				cacher.addItem(bean);
//
//				// 调用更新
////				/*
////				 * UMPDetectRPCClient.getInstance().createRemoteUMPDetectJob(num_iid
////				 * , item.getTitle(), item.getPrice());
////				 */
//
//				flag = false;
//			} else {
//				if (log.isInfoEnabled()) {
//					log.info("item is null");
//				}
//				flag = true;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}
	

	/**
	 * 更新用户商品数据到数据库
	 * **/
//	public void updateUserItem(final long uid,final String favList) {
//		log.info("in update UserInfo");
//		final FavLockCacher lockCacher = FavLockCacher.getInstance();
//		final String key = X.CachePrefix.FAV_ITEMS_DETAIL + X.DOT + uid;
//		boolean isRun = lockCacher.isRefrush(key);
//
//		Runnable run = new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				lockCacher.runRefrush(key);
//				log.info("thread update user fav in db start");
////				TBFavListSyncModel.getInstance().syncItem(uid);
//				try {
//					updateItemData(uid,favList);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				log.info("thread update user fav in db end");
//				lockCacher.destoryRefrush(key);
//
//			}
//		};
//		if (!isRun) {
//			TPool.getInstance().doit(run);
//		}
//	}

	/**
	 * 更新用户商店数据到数据库
	 * **/
//	public void updateUserShop(final long uid,final String favList) {
//		log.info("in update UserInfo");
//		final FavLockCacher lockCacher = FavLockCacher.getInstance();
//		final String key = X.CachePrefix.FAV_ALL_SHOPS + X.DOT + uid;
//		boolean isRun = lockCacher.isRefrush(key);
//
//		Runnable run = new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				lockCacher.runRefrush(key);
//				log.info("thread update user fav shop in db start");
////				TBFavListSyncModel.getInstance().syncShop(uid);
//				try {
//					updateShopData(uid,favList);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				log.info("thread update user fav shop in db end");
//				lockCacher.destoryRefrush(key);
//
//			}
//		};
//		if (!isRun) {
//			TPool.getInstance().doit(run);
//		}
//	}

	/**
	 * 更新收藏商品数据到数据库
	 * */
//	public void updateItemData(long uid,String favList) throws Exception {
//		// 判断数据存在不
//		JSONArray array = new JSONArray(favList);
//		// long[] a = new long[array.length()];
//		String iids = "";
//		for (int i = 0; i < array.length(); i++) {
//			long num_iid = Long.parseLong(array.getString(i));
//			iids += ","+num_iid;
//		}
//
//		Map<String, Item> map = TaobaokeManager.getBatchTBItems(iids);
//		Set<String> set = map.keySet();
//		Iterator<String> ite = set.iterator();
//		while (ite.hasNext()) {
//			String key = ite.next();
//			Item item = map.get(key);
//
//			if (item != null) {
//				ItemBean bean = new ItemBean();
//				bean.setNum_iid(item.getNumIid() + "");
//				bean.setTitle(item.getTitle());
//				bean.setPic_url(item.getPicUrl());
//				bean.setPrice(item.getPrice());
//				bean.setClick_url(item.getDetailUrl());
//				bean.setPrice_high(item.getPrice());
//				bean.setPrice_low(item.getPrice());
//				bean.setDiscount("0");
//
//				TaobaokeItem titem = TaobaokeManager.getBatchTBKConvert(item.getNumIid() + "").get(item.getNumIid() + "");
//				if (titem != null) {
//					bean.setCommission(titem.getCommission());
//					bean.setCommission_rate(titem.getCommissionRate());
//					bean.setCommission_num(titem.getCommissionNum());
//					bean.setCommission_volume(titem.getCommissionVolume());
//					bean.setClick_url(titem.getClickUrl());
//				}
//				// 插入数据库
//				TBFavListSyncModel.getInstance().syncItem(uid, bean);
//			}
//		}
//	}

//	/**
//	 * 更新收藏商店数据到数据库
//	 * */
//	public void updateShopData(long uid,String favList) throws Exception {
//		String iids = "";
//		Map<String,String> idMap = new HashMap<String, String>();
//		JSONArray array = new JSONArray(favList);
//		for (int i = 0; i < array.length(); i++) {
//			JSONObject obj = array.getJSONObject(i);
//			FavShopBean bean = new FavShopBean();
//			bean.num_iid = obj.getLong("id");
//			bean.title = obj.getString("title");
//			iids += ","+bean.num_iid;
//			idMap.put(bean.title, bean.num_iid+"");
//		}
//		
//		Map<String, TaobaokeShop> map = TaobaokeManager.getBatchTBKShops(iids);
//		Set<String> set = map.keySet();
//		Iterator<String> ite = set.iterator();
//		while (ite.hasNext()) {
//			String key = ite.next();
//			TaobaokeShop shop = map.get(key);
//
//			ShopBean bean = new ShopBean();
//			bean.setShop_id(idMap.get(key));
//			bean.setTitle(shop.getShopTitle());
//			if (shop != null) {
//				bean.setClick_url(shop.getClickUrl());
//				bean.setCommission_rate(shop.getCommissionRate());
//				bean.setTitle(shop.getShopTitle());
//			} else {
//				bean.setClick_url("http://shop.m.taobao.com/shop/shop_index.htm?shop_id="
//						+ idMap.get(key));
//			}
//			// 插入数据库
//			TBFavListSyncModel.getInstance().syncShop(uid, bean);
//		}
//
//	}

	class FavShopBean {
		public long num_iid;
		public String title;
	}
	

}
