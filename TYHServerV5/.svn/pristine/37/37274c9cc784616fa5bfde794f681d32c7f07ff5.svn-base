<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.youhui.manager.TaobaoManager" %>
<%@ page import="cn.youhui.bean.TeJiaGoodsBean"%>

  <%
		String itemId=request.getParameter("item_id");
  		String title="";
  		String price="";
  		String img="";
		if(itemId!=null){
			TeJiaGoodsBean tb=TaobaoManager.getItem2(itemId);
			if(tb != null){
				title=tb.getTitle();
				price=tb.getPrice_low();
				img=tb.getPic_url();
			}
		}
  %>

<!DOCTYPE html>
<html>
<head>
<title>随手优惠-商品分享</title>
<script type="text/javascript" src="js/jquery.js"></script><!--jquery包-->
<link rel="stylesheet" href="css/style.css"/><!--导入css-->
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script><!--导入js-->
<script type="text/javascript" src="js/wx_share_script.js"></script><!--导入js-->
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
<meta content="telephone=no" name="format-detection" /><!--忽略识别电话号码-->
<script>
 var _hmt = _hmt || [];
(function() {
    var hm = document.createElement("script");
    hm.src = "//hm.baidu.com/hm.js?75879559e59c3ba3d1664abbcb0b53ce";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
    imgUrl = '<%=img%>';
    link = 'http://m.suishou.cn/SCheap/item.jsp?item_id=<%=itemId%>';
    title = htmlDecode('优质好货尽在随手优惠！');
    desc = htmlDecode('<%=title%>');
})();
</script>
</head>

<body id="page-content" class="pr" style="background:#f2f2f2;padding: 0;overflow:scroll">
	<div id="SRBTitleBg" class="pr">
		<img class="width100 db" src="images/loading_big.png" alt="预加载图片" />
		<img style="left:0;top:0;" class="width100 db pa imgtestgray" src="<%=img %>" alt="" />
	</div>
	<div id="SRBcontent" style="background:#fff;">
		<p class="cb" style="font-size:2em"><%=title %></p>
		<p class="original_price" style="color:red;font-size:3em">￥<b><%= price%></b>
		<img class="width100" src="images/line.png" alt="" />
		</p>
 		<div style="padding:0 1%;background:#fff;">
			<div class="SRBdiv1" style="text-align:center">
				<img class="robBtn" onclick="buy_function('<%=itemId%>')" src="images/buyBtn3.png" alt="立即购买" />
			</div>
		</div>
    </div>
    <div style="position:fixed;top:0;z-index:200;background: rgba(0,0,0,.6);width:100%">
		<div class="pr" style="z-index:300">
			<img onclick="javascript:$('#download').hide()" class="download_off" style="width: 5%;right:0;top:0;position: absolute;z-index:350;" src="images/download_off.png" />
			<img onclick="javascript:window.location.href='http://youhui.cn'" class="width100" style="position: relative;z-index:340;display: block;" src="images/download.png" />
		</div>
	</div>
	<div id="phone_mask" onclick="javascript:$('#phone_mask').hide()" style="display:none;position:absolute;top:0;z-index:200;background: rgba(0,0,0,.6);width:100%;height:100% "><!-- 提示按钮的蒙层 -->
		<img id="android" style="width:100%" src="images/android_mask.png" alt="android" />
		<img id="iphone" style="width:100%" src="images/ios_mask.png" alt="iphone" />
	</div>
	<script>
		function buy_function(id){
			var info = window.navigator.userAgent;//micromessenger
			if(info.indexOf('MicroMessenger') > -1){//在微信里
				if(info.indexOf('Android') > -1){ //在安卓上
					$('#phone_mask').show();
					$('#android').show();
					$('#iphone').hide();
				}else if(info.indexOf('iPhone') > -1){//苹果机
					$('#phone_mask').show();
					$('#iphone').show();
					$('#android').hide();
				}
			}
			else{
				window.location.href='http://b17.cn/item?itemid='+id+'';
			}
		}
	</script>
</body>
</html>