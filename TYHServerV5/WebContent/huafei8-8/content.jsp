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
    <script type="text/javascript" src="js/ui/appframework.ui.min.js"></script>
    <script type="text/javascript">
        $.ui.ready(function(){
               $.ui.toggleHeaderMenu(false);
               $.ui.removeFooterMenu();
               $.feat.nativeTouchScroll=false;
        });
    </script>
</head>
<body>
<div id="afui">
    <div id="content">
        <div style="background:#51bdee" id="start" selected="true" data-header="none" class="panel"><!--    the 兑换中心首页!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            <div style="background: #51bdee">
                <img class="width100" src="img/middle_autumn/img2_1.jpg" alt=""/>
                <img class="width100" onclick="javascript:window.location.href='http://wvs.m.taobao.com/?pid=mm_30429392_3186533_13298189&backHiddenFlag=1&sche=suishou&sche=suishou'" src="img/middle_autumn/img2_btn1.png" alt=""/>
                <div class="pr" style="margin-top: 5%">
                    <img onclick="jump_to_exchange2()" class="width100" src="img/middle_autumn/img2_btn2.png" alt=""/>
                    <div class="pa discount"><div class="pr"><img style="width:65%" src="img/middle_autumn/img2_discount_bg.png" alt=""/><img style="width:100%;left:-19%;top:-23%" class="pa" src="img/middle_autumn/img2_discount.gif" alt=""/></div></div>
                </div>
                <img class="width100" src="img/middle_autumn/img2_2.png" alt=""/>
                <div class="share_btns">
                    <img src="img/middle_autumn/img2_3.png" onclick="friend_share()" alt=""/>
                    <img src="img/middle_autumn/img2_4.png" onclick="weixin_share()" alt=""/>
                    <img src="img/middle_autumn/img2_5.png" onclick="xinlang_share()" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/appframework.js"></script>
<script type="text/javascript" src="js/configuration.js"></script>
<script type="text/javascript">
	function jump_to_exchange2(){
		var tyh_web_uid = GetQueryString('tyh_web_uid');
		if(tyh_web_uid == null){
			window.location.href='exchange2.jsp?tyh_web_uid=';
		}else{
			window.location.href='exchange2.jsp?tyh_web_uid='+tyh_web_uid;
		}
	}
    
    function GetQueryString(name){   //获取浏览器的参数
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
    
    function weixin_share(){
    	var share_str = '{"sns_type":"weixin","request_code":"","share_type":"activity_type","click_url":"http://v2.api.njnetting.cn/huafei8-8/share_wx.html","img_url":"http://static.etouch.cn/suishou/ad_img/2z1ey5837ct.jpg","content":"话费充值8.8折，到哪都没的低价！充完再送集分宝，赶紧来参加哦！","title":"话费8.8折兑换","item_id":"","activity_key":"6xq2thmr","channel":"own","jifenbao_num":"0","sst":""}';
    	local_info.share(share_str);
    }

    function friend_share(){
    	var share_str = '{"sns_type":"pengyou","request_code":"","share_type":"activity_type","click_url":"http://v2.api.njnetting.cn/huafei8-8/share_wx.html","img_url":"http://static.etouch.cn/suishou/ad_img/2z1ey5837ct.jpg","content":"话费充值8.8折，到哪都没的低价！充完再送集分宝，赶紧来参加哦！","title":"话费8.8折兑换","item_id":"","activity_key":"6xq2thmr","channel":"own","jifenbao_num":"0","sst":""}';
    	local_info.share(share_str);
    }

    function xinlang_share(){
    	var share_str = '{"sns_type":"weibo","request_code":"","share_type":"activity_type","click_url":"http://v2.api.njnetting.cn/huafei8-8/share_wx.html","img_url":"http://static.etouch.cn/suishou/ad_img/2z1ey5837ct.jpg","content":"话费充值8.8折，到哪都没的低价！充完再送集分宝，赶紧来参加哦！","title":"话费8.8折兑换","item_id":"","activity_key":"6xq2thmr","channel":"own","jifenbao_num":"0","sst":""}';
    	local_info.share(share_str);
    }
</script>
</body>
</html>