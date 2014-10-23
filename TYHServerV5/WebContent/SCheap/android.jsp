<%@page import="cn.youhui.utils.RequestUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>随手优惠-超级惠分享</title>
	<script type="text/javascript" src="js/jquery.js"></script><!--jquery包-->
	<link rel="stylesheet" href="css/style.css"/><!--导入css-->
	<script type="text/javascript" src="js/jquery.lazyload.min.js"></script><!--导入js-->
	<script type="text/javascript" src="js/jumpss.js"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
</head>
<body onload="init()" background="#fff;">
<div style="background: #000;opacity:0.7;width: 100%;"></div>
<img style="position: absolute;width:100%;left:0;top:0;" src="images/android_mask.png" />
<script type="text/javascript">

var w_url = "";
var ss_url = "suishou://app.youhui.cn/WebStylePage?url="+GetQueryString("c_url");
	<%
		if(!RequestUtil.isFromWeixin(request)){
			out.print("w_url=\"http://youhui.cn\";");
		}
	%>
	
	jumpToSS(ss_url, w_url);

	function GetQueryString(name){   //获取浏览器的参数
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return unescape(r[2]); return '';
	}
	
	function init(){
		var vheight = $(document).height();
		$('div').css('height',vheight);
	}
	
</script>
</body>
</html>