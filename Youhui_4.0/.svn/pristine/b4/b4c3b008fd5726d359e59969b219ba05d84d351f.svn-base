var father = $("#start");
var faterId ;
var wholeJson = '{"height":"1","item_id":"","width":"1",  "bili": "100","pic": "","type": "whole","url": "","is_levels": 0}';
var tempJson = '{"bili":"50","item_id":"", "pic": "","type": "whole","url": "","is_levels": 0,"action_type":"tagStyleItem"}';
var tempJsonArray = '[{"bili":"50","item_id":"", "pic": "","type": "whole","url": "","is_levels": 0},{"bili":"50","item_id":"", "pic": "","type": "whole","url": "","is_levels": 0}]';
var tudong = '';
var quatudong = '';
var quanjuHigh='10';

$(document).ready(function() {
});


//调整整体长宽比例
function tiaozhengWhole(width,heigh){
	//alert(width+" hghghghg");
	//wholeJson =  JSON.parse(wholeJson);
	wholeJson['height'] = heigh;
	wholeJson['width'] = width;
}

//切割后的比例修改
function createChildrenJson(type,bili1,bili2){
	if(bili1> 100){
		bili1 = 50;
	}else{
		bili2 = 100 - bili1;
	}
	if(bili2 > 100 || bili2 < 0){
		bili2 = 50;
	}
	var jsonArray = JSON.parse(tempJsonArray);
	var one = jsonArray[0];
	one['bili'] = bili1;
	one['type'] = type;
	var another = jsonArray[1];
	another['bili'] = bili2;
	another['type'] = type;
	var halfJsa = JSON.parse(tempJsonArray);
	halfJsa[0] = one;
	halfJsa[1] = another;
	return halfJsa;
}

//切割后添加商品
function createChildrenItemJson(jso,item_id,url,pic,type,bili){
	var json = '{}';
	json = JSON.parse(jso);
	json['item_id'] = item_id;
	json['pic'] = pic;
	json['url'] = url;
	json['is_levels']='1';
	json['type']=type;
	json['bili']=bili;
	return json;
}

function getBianType(type){
	var bianType = type;
	if(bianType.substring(type.length-4,type.length) == 'Left' || bianType.substring(type.length-4,type.length) == 'Down'){
		bianType = type.substring(0,type.length-4);
	}else if(bianType.substring(type.length-2,type.length) == 'Up'){
		bianType = type.substring(0,type.length-2);
	}else if(bianType.substring(type.length-5,type.length) == 'Right'){
		bianType = type.substring(0,type.length-5);
	}
	return bianType;
}

function addItemJson(type,item_id,url,pic){	
	var el=document.getElementById(type);
	var bili = el.title;
	if(bili == '' || bili == null){
		bili = "50";
	}
	
	if(type.indexOf("wholeUp") > -1 || type.indexOf("wholeDown") > -1){
		var jsonUp =document.getElementById("wholeUp");
		var biliUp = jsonUp.title;
		if(biliUp == '' || biliUp == null){
			biliUp = "50";
		}
		wholeJson['half'][0]['bili']=biliUp;
		var jsonDown =document.getElementById("wholeDown");
		var biliDown = jsonDown.title;
		if(biliDown == '' || biliDown == null){
			biliDown = "50";
		}
		wholeJson['half'][1]['bili']=biliDown;
		
		
	}else if(type.indexOf("wholeLeft") > -1 || type.indexOf("wholeRight") > -1){
		var jsonUp =document.getElementById("wholeLeft");
		var biliUp = jsonUp.title;
		if(biliUp == '' || biliUp == null){
			biliUp = "50";
		}
		wholeJson['half'][0]['bili']=biliUp;
		var jsonDown =document.getElementById("wholeRight");
		var biliDown = jsonDown.title;
		if(biliDown == '' || biliDown == null){
			biliDown = "50";
		}
		wholeJson['half'][1]['bili']=biliDown;
	}
	if(type.indexOf("wholeLeftLeft") > -1 || type.indexOf("wholeUpUp") > -1 || type.indexOf("wholeLeftUp") > -1 || type.indexOf("wholeUpLeft") > -1){
		if(type != 'wholeLeftLeft' && type != 'wholeUpUp' && type != 'wholeLeftUp' && type != 'wholeUpLeft'){
		var changeType = getBianType(type);
		var jsonUp =document.getElementById(changeType);
		var biliUp = jsonUp.title;
		if(biliUp == '' || biliUp == null){
			biliUp = "50";
		}
		
		wholeJson['half'][0]['quarter'][0]['bili'] = biliUp;
		}
	}else if(type.indexOf("wholeLeftDown") > -1 || type.indexOf("wholeUpDown") > -1 || type.indexOf("wholeLeftRight") > -1 || type.indexOf("wholeUpRight") > -1){
		if(type != 'wholeLeftDown' && type != 'wholeUpDown' && type != 'wholeLeftRight' && type != 'wholeUpRight'){
			var changeType = getBianType(type);
			var jsonUp =document.getElementById(changeType);
			var biliUp = jsonUp.title;
			if(biliUp == '' || biliUp == null){
				biliUp = "50";
			}
			
			wholeJson['half'][0]['quarter'][1]['bili'] = biliUp;
			}
		
	}else if(type.indexOf("wholeDownLeft") > -1 || type.indexOf("wholeDownUp") > -1 || type.indexOf("wholeRightLeft") > -1 || type.indexOf("wholeRightUp") > -1){
		if(type != 'wholeDownLeft' && type != 'wholeDownUp' && type != 'wholeRightLeft' && type != 'wholeRightUp'){
			var changeType = getBianType(type);
			var jsonUp =document.getElementById(changeType);
			var biliUp = jsonUp.title;
			if(biliUp == '' || biliUp == null){
				biliUp = "50";
			}
			
			wholeJson['half'][1]['quarter'][0]['bili'] = biliUp;
			}
	}else if(type.indexOf("wholeDownDown") > -1 || type.indexOf("wholeDownRight") > -1 || type.indexOf("wholeRightRight") > -1 || type.indexOf("wholeRightDown") > -1){
		if(type != 'wholeDownDown' && type != 'wholeDownRight' && type != 'wholeRightRight' && type != 'wholeRightDown'){
			var changeType = getBianType(type);
			var jsonUp =document.getElementById(changeType);
			var biliUp = jsonUp.title;
			if(biliUp == '' || biliUp == null){
				biliUp = "50";
			}
			
			wholeJson['half'][1]['quarter'][1]['bili'] = biliUp;
			}
	}
//	alert(bili);
	if(type == 'whole'){
		wholeJson = createChildrenItemJson(wholeJson,item_id,url,pic,'whole','100');
	}else if(type == 'wholeLeft' || type == 'wholeRight' || type == 'wholeUp' || type == 'wholeDown'){
		var s = type.substring(5);
		if(s == 'Left' || s == 'Up' ){
			if(s == 'Up') s = 'up';
			if(s == 'Left') s = 'left';
			var halfJso = createChildrenItemJson(tempJson,item_id,url,pic,s,bili);
			//alert(JSON.stringify(wholeJson));
			wholeJson['half'][0] = halfJso;
		}else{
			if(s == 'Down' ) s = 'up';
			if(s == 'Right') s = 'left';
			var halfJso = createChildrenItemJson(tempJson,item_id,url,pic,s,bili);
			wholeJson['half'][1] = halfJso;
		}
	}else if(type == 'wholeUpLeft' || type == 'wholeUpRight' || type == 'wholeUpUp' || type == 'wholeUpDown'
			|| type == 'wholeDownLeft' || type == 'wholeDownRight' || type == 'wholeDownUp' || type == 'wholeDownDown'
			|| type == 'wholeLeftLeft' || type == 'wholeLeftRight' || type == 'wholeLeftUp' || type == 'wholeLeftDown'
			|| type == 'wholeRightLeft' || type == 'wholeRightRight' || type == 'wholeRightUp' || type == 'wholeRightDown'	){
		var halfJso;
		if(type.substring(type.length-4,type.length) == 'Left' || type.substring(type.length-5,type.length) == 'Right'){			
			halfJso = createChildrenItemJson(tempJson,item_id,url,pic,'left',bili);
		}
		else{
			halfJso = createChildrenItemJson(tempJson,item_id,url,pic,'up',bili);			
		}
		if(type.substring(5,7) =='Up' || type.substring(5,9) =='Left'){
			if(type.substring(type.length-4,type.length) == 'Left' || type.substring(type.length-2,type.length) == 'Up'){				
				wholeJson['half'][0]['quarter'][0] = halfJso;
			}else{				
				wholeJson['half'][0]['quarter'][1] = halfJso;
			}
		}else{
			if(type.substring(type.length-4,type.length) == 'Left' || type.substring(type.length-2,type.length) == 'Up'){				
				wholeJson['half'][1]['quarter'][0] = halfJso;
			}else{				
				wholeJson['half'][1]['quarter'][1] = halfJso;
			}
		}
	}else{
		var halfJso;
		if(type.substring(type.length-4,type.length) == 'Left' || type.substring(type.length-5,type.length) == 'Right'){			
			halfJso = createChildrenItemJson(tempJson,item_id,url,pic,'left',bili);
		}
		else{
			halfJso = createChildrenItemJson(tempJson,item_id,url,pic,'up',bili);			
		}
		if(type.substring(5,7) =='Up' || type.substring(5,9) =='Left'){
			if(type.substring(type.length-4,type.length) == 'Left' || type.substring(type.length-2,type.length) == 'Up'){				
				//alert("come in222");
				if(type.substring(7,9) == 'Up' || ( type.length >11 && type.substring(9,13)) == 'Left' || type.substring(9,11) == 'Up' || type.substring(7,11) == 'Left'){					
					wholeJson['half'][0]['quarter'][0]['eighth'][0] = halfJso;
				}else{
					wholeJson['half'][0]['quarter'][1]['eighth'][0] = halfJso;					
				}
				//alert("come in333");
				//wholeJson['half'][0]['quarter'][0] = halfJso;
			}else{	
				if(type.substring(7,9) == 'Up' || ( type.length >11 && type.substring(9,13)) == 'Left' || type.substring(9,11) == 'Up' || type.substring(7,11) == 'Left'){					
					wholeJson['half'][0]['quarter'][0]['eighth'][1] = halfJso;
				}else{
					wholeJson['half'][0]['quarter'][1]['eighth'][1] = halfJso;					
				}
			}
		}else{
			if(type.substring(type.length-4,type.length) == 'Left' || type.substring(type.length-2,type.length) == 'Up'){				
				//alert("come in222");
				if(type.substring(10,12) == 'Up' || ( type.length >11 && type.substring(9,13)) == 'Left' || type.substring(9,11) == 'Up' || type.substring(10,14) == 'Left'){					
					wholeJson['half'][1]['quarter'][0]['eighth'][0] = halfJso;
				}else{
					wholeJson['half'][1]['quarter'][1]['eighth'][0] = halfJso;					
				}
				//alert("come in333");
				//wholeJson['half'][0]['quarter'][0] = halfJso;
			}else{	
				if(type.substring(10,12) == 'Up' || ( type.length >11 && type.substring(9,13)) == 'Left' || type.substring(9,11) == 'Up' || type.substring(10,14) == 'Left'){					
					wholeJson['half'][1]['quarter'][0]['eighth'][1] = halfJso;
				}else{
					wholeJson['half'][1]['quarter'][1]['eighth'][1] = halfJso;					
				}
			}
		}
	}
//	alert(wholeJson.toString());
	//alert(JSON.stringify(wholeJson));
}

//添加half
function tianjiaHalf(type,bili1,bili2){	
	wholeJson =  JSON.parse(wholeJson);
	wholeJson['half'] = createChildrenJson(type,bili1,bili2);
	//alert(JSON.stringify(wholeJson)+"         "+type+bili1);
}

function tianjiaHalfNew(type,bili1,bili2){	
//	wholeJson =  JSON.parse(wholeJson);
	wholeJson['half'][0]['bili'] = bili1;
	wholeJson['half'][1]['bili'] = bili2;
	//alert(JSON.stringify(wholeJson)+"         "+type+bili1);
}
function repAll(AFindText,ARepText){
	var newTp = AFindText;
	for(var i = 0 ; i < 3 ; i++){
		newTp = newTp.replace(ARepText,'');
	}
	return newTp;
}

function storgeHalfBili(type,bili2,bili1){
	var pos = '';
	if(type == ''){
		return;
	}
	var sum=0;
	var newtype = type;
	var typeLength = type.length;
		
		newtype = repAll(newtype,'Up');
		var len = newtype.length;
		sum = (typeLength-len) / 2 + sum;
		typeLength = len;
		
		newtype = repAll(newtype,'Down');
		var len = newtype.length;
		sum = (typeLength-len) / 4 + sum;
		typeLength = len;newtype = repAll(type,'Left');
		var len = newtype.length;
		sum = (typeLength-len) / 4 + sum;
		typeLength = len;newtype = repAll(type,'Right');
		var len = newtype.length;
		sum = (typeLength-len) / 5 + sum;
//	alert(type);
	if(type.substring(type.length-5,type.length) == 'Right' || type.substring(type.length-4,type.length) == 'Left'){
		pos = 'left';
		if(type.substring(type.length-5,type.length) == 'Right'){
			addDivBili(type,bili1);
			addDivBili(type.substring(0,type.length-5)+"Left",bili2);
			bili2 = $("#"+type).width();
			bili1 = $("#"+type.substring(0,type.length-5)+"Left").width();
		}else if(type.substring(type.length-4,type.length) == 'Left'){
			addDivBili(type,bili1);
			addDivBili(type.substring(0,type.length-4)+"Right",bili2);
			bili1 = $("#"+type).width();
			bili2 = $("#"+type.substring(0,type.length-4)+"Right").width();
		}
	}else if(type.substring(type.length-2,type.length) == 'Up' || type.substring(type.length-4,type.length) == 'Down'){
		pos = 'up';
		if(type.substring(type.length-4,type.length) == 'Down'){
			addDivBili(type,bili1);
			addDivBili(type.substring(0,type.length-4)+"Up",bili2);
//			alert(type.substring(0,type.length-4)+"Up");
			bili2 = $("#"+type).height();
			bili1 = $("#"+type.substring(0,type.length-4)+"Up").height();
			//alert(type +document.getElementById(type).style.height+  $("#"+type).height() +$("#"+type).width() + (type.substring(0,type.length-4)+"Up") + $("#"+type.substring(0,type.length-4)+"Up").height()+ $("#"+type.substring(0,type.length-4)+"Up").width()+document.getElementById(type.substring(0,type.length-4)+"Up").style.height);
		}else if(type.substring(type.length-2,type.length) == 'Up' ){
//			alert(type +bili1+"  "+bili2);
			addDivBili(type,bili1);
			addDivBili(type.substring(0,type.length-2)+"Down",bili2);
			bili1 = $("#"+type).height();
			bili2 = $("#"+type.substring(0,type.length-2)+"Down").height();			
		}
	}
	if(type != ''){
		return;
	}
	var totalbili = bili1+bili2;
	bili1 = (bili1 / totalbili) *100;
	bili2 = (bili2 / totalbili) *100;
	//alert(type+ bili1 +"  bili  " +bili2);
	if(type == 'wholeRight' || type == 'wholeLeft' || type == 'wholeUp' || type == 'wholeDown'){//half
		tianjiaHalfNew(pos,bili1,bili2);
		//alert(bili1+""+JSON.stringify(wholeJson));
	}else if((type.substring(5,7) == 'Up' || ( (type.lenght > 8) &&(type.substring(5,9) == 'Left'))) &&(sum == 2)){//四分之一的上半部分或者左部分
		tianjiaQuarter('1','',bili1,bili2,pos);
	}else if(((type.lenght > 8 &&type.substring(5,10) == 'Right') || (type.lenght > 8 &&type.substring(5,9) == 'Down'))&&(sum == 2)){	//四分之一的下半部分或者右部分
		tianjiaQuarter(type,'',bili1,bili2,pos);
	}else if((type.substring(5,7) == 'Up' &&(type.substring(7,9) == 'Up' || (type.substring(7,11)=='Left'))) || (type.substring(5,9) == 'Left' && (type.substring(9,11) =='Up' || type.substring(9,13)=='Left')) &&sum == 3){	//八分之一的第上上部分
		tianjiaEighth('1','1',bili1,bili2,pos);
	}else if((type.substring(5,7) == 'Up' && (type.substring(7,11)=='Down' || type.substring(7,12)=='Right')) || (type.substring(5,9) == 'Left' &&(type.substring(9,13)=='Down'||type.substring(9,14)=='Right'))&&sum == 3){	//八分之一的第上下部分
		tianjiaEighth('1','2',bili1,bili2,pos);
	}else if((type.substring(5,10) == 'Right' || (type.substring(10,12)=='Up')|| type.substring(10,14)=='Left') || (type.substring(5,9) == 'Down' && (type.substring(9,11)=='Up')||type.substring(9,13)=='Left') && sum == 3){	//八分之一的第下上部分
		tianjiaEighth('2','1',bili1,bili2,pos);
	}else if((type.substring(5,10) == 'Right'&&(type.substring(10,14)=='Down') || type.substring(10,15)=='Right') || (type.substring(5,9) == 'Down' &&(type.substring(9,13)=='Down')||type.substring(9,14)=='Right')&&sum == 3){	//八分之一的第下下部分
		tianjiaEighth('2','2',bili1,bili2,pos);
	}
}

//添加quarter
function tianjiaQuarter(type,position,bili1,bili2,pos){
		//wholeJson =  JSON.parse(wholeJson);
		//alert(JSON.stringify(wholeJson));
	var halfJas = wholeJson.half;
	//添加上部分或者左部分
	if(type == '1'){		
		//var oneJso = halfJas[0];
		//oneJso = JSON.parse(oneJso);
		//oneJso
		halfJas[0]['quarter'] = createChildrenJson(pos,bili1,bili2);
	}else {
		//添加下部分或右部分
		//var anotherJso = halfJas[1];	
		//anotherJso = JSON.parse(anotherJso);
		//anotherJso
		halfJas[1]['quarter'] = createChildrenJson(pos,bili1,bili2);		
	}
	wholeJson['half'] = halfJas;
}

//添加eighth
function tianjiaEighth(type,position,bili1,bili2,pos){
	
	//wholeJson =  JSON.parse(wholeJson);
	var halfJas = wholeJson.half;
	//添加上部分或者左部分
	if(type == '1'){		
		var oneJso = halfJas[0];
		//oneJso = JSON.parse(oneJso);
		var quarJsa = oneJso.quarter;
		if(position == '1'){			
			var quarJso = quarJsa[0];
			//quarJso = JSON.parse(quarJso);
			quarJso['eighth'] = createChildrenJson(pos,bili1,bili2);
			quarJsa[0] = quarJso;
		}
		else {			
			var anothetQuarJson = quarJsa[1];
			//anothetQuarJson = JSON.parse(anothetQuarJson);
			anothetQuarJson['eighth'] = createChildrenJson(pos,bili1,bili2);
			quarJsa[1] = anothetQuarJson;
		}		
		oneJso.quarter = quarJsa;
		halfJas[0] = oneJso;
	}else{
		//添加下部分或右部分
		var anotherJso = halfJas[1];
		//anotherJso = JSON.parse(anotherJso);
		var quarJsa = anotherJso.quarter;
		if(position == '1'){			
			var quarJso = quarJsa[0];
			//quarJso = JSON.parse(quarJso);
			quarJso['eighth'] = createChildrenJson(pos,bili1,bili2);
			quarJsa[0] = quarJso;
		}
		else {			
			var anothetQuarJson = quarJsa[1];
			//anothetQuarJson = JSON.parse(anothetQuarJson);
			anothetQuarJson['eighth'] = createChildrenJson(pos,bili1,bili2);
			quarJsa[1] = anothetQuarJson;
		}		
		anotherJso.quarter = quarJsa;
		halfJas[1] = anotherJso;		
	}
	wholeJson['half'] = halfJas;
//	alert(JSON.stringify(wholeJson));
}

function createJson(prop, val,fenge,positon,content,width,heigh){
	 // 如果 val 被忽略
	wholeJson =  JSON.parse(wholeJson);
    if(typeof val === "undefined") {
        // 删除属性
        delete wholeJson[prop];
    }
    else {
        // 添加 或 修改
    	wholeJson[prop] = val;
    	//alert(wholeJson.half);
    }
    if(fenge == '1'){
    	wholeJson['height'] = heigh;
    	wholeJson['width'] = width;
    	
    }
    else if(fenge == '2'){
    	wholeJson['is_levels'] = '1';
    	
		//wholeJson = JSON.
	}else if(fenge == '3'){
		wholeJson['is_levels'] = '1';
	}else if(fenge == '4'){
		wholeJson['is_levels'] = '1';
	}
}

function shuqiefa(w,h,tp){//宽 高？
	var i = "";
	$("#fenge").val();
	var j ="";
//	if(tp != '' && tp.length >5){
//		if(tp.substring(tp.length-2,tp.length) == 'Up'){
//			tp = tp . subsubstring(0,tp.length-2);
//		}else if(tp.substring(tp.length-4,tp.length) == 'Down' || tp.substring(tp.length-4,tp.length) == 'Left'){
//			tp = tp . subsubstring(0,tp.length-4);
//		}else if(tp.substring(tp.length-5,tp.length) == 'Right'){
//			tp = tp . subsubstring(0,tp.length-5);
//		}
//	}
	//alert(tp);
	var length = $(father+" #"+faterId+"itemtid").length;
	if(faterId == 'whole'){
			
		tianjiaHalf('left',w,h);
			//alert(wholeJson);
		$("#fenge").val(2);
		$("#whole_left").val(2);
		 i = "wholeLeft";
		 j = "wholeRight";
	}else if(faterId == "wholeLeft"|| tp == 'wholeLeft'){
		if(tp != ''){
			tianjiaHalf('left',w,h);
		}else{			
			tianjiaQuarter(1,1,w,h,'left');
		}
		$("#fenge").val(3);
		$("#whole_left_left").val(2);
		i = "wholeLeftLeft";
		j = "wholeLeftRight";
	}else if(faterId == "wholeRight" || tp == 'wholeRight'){
		if(tp != ''){
			tianjiaHalf('left',w,h);
		}else {
			tianjiaQuarter(2,1,w,h,'left');
		}
		$("#fenge").val(3);
		$("#whole_right_left").val(2);
		i = "wholeRightLeft";
		j = "wholeRightRight";
	}else if(faterId == "wholeUp" || tp == 'wholeUp'){
		if(tp != ''){
			tianjiaHalf('up',w,h);
		}else{
			tianjiaQuarter(1,1,w,h,'left');
		}
		$("#fenge").val(3);
		$("#whole_up_left").val(2);
		i ="wholeUpLeft";
		j = "wholeUpRight";
	}else if(faterId == "wholeDown" || tp == 'wholeDown'){
		if(tp != ''){
			tianjiaHalf('up',w,h);
		}else {
			tianjiaQuarter(2,1,w,h,'left');
		}
		$("#fenge").val(3);
		$("#whole_down_left").val(2);
		i =	"wholeDownLeft";
		j = "wholeDownRight";
	}else if(faterId == 'wholeRightDown' || tp == 'wholeRightDown'){//三级分类
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}else {
			tianjiaEighth(2,2,w,h,'left');
		}
		$("#whole_right_down_left").val(2);
		i =	"wholeRightDownLeft";
		j = "wholeRightDownRight";
	}else if(faterId == 'wholeRightUp' || tp == 'wholeRightUp'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}else {
			tianjiaEighth(2,1,w,h,'left');
		}
		$("#whole_right_up_left").val(2);
		i =	"wholeRightUpLeft";
		j = "wholeRightUpRight";
	}else if(faterId == 'wholeLeftDown' || tp == 'wholeLeftDown'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(1,1,w,h,'up');
		}else tianjiaEighth(1,2,w,h,'left');
		$("#whole_left_down_left").val(2);
		i =	"wholeLeftDownLeft";
		j = "wholeLeftDownRight";
	}else if(faterId == 'wholeLeftUp' || tp == 'wholeLeftUp'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}else tianjiaEighth(1,1,w,h,'left');
		$("#whole_left_up_left").val(2);
		i =	"wholeLeftUpLeft";
		j = "wholeLeftUpRight";
	}else if(faterId == 'wholeDownDown' || tp == 'wholeDownDown'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}else tianjiaEighth(2,2,w,h,'left');
		$("#whole_down_down_left").val(2);
		i = "wholeDownDownLeft";
		j = "wholeDownDownRight";
	}else if(faterId == 'wholeDownUp' || tp == 'wholeDownUp'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}else tianjiaEighth(2,1,w,h,'left');
		$("#whole_down_up_left").val(2);
		i = "wholeDownUpLeft";
		j = "wholeDownUpRight";
	}else if(faterId == 'wholeUpDown' || tp == 'wholeUpDown'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}else tianjiaEighth(1,2,w,h,'left');
		$("#whole_up_down_left").val(2);
		i = "wholeUpDownLeft";
		j = "wholeUpDownRight";
	}else if(faterId == 'wholeUpUp' || tp == 'wholeUpUp'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}else tianjiaEighth(1,1,w,h,'left');
		$("#whole_up_up_left").val(2);
		i = "wholeUpUpLeft";
		j = "wholeUpUpRight";
	}else if(faterId == 'wholeLeftLeft' || tp == 'wholeLeftLeft'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}else tianjiaEighth(1,1,w,h,'left');
		$("#whole_left_left_left").val(2);
		i = "wholeLeftLeftLeft";
		j = "wholeLeftLeftRight";
	}else if(faterId == 'wholeLeftRight' || tp == 'wholeLeftRight'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}else tianjiaEighth(1,2,w,h,'left');
		$("#whole_left_right_left").val(2);
		i = "wholeLeftRightLeft";
		j = "wholeLeftRightRight";
	}else if(faterId == 'wholeRightLeft' || tp == 'wholeRightLeft'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}else tianjiaEighth(2,1,w,h,'left');
		$("#whole_right_left_left").val(2);
		i = "wholeRightLeftLeft";
		j = "wholeRightLeftRight";
	}else if(faterId == 'wholeRightRight' || tp == 'wholeRightRight'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}else tianjiaEighth(2,2,w,h,'left');
		$("#whole_right_right_left").val(2);
		i = "wholeRightRightLeft";
		j = "wholeRightRightRight";
	}else if(faterId == 'wholeUpLeft' || tp == 'wholeUpLeft'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}else tianjiaEighth(1,1,w,h,'left');
		$("#whole_up_left_left").val(2);
		i = "wholeUpLeftLeft";
		j = "wholeUpLeftRight";
	}else if(faterId == 'wholeUpRight' || tp == 'wholeUpRight'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}else tianjiaEighth(1,2,w,h,'left');
		$("#whole_up_right_left").val(2);
		i = "wholeUpRightLeft";
		j = "wholeUpRightRight";
	}else if(faterId == 'wholeDownLeft' || tp == 'wholeDownLeft'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}else tianjiaEighth(2,1,w,h,'left');
		$("#whole_down_left_left").val(2);
		i = "wholeDownLeftLeft";
		j = "wholeDownLeftRight";
	}else if(faterId == 'wholeDownRight' || tp == 'wholeDownRight'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}else tianjiaEighth(2,2,w,h,'left');
		$("#whole_down_right_left").val(2);
		i = "wholeDownRightLeft";
		j = "wholeDownRightRight";
	}
	if(tp != ''){
		return;
	}
	father.empty();
	father.append("<div title='' class='change' id='"+i+"' name='sqLeft'  onclick='getFather(this)' style='height:"+h+"px;width:"+w+"px;border:1px;border-style:solid;border-color:#000;float:left;'></div><div title='' class='change' name='sqRight'  id='"+j+"' onclick='getFather(this)' style='height:"+h+"px;width:"+w+"px;border:1px;border-style:solid;border-color:#000;float:right;'></div>");
	father.css("border","none");
	father.css("width",father.width()+2);
	
	father.css("height",father.height()+2);
	father.css("background-color","#fff");
	father.next().attr("name","");
	father.prev().attr("name","");
	father.removeAttr("onclick");
	father = $("#start");
}

function hengqiefa(w,h,tp){//宽 高？
	var i = "";
	var j ="" ;
	if(tp != '' && tp.length >5){
		if(tp.substring(tp.length-2,tp.length) == 'Up'){
			tp = tp . subsubstring(0,tp.length-2);
		}else if(tp.substring(tp.length-4,tp.length) == 'Down' || tp.substring(tp.length-4,tp.length) == 'Left'){
			tp = tp . subsubstring(0,tp.length-4);
		}else if(tp.substring(tp.length-5,tp.length) == 'Right'){
			tp = tp . subsubstring(0,tp.length-5);
		}
	}
	var length = $(father+" #"+faterId+"itemtid").length;
	//alert(tp+"   "+w+"     "+h +'        '+faterId);
	//alert(tp =='wholeUp');
	if(faterId == 'whole' || tp == 'whole'){
//		alert("gggggggg");
		if(tp == 'whole') {
			tiaozhengWhole(w,h);
		}else{			
			tianjiaHalf('up',w,h);
		}
		$("#fenge").val(2);
		$("#whole_up").val(2);
		 i = "wholeUp";
		 j = "wholeDown";
	}else if(faterId == 'wholeUp' || tp == 'wholeUp'){
		if(tp != ''){
			//alert(tp+"   "+w+"     "+h);
			tianjiaHalf('up',w,h);
		}
		else tianjiaQuarter(1,1,w,h,'up');
		$("#fenge").val(3);
		$("#whole_up_up").val(2);
		 i = "wholeUpUp";
		 j = "wholeUpDown";
	}else if(faterId == 'wholeDown'|| tp == 'wholeDown'){
		if(tp != ''){
			tianjiaHalf('up',w,h);
		}
		else tianjiaQuarter(2,1,w,h,'up');
		$("#fenge").val(3);
		$("#whole_down_up").val(2);
		 i = "wholeDownUp";
		 j = "wholeDownDown";
	}else if(faterId == 'wholeLeft'|| tp == 'wholeLeft'){
		if(tp != ''){
			tianjiaHalf('left',w,h);
		}
		else tianjiaQuarter(1,1,w,h,'up');
		$("#fenge").val(3);
		$("#whole_left_up").val(2);
		 i = "wholeLeftUp";
		 j = "wholeLeftDown";
	}else if(faterId == 'wholeRight'|| tp == 'wholeRight'){
		if(tp != ''){
			tianjiaHalf('left',w,h);
		}
		else tianjiaQuarter(2,1,w,h,'up');
		$("#fenge").val(3);
		$("#whole_right_up").val(2);
		 i = "wholeRightUp";
		 j = "wholeRightDown";
	}else if(faterId == 'wholeRightDown'|| tp == 'wholeRightDown'){//三级分类
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}
		else tianjiaEighth(2,2,w,h,'up');
		$("#whole_right_down_up").val(2);
		i = "wholeRightDownUp";
		j = "wholeRightDownDown";
	}else if(faterId == 'wholeRightUp'|| tp == 'wholeRightUp'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}
		else tianjiaEighth(2,1,w,h,'up');
		$("#whole_right_up_up").val(2);
		i = "wholeRightUpUp";
		j = "wholeRightUpDown";
	}else if(faterId == 'wholeLeftDown'|| tp == 'wholeLeftDown'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}
		else tianjiaEighth(1,2,w,h,'up');
		i = "wholeLeftDownUp";
		j = "wholeLeftDownDown";
	}else if(faterId == 'wholeLeftUp'|| tp == 'wholeLeftUp'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}
		else tianjiaEighth(1,1,w,h,'up');
		$("#whole_left_up_up").val(2);
		i = "wholeLeftUpUp";
		j = "wholeLeftUpDown";
	}else if(faterId == 'wholeDownDown'|| tp == 'wholeDownDown'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}
		else tianjiaEighth(2,2,w,h,'up');
		$("#whole_down_down_up").val(2);
		i = "wholeDownDownUp";
		j = "wholeDownDownDown";
	}else if(faterId == 'wholeDownUp'|| tp == 'wholeDownUp'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}
		else tianjiaEighth(2,1,w,h,'up');
		$("#whole_down_up_up").val(2);
		i = "wholeDownUpUp";
		j = "wholeDownUpDown";
	}else if(faterId == 'wholeUpDown'|| tp == 'wholeUpDown'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}
		else tianjiaEighth(1,2,w,h,'up');
		$("#whole_up_down_up").val(2);
		i = "wholeUpDownUp";
		j = "wholeUpDownDown";
	}else if(faterId == 'wholeUpUp'|| tp == 'wholeUpUp'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'up');
		}
		else tianjiaEighth(1,1,w,h,'up');
		$("#whole_up_up_up").val(2);
		i = "wholeUpUpUp";
		j = "wholeUpUpDown";
	}else if(faterId == 'wholeLeftLeft'|| tp == 'wholeLeftLeft'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}
		else tianjiaEighth(1,1,w,h,'up');
		$("#whole_left_left_up").val(2);
		i = "wholeLeftLeftUp";
		j = "wholeLeftLeftDown";
	}else if(faterId == 'wholeLeftRight'|| tp == 'wholeLeftRight'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}
		else tianjiaEighth(1,2,w,h,'up');
		$("#whole_left_right_up").val(2);
		i = "wholeLeftRightUp";
		j = "wholeLeftRightDown";
	}else if(faterId == 'wholeRightLeft'|| tp == 'wholeRightLeft'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}
		else tianjiaEighth(2,1,w,h,'up');
		$("#whole_right_left_up").val(2);
		i = "wholeRightLeftUp";
		j = "wholeRightLeftDown";
	}else if(faterId == 'wholeRightRight'|| tp == 'wholeRightRight'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}
		else tianjiaEighth(2,2,w,h,'up');
		$("#whole_right_right_up").val(2);
		i = "wholeRightRightUp";
		j = "wholeRightRightDown";
	}else if(faterId == 'wholeUpLeft'|| tp == 'wholeUpLeft'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}
		else tianjiaEighth(1,1,w,h,'up');
		$("#whole_up_left_up").val(2);
		i = "wholeUpLeftUp";
		j = "wholeUpLeftDown";
	}else if(faterId == 'wholeUpRight'|| tp == 'wholeUpRight'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}
		else tianjiaEighth(1,2,w,h,'up');
		$("#whole_up_right_up").val(2);
		i = "wholeUpRightUp";
		j = "wholeUpRightDown";
	}else if(faterId == 'wholeDownLeft'|| tp == 'wholeDownLeft'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}
		else tianjiaEighth(2,1,w,h,'up');
		$("#whole_down_left_up").val(2);
		i = "wholeDownLeftUp";
		j = "wholeDownLeftDown";
	}else if(faterId == 'wholeDownRight'|| tp == 'wholeDownRight'){
		$("#fenge").val(4);
		if(tp != ''){
			tianjiaQuarter(2,1,w,h,'left');
		}
		else tianjiaEighth(2,2,w,h,'up');
		$("#whole_down_right_up").val(2);
		i = "wholeDownRightUp";
		j = "wholeDownRightDown";
	}
	if(tp != ''){
		return;
	}
	//alert(JSON.stringify(wholeJson));
	father.empty();
	father.append("<div title='' class='change' id='"+i+"' name='hqUp'  onclick='getFather(this)' style='height:"+h+"px;width:"+w+"px;border:1px;border-style:solid;border-color:#000;'></div><div title='' class='change' name='hqDown'  id='"+j+"' onclick='getFather(this)' style='height:"+h+"px;width:"+w+"px;border:1px;border-style:solid;border-color:#000;'></div>");
	father.css("border","none");
	father.css("width",father.width()+2);
	father.css("height",father.height()+2);
	father.css("background-color","#fff");
	father.next().attr("name","");
	father.prev().attr("name","");
	father.removeAttr("onclick");
	father = $("#start");
}

function cutVertical(){//竖切
	if(father.height() != null){
		var limit_w = father.width();
		var limit_h = father.height();
		var total_w = $("#whole").width();
		var total_h = $("#whole").height();
		var total_limit = total_h * total_w / 8;
		if(limit_h * limit_w > total_limit){
			var view_width = (limit_w - 2)/2;
			var view_height = limit_h;
			shuqiefa(view_width,view_height,'');
		}
		else{
			alert("不能再竖着切了");
			father.css("background-color","#fff");
			father = $("#start");
		}
	}
	else{
		alert("请先选择被划分的区域");
	}
}

function cutAcross(){//横切
	if(father.height() != null){
		var limit_h= father.height();
		var limit_w = father.width();
		var total_w = $("#whole").width();
		var total_h = $("#whole").height();
		var total_limit = total_h * total_w / 8;
		if(limit_h * limit_w > total_limit){
			var view_height = (limit_h - 2)/2;
			var view_width = father.width();
			hengqiefa(view_width,view_height,'');
		}
		else{
			alert("不能再横着切了");
			father.css("background-color","#fff");
			father = $("#start");
		}
	}
	else{
		alert("请先选择被划分的区域");
	}
}

function addDisplay(img,item_id,click_url){
	var total_w = $("#whole").width();
	var total_h = $("#whole").height();
	total_w = 10;
	total_h = quanjuHigh;
	tiaozhengWhole(total_w,total_h);
	if(father.height() != null){
		father.empty();
		//alert(faterId);
		//alert(JSON.stringify(wholeJson)+faterId );
		addItemJson(faterId,item_id,click_url,img);
//		storgeHalfBili(faterId,'','');
		//father.removeChild(img);
		father.append("<img width=\" 100%\" height=\"100%\" src="+img+"> <input id="+faterId+"itemtid name="+faterId+"itemtid type=\"hidden\" value="+item_id+"> <input id="+faterId+"img name="+faterId+"img type=\"hidden\" value="+img+">  <input id="+faterId+"clickUrl name="+faterId+"clickUrl type=\"hidden\" value="+click_url+"> ");
	}
	else{
		alert("请先选择区域");
	}
}

function addDivBili(type,bili){
//	alert("hghgfhgfhgfh");
	var el=document.getElementById(type); 
//	alert(el.title +"  title");
	el.title= bili;
    //el.bili= bili;
}

function refresh(){//刷新
	window.location.reload();
}

function getFather(f){
	faterId = f.id;
	if( $(f) != father ){
	father.css("background-color","#fff");
	father = $(f);
	father.css("background-color","#ccc");
	}
}