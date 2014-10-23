<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>购买商品数量排行榜-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
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
    		    			tag_name:tag_name
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
	    
	    function goto_del_item_ab()
		{
	    	eval("opt_success.style.display=\"none\"");
	    	var item_ids = $("#item_ids").val();
	    	item_ids = trimString(item_ids);
	    	
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
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=delTagItemAB',
    		    type: 'POST',
    		    data: {
    		    			item_ids:item_ids
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
   		    				//tr.style.display="none";
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
	    
	    // 更新商品的折扣价格
	    function refresh_item_price()
	    {
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=updateTagItemPrice',
    		    type: 'POST',
    		    data: {
    		    			tag_id:tag_id,
    		    			tag_name:tag_name
    		    },
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 15000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=${tag_id}&page=${page}&tag_name=${tag_name}";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		// alert("更新价格操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
	    }
	    
	    // 刷新商品列表(删除已经不能存在,下架的,商家不支持集分宝返利的商品)
	    function refresh_item()
	    {
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=refreshTagItem',
    		    type: 'POST',
    		    data: {
    		    			tag_id:tag_id,
    		    			tag_name:tag_name
    		    },
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 15000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=${tag_id}&page=${page}&tag_name=${tag_name}";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		// alert("刷新操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
	    }
	    
	 	// 还原今天之前的商品的集分宝比例(标签默认比例)
	    function restore_item_rate()
	    {
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=restoreTagItemRate',
    		    type: 'POST',
    		    data: {
    		    			tag_id:tag_id,
    		    			tag_name:tag_name
    		    },
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 15000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=${tag_id}&page=${page}&tag_name=${tag_name}";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		return false;
    		    	}
    		    }
    		});
	    }
	    
	    function goto_add_item()
		{
	    	var item_ids = $("#item_ids").val();
	    	item_ids = item_ids.replace(/&/g, "");
	    	item_ids = trimString(item_ids);
	    	
	    	var id_array = new Array();
	    	id_array = item_ids.split(",");
	    	if (id_array.length > 30)
	    	{
	    		alert("批量输入的商品id不能超过30个，请重新输入！");
	    		return false;
	    	}
	    	for (var i = 0; i < id_array.length; i++ )
	        {
	    		if (isNaN(id_array[i]))
		    	{
		    		alert("您的输入的商品id不合法，请重新输入！");
		    		return false;
		    	}
	        }
	    	
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	if(item_ids == "")
	    	{
	    		alert("你还未填写要添加的商品ID！");
	    		return false;
	        }
	    	else
	    	{
	            if(confirm("你确定要添加这个商品吗？"))
	            {
	            	eval("loadingImg.style.display=\"\"");
	            	eval("opt_success.style.display=\"none\"");
	            	$.ajax({
	        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addTagItem',
	        		    type: 'POST',
	        		    data: {
	        		    			item_ids:item_ids,
	        		    			tag_id:tag_id,
	        		    			tag_name:tag_name
	        		    			},
	        		    dataType: 'json',
	        		    cache: false,
	        		    timeout: 15000,
	        		    success: function(json)
	        		    {
	        		    	var result = json.result;
	        		    	eval("loadingImg.style.display=\"none\"");
	        		    	if (result == "0")
	       		    		{
	        		    		eval("opt_success.style.display=\"\"");
	        		    		$("#item_ids").val("");
	        		    		// var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag_id}&tag_name=${tag_name}";
	        					// location.href=url;
	       		    		}
	        		    	else if (result == "1")
	        		    	{
	        		    		alert("添加操作失败!");
	        		    		return false;
	        		    	}
	        		    },
	        		    error: function(XMLHttpRequest, textStatus, errorThrown)
	        		    {
	        		    	eval("loadingImg.style.display=\"none\"");
	        		    	eval("opt_success.style.display=\"none\"");
	        		    	// alert("添加操作失败!" + XMLHttpRequest.status + ";" + XMLHttpRequest.readyState + ";" + textStatus);
        		    		return false;
	        		    }
	        		});
	            } 
	            else
	            {
	    				return false;
	    		}
	    	}
		}
	    
	    function goto_add_item_url()
		{
	    	var item_url = $("#item_url").val();
	    	item_url = trimString(item_url);
	    	
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	if(item_url == "")
	    	{
	    		alert("你还未填写要添加的商品链接！");
	    		return false;
	        }
	    	else
	    	{
	            if(confirm("你确定要添加这个商品吗？"))
	            {
	            	eval("loadingImg.style.display=\"\"");
	            	eval("opt_success.style.display=\"none\"");
	            	$.ajax({
	        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addTagItemByURL',
	        		    type: 'POST',
	        		    data: {
	        		    			item_url:item_url,
	        		    			tag_id:tag_id,
	        		    			tag_name:tag_name
	        		    			},
	        		    dataType: 'json',
	        		    cache: false,
	        		    timeout: 15000,
	        		    success: function(json)
	        		    {
	        		    	var result = json.result;
	        		    	eval("loadingImg.style.display=\"none\"");
	        		    	if (result == "0")
	       		    		{
	        		    		eval("opt_success.style.display=\"\"");
	        		    		$("#item_url").val("");
	        		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag_id}&tag_name=${tag_name}";
	        					location.href=url;
	       		    		}
	        		    	else if (result == "1")
	        		    	{
	        		    		alert("商品链接不合法，请检查或者将链接发至开发人员检查!");
	        		    		return false;
	        		    	}
	        		    	else if (result == "2")
	        		    	{
	        		    		alert("无法获取到该淘宝客商品 或者 该商品没有返利!");
	        		    		return false;
	        		    	}
	        		    },
	        		    error: function(XMLHttpRequest, textStatus, errorThrown)
	        		    {
	        		    	eval("loadingImg.style.display=\"none\"");
	        		    	eval("opt_success.style.display=\"none\"");
	        		    	
	        		    	alert("淘宝接口超时，无法获取商品信息，请重新操作!");
        		    		return false;
	        		    }
	        		});
	            } 
	            else
	            {
	    				return false;
	    		}
	    	}
		}
	    
	    function add_new_item()
	    {
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemListFromTaobao&tagid=${tag_id}&keyword=${tag_name}&status=${status}&pageNo=${pageNo}&coup=${coup}&parent=${parent}&catId=${catId}&startCouponRate=${startCouponRate}&endCouponRate=${endCouponRate}&sort=${sort}&startPrice=${startPrice}&endPrice=${endPrice}&startCredit=${startCredit}&endCredit=${endCredit}&startCommissionRate=${startCommissionRate}&endCommissionRate=${endCommissionRate}";			
	    }
	    
	    function goto_mix_list()
	    {
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=getMixStyleItemList&tagid=${tag_id}&keyword=${tag_name}";
		}
	    
	    function lockItem(statue)
	    {
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	if(confirm("你确定要锁定/解锁这个商品吗？"))
	    	{
            	$.ajax(
            	{
        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=LockItem',
        		    type: 'POST',
        		    data: {
        		    			statue:statue,
        		    			tag_id:tag_id
        		    },
        		    dataType: 'json',
        		    cache: false,
        		    timeout: 5000,
        		    success: function(json)
        		    {
        		    	var result = json.result;
        		    	if (result == "0")
       		    		{
        		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag_id}&tag_name=${tag_name}";
        					location.href=url;
       		    		}
        		    	else if (result == "1")
        		    	{
        		    		alert("锁定/解锁操作失败!");
        		    		return false;
        		    	}
        		    }
        		});
            	}
	    	else 
	    	{
   				return false;
   			}
	    }
	    
	    function movePosition(itemid0,rank0,type) 
	    {
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	var itemid1 = "";
	    	var rank1 = "";
	    	if(type=='5')
	    	{
	    		itemid1 = $("#beginItemid").val();
	    		rank1 = $("#beginItemRank").val();
	    	}
	    	else if(type=='6')
	    	{
	    		itemid1 = $("#endItemid").val();
	    		rank1 = $("#endItemRank").val();
	    	}
	    	$.ajax(
	    	{
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=MovePosition',
    		    type: 'POST',
    		    data: {    		    			
		    			itemid0:itemid0,
		    			rank0:rank0,
		    			type:type,
		    			tag_id:tag_id,
		    			itemid1:itemid1,
		    			rank1:rank1
    			},
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag_id}&page=${page}&tag_name=${tag_name}";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("移动操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
	    }
	    
	    function addtoctag(ctagid, itemid, rank)
	    {
	   	 if ($("#"+ctagid+itemid).attr("checked"))
	   	 {
	   		eval("loadingImg_" + itemid + ".style.display=\"\"");
	   		var checkList = document.getElementsByName('addctagcheck');
	   		for (var i = 0; i < checkList.length; i++)
	   		{
	   			checkList[i].disabled = true;
	   		}
	   		
	   		$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=additemtotag',
    		    type: 'POST',
    		    data: {    		    			
    		    			itemid:itemid,
    		    			rank:rank,
    		    			ctagid:ctagid,
    		    			},
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	eval("loadingImg_" + itemid + ".style.display=\"none\"");
    		    	var checkList = document.getElementsByName('addctagcheck');
    		   		for (var i = 0; i < checkList.length; i++)
    		   		{
    		   			checkList[i].disabled = false;
    		   		}
    		   		
    		    	if (result == "0")
   		    		{
    					//alert("操作成功");
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("操作失败!");
    		    		return false;
    		    	}
    		    },
    		    error: function(XMLHttpRequest, textStatus, errorThrown)
    		    {
    		    	eval("loadingImg_" + itemid + ".style.display=\"none\"");
    		    	var checkList = document.getElementsByName('addctagcheck');
    		   		for (var i = 0; i < checkList.length; i++)
    		   		{
    		   			checkList[i].disabled = false;
    		   		}
    		   		// alert("操作失败!" + XMLHttpRequest.status + ";" + XMLHttpRequest.readyState + ";" + textStatus);
		    		return false;
    		    }
    		});
	   	 }
	   	 else
	   	 {
	   		eval("loadingImg_" + itemid + ".style.display=\"\"");
	   		var checkList = document.getElementsByName('addctagcheck');
	   		for (var i = 0; i < checkList.length; i++)
	   		{
	   			checkList[i].disabled = true;
	   		}
	   		
	   		$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=delitemtotag',
    		    type: 'POST',
    		    data: {    		    			
    		    			itemid:itemid,
    		    			ctagid:ctagid,
    		    			},
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	eval("loadingImg_" + itemid + ".style.display=\"none\"");
    		    	var checkList = document.getElementsByName('addctagcheck');
    		   		for (var i = 0; i < checkList.length; i++)
    		   		{
    		   			checkList[i].disabled = false;
    		   		}
    		   		
    		    	if (result == "0")
   		    		{
    					// alert("操作成功");
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("操作失败!");
    		    		return false;
    		    	}
    		    },
    		    error: function(XMLHttpRequest, textStatus, errorThrown)
    		    {
    		    	eval("loadingImg_" + itemid + ".style.display=\"none\"");
    		    	var checkList = document.getElementsByName('addctagcheck');
    		   		for (var i = 0; i < checkList.length; i++)
    		   		{
    		   			checkList[i].disabled = false;
    		   		}
    		   		// alert("操作失败!" + XMLHttpRequest.status + ";" + XMLHttpRequest.readyState + ";" + textStatus);
		    		return false;
    		    }
    		});
	   	 }
	   }
	    
	    function addImgName(fileId,imgName,imgid,formId)
	    {
	        var filename = $("#"+fileId).val();  
	        var suffix = filename.substring(filename.lastIndexOf("."));
	        if(suffix != '.jpg')
	        {
	        	alert("图片格式只能为jpg");
	        	return;
	        }
	        var prefix = "http://static.etouch.cn/suishou/adImg/";
	        $("#"+imgid).val(prefix+imgName+suffix);
	        $("#"+formId).submit();
	    }
	    
	    function updateItemImg(itemid)
	    {
	    	var img = $("#img"+itemid).val();
	    	var newprice = $("#price"+itemid).val();
	    	var jfb_rate = $("#jfb_rate_"+itemid).val();
	    	if (jfb_rate == null)
	    	{
	    		jfb_rate = "";
	    	}
	    	if (jfb_rate > 25)
    		{
    			alert("集分宝比例不能超过25！");
    			return;
    		}
	    	
	    	if(img=='')
	    	{
	    		img = $("#pastImg"+itemid).val();
	    	}
	    	if(confirm("你确定要修改这条商品的信息吗？"))
	    	{
	    		eval("loadingImg_" + itemid + ".style.display=\"\"");
            	$.ajax(
            	{
        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=updateItemImg',
        		    type: 'POST',
        		    data: {
			    			img:img,
			    			itemid:itemid,
			    			newprice:newprice,
			    			jfb_rate:jfb_rate
	    			},
        		    dataType: 'json',
        		    cache: false,
        		    timeout: 5000,
        		    success: function(json)
        		    {
        		    	var result = json.result;
        		    	eval("loadingImg_" + itemid + ".style.display=\"none\"");
        		    	if (result == "0")
       		    		{
        		    		var result_jfb_rate = json.jfb_rate;
        		    		document.getElementById("oldImg"+itemid).src=img;
        		    		$("#jfb_rate_"+itemid).val(result_jfb_rate);
       		    		}
        		    	else if (result == "1")
        		    	{
        		    		alert("操作失败!");
        		    		return false;
        		    	}
        		    }
        		});
            } 
	    	else
	    	{
   				return false;
   			}
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
	    
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h3 class="entry-title">购买商品排行榜 
	         
	         <c:if test="${type != null && type== 'update'}">
	         (第一次查询更新中，请于2min后来查看相同时间内商品)
	         </c:if>
	         </h3>
	         <hr/>
	         <form action="../ad/tag_item_manager?actionmethod=showTopTradeItemList " method="post">
开始时间：<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="${startDate}" />
结束时间：<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="${endDate}" />
<input type="submit" value="查看"><font color="red">  (默认时间月初到今天)</font> 
	         </form>
<hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th style="width: 3%"><input name="chkAll" id="chkAll" type="checkbox" onclick="ChkAllClick()" /></th>
	    				<th style="width: 10%">商品id</th>
	    				<th style="width: 12%">购买数量</th>
	    			</tr>
	    		</thead>
	    		<tbody id="tablebd">
	    			<c:forEach var="item" items="${ItemList}"  varStatus="status">
	                 <tr id="tr${item.item_id}">
	                 	<td>${status.index+1}<input type="checkbox" class="idcheck" name="chkItem" value="${item.item_id}" /></td>
	                 	<td><a href="${item.clickURL}" target="_blank"> ${item.item_id} </a> </td>
	                 	<td>${item.rank}</td>
	                </tr>
                   </c:forEach>
	    		</tbody>
	    	</table>
	    	<hr/>
	    	<hr/>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTopTradeItemList&start_time=${startDate}&end_time=${endDate}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<script type="text/javascript">
	$(function () {
        $(".ui_timepicker").datetimepicker({
            //showOn: "button",
            //buttonImage: "./css/images/icon_calendar.gif",
            //buttonImageOnly: true,
            showSecond: true,
            timeFormat: 'hh:mm:ss',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        });
    });
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>