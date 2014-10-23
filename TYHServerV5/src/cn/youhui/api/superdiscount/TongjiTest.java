package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.manager.GetPointUid;


@WebServlet("/TongjiTest")
public class TongjiTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TongjiTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		long start;
		long end;
		String st=request.getParameter("start");
		String en=request.getParameter("end");
		start=Long.parseLong(st);
		end=Long.parseLong(en);
		List<String> uidList=GetPointUid.getInstance().getTradeUid(start,end);
		int dingdanNum=GetPointUid.getInstance().getDingdanNum(uidList);
		int signNum=GetPointUid.getInstance().getSignNum(uidList);
		response.getWriter().print("list size="+uidList.size()+",trade Num="+dingdanNum+",sign Num="+signNum);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
