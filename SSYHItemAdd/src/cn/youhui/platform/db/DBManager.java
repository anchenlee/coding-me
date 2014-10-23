package cn.youhui.platform.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;

import cn.youhui.bean.WaitBean;
import cn.youhui.itemDAO.ItemHisDAO;
import cn.youhui.itemDAO.WaitDAO;
import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.itemadd.dataadapter.ItemHisBean;
import cn.youhui.itemadd.dataadapter.MenuBean;
import cn.youhui.itemadd.dataadapter.TabBean;
import cn.youhui.platform.config.param;

public class DBManager {
	public static final int  STATUS_NORMAL=0;
	public static final int  STATUS_IS_UPDATE=1;
	public static final int  STATUS_FINISH=2;
	public static final int  STATUS_WAIT=3;
	
	
	public static String getUsername(int uid,Connection conn){
		PreparedStatement ps = null;
		String sql="select username from  youhui_v3.m_user where id=?";
		String username="";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1,uid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				username=rs.getString("username");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return username;
	}
	
	public static int getRecord(String itemId,Connection conn){
		PreparedStatement ps = null;
		String sql = "select uid from  `youhui_datamining`.`Item_Add_record` where item_id=?";
		int uid=0;
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				uid=rs.getInt("uid");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return uid;
	}
	public static String getTime(String itemId,Connection conn){
		PreparedStatement ps = null;
		String sql = "select timestamp from  `youhui_datamining`.`Item_Add_record` where item_id=?";
		String time="";
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				time=rs.getString("timestamp");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 if(!time.equals("")){
			 time=sdf.format(Long.parseLong(time));
		 }
		return time;
	}
	
	public static boolean addRecord(int uid,String itemId){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into `youhui_datamining`.`Item_Add_record`(`item_id`,`uid`,`timestamp`) values(?,?,?)";
		try{
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ps.setInt(2,uid);
			ps.setString(3,String.valueOf(new Date().getTime()));
			if(ps.executeUpdate()>0){
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
		
	}
	
	public static boolean addItem(ItemBean ib){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into `youhui_datamining`.`Item_Add_Step1`(`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`sellerid`,`nick`,`price`,`volume`,`shopurl`,`rate`,`zk_price`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if(ib.getZkPrice()==0||ib.getZkPrice()==0.0){
			return false;
		}
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ib.itemid);
			ps.setString(2, ib.imgurl);
			ps.setString(3, ib.title);
			ps.setString(4, ib.recom);
			ps.setString(5, ib.remark);
			ps.setLong(6, System.currentTimeMillis());
			ps.setInt(7, ib.userid);
			ps.setString(8, ib.sellerid);
			ps.setString(9, ib.nick);
			ps.setDouble(10, ib.price);
			ps.setString(11, ib.volume);
			ps.setString(12, ib.shopurl);
			ps.setDouble(13,ib.rate);
			ps.setDouble(14,ib.zkPrice);
			
			int i = ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public static boolean updateItem(ItemBean ib){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update `youhui_datamining`.`Item_Add_Step1` set `itemid`=?,`img_url`=?,`title`=?,`recom`=?,`remark`=?,`timestamp`=?,`userid`=?,`sellerid`=?,`nick`=?,`price`=?,`volume`=?,`shopurl`=?,`rate`=?,`zk_price`=? where itemid=?";
		if(ib.getZkPrice()==0||ib.getZkPrice()==0.0){
			return false;
		}
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ib.itemid);
			ps.setString(2, ib.imgurl);
			ps.setString(3, ib.title);
			ps.setString(4, ib.recom);
			ps.setString(5, ib.remark);
			ps.setLong(6, System.currentTimeMillis());
			ps.setInt(7, ib.userid);
			ps.setString(8, ib.sellerid);
			ps.setString(9, ib.nick);
			ps.setDouble(10, ib.price);
			ps.setString(11, ib.volume);
			ps.setString(12, ib.shopurl);
			ps.setDouble(13,ib.rate);
			ps.setDouble(14,ib.zkPrice);
			ps.setString(15,ib.itemid);
			int i = ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	
	public static ArrayList<TabBean> getTabList(){
		ArrayList<TabBean> list  = null;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select id,title,position from `youhui_datamining`.`Item_Add_TAB`;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			while(rs.next()){
				if(list == null) list = new ArrayList<TabBean>();
				TabBean tb= new TabBean();
				tb.id = rs.getInt(1);
				tb.title = rs.getString(2);
				tb.position = rs.getInt(3);
				list.add(tb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return list;
	}

	public static ArrayList<MenuBean> getMenu(int tabId){
		ArrayList<MenuBean> list  = null;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select id,title,position from `youhui_datamining`.`Item_Add_MENU` where tab_id = ? order by position;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1, tabId);
			ResultSet rs  = ps.executeQuery();
			while(rs.next()){
				if(list == null) list = new ArrayList<MenuBean>();
				MenuBean tb= new MenuBean();
				tb.id = rs.getInt(1);
				tb.title = rs.getString(2);
				tb.position = rs.getInt(3);
				tb.tab_id = tabId;
				list.add(tb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return list;
	}
	
	public static boolean ifExist(String itemId){
		Connection conn = null;
		PreparedStatement ps  = null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select id from `youhui_datamining`.`Item_Add_Step1` where itemid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
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
	
	public static int getPreItemListTotalNum(String itemId,String titleOrRecom){
		Connection conn = null;
		PreparedStatement ps  = null;
		int num=1;
		String sql = "select count(id) as c from `youhui_datamining`.`Item_Add_Step1` where  status="+STATUS_FINISH+" ";
		if(!itemId.equals("")){
			sql=sql+" and itemId='"+itemId+"' ";
		}else if(!titleOrRecom.equals("")){
			titleOrRecom="%"+titleOrRecom+"%";
			sql=sql+" and (title like '"+titleOrRecom+"' or recom like '"+titleOrRecom+"')";
		}
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			if(rs.next()){
				num=rs.getInt("c");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return num;
	}
	
	
	public static ArrayList<ItemBean> getPreItemListAlready(String itemId,String titleOrRecom,int pageNow,HttpServletRequest request){
		
		titleOrRecom=zm(titleOrRecom);
		request.setAttribute("titleOrRecom", titleOrRecom);
		
		int pagNum=getPreItemListTotalNum(itemId, titleOrRecom);
		if(pagNum%param.numPerPage50==0){
			pagNum=pagNum/param.numPerPage50;
		}else{
			pagNum=pagNum/param.numPerPage50+1;
		}
		request.setAttribute("pagNow", pageNow);
		request.setAttribute("pagNum", pagNum);
		
		ArrayList<ItemBean> list  = null;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select `id`,`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`status`,`sellerid`,`nick`,`price`,`volume`,`shopurl`,`rate`,`zk_price` from `youhui_datamining`.`Item_Add_Step1` where  status="+STATUS_FINISH+" ";
		if(!itemId.equals("")){
			sql=sql+" and itemId='"+itemId+"' ";
		}else if(!titleOrRecom.equals("")){
			titleOrRecom="%"+titleOrRecom+"%";
			sql=sql+" and (title like '"+titleOrRecom+"' or recom like '"+titleOrRecom+"')";
		}
		int a=(pageNow-1)*param.numPerPage50;
		sql=sql+" order by timestamp desc limit "+a+","+param.numPerPage50+";";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			while(rs.next()){
				if(list == null) list = new ArrayList<ItemBean>();
				ItemBean tb= new ItemBean();
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
				tb.rate=rs.getDouble(15);
				tb.zkPrice=rs.getDouble(16);
//				List<ItemHisBean> ihb=ItemHisDAO.getInstance().getInfo(tb.itemid);
//				String tagNames="";
//				String keyNames="";
//				for(int i=0;i<ihb.size();i++){
//					
//					if(ihb.get(i).getType().equals("tagItem")){
//						tagNames=tagNames+ihb.get(i).getTokName()+",";
//					}else if(ihb.get(i).getType().equals("keywordItem")){
//						keyNames=keyNames+ihb.get(i).getTokName()+",";
//					}
//				}
//				if(tagNames.length()>0){
//					tagNames=tagNames.substring(0,tagNames.length()-1);
//				}
//				if(keyNames.length()>0){
//					keyNames=keyNames.substring(0,keyNames.length()-1);
//				}
//				tb.setTagNames(tagNames);
//				tb.setKeyNames(keyNames);
				
				list.add(tb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return list;
	}
	
	public static ArrayList<ItemBean> getPreItemList(){
		ArrayList<ItemBean> list  = null;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select `id`,`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`status`,`sellerid`,`nick`,`price`,`volume`,`shopurl` from `youhui_datamining`.`Item_Add_Step1` where  status="+STATUS_FINISH+" order by id desc;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			while(rs.next()){
				if(list == null) list = new ArrayList<ItemBean>();
				ItemBean tb= new ItemBean();
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
				
				list.add(tb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return list;
	}
	
	public static int getTotalPage(){
		int tmp=-1;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select count(id) as c from `youhui_datamining`.`Item_Add_Step1` where status="+STATUS_IS_UPDATE+" ;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			if(rs.next()){
				tmp=rs.getInt("c");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return tmp;
	}
	
	public static ArrayList<ItemBean> getPreItemList2(int pagNow){
		int a=(pagNow-1)*20;
		ArrayList<ItemBean> list  = null;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select `id`,`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`status`,`sellerid`,`nick`,`price`,`volume`,`shopurl`,`rate`,`zk_price` from `youhui_datamining`.`Item_Add_Step1` where status="+STATUS_IS_UPDATE+" order by id desc limit ?,20;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1,a);
			ResultSet rs  = ps.executeQuery();
			while(rs.next()){
				if(list == null) list = new ArrayList<ItemBean>();
				ItemBean tb= new ItemBean();
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
				tb.rate=rs.getDouble("rate");
				tb.zkPrice=rs.getDouble("zk_price");
				list.add(tb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return list;
	}
	
	public static ArrayList<ItemBean> getPreItemList4(){
		ArrayList<ItemBean> list  = null;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select `id`,`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`status`,`sellerid`,`nick`,`price`,`volume`,`shopurl` from `youhui_datamining`.`Item_Add_Step1` where status="+STATUS_NORMAL+" order by id desc;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			while(rs.next()){
				if(list == null) list = new ArrayList<ItemBean>();
				ItemBean tb= new ItemBean();
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
				list.add(tb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return list;
	}
	
	public static ArrayList<ItemBean> getPreItemList3(){
		ArrayList<ItemBean> list  = null;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select `id`,`itemid`,`img_url`,`title`,`recom`,`remark`,`timestamp`,`userid`,`status`,`sellerid`,`nick`,`price`,`volume`,`shopurl` from `youhui_datamining`.`Item_Add_Step1` where status="+STATUS_NORMAL+" order by id desc;";
		try {
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			while(rs.next()){
				if(list == null) list = new ArrayList<ItemBean>();
				ItemBean tb= new ItemBean();
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
				list.add(tb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		
		return list;
	}
	
	public static int Login(String username,String password) {
		int userid = -1;
		if (username != null && password != null && !"".equals(username)
				&& !"".equals(password)) {
			Connection conn = SQL.getInstance().getConnection();
			PreparedStatement s  = null;
			try {

				s = conn.prepareStatement("SELECT id FROM youhui_v3.m_user where  `username`=? and `password`=?;");
				s.setString(1, username);
				s.setString(2, password);
				
				ResultSet rs = s.executeQuery();

				if (rs.next()) {
					userid = rs.getInt("id");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			SQL.getInstance().release(s, conn);
		}
		return userid;
	}
	
	public static boolean setStatus(String itemId) {
			Connection conn = SQL.getInstance().getConnection();
			PreparedStatement s  = null;
			try {

				s = conn.prepareStatement("update `youhui_datamining`.`Item_Add_Step1` set status="+STATUS_FINISH+" where  `itemid`=?;");
				s.setString(1, itemId);
			if(s.executeUpdate()>0){
				return true;
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			SQL.getInstance().release(s, conn);
		return false;
	}

	public static String zm(String str){
		byte[] b;
		String tmp="";
		try {
			b = str.getBytes("iso8859-1");
			tmp=new String(b,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
	
}
