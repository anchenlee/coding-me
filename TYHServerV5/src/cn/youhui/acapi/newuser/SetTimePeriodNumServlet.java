package cn.youhui.acapi.newuser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.utils.BadParameterException;

/**
 * 设置时间段内中奖次数上限
 * @author 
 * @since
 */
@WebServlet("/turnplate/timeperiod")
public class SetTimePeriodNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 0:0:0");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String method = request.getParameter("method");
		if ("addNewTimePeriod".equals(method)) {
			try {
				addNewTimePeriod(request,response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("updateTimePeriod".equals(method)){
			try {
				updateTimePeriod(request,response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void addNewTimePeriod(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			String type = request.getParameter("type");
			String num = request.getParameter("num");
			if ("".equals(startTimeStr) || "".equals(endTimeStr) || "".equals(type) || "".equals(num)) {
				response.getWriter().print("param error");
				return;
			}
			long startTime = sdf.parse(startTimeStr).getTime();
			long endTime = sdf.parse(endTimeStr).getTime();
			boolean flag = HuafeiManager.getInstance().insertNumToTimePeriod(startTime, endTime, num,Integer.parseInt(type));
			if (flag) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("false");
			}
		}catch(BadParameterException e){
			e.printStackTrace();
		}
	}
	
	public void updateTimePeriod(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			String id = request.getParameter("id");
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			String type = request.getParameter("type");
			String num = request.getParameter("num");
			if ("".equals(startTimeStr) || "".equals(endTimeStr) || "".equals(type) || "".equals(num) || "".equals(id)) {
				response.getWriter().print("param error");
				return;
			}
			long startTime = sdf.parse(startTimeStr).getTime();
			long endTime = sdf.parse(endTimeStr).getTime();
			
			boolean flag = HuafeiManager.getInstance().updateNumToTimePeriod(Integer.parseInt(id), startTime, endTime, num, Integer.parseInt(type));
			if (flag) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("false");
			}
		}catch(BadParameterException e){
			e.printStackTrace();
		}
	}

}
