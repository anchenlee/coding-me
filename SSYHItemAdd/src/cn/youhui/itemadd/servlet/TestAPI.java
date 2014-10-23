package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.admin.dao.AddItemUtil;
import cn.youhui.admin.dao.AdminTagDAO;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.bean.TagBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.cache.dao.superDiscountCacher;
import cn.youhui.itemDAO.SuperDiscountDAO;
import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.platform.config.Config;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.util.NetManager;
import cn.youhui.tools.Tools;

/**
 * Servlet implementation class TestAPI
 */
@WebServlet("/TestAPI")
public class TestAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=SQL.getInstance().getConnection();
		try{
			if(request.getParameter("fuck").equals("1")){
				String disCount_date =request.getParameter("discount_date");
				if(disCount_date==null){
					disCount_date=new Date().getTime()+"";
				}
				SimpleDateFormat sdf3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String start_time=sdf3.format(Long.parseLong(disCount_date));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2=new SimpleDateFormat("MM.dd");
				String keyword=sdf2.format(Long.parseLong(disCount_date));
				if(keyword.substring(0,1).equals("0")){
					keyword=keyword.substring(1,keyword.length());
				}
				keyword="超级惠"+keyword;
				disCount_date=sdf.parse(sdf.format(Long.parseLong(disCount_date))).getTime()+"";
				System.out.println(disCount_date+":::"+keyword);
				String content="parent_id=538&tag_name="+keyword+"&description="+keyword+"&bgcolor=99ffffff&jfb_rate=2&sex=0&small_show=zk&status=2&isPadTag=0&heat=%E5%AE%98%E6%96%B9&action_type=tagStyleGrid&action_value=default&phone_icon=&pad_icon=&chaye_action_type=&chaye_action_value=&chaye_icon=http://static.etouch.cn/suishou/ad_img/hn3d52mthk.jpg&chaye_icon_size=2&tongbu=0&position=&start_time="+start_time;
				System.out.println("ttttttt::::::::::::"+NetManager.send(param.adTagUrl, content));
				List<SuperDiscountBean> list=superDiscountCacher.getInstance().getSuperDisCountListByDate(disCount_date);
				for(SuperDiscountBean sb:list){
					TeJiaGoodsBean tjBean =new TeJiaGoodsBean();
					tjBean.setItem_id(sb.getItemId());
					tjBean.setTitle(sb.getTitle());
					tjBean.setRate(0);
					tjBean.setRecoReason("");
					tjBean.setPic_url(sb.getImg());
					tjBean.setPrice_high(sb.getPriceBefore()+"");
					tjBean.setPrice_low(sb.getPriceLow()+"");
					tjBean.setClickURL("a.m.taobao.com/i"+sb.getItemId()+".html");
					tjBean.setPicCutUrl(sb.getImg());
					tjBean.setCatID("");
					tjBean.setBaoyou(sb.getPostage());
					TagBean tagBean = AdminTagDAO.getTagBeanByKeyword(keyword);
					AddItemUtil.addTagItem(tagBean, tjBean, conn);
				}
			}else{
				List<String[]> list=SuperDiscountDAO.getInstance().gettagIds();
				for(int i=0;i<list.size();i++){
					String[] s=list.get(i);
					String tagId=s[0];
					String date=s[1];
					List<String> listItem=SuperDiscountDAO.getInstance().xxx(Integer.parseInt(tagId));
					for(int j=0;j<listItem.size();j++){
						String itemId=listItem.get(j);
						SuperDiscountBean sb=SuperDiscountDAO.getInstance().fck(itemId,Long.parseLong(date));
						sb.setRank(SuperDiscountDAO.getInstance().maxRank(Long.parseLong(date))+1);
						int id=SuperDiscountDAO.getInstance().insertInfo(sb, conn);
						sb.setId(id);
						if (id!=-1) {
							superDiscountCacher.getInstance().addSuperDiscount(String.valueOf(id), sb);
							response.getWriter().write("success");
						}else
						{
							response.getWriter().write("false");
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
