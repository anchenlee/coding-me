<%@page import="cn.suishou.some.free.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">
<%
//String uid=GetUserId.getUid(request);
String title_url=ActivityManager.getActivityImgByActivityId("001");
String item_url=ActivityManager.getItemImgByActivityId("001");
String item_describe=ActivityManager.getItemdescribeByActivityId("001");
%>

<p>
<img src=<%=title_url %>>  <br />
<img src=<%=item_url %>><br>
<%=item_describe %> <br />

<script type="text/javascript">
	function transMessage(){
		var xmlhttp;
		var chooseInterest=document.getElementById("interestId");
		if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
		}else{// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){				
				xmlhttp.responseText;  	
		    }
		}	
		xmlhttp.open("POST","./ActivityServlet?"+encodeURI(encodeURI("chooseInterest="+chooseInterest)),true); 
		xmlhttp.send();
	}
</script>


</p>

<form action="/SomeActivity//freeactivity" method="post">

	<input type="radio" name="interest" value="hate" id="interestId"/> <img src="./Image/egg.png" height="30" width="30" >
	<input type="radio" name="interest" value="like" id="interestId"/> <img src="./Image/flower.png" height="30" width="30" >
	<br />

    <span> 用户评价：</span>
	<textarea name="pingjia" cols ="70" rows = "10" id="pingjiaId" style="resize:none;vertical-align:top;"></textarea><br/>
	<input type="submit" name="pingjia" value="提交" >
</form>
</div>

</body>
</html>