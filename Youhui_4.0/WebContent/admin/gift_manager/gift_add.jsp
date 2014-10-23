<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增礼物-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
         <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    <script type="text/javascript">
    	// 上传手机图标
	    function add_ImgName(ImgName)
		{
		    var filename = $("#gift_imgFile").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#img").val(url);
		    $("#upload_gift_ImgForm").submit();
		}
    	
	 	// 上传手机图标
	    function add_ipad_ImgName(ImgName)
		{
		    var filename = $("#gift_ipad_imgFile").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#imgIpad").val(url);
		    $("#upload_gift_ipad_ImgForm").submit();
		}
	    
		// 提交时检查参数
	    function checkParam()
		{
			var title = $("#title").val();
			if (title == null || title == "")
			{
				alert("请填写<标题>!");
				return false;
			}
			
			var description = $("#description").val();
			if (description == null || description == "")
			{
				alert("请填写<详细内容>!");
				return false;
			}
			
			var clickname = $("#clickname").val();
			if (clickname == null || clickname == "")
			{
				alert("请填写<按钮名称>");
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
				alert("请填写<手机图标>!");
				return false;
			}
			
			var imgIpad = $("#imgIpad").val();
			if (imgIpad == null || imgIpad == "")
			{
				alert("请填写<PAD图标>!");
				return false;
			}
			
			var status = $("#status").val();
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/gift_manager?actionmethod=addGift',
    		    type: 'POST',
    		    data: {
    		    			status:status,
    		    			title:title,
    		    			description:description,
    		    			clickname:clickname,
    		    			start_time:start_time,
    		    			end_time:end_time,
    		    			action_type:action_type,
    		    			action_value:action_value,
    		    			img:img,
    		    			imgIpad:imgIpad
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var page = "${page}";
    					var url = "<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showGiftList&page=" + page + "&status=" + status;
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
			var url = "<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showGiftList&page=" + page;
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
	         <h2 class="entry-title">新增礼物</h2>
				<div id="submit-content">
                <div id="formbox">
                		<input type="hidden" id="status" name="status" value="${status}" />
                		<p>
                             <label><font color="#777">标题：</font></label>
                             <input type="text" name="title" id="title" value="" />
                        </p>
                        <p>
                             <label><font color="#777">详细内容：</font></label>
                             <input type="text" name="description" id="description" value="" />
                       	</p>
                       	<p>
							<label><font color="#777">按钮名称：</font></label>
							<input type="text" name="clickname" id="clickname" value="" />
						</p>
						<p>
							<label><font color="#777">开始时间：</font></label>
							<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="" />&nbsp;&nbsp;&nbsp;<font color="red">格式为：2013-11-9 9:23:24</font>
						</p>
						<p>
							<label><font color="#777">结束时间：</font></label>
							<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="" />&nbsp;&nbsp;&nbsp;<font color="red">格式为：2013-11-9 9:23:24</font>
						</p>
						<p>
							<label><font color="#777">展示样式：</font></label>
							<select id="action_type" name="action_type" onchange="actionTypeChange_1()">
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<c:choose>
										<c:when test="${ActionType.key == 'tagStyleWeb'}">
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
							<input type="text" name="action_value" id="action_value" value="" style="display: none;" />
							<select id="xitongyemian" name="xitongyemian"  style="display: none;">
								<c:forEach var="XTYM" items="${XTYM_Map}">
									<option value="${XTYM.key}">${XTYM.value}</option>
								</c:forEach>
							</select>
							<select id="tagactionvalue" name="tagactionvalue"  >
								<c:forEach var="tag_map" items="${Tag_Type_Value_Map}">
									<c:forEach var="tag" items="${tag_map}">
										<option value="${tag.key}">${tag.value}</option>
									</c:forEach>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">手机图标：</font></label>
							<input type="text" name="img" id="img"  value="" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：616*268  50KB</font>
							<!-- <img id="img_show" src="${activitybean.img}"  height="100"/> -->
						</p>
						<%
						    String gift_ImgSaveName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_gift_ImgForm" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=gift_ImgSaveName%>" target="PadImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="gift_imgFile" name="file" type="file" />
						    <input type="button" onclick="add_ImgName('<%=gift_ImgSaveName%>')" value="上传"  class="button"/>
						</form>
						<iframe name="ImgUploadIfr" style="display:none"></iframe>
						<br/>
						<p>
							<label><font color="#777">PAD图标：</font></label>
							<input type="text" name="imgIpad" id="imgIpad"  value="" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：306*180 30KB</font>
							<!-- <img id="img_show" src="${activitybean.img}"  height="100"/> -->
						</p>
						<%
						    String gift_pad_ImgSaveName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_gift_ipad_ImgForm" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=gift_pad_ImgSaveName%>" target="PadImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="gift_ipad_imgFile" name="file" type="file" />
						    <input type="button" onclick="add_ipad_ImgName('<%=gift_pad_ImgSaveName%>')" value="上传"  class="button"/>
						</form>
						<iframe name="PadImgUploadIfr" style="display:none"></iframe>
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
	
	
	
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>