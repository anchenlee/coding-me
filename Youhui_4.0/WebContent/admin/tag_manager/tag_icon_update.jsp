<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增标签图片-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
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
    	
	    
		// 提交时检查参数
	    function checkParam(tag_id,icon_id,tag_name)
		{
			
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
			
			
			
			var img = $("#img").val();
			if (img == null || img == "")
			{
				alert("请填写<图标>!");
				return false;
			}
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_manager?actionmethod=AddTagIcon',
    		    type: 'POST',
    		    data: {	    			
	    			start_time:start_time,
	    			end_time:end_time,
	    			img:img,
	    			tag_id:tag_id,
	    			tag_name:tag_name,
	    			id:icon_id
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    					var url = "<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagIcoList&tag_id="+tag_id+"&tag_name="+tag_name;
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
    		    	else if (result == "3")
    		    	{
    		    		alert("相同时间内已有图片!");
    		    		return false;
    		    	}
    		    }
    		});
		}
		
		function cancel()
		{
			var url = "<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagIcoList&tag_id=${tag_id}&tag_name=${tag_name}";
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
         <h2 class="entry-title"><input type="button" value="返回" onclick="cancel()"> </h2>
         <br>
	         <h2 class="entry-title">新增标签图片</h2>
				<div id="submit-content">
                <div id="formbox">
                <input type="hidden" id="tag_id" name="tag_id" value="${tag_id}">
                <input type="hidden" id="icon_id" name="icon_id" value="${tagIconBean.id }">
                <input type="hidden" id="tag_name" name="tag_name" value="${ tag_name}">
						<p>
							<label><font color="#777">开始时间：</font></label>
							<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="${tagIconBean.beginTime }" />
						</p>
						<p>
							<label><font color="#777">结束时间：</font></label>
							<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="${tagIconBean.endTime}" />
						</p>
						
						<p>
								 	<label><font color="#777">图片：</font></label>
							<input type="text" name="img" id="img"  value="${tagIconBean.icon}" />&nbsp;&nbsp;&nbsp;<font color="red"></font>
						</p>
						<%
						
						    String imgName_140_320 = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_ad_Img_Form" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=imgName_140_320%> " target="ImgUploadIfr_2" >
						    <label>&nbsp;</label>
						    <input id="ad_img_File" name="file" type="file" />
						    <input type="button" onclick="add_Img_Name('<%=imgName_140_320%>')" value="上传"  class="button"/>
						</form>
						<iframe name="ImgUploadIfr_2"  style="display:none" onload="displayResult(this)">
						</iframe>
						<br/>
						
						<p>
							<input id="submit" type="button" class="button" onclick="checkParam('${tag_id}','${tagIconBean.id }','${ tag_name}')" value="提交" />
						</p>
                  </div>
                </div>
		</div>
		
	  </div>
	 </div>
	</div>
	<script type="text/javascript">
	
	function displayResult(iframe){
		//var re = documet.body.innerHTML;
		var doc = iframe.contentWindow;
		alert("上传成功！");
   	 //var html = doc.body.innerHTML;

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
	

	
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>