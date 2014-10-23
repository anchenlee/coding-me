<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>运营用户列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript">
    	function delUser(id, realname)
    	{
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=delAdminUser',
    		    type: 'POST',
    		    data: {
    		    			user_id:id,
    		    			realname:realname
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var delElement = document.getElementById("tr_" + id);
    		    		delElement.parentNode.removeChild(delElement);
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("删除操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
    	}
    	
    	function goto_add_user()
		{
	    	location.href="<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=showAddAdminUserJSP&page=${page}";
		}
    	
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">运营用户列表</h1>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=showAdminUserList" method="post" >
		         关键词:<input type="text" name="keyword" value="">
		         <input type="submit" class="button" value="搜索" />
		         <input type="button" class="button" value="新增" onclick="goto_add_user()" />
	        </form>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th>登录名</th>
	    				<th>真实姓名</th>
	    				<th>上次登陆时间</th>
	    				<th>用户群组</th>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="User" items="${AdminUserList}">
	                 <tr id="tr_${User.id}">
	                 	<td>${User.username}</td>
	                 	<td>${User.realname}</td>
						<td>${User.last_login_time}</td>
                       	<td>
                       		<c:forEach var="AdminUserGroup" items="${AdminUserGroupMap}">
                       			<c:choose>
									<c:when test="${User.group == AdminUserGroup.key}">
										${AdminUserGroup.value}
									 </c:when>
								 </c:choose>
							</c:forEach>
						</td>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=showUpdateAdminUserJSP&user_id=${User.id}&page=${page}">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="#" onclick="delUser('${User.id}', '${User.realname}')">删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	                        <a href="<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=showUpdateAdminUserMenuListJSP&user_id=${User.id}&realname=${User.realname}&page=${page}">权限管理</a>
	                   	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=showAdminUserList" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>