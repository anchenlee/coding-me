<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="cn.youhui.utils.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
    <title>8.8折充话费-随手优惠</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta content="telephone=no" name="format-detection" /><!--忽略将页面中的数字识别为电话号码-->
    <link rel="stylesheet" type="text/css" href="css/af/icons.css" />
    <link rel="stylesheet" type="text/css" href="css/af/af.ui.css" />
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <script type="text/javascript" src="js/ui/appframework.ui.min.js"></script>
    <%
    String version=request.getParameter("tyh_web_version");
    String platform=request.getParameter("tyh_web_platform");
    String activityStr = "{\"isshare\":true,\"title\":\"国庆话费最低价！\",\"content\":\"话费兑换8.8折，到哪都没的低价！兑完再送集分宝，2014年9月30日-10月8日记得准时来参加哦！\",\"clickurl\":\"http://v2.api.njnetting.cn/huafei8-8/share_wx.html\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/2xzf0ei9glh.jpg\",\"activity_key\":\"twyjghyd\",\"jifen_num\":\"2\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
	String str=ShareUtil.getShareUrl(platform,"全面开启加速模式", activityStr, version);
    %>
    <script type="text/javascript">
        $.ui.ready(function(){
              //$.ui.toggleHeaderMenu(false);
               $.ui.removeFooterMenu();
               $.feat.nativeTouchScroll=false;
        })	    	
    </script>
    <style>
        #download{width:100%;position:relative;z-index:10;}
        #afui > #header{height: auto;border-bottom:none}
    </style>
</head>
<body>
<div id="afui">
    <div id="content">
        <div style="background:#51bdee" id="start" data-header="none" class="panel"><!--    the 兑换中心首页!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            <div style="background: #51bdee">
                <img class="width100" src="img/middle_autumn/share_bg.jpg" alt=""/>
                <img onclick="window.open('<%=str %>')" class="width100" style="margin-top:-10%" src="img/middle_autumn/public_share_btn.png" alt=""/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/appframework.js"></script>
<script type="text/javascript" src="js/configuration.js"></script>
<script type="text/javascript">
	function share(){
    	var share_str = '{"sns_type":"weixin","request_code":"","share_type":"activity_type","click_url":"http://v2.api.njnetting.cn/huafei8-8/share_wx.html","img_url":"http://static.etouch.cn/suishou/ad_img/2z1ey5837ct.jpg","content":"话费充值8.8折，到哪都没的低价！充完再送集分宝，赶紧来参加哦！","title":"话费8.8折兑换","item_id":"","activity_key":"6xq2thmr","channel":"own","jifenbao_num":"0","sst":""}';
    	local_info.share(share_str);
    }
	String activityStr = "{\"isshare\":true,\"title\":\"奖励加速喽！\",\"content\":\"6月1日起，随手君为您全面开启集分宝奖励加速通过模式，让您不再需要苦苦等待15天啦！赶快把这个普大喜奔的消息扩散给你的小伙伴吧！\",\"clickurl\":\"http://d.b17.cn/sactivity/jiasu/\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/6a27yxxa8l.jpg\",\"activity_key\":\"2go4xwb9\",\"jifen_num\":\"0\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
	out.print(ShareUtil.getShareUrl("全面开启加速模式", activityStr, version));
</script>
</body>
</html>