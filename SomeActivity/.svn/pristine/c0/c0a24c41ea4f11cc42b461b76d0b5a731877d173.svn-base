$(document).ready(function() {
	var egg = document.getElementById('egg');
	var flower = document.getElementById('flower');
	var submitt = document.getElementById('submitt');
	egg.addEventListener("click",throw_egg,false);
	flower.addEventListener("click",throw_flower,false);
	submitt.addEventListener("click",submitt_function,false);
});

function submitt_function(){
	var uid = GetQueryString('tyh_web_uid');
	var taobao_nick = GetQueryString('tyh_web_taobaonick');
	var pingjia = $('.mesg_board').val();
	var interest = $('.thought').attr('data-name');
	$.ajax({
		url:'../freeactivity',
		data:'uid='+uid+'&taobao_nick='+taobao_nick+'&activity_id=001&pingjia='+pingjia+'&interest='+interest,
		type:'POST',
		success:function(ret){
			$('#mask').show().next().show();
		}
	});
}

function closee(){ //关闭mask
	$('#mask').hide().next().hide();
}

function GetQueryString(name){   //获取浏览器的参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

function throw_egg(){
	if($(this).attr('name') == 0){
		$('.throw_egg').show();
		$(this).addClass('thought');
		$(this).attr('name',1);
		$('.flower').attr('name',1);
		setInterval(function(){
			$('.throw_egg').hide();
		},2000);
	}else if($(this).attr('name') == 1){}
}

function throw_flower(){  //上花
	if($(this).attr('name') == 0){
		$('.throw_flower').show();
		$(this).addClass('thought');
		$(this).attr('name',1);
		$('.egg').attr('name',1);
		setInterval(function(){
			$('.throw_flower').hide();
		},2000);
	}else if($(this).attr('name') == 1){}
}

function jump(){    // 看详情
	window.location.href= 'http://b17.cn/XwmrCL';
}