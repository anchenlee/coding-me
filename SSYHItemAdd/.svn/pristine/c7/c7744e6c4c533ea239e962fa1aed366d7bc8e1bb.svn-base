<%@page import="cn.youhui.itemadd.dataadapter.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="cn.youhui.bean.Searchkeyword" %>
<%@page import="cn.youhui.tools.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0058)http://localhost:8080/SSYHItemAdd/update?yes=update&id=167 -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改推荐商品-随手达人</title>
    
    
    <style type="text/css">
    .border{ border:1px solid #CCC; padding:2px;}
    #formbox p {
    	width: 400px;
    	margin-left: 15px;
    }
    </style>
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery.Jcrop.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery.Jcrop.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/recommend.css">
    
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

	

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
		
	<link href="<%=request.getContextPath() %>/css/custom.css" rel="stylesheet" type="text/css" media="all">
	<script src="<%=request.getContextPath()%>/js/jquery.Jcrop.js"></script>
    
    <script type="text/javascript">
    	showItemImg();
    	var default_img="";
	    jQuery(function initJr($)
	    {
	    	var previewImageWidth = 500;
	    	var consoleImageWidth = 500;
	        // Create variables (in this scope) to hold the API and image size
	        $pre_view = $('#item_preview'),
	        $('#item_img').Jcrop({
	          onChange: updatePreview,
	          onSelect: updatePreview,
	         // aspectRatio: xsize / ysize
	        },function(){
	          // Use the API to get the real image size
	          
	          //var bounds = this.getBounds();
	          // Store the API in the jcrop_api variable
	          //jcrop_api = this;
	
	          // Move the preview into the jcrop container for css positioning
	          //$preview.appendTo(jcrop_api.ui.holder);
	        });
	        
	        function updatePreview(c)
	        {
	         		//获取图片实际大小
	              	var newImg = new Image();
	              	newImg.src = $('#item_img')[0].src;
	              	var ratesize = newImg.width/consoleImageWidth;//实际放大倍数
	              	var rateviewsize = Math.max(c.w,c.h)/previewImageWidth;//预览需要放大倍数
	              
	             	var sizex = newImg.width/ratesize/rateviewsize;
	              	var sizey = newImg.height/ratesize/rateviewsize;
	              	
	              	var positionx = c.x/rateviewsize;
	              	var positiony = c.y/rateviewsize;
	        	  
	            	var w =c.w/rateviewsize;
	            	var h =c.h/rateviewsize;
	              
	            	$('#x1').val(parseInt(c.x*ratesize));
	     	   	 	$('#y1').val(parseInt(c.y*ratesize));
	     	   	 	$('#w').val(parseInt(c.w*ratesize));
	     	   	 	$('#h').val(parseInt(c.h*ratesize));
	             
	             	$pre_view.css("width",w+"px");
	             	$pre_view.css("height",h+"px");
	             	$pre_view.css("background-size",sizex+"px "+sizey+"px");
	             	$pre_view.css("background-position-x","-"+positionx+"px");
	             	$pre_view.css("background-position-y","-"+positiony+"px");
	        };
	      });
	    
	    function showItemImg()
	    {
	    	var itemOK = "";
	    	if (itemOK == "0")
	    	{
	    		/*
	    		var  item_img_url = "";
		    	var newImage = new Image;
	    		newImage.src = item_img_url;
	    		changeimg(newImage);
	    		*/
	    	}
	    	else if (itemOK == "1")
	    	{
	    		alert("商品已失效(下架或已不支持返利)!");
				var url = "/SSYHItemAdd/ad/profrecom_manager?actionmethod=showProfRecomList&page=";
				location.href=url;
	    	}
	    }
	    
	    function changeimg(tmpimg)
	    {
	    	$("#item_img").attr("src", tmpimg.src);
	    	$(".jcrop-holder img").attr("src", tmpimg.src);
	    	$("#img_tmp").val(tmpimg.src);
	    	$("#item_preview").css("background-image","url("+tmpimg.src+")");
	    }
    
	    function uploadImg(id)
	    {
	    	var x = $("#x1").val();
	    	var y = $("#y1").val();
	    	var w = $("#w").val();
	    	var h = $("#h").val();
	    	var img = document.getElementById("item_img").src;
	    	img=img.replace(/\+/g,"%2B");
	    	img=img.replace(/\&/g,"%26");
	    	
	    	if (img == null || img == "")
	    	{
	    		alert("图片为空，请重试！");
	    		return false;
	    	}
	    	
	    	if (x == null || x == "" || y == null || y == "" || w == null || w == "" || h == null || h == "")
	    	{
	    		alert("尚未划定图片，请重试！");
	    		return false;
	    	}
	    	
	    	$.ajax({
	    		async: true,
	    	    url: '<%=request.getContextPath()%>/update?yes=uploadProfRecomImg',
	    	    type: 'POST',
	    	    data: {"id":id,"x":x,"y":y,"w":w,"h":h,"img":img,ajaxflag:1},
	    	    success: function(json)
	    	    {
	    	    	alert(json);
	    	    }
	    	});
	    }
	    
		// 提交时检查参数
	    function checkParam(id)
		{
	    	var x = $("#x1").val();
	    	var y = $("#y1").val();
	    	var w = $("#w").val();
	    	var h = $("#h").val();
			if (x == null || x == "" || y == null || y == "" || w == null || w == "" || h == null || h == ""){
	    		
	    	}else{
	    		uploadImg(id);
	    	}
	    	
	    	var item_title=document.getElementById("item_title").value;
	    	var item_recom=document.getElementById("item_recom").value;
	    	var item_remark=document.getElementById("item_remark").value;
	    	
	    	item_title=item_title.replace(/\%/g,"%25");
	    	item_recom=item_recom.replace(/\%/g,"%25");
	    	item_remark=item_remark.replace(/\%/g,"%25");
	    	
	    	var item_rate=document.getElementById("item_rate").value;
	    	var item_zkPrice=document.getElementById("item_price_zk").value;
	    	
	    	if(item_title==""){
	    		alert("请填写标题！");
	    		return;
	    	}
	    	if(item_recom==""){
	    		alert("请填写推荐语！");
	    		return;
	    	}
	    	if(item_zkPrice==""){
	    		alert("折扣价格不能为空！");
	    		return;
	    	}
	    	if(item_rate==""){
	    		alert("返集分宝比例不能为空！");
	    		return;
	    	}
	    	
	    	var img = new Image();
	    	img.src =document.getElementById("item_img").src;
	    	var item_img=document.getElementById("item_img");
	    	var w=img.width;
	    	var h=img.height;
			
			if(document.getElementById("w").value!=""&&document.getElementById("w").value!="0"){
				w=document.getElementById("w").value;
			}
	    	if(document.getElementById("h").value!=""&&document.getElementById("h").value!="0"){
	    		h=document.getElementById("h").value;
	    	}
			var size=w+"x"+h;
			
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
	                     alert("参数异常！");
	                 }else if(xmlhttp.responseText=="fail"){
	                     alert("失败！");
	                 }else if(xmlhttp.responseText=="success"){
// 	                	 window.history.go(-1);
// 	                	 window.close();
// 						$('#myModal').modal('show');
						alert("编辑成功");
// 						window.opener.location.reload();
// 						window.close();
						window.history.back();
	                 }
	             }
	           }
	           var param="yes=updateInfo&id="+id+"&item_title="+item_title+"&item_recom="+item_recom+"&item_remark="+item_remark+"&rate="+item_rate+"&zk_price="+item_zkPrice+"&img_size="+size;
	             xmlhttp.open("POST","<%=request.getContextPath()%>/update",true);
	 			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 			xmlhttp.send(param);
		}
		
		
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width: 1300px; zoom: 1;">

				<div class="top-panel">
			<div class="top-logo">
				<img  class="logo-img" alt="" src="img/logo.jpg">
				<img class="title-img" alt="" src="img/title.jpg">
				<span class="title">商品管理系统</span>
				<div class="top-tool">
				</div>
				
			</div>
			<img class="right-img" alt="" src="img/top_right.jpg">
			<ul class="nav nav-tabs menu">
				<li><span class="span_50px"></span></li>
				<li class="active"><a href="http://10.0.0.21:8080/ssy/index.jsp?tabId=1">商品</a></li><li><a href="http://10.0.0.21:8080/ssy/index.jsp?tabId=3">主题</a></li>
			</ul>
		</div>

		<!-- /.modal start-->




	<div id="container" class="clear">
	<div style="margin:63px 0 0 50px;;" class="panel panel-default left-panel">
		<div class="panel-heading">
			<h3 class="panel-title">菜单</h3>
		</div>
		<div class="panel-body">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="<%=request.getContextPath() %>/index.jsp?tabId=1&menuId=1" title="">新添加商品</a></li>
				<li class="avtive"><a href="<%=request.getContextPath() %>/index.jsp?tabId=1&menuId=3" title="">已更新商品</a></li>
				<li><a href="<%=request.getContextPath() %>/oready.jsp?tabId=1&menuId=5" title="">已处理商品</a></li>		
			</ul>
		</div>
	</div>
    <div style="width:70%;margin-left:50px;float:left;" id="content">
         <div id="primary" class="hfeed">
         
		   <div id="post-8" class="post-8 page type-page status-publish hentry">
	         <h2 class="entry-title">&nbsp&nbsp修改商品</h2>
				<div id="submit-content">
							
                <div id="formbox" >
                		<input type="hidden" id="ProfRecomID" value="167">
                		<input type="hidden" id="img_result" name="img_result" value="">
                        <p class="input-group">
                        	<span class="input-group-addon"><font color="#777">店铺</font></span>
							<input type="text" name="item_id" class="form-control" id="item_id" disabled="disabled" value="${item.nick}" placeholder="  " />
                        </p>
                        <p class="input-group">
                             <span class="input-group-addon"><font color="#777">商品价格：</font></span>
                             <input type="text" name="item_price" class="form-control" id="item_price" value="${item.price }" disabled="disabled">
                        </p>
                        <p class="input-group">
                             <span class="input-group-addon"><font color="#777">商品折扣价格：</font></span>
                             <input type="text" name="item_price_zk" class="form-control" id="item_price_zk" value="${item.zkPrice }" >
                        </p>
                          <p class="input-group">
                             <span class="input-group-addon"><font color="#777">商品返集分宝比例：</font></span>
                             <input readonly="readonly" type="text" name="item_price_zk" class="form-control" id="item_rate" value="${item.rate }" >
                        </p>
                        <p class="input-group" style="width:505px;">
                             <span class="input-group-addon"><font color="#777">标题：</font></span>
                             <input type="text" name="item_title" class="form-control" id="item_title" value="${item.title }">
                             <a style="display: table-cell;vertical-align: middle;" href="javascript:window.open('ItemDetail.jsp?itemId=${item.itemid }');">查看详情</a>
                        </p>
                        <p class="input-group">
                             <span class="input-group-addon"><font color="#777">推荐：</font></span>
                             <input type="text" name="item_recom" class="form-control" id="item_recom" value="${item.recom }">
                        </p>
                       	<p class="input-group">
							<span class="input-group-addon"><font color="#777">备注：</font></span>
							<!-- <input type="text" name="reason" id="reason" value="" /> -->
							<textarea style="resize:none" name="item_remark" class="form-control" id="item_remark" rows="3">${item.remark}</textarea>
						</p>
						<p class="input-group">
								<span class="input-group-addon">图片尺寸：</span>
								<span class="input-group-addon">x</span><input class="form-control" type="text" size="4" id="x1" name="x1" style="width: 80px" disabled="disabled">
								<span class="input-group-addon">y</span><input class="form-control" type="text" size="4" id="y1" name="y1" style="width: 80px" disabled="disabled">
								<span class="input-group-addon">w</span><input class="form-control" type="text" size="4" id="w" name="w" style="width: 80px" disabled="disabled">
								<span class="input-group-addon">h</span><input class="form-control" type="text" size="4" id="h" name="h" style="width: 80px" disabled="disabled">
						</p>
						<p class="input-group">
						 	<input type="button" value="保存图片" class="button btn btn-primary" onclick="uploadImg(${item.id})">
							<input id="submit" name="submit" type="button" class="button btn btn-primary" style="margin-left:10px;"  onclick="checkParam(${item.id})" value="提交">
						 </p>
						<div id="img_container" >
							<div id="item_pic_left" style="float:left;width:100px;margin-top:10px;">
							<% List<String> list=Tools.getItemImgFromTaobao(((ItemBean)request.getAttribute("item")).itemid); for(int i=0;i<list.size();i++){String src=list.get(i); %>
								<img name="imgList" onclick="changeImg('<%=src %>',this)" style="width:100%;;margin-top:5px;"  src="<%=src%>">
							<%} %>
							</div>
							<div id="item_bigimg"  style="float:left" class ="imageview imageviewborder">
								<img id="item_img"  width="500px" src="${item.imgurl}" />
                            </div>
                           	<div id ="preview"  style="float:left" class="imageview">
                           		<div id="item_preview" class="imageviewborder"  style="margin:0 auto; top:-50%;width:500px;height:500px;background-repeat:no-repeat;background-image:url('${item.imgurl}')" ></div>
                           	</div>
                  		</div>
                </div>	 
				</div>
		
	  </div>
	 </div>
	</div>
	</div>
	
</body>
<script type="text/javascript">


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
	  var url="<%=request.getContextPath()%>/zkKeyword";
	  var param="yes=insert&val="+val;
	  xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}

	function changeImg(src,a){
		document.getElementById("item_img").src=src;
		
		document.getElementById("item_bigimg").children[1].children[3].src=src;
		document.getElementById("item_bigimg").children[1].children[0].children[0].children[0].src=src;
		document.getElementById("item_preview").style.backgroundImage="url("+src+")";
		for(var i=0;i<document.getElementsByName("imgList").length;i++){
			document.getElementsByName("imgList")[i].style.opacity=1;
		}
		a.style.opacity=0.5;
	}

	var tmp1_num=0;
	var tmp2_num=0;
	var tmp3_num=0;

function add(){
	addtags();
	addkeywords();
	addHome();
}

function addAll(){
	 
	var itemval="{'item_list':[<%=request.getAttribute("itemval")%>]}";
	var tags= document.getElementsByName("checkbox_tags");
	var keywords= document.getElementsByName("checkbox_keywords");
	var keywordsval="";
	for(var i=0;i<keywords.length;i++){
		if(keywords[i].checked){
			keywordsval=keywordsval+keywords[i].value.split("_")[0]+",";
		}
	}
	if(keywordsval.length>0){
		keywordsval=keywordsval.substring(0,keywordsval.length-1);
	}
	
	var tagsval="";
	for(var i=0;i<tags.length;i++){
		if(tags[i].checked){
				tagsval=tagsval+tags[i].value.split("_")[0]+",";
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
			  window.location.href="<%=request.getContextPath()%>/wait.jsp?tabId=1&menuId=7";
		  }
	    }
	  }
	  var homeval=0;
	  var home=document.getElementById("home");
		if(home.checked){
			homeval=1;
		}
		
	  var url2="<%=request.getContextPath()%>/wait";
	  var param="yes=insert&keywordsval="+keywordsval+"&tagsval="+tagsval+"&homeval="+homeval+"&content="+itemval;
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}

function addtags(){
	
	var tags= document.getElementsByName("checkbox_tags");
	var itemval="{'item_list':[<%=request.getAttribute("itemval")%>]}";
	itemval.replace(/\&/g,"%26");
	itemval.replace(/\+/g,"%2B");
	var tagsval="";
	for(var i=0;i<tags.length;i++){
		if(tags[i].checked){
				tmp1_num++;
				tagsval=tagsval+tags[i].value+",";
		}
	}
	if(tagsval.length>0){
		tagsval=tagsval.substring(0,tagsval.length-1);
	}
	if(tagsval==""){
		tmp1=1;
		return;
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
			  tmp1_num=xmlhttp.responseText;
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
	var itemval="{\"item_list\":[<%=request.getAttribute("itemval")%>]}";
	itemval.replace(/\&/g,"%26");
	itemval.replace(/\+/g,"%2B");
	var keywordsval="";
	for(var i=0;i<keywords.length;i++){
		if(keywords[i].checked){
			tmp2_num++;
			keywordsval=keywordsval+keywords[i].value+",";
		}
	}
	if(keywordsval.length>0){
		keywordsval=keywordsval.substring(0,keywordsval.length-1);
	}
	if(keywordsval==""){
		tmp2=1;
		return;
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
		 }
		 else if(xmlhttp.responseText=="fail"){
		  }
		 else{
			  tmp2_num=xmlhttp.responseText;
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
	var home=document.getElementById("home");
	if(!home.checked){
		alert("成功添加了"+tmp1_num+"条到主题，"+tmp2_num+"条到关键字"+tmp3_num+"条到主页！");
		return;
	}
	var itemval="{\"item_list\":[<%=request.getAttribute("itemval")%>]}";
	itemval.replace(/\&/g,"%26");
	itemval.replace(/\+/g,"%2B");
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
			  tmp3_num=xmlhttp.responseText;
			  alert("成功添加了"+tmp1_num+"条到主题，"+tmp2_num+"条到关键字"+tmp3_num+"条到主页！");
			  window.opener.location.reload();
			  window.close();
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="type=recoItem"+"&content="+itemval+"&id=0_1";
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
		  for(var i=0;i<val.split("*").length;i++){
			  var id=val.split("*")[i].split("_")[0];
			  var name=val.split("*")[i].split("_")[1];
			  var tmp=i+"_"+name;
			  if(i<val.split("*").length-1){
			 	 htm=htm+"<option>"+tmp+"</option>";
			  }
		  }
		  htm=htm+"</select>";
		  document.getElementById("td_k2").innerHTML=htm;
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords";
	  var param="k_1="+k_1;
	  xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
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
		  for(var i=0;i<val.split("*").length;i++){
			  var id=val.split("*")[i].split("_")[0];
			  var name=val.split("*")[i].split("_")[1];
			  var tmp=i+"_"+name;
			  htm=htm+"<tr>"+"<td><input name='checkbox_keywords' type='checkbox' value='"+id+"_"+name+"'/>"+tmp+"</td>"+"</tr>";
		  }
		  document.getElementById("table_keywords").innerHTML=htm;
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords";
	  var param="k_1="+k_1+"&k_2="+k_2;
	  xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
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
	  var url="<%=request.getContextPath()%>/keywords";
	  var param="k_1="+k_1+"&k_2="+k_2+"&k_3="+k_3;
	  xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
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
		  var url="<%=request.getContextPath()%>/update";
		  var param="yes=recom&val="+val+"&id="+id;
		  xmlhttp.open("POST",url,true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send(param);
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
		  var url="<%=request.getContextPath()%>/update";
		  var param="yes=remark&val="+val+"&id="+id;
		  xmlhttp.open("POST",url,true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send(param);
	}
	
	function update(id){
		
		  var url="<%=request.getContextPath()%>/update?yes=update&id="+id;
		  window.open(url);
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
			  for(var i=0;i<val.split("*").length;i++){
				  var id=val.split("*")[i].split("_")[0];
				  var name=val.split("*")[i].split("_")[1];
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
		  var url="<%=request.getContextPath()%>/keywords";
		  var param="k_1="+k_1+"&k_2="+k_2;
		  xmlhttp.open("POST",url,true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send(param);
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
			  for(var i=0;i<val.split("*").length;i++){
				  
				  for(var z=0;z<5;z++){
					  if(i<val.split("*").length-1){
						  var id=val.split("*")[i].split("_")[0];
						  var name=val.split("*")[i].split("_")[1];
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
		  var url="<%=request.getContextPath()%>/keywords";
		  var param="k_1="+id1;
		  xmlhttp.open("POST",url,true);
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

</script>
</html>