<%@page import="cn.suishou.some.util.WebUtil"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String step1click = "popup()";
	//String step2click = "popup()";
	//String step1click = "javascript:location.href='http://taoquan.taobao.com/coupon/unify_apply.htm?sellerId=2058908346&activityId=142804925'";
	String step2click = "javascript:location.href='http://b17.cn/XnJ14j'";
%>

<!DOCTYPE html>
<html>
<head>
	<title>免邮试吃疯狂来袭</title>
	<script type="text/javascript" src="js/jquery.min.js"></script><!--jquery包-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
	<meta content="telephone=no" name="format-detection" /><!--忽略将页面中的数字识别为电话号码-->
	<meta content="black" name="apple-mobile-web-app-status-bar-style" /><!--顶端的状态条样式-->
  	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
  	<style type="text/css" media="screen">
	body {
		max-width: 640px;
		margin: 0 auto;
		font-family: 微软雅黑,Arial, Free Sans;
		position: relative;
		height: 100%;
  	}
  	img{display: block;outline: none;-webkit-tap-highlight-color:rgba(0,0,0,0); width:100%}
  	#mask{background: #000;opacity:0.8;height: 100%;width: 100%;z-index: 10;position: absolute;display: none;top:0;left:0;}
  	#popup{position: fixed;top: 15%;z-index: 20;color: #fff;display: none;}
    #download{display: none;position: fixed;bottom: 0;z-index: 200;background: rgba(0,0,0,.6);filter:alpha(opacity=60);_position: absolute;_bottom: auto;_top: expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)));}
  	</style>
</head>
<body>
	<div id="content">
	<img src="images/taste_for_free/img1.jpg" alt="" />
	<img src="images/taste_for_free/img2.jpg" alt="" onclick="javascript:location.href='http://b17.cn/XnJ14j'" />
	<img id="ticket" src="images/taste_for_free/img3.jpg" alt="" onclick="<%=step1click%>" />
	<img src="images/taste_for_free/img4.jpg" alt="" />
	<img id="pocket" src="images/taste_for_free/img5.jpg" alt="" onclick="<%=step2click%>"/>
	<img src="images/taste_for_free/img6.jpg" alt="" onclick="friend_share()" />
	<div id="mask"></div>
	<div id="popup"><img onclick="closepop()" src="images/taste_for_free/ticket_over.png" /></div>
	</div>
	<div id="download">
	<div class="pr" style="z-index:300">
		<img class="download" onclick="location.href='http://youhui.cn/Epai/home/index.html'" style="position: relative;z-index:350;" src="images/jianjia/download.png" />
		<img class="download_off" style="width: 5%;right:0;top:0;position: absolute;z-index:350;" src="images/jianjia/download_off.png" />
	</div>
	</div>
	
	<script type="text/javascript">
	var u = navigator.userAgent;
	if(u.indexOf('MicroMessenger') > -1){//在微信里
	    $('#content img').attr('onclick','');
		$('#download').show();
	}
	
	$('#ticket').touchstart(function(){//领券

	});
	$('#pocket').touchstart(function(){//免邮试吃

	});
	function closepop(){//关闭弹窗
		$('#mask').hide();
		$('#popup').hide();
	}
	function popup(){//弹框
		$('#mask').show();
		$('#popup').show();
	}
	function href(){//跳转
		location.href='http://www.baidu.com';
	}
	
	function friend_share(){
		var share_str = '{"sns_type":"pengyou","request_code":"","share_type":"activity_type","click_url":"http://d.b17.cn/sactivity/weixin_free/taste_for_free.jsp","img_url":"http://static.etouch.cn/suishou/ad_img/2vt4dib51r8.jpg","content":"疯抢“试吃优惠券”获得试吃资格，限量100份，先抢先得，赶紧来抢吧","title":"免邮试吃疯狂来袭","item_id":"","activity_key":"q1u4pz2j","channel":"own","jifenbao_num":"0","sst":""}';
		local_info.share(share_str);
	}
	$('.download_off').bind('click',function(){   //关闭的
		$('#download').hide();
	});
	</script>
</body>
</html>	