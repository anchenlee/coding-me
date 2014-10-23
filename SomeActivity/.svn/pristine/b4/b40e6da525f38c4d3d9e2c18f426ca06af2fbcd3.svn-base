package cn.suishou.some.talk.service.impl;

import java.util.List;

import cn.suishou.some.talk.dao.MessageDao;
import cn.suishou.some.talk.dao.impl.MessageDaoImpl;
import cn.suishou.some.talk.bean.Message;
import cn.suishou.some.talk.service.MessageService;

public class MessageServiceImpl implements MessageService {

	private MessageDao msgDao = new MessageDaoImpl();
	@Override
	public boolean saveMessage(Message msg) {
		return this.msgDao.saveMessage(msg);
		
	}

	@Override
	public List<Message> getListMessage(String lastTime) {
		List<Message> msgList = this.msgDao.getListMessage(lastTime);
		return msgList;
	}

	@Override
	public List<Message> getAllMessage() {
		List<Message> msgList = this.msgDao.getAllMessage();
		return msgList;
	}

}
