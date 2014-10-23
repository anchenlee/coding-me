package cn.youhui.api.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.RespStatusBuilder;

/**
 * Servlet implementation class YHGetTaggedGoods
 */
@WebServlet("/tyh/keywordcount")
public class YHKeywordCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(YHKeywordCount.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public YHKeywordCount() {
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
		String json = request.getParameter("json");
		
		if(uid==null||json==null||uid.equals("")||json.equals("")){
			response.getWriter()
			.print(RespStatusBuilder
					.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
		
		if(request.getMethod().equalsIgnoreCase("GET")){
			json = new String(json.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		try{
		JSONArray array = new JSONArray(json);
		if(array!=null&&array.length()>0){
			for(int i = 0;i<array.length();i++){
				JSONObject obj = array.getJSONObject(i);
				String keyword = obj.getString("keyword");
				int count = obj.getInt("count");
				keywordCount(keyword, count);
			}
			response.getWriter()
			.print(RespStatusBuilder
					.message(Enums.ActionStatus.NORMAL_RETURNED));
			
		 }
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter()
			.print(RespStatusBuilder
					.message(Enums.ActionStatus.PARAMAS_ERROR));
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

	private void keywordCount(String keyword,int count) {
		
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			String sql = "insert into `youhui_datamining`.`m_keyword_count`(`keyword`,`date`,`count`) values(?,?,?) ON DUPLICATE KEY UPDATE `count` = `count`+values(`count`)";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, keyword);
			s.setString(2, sdf.format(new Date()));
			s.setInt(3, count);
			s.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		
	}

}
