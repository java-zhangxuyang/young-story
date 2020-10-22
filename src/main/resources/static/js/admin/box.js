$(function(){
	$('#datetimepicker2').datetimepicker({
        format: 'YYYY-MM-DD hh:mm',
        locale: moment.locale('zh-cn'),
        initialDate:new Date(),
        autoclose:true,
        language:'zh-CN'
    });
})

function checkcheck(id,name){
	if(id === undefined || name=== undefined) { // 只能用 === 运算来测试某个值是否是未定义的
       return true;
    }
    
    if(id == null || name=== null) { // 等同于 a === undefined || a === null
    	 return true;
    }

    
    // String    
    if(id == "" || id == null || id == undefined){ // "",null,undefined
    	 return true;
    }
    if(!$.trim(id)){ // "",null,undefined
    	 return true;
    }
    if(name == "" || name == null || name == undefined){ // "",null,undefined
    	 return true;
    }
    if(!$.trim(name)){ // "",null,undefined
    	 return true;
    }
}

function departureBox(){
	var id = $("#id").val();
	var name = $("#name").val();
	if(this.checkcheck(id,name)){
		layer.msg("请先选择包厢！");
		return;
	}
	layer.confirm('请提醒客人携带好随身物品！', {icon: 3, title:'提示'}, function(index){
		$.ajax({
            type: "post",
            data: {id:id},
            url: "/admin/departureBox",
            dataType: "json",
            success: function(data) {
            	if(data.code == -1){
                	layer.msg(data.msg);
                }else if(data.code == 1){
                	window.location.reload(); 
                }
            }
        });
	});
}
//使用包厢
function useBox(){
	var status = $("#status").val();
	if(status==2){
		layer.confirm('该包厢有预约，请注意时间分配！', {icon: 3, title:'提示'}, function(index){
			layer.close(index); //关闭当前窗口
			useBoxAction();
		});
	}else{
		useBoxAction();
	}
}

function useBoxAction(){
	var id = $("#id").val();
	var name = $("#name").val();
	if(checkcheck(id,name)){
		layer.msg("请先选择包厢！");
		return;
	}
	var content = '<div>\n' +
					'<form id="useBoxForm">'+
						'<input type="hidden" name="boxId" value="'+id+'">'+
						'<div class="form-group"  style="margin-top:5%;">'+
							'<label for="number" class="col-sm-2 control-label">号码牌</label>'+
							'<div class="col-sm-9">'+
								'<input type="text" class="form-control" id="number" name="number" placeholder="右侧号码牌"  autocomplete="off">'+
							'</div>'+
						'</div>'+
					    '<div class="form-group">'+
					    	'<label for="useDate" class="col-sm-2 control-label">使用时长</label>'+
					    	'<div class="col-sm-9">'+
					    		'<input type="number" class="form-control" id="useDate" name="useDate" value=1 min=1 placeholder="使用时长"  autocomplete="off">'+
					    	'</div>'+
					    '</div>'+
					    '<div class="form-group">'+
						    '<label for="people" class="col-sm-2 control-label">包厢人数</label>'+
						    '<div class="col-sm-9">'+
						    	'<input type="number" class="form-control" id="people" name="people" value=1 min=1 placeholder="包厢人数"  autocomplete="off">'+
						    '</div>'+
					    '</div>'+
					    '<div class="form-group">'+
						    '<label for="remind" class="col-sm-2 control-label">到时提醒</label>'+
						    '<div class="col-sm-9">'+
							    '<select class="form-control" name="remind">'+
							    	'<option value="1">是</option>'+
						    		'<option value="0">否</option>'+
								 '</select>'+
						    '</div>'+
					    '</div>'+
					    '<div class="form-group">'+
						    '<label for="remark" class="col-sm-2 control-label">备注</label>'+
						    '<div class="col-sm-9">'+
						    	'<textarea class="form-control" rows="3" name="remark"></textarea>'+
						    '</div>'+
					    '</div>'+
				'</form></div>';
    var index = layer.open({
        type: 1,
        title: name,
        area: ['550px', '400px'],
        shadeClose: true, //点击遮罩关闭
        content:content,
        btn:['确定','取消'],
        yes:function(){
        	var number = $("#number").val();
        	var useDate = $("#useDate").val();
        	var people = $("#people").val();
        	if(number==null || number<=0 ||useDate==null || useDate<=0 ||people==null || people<=0){
        		layer.msg("输入有误，请重试！");
        		return;
        	}
        	layer.confirm('请确认输入信息无误！', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
        			type: "POST",
        			data: $('#useBoxForm').serialize(),
        			url: "/admin/useBox",
        			dataType: "json",
        			success: function(data) {
        				if(data.code == -1){
        					layer.msg(data.msg);
        				}else if(data.code == 1){
        					window.location.reload(); 
        				}
        			}
        		});
        	});
        },
	    btn2:function(){
	        layer.closeAll(index); //关闭当前窗口
	    }
    });
}
//包厢预约
function makeBox(){
	var id = $("#id").val();
	var name = $("#name").val();
	if(this.checkcheck(id,name)){
		layer.msg("请先选择包厢！");
		return;
	}
	
	var content = '<div>\n' +
	'<form id="makeBoxForm">'+
	'<input type="hidden" name="boxId" value="'+id+'">'+
	'<div class="form-group"  style="margin-top:5%;">'+
	'<label for="date" class="col-sm-2 control-label">时间</label>'+
	
	'<div class="input-group date col-sm-9" id="datetimepicker" style="margin-left:19%;">'+
	'<input  type="text" class="form-control" name="toTime" id="toTime" placeholder="到场时间">'+
	'<span class="input-group-addon">'+
	'<span class="glyphicon glyphicon-calendar"></span></span>'+
	'</div>'+
	
	'</div>'+
	'<div class="form-group" style="margin-top:3%";>'+
	'<label for="number" class="col-sm-2 control-label">人数</label>'+
	'<div class="col-sm-9">'+
	'<input type="number" class="form-control" id="number" name="number" placeholder="人数"  value=1 min=1 autocomplete="off">'+
	'</div>'+
	'</div>'+
	'<div class="form-group">'+
	'<label for="mobile" class="col-sm-2 control-label">手机号</label>'+
	'<div class="col-sm-9">'+
	'<input type="mobile" class="form-control" id="mobile" name="mobile" placeholder="手机号"  autocomplete="off">'+
	'</div>'+
	'</div>'+
	'<div class="form-group">'+
	'<label for="remark" class="col-sm-2 control-label">备注</label>'+
	'<div class="col-sm-9">'+
	'<textarea class="form-control" rows="3" name="remark" placeholder="备注（预约女仆）"></textarea>'+
	'</div>'+
	'</div>'+
	'</form></div>';
	var index = layer.open({
		type: 1,
		title: name,
		area: ['550px', '400px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		success: function (layero, index) { // 弹窗成功
			$(layero).find('#datetimepicker').datetimepicker({
		        format: 'yyyy-mm-dd hh:ii',
		        locale: moment.locale('zh-cn'),
		        initialDate:new Date(),
		        startDate:new Date(),
		        autoclose:true,
		        language:'zh-CN'
		    });
		},
		yes:function(){
			var toTime = $("#toTime").val();
			var number = $("#number").val();
			var mobile = $("#mobile").val();
			if(toTime==null || toTime=="" ||number==null || number<=0 || mobile==null || mobile==""){
				layer.msg("输入有误，请重试！");
				return;
			}
			if(!(/^1[0-9]{10}$/.test(mobile))){ 
				layer.msg("手机号码有误，请重填");  
		        return; 
		    } 
			layer.confirm('请确认输入信息无误！', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#makeBoxForm').serialize(),
					url: "/admin/makeBox",
					dataType: "json",
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							window.location.reload(); 
						}
					}
				});
			});
		},
		btn2:function(){
			layer.closeAll(index); //关闭当前窗口
		}
	});
}
//包厢改名
function updateBoxName(){
	var id = $("#id").val();
	var name = $("#name").val();
	if(this.checkcheck(id,name)){
		layer.msg("请先选择包厢！");
		return;
	}
	
	var content = '<div>\n' +
	'<form id="updateBoxNameForm">'+
	'<input type="hidden" name="id" value="'+id+'">'+
	'<div class="form-group"  style="margin-top:5%;">'+
	'<label for="name" class="col-sm-3 control-label">新名称</label>'+
	'<div class="col-sm-8">'+
	'<input type="text" class="form-control"  name="name" id="newname" placeholder="请输入新名称"  maxlenth=4 autocomplete="off">'+
	'</div>'+
	'</div>'+
	'</form></div>';
	var index = layer.open({
		type: 1,
		title: name,
		area: ['400px', '200px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		yes:function(){
			var newname = $("#newname").val();
			if(name==null || name==""){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认将 '+name+' 更改为 '+newname+' 吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#updateBoxNameForm').serialize(),
					url: "/admin/updateBoxName",
					dataType: "json",
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							window.location.reload(); 
						}
					}
				});
			});
		},
		btn2:function(){
			layer.closeAll(index); //关闭当前窗口
		}
	});
}
//包厢续时
function continuation(){
	var id = $("#id").val();
	var name = $("#name").val();
	if(this.checkcheck(id,name)){
		layer.msg("请先选择包厢！");
		return;
	}
	
	var content = '<div>\n' +
	'<form id="continuationForm">'+
	'<input type="hidden" name="id" value="'+id+'">'+
	'<div class="form-group"  style="margin-top:5%;">'+
	'<label for="name" class="col-sm-3 control-label">时长</label>'+
	'<div class="col-sm-8">'+
	'<input type="number" class="form-control"  name="useDuration" value=1 id="useDuration" placeholder="续时时长（小时）"   autocomplete="off">'+
	'</div>'+
	'</div>'+
	'</form></div>';
	var index = layer.open({
		type: 1,
		title: name,
		area: ['400px', '200px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		yes:function(){
			var useDuration = $("#useDuration").val();
			if(useDuration==null || useDuration <= 0){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认为 '+name+' 续时 '+useDuration+'小时 吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#continuationForm').serialize(),
					url: "/admin/continuation",
					dataType: "json",
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							window.location.reload(); 
						}
					}
				});
			});
		},
		btn2:function(){
			layer.closeAll(index); //关闭当前窗口
		}
	});
}


function box_check(id,name,status,remind){
	$('.baoxiang').css({border:''});
	$("#baoxiang"+id).css({border:'2px solid black'});
	$("#id").val(id);
	$("#name").val(name);
	$("#status").val(status);
	if(status == 0){
		$(".boxbutton").show();
		$("#continuation").hide();
		$("#departureBox").hide();
	}else if(status == 1){
		$(".boxbutton").show();
		$("#useBox").hide();
		$("#makeBox").hide();
		if(remind == 0){
			$("#continuation").hide();
		}
	}else if(status == 2){
		$(".boxbutton").show();
		$("#continuation").hide();
		$("#departureBox").hide();
	}
}

function countDown(name,maxtime,fn ) {  
	if(maxtime <= 0){
		fn("时间到!"); 
		return;
	}
	var timer = setInterval(function() { 
		if( !!maxtime ){  
		    var day = Math.floor(maxtime / 86400),
		    hour = Math.floor((maxtime % 86400) / 3600),
		    minutes = Math.floor((maxtime % 3600) / 60), 
		    seconds = Math.floor(maxtime%60), 
		    msg = hour+"时"+minutes+"分"+seconds+"秒"; 
		    if(hour==0 && minutes==5 && seconds==0){
		    	layer.confirm(name+' 的时间剩余五分钟，请注意提醒！', {icon: 3, title:'提醒', btn : ['确定']}, function(index){
		    		layer.close(index);
		    	});
		    	var audio= new Audio("http://mp3.9ku.com/hot/2013/08-14/554695.mp3");//这里的路径写上mp3文件在项目中的绝对路径
		    	audio.play();
		    	/*$.ajax({
		            type: "GET",
		            url: "/admin/boxRemind",
		            data: {text:"時間が来ます！"},
		            dataType: "json"
		        });*/
		    	
		    }
		    fn( msg ); 
		    --maxtime;  
		} else {  
		    clearInterval( timer ); 
		    fn("时间到!"); 
		}  
	 }, 1000); 
} 
function countup(mintime,fn ) {  
	var timer = setInterval(function() { 
			var day = Math.floor(mintime / 86400),
			hour = Math.floor((mintime % 86400) / 3600),
			minutes = Math.floor((mintime % 3600) / 60), 
			seconds = Math.floor(mintime%60), 
			msg = hour+"时"+minutes+"分"+seconds+"秒"; 
			fn( msg ); 
			mintime ++ ;  
			//clearInterval( timer ); 
	}, 1000); 
} 
	

