var array_lt = [];
var son=[];
var con=[];
var minX;
var minY;
var maxX;
var maxY;
var div_num;
var parent;
var delNum;
var array_coor=[];
$(document).ready(function(){
     $('body').delegate('.mar div','click',function(){
         div_num = this.className.substr(4,this.className.length-4);
         parent = $(this).parent().attr('class');
         parent = parent.substr(4,parent.length-4);
         delNum = this.className.substr(4,this.className.length-4);
         $(this).attr("name","active");
         $(this).attr("id","current");


         if(this.className.length==5&&son.indexOf(delNum)==-1){
                 son.push(delNum);
         }
         else if(this.className.length>5&&con.indexOf(delNum.split(""))==-1){
                con = delNum.split("");
         }
     });

     $('.combine').bind('click',function(){
         var lt;
         son.sort();//将数据从小到大进行排序
         for(var i=0;i<son.length;i++){
             lt = {
                 x:getX(son[i]),
                 y:getY(son[i])
             };
             array_lt.push(lt);//获取左上角坐标
         }

         var wid = $('.span'+son[0]).position().left;
         var hig = $('.span'+son[0]).position().top;
         var len = array_lt.length-1;

         switch(son.length){
             case 0:
             case 1:
             case 5:
                 alert('操作错误！');
                 window.location.reload();
                 break;
             case 2:
                 if((array_lt[0].x == array_lt[1].x)&&(Math.abs(array_lt[0].y - array_lt[1].y)==1)){
                     $('.'+parent+' div[name="active"]').remove();
                     $('<div class=\'span'+son[0]+son[1]+'\'>'+son[0]+son[1]+'</div>').appendTo('.'+parent+'');
                 }else if(array_lt[0].y == array_lt[1].y){
                     $('.'+parent+' div[name="active"]').remove();
                     $('<div class=\'span'+son[0]+son[1]+'\'>'+son[0]+son[1]+'</div>').appendTo('.'+parent+'');
                 }else{
                     alert("无法合并！");
                     window.location.reload();
                 }
                 break;
             case 3:
                 if(array_lt[0].x == array_lt[1].x && array_lt[0].x == array_lt[2].x){
                     $('.'+parent+' div[name="active"]').remove();
                     $('<div class=\'span'+son[0]+son[1]+son[2]+'\'>'+son[0]+son[1]+son[2]+'</div>').appendTo('.'+parent+'');
                 }
                 else{
                     alert("无法合并！");
                     window.location.reload();
                 }
                 break;
             case 4:
                 //合并条件  操作
                 var x=0;
                 var y=0;
                 for(var i=0;i<son.length;i++){
                     x+=array_lt[i].x;
                     y+=array_lt[i].y;
                 }
                 if((x==2 && y==2)||(x==2 && y==6)){
                     $('.'+parent+' div[name="active"]').remove();
                     $('<div class=\'span'+son[0]+son[1]+son[2]+son[3]+'\'>'+son[0]+son[1]+son[2]+son[3]+'</div>').appendTo('.'+parent+'');
                 } else{
                     alert("无法合并！");
                     window.location.reload();
                 }
                 break;
             case 6:
                 //合并操作
                 $('.'+parent+' div[name="active"]').remove();
                 $('<div class="span123456" style="width: 98%;">123456</div>').appendTo('.'+parent+'');
                 $('.span123456').css({"top":hig,"left":wid});
                 break;
             default :
             alert('操作错误!!!请重试');
//             window.location.reload();
         }
         array_lt=[];
         son=[];
         console.log(delNum);
     });

    $('.separate').bind('click',function(){
        con.sort();

        switch(con.length){
            case 2:
                $('.'+parent+' .span'+con[0]+con[1]+'').remove();
                $('<div class=\'span'+con[0]+'\'>'+con[0]+'</div><div class=\'span'+con[1]+'\'>'+con[1]+'</div>').appendTo('.'+parent+'');
                break;
            case 3:
                $('.'+parent+' .span'+con[0]+con[1]+con[2]+'').remove();
                $('<div class=\'span'+con[0]+'\'>'+con[0]+'</div><div class=\'span'+con[1]+'\'>'+con[1]+'</div>' +
                    '<div class=\'span'+con[2]+'\'>'+con[2]+'</div>').appendTo('.'+parent+'');
                break;
            case 4:
                $('.'+parent+' .span'+con[0]+con[1]+con[2]+con[3]+'').remove();
                $('<div class=\'span'+con[0]+'\'>'+con[0]+'</div><div class=\'span'+con[1]+'\'>'+con[1]+'</div>' +
                    '<div class=\'span'+con[2]+'\'>'+con[2]+'</div><div class=\'span'+con[3]+'\'>'+con[3]+'</div>').appendTo('.'+parent+'');
                break;
            case 6:
                $('.'+parent+' .span'+con[0]+con[1]+con[2]+con[3]+con[4]+con[5]+'').remove();
                $('<div class=\'span'+con[0]+'\'>'+con[0]+'</div><div class=\'span'+con[1]+'\'>'+con[1]+'</div>' +
                    '<div class=\'span'+con[2]+'\'>'+con[2]+'</div><div class=\'span'+con[3]+'\'>'+con[3]+'</div>' +
                    '<div class=\'span'+con[4]+'\'>'+con[4]+'</div><div class=\'span'+con[5]+'\'>'+con[5]+'</div>').appendTo('.'+parent+'');
                break;
            default :
                alert("操作错误！！！");
//                window.location.reload();
        }
        con=[];

        var martix = parent.substr(3,parent.length-3);
        console.log('martix='+martix+'delNum='+delNum);
        $.ajax({
            url: "../../tags/ipad_tags_action?method=delIpadTags",
            dataType:"json",
            type: "POST",
            data: '&martix='+martix+'&divIds='+delNum,
            success: function(ret){
                alert("分离成功");
            }
        });
        con=[];
    });

    $("#uploadButton").click(function(){
        var imgName = Math.floor(Math.random()*1000000000+1);
        var filename = $("#filename").val();
        var suffix = filename.substring(filename.lastIndexOf("."));
        var prefix = "http://static.etouch.cn/suishou/ad_img/";
        var imgUrl = prefix+imgName+suffix;
        $("#imgNote").val(imgUrl);
        document.getElementById("uploadImgForm").action = 'http://a.suishouyouhui.cn/img/upload?imgsn=' + imgName;
        $("#uploadImgForm").submit();
    });


    $("#uploadButtonReg").click(function(){
        var imgName = Math.floor(Math.random()*1000000000+1);
        var filename = $("#filenameReg").val();
        var suffix = filename.substring(filename.lastIndexOf("."));
        var prefix = "http://static.etouch.cn/suishou/ad_img/";
        var imgUrl = prefix+imgName+suffix;
        $("#imgReg").val(imgUrl);
        document.getElementById("uploadRegImgForm").action = 'http://a.suishouyouhui.cn/img/upload?imgsn=' + imgName;
        $("#uploadRegImgForm").submit();
    });



    /*保存按钮事件*/
    $("#save").click(function(){
        var name = $(this).attr("name");
        if(name=="saveAdd"){
            saveAdd();
        }else{
            saveUpdate();
        }
        $('.'+parent+' .span'+delNum+'').removeAttr('id');
    });
    $("#close").click(function(){
        $('.'+parent+' .span'+delNum+'').removeAttr('id');
    });


    $('.add').click(function(){
        $(".modal").attr('id','myModalAdd');
        $("#save").attr('name','saveAdd');
        $(".modal-title").html('添加信息');
        var tagId =  $('.'+parent+' .span'+delNum+' img').attr('id');
        if((delNum=='')||(tagId != undefined)){
            alert("请选择要添加的位置！可能该处已有内容");
        }
        else{
            $(':input','#uploadImgForm').not(':button').val('');
            $('select').val("");
            $('#myModalAdd').modal({
                keyboard: false
            });
        }
    });

    function getActionValue(action_type){
    	var action_value = "";
    	if(action_type == 'tagStyleList' || action_type == 'tagStyleWaterflow' || action_type == 'tagStyleGrid' 
    		|| action_type == 'tagStyleSingle' || action_type == 'tagStyleCategory' || action_type=='tagStyleMix'
    		|| action_type == 'tagStyleWaterFlowDouble' || action_type == 'tagStyleSale')
    	{
    		action_value = $("#action_value").val();
    	}
    	else if(action_type == 'tagStyleAppPage') 
    	{
    		action_value = $("#sel_system").val();
    	}
    	else 
    	{
    		action_value = $("#inputaction_value").val();
    	}
    	
    	return action_value;
    }


    /*更新*/
    $('.update').click(function(){
        $(".modal").attr('id','myModalUpdate');
        $("#save").attr('name','saveUpdate');
        $(".modal-title").html('更新信息');
        var id =  $('.'+parent+' .span'+delNum+' img').attr('id');
        var imgLink = $('.'+parent+' .span'+delNum+' img').attr('src');
        if(id == undefined || imgLink==undefined){
            alert("该处没有内容，无法更新！");
        }else{
            $.ajax({
                url: "../../tags/ipad_tags_action?method=getTagsById",
                type: "POST",
                dataType: 'json',
                data: '&id='+id,
                success: function(ret){
                    var jsonUpdate = ret;
                    $('#myModalUpdate #action_type').val(jsonUpdate.actionType);
                    var action_type =  jsonUpdate.actionType;
                    if(action_type == 'tagStyleList' || action_type == 'tagStyleWaterflow' || action_type == 'tagStyleGrid' 
                		|| action_type == 'tagStyleSingle' || action_type == 'tagStyleCategory' || action_type=='tagStyleMix'
                		|| action_type == 'tagStyleWaterFlowDouble' || action_type == 'tagStyleSale'){
                    	 $('#myModalUpdate  #action_value').val(jsonUpdate.actionValue);
                    	 eval("action_value.style.display=\"block\"");
                    	 eval("inputaction_value.style.display=\"none\"");
                    	 eval("sel_system.style.display=\"none\"");
                	}else if(action_type == 'tagStyleAppPage') {
                		 $('#myModalUpdate  #sel_system').val(jsonUpdate.actionValue);
                		 eval("action_value.style.display=\"none\"");
                    	 eval("inputaction_value.style.display=\"none\"");
                    	 eval("sel_system.style.display=\"block\"");
                	}else {
                		$('#myModalUpdate  #inputaction_value').val(jsonUpdate.actionValue);
                		eval("action_value.style.display=\"none\"");
	                   	eval("inputaction_value.style.display=\"block\"");
	                   	eval("sel_system.style.display=\"none\"");
                	}
                    
                    $('#myModalUpdate  #action_value').val(jsonUpdate.actionValue);
                    $('#imgNote').val(jsonUpdate.img);
                    $('#startTime').val(jsonUpdate.regularStartDate);
                    $('#endTime').val(jsonUpdate.regularEndDate);
                    $('#imgReg').val(jsonUpdate.regularTimeImg);
                    $('#myModalUpdate').modal({
                        keyboard: false
                    });
                }
            });
        }
    });
    
    /*删除矩阵*/
    $('.delMartix').click(function(){
	    	var k = $("input[type='checkbox']:checked");
	    	var ids = '';
	    	for ( var i = 0; i < k.length; i++) {
	    		ids += k[i].id + ',';
			}
	    	console.debug(ids);
	    	$.ajax({
	            url: "../../tags/ipad_tags_action?method=delMartix",
	            type: "POST",
	            data: '&martixIds='+ids,
	            success: function(ret){
	               window.location.reload();
	            }
	        });
    });
    
    /*添加矩阵*/
    $('.addMartix').click(function(){
    	$.ajax({
            url: "../../tags/ipad_tags_action?method=addMartix",
            type: "POST",
            success: function(ret){
               window.location.reload();
            }
        });
    });
    
    function saveAdd(){
    	var action_type = $("#action_type option:selected").val();
        var action_value = getActionValue(action_type);
        var img = $("#imgNote").val();
        var imgReg = $("#imgReg").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var martix = parent.substr(parent.length-1,1);
        var div_ids = div_num;
        var num =[];
        var coor1;
        var coor2;
        num = div_num.split("");
        num.sort();
        var num_len = num.length-1;
        coor1 = {
            x:getX(num[0]),
            y:getY(num[0])
        };
        coor2 = {
            x:getX(num[num_len]),
            y:getY(num[num_len])
        };
        var x = coor1.x + "," + coor1.y;
		var y = Number(coor2.x+1)+ "," + Number(coor2.y+1);
//        array_coor.push(coor1,coor2);//获取左上角坐标
//        var x = array_coor[0].x+','+array_coor[0].y;
//        var y = array_coor[1].x+1+','+Number(array_coor[1].y+1);
        console.log(x,y);
        $.ajax({
            url: "../../tags/ipad_tags_action?method=addIpadTags",
            dataType: 'json',
            type: "POST",
            data: 'action_value=' + action_value + '&action_type='+action_type+'&martix='+martix+'&img='+img+'&x='+x+'&y='+y+'&div_ids='+div_ids + '&regular_time_img=' + imgReg + '&regular_start_time=' + startTime + '&regular_end_time=' + endTime,
            success: function(ret){
            	var st = new Date(startTime).getTime();
            	var et = new Date(endTime).getTime();
            	var cur = Date.parse(new Date());
            	var showImg = img;
            	if (cur >= st && cur <= et && showImg != "") {
					showImg = imgReg;
				}
                $('<img id=\''+ret.id+'\' src=\''+showImg+'\'>').appendTo('.'+parent+' .span'+delNum+'');
                $('#myModalAdd').modal('hide');
//                window.location.reload();
            }
        });
        son=[];
    }

    function saveUpdate(){
    	var action_type = $("#action_type option:selected").val();
        var action_value = getActionValue(action_type);
        var img = $("#imgNote").val();
        var imgReg = $("#imgReg").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var martix = parent.substr(parent.length-1,1);
        var div_ids = delNum;
        $.ajax({
        url: "../../tags/ipad_tags_action?method=updateIpadTags",
        type: "POST",
        data: 'action_value=' + action_value + '&action_type='+action_type+'&martix='+martix+'&img='+img+'&div_ids='+div_ids + '&regular_time_img=' + imgReg + '&regular_start_time=' + startTime + '&regular_end_time=' + endTime,
        success: function(ret){
//            console.log(ret);
            $('.'+parent+' .span'+delNum+' img').attr("src",ret);
            $('#myModalUpdate').modal('hide');
            window.location.reload();
        }
    });
    }

    function getX(num){  //获取左上角横坐标
        if(parseInt(num %2) == 0){
            return 1;
        }else{
            return 0;
        }
    }
    function getY(num){//获取左上角纵坐标
        return ( parseInt((num-1)/2));
    }

});