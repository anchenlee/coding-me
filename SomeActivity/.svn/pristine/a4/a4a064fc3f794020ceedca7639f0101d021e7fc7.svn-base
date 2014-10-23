<%@page import="cn.suishou.some.util.ShareUtil"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String version = request.getParameter("tyh_web_version");
	String shareStr = "{\"isshare\":true,\"title\":\"超级惠免单大放送\",\"content\":\"7月29日晚8点【超级惠】，优惠君将携手SKG，给“低头族”送出SKG按摩器免单权！粉儿们的福利真是一波接一波！记得准时来抢哦~\",\"clickurl\":\"http://d.b17.cn/sactivity/weixin_free/\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/24r1ncs5g5g.jpg\",\"activity_key\":\"p9k53306\",\"channel\":\"weixin,weibo,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
	String cUrl = ShareUtil.getShareUrl("超级惠免单大放送", shareStr, version);
%>
<!DOCTYPE html>
<html>
<head>
	<title>微信免单</title>
	<script type="text/javascript" src="js/jquery.min.js"></script><!--jquery包-->
	<link rel="stylesheet" href="css/style.css"/><!--导入css-->
	<script type="text/javascript" src="js/controller.js"></script><!--导入js-->
	<script type="text/javascript" src="js/jquery.lazyload.min.js"></script><!--导入js-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
	<meta content="telephone=no" name="format-detection" /><!--忽略将页面中的数字识别为电话号码-->
	<meta content="black" name="apple-mobile-web-app-status-bar-style" /><!--顶端的状态条样式-->
  	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
</head>
<body>
	<div class="pr"><img src="images/img1.jpg" alt="" /><span class="pa copy">suishouyouhui</span></div>
	<img src="images/img2.jpg" alt="" />
	<img src="images/img3.png" alt="" />
	<img src="images/img4.png" alt="" />
	<img src="images/img5.png" alt="" />
	<div class="pr">
	<img src="images/img6.png" alt="" />
	<a href="<%=cUrl%>"><img class="share pa" src="images/share.png" alt="" /></a>
	</div>
</body>
</html> 	