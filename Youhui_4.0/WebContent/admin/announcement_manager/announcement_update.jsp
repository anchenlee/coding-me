<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%//String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/"; %>
<head>
    <title>新增公告-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
     <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    <script type="text/javascript">
    	// 上传默认图标
	    
	    
		// 提交时检查参数
	    function checkParam()
		{
			var title = $("#title").val();
			if (title == null || title == "")
			{
				alert("请填写<标题>!");
				return false;
			}
			
			var content_url = $("#description").val();
			if (content_url == null || content_url == "")
			{
				alert("请填写<详细内容>!");
				return false;
			}
			
			var show_type = $("#show_type").val();
			
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
			
			var action_type = $("#action_type").val();
			var action_value = getActionValue(action_type);
			
			var chaye_action_type = $("#chaye_action_type").val();
			var chaye_action_value = getChayeActionValue(chaye_action_type);
			
			var delay_time = $("#delay_time").val();
			if (delay_time == null || delay_time == "")
			{
				alert("请填写<delay_time延迟时间>!");
				return false;
			}
			
			var right_button_name = $("#right_button_name").val();
			
			var left_button_name = $("#left_button_name").val();
			
			var del_type = $("#del_type").val();
			
			var id = $("#announcement_id").val();
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/announcement_action?actionmethod=updateAnnouncement',
    		    type: 'POST',
    		    data: {
	    			title:title,
	    			content_url:content_url,
	    			start_time:start_time,
	    			end_time:end_time,
	    			action_type:action_type,
	    			action_value:action_value,
	    			chaye_action_type:chaye_action_type,
	    			chaye_action_value:chaye_action_value,
	    			delay_time:delay_time,
	    			left_button_name:left_button_name,
	    			right_button_name:right_button_name,
	    			show_type:show_type,
	    			del_type:del_type,
	    			id:id
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    					var url = "<%=request.getContextPath()%>/ad/announcement_action?actionmethod=showAnnouncementList";
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
    		    	}else if (result == "3")
    		    	{
    		    		alert("数据库更新失败!");
    		    		return false;
    		    	}
    		    }
    		});
		}
		
		function cancel()
		{
			var url = "<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&page=${page}&status=${status}&platform=${platform}";
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
	         <h2 class="entry-title">修改公告</h2>
				<div id="submit-content">
                <div id="formbox">
                		<p>
                		<input type="hidden" id="announcement_id" name="announcement_id" value="${bean.id}">
                             <label><font color="#777">标题：</font></label>
                             <input type="text" name="title" id="title" value="${bean.title}" />
                        </p>
                        <p>
                             <label><font color="#777">内容地址：</font></label>
                             <input type="text" name="description" id="description" value="${bean.contentUrl}" />
                       	</p>
                       	<p>
                             <label><font color="#777">按钮控制：</font></label>
                             <select id="show_type" name="show_type">
                             <c:forEach var="ButtonType" items="${ButtonTypeMap}">
									<c:choose>
										<c:when test="${bean.showType == ButtonType.key}">
											<option value="${ButtonType.key}" selected>${ButtonType.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${ButtonType.key}">${ButtonType.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
                             </select>
                       	</p>
                       	<p>
							<label><font color="#777" >关闭按钮：</font></label>
							<select name="del_type" id="del_type">
							<c:forEach var="ButtonCloseType" items="${ButtonCloseTypeMap}">
									<c:choose>
										<c:when test="${bean.delType == ButtonCloseType.key}">
											<option value="${ButtonCloseType.key}" selected>${ButtonCloseType.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${ButtonCloseType.key}">${ButtonCloseType.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">开始时间：</font></label>
							<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="${bean.startDate}" />
						</p>
						<p>
							<label><font color="#777">结束时间：</font></label>
							<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="${bean.endDate}" />
						</p>
						<p>
                             <label><font color="#777">延迟时间：</font></label>
                             <input type="text" name="delay_time" id="delay_time" value="${bean.delayTime}" />
                       	</p>
                       	<p>
                             <label><font color="#777">左按钮名：</font></label>
                             <input type="text" name="left_button_name" id="left_button_name" value="${bean.leftButton.name}" />
                       	</p>
                       	<p>
							<label><font color="#777">左样式：</font></label>
							<select id="action_type" name="action_type" onchange="actionTypeChange_1()">
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<c:choose>
										<c:when test="${bean.leftButton.action.actionType == ActionType.key}">
											<option value="${ActionType.key}" selected>${ActionType.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${ActionType.key}">${ActionType.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">左内容：</font></label>
							<input type="text" name="action_value" id="action_value" value="${bean.leftButton.action.actionValue}" />
							<select id="xitongyemian" name="xitongyemian"  style="display: none;">
								<c:forEach var="XTYM" items="${Tag_XTYM_Map}">
									<c:choose>
										<c:when test="${bean.leftButton.action.actionValue == XTYM.key}">
											<option value="${XTYM.key}" selected>${XTYM.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${XTYM.key}">${XTYM.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<select id="tagactionvalue" name="tagactionvalue"  style="display: none;">
								<option value="default">默认</option>
								<c:forEach var="tag_map" items="${Tag_Type_Value_Map}">
									<c:forEach var="tag" items="${tag_map}">
										<c:choose>
											<c:when test="${bean.leftButton.action.actionValue == tag.key}">
												<option value="${tag.key}" selected>${tag.value}</option>
											 </c:when>
											 <c:otherwise>
											 	<option value="${tag.key}">${tag.value}</option>
											 </c:otherwise>
										</c:choose>
									</c:forEach>
								</c:forEach>
							</select>
						</p>
						<p>
                             <label><font color="#777">右按钮名：</font></label>
                             <input type="text" name="right_button_name" id="right_button_name" value="${bean.rightButton.name}" />
                       	</p>
						<p>
							<label><font color="#777">右样式：</font></label>
							<select id="chaye_action_type" name="chaye_action_type" onchange="actionTypeChange_2()">
								<option value="">空</option>
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<c:choose>
										<c:when test="${bean.rightButton.action.actionType == ActionType.key}">
											<option value="${ActionType.key}" selected>${ActionType.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${ActionType.key}">${ActionType.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
                        <p>
                           <label><font color="#777">右内容：</font></label>
                           <input type="text" name="chaye_action_value" id="chaye_action_value" value="${tagbean.chaye_action.actionValue}" />
                           <select id="xitongyemian2" name="xitongyemian2"  style="display: none;">
								<c:forEach var="XTYM" items="${Tag_XTYM_Map}">
									<c:choose>
										<c:when test="${bean.rightButton.action.actionValue == XTYM.key}">
											<option value="${XTYM.key}" selected>${XTYM.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${XTYM.key}">${XTYM.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<select id="tagactionvalue2" name="tagactionvalue2"  style="display: none;">
								<option value="default">默认</option>
								<c:forEach var="tag_map" items="${Tag_Type_Value_Map}">
									<c:forEach var="tag" items="${tag_map}">
										<c:choose>
											<c:when test="${bean.rightButton.action.actionValue == tag.key}">
												<option value="${tag.key}" selected>${tag.value}</option>
											 </c:when>
											 <c:otherwise>
											 	<option value="${tag.key}">${tag.value}</option>
											 </c:otherwise>
										</c:choose>
									</c:forEach>
								</c:forEach>
							</select>
                        </p>
                       	
						<br/>
						
						<p>
							<input id="submit" type="button" class="button" onclick="checkParam()" value="提交" />
							
						</p>
                  </div>
                </div>
		</div>
		
	  </div>
	 </div>
	</div>
	<script type="text/javascript">
	function callback(msg){
			alert(msg);
	}
	
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
	
	showActionValue('${bean.leftButton.action.actionType}');
	showChayeActionValue('${bean.rightButton.action.actionType}');
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>      