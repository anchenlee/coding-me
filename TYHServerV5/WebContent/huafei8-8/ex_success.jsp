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
        <div id="ex_success" style="background:#ececec" data-header="none" class="panel"><!--  兑换成功  -->
            <div class="panel_content">
                <img class="width100" src="img/middle_autumn/success.png" alt=""/>
                <p class="tc"><span class="b_f">话费将在10分钟内到账</span></p>
                <p class="tc">如有疑问请联系企业QQ：800060256</p>
                <img onclick="continue_f()" class="width100" src="img/middle_autumn/continue_exchange.png" alt=""/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	function GetQueryString(name){   //获取浏览器的参数
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)return  unescape(r[2]); return null;
	}
	
	function continue_f(){
		var tyh_web_uid = get_uid();
		window.location.href = 'exchange2.jsp?tyh_web_uid='+tyh_web_uid;
	}
	
	function get_uid(){
		var tyh_web_uid = GetQueryString('tyh_web_uid');
		if(tyh_web_uid == null){
			return '';
		}else{
			return tyh_web_uid;
		}
	}
</script>
</body>
</html>