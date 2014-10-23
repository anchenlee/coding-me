<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>编辑推荐-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
            <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    <script type="text/javascript">
    	// 上传手机图标
	    function add_recom_icon_ImgName(ImgName)
		{
		    var filename = $("#recom_icon_imgFile").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#recom_icon").val(url);
		    $("#upload_recom_icon_ImgForm").submit();
		}
	    
		// 提交时检查参数
	    function checkParam()
		{
			var recom_id = $("#recom_id").val();
			if (recom_id == null || recom_id == "")
			{
				alert("推荐的ID为空，不合法!");
				var page = "${page}";
				var url = "<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showRecomList&page=" + page;
				location.href=url;
				return false;
			}
			
			var rank = $("#rank").val();
			var date = $("#date").val();
			
			var title = $("#title").val();
			if (title == null || title == "")
			{
				alert("请填写<标题>!");
				return false;
			}
			
			var description = $("#description").val();
			if (description == null || description == "")
			{
				alert("请填写<详情>!");
				return false;
			}
			
			var memo = $("#memo").val();
			if (memo == null)
			{
				memo = "";
			}
			
			var small_show = $("#small_show").val();
			
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
			
			var recom_icon = $("#recom_icon").val();
			if (recom_icon == null || recom_icon == "")
			{
				alert("请填写<图标>!");
				return false;
			}
			
			var action_type = $("#action_type").val();
			var action_value = getActionValue(action_type);
			
			if (action_value == null || action_value == "")
			{
				alert("请填写<展示内容>!");
				return false;
			}
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/recom_manager?actionmethod=updateRecom',
    		    type: 'POST',
    		    data: {recom_id:recom_id,
    		    			title:title,
    		    			description:description,
    		    			memo:memo,
    		    			small_show:small_show,
    		    			start_time:start_time,
    		    			end_time:end_time,
    		    			recom_icon:recom_icon,
    		    			action_type:action_type,
    		    			action_value:action_value,
    		    			rank:rank,
    		    			date:date
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var page = "${page}";
    					var url = "<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showRecomList&page=" + page;
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("链接不合法，请确保以 http:// 作为链接的开头!");
    		    		return false;
    		    	}
    		    	else if (result == "2")
    		    	{
    		    		alert("开始或结束日期格式错误!");
    		    		return false;
    		    	}
    		    	else if (result == "3")
    		    	{
    		    		alert("缓存更新失败!");
    		    		return false;
    		    	}
    		    	else if (result == "4")
    		    	{
    		    		alert("数据库操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
		}
		
		function cancel()
		{
			var page = "${page}";
			var url = "<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showRecomList&page=" + page;
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
	         <h2 class="entry-title">编辑推荐</h2>
				<div id="submit-content">
                <div id="formbox">
                		<input type="hidden" id="recom_id" name="recom_id" value="${RecomBean.id}" />
                		<input type="hidden" id="rank" name="rank" value="${RecomBean.rank}" />
                		<input type="hidden" id="date" name="date" value="${RecomBean.date}" />
                		<p>
                             <label><font color="#777">标题：</font></label>
                             <input type="text" name="title" id="title" value="${RecomBean.title}" />
                        </p>
                        <p>
                             <label><font color="#777">详情：</font></label>
                             <input type="text" name="description" id="description" value="${RecomBean.description}" />
                       	</p>
                       	<p>
							<label><font color="#777">备注：</font></label>
							<input type="text" name="memo" id="memo" value="${RecomBean.memo}" />
						</p>
                        <p>
							<label><font color="#777">显示小条：</font></label>
							<select id="small_show" name="small_show">
								<c:forEach var="SmallShow" items="${SmallShowMap}">
									<c:choose>
										<c:when test="${RecomBean.show == SmallShow.key}">
											<option value="${SmallShow.key}" selected>${SmallShow.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${SmallShow.key}">${SmallShow.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">开始时间：</font></label>
							<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="${RecomBean.startTime}" />&nbsp;&nbsp;&nbsp;<font color="red">格式为：2013-11-9 9:23:24</font>
						</p>
						<p>
							<label><font color="#777">结束时间：</font></label>
							<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="${RecomBean.endTime}" />&nbsp;&nbsp;&nbsp;<font color="red">格式为：2013-11-9 9:23:24</font>
						</p>
						<p>
							<label><font color="#777">展示样式：</font></label>
							<select id="action_type" name="action_type" onchange="actionTypeChange_1()">
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<c:choose>
										<c:when test="${RecomBean.action.actionType == ActionType.key}">
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
							<label><font color="#777">展示内容：</font></label>
							<input type="text" name="action_value" id="action_value" value="${RecomBean.action.actionValue}" />
							<select id="xitongyemian" name="xitongyemian"  style="display: none;">
								<c:forEach var="XTYM" items="${XTYM_Map}">
									<c:choose>
										<c:when test="${RecomBean.action.actionValue == XTYM.key}">
											<option value="${XTYM.key}" selected>${XTYM.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${XTYM.key}">${XTYM.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<select id="tagactionvalue" name="tagactionvalue"  style="display: none;">
								<c:forEach var="tag_map" items="${Tag_Type_Value_Map}">
									<c:forEach var="tag" items="${tag_map}">
										<c:choose>
											<c:when test="${RecomBean.action.actionValue == tag.key}">
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
							<label><font color="#777">图标：</font></label>
							<input type="text" name="recom_icon" id="recom_icon"  value="${RecomBean.icon}" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：616*268  50KB</font>
							<!-- <img id="img_show" src="${activitybean.img}"  height="100"/> -->
						</p>
						<%
						    String recom_icon_ImgSaveName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_recom_icon_ImgForm" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=recom_icon_ImgSaveName%>" target="ImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="recom_icon_imgFile" name="file" type="file" />
						    <input type="button" onclick="add_recom_icon_ImgName('<%=recom_icon_ImgSaveName%>')" value="上传"  class="button"/>
						</form>
						<iframe name="ImgUploadIfr" style="display:none" ></iframe>
						<br/>
						
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
	
	showActionValue('${RecomBean.action.actionType}');
	

	
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>