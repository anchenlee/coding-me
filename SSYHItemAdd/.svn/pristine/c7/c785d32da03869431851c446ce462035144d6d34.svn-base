package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

/**
 * Servlet implementation class UpdateItemFroWait
 */
@WebServlet("/updateForWait")
public class UpdateItemFroWait extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateItemFroWait() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("yes")!=null){
			if(request.getParameter("yes").equals("updateInfo")){
				String id=request.getParameter("id");
				String title=zm(request.getParameter("item_title"));
				String recom=zm(request.getParameter("item_recom"));
				String remark=zm(request.getParameter("item_remark"));
				int rate2=(int)Double.parseDouble(request.getParameter("rate"));
				Double zkPrice=Double.parseDouble(request.getParameter("zk_price"));
				String imgSize=request.getParameter("img_size");
				
				if(ItemDAO.getInstance().uodateItem(Integer.parseInt(id), title, recom, remark,rate2,zkPrice,imgSize)){
					response.getWriter().write("success");
				}else{
					response.getWriter().write("fail");
				}
				
			}else if(request.getParameter("yes").equals("update")){
				if(request.getParameter("itemId")==null||"".equals(request.getParameter("itemId"))){
					response.getWriter().write("paramException");
					return;
				}
				String itemId=request.getParameter("itemId");
				ItemBean ib=ItemDAO.getInstance().getItem(itemId);
				request.setAttribute("item",ib);
				if(ib.getRecom()!=null&&!ib.getRecom().equals("")){
					ib.setRecom(ib.getRecom().replaceAll("'",""));
					ib.setRecom(ib.getRecom().replaceAll("\"",""));
				}
				if(ib.title!=null&&!ib.title.equals("")){
					ib.setTitle(ib.getTitle().replaceAll("'", ""));
					ib.setTitle(ib.getTitle().replaceAll("\"", ""));
				}
				String itemval="{'recom':'"+ib.recom+"','jfb_rate':'"+ib.rate+"','title':'"+ib.title+"','pic_url':'"+ib.imgurl+"','price_high':'"+ib.price+"','click_url':'"+ib.shopurl+"','price_low':'"+ib.zkPrice+"','item_id':'"+ib.itemid+"'}";
				request.setAttribute("itemval", itemval);
				
				request.getRequestDispatcher("/updateFromWait.jsp").forward(request,response);
			}
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
