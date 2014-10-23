<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>编辑消息-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
     <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    <script type="text/javascript">
    	// 上传默认图标
	    function add_Img_Name(ImgName)
		{
		    var filename = $("#img_File").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#img").val(url);
		    $("#upload_Img_Form").submit();
		}
    	
		// 提交时检查参数
	    function checkParam()
		{
			var pid = $("#pid").val();
			if (pid == null || pid == "")
			{
				alert("消息的ID为空，不合法!");
				var url = "<%=request.getContextPath()%>/ad/message_manager?actionmethod=showMessageList&page=${page}&platform=${platform}";
				location.href=url;
				return false;
			}
			
			var title = $("#title").val();
			if (title == null || title == "")
			{
				alert("请填写<标题>!");
				return false;
			}
			
			var content = $("#msg_content").val();
			if (content == null || content == "")
			{
				alert("请填写<消息内容>!");
				return false;
			}
			
			var platform = $("#platform").val();
			var clientVersion = $("#clientVersion").val();
			var mode = $("#mode").val();
			var code = $("#code").val();
			var range = $("#range").val();
			var formula = "";
			var query = "";
			
			if (range == "1")
			{
				formula = $("#formula").val();
				query = $("#query").val();
				
				if (formula == null || formula == "")
				{
					alert("请填写<发送条件>!");
					return false;
				}
				if (query == null)
				{
					query = "";
				}
			}
			
			var start_time = $("#start_time").val();
			var end_time = $("#end_time").val();
			if(platform == 'android') 
			{
				if (start_time == null || start_time == "")
				{
					alert("请填写<开始时间>");
					return false;
				}
				
				if (end_time == null || end_time == "")
				{
					alert("请填写<结束时间>");
					return false;
				}
			}
			else if(platform == 'iphone' || platform == 'ipad')
			{
				start_time = "";
				end_time = "";
			}
			
			var action_type = $("#action_type").val();
			var action_value = getActionValue(action_type);
			
			if (action_value == null || action_value == "")
			{
				alert("请填写<展示内容>!");
				return false;
			}
			
			var img = $("#img").val();
			if (img == null || img == "")
			{
				alert("请填写<图标>!");
				return false;
			}
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/message_manager?actionmethod=updateMessage',
    		    type: 'POST',
    		    data: {
    		    	pid:pid,
    		    	title:title,
    		    	content:content,
	    			platform:platform,
	    			clientVersion:clientVersion,
	    			mode:mode,
	    			code:code,
	    			range:range,
	    			formula:formula,
	    			query:query,
	    			start_time:start_time,
	    			end_time:end_time,
	    			action_type:action_type,
	    			action_value:action_value,
	    			img:img
    		    },
    		    dataType: 'json',
    		    timeout: 15000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    					var url = "<%=request.getContextPath()%>/ad/message_manager?actionmethod=showMessageList&page=${page}&platform=" + platform;
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
			var url = "<%=request.getContextPath()%>/ad/message_manager?actionmethod=showMessageList&page=${page}&platform=${platform}";
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
	         <h2 class="entry-title">编辑消息</h2>
				<div id="submit-content">
                <div id="formbox">
                		<input type="hidden" id="pid" name="pid" value="${Message.pId}" />
                		<p>
                             <label><font color="#777">标题：</font></label>
                             <input type="text" name="title" id="title" value="${Message.title}" />
                        </p>
                        <p>
                             <label><font color="#777">消息内容：</font></label>
                             <input type="text" name="msg_content" id="msg_content" value="${Message.content}" />
                       	</p>
                       	<p>
							<label><font color="#777">平台选择：</font></label>
							<select id="platform" name="platform"  onChange="platformSelect(this.value)">
								<c:forEach var="Platform" items="${MessagePlatformMap}">
									<c:choose>
										<c:when test="${Message.platform == Platform.key}">
											<option value="${Platform.key}" selected>${Platform.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${Platform.key}">${Platform.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p id="p_clientVersion" style="display: none">
							<label><font color="#777">版本：</font></label>
							<select id="clientVersion" name="clientVersion">
								<c:forEach var="Version" items="${MessageVersionMap}">
									<c:choose>
										<c:when test="${Message.clientVersion == Version.key}">
											<option value="${Version.key}" selected>${Version.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${Version.key}">${Version.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p id="p_mode" style="display: none">
							<label><font color="#777">发送模式：</font></label>
							<select id="mode" name="mode">
								<c:forEach var="Mode" items="${MessageModeMap}">
									<c:choose>
										<c:when test="${Message.mode == Mode.key}">
											<option value="${Mode.key}" selected>${Mode.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${Mode.key}">${Mode.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">通知方式：</font></label>
							<select id="code" name="code">
								<c:forEach var="Code" items="${MessageCodeMap}">
									<c:choose>
										<c:when test="${Message.code == Code.key}">
											<option value="${Code.key}" selected>${Code.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${Code.key}">${Code.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">发送范围：</font></label>
							<select id="range" name="range"  onChange="rangeSelect(this.value)">
								<c:forEach var="Range" items="${MessageRangeMap}">
									<c:choose>
										<c:when test="${Message.range == Range.key}">
											<option value="${Range.key}" selected>${Range.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${Range.key}">${Range.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p id="p_formula" style="display: none">
                             <label><font color="#777">发送条件：</font></label>
                             <input type="text" name="formula" id="formula" value="${Message.formula}" />
                        </p>
                        <p id="p_query" style="display: none">
                             <label><font color="#777">显示数据：</font></label>
                             <input type="text" name="query" id="query" value="${Message.query}" />
                       	</p>
						<p id="p_start_time" style="display: none">
							<label><font color="#777">开始时间：</font></label>
							<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="${Message.starttime}" />&nbsp;&nbsp;&nbsp;<font color="red">格式为：2013-11-9 9:23:24</font>
						</p>
						<p id="p_end_time" style="display: none">
							<label><font color="#777">结束时间：</font></label>
							<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="${Message.endtime}" />&nbsp;&nbsp;&nbsp;<font color="red">格式为：2013-11-9 9:23:24</font>
						</p>
						<p>
							<label><font color="#777">展示样式：</font></label>
							<select id="action_type" name="action_type" onchange="actionTypeChange_1()">
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<c:choose>
										<c:when test="${Message.action.actionType == ActionType.key}">
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
							<input type="text" name="action_value" id="action_value" value="${Message.action.actionValue}" />
							<select id="xitongyemian" name="xitongyemian"  style="display: none;">
								<c:forEach var="XTYM" items="${XTYM_Map}">
									<c:choose>
										<c:when test="${Message.action.actionValue == XTYM.key}">
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
											<c:when test="${Message.action.actionValue == tag.key}">
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
							<input type="text" name="img" id="img"  value="${Message.icon}" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：140*320</font>
						</p>
						<%
						    String imgName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_Img_Form" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=imgName%>" target="ImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="img_File" name="file" type="file" />
						    <input type="button" onclick="add_Img_Name('<%=imgName%>')" value="上传"  class="button"/>
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
	
	showActionValue('${Message.action.actionType}');
	showPlatform();
	showRange();
	
	
	
	
	function showPlatform()
	{
		var platform = "${Message.platform}";
		if(platform == 'iphone' || platform == 'ipad')
		{
			eval("p_clientVersion.style.display=\"none\"");
			eval("p_mode.style.display=\"none\"");
			eval("p_start_time.style.display=\"none\"");
			eval("p_end_time.style.display=\"none\"");
		}
		else if(platform == 'android') 
		{
			eval("p_clientVersion.style.display=\"\"");
			eval("p_mode.style.display=\"\"");
			eval("p_start_time.style.display=\"\"");
			eval("p_end_time.style.display=\"\"");
		}
	}
	
	function platformSelect(platform)
	{
		if(platform == 'iphone' || platform == 'ipad')
		{
			eval("p_clientVersion.style.display=\"none\"");
			eval("p_mode.style.display=\"none\"");
			eval("p_start_time.style.display=\"none\"");
			eval("p_end_time.style.display=\"none\"");
		}
		else if(platform == 'android') 
		{
			eval("p_clientVersion.style.display=\"\"");
			eval("p_mode.style.display=\"\"");
			eval("p_start_time.style.display=\"\"");
			eval("p_end_time.style.display=\"\"");
		}
	}
	
	function showRange()
	{
		var range = "${Message.range}";
		if(range == '0')
		{
			eval("p_formula.style.display=\"none\"");
			eval("p_query.style.display=\"none\"");
		}
		else if(range == '1') 
		{
			eval("p_formula.style.display=\"\"");
			eval("p_query.style.display=\"\"");
		}
	}
	
	function rangeSelect(range)
	{
		if(range == '0')
		{
			eval("p_formula.style.display=\"none\"");
			eval("p_query.style.display=\"none\"");
		}
		else if(range == '1') 
		{
			eval("p_formula.style.display=\"\"");
			eval("p_query.style.display=\"\"");
		}
	}
	
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>