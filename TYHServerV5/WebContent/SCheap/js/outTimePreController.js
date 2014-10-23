$(document).ready(function() {
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
});

function fontControl(){
	var view_width = $(window).width();
	var big_text_width = view_width / 25;
	if (view_width>640) {
		$('.outTimePreContent p').css('font-size','25.6px');
	}
	else{
		$('.outTimePreContent p').css('font-size',big_text_width+'px');
	}
}