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
<script
	src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
	
	<script src="js/jquery.js"></script>
<link href="css/custom.css" rel="stylesheet" type="text/css" media="all">
</head>


<body onload="ol()">
		<div class="mainbody">


		
		<div  style="margin: 10;position: absolute;right:100px;top:150px;width: 600px "  class="panel panel-default " >
		<div class="panel-heading" >
				<h3  style="display: inline;" class="panel-title">选项</h3>
				<a onclick="mol(this)" style="display: inline;"  >more</a>
				<input style="display: inline;" align="right" onclick="addtags()"  type="button" value="添加到标签"/>
				<input style="display: inline;" type="button" onclick="addkeywords()" value="添加到关键字"/>
			</div>
			<table id="les" style="width: 100% ">
				<tr>
					
					<td><input name='checkbox_tags' type="checkbox" value="569_亲，包邮！" /></td>
					<td>亲，包邮！</td>
					<td><input name='checkbox_tags' type="checkbox" value="909_天天9.9" /></td>
					<td>天天9.9</td>
					<td><input name='checkbox_tags' type="checkbox" value="863_淘金币当钱花" /></td>
					<td>淘金币当钱花</td>
					<td><input name='checkbox_tags' type="checkbox" value="606_退货无忧" /></td>
					<td>退货无忧</td>
					<td><input name='checkbox_tags' type="checkbox" value="613_天猫特卖" /></td>
					<td>天猫特卖</td>
					
				</tr>
				<tr>
					
					
					<td><input name='checkbox_tags' type="checkbox" value="601_优质精选" /></td>
					<td>优质精选</td>
					<td><input name='checkbox_tags' type="checkbox" value="614_随手专享" /></td>
					<td>随手专享</td>
					<td><input name='checkbox_tags' type="checkbox" value="622_超值女装" /></td>
					<td>超值女装</td>
					<td><input name='checkbox_tags' type="checkbox" value="615_尾品汇" /></td>
					<td>尾品汇</td>
					<td><input name='checkbox_tags' type="checkbox" value="713_天猫超市" /></td>
					<td>天猫超市</td>
					
				</tr>
			</table>
			</div>
			
			<%
			
			if(request.getAttribute("list2")!=null){
				List<String[]> l2=new ArrayList<String[]>();
				l2=(List<String[]>)request.getAttribute("list2");
			
			%>
			
			<div id="keyword" style="margin: 10;position: absolute;right:100px;top:250px;width: 600px "  class="panel panel-default " >
		<div class="panel-heading">
				<h3  style="display: inline;" class="panel-title">关键词</h3>
			</div>
			<table id="les" style="width: 100% ">
				<tr>
					<td id="td_k1" style="width: 33%">
						<select id="se_k1" onchange="k1(this)">
						<option></option>
						<%int i=0;for(int j=0;j<l2.size();j++){ String id=l2.get(j)[0];String name=l2.get(j)[1]; %>
						<option id="option_<%=id%>"><%=i+"_"+name %></option>
						<%i++;} %>
						</select>
					</td>
					<td id="td_k2" style="width: 33%"></td>
					<td id="td_k3" style="width: 33%"></td>
				</tr>
			</table>
			<table id="table_keywords">
			
			</table>
			</div>
			<%} %>
		
		<%
		if(request.getAttribute("list1")!=null){
			List<String[]> l1=new ArrayList<String[]>();
			l1=(List<String[]>)request.getAttribute("list1");
		%>
		<div id="more"  style="margin: 10;position: absolute;right:100px;top:250px;width: 600px;display: none "  class="panel panel-default " >
		<table  style="width: 100%">
		<%for(int i=0;i<l1.size();i++){ String key=l1.get(i)[0];String name=l1.get(i)[1];%>
		<tr>
			<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" /></td>
			<td id="<%=key %>"><%=name %></td>
			<%if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
			<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" /></td>
			<td id="<%=key %>"><%=name %></td>
			<%}if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
			<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" /></td>
			<td id="<%=key %>"><%=name %></td>
			<%}if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
			<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" /></td>
			<td id="<%=key %>"><%=name %></td>
			<%} %>
		</tr>
		<%} %>
		</table>
		</div>
		<%} %>
		
	</div>
			
			
		
</body>

<script type="text/javascript">

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
	
	var tags= document.getElementsByName("checkbox_tags");
	var itemval="{'item_list':[<%=request.getAttribute("itemval")%>]}";
	alert(itemval);
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
	var itemval="{\"item_list\":[<%=request.getAttribute("itemval")%>]}";
	
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
			  htm=htm+"<tr>"+"<td><input name='checkbox_keywords' type='checkbox' value='"+id+"_"+name+"'/>"+tmp+"</td>"+"</tr>";
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
		
		  var url="<%=request.getContextPath()%>/update?yes=update&id="+id;
		  window.open(url);
	}
</script>
</html>