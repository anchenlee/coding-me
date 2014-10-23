package cn.suishou.some.talk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.some.dao.UserDAO;
import cn.suishou.some.util.StringUtils;

/**
 * Servlet implementation class SaveUserWin
 */
@WebServlet("/save_user_win")
public class SaveUserWin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SaveUserWin() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String winType = req.getParameter("win_type");
		String answer = req.getParameter("answer");
		answer = StringUtils.decode(answer);
		
		UserDAO.getInstance().saveUseWinInfo(uid, winType, answer);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
