package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sun.misc.BASE64Decoder;
import cn.youhui.bean.FanliBean;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TimeUtil;

public class FanliManager 
{
	public static java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
	
	public static int PageSize = 20;
	
	public static String getResultStr(String type, String data, int pageNum, String platform){
		String content = decode(data);
		String[] params = content.split("#");
		String uid = params[1];
		
		if ("waihui".equals(type)) 
		{
			uid = "B" + uid;
		}
		float fanli = MoneyManager.getInstance().getFanli(uid);
		
		int pageCount = getPageCount(uid);
		if (pageNum < 1)
		{
			pageNum = 1;
		}
		else if (pageNum > pageCount)
		{
			pageNum = pageCount;
		}
		
		ArrayList<FanliBean> list = getOrderByType(uid, pageNum);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<items>");
		for (FanliBean bean: list)
		{
			sb.append(bean.toXML());
		}
		sb.append("</items>");
		
		StringBuffer result = RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, pageCount, pageNum, sb.toString(), fanli);
		return result.toString();
	}
	
	public static ArrayList<FanliBean> getOrderByType(String uid, int pageNum)
	{
		ArrayList<FanliBean> list = new ArrayList<FanliBean>();
		
		Connection conn = MySQLDBIns.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(pageNum < 1){
			pageNum = 1;
		}
		try 
		{
			String sql = "SELECT * FROM youhui_cn_fanli.duoduo_tradelist where outer_code=? order by pay_time desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.setInt(2, (PageSize * (pageNum - 1)));
			pstmt.setInt(3, (PageSize * pageNum));
			rs = pstmt.executeQuery();
			
			while (rs.next()) 
			{
				FanliBean bean = new FanliBean();
				
				String trade_id = getNoNullString(rs.getString("trade_id"));
				String itemTitle = getNoNullString(rs.getString("item_title"));
				if("1".equals(getNoNullString(rs.getString("ishide"))))
				{
					itemTitle = "XXXXXXXXXXXX";
				}
				String price = getNoNullString(rs.getString("pay_price"));
				String item_num = getNoNullString(rs.getString("item_num"));
				if(item_num == null || item_num.equals(""))
				{
					item_num = "1";
				}
				String thisFanli = df.format(Double.parseDouble(getNoNullString(rs.getString("fxje"))));
				String pay_time = getNoNullString(rs.getString("pay_time"));
				String state = getNoNullString(rs.getString("checked"));
				if ("1".equals(state)){
					state = "审核中";
				}
				else 
				{
					state = "已核对";
				}
				
				bean.setOrderid(trade_id);
				bean.setName(itemTitle);
				bean.setPrice(price);
				bean.setNum(item_num);
				bean.setFanli(thisFanli);
				bean.setTime(pay_time);
				bean.setState(state);
				
				list.add(bean);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static String getAllOrder(String data) 
	{
		StringBuffer result = new StringBuffer();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try 
		{
			String content = decode(data);
			String[] params = content.split("#");
			String uid = params[1];
			
			float fanli = MoneyManager.getInstance().getFanli(uid);
			float waihui = MoneyManager.getInstance().getWaihui(uid);
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM youhui_cn_fanli.duoduo_tradelist where outer_code='" + uid
					+ "' or outer_code='B" + uid + "' order by pay_time desc";
			ResultSet rs1 = stmt.executeQuery(sql);
			
			StringBuffer flsb = new StringBuffer();
			StringBuffer whsb = new StringBuffer();
			
			flsb.append("<fanliitems>");
			flsb.append("<je>").append(fanli).append("</je>");
			
			whsb.append("<waihuiitems>");
			whsb.append("<je>").append(waihui).append("</je>");
			
			while (rs1.next()) 
			{
				String itemTitle = rs1.getString("item_title");
				if("1".equals(rs1.getString("ishide")))
				{
					itemTitle = "XXXXXXXXXXXX";
				}
				
				StringBuffer sb = new StringBuffer();
				sb.append("<item>");
				sb.append("<orderid><![CDATA[" + rs1.getString("trade_id") + "]]></orderid>");
				sb.append("<name><![CDATA[" + itemTitle + "]]></name>");
				sb.append("<price><![CDATA[" + rs1.getString("pay_price") + "]]></price>");
				
				String item_num = rs1.getString("item_num");
				if(item_num==null||item_num.equals(""))
				{
					item_num = "1";
				}
				sb.append("<num><![CDATA[" + item_num + "]]></num>");
				sb.append("<fanli><![CDATA[" + df.format(Double.parseDouble(rs1.getString("fxje"))) + "]]></fanli>");
				sb.append("<time><![CDATA[" + rs1.getString("pay_time") + "]]></time>");
				
				String state = rs1.getString("checked");
				if ("1".equals(state)){
					state = "审核中";
				}else {
					state = "已核对";
				}
				sb.append("<state><![CDATA[" + state + "]]></state>");
				sb.append("</item>");
				
				String outerCode = rs1.getString("outer_code");
				if(outerCode != null 
						&& (outerCode.contains("b") || outerCode.contains("B")))
				{
					whsb.append(sb);
				}
				else
				{
					flsb.append(sb);
				}
			}
			
			flsb.append("</fanliitems>");
			whsb.append("</waihuiitems>");
			result.append(flsb).append(whsb);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			MySQLDBIns.getInstance().release(conn);
		}
		
		return result.toString();
	}
	
	public static int getPageCount(String uid)
	{
		int pageCount = 0;
		
		String sql = "SELECT count(*) as count FROM youhui_cn_fanli.duoduo_tradelist where outer_code='" + uid + "'";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int count = 0;
			if (rs.next())
			{
				count = Integer.parseInt(rs.getString("count"));
			}
			
			if (count > 0)
			{
				pageCount = count / PageSize;
				int mol = count % PageSize;
				if (mol > 0)
				{
					pageCount++;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			MySQLDBIns.getInstance().release(conn);
		}
		
		return pageCount;
	}
	
	public static String decode(String str)
	{
		BASE64Decoder base64 = new BASE64Decoder();
		String content = "";
		try 
		{
			str = new String(base64.decodeBuffer(str));
			content = str.substring(2, str.length() - 2);
			content = new String(base64.decodeBuffer(content));
		}
		catch (Exception e1) 
		{
			return "";
		}
		return content;
	}
	
	public static String getNoNullString(String src)
	{
		if (null == src || "null".equals(src))
		{
			return "";
		}
		else
		{
			return src;
		}
	}
	
	/**
	 * 包含审核中的总返利
	 * @param uid
	 * @param conn
	 * @return
	 */
	public static float getFanliWithReview(String uid ,Connection conn){
		float fanli = 0;

		try {
			String sql = "select sum(fxje) as fanli from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code =? and checked <= 2;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				fanli = Float.parseFloat(df.format(rs.getFloat("fanli")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		return fanli;
	}


	public static float getFanliWithReviewByMonth(String uid ,Connection conn,String year,String month){
		float fanli = 0;
		int yearInt = 0;
		int monthInt = 0;
		try {
			yearInt = Integer.parseInt(year);
			monthInt = Integer.parseInt(month);
		} catch (Exception e) {
			return fanli;
		}
		String moweiData = "";
		if(monthInt == 12){
			moweiData = (yearInt+1)+"-"+"01-01 00:00:00";
		}else{
			moweiData = yearInt+"-"+(monthInt+1)+"-01 00:00:00";
		}
		try {
			String sql = "select sum(fxje) as fanli from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code =? and checked <= 2 and insert_time<? ;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setLong(2, TimeUtil.getTimeMillis(moweiData));
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				fanli = Float.parseFloat(df.format(rs.getFloat("fanli")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		return fanli;
	}

}

