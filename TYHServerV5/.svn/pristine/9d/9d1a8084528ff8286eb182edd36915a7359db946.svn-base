var pagFlag =2;
//可以用鼠标拖动屏幕
function loadedPanel(what) {
    //We are going to set the badge as the number of li elements inside the target
    $.ui.updateBadge("#aflink", $("#af").find("li").length);
    }
function unloadedPanel(what) {
    console.log("unloaded " + what.id);
}

var u = navigator.userAgent;
var platform = "android";
if (u.indexOf("iPad") > -1) {
	platform = "ipad";
}else if (u.indexOf("iPhone") > -1) {
	platform = "iphone";
}else if (u.indexOf("SSAndroidHD") > -1) {
	platform = "apad";
}else if (u.indexOf("SuishouAndroid") > -1) {
	platform = "android";
}

if(u.indexOf('iphone') > -1){
	if (!((window.DocumentTouch && document instanceof DocumentTouch) || 'ontouchstart' in window)) {
	    var script = document.createElement("script");
	    script.src = "../appframework/plugins/af.desktopBrowsers.js";
	    var tag = $("head").append(script);
	}
}else{
	$.feat.nativeTouchScroll=false;
}
/*初始化*/
function ol(){
    $(".nav").click(function(){
        $(".select").removeClass("select");
        $(this).addClass("select");
    });
    /*左右转场切换pannel*/
    /*本期*/
    $('#main_list').bind("swipeLeft", function() {
        $.ui.loadContent("#history", false, false, "slide");
    });
    /*历史*/
    $('#his_list').bind("swipeLeft", function() {
        $.ui.loadContent("#next", false, false, "slide");
    });
    $('#his_list').bind("swipeRight", function() {
        $.ui.loadContent("#main", false, true, "slideright");
    });
    /* 下期*/
    $('#next_list').bind("swipeRight", function() {
        $.ui.loadContent("#history", false, false, "slideright");
    });
    /*图片初始化*/
    var img = new Image();
    img.src = 'images/loading.gif';
    init_first(1);/*本期初始化*/
    init_first(2);/*历史商品*/
    init_next();/*下期商品*/

/*添加分享连接*/
    var shareurl = '';
    if((platform == "ipad" || platform == "apad")){
    	shareurl = 'suishouhd://app.youhui.cn/YouHuiSharePageHD?title=%E8%B6%85%E7%BA%A7%E6%83%A0%2C%E6%83%A0%E5%88%B0%E8%AE%A9%E4%BD%A0%E5%81%9C%E4%B8%8D%E4%B8%8B%E6%9D%A5&share_type=activity_type&share_encryption_code=eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6Iui2hee6p%2BaDoCzmg6DliLDorqnkvaDlgZzkuI3kuIvmnaUhIiwiY29udGVudCI6Iui3jOegtOecvOmVnOeahOS8mOaDoOWlvei0p%2Bato%2BS4gOazouazoueahOiireadpe%2B8jOi%2FmOS4jei1tue0p%2BadpeaKon5%2B5q%2BP5ZGo5LiA6Iez5ZGo5LqUMTDngrnlh4bml7blvIDmiqLvvIEiLCJjbGlja3VybCI6Imh0dHA6Ly8xMC4wLjAuMjE6ODA4MC90eWhhcGkvU0NoZWFwL2NvcHlfb2ZfaW5kZXguaHRtbCIsImltZ3VybCI6Imh0dHA6Ly9zdGF0aWMuZXRvdWNoLmNuL3N1aXNob3UvYWRfaW1nLzE2cWx3ZnR3MXE1LmpwZyIsImFjdGl2aXR5X2tleSI6IjZyOW44c3Z6IiwiamlmZW5fbnVtIjoiMCIsImNoYW5uZWwiOiJ3ZWl4aW4scGVuZ3lvdSx0eHdiLHFxa2oscmVucmVuLGR1b2JhbixlbWFpbCxzbSJ9';
//    	shareurl = 'suishouhd://app.youhui.cn/YouHuiSharePageHD?title=%E8%B6%85%E7%BA%A7%E6%83%A0%2C%E6%83%A0%E5%88%B0%E8%AE%A9%E4%BD%A0%E5%81%9C%E4%B8%8D%E4%B8%8B%E6%9D%A5&share_type=activity_type&share_encryption_code=eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6Iui2hee6p%2BaDoCzmg6DliLDorqnkvaDlgZzkuI3kuIvmnaUhIiwiY29udGVudCI6Iui3jOegtOecvOmVnOeahOS8mOaDoOWlvei0p%2Bato%2BS4gOazouazoueahOiireadpe%2B8jOi%2FmOS4jei1tue0p%2BadpeaKon5%2B5q%2BP5ZGo5LiA6Iez5ZGo5LqUMTDngrnlh4bml7blvIDmiqLvvIEiLCJjbGlja3VybCI6Imh0dHA6Ly92Mi5hcGkubmpuZXR0aW5nLmNuL1NDaGVhcC9jb3B5X29mX2luZGV4Lmh0bWwiLCJpbWd1cmwiOiJodHRwOi8vc3RhdGljLmV0b3VjaC5jbi9zdWlzaG91L2FkX2ltZy8yZ2t6MG1rbmNiOC5qcGciLCJhY3Rpdml0eV9rZXkiOiI2cjluOHN2eiIsImppZmVuX251bSI6IjAiLCJjaGFubmVsIjoid2VpeGluLHBlbmd5b3UsdHh3YixxcWtqLHJlbnJlbixkdW9iYW4sZW1haWwsc20ifQ%3D%3D';
    }else{
    	shareurl = 'suishou://app.youhui.cn/YouHuiSharePage?title=%E8%B6%85%E7%BA%A7%E6%83%A0%2C%E6%83%A0%E5%88%B0%E8%AE%A9%E4%BD%A0%E5%81%9C%E4%B8%8D%E4%B8%8B%E6%9D%A5&share_type=activity_type&share_encryption_code=eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6Iui2hee6p%2BaDoCzmg6DliLDorqnkvaDlgZzkuI3kuIvmnaUhIiwiY29udGVudCI6Iui3jOegtOecvOmVnOeahOS8mOaDoOWlvei0p%2Bato%2BS4gOazouazoueahOiireadpe%2B8jOi%2FmOS4jei1tue0p%2BadpeaKon5%2B5q%2BP5ZGo5LiA6Iez5ZGo5LqUMTDngrnlh4bml7blvIDmiqLvvIEiLCJjbGlja3VybCI6Imh0dHA6Ly8xMC4wLjAuMjE6ODA4MC90eWhhcGkvU0NoZWFwL2NvcHlfb2ZfaW5kZXguaHRtbCIsImltZ3VybCI6Imh0dHA6Ly9zdGF0aWMuZXRvdWNoLmNuL3N1aXNob3UvYWRfaW1nLzE2cWx3ZnR3MXE1LmpwZyIsImFjdGl2aXR5X2tleSI6IjZyOW44c3Z6IiwiamlmZW5fbnVtIjoiMCIsImNoYW5uZWwiOiJ3ZWl4aW4scGVuZ3lvdSx0eHdiLHFxa2oscmVucmVuLGR1b2JhbixlbWFpbCxzbSJ9';
//    	shareurl = 'suishou://app.youhui.cn/YouHuiSharePage?title=%E8%B6%85%E7%BA%A7%E6%83%A0%2C%E6%83%A0%E5%88%B0%E8%AE%A9%E4%BD%A0%E5%81%9C%E4%B8%8D%E4%B8%8B%E6%9D%A5&share_type=activity_type&share_encryption_code=eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6Iui2hee6p%2BaDoCzmg6DliLDorqnkvaDlgZzkuI3kuIvmnaUhIiwiY29udGVudCI6Iui3jOegtOecvOmVnOeahOS8mOaDoOWlvei0p%2Bato%2BS4gOazouazoueahOiireadpe%2B8jOi%2FmOS4jei1tue0p%2BadpeaKon5%2B5q%2BP5ZGo5LiA6Iez5ZGo5LqUMTDngrnlh4bml7blvIDmiqLvvIEiLCJjbGlja3VybCI6Imh0dHA6Ly92Mi5hcGkubmpuZXR0aW5nLmNuL1NDaGVhcC9jb3B5X29mX2luZGV4Lmh0bWwiLCJpbWd1cmwiOiJodHRwOi8vc3RhdGljLmV0b3VjaC5jbi9zdWlzaG91L2FkX2ltZy8yZ2t6MG1rbmNiOC5qcGciLCJhY3Rpdml0eV9rZXkiOiI2cjluOHN2eiIsImppZmVuX251bSI6IjAiLCJjaGFubmVsIjoid2VpeGluLHBlbmd5b3UsdHh3YixxcWtqLHJlbnJlbixkdW9iYW4sZW1haWwsc20ifQ%3D%3D';
    }
    $("#weixin").bind('click',function(){
        window.location.href=''+shareurl+'&sns_type=weixin';
    });
    $("#pengyou").bind('click',function(){
        window.location.href=''+shareurl+'&sns_type=pengyou';
    });
    $("#qqzone").bind('click',function(){
        window.location.href=''+shareurl+'&sns_type=qqkj';
    });
    $("#sina").bind('click',function(){
        window.location.href=''+shareurl+'&sns_type=weibo';
    });
    $("#txwb").bind('click',function(){
        window.location.href=''+shareurl+'&sns_type=txwb';
    });
}

/*获取本期和历史数据*/
function init_first(pagNow){
    var uid = GetQueryString('tyh_web_uid');
    $.ajax({
        url:'../tyh3/super_discount?type=json',
        data:'page='+pagNow+'&uid='+uid +"&platform=" + platform,
        type:'POST',
        success:function(ret){
            var JSONObj = JSON.parse(ret);
            if(pagNow==1){
                jsonAppend(JSONObj,$("#main_list"));
            }else{
                $("#his_list").append('<div class="super_date">'+JSONObj.resp.data.super_day[0].title+'</div>');
                jsonAppend(JSONObj,$("#his_list"));
            }
        }
    });
}
    /*af上拉下拉刷新内容*/
    var myScroller;
    $.ui.ready(function () {
        myScroller = $("#history").scroller(); //Fetch the scroller from cache
        //Since this is a App Framework UI scroller, we could also do
        // myScroller=$.ui.scrollingDivs['webslider'];
        myScroller.addInfinite();
        myScroller.enable();
        $.bind(myScroller, "infinite-scroll", function () {
            var self = this;
            if($(this.el).find("#infinite").length==0){/*防止上拉出现多个加载*/
                $(this.el).append("<div id='infinite' style='height:60px;line-height:60px;text-align:center;font-weight:bold'>正在加载请稍后...</div>");
            }
            /*$.bind(myScroller, "infinite-scroll-end", function () {*/
                /*$.unbind(myScroller, "infinite-scroll-end");*/
                self.scrollToBottom();
                setTimeout(function () {
                    $(self.el).find("#infinite").remove();
                    self.clearInfinite();/*清除上拉*/
                    pagFlag++;
                    init_first(pagFlag);
                    self.scrollToBottom();
                }, 1000);
            /*});*/
        });
        /*$("#history").css("overflow", "auto");*//*手机访问可能出现白屏*/
    });
/*获取下期数据*/
function init_next(){
    var uid = GetQueryString('uid');
    $.ajax({
        url:'../tyh3/tomorrow_forecast?type=json',
        data:'&uid='+uid +"&platform=" + platform,
        type:'POST',
        success:function(ret){
            if(ret=='not_on_time'){
                $("#titleBg").show();
            }else{
                var JSONObj = JSON.parse(ret);
                $("#next_list").append('<div class="super_date">'+JSONObj.resp.data.title+'</div>');
                jsonAppendNext(JSONObj,$("#next_list"));
            }
        }
    });
}

function jsonAppend(JSONObj,parentNode){
    var son = '';
    for(var i=0;i<JSONObj.resp.data.super_day[0].items.length;i++){
        son += '<li onclick="script:window.location.href=\''+JSONObj.resp.data.super_day[0].items[i].click_url+'\'" name="'+JSONObj.resp.data.super_day[0].items[i].id+'">' +
            '   <div class="goods_img">' +
            '       <img style="display: block" src="images/loading.gif" />'+
            '		<img class="his_img pa" src="'+JSONObj.resp.data.super_day[0].items[i].img+'" />' ;

        /*判断是否卖光*/
        if(JSONObj.resp.data.super_day[0].items[i].is_sold_out == 1){   //卖光了
            son += '<div class="pa mask"></div>'
                +'<img class="soldup pa" src="images/soldup.png" alt="已售罄"/>';
        }else if(JSONObj.resp.data.super_day[0].items[i].is_sold_out == 2){
            son += '<div class="pa mask"></div>'
                +'<img class="soldup pa" src="images/timeup.png" alt="已过期"/>';
        }
        if(JSONObj.resp.data.super_day[0].items[i].limit_num > 0){
            son+='    <div class="note">' +
                '        <div class="pr hi">' +
                '            <div class="text_mask">' +
                '            </div>' +
                '            <span>限量'+JSONObj.resp.data.super_day[0].items[i].limit_num+'件</span>' +
                '        </div>' +
                '     </div>';
        }
        son+='</div>' +
            '   <div class="goods_info">' +
            '       <div class="detail">'+JSONObj.resp.data.super_day[0].items[i].title.substr(0,27)+'</div>' +
            '<div class="tag">';

        /*判断是否有标签*/
        if(JSONObj.resp.data.super_day[0].items[i].item_tags==""){/*没有标签*/
            son+='';
        }else{
        /*有标签*/
            for(var k=0;k<JSONObj.resp.data.super_day[0].items[i].item_tags.length;k++){
                son+='<span class=\'tag'+JSONObj.resp.data.super_day[0].items[i].item_tags[k].id+'\'>'+JSONObj.resp.data.super_day[0].items[i].item_tags[k].name+'</span>';
            }
        }
        son+='</div>' +
            '   <div>' +
            '       <span class="real_price">￥'+JSONObj.resp.data.super_day[0].items[i].discount_price+'元</span>' +
            '       <del class="inv_price">￥'+JSONObj.resp.data.super_day[0].items[i].original_price+'元</del>' +
            '   </div>' +
            '</div>' +
            '</li>';
    }
    parentNode.append(son);
}

function jsonAppendNext(JSONObj,parentNode){
    var son = '';
    for(var i=0;i<JSONObj.resp.data.items.length;i++){
        son += '<li onclick="javascript:window.location.href=\''+JSONObj.resp.data.items[i].click_url+'\'" name="'+JSONObj.resp.data.items[i].id+'">' +
            '   <div class="goods_img">' +
            '       <img style="display: block" src="images/loading.gif" />'+
            '		<img class="his_img pa" src="'+JSONObj.resp.data.items[i].img+'" />';
            
            if(JSONObj.resp.data.items[i].limit_num > 0){
                son+='    <div class="note">' +
                    '        <div class="pr hi">' +
                    '            <div class="text_mask">' +
                    '            </div>' +
                    '            <span>限量'+JSONObj.resp.data.items[i].limit_num+'件</span>' +
                    '        </div>' +
                    '     </div>';
            }
            
        son += '   </div>' +
            '   <div class="goods_info">' +
            '       <div class="detail">'+JSONObj.resp.data.items[i].title.substr(0,27)+'</div>' +
            '   <div class="tag">';
        /*判断是否有标签*/
        if(JSONObj.resp.data.items[i].item_tags==""){/*没有标签*/
            son+='';
        }else{
            /*有标签*/
            for(var k=0;k<JSONObj.resp.data.items[i].item_tags.length;k++){
                son+='<span class=\'tag'+JSONObj.resp.data.items[i].item_tags[k].id+'\'>'+JSONObj.resp.data.items[i].item_tags[k].name+'</span>';
            }
        }
        son+='  </div>' +
            '   <div>' +
            '       <span class="real_price">￥'+JSONObj.resp.data.items[i].original_price+'元</span>'+
            '       <span class="discount">&nbsp优惠价？</span></div>' +
            '   </div>' +
            '</li>';
    }
    parentNode.append(son);
}

function GetQueryString(name){   //获取浏览器的参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return unescape(r[2]); return '';
}