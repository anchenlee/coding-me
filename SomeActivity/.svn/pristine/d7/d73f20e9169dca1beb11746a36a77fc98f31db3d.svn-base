package cn.suishou.some.talk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import cn.suishou.some.common.Constant;
import cn.suishou.some.db.SQL;
import cn.suishou.some.manager.JedisListManager;
import cn.suishou.some.talk.dao.MessageDao;
import cn.suishou.some.talk.bean.Message;

public class MessageDaoImpl implements MessageDao {

	@SuppressWarnings("finally")
	@Override
	public boolean saveMessage(Message msg) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = true;
		// insert into message(generateTime,message,ip,platform,app_version,nick,uid,aid) values(now(),'hello','192.168.110.110','huiwei','app0001','小菜','u001',1);
		String sql = "insert into youhui_talk.message(generateTime,message,ip,platform,app_version,nick,uid,aid) values(?,?,?,?,?,?,?,?)";
		
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, msg.getGenerateTime());
			ps.setString(2, msg.getMessage());
			ps.setString(3, msg.getIp());
			ps.setString(4, msg.getPlatform());
			ps.setString(5, msg.getVersion());
			ps.setString(6, msg.getU_nick());
			ps.setString(7, msg.getUid());
			ps.setInt(8, msg.getAid());
			flag = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQL.getInstance().release(ps, conn);
			return flag;
		}
	}

	@Override
	public List<Message> getListMessage(String lastTime) {
		Connection conn = null;
		PreparedStatement ps = null;
		List<Message> msgList = null;
		String sql = "select nick,generateTime,message from youhui_talk.message where generateTime > ?";
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			Timestamp time = Timestamp.valueOf(lastTime);
			ps.setTimestamp(1, time);
			ResultSet rs = ps.executeQuery();
			msgList = new ArrayList<Message>();
			while (rs.next()){
				Message msg = new Message();
				msg.setU_nick(rs.getString("nick"));
				msg.setMessage(rs.getString("message"));
				msg.setGenerateTime(rs.getTimestamp("generateTime"));
				msgList.add(msg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQL.getInstance().release(ps, conn);
		}
		return msgList;
	}

	@Override
	public List<Message> getAllMessage() {
		Connection conn = null;
		PreparedStatement ps = null;
		List<Message> msgList = null;
		String sql = "select nick,generateTime,message from youhui_talk.message ";
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			msgList = new ArrayList<Message>();
			while (rs.next()){
				Message msg = new Message();
				msg.setU_nick(rs.getString("nick"));
				msg.setMessage(rs.getString("message"));
				msg.setGenerateTime(rs.getTimestamp("generateTime"));
				msgList.add(msg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQL.getInstance().release(ps, conn);
		}
		return msgList;
	}

	@Override
	public void deleteAllMessage() {
		
	}
	
	public void reloadR2M(){
		Connection conn = null;
		PreparedStatement ps = null;
		conn = SQL.getInstance().getConnection();
		try {
			String sql4del = "delete from youhui_talk.message";
			String sql4save = "insert into youhui_talk.message(generateTime,message,ip,platform,app_version,nick,uid,aid) values(?,?,?,?,?,?,?,?) ";
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql4del);
			ps.execute();
			ps = conn.prepareStatement(sql4save);
			JedisListManager jedisList = new JedisListManager(Constant.TALK_MSG);
			List<String> msgList = jedisList.getAll();
			if (null != msgList){
				
				Gson gson = new Gson();
				for (String msg : msgList){
					Message entity = gson.fromJson(msg , Message.class);
					
					ps.setTimestamp(1, entity.getGenerateTime());
					ps.setString(2, entity.getMessage());
					ps.setString(3, entity.getIp());
					ps.setString(4, entity.getPlatform());
					ps.setString(5, entity.getVersion());
					ps.setString(6, entity.getU_nick());
					ps.setString(7, entity.getUid());
					if (null != entity.getAid()){
						ps.setInt(8, entity.getAid());
					} else{
						ps.setInt(8, 0);
					}
					ps.execute();
				}
			}
			conn.commit();
			
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			SQL.getInstance().release(ps, conn);
		}
	}

}
