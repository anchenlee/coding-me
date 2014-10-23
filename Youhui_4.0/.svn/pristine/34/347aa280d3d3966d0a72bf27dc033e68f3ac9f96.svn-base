package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.netting.bean.MMixBean;
import com.netting.bean.MixPageStyleBean;
import com.netting.bean.SuiShouAction;
import com.netting.db.DataSourceFactory;

public class AdminMMixDAO {

private static Log logger = LogFactory.getLog(AdminMMixDAO.class);
	
	private static int pageSize = 10;
	
	public static int addMMixBean(String jsonDate,MMixBean bean,Connection conn,String content,String title,String titleIconUrl){
		String pid;
		PreparedStatement ps = null;
		String sql = "insert into youhui_datamining.m_mix_products(item_id,p_id,type,is_levels,tag_id,rank,update_time,status,id,pic,action,height,width,`content`,`title`,`proportion`,`json_data`,`title_ico_url`,`action_type`)" +
				" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int rank = getMaxId(conn);
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getItem_id());
			ps.setString(2, bean.getP_id());
			ps.setString(3, bean.getType());
			ps.setString(4, bean.getIs_Levels());
			ps.setString(5, bean.getTag_id());
			ps.setInt(6, rank);
			ps.setLong(7, System.currentTimeMillis());
			ps.setString(8, "1");
			ps.setInt(9, rank);
			ps.setString(10, bean.getPic());
			ps.setString(11, bean.getSsac().getUrl());
			ps.setString(12, bean.getHeight());
			ps.setString(13, bean.getWidth());
			ps.setString(14, content);
			ps.setString(15, title);
			ps.setString(16, bean.getBili());
			ps.setString(17, jsonDate);
			ps.setString(18, titleIconUrl);
			ps.setString(19, bean.getActionType());
			ps.execute();
			
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return rank;
	}
	
	public static int getMaxId(Connection conn){
		int id = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select max(id) as maxid from youhui_datamining.m_mix_products ;";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				id = rs.getInt("maxid") + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	public static boolean MMixConvert(String content,String tag_id,String wholeContent,String title,String titleIconUrl){
		boolean flag = false;
		Connection conn = null;
		try {
			try {
				conn = DataSourceFactory.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject jso = new JSONObject(content);//whole
			MMixBean wholeBean = getMMixBeanFromJson(jso, "0", tag_id);
			String half_pid = addMMixBean(content,wholeBean,conn,wholeContent,title,titleIconUrl)+"";
			if(!jso.has("half")){
				return true;
			}
			JSONArray jsa = jso.getJSONArray("half");
			for(int i = 0 ; i < jsa.length() ; i ++){
				JSONObject halfJso = jsa.getJSONObject(i);//half
				MMixBean halfBean = getMMixBeanFromJson(halfJso, half_pid, tag_id);
				String quarter_pid = addMMixBean("",halfBean, conn,"","","")+"";
				if(!halfJso.has("quarter")){
					continue;
				}
				JSONArray jsa1 = halfJso.getJSONArray("quarter");
				for(int j = 0; j < jsa1.length() ; j ++){
					JSONObject quarterJso = jsa1.getJSONObject(j);//quarter
					MMixBean quarterBean = getMMixBeanFromJson(quarterJso,quarter_pid , tag_id);
					String eighth_pid = addMMixBean("",quarterBean, conn,"","","")+"";
					if(!quarterJso.has("eighth")){
						continue;
					}
					JSONArray jsa2 = quarterJso.getJSONArray("eighth");
					for(int k = 0 ; k < jsa2.length() ; k ++){
						JSONObject eighthJso = jsa2.getJSONObject(k);//eighth
						MMixBean eighthBean = getMMixBeanFromJson(eighthJso, eighth_pid, tag_id);
						addMMixBean("",eighthBean, conn,"","","");
					}
				}
			}
			flag = true;
		} catch (JSONException e) {
			e.printStackTrace();
			flag = false;
		}finally{
			DataSourceFactory.closeAll(null, null, conn);
		}
		return flag;
	}
	
	public static boolean isExistStyle(String data){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_mix_style_page where json_data=?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, data);
			
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	public static MMixBean getItemJson(String id){
		MMixBean bean = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_mix_products where id=?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()){
				bean = new MMixBean();
				bean.setJsonData(rs.getString("json_data"));
				bean.setContent(rs.getString("content"));
				bean.setId(id);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return bean;
	}
	
	public static boolean addMixStylePage(String content,String biaoshi,String data){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into youhui_datamining.m_mix_style_page(flag,content,json_data) values(?,?,?);";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, biaoshi);
			ps.setString(2, content);
			ps.setString(3, data);
			
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	public static List<MixPageStyleBean> getMMixPageStyle(int page){
		List<MixPageStyleBean> list = new ArrayList<MixPageStyleBean>();
		if(page <= 0 ){
			page = 1;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_mix_style_page limit ?,?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*pageSize);
			ps.setInt(2, pageSize);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				MixPageStyleBean  bean = new MixPageStyleBean();
				bean.setContent(convertHeightWidthItemList(rs.getString("content")));
				bean.setId(rs.getString("id"));
				bean.setPic(rs.getString("pic"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	public static int getMMixPageStylePage(){
		
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) as c FROM youhui_datamining.m_mix_style_page ;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		if(count % pageSize == 0){
			count = count / pageSize;
		}else{
			count = count / pageSize+1;
		}
		return count;
	}
	
	public static String getMixPageStyle(String id){
		String content = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_mix_style_page where id=? ;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				content = (rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return content;
	}
	
	public static String getMixPageStyleJson(String id){
		String content = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_mix_style_page where id=? ;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				content = (rs.getString("json_data"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return content;
	}
	
	public static List<MMixBean> getMMixBeanList(String tagId,int page){
		List<MMixBean> list = new ArrayList<MMixBean>();
		if(page <= 0){
			page = 1;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_mix_products where content !='' and p_id='0' and status='1' and tag_id=? order by id desc limit ?,?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagId);
			ps.setInt(2, (page-1)*pageSize);
			ps.setInt(3, pageSize);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				MMixBean bean = new MMixBean();
				bean.setId(rs.getString("id"));
				bean.setContent(convertHeightWidthItemList(rs.getString("content")));
				bean.setTitle(rs.getString("title"));
				bean.setJsonData(rs.getString("json_data"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	public static int getMMixBeanListPage(String tagId){
		
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) as c FROM youhui_datamining.m_mix_products where content !='' and p_id='0' and status='1' and tag_id=? ;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagId);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		if(count % pageSize == 0){
			count = count / pageSize;
		}else{
			count = count / pageSize+1;
		}
		return count;
	}
	
	public static List<String> getAllChildrenId(String id){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = DataSourceFactory.getConnection();
			List<String> list1 = getChildrenId(id,conn);
			if(list1 != null && list1.size() > 0){
				list.addAll(list1);
				for(String cid : list1){
					List<String> list2 = getChildrenId(cid,conn);
					if(list2 != null && list2.size() > 0){
						list.addAll(list2);
						for(String ccid : list2){
							List<String> list3 = getChildrenId(ccid,conn);
							if(list3 != null && list3.size() > 0){
								list.addAll(list3);
								for(String cccid : list3){
									List<String> list4 = getChildrenId(cccid,conn);
									if(list4 != null && list4.size() > 0){
										list.addAll(list4);
									}
								}
							}
						}
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(null, null, conn);
		}
		return list;
	}
	
	public static List<String> getChildrenId(String id,Connection conn){
		List<String> list = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id from youhui_datamining.m_mix_products where p_id=?;";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean delMMixBean(String id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = getAllChildrenId(id);
		list.add(id);
		String sql = "update youhui_datamining.m_mix_products set status='2' where id=?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			for(String cid : list){				
				ps.setString(1, cid);
				ps.addBatch();
			}
			
			ps.executeBatch();
			flag = true;
		} catch (SQLException e) {
			flag = false;
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
	
	public static MMixBean getMMixBeanFromJson(JSONObject jso,String p_id,String tag_id){
		MMixBean bean = null;
		try {
			bean = new MMixBean();
			bean.setIs_Levels(jso.getString("is_levels"));
			if(jso.has("item_id")){				
				bean.setItem_id(jso.getString("item_id"));
			}
			if(jso.has("height")){
				bean.setHeight(jso.getString("height"));
			}
			if(jso.has("width")){
				bean.setWidth(jso.getString("width"));
			}
			if(jso.has("action_type")){
				bean.setActionType(jso.getString("action_type"));
			}
			if(jso.has("bili")){
				String bili = jso.getString("bili");
				if(bili.length() > 6){
					bili = bili.substring(0,5);
				}
//				DecimalFormat df = new DecimalFormat("#.00");				
				bean.setBili(bili);

			}
			bean.setPic(jso.getString("pic"));
			bean.setSsac(new SuiShouAction(jso.getString("url")));
			bean.setP_id(p_id);
			bean.setTag_id(tag_id);
			bean.setType(jso.getString("type"));
		} catch (JSONException e) {
			System.out.println(jso.toString());
			return null;
		}
		
		return bean;
	}
	
	public static String convertHeightWidthItemList(String content){
		if(content == null || "".equals(content)){
			return "";
		}
		String changeContent = content;
		String pipei = "height:.*?border";
		Pattern pattern = Pattern.compile(pipei);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			String div = matcher.group();
			Pattern pattern1 = Pattern.compile("height.*?width");
			Matcher matcher1 = pattern1.matcher(div);
			if(matcher1.find()){
				String shuzi = matcher1.group();
				shuzi = shuzi.replaceAll("[^0-9.]", "");
				shuzi = shuzi.replaceAll("\\.5.0", ".5");
				changeContent = changeContent.replaceAll(shuzi, Double.parseDouble(shuzi)/2+"");
			}
			
			pattern1 = Pattern.compile("width.*?border");
			matcher1 = pattern1.matcher(div);
			if(matcher1.find()){
				String shuzi = matcher1.group();
				//System.out.println(shuzi+"shuzi");
				shuzi = shuzi.replaceAll("[^0-9.]", "");
				shuzi = shuzi.replaceAll("\\.5.0", ".5");
				//System.out.println(shuzi+"shuzi");
				changeContent = changeContent.replaceAll(shuzi, Double.parseDouble(shuzi)/2+"");				
			}
			
		}
		changeContent = changeContent.replace("id=\"whole\"", " id=\"whole\" style=\"height: 200.2px; width: 201.1px; float: left;\"").replaceAll("border: 1px", "border: 0px").replaceAll("99.5", "98.5").replaceAll("class=\"change\"", "");
//		changeContent = convertHeightWidthNew(changeContent);
		//		System.out.println(changeContent);
		return changeContent;
	}
	
	public static boolean isDeleted(String id){
		boolean flag = false;
		
		return flag;
	}
	
	
	public static String convertHeightWidth(String content){
		if(content == null || "".equals(content)){
			return "";
		}
		String changeContent = content;
		String pipei = "height:.*?border";
		Pattern pattern = Pattern.compile(pipei);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			String div = matcher.group();
			Pattern pattern1 = Pattern.compile("height.*?width");
			Matcher matcher1 = pattern1.matcher(div);
			if(matcher1.find()){
				String shuzi = matcher1.group();
//				System.out.println(shuzi+"shuzi");
				shuzi = shuzi.replaceAll("[^0-9.]", "");
				shuzi = shuzi.replaceAll("\\.5.0", ".5");
				changeContent = changeContent.replaceAll(shuzi, (Double.parseDouble(shuzi)/2)+"");
			}
			
			pattern1 = Pattern.compile("width.*?border");
			matcher1 = pattern1.matcher(div);
			if(matcher1.find()){
				String shuzi = matcher1.group();
//				System.out.println(shuzi+"shuzi");
				shuzi = shuzi.replaceAll("[^0-9.]", "");
				shuzi = shuzi.replaceAll("\\.5.0", ".5");
				//System.out.println(shuzi+"shuzi");
				changeContent = changeContent.replaceAll(shuzi, (Double.parseDouble(shuzi)/2)+"");				
			}
			
		}
		changeContent = changeContent.replace("id=\"whole\"", " id=\"whole\" style=\"height: 203.2px; width: 201.0px; float: left; border:0px;margin-left:4px\"").replaceAll("class=\"change\"", "").replaceAll("whole", "quanbu");
		System.out.println(changeContent);
//		changeContent = convertHeightWidthNew(changeContent);
//		System.out.println(changeContent);
		return changeContent;
	}	
	
	
	public static String convertHeightWidthNew(String content){
		if(content == null || "".equals(content)){
			return "";
		}
//		content = content.replaceAll("\\.5.0", ".5");
		String pipei = "style=\".*?\"";
		Pattern pattern = Pattern.compile(pipei);
		Matcher matcher = pattern.matcher(content);
		System.out.println(content);
		while(matcher.find()){
			String div = matcher.group();
			String replaceStr =  div.substring(0, div.length()-1)+" background-color: rgb(204, 204, 204);\"";
			content = content.replaceAll(div, replaceStr);
			
		}
		System.out.println(content);
		return content;
	}
	
	public static int getCengshu(String content){
		int count = 0;
		if(content == null || "".equals(content)){
			return count;
		}
		int length = content.length();
		content = content.replaceAll("Up", "");
		count = count + (length - content.length())/2;
		length = content.length();
		content = content.replaceAll("Down", "");
		count = count + (length - content.length())/4;
		length = content.length();
		content = content.replaceAll("Left", "");
		count = count + (length - content.length())/4;
		length = content.length();
		content = content.replaceAll("Right", "");
		count = count + (length - content.length())/5;
		return count;
	}
	
	public static boolean addMMix(){
		boolean flag = false;
		try {
//			JSONArray ejsa = new JSONArray();
			JSONObject eJso = new JSONObject();
			eJso.put("is_levels", "1");
			eJso.put("type", "whole");
			eJso.put("pic", "http://img04.taobaocdn.com/bao/uploaded/i4/T1bIhNFq4eXXXXXXXX_!!0-item_pic.jpg");
			eJso.put("url", "http://item.taobao.com/item.htm?ft=t&id=18415198429&mt=");
			eJso.put("item_id", "18415198429");
			MMixConvert(eJso.toString(), "569","","","#ffffff");
			flag = true;
//			ejsa.put(0, eJso);
			
//			JSONObject eJso1 = new JSONObject();
//			eJso1.put("is_levels", "1");
//			eJso1.put("type", "eighth");
//			ejsa.put(1, eJso1);
//			
//			JSONObject hJso = new JSONObject();
//			hJso.put("eJsa", ejsa);
			
			
			
		} catch (JSONException e) {
			flag = false;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		getAllChildrenId("207");
//		System.out.println(convertHeightWidth("height: 292.5px; width: 1459px; border: none; background-color: rgb(255, 255, 255);"));
		
	}
}
