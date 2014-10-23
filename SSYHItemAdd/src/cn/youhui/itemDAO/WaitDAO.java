package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import cn.youhui.bean.WaitBean;
import cn.youhui.platform.db.SQL;

public class WaitDAO {

	private static WaitDAO instance=null;
	public static WaitDAO getInstance(){
		if(instance==null){
			instance=new WaitDAO();
		}
		return instance;
	}
	private WaitDAO(){}
	
	public int getCount(String itemId){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="select count(id) as c from  youhui_datamining.wait where item_id=?";
		int count=0;
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				count=rs.getInt("c");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return count;
	}
	
	public boolean dellInfo(int id){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="delete from  youhui_datamining.wait where id=?";
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
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
	
	public boolean dellInfo(int id,Connection conn){
		PreparedStatement ps  = null;
		String sql="delete from  youhui_datamining.wait where id=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			if(ps.executeUpdate()>0){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean delTag(String tagId,int id){
		String tagids=getTagIds(id);
		String newTagIds="";
		for(int i=0;i<tagids.split(",").length;i++){
			if(!tagids.split(",")[i].equals(tagId)){
				newTagIds=newTagIds+tagids.split(",")[i]+",";
			}
		}
		if(newTagIds.length()>0){
			newTagIds=newTagIds.substring(0,newTagIds.length()-1);
		}
		if(updateTagIds(id,newTagIds)){
			return true;
		}
		return false;
	}
	
	public boolean addTag(String tagId,int id){
		String tagids=getTagIds(id);
		Set<String> set=new HashSet<String>();
		for(int i=0;i<tagids.split(",").length;i++){
			if(tagids.split(",")[i]!=null&&!"".equals(tagids.split(",")[i])){
				set.add(tagids.split(",")[i]);
			}
		}
		for(int i=0;i<tagId.split(",").length;i++){
			set.add(tagId.split(",")[i]);
		}
		String newTagIds="";
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			newTagIds=newTagIds+it.next()+",";
		}
		if(newTagIds.length()>0){
			newTagIds=newTagIds.substring(0,newTagIds.length()-1);
		}
		if(updateTagIds(id,newTagIds)){
			return true;
		}
		return false;
	}
	
	public boolean delKey(String keywordId,int id){
		String keywordids=getKeyIds(id);
		String newKeywordIds="";
		for(int i=0;i<keywordids.split(",").length;i++){
			if(!keywordids.split(",")[i].equals(keywordId)){
				newKeywordIds=newKeywordIds+keywordids.split(",")[i]+",";
			}
		}
		if(newKeywordIds.length()>0){
			newKeywordIds=newKeywordIds.substring(0,newKeywordIds.length()-1);
		}
		if(updateKeyIds(id,newKeywordIds)){
			return true;
		}
		return false;
	}
	
	public boolean addKey(String keywordId,int id){
		String keywordids=getKeyIds(id);
		Set<String> set=new HashSet<String>();
		for(int i=0;i<keywordids.split(",").length;i++){
			if(keywordids.split(",")[i]!=null&&!"".equals(keywordids.split(",")[i])){
				set.add(keywordids.split(",")[i]);
			}
		}
		for(int i=0;i<keywordId.split(",").length;i++){
			set.add(keywordId.split(",")[i]);
		}
		String newKeywordIds="";
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			newKeywordIds=newKeywordIds+it.next()+",";
		}
		if(newKeywordIds.length()>0){
			newKeywordIds=newKeywordIds.substring(0,newKeywordIds.length()-1);
		}
		if(updateKeyIds(id,newKeywordIds)){
			return true;
		}
		return false;
	}
	
	public boolean delHome(int id){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="update youhui_datamining.wait set home=0 where id=?";
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
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
	
	public boolean addHome(int id){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="update youhui_datamining.wait set home=1 where id=?";
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
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
	
	public String getTagIds(int id){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="select tagids from youhui_datamining.wait where id=?";
		String str="";
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				str=rs.getString("tagids");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return str;
	}
	
	public boolean updateTagIds(int id,String tagids){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="update youhui_datamining.wait set tagids=? where id=?";
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,tagids);
			ps.setInt(2,id);
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
	
	public String getKeyIds(int id){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="select keywordids from youhui_datamining.wait where id=?";
		String str="";
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				str=rs.getString("keywordids");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return str;
	}
	
	public boolean updateKeyIds(int id,String keywordids){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="update  youhui_datamining.wait set keywordids=? where id=?";
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,keywordids);
			ps.setInt(2,id);
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
	
	public boolean insert(String itemId,String original,String tagids,String keywordids,String home){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "insert into youhui_datamining.wait (timestamp,item_id,original,tagids,keywordids,home) values (?,?,?,?,?,?)";
		try{
			conn = SQL.getInstance().getConnection();
			ps= conn.prepareStatement(sql);
			ps.setString(1,String.valueOf(new Date().getTime()));
			ps.setString(2,itemId);
			ps.setString(3,original);
			ps.setString(4,tagids);
			ps.setString(5,keywordids);
			ps.setString(6,home);
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
	
	public List<WaitBean> getAll(int pagNow){
		int a=(pagNow-1)*20;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select * from  youhui_datamining.wait limit ?,20";
		List<WaitBean> list=new ArrayList<WaitBean>();
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,a);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				WaitBean wb=new WaitBean();
				wb.setId(rs.getInt("id"));
				wb.setItemId(rs.getString("item_id"));
				wb.setOriginal(rs.getString("original"));
				wb.setTagids(rs.getString("tagids"));
				wb.setKeywordids(rs.getString("keywordids"));
				wb.setHome(rs.getString("home"));
				list.add(wb);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return list;
	}
	
	public int getTotalNum(){
		int num=-1;
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select count(*) as c from youhui_datamining.wait";
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				num=rs.getInt("c");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		if(num!=-1){
			if(num%20==0){
				num=num/20;
			}else{
				num=num/20+1;
			}
		}
		return num;
	}
	
	public WaitBean getById(int id){
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql = "select * from  youhui_datamining.wait where id=?";
		WaitBean wb=new WaitBean();
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				wb.setId(rs.getInt("id"));
				wb.setItemId(rs.getString("item_id"));
				wb.setOriginal(rs.getString("original"));
				wb.setTagids(rs.getString("tagids"));
				wb.setKeywordids(rs.getString("keywordids"));
				wb.setHome(rs.getString("home"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return wb;
	}
	
	public WaitBean getById(int id,Connection conn){
		PreparedStatement ps  = null;
		String sql = "select * from  youhui_datamining.wait where id=?";
		WaitBean wb=new WaitBean();
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				wb.setId(rs.getInt("id"));
				wb.setItemId(rs.getString("item_id"));
				wb.setOriginal(rs.getString("original"));
				wb.setTagids(rs.getString("tagids"));
				wb.setKeywordids(rs.getString("keywordids"));
				wb.setHome(rs.getString("home"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return wb;
	}
	
}
