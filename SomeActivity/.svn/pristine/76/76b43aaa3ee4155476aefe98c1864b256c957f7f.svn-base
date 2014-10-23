var tagid=930;
var pg =1;
var ad_num = 1;

function do_it_over(jsonobj_2){
	for (var i = 1; i <= jsonobj_2.items.length; i++) {
		sell_out = 0;
		$(".youhui_nums_"+i).text(items[i-1]);
		if (items[i-1] == 0) {
			$(".sell_ad_"+i).attr("src","images/ad1_"+i+"_over.jpg");
		};
	};
}

function initialize(jsonobj, isclear){//初始化
	    if(isclear){
	    	$("#content_2_left div").empty();
	    }
	
	    var goods_num = jsonobj.items.length;
	    
	    for (var i = 0; i < goods_num; i=i+2){
	    	 ad_num++;
	    $(".content_2_left").append("<div class='contain2'><div class='add'><a href='' id='left_pic_a_"+ad_num+"'><div class='add_up' id='ad_pic_left_"+ad_num+"'><div class='jifenbao'>送<span class='jifenbao_s' id='left_jifenbao_"+ad_num+"'></span>倍集分宝</div><div class='add_price'>￥:<span class='price' id='left_price_"+ad_num+"'></span></div></div></a><div class='add_down'><a href='' id='left_word_a_"+ad_num+"'><span class='grey' id='left_word_"+ad_num+"'></span></a></div></div><div class='add'><a href='' id='right_pic_a_"+ad_num+"'><div class='add_up' id='ad_pic_right_"+ad_num+"'><div class='jifenbao'>送<span class='jifenbao_s' id='right_jifenbao_"+ad_num+"'></span>倍集分宝</div><div class='add_price'>￥:<span class='price' id='right_price_"+ad_num+"'></span></div></div></a><div class='add_down'><a href='' id='right_word_a_"+ad_num+"'><span class='grey' id='right_word_"+ad_num+"'></span></a></div></div></div>");
	    control_position();//创建新的广告位
	    
	   


	    var multiple_left = jsonobj.items[i].rate;//集分宝倍数
	    var h_price_left = jsonobj.items[i].price_low.toFixed(2);//价格
	    var ad_pic_left = jsonobj.items[i].pic_url;//背景图
	    var http_url_left = jsonobj.items[i].click_url;//超链接地址
	    var ad_text_left = jsonobj.items[i].title;//广告下方文字信息
	    
	    $("#left_jifenbao_"+ad_num).text(multiple_left);
	    $("#ad_pic_left_"+ad_num).css("background-image","url("+ad_pic_left+")");
	    $("#left_price_"+ad_num).text(h_price_left);
	    $("#left_pic_a_"+ad_num).attr("href",http_url_left);
	    $("#left_word_a_"+ad_num).attr("href",http_url_left);
	    $("#left_word_"+ad_num).text(ad_text_left);

	    var multiple_right = jsonobj.items[i+1].rate;//集分宝倍数
	    var h_price_right = jsonobj.items[i+1].price_low.toFixed(2);//价格
	    var ad_pic_right = jsonobj.items[i+1].pic_url;//背景图
	    var http_url_right = jsonobj.items[i+1].click_url;//超链接地址
	    var ad_text_right = jsonobj.items[i+1].title;//广告下方文字信息

	    $("#right_jifenbao_"+ad_num).text(multiple_right);
	    $("#ad_pic_right_"+ad_num).css("background-image","url("+ad_pic_right+")");
	    $("#right_price_"+ad_num).text(h_price_right);
	    $("#right_pic_a_"+ad_num).attr("href",http_url_right);
	    $("#right_word_a_"+ad_num).attr("href",http_url_right);
	    $("#right_word_"+ad_num).text(ad_text_right);

	    };//for循环结束
}

function control_position(){
	var view_width = $(document).width() || $(document.body).width(); 
	var btn_h = view_width/2.1;
	if (btn_h>305) {
		$(".btn1").css("height",305+"px");
		$(".btn2").css("height",305+"px");
		$(".btn2").css("margin-top",-65+"px");
		$(".btn3").css("height",305+"px");
		$(".btn3").css("margin-top",-65+"px");
		$(".btn4").css("height",305+"px");
		$(".btn4").css("margin-top",-65+"px");
	}
	else{
		$(".btn1").css("height",btn_h+"px");
		$(".btn2").css("height",btn_h+"px");
		$(".btn2").css("margin-top",-btn_h/5+"px");
		$(".btn3").css("height",btn_h+"px");
		$(".btn3").css("margin-top",-btn_h/5+"px");
		$(".btn4").css("height",btn_h+"px");
		$(".btn4").css("margin-top",-btn_h/5+"px");
	};

	var blank_50px_h = view_width/30;
	$(".blank_50px").css("height",blank_50px_h+"px");

	var allfont_size = view_width/29;
	if (allfont_size>15.8) {
		$("body").css("font-size",15.8+"px");
	}else{
		$("body").css("font-size",allfont_size+"px");
	};

	var bigwords_size = view_width/18;
	if (bigwords_size>20) {
		$(".big_words").css("font-size",20+"px");
	}else{
		$(".big_words").css("font-size",bigwords_size+"px");
	};

	var down_h = view_width/8.5;
	if (down_h>48) {
		$(".down").css("height",48+"px");
	}else{
		$(".down").css("height",down_h+"px");
	};

	var addup_h = view_width/2.65;
	if (addup_h > 241) {
		$(".add_up").css("height",241+"px");
		$(".contain").css("height",241+50+"px");
	}else{
		$(".add_up").css("height",addup_h+"px");
		$(".contain").css("height",addup_h+50+"px");
	};
	
	var add_price_marginTop = view_width / 3.5; //位置太靠下，增大80； 位置靠上，减小80；   
	if(add_price_marginTop > 200){
		$(".add_price").css("margin-top",200+"px");
	}else{
		$(".add_price").css("margin-top",add_price_marginTop +"px");
	}
	
}

function function_togg(){
	$("#content_1").toggle();
	if ($(".toggg").attr("alt") == 1) {
		$(".toggg").attr("src","images/up.png");
		$(".toggg").attr("alt",0);
	}
	else if ($(".toggg").attr("alt") == 0) {
		$(".toggg").attr("src","images/down.png");
		$(".toggg").attr("alt",1);
		window.scrollTo(0,0);//返回顶部
	};
}

function get_bg(num){
	var info = $(".btn"+num).css("background-image");
	var info_str = info.substring(42,72);
	info_str = "url(."+info_str;
	return info;
}

function click1(){
	if ($(".btn1").attr("alt")==1) {//点击谁谁变白色  之前的白色变有色
		var new_bg;
		new_bg = get_bg(1);
		for (var i = 1; i < 5; i++) {
				if ($(".btn"+i).attr("alt")==0) {
					$(".btn"+i).css("background-image",new_bg);
					$(".btn1").css("background-image","url(./images/whitee_btn.png)");
					$(".btn"+i).attr("alt","1");
					$(".btn"+i).css("color","#fff");
					break;
				};
			}
		$(".btn1").attr("alt","0");
		$(".btn1").css("color","#8F8F8F");
		tagid = 930;
		pg = 1;
		show(tagid, pg);
	};

}

function click2(){
	if ($(".btn2").attr("alt")==1) {
		var new_bg;
		new_bg = get_bg(2);
		for (var i = 1; i < 5; i++) {
				if ($(".btn"+i).attr("alt")==0) {
					$(".btn"+i).css("background-image",new_bg);
					$(".btn2").css("background-image","url(./images/whitee_btn.png)");
					$(".btn"+i).attr("alt","1");
					$(".btn"+i).css("color","#fff");
					break;
				};
			}
		$(".btn2").attr("alt","0");
		$(".btn2").css("color","#8F8F8F");
		tagid = 931;
		pg = 1;
		show(tagid, pg);
	};

}

function click3(){
	if ($(".btn3").attr("alt")==1) {
		var new_bg;
		new_bg = get_bg(3);
		for (var i = 1; i < 5; i++) {
				if ($(".btn"+i).attr("alt")==0) {
					$(".btn"+i).css("background-image",new_bg);
					$(".btn3").css("background-image","url(./images/whitee_btn.png)");
					$(".btn"+i).attr("alt","1");
					$(".btn"+i).css("color","#fff");
					break;
				};
			}
		$(".btn3").attr("alt","0");
		$(".btn3").css("color","#8F8F8F");
		tagid = 932;
		pg = 1;
		show(tagid, pg);
	};
}

function click4(){
	if ($(".btn4").attr("alt")==1) {
		var new_bg;
		new_bg = get_bg(4);
		for (var i = 1; i < 5; i++) {
				if ($(".btn"+i).attr("alt")==0) {
					$(".btn"+i).css("background-image",new_bg);
					$(".btn4").css("background-image","url(./images/whitee_btn.png)");
					$(".btn"+i).attr("alt","1");
					$(".btn"+i).css("color","#fff");
					break;
				};
			}
		$(".btn4").attr("alt","0");
		$(".btn4").css("color","#8F8F8F");
		tagid = 933;
		pg = 1;
		show(tagid, pg);
	};
}