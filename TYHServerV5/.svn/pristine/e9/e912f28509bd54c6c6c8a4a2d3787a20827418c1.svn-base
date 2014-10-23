$(document).ready(function() {
	$('img').lazyload();
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
});

function fontControl(){
	var view_width = $(window).width();
	var text_width = view_width / 26;
	if (view_width>640) {
		$('body').css('font-size','20.73076923076923px');
	}
	else{
		$('body').css('font-size',text_width+'px');
	}
}