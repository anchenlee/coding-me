package cn.suishou.some.autumnitem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取对应栏目下商品
 * @author lujiabin
 * @since 2014-9-2
 */
@WebServlet("/item")
public class GetItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetItemListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String uid = request.getParameter("uid");
			String menuid = request.getParameter("menuid");
			
			if(uid == null || "".equals(uid)) {
//				response.getWriter().print("Parameters Error...");
//				return;
				uid=ClickLikeServlet.getRemoteAddress(request);
			}
			if(menuid == null || "".equals(menuid)) {
				response.getWriter().print("Parameters Error...");
				return;
			}
			
			//保存用户点击栏目记录
			RecordDAO.getInstance().saveClickMenuInfo(uid, menuid);
			
			//更新对应栏目点击数量
			MenuDAO.getInstance().updateClickNum(menuid);
			
			//获取对应栏目下商品列表
			List<ItemBean> list = ItemDAO.getInstance().GetItemList(uid, menuid);
			
			String data = "";
			if(list.size() > 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("{\"items\":[");
				for(ItemBean item : list) {
					sb.append(item.toJson()).append(",");
				}
				data = sb.toString();
				data = data.substring(0, data.length()-1);
				data = data+"]}";
			}
			response.getWriter().print(data);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
