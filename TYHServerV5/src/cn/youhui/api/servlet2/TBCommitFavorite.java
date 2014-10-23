package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.youhui.common.Config;
import cn.youhui.common.Enums;
import cn.youhui.model.storage.TBFavListSyncModel;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 保存用户收藏信息
 * @author leejun
 * @since 2013-03-30
 */
@WebServlet("/tyh2/commitfav")
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
				response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			} else {
				try {
					if (type.equals("item")) {	
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

}
