<%@page import="cn.youhui.utils.StringUtils"%>
<%@page import="cn.youhui.utils.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%
    String url = request.getParameter("url");
    if(StringUtils.isEmpty(url)){
    	response.sendRedirect("../notfind.html");
    	return;
    }
	String rurl = new String(Base64.decode(url));
	
	String data = "";
	if(!StringUtils.isEmpty(rurl)){
		data = rurl.split("data=")[1];
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>集分宝活动</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"type="text/javascript"></script>
<style>
	*{margin:0;padding:0;}
	html,body{height:100%;width:100%;}
	body{overflow:hidden;}
</style>
</head>
<body>
<iframe id="if" frameBorder="0" width="100%" height="100%" src="<%=rurl%>"></iframe>
</body>
<script type="text/javascript">
var iframe=document.getElementById("if");
iframe.addEventListener("load", function(){
	this.removeEventListener("load", arguments.call, false);
	callback();
}, false);

function callback(){
	$.ajax({url: "<%=request.getContextPath()%>/jfbadcallbackforold", 
	    type: "POST",
		data: 'data=<%=data%>',
		success: function(ret){
   			if('success' == ret){
   			$('body').append('<div id="always_footer" style="position:fixed;bottom:0;width:100%;background:#f7f7f7;padding:1%;border:1px solid #d7d7d7;color:#f18408;text-align:center;"><span>领取成功咯</span></div>');
   			setTimeout(function(){
   		        $('#always_footer').remove();
   		    }, 3000);   				
   			}
		}});
}
</script>
</html>