<%@page import="java.net.URLEncoder"%>
<%@page import="cn.suishou.some.util.VersionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String platform = request.getParameter("tyh_web_platform");
    String version = request.getParameter("tyh_web_version");
    String ls = "suishou://youhui.cn?action_title="+URLEncoder.encode("年货-零嘴", "UTF-8")+"&action_value=896&action_type=tagStyleGrid&jump_from=ad";
    String bj = "suishou://youhui.cn?action_title="+URLEncoder.encode("年货-保健品", "UTF-8")+"&action_value=897&action_type=tagStyleGrid&jump_from=ad";
    String sx = "suishou://youhui.cn?action_title="+URLEncoder.encode("年货-生鲜", "UTF-8")+"&action_value=898&action_type=tagStyleGrid&jump_from=ad";
    boolean isLowVer = false;
    if(VersionUtil.isLowVersion(platform, version)){
    	ls = "";
    	bj = "";
    	sx = "";
    	isLowVer = true;
    }
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1//DTD//xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="year_goods.css" />
<link rel="stylesheet" type="text/css" href="update.css" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>年货食品会场</title>
<style type="text/css">
#tip_bar{position:fixed;z-index:1000;width:100%;height:30px;line-height:30px;background:hsla(0,0%,0%,.7);text-align:center;top:0;left:0;color:#fff;font-size:12px;box-shadow:0 3px 5px hsla(0,0%,0%,.5);}
</style>
</head>
<body>

<% if(isLowVer){%>
<div id="content">
	<img id="bgcolor" src="images/bgcolor_update.png" />
	<img id="first" src="images/font.png" />
	<a href="http://dl.b17.cn/app91469327"><img id="update" src="images/update.png" alt="立即升级" /></a>
	<!-- 下方添加代码，z-index设置区间[-1,100) -->
</div>
<% }%>

<div class="control">
<img src="images/total.png" id="img_1" />
<a href="<%=ls%>"><img src="images/part2_lr.png" id="img_2" /></a>
<a href="<%=bj%>"><img src="images/part3_lr.png" id="img_3" /></a>
<a href="<%=sx%>"><img src="images/part4_lr.png" id="img_4" /></a>
</div>

<script type="text/javascript">
	function control(){
		var view_width_update = document.documentElement.clientWidth || document.body.clientWidth;
		var text1 = document.getElementById("first");
		var text2 = document.getElementById("update");

		text1.style.left = 145/640 * view_width_update + "px";
		text1.style.top = 85/640 * view_width_update + "px";

		text2.style.left = 145/640 * view_width_update + "px";
		text2.style.top = 250/640 * view_width_update + "px";
	}
   function control_width () {
	var view_width = document.documentElement.clientWidth || document.body.clientWidth; 
	var oImg_2 = document.getElementById("img_2");
	    oImg_2.style.left=44/640*view_width+"px";
	    oImg_2.style.top=457/640*view_width+"px";
	var oImg_3 = document.getElementById("img_3");
		oImg_3.style.left=44/640*view_width+"px";
		oImg_3.style.top=837/640*view_width+"px";
	var oImg_4 = document.getElementById("img_4");
		oImg_4.style.left=44/640*view_width+"px";
		oImg_4.style.top=1233/640*view_width+"px";
	}
	 control_width();
	 <%if(isLowVer){%>
	 	control();
	 	<%}%>
		window.onresize=function () {
			control_width();
			<%if(isLowVer){%>
			 control();
			 <%}%>
		};

</script>
</body>
</html>