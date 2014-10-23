$(document).ready(function() {
$('#img_go').on('click',function(){
	location.href = 'index2.html';
})
fontControl();
$(window).resize(function(){fontControl()});
});

function fontControl(){
var view_width = $(window).width();
var fwidth = view_width/22;
if (fwidth > 26) {
	$('#content').css('font-size',26+'px');
	$('#content').css('line-height',30+'px');
}
else{
	$('#content').css('font-size',fwidth+'px');
	fwidth = fwidth * 1.5;
	$('#content').css('line-height',fwidth+'px');
}
}