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
import cn.suishou.some.talk.bean.Activity;
import cn.suishou.some.talk.bean.Message;
import cn.suishou.some.talk.cache.OperationMsgCacher;
import cn.suishou.some.talk.service.ActivityService;
import cn.suishou.some.talk.service.MessageService;
import cn.suishou.some.talk.service.impl.ActivityServiceImpl;
import cn.suishou.some.talk.service.impl.MessageServiceImpl;

/**
 * Servlet implementation class TalkServlet
 */
@WebServlet("/talk_init_servlet")
public class TalkServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ActivityService talkService = new ActivityServiceImpl();
	private MessageService msgService = new MessageServiceImpl();
	
	public TalkServlet(){
	}
	// http://localhost:8080/talk/talk_init_servlet?act_name=xx&act_topic=xx&start_time=2014-09-09&end_time=2014-09-20
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String format = "json";
		
		String actName = req.getParameter("act_name");
		String actTopic = req.getParameter("act_topic");
		String startTime = req.getParameter("start_time");
		String endTime = req.getParameter("end_time");
		
		Activity act = new Activity();
		act.setActName(actName);
		act.setActTopic(actTopic);
		act.setStartTime(startTime);
		act.setEndTime(endTime);
		
		try{
			this.talkService.saveActivity(act);
			List<Message> msgList = null;
			msgList = OperationMsgCacher.getInstance().getMsgList();
					
			if (null == msgList){
				msgList = this.msgService.getAllMessage();
			}
			
			if(null == msgList) {
				resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.NO_RESULT),format));
			} else {
				resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.NORMAL_RETURNED),msgList,"categorys",1,1,format));
			}
		} catch (Exception e){
			e.printStackTrace();
			resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.PARAMAS_ERROR),format));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
