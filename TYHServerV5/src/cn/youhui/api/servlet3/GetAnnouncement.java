package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.Announcement;
import cn.youhui.common.Enums;
import cn.youhui.manager.AnnouncementManager;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh3/getannouncement")
public class GetAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAnnouncement() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<Announcement> list = AnnouncementManager.getAnnouncementList(1, 0);
		if(list == null || list.size() == 0){
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.NO_RESULT));
			return;
		}else{
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.NORMAL_RETURNED,listToString(list)));
			return;
		}
	}

	private static String listToString(List<Announcement> list){
		StringBuffer sb = new StringBuffer();
		sb.append("<Announcement_list>");
		if(list != null && list.size() > 0){
			for(Announcement bean : list){
				sb.append(bean.toXML());
			}
		}
		sb.append("</Announcement_list>");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
