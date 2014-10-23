
package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.youhui.itemDAO.ItemDAO;
import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.platform.db.DBManager;

/**
 * Servlet implementation class AddItem
 */
@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String itemid=request.getParameter("itemid");
		String title=request.getParameter("title");
		String imgurl=request.getParameter("img");
		String recom=request.getParameter("recom");
		String remark = request.getParameter("remark");
		
		
		String volume=request.getParameter("volume");
		String shop_url=request.getParameter("shop_url");
		String sellerid=request.getParameter("sellerid");
		String price=request.getParameter("price");
		String nick=request.getParameter("nick");

		int userid = DBManager.Login(username, password);
		
//		if(nick!=null&&!"".equals(nick)){
//			nick = new String(nick.getBytes("iso8859-1"),"UTF-8");
//		}
//		if(shop_url!=null&&!"".equals(shop_url)){
//			shop_url = new String(shop_url.getBytes("iso8859-1"),"UTF-8");
//		}
//		
//		if(title!=null&&!"".equals(title)){
//			System.out.println("11111111111111111111111111111111111111111111"+title);
//			title = new String(title.getBytes("iso8859-1"),"UTF-8");
//			System.out.println("2222222222222222222222222222222222222222222"+title);
//		}
//		if(recom!=null&&!"".equals(recom)){
//			recom = new String(recom.getBytes("iso8859-1"),"UTF-8");
//		}
//		
//		if(remark!=null&&!"".equals(remark)){
//			remark = new String(remark.getBytes("iso8859-1"),"UTF-8");
//		}
		
		
		if(userid>=0){
			
			ItemBean ib = new ItemBean();
			ib.imgurl = imgurl;
			ib.userid = userid;
			ib.itemid = itemid;
			ib.nick = nick;
			ib.price = Double.parseDouble(price);
			ib.recom = recom;
			ib.remark = remark;
			ib.sellerid = sellerid;
			ib.shopurl = shop_url;
			ib.title = title;
			ib.volume = volume;
			ib.rate=Double.parseDouble("0");
			ib.zkPrice=Double.parseDouble(price);
			if(DBManager.ifExist(ib.itemid)){
//				DBManager.updateItem(ib);
				response.getWriter().print("exist");
			}else if(DBManager.addItem(ib)&&DBManager.addRecord(ib.userid, ib.itemid)){
				response.getWriter().print("success");
			}else {
				response.getWriter().print("fail");
			}
		}else {
			response.getWriter().print("paramException");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
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
