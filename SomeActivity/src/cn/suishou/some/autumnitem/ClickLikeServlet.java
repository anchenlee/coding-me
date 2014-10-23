package cn.suishou.some.autumnitem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 点赞接口
 * @author lujiabin
 * @since 2014-9-2
 */
@WebServlet("/like")
public class ClickLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public ClickLikeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String uid = request.getParameter("uid");
			String itemid = request.getParameter("itemid");
			if(uid == null || "".equals(uid)) {
//				response.getWriter().print("Parameters Error...");
//				return;
				uid=getRemoteAddress(request);
			}
			if(itemid == null || "".equals(itemid)) {
				response.getWriter().print("Parameters Error...");
				return;
			}
			int addNum=2;
			int zanNum=ItemDAO.getInstance().getLikeNumToday(itemid)*addNum;//该商品已经被赞的量
			//超出规定点赞量直接退出
			if(tmp(zanNum, itemid)){
				response.getWriter().print("{\"num\":"+ItemDAO.getInstance().getLikeNum(itemid)+"}");
				return;
			}
			
//			int numPerDay=getNumPerDay(itemid);//平均每天需要维持的点赞量
//			System.out.println("AVGGGGGGGGGGGGGGGGGGGG::>>>>"+numPerDay+"//itemid:"+itemid+"//uid:"+uid);
//			
//			int addNum=getAvg(numPerDay, zanNum,itemid);
			
			//更新商品点击数量
			ItemDAO.getInstance().updateClickNum(itemid,addNum);
			
			//保存用户点击商品记录
			RecordDAO.getInstance().saveClickLikeInfo(uid, itemid);
			
			//获取当前赞数
			int num = ItemDAO.getInstance().getLikeNum(itemid);
			
			response.getWriter().print("{\"num\":"+num+"}");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public int getNumPerDay(String itemid){
		int numPerDay=4600;
		if(itemid.equals("40676676113")||itemid.equals("40511583184")||itemid.equals("40643382225")||itemid.equals("40560256190")){
			numPerDay=7000;
		}else if(itemid.equals("40716872437")||itemid.equals("40271896350")||itemid.equals("39888147980")){
			numPerDay=6000;
		}else if(itemid.equals("35166580766")||itemid.equals("3599278492")||itemid.equals("19009934980")||itemid.equals("36639060344")){
			numPerDay=4000;
		}
		return numPerDay;
	}
	public boolean tmp(int zanNum,String itemid){
		boolean bac=false;
		if(itemid.equals("40676676113")||itemid.equals("40511583184")||itemid.equals("40643382225")||itemid.equals("40560256190")){
			if(zanNum>=7000){
				bac=true;
			}
		}else if(itemid.equals("40716872437")||itemid.equals("40271896350")||itemid.equals("39888147980")){
			if(zanNum>=6000){
				bac=true;
			}
		}else if(itemid.equals("35166580766")||itemid.equals("3599278492")||itemid.equals("19009934980")||itemid.equals("36639060344")){
			if(zanNum>=4000){
				bac=true;
			}
		}else{
			if(zanNum>=4600){
				bac=true;
			}
		}
		return bac;
	}
	public int getAvg(int numPerDay,int zanNum,String itemid){
		int avg=200;//每小时需要维持的点击量
		try{
			long now=new Date().getTime();
			long time=sdf.parse(sdf.format(now)).getTime()+86400000;//明天
			int hour = (int) (time - now)/3600000;
			//四舍五入
			if((time-now)%3600000 > 1800000){
				hour++;
			}
			if((numPerDay-zanNum)%hour==0){
				avg=(numPerDay-zanNum)/hour;
			}else{
				avg=(numPerDay-zanNum)/hour+1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		int userNumPerHour=RecordDAO.getInstance().getUserNum(itemid);
		int num=1;
		if(avg%userNumPerHour==0){
			num=avg/userNumPerHour;
		}else{
			num=avg/userNumPerHour+1;
		}
		return num;
	}
	  public static  String getRemoteAddress(HttpServletRequest request) {
	        String ip = request.getHeader("x-forwarded-for");
	        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	            ip = request.getRemoteAddr();
	        }
	        return ip;
	    }
}
