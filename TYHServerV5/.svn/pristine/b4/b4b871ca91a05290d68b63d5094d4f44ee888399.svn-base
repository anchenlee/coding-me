package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.KeyCategoryBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.AllTagCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh3/moretags")
public class GetMoreTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
    public GetMoreTags() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		List<KeyCategoryBean> klist = AllTagCacher.getInstance().getTagList(); 
		StringBuffer ret = new StringBuffer();
		String platform = request.getParameter("platform");
		ret.append("<tags>");
		String moreTagIds [] = {"591","832","831"};
		for (int i = 0; i < moreTagIds.length; i++) {
			KeywordBean kb = TagCacher.getInstance().getTag(moreTagIds[i]);
			if("ipad".equals(platform) || "apad".equals(platform)){
					if("591".equals(kb.getId())){
						kb.setIcon("http://static.etouch.cn/suishou/ad_img/13oxj4mjoq5.png");//http://static.etouch.cn/suishou/ad_img/13owoazr6ad.png//买家必读
					}else if("832".equals(kb.getId())){
						kb.setIcon("http://static.etouch.cn/suishou/ad_img/13owoazr6ad.png");//http://static.etouch.cn/suishou/ad_img/13oxa1ly125.png//订单领取
					}else if("831".equals(kb.getId())){
						kb.setIcon("http://static.etouch.cn/suishou/ad_img/13oxa1ly125.png");//http://static.etouch.cn/suishou/ad_img/13oxj4mjoq5.png//充值中心
					}
			}else{
				if("591".equals(kb.getId())){
					kb.setIcon("http://static.etouch.cn/suishou/ad_img/2b7ymyfxbo1.png");
				}else if("832".equals(kb.getId())){
					kb.setIcon("http://static.etouch.cn/suishou/ad_img/1rxfkutfm12.png");
				}else if("831".equals(kb.getId())){
					kb.setIcon("http://static.etouch.cn/suishou/ad_img/1rxfq05k3by.png");
				}
			}
			ret.append(kb.toXMLNew(platform));
		}
		ret.append("</tags>");
//		for(KeyCategoryBean kc : klist){
//				re.append(kc.toMoreXML());
//		}
		response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", ret.toString()).toString());
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
