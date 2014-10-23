package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.apache.tomcat.util.digester.SetRootRule;

import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.cache.dao.superDiscountCacher;
import cn.youhui.cache.dao.superItemTagsCacher;
import cn.youhui.platform.db.SQL;

public class SuperDiscountDAO {
	
	/**
	 * @author jj
	 * @date 2014-06-13
	 */
	private static SuperDiscountDAO instance=null;
	public static SuperDiscountDAO getInstance(){
		if(instance==null){
			instance =new SuperDiscountDAO();
		}
		return instance;
	}
	private SuperDiscountDAO(){}

	public int gettagIds(String keyword){
		Connection conn=null;
		int id=-1;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="SELECT id FROM youhui_datamining.m_discount_keywords where keyword = ?;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,keyword);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				id=rs.getInt("id");
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return id;
	}
	
	public List<String[]> gettagIds(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
		Connection conn=null;
		List<String[]> list=new ArrayList<String[]>();
		try{
			conn=SQL.getInstance().getConnection();
			String sql="SELECT id,keyword FROM youhui_datamining.m_discount_keywords where keyword like '%超级惠%' and id <> 1215;";
			PreparedStatement st=conn.prepareStatement(sql);
			System.out.println(st);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				String[] s=new String[2];
				s[0]=rs.getString("id");
				s[1]=sdf.parse("2014."+rs.getString("keyword").split("超级惠")[1]).getTime()+"";
				list.add(s);
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return list;
	}
	public List<String> xxx(int tagId){
		Connection conn=null;
		List<String> list=new ArrayList<String>();
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select item_id from youhui_datamining.m_tagtoitem where tag_id=? and rel_status=1";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,tagId);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				list.add(rs.getString("item_id"));
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return list;
	}
	
	public SuperDiscountBean fck(String itemId,long discountTimestamp){
		Connection conn=null;
		SuperDiscountBean sb=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="SELECT * FROM youhui_datamining.m_discount_products where item_id=?;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,itemId);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				sb=new SuperDiscountBean();
				sb.setAddTimestamp(new Date().getTime());
				sb.setDiscountDate(dat(discountTimestamp));
				sb.setDiscountTimestamp(discountTimestamp);
				sb.setEndTimestamp(discountTimestamp+82800000);
				sb.setImg(rs.getString("pic_url"));
				sb.setIsHide(0);
				sb.setIsSecondKill(0);
				sb.setItemId(itemId);
				sb.setItemTags("");
				sb.setNum(-1);
				sb.setPostage(rs.getInt("baoyou"));
				sb.setPriceBefore(rs.getDouble("price_high"));
				sb.setPriceLow(rs.getDouble("price_low"));
				sb.setRank(0);
				sb.setRemainNum(-1);
				sb.setSoldOut(0);
				sb.setStartTimestamp(discountTimestamp+1000);
				sb.setTaoJb(0);
				sb.setTitle(rs.getString("title"));
			}
			
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return sb;
	}
	public static String dat(long time){
		SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
		return sdf.format(time);
	}
	
	public boolean setHide(int id,int isHide){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_discount.discount set is_hide=? where id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,isHide);
			st.setInt(2,id);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return false;
	}
	
	public boolean del(int id){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="delete from youhui_discount.discount where id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,id);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return false;
	}
	
	public String  getPages(Connection conn){
		String  str="";
		try{
			String sql="select distinct(discount_timestamp)  as c from youhui_discount.discount order by discount_timestamp desc";
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				str=str+"\""+rs.getString("c")+"\",";
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return str;
	}
	
	
	public java.util.List<SuperDiscountBean> getAll(){
		
		Connection conn=null;
		java.util.List<SuperDiscountBean> list=new java.util.ArrayList<SuperDiscountBean>();
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select * from youhui_discount.discount " ;
			java.sql.PreparedStatement st=conn.prepareStatement(sql);
			java.sql.ResultSet rs=st.executeQuery();
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
				discountbean.setStartTimestamp(rs.getLong("start_timestamp"));
				discountbean.setEndTimestamp(rs.getLong("end_timestamp"));
				discountbean.setRank(rs.getInt("rank"));
				discountbean.setRemainNum(rs.getInt("remain_num"));
				discountbean.setItemTags(rs.getString("item_tags"));
				discountbean.setIsSecondKill(rs.getInt("is_second_kill"));
				discountbean.setSoldOut(rs.getInt("sold_out"));
				discountbean.setTaoJb(rs.getInt("tao_jb"));
				discountbean.setIsHide(rs.getInt("is_hide"));
				list.add(discountbean);
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return list;
	}
	
		
		public java.util.List<SuperDiscountBean> getInfo(java.sql.Connection conn,String date){


			java.util.List<SuperDiscountBean> list=new java.util.ArrayList<SuperDiscountBean>();
			try{
				String sql="select * from youhui_discount.discount where discount_timestamp=? order by rank desc" ;
				java.sql.PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1,date);
				java.sql.ResultSet rs=st.executeQuery();
				System.out.println(st);
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
					discountbean.setStartTimestamp(rs.getLong("start_timestamp"));
					discountbean.setEndTimestamp(rs.getLong("end_timestamp"));
					discountbean.setRank(rs.getInt("rank"));
					discountbean.setRemainNum(rs.getInt("remain_num"));
					discountbean.setItemTags(rs.getString("item_tags"));
					discountbean.setIsSecondKill(rs.getInt("is_second_kill"));
					discountbean.setIsHide(rs.getInt("is_hide"));
					list.add(discountbean);
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}
			return list;
		}


		public int insertInfo(SuperDiscountBean discountbean,java.sql.Connection conn){
			int id=-1;
			try{
				String sql=" insert into youhui_discount.discount (item_id,title,img,price_before,price_low,num,postage,discount_timestamp,add_timestamp,discount_date,start_timestamp,end_timestamp,rank,is_second_kill,remain_num,item_tags,tao_jb,is_hide) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
				java.sql.PreparedStatement st=conn.prepareStatement(sql);
				
				st.setString(1,discountbean.getItemId());
				st.setString(2,discountbean.getTitle());
				st.setString(3,discountbean.getImg());
				st.setDouble(4,discountbean.getPriceBefore());
				st.setDouble(5,discountbean.getPriceLow());
				st.setInt(6,discountbean.getNum());
				st.setInt(7,discountbean.getPostage());
				st.setLong(8,discountbean.getDiscountTimestamp());
				st.setLong(9,discountbean.getAddTimestamp());
				st.setString(10,discountbean.getDiscountDate());
				st.setLong(11,discountbean.getStartTimestamp());
				st.setLong(12,discountbean.getEndTimestamp());
				st.setInt(13,discountbean.getRank());
				st.setInt(14,discountbean.getIsSecondKill());
				st.setInt(15,discountbean.getRemainNum());
				st.setString(16,discountbean.getItemTags());
				st.setInt(17,discountbean.getTaoJb());
				st.setInt(18,discountbean.getIsHide());
				System.out.println(st);
				st.execute();
				ResultSet rs=st.getGeneratedKeys();
				
				if(rs.next()){
					id=rs.getInt(1);
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}
			return id;
		}
		
		public boolean updateInfo(SuperDiscountBean discountbean,java.sql.Connection conn){


			try{
				String sql=" update youhui_discount.discount set item_id = ?,title = ?,img = ?,price_before = ?,price_low = ?,num = ?,postage = ?,start_timestamp = ?,end_timestamp = ?  ,item_tags=?,is_second_kill=?,remain_num=?,tao_jb=?,is_hide=? where id = ?" ;
				java.sql.PreparedStatement st=conn.prepareStatement(sql);
				
				st.setString(1,discountbean.getItemId());
				st.setString(2,discountbean.getTitle());
				st.setString(3,discountbean.getImg());
				st.setDouble(4,discountbean.getPriceBefore());
				st.setDouble(5,discountbean.getPriceLow());
				st.setInt(6,discountbean.getNum());
				st.setInt(7,discountbean.getPostage());
				st.setLong(8,discountbean.getStartTimestamp());
				st.setLong(9,discountbean.getEndTimestamp());
				st.setString(10,discountbean.getItemTags());
				st.setInt(11,discountbean.getIsSecondKill());
				st.setInt(12,discountbean.getRemainNum());
				st.setInt(13,discountbean.getTaoJb());
//				st.setLong(15,discountbean.getDiscountTimestamp());
//				st.setString(16,discountbean.getDiscountDate());
				st.setInt(14,discountbean.getIsHide());
				st.setInt(15,discountbean.getId());
				System.out.println(st);
				if(st.executeUpdate()>0){
					return true;
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}
			return false;
		}
		
		public int maxRank(long discountTimestamp){
			Connection conn=null;
			try{
				conn=SQL.getInstance().getConnection();
				String sql="select max(rank) as c from  youhui_discount.discount where discount_timestamp=?";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setLong(1,discountTimestamp);
				ResultSet rs=st.executeQuery();
				if(rs.next()){
					return rs.getInt("c");
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				SQL.getInstance().release(null,conn);
			}
			return 0;
		}
		
		public boolean changeRankNew(Map<String,Integer> map){
			Connection conn=SQL.getInstance().getConnection();
			Iterator<String > it=map.keySet().iterator();
				try{
					String sql="update youhui_discount.discount set rank=? where id=?";
					PreparedStatement st=conn.prepareStatement(sql);
					conn.setAutoCommit(false);
					
					while(it.hasNext()){
						String key=it.next();
						int rank=map.get(key);
						st.setInt(1, rank);
						st.setString(2,key);
						st.addBatch();
					}
					int[] i=st.executeBatch();
					conn.setAutoCommit(true);
					
					if(i[0]>0){
						return true;
					}
				}catch(Exception e){
					e.printStackTrace(); 
				}finally{
					SQL.getInstance().release(null,conn);
				}
				return false;
		}
		
		public boolean changeRank(int id1,int id2,int rank1,int rank2,long date){
			Connection conn=SQL.getInstance().getConnection();
			int tmp=sub(id1, id2, rank1, rank2, date, conn);
			if(tmp!=0){
				try{
					String sql="update youhui_discount.discount set rank=? where id=?";
					PreparedStatement st=conn.prepareStatement(sql);
					if(tmp==1){
						st.setInt(1,rank2);
						st.setInt(2,id1);
					}else if(tmp==2){
						st.setInt(1,rank2);
						st.setInt(2,id1);
					}
					System.out.println("3333333:::"+st);
					if(st.executeUpdate()>0){
						return true;
					}
				}catch(Exception e){
					e.printStackTrace(); 
				}finally{
					SQL.getInstance().release(null,conn);
				}
			}
			return false;
		}
		public int sub(int id1,int id2,int rank1,int rank2,long date,Connection conn){
			try{
				String sql="";
				PreparedStatement st=null;
				if(rank1<rank2){
					sql="update  youhui_discount.discount set rank=rank-1 where rank>=? and rank<=? and discount_timestamp =?";
					st=conn.prepareStatement(sql);
					st.setInt(1,rank1);
					st.setInt(2,rank2);
					st.setLong(3,date);
					System.out.println("11111111:::"+st);
					if(st.executeUpdate()>0){
						return 1;
					}
				}else if(rank1>rank2){
					sql="update  youhui_discount.discount set rank=rank+1 where rank>=? and rank<=? and discount_timestamp =?";
					st=conn.prepareStatement(sql);
					st.setInt(1,rank2);
					st.setInt(2,rank1);
					st.setLong(3,date);
					System.out.println("2222222:::"+st);
					if(st.executeUpdate()>0){
						return 2;
					}
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}
			return 0;
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
				conn=SQL.getInstance().getConnection();
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
					discountbean.setStartTimestamp(rs.getLong("start_timestamp"));
					discountbean.setEndTimestamp(rs.getLong("end_timestamp"));
					discountbean.setRank(rs.getInt("rank"));
					discountbean.setRemainNum(rs.getInt("remain_num"));
					discountbean.setItemTags(rs.getString("item_tags"));
					discountbean.setIsSecondKill(rs.getInt("is_second_kill"));
					discountbean.setIsHide(rs.getInt("is_hide"));
					list.add(discountbean);
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				SQL.getInstance().release(null,conn);
			}
			return list;
		}
		
		/**
		 * 根据ID取数据
		 * 2014-6-17
		 * jiangbing
		 */
		public SuperDiscountBean getSuperDiscountById(int id){
			Connection conn=null;
			SuperDiscountBean discountbean= null;
			try{
				conn=SQL.getInstance().getConnection();
				String sql="select * from youhui_discount.discount where id =?";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setInt(1,id);
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
					discountbean.setIsSecondKill(rs.getInt("is_second_kill"));
					discountbean.setItemTags(rs.getString("item_tags"));
					discountbean.setIsHide(rs.getInt("is_hide"));
					discountbean.setRank(rs.getInt("rank"));
				}
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				SQL.getInstance().release(null,conn);
			}
			return discountbean;
		}
		public void clean(){
			Connection conn=null;
			try{
				conn=SQL.getInstance().getConnection();
				String sql="delete from youhui_discount.discount;";
				PreparedStatement st=conn.prepareStatement(sql);
				st.executeUpdate();
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				SQL.getInstance().release(null,conn);
			}
		}
		public List<SuperDiscountBean> getSuperDiscountByKeywordAndDate(String keyword,String date)
		{
			Connection conn=null;
			SuperDiscountBean discountbean= null;
			List<SuperDiscountBean> list = new ArrayList<SuperDiscountBean>();
		    String where = " 1=1 ";
			try{
				conn=SQL.getInstance().getConnection();
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
				SQL.getInstance().release(null,conn);
			}
			return list;
		}
		
}
