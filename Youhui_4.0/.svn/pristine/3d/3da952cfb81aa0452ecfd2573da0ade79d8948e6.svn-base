package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.netting.bean.IpadTagBean;
import com.netting.bean.Tag_Bean;
import com.netting.db.DataSourceFactory;

/**
 * 
 * @author
 */
public class Admin_Ipad_Tag_DAO {
	private static Log logger = LogFactory.getLog(Admin_Ipad_Tag_DAO.class);

	public static boolean addIpadTag(String actionType, String actionValue,
			String x, String y, String martix, String divIds, String img,
			String regularTimeImg, long regularStartTime, long regularEndTime) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		boolean flag = false;
		try {
			String sql = "insert into youhui_datamining.tyh_ipad_tags(x,y,enable,create_time,update_time,martix,div_ids,img,action_type,action_value,regular_time_img,regular_start_time,regular_end_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, x);
			ps.setString(2, y);
			ps.setString(3, "1");
			ps.setLong(4, System.currentTimeMillis());
			ps.setLong(5, System.currentTimeMillis());
			ps.setString(6, martix);
			ps.setString(7, divIds);
			ps.setString(8, img);
			ps.setString(9, actionType);
			ps.setString(10, actionValue);
			ps.setString(11, regularTimeImg);
			ps.setLong(12, regularStartTime);
			ps.setLong(13, regularEndTime);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("Admin_Tag_DAO.addIpadTag error:", e);
		} finally {
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return flag;
	}

	public static String getInstanceInsertId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		String flag = "";
		;
		try {
			String sql = "select max(id) as m from youhui_datamining.tyh_ipad_tags where enable = 1;";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = rs.getString("m");
			}
		} catch (Exception e) {
			logger.error("Admin_Tag_DAO.addIpadTag error:", e);
		} finally {
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return flag;
	}

	public static boolean delIpadTag(String divIds, String martix) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		boolean flag = false;
		try {
			String sql = "update youhui_datamining.tyh_ipad_tags set enable = 0 where div_ids=? and martix=? and enable = 1";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, divIds);
			ps.setString(2, martix);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("Admin_Ipad_Tag_DAO.delIpadTag error:", e);
		} finally {
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return flag;
	}

	public static boolean delMartix(String martixId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		boolean flag = false;
		try {
			String sql = "update youhui_datamining.tyh_ipad_tags set enable = 0 where  martix=? and enable = 1";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, martixId);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("Admin_Ipad_Tag_DAO.delMartix error:", e);
		} finally {
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return flag;
	}

	public static boolean updateIpadTag(String actionType, String actionValue,
			String divIds, String martix, String img, String regularTimeImg,
			long regularStartTime, long regularEndTime) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		boolean flag = false;
		try {
			String sql = "update youhui_datamining.tyh_ipad_tags set action_type = ?, action_value = ?, img = ?,regular_time_img = ?,regular_start_time = ?,regular_end_time = ? where div_ids=? and martix=? and enable = 1";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, actionType);
			ps.setString(2, actionValue);
			ps.setString(3, img);
			ps.setString(4, regularTimeImg);
			ps.setLong(5, regularStartTime);
			ps.setLong(6, regularEndTime);
			ps.setString(7, divIds);
			ps.setString(8, martix);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("Admin_Ipad_Tag_DAO.updateIpadTag error:", e);
		} finally {
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return flag;
	}

	public static String martixIds() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String value = "";
		try {
			String sql = "select martix from youhui_datamining.tyh_ipad_tags group by martix;";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				value += rs.getString("martix") + ",";
			}
		} catch (Exception e) {
			logger.error("Admin_Ipad_Tag_DAO.getTagBean error:", e);
		} finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return value;
	}

	public static List<IpadTagBean> getIpadTagsByMartix(String martix) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IpadTagBean> list = new ArrayList<IpadTagBean>();
		try {
			String sql = "select * from youhui_datamining.tyh_ipad_tags where martix = ? and enable = 1;";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, martix);
			rs = ps.executeQuery();
			while (rs.next()) {
				IpadTagBean bean = new IpadTagBean();
				bean.setId(rs.getString("id"));
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				bean.setImg(rs.getString("img"));
				bean.setRegularTimeImg(rs.getString("regular_time_img"));
				bean.setRegularStartTime(rs.getLong("regular_start_time"));
				bean.setRegularEndTime(rs.getLong("regular_end_time"));
				bean.setMartix(rs.getString("martix"));
				bean.setX(rs.getString("x"));
				bean.setY(rs.getString("y"));
				bean.setDivIds(rs.getString("div_ids"));
				bean.setEnable(rs.getString("enable"));
				bean.setCreateTime(rs.getString("create_time"));
				bean.setUpdateTime(rs.getString("update_time"));
			}
		} catch (Exception e) {
			logger.error("Admin_Tag_DAO.getTagBean error:", e);
		} finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}

	public static List<Tag_Bean> getTagsList() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		List<Tag_Bean> list = new ArrayList<Tag_Bean>();
		try {
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where  `parent_id` = 538 and `effective`='0' AND `client_show` = 1 order by `rank` ASC;";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rtst = ps.executeQuery();
			while (rtst.next()) {
				Tag_Bean bean = new Tag_Bean();

				bean.setTag_id(rtst.getString("id"));
				bean.setTag_name(rtst.getString("keyword"));
				bean.setPad_icon(rtst.getString("tag_icon"));

				list.add(bean);
			}
		} catch (Exception e) {
			logger.error("Admin_Tag_DAO.getTagBean error:", e);
		} finally {
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return list;
	}

	public static String getAllMartixLayout() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject json = new JSONObject();
		try {
			String sql = "SELECT * FROM youhui_datamining.tyh_ipad_tags where enable = 1";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String actionType = rs.getString("action_type");
				String actionValue = rs.getString("action_value");
				String martix = rs.getString("martix");
				String x = rs.getString("x");
				String y = rs.getString("y");
				String divIds = rs.getString("div_ids");
				String img = rs.getString("img");
				String regularTimeImg = rs.getString("regular_time_img");
				String regularStartTime = rs.getString("regular_start_time");
				String regularEndTime = rs.getString("regular_end_time");

				JSONObject jo = new JSONObject();

				// SimpleDateFormat sdf = new
				// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				jo.put("id", id);
				jo.put("actionType", actionType);
				jo.put("actionValue", actionValue);
				jo.put("martix", martix);
				jo.put("x", x);
				jo.put("y", y);
				jo.put("divIds", divIds);
				long cur = System.currentTimeMillis();
				if (cur >= Long.parseLong(regularStartTime)
						&& cur <= Long.parseLong(regularEndTime)
						&& !"".equals(regularTimeImg)) {
					jo.put("img", regularTimeImg);
				} else {
					jo.put("img", img);
				}
				// jo.put("regularTimeImg", regularTimeImg);
				// jo.put("regularStartTime", regularStartTime);
				// jo.put("regularEndTime", regularEndTime);
				// if (!"0".equals(regularStartTime)) {
				// jo.put("regularStartDate", sdf.format(new
				// Date(Long.parseLong(regularStartTime))) );
				// }else {
				// jo.put("regularStartDate", "");
				// }
				// if (!"0".equals(regularEndTime)) {
				// jo.put("regularEndDate", sdf.format(new
				// Date(Long.parseLong(regularEndTime))));
				// }else {
				// jo.put("regularEndDate", "");
				// }

				Object jso = json.opt(martix);
				if (jso == null) {
					JSONArray list = new JSONArray();
					list.put(jo);
					json.put(martix, list);
				} else {
					JSONArray list = new JSONArray(jso.toString());
					list.put(jo);
					json.put(martix, list);
				}
			}
			System.out.println(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return json.toString();
	}

	public static String getMaxMartixId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String value = "0";
		try {
			String sql = "select max(martix) as m from youhui_datamining.tyh_ipad_tags where enable = 1;";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("m");
			}
			if (value == null) {
				value = "0";
			}
		} catch (Exception e) {
			logger.error("getMaxMartixId error:", e);
		} finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return value;
	}

	public static boolean addDefaultDiv(int martix, int div_ids) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;
		int i = div_ids - 1;
		String x = "";
		String y = "";
		if (i % 2 == 0) {
			x = "0," + (i / 2);
			y = "1," + (i / 2 + 1);
		} else {
			x = "1," + ((i - 1) / 2);
			y = "2," + ((i - 1) / 2 + 1);
		}
		try {
			String sql = "insert into youhui_datamining.tyh_ipad_tags(martix,div_ids,enable,create_time,update_time,x,y) values(?,?,?,?,?,?,?);";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(martix));
			ps.setString(2, String.valueOf(div_ids));
			ps.setInt(3, 1);
			ps.setLong(4, System.currentTimeMillis());
			ps.setLong(5, System.currentTimeMillis());
			ps.setString(6, x);
			ps.setString(7, y);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("addDefaultDiv error:", e);
		} finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}

	public static String getTagById(String id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject jo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			String sql = "SELECT * FROM youhui_datamining.tyh_ipad_tags where id = ? and enable = 1";
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				jo = new JSONObject();
				String actionType = rs.getString("action_type");
				String actionValue = rs.getString("action_value");
				String martix = rs.getString("martix");
				String x = rs.getString("x");
				String y = rs.getString("y");
				String divIds = rs.getString("div_ids");
				String img = rs.getString("img");
				String regularTimeImg = rs.getString("regular_time_img");
				String regularStartTime = rs.getString("regular_start_time");
				String regularEndTime = rs.getString("regular_end_time");

				jo.put("id", id);
				jo.put("actionType", actionType);
				jo.put("actionValue", actionValue);
				jo.put("martix", martix);
				jo.put("x", x);
				jo.put("y", y);
				jo.put("img", img);
				jo.put("divIds", divIds);
				jo.put("regularTimeImg", regularTimeImg);
				jo.put("regularStartTime", regularStartTime);
				jo.put("regularEndTime", regularEndTime);
				if (!"0".equals(regularStartTime)) {
					jo.put("regularStartDate", sdf.format(new Date(Long
							.parseLong(regularStartTime))));
				} else {
					jo.put("regularStartDate", "");
				}
				if (!"0".equals(regularEndTime)) {
					jo.put("regularEndDate", sdf.format(new Date(Long
							.parseLong(regularEndTime))));
				} else {
					jo.put("regularEndDate", "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		if (jo != null) {
			return jo.toString();
		} else {
			return "";
		}
	}
}
