package cn.youhui.api.superdiscount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.SuperItemTags;
import cn.youhui.cacher.dao.superItemTagsCacher;
import cn.youhui.dao.SuperItemTagsDAO;

/**
 * 获取标签详情
 */
@WebServlet("/ItemTags")
public class ItemTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemTags() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("tag_id")==null||"".equalsIgnoreCase(request.getParameter("tag_id"))){
			response.getWriter().write("paramException");
			return;
		}
		int tagId=Integer.parseInt(request.getParameter("tag_id"));
//		SuperItemTags sit=SuperItemTagsDAO.getInstance().getById(tagId);
		SuperItemTags sit=superItemTagsCacher.getInstance().getSuperItemTagsById(""+tagId);
		if(sit==null){
			response.getWriter().write("notfond");
			return;
		}
		String bac=SuperItemTagsDAO.getInstance().getById(tagId).toJson();
		response.getWriter().write(bac);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
