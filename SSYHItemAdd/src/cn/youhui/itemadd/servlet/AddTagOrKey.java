package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Param;

import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.youhui.admin.dao.AddItemUtil;
import cn.youhui.itemDAO.ItemDAO;
import cn.youhui.itemDAO.ItemHisDAO;
import cn.youhui.itemadd.dataadapter.BaseAdapter;
import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.DBManager;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.util.NetManager;

/**
 * Servlet implementation class AddTagOrKey
 */
@WebServlet("/addtok")
public class AddTagOrKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTagOrKey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String tmp=zm(request.getParameter("id"));
			String ids="";
			for(int i=0;i<tmp.split(",").length;i++){
				if(i==tmp.split(",").length-1){
					ids=ids+tmp.split(",")[i].split("_")[0];
				}else{
					ids=ids+tmp.split(",")[i].split("_")[0]+",";
				}
			}
			JSONArray ja=JSONObject.fromObject(zm(request.getParameter("content"))).getJSONArray("item_list");
			int count=0;
			for(int i=0;i<ja.size();i++){
				ItemBean item=new ItemBean();
				String itemId=JSONObject.fromObject(ja.get(i)).getString("item_id");
				item=ItemDAO.getInstance().getItem(itemId);
				String str=NetManager.getInstance().send(param.baoyou, "baoyou=baoyou&itemid="+itemId);
				JSONObject jo=JSONObject.fromObject(str);
				if(jo.has("baoyou")){
					item.setBaoyou(jo.getInt("baoyou"));
				}
				if(jo.has("catid")){
					item.setCatId(jo.getString("catid"));
				}
				item.setOriginalImgUrl(JSONObject.fromObject(ja.get(i)).getString("pic_url"));
				Connection conn=SQL.getInstance().getConnection();
				if(AddItemUtil.addItem(item, ids, request.getParameter("type"),conn)){
					if(request.getParameter("type").equals("recoItem")){
						count++;
					}else{
						count=count+ids.split(",").length;
					}
				}
				SQL.getInstance().release(null, conn);
			}
			if(count==0){
				response.getWriter().write("fail");
			}else{
				response.getWriter().write(String.valueOf(count));
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String zm(String str){
		byte[] b;
		String tmp="";
		try {
			b = str.getBytes("iso8859-1");
			tmp=new String(b,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
}
