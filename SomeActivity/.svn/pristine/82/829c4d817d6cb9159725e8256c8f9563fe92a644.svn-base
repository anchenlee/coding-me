$(document).ready(function(){
	$(window).resize(function(){fontControl();});
	var tagid = GetQueryString('tagid');
	$.ajax({url: "../GetTagItems?is_need_convert=0", 
	    type: "POST",
		data: 'tagid=' + tagid,
		success: function(ret){
			var JSONObj = eval('('+ret+')');
			var itemLength = JSONObj.items.length;
			for(var i=0;i<itemLength;i++){
				if((JSONObj.items[i].discount >= 10) || (JSONObj.items[i].discount <= 0)){
					$('#content').append('<div class="ads" onclick="javascrtpt:window.location.href=\''+JSONObj.items[i].click_url+'\'">'
							+'<img src="'+JSONObj.items[i].pic_url+'" onclick="javascrtpt:window.location.href=\''+JSONObj.items[i].click_url+'\'" alt="图片链接">'
							+'<div class="adsText di">'
								+'<p class="adsTitle">'+JSONObj.items[i].t_title+'</p>'
								+'<p class="adsSecTitle">'+JSONObj.items[i].title+'</p>'
								+'<p class="price">¥ '+JSONObj.items[i].price_low+'&nbsp&nbsp<span class="oldPrice">¥ '+JSONObj.items[i].price_high+'</span></p>'
								+'<p class="savePrice"><img class="hrefPic" src="images/href.png" onclick="javascrtpt:window.location.href=\''+JSONObj.items[i].click_url+'\'" alt="点击查看"></p>'
							+'</div>'
						+'</div>');
				}
				else{
					$('#content').append('<div class="ads" onclick="javascrtpt:window.location.href=\''+JSONObj.items[i].click_url+'\'">'
										+'<img src="'+JSONObj.items[i].pic_url+'" onclick="javascrtpt:window.location.href=\''+JSONObj.items[i].click_url+'\'" alt="图片链接">'
										+'<div class="adsText di">'
											+'<p class="adsTitle">'+JSONObj.items[i].t_title+'</p>'
											+'<p class="adsSecTitle">'+JSONObj.items[i].title+'</p>'
											+'<p class="price">¥ '+JSONObj.items[i].price_low+'&nbsp&nbsp<span class="oldPrice">¥ '+JSONObj.items[i].price_high+'</span><span class="zhekou">'+JSONObj.items[i].discount+'折</span></p>'
											+'<p class="savePrice">劲省：¥'+JSONObj.items[i].save_price+'<img class="hrefPic" src="images/href.png" onclick="javascrtpt:window.location.href=\''+JSONObj.items[i].click_url+'\'" alt="点击查看"></p>'
										+'</div>'
									+'</div>');
				}
			}
			fontControl();
 		}});
})

function fontControl(){
	var viewWidth = $(window).width();
	var bWidth = viewWidth / 23;
	var sWidth = viewWidth / 30;
	if(viewWidth > 640){
		$('p').css('fontSize','27.0869px');
		$('.titleTxt').css('fontSize','27.0869px');
		$('.oldPrice').css('fontSize','20.7666px');
	}else{
		$('p').css('fontSize',bWidth+'px');
		$('.titleTxt').css('fontSize',bWidth+'px');
		$('.oldPrice').css('fontSize',sWidth+'px');
	}
}


function GetQueryString(name){   //获取浏览器的参数
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}