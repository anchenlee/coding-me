//可以用鼠标拖动屏幕
function loadedPanel(what) {
    //We are going to set the badge as the number of li elements inside the target
    $.ui.updateBadge("#aflink", $("#af").find("li").length);
}
function unloadedPanel(what) {
    console.log("unloaded " + what.id);
}
if (!((window.DocumentTouch && document instanceof DocumentTouch) || 'ontouchstart' in window)) {
    var script = document.createElement("script");
    script.src = "plugins/af.desktopBrowsers.js";
    var tag = $("head").append(script);
}  

function ol(){
    init_first(1);
    init_next();
    var u = navigator.userAgent;//判断安卓苹果
    if (u.indexOf('iPhone') > -1) {//苹果手机
         $('#afui').scroller({useJsScroll:true});
         if (!((window.DocumentTouch && document instanceof DocumentTouch) || 'ontouchstart' in window)){
             var script = document.createElement("script");
             script.src = "js/plugins/af.desktopBrowsers.js";
             var tag = $("head").append(script);
         }
    }else{//其他手机
        $.feat.nativeTouchScroll=true;
        // $('#to_top').show();
    }
}

function init_first(pagNow){
    var uid = GetQueryString('tyh_web_uid');
    $.ajax({
        url:'../tyh3/super_discount?type=json',
        data:'page='+pagNow+'&uid='+uid,
        type:'POST',
        success:function(ret){
            var JSONObj = JSON.parse(ret);
            if(pagNow==1){
                jsonAppend();
            }
            /*图片延迟加载*/
        }
    });
} 


    //上拉下拉刷新 
$.ui.ready(function () {    
    var myScroller;
    //$.feat.nativeTouchScroll=false;
    myScroller = $("#webslider").scroller(); //Fetch the scroller from cache;
    myScroller.addInfinite();
     //myScroller.addPullToRefresh();
        myScroller.enable();
        $.bind(myScroller, "infinite-scroll", function () {            
            var self = this;                
            console.log("infinite triggered");   
            if($(this.el).find('#infinite').length==0)  {
               $(this.el).append("<div id='infinite' style='height:60px;line-height:60px;text-align:center;font-weight:bold'>正在加载请稍后...</div>");            
            }         
            $.bind(myScroller, "infinite-scroll-end", function () {
                $.unbind(myScroller, "infinite-scroll-end");
                self.scrollToBottom();                
                setTimeout(function (){                     
                    $(self.el).find("#infinite").remove();                
                    self.clearInfinite();
                    //刷新请求
                    pagFlag++;
                    init_first(pagFlag);  

                    $('#Bgd3').append(                
                        "<div style='background:'> <div class='path'><img src='images/image11.png' class='part'/><div class='Tip T2 Ab'><div class='Tip2'><img src='images/image13.png' class='p_13'/><div class='Tip_number Ab'><p><span class='F6'>5962</span></p></div><div class='Tip_text Ab'><div class='sign'><span class='wenben'>签到</span>&nbsp;<span class='num'>160</span></div><div class='fenhong'><span class='wenben'>分红</span>&nbsp;<span class='num'>1065</span></div><div class='trade'><span class='wenben'>购物</span>&nbsp;<span class='num'>2056</span></div></div></div></div></div><div class='path'><img src='images/image10.png' class='part'/><div class='Tip T1 Ab'><div class='Tip2'><img src='images/image13.png' class='p_13'/><div class='Tip_number Ab'><p><span class='F6'>5962</span></p></div><div class='Tip_text Ab'><div class='sign'><span class='wenben'>签到</span>&nbsp;<span class='num'>160</span></div><div class='fenhong'><span class='wenben'>分红</span>&nbsp;<span class='num'>1065</span></div><div class='trade'><span class='wenben'>购物</span>&nbsp;<span class='num'>2056</span></div></div></div></div></div>  </div>");        
                    self.scrollToBottom();
                }, 1000);
            });
        });
            // $("#webslider").css("overflow", "auto");
});

function init_next(){
    var uid = GetQueryString('uid');
    $.ajax({
        url:'../tyh3/tomorrow_forecast?type=json',
        data:'&uid='+uid,
        type:'POST',
        success:function(ret){
            if(ret=='not_on_time'){
                $("#titleBg").show();
            }else{
                var JSONObj = JSON.parse(ret);
                jsonAppend();
            }
        }
    });
}

function jsonAppend(){
    //var jsonP = '{"allsavenum":28,"nickname":"平安健康","trade_award_num":258,"fenhongnum":36.12,"img":"http://static.etouch.cn/suishou/ad_img/taobao_icon.png","level":"http:111","fenhong_ratio":"2%","send_ratio":"12%","mingxi":[{"odevity":"ji","mx":{"mingxi1":{"describe":[{"sign":"签到","sign_num":123,"fenhong":"分红","fenhong_num":111,"trade":"购物","trade_num":999}],"id":"6","total_num":1233,"date":"09月","img":"http://www.sogou.com/images/logo/new/sogou.png","type":"2"},"mingxi2":{"date":"09.17","describe":"又升级啦","id":"5","img":"http://www.baidu.com/img/bdlogo.png","type":"1"}}},{"odevity":"ou","mx":{"mingxi1":{"describe":[{"sign":"签到","sign_num":258,"fenhong":"分红","fenhong_num":147,"trade":"购物","trade_num":0}],"id":"4","total_num":405,"date":"09月","img":"http://www.sogou.com/images/logo/new/sogou.png","type":"2"},"mingxi2":{"type":"4"}}},{"odevity":"ji","mx":{"mingxi1":{"describe":[{"sign":"签到","sign_num":258,"fenhong":"分红","fenhong_num":0,"trade":"购物","trade_num":0}],"id":"3","total_num":258,"date":"09月","img":"http://www.sogou.com/images/logo/new/sogou.png","type":"2"},"mingxi2":{"type":"4"}}},{"odevity":"ou","mx":{"mingxi1":{"describe":[{"sign":"签到","sign_num":100,"fenhong":"分红","fenhong_num":0,"trade":"购物","trade_num":0}],"id":"2","total_num":100,"date":"09月","img":"http://www.sogou.com/images/logo/new/sogou.png","type":"2"},"mingxi2":{"type":"4"}}},{"odevity":"ji","mx":{"mingxi1":{"id":"1","tishi":"没有奖励","img":"http://www.sogou.com/images/logo/new/sogou.png","type":"3"}}}]}';
    //var JSONObj = JSON.parse(jsonP);
    var son = '';
    for (var i = 0; i < mingxi.length; i++) {//循环前4组数据
        if (JSONObj.mingxi[i].odevity=='ji') {   //当前加入行是奇数行
            console.log(JSONObj.mingxi[i].odevity);
            son+='<div class="path Typefirst">'+
                   '  <img src="images/image10.png" class="part">';//加入奇数行的背景图
            if (JSONObj.mingxi[i].mx.mingxi1.type==1) {//判断要加入第一个节点的类型
                son+='<div class="Box b1 Ab">' +
                     '    <div id="FisrtDate"class=" Box2">' +
                     '        <p class="date d1 ">'+JSONObj.mingxi[i].mx.mingxi1.date+'</p>' +
                     '        <img src="images/Icon12.png" class="Circle i1" />' +
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="i_4 Ab"  />'+               
                     '        <p class="Msg m1 ">'+JSONObj.mingxi[i].mx.mingxi1.describe+'</p>' +
                     '    </div>' +
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                   son+='<div class="Box b2 Ab">' +
                        '   <div id="FisrtDate"class=" Box2">' +
                        '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                        '       <img src="images/Icon12.png" class="Circle i2" />' +
                        '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                        '       <p class="Msg m2 ">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                        '   </div>' +
                        '</div>';
                };
                if (JSONObj.mingxi[i].mx.mingxi2.type==2) {
                    son+='<div class="Tip T2 Ab">'+
                         '   <div class="Tip2">'+      
                         '     <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="p_13"/>'+           
                         '        <div class="Tip_number Ab">'+          
                         '              <p>'+
                         '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi2.total_num+'</span>'+                  
                         '              </p>'+
                         '        </div>' +
                         '        <div class="Tip_text Ab" >'+                                       
                         '              <div class="sign">'+
                         '                  <span class="wenben" >'+JSONObj.mingxi[i].mx.mingxi2.describe[0].sign+'</span>&nbsp;'+
                         '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi2.describe[0].sign_num+'</span>'+  
                         '              </div>'+                                                
                         '              <div class="fenhong">'+
                         '                  <span class="wenben">'+JSONObj.mingxi[i].mx.mingxi2.describe[0].fenhong+'</span>&nbsp;'+
                         '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi2.describe[0].fenhong_num+'</span>'+
                         '              </div>'+ 
                         '              <div class="trade">'+
                         '                  <span class="wenben">'+JSONObj.mingxi[i].mx.mingxi2.describe[0].trade+'</span>&nbsp;'+
                         '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi2.describe[0].trade_num+'</span>'
                         '              </div>'+
                         '         </div>'+
                         '    </div>'+ 
                         '</div>';
                 };
                if (JSONObj.mingxi[i].mx.mingxi2.type==3) {
                    son+='<div class="Tip T2 Ab">'+
                         '    <div class="Tip2">'+
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="Oct p_13"/>'+
                         '           <div class="Tip_message Ab">'+
                         '              <div class="wenben F5" id="monthNone">'+JSONObj.mingxi[i].mx.mingxi2.tishi+'</div>'+               
                         '           </div>'+
                         '     </div>'+ 
                         '</div>';
                };
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==2) {//判断要加入第一个节点的类型
                son+='<div class="Tip T1 Ab">'+
                     '   <div class="Tip2">'+      
                     '     <img src="images/image14.png" class="p_13"/>'+           
                     '        <div class="Tip_number Ab">'+          
                     '              <p>'+
                     '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi1.total_num+'</span>'+                  
                     '              </p>'+
                     '        </div>' +
                     '        <div class="Tip_text Ab" >'+                                       
                     '              <div class="sign">'+
                     '                  <span class="wenben" >'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign+'</span>&nbsp;'+
                     '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign_num+'</span>'+  
                     '              </div>'+                                                
                     '              <div class="fenhong">'+
                     '                   <span class="wenben">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong+'</span>&nbsp;'+
                     '                   <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong_num+'</span>'+
                     '              </div>'+ 
                     '              <div class="trade">'+
                     '                   <span class="wenben">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade+'</span>&nbsp;'+
                     '                   <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade_num+'</span>'+
                     '              </div>'+
                     '         </div>'+
                     '    </div>'+ 
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b2 Ab">' +
                         '   <div id="FisrtDate"class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg m2 ">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                };
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                };
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==3) {//判断要加入第一个节点的类型
                son+='<div class="Tip T1 Ab">'+
                     '    <div class="Tip2">'+
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="p_13"/>'+
                     '           <div class="Tip_message Ab">'+
                     '              <div class="wenben F5" id="monthNone">'+JSONObj.mingxi[i].mx.mingxi1.tishi+'</div>'+               
                     '           </div>'+
                     '     </div>' +
                     '</div>'; 
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b2 Ab">' +
                         '   <div id="FisrtDate"class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg m2 ">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                };
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                };

            };
            son+='</div>';
        }
        else if(JSONObj.mingxi[i].odevity=='ou') {//偶数行
            console.log(JSONObj.mingxi[i].odevity);
            son+='<div class="path TypeSec">'+
                   '  <img src="images/image11.png" class="part">';//加入奇数行的背景图
            if (JSONObj.mingxi[i].mx.mingxi1.type==1) {//判断要加第一个入节点的类型 
                son+='<div class="Box b3 Ab">' +
                     '    <div id="FisrtDate" class=" Box2">' +
                     '        <p class="date d3 ">'+JSONObj.mingxi[i].mx.mingxi1.date+'</p>' +
                     '        <img src="images/Icon12.png" class="Circle i1" />' +
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="i_6 Ab"  />'+               
                     '        <p class="Msg m3 ">'+JSONObj.mingxi[i].mx.mingxi1.describe+'</p>' +
                     '    </div>' +
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                  son+='<div class="Box b4 Ab">' +
                       '  <div id="FisrtDate"class=" Box2">' +
                       '      <p class="date d4 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                       '      <img src="images/Icon12.png" class="Circle i2" />' +
                       '      <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_7 Ab"  />'+               
                       '      <p class="Msg m4 ">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                       '  </div>' +
                       '</div>';
                };
                if (JSONObj.mingxi[i].mx.mingxi2.type==2) {
                     son+='<div class="Tip T4 Ab">'+
                         '   <div class="Tip2">'+      
                         '     <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="p_13"/>'+           
                         '        <div class="Tip_number Ab">'+          
                         '              <p>'+
                         '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi1.total_num+'</span>'+                  
                         '              </p>'+
                         '        </div>' +
                         '        <div class="Tip_text Ab" >'+                                       
                         '              <div class="sign">'+
                         '                  <span class="wenben" >'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign+'</span>&nbsp;'+
                         '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign_num+'</span>'+  
                         '              </div>'+                                                
                         '              <div class="fenhong">'+
                         '                  <span class="wenben">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong+'</span>&nbsp;'+
                         '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong_num+'</span>'+
                         '              </div>'+ 
                         '              <div class="trade">'+
                         '                  <span class="wenben">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade+'</span>&nbsp;'+
                         '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade_num+'</span>'+
                         '              </div>'+
                         '         </div>'+
                         '    </div>'+ 
                         '</div>';
                };
                if (JSONObj.mingxi[i].mx.mingxi2.type==3) {
                    son+='<div class="Tip T4 Ab">'+
                         '    <div class="Tip2">'+
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="Oct p_13"/>'
                         '           <div class="Tip_message Ab">'+
                         '              <div class="wenben F5" id="monthNone">'+JSONObj.mingxi[i].mx.mingxi2.tishi+'</div>'+               
                         '           </div>'+
                         '     </div>'+
                         '</div>';
                };
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==2) {
                son+='<div class="Tip T3 Ab">'+
                     '   <div class="Tip2">'+      
                     '     <img src="images/image14.png" class="p_13"/>'+           
                     '        <div class="Tip_number Ab">'+          
                     '              <p>'+
                     '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi1.total_num+'</span>'+                  
                     '              </p>'+
                     '        </div>' +
                     '        <div class="Tip_text Ab" >'+                                       
                     '              <div class="sign">'+
                     '                  <span class="wenben" >'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign+'</span>&nbsp;'+
                     '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign_num+'</span>'+  
                     '              </div>'+                                                
                     '              <div class="fenhong">'+
                     '                  <span class="wenben">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong+'</span>&nbsp;'+
                     '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong_num+'</span>'+
                     '              </div>'+ 
                     '              <div class="trade">'+
                     '                  <span class="wenben">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade+'</span>&nbsp;'+
                     '                  <span class="num">'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade_num+'</span>'+
                     '              </div>'+
                     '         </div>'+
                     '    </div>'+ 
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b3 Ab">' +
                         '   <div id="FisrtDate"class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg m2 ">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                };
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                };
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==3) {
                son+='<div class="Tip T4 Ab">'+
                     '    <div class="Tip2">'+
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="Oct p_13"/>'
                     '           <div class="Tip_message Ab">'+
                     '              <div class="wenben F5" id="monthNone">'+JSONObj.mingxi[i].mx.mingxi1.tishi+'</div>'+               
                     '           </div>'+
                     '     </div>'+
                     '</div>'; 
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b3 Ab">' +
                         '    <div id="FisrtDate"class=" Box2">' +
                         '        <p class="date d3 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '        <img src="images/Icon12.png" class="Circle i1" />' +
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_6 Ab"  />'+               
                         '        <p class="Msg m3 ">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '    </div>' +
                         '</div>';
                };
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                };
            }
            son+='</div>';
        };
    };
       $('#Bgd3').append(son);      
};  







                            
        
                                
                                   
                                    
                                        
                                            
                                        
                                    
                                    
                                        
                                            
                                            
                                        
                                        
                                            
                                            
                                       
                                        
                                            
                                            
                                        
                                    
                                  
                            
                        