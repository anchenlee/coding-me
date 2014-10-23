package cn.youhui.api.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.ramdata.Tag4UserCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.ramdata.TagStatsCacher;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TPool;

/**
 * Servlet implementation class YHGetUserTag
 */
@WebServlet("/tyh/usertags")
public class YHGetUserTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(YHGetUserTags.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public YHGetUserTags() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String accessToken = request.getParameter("access_token");
		String sex = request.getParameter("sex");
		String page = request.getParameter("page");
		page = page == null ? "1" : page;
		accessToken = accessToken == null ? "" : accessToken;
		uid = uid == null || uid.equals("") ? "000000" : uid;
		int pageInt = 1;
		try {
			pageInt = Integer.parseInt(page);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sex = sex == null ? "0" : sex;
		StringBuffer result = new StringBuffer();
		StringBuffer sb = null;
		result.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		result.append("<resp>");
		if (uid.equals("")) {
			response.getWriter().write(
					RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR)
							.toString());
			return;
		}
		try {
			String ver = TagCacher.getInstance().getTagVer();
			TagStatsCacher statsCacher = TagStatsCacher.getInstance();
			Tag4UserCacher cacher = Tag4UserCacher.getInstance();
			int totalPage = 0;
			ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();

			if (cacher.getTag4UserVer(uid).equals("0") || !cacher.getTag4UserVer(uid).equals(ver)) {// 版本不对
				initUserTags(uid, sex);
			}
			totalPage = cacher.getTagList4UserTotal(uid);
			if (totalPage < 1) {// 列表为空，重新初始化
				initUserTags(uid, sex);
				totalPage = cacher.getTagList4UserTotal(uid);
			}
			list = cacher.getTagList4User(uid, pageInt, totalPage);
			sb = new StringBuffer();
			sb.append("<head>");
			sb.append("<status>1000</status>");
			sb.append("<desc><![CDATA[OK]]></desc>");
			sb.append("<page>" + pageInt + "</page>");
			sb.append("<total>" + totalPage + "</total>");
			sb.append("<access_token>" + accessToken + "</access_token>");
			sb.append("</head>");
			sb.append("<data>");
			// for (KeyCategoryBean cat : list) {
			sb.append("<tags>");
			for (KeywordBean key : list) {
				if (key.getId().equals("609") || cn.youhui.api.servlet2.YHGetAllTags.localLifeTagId.equals(key.getId())){
					continue;
				}else{
					sb.append("<tag>");
					sb.append("<id>" + key.getId() + "</id>");
					sb.append("<name><![CDATA[" + key.getKeyword() + "]]></name>");
					sb.append("<icon><![CDATA[" + key.getIcon() + "]]></icon>");
					sb.append("<showstyle>" + key.getShowStyle() + "</showstyle>");
					sb.append("</tag>");
					statsCacher.incrViewCount(key.getId());
				}
			}
			sb.append("</tags>");
			// }
			sb.append("</data>");
			addStats(list);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e, e);
			sb = new StringBuffer();
			sb.append("<head>");
			sb.append("<status>1007</status>");
			sb.append("<desc><![CDATA[" + e.toString() + "]]></desc>");
			sb.append("</head>");
			e.printStackTrace();
		}
		result.append(sb);
		result.append("</resp>");
		response.getWriter().write(result.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void initUserTags(String uid, String sex) {
		Tag4UserCacher cacher = Tag4UserCacher.getInstance();
		ArrayList<KeywordBean> cacheList = cacher.getTagList4Sex(sex);
		if (sex != "0") {
			cacheList.addAll(cacher.getTagList4Sex("0"));
		}
		ArrayList<KeywordBean> list = compareTag(uid, sex, cacheList);
		cacher.setTagList4User(uid, list);
		cacher.setTag4UserVer(uid);
		// 插入数据库关系表
		insertTag4User2DB(list, uid);
	}

	private ArrayList<KeywordBean> compareTag(String uid, String sex,
			ArrayList<KeywordBean> arrayList) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		list.addAll(arrayList);
		try {
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`tyh_tag4user` WHERE `uid` = '"
					+ uid + "' AND `status` = '0' ;";
			ResultSet rs = s.executeQuery(sql);
			ArrayList<String> disableTags = new ArrayList<String>();
			while (rs.next()) {
				disableTags.add(rs.getString("tag_id"));
			}
			for (KeywordBean bean : arrayList) {
				for (String tagId : disableTags) {
					if (bean.getId().equals(tagId)) {
						list.remove(bean);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	private void insertTag4User2DB(final ArrayList<KeywordBean> list,
			final String uid) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Connection conn = MySQLDBIns.getInstance().getConnection();
				TagStatsCacher cacher = TagStatsCacher.getInstance();
				try {
					Statement s = conn.createStatement();
					for (KeywordBean key : list) {
						try {
							String sql = "INSERT INTO `youhui_datamining`.`tyh_tag4user`(`uid`,`tag_id`,`timestamp`)VALUES('"
									+ uid
									+ "','"
									+ key.getId()
									+ "','"
									+ System.currentTimeMillis() + "');";
							s.executeUpdate(sql);

							sql = "INSERT INTO `youhui_datamining`.`tyh_tag_stats`(`tag_id`,`owned_count`,`timestamp`)VALUE('"
									+ key.getId()
									+ "','1','"
									+ System.currentTimeMillis()
									+ "') ON DUPLICATE KEY UPDATE `owned_count` = `owned_count` + 1,`timestamp` = '"
									+ System.currentTimeMillis() + "'";
							s.executeUpdate(sql);
							cacher.incrOwnedCount(key.getId());
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e, e);
				} finally {
					MySQLDBIns.getInstance().release(conn);
				}
			}
		};
		TPool.getInstance().doit(r);
	}

	private void addStats(final ArrayList<KeywordBean> list) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Connection conn = MySQLDBIns.getInstance().getConnection();
				try {
					Statement s = conn.createStatement();
					for (KeywordBean key : list) {
						String sql = "INSERT INTO `youhui_datamining`.`tyh_tag_stats`(`tag_id`,`view_count`,`timestamp`)VALUE('"
								+ key.getId()
								+ "','1','"
								+ System.currentTimeMillis()
								+ "') ON DUPLICATE KEY UPDATE `view_count` = `view_count` + 1,`timestamp` = '"
								+ System.currentTimeMillis() + "'";
						s.addBatch(sql);
					}
					s.executeBatch();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e, e);
				} finally {
					MySQLDBIns.getInstance().release(conn);
				}
			}
		};
		TPool.getInstance().doit(r);
	}
	
}
