package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.youhui.bean.FixedTagBean;
import cn.youhui.bean.ProfRecom;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.ramdata.ProfRecomCacher;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuiShouActionUtil;

/**
 * 获取首页推荐列表
 * @author lijun
 * @since 2014-02-20
 */
@WebServlet("/tyh3/profrecomd")
public class GetProfRecomd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String format = ParamUtil.getParameter(request, "format");
			int page = ParamUtil.getParameterInt(request, "page", 1);
			long lastTime = ParamUtil.getParameterLong(request, "last_time");
			String platform = ParamUtil.getParameter(request, "platform");
			long now = 0;
			int findNum = 0;
			if(page == 1){
				now = System.currentTimeMillis();
				if(lastTime != 0){
					findNum = ProfRecomCacher.getInstance().getUpNum(lastTime, now);
					if(findNum > 60){
						findNum = 60;
					}
				}
				lastTime = now;
			}else{
				now = lastTime;
			}
			List<ProfRecom> list = ProfRecomCacher.getInstance().getList(lastTime, page ,platform);
			
			if(page == 1){
				ProfRecom recom = new ProfRecom();
				recom.setIsItem(0);
				recom.setImgSize("270x270");
				long s = System.currentTimeMillis();
				int i = (int)((s/1000000)%10000);
				recom.setFavNum(i);
				if (platform.equals("ipad")) {
					recom.setItemImg("http://static.etouch.cn/suishou/ad_img/27tn3nw2ktt.png");
				}else{
					recom.setItemImg("http://static.etouch.cn/suishou/item_img/chaojihuiicon.jpg");
				}
				recom.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform, "tagStyleWeb", "http://b17.cn/Xp8I44")));
//				recom.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform, "tagStyleWeb", "http://sandbox.api.njnetting.cn/SCheap/index.html")));
				
				list.add(0, recom);
				FixedTagBean fb=getFixedTag();
				if(fb!=null&&fb.getTitle()!=null&&!"".equals(fb.getTitle())){
					ProfRecom recom2=new ProfRecom();
					recom2.setIsItem(0);
					recom2.setImgSize("270x270");
					long s2 = System.currentTimeMillis();
					int i2 = (int)((s2/1000000)%10000);
					recom2.setFavNum(i2);
					recom2.setItemImg(fb.getImg()+"fixedtag");
					recom2.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform, "tagStyleGrid", fb.getId(),fb.getTitle())));
					list.add(1, recom2);
				}
				
			}
			
			int total = ProfRecomCacher.getInstance().getTotalPage(lastTime);
			
			if("json".equalsIgnoreCase(format)){
				if(list != null && list.size() > 0){
					response.getWriter().print(RespStatusBuilder.messageJson(ActionStatus.NORMAL_RETURNED , "", total, page ,toJsonResult(list, now, findNum)).toString());
				}else{
					response.getWriter().print(RespStatusBuilder.messageJson(ActionStatus.NO_RESULT).toString());
				}
			}else{
				if(list != null && list.size() > 0){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED , "", total, page ,toResult(list, now, findNum)).toString());
				}else{
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
				}
			}
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}
	
	
	public static FixedTagBean getFixedTag(){
		
		String str = new JedisHashManager("fixedtag").get( "value");
		FixedTagBean fixedTag = null;
		Gson gs = new Gson();
		fixedTag = gs.fromJson(str, FixedTagBean.class);
		return fixedTag;
	}

	private String toJsonResult(List<ProfRecom> list, long now, int findNum){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"time\":" + now + ",");
		sb.append("\"find_num\":" + findNum + ",");
		if(list != null && list.size() > 0){
			sb.append("\"prof_recoms\":[");
			for(ProfRecom bean : list){
				sb.append(bean.toJson() + ",");
			}
			sb = new StringBuffer(sb.substring(0, sb.length() -1));
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}
	
	private String toResult(List<ProfRecom> list, long now, int findNum){
		StringBuffer sb = new StringBuffer();
		sb.append("<time>" + now + "</time>");
		sb.append("<find_num>" + findNum + "</find_num>");
		if(list != null && list.size() > 0){
			sb.append("<prof_recoms>");
			for(ProfRecom bean : list){
				sb.append(bean.toXML());
			}
			sb.append("</prof_recoms>");
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		int i = (int)((s/1000000)%10000);
		System.out.println(i);
	}

}
