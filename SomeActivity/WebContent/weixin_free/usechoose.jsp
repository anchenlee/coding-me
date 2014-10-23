<%@page import="cn.suishou.some.dao.UserChooseDao"%>
<%@page import="java.util.*"%>
<%@page import="cn.suishou.some.bean.UserChooseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户选择</title>
</head>
<body>

<table border="1" align="center" id="choose">
<tr>
	<td>头像</td><td>昵称</td><td>选择</td>
</tr>
<%
List<UserChooseBean> ucbList=UserChooseDao.getUserChooseDao().getWeixinUser();
for(int i=0;i<ucbList.size();i++){
%>
<tr >
	<td> <img alt="" width="50" height="50" src=<%=ucbList.get(i).getImg() %>>  </td>
	<td> <%=ucbList.get(i).getNickName() %></td>
	<td> <%=ucbList.get(i).getChoose() %></td>
</tr>
<%
}
%>
</table>

</body>
</html>