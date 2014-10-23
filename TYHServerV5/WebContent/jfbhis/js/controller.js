$(document).ready(function() {
	fontControl();
	judge_level();
	$(window).resize(function(){fontControl();});
	$(function(){
		$("img").lazyload({
			effect:"fadeIn"
		});
	});
});

var color=["#ff85b2","#5Fbdff","#90db3b"];
var data1 = [25,40,35];
var data2 = [10,10,80];

function fontControl(){
//	alert(1);
	var canvas = document.getElementById("circleone");
	var canvasTwo = document.getElementById("circletwo");
	var w = $(window).width() / 24;
	if ( w > 28) {
		$("body").css("font-size",28+"px");
	}
	else{
	$("body").css("font-size",w+"px");
	}
	drawCircle(canvas,data1);
	drawCircle(canvasTwo,data2);
}

function drawCircle(canvas,data){
	var view_w = ($(window).width()) / 5;
	if (view_w > 129) {
		view_w = 129;
	};
	canvas.width = view_w;
	canvas.height = view_w;
    var ctx = canvas.getContext("2d");
    var startPoint = 0;
    var r = (view_w-5) /2 ;
    for (var i = 0; i < data.length; i++) {
    	ctx.fillStyle =  color[i];
    	ctx.beginPath();
    	ctx.moveTo(r,r);
    	ctx.arc(r,r,r,startPoint,startPoint+Math.PI*2*(data[i]/100),false);
    	ctx.fill();
    	startPoint += Math.PI*2*(data[i]/100);
      }
}

var level = 3;

function judge_level(){
	$("#ad2").attr("src","images/2014_"+3+".png");
}