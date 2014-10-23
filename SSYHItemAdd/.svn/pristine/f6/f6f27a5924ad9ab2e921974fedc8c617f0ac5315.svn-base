package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.platform.db.SQL;

public class ItemDAO {

	public static final int  STATUS_NORMAL=0;
	public static final int  STATUS_IS_UPDATE=1;
	public static final int  STATUS_FINISH=2;
	public static final int  STATUS_WAIT=3;
	
	private static ItemDAO instance=null;
	public static ItemDAO getInstance(){
		if(instance==null){
			instance=new ItemDAO();
		}
		return instance;
	}
	private ItemDAO(){}
	
	public int getNum(int userid,long timestamp){
		int num=-1;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select count(id) as c from   youhui_datamining.Item_Add_record  where timestamp>=? and timestamp<? and uid=?;";
		try{
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setLong(1,timestamp);
			ps.setLong(2,timestamp+86400000);
			ps.setInt(3,userid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				num=rs.getInt("c");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return num;
	}
	
	public boolean delInfo(String itemId){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "delete from   `youhui_datamining`.`Item_Add_Step1`  where itemid=?;";
		try{
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1,itemId);
			if(ps.executeUpdate()>0){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public boolean setStatus(String itemId,int status){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "update  `youhui_datamining`.`Item_Add_Step1` set status=? , timestamp=? where itemid=?;";
		try{
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1,status);
			ps.setString(2,String.valueOf(new Date().getTime()));
			ps.setString(3, itemId);
			if(ps.executeUpdate()>0){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public boolean setStatus(String itemId,int status,Connection conn){
		PreparedStatement ps  = null;
		String sql = "update  `youhui_datamining`.`Item_Add_Step1` set status=? , timestamp=? where itemid=?;";
		try{
			ps= conn.prepareStatement(sql);
			ps.setInt(1,status);
			ps.setString(2,String.valueOf(new Date().getTime()));
			ps.setString(3, itemId);
			if(ps.executeUpdate()>0){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	public String getImgSize(String itemId){
		Connection conn = null;
		PreparedStatement ps  = null;
		String str="";
		String sql = "select `img_size` from `youhui_datamining`.`Item_Add_Step1` where itemid=?;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1, itemId);
			ResultSet rs  = ps.executeQuery();
			if(rs.next()){
				str=str+rs.getString("img_size");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return str;
	}
	
	public  String getByStatus0(){
		Connection conn = null;
		PreparedStatement ps  = null;
		String str="";
		String sql = "select `itemid` from `youhui_datamining`.`Item_Add_Step1` where status=0;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			while(rs.next()){
				str=str+rs.getString("itemid")+",";
			}
			if(str.length()>0){
				str.substring(0,str.length()-1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return str;
	}
	
	public  ItemBean getItem(String itemId){
		Connection conn = null;
		PreparedStatement ps  = null;
		ItemBean tb= new ItemBean();
		String sql = "select `id`,`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`status`,`sellerid`,`nick`,`price`,`volume`,`shopurl`,`zk_price`,`rate`,`img_size` from `youhui_datamining`.`Item_Add_Step1` where itemid=?;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ResultSet rs  = ps.executeQuery();
			if(rs.next()){
				
				tb.id= rs.getInt(1);
				tb.itemid = rs.getString(2);
				tb.imgurl = rs.getString(3);
				tb.title = rs.getString(4);
				tb.recom = rs.getString(5);
				tb.remark = rs.getString(6);
				tb.timestamp = rs.getLong(7);
				tb.userid = rs.getInt(8);
				tb.status = rs.getInt(9);
				tb.sellerid = rs.getString(10);
				tb.nick = rs.getString(11);
				tb.price = rs.getDouble(12);
				tb.volume = rs.getString(13);
				tb.shopurl = rs.getString(14);
				tb.zkPrice=rs.getDouble(15);
				tb.rate=rs.getDouble(16);
				tb.imgSize=rs.getString("img_size");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return tb;
	}
	
	
	public  ItemBean getItem(String itemId,Connection conn){
		PreparedStatement ps  = null;
		ItemBean tb= new ItemBean();
		String sql = "select `id`,`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`status`,`sellerid`,`nick`,`price`,`volume`,`shopurl`,`zk_price`,`rate`,`img_size` from `youhui_datamining`.`Item_Add_Step1` where itemid=?;";
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ResultSet rs  = ps.executeQuery();
			if(rs.next()){
				
				tb.id= rs.getInt(1);
				tb.itemid = rs.getString(2);
				tb.imgurl = rs.getString(3);
				tb.title = rs.getString(4);
				tb.recom = rs.getString(5);
				tb.remark = rs.getString(6);
				tb.timestamp = rs.getLong(7);
				tb.userid = rs.getInt(8);
				tb.status = rs.getInt(9);
				tb.sellerid = rs.getString(10);
				tb.nick = rs.getString(11);
				tb.price = rs.getDouble(12);
				tb.volume = rs.getString(13);
				tb.shopurl = rs.getString(14);
				tb.zkPrice=rs.getDouble(15);
				tb.rate=rs.getDouble(16);
				tb.imgSize=rs.getString("img_size");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return tb;
	}
	
	
	public  ItemBean getPreItem(String id){
		Connection conn = null;
		PreparedStatement ps  = null;
		ItemBean tb= new ItemBean();
		String sql = "select `id`,`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`status`,`sellerid`,`nick`,`price`,`volume`,`shopurl`,`zk_price`,`rate`,`img_size` from `youhui_datamining`.`Item_Add_Step1` where id=?;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1,id);
			ResultSet rs  = ps.executeQuery();
			if(rs.next()){
				
				tb.id= rs.getInt(1);
				tb.itemid = rs.getString(2);
				tb.imgurl = rs.getString(3);
				tb.title = rs.getString(4);
				tb.recom = rs.getString(5);
				tb.remark = rs.getString(6);
				tb.timestamp = rs.getLong(7);
				tb.userid = rs.getInt(8);
				tb.status = rs.getInt(9);
				tb.sellerid = rs.getString(10);
				tb.nick = rs.getString(11);
				tb.price = rs.getDouble(12);
				tb.volume = rs.getString(13);
				tb.shopurl = rs.getString(14);
				tb.zkPrice=rs.getDouble(15);
				tb.rate=rs.getDouble(16);
				tb.imgSize=rs.getString("img_size");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return tb;
	}
	
	
	public boolean uodateItemImg(String url,String id){
		Connection conn = null;
		PreparedStatement ps=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_datamining.Item_Add_Step1 set img_url=? where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,url);
			ps.setString(2,id);
			if(ps.executeUpdate()>0){
				return true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public boolean uodateItem(int id,String title,String recom,String remark,int rate,Double zkPrice,String imgSize){
		Connection conn = null;
		PreparedStatement ps=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_datamining.Item_Add_Step1 set title=?,recom=?,remark=?,rate=?,zk_price=?,img_size=? where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,title);
			ps.setString(2,recom);
			ps.setString(3, remark);
			ps.setInt(4, rate);
			ps.setDouble(5, zkPrice);
			ps.setString(6,imgSize);
			ps.setInt(7,id);
			
			if(ps.executeUpdate()>0){
				return true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public boolean uodateItem(double zkPrice,double rate,String itemId){
		Connection conn = null;
		PreparedStatement ps=null;
		if(zkPrice==0||zkPrice==0.0){
			return false;
		}
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_datamining.Item_Add_Step1 set zk_price=?,rate=?,status=? where itemid=?";
			ps=conn.prepareStatement(sql);
			ps.setDouble(1,zkPrice);
			ps.setDouble(2,rate);
			ps.setInt(3,STATUS_IS_UPDATE);
			ps.setString(4, itemId);
			
			
			if(ps.executeUpdate()>0){
				return true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public boolean getNumByStatus1(){
		Connection conn = null;
		PreparedStatement ps=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select count(id)as c from youhui_datamining.Item_Add_Step1 where status=0";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("c")==0){
					return true;
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public boolean updateRecom(String id,String val){
		Connection conn = null;
		PreparedStatement ps=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_datamining.Item_Add_Step1 set recom=? where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,val);
			ps.setString(2,id);
			if(ps.executeUpdate()>0){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public boolean updateRemark(String id,String val){
		Connection conn = null;
		PreparedStatement ps=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_datamining.Item_Add_Step1 set remark=? where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,val);
			ps.setString(2,id);
			if(ps.executeUpdate()>0){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
}
