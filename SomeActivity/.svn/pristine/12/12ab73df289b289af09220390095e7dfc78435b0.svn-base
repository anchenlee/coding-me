$(document).ready(function() {
	$('img').lazyload();
	fontcontrol();
	$(window).resize(function(){fontcontrol();})
	lights();
	btns();
	mask();
});

function lights(){
	$lights = $('.lights');
	if($lights.attr('data-name')==0){
		$lights.attr('src','images/lights1.png');
		$lights.attr('data-name',1);
	}else if($lights.attr('data-name')==1){
		$lights.attr('src','images/lights0.png');
		$lights.attr('data-name',0);
	}
	setTimeout(lights,150);
}

function btns(){
	$('.cj_btn').animate({width:'72.25%'},200);
	$('.cj_btn').animate({width:'71.25%'},200);
	$('.share_btn').animate({width:'51.25%'},200);
	$('.share_btn').animate({width:'50.25%'},200);
	setTimeout(btns,400);
}

function mask(){
	$('.mask').animate({left:'80%'},1000);
	$('.mask').animate({left:'20%'},1000);
	setTimeout(mask,2000);
}

function fontcontrol(){
	var view_width = $(window).width();
	var font_width = view_width / 28;
	var txt_width = view_width /25;
	var txt_height = view_width * 0.655;
	if(view_width > 800){
	 	$('#content3 p').css('font-size','28.57142857142857px');
	 	$('.title').css('font-size','28.57142857142857px');
	 	$('.txtarea').css('font-size','32px');
	 	$('.cj_btn_mask').css('font-size','32px');
	 	$('.txtarea').css('height','280px');
	 }else{
		$('.txtarea').css('font-size',txt_width+'px');
		$('.cj_btn_mask').css('font-size',txt_width+'px');
		$('.txtarea').css('height',txt_height+'px');
	 	$('#content3 p').css('font-size',font_width+'px');
	 	$('.title').css('font-size',font_width+'px');
	 }
}

function cj_btn(opn){//点击抽奖 三种情况
	$('#hhtml').css('overflow','hidden');
	switch(opn){
		case 1: //活动规则
			$('.txtarea').html('1.用户使用账户内9个集分宝即可参与抽奖，成功抽取到麦当劳现金券的用户将消耗9个集分宝；未抽取到的用户，该账户内的集分宝则不会被扣除\r2.每个独立用户默认只可参与一次9个集分宝抽麦当劳现金券活动\r3.每日分享活动一次即可额外获一次抽奖机会，每个【独立用户】最多可参加5次抽奖（含默认的第一次抽奖）\r4.活动期间如发现有用户违规操作，随手优惠有权对该账号进行冻结处理\r【独立用户】是指每个手机终端，每个手机号，每个淘宝ID，每个支付宝账号一一对应');
		break;
		case 2://麦当劳电子券使用规则
			$('.txtarea').html('1.成功抽取后，可获取该电子现金券的二维码或串码至麦当劳兑换价值10元的麦当劳产品，请于点餐时提供二维码或串码\r2.该电子现金券只适用于中国大陆地区的麦当劳餐厅，且不适用于甜品站及麦乐送送餐服务；每张电子现金券（二维码/串码）仅能使用1次，不找零，不退换，不能兑换现金，消费时抵扣的金额不开具发票\r3.本活动提供的麦当劳电子现金券有效期至2017年5月31日，需在有效期内使用，过期作废');
		break;
		case 3://如何抽取
			$('.txtarea').html('1.用户登陆“随手优惠”\r2.账户累计达到9个以上集分宝即可参与抽奖\r3.成功抽取到现金券的用户（消耗9个集分宝），获取相应现金券二维码及串码；未抽取到的用户，账户内的集分宝不会扣除\r4.每日分享好友，可多获1次抽奖机会，每个ID最多可参加5次抽奖');
		break;
		case 4://如何使用
			$('.txtarea').html('消费时打开“随手优惠”→品牌惠→麦当劳→打开该优惠券，出示其二维码/串码'); 
		break;
	}
	$('.tanchuang').css('display','block');
}

function closeBtn(){//关闭弹出框
	$('#hhtml').css('overflow','auto');
	$('.tanchuang').css('display','none');
}

function choujiangBack(){
	history.go(-1);
}