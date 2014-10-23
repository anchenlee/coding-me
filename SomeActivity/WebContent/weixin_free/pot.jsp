<%@page import="java.net.URLEncoder"%>
<%@page import="cn.suishou.some.dao.UserChooseDao"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
    <title>优惠君福利time</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta content="telephone=no" name="format-detection" /><!--忽略将页面中的数字识别为电话号码-->
    <link rel="stylesheet" type="text/css" href="../qinggong/css/af/icons.css" />
    <link rel="stylesheet" type="text/css" href="../qinggong/css/af/af.ui.css" />
    <script type="text/javascript" src="../qinggong/js/appframework.js"></script>
    <script type="text/javascript" src="../qinggong/js/configuration.js"></script>
    <script type="text/javascript" src="../qinggong/js/ui/appframework.ui.min.js"></script>
    <script src="js/wx_share_script.js" type="text/javascript"></script>
    <style type="text/css">
    	html{font-size:55.5%}
    @media screen and (max-width: 800px){
    	html{font-size:92.5%}
    }
    @media screen and (max-width: 320px){
    	html{font-size:42.5%}
    }
    .width100{width:100%;display:block}
    .width50{width:50%}
    .pa{position:absolute}
    #download{height:44px}
    #afui .panel{padding:0;background:#1a959d}
    #afui > #header{background:none;border-bottom:none}
    .di{display:inline-block}
    .pr{position:relative}
    .pa{position:absolute}
    .fire{bottom:40%;left:0;display:none}
    .afPopup>HEADER{font-size:0}
    .afPopup>FOOTER>A.center{display:none}
    .afPopup{background:#e9ffc4;border:none}
    #headpic{bottom:0;left:40%;width:10%;display:block}
    #username{bottom:0;left:52%;color:#026a71;font-size:2rem}
    </style>
    <script>
    var time_status = 1;
    var fire_status = 0;//1 烧左边    2烧右边
    
	(function() {
		var img = new Image(); //创建一个Image对象，实现图片的预下载
		img.src = 'images/pot/pop_ios.png';
		var img2 = new Image(); //创建另一个Image对象，实现图片的预下载 
		img2.src = 'images/pot/pop.png';
	    imgUrl = 'http://static.etouch.cn/suishou/ad_img/3elopc7qt6c.jpg';
	    link = 'http://d.b17.cn/sactivity/weixin_free/pot.jsp';
	    title = htmlDecode('优惠君福利time');
	    desc = htmlDecode('韩式耐高温砂锅,免费抢到手软,根本停不下来！');
	})();
	</script>
</head>
<body>

<%
String frUrl=request.getRequestURL().toString();
String hasAuthorize=request.getParameter("hasAuthorize");

frUrl = URLEncoder.encode(frUrl, "UTF-8");

 if(hasAuthorize!=null){
	if(!hasAuthorize.equals("OK")){
		response.sendRedirect("http://weixin.suishou.cn/authorization?formerUrl="+frUrl);
	}
}else{
	response.sendRedirect("http://weixin.suishou.cn/authorization?formerUrl="+frUrl);
}
 
	request.setCharacterEncoding("UTF-8");
	String openid=request.getParameter("openid");
	String name=request.getParameter("nickname");
	String nickname=null;
	if(name!=null){
		nickname = name;
		//nickname=new String(name.getBytes("iso-8859-1"), "UTF-8");
	}
	String headimg=request.getParameter("headimg");
	
%>

<div id="afui">
	<div id="custom_header" onclick="javascript:window.location.href='http://youhui.cn'" style="height:44px">
		<img id="download" class="width100" src="images/pot/download.jpg" />
	</div>
    <div id="content">
        <div id="pre" selected="true" data-footer="none" data-header="custom_header" class="panel"><!--    the 兑换中心首页!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->	
			<img class="width100" src="images/pot/bg1.jpg" />
			<div class="pr">
				<img class="width100" src="images/pot/bg2.jpg" />
				<img id="headpic" class="pa" src=<%=headimg %> />
				<div id="username" class="pa" ><%=nickname %></div>
				<div id="openid" style="display: none" ><%=openid %></div>
			</div>
			<img class="width100" src="images/pot/bg.jpg" />
			<div>
				<div id="l_pot" name="0" class="width50 di pr"><img class="width100" src="images/pot/pink_pot.jpg" /><img id="l_fire" class="width100 fire pa" src="images/pot/fire.gif" /></div><!--
				--><div id="r_pot" name="0" class="width50 di pr"><img class="width100" src="images/pot/green.jpg" /><img id="r_fire" class="width100 fire pa" src="images/pot/fire.gif" /></div>
			</div>
		</div>
	</div>
</div>
<script src="../qinggong/js/plugins/af.css3animate.js" type="text/javascript"></script>
<script src="../qinggong/js/plugins/af.scroller.js" type="text/javascript"></script>
<script src="../qinggong/js/plugins/af.popup.js" type="text/javascript"></script>
<script type="text/javascript">
	var u = navigator.userAgent;
	var src_obj;
	if(u.indexOf('iPhone') > -1){//是苹果
		src_obj = {1:'images/pot/pop_ios.png',2:'images/pot/pop_not_begin.png',3:'images/pot/pop_end.png'};
	}else{//不是苹果
		src_obj = {1:'images/pot/pop.png',2:'images/pot/pop_not_begin.png',3:'images/pot/pop_end.png'};
	}
<%
if(openid != null && !"".equals(openid)){
	int c = UserChooseDao.getUserChooseDao().getUserChoose(openid);
	if(c > 0){
		out.print("fire_status = "+ c +";");
	}
}
long outTime = 1411347600000l;
if(System.currentTimeMillis() > outTime){
	out.print("time_status = 3;");
}

%>
	if(fire_status == 1){//判断火焰状态
		$('#l_fire').show();
		change_status();
	}else if(fire_status == 2){
		$('#r_fire').show();
		change_status();
	}
	
    var pot;
	$('#l_pot').bind('click',function(){
		pot="l";
		if($(this).attr('name') == 0){
			$('.fire').hide();
			$('#l_fire').show();
			change_status();
			tijiaoChoose();
			setTimeout(function(){
				popup(src_obj[time_status]);
			},500);
		}
	})
	
	$('#r_pot').bind('click',function(){
		pot="r";
		if($(this).attr('name') == 0){
			$('.fire').hide();
			$('#r_fire').show();
			change_status();
			tijiaoChoose();
			setTimeout(function(){
				popup(src_obj[time_status]);
			},2000);
		}
	})
	
	function change_status(){ //禁止多次戳
		$('#l_pot').attr('name',1);
		$('#r_pot').attr('name',1);
	}
	
	function popup(src){ //弹出
		$(document.body).popup({
			id:"myPopup",
		    title: "",
		    message: "<img class='width100' src='"+src+"' />",
		    cancelOnly: true
		})
	}
	
	function tijiaoChoose() { 
		var name=$("#username").text();
		var openid=$("#openid").text();
    	$.ajax({
    		url:"../userchoose",
        	type:"post",
        	data:"nickname="+name+"&choose="+pot+"&openid="+openid,
    	});
    }  
</script>
</body>
</html>