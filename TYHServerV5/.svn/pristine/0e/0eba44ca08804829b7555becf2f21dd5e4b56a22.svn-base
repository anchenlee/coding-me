package cn.youhui.api.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.Action;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.UserTagCacher;
import cn.youhui.ramdata.UserTagInIpadCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 获取用户标签
 * @author leejun
 * @since 2012-11-19
 */
@WebServlet("/tyh/usertag")
public class YHGetUserTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String tagStylelList = "tagStyleList";
	private static final String tagStyleWaterflow = "tagStyleWaterflow";
	private static final String tagStyleGrid = "tagStyleGrid";
	private static final String tagStyleMix = "tagStyleMix";
	private static final String tagStyleWeb = "tagStyleWeb";
	private static final String systemWeb = "systemWeb";
	private static final String tagStyleCategory = "tagStyleCategory";
	
    public YHGetUserTag() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
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
					if (null == k.getAction() 
							|| k.getAction().getActionType() == null || k.getAction().getActionValue() == null)
					{
						k.setAction(new Action("", ""));
					}
					if(tagStyleCategory.equals(k.getAction().getActionType()))
						k.getAction().setActionType(tagStylelList);
					else if(systemWeb.equals(k.getAction().getActionType()))
					    k.getAction().setActionType(tagStyleWeb);
					if(k.getAction().getActionValue().equals(k.getId()))
					    k.getAction().setActionValue(k.getKeyword()); 
					if(cn.youhui.api.servlet2.YHGetAllTags.localLifeTagId.equals(k.getId())){
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
				for(KeywordBean k : ret){
					k.setOwn(1);
					if (null == k.getAction() 
							|| k.getAction().getActionType() == null || k.getAction().getActionValue() == null)
					{
						k.setAction(new Action("", ""));
					}
					
					if(tagStyleCategory.equals(k.getAction().getActionType()) || k.getId().equals("609") || cn.youhui.api.servlet2.YHGetAllTags.localLifeTagId.equals(k.getId()))
					{
						continue;
					}
					else if(systemWeb.equals(k.getAction().getActionType()))
				    {
						k.getAction().setActionType(tagStyleWeb);
				    }
					if(k.getAction().getActionValue().equals(k.getId()))
				    {
						k.getAction().setActionValue(k.getKeyword());   //后期将actionvalue改为tagid,为了兼容老版本，做此修改
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
