package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonParser;

import cn.youhui.admin.dao.AdminRecoItemDAO;
import cn.youhui.cache.dao.RecoItemCacheDAO;
import cn.youhui.platform.util.NetManager;

/**
 * @author jiangjun
 * @since 2014-08-14
 */
@WebServlet("/AddHomeItems2Suggest")
public class AddHomeItems2Suggest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddHomeItems2Suggest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
//		Set<String> set=RecoItemCacheDAO.getAllBigReco();
		List<String> list=AdminRecoItemDAO.getListNew2();
		String content="";
		for(String val:list){
			content=content+val+",";
		}
		if(content.length()>0){
			content=content.substring(0,content.length()-1);
		}
		System.out.println(content);
		String url="http://admin.suishou.cn/stat/AddSugItems";
		NetManager.send(url, "pid="+content);
		response.getWriter().print(content);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public static void main(String[] args) {
		List<String> list=AdminRecoItemDAO.getListNew2();
		String content="";
		for(String val:list){
			content=content+val+",";
		}
		if(content.length()>0){
			content=content.substring(0,content.length()-1);
		}
		System.out.println(content);
	}
}
