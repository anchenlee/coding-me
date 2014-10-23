function slide_init(){
         var aPicLi=document.getElementById('pic_list').getElementsByTagName('li');
         var aTextLi=document.getElementById('text_list').getElementsByTagName('li');
         var aIcoLi=document.getElementById('ico_list').getElementsByTagName('li');
         var oIcoUl=document.getElementById('ico_list').getElementsByTagName('ul')[0];
         var oPrev=document.getElementById('btn_prev');
         var oNext=document.getElementById('btn_next');
         var oDiv=document.getElementById('box');
         var i=0;
         var iNowUlLeft=0;
         var iNow=0;
         oPrev.onclick=function(){
             if(iNowUlLeft>0){
               iNowUlLeft=0;
             oUlleft();
         }
             oPrev.className=iNowUlLeft==0?'btn':'btn showBtn';
             oNext.className=iNowUlLeft==(aIcoLi.length-4)?'btn':'btn showBtn';
         };
         oNext.onclick=function(){
             if(iNowUlLeft<aIcoLi.length-4){
             iNowUlLeft=4;
             oIcoUl.style.left=-(aIcoLi[0].offsetWidth+aIcoLi[0].offsetLeft)*iNowUlLeft+'px';
             }
             oPrev.className=iNowUlLeft==0?'btn':'btn showBtn';
             oNext.className=iNowUlLeft==(aIcoLi.length-4)?'btn':'btn showBtn';
         };
         for(i=0;i<aIcoLi.length;i++){
             aIcoLi[i].index=i;
             aIcoLi[i].onmouseover=function(){
                 if(iNow==this.index){
                     return false;
                 }
                 iNow=this.index;
                tab();
             };
         }
         function tab(){
             for(i=0;i<aIcoLi.length;i++){
                 aIcoLi[i].className='';
                 aPicLi[i].style.filter='alpha(opacity:0)';
                 aPicLi[i].style.opacity=0;
                 aPicLi[i].style.zIndex=1;
                 aTextLi[i].getElementsByTagName('h2')[0].className='';
                 miaovStopMove(aPicLi[i]);
             }
             aIcoLi[iNow].className='active';
             aPicLi[iNow].style.zIndex=2;
             miaovStartMove(aPicLi[iNow],{opacity:100},MIAOV_MOVE_TYPE.BUFFER);
             aTextLi[iNow].getElementsByTagName('h2')[0].className='show';
         }
         function oUlleft(){//控制UI的翻页
             oIcoUl.style.left=-(aIcoLi[0].offsetWidth+aIcoLi[0].offsetLeft)*iNowUlLeft+'px';
         }
         function autoplay(){
             iNow++;
             if(iNow>=aIcoLi.length){//轮播到最后一页   循环到开头
                 iNow=0;
             }
             if(iNow<iNowUlLeft){
                 iNowUlLeft=iNow;
             }else if (iNow>=iNowUlLeft+4){
                 iNowUlLeft=4;
             }
             oPrev.className=iNowUlLeft==0?'btn':'btn showBtn';
             oNext.className=iNowUlLeft==(aIcoLi.length-4)?'btn':'btn showBtn';
             oUlleft();
             tab();
         }
         var time=setInterval(autoplay,3000);
         oDiv.onmouseover=function(){
             clearInterval(time);
         };
         oDiv.onmouseout=function(){
             time=setInterval(autoplay,3000);
         };
     }
function css(obj, attr, value){
	if(arguments.length==2)
	{
		if(attr!='opacity')
		{
			return parseInt(obj.currentStyle?obj.currentStyle[attr]:document.defaultView.getComputedStyle(obj, false)[attr]);
		}
		else
		{
			return Math.round(100*parseFloat(obj.currentStyle?obj.currentStyle[attr]:document.defaultView.getComputedStyle(obj, false)[attr]));
		}
	}
	else if(arguments.length==3)
		switch(attr)
		{
			case 'width':
			case 'height':
			case 'paddingLeft':
			case 'paddingTop':
			case 'paddingRight':
			case 'paddingBottom':
				value=Math.max(value,0);
			case 'left':
			case 'top':
			case 'marginLeft':
			case 'marginTop':
			case 'marginRight':
			case 'marginBottom':
				obj.style[attr]=value+'px';
				break;
			case 'opacity':
				obj.style.filter="alpha(opacity:"+value+")";
				obj.style.opacity=value/100;
				break;
			default:
				obj.style[attr]=value;
		}
	return function (attr_in, value_in){css(obj, attr_in, value_in)};
}

var MIAOV_MOVE_TYPE={
	BUFFER: 1,
	FLEX: 2
};

function miaovStopMove(obj)
{
	clearInterval(obj.timer);
}

function miaovStartMove(obj, oTarget, iType, fnCallBack, fnDuring){
	var fnMove=null;
	if(obj.timer){
		clearInterval(obj.timer);
	}
	
	switch(iType){
		case MIAOV_MOVE_TYPE.BUFFER:
			fnMove=miaovDoMoveBuffer;
			break;
		case MIAOV_MOVE_TYPE.FLEX:
			fnMove=miaovDoMoveFlex;
			break;
	}
	
	obj.timer=setInterval(function (){
		fnMove(obj, oTarget, fnCallBack, fnDuring);
	}, 30);
}

function miaovDoMoveBuffer(obj, oTarget, fnCallBack, fnDuring)
{
	var bStop=true;
	var attr='';
	var speed=0;
	var cur=0;
	
	for(attr in oTarget)
	{
		cur=css(obj, attr);
		if(oTarget[attr]!=cur)
		{
			bStop=false;
			
			speed=(oTarget[attr]-cur)/5;
			speed=speed>0?Math.ceil(speed):Math.floor(speed);
			
			css(obj, attr, cur+speed);
		}
	}
	
	if(fnDuring)fnDuring.call(obj);
	
	if(bStop)
	{
		clearInterval(obj.timer);
		obj.timer=null;
		
		if(fnCallBack)fnCallBack.call(obj);
	}
}

function miaovDoMoveFlex(obj, oTarget, fnCallBack, fnDuring)
{
	var bStop=true;
	var attr='';
	var speed=0;
	var cur=0;
	
	for(attr in oTarget)
	{
		if(!obj.oSpeed)obj.oSpeed={};
		if(!obj.oSpeed[attr])obj.oSpeed[attr]=0;
		cur=css(obj, attr);
		if(Math.abs(oTarget[attr]-cur)>=1 || Math.abs(obj.oSpeed[attr])>=1)
		{
			bStop=false;
			
			obj.oSpeed[attr]+=(oTarget[attr]-cur)/5;
			obj.oSpeed[attr]*=0.7;
			
			css(obj, attr, cur+obj.oSpeed[attr]);
		}
	}
	
	if(fnDuring)fnDuring.call(obj);
	
	if(bStop)
	{
		clearInterval(obj.timer);
		obj.timer=null;
		
		if(fnCallBack)fnCallBack.call(obj);
	}
}