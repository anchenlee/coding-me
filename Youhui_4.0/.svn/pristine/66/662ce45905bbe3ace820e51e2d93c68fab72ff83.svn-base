<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>查看任务日志-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
    <script type="text/javascript">
		function cancel()
		{
			var page = "${page}";
			var job_username = "${job_username}";
			var url = "<%=request.getContextPath()%>/ad/jobdetail_manager?actionmethod=showJobDetailList&page=" + page + "&job_username=" + job_username;
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
         
		   <div id="post-8" class="post-8 page type-page status-publish hentry">
	         <h2 class="entry-title">查看任务日志</h2>
				<div id="submit-content">
                <div id="formbox">
                		<p>
                             <label><font color="#777">任务名称：</font></label>
                             <input type="text" name="jobName" id="jobName" value="${JobDetialBean.jobName}"  disabled="disabled"/>
                        </p>
                        <p>
                             <label><font color="#777">创建人：</font></label>
                             <input type="text" name="userName" id="userName" value="${JobDetialBean.userName}"  disabled="disabled"/>
                       	</p>
                       	<p>
							<label><font color="#777">总数：</font></label>
							<input type="text" name="allNum" id="allNum" value="${JobDetialBean.allNum}"  disabled="disabled"/>
						</p>
						<p>
							<label><font color="#777">成功数：</font></label>
							<input type="text" name="successNum" id="successNum" value="${JobDetialBean.successNum}"  disabled="disabled"/>
						</p>
						<p>
							<label><font color="#777">创建时间：</font></label>
							<input type="text" name="create_time" id="create_time" value="${JobDetialBean.create_time}"  disabled="disabled"/>
						</p>
						<p>
							<label><font color="#777">备注：</font></label>
							<textarea rows="10" disabled="disabled">${JobDetialBean.remark}</textarea>
						</p>
						<p>
							<label><font color="#777">状态：</font></label>
							<c:choose>
		                       		<c:when test="${JobDetialBean.status == 0}">
										<input type="text" name="status" id="status" value="进行中" disabled="disabled"/>
									 </c:when>
									 <c:when test="${JobDetialBean.status == 1}">
										<input type="text" name="status" id="status" value="完成" disabled="disabled"/>
									 </c:when>
								</c:choose>
						</p>
						<p>
							<input id="cancel" type="button" class="button" onclick="cancel()" value="返回" />
						</p>
                </div>
                </div>
		</div>
		
	  </div>
	 </div>
	</div>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>