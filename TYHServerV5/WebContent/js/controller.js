var castingFlag = 0,
	IMGNUMERS = 4, //轮播图片个数
	nowImg = 1,
	timeoutID,  //定时器
	clickflag = 0; //点击控制
$(document).ready(function() {
	set_meta();
	init();
	$('img').lazyload();
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
	$(window).scroll(function(){
        var offset = $(document).scrollTop();
        if(offset>740){
            $('.navbar-wrapper').css({"position":"fixed","top":"0"});
        }
        else{
            $('.navbar-wrapper').css({"position":"absolute","top":"740px"});
        }
    });	
	$('.2w_code').click(function(){//点击跳转suishou.cn
		window.open('http://www.suishou.cn');
	});
	$('.2w_code').mouseover(function(){//显示二维码
		$('.real_2w_code').show();
	});
	$('.2w_code').mouseout(function(){//隐藏二维码
		$('.real_2w_code').hide();
	});
});

function fontControl(){
	$('.adCasting1').css('left','0').css('top','0');
	$('.adCasting2').css('left','1200px').css('top','0');
	$('.adCasting3').css('left','2400px').css('top','0');
	$('.adCasting4').css('left','3600px').css('top','0');
	var view_width = $(window).width();
	var text_width = view_width / 25;
	if (view_width < 640) {
		$('.content_title').css('font-size',text_width+'px');
	}
}

function back_home_page(){
	window.location.href="index.html";
}

function init(){  //初始化页面
	$('#nav ul').empty();
	$('.navbar-wrapper').empty();
	$('#ad-body').empty();
	$('#pic_list').empty();
	$('#text_list').empty();
	$('#ico_list ul').empty();
	$('#copyright').hide();
	var view_width = $(window).width();
	$('#turn_play').width(view_width+'px');
	$('#slide').width(view_width+'px');
	var pare = '';
	var son2 = '';
	for(var m = 0; m<nav_information.length; m++){//生成导航
		if(m==0){
			pare += '<li class="li_title_2 di active">'+nav_information[m].tag_name+'</li>';
			son2 += '<li onclick="javascript:scroll(0,0)" style="background: none;padding-bottom: 15px;"><img class="width100" src="images/logo.png" /></li>';
		}else{
			pare += '<li onclick="check_more(this)" name="'+nav_information[m].tag_id+'" class="li_title_2 di">'+nav_information[m].tag_name+'</li>';
			son2 += '<li><a href="#part'+m+'">'+nav_information[m].tag_name+'</a></li>';
		}
	}
	$('#nav ul').append(''+pare+'');
	$('.navbar-wrapper').append(''+son2+'');
	//商品列表
	$.ajax({
		url:'./GetItemTagsForWeb',
		type:'POST',
		success:function(ret){
			var JSONObj = eval('('+ret+')');
			var son = '';
			for(var i=0;i<JSONObj.length;i++){  //商品大类的数组
				var j = i + 1;
				son += '<div class="list-body">'
							+'<div class="list-title">'
								+'<span id="part'+j+'" class="list-type"><b>'+JSONObj[i].tag_name+'</b></span>'
								+'<span class="more cp" name="'+JSONObj[i].tag_id+'" onclick="check_more(this)">更多<em>'+JSONObj[i].tag_num+'</em>款</span>'
							+'</div>'
							+'<ul class="content">';
				for(var j=0; j<JSONObj[i].item.length;j++){
					son += '<li onmouseover="ad_hover(this)" onmouseout="ad_hover_out(this)">';
							if(JSONObj[i].item[j].discount == 0){
								son += '<div class="pic pr"><img onclick="open_detail('+JSONObj[i].item[j].item_id+')" src="'+JSONObj[i].item[j].pic_url+'" alt=""></div>';
							}else{
								son += '<div class="pic pr"><i class="rate">'+JSONObj[i].item[j].discount+'</i><img onclick="open_detail('+JSONObj[i].item[j].item_id+')" src="'+JSONObj[i].item[j].pic_url+'" alt=""></div>';
							}
					son	+='<div class="postage"><span class="price">￥<em>'+JSONObj[i].item[j].price_low+'</em></span><span class="old_price">￥<em>'+JSONObj[i].item[j].price_high+'</em></span></div>'
							+'<div onclick="open_detail('+JSONObj[i].item[j].item_id+')" class="title">'+JSONObj[i].item[j].title+'</div>'
							+'<a class="link" onclick="open_detail('+JSONObj[i].item[j].item_id+')" title="">立即购买</a>'
							+'</li>';
				}
				son += '</ul></div>';
			}
			$('#ad-body').append(''+son+'');
			$('#copyright').show();
		}
	});
	//商品列表结束
	//轮播图片
	var uid = GetQueryString('uid');
	$.ajax({
		url:'./SuperDiscountForWeb',
		type:'POST',
		data:'uid='+uid,
		async:false,
		success:function(ret){
			var JSON = eval('('+ret+')');
			var child1 = '';
			var child2 = '';
			var child3 = '';
/*			if(JSON.length < 5){//hide轮播翻页
				$('#btn_prev').hide();
				$('#btn_next').hide();
			}*/
			for(var i=0; i<JSON.length; i++){
				if(JSON[i].isSecondKill == 1){//是秒杀
					if(i==0){
						child1 += '<a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank"><li style="z-index:2;opacity:1;fliter:alpha(opacity=100);background-image:url('+JSON[i].imgBig+');background-color:#'+JSON[i].color+'"></li></a>';
						child2 += '<li><h2 class="show"><a href="http://www.suishou.cn" target="_blank">'+JSON[i].title+'</a></h2></li>';
						child3 += '<li class="active"><a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank"><img src="'+JSON[i].imgSmall+'" /></a></li>';
					}else{
						child1 += '<a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank"><li style="background-image:url('+JSON[i].imgBig+');background-color:#'+JSON[i].color+'"></li></a>';
						child2 += '<li><h2><a href="http://www.suishou.cn" target="_blank">'+JSON[i].title+'</a></h2></li>';
						child3 += '<li><a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank"><img src="'+JSON[i].imgSmall+'"/></a></li>';
					}
				}else{//不是秒杀
					if(i==0){
						child1 += '<a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank"><li style="z-index:2;opacity:1;fliter:alpha(opacity=100);background-image:url('+JSON[i].imgBig+');background-color:#'+JSON[i].color+'"></li></a>';
						child2 += '<li><h2 class="show"><a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank">'+JSON[i].title+'</a></h2></li>';
						child3 += '<li class="active"><a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank"><img src="'+JSON[i].imgSmall+'" /></a></li>';
					}else{
						child1 += '<a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank" target="_blank"><li style="background-image:url('+JSON[i].imgBig+');background-color:#'+JSON[i].color+'"></li></a>';
						child2 += '<li><h2><a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank">'+JSON[i].title+'</a></h2></li>';
						child3 += '<li><a href="http://detail.tmall.com/item.htm?id='+JSON[i].itemId+'" target="_blank"><img src="'+JSON[i].imgSmall+'"/></a></li>';
					}
				}
			}
			$('#pic_list').append(''+child1+'');
			$('#text_list').append(''+child2+'');
			$('#ico_list ul').append(''+child3+'');
		}
	});
	slide_init();//调用轮播方法
}

function check_more(obj){  //查看更多
	var tag_id = $(obj).attr('name');
	window.location.href = 'subindex.html?tag_id='+tag_id;
}

var ad_hover_flag = 0;//鼠标时间标记
function ad_hover(obj){   //鼠标放上去  触发  改变样式
	if(ad_hover_flag ==0){
		ad_hover_flag = 1;
		$(obj).find('.title a').css('color','#d43d22');
		$(obj).find('.link').css('color','#d12f12');
		$(obj).find('.link').css('background','url(images/buy_btn.png) no-repeat 0 0');
	}
}
function ad_hover_out(obj){   //鼠标放上去  触发  改变样式
	if(ad_hover_flag ==1){
		$(obj).find('.title a').css('color','#666');
		$(obj).find('.link').css('color','#3b3b3b');
		$(obj).find('.link').css('background','url(images/buy_btn.png) no-repeat 0 -32px');
		ad_hover_flag = 0;
	}
}

function open_detail(item_id){  //跳转商品详情
	window.open('http://detail.tmall.com/item.htm?id='+item_id);
}

function GetQueryString(name){   //获取浏览器的参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return unescape(r[2]); return '';
}