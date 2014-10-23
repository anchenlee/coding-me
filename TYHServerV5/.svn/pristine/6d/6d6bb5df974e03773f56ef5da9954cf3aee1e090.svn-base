var now_page = 1;
var total_pages = 5;
$(document).ready(function(){
	set_meta();
	$('img').lazyload();
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
	
	var opts = {
    		lines: 11, // loading小块的数量
    	     length: 5, // 小块的长度
    	     width: 4, // 小块的宽度
    	     radius: 5, // 整个圆形的半径
    	     corners: 1, // 小块的圆角，越大则越圆
    	     color: '#000', // 颜色
    	     speed: 1, // 变换速度
    	     trail: 60, // 余晖的百分比
    	     className: 'spinner', // 给loading添加的css样式名
    	     zIndex: 2e9, // The z-index (defaults to 2000000000)
    	     top: '0', // Top position relative to parent in px
    	     left: '50%' // Left position relative to parent in px 
    };
    var target = document.getElementById('foo');
    var spinner = new Spinner(opts).spin(target);
	
	var range = 50;             //距下边界长度/单位px
	var totalheight = 0;
	$(window).scroll(function(){
		var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度) 
		totalheight = parseFloat($(window).height()) + parseFloat(srollPos);
		if(($(document).height()-range) <= totalheight){//滚动条到底部的判断
			var tag_id = GetQueryString('tag_id');
			var fuckid = 0;
			var current_name = parseInt($('.current').attr('name'));
			if(current_name != tag_id){  //判断二级分类
				fuckid = current_name;
			}else if(current_name == tag_id){//判断二级分类
				fuckid = tag_id;
			}
			now_page++;
			if(now_page < total_pages){
				$('#foo').show();
				page_init(fuckid,now_page);  //滚动加载
			}
		}
		if(srollPos > 400){
			$('.plane').show().next().show();
		}
		if(srollPos <= 400){
			$('.plane').hide().next().hide();
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

function init(page){  //初始化页面
	var tag_id = GetQueryString('tag_id');
	var pare = '';
	for(var m = 0; m<nav_information.length; m++){//生成导航
		if(tag_id == nav_information[m].tag_id){
			pare += '<li onclick="jump_newtag('+nav_information[m].tag_id+')" class="li_title_2 di active">'+nav_information[m].tag_name+'</li>';
		}else{
			if(nav_information[m].tag_id == 0000){//首页
				pare += '<li onclick="back_home_page()" class="li_title_2 di">'+nav_information[m].tag_name+'</li>';
			}else{
				pare += '<li onclick="jump_newtag('+nav_information[m].tag_id+')" class="li_title_2 di">'+nav_information[m].tag_name+'</li>';
			}
		}
	}
	$('#nav ul').append(''+pare+'');
	$("#footer").empty();
	$('.sub-nav').empty();
	$('.main-nav').empty();
	$('#content').empty();
	$.ajax({
		url:'../MoreTagItemsForWeb',
		data:'tag_id='+tag_id+'&main=1&pag_now='+page,
		type:'POST',
		success:function(ret){
			var JSONObj = eval('('+ret+')');
			var son = '';
			var son2 = ''; //二级分类
			for(var i=0;i<JSONObj.tags.length;i++){
				if(i == 0){
					son2 += '<a class="current" name="'+JSONObj.tags[i].id+'" onclick="classify('+JSONObj.tags[i].id+',1,this)">'+JSONObj.tags[i].keyword+'</a>';
				}else{
					son2 += '<a name="'+JSONObj.tags[i].id+'" onclick="classify('+JSONObj.tags[i].id+',1,this)">'+JSONObj.tags[i].keyword+'</a>';
				}
			}
			//商品部分
			son += '<div class="list-body">'
				+'<div class="list-title">'
					+'<span class="list-type"><b>'+JSONObj.tags[0].keyword+'</b></span>'
				+'</div>'
				+'<ul class="content">';
			for(var j=0; j<JSONObj.item_tags[0].items.length;j++){
				son += '<li onmouseover="ad_hover(this)" onmouseout="ad_hover_out(this)">';
				if((JSONObj.item_tags[0].items[j].discount==0)||(JSONObj.item_tags[0].items[j].discount=='')){//没有折扣
					son	+='<div class="pic pr"><img onclick="open_detail('+JSONObj.item_tags[0].items[j].item_id+')" src="'+JSONObj.item_tags[0].items[j].pic_url+'" alt=""></div>'
						+'<div class="postage"><span class="price">￥<em>'+JSONObj.item_tags[0].items[j].price_low+'</em></span></div>';
				}else{//有折扣
					son	+='<div class="pic pr"><i class="rate">'+JSONObj.item_tags[0].items[j].discount+'</i><a onclick="open_detail('+JSONObj.item_tags[0].items[j].item_id+')"><img src="'+JSONObj.item_tags[0].items[j].pic_url+'" alt=""></a></div>'
					+'<div class="postage"><span class="price">￥<em>'+JSONObj.item_tags[0].items[j].price_low+'</em></span><span class="old_price">￥<em>'+JSONObj.item_tags[0].items[j].price_high+'</em></span></div>';
				}
				son += '<div class="title"><a onclick="open_detail('+JSONObj.item_tags[0].items[j].item_id+')">'+JSONObj.item_tags[0].items[j].title+'</a></div>'
							+'<a class="link" onclick="open_detail('+JSONObj.item_tags[0].items[j].item_id+')" title="">立即购买</a>'
						+'</li>';
			}
			son += '</ul></div>';
			$('.sub-nav').append(''+son2+'');
/*			var pare = ''; //一级分类
			for(var k=0; k<JSONObj.parent_tags.length; k++){
				if(tag_id == JSONObj.parent_tags[k].tag_id){
					pare += '<li onclick="jump_newtag('+JSONObj.parent_tags[k].tag_id+')" class="li_title_2 di active"><b>'+JSONObj.parent_tags[k].tag_name+'</b></li>';
				}else{
					pare += '<li onclick="jump_newtag('+JSONObj.parent_tags[k].tag_id+')" class="li_title_2 di"><b>'+JSONObj.parent_tags[k].tag_name+'</b></li>';
				}
			}*/
			//$('#nav ul').append(''+pare+'');
			$('#content').append(''+son+'');
			$('#copyright').show();
/*			$("#footer").paginate({  //调用分页的东西
			    count: JSONObj.pages,  //总个数
			    start: JSONObj.pag_now,  //默认从哪一个开始
			    display: 15, //一次展示几个
			    text_color:'#888',
			    background_color:'#eee',
			    text_hover_color:'#000',
			    background_hover_color:'#cfcfcf',
			    border: false,
			    onChange: function(page){ //点击页码 回调函数
			    	classify(tag_id,page);
			    }//回调结束
			});*/
		}
	});
}

function page_init(tag_id,page){ //滚动条到底部加载商品
	var son3 = '';
	$.ajax({
		url:'../MoreTagItemsForWeb',
		data:'tag_id='+tag_id+'&pag_now='+page,
		type:'POST',
		success:function(ret){
			var JSONObj = eval('('+ret+')');
			total_pages = JSONObj.pages;
			for(var j=0; j<JSONObj.item_tags[0].items.length;j++){
				son3 += '<li onmouseover="ad_hover(this)" onmouseout="ad_hover_out(this)" onclick="open_detail('+JSONObj.item_tags[0].items[j].item_id+')">';
				if((JSONObj.item_tags[0].items[j].discount==0)||(JSONObj.item_tags[0].items[j].discount=='')){//没有折扣
					son3 +='<div class="pic pr"><img src="'+JSONObj.item_tags[0].items[j].pic_url+'" alt=""></div>'
						+'<div class="postage"><span class="price">￥<em>'+JSONObj.item_tags[0].items[j].price_low+'</em></span></div>';
				}else{//有折扣
					son3 +='<div class="pic pr"><i class="rate">'+JSONObj.item_tags[0].items[j].discount+'</i><a><img src="'+JSONObj.item_tags[0].items[j].pic_url+'" alt=""></a></div>'
					+'<div class="postage"><span class="price">￥<em>'+JSONObj.item_tags[0].items[j].price_low+'</em></span><span class="old_price">￥<em>'+JSONObj.item_tags[0].items[j].price_high+'</em></span></div>';
				}
				son3 += '<div class="title">'+JSONObj.item_tags[0].items[j].title+'</div>'
							+'<a class="link" title="">立即购买</a>'
						+'</li>';
			}
			setTimeout(function(){
				$('#foo').hide();
				$('.content').append(''+son3+'');
			},1000);
/*			$("#footer").paginate({  //调用分页的东西
			    count: JSONObj.pages,  //总个数
			    start: JSONObj.pag_now,  //默认从哪一个开始
			    display: 15, //一次展示几个
			    text_color:'#888',
			    background_color:'#eee',
			    text_hover_color:'#000',
			    background_hover_color:'#cfcfcf',
			    border: false,
			    onChange: function(page){ //点击页码 回调函数
			    	classify(tag_id,page);
			    }//回调结束
			});*/
		}
	});
}

function classify(objname,page,obj){  //跳转分类信息
	if(obj){
		$('.sub-nav').find('a').removeClass('current');
		$(obj).addClass('current');
	}
	var tag_id = objname;
	$('#copyright').hide();
	$('#content').empty();
	$("#footer").empty();
	var son3 = '';
	$.ajax({
		url:'../MoreTagItemsForWeb',
		data:'tag_id='+tag_id+'&pag_now='+page,	
		type:'POST',
		success:function(ret){
			var JSONObj = eval('('+ret+')');
			son3 += '<div class="list-body">'
				+'<div class="list-title">'
					+'<span class="list-type"><b>'+JSONObj.item_tags[0].tag_name+'</b></span>'
				+'</div>'
				+'<ul class="content">';
			for(var j=0; j<JSONObj.item_tags[0].items.length;j++){
				son3 += '<li onmouseover="ad_hover(this)" onmouseout="ad_hover_out(this)" onclick="open_detail('+JSONObj.item_tags[0].items[j].item_id+')">';
				if((JSONObj.item_tags[0].items[j].discount==0)||(JSONObj.item_tags[0].items[j].discount=='')){//没有折扣
					son3 +='<div class="pic pr"><img src="'+JSONObj.item_tags[0].items[j].pic_url+'" alt=""></div>'
						+'<div class="postage"><span class="price">￥<em>'+JSONObj.item_tags[0].items[j].price_low+'</em></span></div>';
				}else{//有折扣
					son3 +='<div class="pic pr"><i class="rate">'+JSONObj.item_tags[0].items[j].discount+'</i><a><img src="'+JSONObj.item_tags[0].items[j].pic_url+'" alt=""></a></div>'
					+'<div class="postage"><span class="price">￥<em>'+JSONObj.item_tags[0].items[j].price_low+'</em></span><span class="old_price">￥<em>'+JSONObj.item_tags[0].items[j].price_high+'</em></span></div>';
				}
				son3 += '<div class="title">'+JSONObj.item_tags[0].items[j].title+'</div>'
							+'<a class="link" title="">立即购买</a>'
						+'</li>';
			}
			son3 += '</ul></div>';
			$('#content').append(''+son3+'');
			$('#copyright').show();
/*			$("#footer").paginate({  //调用分页的东西
			    count: JSONObj.pages,  //总个数
			    start: JSONObj.pag_now,  //默认从哪一个开始
			    display: 15, //一次展示几个
			    text_color:'#888',
			    background_color:'#eee',
			    text_hover_color:'#000',
			    background_hover_color:'#cfcfcf',
			    border: false,
			    onChange: function(page){ //点击页码 回调函数
			    	classify(tag_id,page);
			    }//回调结束
			});*/
		}
	});
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

function jump_newtag(tag_id){
	window.location.href = 'subindex.html?tag_id='+tag_id;
}

function open_detail(item_id){  //跳转详情
	window.open('http://detail.tmall.com/item.htm?id='+item_id);
}

function GetQueryString(name){   //获取浏览器的参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return unescape(r[2]); return '';
}