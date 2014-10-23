<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>编辑活动-随手4.0</title>
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
	    
		// 提交时检查参数
	    function checkParam()
		{
			var activity_id = $("#activity_id").val();
			if (activity_id == null || activity_id == "")
			{
				alert("活动的ID为空，不合法!");
				var page = "${page}";
				var url = "<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showActivityList&page=" + page;
				location.href=url;
				return false;
			}
			
			var key = $("#key").val();
			var rule = $("#rule").val();
			var frequency = $("#frequency").val();
			
			var name = $("#name").val();
			if (name == null || name == "")
			{
				alert("请填写<名称>!");
				return false;
			}
			
			var description = $("#description").val();
			if (description == null || description == "")
			{
				alert("请填写<介绍>!");
				return false;
			}
			
			var jfbNum = $("#jfbNum").val();
			if (jfbNum == null || jfbNum == "")
			{
				alert("请填写<奖励集分宝数>");
				return false;
			}
			
			var start_time = $("#start_time").val();
			if (start_time == null || start_time == "")
			{
				alert("请填写<开始时间>");
				return false;
			}
			
			var end_time = $("#end_time").val();
			if (end_time == null || end_time == "")
			{
				alert("请填写<结束时间>");
				return false;
			}
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/activity_manager?actionmethod=updateActivity',
    		    type: 'POST',
    		    data: {activity_id:activity_id,
    		    			key:key,
    		    			name:name,
    		    			description:description,
    		    			jfbNum:jfbNum,
    		    			frequency:frequency,
    		    			rule:rule,
    		    			start_time:start_time,
    		    			end_time:end_time
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var page = "${page}";
    					var url = "<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showActivityList&page=" + page;
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("操作失败!");
    		    		return false;
    		    	}
    		    	else if (result == "2")
    		    	{
    		    		alert("开始或结束日期格式错误!");
    		    		return false;
    		    	}
    		    }
    		});
		}
		
		function cancel()
		{
			var page = "${page}";
			var url = "<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showActivityList&page=" + page;
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
	         <h2 class="entry-title">编辑活动</h2>
				<div id="submit-content">
                <div id="formbox">
                		<input type="hidden" id="activity_id" name="activity_id" value="${Activity_bean.id}" />
                		<p>
                             <label><font color="#777">KEY：</font></label>
                             <input type="text" name="key" id="key" value="${Activity_bean.key}" disabled="disabled"/>
                        </p>
                		<p>
                             <label><font color="#777">名称：</font></label>
                             <input type="text" name="name" id="name" value="${Activity_bean.name}" />
                        </p>
                        <p>
                             <label><font color="#777">介绍：</font></label>
                             <input type="text" name="description" id="description" value="${Activity_bean.description}" />
                       	</p>
                       	<p>
                             <label><font color="#777">奖励集分宝数：</font></label>
                             <input type="text" name="jfbNum" id="jfbNum" value="${Activity_bean.jfbNum}" />
                       	</p>
                       	<p>
							<label><font color="#777">规则：</font></label>
							<select id="rule" name="rule">
								<c:forEach var="Rule" items="${ActivityRuleMap}">
									<c:choose>
										<c:when test="${Activity_bean.rule == Rule.key}">
											<option value="${Rule.key}" selected>${Rule.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${Rule.key}">${Rule.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">频次：</font></label>
							<select id="frequency" name="frequency">
								<c:forEach var="Frequency" items="${ActivityFrequencyMap}">
									<c:choose>
										<c:when test="${Activity_bean.frequency == Frequency.key}">
											<option value="${Frequency.key}" selected>${Frequency.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${Frequency.key}">${Frequency.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">开始时间：</font></label>
							<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="${Activity_bean.startTime}" />&nbsp;&nbsp;&nbsp;<font color="red">格式为：2013-11-9 9:23:24</font>
						</p>
						<p>
							<label><font color="#777">结束时间：</font></label>
							<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="${Activity_bean.endTime}" />&nbsp;&nbsp;&nbsp;<font color="red">格式为：2013-11-9 9:23:24</font>
						</p>
						<p>
							<input id="submit" type="button" class="button" onclick="checkParam()" value="提交" />
							<input id="cancel" type="button" class="button" onclick="cancel()" value="取消" />
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