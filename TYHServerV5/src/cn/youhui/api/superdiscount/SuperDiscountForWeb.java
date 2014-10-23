package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import cn.youhui.bean.SuperDay;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.bean.SuperDiscountForWebBean;
import cn.youhui.bean.SuperForWebBean;
import cn.youhui.cacher.dao.SuperForWebCacher;
import cn.youhui.cacher.dao.superDiscountCacher;
import cn.youhui.common.ParamConfig;
import cn.youhui.dao.SuperDiscountDAO;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.SuiShouActionUtil;
import cn.youhui.utils.SuperTools;
import cn.youhui.utils.Util;

/**
 * Servlet implementation class SuperDiscountForWeb
 */
@WebServlet("/SuperDiscountForWeb")
public class SuperDiscountForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperDiscountForWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String today=SuperTools.getToday();
		String uid=ParamUtil.getParameter(request, "uid");
		List<SuperDiscountBean> list=null;
		if(uid.equals(ParamConfig.UID_OF_QIANYING)){
			list=superDiscountCacher.getInstance().getSuperDisCountListByDate(SuperTools.getTomorrow()+"");
		}else{
			list=superDiscountCacher.getInstance().getSuperDisCountListByDate(today+"");
		}
		if(list.size()==0){
			list=superDiscountCacher.getInstance().getSuperDisCountListByDate(SuperDiscountDAO.getInstance().getNextDate(today)+"");
		}
		StringBuffer ja=new StringBuffer();
		ja.append("[");
		for(int i=0;i<list.size();i++){
			SuperDiscountBean sb=list.get(i);
			
			if(!uid.equals(ParamConfig.UID_OF_QIANYING)&&sb.getIsHide()==1){
				list.remove(i);
				i--;
				continue;
			}
			
			SuperForWebBean swb=SuperForWebCacher.getSuperForWebById(sb.getId()+"");
			SuperDiscountForWebBean sdb=new SuperDiscountForWebBean();
			sdb.setId(sb.getId());
//			String img=SuperTools.getImgType(sb.getImg());
			sdb.setImgBig(swb.getBigImg());
			sdb.setImgSmall(swb.getSmallImg());
			sdb.setColor(swb.getColor());
			sdb.setIsSecondKill(sb.getIsSecondKill());
			sdb.setItemId(sb.getItemId());
			sdb.setTitle(sb.getTitle());
			sdb.setPriceLow(sb.getPriceLow());
			sdb.setPriceHigh(sb.getPriceBefore());
			Gson g=new Gson();
			
			ja.append(g.toJson(sdb).toString()+",");
		}
		ja.append("]");
		response.getWriter().print(ja.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
