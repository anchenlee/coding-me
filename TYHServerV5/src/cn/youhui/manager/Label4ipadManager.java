package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.youhui.bean.AdBean;
import cn.youhui.bean.Label4ipadBean;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.ramdata.Tagid2TagnameCacher;
import cn.youhui.utils.StringUtils;


public class Label4ipadManager {
	private static final Logger log = Logger.getLogger(Label4ipadManager.class);

	private static Label4ipadManager instance;
	
	private Label4ipadManager() {

	}

	public static Label4ipadManager getInstance() {
		if (instance == null)
			instance = new Label4ipadManager();
		return instance;
	}
	
	public String getResultList(boolean isTagId) {
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<resp>");
			sb.append("<head>");
			sb.append("<status>1000</status>");
			sb.append("</head>");
			ArrayList<Label4ipadBean> searchResult = getLabelList();
			sb.append("<data>");
			sb.append("<labelslist>");
			for (int i = 0; i < searchResult.size(); i++) {
				sb.append("<labels row=\""+searchResult.get(i).getRow()+
						 "\" col=\"" + searchResult.get(i).getCol() +
						 "\" width=\"" + searchResult.get(i).getWidth() +
						"\" height=\"" + searchResult.get(i).getHeight() +
						"\">");
				sb.append("<style>");
				sb.append(searchResult.get(i).getStyle());
				sb.append("</style>");
				sb.append("<bgcolor>");
				sb.append(searchResult.get(i).getBgcolor());
				sb.append("</bgcolor>");
				
				ArrayList<AdBean> adList = null;
				if (searchResult.get(i).getStyle().equalsIgnoreCase("singlead"))
				{
					adList = getMinTimeAdBean(searchResult.get(i).getAdids());
				}
				else
				{
					adList = getListByAdids(searchResult.get(i).getAdids());
				}
				
				for(int j = 0; j < adList.size(); j++) 
				{
					sb.append("<label>");

					sb.append("<title><![CDATA[");
					sb.append(adList.get(j).getAd_title()==null?"":adList.get(j).getAd_title());
					sb.append("]]></title>");
					sb.append("<desc><![CDATA[");
					sb.append(adList.get(j).getDescription()==null?"":adList.get(j).getDescription());
					sb.append("]]></desc>");
					sb.append("<img><![CDATA[");
					sb.append(adList.get(j).getImg()==null?"":adList.get(j).getImg());
					sb.append("]]></img>");
					sb.append("<action>");
					sb.append("<type>");
					sb.append("<![CDATA[");
					sb.append(adList.get(j).getActiontype()==null?"":adList.get(j).getActiontype());
					sb.append("]]>");
					sb.append("</type>");
					sb.append("<value>");
					sb.append("<![CDATA[");
					if (adList.get(j).getActionvalue() != null && !"".equals(adList.get(j).getActionvalue())){
						if(!isTagId){
							String keyword = getKeywordName(adList.get(j).getActionvalue());
							if (null != keyword){
								sb.append(keyword);
							}else{
								sb.append(adList.get(j).getActionvalue());
							}
						}else{
							if(StringUtils.isNumeric(adList.get(j).getActionvalue())){
								sb.append(adList.get(j).getActionvalue());
							}else{
								String tagId = Tagid2TagnameCacher.getInstance().getIdbyName(adList.get(j).getActionvalue());
								if(tagId != null && !"".equals(tagId)){
									sb.append(tagId);
								}else{
									sb.append(adList.get(j).getActionvalue());
								}
							}
						}
					}else{
						sb.append("");
					}
					
					sb.append("]]>");
					sb.append("</value>");
					sb.append("</action>");
					
					sb.append("</label>");
				}
				sb.append("</labels>");
			}

			sb.append("</labelslist>");
			sb.append("</data>");

			sb.append("</resp>");

			String result = "";

			result = sb.toString();

			return result;

	}
	
	public ArrayList<Label4ipadBean> getLabelList() {
		ArrayList<Label4ipadBean> list = new ArrayList<Label4ipadBean>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_datamining.label_ipad where status=1 order by date";

			rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				Label4ipadBean bean = new Label4ipadBean();
				bean.setId(rs.getInt("id"));
				bean.setAdids(rs.getString("adids"));
				bean.setStyle(rs.getString("style"));
				bean.setRow(rs.getInt("row"));
				bean.setCol(rs.getInt("col"));
				bean.setWidth(rs.getInt("width"));
				bean.setHeight(rs.getInt("height"));
				bean.setStatus(rs.getString("status"));
				bean.setDate(rs.getString("date"));
				bean.setClassnames(rs.getString("classnames"));
				bean.setBgcolor(rs.getString("bgcolor"));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public String getKeywordName(String id) 
	{
		String keyword = null;
		
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `id`= '" + id + "';";

			rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) 
			{
				keyword = rs.getString("keyword");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if(rs!=null)
				rs.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			MySQLDBIns.getInstance().release(conn);
		}
		
		return keyword;
	}

	
	public ArrayList<AdBean> getListByAdids(String adids) 
	{
		ArrayList<AdBean> list = new ArrayList<AdBean>();
		if (adids == null || adids.equals("") || adids.equalsIgnoreCase("null"))
		{
			return list;
		}
		
		long now = System.currentTimeMillis();
		String sql = "SELECT * FROM youhui_datamining.tyh_ads where `start_time` < " + now + " AND `end_time` > " + now + " AND id = ?";
		
		Connection conn = MySQLDBIns.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			String[] idArray = adids.split(",");
			for (String id : idArray)
			{
				ps.setString(1, id);
				
				rs  = ps.executeQuery();
				if (rs.next()) 
				{
					AdBean ad = new AdBean();
					ad.setAd_id(rs.getString("id"));
					ad.setAd_title(rs.getString("ad_title"));
					ad.setAd_desc(rs.getString("ad_content"));
					ad.setAd_type(rs.getString("ad_type"));
					ad.setStarttime(rs.getString("start_time"));
					ad.setAddtime(rs.getString("timestamp"));
					ad.setEndtime(rs.getString("end_time"));
					ad.setAd_platform(rs.getString("platform"));
					ad.setDescription(rs.getString("description"));
					ad.setImg(rs.getString("img"));
					ad.setActiontype(rs.getString("action_type"));
					ad.setActionvalue(rs.getString("action_value"));
					
					list.add(ad);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				rs.close();
				if(ps!=null)
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MySQLDBIns.getInstance().release(conn);
		}
		MySQLDBIns.getInstance().release(conn);
		return list;
	}
	
	public ArrayList<AdBean> getSingleAdByID(String adids) 
	{
		ArrayList<AdBean> list = new ArrayList<AdBean>();
		
		long now = System.currentTimeMillis();
		String sql = "SELECT * FROM youhui_datamining.tyh_ads where `start_time` < " + now + " AND `end_time` > " + now + " AND id = ?";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			String[] idArray = adids.split(",");
			for (String id : idArray)
			{
				ps.setString(1, id);
				
				rs  = ps.executeQuery();
				if (rs.next()) 
				{
					AdBean ad = new AdBean();
					ad.setAd_id(rs.getString("id"));
					ad.setAd_title(rs.getString("ad_title"));
					ad.setAd_desc(rs.getString("ad_content"));
					ad.setAd_type(rs.getString("ad_type"));
					ad.setStarttime(rs.getString("start_time"));
					ad.setAddtime(rs.getString("timestamp"));
					ad.setEndtime(rs.getString("end_time"));
					ad.setAd_platform(rs.getString("platform"));
					ad.setDescription(rs.getString("description"));
					ad.setImg(rs.getString("img"));
					ad.setActiontype(rs.getString("action_type"));
					ad.setActionvalue(rs.getString("action_value"));
					
					list.add(ad);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				rs.close();
				if(ps!=null)
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MySQLDBIns.getInstance().release(conn);
		}
		MySQLDBIns.getInstance().release(conn);
		return list;
	}
	
	public ArrayList<AdBean> getMinTimeAdBean(String adids)
	{
		ArrayList<AdBean> resultList = new ArrayList<AdBean>();
		
		ArrayList<AdBean> list = this.getSingleAdByID(adids);
		if (list == null || list.size() <= 0)
		{
			return resultList;
		}
		
		AdBean resultBean = list.get(0);
		long minTime = Long.parseLong(resultBean.getEndtime()) - Long.parseLong(resultBean.getStarttime());
		for (AdBean bean : list)
		{
			long time = Long.parseLong(bean.getEndtime()) - Long.parseLong(bean.getStarttime());
			if (time < minTime)
			{
				minTime = time;
				resultBean = bean;
			}
		}
		
		resultList.add(resultBean);
		
		return resultList;
	}
	
}
