<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.suishou.some.util.ShareUtil"%>

<%
	String version = request.getParameter("tyh_web_version");
	String url = "http://d.b17.cn/sactivity/huafeicz/an_download.html";
	String shareStr = "{\"isshare\":true,\"title\":\"9.3话费充值活动\",\"content\":\"小伙伴们，中秋充话费，还能狂赚集分宝哦！2014年9月3日0点到24点时间内成功通过随手优惠充值中心为您手机充值的用户，充多少送多少!不要迟到喔!\",\"clickurl\":\""+ url +"\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/2yj37yq5i8s.jpg\",\"activity_key\":\"808jnrw6\",\"channel\":\"weixin,weibo,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
	String cUrl = ShareUtil.getShareUrl("9.3话费充值活动", shareStr, version);
%>

<!DOCTYPE html>
<html><head>
    <title>9.3充话费送集分宝</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <link rel="stylesheet" type="text/css" href="css/af/icons.css" />
    <link rel="stylesheet" type="text/css" href="css/af/af.ui.css" />
    <script type="text/javascript" src="js/appframework.js"></script>
    <script type="text/javascript" src="js/ui/appframework.ui.min.js"></script>
    <script type="text/javascript">
        $.ui.ready(function(){
               $.ui.toggleHeaderMenu(false);
               $.ui.removeFooterMenu();
        })
        $('#afui').scroller({useJsScroll:true});
        if (!((window.DocumentTouch && document instanceof DocumentTouch) || 'ontouchstart' in window)) {
            var script = document.createElement("script");
            script.src = "js/plugins/af.desktopBrowsers.js";
            var tag = $("head").append(script);
        }
    </script>
    <!-- emulate touch on desktop browsers only -->
    <style>
        .width100{display: block;width: 100%}
        #afui .panel{background: #f4ec3e; padding: 0}
        .pr{position: relative}
        .pa{position: absolute}
        .discount{right: 7%;top: -22%;width: 15%}
        .share_btns{text-align: center}
        .share_btns img{display: inline-block;width: 25%}
        #download{top: 0;left: 0;z-index: 10;}
        #download2{top: 0;left: 0;z-index: 10;position:fixed;}
        #jfb_left{color: #ff3552}
        #afui > #header{background: #fff;color: #494949;border-color:#a8a8a8}
        .price_choice span{margin-top: 3%;display: inline-block;width: 7em;height: 3em;line-height: 3em;text-align: center;border: 1px solid #9e9e9e;background: #fff;color: #494949;}
        .price_choice .price_checked{background-image: url(img/middle_autumn/price_chosen.jpg);background-size:100% 100%;border-color: #ff3552;}
        .down{right: 4%;top: 26%;}
    </style>
</head>
<body>
<div id="afui">
    <div id="header">
        <!-- 可以随意添加HTML代码在这里 -->
    </div>
    <div id="content">
        <div id="announce" selected="true" class="panel"><!--    the first   PAGE~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            <div>
                <img class="width100" src="img/middle_autumn/img1_1.jpg" alt=""/>
                <img class="width100" src="img/middle_autumn/img1_2.jpg" alt=""/>
                <img class="width100" onclick="location.href='<%=cUrl%>'" src="img/middle_autumn/img1_3.png" alt=""/>
            </div>
        </div>
        <div id="announce_weixin" selected="" class="panel"><!--    the first  weixin   PAGE~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            <img id="download" class="width100" src="img/middle_autumn/download.jpg" alt=""/>
            <img id="download2" class="width100" src="img/middle_autumn/download.jpg" alt=""/>
            <div class="pr">
                <img class="width100" src="img/middle_autumn/img1_1.jpg" alt=""/>
                <img class="width100" src="img/middle_autumn/img1_2.jpg" alt=""/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    //CustomHtmlSheet
    function showCustomHtmlSheet() {
        $("#afui").actionsheet('<a  >Back</a><a  onclick="alert(\'hi\');" >Show Alert 3</a><a class="red"  onclick="alert(\'goodbye\');">Show Alert 4</a> <a  onclick="alert(\'你好\');">Show Alert 5</a>');
    }
    //CustomJsonSheet
    function showCustomJsonSheet() {
        $("#afui").actionsheet(
                [{
                    text: '返回',
                    cssClasses: 'red',
                    handler: function () {
                        $.ui.goBack();
                    }
                }, {
                    text: 'show alert 5',
                    cssClasses: 'blue',
                    handler: function () {
                        alert("hi");
                    }
                }, {
                    text: 'show alert 6',
                    cssClasses: 'red',
                    handler: function () {
                        alert("goodbye");
                    }
                },{
                    text: '最后一个actionsheet',
                    cssClasses: '',
                    handler: function () {
                        alert("hello");
                    }
                }]);
    }

    function showPopup1() {
        $("#afui").popup("I'm replacing an alert box");
    }

    function showPopup2() {
        $("#afui").popup({
            title: "标题",
            message: "内容This is a test of the emergency alert system!! Don't PANIC!",
            cancelText: "取消",
            cancelCallback: function () {
                alert('点击了取消按钮');
                console.log("cancelled");
            },
            doneText: "确定",
            doneCallback: function () {
                alert('确定的回调函数');
                console.log("Done for!");
            },
            cancelOnly: false
        });
    }
    function showPopup3() {
        $("#afui").popup({
            title: "Login",
            message: "Username: <input type='text' class='af-ui-forms'><br>Password: <input type='text' class='af-ui-forms' style='webkit-text-security:disc'>Email: <input type='text' id='email' name='email' class='af-ui-forms'>",
            cancelText: "Cancel",
            cancelCallback: function () {alert('点击取消了')},
            doneText: "Login",
            doneCallback: function () {
                //alert("Logging in");
                alert($('#email').val());
            },
            cancelOnly: false
        });
    }

</script>
</body>
</html>