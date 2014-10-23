<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.youhui.manager.UserManager"%>
<%@page import="cn.youhui.manager.UserAccountManager"%>
<%@page import="cn.youhui.bean.UserAccount"%>
<%@page import="cn.youhui.api.huafei.HuafeiCZDAO"%>
<%@page import="java.util.List" %>
<%@page import="cn.youhui.util.oufeicz.PhoneNumAttributionUtil"%>

<%
    String uid = request.getParameter("tyh_web_uid");
	boolean isBindPhone = UserManager.getInstance().isBindPhone(uid);
	UserAccount ua = UserAccountManager.getInstance().getUserAccount(uid);
	int yue = 0;
	if(ua != null){
		yue = ua.getYuE();
	}
	String phoneHis = "";
	String phoneLast = "";
	String attr = "";
	List<String> plist = HuafeiCZDAO.getInstance().getPhoneNumByUid(uid);
	boolean hasRecord = false;
	if(plist != null && plist.size() > 0){
		hasRecord = true;
		phoneLast = plist.get(0);
		attr = PhoneNumAttributionUtil.getPhoneNumAttribution(phoneLast);
		for(String p : plist){
			phoneHis += p + ",";
		}
	}
%>
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
	var rett_code = 0;
    function confirm_num_price(){    //验证  电话号码  和  价格
		var isbind = 0;
		<% if(isBindPhone){%>
		   isbind = 1;
		<%}%>
		var jfb_remain = parseInt($('#jfb_left_value').text());//集分宝余额
		var price = parseInt($('.price_checked').attr('name'));
		var price_jfb = price_discount[price];
		var phone_num = $('.phone_num_exchange').val();
		if(jfb_remain < price_jfb){  //余额不足
    		$('#not_enough').css('visibility','visible');
    		$('#duihuanjia').hide();
    		$('#confirm_num_price_step').attr('onclick','');
    		$('#confirm_num_price_step').attr('src','img/middle_autumn/confirm_btn_gray.png');
    	}else{
    		$('#not_enough').css('visibility','hidden');
    		$('#duihuanjia').show();
    	}
		if(phone_num.length < 11){
			if(phone_num.length == 3){ //输入前3位的时候自动补齐
				var phone_team = $('#phone_team').text().split(",");
				var pt_obj = '{';
				for(var i=0; i<phone_team.length; i++){
					var a = phone_team[i].substr(0,3);
					pt_obj += ''+a+':'+phone_team[i]+',';
				}
				pt_obj = pt_obj.substr(0,pt_obj.length-1);
				pt_obj += '}';
				pt_obj = eval('('+pt_obj+')');
				$('.phone_num_exchange').val(''+pt_obj[phone_num]+'');
			}
		}else{
			$.ajax({
			    type:"POST",
			    url:"../telval",
			    data:{'tel':phone_num,'price':price},
			    success:function(data){
			    	var json_obj = eval('('+data+')');
			    	$('#belong_to_val').text(''+json_obj.attr+'');
			    	rett_code = json_obj.ret_code;
			    	if(json_obj.ret_code == 0){//有问题  不给充值
			    		$('#support').text(''+json_obj.error_msg+'');
			    		$('#support').css('display','inline-block');
			    		$('#confirm_num_price_step').attr('onclick','');
			    		$('#confirm_num_price_step').attr('src','img/middle_autumn/confirm_btn_gray.png');
			    	}else{
			    		$('#support').text('');
			    		$('#support').css('display','none');
			    	}
			    	//alert(jfb_remain+','+price_jfb+','+json_obj.ret_code+','+isbind);
			    	if((jfb_remain >= price_jfb)&&(json_obj.ret_code == 1)&&(isbind == 1)){// 余额够， 电话号码也OK
			    		$('#confirm_num_price_step').attr('onclick','confirm_num_price_step2()');
			    		$('#confirm_num_price_step').attr('src','img/middle_autumn/confirm_btn.png');
			    	}
			    }
			});
		}
	}
    
        function inittt(){
        	//判断是否有电话号码记录
            if($('#phone_team').text().length > 0){
	               	var phone_team = $('#phone_team').text().split(",");
					$('.phone_num_exchange').val(''+phone_team[0]+'');
            }
			//自动填充电话号码
        	//按钮判断
        	confirm_num_price();
        	//按钮判断
        		var jfb_remain = parseInt($('#jfb_left_value').text());//集分宝余额
        		if(jfb_remain < 880){  //余额不足
            		$('#not_enough').css('visibility','visible');
            		$('#duihuanjia').hide();
            	}else{
            		$('#not_enough').css('visibility','hidden');
            		$('#duihuanjia').show();
            	}
               $.ui.toggleHeaderMenu(false);
               $.ui.removeFooterMenu();
        }
    </script>
</head>
<body onload="inittt()">
<div id="afui">
    <div id="content">
        <div id="exchange" style="padding-bottom:10%;background:#ececec" data-header="none" class="panel"><!--    the    兑换中心  选择你的充值价格 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            <span id="phone_team" style="display: none"><%=phoneHis%></span><!-- 储存电话号码记录 -->
            <div class="panel_content">
                <p style="margin: 10% 0 5% 0"><span class="s_f">集分宝余额：<span id="jfb_left_value" class="f_r"><%=yue%></span></span></p>
                <div class="pr"><input oninput="confirm_num_price()" class="phone_num_exchange" maxlength="11" type="text" placeholder="手机号码"/></div>
                <p style="margin:3% 0"><span id="belong_to" class="s_f">归属地：<span id="belong_to_val"><%=attr%></span><span id="support">该地区不支持兑换</span></span></p>
                <p class="price_choice"><span class="price_checked" onclick="chose_price(this)" name="10" class="price_checked">10元</span><span onclick="chose_price(this)" name="20" style="">20元</span><span onclick="chose_price(this)" name="30">30元</span></p>
                <p class="price_choice"><span onclick="chose_price(this)" name="50">50元</span><span onclick="chose_price(this)" name="100" style="">100元</span><span style="visibility:hidden;" onclick="chose_price(this)" name="200">200元</span></p>
                <p id="duihuanjia" style="margin: 7% 0;"><span class="s_f">兑换价：<img style="display: inline-block;vertical-align: top;width: 15%;margin:0 3%" src="img/middle_autumn/9discount.png" alt=""/><span class="jfb_nums f_r b_f">880</span>个集分宝</span></p>
                <p id="not_enough" style="visibility:hidden;text-align:center;margin:7% 0;" class="tc">
                	<span class="tc f_r" style="font-size:4rem">兑换需要<span class="jfb_nums">880</span>个集分宝</span><br />
                	<span class="tc f_r" style="font-size:2.6rem">亲的余额不足哦</span>
                </p>
                <%----%>
                <% if(!isBindPhone){%>
	            	<p id="set_cash_code"><img onclick="set_code()" class="width100" src="img/middle_autumn/set_cash_code.png" /></p>
	            <%}%>
	            <% if(isBindPhone){%>
                <form action="../huafeicz" method="post" id="myform">
                	<input id="form_phone_num" name="phone_num" value="" type="hidden" />
                	<input id="form_num" name="num" value="" type="hidden" />
                	<input id="form_uid" name="uid" value="" type="hidden" />
                	<img id="confirm_num_price_step" onclick="" style="margin-top: 5%" class="width100" src="img/middle_autumn/confirm_btn_gray.png" alt=""/>
                </form>
                <%}%>
                <%if(hasRecord){%>
                	<img onclick="checkRecord()" id="check_record" class="width100" src="img/middle_autumn/check_record.png" />
                <%}%>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	function chose_price(obj){ //改变要充值的价格
		$('.price_choice span').removeClass('price_checked');
    	$(obj).addClass('price_checked');
    	var price = $(obj).attr('name');
    	var jfb = price_discount[price];
    	$('.jfb_nums').text(''+jfb+'');
    	confirm_num_price();
	}
	
	function set_code(){//跳转至设置密保
		showPopup1();
		window.location.href='suishou://app.youhui.cn/MiBaoSetPage';
	}
	
	function confirm_num_price_step2(){ //提交 电话号码 和 价格
			var price = $('.price_checked').attr('name');
			var phone_num = $('.phone_num_exchange').val();
			var uid = GetQueryString('tyh_web_uid');
			$('#form_phone_num').val(''+phone_num+'');  //phone_num
			$('#form_num').val(''+price+'');  //num
			$('#form_uid').val(''+uid+'');  //uid
			document.getElementById('myform').submit();
	}
    
    function GetQueryString(name){   //获取浏览器的参数
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}

    function showPopup1(){ //弹出框提示刷新
        $('#afui').popup({
        	title:'',
            message:'<img style="margin-top:-20%" onclick="back_first_page()" class="width100" src="img/middle_autumn/refresh_btn.png" />',
           	cancelText:'刷新',
            cancelCallback:function(){
                window.location.href='content.jsp';
            },
            cancelOnly: true
        });
    }
    
    function back_first_page(){//返回首页
        window.location.reload();
    }
    
    function checkRecord(){//跳转至充值记录详情页面
    	var tyh_web_uid = GetQueryString('tyh_web_uid');
    	window.location.href='checkRecord.jsp?tyh_web_uid='+tyh_web_uid;
    }
</script>
</body>
</html>