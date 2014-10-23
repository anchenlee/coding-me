<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增活动</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate_pack.js"></script>
<script type="text/javascript">
        function convert() 
        { 
       		var from_channel = $("#from_channel").val();
       		var item_id = $("#item_id").val();
       		if (item_id == "")
       		{
           		alert("不允许为空！");
        	}
       		else
       		{
        		$.ajax({
        	    	url: '<%=request.getContextPath()%>/ad/ad_manager?actionmethod=urlConvert&from_channel=' + from_channel + '&item_id=' + item_id,
        	    	type: 'GET',
        	    	dataType: 'json',
        	    	timeout: 5000,
        	    	success: function(json)
        	    	{
        	     		var status = json.status;
        	     		if (status == "1000")
        	     		{
            	     		var iphone_url = json.iphone_url;
            	     		if(iphone_url == null || iphone_url == "")
            	     		{
            	     			$("#result_iphone").val("无IPHONE返利链接");
                	     	}
            	     		else
            	     		{
        	     				$("#result_iphone").val(iphone_url);
                	     	}
            	     		
            	     		var android_url = json.android_url;
            	     		if(android_url == null || android_url == "")
            	     		{
            	     			$("#result_android").val("无ANDROID返利链接");
                	     	}
            	     		else
            	     		{
        	     				$("#result_android").val(android_url);
                	     	}
            	     		
            	     		var ipad_url = json.ipad_url;
            	     		if(ipad_url == null || ipad_url == "")
            	     		{
            	     			$("#result_ipad").val("无IPAD返利链接");
                	     	}
            	     		else
            	     		{
        	     				$("#result_ipad").val(ipad_url);
                	     	}
            	     		
            	     		var all_url = json.all_url;
            	     		if(all_url == null || all_url == "")
            	     		{
            	     			$("#result_all").val("无ALL返利链接");
                	     	}
            	     		else
            	     		{
        	     				$("#result_all").val(all_url);
                	     	}
            	     	}
        	     		else
        	     		{
            	    		alert("链接转换失败，请联系技术人员！");
                		}
        			}
        	   });
          }
        }
        
        function changeType(){
        	var type = $("#from_channel").val();
        	if(type == 'address'){
        		document.getElementById("zhuanhuanleixin").innerHTML = "转换地址:";
        	}else{
        		document.getElementById("zhuanhuanleixin").innerHTML = "商品ID：";
        	}
        }
        
    </script>
<style type="text/css">
	.onecolumn {
		width: 95%;
		background: #fff;
		border: 1px solid #cdcdcd;
		margin: 20px 20px 20px 13px;
		-moz-border-radius: 5px;
		-webkit-border-radius: 5px;
		-moz-box-shadow: 0 1px 2px #ccc;
		-webkit-box-shadow: 0 1px 2px #ccc;
		box-shadow: 0 1px 2px #ccc;
	}
	
	.onecolumn .header {
		margin: 0;
		padding: 0;
		float: left;
		background: #cdcdcd url("<%=request.getContextPath()%>/img/bg_window_header.png") repeat-x;
		width: 100%;
		height: 40px;
		border-bottom: 1px solid #ccc;
		-moz-border-radius-topright: 5px;
		-webkit-border-top-right-radius: 5px;
		-moz-border-radius-topleft: 5px;
		-webkit-border-top-left-radius: 5px;
	}
	
	.onecolumn .header span {
		display: block;
		font-size: 16px;
		font-weight: bold;
		padding: 10px 0 0 15px;
		float: left;
		width: auto;
		cursor: n-resize;
	}
	
	table {
		text-align: left;
		margin: auto;
		padding: auto;
	}
	
	th, td {
		margin: 0;
		padding: 0;
		border: 0;
		font-weight: inherit;
		font-style: inherit;
		font-size: 100%;
		font-family: inherit;
		vertical-align: baseline;
	}
</style>
</head>
<body >
 <!-- Begin one column window -->
<div class="onecolumn">
<div class="header"> <span>链接转换</span></div>
<div class="content">
<br />
<br />
<br />
<table width="500" border="1" cellpadding="0" cellspacing="0">
	<tbody>
	  <tr height="30px">
	    <td width="70" align="right">
	    	<label>类型:</label>
	    </td>
	    <td>
	    	<select id="from_channel" name="from_channel" style="float: left" onchange="changeType()">
	    		<option value="6" selected>广告</option>
	    		<option value="13">退出广告</option>
	    		<option value="11">推送</option>
	    		<option value="8">特惠</option>
	    		<option value="9">推荐</option>
	    		<option value="12">礼物</option>
	    		<option value="address">链接地址转换</option>
	    	</select>
	    </td>
	  </tr>
	  <tr height="50px">
	    <td width="70" align="right">
	    	<label id="zhuanhuanleixin">商品ID：</label>
	    </td>
	    <td style="text-align: left">
	    	<input type="text" name="item_id" id="item_id" style="width:200px;" style="color: #FF0000;width:400px;">
	    </td>
	  </tr>
	  <tr height="50px">
	    <td width="70" align="right">
	    	&nbsp;
	    </td>
	    <td align="left">
			<input type="button" id="btn" value="转换" onclick="convert();"  style="float: left">
	    </td>
	  </tr>
	  <tr height="50px">
	    <td width="70" align="right">
	    	<label><font color="red">ALL：</font></label>
	    </td>
	    <td style="text-align: left">
			<input type="text" id="result_all" style="color: #FF0000;width:400px;" />
	    </td>
	  </tr>
	  <tr height="50px">
	    <td width="70" align="right">
	    	<label><font color="red">IPHONE：</font></label>
	    </td>
	    <td style="text-align: left">
			<input type="text" id="result_iphone" style="color: #FF0000;width:400px;" />
	    </td>
	  </tr>
	  <tr height="50px">
	    <td width="70" align="right">
	    	<label><font color="red">ANDROID：</font></label>
	    </td>
	    <td style="text-align: left">
			<input type="text" id="result_android" style="color: #FF0000;width:400px;" />
	    </td>
	  </tr>
	  <tr height="50px">
	    <td width="70" align="right">
	    	<label><font color="red">IPAD：</font></label>
	    </td>
	    <td style="text-align: left">
			<input type="text" id="result_ipad" style="color: #FF0000;width:400px;" />
	    </td>
	  </tr>
  </tbody>	
</table>
<br />
<br>
</div>
</div>
</body>
</html>
