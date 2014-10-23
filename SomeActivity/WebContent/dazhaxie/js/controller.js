/**
 * Created by lianchen on 14-9-23.
 */
//可以用鼠标拖动屏幕
function loadedPanel(what) {
    //We are going to set the badge as the number of li elements inside the target
    $.ui.updateBadge("#aflink", $("#af").find("li").length);
}
function unloadedPanel(what) {
}
var u = navigator.userAgent;
if(u.indexOf('iphone') > -1){
    if (!((window.DocumentTouch && document instanceof DocumentTouch) || 'ontouchstart' in window)) {
        var script = document.createElement("script");
        script.src = "../appframework/plugins/af.desktopBrowsers.js";
        var tag = $("head").append(script);
    }
}else{
    $.feat.nativeTouchScroll=false;
}
/*初始化留言板*/
$(function(){
	document.getElementById('if').contentWindow.document.write('<head><style>' +
        'html{ overflow-x:hidden} ' +
        'div{' +
        '   font-family: "microsoft yahei";' +
        '   padding: .2rem .5rem;' +
        '   font-size: .6rem;' +
        '   line-height: .8rem;' +
        '   margin-top: .2rem;' +
        '   background: #fffec3;' +
        '   border-radius: .5rem;' +
        '   border: 1px solid #662819;' +
        '}' +
        'span{' +
        '   color: #00AFEE;' +
        '}' +
        '</style></head>');
    document.getElementById('if').contentWindow.document.write('<body>');
    var act_name = '聊天框';
    var act_topic = '聊天话题';
    var start_time = '2014-09-17';
    var end_time = '2014-09-20';
    $.ajax({
        url:'../talk_init_servlet',
        type:'POST',
        data:'act_name='+act_name+'&act_topic='+act_topic+'&start_time='+start_time+'&end_time='+end_time,
        success:function(ret){
            var JSONObj = JSON.parse(ret);
            var son = '';
            for(var i=0;i<JSONObj.resp.data.categorys.length;i++){
                son+='<div name=\''+JSONObj.resp.data.categorys[i].generateTime+'\'><span>'+JSONObj.resp.data.categorys[i].nick+':</span> '+JSONObj.resp.data.categorys[i].msg+'</div>'
            }
            if_append(son);
        }
    });
    shit();
    document.getElementById('if').contentWindow.document.write('</body>');
    document.getElementById('if').contentWindow.document.write('</html>');
});

/*定时获取添加留言内容*/
function shit(){
    setTimeout(function(){
        var len = document.getElementById('if').contentWindow.document.getElementsByTagName('div').length;
        var times = document.getElementById('if').contentWindow.document.getElementsByTagName('div')[0].getAttribute("name");
        $.ajax({//定时获取用户数据
            url:'../get_message_servlet',
            type:'POST',
            data:'last_time='+times,
            success:function(ret){
                var JSONObj = JSON.parse(ret);
                var son = '';
                if(JSONObj.resp.head.status == 100){
                    for(var i=0;i<JSONObj.resp.data.categorys.length;i++){
                    	console.log(JSONObj.resp.data.categorys.length);
                        set_append(JSONObj.resp.data.categorys[i].generateTime,JSONObj.resp.data.categorys[i].nick,JSONObj.resp.data.categorys[i].msg);
                    }
                }else{
                    son+='';
                }
            }
        });
        shit();
    },3158);
}

/*初始化添加留言内容*/
function if_append(mesg){
	document.getElementById('if').contentWindow.document.write(mesg);
}

/*定时添加留言内容*/
function set_append(t,n,m){
	var newNode = document.createElement("div");//创建div
	newNode.setAttribute('name',t);
	var newSpan = document.createElement("span");//设置nickname
	newSpan.innerHTML = ''+n+':';
	var newA = document.createElement("a");
	newA.innerHTML = ''+m+'';
	newNode.appendChild(newSpan);
	newNode.appendChild(newA);
	var f_body = document.getElementById('if').contentWindow.document.getElementsByTagName('body')[0];
	var first_node = document.getElementById('if').contentWindow.document.getElementsByTagName('div')[0];
	f_body.insertBefore(newNode,first_node);
}

/*用户提交留言内容*/
var flag =0;
function submit(){
    var xx = 2555;
    var mesg = document.getElementById('message').value;
    if(mesg == ''){
        alert('不能为空');
    }else{
        var uid = GetQueryString('tyh_web_uid');//用户ID
        var nick = GetQueryString('tyh_web_taobaonick');//用户昵称
        if(flag ==0){
            flag =1;
            $.ajax({
                url:'../send_message_servlet',
                type:'POST',
                data:'uid='+uid+'&message='+mesg+'&u_nick='+nick+'&platform='+xx+'&app_version='+xx+'&aid='+xx,
                success:function(ret){
                    var JSONObj = eval('('+ret+')');
                    if(JSONObj.resp.head.status == 100){
                        flag=0;
                        document.getElementById('message').value='';
                    }else if(JSONObj.resp.head.status == 101){
                    }
                }
            })
        }
    }
}

function GetQueryString(name){   //获取浏览器的参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
    }

/*5号奖项弹出框设置*/
function selShow(obj){
    var id = $(obj).attr("id");
    console.log(id);
    $('#'+id+'').css({"opacity":1});
    $('#'+id+'').addClass("selected");
    submitAnswer(5); //提交多选
}

function choice_7(obj){
    var info = $(obj).attr('name');
    submitAnswer(7,info);
   /* popTips();*/
}

/*点击手柄开始抽奖*/
$("#touch").bind("click",function(){
    $("#sb_left").hide();
    $("#touch").hide();
    $("#arrow").hide();
    $("#sb_right").show();
    var re = Math.ceil(Math.random()*12+12);
    ani(re);
});
var T1 = 200;
var T2 = 100;
var T3 = 300;
/*获取数据前滚动*/
function ani(re){
    var ba = -0.04;
    var Y1 = toPercent(ba*4);
    var Y2 = toPercent(ba*(re-4));
    var Y3 = toPercent(ba*re);
    var t1 = T1*4;
    var t2 = T2*(re-8);
    var t3 = T3*4;
    $("#animate_ul").css3Animate({
        y: Y1,
        time: t1,
        success: function () {
            $("#animate_ul").css3Animate({
                y: Y2,
                time: t2,
                success: function () {
                    $("#animate_ul").css3Animate({
                        y: Y3,
                        time: t3,
                        callback: function (){
                            var me = re -12;
                            var mesg = msgTrans(me);
                            //生成弹窗html
                            append_popup(mesg);
                            $("#btn").click(function(){
                                submitAnswer(me);  //提交结果
                            });
                        }
                    });
                }
            });
        }
    });
}

function append_popup(mesg){  //生成弹窗
    $('#afui').append('<div id="own_popup" style="position: absolute;top: 10%;width: 90%;left: 5%;z-index:99999999">'+mesg+'</div>');
    $('.afui_panel_mask').show();
}

/*提交中奖内容*/
function submitAnswer(x,info){
	var uid = GetQueryString('tyh_web_uid');//用户ID
    var type = parseInt(translate(x));
    var ans = '';
    switch (type){
        case 0: //输入 提交
            ans= document.getElementById("tttt").value;
            break;
        case 1:  //分享
            ans='wxshare.html';
            break;
        case 3:  //选择
            ans = $(".selected").attr("name");
            break;
        case 4:
            ans = info;
            break;
        default:
            break;
    }
    $.ajax({
        url:'../save_user_win?uid='+uid+'&win_type='+type+'&answer='+ans,
        type:'POST',
        success:function(ret){
            $('#own_popup').remove();//关闭弹窗
            $('.afui_panel_mask').hide();
            $(document.body).popup({
                id:"myPopup",
                title:"随手提示:",
                message:"<img src='../dazhaxie/images/success_03.png'> ",
                cancelOnly:true
            });
            setTimeout(function(){
                $("#myPopup").trigger("close");
            },2000);
        }
    });
}
/*中奖结果类型   分享--- 非分享*/
function translate(key){
    var dict = {'1':"1",'2':"1",'3':"0",'4':"0",'5':"3",'6':"0",'7':"4",'8':"0",'9':"0",'10':"0",'11':"0",'12':"4"};//9:1
    return dict[key];
}

/*12个奖项的弹出框*/
function msgTrans(num){
    var msg = [];
    msg[1]='<div class="pr wi hi" id="msg1"><img class="wi block pr" src="../dazhaxie/images/pop1_03.png">' +
        '<div class="pengyou pa on " onclick="friend_share(1)" ></div> ' +
        '<div class="weixin pa on " onclick="weixin_share(1)" ></div>' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[2]='<div class="pr wi hi" id="msg2"><img class="wi block pr" src="../dazhaxie/images/pop2_03.png">' +
        '<div class="weibo pa " onclick="weibo_share(2)" ></div>' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[3]='<div class="pr wi hi" id="msg3"><img class="wi block pr" src="../dazhaxie/images/pop3_03.png">' +
        '<input id="tttt" class="pa on born" type="text">' +
        '<input id="btn" class="pa on born" type="button">' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[4]='<div class="pr wi hi" id="msg4"><img class="wi block pr" src="../dazhaxie/images/pop4_03.png">' +
        '<input id="tttt" class="pa on born" type="text">' +
        '<input id="btn" class="pa on born" type="button">' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[5]='<div class="pr wi hi" id="msg5">' +
        '   <img class="wi block pr" src="../dazhaxie/images/pop5_03.png" />' +
        '<img id="c1" class="block pa on check"  src="../dazhaxie/images/select_03.png" name="1" onclick="selShow(this)"/>' +
        '<img id="c2" class="block pa on check"  src="../dazhaxie/images/select_03.png" name="2" onclick="selShow(this)"/>' +
        '<img id="c3" class="block pa on check"  src="../dazhaxie/images/select_03.png" name="3" onclick="selShow(this)"/>' +
        '<img id="c4" class="block pa on check"  src="../dazhaxie/images/select_03.png" name="4" onclick="selShow(this)"/>' +
        '<img id="c5" class="block pa on check"  src="../dazhaxie/images/select_03.png" name="5" onclick="selShow(this)"/>' +
        '<img id="c6" class="block pa on check"  src="../dazhaxie/images/select_03.png" name="6" onclick="selShow(this)"/>' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[6]='<div class="pr wi hi" id="msg6"><img class="wi block pr" src="../dazhaxie/images/pop6_03.png" />' +
        '<input id="tttt" maxlength="3" class="pa on born" type="text" />' +
        '<input id="btn" class="pa on born" type="button" />' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[7]='<div class="pr wi hi" id="msg7"><img class="wi block pr" src="../dazhaxie/images/pop7_03.png" />' +
        '<div onclick="choice_7(this)" name="等于" class="right pa on born"></div> ' +
        '<div onclick="choice_7(this)" name="不等于" class="wrong pa on born"></div> ' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[8]='<div class="pr wi hi" id="msg8"><img class="wi block pr" src="../dazhaxie/images/pop8_03.png" />' +
        '<input id="tttt" class="pa on born" type="text" />' +
        '<input id="btn" class="pa on born" type="button" />' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    /*msg[9]='<div class="pr wi hi" id="msg9"><img class="wi block pr" src="../dazhaxie/images/pop9_03.png" />' +
        '<div class="weixin pa on "></div> ' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';*/
    msg[9]='<div class="pr wi hi" id="msg10"><img class="wi block pr on" src="../dazhaxie/images/pop10_03.png" />' +
	    '<input id="tttt" class="pa wi hi on born" type="text" />' +
	    '<input id="btn" class="pa wi hi on born" type="button" />' +
	    '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
	    '</div>';
    msg[10]='<div class="pr wi hi" id="msg10"><img class="wi block pr on" src="../dazhaxie/images/pop10_03.png" />' +
        '<input id="tttt" class="pa wi hi on born" type="text" />' +
        '<input id="btn" class="pa wi hi on born" type="button" />' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[11]='<div class="pr wi hi" id="msg11"><img class="wi block pr" src="../dazhaxie/images/pop11_03.png" />' +
        '<input id="tttt" class="pa on born" type="text" />' +
        '<input id="btn" class="pa on born" type="button" />' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    msg[12]='<div class="pr wi hi" id="msg12"><img class="wi block pr" src="../dazhaxie/images/pop12_03.png" />' +
        '<div onclick="choice_7(this)" name="萌萌搭" class="mmd pa on" ></div> ' +
        '<div onclick="choice_7(this)" name="抢到报" class="qdb pa on" ></div> ' +
        '<div onclick="choice_7(this)" name="好嫌弃" class="hxq pa on" ></div> ' +
        '<div onclick="choice_7(this)" name="这什么" class="zsm pa on" ></div> ' +
        '<div id="refresh" class="pa" onclick="window.location.reload()"></div> ' +
        '</div>';
    return msg[num];
}
/*转换百分数*/
function toPercent(num){
    num = Number(num)*100+'%';
    return num;
}

function weixin_jump(){
	var share_str = '{"sns_type":"weixin","request_code":"","share_type":"activity_type","click_url":"http://d.b17.cn/sactivity/dazhaxie/wxshare.html","img_url":"http://static.etouch.cn/suishou/ad_img/2yc6oc2k125.jpg","activity_key":"0tnie1cj","content":"0元抽第二季，国庆大砸谢，千元大奖等你拿！","title":"0元抽第二季","item_id":"","activity_key":"0tnie1cj","channel":"own","jifenbao_num":"0","sst":""}';
    local_info.share(share_str);
}

function weixin_share(num){
    var share_str = '{"sns_type":"weixin","request_code":"","share_type":"activity_type","click_url":"http://d.b17.cn/sactivity/dazhaxie/wxshare.html","img_url":"http://static.etouch.cn/suishou/ad_img/2yc6oc2k125.jpg","activity_key":"0tnie1cj","content":"0元抽第二季，国庆大砸谢，千元大奖等你拿！","title":"0元抽第二季","item_id":"","activity_key":"0tnie1cj","channel":"own","jifenbao_num":"0","sst":""}';
    local_info.share(share_str);
    submitAnswer(num);
}
function friend_share(num){
    var share_str = '{"sns_type":"pengyou","request_code":"","share_type":"activity_type","click_url":"http://d.b17.cn/sactivity/dazhaxie/wxshare.html","img_url":"http://static.etouch.cn/suishou/ad_img/2yc6oc2k125.jpg","activity_key":"0tnie1cj","content":"0元抽第二季，国庆大砸谢，千元大奖等你拿！","title":"0元抽第二季","item_id":"","activity_key":"0tnie1cj","channel":"own","jifenbao_num":"0","sst":""}';
    local_info.share(share_str);
    submitAnswer(num);
}
function weibo_share(num){
    var share_str = '{"sns_type":"weibo","request_code":"","share_type":"activity_type","click_url":"http://d.b17.cn/sactivity/dazhaxie/wxshare.html","img_url":"http://static.etouch.cn/suishou/ad_img/2yc6oc2k125.jpg","activity_key":"0tnie1cj","content":"0元抽第二季，国庆大砸谢，千元大奖等你拿！","title":"0元抽第二季","item_id":"","activity_key":"0tnie1cj","channel":"own","jifenbao_num":"0","sst":""}';
    local_info.share(share_str);
    submitAnswer(num);
}