<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	

%>

<html>
<head>
    <title>${tag_name}-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <link href="<%=request.getContextPath()%>/js/fancybox/jquery.fancybox-1.3.0.css" rel="stylesheet"
	type="text/css" media="all">

    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.img.preload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/hint.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.visualize.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fancybox/jquery.fancybox-1.3.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/custom_blue.js"></script>
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
	    		$("#deny_repeated").val("yes");

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
	        		    	$("#deny_repeated").val("yes");
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
	        		    	}else if (result == "4"){
	        		    		alert("淘宝接口超时，无法获取商品信息，请重新操作!");
	        		    		return false;
	        		    	}
	        		    },
	        		    error: function(XMLHttpRequest, textStatus, errorThrown)
	        		    {
	        		    	eval("loadingImg.style.display=\"none\"");
	        		    	eval("opt_success.style.display=\"none\"");
	        		    	
        		    		$("#deny_repeated").val("yes");
	        		    	alert("淘宝接口超时，无法获取商品信息，请重新操作!");
        		    		return false;
	        		    }
	        		});
	    	}
		}
	    
	    function add_new_item()
	    {
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemListFromTaobao&tagid=${tag_id}&keyword=${tag_name}&status=${status}&pageNo=${pageNo}&coup=${coup}&parent=${parent}&catId=${catId}&startCouponRate=${startCouponRate}&endCouponRate=${endCouponRate}&sort=${sort}&startPrice=${startPrice}&endPrice=${endPrice}&startCredit=${startCredit}&endCredit=${endCredit}&startCommissionRate=${startCommissionRate}&endCommissionRate=${endCommissionRate}";			
	    }
	    
	    function goto_mix_list()
	    {
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showMixItemPage&tagid=${tag_id}&keyword=${tag_name}";
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
			var prefix = "http://static.etouch.cn/suishou/item_img/";
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
	    
	    function upsetItem(){
	    	var tagid = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	var upsetNum = $("#upsetNum").val();
	    	if(upsetNum == ""){
	    		alert("个数不能为空");
	    		return false;
	    	}
	    	if(confirm("确定重新排序？"))
	    	{
            	$.ajax(
            	{
        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=upSetTodayTagItemPosition',
        		    type: 'POST',
        		    data: {
        		    			upsetNum:upsetNum,
        		    			tagid:tagid
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
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h3 class="entry-title">《${tag_name}》商品列表</h3>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addTagItemByURLByForm" method="post" >
		         商品ID:<input type="text" id="item_ids" name="item_ids" value="">
		         <input type="button" class="button" value="删除"  onclick="goto_del_item()"/>
		         <input type="button" class="button" value="完全删除"  onclick="goto_del_item_ab()"/>
		         <input type="button" class="button" value="添加" onclick="goto_add_item()" />
		         商品链接:<input type="text" id="item_url" name="item_url" value="">
		         <input type="button" class="button" value="添加" onclick="goto_add_item_url()" />
		         <input type="hidden" id="deny_repeated" value="yes">
		         <!-- 
		         
		         <input type="hidden" name="tag_name" value="${tag_name }">
		         <input type="hidden" name="tag_id" value="${tag_id }">
		         <input type="hidden" name="parent" value="${parent }">
		         <input type="submit" value="添加" >
		          -->
		         
		         
		        重新排序个数 <input type="text" name="upsetNum" id="upsetNum"> 
		         <input type="button" value="确定" onclick="upsetItem()">
		         &nbsp;&nbsp;&nbsp;&nbsp; 
		         <img id="loadingImg" alt="" src="../img/loading.gif" style="width: 20px;height: 20px;display: none ">
		         <span  id="opt_success" style="display: none">操作成功</span>
		         <div  style="float: right">
		         	<input type="button" class="button" value="还原集分宝比例" onclick="restore_item_rate()" />
			         <input type="button" class="button" value="刷新商品" onclick="refresh_item()" />
			         <input type="button" class="button" value="刷新价格" onclick="refresh_item_price()" />
			         <input type="button" class="button" value="新增商品" onclick="add_new_item()" />
			         <!-- 
			         <input type="button" class="button" value="混合样式" onclick="goto_mix_list()" />
			          -->
			         
			     </div>
	        </form>
		    <hr/>
		    <c:if test="${headTagList_1 != null && fn:length(headTagList_1) > 0}">
			    <c:forEach var="tag_map" items="${headTagList_1}">
				    <c:forEach var="tag" items="${tag_map}">
						<a href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag.key}&tag_name=${tag.value}" style="color: #1F92FF">${tag.value}</a>&nbsp;|&nbsp;
					</c:forEach>
				</c:forEach>
			</c:if>
		    <hr/>
		    <c:if test="${headTagList_2 != null && fn:length(headTagList_2) > 0}">
			    <c:forEach var="tag_map_2" items="${headTagList_2}">
				    <c:forEach var="tag_2" items="${tag_map_2}">
						<a href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag_2.key}&tag_name=${tag_2.value}" style="color: #1F92FF">${tag_2.value}</a>&nbsp;|&nbsp;
					</c:forEach>
				</c:forEach>
			</c:if>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th style="width: 4%"><input name="chkAll" id="chkAll" type="checkbox" onclick="ChkAllClick()" /></th>
	    			    <th style="width: 3%">ID</th>
	    			    <c:choose>
							<c:when test="${page == 1}">
								<th  style="width: 5%">锁定</th>
							 </c:when>
						</c:choose>
	    				<th style="width: 10%">名称</th>
	    				<th style="width: 10%">图片</th>
	    				<c:if test="${ctaglist != null && fn:length(ctaglist) > 0}">
	    					<th style="width: 8%">子标签</th>
	    				</c:if>
	    				<th style="width: 3%"></th>
	    				<th style="width: 3%">价格</th>
	    				<th style="width: 5%">折扣</th>
	    				<!-- 
	    				<th style="width: 5%">返利</th>
	    				 -->
	    				<th style="width: 7%">集分宝</th>
	    				<th style="width: 15%">更新时间</th>
	    				<th style="width: 12%">排序操作</th>
	    				<th style="width: 8%">编辑</th>
	    			</tr>
	    		</thead>
	    		<tbody id="tablebd">
	    			<c:forEach var="item" items="${ItemList}"  varStatus="status">
	                 <tr id="tr${item.item_id}">
	                 	<td>${status.index+1}<input type="checkbox" class="idcheck" name="chkItem" value="${item.item_id}" /></td>
	                 	<td>${item.item_id}</td>
	                 	<c:choose>
							<c:when test="${page==1&& status.index+1<=statue}">
								<td>
									<a onclick="lockItem(${status.index})">
										<img alt="" style="width:28px;" src="<%=request.getContextPath() %>/img/lock_red.png">
								 	</a>
								 </td>
							 </c:when>
							 <c:when test="${page==1&& status.index+1>statue}">
								 <td>
								 	<a onclick="lockItem(${status.index+1})">
								 		<img alt="" style="width:28px;" src="<%=request.getContextPath() %>/img/lock-unlock_blue.png">
								 	</a>
								 </td>
							 </c:when>
						</c:choose>
                       	<td width="150px;"><a target="_blank" href="<%=request.getContextPath() %>/admin/tag_manager/discount_product_taobao.jsp?itemid=${item.item_id}" style="color: #1F92FF">${item.title}</a></td>
						<td>
							<label class="media_photos">
		                       	<a rel="slide" target="_blank" href="${item.pic_url}" title="${item.title}">
		                       		<img id="oldImg${item.item_id}" width="75px"  src="${item.pic_url}">
		                       	</a>
	                       	</label>
	                       	<input type="hidden" id="pastImg${item.item_id}" value="${item.pic_url}">
                       	</td>
                       	<c:if test="${ctaglist != null && fn:length(ctaglist) > 0}">
	                       	<td>
		                       	<c:forEach var="citem" items="${ctaglist}">
			                       	<c:choose>
				                       	<c:when test="${fn:contains(citem.itemids, item.item_id)}">
					                       	<input type='checkbox' id="${citem.id}${item.item_id}" name="addctagcheck" onclick= "addtoctag(${citem.id},${item.item_id},${item.rank})" checked /> 
											<a href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${tag_id}&tag_id=${citem.id}&tag_name=${citem.keyword}" style="color: #1F92FF">${citem.keyword}</a><br/>
				                       	</c:when>
				                       	<c:otherwise>
					                       	<input type='checkbox' id="${citem.id}${item.item_id}" name="addctagcheck" onclick='addtoctag(${citem.id} ,${item.item_id},${item.rank})' /> 
					                       	<a href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${tag_id}&tag_id=${citem.id}&tag_name=${citem.keyword}" style="color: #1F92FF">${citem.keyword}</a><br/>
				                       	</c:otherwise>
			                       	</c:choose>
		                       	</c:forEach>
	                       	</td>
                       	</c:if>
                       	<td><img id="loadingImg_${item.item_id}" alt="" src="../img/loading.gif" style="width: 20px;height: 20px;display: none "></td>
                       	<td>
                       	<input type="text" id="price${item.item_id}" value="${item.price_low}" style="width: 30px" readOnly>
                       	</td>
                       	<td>${item.discount}</td>
                       	<!-- 
                       	<td>${item.commission_rate}%</td>
                       	 -->
                       	<td>
                       		<input type="text" id="jfb_rate_${item.item_id}" value="${fn:replace(item.rate, '.0', '')}" style="width: 30px" readOnly />
                       	</td>
                       	<td>${item.update_time}</td>
                       	<td width="110px;">
	                       	<a onclick="movePosition(${item.item_id},${item.rank},1)">
								<img style="width: 16px;" alt="上移一位" title="上移一位" src="<%=request.getContextPath() %>/img/shang1.png" onclick="location.href='#'">
	                       	</a>
							<a onclick="movePosition(${item.item_id},${item.rank},2)">
								<img style="width: 16px;" alt="下移一位" title="下移一位" src="<%=request.getContextPath() %>/img/xia1.png" onclick="location.href='#'">
							</a>
							<a onclick="movePosition(${item.item_id},${item.rank},5)">
								<c:choose>
									<c:when test="${status.index==0}">						
										<input type="hidden" id="beginItemid" value="${item.item_id}">
										<input type="hidden" id="beginItemRank" value="${item.rank}">
									</c:when>
								</c:choose>
								<img style="width: 16px;" alt="移至本页首" title="移至本页首位" src="<%=request.getContextPath() %>/img/shang2.png">
							</a>
							<a onclick="movePosition(${item.item_id},${item.rank},6)">
								<c:choose>
									<c:when test="${status.last}">						
										<input type="hidden" id="endItemid" value="${item.item_id}">
										<input type="hidden" id="endItemRank" value="${item.rank}">
									</c:when>
								</c:choose>
								<img style="width: 16px;" alt="移至本页末" title="移至本页末位" src="<%=request.getContextPath() %>/img/xia2.png">
							</a>
							<a onclick="movePosition(${item.item_id},${item.rank},3)">
								<img style="width: 16px;" alt="移至首位" title="移至首位" src="<%=request.getContextPath() %>/img/shang3.png" onclick="location.href='#'">
							</a>
							<a onclick="movePosition(${item.item_id},${item.rank},4)">
								<img style="width: 16px;" alt="移至末位" title="移至末位" src="<%=request.getContextPath() %>/img/xia3.png" onclick="location.href='#'">
							</a>
						</td>
                       	<td>
	                         <a href="<%=request.getContextPath()%>/admin/tag_manager/tag_item_update.jsp?id=${item.item_id}&parent=${parent}&tag_id=${tag_id}&tag_name=${tag_name}" class="model">修改</a>
	                       	<!-- 
	                       	<% String imgSaveName = Long.toString(System.nanoTime(), 36); %>
	                       	<form id="uploadImgForm${item.item_id}" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/reduceImg?imgsn=<%=imgSaveName%>" target="uploadIfr" >
	                             <input type="hidden" name="version" value="adImg" >
						    <input type="hidden" name="fileName" value="<%=imgSaveName%>">
	                             <input id="file${item.item_id}" type="file" name="imgFile" style="width:120px;"  />
	                             <input type="button" onclick="addImgName('file${item.item_id}','<%=imgSaveName%>','img${item.item_id}','uploadImgForm${item.item_id}')" value="上传" />
	                         </form>
	                         <input type="text"  name="img" id="img${item.item_id}" style="width:120px;">
	                         <input type="button" onclick="updateItemImg(${item.item_id})" value="更改">
	                         <a href="javascript:dialog(${item.item_id});" >修改信息</a>
	                         <iframe name="uploadIfr" id="uploadIfr"  style="display: none" onload="displayResult(this)"></iframe>
                       	</td>
	                       	 -->
	                </tr>
                   </c:forEach>
	    		</tbody>
	    	</table>
	    	<hr/>
	    	<input type="button" class="button" value="删除"  onclick="goto_del_item()"/>
	    	<hr/>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag_id}&tag_name=${tag_name}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	<script type="text/javascript">
	
	function dialog(id) {
		var val = window.showModalDialog("<%=request.getContextPath()%>/admin/tag_manager/tag_item_update.jsp?id="+id, 
		window, "dialogWidth:800px;status:no;dialogHeight:400px");
		
		if(!val){ 
			val= window.ReturnValue;
		} 
	}

	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>