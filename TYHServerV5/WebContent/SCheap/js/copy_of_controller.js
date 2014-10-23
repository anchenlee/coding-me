var usercodesurl ='';
$(document).ready(function() {
	$(window).resize(function(){
		fontcontrol();
	});
	$('#download').bind('click',function(){
		location.href="http://youhui.cn/Epai/home/index.html";
	});
/*	$('.download_off').bind('click',function(){
		$('#download').hide();
	});*/
});

function start(){
	loadpage = 1;
	totalpage = 1;
	init(loadpage);
	fontcontrol();
}

function init(pagNow){  //初始化生成界面
	//var tyh_web_uid = GetQueryString('tyh_web_uid');
	var imggg = new Image();
	imggg.src = 'images/loading.gif';
	fontcontrol();
	$.ajax({
		url:'../tyh3/super_discount?type=json',
		data:'page='+pagNow,
		type:'POST',
		success:function(ret){
			console.info(ret);
			//alert('当前页为：'+pagNow+'<=======================>信息内容：'+ret);
				var JSONObj = eval("("+ret+")");
				$('.preText').text(''+JSONObj.resp.data.yugao.disounting_title+'');
				$('.preText2').text(''+JSONObj.resp.data.yugao.disounting_tips+'');
				$('.scTitlePre').attr('src',JSONObj.resp.data.yugao.icon);   //点击 明日预告跳转(old)
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
						window.location.href=''+JSONObj.resp.data.yugao.action.action_url+'';
					});
				var son = '';
				usercodesurl = JSONObj.resp.data.user_codes_url;
				var u = navigator.userAgent;
						for(var j=0;j<JSONObj.resp.data.super_day[0].items.length;j++){
							if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
								son += '<div class="pr ad" onclick="jumpto(1,\''+JSONObj.resp.data.super_day[0].items[j].click_url+'\',\''+JSONObj.resp.data.super_day[0].items[j].click_web+'\')" name="'+JSONObj.resp.data.super_day[0].items[j].id+'">';
							} else if (u.indexOf('iPhone') > -1) {//苹果手机
								son += '<div class="pr ad" onclick="jumpto(2,\''+JSONObj.resp.data.super_day[0].items[j].click_url+'\',\''+JSONObj.resp.data.super_day[0].items[j].click_web+'\')" name="'+JSONObj.resp.data.super_day[0].items[j].id+'">';
							}else{
								son += '<div class="pr ad" onclick="jumpto(3,\''+JSONObj.resp.data.super_day[0].items[j].click_url+'\',\''+JSONObj.resp.data.super_day[0].items[j].click_web+'\')" name="'+JSONObj.resp.data.super_day[0].items[j].id+'">';
							}
								son	+= '<div class="adsTop pr">'
									+'<img class="width100 db" src="images/loading.gif" />'
										+'<img style="top:0;left:0;" class="width100 db pa" src="'+JSONObj.resp.data.super_day[0].items[j].img+'" alt="" /><div class="itemtags pa">';
							for(var k=0;k<JSONObj.resp.data.super_day[0].items[j].item_tags.length;k++){//获取 拍下立减 等标签
								son += '<img class="itemtag di" src="'+JSONObj.resp.data.super_day[0].items[j].item_tags[k].color_img+'" alt="" />';
							}
	
							if(JSONObj.resp.data.super_day[0].items[j].is_sold_out == 1){  //卖光了
								son +=	'</div><span class="limitNum db pa">已售罄&nbsp&nbsp&nbsp</span>';
							}else{
								if(JSONObj.resp.data.super_day[0].items[j].limit_num > 0){
									son +=	'</div><span class="limitNum db pa"><span style="opacity:1;">限量'+JSONObj.resp.data.super_day[0].items[j].limit_num+'件</span>&nbsp&nbsp&nbsp</span>';
								}else{
									son +=	'</div>';
								}
							}
							son += '</div>'
								+'<div class="adsBottom">'
								+'<p class="cr pricees" style="border-bottom:1px dotted #d0d0d0;">';
							if(JSONObj.resp.data.super_day[0].items[j].is_second_kill == 1){ //秒杀显示集分宝
								son += '<span class="price_now">'+JSONObj.resp.data.super_day[0].items[j].second_kill_price+'集分宝</span>';
							}else if(JSONObj.resp.data.super_day[0].items[0].is_second_kill == 0){ //非秒杀显示折扣价
								son += '<span class="price_now">￥'+JSONObj.resp.data.super_day[0].items[j].discount_price+'元</span>';
							}
							son += '<span class="useness cg price_before">￥'+JSONObj.resp.data.super_day[0].items[j].original_price+'元</span>'
										+'</p>'
										+'<p class="adInfo cb">'+JSONObj.resp.data.super_day[0].items[j].title+'</p>'
									+'</div>';
							if(JSONObj.resp.data.super_day[0].items[j].is_sold_out == 1){   //卖光了
								son += '<div class="pa mask"></div>'
										+'<img class="soldup pa" src="images/soldup.png" alt="已售罄"/>';
							}
							son += '</div>';
						}
						$('.hehe_content').append('<div class="content pr width100"><span class="pr db"><img class="scTitle2 width100 db" src="images/scTitle2.png" alt="超级惠/进行式" /><span class="pa dateText cw">'+JSONObj.resp.data.super_day[0].title+'</span><span class="pa dateText2 cr">'+JSONObj.resp.data.super_day[0].tips+'</span></span><div class="ads">'+son+'</div></div>');
						fontcontrol();
						requestflag = 0;
						$('img').lazyload();
			}
		})
}

function fontcontrol(){
	var view_width = $(window).width();
	var huge_text_width = view_width / 20;
	var big_text_width = view_width / 25;
	var middle_text_width = view_width / 28;
	var tiny_text_width = view_width / 30;
	var tiny_height = tiny_text_width * 2.9;
	if (view_width>640) {
		$('#contentPre').css('font-size','22.85714285714286px');
		$('.dateText').css('font-size','25.6px');      //big
		$('.dateText2').css('font-size','22.85714285714286px');
		$('.ads').css('font-size','22.85714285714286px');
		$('.price_before').css('font-size','21.33333333333333px');  //tiny
		$('.pricees').css('font-size','25.6px');
		$('.adInfo').css('font-size','21.33333333333333px');
		$('.price_now').css('font-size','32px');
		$('.adInfo').css('height','61.86666666666px');
	}
	else{
		$('#contentPre').css('font-size',middle_text_width+'px');
		$('.dateText2').css('font-size',middle_text_width+'px');
		$('.dateText').css('font-size',big_text_width+'px');
		$('.ads').css('font-size',middle_text_width+'px');
		$('.price_before').css('font-size',tiny_text_width+'px');
		$('.pricees').css('font-size',big_text_width+'px');
		$('.adInfo').css('font-size',tiny_text_width+'px');
		$('.adInfo').css('height',tiny_height+'px');
		$('.price_now').css('font-size',huge_text_width+'px');
	}
}

function GetQueryString(name){   //获取浏览器的参数
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return unescape(r[2]); return '';
	}

function jumpto(url,click_url,click_web){
	var u = navigator.userAgent;
	if(u.indexOf('MicroMessenger') > -1){//在微信里
		switch(url){
			case 1:
				window.location.href='shareMid.jsp';
				break;
			case 2:
				window.location.href='shareMid.jsp';
				break;
			case 3:
				break;
		}
	}else{//不在微信里
		jumpToSS(click_url,click_web);
	}
}