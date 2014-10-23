<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>编辑广告-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    <script type="text/javascript">
    	// 上传默认图标
	    function add_Img_Name(ImgName)
		{
		    var filename = $("#ad_img_File").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#img").val(url);
		    $("#upload_ad_Img_Form").submit();
		}
    	
	 	// 上传1号图标
	    function add_Img_1_Name(ImgName)
		{
		    var filename = $("#ad_img_1_File").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#img1").val(url);
		    $("#upload_ad_Img_1_Form").submit();
		}
	 	
	 	// 上传2号图标
	    function add_Img_2_Name(ImgName)
		{
		    var filename = $("#ad_img_2_File").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#img2").val(url);
		    $("#upload_ad_Img_2_Form").submit();
		}
	    
		// 提交时检查参数
	    function checkParam()
		{
			var ad_id = $("#ad_id").val();
			if (ad_id == null || ad_id == "")
			{
				alert("广告的ID为空，不合法!");
				var url = "<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&page=${page}&status=${status}&platform=${platform}";
				location.href=url;
				return false;
			}
			
			var rank = $("#rank").val();
			
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
			
			var platform = $("#platform").val();
			
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
				alert("请填写<图标>!");
				return false;
			}
			
			var img1 = $("#img1").val();
			if (img1 == null || img1 == "")
			{
				img1 = img;
			}
			var img2 = $("#img2").val();
			if (img2 == null || img2 == "")
			{
				img2 = img;
			}
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/ad_manager?actionmethod=updateAD',
    		    type: 'POST',
    		    data: {
    		    	ad_id:ad_id,
    		    	rank:rank,
	    			title:title,
	    			description:description,
	    			platform:platform,
	    			start_time:start_time,
	    			end_time:end_time,
	    			action_type:action_type,
	    			action_value:action_value,
	    			img:img,
	    			img1:img1,
	    			img2:img2
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    					var url = "<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&page=${page}&status=${status}&platform=${platform}";
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
	         <h2 class="entry-title">编辑广告</h2>
				<div id="submit-content">
                <div id="formbox">
                		<input type="hidden" id="ad_id" name="ad_id" value="${ADBean.id}" />
                		<input type="hidden" id="rank" name="rank" value="${ADBean.rank}" />
                		<p>
                             <label><font color="#777">标题：</font></label>
                             <input type="text" name="title" id="title" value="${ADBean.title}" />
                        </p>
                        <p>
                             <label><font color="#777">详细内容：</font></label>
                             <input type="text" name="description" id="description" value="${ADBean.description}" />
                       	</p>
                       	<p>
							<label><font color="#777">平台选择：</font></label>
							<select id="platform" name="platform">
								<c:forEach var="Platform" items="${ADPlatformMap}">
									<c:choose>
										<c:when test="${ADBean.platform == Platform.key}">
											<option value="${Platform.key}" selected>${Platform.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${Platform.key}">${Platform.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">开始时间：</font></label>
							<input type="text"  class="ui_timepicker"  name="start_time" id="start_time" value="${ADBean.start_time}" />
						</p>
						<p>
							<label><font color="#777">结束时间：</font></label>
							<input type="text"  class="ui_timepicker"  name="end_time" id="end_time" value="${ADBean.end_time}" />
						</p>
						<p>
							<label><font color="#777">展示样式：</font></label>
							<select id="action_type" name="action_type" onchange="actionTypeChange_1()">
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<c:choose>
										<c:when test="${ADBean.actionType == ActionType.key}">
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
							<input type="text" name="action_value" id="action_value" value="${ADBean.actionValue}" />
							<select id="xitongyemian" name="xitongyemian"  style="display: none;">
								<c:forEach var="XTYM" items="${XTYM_Map}">
									<c:choose>
										<c:when test="${ADBean.actionValue == XTYM.key}">
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
											<c:when test="${ADBean.actionValue == tag.key}">
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
							<c:choose>
								<c:when test="${ADBean.platform == 'ipad2.0'}">
									<label><font color="#777">IPAD图标：</font></label>
								 </c:when>
								 <c:otherwise>
								 	<label><font color="#777">图140*320：</font></label>
								 </c:otherwise>
							</c:choose>
							<input type="text" name="img" id="img"  value="${ADBean.img}" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：140*320</font>
						</p>
						<%
						    String imgName_140_320 = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_ad_Img_Form" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=imgName_140_320%>" target="ImgUploadIfr_2" >
						    <label>&nbsp;</label>
						    <input id="ad_img_File" name="file" type="file" />
						    <input type="button" onclick="add_Img_Name('<%=imgName_140_320%>')" value="上传"  class="button"/>
						</form>
						<iframe name="ImgUploadIfr" style="display:none"></iframe>
						<br/>
						
						<p>
							<label><font color="#777">图160*320：</font></label>
							<input type="text" name="img1" id="img1"  value="${ADBean.img1}" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：160*320</font>
						</p>
						<%
						    String imgName_160_320 = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_ad_Img_1_Form" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=imgName_160_320%>" target="ImgUploadIfr_2" >
						    <label>&nbsp;</label>
						    <input id="ad_img_1_File" name="file" type="file" />
						    <input type="button" onclick="add_Img_1_Name('<%=imgName_160_320%>')" value="上传"  class="button"/>
						</form>
						<iframe name="ImgUploadIfr_1" style="display:none"></iframe>
						<br/>
						
						<p>
							<label><font color="#777">图215*320：</font></label>
							<input type="text" name="img2" id="img2"  value="${ADBean.img2}" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：215*320</font>
						</p>
						<%
						    String imgName_215_320 = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_ad_Img_2_Form" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=imgName_215_320%>" target="ImgUploadIfr_2" >
						    <label>&nbsp;</label>
						    <input id="ad_img_2_File" name="file" type="file" />
						    <input type="button" onclick="add_Img_2_Name('<%=imgName_215_320%>')" value="上传"  class="button"/>
						</form>
						<iframe name="ImgUploadIfr_2" style="display:none"></iframe>
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
	
	showActionValue('${ADBean.actionType}');
	

	
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>