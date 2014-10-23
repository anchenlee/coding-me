<%@page import="cn.suishou.some.util.FetchData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String uid = request.getParameter("tyh_web_uid");
        String platform = request.getParameter("tyh_web_platform");
        if(uid == null){
        	uid = "";
        }
        if(platform == null){
        	platform = "";
        }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1//DTD//xhtml1-transitional.dtd">
<html>
<head>
	<title>3.8女人我最大</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/controller.js"></script>
	<link rel="stylesheet" href="css/style.css"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    
    <script>
    $(document).ready(function() {
    	function_togg();
        control_position();
    	$(window).resize(function(){control_position()});
    	//var t='{"total": "2","desc": "OK","page": "1","status": "1000","items": [{"item_id": "36495438217","title": "正品番茄派手滋润山羊奶护手霜保湿美白手膜足裂痕死皮情人节礼物","price_low": 3,"price_high": 10,"discount": "30.000002","pic_url": "http://img03.taobaocdn.com/bao/uploaded/i3/T1M1VUFt8hXXXXXXXX_!!0-item_pic.png","click_url": "http://a.m.taobao.com/i36495438217.html","rate": "5"},{"item_id": "36629833663","title": "HelloKitty懒人创意纸巾抽可爱卡通毛绒布艺纸巾盒纸巾套","price_low": 19.9,"price_high": 49.5,"discount": "40.612244","pic_url": "http://img01.taobaocdn.com/bao/uploaded/i1/T1m1R1FvxcXXXXXXXX_!!0-item_pic.png","click_url": "http://a.m.taobao.com/i36629833663.html","rate": "2"}]}';
    	show(930, 1);
    	
        $(window).scroll(function () {
            $(window).scrollTop();//这个方法是当前滚动条滚动的距离
            var margin_height = $(window).height();//获取当前窗体的高度
            $(document).height();//获取当前文档的高度
            var bot = 80; //bot是底部距离的高度
            if ((bot + $(window).scrollTop()) >= ($(document).height() - $(window).height())) {
            	pg = pg + 1;
            	show(tagid, pg);
        	}
        });
    	
    });
    
    function show(tagid, page){
    	var resp =$.ajax({async:false, url:'<%=request.getContextPath()%>/GetTagItems?tagid=' + tagid + '&page=' + page + '&uid=<%=uid%>&platform=<%=platform%>'});
    	
    	var t = resp.responseText;
    	
    	var obj=eval('('+t+')');//解析字符串
    	var isclear = false;
    	if(page == 1){
    		isclear = true;
    	}
    	initialize(obj, isclear);
    }

    </script>
    
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
</head>
<body>
<div id="title">
		<img alt="3.8女人我最大" src="images/title.png" />
</div>
<a id="return_top" href="#content_2" style="cursor:pointer;width:80px;bottom:10px;"><img src="images/1111.png" /></a>

<div id="content_1">
		<div class="content_1">
		<div class="contain">
			<div class="ad">
				<div class="ad_up">
					<div class="up"><a href="http://b17.cn/XgcOOH">
					<%
					String img1 = "images/ad1_1.png";
					if(true){
						img1 = "images/ad1_1_over.png";
					}
					%>
					<img src="<%=img1%>" class="sell_ad_1" border="0" /></a></div>
					<div class="down">
					<span class="left"><span class="big_words">￥3.8</span>元<br/>限<span class="youhui_nums_1">10</span>条</span>
					<span class="right grey old_price"><img class="baoyou"src="images/baoyou.png" /><br/>￥198.00</span></div>
				</div>
				<div class="ad_down">
					<a href="http://b17.cn/XgcOOH"><span class="grey">[伊露琪服饰旗舰店]</span><span class="black"><br />韩版显瘦牛仔小脚女裤</span></a></div>
			</div>

			<div class="ad">
				<div class="ad_up">
					<div class="up"><a href="http://b17.cn/XG3YUW">
					<%
					String img2 = "images/ad1_2.png";
					if(true){
						img2 = "images/ad1_2_over.png";
					}
					%>
					<img src="<%=img2%>" class="sell_ad_2" border="0" /></a></div>
					<div class="down"><span class="left"><span class="big_words">￥3.8</span>元<br/>限<span class="youhui_nums_2">50</span>件</span><span class="right grey old_price">
					<img class="baoyou"src="images/baoyou.png" /><br/>￥48.00</span></div>
				</div>
				<div class="ad_down">
					<a href="http://b17.cn/XG3YUW"><span class="grey">[星纯旗舰店]</span><span class="black"><br />玫瑰滋养嫩白护手霜60g</span></a></div>
			</div>
		</div>

		<div class="contain">
			<div class="ad">
				<div class="ad_up">
					<div class="up"><a href="http://b17.cn/XAjpUN">
					<%
					String img3 = "images/ad1_3.png";
					if(true){
						img3 = "images/ad1_3_over.png";
					}
					%>
					<img src="<%=img3%>" class="sell_ad_3" border="0" /></a></div>
					<div class="down"><span class="left"><span class="big_words">￥3.8</span>元<br/>限<span class="youhui_nums_3">20</span>件</span><span class="right grey old_price"><img class="baoyou"src="images/baoyou.png" /><br/>￥32.00</span></div>
				</div>
					<div class="ad_down">
						<a href="http://b17.cn/XAjpUN"><span class="grey">[忆思源食品专营店]</span><span class="black"><br />陕西超大好吃的野酸枣400g</span></a>
				</div>
			</div>

			<div class="ad">
				<div class="ad_up">
					<div class="up"><a href="http://b17.cn/XdlMeI">
					<%
					String img4 = "images/ad1_4.png";
					if(true){
						img4 = "images/ad1_4_over.png";
					}
					%>
					<img src="<%=img4%>" class="sell_ad_4" border="0" /></a></div>
					<div class="down"><span class="left"><span class="big_words">￥3.8</span>元<br/>限<span class="youhui_nums_4">50</span>条</span><span class="right grey old_price"><img class="baoyou"src="images/baoyou.png" /><br/>￥128.00</span></div>
				</div>
				<div class="ad_down">
					<a href="http://b17.cn/XdlMeI"><span class="grey">[膜语正品防伪]</span><span class="black"><br />紧致丰胸复方精油10ml</span></a></div>
			</div>
		</div>

		<div class="contain">
			<div class="ad">
				<div class="ad_up"><div class="up"><a href="http://b17.cn/X9Zm2h">
				<%
					String img5 = "images/ad1_5.png";
					if(true){
						img5 = "images/ad1_5_over.png";
					}
					%>
				<img src="<%=img5%>" class="sell_ad_5" border="0" /></a></div>
				<div class="down"><span class="left"><span class="big_words">￥3.8</span>元<br/>限<span class="youhui_nums_5">100</span>件</span><span class="right grey old_price"><img class="baoyou"src="images/baoyou.png" /><br/>￥38.00</span></div>
				</div>
				<div class="ad_down">
					<a href="http://b17.cn/X9Zm2h"><span class="grey">[美体馆正品防伪]</span><span class="black"><br />小S懒人魔茶瘦腰腹享瘦贴片</span>
					</a></div>
			</div>

			<div class="ad">
				<div class="ad_up"><div class="up"><a href="http://b17.cn/XnskK0">
				<%
					String img6 = "images/ad1_6.png";
					if(true){
						img6 = "images/ad1_6_over.png";
					}
					%>
				<img src="<%=img6%>" class="sell_ad_6" border="0" /></a></div>
				<div class="down"><span class="left"><span class="big_words">￥3.8</span>元<br/>限<span class="youhui_nums_6">50</span>件</span><span class="right grey old_price"><img class="baoyou"src="images/baoyou.png" /><br/>￥15.50</span></div>
				</div>
				<div class="ad_down">
					<a href="http://b17.cn/XnskK0"><span class="grey">[正宗黄埔和记]</span><span class="black"><br />燕麦巧克力128克礼包装</span>
					</a></div>
			</div>
		</div>

		<div class="contain">
			<div class="ad">
				<div class="ad_up"><div class="up"><a href="http://b17.cn/XW16dx">
				<%
					String img7 = "images/ad1_7.png";
					if(true){
						img7 = "images/ad1_7_over.png";
					}
					%>
				<img src="<%=img7%>" class="sell_ad_7" border="0" /></a></div>
				<div class="down"><span class="left"><span class="big_words">￥3.8</span>元<br/>限<span class="youhui_nums_7">10</span>件</span><span class="right grey old_price"><img class="baoyou"src="images/baoyou.png" /><br/>￥38.88</span></div>
				</div>
				<div class="ad_down">
					<a href="http://b17.cn/XW16dx"><span class="grey">[livheart旗舰店]</span><span class="black"><br />丽芙羊毛绒款公交卡零钱包</span>
					</a></div>
			</div>
			<div class="ad">
				<div class="ad_up"><div class="up"><a href="http://b17.cn/XcK2vf">
				<%
					String img8 = "images/ad1_8.png";
					if(true){
						img8 = "images/ad1_8_over.png";
					}
					%>
				<img src="<%=img8%>" class="sell_ad_8" border="0" /></a></div>
				<div class="down"><span class="left"><span class="big_words">￥3.8</span>元<br/>限<span class="youhui_nums_8">10</span>件</span><span class="right grey old_price"><img class="baoyou"src="images/baoyou.png" /><br/>￥51.00</span></div>
				</div>
				<div class="ad_down">
					<a href="http://b17.cn/XcK2vf"><span class="grey">[伊米妮旗舰店]</span><span class="black"><br />品牌升级定制包包挂饰</span>
					</a></div>
			</div>
		</div>

		</div>
	</div>
	<div id="blank"><img src="images/up.png" class="toggg" alt="0" onclick="function_togg()" /></div>
	<div class="blank_10px"></div>
<div id="content_2">
<div class="content_2">
	<div id="content_2_left">
		<div class="content_2_left">

			<!--div class="contain2">
			<div class="add">
				<a href="">
					<div class="add_up">
						<div class="jifenbao">送
							<span class="jifenbao_s">2</span>倍集分宝
						</div>
						<div class="add_price">￥:
							<span class="price">39.00</span>
						</div>
					</div>
				</a>
				<div class="add_down">
					<a href="">
						<span class="grey">芙蓉宜居 决明子枕头<br />磁疗枕芯 保健护颈...</span>
					</a>
				</div>
			</div>
			<div class="add">
				<a href=""><div class="add_up">
					<div class="jifenbao">送<span class="jifenbao_s">2</span>倍集分宝</div>
					<div class="add_price">￥:<span class="price">39.00</span></div>
				</div></a>
				<div class="add_down"><a href=""><span class="grey">芙蓉宜居 决明子枕头<br />磁疗枕芯 保健护颈...</span></a></div>
			</div>
		</div-->

	</div>
	</div>
	
	<div id="content_2_right">
		<div class="btn btn1" alt="0" onclick="click1()">
			<div class="btn_text">
				<div class="blank_50px"></div>
				<div class="btn_up"><span>8</span><br/>元</div>
				<div class="btn_down">包<br/>邮</div>
			</div>
		</div>
		<div class="btn btn2" alt="1" onclick="click2()">
			<div class="btn_text">
				<div class="blank_50px"></div>
				<div class="btn_up"><span>18</span><br/>元</div>
				<div class="btn_down">包<br/>邮</div>
			</div>
		</div>
		<div class="btn btn3" alt="1" onclick="click3()">
			<div class="btn_text">
				<div class="blank_50px"></div>
				<div class="btn_up"><span>28</span><br/>元</div>
				<div class="btn_down">包<br/>邮</div>
			</div>
		</div>
		<div class="btn btn4" alt="1" onclick="click4()">
			<div class="btn_text">
				<div class="blank_50px"></div>
				<div class="btn_up"><span>38</span><br/>元</div>
				<div class="btn_down">包<br/>邮</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html> 