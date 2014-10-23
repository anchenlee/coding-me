package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.GiftManager;
import cn.youhui.ramdata.UserTagCacher;
import cn.youhui.ramdata.UserTagInIpadCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 获取用户标签
 * @author leejun
 * @since 2012-11-19
 */
@WebServlet("/tyh2/usertag")
public class YHGetUserTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHGetUserTag() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String versionCode = request.getParameter("version_code");
		String accessToken = request.getParameter("access_token");
		accessToken = accessToken == null ? "" : accessToken;
		if("ipad".equalsIgnoreCase(platform)){
			List<KeywordBean> ret = UserTagInIpadCacher.getInstance().getTags(uid);
			if(ret == null || ret.size() == 0){
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
				return;
			}else{
				StringBuffer re = new StringBuffer();
				re.append("<tags>");
				for(KeywordBean k : ret){
					k.setOwn(1);
					if(YHGetAllTags.localLifeTagId.equals(k.getId()) && !YHGetAllTags.isNewVersion(platform, versionCode)){
						continue;
					}
					re.append(k.toXML());
				}
				re.append("</tags>");
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, accessToken, re.toString()).toString());
			}       
		}else{
			List<KeywordBean> ret = UserTagCacher.getInstance().getTags(uid);
			if(ret == null || ret.size() == 0){
				UserTagCacher.getInstance().reload();
				ret = UserTagCacher.getInstance().getTags(uid);
			}
			if(ret == null || ret.size() == 0){
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
				return;
			}else{
				StringBuffer re = new StringBuffer();
				re.append("<tags>");
				re.append("<gift_num>"+GiftManager.getInstance().getSize()+"</gift_num>");
				for(KeywordBean k : ret){
					k.setOwn(1);
					if(YHGetAllTags.localLifeTagId.equals(k.getId()) && !YHGetAllTags.isNewVersion(platform, versionCode)){
						continue;
					}
					if("752".equals(k.getId()) && !"android".equalsIgnoreCase(platform)){
						continue;
					}
					re.append(k.toXML());
				}
				re.append("</tags>");
				re.append("<tagheat>").append(UserTagCacher.getInstance().getTagStatus()).append("</tagheat>");
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, accessToken, re.toString()).toString());
			}      
		}
	}

	public static String test(String uid){
		List<KeywordBean> ret = UserTagCacher.getInstance().getTags(uid);
		StringBuffer re = new StringBuffer();
		re.append("<tags>");
		for(KeywordBean k : ret){
			re.append(k.toXML());
		}
		re.append("</tags>");
		return RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, "", re.toString()).toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
