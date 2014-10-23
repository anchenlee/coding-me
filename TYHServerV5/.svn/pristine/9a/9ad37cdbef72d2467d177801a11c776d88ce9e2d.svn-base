<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.youhui.manager.JFB_Act_Manager"%>
<%@page import="cn.youhui.manager.UserManager"%>
<%@page import="cn.youhui.bean.JFB_Activity_Bean"%>
<%@page import="cn.youhui.utils.Encrypt"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.Connection" %>
<%@page import="cn.youhui.dao.MySQLDBIns" %>
<%
	String param = request.getParameter("param");
	String outerCode = request.getParameter("tyh_outer_code");
	if(outerCode == null)
	{
		outerCode = "";
	}
	
	String actID = null;
	String tyh_web_uid = null;
	if (param != null && !param.equals(""))
	{
		String paramDec = Encrypt.decode(param);
		String[] params = paramDec.split("&");
		if (params.length == 2)
		{
			actID = params[0];
			tyh_web_uid = params[1];
		}
	}
	
	String taobao_nick = null;
	JFB_Activity_Bean act_bean = null;
	
	Connection conn = null;
	try
	{
		conn = MySQLDBIns.getInstance().getConnection();
		
		// 检查活动是否仍然存在 
		act_bean = JFB_Act_Manager.getActBeanWithConn(actID, conn); 
		if (null == act_bean)
		{
			response.sendRedirect("http://youhui.cn");
			return;
		}
		else 
		{
			// 检查活动状态
			if (act_bean.getStatus() != 1)
			{
				response.sendRedirect(act_bean.getClick_url());
				return;
			}
			
			// 检查活动点击次数是否超额
			if (act_bean.getB_click_num() >= act_bean.getClick_num())
			{
				response.sendRedirect(act_bean.getClick_url());
				return;
			}
			
			act_bean.setClick_url(act_bean.getClick_url() + "&tyh_outer_code=" + outerCode);
		}
		
		// 检查访问用户是否存在
		taobao_nick = UserManager.getInstance().getUserNickWithConn(tyh_web_uid, conn);
		if (taobao_nick == null)
		{
			response.sendRedirect(act_bean.getClick_url());
			return;
		}
		
		int flag = JFB_Act_Manager.getInstance().lock_JFB(actID, tyh_web_uid, taobao_nick, act_bean, conn);
		if (flag == 1)
		{
			response.sendRedirect(act_bean.getClick_url());
			return;
		}
	}
	catch(Exception e)
	{
		return;
	}
	finally 
	{
		MySQLDBIns.getInstance().release(conn);
	}
	
	/**
	if (param == null || param.equals(""))
	{
		response.sendRedirect(act_bean.getClick_url());
		return;
	}
	*/
%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<title>随手优惠-领取集分宝</title>
<style>
*{padding: 0; margin: 0;}
a{text-decoration: none; color: #999;}
body{margin:0 ; padding:15px; background: #fff; border: 0px solid #ccc; text-align: center;line-height: 1.5;}
p{padding:5px; color: #666; font-size: 16px; text-align: left;}
.c-btn-oran-big {display: inline-block;min-width: 60px;height: 40px;padding: 0 15px;border: 0;background: #f40;text-align: center;text-decoration: none;line-height: 40px;color: #fff;font-size: 14px;font-weight: 700;-webkit-border-radius: 2px;background: -webkit-gradient(linear,0 0,0 100%,color-stop(0,#f50),color-stop(1,#f40));text-shadow: 0 -1px 1px #ca3511;-webkit-box-shadow: 0 -1px 0 #bf3210 inset;min-width: 100%;height: 44px;line-height: 44px;padding: 0;font-size: 24px;text-shadow: 0 -1px 0 #441307;}
</style>
</head>
<body>
	<p>		亲爱的 <strong style="color:#f50"><%out.print(taobao_nick);%></strong> ！您获得了<strong style="color:#f50"><%out.print(act_bean.getPer_num());%></strong>个集分宝，点击立即领取.....^_^ 	</p>
	<p style="color:red;">		(<span id="secondToCount">3</span>秒钟后为您自动跳转......) 	</p>
	<input type="button" class="c-btn-oran-big" value="立即跳转" onclick="getJFB()">
	
	<script type="text/javascript">
	countDown(3);
	function countDown(seconds)
	{
		var secondToCount = document.getElementById('secondToCount');
		secondToCount.innerHTML = seconds;
		if (--seconds >= 0)
		{
			setTimeout("countDown(" + seconds + ")", 700);
		}
		else
		{
			getJFB();
		}
	}
	
	function getJFB()
	{
		location.href='<%=request.getContextPath()%>/tyh2/jfb_act?param=<%=param%>';
	}
	</script>
</body>
</html>
