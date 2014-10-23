package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.TeJiaCategoryBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.utils.RespStatusBuilder;


@WebServlet("/tyh2/tttjcat")
public class YHGetTTTJCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public YHGetTTTJCategory() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<TeJiaCategoryBean> list = TeJiaCategoryBean.tejiaList();
		response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED,getTeJiaCat(list)).toString());
	}

	public static String getTeJiaCat(List<TeJiaCategoryBean> list){
		StringBuffer sb = new StringBuffer();
		if(list != null &&list.size() >0){
			sb.append("<cats>");
			for(TeJiaCategoryBean bean : list){
				sb.append("<cat>");
				sb.append(bean.toXML());
				sb.append("</cat>");
			}
			sb.append("</cats>");
		}
		return sb.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
