<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="cn.suishou.some.util.ShareUtil"%>
<%
	String version = request.getParameter("tyh_web_version");
    String hasUseTimes = request.getParameter("has_use_times");
    if(hasUseTimes == null || "".equals(hasUseTimes)){
    	hasUseTimes = "1";
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>抽奖结果</title>
	<link rel="stylesheet" href="css/style.css"/><!--导入css-->
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="js/controller.js"></script><!--导入js-->
	<script type="text/javascript" src="js/jquery.lazyload.min.js"></script><!--导入js-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
    	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
</head>
<body onselectstart="return false">
<div id="prize_content">
	<div id="p_part1">
		<p>如有疑问请咨询企业客服QQ：800060256</p>
		<img class="tenyuan" src="images/type<%=hasUseTimes%>.png" alt="">
		<img class="closeBtn pa" onclick="choujiangBack()" src="images/close_btn.png" alt="">
		<a href="
		<%
			String activityStr = "{\"isshare\":true,\"title\":\"现金券免费抽！\",\"content\":\"我刚刚参与了#随手优惠#麦当劳现金券免费抽活动，9分钱可享麦当劳10元现金券一张，撸50元免单不是事儿！赶快为身边的吃货转起吧！\",\"clickurl\":\"http://d.b17.cn/sactivity/McDonald/\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/fn1cmn2i85.jpg\",\"activity_key\":\"ugp34xqi\",\"jifen_num\":\"0\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
			out.print(ShareUtil.getShareUrl("现金券免费抽", activityStr, version));
		%>
	"><img class="signNow pa" src="images/shareNow.png" alt="立即分享"/></a>
	</div>
</div>
</body>
</html>