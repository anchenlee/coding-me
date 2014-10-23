package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.bean.M_Mix_PagetypeBean;
import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.M_Mix_PagetypeCacher;
import cn.youhui.ramdata.Tag2TagCacher;
import cn.youhui.utils.RespStatusBuilder;

//没用
@WebServlet("/tyh2/tagStyleMix")
public class YHGetTagStyleMix extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String tagStyleMix = "tagStyleMix";
	private static final int pageSize = 10;
    public YHGetTagStyleMix() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String access = request.getParameter("access_token");
		String tagId = request.getParameter("tagid");
		String page = request.getParameter("page");
		String uid = request.getParameter("uid");
		if(uid == null)
			uid =""; 
		if(page==null||"".equals(page)) page = "1";
		String type = request.getParameter("type");
		if (access == null) {
			access = "";
		}
		if (tagId == null || "".equals(tagId)) {
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}		
		if(tagStyleMix.equals(type)) {
			int total = M_Mix_PagetypeCacher.getInstance().getTotalNum(tagId, pageSize);
			if(total > 0){					
					ArrayList<M_Mix_PagetypeBean> list = M_Mix_PagetypeCacher.getInstance().getProductByKeyword(tagId, pageSize, page);
				List<KeywordBean> taglist = Tag2TagCacher.getInstance().getTagsByParentIdWithAll(tagId);
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, total, Integer.parseInt(page), changetoXml1(list, taglist, uid)));
				return;
			}
		}
		else {
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
	}

	private String changetoXml1(List<M_Mix_PagetypeBean> itemlist, List<KeywordBean> taglist, String uid){
		StringBuffer sb = new StringBuffer();
		if(taglist != null && taglist.size() > 0){
			sb.append("<tags>");
			for(KeywordBean k : taglist){
				sb.append(k.toXML());
			}
			sb.append("</tags>");
		}
		if(itemlist != null && itemlist.size() > 0){
			sb.append("<itemspages>");
			for (M_Mix_PagetypeBean bean : itemlist) {
				if (bean != null) {
					sb.append(bean.toXML());
				} 
			}
			sb.append("</itemspages>");
		}
			return sb.toString();
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
