package cn.youhui.api.servlet2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.youhui.common.Enums;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.RespStatusBuilder;

@WebServlet("/tyh2/keywordcount")
public class YHKeywordCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(YHKeywordCount.class);

	public YHKeywordCount() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	
		String uid = request.getParameter("uid");
		String json = request.getParameter("json");
		
		if(uid==null||json==null||uid.equals("")||json.equals("")){
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
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
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.NORMAL_RETURNED));
			
		 }
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			e.printStackTrace();
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		
	}

}
