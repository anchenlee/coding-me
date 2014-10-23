<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户权限管理-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript">
    	function updateUserToMenu(menu_id, menu_title)
    	{
    		var user_id = "${admin_user_id}";
    		var user_realname = "${admin_user_realname}";
    		if ($("#"+menu_id).attr("checked"))
    		{
    			eval("loadingImg_" + menu_id + ".style.display=\"\"");
            	eval("opt_success_" + menu_id + ".style.display=\"none\"");
            	
    			$.ajax({
        		    url: '<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=addMenuToUser',
        		    type: 'POST',
        		    data: {
        		    	menu_id:menu_id,
        		    	menu_title:menu_title,
        		    	user_id:user_id,
        		    	user_realname:user_realname
        		    },
        		    dataType: 'json',
        		    timeout: 5000,
        		    success: function(json)
        		    {
        		    	var result = json.result;
        		    	if (result == "0")
       		    		{
        		    		// alert("操作成功!");
        		    		eval("loadingImg_" + menu_id + ".style.display=\"none\"");
            				eval("opt_success_" + menu_id + ".style.display=\"\"");
        		    		return true;
       		    		}
        		    	else if (result == "1")
        		    	{
        		    		alert("删除操作失败!");
        		    		return false;
        		    	}
        		    	else if (result == "2")
        		    	{
        		    		alert("该权限已经存在!");
        		    		return false;
        		    	}
        		    }
        		});
    		}
    		else
    		{
    			eval("loadingImg_" + menu_id + ".style.display=\"\"");
            	eval("opt_success_" + menu_id + ".style.display=\"none\"");
            	
    			$.ajax({
        		    url: '<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=delMenuToUser',
        		    type: 'POST',
        		    data: {
        		    	menu_id:menu_id,
        		    	menu_title:menu_title,
        		    	user_id:user_id,
        		    	user_realname:user_realname
        		    },
        		    dataType: 'json',
        		    timeout: 5000,
        		    success: function(json)
        		    {
        		    	var result = json.result;
        		    	if (result == "0")
       		    		{
        		    		// alert("操作成功!");
        		    		eval("loadingImg_" + menu_id + ".style.display=\"none\"");
            				eval("opt_success_" + menu_id + ".style.display=\"\"");
        		    		return true;
       		    		}
        		    	else if (result == "1")
        		    	{
        		    		alert("删除操作失败!");
        		    		return false;
        		    	}
        		    }
        		});
    		}
    	}
    	
    	function cancel()
		{
			location.href="<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=showAdminUserList&page=${page}";
			location.href=url;
		}
    	
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">用户权限管理</h1>
	         <input type="button" class="button" value="返回" onclick="cancel()" />
		     <hr/>
			 <table class="tablewidget" style="width: 50%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th style="width: 30%"></th>
	    			    <th style="width: 30%"></th>
	    				<th style="width: 30%">栏目</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="Menu" items="${AllMenuMap}">
		                 <tr>
	                       	<td>
								<c:choose>
			                       	<c:when test="${fn:contains(UserMenu, Menu.key)}">
				                       	<input type='checkbox' id="${Menu.key}" onclick= "updateUserToMenu('${Menu.key}', '${Menu.value}')" checked />
			                       	</c:when>							    
			                       	<c:otherwise>
				                       	<input type='checkbox' id="${Menu.key}" onclick= "updateUserToMenu('${Menu.key}', '${Menu.value}')" /> 
			                       	</c:otherwise>
		                       	</c:choose>
							</td>
							<td>
								<img id="loadingImg_${Menu.key}" alt="" src="../img/loading.gif" style="width: 20px;height: 20px;display: none ">
		         				<span  id="opt_success_${Menu.key}" style="display: none">操作成功</span>
		         			</td>
							<td>${Menu.value}</td>
		                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
		</div>
	  </div>
	 </div>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>