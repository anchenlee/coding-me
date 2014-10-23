var page = 1;
var uid = '';
var dizhi = '';
var year = '';
var month = '';

function getData(uid1){
	uid = uid1;
}

$(document).ready(function() {
	fontcontrol();
    $(window).resize(function(){fontcontrol();});
});
    //$(window).bind('scroll',function(){show();}); 
   /* function show() {
    	var wheight = $(window).height();
		var dheight = $(document).height();
        if(50 + ($(window).scrollTop()) >= (dheight - wheight)){
        	page = page +1;
        	$.ajax({
        	    url: dizhi+'/GetFenghongAjax?uid='+uid+'&page='+page,
        	    type: 'POST',
        	    dataType: 'json',
        	    cache: false,
        	    timeout: 5000,
        	    success: function(json){
        	    	if(json != '' && json.length > 0){     	    		
        	    		for(var i = 0 ; i < json.length ; i ++){
        	    			var bean = (json[i]);
        	    			var checked = bean.state;
        				     var ch = "已核对";
        				     if("4"==(checked)){
        				    	 ch = "失败";
        				     }else if("5"==(checked)){
        				    	 ch = "审核中";
        				     }
        	    			$("#content").append("<div class='records'><img src='"+bean.price+"' class='records_body_1' alt=''><div class='records_body_2'><p>"+bean.name+"</p><p><span class='bold'>送<span class='red'>"+bean.num+"</span>个</span></p></div><div class='records_body_3'>"+ch+"<br/>&nbsp</div><div class='date_left'>订单编号:"+bean.orderid+"</div><div class='date_right'>"+bean.time+"</div></div>");
        	    			fontcontrol();
        	    		}
        	    	}
        	    }
        	});
    	} 
	}*/

function fontcontrol(){
    var view_width = $(window).width();
    var fsize = view_width/30;
    if(fsize>22){
        $('body').css('font-size',22+'px');
        $('.records_body_2').css('top',-22+'px');
    }
    else{
        $('body').css('font-size',fsize+'px');
        $('.records_body_2').css('top',-fsize+'px');
    }

    var tinysize = view_width/50;
    $jifenbao = $('.jifenbao');
    if(fsize>12){
        $jifenbao.css('font-size',12+'px');
    }
    else{
        $jifenbao.css('font-size',tinysize+'px');
    }
}