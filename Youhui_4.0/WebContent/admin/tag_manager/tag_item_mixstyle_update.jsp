<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品模板修改-随手4.0</title>
<jsp:include page="../include/css_js.jsp"></jsp:include>
<style>
<link type="text/css" href="<%=request.getContextPath() %>/css/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.8.16.custom.min.js"></script>
<link href="<%=request.getContextPath() %>/css/screen.css" rel="stylesheet" type="text/css"
	media="all">
<link href="<%=request.getContextPath() %>/css/datepicker.css" rel="stylesheet" type="text/css"
	media="all">
<link href="<%=request.getContextPath() %>/css/tipsy.css" rel="stylesheet" type="text/css" media="all">
<link href="<%=request.getContextPath() %>/css/visualize.css" rel="stylesheet" type="text/css"
	media="all">
<link href="<%=request.getContextPath() %>/css/jquery.wysiwyg.css" rel="stylesheet"
	type="text/css" media="all">
<link href="<%=request.getContextPath() %>/css/jquery.fancybox-1.3.0.css" rel="stylesheet"
	type="text/css" media="all">
<!--[if IE]>
	<link href="css/ie.css" rel="stylesheet" type="text/css" media="all">
	<script type="text/javascript" src="js/excanvas.js"></script>
<![endif]-->


#sortable {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 480px;
}

#sortable li {
	margin: 0 3px 3px 3px;
	padding: 2em;
	font-size: 1.2em;
	width: 100px;
	float: left;
	display: inline;
}

#sortable li.first {
	margin: 4px;
	padding: 4px;
	width: 220px;
	height: 220px;
	border: 2px solid #DD4B39 ;
}

#sortable li.four {
	margin: 4px;
	padding: 4px;
	width: 100px;
	height: 100px;
	border: 2px solid #F1C;
}

#sortable li.other {
	margin: 4px;
	padding: 4px;
	width: 100px;
	height: 50px;
	border: 2px solid #888;
}

#sortable li.s1,#sortable li.s2,#sortable li.s3,#sortable li.s8 {
	margin: 4px;
	padding: 4px;
	width: 320px;
	height: 150px;
	border: 2px solid #888;
}

#sortable li.s4, #sortable li.s5,#sortable li.s6, #sortable li.s7, #sortable li.s10, #sortable li.s11, #sortable li.s12,#sortable li.s14,#sortable li.s15,#sortable li.s16,#sortable li.s17,#sortable li.s18 {
	margin: 4px;
	padding: 4px;
	width: 150px;
	height: 150px;
	border: 2px solid #888;
}

#sortable li.s9,#sortable li.s13 {
	margin: 4px;
	padding: 4px;
	width: 150px;
	height: 320px;
	border: 2px solid #888;
}



#sortable li.s14{
	margin: 4px;
	padding: 4px;
	width: 150px;
	height: 150px;
	border: 2px solid #888;
	position: relative;
	margin-top: -166px;
}


#sortable li span {
	position: absolute;
	margin-left: -1.3em;
}
td {
border: 2px solid #DD4B39 ;
}
</style>
<script>
	var sorted = "";
    initSortable("#sortable") ;
    var style = "";//选中的模板
    var choseItem = '';//选中的添加图片焦点区域

    function initSortable(id4Sort){
        $(function() {
            $( id4Sort ).sortable({
            	revert: true ,
                update: function(event,ui){

                } ,

                stop: function(event, ui) {
                	if(style==1) {
	                    $(id4Sort + " li:eq(0)").removeClass($(id4Sort + " li:eq(0)").attr("class"));
	                    $(id4Sort + " li:eq(0)").addClass("s1") ;
	
	                    $(id4Sort + " li:eq(1)").removeClass($(id4Sort + " li:eq(1)").attr("class"));
	                    $(id4Sort + " li:eq(1)").addClass("s2") ;
	
	                    //返回最后的顺序
	                    var arrayString = $(this).sortable('toArray').toString() ;
	                    //alert(arrayString);
	                    sortedCallBack(arrayString) ;
                	} else if(style==2) {
                        $(id4Sort + " li:eq(0)").removeClass($(id4Sort + " li:eq(0)").attr("class"));
                        $(id4Sort + " li:eq(0)").addClass("s3") ;

                        $(id4Sort + " li:eq(1)").removeClass($(id4Sort + " li:eq(1)").attr("class"));
                        $(id4Sort + " li:eq(1)").addClass("s4") ;

                        $(id4Sort + " li:eq(2)").removeClass($(id4Sort + " li:eq(2)").attr("class"));
                        $(id4Sort + " li:eq(2)").addClass("s5") ;

                        //返回最后的顺序
                        var arrayString = $(this).sortable('toArray').toString() ;
                        //alert(arrayString);
                        sortedCallBack(arrayString) ;
                	} else if(style==3) {
                        $(id4Sort + " li:eq(0)").removeClass($(id4Sort + " li:eq(0)").attr("class"));
                        $(id4Sort + " li:eq(0)").addClass("s6") ;
                        
                        $(id4Sort + " li:eq(1)").removeClass($(id4Sort + " li:eq(1)").attr("class"));
                        $(id4Sort + " li:eq(1)").addClass("s7") ;

                        $(id4Sort + " li:eq(2)").removeClass($(id4Sort + " li:eq(2)").attr("class"));
                        $(id4Sort + " li:eq(2)").addClass("s8") ;

                        //返回最后的顺序
                        var arrayString = $(this).sortable('toArray').toString() ;
                        //alert(arrayString);
                        sortedCallBack(arrayString) ;
                   	} else if(style==4) {
                        $(id4Sort + " li:eq(0)").removeClass($(id4Sort + " li:eq(0)").attr("class"));
                        $(id4Sort + " li:eq(0)").addClass("s9") ;

                        $(id4Sort + " li:eq(1)").removeClass($(id4Sort + " li:eq(1)").attr("class"));
                        $(id4Sort + " li:eq(1)").addClass("s10") ;
                        
                        $(id4Sort + " li:eq(2)").removeClass($(id4Sort + " li:eq(2)").attr("class"));
                        $(id4Sort + " li:eq(2)").addClass("s11") ;

                        //返回最后的顺序
                        var arrayString = $(this).sortable('toArray').toString() ;
                        //alert(arrayString);
                        sortedCallBack(arrayString) ;
                   	} else if(style==5) {
                        $(id4Sort + " li:eq(0)").removeClass($(id4Sort + " li:eq(0)").attr("class"));
                        $(id4Sort + " li:eq(0)").addClass("s12") ;

                        $(id4Sort + " li:eq(1)").removeClass($(id4Sort + " li:eq(1)").attr("class"));
                        $(id4Sort + " li:eq(1)").addClass("s13") ;

                        $(id4Sort + " li:eq(2)").removeClass($(id4Sort + " li:eq(2)").attr("class"));
                        $(id4Sort + " li:eq(2)").addClass("s14");

                        //返回最后的顺序
                        var arrayString = $(this).sortable('toArray').toString() ;
                        //alert(arrayString);
                        sortedCallBack(arrayString) ;
                   	} else if(style==6) {
                        $(id4Sort + " li:eq(0)").removeClass($(id4Sort + " li:eq(0)").attr("class"));
                        $(id4Sort + " li:eq(0)").addClass("s15") ;

                        $(id4Sort + " li:eq(1)").removeClass($(id4Sort + " li:eq(1)").attr("class"));
                        $(id4Sort + " li:eq(1)").addClass("s16") ;
                        
                        $(id4Sort + " li:eq(2)").removeClass($(id4Sort + " li:eq(2)").attr("class"));
                        $(id4Sort + " li:eq(2)").addClass("s17") ;

                        $(id4Sort + " li:eq(3)").removeClass($(id4Sort + " li:eq(3)").attr("class"));
                        $(id4Sort + " li:eq(3)").addClass("s18") ;

                        //返回最后的顺序
                        var arrayString = $(this).sortable('toArray').toString() ;
                        //alert(arrayString);
                        sortedCallBack(arrayString) ;
                   	}
                }
            });
            
            $( "#sortable" ).disableSelection();
        });

        function sortedCallBack(resultOrder){
        	//sorted = resultOrder;
        }
    }

    function saves(){
    	sorted='';
    	var reg = /^[0-9]+$/ ;//数字
    	$("#sortable li").each(
                function()
                {
                	 //if(!reg.test($(this).attr("id"))) {
                     //    alert('请选择商品');
                     //    return;
                	 //}
                     //alert($(this).attr("id"));
                     sorted = sorted+ $(this).attr("id") + ","; 
                }
            );
    	sorted = sorted.substring(0, sorted.length-1);
    	
    	//校验sorted,以逗号隔开的不同数字
    	isreg = true;
    	sortedArr = sorted.split(",");
    	var nary=sortedArr.sort();
    	for(var i=0;i<sortedArr.length;i++){
    		if(!reg.test(nary[i])) {
    			isreg = false;
    			break;
    		}
	    	if(nary[i]==nary[i+1]){
	    	    //alert("数组重复内容："+nary[i]);
    			isreg = false;
    			break;
	    	}
    	}
        //alert(sorted);
        if(isreg==false){
    	   alert('数据不合法');
    	   return false;
        }

		var tagid = "${tagid}";
		var oldpids = '${ppsBean.item_ids}';
		var position = '${ppsBean.rank}';
		var id = '${id}';
       if(sorted!='' && isreg){
    	   if(sorted=='${ppsBean.item_ids}'&&style == '${ppsBean.typeid}' ) {
	    	   alert('没有修改');
	    	   return;
    	   } 
    	   $.ajax({
   		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=updateMixStyleItem',
   		    type: 'POST',
   		    data: {
   		    			pids:sorted,
   		    			tagid:tagid,
   		    			style:style,
   		    			position:position,
   		    			oldpids:oldpids,
   		    			id:id,
   		    			},
   		    dataType: 'json',
   		    timeout: 1000,
   		    success: function(json)
   		    {
   		    	var result = json.result;
   		    	if (result == "0")
  		    		{
   		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=getMixStyleItemList&tagid=${tagid}&page=${page}&keyword=${keyword}";
   					location.href=url;
  		    		}
   		    	else if (result == "1")
   		    	{
   		    		alert("操作失败!");
   		    		return false;
   		    	}
   		    }
   		});
    	return true;
       }
	}
    
    function choseStyle(i) {
    	style = i;
    	$("#sortable").find("li").remove();
    	if(i==1) {
 		   var $li_1= $("<li class=\"s1\" onclick=\"setEdit($(this).attr('class'));\"> </li>");  
 		   var $li_2 =$("<li class=\"s2\" onclick=\"setEdit($(this).attr('class'));\"> </li>"); 
 		   $("#sortable").append($li_1);   
 		   $("#sortable").append($li_2);   
 		}else if(i==2) {
    		   var $li_1= $("<li class=\"s3\" onclick=\"setEdit($(this).attr('class'));\"> </li>");  
    		   var $li_2 =$("<li class=\"s4\" onclick=\"setEdit($(this).attr('class'));\"> </li>"); 
    		   var $li_3 =$("<li class=\"s5\" onclick=\"setEdit($(this).attr('class'));\"> </li>");
    		   $("#sortable").append($li_1);   
    		   $("#sortable").append($li_2);   
    		   $("#sortable").append($li_3); 
    	}else if(i==3) {
 		   var $li_1= $("<li class=\"s6\" onclick=\"setEdit($(this).attr('class'));\"> </li>");  
		   var $li_2 =$("<li class=\"s7\" onclick=\"setEdit($(this).attr('class'));\"> </li>"); 
		   var $li_3 =$("<li class=\"s8\" onclick=\"setEdit($(this).attr('class'));\"> </li>");
		   $("#sortable").append($li_1);   
		   $("#sortable").append($li_2);   
		   $("#sortable").append($li_3); 
		}else if(i==4) {
	 		   var $li_1= $("<li class=\"s9\" onclick=\"setEdit($(this).attr('class'));\"> </li>");  
			   var $li_2 =$("<li class=\"s10\" onclick=\"setEdit($(this).attr('class'));\"> </li>");
			   var $li_3 =$("<li class=\"s11\" onclick=\"setEdit($(this).attr('class'));\"> </li>"); 
			   $("#sortable").append($li_1);   
			   $("#sortable").append($li_2);
			   $("#sortable").append($li_3);
		}else if(i==5) {
	 		   var $li_1= $("<li class=\"s12\" onclick=\"setEdit($(this).attr('class'));\"> </li>");  
			   var $li_2 =$("<li class=\"s13\" onclick=\"setEdit($(this).attr('class'));\"> </li>");
			   var $li_3 =$("<li class=\"s14\" onclick=\"setEdit($(this).attr('class'));\"> </li>"); 
			   $("#sortable").append($li_1);   
			   $("#sortable").append($li_2);
			   $("#sortable").append($li_3);
		}else if(i==6) {
	 		   var $li_1= $("<li class=\"s15\" onclick=\"setEdit($(this).attr('class'));\"> </li>");  
			   var $li_2 =$("<li class=\"s16\" onclick=\"setEdit($(this).attr('class'));\"> </li>");
			   var $li_3 =$("<li class=\"s17\" onclick=\"setEdit($(this).attr('class'));\"> </li>");
			   var $li_4 =$("<li class=\"s18\" onclick=\"setEdit($(this).attr('class'));\"> </li>"); 
			   $("#sortable").append($li_1);   
			   $("#sortable").append($li_2);
			   $("#sortable").append($li_3);
			   $("#sortable").append($li_4);
			}
    }
    
    
    function setEdit(check) {
    	choseItem = check;
    	//$("."+choseItem).css("border","2px solid #FF0000");
    	//$("."+uncheck1).css("border","2px solid #888");
    	//$("."+uncheck2).css("border","2px solid #888");
    	//$("."+uncheck3).css("border","2px solid #888");
    	
    	
    	$("#sortable li").each(
                function()
                {
                     if($(this).attr("class")==check)
                    	 $(this).css("border","2px solid #FF0000");
                     else
                    	 $(this).css("border","2px solid #888");
                }
            );
    	
		document.getElementById("detail").style.display = 'block';
		document.getElementById("detail1").innerHTML = $("."+choseItem).attr("t");
		document.getElementById("detail2").innerHTML = $("."+choseItem).attr("p");
		document.getElementById("detail3").innerHTML = $("."+choseItem).attr("d");
		if(document.getElementById("detail1").innerHTML=='undefined'){
			document.getElementById("detail1").innerHTML='';
		}
		if(document.getElementById("detail2").innerHTML=='undefined'){
			document.getElementById("detail2").innerHTML='';
		}
		if(document.getElementById("detail3").innerHTML=='undefined'){
			document.getElementById("detail3").innerHTML='';
		}
    }
    
  //  function setVar(id,img) {
  //  		$("."+choseItem).attr("style","background: url("+img+"_160x160.jpg) no-repeat 50% 0;border:2px solid #FF0000;");  
  //  		$("."+choseItem).attr("id",id); 
  //  }
    function setVar(id,img,title,price,discount) {
		$("."+choseItem).attr("style","background: url("+img+"_160x160.jpg) no-repeat 50% 0;border:2px solid #FF0000;");  
		$("."+choseItem).attr("id",id);
		
		$("."+choseItem).attr("t",title);
		$("."+choseItem).attr("p",price);
		$("."+choseItem).attr("d",discount);

		document.getElementById("detail").style.display = 'block';
		document.getElementById("detail1").innerHTML = title;
		document.getElementById("detail2").innerHTML = price;
		document.getElementById("detail3").innerHTML = discount;
		
}
    
    function load() {
    	
    	choseStyle("${ppsBean.typeid}") ;
    	<c:forEach var="item" items="${tjList}"  varStatus="status">
    	$("#sortable li:eq(${status.index})").attr("style","background: url("+'${item.pic_url}'+"_160x160.jpg) no-repeat 50% 0;border:2px solid #FF0000;");  
    	$("#sortable li:eq(${status.index})").attr("id","${item.item_id}");
    	
    	$("#sortable li:eq(${status.index})").attr("t",'${item.title}');
    	$("#sortable li:eq(${status.index})").attr("p",'${item.price_low}');
    	$("#sortable li:eq(${status.index})").attr("d",'${item.item_id}');
    	
    	</c:forEach>

    }
    </script>
</head>
<body onload="load();" style="min-width:1300px;">
<div class="content_wrapper">
		<!-- Begin header -->
		<%@include file="../include/header.jsp" %>
		<!-- End header -->
		<!-- Begin left panel -->
		<div id="container" class = "clear">
		<div id="content">
		<!-- 
		<a href="javascript:;" id="show_menu">&raquo;</a>
		 -->
		<jsp:include page="../include/menu.jsp"></jsp:include>
		<!-- End left panel -->
		<!-- Begin content -->
			<a href="javascript:saves();" id="save"><input type=button value="保存"></a>
			<div class="inner">
				<!-- Begin one column window -->
				<div class="onecolumn"  style="float:left; width:450px;height: 250px;">
					<div class="header" style="margin: 0;padding: 0;float: left;width: 100%;height: 40px;border-bottom: 1px;background:../../img/bg_window_header.png;">
						<span style="display: block;font-size: 16px;font-weight: bold;padding: 10px 0 0 15px;float: left;width: auto;cursor: n-resize;">拖动以调整显示顺序 </span>
					</div>
					<br class="clear">
					<div class="content"  style="height: 250px;">
						<ul id="sortable">
						</ul>
					</div>
					<div class="detail" id="detail" style="height: 100px; display: none;">
					商品名：<span  class="detail1" id="detail1"></span><br/>
					价格：<span class="detail2" id="detail2"></span><br/>
					商品ID：<span class="detail3" id="detail3"></span>
					</div>
					<br>
					<p style="margin-top: 100px;">请选择模板：</p>
					<div id="style1"  style="float:left; width:33%" onclick="choseStyle(1);">
					<table border="1" width="100px" height="50px;">
					<tr><td colspan="2"></td></tr>
					<tr><td colspan="2"></td></tr>
					</table>
					</div>
					
					<div id="style2"  style="float:left; width:33%;" onclick="choseStyle(2);">
					<table border="1" width="100px" height="50px;">
					<tr><td colspan="2"></tr>
					<tr><td></td><td></td></tr>
					</table>
					</div>
					
					<div id="style3"  style="float:left; width:33%;" onclick="choseStyle(3);">
					<table border="1" width="100px" height="50px;">
					<tr><td></td><td></td></tr>
					<tr><td colspan="2"></tr>
					</table>
					</div>
					
					<div id="style4"  style="float:left; width:33%;margin-top: 30px;" onclick="choseStyle(4);">
					<table border="1" width="100px" height="50px;">
					<tr><td rowspan="2"><td></td></tr>
					<tr><td></td></tr>
					</table>
					</div>

					<div id="style5"  style="float:left; width:33%;margin-top: 30px;" onclick="choseStyle(5);">
					<table border="1" width="100px" height="50px;">
					<tr><td></td><td rowspan="2"></tr>
					<tr><td></td></tr>
					</table>
					</div>
					
					<div id="style6"  style="float:left; width:33%;margin-top: 30px;" onclick="choseStyle(6);">
					<table border="1" width="100px" height="50px;">
					<tr><td></td><td></td></tr>
					<tr><td></td><td></td></tr>
					</table>
					</div>
				</div>
				
				<div class="onecolumn" style="float:right; width:50%">
					<div class="header"><span>商品列表</span></div>
					  <ul style="list-style-type: none;"> 
					  
					  	<c:forEach var="item" items="${ItemList}">
					            <li style="border: 1px solid #DD4B39 ;float: left;width: 120px;margin-left: 10px;" class="draggable">
					            <img src="${item.pic_url}"  height="120px" width="120px" class="draggable txt1" id="${item.item_id}" onclick="setVar(this.id,this.src,'${item.title}','${item.price_low} ','${item.title}');"/>
					            </li>
					  </c:forEach>
					  </ul>
				</div>
			</div>

			<br class="clear" /> <br class="clear" />
			</div>
			<jsp:include page="../include/foot.jsp"></jsp:include></div>
		<!-- End content -->
	</div>
</body>
</html>