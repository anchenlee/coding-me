<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String taobaoNick = request.getParameter("tyh_web_taobaonick");
    if(taobaoNick == null){
    	taobaoNick = "";
    }
    taobaoNick = new String(taobaoNick.getBytes("iso-8859-1"), "utf-8");
%>
<!DOCTYPE HTML>
<html lang="en-zh">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>岁末感恩，甜蜜回馈</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

	<style type="text/css">
		*{margin:0;padding:0;}
		body{background:#ffddf0;}
		#box,.top,.bottom,img{width:100%;}
		.main{font-size:18px;color:#3e3e3e;line-height:1.8;margin:10%;width:80%;}
		.main p{text-indent:36px;}
		.main h2{font-size:20px;color:#3e3e3e;}
	</style>
</head>
<body>
	<div id="box">
		<div class="top">
			<img src="./images/1.jpg" alt="" />
		</div>
		<div class="main">
			<h2>尊敬的<%=taobaoNick%>：</h2>
			<p>恭喜您免费获得【随手优惠】为您准备的甜蜜好礼一份！</p>
            <p><span style="color:red;">截至目前，我们还未收到您反馈的收奖信息，</span><span style="color:red">请在2013年12月15日前</span>将您的淘宝昵称（当前登录随手优惠的淘宝账号）、姓名、地址、手机号码，发送私信至随手优惠新浪微博官方账号，<span style="color:red">过期作废</span>；我们已陆续寄出奖品，敬请期待！
            </p>
			<br/>
			<h4>活动内容：</h4>
			<p>为了感谢您对随手优惠一年来的支持与信任， 优惠君将向部分忠实用户赠送甜蜜好礼——“松露巧克力礼盒装”。</p>
			<p>本活动仅针对收到通知的用户，每个淘宝账号仅可获得一份！</p>
			<p>随手优惠，暖心到家么么哒！</p>
		</div>
		<div class="bottom">
			<img src="./images/2.jpg" alt="" />
		</div>
	</div>
</body>
</html>