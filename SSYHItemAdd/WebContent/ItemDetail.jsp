<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<iframe id="if1" src="http://a.m.taobao.com/i<%=request.getParameter("itemId") %>.htm" style="width: 100%;height: 620px"></iframe>
<iframe id="if2" src="http://item.taobao.com/item.htm?id=<%=request.getParameter("itemId") %>" style="width: 100%;height: 620px"></iframe>
</body>
</html>