<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增后台管理用户-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript">
		// 提交时检查参数
	    function checkParam()
		{
			var username = $("#username").val();
			if (username == null || username == "")
			{
				alert("请填写<登录名>!");
				return false;
			}
			
			var password = $("#password").val();
			if (password == null || password == "")
			{
				alert("请填写<登陆密码>!");
				return false;
			}
			
			var realname = $("#realname").val();
			if (realname == null || realname == "")
			{
				alert("请填写<真实姓名>");
				return false;
			}
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=addAdminUser',
    		    type: 'POST',
    		    data: {
    		    			username:username,
    		    			password:password,
    		    			realname:realname
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		location.href="<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=showAdminUserList&page=${page}";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("操作失败!");
    		    		return false;
    		    	}
    		    	else if (result == "2")
    		    	{
    		    		alert("用户名重复，请重新输入!");
    		    		$("#username").val("");
    		    		return false;
    		    	}
    		    }
    		});
		}
		
		function cancel()
		{
			location.href="<%=request.getContextPath()%>/ad/admin_user_manager?actionmethod=showAdminUserList&page=${page}";
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
	         <h2 class="entry-title">新增后台管理用户</h2>
				<div id="submit-content">
                <div id="formbox">
                		<p>
                             <label><font color="#777">登录名：</font></label>
                             <input type="text" name="username" id="username" value="" />
                        </p>
                        <p>
                             <label><font color="#777">登录密码：</font></label>
                             <input type="password" name="password" id="password" value="" />
                       	</p>
                       	<p>
							<label><font color="#777">真实姓名：</font></label>
							<input type="text" name="realname" id="realname" value="" />
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