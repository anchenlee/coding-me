package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.youhui.bean.FrameTitle;
import cn.youhui.common.Enums;
import cn.youhui.manager.FrameTitleManager;
import cn.youhui.ramdata.FrameTitleCache;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh3/frametitles")
public class GetFrameTitles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFrameTitles() {
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String platform = request.getParameter("platform");
		String initPlatform = platform;
		if ("".equals(platform) || platform == null) {
			platform = "android";
		}
		platform = platform.toLowerCase();
		if ("android".equals(platform) || "iphone".equals(platform) || "pc".equals(platform)) {
			platform = "cell";
		}else if ("ipad".equals(platform) || "apad".equals(platform)){
			platform = "pad";
		}else {
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.NO_RESULT));
			return;
		}
		List<FrameTitle> list = FrameTitleCache.getFrameTitleList(platform);
		if (list == null || list.size() == 0) {
			if ("pad".equals(platform)) {
				list = FrameTitleManager.getIpadFrameTitleList(initPlatform);
			} else {
				list = FrameTitleManager.getFrameTitleList(initPlatform);
			}
		}
		if (list == null || list.size() == 0) {
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.NO_RESULT));
			return;
		} else {
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.NORMAL_RETURNED,listToString(list,initPlatform)));
			return;
		}

	}

	private static String listToString(List<FrameTitle> list,String platform) {
		StringBuffer sb = new StringBuffer();
		sb.append("<frame_title_list>");
		if (list != null && list.size() > 0) {
			for (FrameTitle bean : list) {
				sb.append(bean.toXML(platform));
			}
		}
		sb.append("</frame_title_list>");
		return sb.toString();
	}

	public static void main(String[] args) {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
