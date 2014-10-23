package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.youhui.admin.dao.GetItemUtil;
import cn.youhui.itemDAO.ItemDAO;
import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.platform.config.param;
import cn.youhui.platform.util.NetManager;

/**
 * Servlet implementation class addtokjump
 */
@WebServlet("/addtokjump")
public class Addtokjump extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addtokjump() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String addid=request.getParameter("id");
		JSONObject jo=null;
		String str=GetItemUtil.ListToString();
//		String str=NetManager.getInstance().send(param.tagAndKeyword_Address, "");
		jo=JSONObject.fromObject(str);
		param.json=jo.toString();
		
		
		Map<String,String> map=new HashMap<String,String>();
		Map<String,String> map2=new HashMap<String,String>();
		List<String[]> l1=new ArrayList<String[]>();
		List<String[]> l2=new ArrayList<String[]>();
		
		JSONObject d=(JSONObject) jo.get("data");
		
		JSONArray ja=(JSONArray) d.get("tags");
		for(int i=0;i<ja.size();i++){
			JSONObject tag=(JSONObject) ja.get(i);
			String id=tag.getString("id");
			String name=tag.getString("name");
			String[] s=new String[2];
			s[0]=id;
			s[1]=name;
			l1.add(s);
		}
//		for(int i=0;i<ja.size();i++){
//			JSONObject tag=(JSONObject) ja.get(i);
//			String id=tag.getString("id");
//			String name=tag.getString("name");
//			map.put(id,name);
//		}
		JSONArray ja2=(JSONArray) d.get("keywords");
		for(int i=0;i<ja2.size();i++){
			JSONObject jo2=ja2.getJSONObject(i);
			String id=(String) jo2.get("id");
			String name=(String) jo2.get("name");
			map2.put(id,name);
			String[] s=new String[2];
			s[0]=id;
			s[1]=name;
			l2.add(s);
		}
//		JSONArray ja2=(JSONArray) d.get("keywords");
//		for(int i=0;i<ja2.size();i++){
//			JSONObject jo2=ja2.getJSONObject(i);
//			String id=(String) jo2.get("id");
//			String name=(String) jo2.get("name");
//			map2.put(id,name);
//		}
		
//		request.setAttribute("map", map);
//		request.setAttribute("map2", map2);
		request.setAttribute("list1", l1);
		request.setAttribute("list2", l2);
		request.setAttribute("id", addid);
		ItemBean ib=ItemDAO.getInstance().getPreItem(addid);
		String itemval="{'jfb_rate':"+ib.rate+",'title':'"+ib.title+"','pic_url':'"+ib.imgurl+"','price_high':'"+ib.price+"','click_url':'"+ib.shopurl+"','price_low':'"+ib.zkPrice+"','item_id':'"+ib.itemid+"'}";
		request.setAttribute("itemval", itemval);
		
		request.getRequestDispatcher("/addtok.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
