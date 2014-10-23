$(document).ready(function() {
	$.ajax({
		url:'../super/superdiscount?method=getInfo',
		type:'POST',
		success:function(ret){
			console.info(ret);
			var JSONObj = eval('('+ret+')');
			var son = '';
			for(var i=0;i<JSONObj.superdays.length;i++){
				for(var j=0;j<JSONObj.superdays[i].items.length;j++){
					son += '<div class="ad">'
						+'<div class="pr adsTop"><img src="'+JSONObj.superdays[i].items[j].img+'" alt=""><p class="adInfo">'+JSONObj.superdays[i].items[j].title+'</p></div>'
						+'<div class="adsBottom">'
							+'<p class=""><span class="price_now">现价：'+JSONObj.superdays[i].items[j].price_low+'</span><span class="discount">折扣：</span></p>'
							+'<p class=""><span class="price_before">原价：'+JSONObj.superdays[i].items[j].price_before+'</span><span class="limitNum">数量：'+JSONObj.superdays[i].items[j].num+'</span></p>'
						+'</div>'
					+'</div>';
				}
				$('#content').append('<div class="ads"><p>'+JSONObj.superdays[i].date+'</p>'+son+'</div>');
				$('img').lazyload();
				son = '';
			}
		}
	})
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
});

function fontControl(){
	var view_width = $(window).width();
	var text_width = view_width / 25;
	if (view_width>640) {
		$('.content_title').css('font-size','31.4px');
	}
	else{
		$('.content_title').css('font-size',text_width+'px');
	}
}