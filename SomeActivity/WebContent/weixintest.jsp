<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信授权</title>
</head>
<body>

<%
	String frUrl=request.getRequestURL().toString();
	System.out.print("ss_url::" + frUrl);
	String formerUrl = "http://d.b17.cn/sactivity/weixintest.jsp";
	String hasAuthorize=request.getParameter("hasAuthorize");
    System.out.println("hasAuthorize="+hasAuthorize);
	if(hasAuthorize!=null){
		if(!hasAuthorize.equals("OK")){
		//	response.sendRedirect("http://weixin.suishou.cn/authorization?formerUrl="+formerUrl);
			response.sendRedirect("http://127.0.0.1:8080/WeixinOpenSystem/authorization?formerUrl="+frUrl);
		}
	}else{
	//	response.sendRedirect("http://weixin.suishou.cn/authorization?formerUrl="+formerUrl);
		response.sendRedirect("http://127.0.0.1:8080/WeixinOpenSystem/authorization?formerUrl="+frUrl);
	}
		
	
 %>

<%
	request.setCharacterEncoding("UTF-8");
	String openid=request.getParameter("openid");
	String name=request.getParameter("nickname");
	String nickname=null;
	if(name!=null){
		nickname=new String(name.getBytes("iso-8859-1"), "UTF-8");
	}
	
	System.out.println("nickname="+nickname);
	String headimg=request.getParameter("headimg");
%>

<span>openId=<%=openid %> </span> <br/>
<span>nickname<%=nickname %> </span> <br/>

<img src=<%=headimg %> />

</body>
</html>