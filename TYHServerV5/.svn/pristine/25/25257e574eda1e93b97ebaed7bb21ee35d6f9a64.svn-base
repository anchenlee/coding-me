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
import cn.youhui.ramdata.AllIpadTagCacher;
import cn.youhui.ramdata.AllTagCacher;
import cn.youhui.ramdata.UserTagCacher;
import cn.youhui.ramdata.UserTagInIpadCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 获取所有标签
 * @author leejun
 * @since 2012-11-19
 */
@WebServlet("/tyh3/alltag")
public class YHGetAllTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String localLifeTagId = "688";
       
    public YHGetAllTags() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String accessToken = request.getParameter("access_token");
		String platform = request.getParameter("platform");
		String versionCode = request.getParameter("version_code");
		accessToken = accessToken == null ? "" : accessToken;
		uid = uid == null ? "" : uid;
		if("ipad".equalsIgnoreCase(platform)){
			String tagIds = UserTagInIpadCacher.getInstance().getTagIds(uid);
			if(tagIds == null) tagIds = "";
			List<KeyCategoryBean> klist = AllIpadTagCacher.getInstance().getTagList(); 
			if(klist == null || klist.size() == 0){
				AllIpadTagCacher.getInstance().reload();
				klist = AllIpadTagCacher.getInstance().getTagList(); 
			}
			if(klist == null || klist.size() == 0){
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
				return;
			}else{
				StringBuffer re = new StringBuffer();
				re.append("<categorys>");
				for(KeyCategoryBean kc : klist){
					for(KeywordBean kw : kc.getList()){
//	.
						adduid(kw, uid);
						if(tagIds.contains(kw.getId())){
							kw.setOwn(1);
						}
						if(localLifeTagId.equals(kw.getId()) && !isNewVersion(platform, versionCode)){
							kw.setShowStyle(2);
						}
					}
					re.append(kc.toXMLNew(platform));
				}
				re.append("</categorys>");
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, accessToken, re.toString()).toString());
			}       
		}else{
			String tagIds = UserTagCacher.getInstance().getTagIds(uid);
			if(tagIds == null) 
			{
				tagIds = "";
			}
			List<KeyCategoryBean> klist = AllTagCacher.getInstance().getTagList(); 
			if(klist == null || klist.size() == 0)
			{
				AllTagCacher.getInstance().reload();
				klist = AllTagCacher.getInstance().getTagList();
			}
			if(klist == null || klist.size() == 0)
			{
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.DATABASE_ERROR).toString());
				return;
			}
			else
			{
				StringBuffer re = new StringBuffer();
				re.append("<categorys>");
				for(KeyCategoryBean kc : klist){
					for(KeywordBean kw : kc.getList()){
//						adduid(kw, uid);
						if(tagIds.contains(kw.getId())){
							kw.setOwn(1);
						}
						if(localLifeTagId.equals(kw.getId()) && !isNewVersion(platform, versionCode)){
							kw.setShowStyle(2);
						}
						if("752".equals(kw.getId()) && !"android".equalsIgnoreCase(platform)){
							kw.setShowStyle(2);
						}
					}
					re.append(kc.toXMLNew(platform));
				}
				re.append("</categorys>");
				response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, accessToken, re.toString()).toString());
			} 
		}
	}
	
	static boolean isNewVersion(String platform, String versionCode){
		boolean flag = false;
		try{
			if("android".equalsIgnoreCase(platform)){
				int version = Integer.parseInt(versionCode);
				if(version >= 34){
					flag = true;
				}
			}else if("iphone".equalsIgnoreCase(platform)){
				int version = Integer.parseInt(versionCode);
				if(version >= 19){
					flag = true;
				}
			}
		}catch(Exception e){
		}
		return flag;
	}

    public static String get(String platform){
    	String tagIds = UserTagCacher.getInstance().getTagIds(null);
    	StringBuffer re = new StringBuffer();
		re.append("<categorys>");
		List<KeyCategoryBean> klist = AllTagCacher.getInstance().getTagList(); 
		for(KeyCategoryBean kc : klist){
			for(KeywordBean kw : kc.getList()){
				if(tagIds.contains(kw.getId()))
					kw.setOwn(1);
			}
			re.append(kc.toXMLNew(platform));
		}
		re.append("</categorys>");
		return re.toString();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 为话费充值增加unid参数，便于充话费返集分宝
	 */
	private static void adduid(KeywordBean tag, String uid){
		if(tag != null && tag.getAction() != null && tag.getAction().getActionValue().indexOf("http:") > -1){
			if( tag.getAction().getActionValue().indexOf("?") > -1){
				tag.getAction().setActionValue(tag.getAction().getActionValue()+ "&unid=" + uid);
			}else{
				tag.getAction().setActionValue(tag.getAction().getActionValue()+ "?unid=" + uid);
			}
		}
	}

}
