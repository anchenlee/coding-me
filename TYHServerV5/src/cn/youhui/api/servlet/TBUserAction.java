package cn.youhui.api.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.youhui.common.Enums;
import cn.youhui.common.MySqlExecutor;
import cn.youhui.common.MySqlRunner;
import cn.youhui.utils.RespStatusBuilder;

/**
 * Servlet implementation class TBNewFavoriteList
 */
@WebServlet("/tyh/useraction")
public class TBUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TBUserAction.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TBUserAction() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.getWriter().print(
				RespStatusBuilder.message(Enums.ActionStatus.SERVER_ERROR));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String uid = request.getParameter("uid");
			String json = request.getParameter("json");
			
			if(uid==null||json==null||uid.equals("")||json.equals("")){
				// 返回参数错误
				response.getWriter().print(
						RespStatusBuilder
								.message(Enums.ActionStatus.PARAMAS_ERROR));
				return ;
			}
			
			if(request.getMethod().equalsIgnoreCase("GET")){
				json = new String(json.getBytes("ISO-8859-1"),"UTF-8");
			}
			
			try{
			JSONArray array = new JSONArray(json);
			
			long uidLong = 0;
			uidLong = Long.parseLong(uid);
			if(array!=null&&array.length()>0){
				for(int i =0 ;i<array.length();i++){
					JSONObject obj = array.getJSONObject(i);
					String itemid = obj.getString("itemid");
					String type = obj.getString("type");
					String detail = obj.getString("detail");
					insertAction(uidLong, itemid, type, detail);
					
				}
				response.getWriter().print(
						RespStatusBuilder
								.message(Enums.ActionStatus.NORMAL_RETURNED));
			}
			}catch(NumberFormatException e){
				e.printStackTrace();
				response.getWriter().print(
						RespStatusBuilder
								.message(Enums.ActionStatus.PARAMAS_ERROR));
			}catch(Exception e){
				e.printStackTrace();
				response.getWriter().print(
						RespStatusBuilder
								.message(Enums.ActionStatus.PARAMAS_ERROR));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(
					RespStatusBuilder.message(Enums.ActionStatus.SERVER_ERROR));
		}
	}


	public boolean insertAction(final long uid, final String itemdId,final String type,final String detail) {
		MySqlRunner<Boolean> rr = new MySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				conn.createStatement().execute("use youhui_datamining");
				String sql = "insert into m_user_action(uid,type,detail,item_id, timestamp) values(?,?,?,?,?) ";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, uid + "");
				s.setString(2, type);
				s.setString(3, detail);
				s.setString(4, itemdId);
				s.setString(5, System.currentTimeMillis()+"");
				int i = s.executeUpdate();
				return i>0?true:false;
			}

		};
		MySqlExecutor<Boolean> re = new MySqlExecutor<Boolean>();
		return re.exe(rr);

	}

	

}
