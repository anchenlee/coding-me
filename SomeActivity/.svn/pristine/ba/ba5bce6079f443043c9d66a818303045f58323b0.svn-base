<%@page import="cn.suishou.some.util.ShareUtil"%>
<%@page import="cn.suishou.some.util.VersionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%
	String version = request.getParameter("tyh_web_version");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>全面开启加速模式</title>
	<script type="text/javascript" src="../js/jquery.min.js"></script><!--jquery包-->
	<link rel="stylesheet" href="css/style.css"/><!--导入css-->
	<script type="text/javascript" src="js/controller.js"></script><!--导入js-->
	<script type="text/javascript" src="js/jquery.lazyload.min.js"></script><!--导入js-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
</head>
<body>
<div id="title" class="pr">
	<img src="images/bg.png" class="bgpic" alt="" />
	<img src="images/jiangli.png" class="jlpic pa" alt="" />
	<img src="images/superman.png" class="smpic pa" alt="" />
	<img src="images/rocket.png" class="rpic pa" alt="" />
</div>
<div id="content" class="pr">
	<img src="images/bg2.png" class="bg2pic" alt="" />
	<p class="ctext pa"><span class="co">6月1日</span>起，确认收货的小额订单，随手君会为您开启集分宝奖励加速通过模式，让您不再需要苦苦等待15天啦！赶快把这个普大喜奔的消息扩散到你的朋友圈吧！
	<br/>
	<span class="co">Ps:点击我的帐户进入集分宝余额查看加速订单列表</span>
	</p>
	<div class="btns pa">
		<img class="fl cp" onclick="acount()" src="images/user.png" alt="" />
		<a href="
		<%
		String activityStr = "{\"isshare\":true,\"title\":\"奖励加速喽！\",\"content\":\"6月1日起，随手君为您全面开启集分宝奖励加速通过模式，让您不再需要苦苦等待15天啦！赶快把这个普大喜奔的消息扩散给你的小伙伴吧！\",\"clickurl\":\"http://d.b17.cn/sactivity/jiasu/\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/6a27yxxa8l.jpg\",\"activity_key\":\"2go4xwb9\",\"jifen_num\":\"0\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
		out.print(ShareUtil.getShareUrl("全面开启加速模式", activityStr, version));
		%>
		" ><img class="fr cp" src="images/share.png" alt="" /></a>
		
	</div>
	
	<div class="footer pa"><img class="kefupic vat" src="images/kefu.png" alt=""/><span class="vat">&nbsp&nbsp如有疑问请咨询随手优惠企业QQ：800060256</span></div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('.jlpic').animate({right:'33%'},80);
	$('.jlpic').animate({right:'27%'},80);
	$('.jlpic').animate({right:'32%'},80);
	$('.jlpic').animate({right:'28%'},80);
	$('.jlpic').animate({right:'31%'},80);
	$('.jlpic').animate({right:'29%'},80);
	$('.jlpic').animate({right:'30%'},80);
	$('.smpic').animate({left:'75%'},1000);
	$('.rpic').animate({top:'30%'},500);
	fontControl();
	$(window).resize(function(){fontControl()});
});

function fontControl(){
	var viewWidth = $(window).width();
	var fontSize = viewWidth / 28;
	var fontSize2 = viewWidth / 36;
	var lineheight = fontSize * 1.5;
	if(fontSize>18){
		$('.ctext').css('font-size',18+'px');
		$('.ctext').css('line-height',36+'px')
		$('.footer').css('font-size2',15+'px');
	}
	else{
		$('.ctext').css('font-size',fontSize+'px');
		$('.ctext').css('line-height',lineheight+'px');
		$('.footer').css('font-size',fontSize2+'px');
	}
}

function acount(){
	<%
	if(VersionUtil.isHigher("4.0.0", version)){
		%>
		location.href = "suishou://app.youhui.cn/WoDeTaoBaoPage";
		<%
	}else{
		%>
		alert('想加速请升级至最新版！');
		<%
	}
	%>
};
</script>
</html> 	