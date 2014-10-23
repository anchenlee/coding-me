package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.SuperDay;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.cacher.dao.superDiscountCacher;
import cn.youhui.common.Config;
import cn.youhui.common.ParamConfig;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.SuiShouActionUtil;
import cn.youhui.utils.Util;

public class SuperDiscountDAO {
	
	/**
	 * @author jj
	 * @date 2014-06-13
	 */
	private static SuperDiscountDAO instance=null;
	
	public static String ADMIN_UID="21172545";
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2=new SimpleDateFormat("MM.dd");
	public static SuperDiscountDAO getInstance(){
		if(instance==null){
			instance =new SuperDiscountDAO();
		}
		return instance;
	}
	private SuperDiscountDAO(){}

	public long getNextDate(String today){
		Connection conn=null;
		long ll=0;
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="select min(discount_timestamp) as c from youhui_discount.discount where  discount_timestamp>?;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,today);
			ResultSet rs =st.executeQuery();
			if(rs.next()){
				ll=rs.getLong("c");
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return ll;
	}
	
		public long getMaxDate(){
			Connection conn=null;
			long ll=0;
			try{
				conn=MySQLDBIns.getInstance().getConnection();
				String sql="select max(discount_timestamp) as c from youhui_discount.discount ;";
				PreparedStatement st=conn.prepareStatement(sql);
				ResultSet rs =st.executeQuery();
				if(rs.next()){
					ll=rs.getLong("c");
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				MySQLDBIns.getInstance().release(conn);
			}
			return ll;
		}
	
		public int getInfoById(String itemId){
			Connection conn=null;
			int x=-1;
			try{
				conn=MySQLDBIns.getInstance().getConnection();
				String sql="select * from youhui_discount.discount where item_id=?";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1,itemId);
				ResultSet rs=st.executeQuery();
				if(rs.next()){
					x=rs.getInt("id");
				}
				
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				MySQLDBIns.getInstance().release(conn);
			}
			return x;
		}
		
		public SuperDiscountBean getInfoById(int id){
			SuperDiscountBean discountbean= null;
			Connection conn=null;
			try{
				conn=MySQLDBIns.getInstance().getConnection();
				String sql="select * from youhui_discount.discount where id=?";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setInt(1,id);
				ResultSet rs=st.executeQuery();
				if(rs.next()){
					discountbean=new SuperDiscountBean();
					discountbean.setId(rs.getInt("id"));
					discountbean.setItemId(rs.getString("item_id"));
					discountbean.setTitle(rs.getString("title"));
					discountbean.setImg(rs.getString("img"));
					discountbean.setPriceBefore(rs.getDouble("price_before"));
					discountbean.setPriceLow(rs.getDouble("price_low"));
					discountbean.setNum(rs.getInt("num"));
					discountbean.setPostage(rs.getInt("postage"));
					discountbean.setDiscountTimestamp(rs.getLong("discount_timestamp"));
					discountbean.setAddTimestamp(rs.getLong("add_timestamp"));
					discountbean.setDiscountDate(rs.getString("discount_date"));
					discountbean.setStartTimestamp(rs.getLong("start_timestamp"));
					discountbean.setEndTimestamp(rs.getLong("end_timestamp"));
					discountbean.setRank(rs.getInt("rank"));
					discountbean.setIsSecondKill(rs.getInt("is_second_kill"));
					discountbean.setRemainNum(rs.getInt("remain_num"));
					discountbean.setItemTags(rs.getString("item_tags"));
				}
				
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				MySQLDBIns.getInstance().release(conn);
			}
			return discountbean;
		}
		public List<SuperDay> getInfoFromRedis(int pagNow,String uid,String platform,String tomorrow){
			
			List<SuperDay> l2=null;
			java.util.List<SuperDiscountBean> list=null;
			try{
				List<Long> l=superDiscountCacher.getInstance().getPags(pagNow,tomorrow);
				l2=new ArrayList<SuperDay>();
				for(Long ll:l){
					list=superDiscountCacher.getInstance().getSuperDisCountListByDate(ll+"");
					Collections.sort(list);//排序
					for(int i=0;i<list.size();i++){
						SuperDiscountBean sb=list.get(i);
						sb.setImg(Util.getCustomImg(sb.getImg(),"400x400"));//设置首页小图
						if(sb.getIsHide()==1&&!uid.equals(ADMIN_UID)){//隐藏商品只能由管理员查看
							list.remove(sb);
							i--;
						}else{
							sb.setClickUrl(SuiShouActionUtil.getSuishouWebUrl(platform,ParamConfig.start_rush_url+"?id="+sb.getId()));
						}
					}
					
					if(list.size()>0){
						long date=sdf.parse(sdf.format(new Date())).getTime();
						ll=sdf.parse(sdf.format(ll)).getTime();
						SuperDay sd=new SuperDay();
						sd.setTitle(sdf2.format(ll));
						if(ll==date){
							sd.setTips(ParamConfig.todaytips);
							sd.setStatus(ParamConfig.todaystatus);
						}else if(ll<date){
							sd.setTips(ParamConfig.oldtips);
							sd.setStatus(ParamConfig.oldstatus);
						}
						sd.setList(list);
						l2.add(sd);
					}
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}
			return l2;
		}
		public List<SuperDay> getInfo(int pagNow,String uid,String platform){
			
			String outerCode = OuterCode.getOuterCode(uid, platform, "0", "7", "607");
	
			
			Connection conn=null;
			List<SuperDay> l2=null;
			java.util.List<SuperDiscountBean> list=null;
			try{
				conn=MySQLDBIns.getInstance().getConnection();
				List<Long> l=getPags(conn,pagNow);
				l2=new ArrayList<SuperDay>();
				for(Long ll:l){
					System.out.println("dddddd："+ll);
					list=new java.util.ArrayList<SuperDiscountBean>();
					String sql="select * from youhui_discount.discount where discount_timestamp=? order by rank" ;
					PreparedStatement st=conn.prepareStatement(sql);
					st.setLong(1,ll);
					ResultSet rs=st.executeQuery();
					while(rs.next()){
						SuperDiscountBean discountbean= new SuperDiscountBean();
						
						String clickURL=Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" +rs.getString("item_id") ;
						
						discountbean.setClickUrl(clickURL);
						discountbean.setId(rs.getInt("id"));
						discountbean.setItemId(rs.getString("item_id"));
						discountbean.setTitle(rs.getString("title"));
						discountbean.setImg(rs.getString("img"));
						discountbean.setPriceBefore(rs.getDouble("price_before"));
						discountbean.setPriceLow(rs.getDouble("price_low"));
						discountbean.setNum(rs.getInt("num"));
						discountbean.setPostage(rs.getInt("postage"));
						discountbean.setDiscountTimestamp(rs.getLong("discount_timestamp"));
						discountbean.setAddTimestamp(rs.getLong("add_timestamp"));
						discountbean.setDiscountDate(rs.getString("discount_date"));
						discountbean.setStartTimestamp(rs.getLong("start_timestamp"));
						discountbean.setEndTimestamp(rs.getLong("end_timestamp"));
						discountbean.setRank(rs.getInt("rank"));
						discountbean.setIsSecondKill(rs.getInt("is_second_kill"));
						discountbean.setRemainNum(rs.getInt("remain_num"));
						discountbean.setItemTags(rs.getString("item_tags"));
						list.add(discountbean);
					}
					if(list.size()>0){
						long date=sdf.parse(sdf.format(new Date())).getTime();
						ll=sdf.parse(sdf.format(ll)).getTime();
						SuperDay sd=new SuperDay();
						sd.setTitle(sdf2.format(ll));
						System.out.println(ll+"//"+date);
						if(ll==date){
							sd.setTips(ParamConfig.todaytips);
							sd.setStatus(ParamConfig.todaystatus);
							
							System.out.println(sd.getTips());
						}else if(ll<date){
							sd.setTips(ParamConfig.oldtips);
							sd.setStatus(ParamConfig.oldstatus);
						}
						sd.setList(list);
						l2.add(sd);
					}
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				MySQLDBIns.getInstance().release(conn);
			}
			return l2;
		}
		
		public List<Long> getPags(Connection conn,int pagNow){
			
			List<Long> list=null;
			
			try{
				list=new ArrayList<Long>();
				long date=sdf.parse(sdf.format(new Date())).getTime();
				String sql="select discount_timestamp from youhui_discount.discount  where discount_timestamp <?  group by discount_date order by discount_timestamp desc limit ?,? ";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setLong(1,date+Long.parseLong("86400000"));
				st.setInt(2,(pagNow-1)*ParamConfig.superNumPerPage);
				st.setInt(3,ParamConfig.superNumPerPage);
				System.out.println(st);
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					list.add(rs.getLong("discount_timestamp"));
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}
			return list;
		}

		public int getTotalPages(String date){
			int  tmp=-1;
			Connection conn=null;
			try{
				conn=MySQLDBIns.getInstance().getConnection();
				String sql="select count(distinct(discount_date)) as c from youhui_discount.discount where discount_date <> ?";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1,date);
				ResultSet rs=st.executeQuery();
				if(rs.next()){
					tmp=rs.getInt("c");
					
				}
				
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				MySQLDBIns.getInstance().release(conn);
			}
			if(tmp!=-1){
				if(tmp%2==0){
					tmp=tmp/2;
				}else{
					tmp=tmp/2+1;
				}
			}
			return tmp;
		}
		
		/**
		 * 明日预告
		 * 2014-6-17
		 * jj
		 */
		public List<SuperDiscountBean> getTommorrowForecast(String date){
			Connection conn=null;
			List<SuperDiscountBean> list=new ArrayList<SuperDiscountBean>();
			try{
				conn=MySQLDBIns.getInstance().getConnection();
				String sql="select * from youhui_discount.discount where discount_date =? order by rank";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1,date);
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					SuperDiscountBean discountbean= new SuperDiscountBean();
					discountbean.setId(rs.getInt("id"));
					discountbean.setItemId(rs.getString("item_id"));
					discountbean.setTitle(rs.getString("title"));
					discountbean.setImg(rs.getString("img"));
					discountbean.setPriceBefore(rs.getDouble("price_before"));
					discountbean.setPriceLow(rs.getDouble("price_low"));
					discountbean.setNum(rs.getInt("num"));
					discountbean.setPostage(rs.getInt("postage"));
					discountbean.setDiscountTimestamp(rs.getLong("discount_timestamp"));
					discountbean.setAddTimestamp(rs.getLong("add_timestamp"));
					discountbean.setDiscountDate(rs.getString("discount_date"));
					System.out.println(rs.getString("discount_date"));
					discountbean.setStartTimestamp(rs.getLong("start_timestamp"));
					discountbean.setEndTimestamp(rs.getLong("end_timestamp"));
					discountbean.setRank(rs.getInt("rank"));
					discountbean.setIsSecondKill(rs.getInt("is_second_kill"));
					discountbean.setRemainNum(rs.getInt("remain_num"));
					discountbean.setItemTags(rs.getString("item_tags"));
					list.add(discountbean);
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				MySQLDBIns.getInstance().release(conn);
			}
			return list;
		}
		
		/**
		 * 根据ID取数据
		 * 2014-6-17
		 * jiangbing
		 */
		public SuperDiscountBean getSuperDiscountById(String id){
			Connection conn=null;
			SuperDiscountBean discountbean= null;
			try{
				conn=MySQLDBIns.getInstance().getConnection();
				String sql="select * from youhui_discount.discount where id =?";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1,id);
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					discountbean=new SuperDiscountBean();
					discountbean.setId(rs.getInt("id"));
					discountbean.setItemId(rs.getString("item_id"));
					discountbean.setTitle(rs.getString("title"));
					discountbean.setImg(rs.getString("img"));
					discountbean.setPriceBefore(rs.getDouble("price_before"));
					discountbean.setPriceLow(rs.getDouble("price_low"));
					discountbean.setNum(rs.getInt("num"));
					discountbean.setPostage(rs.getInt("postage"));
					discountbean.setDiscountTimestamp(rs.getLong("discount_timestamp"));
					discountbean.setAddTimestamp(rs.getLong("add_timestamp"));
					discountbean.setDiscountDate(rs.getString("discount_date"));
					System.out.println(rs.getString("discount_date"));
					discountbean.setStartTimestamp(rs.getLong("start_timestamp"));
					discountbean.setEndTimestamp(rs.getLong("end_timestamp"));
					discountbean.setRemainNum(rs.getInt("remain_num"));
					discountbean.setRank(rs.getInt("rank"));
					discountbean.setItemTags(rs.getString("item_tags"));
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				MySQLDBIns.getInstance().release(conn);
			}
			return discountbean;
		}
		
		public static String dat(long timestamp) {
			SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
			String date=sdf.format(timestamp);
			if(date.substring(0,1).equals("0")){
				date=date.substring(1,date.length());
			}
			return date;
		}
		
		public List<SuperDiscountBean> getSuperDiscountByKeywordAndDate(String keyword,String date)
		{
			Connection conn=null;
			SuperDiscountBean discountbean= null;
			List<SuperDiscountBean> list = new ArrayList<SuperDiscountBean>();
		    String where = " 1=1 ";
			try{
				conn=MySQLDBIns.getInstance().getConnection();
				if (!"".equals(keyword) && null != keyword) {
					where = where + " and title like %"+keyword+"%";
				}
				if (!"".equals(date) && null != date) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date dt = sdf.parse(date);
					where = where + " and discount_timestamp ="+String.valueOf(dt.getTime());
				}
				where += " order by discount_timestamp;";
				String sql="select * from youhui_discount.discount where " + where;
				PreparedStatement st=conn.prepareStatement(sql);
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					discountbean=new SuperDiscountBean();
					discountbean.setId(rs.getInt("id"));
					discountbean.setItemId(rs.getString("item_id"));
					discountbean.setTitle(rs.getString("title"));
					discountbean.setImg(rs.getString("img"));
					discountbean.setPriceBefore(rs.getDouble("price_before"));
					discountbean.setPriceLow(rs.getDouble("price_low"));
					discountbean.setNum(rs.getInt("num"));
					discountbean.setPostage(rs.getInt("postage"));
					discountbean.setDiscountTimestamp(rs.getLong("discount_timestamp"));
					discountbean.setAddTimestamp(rs.getLong("add_timestamp"));
					discountbean.setDiscountDate(rs.getString("discount_date"));
					discountbean.setStartTimestamp(rs.getLong("start_timestamp"));
					discountbean.setEndTimestamp(rs.getLong("end_timestamp"));
					discountbean.setRemainNum(rs.getInt("remain_num"));
					discountbean.setRank(rs.getInt("rank"));
					discountbean.setItemTags(rs.getString("item_tags"));
					list.add(discountbean);
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				MySQLDBIns.getInstance().release(conn);
			}
			return list;
		}
}
