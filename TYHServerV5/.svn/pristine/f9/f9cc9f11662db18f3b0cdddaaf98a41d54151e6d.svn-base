package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netting.bean.IpadTagBean;

import cn.youhui.bean.Action;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.Admin_Ipad_Tag_DAO;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuiShouIpadActionUtil;

/**
 * @category 获取所有标签
 * @author 
 * @since 
 */
@WebServlet("/tyh3/ipadalltag")
public class GetIpadAllTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetIpadAllTags() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String martixIds = Admin_Ipad_Tag_DAO.getMartixIds();
		String martixArray  [] = null;
		if (!"".equals(martixIds)) {
			martixArray = martixIds.split(",");
		}
		StringBuffer value = new StringBuffer();
		value.append("<matrix_list>");
		for (int i = 0; i < martixArray.length; i++) {
			List<IpadTagBean> tags = Admin_Ipad_Tag_DAO.getIpadTagsByMartix(martixArray[i]);
			value.append( "<matrix><pdata_list>");
			StringBuffer xml = new StringBuffer();
			for (int j = 0; j < tags.size(); j++) {
				IpadTagBean it = tags.get(j);
				if ("tagStyleGrid".equals(it.getActionType()) || "tagStyleWaterflow".equals(it.getActionType()) || "tagStyleSale".equals(it.getActionType()) ) {
					KeywordBean kb = TagCacher.getInstance().getTag(it.getActionValue());
					if (kb != null) {
						it.setActionValue(it.getActionValue() + "," + kb.getKeyword());
						it.setTag_action(new Action(it.getActionType(), it.getActionValue()));
					}
				}
				if ("goodShare".equals(it.getActionType())) {
					TeJiaGoodsBean item = TagItemCacher.getInstance().getProduct(it.getActionValue());
					if (item != null) {
						it.setActionValue(item.getClickURL() + "," + item.getPic_url() + "," + item.getKeyword() + "," + item.getTitle() + "," + it.getActionValue());
						it.setTag_action(new Action(it.getActionType(), it.getActionValue()));
					}
				}
				xml = new StringBuffer();
				SuiShouAction url = new SuiShouAction(SuiShouIpadActionUtil.getSuiShouIpadActionUrl(it.getTag_action()));
				long cur = System.currentTimeMillis();
				String showImg = it.getImg();
				if ( cur >= it.getRegularStartTime() && cur <= it.getRegularEndTime()) {
					showImg = it.getRegularTimeImg();
				}
				xml.append("<tag>")
						.append("<own>").append(0).append("</own>")
						.append("<id>").append(it.getId()).append("</id>")
						.append("<icon><![CDATA[").append(showImg).append("]]></icon>")
						.append(url.toXML())
						.append("<x_a>").append(tags.get(j).getX().substring(0, tags.get(j).getX().indexOf(","))).append("</x_a>")
						.append("<x_b>").append(tags.get(j).getX().substring(tags.get(j).getX().indexOf(",") + 1, tags.get(j).getX().length())).append("</x_b>")
						.append("<y_a>").append(tags.get(j).getY().substring(0, tags.get(j).getX().indexOf(","))).append("</y_a>")
						.append("<y_b>").append(tags.get(j).getY().substring(tags.get(j).getX().indexOf(",") + 1, tags.get(j).getX().length())).append("</y_b>")
						.append("</tag>");
						value.append(xml);
				}
				value.append("</pdata_list></matrix>");
			}
			value.append("</matrix_list>");
			response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", value.toString()).toString());
		}
}
