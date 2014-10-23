<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
 

</head>
<body
	class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome"
	onload="ol()">

	<div style="margin-top: 200px;">
		<div style="margin: 0 auto;">

			<div class="widget" style="margin: 0 auto; width: 240px;">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">管理后台</h3>
					</div>
					<div class="panel-body">
						<div class="input-group">
							<span class="input-group-addon">账号</span> <input type="text"
								class="form-control" placeholder="账号" id="username"
								name="username">
						</div>
					<p>
						<div class="input-group">
							<span class="input-group-addon">密码</span> <input type="password"
								class="form-control" placeholder="密码" id="password"
								name="password">
						</div>
					</p>	
						<button type="button" class="btn btn-primary btn-block"
							onclick="login()">登录</button>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>

<script type="text/javascript" charset="utf-8">
    
    function ol(){
//     	if(""!=(getCookie("login"))){
<%--     		window.location.href="<%=request.getContextPath()%>/shop/index.jsp"; --%>
//     	}
    }
	function login(){
        if(document.getElementById("username").value==""){
            alert("请输入用户名！");return;
        }else if(document.getElementById("password").value==""){
            alert("请输入密码");return;
		}
        
        var username=document.getElementById("username").value.replaceAll(" ","");
        var password=document.getElementById("password").value.replaceAll(" ","");
        var xmlhttp;
        if (window.XMLHttpRequest)
          {// code for IE7+, Firefox, Chrome, Opera, Safari
          xmlhttp=new XMLHttpRequest();
          }
        else
          {// code for IE6, IE5
          xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
          }
          xmlhttp.onreadystatechange=function()
          {
          if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                if(xmlhttp.responseText=="paramException"){
                    alert("参数异常！");
                }else if(xmlhttp.responseText=="fail"){
                    alert("登录失败！");
                }else if(xmlhttp.responseText=="success"){
                    window.location.href="<%=request.getContextPath()%>/index.jsp";
                }
            }
          }
            xmlhttp.open("GET","<%=request.getContextPath()%>/login?t=login&username="+username+"&password="+password,true);
            xmlhttp.send(); 
    }
           
	String.prototype.replaceAll = function(s1,s2) {
			return this.replace(new RegExp(s1,"gm"),s2); 
           }
           
	function setCookie(c_name,value,expireseconds){
           var exdate=new Date()
           exdate.setTime(exdate.getTime()+expireseconds)
           document.cookie=c_name+ "=" +escape(value)+
           ((expireseconds==null) ? "" : ";expires="+exdate.toGMTString())
 	}
           
	function getCookie(c_name){
           if (document.cookie.length>0)
             {
             c_start=document.cookie.indexOf(c_name + "=")
             if (c_start!=-1)
               {
               c_start=c_start + c_name.length+1 
               c_end=document.cookie.indexOf(";",c_start)
               if (c_end==-1) c_end=document.cookie.length
               return unescape(document.cookie.substring(c_start,c_end))
               } 
             }
           return ""
	}
    </script>

</html>