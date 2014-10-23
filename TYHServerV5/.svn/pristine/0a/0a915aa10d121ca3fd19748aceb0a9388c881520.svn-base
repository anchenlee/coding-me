$(document).ready(function() {
	$.ajax({
		url:'../tyh3/tomorrow_forecast?type=json',
		type:'POST',
		success:function(ret){
			console.info(ret);
			var JSONObj = eval("("+ret+")");
			var son = '';
				for(var j=0;j<JSONObj.resp.data.items.length;j++){
					//判断是否包邮
					if(JSONObj.resp.data.items[j].is_baoyou == 1){   //包邮
						son += '<div class="ad">'
									+'<div class="adsTop pr"><img class="width100" src="'+JSONObj.resp.data.items[j].img+'" alt="" /><img class="postage pa" src="images/postage.png" alt="" /><p class="adInfo cb"><span class="sKill">秒杀</span>'+JSONObj.resp.data.items[j].title+'</p></div>'
									+'<div class="adsBottom">'
										+'<p class="cr"><span class="price_now">￥'+JSONObj.resp.data.items[j].discount_price+'元</span><span class="useness cg price_before">￥'+JSONObj.resp.data.items[j].original_price+'元</span><span class="discount fr">'+JSONObj.resp.data.items[j].discount+'折</span></p>'
										+'<p class=""><span class="saveMoney cb">劲省￥'+JSONObj.resp.data.items[j].save_money+'元</span><span class="limitNum fr">限量'+JSONObj.resp.data.items[j].limit_num+'件</span></p>'
									+'</div>'
								+'</div>';
					}else{   //不包邮
						son += '<div class="ad">'
							+'<div class="adsTop pr"><img class="width100" src="'+JSONObj.resp.data.items[j].img+'" alt="" /><p class="adInfo cb"><span class="sKill">秒杀</span>'+JSONObj.resp.data.items[j].title+'</p></div>'
							+'<div class="adsBottom">'
								+'<p class="cr"><span class="price_now">￥'+JSONObj.resp.data.items[j].discount_price+'元</span><span class="useness cg price_before">￥'+JSONObj.resp.data.items[j].original_price+'元</span><span class="discount fr">'+JSONObj.resp.data.items[j].discount+'折</span></p>'
								+'<p class=""><span class="saveMoney cb">劲省￥'+JSONObj.resp.data.items[j].save_money+'元</span><span class="limitNum fr">限量'+JSONObj.resp.data.items[j].limit_num+'件</span></p>'
							+'</div>'
						+'</div>';
					}
				}
				$('body').append('<div class="content"><span class="pr db"><img class="scTitle2 width100 db" src="images/scTitle2.png" alt="超级惠/进行式" /><span class="pa dateText cw" style="left:24%;">明日预告</span><span class="pa dateText2 cr">即将开始</span></span><div class="ads">'+son+'</div></div>');
				$('img').lazyload();
				fontControl();
				son = '';
		}
	});
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
});

function fontControl(){
	var view_width = $(window).width();
	var big_text_width = view_width / 25;
	var middle_text_width = view_width / 31;
	var tiny_text_width = view_width / 37;
	if (view_width>640) {
		$('#contentPre').css('font-size','22.06896551724138px');
		$('.dateText').css('font-size','25.6px');
		$('.dateText2').css('font-size','22.06896551724138px');
		$('.ads').css('font-size','22.06896551724138px');
		$('.price_before').css('font-size','17.297297297297297px');
	}
	else{
		$('#contentPre').css('font-size',middle_text_width+'px');
		$('.dateText2').css('font-size',middle_text_width+'px');
		$('.dateText').css('font-size',big_text_width+'px');
		$('.ads').css('font-size',middle_text_width+'px');
		$('.price_before').css('font-size',tiny_text_width+'px');
	}
}