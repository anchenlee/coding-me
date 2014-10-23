$(document).ready(function() {
	$('img').lazyload();
	fontControl();
	$(window).resize(function(){
		fontControl();
	});
});

function fontControl(){
	var view_width = $(window).width();
	var text_width = view_width / 36;
	if (view_width<640) {
		$('.copy').css('font-size',text_width+'px');
	}
}