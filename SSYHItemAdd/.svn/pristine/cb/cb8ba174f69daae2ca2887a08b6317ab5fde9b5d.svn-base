<%@page import="cn.youhui.platform.db.DBManager"%>
<%@page import="cn.youhui.itemadd.dataadapter.BaseAdapter"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>商品管理后台</title>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
	
	<script src="js/jquery.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/WdatePicker.js"></script>
	<link href="<%=request.getContextPath() %>/css/datepicker.css" rel="stylesheet" type="text/css" media="all">
<link href="css/custom.css" rel="stylesheet" type="text/css" media="all">
</head>

<%
int pagNow=1;
if(request.getParameter("pagNow")!=null&&!"".equals(request.getParameter("pagNow"))){
	pagNow=Integer.parseInt(request.getParameter("pagNow"));
}
int totalPage=DBManager.getTotalPage();
int xx=20;
if(totalPage%20==0){
	totalPage=totalPage/xx;
}else{
	totalPage=totalPage/xx+1;
}
String tabId = request.getParameter("tabId");
String menuId = request.getParameter("menuId");
int tid = 1;
int mid = 1;
	if(tabId!=null&&!"".equals(tabId)){
		tid = Integer.parseInt(tabId);
	}

	if(menuId!=null&&!"".equals(menuId)){
		mid = Integer.parseInt(menuId);
	}

	BaseAdapter ba = new BaseAdapter(tid,mid);
%>

<body onload="ol()">
		<div class="top-panel">
			<div class="top-logo">
				<img class="logo-img" alt="" src="img/logo.jpg">
				<img class="title-img" alt="" src="img/title.jpg">
				<span class="title">商品管理系统</span>
				<div class="top-tool">
				</div>
				
			</div>
			<img class="right-img" alt="" src="img/top_right.jpg">
			<ul class="nav nav-tabs menu">
				<li><span class="span_50px"></span></li>
				<%=ba.getTab(request) %> 
			</ul>
		</div>
		<div class="mainbody">

		<div class="panel panel-default left-panel" >
			<div class="panel-heading">
				<h3 style="display: inline;" class="panel-title">菜单</h3>
				
			</div>
			<div class="panel-body">
				<ul class="nav nav-pills nav-stacked">
					<%=ba.getMenu() %>
				</ul>
			</div>
		</div>

		<div class="panel panel-default main-panel" style="width: 1610px">
			<div class="panel-heading">
				<h3 style="display: inline;" class="panel-title">菜单</h3>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%if(mid==5){ %>
				<input style="display: inline;" align="right" onclick="addtags()"  type="button" value="添加到标签"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input style="display: inline;" type="button" onclick="addkeywords()" value="添加到关键字"/>
				<%}else if(mid==1){ %>
<!-- 				<input style="display: inline;" type="button" onclick="crawl()" value="更新"/> -->
				<%} %>
			</div>
			<div class="panel-body" style="display: inline;position: relative;">
			<%if(mid==5){ %>
			<%=ba.getContent() %>
			<%}else if(mid==3){ %>
				<%=ba.getContent2(pagNow) %> 
				<%}else{ %>
				<%=ba.getContent3() %>
				<%} %>
			</div>
			
			<%String  url=request.getContextPath()+"/index.jsp?tabId=1&menuId=3";int pagNum=totalPage; if(mid==3){%>
			<div style="position: relative;text-align: center;">
			<ul class="pagination" style="">
			
			<%if(pagNow==1){ %>
			  	<li><a href="<%=url%>&pagNow=1">&laquo;</a></li>
			  <%}else{ %>
			  	<li><a href="<%=url%>&pagNow=<%=(pagNow-1)%>">&laquo;</a></li>
			  <%} %>
			<%int num=5;if(pagNum<5){num=pagNum;}if(pagNow-5<=0){
				for(int i=1;i<=num;i++){%>
				<%if(pagNow==i){ %>
				<li class="active"><a href="<%=url%>&pagNow=<%=i%>"><%=i %></a></li>
				<%}else{ %>
					<li><a href="<%=url%>&pagNow=<%=i%>"><%=i %></a></li>
				<%} %>
				
			<%}%>
			<li><a >...</a></li>
			<% }else if(pagNow+5>=pagNum){ %>
				<li ><a >...</a></li>
				<%for(int i=pagNum-5;i<=pagNum;i++){%>
				<%if(pagNow==i){ %>
				<li class="active"><a href="<%=url%>&pagNow=<%=i%>"><%=i %></a></li>
				<%}else{ %>
					<li><a href="<%=url%>&pagNow=<%=i%>"><%=i %></a></li>
				<%} %>
			<%}%>
			<%}else{ %>
				<li><a >...</a></li>
				<li ><a href="<%=url%>&pagNow=<%=pagNow-2%>"><%=pagNow-2%></a></li>
				<li ><a href="<%=url%>&pagNow=<%=pagNow-1%>"><%=pagNow-1%></a></li>
				<li class="active"><a href="<%=url%>&pagNow=<%=pagNow%>"><%=pagNow%></a></li>
				<li ><a href="<%=url%>&pagNow=<%=pagNow+1%>"><%=pagNow+1%></a></li>
				<li ><a href="<%=url%>&pagNow=<%=pagNow+2%>"><%=pagNow+2%></a></li>
				<li><a >...</a></li>
			<%} %>
			
			<%if(pagNow==pagNum){ %>
			  	<li><a href="<%=url%>&pagNow=<%=pagNum%>">&raquo;</a></li>
			  <%}else{ %>
			  	<li><a href="<%=url%>&pagNow=<%=(pagNow+1)%>">&raquo;</a></li>
			  <%} %>
			  
			</ul>
			<select onchange="sel(this)" style="position:absolute; left: 38%;top: 25px;">
			<%for(int i=1;i<=pagNum;i++){ %>
				<%if(pagNow==i){ %>
					<option selected="selected"><%=i %></option>
				<%}else{ %>
					<option><%=i %></option>
				<%} %>
			<%} %>
			</select>
			</div>
			<%} %>
			
		</div>
	</div>
			
			
		
</body>

<script type="text/javascript">

function sel(a){
	var pagNow=a.value;
	window.location.href="<%=url%>&pagNow="+pagNow;
}

function del2(itemId){
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText=="paramException"){
			  alert("参数异常");
		  }else if(xmlhttp.responseText=="fail"){
			  alert("失败");
		  }else if(xmlhttp.responseText=="success"){
			 window.location.reload();
		  }
	    }
	  }
	  var url="<%=request.getContextPath()%>/deltkh?itemId="+itemId;
	  
	  if(confirm("确认删除？")){
		  xmlhttp.open("GET",url,true);
			xmlhttp.send();
	  }
}

function del(itemId){
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(xmlhttp.responseText=="paramException"){
			  alert("参数异常");
		  }else if(xmlhttp.responseText=="fail"){
			  alert("失败");
		  }else if(xmlhttp.responseText=="success"){
			 window.location.reload();
		  }
	    }
	  }
	  var url="<%=request.getContextPath()%>/deltkh?itemId="+itemId;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function crawl(){
	var itemIds="";
	var itemIdsElement=document.getElementsByName("hid_itemId");
	for(var i=0;i<itemIdsElement.length;i++){
		var itemid=itemIdsElement[i].value;
		
		
		$.getJSON("http://pub.alimama.com/pubauc/searchAuctionList.json?q=http://item.taobao.com/item.htm?id="+itemid+"&callback=?", function(json) {
	        alert(json);
		});
		
		
	    $.ajax({ 
            async:false, 
            url: 'http://pub.alimama.com/pubauc/searchAuctionList.json?q=http://item.taobao.com/item.htm?id='+itemid,  // 跨域URL
            type: 'GET', 
            dataType: 'jsonp', 
            jsonp: 'jsoncallback', //默认callback
           // data: mydata, 
            timeout: 5000, 
            beforeSend: function(){  //jsonp 方式此方法不被触发。原因可能是dataType如果指定为jsonp的话，就已经不是ajax事件了
            },
            success: function (json) { //客户端jquery预先定义好的callback函数，成功获取跨域服务器上的json数据后，会动态执行这个callback函数 
                if(json.actionErrors.length!=0){ 
                    alert(json.actionErrors); 
                } 
                alert(json);
            },
            complete: function(XMLHttpRequest, textStatus){ 
                $.unblockUI({ fadeOut: 10 }); 
            }, 
            error: function(xhr){ 
                //jsonp 方式此方法不被触发
                //请求出错处理 
                alert("请求出错(请检查相关度网络状况.)"); 
            }
        });
		
		if(i==itemIdsElement.length-1){
			itemIds=itemIds+itemid;
		}else{
			itemIds=itemIds+itemid+",";
		}
	}
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var val=xmlhttp.responseText;
		  alert("成功更新了 "+val+" 条");
	    }
	  }
	  var url="<%=request.getContextPath()%>/crawl?itemIds="+itemIds;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function addtags(){
	var items= document.getElementsByName("checkbox_item");
	var tags= document.getElementsByName("checkbox_tags");
	var itemval="{\"item_list\":[";
	var tmp="";
	for(var i=0;i<items.length;i++){
		if(items[i].checked){
				tmp=tmp+items[i].value+",";
		}
	}
	if(tmp.length>0){
		tmp=tmp.substring(0,tmp.length-1);
	}
	itemval=itemval+tmp+"]}";
	
	var tagsval="";
	for(var i=0;i<tags.length;i++){
		if(tags[i].checked){
				tagsval=tagsval+tags[i].value+",";
		}
	}
	if(tagsval.length>0){
		tagsval=tagsval.substring(0,tagsval.length-1);
	}
	var url="http://10.0.0.21:8080/Youhui/addcategoryitems?type=tagItem&id="+tagsval+"&content="+itemval;
// 	$(function(){
// 		$.getJSON("http://10.0.0.21:8080/Youhui/addcategoryitems?jsoncallback=?", { type:'tagItem', id: tagsval,content:itemval }, function(json) {
// 	        alert(json);
// 		});
// 	});
	
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 if(xmlhttp.responseText=="paramException"){
			 alert("参数异常");
		 }else if(xmlhttp.responseText=="fail"){
			  alert("失败");
		  }else{
			  alert("成功 "+xmlhttp.responseText+" 条！");
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="url="+"http://10.0.0.21:8080/Youhui/addcategoryitems?type=tagItem"+"&id="+tagsval+"&content="+itemval;
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}
function addkeywords(){
	var items= document.getElementsByName("checkbox_item");
	var keywords= document.getElementsByName("checkbox_keywords");
	var itemval="{\"item_list\":[";
	var tmp="";
	for(var i=0;i<items.length;i++){
		if(items[i].checked){
				tmp=tmp+items[i].value+",";
		}
	}
	if(tmp.length>0){
		tmp=tmp.substring(0,tmp.length-1);
	}
	itemval=itemval+tmp+"]}";
	
	var keywordsval="";
	for(var i=0;i<keywords.length;i++){
		if(keywords[i].checked){
			keywordsval=keywordsval+keywords[i].value+",";
		}
	}
	if(keywordsval.length>0){
		keywordsval=keywordsval.substring(0,keywordsval.length-1);
	}
	var url="http://10.0.0.21:8080/Youhui/addcategoryitems?type=keywordItem&id="+keywordsval+"&content="+itemval;
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 if(xmlhttp.responseText=="paramException"){
			 alert("参数异常");
		 }else if(xmlhttp.responseText=="fail"){
			  alert("失败");
		  }else{
			  for(var i=0;i<items.length;i++){
					if(items[i].checked){
							document.getElementById("ul_item").removeChild(items[i].parentNode.parentNode.parentNode);
					}
				}
			  alert("成功 "+xmlhttp.responseText+" 条！");
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="url="+"http://10.0.0.21:8080/Youhui/addcategoryitems?type=keywordItem"+"&id="+keywordsval+"&content="+itemval;
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}
function k1(a){
	var k_1=a.value.split("_")[0];
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var val=xmlhttp.responseText;
		  var htm="<select id='se_k2' onchange='k2(this)'><option></option>";
		  for(var i=0;i<val.split("/").length;i++){
			  var id=val.split("/")[i].split("_")[0];
			  var name=val.split("/")[i].split("_")[1];
			  var tmp=i+"_"+name;
			  if(i<val.split("/").length-1){
			 	 htm=htm+"<option>"+tmp+"</option>";
			  }
		  }
		  htm=htm+"</select>";
		  document.getElementById("td_k2").innerHTML=htm;
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+k_1;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function k2(a){
	var k_1=document.getElementById("se_k1").value.split("_")[0];
	var k_2=a.value.split("_")[0];
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var val=xmlhttp.responseText;
		  var htm="";
		  var arrayObj = new Array();
		  for(var i=0;i<val.split("/").length;i++){
			  var id=val.split("/")[i].split("_")[0];
			  var name=val.split("/")[i].split("_")[1];
			  var tmp=i+"_"+name;
			  htm=htm+"<tr>"+"<td><input name='checkbox_keywords' type='checkbox' value="+id+"/>"+tmp+"</td>"+"</tr>";
		  }
		  document.getElementById("table_keywords").innerHTML=htm;
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+k_1+"&k_2="+k_2;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function k3(a){
	var k_1=document.getElementById("td_k1").value.split("_")[0];
	var k_2=document.getElementById("td_k2").value.split("_")[0];
	var k_3=a.value.split("_")[0];
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  alert(xmlhttp.responseText)
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+k_1+"&k_2="+k_2+"&k_3="+k_3;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function mol(a){
	var table=document.getElementById("more");
	if(table.style.display=="none"){
		table.style.display="";
		a.innerHTML="less";
		var h=parseInt(document.getElementById("keyword").style.top.split("px")[0])+970+"px";
		document.getElementById("keyword").style.top=h;
	}else{
		table.style.display="none";
		a.innerHTML="more";
		var h=parseInt(document.getElementById("keyword").style.top.split("px")[0])-970+"px";
		document.getElementById("keyword").style.top=h;
	}
	
}


	function changeRecom(a,id){
		var val=a.value;
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  if(xmlhttp.responseText=="paramException"){
				  alert("参数异常");
			  }else if(xmlhttp.responseText=="fail"){
				  alert("失败");
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/update?yes=recom&val="+val+"&id="+id;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	function changeRemark(a,id){
		var val=a.value;
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  if(xmlhttp.responseText=="paramException"){
				  alert("参数异常");
			  }else if(xmlhttp.responseText=="fail"){
				  alert("失败");
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/update?yes=remark&val="+val+"&id="+id;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	
	function update(id){
		  var url="<%=request.getContextPath()%>/update?yes=update&id="+id+"&pagNow="+<%=pagNow%>;
		  window.location.href=(url);
	}
	
	function ol(){
		document.body.scrollTop="1000px";
		document.documentElement.scrollTop="1000px";
	}
	
	function selChange(a){
		
		date="";
		if(document.getElementById("date").value!=""){
			date=document.getElementById("date").value;
		}
		
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  if(xmlhttp.responseText=="paramException"){
				  alert("参数异常");
			  }else if(xmlhttp.responseText=="fail"){
				  alert("失败");
			  }else {
				  var num=xmlhttp.responseText;
				  document.getElementById("lii").innerHTML=num;
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/addNum?username="+a.value+"&date="+date;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	
</script>
</html>