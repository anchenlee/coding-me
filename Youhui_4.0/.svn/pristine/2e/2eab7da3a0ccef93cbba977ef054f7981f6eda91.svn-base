<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.netting.dao.admin.TaobaoAPI_DAO"%>
<%@page import="com.netting.bean.TeJiaGoodsBean"%>
<%@page import="com.netting.dao.admin.Admin_Tag_Item_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%

	String itemId = request.getParameter("id");
	if(itemId == null){
		itemId = "";
	}
	String p = request.getParameter("page");
	if(p == null || "".equals(p)){
		p = "1";
	}
	String parent = request.getParameter("parent");
	String tag_id = request.getParameter("tag_id");
	String tag_name = request.getParameter("tag_name");
	TeJiaGoodsBean bean = Admin_Tag_Item_DAO.getDiscountProduct(itemId);
	List<String> imgs = new ArrayList<String>();
	//List<String> imgs = TaobaoAPI_DAO.getItemImgFromTaobao(itemId);
%>
<head>
    <title>${tag_name}-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
    <!-- 
    <jsp:include page="../include/css_js.jsp"></jsp:include>
     -->
    <script type="text/javascript">
    
	    function trimString(str)
		{
	    	return str.replace(/(^\s*)|(\s*$)/g, "");
		}
	    
	    function goto_del_item()
		{
	    	eval("opt_success.style.display=\"none\"");
	    	var item_ids = $("#item_ids").val();
	    	item_ids = trimString(item_ids);
	    	var parentId = "${parent}";
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	
	    	$(".idcheck").each(function() 
   			{
   				if ($(this).attr("checked")) 
   				{
   					var checkedID = $(this).val();
   					if (checkedID !=null && checkedID != "")
   					{
   						if (item_ids != null && item_ids != "")
   						{
   							item_ids = item_ids + "," + checkedID;
   						}
   						else
   						{
   							item_ids = checkedID;
   						}
   					}
   				}
	        });
	    	if (item_ids == null || item_ids == "")
	    	{
	    		alert("您尚未选择要删除的商品!");
				return false;
	    	}
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=delTagItem',
    		    type: 'POST',
    		    data: {
    		    			item_ids:item_ids,
    		    			tag_id:tag_id,
    		    			tag_name:tag_name,
    		    			parentId:parentId
    		    			},
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		eval("opt_success.style.display=\"\"");
    		    		item_idsArr = item_ids.split(",");
    		    		for(var i=0;i<item_idsArr.length;i++)
   		    			{
   		    				var tr = document.getElementById("tr"+item_idsArr[i]);
   		    				tr.parentNode.removeChild(tr);
   		    				// tr.style.display="none";
   		    			}
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("删除操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
		}	      
	  	    
	    function addImgName(imgName)
	    {
	        var filename = $("#file").val();  
	        var suffix = filename.substring(filename.lastIndexOf("."));
	        if(suffix != '.jpg')
	        {
	        	alert("图片格式只能为jpg");
	        	return;
	        }
			var prefix = "http://static.etouch.cn/suishou/item_img/";
	        $("#img").val(prefix+imgName+suffix);
	        
	        //setTimeout(function(){ getSuccess();},2000);
	        $("#uploadImgForm").submit();
	    }
	    
	    function getSuccess(){
	    	alert("上传成功！");
	    }
	    
	    function updateItemImg(itemid)
	    {
	    	var img = $("#img").val();
	    	var title = $("#title").val();
	    	var newprice = $("#price").val();
	    	var jfb_rate = $("#jfb_rate").val();
	    	var click_url = $("#click_url").val();
	    	if (jfb_rate == null)
	    	{
	    		jfb_rate = "";
	    	}
	    	if (jfb_rate > 25)
    		{
    			alert("集分宝比例不能超过25！");
    			return;
    		}
	    	if(img == ""){
	    		alert("图片不能为空");
	    		return;
	    	}
	    	if(title == ""){
	    		alert("标题不能为空");
	    		return;
	    	}
	    	if(click_url == undefined){
	    		click_url = "";
	    	}
	    	var biaoti = $("#biaoti").val();
	    	if(biaoti ==undefined){
	    		biaoti = "";
	    	}
	    	if(biaoti !=undefined && biaoti.lenght>30){
	    		alert("标题不能超过30个字！");
	    		return;
	    	}
	    	//if(confirm("你确定要修改这条商品的信息吗？"))
	    	//{
	    		//eval("loadingImg.style.display=\"\"");
            	$.ajax(
            	{
        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=updateItemImg',
        		    type: 'POST',
        		    data: {
			    			img:img,
			    			itemid:itemid,
			    			newprice:newprice,
			    			jfb_rate:jfb_rate,
			    			title:title,
			    			click_url:click_url,
			    			biaoti:biaoti
	    			},
        		    dataType: 'json',
        		    cache: false,
        		    timeout: 5000,
        		    success: function(json)
        		    {
        		    	var result = json.result;
        		    	//eval("loadingImg.style.display=\"none\"");
        		    	if (result == "0")
       		    		{
        		    		var result_jfb_rate = json.jfb_rate;
        		    		//document.getElementById("oldImg"+itemid).src = img;
        		    		//$("#oldImg"+itemid).src = img;
        		    		$("#oldImg"+itemid).attr("src",img);
        		    		$("#jfb_rate_"+itemid).val(result_jfb_rate);
        		    		//document.getElementById("itemtitle"+itemid).innerHTML(title);
        		    		$('#itemtitle'+itemid).text(title);
        		    		$("#price"+itemid).val(newprice);
        		    		$("#fancybox-inner").empty();
        		    		$("#fancybox-close").css("display","none");
        		    		$("#fancybox-wrap").css("display","none");
        		    		$("#fancybox-overlay").css("display","none");
        		    		$("#fancybox-loading").css("display","none");
        		    		//$("fancybox-close").css("display","none");
        		    		
        		    		
        		    		//$("#fancybox-close").css("display")=="none";
        		    		//$("#fancybox-wrap").css("display")=="none";
        		    		//$("#fancybox-overlay").css("display")=="none";
        		    		//$("#fancybox-loading").css("display")=="none";
        		    		//eval("fancybox-close.style.display=\"none\"");
        		    		//eval("fancybox-left.style.display=\"none\"");
        		    		//eval("fancybox-loading.style.display=\"none\"");
        		    		//window.open('', '_self');
        		    		//window.close();
        		    		//$("#tijiaoForm").submit();
        		    		//var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=<%=parent%>&tag_id=<%=tag_id%>&tag_name=<%=tag_name%>&page=<%=p%>";
        					//location.href=url;
       		    		}
        		    	else if (result == "1")
        		    	{
        		    		alert("操作失败!");
        		    		return false;
        		    	}
        		    	else if (result == "2")
        		    	{
        		    		alert("地址转换!");
        		    		return false;
        		    	}
        		    }
        		});
          //  } 
	    	//else
	    	//{
   			//	return false;
   		//	}
	    }
	    
	    
	    function add_tr()
	    {
	    	var table1 = document.getElementById("tablebd");
	    	var flag = "${statue}";
			var tr1 = table1.rows[flag];
			var ty=document.createElement("tr"); 
			
			ty.innerHTML  = "<td>22191811643</td><td>bbb</td><td>bbb</td><td>bbb</td><td>bbb</td>";

			tr1.parentNode.insertBefore(ty,tr1);
	    }
	    
	    function ChkAllClick(sonName, cbAllId)
	    {
	        var arrSon = document.getElementsByName("chkItem");
	     	var cbAll = document.getElementById("chkAll");
	     	var tempState = cbAll.checked;
	    	for(var i = 0; i < arrSon.length; i++) 
	    	{
	      		if(arrSon[i].checked != tempState)
	      		{
	      			arrSon[i].click();
	      		}
	    	}
	    }
	    
	function choicedImg(id,total,curpic,img){
		for(var i = 1 ; i <= total ; i ++){	
			//document.getElementById("list_img"+i).style="";
			$("#list_img"+i).attr("class",'');
		}
		$("#img").val(img);
		if(curpic == '-1'){
			$("#oldImg").attr("class",'');
		}
		else if(curpic == '0'){
		$("#"+id).attr("class",'imgdisplay');
		$("#orgin_pic").attr("checked",'checked');
	}else{
		$("#"+id).attr("class",'imgdisplay');
		$("#oldImg").attr("class",'');
		$("#pic_list"+curpic).attr("checked",'checked');
	}
	}
	
	function displayPic(id){
		$.ajax(
            	{
        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=getItemImgsFromTaobao',
        		    type: 'POST',
        		    data: {
			    			id:id
	    			},
        		    dataType: 'json',
        		    cache: false,
        		    timeout: 6000,
        		    success: function(json)
        		    {
        		    	var size = json.length;
        		    	if(size > 0){
        		    		var addPropValue = document.getElementById("taobao_pic_list");
        		    		//var fancybox = document.getElementById("fancybox-inner"); 
        		    		//fancybox.setAttribute("style","top: 0px; left: 0px; width: 704px; height: 604px; overflow: hidden;");
        		    		//var wrap = document.getElementById("fancybox-wrap"); 
        		    		//wrap.setAttribute("style","opacity: 1; width: 704px; height: 504px; top: 986px; left: 577.5px; display: block;");
        		    		for(var i = 1 ; i<= size ; i ++){
        		    			var img = json[i-1];
        		    			var propertyimg = document.createElement("img");
        		    			propertyimg.setAttribute("id","list_img"+i);        		    			
        		    			propertyimg.setAttribute("src",img);
        		    			propertyimg.setAttribute("height","70px;");
        		    			propertyimg.setAttribute("style","margin-left\ :6px;");       		    			
        		    			propertyimg.setAttribute("onclick","choicedImg('list_img"+i+"','"+size+"','"+i+"','"+img+"')");
        		    			addPropValue.appendChild(propertyimg);
        		    		}
        		    	}
        		    }
        		});
	}
	 function setHighAndWidth(){
			var fancybox = document.getElementById("fancybox-inner"); 
	 		fancybox.setAttribute("style","top: 0px; left: 0px; width: 704px; height: 604px; overflow: hidden;");
	 		var wrap = document.getElementById("fancybox-wrap"); 
	 		wrap.setAttribute("style","opacity: 1; width: 704px; height: 504px; top: 986px; left: 577.5px; display: block;");
	 		
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
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width: 100%; height: 800px;">
	<div id="container" class = "clear" style="height: 504px;">
     <div id="content">
         <div id="primary" class="hfeed">
	         <h3 class="entry-title">商品修改
	         <img id="loadingImg" alt="" src="<%=request.getContextPath()%>/img/loading.gif" style="width: 20px;height: 20px;display: none ">
	         </h3>
	         <hr/>
	         <form id="tijiaoForm" action="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=<%=parent%>&tag_id=<%=tag_id%>&tag_name=<%=tag_name%>&page=<%=p%>" method="post" >
		  <%
		       if((parent != null && parent.equals("1249")) || (tag_id != null && tag_id.equals("1249"))){
		    	   %>
		    	       	标题:	<input type="text" style="width: 400px;"  name="biaoti" id="biaoti" value="<%if(bean != null) out.print(bean.getKeyword());%>">
		       <br>
		    	   <%
		    	   }
		  %>  
		   名称：	 <input type="text" style="width: 400px;"  name="title" id="title"  value="<%if(bean!= null) out.print(bean.getTitle());%>">
		       <br>    
		       <%
		       if((parent != null && parent.equals("1249"))|| (tag_id != null && tag_id.equals("1249"))){
		    	   %>
		     
		    	   	地址:	<input type="text" style="width: 400px;"  name="click_url" id="click_url" value="<%if(bean != null) out.print(bean.getClickURL());%>">
		       <br>
		    	   <% 
		       }
		       %>
		       价格：	 <input type="text"  name="price" id="price"  value="<%if(bean!= null) out.print(bean.getPrice_low());%>">
		       <br>
		       集分宝	<input type="text"  name="jfb_rate" id="jfb_rate" value="<%if(bean != null) out.print(bean.getRate());%>">
		       <br>
	           图片:     <input type="text" style="width: 600px;"  name="img" id="img" style="width:120px;" value="<%if(bean != null) out.print(bean.getPic_url());%>">
	           <div>
	           <div id="taobao_pic_list">
	           <script type="text/javascript">
	           //displayPic('<%=itemId%>');
	           
	           setHighAndWidth();
	           setTimeout(function(){ displayPic('<%=itemId%>');},500);
	           </script>
	           </div>
	           原图： <input id = "orgin_pic" type="radio" name="pic"  value="<%if(bean != null) out.print(bean.getPic_url());%>" style="display: none;">
	           <img id="oldImg"  alt="" src="<%if(bean != null) out.print(bean.getPic_url());%>" width="75px" height="60px" onclick="choicedImg('oldImg','<%=imgs.size() %>','0','<%if(bean != null) out.print(bean.getPic_url());%>')"  style="">
	           <!-- 
	            <input type="radio" name="pic"  value="" onclick="choicedImg('','<%=imgs.size() %>','-1')">
	            不选:
	            -->
	           </div>
	        </form>
	                       	<% String imgSaveName = Long.toString(System.nanoTime(), 36); %>
	                       	<form id="uploadImgForm" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/reduceImg?imgsn=<%=imgSaveName%>" target="uploadIfr" >
	                             <input type="hidden" name="version" value="adImg" >
						    <input type="hidden" name="fileName" value="<%=imgSaveName%>">
	                             <input id="file" type="file" name="imgFile" style="width:120px;"  />
	                             <input type="button" onclick="addImgName('<%=imgSaveName%>')" value="上传" />
	                         </form>
	                         <input type="button" onclick="updateItemImg('<%=itemId%> ')" value="更改">
	                         <iframe name="uploadIfr" id="uploadIfr"  style="display: none" ></iframe>
		</div>
	  </div>
	 </div>
	 <script type="text/javascript">

	 </script>
	</body>
	<script type="text/javascript">
	
	function dialog() {
		var val = window.showModalDialog("<%=request.getContextPath()%>/admin/tag_manager/tag_item_update.jsp", 
		window, "dialogWidth:600px;status:no;dialogHeight:300px");
		
		if(!val){ 
			val= window.ReturnValue;
		} 
	}

	</script>
</html>