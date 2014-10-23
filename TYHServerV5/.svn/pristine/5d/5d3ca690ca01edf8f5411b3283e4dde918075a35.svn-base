package cn.youhui.api.servlet2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.GetItemManager;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh2/getitem")
public class YHGetItemByItemid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public YHGetItemByItemid() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String itemid = request.getParameter("itemid");
		if(itemid == null || "".equals(itemid)){
			response.getWriter().print(getResult(ActionStatus.PARAMAS_ERROR,""));
			return;
		}

		TeJiaGoodsBean bean = GetItemManager.getGoodsByItemid(itemid);
		if(bean == null){
			try {
				String content = NetManager.getInstance().getContent("http://tbitem.duapp.com/item?itemid="+itemid);
				if(content == null || "".equals(content)){
					response.getWriter().print(getResult(ActionStatus.SERVER_ERROR,""));
					return;
				}
				response.getWriter().print(getResult(ActionStatus.NORMAL_RETURNED,content));
				return;
			} catch (Exception e) {
				response.getWriter().print(getResult(ActionStatus.SERVER_ERROR,""));
				return;
			}
		}
		response.getWriter().print(getResult(ActionStatus.NORMAL_RETURNED,getJsonBean(bean)));
	}

	public static String getJsonBean(TeJiaGoodsBean bean){
		if(bean == null){
			return "";
		}
		JSONObject jso = new JSONObject();
		try {
			jso.put("item_id", bean.getItem_id());
			jso.put("title", bean.getTitle());
			jso.put("price", bean.getPrice_low());
			jso.put("pic_url", bean.getPic_url());
			jso.put("click_url", bean.getClickURL());

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jso.toString();
	}

	public static String getResult(Enums.ActionStatus status,String data){
		JSONObject jso = new JSONObject();
		try {
			jso.put("status",status.inValue());
			jso.put("desc", status.getDescription());
			jso.put("data", data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jso.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
