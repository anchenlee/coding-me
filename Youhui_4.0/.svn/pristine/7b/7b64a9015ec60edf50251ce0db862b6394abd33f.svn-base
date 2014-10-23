<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>活动列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript">
    
    	function goto_add_activity()
		{
	    	location.href="<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showAddActivityJSP&page=${page}";
		}
    	
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">活动列表</h1>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showActivityList" method="post" >
		         关键词:<input type="text" name="keyword" value="">
		         <input type="submit" class="button" value="搜索" />
		         <input type="button" class="button" value="新增" onclick="goto_add_activity()" />
	        </form>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th>key</th>
	    			    <th>名称</th>
	    				<th>介绍</th>
	    				<th>频次</th>
	    				<th>奖励集分宝数</th>
	    				<th>有效期</th>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="Activity_bean" items="${ActList}">
	                 <tr id="${Activity_bean.id}">
	                 	<td>${Activity_bean.key}</td>
	                 	<td>${Activity_bean.name}</td>
	                 	<td>${Activity_bean.description}</td>
						<td>
	                       	<c:forEach var="frequency" items="${ActivityFrequencyMap}">
								<c:choose>
									<c:when test="${Activity_bean.frequency == frequency.key}">
										${frequency.value}
									</c:when>
								</c:choose>
							</c:forEach>
                       	</td>
                       	<td>${Activity_bean.jfbNum}</td>
                       	<td>${Activity_bean.startTime}---${Activity_bean.endTime}</td>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showUpdateActivityJSP&activity_id=${Activity_bean.id}&page=${page}">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showSendJFBJSP&activity_id=${Activity_bean.id}&page=${page}">手动发送</a>
	                   	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showActivityList" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>