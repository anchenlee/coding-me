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
	    function GetQueryString(name){   //获取浏览器的参数
		    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		    var r = window.location.search.substr(1).match(reg);
		    if(r!=null)return  unescape(r[2]); return null;
		}
	    
    	function init_check(){//初始化生成
    		var uid = GetQueryString('tyh_web_uid');
    		var son = '';
    		$.ajax({
    			url:'../czrecord',
    			data:'uid='+uid,
    			type:'POST',
    			success:function(ret){
    				var JSONobj = eval('('+ret+')');
    				for(var i=0; i<JSONobj.records.length; i++){
    					son += '<div style="font-size:2.5rem;border-bottom:1px solid #c1c1c1;padding-bottom:3%;margin-top:3%">'+
                					'<span class="record_info" style="width:30%">'+JSONobj.records[i].phone_num+'<br/>兑换'+JSONobj.records[i].cz_num+'元话费</span><!--'+
               		 				'--><span class="record_info" style="width:50%"><span id="r_date">'+JSONobj.records[i].create_time+'</span><br /><span style="color:#ff3552">花费'+JSONobj.records[i].jfb_num+'集分宝</span></span><!--'+
               		 				'--><span class="record_info" style="width:19%"><img id="record_su" src="img/middle_autumn/record_success.png" /></span>'+
               		 			'</div>';
    				}
    				$('#record_content').append(''+son+'');
    			}
    		})
    	}
        $.ui.ready(function(){
               $.ui.toggleHeaderMenu(false);
               $.ui.removeFooterMenu();
               init_check();
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
        <div id="ex_fail" style="background:#ececec" data-header="none" class="panel"><!--  兑换失败   -->
            <div id="record_content" class="panel_content">
            	<!-- <div style="font-size:2.5rem;border-bottom:1px solid #c1c1c1;padding-bottom:3%;margin-top:3%">
            		<span class="record_info" style="width:30%">15165632698<br/>兑换50元话费</span>
            		<span class="record_info" style="width:50%"><span id="r_date">2014年9月3日16:40:50</span><br /><span style="color:#ff3552">花费5000集分宝</span></span>
            		<span class="record_info" style="width:19%"><img id="record_su" src="img/middle_autumn/record_success.png" /></span>
            	</div>	 -->
            </div>
        </div>
    </div>
</div>
</body>
</html>