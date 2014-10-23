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
    <title>任务执行列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript">
	    function delJobDetail(job_id)
		{
	    	$.ajax({
			    url: '<%=request.getContextPath()%>/ad/jobdetail_manager?actionmethod=delJobDetail',
			    type: 'POST',
			    data: {
			    	job_id:job_id
			    },
			    dataType: 'json',
			    timeout: 5000,
			    success: function(json)
			    {
			    	var result = json.result;
			    	if (result == "0")
		    		{
			    		var delElement = document.getElementById(job_id);
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
	    
	    function usernameSelect()
		{
	    	var job_username = $("#username").val();
	    	location.href="<%=request.getContextPath()%>/ad/jobdetail_manager?actionmethod=showJobDetailList&job_username="+job_username;
		}
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">任务执行列表</h1>
	         <hr/>
	         	用户名:
	         	<select id="username" name="username"  onchange="usernameSelect()">
		         	<c:forEach var= "admin_user" items="${AdminUserMap}" >
			         	<c:choose>
							<c:when test="${job_username == admin_user.key}">
								<option value="${admin_user.key}" selected>${admin_user.value}</option>
							 </c:when>
							 <c:otherwise>
							 	<option value="${admin_user.key}">${admin_user.value}</option>
							 </c:otherwise>
						</c:choose>
		         	</c:forEach>
	         	</select>
	         <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th>任务名称</th>
	    				<th>创建人</th>
	    				<th>总数</th>
	    				<th>成功数</th>
	    				<th>创建时间</th>
	    				<th>备注</th>
	    				<th>状态</th>
	    				<th>操作</th>
	    		</thead>
	    		<tbody>
					<c:forEach var="Job_bean" items="${JobDetailList}">
		                <tr id="${Job_bean.id}">
		                	<td>${Job_bean.jobName}</td>
		                 	<td>${Job_bean.userName}</td>
		                 	<td>${Job_bean.allNum}</td>
							<td>${Job_bean.successNum}</td>
	                       	<td>${Job_bean.create_time}</td>
	                       	<td>
	                       		<c:choose>
	                       			<c:when test="${fn:length(Job_bean.remark) > 30}">
	                       				<c:out value="${fn:substring(Job_bean.remark, 0, 30)}......" /> 
	                       			</c:when>
	                       			<c:otherwise>
	                       				${Job_bean.remark}
	                       			</c:otherwise>
                       			</c:choose>
	                       	</td>
	                       	<td>
		                       	<c:choose>
		                       		<c:when test="${Job_bean.status == 0}">
										进行中
									 </c:when>
									 <c:when test="${Job_bean.status == 1}">
										完成
									 </c:when>
								</c:choose>
	                       	</td>
	                       	<td>
	                       		<a href="<%=request.getContextPath()%>/ad/jobdetail_manager?actionmethod=showJobDetail&page=${page}&job_username=${job_username}&job_id=${Job_bean.id}">查看详情</a>
	                       		&nbsp;&nbsp;|&nbsp;&nbsp;
	                       		<a href="#" onclick="delJobDetail('${Job_bean.id}')">删除</a>
	                       	</td>
		                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	    	
	    	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/jobdetail_manager?actionmethod=showJobDetailList&job_username=${job_username}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>