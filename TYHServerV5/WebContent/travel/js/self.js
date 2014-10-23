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
function firstAppend(JSONObj){
    $(".SelfName").html(JSONObj.nickname);
    $(".SaveNumber").html(JSONObj.allsavenum);
    $(".Money").html(JSONObj.fenhongnum);
    $(".Ca1").html(JSONObj.trade_award_num);
    $(".p_08").attr("src",JSONObj.img);
    $(".Ca2").html(JSONObj.fenhong_ratio);
    $(".Ca3").html(JSONObj.send_ratio);
    $(".i_02").attr("src",JSONObj.level);
    $(".i_10").attr("src",JSONObj.level);
}
var numTag=0; 
function jsonAppendou(JSONObj){
    var son = '';
    for (var i = 0; i <JSONObj.mingxi.length; i++) {//循环前4组数据
        if (JSONObj.mingxi[i].odevity=='ji') {   //当前加入行是奇数行
            console.log(JSONObj.mingxi[i].odevity);
            son+='<div class="path">'+
                   '  <img src="images/image10.png" class="part">';//加入奇数行的背景图
            if (JSONObj.mingxi[i].mx.mingxi1.type==1) {//判断要加入第一个节点的类型
                son+='<div class="Box b1 Ab">' +
                     '    <div class=" Box2">' +
                     '        <p class="date d1 ">'+JSONObj.mingxi[i].mx.mingxi1.date+'</p>' +
                     '        <img src="images/Icon12.png" class="Circle i1" />' +
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="i_4 Ab"  />'+               
                     '        <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi1.describe+'</p>' +
                     '    </div>' +
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                   son+='<div class="Box b2 Ab">' +
                        '   <div class=" Box2">' +
                        '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                        '       <img src="images/Icon12.png" class="Circle i2" />' +
                        '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                        '       <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                        '   </div>' +
                        '</div>';
                }
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
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].sign+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].sign_num+'</span>'+  
                         '              </div>'+                                                
                         '              <div class="fenhong">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].fenhong+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].fenhong_num+'</span>'+
                         '              </div>'+ 
                         '              <div class="trade">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].trade+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].trade_num+'</span>'+
                         '              </div>'+
                         '         </div>'+
                         '    </div>'+ 
                         '</div>';
                 }
                if (JSONObj.mingxi[i].mx.mingxi2.type==3) {
                    son+='<div class="Tip T2 Ab">'+
                         '    <div class="Tip2">'+
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="Oct p_13"/>'+
                         '        <div class="Tip_message F5 Ab">'+JSONObj.mingxi[i].mx.mingxi2.tishi+'</div>'+               
                         '     </div>'+ 
                         '</div>';
                }
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==2) {//判断要加入第一个节点的类型
                son+='<div class="Tip T1 Ab">'+
                     '   <div class="Tip2">'+      
                     '     <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="p_13"/>'+           
                     '        <div class="Tip_number Ab">'+          
                     '              <p>'+
                     '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi1.total_num+'</span>'+                  
                     '              </p>'+
                     '        </div>' +
                     '        <div class="Tip_text Ab" >'+                                       
                     '              <div class="sign">'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign+'</span>&nbsp;'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign_num+'</span>'+  
                     '              </div>'+                                                
                     '              <div class="fenhong">'+
                     '                   <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong+'</span>&nbsp;'+
                     '                   <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong_num+'</span>'+
                     '              </div>'+ 
                     '              <div class="trade">'+
                     '                   <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade+'</span>&nbsp;'+
                     '                   <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade_num+'</span>'+
                     '              </div>'+
                     '         </div>'+
                     '    </div>'+ 
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b2 Ab">' +
                         '   <div  class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                }
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==3) {//判断要加入第一个节点的类型
                son+='<div class="Tip T1 Ab">'+
                     '    <div class="Tip2">'+
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="p_13"/>'+
                     '        <div class="Tip_message F5 Ab">'+JSONObj.mingxi[i].mx.mingxi1.tishi+'</div>'+               
                     '     </div>' +
                     '</div>'; 
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b2 Ab">' +
                         '   <div  class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                }
            }
            son+='</div>';
        }
        else if(JSONObj.mingxi[i].odevity=='ou') {//偶数行
            console.log(JSONObj.mingxi[i].odevity);
            son+='<div class="path">'+
                   '  <img src="images/image11.png" class="part">';//加入奇数行的背景图
            if (JSONObj.mingxi[i].mx.mingxi1.type==1) {//判断要加第一个入节点的类型 
                son+='<div class="Box b3 Ab">' +
                     '    <div  class=" Box2">' +
                     '        <p class="date d3 ">'+JSONObj.mingxi[i].mx.mingxi1.date+'</p>' +
                     '        <img src="images/Icon12.png" class="Circle i1" />' +
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="i_6 Ab"  />'+               
                     '        <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi1.describe+'</p>' +
                     '    </div>' +
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                  son+='<div class="Box b4 Ab">' +
                       '  <div  class=" Box2">' +
                       '      <p class="date d4 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                       '      <img src="images/Icon12.png" class="Circle i2" />' +
                       '      <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_7 Ab"  />'+               
                       '      <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                       '  </div>' +
                       '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==2) {
                     son+='<div class="Tip T4 Ab">'+
                         '   <div class="Tip2">'+      
                         '     <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="p_13"/>'+           
                         '        <div class="Tip_number Ab">'+          
                         '              <p>'+
                         '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi2.total_num+'</span>'+                  
                         '              </p>'+
                         '        </div>' +
                         '        <div class="Tip_text Ab" >'+                                       
                         '              <div class="sign">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign_num+'</span>'+  
                         '              </div>'+                                                
                         '              <div class="fenhong">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong_num+'</span>'+
                         '              </div>'+ 
                         '              <div class="trade">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade_num+'</span>'+
                         '              </div>'+
                         '         </div>'+
                         '    </div>'+ 
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==3) {
                    son+='<div class="Tip T4 Ab">'+
                         '    <div class="Tip2">'+
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="Oct p_13"/>'+
                         '        <div class="Tip_message F5 Ab">'+JSONObj.mingxi[i].mx.mingxi2.tishi+'</div>'+               
                         '     </div>'+
                         '</div>';
                }
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==2) {
                son+='<div class="Tip T3 Ab">'+
                     '   <div class="Tip2">'+      
                     '     <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="p_13"/>'+           
                     '        <div class="Tip_number Ab">'+          
                     '              <p>'+
                     '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi1.total_num+'</span>'+                  
                     '              </p>'+
                     '        </div>' +
                     '        <div class="Tip_text Ab" >'+                                       
                     '              <div class="sign">'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign+'</span>&nbsp;'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign_num+'</span>'+  
                     '              </div>'+                                                
                     '              <div class="fenhong">'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong+'</span>&nbsp;'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong_num+'</span>'+
                     '              </div>'+ 
                     '              <div class="trade">'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade+'</span>&nbsp;'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade_num+'</span>'+
                     '              </div>'+
                     '         </div>'+
                     '    </div>'+ 
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b4 Ab">' +
                         '   <div class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                }
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==3) {
                son+='<div class="Tip T3 Ab">'+
                     '    <div class="Tip2">'+
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="p_13"/>'+
                     '        <div class="Tip_message F5 Ab">'+JSONObj.mingxi[i].mx.mingxi1.tishi+'</div>'+               
                     '    </div>'+
                     '</div>'; 
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b4 Ab">' +
                         '    <div class=" Box2">' +
                         '        <p class="date d3 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '        <img src="images/Icon12.png" class="Circle i1" />' +
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_6 Ab"  />'+               
                         '        <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '    </div>' +
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                }
            }
            son+='</div>';
        }
        else{
            son+='';
        }
    }
    if (JSONObj.mingxi.length%2==0) {
        numTag=0;
    }else{numTag=1;}
    $('#Bgd3').append(son);      
}
function jsonAppendji(JSONObj){
    var son = '';
    for (var i = 0; i <JSONObj.mingxi.length; i++) {//循环前4组数据
        if (JSONObj.mingxi[i].odevity=='ou') {   //当前加入行是奇数行
            console.log(JSONObj.mingxi[i].odevity);
            son+='<div class="path">'+
                   '  <img src="images/image10.png" class="part">';//加入奇数行的背景图
            if (JSONObj.mingxi[i].mx.mingxi1.type==1) {//判断要加入第一个节点的类型
                son+='<div class="Box b1 Ab">' +
                     '    <div class=" Box2">' +
                     '        <p class="date d1 ">'+JSONObj.mingxi[i].mx.mingxi1.date+'</p>' +
                     '        <img src="images/Icon12.png" class="Circle i1" />' +
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="i_4 Ab"  />'+               
                     '        <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi1.describe+'</p>' +
                     '    </div>' +
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                   son+='<div class="Box b2 Ab">' +
                        '   <div class=" Box2">' +
                        '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                        '       <img src="images/Icon12.png" class="Circle i2" />' +
                        '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                        '       <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                        '   </div>' +
                        '</div>';
                }
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
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].sign+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].sign_num+'</span>'+  
                         '              </div>'+                                                
                         '              <div class="fenhong">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].fenhong+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].fenhong_num+'</span>'+
                         '              </div>'+ 
                         '              <div class="trade">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].trade+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].trade_num+'</span>'+
                         '              </div>'+
                         '         </div>'+
                         '    </div>'+ 
                         '</div>';
                 }
                if (JSONObj.mingxi[i].mx.mingxi2.type==3) {
                    son+='<div class="Tip T2 Ab">'+
                         '    <div class="Tip2">'+
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="Oct p_13"/>'+
                         '        <div class="Tip_message F5 Ab">'+JSONObj.mingxi[i].mx.mingxi2.tishi+'</div>'+               
                         '     </div>'+ 
                         '</div>';
                }
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==2) {//判断要加入第一个节点的类型
                son+='<div class="Tip T1 Ab">'+
                     '   <div class="Tip2">'+      
                     '     <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="p_13"/>'+           
                     '        <div class="Tip_number Ab">'+          
                     '              <p>'+
                     '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi1.total_num+'</span>'+                  
                     '              </p>'+
                     '        </div>' +
                     '        <div class="Tip_text Ab" >'+                                       
                     '              <div class="sign">'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign+'</span>&nbsp;'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign_num+'</span>'+  
                     '              </div>'+                                                
                     '              <div class="fenhong">'+
                     '                   <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong+'</span>&nbsp;'+
                     '                   <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong_num+'</span>'+
                     '              </div>'+ 
                     '              <div class="trade">'+
                     '                   <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade+'</span>&nbsp;'+
                     '                   <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade_num+'</span>'+
                     '              </div>'+
                     '         </div>'+
                     '    </div>'+ 
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b2 Ab">' +
                         '   <div  class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                }
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==3) {//判断要加入第一个节点的类型
                son+='<div class="Tip T1 Ab">'+
                     '    <div class="Tip2">'+
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="p_13"/>'+
                     '        <div class="Tip_message F5 Ab">'+JSONObj.mingxi[i].mx.mingxi1.tishi+'</div>'+                                   
                     '     </div>' +
                     '</div>'; 
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b2 Ab">' +
                         '   <div  class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                }
            }
            son+='</div>';
        }
        else if(JSONObj.mingxi[i].odevity=='ji') {//偶数行
            console.log(JSONObj.mingxi[i].odevity);
            son+='<div class="path">'+
                   '  <img src="images/image11.png" class="part">';//加入奇数行的背景图
            if (JSONObj.mingxi[i].mx.mingxi1.type==1) {//判断要加第一个入节点的类型 
                son+='<div class="Box b3 Ab">' +
                     '    <div  class=" Box2">' +
                     '        <p class="date d3 ">'+JSONObj.mingxi[i].mx.mingxi1.date+'</p>' +
                     '        <img src="images/Icon12.png" class="Circle i1" />' +
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="i_6 Ab"  />'+               
                     '        <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi1.describe+'</p>' +
                     '    </div>' +
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                  son+='<div class="Box b4 Ab">' +
                       '  <div  class=" Box2">' +
                       '      <p class="date d4 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                       '      <img src="images/Icon12.png" class="Circle i2" />' +
                       '      <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_7 Ab"  />'+               
                       '      <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                       '  </div>' +
                       '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==2) {
                     son+='<div class="Tip T4 Ab">'+
                         '   <div class="Tip2">'+      
                         '     <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="p_13"/>'+           
                         '        <div class="Tip_number Ab">'+          
                         '              <p>'+
                         '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi2.total_num+'</span>'+                  
                         '              </p>'+
                         '        </div>' +
                         '        <div class="Tip_text Ab" >'+                                       
                         '              <div class="sign">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].sign+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].sign_num+'</span>'+  
                         '              </div>'+                                                
                         '              <div class="fenhong">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].fenhong+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].fenhong_num+'</span>'+
                         '              </div>'+ 
                         '              <div class="trade">'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].trade+'</span>&nbsp;'+
                         '                  <span>'+JSONObj.mingxi[i].mx.mingxi2.describe[0].trade_num+'</span>'+
                         '              </div>'+
                         '         </div>'+
                         '    </div>'+ 
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==3) {
                    son+='<div class="Tip T4 Ab">'+
                         '    <div class="Tip2">'+
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="Oct p_13"/>'+
                         '        <div class="Tip_message F5 Ab">'+JSONObj.mingxi[i].mx.mingxi2.tishi+'</div>'+               
                         '     </div>'+
                         '</div>';
                }
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==2) {
                son+='<div class="Tip T3 Ab">'+
                     '   <div class="Tip2">'+      
                     '     <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="p_13"/>'+           
                     '        <div class="Tip_number Ab">'+          
                     '              <p>'+
                     '                 <span class="F6">'+JSONObj.mingxi[i].mx.mingxi1.total_num+'</span>'+                  
                     '              </p>'+
                     '        </div>' +
                     '        <div class="Tip_text Ab" >'+                                       
                     '              <div class="sign">'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign+'</span>&nbsp;'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].sign_num+'</span>'+  
                     '              </div>'+                                                
                     '              <div class="fenhong">'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong+'</span>&nbsp;'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].fenhong_num+'</span>'+
                     '              </div>'+ 
                     '              <div class="trade">'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade+'</span>&nbsp;'+
                     '                  <span>'+JSONObj.mingxi[i].mx.mingxi1.describe[0].trade_num+'</span>'+
                     '              </div>'+
                     '         </div>'+
                     '    </div>'+ 
                     '</div>';
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b4 Ab">' +
                         '   <div class=" Box2">' +
                         '       <p class="date d2 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '       <img src="images/Icon12.png" class="Circle i2" />' +
                         '       <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_5 Ab"  />'+               
                         '       <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '   </div>' +
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                }
            }
            if (JSONObj.mingxi[i].mx.mingxi1.type==3) {
                son+='<div class="Tip T3 Ab">'+
                     '    <div class="Tip2">'+
                     '        <img src=\''+JSONObj.mingxi[i].mx.mingxi1.img+'\' class="Oct p_13"/>'+
                     '        <div class="Tip_message F5 Ab">'+JSONObj.mingxi[i].mx.mingxi1.tishi+'</div>'+               
                     '     </div>'+
                     '</div>'; 
                if (JSONObj.mingxi[i].mx.mingxi2.type==1) {//判断要加入第二个节点的类型
                    son+='<div class="Box b4 Ab">' +
                         '    <div class=" Box2">' +
                         '        <p class="date d3 ">'+JSONObj.mingxi[i].mx.mingxi2.date+'</p>' +
                         '        <img src="images/Icon12.png" class="Circle i1" />' +
                         '        <img src=\''+JSONObj.mingxi[i].mx.mingxi2.img+'\' class="i_6 Ab"  />'+               
                         '        <p class="Msg">'+JSONObj.mingxi[i].mx.mingxi2.describe+'</p>' +
                         '    </div>' +
                         '</div>';
                }
                if (JSONObj.mingxi[i].mx.mingxi2.type==4) {
                    son+='';
                }
            }
            son+='</div>';
        }
        else{
            son+='';
        }
    }
    if (JSONObj.mingxi.length%2==0) {
        numTag=1;
    }else{numTag=0;}
    $('#Bgd3').append(son);      
}
function lessAppend(JSONObj){
	var lson='';
	jsonAppendou(JSONObj);
	if (JSONObj.mingxi.length==0) {		
		lson+='<img src="images/image10.png" class="part">'+
		     '<img src="images/image11.png" class="part">'+
		     '<img src="images/image10.png" class="part">'+
		     '<img src="images/image11.png" class="part">';
	}else if (JSONObj.mingxi.length==1) {
		lson+='<img src="images/image11.png" class="part">'+
		     '<img src="images/image10.png" class="part">'+
		     '<img src="images/image11.png" class="part">';
	}else if (JSONObj.mingxi.length==2) {
		lson+='<img src="images/image10.png" class="part">'+
		     '<img src="images/image11.png" class="part">';
	}else{
		lson+='<img src="images/image11.png" class="part">';
	}
	$('#Bgd3').append(lson); 
}
function init_first(pagNow){
    var uid = GetQueryString('uid');
     $.ajax({
    	url:'../jfbhistest',
        data:'page='+pagNow+'&uid='+uid,
        type:'POST',
        success:function(data){  
        	console.log(data);
             var JSONObj = JSON.parse(data);
             if(pagNow==1){//不刷新时第一次加载数据
                  firstAppend(JSONObj);
                  if (JSONObj.mingxi.length<4) {
                  	   lessAppend(JSONObj)	
                  }else{jsonAppendou(JSONObj);}               
             }else{
                 if (numTag=0) {jsonAppendou(JSONObj);}
                 else{jsonAppendji(JSONObj);}                
              }
        }
     });
  } 
var pagflag=1;
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
               $(this.el).append("<div id='infinite' style='height:60px;line-height:60px;text-align:center;font-size:2.5rem;font-weight:bold'>正在加载请稍后...</div>");            
            }         
            $.bind(myScroller, "infinite-scroll-end", function () {
                $.unbind(myScroller, "infinite-scroll-end");
                self.scrollToBottom();                
                setTimeout(function (){                     
                    $(self.el).find("#infinite").remove();                
                    self.clearInfinite();
                    //刷新请求
                     pagflag++;
                     init_first(pagflag);
                     self.scrollToBottom();
                }, 1000);
            });
        });
        // $("#webslider").css("overflow", "auto");
});
function GetQueryString(name){   //获取浏览器的参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return unescape(r[2]); return '';
}







                            
        
                                
                                   
                                    
                                        
                                            
                                        
                                    
                                    
                                        
                                            
                                            
                                        
                                        
                                            
                                            
                                       
                                        
                                            
                                            
                                        
                                    
                                  
                            
