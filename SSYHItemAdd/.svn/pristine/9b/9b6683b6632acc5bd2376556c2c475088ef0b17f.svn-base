package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.cache.dao.superItemTagsCacher;
import cn.youhui.itemDAO.SuperItemTagsDAO;

/**
 * Servlet implementation class SuperItemTags
 */
@WebServlet("/SuperItemTags")
public class SuperItemTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperItemTags() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if(method!=null){
			if(method.equalsIgnoreCase("get_all")){
				List<cn.youhui.bean.SuperItemTags> list=SuperItemTagsDAO.getInstance().getAllTags();
				StringBuffer sb=new StringBuffer();
				sb.append("[");
				for(cn.youhui.bean.SuperItemTags sit:list){
					sb.append(sit.toJson()+",");
				}
				sb.append("]");
				System.out.println(sb.toString());
				response.getWriter().write(sb.toString());
			}else if(method.equalsIgnoreCase("add")){
				long timestamp=new Date().getTime();
				String name=zm(request.getParameter("name"));
				String desc=zm(request.getParameter("desc"));
				String img=request.getParameter("img");
				
				if(name==null||desc==null||img==null){
					response.getWriter().write("paramException");
					return;
				}
				int id=SuperItemTagsDAO.getInstance().add(name, desc,timestamp,img);
				if(id!=-1){
					cn.youhui.bean.SuperItemTags st=new cn.youhui.bean.SuperItemTags();
					st.setId(id);
					st.setName(name);
					st.setDesc(desc);
					st.setImg(img);
					st.setTimestamp(timestamp);
					superItemTagsCacher.getInstance().addSuperDiscountById(""+id, st);
					response.getWriter().write("success");
				}else{
					response.getWriter().print("fail");
				}
			}else if(method.equalsIgnoreCase("del")){
				String id=request.getParameter("id");
				if(id==null||"".equals(id)){
					response.getWriter().print("paramException");
					return;
				}
				if(SuperItemTagsDAO.getInstance().del(Integer.parseInt(id))){
					superItemTagsCacher.getInstance().delSuperDiscount(id);
					response.getWriter().print("success");
					return;
				}
				response.getWriter().print("fail");
			}else if(method.equalsIgnoreCase("update")){
				String name=request.getParameter("name");
				String desc=request.getParameter("desc");
				String img=request.getParameter("img");
				String id=request.getParameter("id");
				
				if(name==null||desc==null||id==null||img==null){
					response.getWriter().write("paramException");
					return;
				}
				cn.youhui.bean.SuperItemTags st=new cn.youhui.bean.SuperItemTags();
				st.setId(Integer.parseInt(id));
				st.setName(name);
				st.setDesc(desc);
				st.setImg(img);
				if(SuperItemTagsDAO.getInstance().update(st)){
					superItemTagsCacher.getInstance().update(st);
					response.getWriter().write("success");
					return;
				}
				response.getWriter().print("fail");
			}
		}
	}

	
	public String zm(String str){
		String tmp="";
		try{
			byte[] b=str.getBytes("iso8859-1");
			tmp=new String(b,"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return tmp;
 	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
