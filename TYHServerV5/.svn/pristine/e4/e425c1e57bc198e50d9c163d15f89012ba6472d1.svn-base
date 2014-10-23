<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html><head>
    <title>兑换中心</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta content="telephone=no" name="format-detection" /><!--忽略将页面中的数字识别为电话号码-->
    <link rel="stylesheet" type="text/css" href="css/af/icons.css" />
    <link rel="stylesheet" type="text/css" href="css/af/af.ui.css" />
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <script type="text/javascript" src="js/appframework.js"></script>
    <script type="text/javascript" src="js/configuration.js"></script>
    <script type="text/javascript" src="js/ui/appframework.ui.min.js"></script>
    <script type="text/javascript" src="js/plugins/af.popup.js"></script>
    <script type="text/javascript" src="js/md5.js"></script>
    <script type="text/javascript">
        $.ui.ready(function(){
	    		var jfb = GetQueryString('need_jfb');
	    		var phone_num = GetQueryString('phone_num');
	    		var price = GetQueryString('num');
    			$('#cost_jfb_value').text(''+jfb+''); //话费多少集分宝
    			$('.amount').text(''+price+'');//话费充多少
    			$('#reward_jfb').text(''+price+'');//话费充多少  奖励多少集分宝
    			$('.phone_num_confirm').text(''+phone_num+'');//电话号码
               $.ui.toggleHeaderMenu(false);
               $.ui.removeFooterMenu();
        });
        var u = navigator.userAgent;//判断安卓苹果
        if (u.indexOf('iPhone') > -1) {//苹果手机
            $('#afui').scroller({useJsScroll:true});
            if (!((window.DocumentTouch && document instanceof DocumentTouch) || 'ontouchstart' in window)){
                var script = document.createElement("script");
                script.src = "js/plugins/af.desktopBrowsers.js";
                var tag = $("head").append(script);
            }
        }else{//其他手机
        	$.feat.nativeTouchScroll=true;
        }
    </script>
</head>
<body>
<div id="afui">
    <div id="content">
        <div id="confirm_num" data-header="none" class="panel"><!--  确认 电话号码~   准备兑换-->
            <div class="panel_content">
            <p style="margin-top:10%"><span class="a_f">充值号码：<span class="phone_num_confirm"></span></span></p>
            <p><span class="a_f">充值金额：<span class="amount"></span>元</span></p>
            <p style="text-align:center;margin: 8% 0"><span id="cost_jfb" class="m_f">花费<span id="cost_jfb_value" style="font-size:8rem"></span>个集分宝</span></p>
            <p>兑换后您将获得<span id="reward_jfb"></span>个集分宝</p>
            <input id="cash_code" type="password" placeholder="提现密码" />
            <p id="wrong_code" style="margin: 0;visibility:hidden;"><span class="f_r">提现密码输入错误</span></p>
            <a onclick="succ_or_fail()"><img id="exchange_btn" class="width100" src="img/middle_autumn/exchange_btn.png" alt=""/></a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	var u = navigator.userAgent;//判断安卓苹果
	if(u.indexOf('7_0') > -1){//7.0版本一坨屎
		$('#confirm_num').css('padding-bottom','100%');
	}
    function succ_or_fail() {   //最后一步
    	$('#wrong_code span').text('密码校验中...');
    	$('#wrong_code').css('visibility','visible');
    	var cash_code = $('#cash_code').val();
    	var hash = hex_md5(''+cash_code+'');
    	var tid = GetQueryString('tid');
    	var tyh_web_uid = GetQueryString('tyh_web_uid');
    	$.ajax({
    		type:'POST',
    		url:'../checkcz',
    		data:{'password':hash,'tid':tid},
    		success:function(data){
    			if(data == 1){
    				window.location.href='ex_success.jsp?tyh_web_uid='+tyh_web_uid;
    			}else if(data == 2){ //密码错误
    				$('#wrong_code span').text('提现密码错误');
    				$('#afui input[type="text"]').css('border-color','#ff3552');
    			}else if(data ==3 ){ //余额不足
    				window.location.href='ex_fail.jsp?reason=3&tyh_web_uid='+tyh_web_uid;
    			}else if(data == 5){ // 超时
    				window.location.href='ex_fail.jsp?reason=5&tyh_web_uid='+tyh_web_uid;
    			}else{
    				window.location.href='ex_fail.jsp?tyh_web_uid='+tyh_web_uid;
    			}
    		}
    	})
    }
    
    function GetQueryString(name){   //获取浏览器的参数
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
</script>
</body>
</html>