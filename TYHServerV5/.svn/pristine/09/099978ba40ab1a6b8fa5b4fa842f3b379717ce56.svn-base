package cn.youhui.api.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.youhui.dao.MySQLDBIns;
import cn.youhui.ramdata.Tag4UserCacher;
import cn.youhui.ramdata.TagStatsCacher;
import cn.youhui.utils.TPool;

/**
 * Servlet implementation class YHDeleteUserTag
 */
@WebServlet("/tyh/deltag")
public class YHDeleteUserTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(YHDeleteUserTag.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public YHDeleteUserTag() {
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
		String tagId = request.getParameter("tagId");
		String accessToken = request.getParameter("access_token");
		accessToken = accessToken == null ? "" : accessToken;
		StringBuffer result = new StringBuffer();
		StringBuffer sb = null;
		result.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		result.append("<resp>");
		if (uid == null || uid.equals("") || tagId == null || tagId.equals("")) {
			sb = new StringBuffer();
			sb.append("<head>");
			sb.append("<status>1001</status>");
			sb.append("<desc><![CDATA[PARAMAS_ERROR]]></desc>");
			sb.append("</head>");
			result.append(sb);
			result.append("</resp>");
			response.getWriter().write(result.toString());
			return;
		}
		try {
			Tag4UserCacher cacher = Tag4UserCacher.getInstance();
			TagStatsCacher statsCacher = TagStatsCacher.getInstance();
			cacher.removeTag4User(uid, tagId);
			statsCacher.incrDeleteCount(tagId);
			sb = new StringBuffer();
			sb.append("<head>");
			sb.append("<status>1000</status>");
			sb.append("<desc><![CDATA[OK]]></desc>");
			sb.append("<access_token>" + accessToken + "</access_token>");
			sb.append("</head>");
			result.append(sb);
			result.append("</resp>");
			response.getWriter().write(result.toString());
			addStats(uid, tagId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e, e);
			sb.append("<head>");
			sb.append("<status>1007</status>");
			sb.append("<desc><![CDATA[" + e.getMessage() + "]]></desc>");
			sb.append("</head>");
			result.append(sb);
			result.append("</resp>");
			response.getWriter().write(result.toString());
		}
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

	private void addStats(final String uid, final String tagId) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Connection conn = MySQLDBIns.getInstance().getConnection();
				try {
					Statement s = conn.createStatement();
					String sql = "UPDATE `youhui_datamining`.`tyh_tag4user` SET `status` = '0',`timestamp` = '"
							+ System.currentTimeMillis()
							+ "' WHERE `uid` = '"
							+ uid
							+ "' AND `tag_id` = '"
							+ tagId
							+ "' AND `status` = '1'";
					int result = s.executeUpdate(sql);
					if (result > 0) {
						sql = "INSERT INTO `youhui_datamining`.`tyh_tag_stats`(`tag_id`,`delete_count`,`owned_count`,`timestamp`)VALUE('"
								+ tagId
								+ "','1','1','"
								+ System.currentTimeMillis()
								+ "') ON DUPLICATE KEY UPDATE `delete_count` = `delete_count` + 1 ,`owned_count` = `owned_count` - 1,`timestamp` = '"
								+ System.currentTimeMillis() + "'";
						s.executeUpdate(sql);
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

}
