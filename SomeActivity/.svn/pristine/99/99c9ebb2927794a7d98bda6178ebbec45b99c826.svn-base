package cn.suishou.some.talk.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.some.response.HeaderBean;
import cn.suishou.some.response.ResponseBean;
import cn.suishou.some.response.Enums.ActionStatus;
import cn.suishou.some.talk.bean.Message;
import cn.suishou.some.talk.service.MessageService;
import cn.suishou.some.talk.service.impl.MessageServiceImpl;
import cn.suishou.some.talk.cache.OperationMsgCacher;

/**
 * 
 * @author weifeng
 * 2014-09-17
 */
@WebServlet("/get_message_servlet")
public class GetMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MessageService msgService = new MessageServiceImpl();
	
    public GetMessageServlet() {
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String format = "json";
		String lastTime = req.getParameter("last_time");
		try{
			List<Message> msgList = null;
			msgList = OperationMsgCacher.getInstance().getMsgListByTimer(lastTime);
					
			if(null == msgList || msgList.size() == 0) {
				resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.NO_RESULT),format));
			} else {
				resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.NORMAL_RETURNED),msgList,"categorys",1,1,format));
			}
		} catch (Exception e){
			e.printStackTrace();
			resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.PARAMAS_ERROR),format));
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
