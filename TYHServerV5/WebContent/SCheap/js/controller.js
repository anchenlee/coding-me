﻿var loadpage = 1;
var totalpage = 1;
var requestflag = 0;
var usercodesurl ='';
//测试URL
//var shareurl = 'suishou://app.youhui.cn/YouHuiSharePage?title=%E8%B6%85%E7%BA%A7%E6%83%A0%2C%E6%83%A0%E5%88%B0%E8%AE%A9%E4%BD%A0%E5%81%9C%E4%B8%8D%E4%B8%8B%E6%9D%A5&share_type=activity_type&share_encryption_code=eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6Iui2hee6p%2BaDoCzmg6DliLDorqnkvaDlgZzkuI3kuIvmnaUhIiwiY29udGVudCI6Iui3jOegtOecvOmVnOeahOS8mOaDoOWlvei0p%2Bato%2BS4gOazouazoueahOiireadpe%2B8jOi%2FmOS4jei1tue0p%2BadpeaKon5%2B5q%2BP5ZGo5LiA6Iez5ZGo5LqUMTDngrnlh4bml7blvIDmiqLvvIEiLCJjbGlja3VybCI6Imh0dHA6Ly8xMC4wLjAuMjE6ODA4MC90eWhhcGkvU0NoZWFwL2NvcHlfb2ZfaW5kZXguaHRtbCIsImltZ3VybCI6Imh0dHA6Ly9zdGF0aWMuZXRvdWNoLmNuL3N1aXNob3UvYWRfaW1nLzE2cWx3ZnR3MXE1LmpwZyIsImFjdGl2aXR5X2tleSI6IjZyOW44c3Z6IiwiamlmZW5fbnVtIjoiMCIsImNoYW5uZWwiOiJ3ZWl4aW4scGVuZ3lvdSx0eHdiLHFxa2oscmVucmVuLGR1b2JhbixlbWFpbCxzbSJ9';
//sandbox URL
//var shareurl = 'suishou://app.youhui.cn/YouHuiSharePage?title=%E8%B6%85%E7%BA%A7%E6%83%A0%2C%E6%83%A0%E5%88%B0%E8%AE%A9%E4%BD%A0%E5%81%9C%E4%B8%8D%E4%B8%8B%E6%9D%A5&share_type=activity_type&share_encryption_code=eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6Iui2hee6p%2BaDoCzmg6DliLDorqnkvaDlgZzkuI3kuIvmnaUhIiwiY29udGVudCI6Iui3jOegtOecvOmVnOeahOS8mOaDoOWlvei0p%2Bato%2BS4gOazouazoueahOiireadpe%2B8jOi%2FmOS4jei1tue0p%2BadpeaKon5%2B5q%2BP5ZGo5LiA6Iez5ZGo5LqUMTDngrnlh4bml7blvIDmiqLvvIEiLCJjbGlja3VybCI6Imh0dHA6Ly9zYW5kYm94LmFwaS5uam5ldHRpbmcuY24vU0NoZWFwL2NvcHlfb2ZfaW5kZXguaHRtbCIsImltZ3VybCI6Imh0dHA6Ly9zdGF0aWMuZXRvdWNoLmNuL3N1aXNob3UvYWRfaW1nLzE2cWx3ZnR3MXE1LmpwZyIsImFjdGl2aXR5X2tleSI6IjZyOW44c3Z6IiwiamlmZW5fbnVtIjoiMCIsImNoYW5uZWwiOiJ3ZWl4aW4scGVuZ3lvdSx0eHdiLHFxa2oscmVucmVuLGR1b2JhbixlbWFpbCxzbSJ9';
//正式的url

var u = navigator.userAgent;
var platform = "android";
if (u.indexOf("iPad") > -1) {
	platform = "ipad";
}else if (u.indexOf("iPhone") > -1) {
	platform = "iphone";
}else if (u.indexOf("SSAndroidHD") > -1) {
	platform = "apad";
}else if (u.indexOf("SuishouAndroid") > -1) {
	platform = "android";
}

var shareurl = "";
	if((platform == "ipad" || platform == "apad")){
		shareurl = 'suishouhd://app.youhui.cn/YouHuiSharePageHD?title=%E8%B6%85%E7%BA%A7%E6%83%A0%2C%E6%83%A0%E5%88%B0%E8%AE%A9%E4%BD%A0%E5%81%9C%E4%B8%8D%E4%B8%8B%E6%9D%A5&share_type=activity_type&share_encryption_code=eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6Iui2hee6p%2BaDoCzmg6DliLDorqnkvaDlgZzkuI3kuIvmnaUhIiwiY29udGVudCI6Iui3jOegtOecvOmVnOeahOS8mOaDoOWlvei0p%2Bato%2BS4gOazouazoueahOiireadpe%2B8jOi%2FmOS4jei1tue0p%2BadpeaKon5%2B5q%2BP5ZGo5LiA6Iez5ZGo5LqUMTDngrnlh4bml7blvIDmiqLvvIEiLCJjbGlja3VybCI6Imh0dHA6Ly92Mi5hcGkubmpuZXR0aW5nLmNuL1NDaGVhcC9jb3B5X29mX2luZGV4Lmh0bWwiLCJpbWd1cmwiOiJodHRwOi8vc3RhdGljLmV0b3VjaC5jbi9zdWlzaG91L2FkX2ltZy8yZ2t6MG1rbmNiOC5qcGciLCJhY3Rpdml0eV9rZXkiOiI2cjluOHN2eiIsImppZmVuX251bSI6IjAiLCJjaGFubmVsIjoid2VpeGluLHBlbmd5b3UsdHh3YixxcWtqLHJlbnJlbixkdW9iYW4sZW1haWwsc20ifQ%3D%3D';
	}else {
		shareurl = 'suishou://app.youhui.cn/YouHuiSharePage?title=%E8%B6%85%E7%BA%A7%E6%83%A0%2C%E6%83%A0%E5%88%B0%E8%AE%A9%E4%BD%A0%E5%81%9C%E4%B8%8D%E4%B8%8B%E6%9D%A5&share_type=activity_type&share_encryption_code=eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6Iui2hee6p%2BaDoCzmg6DliLDorqnkvaDlgZzkuI3kuIvmnaUhIiwiY29udGVudCI6Iui3jOegtOecvOmVnOeahOS8mOaDoOWlvei0p%2Bato%2BS4gOazouazoueahOiireadpe%2B8jOi%2FmOS4jei1tue0p%2BadpeaKon5%2B5q%2BP5ZGo5LiA6Iez5ZGo5LqUMTDngrnlh4bml7blvIDmiqLvvIEiLCJjbGlja3VybCI6Imh0dHA6Ly92Mi5hcGkubmpuZXR0aW5nLmNuL1NDaGVhcC9jb3B5X29mX2luZGV4Lmh0bWwiLCJpbWd1cmwiOiJodHRwOi8vc3RhdGljLmV0b3VjaC5jbi9zdWlzaG91L2FkX2ltZy8yZ2t6MG1rbmNiOC5qcGciLCJhY3Rpdml0eV9rZXkiOiI2cjluOHN2eiIsImppZmVuX251bSI6IjAiLCJjaGFubmVsIjoid2VpeGluLHBlbmd5b3UsdHh3YixxcWtqLHJlbnJlbixkdW9iYW4sZW1haWwsc20ifQ%3D%3D';
	}
	$(document).ready(function() {
	$(window).resize(function(){
		fontcontrol();
	});
	

	$(window).scroll(function(){ //滚动条滚动时调用
		if(requestflag == 0){
			var wheight = $(window).height();
			var dheight = $(document).height();
			var bot = 50;    //离底部还有 100px
			if (bot + ($(window).scrollTop()) >= (dheight - wheight)) {  // 说明滚动条已达底部 再次加载数据
				if(loadpage < totalpage){
	    			loadpage ++;
	    			requestflag = 1;
	    			init(loadpage);
				}
		    }
		}
	});
});

function start(){
	loadpage = 1;
	totalpage = 1;
	init_first();
	fontcontrol();
}

function init_first(){  //首次加载
	var u = navigator.userAgent;
	var platform = "android";
	if (u.indexOf("iPad") > -1) {
		platform = "ipad";
	}else if (u.indexOf("iPhone") > -1) {
		platform = "iphone";
	}else if (u.indexOf("SSAndroidHD") > -1) {
		platform = "apad";
	}else if (u.indexOf("SuishouAndroid") > -1) {
		platform = "android";
	}

	
	var imggg = new Image();
	imggg.src = 'images/loading.gif';
	var uid = GetQueryString('tyh_web_uid');
	fontcontrol();
	$.ajax({
		url:'../tyh3/super_discount?type=json',
		data:'page=1&uid='+uid+"&platform=" + platform,
		type:'POST',
		success:function(ret){
			//alert('当前页为：'+pagNow+'<=======================>信息内容：'+ret);
			//console.info(ret);
				$('#foo').hide();
				var JSONObj = eval("("+ret+")");
				console.debug(JSONObj);
				$('.preText').text(''+JSONObj.resp.data.yugao.disounting_title+'');
				$('.preText2').text(''+JSONObj.resp.data.yugao.disounting_tips+'');
				//$('.scTitlePre').attr('src',JSONObj.resp.data.yugao.icon);   //点击 明日预告跳转(old)
				/*if(JSONObj.resp.data.status == 2){
					$('.scTitlePre').bind('click',function(){
						window.location.href="outTimePre.html";
					})
				}else if(JSONObj.resp.data.status == 1){
					$('.scTitlePre').bind('click',function(){
						window.location.href="tomorrowPre.html";
					})
				}*/
				$('.scTitlePre').bind('click',function(){   //跳转明日预告
					window.location.href=''+JSONObj.resp.data.yugao.action.action_url+'?uid='+uid+'';
				});
				var son = '';
				usercodesurl = JSONObj.resp.data.user_codes_url;
				totalpage = JSONObj.resp.head.total;
					for(var i=0;i<JSONObj.resp.data.super_day.length;i++){
						for(var j=0;j<JSONObj.resp.data.super_day[i].items.length;j++){
							son += '<div class="pr ad" onclick="script:window.location.href=\''+JSONObj.resp.data.super_day[i].items[j].click_url+'\'" name="'+JSONObj.resp.data.super_day[i].items[j].id+'">'
									+'<div class="adsTop pr">'
									+'<img class="width100 db" src="images/loading.gif" />'
										+'<img style="top:0;left:0;" class="width100 db pa" src="'+JSONObj.resp.data.super_day[i].items[j].img+'" alt="" /><div class="itemtags pa">';
							for(var k=0;k<JSONObj.resp.data.super_day[i].items[j].item_tags.length;k++){//获取 拍下立减 等标签
								son += '<img class="itemtag di" src="'+JSONObj.resp.data.super_day[i].items[j].item_tags[k].color_img+'" alt="" />';
							}
							/*if(JSONObj.resp.data.super_day[i].items[j].is_sold_out == 1){  //卖光了
								son +=	'</div><span class="limitNum db pa">已售罄&nbsp&nbsp&nbsp</span>';
							}else{*/
								son += '</div>';
								if(JSONObj.resp.data.super_day[i].items[j].limit_num > 0){
									son += '<span class="limitNum db pa"><span style="opacity:1;">限量'+JSONObj.resp.data.super_day[i].items[j].limit_num+'件</span>&nbsp&nbsp&nbsp</span>';
								}
							/*}*/
							son += '</div>'
								+'<div class="adsBottom">'
								+'<p class="cr pricees" style="border-bottom:1px dotted #d0d0d0;">';
							if(JSONObj.resp.data.super_day[i].items[j].is_second_kill == 1){ //秒杀显示集分宝
								son += '<span class="price_now">'+JSONObj.resp.data.super_day[i].items[j].second_kill_price+'集分宝</span>';
							}else if(JSONObj.resp.data.super_day[i].items[j].is_second_kill == 0){ //非秒杀显示折扣价
								son += '<span class="price_now">￥'+JSONObj.resp.data.super_day[i].items[j].discount_price+'元</span>';
							}
		
							son += '<span class="useness cg price_before">￥'+JSONObj.resp.data.super_day[i].items[j].original_price+'元</span>'
										+'</p>'
										+'<p class="adInfo cb">'+JSONObj.resp.data.super_day[i].items[j].title+'</p>'
									+'</div>';
							
							if(JSONObj.resp.data.super_day[i].items[j].is_sold_out == 1){   //卖光了
								son += '<div class="pa mask"></div>'
										+'<img class="soldup pa" src="images/soldup.png" alt="已售罄"/>';
							}else if(JSONObj.resp.data.super_day[i].items[j].is_sold_out == 2){
								son += '<div class="pa mask"></div>'
									+'<img class="soldup pa" src="images/timeup.png" alt="已过期"/>';
							}
							son += '</div>';
						}
						if(i == 0){
							$('#contentBody').append('<div class="content pr width100"><span class="pr db"><img class="scTitle2 width100 db" src="images/scTitle2.png" alt="超级惠/进行式" /><span class="pa dateText cw">'+JSONObj.resp.data.super_day[i].title+'</span><span class="pa dateText2 cr">'+JSONObj.resp.data.super_day[i].tips+'</span></span><div class="ads">'+son+'<div style="background:#fff;"><img style="margin-top:5%;" class="share width100" src="images/share.png" /><p style="text-align:center;"><img class="sharebtn" onclick="script:window.location.href=\''+shareurl+'&sns_type=weixin\'" src="images/sharebtn1.png" alt="微信分享" /><img class="sharebtn" onclick="script:window.location.href=\''+shareurl+'&sns_type=pengyou\'" src="images/sharebtn2.png" alt="朋友圈"/><img class="sharebtn" onclick="script:window.location.href=\''+shareurl+'&sns_type=qqkj\'" src="images/sharebtn3.png" alt="qq空间"/><img class="sharebtn" onclick="script:window.location.href=\''+shareurl+'&sns_type=weibo\'" alt="微博" src="images/sharebtn4.png" /><img class="sharebtn" onclick="script:window.location.href=\''+shareurl+'&sns_type=txwb\'" alt="腾讯微博" src="images/sharebtn5.png" /></p></div></div></div>');
						}else{
							$('#contentBody').append('<div class="content pr width100"><span class="pr db"><img class="scTitle2 width100 db" src="images/scTitle2.png" alt="超级惠/进行式" /><span class="pa dateText cw">'+JSONObj.resp.data.super_day[i].title+'</span><span class="pa dateText2 cr">'+JSONObj.resp.data.super_day[i].tips+'</span></span><div class="ads">'+son+'</div></div>');
						}
						fontcontrol();
						requestflag = 0;
						$('img').lazyload();
						son = '';
					}
			}
		})
}

function init(pagNow){  //初始化生成界面
	var imggg = new Image();
	imggg.src = 'images/loading.gif';
	var uid = GetQueryString('tyh_web_uid');
	fontcontrol();
	if(pagNow <= totalpage){//滚动加载页数不能超过总页数
		$('#foo').show();
	$.ajax({
		url:'../tyh3/super_discount?type=json',
		data:'page='+pagNow+'&uid='+uid,
		type:'POST',
		success:function(ret){
			//alert('当前页为：'+pagNow+'<=======================>信息内容：'+ret);
			//console.info(ret);
			setTimeout(function(){  //1s后添加
				$('#foo').hide();
				var JSONObj = eval("("+ret+")");
				/*if(JSONObj.resp.data.status == 2){
					$('.scTitlePre').bind('click',function(){
						window.location.href="outTimePre.html";
					})
				}else if(JSONObj.resp.data.status == 1){
					$('.scTitlePre').bind('click',function(){
						window.location.href="tomorrowPre.html";
					})
				}*/
				var son = '';
				usercodesurl = JSONObj.resp.data.user_codes_url;
				totalpage = JSONObj.resp.head.total;
					for(var i=0;i<JSONObj.resp.data.super_day.length;i++){
						for(var j=0;j<JSONObj.resp.data.super_day[i].items.length;j++){
							son += '<div class="pr ad" onclick="script:window.location.href=\''+JSONObj.resp.data.super_day[i].items[j].click_url+'\'" name="'+JSONObj.resp.data.super_day[i].items[j].id+'">'
									+'<div class="adsTop pr">'
									+'<img class="width100 db" src="images/loading.gif" />'
										+'<img style="top:0;left:0;" class="width100 db pa" src="'+JSONObj.resp.data.super_day[i].items[j].img+'" alt="" /><div class="itemtags pa">';
							for(var k=0;k<JSONObj.resp.data.super_day[i].items[j].item_tags.length;k++){//获取 拍下立减 等标签
								son += '<img class="itemtag di" src="'+JSONObj.resp.data.super_day[i].items[j].item_tags[k].color_img+'" alt="" />';
							}
							/*if(JSONObj.resp.data.super_day[i].items[j].is_sold_out == 1){  //卖光了
								son +=	'</div><span class="limitNum db pa">已售罄&nbsp&nbsp&nbsp</span>';
							}else{*/
								son += '</div>';
								if(JSONObj.resp.data.super_day[i].items[j].limit_num > 0){
									son += '<span class="limitNum db pa"><span style="opacity:1;">限量'+JSONObj.resp.data.super_day[i].items[j].limit_num+'件</span>&nbsp&nbsp&nbsp</span>';
								}
							/*}*/
								
							son += '</div>'
								+'<div class="adsBottom">'
								+'<p class="cr pricees" style="border-bottom:1px dotted #d0d0d0;">';
							if(JSONObj.resp.data.super_day[i].items[j].is_second_kill == 1){ //秒杀显示集分宝
								son += '<span class="price_now">'+JSONObj.resp.data.super_day[i].items[j].second_kill_price+'集分宝</span>';
							}else if(JSONObj.resp.data.super_day[i].items[j].is_second_kill == 0){ //非秒杀显示折扣价
								son += '<span class="price_now">￥'+JSONObj.resp.data.super_day[i].items[j].discount_price+'元</span>';
							}
		
							son += '<span class="useness cg price_before">￥'+JSONObj.resp.data.super_day[i].items[j].original_price+'元</span>'
										+'</p>'
										+'<p class="adInfo cb">'+JSONObj.resp.data.super_day[i].items[j].title+'</p>'
									+'</div>';
							
							if(JSONObj.resp.data.super_day[i].items[j].is_sold_out == 1){   //卖光了
								son += '<div class="pa mask"></div>'
										+'<img class="soldup pa" src="images/soldup.png" alt="已售罄"/>';
							}else if(JSONObj.resp.data.super_day[i].items[j].is_sold_out == 2){
								son += '<div class="pa mask"></div>'
									+'<img class="soldup pa" src="images/timeup.png" alt="已过期"/>';
							}
							son += '</div>';
						}
						$('#contentBody').append('<div class="content pr width100"><span class="pr db"><img class="scTitle2 width100 db" src="images/scTitle2.png" alt="超级惠/进行式" /><span class="pa dateText cw">'+JSONObj.resp.data.super_day[i].title+'</span><span class="pa dateText2 cr">'+JSONObj.resp.data.super_day[i].tips+'</span></span><div class="ads">'+son+'</div></div>');
						fontcontrol();
						requestflag = 0;
						$('img').lazyload();
						son = '';
					}
				},1000);
			}
		})
	}
}

function fontcontrol(){
/*	var view_width = $(window).width();
	var tiny_height = view_width * 0.096666666666667;
	if (view_width>640) {
		$('.adInfo').css('height','61.86666666666px');
	}
	else{
		$('.adInfo').css('height',tiny_height+'px');
	}*/
}

function adBuy(obj){    //跳转到秒杀界面
	//PS是 是否包邮 是否秒杀参数     10包邮不秒杀   11包邮秒杀    00 不包邮不秒杀  01不包邮秒杀
		var id = $(obj).attr('name');
		var uid = GetQueryString('tyh_web_uid');
		window.location.href = 'startRushbuy.html?id='+id+'&uid='+uid+'';
}

function GetQueryString(name){   //获取浏览器的参数
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return unescape(r[2]); return '';
}

function hrefPre(){
	location.href="outTimePre.html";
}

function hrefusercodes(){   //跳转到用户的串码列表
	var uid = GetQueryString('tyh_web_uid');
	window.location.href = ''+usercodesurl+'?uid='+uid;
}

$(function(){
    var opts = {
    		lines: 8, // loading小块的数量
    	     length: 5, // 小块的长度
    	     width: 4, // 小块的宽度
    	     radius: 5, // 整个圆形的半径
    	     corners: 1, // 小块的圆角，越大则越圆
    	     color: '#b5b5b5', // 颜色
    	     speed: 1, // 变换速度
    	     trail: 60, // 余晖的百分比
    	     className: 'spinner', // 给loading添加的css样式名
    	     zIndex: 2e9, // The z-index (defaults to 2000000000)
    	     top: '0', // Top position relative to parent in px
    	     left: '50%' // Left position relative to parent in px    
    };
    var target = document.getElementById('foo');
    var spinner = new Spinner(opts).spin(target);
 });