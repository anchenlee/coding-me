package cn.suishou.some.talk.dao;

import java.util.List;

import cn.suishou.some.talk.bean.Message;

public interface MessageDao {

	boolean saveMessage(Message msg);
	
	/**
	 * 返回用户看到的最后一条消息的lastTime到当前时间的所有消息
	 * @param lastTime
	 * @return
	 */
	List<Message> getListMessage(String lastTime);
	
	List<Message> getAllMessage();
	
	void deleteAllMessage();
	
	void reloadR2M();
	
}
