package cn.suishou.some.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import cn.suishou.some.bean.TeJiaGoodsBean;
import cn.suishou.some.manager.JedisHashManager;
import cn.suishou.some.manager.JedisSortedSetManager;
import cn.suishou.some.util.OuterCode;



@WebServlet("/GetTagItems")
public class GetTagItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int pageSize = 20;
	private static String key = "tag.tag2item";
	private static String itemKey = "youhui.cn.tag.products.";
	
    public GetTagItems() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String tagId = request.getParameter("tagid");
		String p = request.getParameter("page");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String isNeedCT = request.getParameter("is_need_convert");
		boolean isNeedConvert = true;
		if("0".equals(isNeedCT)){
			isNeedConvert = false;
		}
		if(uid == null){
			uid = "";
		}
		int page = 1;
		try {
			page = Integer.parseInt(p);
		} catch (Exception e) {
			page = 1;
		}
		if(tagId == null || "".equals(tagId)){
			response.getWriter().print(ResultToString(null, 1, 1));
			return;
		}
		try {
			
		
		JedisSortedSetManager sort = new JedisSortedSetManager(key+tagId);
		long size = sort.size();
		if(size % pageSize == 0){
			size = size / pageSize;
		}else{
			size = size / pageSize +1;
		}
		if(page < 1){
			page = 1;
		}
		Set<String> idset = sort.zrange((page - 1)*pageSize, (page * pageSize - 1));
		
		List<TeJiaGoodsBean> itemlist = getItems(idset, uid, platform, isNeedConvert);
		if(page > size){
			page = (int)size;
		}
		String ret = ResultToString(itemlist, page, size);
		System.out.println(ret);
		response.getWriter().print(ret);
		} catch (Exception e) {
			response.getWriter().print(ResultToString(null, 1, 1));
		}
	}

	public static String ResultToString(List<TeJiaGoodsBean> itemlist,int page,long total){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
//		JSONObject jso = new JSONObject();
//		try {
			if(itemlist == null || itemlist.size() == 0){
				sb.append("\"status\":").append("\"1009\",")
				.append("\"desc\":").append("\"Did not return a result\"");
				
//				jso.put("status", "1009");
//				jso.put("desc", "Did not return a result");
			}else{
				sb.append("\"status\":").append("\"1000\",")
				.append("\"desc\":").append("\"OK\"").append(",")
				.append("\"page\":").append(page).append(",")
				.append("\"total\":").append(total).append(",")
				.append("\"items\":").append("[");
				
//				jso.put("status", "1000");
//				jso.put("desc", "OK");
//				jso.put("page", page);
//				jso.put("total", total);
//				JSONArray jsa = new JSONArray();
				for(TeJiaGoodsBean bean : itemlist){
					sb.append(bean.toJsonNew());
					sb.append(",");
//					jsa.put(bean.toJsonNew());
				}
				sb.deleteCharAt(sb.length()-1);
//				jso.put("item", jsa);
				sb.append("]");
			}
//		} catch (JSONException e) {
//				return jso.toString();
//		}
		sb.append("}");
		return sb.toString();
	}
	
	public List<TeJiaGoodsBean> getItems(Set<String> idset, String uid, String platform, boolean isNeedConvert){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		if(idset != null && idset.size() > 0)
		{
			try {
			for(String id : idset)
			{
				TeJiaGoodsBean bean = fromJsonToBean(id);
				if(bean != null){
					if(isNeedConvert){
						String outerCode =  OuterCode.getOuterCode(uid, platform, bean.getRate()+"", "6", "");
						String url = "http://b17.cn/item?tyh_outer_code=" + outerCode + "&itemid=" + bean.getItem_id();
						bean.setClickURL(url);
					}
					//bean.setClickURL("suishou://youhui.cn?action_title="+ URLEncoder.encode(bean.getTitle(), "UTF-8") +"&action_value="+bean.getItem_id()+"&action_type=tagStyleItem&jump_from=own");
					list.add(bean);
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}
		return list;
	}
	
	public static TeJiaGoodsBean fromJsonToBean(String id){
		TeJiaGoodsBean bean = null;
		JedisHashManager hash = new JedisHashManager(itemKey);
		Gson gson = new Gson();
		String json = hash.get(id);
		if (json != null) {
			bean = gson.fromJson(json, TeJiaGoodsBean.class);
		}
//		KeywordBean keyword = new Gson().fromJson(content, KeywordBean.class);
		
		return bean;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
