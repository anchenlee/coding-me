$(document).ready(function() {
	$('img').lazyload();
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
});

function fontControl(){
	var view_width = $(window).width();
	$('img').width(view_width);
	var text_width = view_width / 25;
	if (view_width>640) {
		$('.content_title').css('font-size','31.4px');
	}
	else{
		$('.content_title').css('font-size',text_width+'px');
	}
}

function submit_btn(){
	var code = $('.code').val();//串码
	var item_id = $('.item_id').val();//商品id
	$.ajax({
		url:'../CheckCode',
		data:'code='+code+'&item_id='+item_id,
		type:'POST',
		success:function(ret){
			if(ret == 'true'){
				alert('串码可用');
			}else{
				alert('不是有效的串码!');
			}
		}
	})
}