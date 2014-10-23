<%@page import="com.netting.dao.admin.Admin_Tag_DAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.netting.bean.Tag_Bean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商品混排新增-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <!-- 
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js"></script><!--谷歌jquery包-->
    
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/controller.js"></script><!--导入js-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mixstyle.css"/><!--导入css-->
    <script type="text/javascript">
	   function getPageItem(){
		   var page = $("#pagejump").val();
		   getPageItemList(page);
	   } 
	   
	   function selectTagItem(){
		   var choiceTagid = $("#choiceTagid").val();
		   $("#shangpinTagid").val(choiceTagid);
		   getPageItemList(1);
	   }
    function chakan() {
    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showMixStylePage&tagid=${tagid}&keyword=${keyword}";			
    }
    function addMixProducts(){
    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addMixStyleItemListNew&tagid=${tagid}&keyword=${keyword}";			
        }
	    function add_new_item()
	    {
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addMixStyleItemList&tagid=${tagid}&keyword=${keyword}";			
	    }
	    
	    function tijiao(){
	    	var width = $("#widthth").val();
	    	var height = $("#heightth").val();
	    	var title = $("#titleInput").val();
	    	if(width == '' || height == '' ){
	    		alert("长宽比例不能为空");
	    		return;
	    	}
	    	if(title == ''){
	    		alert("标题不能为空");
	    		return;
	    	}
	    	$("#width").val(width);
	    	$("#height").val(height);
	    	$("#title").val(title);
	    	var content = document.getElementById("whole").innerHTML;
	    	
	    	content = "<div class=\"change\"  id=\"whole\">"+content +"</div>";
	    	$("#wholeContent").val(content);
	    	$("#addMixForm").submit();
	    }

	    function getPageItemList(page){
	    	var tagid = '${tagid}';
	    	tagid = $("#shangpinTagid").val();
	    	$.ajax(
	            	{
	        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=getPageItemList',
	        		    type: 'POST',
	        		    data: {
	        		    	page:page,
	        		    	tagid:tagid
		    			},
	        		    dataType: 'json',
	        		    cache: false,
	        		    timeout: 5000,
	        		    success: function(json)
	        		    {
	        		    	var list = $("#item_list");
	        		    	var totalPage = json.totalPage;
	        		    	var itemList = json.itemList;
	        		    	if(itemList == '' || itemList.length ==0){
	        		    		return;
	        		    	}
	        		    	var curpage = json.page;
	        		    	list.empty();
	        		    	for(var i = 0; i <itemList.length ; i++ ){
	        		    		var bean = JSON.parse(itemList[i]);
	        		    		//alert(bean);
	        		    	list.append("<li style=\"border: 1px solid #DD4B39 ;float: left;width: 120px;margin-left: 10px;\" class=\"draggable\">  <img src=\""+bean.pic_url+"\" height=\"120px\" width=\"120px\" class=\"draggable txt1\" id=\"21234568401\" onclick=\"addDisplay('"+bean.pic_url+"','"+bean.item_id+"','"+bean.clickURL+"')\">  </li>") ;
	        		    	}
	        		    	var perpage = curpage -1;
	        		    	if(perpage <1) perpage = 1;
	        		    	var nextpage = curpage +1;
	        		    	if(nextpage > totalPage ) nextpage = totalPage;
	        		    	var pagedisplay = $("#pagedisplay");
	        		    	pagedisplay.empty();
	        		    	var select = '';
	        		    	for(var j = 1 ; j <= totalPage ; j ++){
	        		    		select += "<option value="+j;
	        		    		if(j == curpage) {
	        		    			select +=" selected ";
	        		    		}
	        		    		select += "  >"+j+"</option>";
	        		    	}
	        		    	pagedisplay.append("<div class=\"\" align=\"center\"> 当前页"+curpage+"/"+totalPage+" &nbsp;  <a href=\"#\" onclick=\"getPageItemList('1')\">首页</a> &nbsp;    <a href=\"#\" onclick=\"getPageItemList('"+perpage+"')\">上一页</a>&nbsp;<a href=\"#\" onclick=\"getPageItemList('"+nextpage+"')\">下一页</a>&nbsp;<a href=\"#\" onclick=\"getPageItemList('"+totalPage+"')\">末页</a>&nbsp;&nbsp;转到第<select onchange=\"getPageItem()\"  id=\"pagejump\" > "+select+" </select>页</div>");
	        		    	
	        		    	pagedisplay.append("");
	        		    	
	        		    }
	        		});
	    }
	    
	    function baocun(){
	    	if(!confirm("确定保存该样式？")){
	    		return;
	    	}
	    	var content = document.getElementById("addMixForm").innerHTML;
	    	var biaoshi =  $("#fenge").val();
	    	var tagname = '${keyword}';
	    	var tagid = '${tagid}';
	    	//content = content.replace(tagname,"#tagName#");
	    	//content = content.replace(tagid,"#tagid#");
	    	var data = JSON.stringify(wholeJson); 
	    	$.ajax(
	            	{
	        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addMixStylePage',
	        		    type: 'POST',
	        		    data: {
				    			content:content,
				    			biaoshi:biaoshi,
				    			data:data
		    			},
	        		    dataType: 'json',
	        		    cache: false,
	        		    timeout: 5000,
	        		    success: function(json)
	        		    {
	        		    	var result = json.result;
	        		    	if (result == "0")
	       		    		{
	        		    		alert("保存成功");
	        		    		return ;
	       		    		}
	        		    	else if (result == "1")
	        		    	{
	        		    		alert("操作失败!");
	        		    		return false;
	        		    	}else if(result == "2"){
	        		    		alert("样式重复！");
	        		    		return false;
	        		    	}
	        		    }
	        		});
	    }
	    function tiaojiaoshuju(){
	    	var title = $("#titleInput").val();
	    	if(title == ''){
	    		alert("标题不能为空！");
	    		return;
	    	}
	    	var bg_color = $("#bg_color").val();
	    	if(bg_color == ''){
	    		alert("请选择icon！");
	    		return;
	    	}
	    	bg_color = bg_color.replace("#","");
	    	var tagid = '${tagid}';
	    	var keyword = '${keyword}';
			var content1 = document.getElementById("addMixForm").innerHTML;
			content1 = content1.replace("style=\"width: 400px; height: 400px;","style=\"width: 201px; height: 201px;")
	    	//content1 = "<div class=\"change\"  id=\"whole\">"+content1 +"</div>";
	    //	var chilidr= document.getElementById("whole").firstChild.innerHTML;
	    //	var tContent = document.getElementById("wholeUpLeft").innerHTML;
	    //alert(content1);
	    	var data = JSON.stringify(wholeJson);
	    	$.ajax(
	            	{
	        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addMixStyleItemPage',
	        		    type: 'POST',
	        		    data: {
				    			data:data,
				    			title:title,
				    			content:content1,
				    			keyword:keyword,
				    			tagid:tagid,
				    			bg_color:bg_color
		    			},
	        		    dataType: 'text',
	        		    cache: false,
	        		    timeout: 5000,
	        		    success: function(data)
	        		    {
	        		    	location.href='tag_item_manager?actionmethod=showMixItemPage&tagid='+tagid+'&keyword='+keyword;
	        		    }
	        		});
	    }
	    
	    function choiceBgcolor(id){
	    	for(var i = 1 ; i < 10 ; i ++){
	    		$("#list_bgcolor"+i).attr("class",'');
	    	}
	    	$("#list_bgcolor"+id).attr("class",'imgdisplay');
	    	var bgcolor = $("#input_bgcolor"+id).val();
	    	$("#bg_color").val(bgcolor);
	    	$("#titleInput").attr("style",'color:'+bgcolor+';width:300px;');	    	  
	    }
    </script>
    
        <style type="text/css">
    .imgdisplay{
   BorDer-riGHT: #DF1B4A 1px groove;
    BorDer-Top: #DF1B4A 1px groove;
     BorDer-LeFT: #DF1B4A 1px groove; 
     BorDer-BoTToM: #DF1B4A 1px groove
    }
    
    </style>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
	<!-- 

	 -->
	 

	 
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">
	         <input type="hidden" id="shangpinTagid" name="shangpinTagid" value="">
	         <input  type="button" value="《${keyword}》新增混排" onclick="addMixProducts()">
	         <input type="button" value="保存样式" onclick="baocun()">
	         <input type="button" value="查看样式" onclick="chakan()">
	         	         <!-- 
	         <span style="float: right">
	         请输入标题：
	         <input type="text" id="titleInput" name="titleInput" value=""  style="width: 300px;">
	         	         <input type="button" value="提交" onclick="tiaojiaoshuju()">
	         <input type="button" value="提交" onclick="tijiao()">
	         请输入高与宽比：
	         <input type="text" id="heightth" name="heightth" value=""  style="width: 30px;">
	         <input type="text" id="widthth" name="widthth" value=""  style="width: 30px;">
	         </span>
	         	          -->
	         </h1>
	         <hr/>
	         <div style="margin: auto;">
	         <c:forEach var="item" items="${styleList}"  varStatus="status">
<div style="float: left;margin-right :7px;">
<a href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addMixStyleItemListNew&tagid=${tagid}&keyword=${keyword}&style_id=${item.id}"><img alt="" width="120px;" src="${item.pic} "> </a> 
	    			   </div>
	    			    </c:forEach>
	         </div>
	         <div style="float: left;"></div>
    <hr/>

<div id="btns" name="start" style=" height: 30px; padding-bottom: 10px;margin-left: 80px; margin-top: 30px;">
<button type="button" class="btn" onclick="cutVertical()" >竖切</button>
<button type="button" class="btn" onclick="cutAcross()">横切</button>
<button type="button" class="btn" onclick="refresh()">重置</button>
</div>
<div id="content">

<input type="hidden" name="actionmethod" value="addItemMixStyleNew">
<input type="hidden" id="width" name="width" value="">
	         <input type="hidden" id="height" name="height" value="">

	 <input type="hidden" id="fenge"  name="fenge" value="1">
	 
	 <!-- 二分之一 -->
	 <input  type="hidden" name="tagid" value="${tagid}">
	 <input  type="hidden" name="keyword" value="${keyword}">
	 <input type="hidden" id="whole_up" name="whole_up" value="1">
	 <input type="hidden" id="whole_left" name="whole_left" value="1">
	 <!-- 四分之一 -->
	 <input type="hidden" id="whole_up_up" name="whole_up_up" value="1">
	 <input type="hidden" id="whole_up_left" name="whole_up_left" value="1">
	 <input type="hidden" id="whole_down_up" name="whole_down_up" value="1">
	 <input type="hidden" id="whole_down_left" name="whole_down_left" value="1">
	 <input type="hidden" id="whole_left_up" name="whole_left_up" value="1">
	 <input type="hidden" id="whole_left_left" name="whole_left_left" value="1">
	 <input type="hidden" id="whole_right_up" name="whole_right_up" value="1">
	 <input type="hidden" id="whole_right_left" name="whole_right_left" value="1">  
	 <!-- 八分之一 -->
	 <input type="hidden" id="whole_up_up_up" name="whole_up_up_up" value="1">
	 <input type="hidden" id="whole_up_up_left" name="whole_up_up_left" value="1">
	  <input type="hidden" id="whole_up_down_up" name="whole_up_down_up" value="1">
	   <input type="hidden" id="whole_up_down_left" name="whole_up_down_left" value="1">
	   
	   	 <input type="hidden" id="whole_up_left_up" name="whole_up_left_up" value="1">
	 <input type="hidden" id="whole_up_left_left" name="whole_up_left_left" value="1">
	  <input type="hidden" id="whole_up_right_up" name="whole_up_right_up" value="1">
	   <input type="hidden" id="whole_up_right_left" name="whole_up_right_left" value="1">
	
		 <input type="hidden" id="whole_down_up_up" name="whole_down_up_up" value="1">
	 <input type="hidden" id="whole_down_up_left" name="whole_down_up_left" value="1">
	  <input type="hidden" id="whole_down_down_up" name="whole_down_down_up" value="1">
	   <input type="hidden" id="whole_down_down_left" name="whole_down_down_left" value="1">

	<input type="hidden" id="whole_down_left_up" name="whole_down_left_up" value="1">
	 <input type="hidden" id="whole_down_left_left" name="whole_down_left_left" value="1">
	  <input type="hidden" id="whole_down_right_up" name="whole_down_right_up" value="1">
	   <input type="hidden" id="whole_down_right_left" name="whole_down_right_left" value="1">

	<input type="hidden" id="whole_left_up_up" name="whole_left_up_up" value="1">
	 <input type="hidden" id="whole_left_up_left" name="whole_left_up_left" value="1">
	  <input type="hidden" id="whole_left_down_up" name="whole_left_down_up" value="1">
	   <input type="hidden" id="whole_left_down_left" name="whole_left_down_left" value="1">

	<input type="hidden" id="whole_left_left_up" name="whole_left_left_up" value="1">
	 <input type="hidden" id="whole_left_left_left" name="whole_left_left_left" value="1">
	  <input type="hidden" id="whole_left_right_up" name="whole_left_right_up" value="1">
	   <input type="hidden" id="whole_left_right_left" name="whole_left_right_left" value="1">

	<input type="hidden" id="whole_right_up_up" name="whole_right_up_up" value="1">
	 <input type="hidden" id="whole_right_up_left" name="whole_right_up_left" value="1">
	  <input type="hidden" id="whole_right_down_up" name="whole_right_down_up" value="1">
	   <input type="hidden" id="whole_right_down_left" name="whole_right_down_left" value="1">

		<input type="hidden" id="whole_right_left_up" name="whole_right_left_up" value="1">
	 <input type="hidden" id="whole_right_left_left" name="whole_right_left_left" value="1">
	  <input type="hidden" id="whole_right_right_up" name="whole_right_right_up" value="1">
	   <input type="hidden" id="whole_right_right_left" name="whole_right_right_left" value="1">
	   <input type="hidden" id="title" name="title" >
	   <input type="hidden" name="wholeContent" id="wholeContent">
<form id="addMixForm" action=" <%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addItemMixStyleNew"  style="width: 30% ; float : left">
<div  id="whole"  class="change" onclick="getFather(this)" style="width: 400px; height: 400px;"></div>
</form>
 <span style="margin-left:50px;"><strong>请输入标题：</strong></span>
	         <input type="text" id="titleInput" name="titleInput" value=""  style="width: 300px; ">
	         <input type="hidden" id="bg_color" name="bg_color" value="" > 
	                	    
	         	                   &nbsp;<img alt="" src="http://static.etouch.cn/suishou/ad_img/3s340cpyw.png" onclick="choiceBgcolor('1')" id="list_bgcolor1"  style="height: 25px;"><input type="hidden" id="input_bgcolor1" name="input_bgcolor1" value="http://static.etouch.cn/suishou/ad_img/3s340cpyw.png">
	         &nbsp;<img alt="" src="http://static.etouch.cn/suishou/ad_img/3te04ddjz.png" onclick="choiceBgcolor('2')" id="list_bgcolor2"  style="height: 25px;"><input type="hidden" id="input_bgcolor2" name="input_bgcolor2" value="http://static.etouch.cn/suishou/ad_img/3te04ddjz.png">
	        &nbsp;<img  alt="" src="http://static.etouch.cn/suishou/ad_img/3trvz56g8.png" onclick="choiceBgcolor('3')" id="list_bgcolor3" style="height: 25px;"> <input type="hidden" id="input_bgcolor3" name="input_bgcolor3" value="http://static.etouch.cn/suishou/ad_img/3trvz56g8.png">
	         	                &nbsp;<img alt="" src="http://static.etouch.cn/suishou/ad_img/3ubslyqia.png" onclick="choiceBgcolor('4')" id="list_bgcolor4"  style="height: 25px;"><input type="hidden" id="input_bgcolor4" name="input_bgcolor4" value="http://static.etouch.cn/suishou/ad_img/3ubslyqia.png">
	         &nbsp;<img alt="" src="http://static.etouch.cn/suishou/ad_img/3uth4bpa6.png" onclick="choiceBgcolor('5')" id="list_bgcolor5"  style="height: 25px;"><input type="hidden" id="input_bgcolor5" name="input_bgcolor5" value="http://static.etouch.cn/suishou/ad_img/3uth4bpa6.png">
	        &nbsp;<img  alt="" src="http://static.etouch.cn/suishou/ad_img/3v6dcgllj.png" onclick="choiceBgcolor('6')" id="list_bgcolor6" style="height: 25px;"> <input type="hidden" id="input_bgcolor6" name="input_bgcolor6" value="http://static.etouch.cn/suishou/ad_img/3v6dcgllj.png">
	         	                &nbsp;<img alt="" src="http://static.etouch.cn/suishou/ad_img/3vk2l95dk.png" onclick="choiceBgcolor('7')" id="list_bgcolor7"  style="height: 25px;"><input type="hidden" id="input_bgcolor7" name="input_bgcolor7" value="http://static.etouch.cn/suishou/ad_img/3vk2l95dk.png">
	         &nbsp;<img alt="" src="http://static.etouch.cn/suishou/ad_img/3vwxr83fv.png" onclick="choiceBgcolor('8')" id="list_bgcolor8"  style="height: 25px;"><input type="hidden" id="input_bgcolor8" name="input_bgcolor8" value="http://static.etouch.cn/suishou/ad_img/3vwxr83fv.png">
	        &nbsp;<img  alt="" src="http://static.etouch.cn/suishou/ad_img/3w93yy7q5.png" onclick="choiceBgcolor('9')" id="list_bgcolor9" style="height: 25px;"> <input type="hidden" id="input_bgcolor9" name="input_bgcolor9" value="http://static.etouch.cn/suishou/ad_img/3w93yy7q5.png">
	         	 
	         	    
	         	      
	         	         <input type="button" value="提交" onclick="tiaojiaoshuju()">
	         	         <span style="float: right; margin-right: 10%;">标签下商品:
	         	         <select id="choiceTagid" onchange="selectTagItem()">
	         	         <option value="">全部</option>
	         	         	         	         <%
	         	        ArrayList<HashMap<String, String>> tagList = Admin_Tag_DAO.getHeaderTagList(1, 538);
	         	        //tagList.addAll(Admin_Tag_DAO.getHeaderTagList(2, 538));
	         	        for(HashMap<String, String> map : tagList){
	         	        	Iterator<String> iter = map.keySet().iterator();

	         	        	if (iter.hasNext()) {
	         	        	    String key = iter.next();
	         	        	    String value = map.get(key);
	         	        	    
	         	        	    %>
	         	        	    <option value="<%=key%>"><%=value %></option>
	         	        	    <% 
	         	        	}
	         	        }
	         	         %>
	         	         </select>
	         	         </span>
<div style="float: right; width: 68%">
<%@include file="tag_item_list_page.jsp" %>
</div>
</div>

<SCRIPT language=javascript>
var theobject = null; //拖拽开始获得的值
function resizeObject() {
	this.el = null; //拖拽对象
	this.dir = "";
	this.grabx = null;//全局变量
	this.graby = null;
	this.width = null;
	this.height = null;
	this.left = null;
	this.top = null;
}
//找出拖拽的方向
function getDirection(el) {
	var xPos, yPos, offset, dir;
	dir = "";
	xPos = window.event.offsetX;//获取鼠标指针位置相对于触发事件的对象的 x 坐标,标签的左上角为原点
	yPos = window.event.offsetY;// y坐标
	offset = 8; 
	//n向上 s向下 w向左 e向右
	if (yPos<offset) {dir += "n";}
	else if (yPos > el.offsetHeight-offset){ dir += "s";}
	if (xPos<offset) {dir += "w";}
	else if (xPos > el.offsetWidth-offset) {dir += "e";}
	return dir;
}
function doDown() { //鼠标按下
	var el = getReal(event.srcElement, "className", "change");
	if (el == null) {
		theobject = null;
		return;
	}
	dir = getDirection(el);
	if (dir == "") return;
	theobject = new resizeObject();
	theobject.el = el;
	theobject.dir = dir;
	theobject.grabx = window.event.clientX;
	theobject.graby = window.event.clientY;
	theobject.width = el.offsetWidth;
	theobject.height = el.offsetHeight;
	theobject.left = el.offsetLeft;
	theobject.top = el.offsetTop;
	window.event.returnValue = false;
	window.event.cancelBubble = true;
}
function doUp() {  //鼠标松开
	if (theobject != null) {
		theobject = null;
	}
}
function doMove() { //鼠标移动
	var el, xPos, yPos, str, xMin, yMin;
	xMin = 8; //最小宽度
	yMin = 8; //最小高度
	el = getReal(event.srcElement, "className", "change");
	if (el.className == "change") {
		str = getDirection(el);
	//修改光标
		if (str == "") str = "default";
		else str += "-resize";
		el.style.cursor = str;
	}
	//拖拽开始
	//n向上 s向下 w向左 
	if(theobject != null) {
		if (dir.indexOf("e") != -1 && theobject.el.getAttribute('name')=="sqLeft"){//未找到字符串 返回-1 找到就返回该位置 e右
			theobject.el.style.width = Math.max(xMin, theobject.width + window.event.clientX - theobject.grabx) + "px";
			if (theobject.el.id != "whole"){
			var browidth = parseInt(theobject.el.parentNode.style.width) - 4 - parseInt(theobject.el.style.width);//防止溢出
			theobject.el.nextSibling.style.width = browidth+"px";
			jisuan(theobject.el.nextSibling,0);
			};
			jisuan(theobject.el,0);
			//宽为0   高为1
		}
		if (dir.indexOf("s") != -1 && ( theobject.el.id == "whole" ||theobject.el.getAttribute('name')=="hqUp")){//s下
			theobject.el.style.height = Math.max(yMin, theobject.height + window.event.clientY - theobject.graby) + "px";
			if (theobject.el.id != "whole") {
			var broheight = parseInt(theobject.el.parentNode.style.height) - 4 - parseInt(theobject.el.style.height);
			theobject.el.nextSibling.style.height = broheight+"px";
			jisuan(theobject.el.nextSibling,1);
			};
			jisuan(theobject.el,1);
		}
		if (dir.indexOf("w") != -1 && theobject.el.getAttribute('name')=="sqRight") {//w左
			theobject.el.style.left = Math.min(theobject.left + window.event.clientX - theobject.grabx, theobject.left + theobject.width - xMin) + "px";
			theobject.el.style.width = Math.max(xMin, theobject.width - window.event.clientX + theobject.grabx) + "px";
			if (theobject.el.id != "whole") {
			var browidth = parseInt(theobject.el.parentNode.style.width) - 4 - parseInt(theobject.el.style.width);
			theobject.el.previousSibling.style.width = browidth+"px";
			jisuan(theobject.el.previousSibling,0);
			};
			jisuan(theobject.el,0);
		}
		if (dir.indexOf("n") != -1&& theobject.el.getAttribute('name')=="hqDown"){//n上
			theobject.el.style.top = Math.min(theobject.top + window.event.clientY - theobject.graby, theobject.top + theobject.height - yMin) + "px";
			theobject.el.style.height = Math.max(yMin, theobject.height - window.event.clientY + theobject.graby) + "px";
			if (theobject.el.id != "whole") {
			var broheight = parseInt(theobject.el.parentNode.style.height) - 4 -parseInt(theobject.el.style.height);
			theobject.el.previousSibling.style.height = broheight+"px";
			jisuan(theobject.el.previousSibling,1);
			};
			jisuan(theobject.el,1);
		}
		window.event.returnValue = false;
		window.event.cancelBubble = true;
	}
}

function jisuan(child,wh){
	//alert(child.id);
	if(child.id != "whole"){
		if (wh == 0) {//宽
			var bfb = parseInt(child.style.width) / parseInt(child.parentNode.style.width);//百分比
			bfb = Math.round(bfb*100);
			var kgb = parseInt(child.style.width) / parseInt(child.style.height); //宽高比
			kgb = Math.round(kgb*10);
			
	storgeHalfBili(child.id,100-bfb,bfb);
		//	shuqiefa(bfb,100-bfb,child.id);
			child.innerHTML = "宽:"+bfb+"%";
		}
		else{//高
			var bfb = parseInt(child.style.height) / parseInt(child.parentNode.style.height);
			bfb = Math.round(bfb*100);
			storgeHalfBili(child.id,100-bfb,bfb);
			//hengqiefa(bfb,100-bfb,child.id);
			child.innerHTML = "高:"+bfb+"%";
		}
	}
	else{
		var kgb =  400 / parseInt(child.style.height); //宽高比 固定高
		kgb = Math.round(kgb*10);
		storgeHalfBili(child.id,100-bfb,bfb);
		if(kgb < 20){		
		//hengqiefa(kgb,10,child.id);
		}
		quanjuHigh = kgb;
		child.innerHTML = "宽高比为"+kgb+":10";
	}
}

function getReal(el, type, value) {  //分析鼠标区域
	temp = el;
	while ((temp != null) && (temp.tagName != "BODY")) {
		if (eval("temp." + type) == value) { //如果是 类change ，返回此标签
			el = temp;
			return el;
		}
		temp = temp.parentElement;
	}
	return el;
}
document.onmousedown = doDown;
document.onmouseup   = doUp;
document.onmousemove = doMove;
</SCRIPT>
		</div>
		 
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>