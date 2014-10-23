package cn.youhui.itemadd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.itemadd.ProjectInfo;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TbkItem;
import com.taobao.api.request.TbkItemsDetailGetRequest;
import com.taobao.api.response.TbkItemsDetailGetResponse;

/**
 * Servlet implementation class ItemDetail
 */
@WebServlet("/ItemDetail")
public class ItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		if(id==null||"".equals(id)){
			response.getWriter().print("paramException");
		}else {
			TaobaoClient client=new DefaultTaobaoClient(ProjectInfo.AppReqUrl, ProjectInfo.AppKey, ProjectInfo.AppSecret);
			TbkItemsDetailGetRequest req=new TbkItemsDetailGetRequest();
			req.setFields("num_iid,seller_id,nick,title,price,volume,pic_url,item_url,shop_url");
			req.setNumIids(id);
			try {
				TbkItemsDetailGetResponse rsp= client.execute(req);
				if(rsp.getTbkItems().size()>0){
					TbkItem item = rsp.getTbkItems().get(0);
					response.getWriter().print("{\"volume\":\""+item.getVolume()+"\",\"shop_url\":\""+item.getShopUrl()+"\",\"sellerid\":\""+item.getSellerId()+"\",\"price\":\""+item.getPrice()+"\",\"title\":\""+item.getTitle()+"\",\"src\":\""+item.getPicUrl()+"\",\"itemid\":\""+id+"\",\"nick\":\""+item.getNick()+"\"}");
				}else {
					response.getWriter().print("paramException");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				response.getWriter().print("paramException");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
	
	public static void main(String[] args) {
		TaobaoClient client=new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "12347692", "c62deb79ce636f9cbac7c074269ab1d6");
		TbkItemsDetailGetRequest req=new TbkItemsDetailGetRequest();
		req.setFields("num_iid,seller_id,nick,title,price,volume,pic_url,item_url,shop_url");
		String id="38823905949";
		req.setNumIids(id);
		try {
			TbkItemsDetailGetResponse rsp= client.execute(req);
			if(rsp.getTbkItems().size()>0){
				TbkItem item = rsp.getTbkItems().get(0);
				System.out.println("{\"volume\":\""+item.getVolume()+"\",\"shop_url\":\""+item.getShopUrl()+"\",\"sellerid\":\""+item.getSellerId()+"\",\"price\":\""+item.getPrice()+"\",\"title\":\""+item.getTitle()+"\",\"src\":\""+item.getPicUrl()+"\",\"itemid\":\""+id+"\",\"nick\":\""+item.getNick()+"\"}");
			}else {
				System.out.println("paramException");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("paramException");
		}
	}
	

}
