package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.UserTagCacher;
import cn.youhui.ramdata.UserTagInIpadCacher;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @category 更新用户标签
 * @author leejun
 * @since 2012-11-19
 */
@WebServlet("/tyh2/updateusertag")
public class YHUpdateUserTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHUpdateUserTags() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String tagIds = request.getParameter("tagids");
		String platform = request.getParameter("platform");
		String accessToken = request.getParameter("access_token");
		String type = request.getParameter("type");
		accessToken = accessToken == null ? "" : accessToken;
		if(uid == null || tagIds == null || !test(tagIds)){
			response.getWriter().write(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
			return;
		}
		boolean b = false;
		if("ipad".equalsIgnoreCase(platform)){
			if("add".equals(type)){
				if(TagCacher.getInstance().isexistTag(tagIds)){
				    b = UserTagInIpadCacher.getInstance().addTagId(uid, tagIds);
				}
			}else{
				if(TagCacher.getInstance().isRightTagIds(tagIds)){
					b = UserTagInIpadCacher.getInstance().setTagIds(uid, tagIds);
				}
			}
		}else{
			if("add".equals(type)){
				if(TagCacher.getInstance().isexistTag(tagIds)){
				    b = UserTagCacher.getInstance().addTagId(uid, tagIds);		
				}
			}else{
				if(TagCacher.getInstance().isRightTagIds(tagIds)){
					b = UserTagCacher.getInstance().setTagIds(uid, tagIds);
				}
			}
		}
		if(b == true)
			response.getWriter().write(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED).toString());
		else  response.getWriter().write(RespStatusBuilder.message(ActionStatus.SERVER_ERROR).toString());
	}

	private static boolean test(String tagIds){
		 boolean ret = false;
		 Pattern p = Pattern.compile("(\\d|;)*");
		 Matcher m = p.matcher(tagIds);
		 ret = m.matches();
		 return ret;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
