<%@page import="cn.suishou.some.dao.JiFenBaoMXManager"%>
<%@page import="cn.suishou.some.util.ShareUtil"%>
<%@page import="cn.suishou.some.util.VersionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    long t712 = 1405180799000l;
    long now = System.currentTimeMillis();
	if(t712 < now){
		response.sendRedirect("./index_over.html");
	}
    
	String uid = request.getParameter("tyh_web_uid");
	String version = request.getParameter("tyh_web_version");
	String platform = request.getParameter("tyh_web_platform");
	int status = 0;
	if(version == null || "".equals(version) || VersionUtil.isLowVersion(platform, version)){
		status = 3;
	}else if(uid == null || "".equals(uid)){
		status = 1;
	}else if(JiFenBaoMXManager.getInstance().getYue(uid) < 9){
		status = 2;
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="hhtml">
<head>
	<title>麦当劳优惠券抽奖</title>
	<link rel="stylesheet" href="css/style.css"/><!--导入css-->
	<script type="text/javascript" src="../js/jquery.min.js"></script><!--导入js-->
	<script type="text/javascript" src="js/controller.js"></script><!--导入js-->
	<script type="text/javascript" src="js/jquery.lazyload.min.js"></script><!--导入js-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
    	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
</head>
<body onselectstart="return false" oncopy="return false;">
<img id="bbg" class="width100" src="images/bg1.png" alt="">
<div id="content1">
	<img class="cj_bg" src="images/choujiang_bg.png" alt="">
	<img class="lights pa" data-name="0" src="images/lights0.png" alt="">
	<img class="cj_btn pa" onclick="choujiang()" src="images/choujiang_btn.png" alt="立即抽取">
	<span class="cj_btn_mask pa">正在抽奖中<span class="cj_btn_point"></span></span>
</div>
<img class="width100" src="images/jag1.png" alt="">
<div id="content2">
	<img class="share_bg" src="images/share_bg.png" alt="">
	<a href="
		<%
			String activityStr = "{\"isshare\":true,\"title\":\"现金券免费抽！\",\"content\":\"我刚刚参与了#随手优惠#麦当劳现金券免费抽活动，9分钱可享麦当劳10元现金券一张，撸50元免单不是事儿！赶快为身边的吃货转起吧！\",\"clickurl\":\"http://d.b17.cn/sactivity/McDonald/\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/fn1cmn2i85.jpg\",\"activity_key\":\"ugp34xqi\",\"jifen_num\":\"0\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
			if(status == 3){
				out.print("indexWeixin1.html");
			}else{
				out.print(ShareUtil.getShareUrl("现金券免费抽", activityStr, version));
			}
		%>
	"><img class="share_btn pa" src="images/share_btn.png" alt=""></a>
	<img class="b1 pa" src="images/b1.png" alt="">
	<img class="b2 pa" src="images/b2.png" alt="">
	<img class="machine width100" src="images/machine.png" alt="">
	<img class="flyship" onclick="cj_btn(1)" src="images/flyship.png" alt="了解活动详情">
	<img class="checkL pa" onclick="cj_btn(3)" src="images/p_check_btn_left.png" alt="点击查看如何抽取">
	<img class="checkR pa" onclick="cj_btn(4)" src="images/p_check_btn_right.png" alt="点击查看如何使用">
</div>
<img class="width100" src="images/jag2.png" alt="">
<div id="content3">
	<p>&nbsp&nbsp&nbsp&nbsp&nbsp如有疑问请咨询随手优惠企业QQ：800060256</p>
	<img class="kefu pa" src="images/kefu.png" alt="">
</div>
<div class="tanchuang">
	<div id="p_part1">
			<p class="title">随手优惠提示：</p>
			<img class="tenyuan" src="images/white_bg.png" alt="">
			<img class="closeBtn pa" onclick="closeBtn()" src="images/close_btn.png" alt="">
			<img class="confirm pa" onclick="closeBtn()" src="images/confirm_btn.png" alt="">
			<div class="tc_text pa">
				<textarea readonly="readonly" class="txtarea"></textarea>
			</div>
	</div>
</div>
<div id="float_ad">
	<div style="position:relative;">
		<a href="#bbg"><img class="gotop pa" src="images/gotop.png" alt=""></a>
		<a href="javascript:jumptoss('LiWuPage')"><img class="sign pa" src="images/signEveryday.png" alt=""></a>
		<a href="javascript:jumptoss('WoDeTaoBaoPage')"><img class="yue pa" src="images/yue.png" alt=""></a>
		<img class="fixed_img width100" src="images/fixed_img.png" alt="">
	</div>
</div>
<script>
function choujiang(){
	<%
		if(status == 1){
	%>
			alert('请先登录！');
	<%
		}else if(status == 2){
	%>
		location.href = "indexJFBShort.html";
		return;
	<%
		}else if(status == 3){
	%>
		location.href = "indexWeixin1.html";
		return;
	<%
		}else{
	%>
	CJing();
	$.ajax({
		url: "<%=request.getContextPath()%>/luckaccj?ac=mcd",
	    type: "POST",
		data: '&uid=<%=uid%>',
		success: function(re){
			 var ret = parseInt(re);
			 if(ret > 100){           //次数不够
				ret = ret - 100;
			    if(ret < 1){
			    	ret = 1;
			    }else if(ret > 5){
			    	ret = 5;
			    }
			    if(ret < 5){
					location.href = "index1to4.jsp?tyh_web_version=<%=version%>&has_use_times=" + ret;
			    }else{
			    	location.href = "index5.html";
			    }
			 }else{
			  switch (ret) {
				case 2:
					location.href = "indexYes.html";
					break;
				case 3:
					location.href = "indexNo.html";
					break;
				case 10:
					alert('活动还没开始呢，不要着急哦，亲');
					break;
				default:
					location.href = "indexNo.html";
			  }
			 }
			 
  		}});
	<%
		}
	%>
}

function jumptoss(page){
	<%
	if(VersionUtil.isHigher("4.0.0", version)){
		%>
		location.href = "suishou://app.youhui.cn/" + page;
		<%
	}else{
		%>
		alert('请升级至最新版！');
		<%
	}
	%>
}

function CJing(){
	$('.cj_btn').css('display','none');
	$('.cj_btn_mask').css('display','inline-block');
	loading();
}

function CJdone(){
	$('.cj_btn').css('display','inline-block');
	$('.cj_btn_mask').css('display','none');
}

function loading(){
	setTimeout(a,300);
	setTimeout(b,600);
	setTimeout(c,900);
	setTimeout(loading,1000);
}

function a(){$('.cj_btn_point').text('.');}
function b(){$('.cj_btn_point').text('..');}
function c(){$('.cj_btn_point').text('...');}
</script>
</body>
</html>