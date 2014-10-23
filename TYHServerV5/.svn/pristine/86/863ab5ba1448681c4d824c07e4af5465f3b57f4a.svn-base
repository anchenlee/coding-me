package cn.youhui.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.youhui.bean.GiftBean;
import cn.youhui.bean.JFB_Activity_Bean;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.DateUtil;
import cn.youhui.utils.Encrypt;
import cn.youhui.utils.OuterCode;

/**
 * 集分宝活动查询操作类
 * @author UESR
 *
 */
public class JFB_Act_Manager 
{
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
	
	private static final Logger log = Logger.getLogger(JFB_Act_Manager.class);
	
	private static JFB_Act_Manager instance = null;
	
	public static Long lastupdatetime = 0L;
	
	public static JFB_Act_Manager getInstance()
	{
		if(instance == null) 
		{
			instance = new JFB_Act_Manager();
		}
		return instance;
	}
	
	public static JFB_Activity_Bean getActBean(String actID)
	{
		JFB_Activity_Bean bean = null;
		
		String sql = "SELECT * FROM `youhui_shpt`.`jfb_activity_reg` where `id`=?;";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		
		try
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, actID);
			ResultSet rtst = ps.executeQuery();
			if (rtst.next())
			{
				bean = new JFB_Activity_Bean();
				
				bean.setId(rtst.getInt("id"));
				bean.setUid(rtst.getString("username"));
				bean.setNick(rtst.getString("realname"));
				bean.setAct_name(rtst.getString("act_name"));
				bean.setPer_num(rtst.getInt("per_num"));
				bean.setClick_url(rtst.getString("click_url"));
				// bean.setCreate_time(CodeUtil.getDateTimeStr(rtst.getLong("create_time")));
				bean.setStatus(rtst.getInt("status"));
				bean.setImg(rtst.getString("img_url"));
				bean.setAct_desc(rtst.getString("act_desc"));
				bean.setTel(rtst.getString("tel"));
				bean.setQQ(rtst.getString("qq"));
				bean.setClick_num(rtst.getInt("click_num"));
				bean.setB_click_num(rtst.getInt("be_click_num"));
			}
		}
		catch (SQLException e) 
		{
			log.error(e, e);
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return bean;
	}
	
	public static JFB_Activity_Bean getActBeanWithConn(String actID, Connection conn)
	{
		JFB_Activity_Bean bean = null;
		
		String sql = "SELECT `id`,`username`,`realname`,`act_name`,`status`,`act_desc`,`img_url`,`tel`,`qq`,`click_num`,`be_click_num`,`per_num`,`click_url` FROM `youhui_shpt`.`jfb_activity_reg` where `id`=?;";
		
		try
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, actID);
			ResultSet rtst = ps.executeQuery();
			if (rtst.next())
			{
				bean = new JFB_Activity_Bean();
				
				bean.setId(rtst.getInt("id"));
				bean.setUid(rtst.getString("username"));
				bean.setNick(rtst.getString("realname"));
				bean.setAct_name(rtst.getString("act_name"));
				bean.setPer_num(rtst.getInt("per_num"));
				bean.setClick_url(rtst.getString("click_url"));
				// bean.setCreate_time(CodeUtil.getDateTimeStr(rtst.getLong("create_time")));
				bean.setStatus(rtst.getInt("status"));
				bean.setImg(rtst.getString("img_url"));
				bean.setAct_desc(rtst.getString("act_desc"));
				bean.setTel(rtst.getString("tel"));
				bean.setQQ(rtst.getString("qq"));
				bean.setClick_num(rtst.getInt("click_num"));
				bean.setB_click_num(rtst.getInt("be_click_num"));
			}
		}
		catch (SQLException e) 
		{
			log.error(e, e);
		} 
		return bean;
	}
	
	public static String DecodeChinessNick(String nickStr)
	{
		String tyh_web_taobaonick = "";
		try 
		{
			tyh_web_taobaonick = URLDecoder.decode(nickStr, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			tyh_web_taobaonick = "";
		}

		return tyh_web_taobaonick;
	}
	
	/**
	 * 检查该用户是否已经领取过该活动的集分宝
	 * @param actID
	 * @param tyh_web_uid
	 * @return
	 */
	public static boolean checkUserToAct(String actID, String tyh_web_uid, Connection conn)
	{
		boolean flag = false;
		
		String sql1 = "SELECT `id` FROM `youhui_shpt`.`jfb_activity_detail` where `uid`=? AND `activity_id`=? limit 1;";
		
		try
		{
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, tyh_web_uid);
			ps.setInt(2, Integer.parseInt(actID));
			
			ResultSet rtst = ps.executeQuery();
			if (rtst.next())
			{
				flag = true;
			}
		}
		catch (SQLException e) 
		{
			log.error(e, e);
		}
		
		return flag;
	}
	
	/**
	 * 检查该用户当天已经领取的次数是否超值
	 * @param actID
	 * @param tyh_web_uid
	 * @return
	 */
	public static boolean checkUserDateNum(String tyh_web_uid, Connection conn)
	{
		boolean flag = false;
		
		String sql1 = "SELECT count(`id`) as count FROM `youhui_shpt`.`jfb_activity_detail` where `uid`=? AND click_time < ? AND click_time > ?;";
		
		try
		{
//			String date_str = getDateStr();
//			long start_time_mills = getTimeMillis(date_str, "00:00:00");
//			long end_time_mills = getTimeMillis(date_str, "23:59:59");
			
			long start_time_mills = DateUtil.getMidnight(new Date());
			long end_time_mills = DateUtil.getMidnight(new Date(System.currentTimeMillis() + 24*60*60*1000));
			
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, tyh_web_uid);
			ps.setLong(2, end_time_mills);
			ps.setLong(3, start_time_mills);
			
			ResultSet rtst = ps.executeQuery();
			if (rtst.next())
			{
				int count = rtst.getInt("count");
				if (count >= 3)
				{
					flag = true;
				}
			}
		}
		catch (Exception e) 
		{
			log.error(e, e);
		} 
		return flag;
	}
	
	/**
	 * 检查该用户当天已经领取的次数是否超值
	 * @param actID
	 * @param tyh_web_uid
	 * @return
	 */
	public static boolean checkUserDateNum_2(String tyh_web_uid, Connection conn)
	{
		boolean flag = false;
		
		String sql1 = "SELECT count(`id`) as count FROM `youhui_shpt`.`jfb_activity_detail` where `uid` = ? AND `status` = 1 AND click_time < ? AND click_time > ?;";
		
		try
		{
			String date_str = getDateStr();
			String start_time_mills = String.valueOf(getTimeMillis(date_str, "00:00:00"));
			String end_time_mills = String.valueOf(getTimeMillis(date_str, "23:59:59"));
			
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, tyh_web_uid);
			ps.setString(2, end_time_mills);
			ps.setString(3, start_time_mills);
			
			ResultSet rtst = ps.executeQuery();
			if (rtst.next())
			{
				int count = rtst.getInt("count");
				if (count >= 3)
				{
					flag = true;
				}
			}
			else
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			log.error("checkUserDateNum_2 error", e);
			flag = true;
		} 
		return flag;
	}
	
	/**
	 * 检查该用户是否已经领取过该活动的集分宝_2
	 * @param actID
	 * @param tyh_web_uid
	 * @return
	 */
	public synchronized String checkUserToAct_2(String actID, String tyh_web_uid, Connection conn)
	{
		String ser_num = "0";
		
		try
		{
//			boolean flag = hasCommitToday(tyh_web_uid, conn);
//			if (flag)
//			{
//				// log.info(tyh_web_uid + "已领取三次及三次以上");
//				return "0";
//			}
			
			String sql1 = "SELECT `id`,`status`,`ser_num` FROM `youhui_shpt`.`jfb_activity_detail` where `uid`=? AND `activity_id`=? AND `status` = 0 AND `ser_num` <> '0';";
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, tyh_web_uid);
			ps.setInt(2, Integer.parseInt(actID));
			
			ResultSet rtst = ps.executeQuery();
			if (rtst.next())
			{
				String detail_id = rtst.getString("id");
				String status = rtst.getString("status");
				ser_num = rtst.getString("ser_num");
				
				if (null == detail_id || "".equals(detail_id) || "null".equalsIgnoreCase(detail_id))
				{
					return "0";
				}
				
				if (status.equals("0") && !ser_num.equals("0"))
				{
					boolean flag_2 = JFB_Act_Manager.getInstance().send_JFB(detail_id, conn);
					if (flag_2)
					{
						return ser_num;
					}
					else
					{
						return "0";
					}
				}
				else
				{
					return "0";
				}
			}
			else
			{
				// System.out.println("actID=" + actID + ";tyh_web_uid=" + tyh_web_uid + ";无尚未提交的集分宝");
				return "0";
			}
		}
		catch (SQLException e) 
		{
			log.error("checkUserToAct_2 error", e);
			return "0";
		} 
	}
	
	/**
	 * 检查当天提交至用户的集分宝账户的次数是否超过3次
	 * @param uid
	 * @return
	 */
	public static boolean hasCommitToday(String uid, Connection conn)
	{
		boolean flag = false;
		
		SimpleDateFormat ft1 = new SimpleDateFormat("yyyy/MM/dd");
		String today = ft1.format(new Date());
		
		// String sql = "select `id` from youhui_cn_fanli.tyh_activity_join where activity_id = '8' and `activity_unique_id` = ?;";
		
		String sql = "select count(`id`) as acount from youhui_cn_fanli.tyh_activity_join where activity_id = '8' and `uid` = ? and `date`=?;";
		
		try 
		{
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, today);
			ResultSet rs = s.executeQuery();
			if (rs.next())
			{
				int count = rs.getInt("acount");
				if(count >= 3)
				{
					flag = true;
				}
			}
		}
		catch (Exception e){
			log.error("hasCommitToday error", e);
			return true;
		}
		return flag;
	}
	
	/**
	 * 检查当天该集分宝活动是否还有余额
	 * @param actID
	 * @return
	 */
	public boolean checkEnoughJFB(String actID, Connection conn)
	{
		boolean flag = false;
		
		String sql1 = "SELECT (`click_num` + `gift_click_num`) AS click_num FROM `youhui_shpt`.`jfb_activity_reg` where " +
				" `id` = ? AND `be_click_num` < (`click_num` + `gift_click_num`);";
		
		String sql2 = "SELECT COUNT(`id`) as count FROM `youhui_shpt`.`jfb_activity_detail` where " +
				" `activity_id` = ?;";
		
		try{
			int click_num = 0;
			int all_count = 0;
			
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setInt(1, Integer.parseInt(actID));
			
			ResultSet rtst = ps.executeQuery();
			if (rtst.next())
			{
				click_num = rtst.getInt("click_num");
			}
			else
			{
				return false;
			}
			
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, Integer.parseInt(actID));
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				all_count = rtst.getInt("count");
			}
			
			if (all_count < click_num)
			{
				flag = true;
			}
		}
		catch (SQLException e) 
		{
			log.error("checkEnoughJFB error", e);
		}
		
		return flag;
	}
	
	public boolean checkEnoughJFB2(String actID, Connection conn)
	{
		boolean flag = false;
		
		String sql1 = "SELECT id FROM `youhui_shpt`.`jfb_activity_reg` where " +
				" `id` = ? AND `be_click_num` < (`click_num` + `gift_click_num`);";
		
		try{
			
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setInt(1, Integer.parseInt(actID));
			ResultSet rtst = ps.executeQuery();
			if (rtst.next()){
				return true;
			}
		}
		catch (SQLException e) 
		{
			log.error("checkEnoughJFB error", e);
		}
		
		return flag;
	}
	
	/**
	 * 为用户锁定集分宝</p>
	 * 该方法同步进行 
	 * @param actID
	 * @param tyh_web_uid
	 * @param nick
	 * @param act_bean
	 * @return
	 * @throws SQLException
	 */
	public synchronized int lock_JFB(String actID, String tyh_web_uid, String nick, JFB_Activity_Bean act_bean, Connection conn) 
			throws SQLException
	{
		int flag = 1;
		
		String sql = "INSERT INTO `youhui_shpt`.`jfb_activity_detail`(`id`,`uid`,`nick`,`click_time`,`jfb_num`,`activity_id`,`status`,`ser_num`) values (?,?,?,?,?,?,?,?);";
		
		try
		{
//			boolean hasGetJFB = JFB_Act_Manager.checkUserToAct(actID, tyh_web_uid, conn);
//			if (hasGetJFB)
//			{
//				return 1;
//			}
			
			boolean hasGetEnoughJFB = JFB_Act_Manager.checkUserDateNum(tyh_web_uid, conn);
			if (hasGetEnoughJFB)
			{
				return 1;
			}
			
			boolean enoughJFB_Flag = JFB_Act_Manager.getInstance().checkEnoughJFB2(actID, conn);
			if (!enoughJFB_Flag)
			{
				return 1;
			}
			
//			Calendar cal = Calendar.getInstance();
//			String dateStr = String.format("%1$tY%1$tm%1$td", cal);
			
			String dateStr = ft.format(new Date());
			
			String ser_num = dateStr + tyh_web_uid + actID;
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tyh_web_uid + "#" + actID);
			ps.setString(2, tyh_web_uid);
			ps.setString(3, nick);
			ps.setString(4, String.valueOf(System.currentTimeMillis()));
			ps.setInt(5, act_bean.getPer_num());
			ps.setString(6, actID);
			ps.setInt(7, 0);
			ps.setString(8, ser_num);
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = 0;
			}
			else	
			{
				return 1;
			}
			
		}
		catch (SQLException e) 
		{
			log.error("lock_JFB error:", e);
			return 1;
		} 
		
		return flag;
	}
	
	/**
	 * 发送集分宝 将状态改成2(中间过渡值)
	 * @param detail_id
	 * @param conn
	 * @return
	 */
	public boolean send_JFB(String detail_id, Connection conn)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_shpt`.`jfb_activity_detail` SET `status` = 2  WHERE `id` = ? AND `status` = 0;";

		try
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, detail_id);
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (SQLException e) 
		{
			log.error("JFB_Act_Manager.send_JFB error", e);
			flag = false;
		} 
		return flag;
	}
	
	/**
	 * 集分宝活动领取提交后 更改商户平台的detail记录状态为1(最终成功状态)
	 * @param ser_num
	 * @return 0:执行成功  1:join表并没有成功插入相关联的记录  2:detail表无记录  3:执行失败
	 */
	public static int updateJFBDetailStatus(String uid, String ser_num, Connection conn)
	{
		int flag = 3;
		
//		String checkJoinSQL = "select `uid` from `youhui_cn_fanli`.`tyh_activity_join` WHERE `activity_id` = '8' AND `uid` = ? AND `activity_unique_id` = ?;";
		
//		String sqlQueryID = "SELECT `id` FROM `youhui_shpt`.`jfb_activity_detail` WHERE `ser_num` = ? AND `status` = 2;";
		
		String sql = "UPDATE `youhui_shpt`.`jfb_activity_detail` SET `status` = 1  WHERE `id` = ? AND `status` = 2;";

		try
		{
			String detail_id = null;
			
//			PreparedStatement ps = conn.prepareStatement(checkJoinSQL);
//			ps.setString(1, uid);
//			ps.setString(2, ser_num);
//			ResultSet rtst = ps.executeQuery();
//			if (rtst.next())
//			{
//				String uidDB = rtst.getString("uid");
//				if (uidDB == null || uidDB.equals("") || uidDB.equalsIgnoreCase("null"))
//				{
//					// join表并没有成功插入相关联的记录
//					return 1;
//				}
//			}
//			else
//			{
//				// join表并没有成功插入相关联的记录
//				return 1;
//			}
			
//			ps = conn.prepareStatement(sqlQueryID);
//			ps.setString(1, ser_num);
//			rtst = ps.executeQuery();
//			if (rtst.next())
//			{
//				detail_id = rtst.getString("id");
//			}
//			else
//			{
//				// detail表无记录
//				return 2;
//			}
			
//			if (null != detail_id && !"".equals(detail_id))
//			{
			    PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, detail_id);
				int res = ps.executeUpdate();
				if (res > 0)
				{
					return 0;
				}
				else
				{
					return 3;
				}
//			}
//			else
//			{
//				// detail表无记录
//				return 2;
//			}
		}
		catch (SQLException e) 
		{
			log.error("JFB_Act_Manager.updateJFBDetailStatus error", e);
			flag = 3;
		}
		return flag;
	}
	
	public boolean send_JFB(String uid, String acId)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_shpt`.`jfb_activity_detail` SET `status` = 1   where `uid`=? AND `activity_id`=? AND `status` = 0 ;";

		Connection conn = null;
		try
		{
			conn = MySQLDBIns.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, acId);
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (SQLException e) 
		{
			log.error("JFB_Act_Manager.send_JFB error", e);
			flag = false;
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 客户端访问获取gift列表
	 * @param request
	 * @param uid
	 * @return
	 */
	public List<GiftBean> get_JFB_Act_List(HttpServletRequest request, String uid)
	{
		String platform = request.getParameter("platform");
		List<GiftBean> resultList = new ArrayList<GiftBean>();
		List<String> clickUrlList = new ArrayList<String>();
		
		String sql1 = "SELECT t2.* FROM `youhui_shpt`.`jfb_activity_detail` t1,`youhui_shpt`.`jfb_activity_reg` t2 WHERE " +
							" t1.uid = ? " +
							" AND t1.activity_id = t2.id " +
							" AND t2.plan_date = ?;";
		
		/*
		String sql2 = "SELECT t1.* FROM `youhui_shpt`.`jfb_activity_reg` t1 WHERE " +
							" t1.`id` >= (SELECT floor(RAND() * (SELECT MAX(`id`) FROM `youhui_shpt`.`jfb_activity_reg`)))" + 
							" AND t1.`status` = 1 " +
							" AND t1.`plan_date` = ? " +
							" AND t1.`be_click_num` < t1.`click_num` " +
							" AND t1.`id` NOT IN (SELECT DISTINCT(`activity_id`) FROM `youhui_shpt`.`jfb_activity_detail` WHERE `uid` = ? )  " +
							" ORDER BY t1.`id` LIMIT 3;";
		*/
		
		String sql2 = "SELECT * from (SELECT t1.* FROM `youhui_shpt`.`jfb_activity_reg` t1 WHERE " +
				" t1.`status` = 1 " +
				" AND t1.`plan_date` = ? " +
				" AND t1.plan_time < ? "+
				" AND t1.`be_click_num` < (t1.`click_num` + t1.`gift_click_num`) " +
				" AND t1.`id` NOT IN (SELECT DISTINCT(`activity_id`) FROM `youhui_shpt`.`jfb_activity_detail` WHERE `uid` = ?)  " +
				" ORDER BY Rand()) a ORDER BY a.`per_num` DESC;";

		Connection conn = MySQLDBIns.getInstance().getConnection();
		try
		{
			/*
			String uri = request.getRequestURI();
			String url = request.getRequestURL().toString();
			String contextPath = request.getContextPath();
			String actionValue = url.replaceAll(uri, "") + contextPath + "/jfb/index.jsp";
			*/
			String actionValue = "http://tyhapi.youhui.cn/jfb/index.jsp";
			
			long nowTime = System.currentTimeMillis();
			String dateStr = getDateStr(nowTime);
			
			PreparedStatement ps = conn.prepareStatement(sql1);
			
			ps.setString(1, uid);
			ps.setString(2, dateStr);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				if (resultList.size() >= 3)
				{
					break;
				}
				
				GiftBean gift = new GiftBean();
				
				gift.setGift_id(rs.getString("id"));
				gift.setTitle(rs.getString("act_name"));
				gift.setType("url");
				gift.setDescription(rs.getString("act_desc"));
				gift.setImg(rs.getString("img_url"));
				gift.setImgIpad(rs.getString("pad_img_url"));
				gift.setClickname("查看商品");
				gift.setHasGet(1);
				gift.setActiontype("tagStyleWeb");
				
				String click_url = rs.getString("click_url");
				clickUrlList.add(click_url);
				
				if(click_url.indexOf("http://b17.cn/item?itemid=") > -1){
					String outerCode = OuterCode.getOuterCode(uid, platform, "0", "12", "0");
					click_url = click_url+ "&tyh_outer_code=" + outerCode ;
				}
				
				gift.setActionvalue(click_url);
				/*
				if (uid == null || uid.equals(""))
				{
					gift.setActionvalue(actionValue + "?actID=" + rs.getString("id"));
				}
				else
				{
					String paramStr = rs.getString("id") + "&" + uid;
					paramStr = Encrypt.encode2(paramStr);
					gift.setActionvalue(actionValue + "?param=" + paramStr);
				}
				*/
				
				resultList.add(gift);
			}
			
			rs = null;
			ps = null;
			
			if (resultList.size() < 3)
			{
				ps = conn.prepareStatement(sql2);
				ps.setString(1, dateStr);
				ps.setLong(2, System.currentTimeMillis());
				ps.setString(3, uid);
				
				rs = ps.executeQuery();
				while (rs.next())
				{
					if (resultList.size() == 3)
					{
						break;
					}
					
					GiftBean gift = new GiftBean();
					
					gift.setGift_id(rs.getString("id"));
					gift.setTitle(rs.getString("act_name"));
					gift.setType("url");
					gift.setDescription(rs.getString("act_desc"));
					gift.setImg(rs.getString("img_url"));
					gift.setImgIpad(rs.getString("pad_img_url"));
					
					int be_click_num = rs.getInt("be_click_num");
					int click_num = rs.getInt("click_num");
					if (be_click_num >= click_num)
					{
						gift.setClickname("查看商品");
					}
					else
					{
						gift.setClickname("领取集分宝");
					}
					
					gift.setHasGet(0);
					gift.setActiontype("tagStyleWeb");
					
					
					String click_url = rs.getString("click_url");
					if (checkActRepeat(click_url, clickUrlList))
					{
						continue;
					}
					clickUrlList.add(click_url);
					
					if (uid == null || uid.equals("")){
						// gift.setActionvalue(actionValue + "?actID=" + rs.getString("id"));
						
						gift.setActionvalue(click_url);
					}else{
						String paramStr = rs.getString("id") + "&" + uid;
						paramStr = Encrypt.encode2(paramStr);
						gift.setActionvalue(actionValue + "?param=" + paramStr);
					}
					
					if(click_url.indexOf("http://b17.cn/item?itemid=") > -1){
						String outerCode = OuterCode.getOuterCode(uid, platform, "0", "12", "0");
						gift.setActionvalue(gift.getActionvalue()+ "&tyh_outer_code=" + outerCode );
					}
					
					resultList.add(gift);
				}
			}
			
			if (resultList.size() > 3)
			{
				resultList = resultList.subList(0, 3);
			}
		}
		catch (SQLException e) 
		{
			log.error(e, e);
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		
		return resultList;
	}
	
	public static boolean checkActRepeat(String click_url, List<String> clickUrlList)
	{
		boolean flag = false;
		if (clickUrlList != null && clickUrlList.size() > 0)
		{
			for (String url : clickUrlList)
			{
				if (url.equalsIgnoreCase(click_url))
				{
					flag = true;
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * 获取日期
	 * @param mills
	 * @return
	 */
	public static String getDateStr(long mills)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date(mills));
		return dateStr;
	}
	
	/**
	 * 获取某日某时刻毫秒数
	 * @param dateStr 年月日格式：2013-07-25
	 * @param timeStr 时分秒格式：15:17:16
	 * @return 某日某时刻毫秒数
	 * @throws ParseException
	 */
	public static long getTimeMillis(String dateStr, String timeStr) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//此处会抛异常
		java.util.Date date = sdf.parse(dateStr + " " + timeStr);
		
		//获取毫秒数
		long longDate = date.getTime();
		
		return longDate;
	}
	
	/**
	 * 获取日期字符串,格式：2013-09-11
	 * @return
	 */
	public static String getDateStr()
	{
		Calendar cal = Calendar.getInstance();
		String datetime = String.format("%1$tY-%1$tm-%1$td", cal);
		return datetime;
	}
	
	public static void main(String[] args) throws ParseException {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		long s = System.currentTimeMillis();
		JFB_Act_Manager.getInstance().checkUserDateNum("108612506",conn);

		long ss = System.currentTimeMillis();
		System.out.println(ss - s);
		MySQLDBIns.getInstance().release(conn);
	}
	

}
