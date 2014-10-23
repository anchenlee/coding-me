<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>送集分宝-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript">
	    
		// 提交时检查参数
	    function checkParam()
		{
			var activity_id = $("#activity_id").val();
			if (activity_id == null || activity_id == "")
			{
				alert("活动ID为空，操作违法！");
				var page = "${page}";
				var url = "<%=request.getContextPath()%>/ad/activity_manager?actionmethod=showActivityList&page=" + page;
				location.href=url;
				return false;
			}
			
			var taobao_nick = $("#taobao_nick").val();
			if (taobao_nick == null || taobao_nick == "")
			{
				alert("请填写<用户淘宝昵称>！");
				return false;
			}
			
			var activity_name = $("#activity_name").val();
			if (activity_name == null || activity_name == "")
			{
				alert("请填写<活动名称>");
				return false;
			}
			
			var jfb_num = $("#jfb_num").val();
			if (jfb_num == null || jfb_num == "")
			{
				alert("请填写<集分宝个数>");
				return false;
			}
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/activity_manager?actionmethod=sendJFB',
    		    type: 'POST',
    		    data: {
    		    	activity_id:activity_id,
    		    	taobao_nick:taobao_nick,
    		    	activity_name:activity_name,
    		    	jfb_num:jfb_num
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var content = json.content;
    		    		alert("发送完成," + content);
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
	         	<h2 class="entry-title">送集分宝</h2>
				<div id="submit-content">
	                <div id="formbox">
	                		<input type="hidden" id="activity_id" name="activity_id" value="${activity_id}" />
	                        <p>
	                             <label><font color="#777">淘宝昵称：</font></label>
	                             <input type="text" name="taobao_nick" id="taobao_nick" value="" />
	                       	</p>
	                       	<p>
	                             <label><font color="#777">活动名称：</font></label>
	                             <input type="text" name="activity_name" id="activity_name" value="" />
	                       	</p>
	                       	<p>
	                             <label><font color="#777">集分宝个数：</font></label>
	                             <input type="text" name="jfb_num" id="jfb_num" value="" />
	                       	</p>
							<p>
								<input id="submit" type="button" class="button" onclick="checkParam()" value="发送" />
								<input id="cancel" type="button" class="button" onclick="cancel()" value="取消" />
							</p>
	                  </div>
                </div>
		</div>
		<hr/>
		<div id="post-8" class="post-8 page type-page status-publish hentry">
	         	<h2 class="entry-title">批量发送集分宝</h2>
				<div id="submit-content">
	                <div id="formbox">
	                		<form action="<%=request.getContextPath()%>/ad/activity_manager?actionmethod=sendListJFB"  method="post" enctype="multipart/form-data">
								<p>
									<label><font color="#777">集分宝发送文件：</font></label>
									<input id="jfb_file" name="jfb_file" type="file" />
	                       		</p>
								<p>
									<input id="submit" type="submit" class="button" value="发送" />
								</p>
							</form>
	                  </div>
                </div>
		</div>
	  </div>
	 </div>
	</div>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>