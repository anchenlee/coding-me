package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.GiftBean;
import cn.youhui.common.Config;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.jfbad.JFBAd;
import cn.youhui.jfbad.JFBAdManager;
import cn.youhui.utils.Base64;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * 礼物接口  访问新数据
 * @author lijun
 * @since 2014-5-8
 */
@WebServlet("/tyh2/gifts")
public class YHGetGift2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = ParamUtil.getParameter(request, "uid");
		List<JFBAd> list = JFBAdManager.getInstance().getAdListByUid(uid);
		if(list != null && list.size() > 0){
			List<GiftBean> gifts =  change(list);
			response.getWriter().print(RespStatusBuilder.messageGift(ActionStatus.NORMAL_RETURNED ,"", toResult(gifts)).toString());
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT,"").toString());
		}
	}
	
	private List<GiftBean> change(List<JFBAd> adlist){
		List<GiftBean> list = new ArrayList<GiftBean>();
		if(adlist != null && adlist.size() > 0){
			for(JFBAd ad : adlist){
				String clickUrl = Config.JFBAD_HOST + "/jfbad/index.jsp?url=" +  Base64.encode(ad.getAction().getUrl().getBytes());
				GiftBean gift = new GiftBean(ad.getAdName(), ad.getGainInfo(), ad.getImg(), ad.getIpadImg(), "tagStyleWeb", clickUrl, ad.getButton(), 0);
				list.add(gift);
			}
		}
		return list;
	}
	
	private String toResult(List<GiftBean> list){
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() >0){
			sb.append("<gifts>");
			for(GiftBean ad : list){
				sb.append(ad.toXML());
			}
			sb.append("</gifts>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
