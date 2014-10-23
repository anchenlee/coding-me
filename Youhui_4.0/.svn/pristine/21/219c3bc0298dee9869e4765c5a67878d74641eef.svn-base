<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>操作日志列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
	
	<script type="text/javascript">
		$(function () {
	        $(".ui_timepicker").datetimepicker({
	            //showOn: "button",
	            //buttonImage: "./css/images/icon_calendar.gif",
	            //buttonImageOnly: true,
	            showSecond: true,
	            timeFormat: 'hh:mm:ss',
	            stepHour: 1,
	            stepMinute: 1,
	            stepSecond: 1
	        });
	    });
    </script>
	
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">操作日志列表</h1>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/opt_log_manager?actionmethod=showlogList" method="post" >
		         关键词:<input type="text" name="keyword" id="keyword" value="${keyword}">
		       	用户名:<select id="username" name="username">
					         	<c:forEach var= "admin_user" items="${AdminUserMap}" >
						         	<c:choose>
										<c:when test="${username == admin_user.key}">
											<option value="${admin_user.key}" selected>${admin_user.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${admin_user.key}">${admin_user.value}</option>
										 </c:otherwise>
									</c:choose>
					         	</c:forEach>
				         	</select>
				模块:<select id="menu_id" name="menu_id">
				         	<c:forEach var= "Menu" items="${MenuMap}" >
					         	<c:choose>
									<c:when test="${menu_id == Menu.key}">
										<option value="${Menu.key}" selected>${Menu.value}</option>
									 </c:when>
									 <c:otherwise>
									 	<option value="${Menu.key}">${Menu.value}</option>
									 </c:otherwise>
								</c:choose>
				         	</c:forEach>
			         	</select>
				操作类型:<select id="opt_type" name="opt_type">
						         	<c:forEach var= "OptType" items="${OptTypeMap}" >
							         	<c:choose>
											<c:when test="${opt_type == OptType.key}">
												<option value="${OptType.key}" selected>${OptType.value}</option>
											 </c:when>
											 <c:otherwise>
											 	<option value="${OptType.key}">${OptType.value}</option>
											 </c:otherwise>
										</c:choose>
						         	</c:forEach>
					         	</select>
			    开始时间:<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="${start_time}" />
			    结束时间:<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="${end_time}" />
			    <input type="submit" class="button" value="搜索" />
			    &nbsp;&nbsp;&nbsp;&nbsp;<font color="red">总数:${all_log_count}</font>
	        </form>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th>操作人</th>
	    				<th>模块</th>
	    				<th>时间</th>
	    				<th>内容</th>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="log_bean" items="${LogList}">
	                 <tr id="${log_bean.id}">
	                 	<td>
	                 		<c:forEach var= "admin_user" items="${AdminUserMap}" >
					         	<c:choose>
									<c:when test="${log_bean.username == admin_user.key}">
										${admin_user.value}
									 </c:when>
								</c:choose>
				         	</c:forEach>
	                 	</td>
	                 	<td>
	                 		<c:forEach var= "Menu" items="${MenuMap}" >
					         	<c:choose>
									<c:when test="${log_bean.menu_id == Menu.key}">
										${Menu.value}
									 </c:when>
								</c:choose>
				         	</c:forEach>
	                 	</td>
						<td>${log_bean.opt_time}</td>
                       	<td>${log_bean.content}</td>
	                   	<td></td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/opt_log_manager?actionmethod=showlogList&username=${username}&menu_id=${menu_id}&opt_type=${opt_type}&start_time=${start_time}&end_time=${end_time}&keyword=${keyword}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>