package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.cache.dao.superDiscountCacher;
import cn.youhui.itemDAO.SuperDiscountDAO;
import cn.youhui.platform.db.SQL;

/**
 * Servlet implementation class SuperDiscountRank
 */
@WebServlet("/SuperDiscountRank")
public class SuperDiscountRank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperDiscountRank() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String id1=request.getParameter("id1");
//		String id2=request.getParameter("id2");
//		String rank1=request.getParameter("rank1");
//		String rank2=request.getParameter("rank2");
		String data=request.getParameter("data");
		String discountTimestamp=request.getParameter("discount_timestamp");
//		if(id1==null||id2==null||rank1==null||rank2==null||"".equals(id1)||"".equals(id2)||"".equals(rank1)||"".equals(rank2)){
//			response.getWriter().print("ParamException");
//			return;
//		}
		if(data==null||"".equals(data)){
			response.getWriter().print("ParamException");
			return;
		}
		JsonParser jp=new JsonParser();
		JsonArray ja=jp.parse(data).getAsJsonArray();
		Map<String,Integer> map=new HashMap<String, Integer>();
		int top=ja.size();
		for(int i=0;i<ja.size();i++){
			String id=ja.get(i).getAsString();
			map.put(id, top);
			top--;
		}
//		if(SuperDiscountDAO.getInstance().changeRank(Integer.parseInt(id1), Integer.parseInt(id2), Integer.parseInt(rank1), Integer.parseInt(rank2), Long.parseLong(discountTimestamp))){
		if(SuperDiscountDAO.getInstance().changeRankNew(map)){
				Connection conn=SQL.getInstance().getConnection();
			List<SuperDiscountBean> list=SuperDiscountDAO.getInstance().getInfo(conn, discountTimestamp);
			System.out.println("===========================>>>>>>>>>>>>>>"+list.size());
			for(SuperDiscountBean sb:list){
				superDiscountCacher.getInstance().delSuperDiscountById(sb.getId()+"", discountTimestamp);
				superDiscountCacher.getInstance().addSuperDiscount(sb.getId()+"",sb);
			}
			SQL.getInstance().release(null, conn);
			response.getWriter().print("success");
			return;
		}
		response.getWriter().print("fail");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
