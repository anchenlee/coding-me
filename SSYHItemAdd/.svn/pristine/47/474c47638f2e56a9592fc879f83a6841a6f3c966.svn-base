package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.admin.dao.AdminTagDAO;
import cn.youhui.bean.TagBean;

/**
 * Servlet implementation class GetSonOfTag
 */
@WebServlet("/GetSonOfTag")
public class GetSonOfTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSonOfTag() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("content-type", "text/html;charset=UTF-8");
		if(request.getParameter("tagId")==null||"".equals(request.getParameter("tagId"))){
			response.getWriter().write("paramException");
			return;
		}
		String parentId=request.getParameter("tagId");
		List<TagBean> list=AdminTagDAO.getTags(parentId);
		
		if(list.size()==0){
			response.getWriter().write("empty");
			return;
		}
		
		String htm="";
		for(int i=0;i<list.size();i++){
			htm=htm+"<tr name='tr_"+parentId+"'>";
			if(i<list.size()){
				TagBean tb=list.get(i);
				String tagId=tb.getTag_id();
				String name=tb.getTag_name();
				String tmp=tagId+"_"+name;
				htm=htm+"<td><input name=\"checkbox_tags\" type=\"checkbox\" value=\""+tmp+"\" /></td><td>"+name+"</td>";
			}
			i++;
			if(i<list.size()){
				TagBean tb=list.get(i);
				String tagId=tb.getTag_id();
				String name=tb.getTag_name();
				String tmp=tagId+"_"+name;
				htm=htm+"<td><input name=\"checkbox_tags\" type=\"checkbox\" value=\""+tmp+"\" /></td><td>"+name+"</td>";
			}
			i++;
			if(i<list.size()){
				TagBean tb=list.get(i);
				String tagId=tb.getTag_id();
				String name=tb.getTag_name();
				String tmp=tagId+"_"+name;
				htm=htm+"<td><input name=\"checkbox_tags\" type=\"checkbox\" value=\""+tmp+"\" /></td><td>"+name+"</td>";
			}
			i++;
			if(i<list.size()){
				TagBean tb=list.get(i);
				String tagId=tb.getTag_id();
				String name=tb.getTag_name();
				String tmp=tagId+"_"+name;
				htm=htm+"<td><input name=\"checkbox_tags\" type=\"checkbox\" value=\""+tmp+"\" /></td><td>"+name+"</td>";
			}
			i++;
			if(i<list.size()){
				TagBean tb=list.get(i);
				String tagId=tb.getTag_id();
				String name=tb.getTag_name();
				String tmp=tagId+"_"+name;
				htm=htm+"<td><input name=\"checkbox_tags\" type=\"checkbox\" value=\""+tmp+"\" /></td><td>"+name+"</td>";
			}
			
			htm=htm+"</tr>";
		}
		
		response.getWriter().write(htm);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
