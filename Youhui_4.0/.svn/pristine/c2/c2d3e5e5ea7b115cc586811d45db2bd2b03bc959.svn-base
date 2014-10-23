<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
	<title>后台管理-登录</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reset.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/black.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/custom.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/prettyPhoto.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/pagenavi-css.css">
		
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
		<script type="text/javascript" charset="utf-8">
		   function checkParam()
		   {
	       	   var name = $("#username").val();
	           var pass = $("#password").val();
	           
	           if(name == "" || pass == "")
	           {
		          alert("用户名和密码不能为空！");
		          return false;
	           }
	           else
	           {
	        	   return true;
	        	   //$("#form_login").submit();
	           }
           }
		   </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome">
		
	  	<div style="margin-top:200px;">  
			<div style="margin: 0 auto;">
			<div class="widget" style="margin: 0 auto; width:250px;">
			<form action="<%=request.getContextPath()%>/AdminLoginAction?actionmethod=login" method="post" onsubmit="return checkParam()" id="form_login" name="form_login">
				<div>
				<img style="float:left;" width="120px" src="<%=request.getContextPath() %>/img/login_v4.png">
				<div id="header-name" style="color:#777; margin:20px 0 10px 20px;">管理后台</div>
				</div>
				<br/>
				<p>
					<label style="display: block;float: left;font-weight: bold;margin: 0 10px 0 0;width: 115px;overflow: hidden;">
					帐号:
					</label>
					<input type="text" style="width:94%;" id="username" name="username">
				</p>
				<p>
					<label style="display: block;float: left;font-weight: bold;margin: 0 10px 0 0;width: 115px;overflow: hidden;">
					密码:
					</label>
					<input type="password" style="width:94%;" id="password" name="password">
				</p>
				<p style="text-align: right;">
					<input id="submit" type="submit" value="登录" class="button">
				</p>
				</form>
				</div>
			</div>
	   </div>
	</body>
</html>