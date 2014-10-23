package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;


import cn.youhui.admin.dao.AdminTagItemDAO;
import cn.youhui.bean.SecondKill;
import cn.youhui.bean.SuperCouponBean;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.bean.SuperForWebBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.cache.dao.SuperCouponCacher;
import cn.youhui.cache.dao.SuperForWebCacher;
import cn.youhui.cache.dao.TagItemCacheDAO;
import cn.youhui.cache.dao.secondKillCacher;
import cn.youhui.cache.dao.superDiscountCacher;
import cn.youhui.itemDAO.AdminAssessDAO;
import cn.youhui.itemDAO.SecondKillDAO;
import cn.youhui.itemDAO.SuperCouponDAO;
import cn.youhui.itemDAO.SuperDiscountDAO;
import cn.youhui.itemDAO.SuperForWebDAO;
import cn.youhui.itemDAO.UpdateAliyunItemJob;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.util.NetManager;


@WebServlet("/super/superdiscount")
public class SuperDiscountAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SuperDiscountAction() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		
		String method = request.getParameter("method");
		if("getInfo".equals(method)){
			String str=getInfo(request,response);
			
			if("".equals(str)){
				response.getWriter().print("empty");
			}else{
				response.getWriter().print(str);
				System.out.println(str);
			}
		}else if("insertInfo".equals(method)){
			try {
				insertInfo(request,response);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else if("updateInfo".equals(method)){
			try {
				
				String info = request.getParameter("info");
				JSONObject jo = new JSONObject(info);
				
				String id = jo.getString("id");
				//网页端轮播
				updateForWeb(request.getParameter("super_web"),id);
				
				if(jo.has("is_second_kill")&&jo.getInt("is_second_kill")==1){
					updateInfo2(request,response,jo);
				}else if(jo.has("is_second_kill")&&jo.getInt("is_second_kill")==0){
					updateInfo(request,response,jo);
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else if("getInfoById".equals(method)){
				try {
					getInfoById(request,response);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		}else if("del".equals(method)){
			try {
				del(request,response);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else if("delyhq".equals(method)){
			String id=request.getParameter("id");
			String itemId=request.getParameter("item_id");
			if(id==null||"".equals(id)||itemId==null||"".equals(itemId)){
				response.getWriter().print("paramException!");
				return;
			}
			System.out.println(id+"//"+itemId);
			SuperCouponDAO.getInstance().del(Integer.parseInt(id));
			SuperCouponCacher.getInstance().del(itemId);
			response.getWriter().print("success");
		}else if("addyhq".equals(method)){
			String super_coupon_address=request.getParameter("yhq_url");
			String item_id = request.getParameter("item_id");
			String title = request.getParameter("title");
			String yhqTitle=request.getParameter("yhq_title");
			
			SuperCouponBean scb=new SuperCouponBean();
			scb.setYhqTitle(yhqTitle);
			scb.setCouponAddress(super_coupon_address);
			scb.setItemId(item_id);
			scb.setItemTitle(title);
			int id=SuperCouponDAO.getInstance().add(scb);
			scb.setId(id);
			SuperCouponCacher.getInstance().insertInfo(item_id, scb);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private String getInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String pagNow="\"\"";
		String date="";
		
		Connection conn = SQL.getInstance().getConnection();
		
		long la=System.currentTimeMillis();
		String  pages=SuperDiscountDAO.getInstance().getPages(conn);
		long lb=System.currentTimeMillis();
		
		if(!pages.equals("")){
			pagNow=pages.split(",")[0];
		}
		if(request.getParameter("pagNow")!=null&&!"".equals(request.getParameter("pagNow"))){
			pagNow=request.getParameter("pagNow");
		}
		StringBuffer jsonlist=new StringBuffer();
		jsonlist.append("{\"totaldates\":["+pages+"],\"date_now\":"+pagNow+",");
			pagNow=pagNow.replaceAll("\"", "");
			
			long lc=System.currentTimeMillis();
			
			List<SuperDiscountBean> list=SuperDiscountDAO.getInstance().getInfo(conn,pagNow);
			long ld=System.currentTimeMillis();
			
			jsonlist.append("\"items\":[");
			
			for(int j=0;j<list.size();j++){
				date=dat3(list.get(j).getDiscountTimestamp());
				if(j==list.size()-1){
					int count = AdminAssessDAO.getAssessCountByItemIdAndDate(list.get(j).getItemId(), String.valueOf(list.get(j).getDiscountTimestamp()),conn);
					String value = list.get(j).toJson(conn);
					jsonlist.append(value.substring(0, value.length() - 1) + ",\"assess_count\":\"" + String.valueOf(count) + "\"}");
				}else{
					int count = AdminAssessDAO.getAssessCountByItemIdAndDate(list.get(j).getItemId(), String.valueOf(list.get(j).getDiscountTimestamp()),conn);
					String value = list.get(j).toJson(conn);
					jsonlist.append(value.substring(0, value.length() - 1) + ",\"assess_count\":\"" + String.valueOf(count) + "\"},");
				}
			}
			
			long le=System.currentTimeMillis();
			
			jsonlist.append("],");
			jsonlist.append("\"date\":\""+date+"\"");
		jsonlist.append("}");
		
		SQL.getInstance().release(null,conn);
		
		System.out.println("lb-la"+(lb-la));
		System.out.println("lc-lb"+(lc-lb));
		System.out.println("ld-lc"+(ld-lc));
		System.out.println("le-ld"+(le-ld));
		return jsonlist.toString();
	}
	
	private void insertInfo(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String info = request.getParameter("info");
		JSONObject jo = new JSONObject(info);
		String isHide=jo.getString("is_hide");
		String item_id = jo.getString("item_id");
		String title = jo.getString("title");
		String img = jo.getString("img");
		String price_before = jo.getString("price_before");
		String price_low = jo.getString("price_low");
		String num = jo.getString("num");
		String postage = jo.getString("postage");
//		String discount_timestamp = jo.getString("discount_date");
		String discount_date = jo.getString("discount_date");
		String start_timestamp = jo.getString("start_timestamp");
		String end_timestamp = jo.getString("end_timestamp");
//		String sold_out = jo.getString("sold_out");
		String isSecondKill=jo.getString("is_second_kill");
		String itemTags=jo.getString("item_tags");
		int taoJb=jo.getInt("tao_jb");
		String super_coupon_address=jo.getString("yhq_url");
		String yhqTitle=jo.getString("yhq_title");
		
//		int taoJb=0;
		
				
		boolean flag = true;
		if( item_id == null || title == null || img == null || price_before == null || price_low == null
				|| num == null || postage == null || discount_date == null ||
				start_timestamp == null || end_timestamp == null)
		{
			flag = false;
		}
		
		
		
		Connection conn = SQL.getInstance().getConnection();
		if (flag) {
			long discountTimestamp=dat2(discount_date);
			SuperDiscountBean sd = new SuperDiscountBean();
			sd.setIsHide(Integer.parseInt(isHide));
			sd.setIsSecondKill(Integer.parseInt(isSecondKill));
			sd.setRank(SuperDiscountDAO.getInstance().maxRank(discountTimestamp)+1);//设置排序rank
			sd.setItemId(item_id.replaceAll(" ", ""));//去空格
			sd.setTitle(title);
			sd.setImg(img);
			sd.setPriceBefore(Double.parseDouble(price_before));
			sd.setPriceLow(Double.parseDouble(price_low));
			sd.setNum(Integer.parseInt(num));
			sd.setPostage(Integer.parseInt(postage));
			sd.setDiscountTimestamp(discountTimestamp);
			sd.setAddTimestamp(System.currentTimeMillis());
			sd.setDiscountDate(dat(discountTimestamp));
			sd.setStartTimestamp(dat2(start_timestamp));
			sd.setEndTimestamp(dat2(end_timestamp));
			sd.setRemainNum(Integer.parseInt(num));
			sd.setItemTags(itemTags);
			sd.setTaoJb(taoJb);
			
			if(isSecondKill!=null&&"1".equals(isSecondKill)){
				String killNum=jo.getString("kill_num");
				String killStartTimestamp=jo.getString("kill_start_timestamp");
				String killEndTimestamp=jo.getString("kill_end_timestamp");
				String killPrice=jo.getString("kill_price");
				
				
				int id=SuperDiscountDAO.getInstance().insertInfo(sd, conn);
				
				
				sd.setId(id);
				if (id!=-1) {
					
					//同步商品
					UpdateAliyunItemJob.updateItem(item_id, img, price_low, price_before, title);
					
					insertForWeb(id,request.getParameter("super_web"));
					
					superDiscountCacher.getInstance().addSuperDiscount(String.valueOf(id), sd);
					
					SecondKill sk=new SecondKill();
					sk.setKillNum(Integer.parseInt(killNum));
					sk.setKillStartTimestamp(dat2(killStartTimestamp));
					sk.setKillEndTimestamp(dat2(killEndTimestamp));
					sk.setKillPrice(Integer.parseInt(killPrice));
					sk.setType("100");
					sk.setTypeId(id);
					sk.setRemainNum(Integer.parseInt(killNum));
					
					SecondKillDAO.getInstance().insertInfo(sk);
					
					secondKillCacher.getInstance().addSecondKill(param.secondkilltype+String.valueOf(id), sk);
					
					String str = NetManager.getInstance().send(param.addOederUrl,"pid="+item_id+"&couponid="+sk.getType()+sk.getTypeId()+"&title="+title+"&img="+img+"&tag=2&starttime="+sk.getKillStartTimestamp()+"&endtime="+sk.getKillEndTimestamp()+"&price="+sk.getKillPrice()+"&stock="+sk.getKillNum()+"&img="+img+"&desc=");
					if(str.equals("true")){
						response.getWriter().write("success");
					}else{
						response.getWriter().write("false2");
					}
				}else
				{
					response.getWriter().write("false");
				}
			}else{
				int id=SuperDiscountDAO.getInstance().insertInfo(sd, conn);
				sd.setId(id);
				if (id!=-1) {
					
					//同步商品
					UpdateAliyunItemJob.updateItem(item_id, img, price_low, price_before, title);
					
					insertForWeb(id, request.getParameter("super_web"));
					
					superDiscountCacher.getInstance().addSuperDiscount(String.valueOf(id), sd);
					response.getWriter().write("success");
				}else
				{
					response.getWriter().write("false");
				}
			}
			
			
			addyhq(super_coupon_address, item_id, title,yhqTitle);
			
		}else
		{
			response.getWriter().write("paramException");
		}
		SQL.getInstance().release(null,conn);
	}
	
	//优惠券添加
	public void addyhq(String super_coupon_address,String item_id,String title,String yhqTitle){
		if(super_coupon_address!=null&&!"".equals(super_coupon_address)){
			SuperCouponBean scb=new SuperCouponBean();
			scb.setCouponAddress(super_coupon_address);
			scb.setItemId(item_id);
			scb.setItemTitle(title);
			scb.setTimestamp(new Date().getTime());
			scb.setYhqTitle(yhqTitle);
			scb.setId(SuperCouponDAO.getInstance().add(scb));
			SuperCouponCacher.getInstance().insertInfo(item_id, scb);
		}
	}
	
	//网页端的轮播参数
	public void insertForWeb(int superId,String dat){
		String data=dat;
		Gson g=new Gson();
		SuperForWebBean swb=g.fromJson(data,SuperForWebBean.class);
		swb.setSuperId(superId);
		int id2=SuperForWebDAO.getInstance().insertInfo(swb);
		if(id2!=-1){
			SuperForWebCacher.addSuperForWeb(superId+"", swb);
		}
	}
	public void updateForWeb(String dat,String superId){
		String data=dat;
		Gson g=new Gson();
		SuperForWebBean swb=g.fromJson(data,SuperForWebBean.class);
		if(SuperForWebDAO.getInstance().ifExist(swb.getId())){
			if(SuperForWebDAO.getInstance().updateInfo(swb)){
				SuperForWebCacher.updateSuperForWeb(superId+"", swb);
			}
		}else{
			if(SuperForWebDAO.getInstance().insertInfo(swb)!=-1){
				SuperForWebCacher.addSuperForWeb(superId, swb);
			}
		}
	}
	
	private void updateInfo(HttpServletRequest request, HttpServletResponse response,JSONObject jo) throws JSONException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String isHide=jo.getString("is_hide");
		String id = jo.getString("id");
		String item_id = jo.getString("item_id");
		String title = jo.getString("title");
		String img = jo.getString("img");
		String price_before = jo.getString("price_before");
		String price_low = jo.getString("price_low");
		String num = jo.getString("num");
		String postage = jo.getString("postage");
//		long discountTimestamp = dat2(jo.getString("discount_timestamp"));
//		String discountDate = dat(dat2(jo.getString("discount_timestamp")));
		String start_timestamp = jo.getString("start_timestamp");
		String end_timestamp = jo.getString("end_timestamp");
		int sold_out=0;
		String itemTags=jo.getString("item_tags");
		String isSecondKill=jo.getString("is_second_kill");
		String remainNum=jo.getString("remain_num");
		String super_coupon_address=jo.getString("yhq_url");
		String yhq_title=jo.getString("yhq_title");
		String yhq_id=jo.getString("yhq_id");
//		int taoJb=jo.getInt("tao_jb");
		int taoJb=0;
		if(remainNum.equals("0")){
			sold_out=1;
		}
		
		boolean flag = true;
		if(id == null || item_id == null || title == null || img == null || price_before == null || price_low == null
				|| num == null || postage == null  ||
				start_timestamp == null || end_timestamp == null )
		{
			flag = false;
		}
		
		Connection conn = SQL.getInstance().getConnection();
		if (flag) {
			SuperDiscountBean sd = new SuperDiscountBean();
			
			sd.setIsHide(Integer.parseInt(isHide));
			sd.setId(jo.getInt("id"));
			sd.setItemId(jo.getString("item_id"));
			sd.setTitle(jo.getString("title"));
			sd.setImg(jo.getString("img"));
			sd.setPriceBefore(jo.getDouble("price_before"));
			sd.setPriceLow(jo.getDouble("price_low"));
			sd.setNum(jo.getInt("num"));
			sd.setPostage(jo.getInt("postage"));
			sd.setAddTimestamp(System.currentTimeMillis());
			sd.setStartTimestamp(dat2(jo.getString("start_timestamp")));
			sd.setEndTimestamp(dat2(jo.getString("end_timestamp")));
//			sd.setDiscountTimestamp(discountTimestamp);
//			sd.setDiscountDate(discountDate);
			sd.setSoldOut(sold_out);
			sd.setItemTags(itemTags);
			sd.setRemainNum(Integer.parseInt(remainNum));
			sd.setIsSecondKill(Integer.parseInt(isSecondKill));
			sd.setTaoJb(taoJb);
			if (SuperDiscountDAO.getInstance().updateInfo(sd, conn)) {
				
				//同步商品
				UpdateAliyunItemJob.updateItem(item_id, img, price_low, price_before, title);
				
				SuperDiscountBean sd2=SuperDiscountDAO.getInstance().getSuperDiscountById(sd.getId());
				superDiscountCacher.getInstance().updateSuperDiscountById(id, sd2);
				
				//编辑优惠券
				if(super_coupon_address!=null&&!"".equals(super_coupon_address)&&yhq_title!=null&&!"".equals(yhq_title)){
					SuperCouponBean scb=new SuperCouponBean();
					scb.setCouponAddress(super_coupon_address);
					scb.setId(Integer.parseInt(yhq_id));
					scb.setItemId(item_id);
					scb.setItemTitle(title);
					scb.setTimestamp(new Date().getTime());
					scb.setYhqTitle(yhq_title);
					SuperCouponDAO.getInstance().update(scb);
					SuperCouponCacher.getInstance().insertInfo(item_id, scb);
				}
				response.getWriter().write("success");
			}else
			{
				response.getWriter().write("false");
			}
				
		}else
		{
			response.getWriter().write("paramException");
		}
		SQL.getInstance().release(null,conn);
		
	}
	
	private void updateInfo2(HttpServletRequest request, HttpServletResponse response,JSONObject jo) throws JSONException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String isHide=jo.getString("is_hide");
		String id = jo.getString("id");
		String item_id = jo.getString("item_id");
		String title = jo.getString("title");
		String img = jo.getString("img");
		String price_before = jo.getString("price_before");
		String price_low = jo.getString("price_low");
		String num = jo.getString("num");
		String postage = jo.getString("postage");
		String start_timestamp = jo.getString("start_timestamp");
		String end_timestamp = jo.getString("end_timestamp");
//		long discountTimestamp = dat2(jo.getString("discount_timestamp"));
//		String discountDate = dat(dat2(jo.getString("discount_timestamp")));
		int sold_out = 0;
		String itemTags=jo.getString("item_tags");
		String isSecondKill=jo.getString("is_second_kill");
		String remainNum=jo.getString("remain_num");
		String super_coupon_address=jo.getString("yhq_url");
		String yhq_title=jo.getString("yhq_title");
		String yhq_id=jo.getString("yhq_id");
//		int taoJb=jo.getInt("tao_jb");
		int taoJb=0;
		if(remainNum.equals("0")){
			sold_out=1;
		}
		
		String killNum=jo.getString("kill_num");
		String killStartTimestamp=jo.getString("kill_start_timestamp");
		String killEndTimestamp=jo.getString("kill_end_timestamp");
		String killPrice=jo.getString("kill_price");
		
		boolean flag = true;
		if(id == null || item_id == null || title == null || img == null || price_before == null || price_low == null
				|| num == null || postage == null  ||
				start_timestamp == null || end_timestamp == null ||killNum==null||killStartTimestamp==null||killEndTimestamp==null||killPrice==null)
		{
			flag = false;
		}
		
		Connection conn = SQL.getInstance().getConnection();
		if (flag) {
			SuperDiscountBean sd = new SuperDiscountBean();
			
			sd.setIsHide(Integer.parseInt(isHide));
			sd.setId(jo.getInt("id"));
			sd.setItemId(jo.getString("item_id"));
			sd.setTitle(jo.getString("title"));
			sd.setImg(jo.getString("img"));
			sd.setPriceBefore(jo.getDouble("price_before"));
			sd.setPriceLow(jo.getDouble("price_low"));
			sd.setNum(jo.getInt("num"));
			sd.setPostage(jo.getInt("postage"));
//			sd.setDiscountTimestamp(discountTimestamp);
//			sd.setDiscountDate(discountDate);
			sd.setAddTimestamp(System.currentTimeMillis());
			sd.setStartTimestamp(dat2(jo.getString("start_timestamp")));
			sd.setEndTimestamp(dat2(jo.getString("end_timestamp")));
			sd.setSoldOut(sold_out);
			sd.setIsSecondKill(Integer.parseInt(isSecondKill));
			sd.setItemTags(itemTags);
			sd.setRemainNum(Integer.parseInt(remainNum));
			sd.setTaoJb(taoJb);
			
			SecondKill sk=new SecondKill();
			sk.setRemainNum(Integer.parseInt(killNum));
			sk.setType(param.secondkilltype);
			sk.setTypeId(Integer.parseInt(id));
			sk.setKillPrice(Integer.parseInt(killPrice));
			sk.setKillNum(Integer.parseInt(killNum));
			sk.setKillStartTimestamp(dat2(killStartTimestamp));
			sk.setKillEndTimestamp(dat2(killEndTimestamp));
			
			if (SuperDiscountDAO.getInstance().updateInfo(sd, conn)) {
				
				//同步商品
				UpdateAliyunItemJob.updateItem(item_id, img, price_low, price_before, title);
				
				SuperDiscountBean sd2=SuperDiscountDAO.getInstance().getSuperDiscountById(sd.getId());
				superDiscountCacher.getInstance().updateSuperDiscountById(id, sd2);
				if(SecondKillDAO.getInstance().getInfo(Integer.parseInt(id),param.secondkilltype)!=null){//如果已经存在秒杀表中  则是更新 否则是添加
					SecondKillDAO.getInstance().update(sk,Integer.parseInt(id));
					secondKillCacher.getInstance().updateSecondKillById(param.secondkilltype+id, sk);
				}else{
					SecondKillDAO.getInstance().insertInfo(sk);
					secondKillCacher.getInstance().addSecondKill(param.secondkilltype+id, sk);
				}
				
				//优惠券更新
				if(super_coupon_address!=null&&!"".equals(super_coupon_address)&&yhq_title!=null&&!"".equals(yhq_title)){
					SuperCouponBean scb=new SuperCouponBean();
					scb.setCouponAddress(super_coupon_address);
					scb.setId(Integer.parseInt(yhq_id));
					scb.setItemId(item_id);
					scb.setItemTitle(title);
					scb.setYhqTitle(yhq_title);
					scb.setTimestamp(new Date().getTime());
					SuperCouponDAO.getInstance().update(scb);
					SuperCouponCacher.getInstance().insertInfo(item_id, scb);
				}
				response.getWriter().write("success");
			}else
			{
				response.getWriter().write("false");
			}
				
		}else
		{
			response.getWriter().write("paramException");
		}
		SQL.getInstance().release(null,conn);
		
	}
	
	private void getInfoById(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		if(id==null||"".equals(id)){
			response.getWriter().print("paramException");
			return;
		}
		SuperDiscountBean sd = SuperDiscountDAO.getInstance().getSuperDiscountById(Integer.parseInt(id));
		if(sd==null){
			response.getWriter().print("empty");
			return;
		}
		
		Connection conn=SQL.getInstance().getConnection();
		String sdJson = sd.toJson(conn);
		SQL.getInstance().release(null, conn);
		
		if(sd.getIsSecondKill()==1){
			SecondKill sk=SecondKillDAO.getInstance().getInfo(Integer.parseInt(id),param.secondkilltype);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			sdJson=sdJson.substring(0,sdJson.length()-1);
			sdJson=sdJson+",\"kill_price\":\""+sk.getKillPrice()+"\"";
			sdJson=sdJson+",\"kill_num\":\""+sk.getKillNum()+"\"";
			sdJson=sdJson+",\"kill_start_time\":\""+sdf.format(sk.getKillStartTimestamp())+"\"";
			sdJson=sdJson+",\"kill_end_time\":\""+sdf.format(sk.getKillEndTimestamp())+"\"}";
		}
		System.out.println("ffffffff::::::::"+sdJson);
		response.getWriter().print(sdJson);
	}
	
	private void del(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException{
		String id = request.getParameter("id");
		String isSecondKill = request.getParameter("is_second_kill");
		if(id==null||"".equals(id)||isSecondKill==null||"".equals(isSecondKill)){
			response.getWriter().print("paramException");
			return;
		}
		if(isSecondKill.equals("0")){
			SuperDiscountBean sb=SuperDiscountDAO.getInstance().getSuperDiscountById(Integer.parseInt(id));
			if(SuperDiscountDAO.getInstance().del(Integer.parseInt(id))){
				superDiscountCacher.getInstance().delSuperDiscountById(id, sb.getDiscountTimestamp()+"");
				response.getWriter().write("success");
				return;
			}
		}else if(isSecondKill.equals("1")){
			SuperDiscountBean sb=SuperDiscountDAO.getInstance().getSuperDiscountById(Integer.parseInt(id));
			if(SuperDiscountDAO.getInstance().del(Integer.parseInt(id))&&SecondKillDAO.getInstance().del(Integer.parseInt(id))){
				secondKillCacher.getInstance().delSecondKillById(param.secondkilltype+id);
				superDiscountCacher.getInstance().delSuperDiscountById(id, sb.getDiscountTimestamp()+"");
				response.getWriter().write("success");
				return;
			}
		}
		response.getWriter().write("fail");
	}
	
	private String convertToJson(SuperDiscountBean sd){
		String json = "{";
		json += "\"id\":\"" + sd.getId() + "\",\"item_id\":\"" + sd.getItemId() + "\",\"title\":\"" + sd.getTitle() + "\",\"img\":\"" + sd.getImg() + "\",\"price_before\":\"" + sd.getPriceBefore();
		json += "\",\"price_low\":\"" + sd.getPriceLow() + "\",\"num\":\"" + sd.getNum() +"\",\"postage\":\"" + sd.getPostage() + "\",\"discount_timestamp\":\"" + sd.getDiscountTimestamp();
		json += "\",\"add_timestamp\":\"" + sd.getAddTimestamp() + "\",\"discount_date\":\"" + sd.getDiscountDate() + "\",\"start_timestamp\":\"" + sd.getStartTimestamp();
		json += "\",\"end_timestamp\":\"" + sd.getEndTimestamp() + "\",\"sold_out\":\"" + sd.getSoldOut() + "\",\"rank\":\"" + sd.getRank() + "\"}";
		return json;
	}
	
	public static String dat(long timestamp) {
		SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
		String date=sdf.format(timestamp);
		if(date.substring(0,1).equals("0")){
			date=date.substring(1,date.length());
		}
		return date;
	}
	
	public static String dat3(long timestamp) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date=sdf.format(timestamp);
		if(date.substring(0,1).equals("0")){
			date=date.substring(1,date.length());
		}
		return date;
	}
	
	public static long dat2(String date){
		System.out.println("--------------------------->"+date);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long l=0;
		try{
			l=sdf.parse(date).getTime();
		}catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}

	public static void main(String[] args) {
		String img="xxxx";
		img=img.replace("&","26%");
		String str = NetManager.getInstance().send("http://10.0.0.21:8080/purchaseapi/AddCouponProduct","pid=11111111&couponid=112233&title=冈本&img="+img+"&tag=2&starttime=1403430615000&endtime=1403430615000&price=123&stock=10&desc=fckkkkkkkk");
		System.out.println(str);
	}
	
}
