package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.youhui.bean.GoodsTrade;
import cn.youhui.dao.Admin_Goods_DAO;

import com.google.gson.Gson;


/**
 * 首页
 * @author 
 *
 */

@WebServlet("/superDiscount/GoodsAction")
public class AdminGoodsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminGoodsAction() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("actionmethod");
		
		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		
		try {
			if(methodName.equals("getGoodsByUID")){
				getGoodsByUID(request, response);
			}else if(methodName.equals("getGoodsById")){
				getGoodsById(request, response);
			}
		} catch (Exception e){
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void getGoodsByUID(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String UID = "0";
			List<GoodsTrade> goodList = Admin_Goods_DAO.getGoodsTradeByUID(UID);
			Gson gs = new Gson();
			JSONArray list = new JSONArray();
			for (int i = 0; i < goodList.size(); i++) {
				String gt = gs.toJson(list.get(i), GoodsTrade.class);
				list.put(new JSONObject(gt));
	   		}
			response.getWriter().print(list);
	}
	
	public void getGoodsById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			GoodsTrade gts = Admin_Goods_DAO.getGoodsTradeById(id);
			Gson gs = new Gson();
			String gt = gs.toJson(gts, GoodsTrade.class);
			response.getWriter().print(gt);
	}

}
