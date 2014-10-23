﻿<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="cn.suishou.some.util.VersionUtil"%>
<%
String version = request.getParameter("tyh_web_version");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="hhtml">
<head>
	<title>麦当劳优惠券抽奖</title>
	<script type="text/javascript" src="../js/jquery.min.js"></script><!--导入js-->
	<link rel="stylesheet" href="css/style.css"/><!--导入css-->
	<script type="text/javascript" src="js/controller.js"></script><!--导入js-->
	<script type="text/javascript" src="js/jquery.lazyload.min.js"></script><!--导入js-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
    	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
</head>
<body onselectstart="return false" oncopy="return false;">
<img id="bbg" class="width100" src="images/bg1.png" alt="">
<div id="content1">
	<img class="cj_bg" src="images/choujiang_bg_ye.png" alt="">
	<img class="cj_btn_ye pa" src="images/choujiang_btn_ye.png" alt="提前签到赚集分宝">
</div>
<img class="width100" src="images/jag1.png" alt="">
<div id="content2">
	<img class="b1 pa" src="images/b1.png" alt="">
	<img class="b2 pa" src="images/b2.png" alt="">
	<img class="machine width100" src="images/machine.png" alt="">
	<img class="flyship" onclick="cj_btn(1)" src="images/flyship.png" alt="了解活动详情">
	<img class="checkLye pa" onclick="cj_btn(3)" src="images/p_check_btn_left.png" alt="点击查看如何抽取">
	<img class="checkRye pa" onclick="cj_btn(4)" src="images/p_check_btn_right.png" alt="点击查看如何使用">
</div>
<img class="width100" src="images/jag2.png" alt="">
<div id="content3">
	<p>&nbsp&nbsp&nbsp&nbsp&nbsp如有疑问请咨询随手优惠企业QQ：800060256</p>
	<img class="kefu pa" src="images/kefu.png" alt="">
</div>
<div class="tanchuang">
	<div id="p_part1">
			<p class="title">随手优惠提示：</p>
			<img class="tenyuan" src="images/white_bg.png" alt="">
			<img class="closeBtn pa" onclick="closeBtn()" src="images/close_btn.png" alt="">
			<img class="confirm pa" onclick="closeBtn()" src="images/confirm_btn.png" alt="">
			<div class="tc_text pa">
				<textarea readonly="readonly" class="txtarea"></textarea>
			</div>
	</div>
</div>
<div id="float_ad">
	<div style="position:relative;">
		<a href="#bbg"><img class="gotop pa" src="images/gotop.png" alt=""></a>
		<a href="javascript:jumptoss('LiWuPage')"><img class="sign pa" src="images/signEveryday.png" alt=""></a>
		<a href="javascript:jumptoss('WoDeTaoBaoPage')"><img class="yue pa" src="images/yue.png" alt=""></a>
		<img class="fixed_img width100" src="images/fixed_img.png" alt="">
	</div>
</div>
</body>
<script>
function jumptoss(page){
	<%
	if(VersionUtil.isHigher("4.0.0", version)){
		%>
		location.href = "suishou://app.youhui.cn/" + page;
		<%
	}else{
		%>
		alert('请升级至最新版！');
		<%
	}
	%>
}
</script>
</html>