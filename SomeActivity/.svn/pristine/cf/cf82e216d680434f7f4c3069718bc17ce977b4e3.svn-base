package cn.suishou.some.talk.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

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
import cn.suishou.some.exception.BadParameterException;
import cn.suishou.some.talk.cache.OperationMsgCacher;
import cn.suishou.some.util.StringUtils;

/**
 * 
 * @author weifeng
 * 2014-09-17
 */
@WebServlet("/send_message_servlet")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MessageService msgService = new MessageServiceImpl();
	
    public SendMessageServlet() {
    }

    // /send_message_servlet?uid=xx&u_nick=xx&message=xx&aid=xx
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String format = "json";
		String uid = req.getParameter("uid");
		String u_nick = req.getParameter("u_nick");
		u_nick = StringUtils.decode(u_nick);
		String message = req.getParameter("message");
		String aid = req.getParameter("aid");
		String platform = req.getParameter("platform");
		String version = req.getParameter("app_version");
		try {
			String ip = req.getRemoteHost();
			Timestamp generateTime = new Timestamp(new Date().getTime());
			Message msg = new Message();
			msg.setUid(uid);
			msg.setU_nick(u_nick);
			
			if (null == message || "".equals(message.trim())){
				throw new BadParameterException("uid=" + uid + ",u_nick=" + u_nick + "输入的消息不能为空");
			}
			
			msg.setMessage(message);
			if (aid != null){
				msg.setAid(Integer.parseInt(aid));
			}
			msg.setPlatform(platform);
			msg.setVersion(version);
			msg.setIp(ip);
			msg.setGenerateTime(generateTime);
			
			boolean flag = this.msgService.saveMessage(msg);
			if (!flag){
				OperationMsgCacher.getInstance().saveMsg(msg);
				resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.NORMAL_RETURNED),format));
			}else {
				resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.PARAMAS_ERROR),format));
			}
			
		} catch (BadParameterException e){
			e.printStackTrace();
			resp.getWriter().print(new ResponseBean(new HeaderBean(ActionStatus.PARAMAS_ERROR),format));
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
