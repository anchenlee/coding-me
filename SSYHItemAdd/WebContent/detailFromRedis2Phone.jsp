<%@page import="cn.youhui.platform.db.DBManager"%>
<%@page import="cn.youhui.itemadd.dataadapter.BaseAdapter"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="content-script-type" content="text/javascript">
<title>商品管理后台</title>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap2.0.1.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom2.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style2.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom2.css">
</head>


<body onload="ol()">
		<div class="mainbody" style="width: 1700px;margin:0 auto;">
		<div class="panel panel-default main-panel" style="border:none;">
			<div class="panel-heading">
				<h3 style="display: inline;" class="panel-title">数据库</h3>
			</div>
				<div id="panelLeft" class="panel-body" style="position: relative;width:760px;">
				<% 
	    			int i = 0;
				%>
	
				<c:forEach items="${l1}" var="s">
						<div style="width:218px;height:280px;padding:10px 15px;border:1px solid #ddd;border-radius:5px;float:left;margin:10px;">
							<span id='cp_<%=i %>' style="width:188px;display:block;text-align: center;cursor:move;">点我拖动</span>
							<img src="${s.itemImg }" style="display:inline-block;width:100px;"/>
							<p>${s.itemTitle }</p>
							<p>商品id:${s.itemId }</p>
							<p>折扣价格:${s.itemPromPrice }</p>
						</div>
						<% i++; %>
					</c:forEach>
				
				</div>	
		</div>
		
		<div class="panel panel-default main-panel" style="float:right;border:none;">
			<div class="panel-heading">
				<h3 style="display: inline;" class="panel-title">手机</h3>
			</div>	
				<div id="panelRight" class="panel-body" style="position: relative;width:760px;">
				<c:forEach items="${l2}" var="s">
						<div style="width:218px;height:280px;padding:10px 15px;border:1px solid #ddd;margin:10px;border-radius:5px;float:left;">
							<img src="${s.itemImg }" style="display:inline-block;width:100px;"/>
							<p>${s.itemTitle }</p>
							<p>商品id:${s.itemId }</p>
							<p>折扣价格:${s.itemPromPrice }</p>
						</div>
					</c:forEach>
				</div>		
		</div>		
	</div>		
</body>
<script type="text/javascript">
var position_array = new Array();
position_array = document.getElementById('panelLeft').getElementsByTagName('SPAN');
	//拖拽拖拽
var isDraging=false; //是否可以拖拽
var mouseOffsetX = 0;   //偏移
var mouseOffsetY = 0;
var dragObj={};//拖拽对象
//获取类对象
	for(var i=0;i<position_array.length;i++){
		position_array[i].addEventListener('mousedown',function(){ //检测到标题 被 鼠标按下
		var e = e||window.event;
		dragObj.o = this.parentNode;
		dragObj.id = this.id;
		dragObj.id = parseInt(dragObj.id.substr(3));/*  获取拖拽div的id */
		dragObj.o.style.filter='alpha(opacity=60)'; //拖动透明效果(IE)
		var shang = parseInt(dragObj.id / 3);  //第几行
		var yu = dragObj.id % 3;	//第几列
		var target_top = 15 + shang*280;  //算出top值
		var target_left = 15 + yu*218;  //算出left值
		dragObj.o.style.top = target_top +'px';
		dragObj.o.style.left = target_left +'px';
		dragObj.o.style.position = 'absolute';
		dragObj.o.style.opacity='0.6';//拖动透明效果(firefox)
		mouseOffsetX = e.pageX - dragObj.o.offsetLeft;
		mouseOffsetY = e.pageY - dragObj.o.offsetTop;
		isDraging = true;
		});
	}

//鼠标移动
document.onmousemove = function(e){
	var e = e || window.event;
	var mouseX = e.pageX;//鼠标当前的位置
	var mouseY = e.pageY;
	
	var moveX = 0;  //div的新位置
	var moveY = 0;
	
	if( isDraging === true){
		moveX = mouseX - mouseOffsetX;
		moveY = mouseY - mouseOffsetY;
		
		var pageWidth = document.documentElement.clientWidth;
		var pageHeight = document.documentElement.clientHeight;
		
		var dialogWidth = dragObj.o.offsetWidth;
		var dialogHeight = dragObj.o.offsetHeight;
		
		var maxX = (pageWidth - dialogWidth)*0.98;
		var maxY = pageHeight - dialogHeight;
		
		moveX = Math.min( maxX,Math.max(0,moveX));
		moveY = Math.min( maxY,Math.max(0,moveY));
		
		dragObj.o.style.left = moveX + 'px';
		dragObj.o.style.top= moveY + 'px';
	};
};

document.onmouseup = function(){//鼠标松开
	var newLeft = parseInt(dragObj.o.style.left);//当前拖拽的div的  left
	var newTop = parseInt(dragObj.o.style.top);//当前拖拽的div的  top
	
	//寻找位置
	var cols = (newLeft - 15) / 218;  //列
	var rows_judge = (newTop - 15) % 280;   //通过余数判断位置
	var rows = parseInt((newTop - 15) / 280);
	if(cols < 0 && -1<cols){  //属于第0列
		if(rows_judge <100){      //属于上一行
			cols = 0;
		}else if (rows_judge >180){       //属于下一行
			rows++;
			cols = 0;
		}
	}
	else if(cols < 1.1 && 0<cols){ //属于第1列
		if(rows_judge <100){
			cols = 1;
		}else if (rows_judge >180){
			rows++;
			cols = 1;
		}
	}
	else if(cols < 2.17 && 1.1<cols){ //属于第2列
		if(rows_judge <100){
			cols = 2;
		}else if (rows_judge >180){
			rows++;
			cols = 2;
		}
	}
	else if(cols < 3 && 2.17<cols){ //属于第2列
		if(rows_judge <100){
			rows++;
			cols = 0;
		}else if (rows_judge >180){
			rows+2;
			cols = 0;
		}
	}
	exchangPosition(rows,cols);
	    //寻找位置结束 
	isDraging = false;
	dragObj={};
	resetID();
};

function exchangPosition(exRows,exCols)   //调整位置(行，列)
{
	var exID = exCols + exRows * 3;
	var addDiv = dragObj.o.innerHTML;
	$('#cp_'+dragObj.id).parent().remove();
	if(exID == 0){
		$('#panelLeft').prepend('<div style="width:218px;height:280px;padding:10px 15px;border:1px solid #ddd;border-radius:5px;float:left;margin:10px;">'+addDiv+'</div>');
	}else{
		exID--;
		$('#cp_'+exID).parent().after('<div style="width:218px;height:280px;padding:10px 15px;border:1px solid #ddd;border-radius:5px;float:left;margin:10px;">'+addDiv+'</div>');
	}
}


function resetID(){    //重置所有的ID
	var j = 0;
	$('#panelLeft span').each(function(){
		$(this).attr('id','cp_'+j);
		j++;
	})
}

//拖拽结束

</script>
</html>