//导航栏的脚本
var nav_information = [
	                      {'tag_id':0000,'tag_name':'首页'},
	                      {'tag_id':909,'tag_name':'9.9包邮'},
	                      {'tag_id':1011,'tag_name':'女装'},
	                      {'tag_id':1017,'tag_name':'女鞋'},
	                      {'tag_id':1015,'tag_name':'美妆'},
	                      {'tag_id':1013,'tag_name':'男士'}
                      ];
var title_array = {0000:'超级惠官网',909:'9.9包邮<-超级惠',1011:'女装惠<-超级惠',1017:'女鞋惠<-超级惠',1015:'美妆惠<-超级惠',1013:'男士惠<-超级惠'};
var mete_array = {0000:'超级惠官网 超低折扣',909:'9.9包邮频道 超级惠 超低折扣',1011:'女装惠频道 超级惠 超低折扣',1017:'女鞋惠频道 超级惠 超低折扣',1015:'美妆惠频道 超级惠 超低折扣',1013:'男士惠频道 超级惠 超低折扣'};
//title,keywords,description的值
function set_meta(){
	var tag_id = GetQueryString('tag_id');
	if(tag_id == ''){
		$('title').text(''+title_array[0000]+'');
		$('meta').eq(0).attr('content',''+mete_array[0000]+'');
		$('meta').eq(1).attr('content',''+mete_array[0000]+'');
	}else{
		$('title').text(''+title_array[tag_id]+'');
		$('meta').eq(0).attr('content',''+mete_array[tag_id]+'');
		$('meta').eq(1).attr('content',''+mete_array[tag_id]+'');
	}
}

function GetQueryString(name){   //获取浏览器的参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return unescape(r[2]); return '';
}