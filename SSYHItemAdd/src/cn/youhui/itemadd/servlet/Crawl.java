package cn.youhui.itemadd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import cn.youhui.itemDAO.ItemDAO;
import cn.youhui.platform.util.NetManager;

/**
 * Servlet implementation class Crawl
 */
@WebServlet("/crawl")
public class Crawl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Crawl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("itemIds")==null||"".equals(request.getParameter("itemIds"))){
			response.getWriter().write("paramException");
			return;
		}
		String itemIds=request.getParameter("itemIds");
		int successNum=0;
		for(int i=0;i<itemIds.split(",").length;i++){
			
			String itemId=itemIds.split(",")[i];
			
			String url="http://pub.alimama.com/pubauc/searchAuctionList.json?q=http://item.taobao.com/item.htm?id="+itemId;
			String data=NetManager.getInstance().send(url,"");
			response.getWriter().write(data);return;
//			JSONObject jo=JSONObject.fromObject(data);
//			if(jo.has(data)){
//				double reservePrice=((JSONObject)(jo.getJSONObject("data").getJSONArray("pagelist").get(0))).getDouble("reservePrice");
//				double zkPrice=((JSONObject)(jo.getJSONObject("data").getJSONArray("pagelist").get(0))).getDouble("zkPrice");
//				double commission_rate=((JSONObject)(jo.getJSONObject("data").getJSONArray("pagelist").get(0))).getDouble("commission_rate");
//				
//				double rate=((int)commission_rate)/2;
//				if(ItemDAO.getInstance().uodateItem(String.valueOf(reservePrice), zkPrice, rate)){
//					successNum++;
//				}
//			}
		}
		response.getWriter().write(successNum);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub'
		doGet(request, response);
	}

}
