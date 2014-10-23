<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="cn.youhui.acapi.newuser.TurnplateManager" %>

<!DOCTYPE>
<html>
<head>
<title>幸运大转盘</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
    <style type="text/css">
    body{
    	overflow:scroll;
    	overflow-x:hidden;
    	max-width: 640px;
    	margin: 0 auto;
    	background: #fff;
    }
     .point{
    	top: 32.5%;
    	left: 43.65%;
    	width:13.59375%;
    	outline: none;
    	border: none;
    	-webkit-tap-highlight-color:rgba(0,0,0,0); 
    }
    img{-webkit-tap-highlight-color:rgba(0,0,0,0);}
    .db{display: block;}
    .pr{position: relative;}
    .pa{position: absolute;}
    .rotate_table{
    	width:71.375%;
    	top:15%;
    	left:15%;
    }
    .width100{width:100%}
    .bg1{top: 0;left: 0;z-index: -1;}
    .rotext{width:95%;margin:5% auto 0;}
    .mask{top:0;left:0;background: #000;opacity:0.5;height: 100%;width:100%;z-index: 2000;}
    html{height: 100%;}
    .login,.tb_user{z-index: 3000;position: absolute;top: 10%;left: 0;width:100%;}
    .zhuyin{position: fixed;top:15%;width:100%;z-index: 10000;}
    .mask{position: absolute;z-index: 9000;top:0;left: 0;background: #000;opacity:0.6;height: 100%;width: 100%;}
    </style>
    <script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?75879559e59c3ba3d1664abbcb0b53ce";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
    
</head>
<body style="position: relative;height: 100%;">
	<div id="rotate_content">
		<span class="db pr">
			<img class="bg1 width100" src="images/bg1.jpg" alt="背景" />
			<img style="z-index: 500" class="pa rotate_table db" src="images/rotate.png" alt="转盘" />
			<img style="z-index: 1000" id="img" class="pa point" alt="指针" src="images/point.png" alt="指针" />
		</span>
	</div>
	<img class="prize rotext db" src="images/prize.png" alt="奖品"/>
	<img class="rule rotext db" src="" alt="规则"/>
	<img class="zhuyin" onclick="hide(this)" style="display: none;" src="images/zhuyin.png" alt="主淫"/>
	<div style="display: none;" class="mask pa"></div>
	<img style="display: none;z-index: 10000;" class="login awards" onclick="hreflogin()" src="" alt="奖项">
	<img style="display: none;z-index: 10000;" class="tb_user" onclick="hreflogin()" src="images/tb_user.png" alt="淘宝账号异常">
<script type='text/javascript' src='js/jquery.min.js'></script>
<script src="js/jQueryRotate.2.2.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script type="text/javascript">
var rotate_flag = 0;
$(function(){
	var plat = GetQueryString('tyh_web_platform');
	if(plat == 'iphone'){
		$('.rule').attr('src','images/ios_rule.png');
	}else{
		$('.rule').attr('src','images/android_rule.png');
	}
	var url_param = get_url_param();
	$("#img").rotate({
		bind:{
			click:function(){
				if(rotate_flag == 0){
					rotate_flag = 1;
					$('.rotate_table').rotate({
					 	duration:5000,   //间隔
					 	angle: 0,    //初始角度
	        			animateTo:3600,  //10圈
						easing: $.easing.easeOutSine,  //旋转动画插件 
						callback: function(){
							console.info(1);
						}
				 	})
				 	var result = 0;    //角度
					var str = '';
					var awards = 0;  //几等奖
					$.ajax({
						url:'../turnplate?',
						data:url_param,
						type:'POST',
						async: false,
						success:function(ret){
							var JSONObj = eval('('+ret+')');
							if(JSONObj.turnplate_result.status == 1){
								result = 360 - (parseInt(JSONObj.turnplate_result.result_code) - 0.5) * 72;
								str = JSONObj.turnplate_result.result_str;
								awards = JSONObj.turnplate_result.result_code;
							}else if(JSONObj.turnplate_result.status == 2){
								setTimeout( function(){
									alert('你已经抽过奖！');
								}, 5000);
							}else{
								setTimeout( function(){
									alert('抽奖失败');
								}, 5000);
							}
						}
					});  //ajax请求结束
					if(result > 0){
						$('.rotate_table').rotate({
						 	duration:5000,   //间隔
						 	angle: 0,    //初始角度
		        			animateTo:3600+result,  //2圈++
							easing: $.easing.easeOutSine,  //旋转动画插件 
							callback: function(){
								/* if(window.confirm(''+str+'')){ */
									//console.info(2);
									addmask(awards);
					              /* } */
							}
					 	})
					}
				}
			}   //click结束
		}
	});
	$('#over').bind('click',function(){
		var h = $(document).height();
		$('body').append('<div class="mask"></div>');
		$('.mask').height(h);
		$('.zhuyin').fadeIn();
	})
});

function GetQueryString(name){   //获取浏览器的参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

function hide(obj){
	$('.mask').remove();
	$(obj).hide(500);
}

function get_url_param(){
	var url = window.location.search;
	var url_array = url.split('?');
	return url_array[1];
}

function notifyWebPage(local_info){  //回调
	local_info = eval('('+local_info+')');
	var result = local_info.result_code;
	var uid = local_info.uid;
	var imei = local_info.imei;
	if(result == "OK"){
	$.ajax({
		url:'../getgift?',
		data:'uid='+uid+'&imei='+imei,
		type:'GET',
		success:function(ret){
			console.info('结果:'+ret);
			var JSONObj = eval('('+ret+')');
			var status = JSONObj.turnplate_result.status;
			if(status == 1){   //获奖页面
				location.href = "success.html?code="+JSONObj.turnplate_result.result_code+"&imei="+imei+"&uid="+uid;
			}else if(status == 3){//24小时未领取
				location.href = "success.html?code=10&imei="+imei+"&uid="+uid;
			}else if(status == 2){//淘宝号异常
				var h = $(document).height();
				$('.mask').css('height',h+'px');
				$('.mask').show();
				$('.tb_user').show();
			}
		}
	})
}
}

function addmask(num){  //添加蒙层 出现登录按钮
	var n = parseInt(num);
	var src = '';
	switch(n){
		case 1:src='images/5jfb.png';
			break;
		case 2:src='images/10jfb.png';
			break;
		case 3:src='images/50jfb.png';
			break;
		case 4:src='images/100jfb.png';
			break;
		case 5:src='images/10yuan.png';
			break;
	}
	$('.awards').attr('src',''+src+'');
	var h = $(document).height();
	$('.mask').css('height',h+'px');
	$('.mask').css('display','block');
	$('.login').css('display','block');
}

function hreflogin(){   //点击登录按钮 跳转
	local_info.login();
}

</script>

<script type='text/javascript'>
<%
String imei = request.getParameter("tyh_web_imei");
String uid = request.getParameter("tyh_web_uid");
String isfrom = request.getParameter("is_from");

if("ad".equals(isfrom) && TurnplateManager.getInstance().isNogetHuafei(imei, uid)){
	out.print("location.href = 'success.html?code=5&imei="+imei+"&uid="+uid+"';");
}
if(TurnplateManager.getInstance().hasJoin(imei)){
	out.print("banrotate();");
}
int recode = TurnplateManager.getInstance().getNoGetGiftResult(imei);
if("ad".equals(isfrom) && recode > 0){
	out.print("addmask(" + recode + ")");
}
%>

function banrotate(){//   禁用转盘
	$('#img').attr('src','images/choujianged.png');
	$('#img').attr('id','over');
}

</script>
	<script type="text/javascript" src="http://js.tongji.linezing.com/3481949/tongji.js"></script>
	<noscript>
	<a href="http://www.linezing.com">
	<img src="http://img.tongji.linezing.com/3481949/tongji.gif"/></a>
	</noscript>
</body>
</html>
