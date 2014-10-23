package cn.youhui.api.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import cn.youhui.bean.ItemBean;
import cn.youhui.bean.UMPItemBean;
import cn.youhui.common.Enums;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.ramdata.FavAllItemsCacher;
import cn.youhui.ramdata.FavUMPCacher;
import cn.youhui.ramdata.FavUser2ItemsCacher;
import cn.youhui.ramdata.FavUMPItems4UsersCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * Servlet implementation class GetFavoriteList
 * 
 * get user's promotion products pushes
 */
@WebServlet("/taobao/umppush")
public class TBGetUMPPush extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TBGetUMPPush.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TBGetUMPPush() {
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
		try{
		String uid = request.getParameter("uid");
		String access = request.getParameter("access_token");
		String page = request.getParameter("page");
		
		if(access==null){
			access = "";
		}
		
		if(uid== null){
			uid = "";
		}
		if(page == null){
			page = "1";
		}
		if(uid.equals("")||access.equals("")){
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
		 String result = xmlFormat(Long.parseLong(uid), Integer.parseInt(page), access);
		 response.getWriter().print(result);
//		response.sendRedirect(request.getContextPath()+"/docs/xml/get_ump_push.xml");
		}catch(Exception e){
			log.error(e, e);
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.SERVER_ERROR));
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String xmlFormat(long uid,int page,String access_token) throws Exception{
		StringBuffer sb = new StringBuffer();
		
		FavUMPItems4UsersCacher cacher = FavUMPItems4UsersCacher.getInstance();
		List<String> list = cacher.getUMPItemIDs(uid);
		sb.append("<umps>");
		FavUMPCacher umpCacher = FavUMPCacher.getInstance() ;
//		ArrayList<String> list = cacher.getItems();
		int count = list.size();
		count = count%10==0?count/10:count/10+1;
		if(page > count&&count>0){
			page = count;
		}
		for(int i = (page-1)*10; i < page*10 && i<list.size() ;i++ ){
			sb.append("<umpitem>");
			String itemId = list.get(i);
			UMPItemBean item = umpCacher.getUMPItem(itemId) ;
			sb.append("<itemid>"+item.getItemId()+"</itemid>");
			sb.append("<itemtitle>"+item.getItemTitle()+"</itemtitle>");
			sb.append("<umpid>"+item.getPromotionId()+"</umpid>");
			sb.append("<umpname>"+item.getPromoName()+"</umpname>");
			sb.append("<old_price>"+item.getOriginalPrice()+"</old_price>");
			sb.append("<new_price>"+item.getPromoPrice()+"</new_price>");
			sb.append("<umpdesc><![CDATA[降价了]]></umpdesc>");
			sb.append("<endtime>"+item.getEndTime().getTime()+"</endtime>");
			sb.append("<img><![CDATA[]]></img>");
			sb.append("</umpitem>");
		}
		sb.append("</umps>");
		
		return RespStatusBuilder.messageAccess(Enums.ActionStatus.NORMAL_RETURNED.inValue(), 
				Enums.ActionStatus.NORMAL_RETURNED.getDescription(), 
				access_token, count, page, sb.toString()).toString();
		
	}
	

}
