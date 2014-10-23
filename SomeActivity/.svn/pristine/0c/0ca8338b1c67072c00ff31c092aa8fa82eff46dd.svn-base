$(document).ready(function() {
	$('img').lazyload();
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
	$('.down').bind('click',function(){
		$('.down').hide();
		$('.rule').show();
		$('.up').show();
	});
	$('.up').bind('click',function(){
		$('.down').show();
		$('.rule').hide();
		$('.up').hide();
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