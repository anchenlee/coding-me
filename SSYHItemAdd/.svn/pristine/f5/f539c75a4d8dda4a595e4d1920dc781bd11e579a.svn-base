<%@page import="cn.youhui.itemadd.dataadapter.BaseAdapter"%>
<%@page import="java.util.*" %>
<%@page import="cn.youhui.bean.Searchkeyword" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="content-script-type" content="text/javascript">
<title>商品管理后台</title>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
	
	<link rel="stylesheet" href="/css/custom2.css">

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom2.css">
</head>

<% 
String tabId = request.getParameter("tabId");
String menuId = request.getParameter("menuId");
String itemId="";
String titleOrRecom="";
int pagNow=1;
if(request.getParameter("pagNow")!=null&&!"".equals(request.getParameter("pagNow"))){
	if(Integer.parseInt(request.getParameter("pagNow"))>1){
		pagNow=Integer.parseInt(request.getParameter("pagNow"));
	}
}
if(request.getParameter("itemId")!=null&&!"".equals(request.getParameter("itemId"))){
	itemId=request.getParameter("itemId");
}
if(request.getParameter("titleOrRecom")!=null&&!"".equals(request.getParameter("titleOrRecom"))){
	titleOrRecom=request.getParameter("titleOrRecom");
}
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

		<div class="panel panel-default main-panel">
			<div class="panel-heading">
				<h3 style="display: inline;" class="panel-title">菜单</h3>
				<input type="button" value="添加" class="btn btn-default"  onclick="tj()" style="position:relative;" align="right" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="search" type="text" /><input type="button" style="margin: 0 2px 0 4px" class="btn btn-default" onclick="searchByItemId()"  value="按商品id搜索"/><input type="button" style="margin: 0 0 0 2px" class="btn btn-default"  onclick="searchByItemTitleOrRecom()"  value="按商品标题或者推荐语搜索"/>
			</div>
			<div class="panel-body" style="position: relative;">
			<%if(mid==5){ ba.tmp(request);%>
			<%=ba.getContentAlready(itemId,titleOrRecom,pagNow,request) %>
			<%}else if(mid==3){ %>
				<%}else{ %>
				<%=ba.getContent3() %>
				<%} %>
			</div>
			
			<%int pagNum=(Integer)request.getAttribute("pagNum"); String url=request.getContextPath()+"/oready.jsp?tabId=1&menuId=5"+"&itemId="+itemId+"&titleOrRecom="+request.getAttribute("titleOrRecom"); %>
			<div>
			<ul class="pagination" style="float: right;">
			
			
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
			<select onchange="sel(this)" style="float: right;position: relative; right: 10px;top: 25px;">
			<%for(int i=1;i<=pagNum;i++){ %>
				<%if(pagNow==i){ %>
					<option selected="selected"><%=i %></option>
				<%}else{ %>
					<option><%=i %></option>
				<%} %>
			<%} %>
			</select>
			</div>
		</div>
		
	</div>
			
			
	
	<!-- /.modal start-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content" style="width: 750px">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">选项</h4>
		      </div>
		      <div class="modal-body">
		        

						<div class="mainbody" >
							<div  style="margin: 10;right:100px;top:150px;width: 600px; "  class="panel panel-default " >
							<div class="panel-heading" >
									<h3  style="display: inline;" class="panel-title">选项</h3>
									<a onclick="mol(this)" style="display: inline;"  >more</a>
<!-- 									<input style="display: inline;" align="right" onclick="addtags()"  type="button" value="添加到标签"/> -->
<!-- 									<input style="display: inline;" type="button" onclick="addkeywords()" value="添加到关键字"/> -->
<!-- 									<input style="display: inline;" type="button" onclick="addHome()" value="添加到主页"/> -->
								</div>
								<table id="les" style="width: 100% ">
									<tr>
										
										<td><input name='checkbox_tags' type="checkbox" value="569_亲，包邮！" onchange="tag_change(569,this)" /></td>
										<td>亲，包邮！</td>
										<td><input name='checkbox_tags' type="checkbox" value="909_天天9.9" onchange="tag_change(909,this)" /></td>
										<td>天天9.9</td>
										<td><input name='checkbox_tags' type="checkbox" value="863_淘金币当钱花" onchange="tag_change(863,this)" /></td>
										<td>淘金币当钱花</td>
										<td><input name='checkbox_tags' type="checkbox" value="606_退货无忧" onchange="tag_change(606,this)" /></td>
										<td>退货无忧</td>
										<td><input name='checkbox_tags' type="checkbox" value="613_天猫特卖" onchange="tag_change(613,this)" /></td>
										<td>天猫特卖</td>
										
									</tr>
									<tr>
										
										
<!-- 										<td><input name='checkbox_tags' type="checkbox" value="601_优质精选" onchange="tag_change(610,this)" /></td> -->
<!-- 										<td>优质精选</td> -->
										<td><input name='checkbox_tags' type="checkbox" value="614_随手专享" onchange="tag_change(614,this)" /></td>
										<td>随手专享</td>
<!-- 										<td><input name='checkbox_tags' type="checkbox" value="622_超值女装" onchange="tag_change(622,this)" /></td> -->
<!-- 										<td>超值女装</td> -->
<!-- 										<td><input name='checkbox_tags' type="checkbox" value="615_尾品汇" onchange="tag_change(615,this)" /></td> -->
<!-- 										<td>尾品汇</td> -->
										<td><input name='checkbox_tags' type="checkbox" value="713_天猫超市" onchange="tag_change(713,this)" /></td>
										<td>天猫超市</td>
										<td><input name='checkbox_tags' type="checkbox" value="1017_女鞋" onchange="tag_change(1017,this)" /></td>
										<td>女鞋</td>
										<td><input name='checkbox_tags' type="checkbox" value="1015_化妆品" onchange="tag_change(1015,this)" /></td>
										<td>化妆品</td>
										<td><input name='checkbox_tags' type="checkbox" value="1013_男士" onchange="tag_change(1013,this)" /></td>
										<td>男士</td>
										<td><input name='checkbox_tags' type="checkbox" value="1011_女装" onchange="tag_change(1011,this)" /></td>
										<td>女装</td>
										
										
									</tr>
								</table>
								</div>
								
								
									<%
							if(request.getAttribute("list1")!=null){
								List<String[]> l1=new ArrayList<String[]>();
								l1=(List<String[]>)request.getAttribute("list1");
							%>
							<div id="more"  style="margin: 10;right:100px;top:250px;width: 600px;display: none "  class="panel panel-default " >
							<table  style="width: 100%">
							<%for(int i=0;i<l1.size();i++){ String key=l1.get(i)[0];String name=l1.get(i)[1];%>
							<tr>
								<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" onchange="tag_change(<%=key %>,this)" /></td>
								<td id="<%=key %>"><%=name %></td>
								<%if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
								<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" onchange="tag_change(<%=key %>,this)"/></td>
								<td id="<%=key %>"><%=name %></td>
								<%}if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
								<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" onchange="tag_change(<%=key %>,this)"/></td>
								<td id="<%=key %>"><%=name %></td>
								<%}if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
								<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" onchange="tag_change(<%=key %>,this)"/></td>
								<td id="<%=key %>"><%=name %></td>
								<%} %>
							</tr>
							<%} %>
							</table>
							</div>
							<%} %>
								
								
								<%	if(request.getAttribute("list3")!=null){ 
										List<Searchkeyword> l3=(List<Searchkeyword>)request.getAttribute("list3");
								%>
								<div id="zk_keyword" style="margin: 10;right:100px;top:250px;width: 600px "  class="panel panel-default " >
									<div class="panel-heading" id="h3s">
										<h3 id="h3_1"  style="display: inline;" class="panel-title" >自定义关键词      </h3>
										<input type="text" id="zk_val" /><input type="button" onclick="zk_insert()" value="插入"/>
										<input type="button" onclick="zk_search	(this)" value="搜索"/>
										<input type="button" onclick="zk_hide(this)" name="0" value="↓"/>
									</div>
									<table id="zk_table">
												<tr style="width:598px;display: block;display: none;">
										<%for(int j=0;j<l3.size();j++){String id=l3.get(j).getId(); String name=l3.get(j).getName(); %>
											<td style="display: inline-block;"><input name='checkbox_keywords' type='checkbox' value='<%=id%>_<%=name%>'/><%=name %></td>
										<%}%>
										</tr>
									</table>
								</div>
								<%} %>
								
								<%
								
								if(request.getAttribute("list2")!=null){
									List<String[]> l2=new ArrayList<String[]>();
									l2=(List<String[]>)request.getAttribute("list2");
								
								%>
								
								<div id="keyword" style="margin: 10;right:100px;top:250px;width: 600px "  class="panel panel-default " >
							<div class="panel-heading" id="h3s">
									<h3 id="h3_1"  style="display: inline;" class="panel-title" onclick="h3_1()">关键词</h3>
								</div>
								
								<table id="table_keywords1" style="width: 100% ">
									<%for(int j=0;j<l2.size();j++){ %>
										<tr>
										<%for(int z=0;z<5;z++){if(j<l2.size()){ String id=l2.get(j)[0];String name=l2.get(j)[1];  %>
											<td id="td_<%=id%>"><a onclick="key1('<%=id%>','<%=name%>')"><%=name %></a></td>
										<%j++;}} %>
										
										</tr>
									<%} %>
<!-- 									<tr> -->
<!-- 										<td id="td_k1" style="width: 33%"> -->
<!-- 											<select id="se_k1" onchange="k1(this)"> -->
<!-- 											<option></option> -->
<%-- 											<%int i=0;for(int j=0;j<l2.size();j++){ String id=l2.get(j)[0];String name=l2.get(j)[1]; %> --%>
<%-- 											<option id="option_<%=id%>"><%=i+"_"+name %></option> --%>
<%-- 											<%i++;} %> --%>
<!-- 											</select> -->
<!-- 										</td> -->
<!-- 										<td id="td_k2" style="width: 33%"></td> -->
<!-- 										<td id="td_k3" style="width: 33%"></td> -->
<!-- 									</tr> -->
								</table>
								
								<table id="table_keywords2" style="width: 100%;display: none;" >
								</table>
								
								<table id="table_keywords3">
								
								</table>
								</div>
								<%} %>
							
						<div  style="margin: 10;right:100px;top:250px;width: 600px "  class="panel panel-default " >
							<div class="panel-heading" id="h3s">
									<h3 id="h3_1"  style="display: inline;" class="panel-title" onclick="h3_1()">主页</h3>
							</div>
							<table>
							<tr>
								<td><input id="home" type="checkbox"  />主页</td>
							</tr>
							</table>
						</div>
							
						</div>

		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary" onclick="addAll()">添加</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		
		
</body>

<script type="text/javascript">

function zk_hide(obj){
	if($(obj).attr('name') == 0){
		$(obj).parents('#zk_keyword').find('tr').show();
		$(obj).val('↑');
		$(obj).attr('name',1);
	}else{
		$(obj).parents('#zk_keyword').find('tr').hide();
		$(obj).val('↓');
		$(obj).attr('name',0);
	}
}

function zk_search(obj){
	$(obj).parents('#zk_keyword').find('tr').show();
	var text = $(obj).prevAll('#zk_val').val();
	$(obj).parents('#zk_keyword').find('td').each(function(){
		if(text != ''){
			if($(this).text().indexOf(text) >= 0){
				$(this).css('color','red');
			}else{
				$(this).css('color','#000');
			}
		}
	})
	$(obj).next().val('↑');
	$(obj).next().attr('name',1);
}

function zk_insert(){
	var val=document.getElementById("zk_val").value;
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
		  
		  var id=xmlhttp.responseText;
		  if(id=="paramException"){
			alert("参数异常");
		  }else if(id=="fail"){
			alert("失败");
		  }else{
			  var newNode=document.createElement("tr");
			  newNode.innerHTML="<td><input name='checkbox_keywords' type='checkbox' value='"+id+"_"+val+"'/>"+val+"</td>";
			  document.getElementById("zk_table").appendChild(newNode);
		  }
	    }
	  }
	  var url="<%=request.getContextPath()%>/zkKeyword?yes=insert&val="+val;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

String.prototype.replaceAll = function(s1,s2) {
	return this.replace(new RegExp(s1,"gm"),s2); 
   }
var tt=0;
var kk=0;
var hh=0;


function tag_change(tagId,a){
	var tt=document.getElementsByName("tr_"+tagId);
	if(a.checked&&tt.length==0){
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
			  if(val!="empty"){
				 $("#les").append(val);
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/GetSonOfTag?tagId="+tagId;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
}

function sel(a){
	var pagNow=a.value;
	window.location.href="<%=url%>&pagNow="+pagNow;
}

function ol(){
// 	$(function(){
// 		$.getJSON("http://10.0.0.21:8080/testapi/test?callback=?", function(json) {
// 	        alert(json);
// 		});
// 	});
}
function back(a){
	alert(a.content);
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

function addAll(){
	addtags();
	addkeywords();
	addHome();
}

// function addAll(){
// 	var items= document.getElementsByName("checkbox_item");
// 	var tags= document.getElementsByName("checkbox_tags");
// 	var keywords= document.getElementsByName("checkbox_keywords");
// 	var home=document.getElementById("home");
// 	var homeval=0;
// 	if(home.checked){
// 		homeval=1;
// 	}
// 	var itemval="{\"item_list\":[";
// 	var tmp="";
// 	for(var i=0;i<items.length;i++){
// 		if(items[i].checked){
// 				tmp=tmp+items[i].value+",";
// 		}
// 	}
// 	if(tmp.length>0){
// 		tmp=tmp.substring(0,tmp.length-1);
// 	}
// 	itemval=itemval+tmp+"]}";
	
// 	var tagsval="";
// 	for(var i=0;i<tags.length;i++){
// 		if(tags[i].checked){
// 				tagsval=tagsval+tags[i].value+",";
// 		}
// 	}
// 	if(tagsval.length>0){
// 		tagsval=tagsval.substring(0,tagsval.length-1);
// 	}
	
// 	var keywordsval="";
// 	for(var i=0;i<keywords.length;i++){
// 		if(keywords[i].checked){
// 			keywordsval=keywordsval+keywords[i].value+",";
// 		}
// 	}
// 	if(keywordsval.length>0){
// 		keywordsval=keywordsval.substring(0,keywordsval.length-1);
// 	}
	
// 	var xmlhttp;
// 	if (window.XMLHttpRequest)
// 	  {// code for IE7+, Firefox, Chrome, Opera, Safari
// 	  xmlhttp=new XMLHttpRequest();
// 	  }
// 	else
// 	  {// code for IE6, IE5
// 	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
// 	  }
// 	  xmlhttp.onreadystatechange=function()
// 	  {
// 	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
// 	    {
// 		 if(xmlhttp.responseText=="paramException"){
// 			 alert("参数异常");
// 		 }
// 		 else if(xmlhttp.responseText=="fail"){
// 		  }
// 		 else{
// 			 // alert("成功 "+xmlhttp.responseText+" 条！");
// 			  tt=xmlhttp.responseText;
// 		  }
// 	    }
// 	  }
<%-- 	  var url2="<%=request.getContextPath()%>/addtkh"; --%>
// 	  var param="tagsval="+tagsval+"&keywordsval="+keywordsval+"&homeval="+homeval+"&content="+itemval;
// 		xmlhttp.open("POST",url2,true);
// 		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
// 		xmlhttp.send(param);
// }

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
		 }
		 else if(xmlhttp.responseText=="fail"){
		  }
		 else{
			 // alert("成功 "+xmlhttp.responseText+" 条！");
			  tt=xmlhttp.responseText;
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="type=tagItem"+"&id="+tagsval+"&content="+itemval;
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
		 }
		 else if(xmlhttp.responseText=="fail"){
		  }
		 else{
			  for(var i=0;i<items.length;i++){
					if(items[i].checked){
							document.getElementById("ul_item").removeChild(items[i].parentNode.parentNode.parentNode);
					}
				}
//  			  alert("成功 "+xmlhttp.responseText+" 条！");
			  kk=xmlhttp.responseText;
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="type=keywordItem"+"&id="+keywordsval+"&content="+itemval;
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}

function addHome(){
	var items= document.getElementsByName("checkbox_item");
	var home=document.getElementById("home");
	if(!home.checked){
// 		alert("成功添加了"+tt+"条到主题，"+kk+"条到关键字"+hh+"条到主页！");
		window.location.reload();
		return;
	}
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
		 }
		 else if(xmlhttp.responseText=="fail"){
		  }
		 else{
			  hh=xmlhttp.responseText;
// 			  alert("成功添加了"+tt+"条到主题，"+kk+"条到关键字"+hh+"条到主页！");
			  window.location.reload();
// 			  alert("成功!");
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="type=recoItem"+"&content="+itemval+"&id=0";
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}

function h3_1(){
	if(document.getElementById("h3_2")!=null){
		document.getElementById("h3s").removeChild(document.getElementById("h3_2"));
	}
	if(document.getElementById("h3_3")!=null){
		document.getElementById("h3s").removeChild(document.getElementById("h3_3"));
	}
	document.getElementById("table_keywords2").style.display="none";
	document.getElementById("table_keywords3").style.display="none";
	document.getElementById("table_keywords1").style.display="";
}

function h3_2(){
	if(document.getElementById("h3_3")!=null){
		document.getElementById("h3s").removeChild(document.getElementById("h3_3"));
	}
	document.getElementById("table_keywords1").style.display="none";
	document.getElementById("table_keywords3").style.display="none";
	document.getElementById("table_keywords2").style.display="";
}

function key1(id1,name2){
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
		  for(var i=0;i<val.split("/").length;i++){
			  
			  for(var z=0;z<5;z++){
				  if(i<val.split("/").length-1){
					  var id=val.split("/")[i].split("_")[0];
					  var name=val.split("/")[i].split("_")[1];
					  htm=htm+"<td><a onclick=\"key2('"+id1+"','"+id+"','"+name+"')\">"+name+"</a></td>";
					  i++;
				  }
			  }
			  
		  }
		  document.getElementById("table_keywords2").innerHTML=htm;
		  document.getElementById("table_keywords1").style.display="none";
		  $("#h3_1").after("<h3 id=\"h3_2\"  style=\"display: inline;\" class=\"panel-title\" onclick=\"h3_2()\">>>"+name2+"</h3>");
		  document.getElementById("table_keywords2").style.display="";
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+id1;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
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


function key2(k_1,k_2,name2){
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
		  for(var i=0;i<val.split("/").length;i++){
			  var id=val.split("/")[i].split("_")[0];
			  var name=val.split("/")[i].split("_")[1];
			  var tmp=id+"_"+name;
			  htm=htm+"<tr>"+"<td><input name='checkbox_keywords' type='checkbox' value="+tmp+">"+name+"</td>"+"</tr>";
		  }
		  document.getElementById("table_keywords3").innerHTML=htm;
		  document.getElementById("table_keywords1").style.display="none";
		  document.getElementById("table_keywords2").style.display="none";
		  $("#h3_2").after("<h3 id=\"h3_3\"  style=\"display: inline;\" class=\"panel-title\">>>"+name2+"</h3>");
		  document.getElementById("table_keywords3").style.display="";
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+k_1+"&k_2="+k_2;
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
		  for(var i=0;i<val.split("/").length;i++){
			  var id=val.split("/")[i].split("_")[0];
			  var name=val.split("/")[i].split("_")[1];
			  var tmp=id+"_"+name;
			  htm=htm+"<tr>"+"<td><input name='checkbox_keywords' type='checkbox' value="+tmp+">"+name+"</td>"+"</tr>";
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
	
	function tj(){
		
		var items=document.getElementsByName("checkbox_item");
		var tmp=0;
		for(var i=0;i<items.length;i++){
			if(items[i].checked){
				tmp=1;
			}
		}
		if(tmp==0){
			alert("请选择商品！");
			return;
		}
		for(var i=0;i<document.getElementsByName("checkbox_tags").length;i++){
			document.getElementsByName("checkbox_tags")[i].checked=false;
		}
		for(var i=0;i<document.getElementsByName("fuck").length;i++){
			document.getElementById("les").childNodes[0].removeChild(document.getElementsByName("fuck")[i]);
		}
		
		$('#myModal').modal('show');
	}
	
	
	function delTok(id,a,type,itemId){
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
				  alert(1);
			  }else if(xmlhttp.responseText=="success"){
				  $(a).parent().remove();
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/deltok?id="+id+"&type="+type+"&itemId="+itemId;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	
	function del(itemId,a){
		
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
				  var li=document.getElementById(a);
					document.getElementById("ul_item").removeChild(li);
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/deltkh?itemId="+itemId;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	
	function update(itemId){
		var url="<%=request.getContextPath()%>/updateForOready?yes=update&itemId="+itemId;
		  window.location.href=url;
	}
	
	function searchByItemId(){
		var itemId=document.getElementById("search").value;
		if(itemId.replaceAll(" ","")==""){
			alert("请填写商品id！");
		}
		var url="<%=request.getContextPath()%>/oready.jsp?tabId=1&menuId=5&itemId="+itemId;
		window.location.href=url;
	}
	function searchByItemTitleOrRecom(){
		var titleOrRecom=document.getElementById("search").value;
		if(titleOrRecom.replaceAll(" ","")==""){
			alert("请填写商品id！");
		}
		var url="<%=request.getContextPath()%>/oready.jsp?tabId=1&menuId=5&titleOrRecom="+titleOrRecom;
		url=encodeURI(url);
		window.location.href=url;
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