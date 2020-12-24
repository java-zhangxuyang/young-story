$(function(){
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
            url: "/admin/box/departureBox",
            dataType: "json",
            success: function(data) {
            	if(data.code == -1){
                	layer.msg(data.msg);
                }else if(data.code == 1){
                	layer.closeAll(layer.indexmen);
                	layer.msg("操作成功", { time: 500 }, function () {
	                    window.location.reload(); 
	                }); 
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
	$.ajax({
		type: "POST",
		url: "/admin/passFlow/getPassFlowList",
		dataType: "json",
		async: false,
		success: function(data) {
			if(data.code == -1){
				layer.msg(data.msg);
			}else if(data.code == 1){
				passFlowList = data.payload;
			}
		}
	});
	var passFlowContent = '';
	if(passFlowList != null && passFlowList.length > 0){
		for (var i=0;i<passFlowList.length;i++){  
			passFlowContent += '<option value="'+passFlowList[i].number+'">'+passFlowList[i].number+'</option>';
		}
	}
	var content = '<div>\n' +
					'<form id="useBoxForm">'+
						'<input type="hidden" name="boxId" value="'+id+'">'+
						'<div class="form-group"  style="margin-top:5%;">'+
							'<label for="number" class="col-sm-2 control-label">号码牌</label>'+
							'<div class="col-sm-9">'+
								/*'<input type="text" class="form-control" id="number" name="number" placeholder="右侧号码牌"  autocomplete="off">'+*/
							    '<select class="form-control" name="number" id="number">'+passFlowContent+
								 '</select>'+
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
						    '<label for="remind" class="col-sm-2 control-label">计时方式</label>'+
						    '<div class="col-sm-9">'+
							    '<select class="form-control" name="remind">'+
							    	'<option value="1">倒计时</option>'+
						    		'<option value="0">正计时</option>'+
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
        	if(number==null ||useDate==null || useDate<=0 ||people==null || people<=0){
        		layer.msg("输入有误，请重试！");
        		return;
        	}
        	layer.confirm('请确认输入信息无误！', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
        			type: "POST",
        			data: $('#useBoxForm').serialize(),
        			url: "/admin/box/useBox",
        			dataType: "json",
        			success: function(data) {
        				if(data.code == -1){
        					layer.msg(data.msg);
        				}else if(data.code == 1){
        					layer.closeAll(layer.indexmen);
        					layer.msg("操作成功", { time: 500 }, function () {
			                    window.location.reload(); 
			                });
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
	'<textarea class="form-control" rows="3" name="back1" placeholder="备注（预约女仆）"></textarea>'+
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
					url: "/admin/box/makeBox",
					dataType: "json",
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							layer.closeAll(layer.indexmen);
							layer.msg("操作成功", { time: 500 }, function () {
			                    window.location.reload(); 
			                });
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
					url: "/admin/box/updateBoxName",
					dataType: "json",
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							layer.closeAll(layer.indexmen);
							layer.msg("操作成功", { time: 500 }, function () {
			                    window.location.reload(); 
			                });
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
					url: "/admin/box/continuation",
					dataType: "json",
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							layer.closeAll(layer.indexmen);
							layer.msg("操作成功", { time: 500 }, function () {
			                    window.location.reload(); 
			                });
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
		$("#statusRecovery").hide();
	}else if(status == 1){
		$(".boxbutton").show();
		$("#useBox").hide();
		$("#makeBox").hide();
		$("#statusRecovery").hide();
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
		            url: "/admin/box/boxRemind",
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

/**************************客流*********************************/
//添加客流
function addPassFlow(){
	var content = '<div>\n' +
	'<form id="addPassFlowForm">'+
	'<div class="form-group"  style="margin-top:5%;">'+
	'<label for="people" class="col-sm-3 control-label">人数</label>'+
	'<div class="col-sm-8">'+
	'<input type="number" class="form-control"  name="people" value=1  min=1 id="people" placeholder="人数"   autocomplete="off">'+
	'</div>'+
	'</div>'+
	'</form></div>';
	var index = layer.open({
		type: 1,
		title: '欢迎光临',
		area: ['400px', '200px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		yes:function(){
			var people = $("#people").val();
			if(people==null || people <= 0){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认添加来客为 '+people+'人 吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#addPassFlowForm').serialize(),
					url: "/admin/passFlow/addPassFlow",
					dataType: "json",
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							layer.closeAll(layer.indexmen);
							layer.msg("操作成功", { time: 500 }, function () {
			                    window.location.reload(); 
			                });
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
	
//添加推荐人
function addRecommender(id,number){
	$.ajax({
		type: "POST",
		url: "/admin/staff/getStaffList",
		data:{type:1},
		dataType: "json",
		async: false,
		success: function(data) {
			if(data.code == -1){
				layer.msg(data.msg);
			}else if(data.code == 1){
				staffList = data.payload;
			}
		}
	});
	var staffContent='';
	if(staffList != null && staffList.length > 0){
		for (var i=0;i<staffList.length;i++){  
			staffContent += '<option value="'+staffList[i].userName+'" >'+staffList[i].userName+'</option>';
		}
	}
	var content = '<div>\n' +
	'<form id="addRecommenderForm">'+
	'<input type="hidden" name="id" value="'+id+'">'+
	'<div class="form-group"  style="margin-top:5%;">'+
	'<label for="back1" class="col-sm-3 control-label">推荐人</label>'+
	'<div class="col-sm-8">'+
		'<select class="form-control" name="back1" id="back1">'+
			'<option value="" disabled selected hidden></option>'+
			staffContent+
		 '</select>'+
	'</div>'+
	'</div>'+
	'</form></div>';
	var index = layer.open({
		type: 1,
		title: '员工推荐',
		area: ['400px', '200px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		yes:function(){
			var back1 = $("#back1").val();
			if(back1==null){
				layer.msg("请选择推荐人！");
				return;
			}
			layer.confirm('确认该来客的推荐人为 '+back1+' 吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#addRecommenderForm').serialize(),
					url: "/admin/passFlow/addRecommender",
					dataType: "json",
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							layer.closeAll(layer.indexmen);
							layer.msg("操作成功", { time: 500 }, function () {
								window.location.reload(); 
							});
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

//女仆消费
function consumption(id,number){
	var content = '<div>\n' +
					'<form id="consumptionForm">'+
						'<input type="hidden" name="passId" value="'+id+'">'+
						'<div class="form-group"  style="margin-top:5%;">'+
							'<label for="number" class="col-sm-2 control-label">号码牌</label>'+
							'<div class="col-sm-9">'+
								'<input type="text" class="form-control" id="passId" name="passId" value="'+number+'" placeholder="号码牌"  autocomplete="off" readonly="readonly">'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
						'<label for="type" class="col-sm-2 control-label">消费类型</label>'+
						'<div class="col-sm-9">'+
						'<select class="form-control" name="type" id="type">'+
						'<option value="1">入场</option>'+
						'<option value="2">包厢</option>'+
						'<option value="3" selected>女仆</option>'+
						'<option value="4">其他</option>'+
						'</select>'+
						'</div>'+
						'</div>'+
						'<div class="form-group hidediv hidedivboxtype" style="display:none;">'+
						'<label for="type" class="col-sm-2 control-label">包厢类型</label>'+
						'<div class="col-sm-9">'+
						'<select class="form-control" name="boxtype" id="boxtype">'+
							'<option value="1">小包</option>'+
							'<option value="2">大包</option>'+
							'<option value="3">狼人杀</option>'+
							'<option value="4">剧本杀</option>'+
						'</select>'+
						'</div>'+
						'</div>'+
					'<div class="form-group hidediv hidedivuseTime">'+
						'<label for="number" class="col-sm-2 control-label">时长</label>'+
						'<div class="col-sm-9">'+
							'<input type="number" class="form-control" id="useTime" name="back3" min=1 value=1  placeholder="时长" autocomplete="off">'+
						'</div>'+
					'</div>'+
					'<div class="form-group hidediv hidedivpeople" style="display:none;">'+
					'<label for="people" class="col-sm-2 control-label">人数</label>'+
					'<div class="col-sm-9">'+
					'<input type="number" class="form-control"  name="people" value=1 id="people" min=1 placeholder="人数"   autocomplete="off">'+
					'</div>'+
					'</div>'+
					'<div class="form-group hidediv hidedivback2">'+
					'<label for="number" class="col-sm-2 control-label">女仆数量</label>'+
					'<div class="col-sm-9">'+
						'<input type="number" class="form-control" id="num" name="back2" min=1 value=1 placeholder="女仆数量" autocomplete="off">'+
					'</div>'+
					'</div>'+
					    '<div class="form-group">'+
					    	'<label for="money" class="col-sm-2 control-label">金额</label>'+
					    	'<div class="col-sm-9">'+
					    		'<input type="money" class="form-control" id="money" name="money" min=1 value="58" placeholder="消费金额"  autocomplete="off"  readonly="readonly">'+
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
        title: '消费',
        area: ['550px', '450px'],
        shadeClose: true, //点击遮罩关闭
        content:content,
        btn:['确定','取消'],
        success: function (layero, index) { // 弹窗成功
        	$(document).on('change', '#num', function() { 
        		var num = $("#num").val();
        		var useTime = $("#useTime").val();
        		$("#money").val(58 * num * useTime);
        	});
        	$(document).on('change', '#useTime', function() {
        		//TODO
        		if($("#type").val() == 2){
        			var boxMoney=0;
        			if($("#boxtype").val() == 1){
        				boxMoney=38;
        			}else if($("#boxtype").val() == 2){
        				boxMoney=58;
        			}else if($("#boxtype").val() == 3){
        				boxMoney=118;
        			}else if($("#boxtype").val() == 4){
        				boxMoney=108;
        			}
        			var useTime = $("#useTime").val();
        			$("#money").val(boxMoney * useTime);
        		}else if($("#type").val() == 3){
        			var num = $("#num").val();
        			var useTime = $("#useTime").val();
        			$("#money").val(58 * num * useTime);
        		}
        	});
        	$(document).on('change', '#boxtype', function() { 
        		var boxMoney=0;
    			if($("#boxtype").val() == 1){
    				boxMoney=38;
    			}else if($("#boxtype").val() == 2){
    				boxMoney=58;
    			}else if($("#boxtype").val() == 3){
    				boxMoney=118;
    			}else if($("#boxtype").val() == 4){
    				boxMoney=108;
    			}
        		var useTime = $("#useTime").val();
        		$("#money").val(boxMoney * useTime);
        	});
        	$(document).on('change', '#people', function() { 
        		var people = $("#people").val();
        		$("#money").val(28 * people);
        	});
        	$(document).on('change', '#type', function() { 
        		if($("#type").val()==4){
        			$(".hidediv").hide();
        			$("#money").removeAttr("readonly");
        			$("#money").val("");
        		}else if($("#type").val()==3){
        			$(".hidediv").hide();
        			$("#useTime").val(1);
        			$(".hidedivuseTime").show();
        			$(".hidedivback2").show();
        			$("#money").attr("readonly","readonly");
        			$("#money").val(58);
        		}else if($("#type").val()==1){
        			$(".hidediv").hide();
        			$(".hidedivpeople").show();
        			$("#money").attr("readonly","readonly");
        			$("#money").val(28);
        		}else if($("#type").val()==2){
        			$(".hidediv").hide();
        			$("#useTime").val(1);
        			$(".hidedivboxtype").show();
        			$(".hidedivuseTime").show();
        			$("#money").attr("readonly","readonly");
        			$("#money").val(38);
        		}
        	});
			
		},
        yes:function(){
        	var money = $("#money").val();
        	if(money==null || money<=0){
        		layer.msg("输入有误，请重试！");
        		return;
        	}
        	layer.confirm('请确认输入信息无误！', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
        			type: "POST",
        			data: $('#consumptionForm').serialize(),
        			url: "/admin/passFlow/addConsumption",
        			dataType: "json",
        			success: function(data) {
        				if(data.code == -1){
        					layer.msg(data.msg);
        				}else if(data.code == 1){
        					layer.closeAll(layer.indexmen);
        					layer.msg("操作成功", { time: 500 }, function () {
			                    window.location.reload(); 
			                });
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

Date.prototype.format = function(fmt) { 
     var o = { 
        "M+" : this.getMonth()+1,                 //月份 
        "d+" : this.getDate(),                    //日 
        "h+" : this.getHours(),                   //小时 
        "m+" : this.getMinutes(),                 //分 
        "s+" : this.getSeconds(),                 //秒 
        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
        "S"  : this.getMilliseconds()             //毫秒 
    }; 
    if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
     for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
         }
     }
    return fmt; 
}

function checkOut(id,sign){
		$.ajax({
			type: "POST",
			data: {id:id},
			url: "/admin/passFlow/selectConsumptionNoteById",
			dataType: "json",
			async: false,
			success: function(data) {
				if(data.code == -1){
					layer.msg(data.msg);
				}else if(data.code == 1){
					xflist = data.payload;
				}
			}
		});
		var money = 0;
		if(xflist != null && xflist.length > 0){
			for (var i=0;i<xflist.length;i++){  
				money += xflist[i].money;
			}
		}
		var jzcontent = '<table class="table table-striped">'+
		  '<thead>'+
		    '<tr>'+
		      '<th style="width:15%;">消费类型</th>'+
		      /*'<th style="width:10%;">是否免单</th>'+*/
		      '<th style="width:15%;">消费时间</th>'+
		      '<th style="width:10%;">消费金额</th>'+
		      '<th style="width:50%;">备注</th>'+
		    '</tr>'+
		  '</thead>'+
		  '<tbody>';
		if(xflist != null && xflist.length > 0){
			for (var i=0;i<xflist.length;i++){  
				jzcontent += '<tr><td>'+xflist[i].typeName+'</td>'+
						'<td>'+new Date(xflist[i].time).format('hh:mm:ss')+'</td>'+
						'<td style="'+(xflist[i].money>0?"color:green;":"color:red;")+'">'+xflist[i].money+'元</td>'+
						'<td style="width: 50px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">'+(xflist[i].remark==null?'':xflist[i].remark)+'</td>'+
						'</tr>';
			}
		}
		  jzcontent += '</tbody></table>';
	 var indexjiezhang = layer.open({
	        type: 1,
	        title: '共计'+money+'元',
	        area: ['800px', '650px'],
	        shadeClose: true, //点击遮罩关闭
	        content:jzcontent,
	        maxmin: true,
	        btn:sign==1?['普通结账','会员结账','抵扣券','套餐','取消']:[],
	        success: function (layero, index) { // 弹窗成功
			},
			end:function(){
	            // 清除session里缓存
				xflist = "";
				sumMoney = 0;
	        },
	        yes:function(){
	        	$.ajax({
    				type: "POST",
    				async: false,
    				data: {id:id},
    				url: "/admin/passFlow/settleAccounts",
    				dataType: "json",
    				success: function(data) {
    					if(data.code == -1){
    						layer.msg(data.msg);
    					}else if(data.code == 1){
    						moneyMap = data.payload;
    					}
    				}
    			});
    			var sumMoney = moneyMap.sumMoney;
	        	layer.confirm('请确认共计 '+sumMoney+'元 无误？', {icon: 3, title:'提示'}, function(index){
	        		$.ajax({
	        			type: "POST",
	        			data: {id:id},
	        			url: "/admin/passFlow/checkOut",
	        			dataType: "json",
	        			success: function(data) {
	        				if(data.code == -1){
	        					layer.msg(data.msg);
	        				}else if(data.code == 1){
	        					layer.closeAll(layer.indexmen);
	        					layer.confirm(data.payload, {icon: 1, title:'提示',btn:['确定'],cancel: function(){
	        						// 右上角关闭事件的逻辑
	        						window.location.reload(); 
	        					}}, function(index){
	        						window.location.reload(); 
	        					});
	        				}
	        			}
	        		});
	        	});
	        },
		    btn2:function(){
		    	var content = '<div>\n' +
		    	'<form id="addPassFlowForm">'+
		    	'<div class="form-group"  style="margin-top:5%;">'+
		    	'<label for="mobile" class="col-sm-3 control-label">手机号</label>'+
		    	'<div class="col-sm-8">'+
		    	'<input type="mobile" class="form-control"  name="mobile" id="mobile" placeholder="请输入会员手机号" autocomplete="off">'+
		    	'</div>'+
		    	'</div>'+
		    	'</form></div>';
		    	var index = layer.open({
		    		type: 1,
		    		title: '会员结账',
		    		area: ['400px', '200px'],
		    		shadeClose: true, //点击遮罩关闭
		    		content:content,
		    		btn:['确定','取消'],
		    		yes:function(){
		    			var mobile = $("#mobile").val();
		    			if(!(/^1[0-9]{10}$/.test(mobile))){ 
		    				layer.msg("手机号码有误，请重新输入！");  
		    		        return; 
		    		    } 
		    			$.ajax({
		    				type: "POST",
		    				async: false,
		    				data: {id:id,mobile:mobile},
		    				url: "/admin/passFlow/settleAccounts",
		    				dataType: "json",
		    				success: function(data) {
		    					if(data.code == -1){
		    						layer.msg(data.msg);
		    					}else if(data.code == 1){
		    						moneyMap = data.payload;
		    					}
		    				}
		    			});
		    			var sumMoney = moneyMap.sumMoney;
		    			var vipMoney = moneyMap.vipMoney;
		    			var surMoney = moneyMap.surMoney;
		    			if(sumMoney > vipMoney){
		    				if(vipMoney > 0){
		    					layer.confirm('<center>此次消费：'+sumMoney+'元，会员余额：'+vipMoney+'元。<br/>会员额度不足,请充值！<br/>确认结账吗？<center/>',
		    							{
		    						icon: 7, 
		    						title:'提示', 
		    						btn:['去充值','继续结账','取消'],
		    						btn1:function(){
		    							window.location = "/admin/vip?mobile="+mobile.substr(7,11);
		    						},
		    						btn2:function(){
		    							layer.confirm('会员卡额度清零，手工收费 '+(sumMoney-vipMoney)+'元', {icon: 3, title:'提示'}, function(index){
		    								$.ajax({
		    									type: "POST",
		    									data: {id:id,mobile:mobile},
		    									url: "/admin/passFlow/checkOut",
		    									dataType: "json",
		    									success: function(data) {
		    										if(data.code == -1){
		    											layer.msg(data.msg);
		    										}else if(data.code == 1){
		    											layer.closeAll(layer.indexmen);
		    											layer.confirm(data.payload, {icon: 1, title:'提示',btn:['确定'],cancel: function(){
		    	        	        						// 右上角关闭事件的逻辑
		    	        	        						window.location.reload(); 
		    	        	        					}}, function(index){
		    	        	        						window.location.reload(); 
		    	        	        					});
		    										}
		    									}
		    								});
		    							});
		    						}
    							});
		    				}else{
		    					layer.confirm('<center>此次消费：'+sumMoney+'元，会员余额：'+vipMoney+'元。<br/>会员额度不足，请选择充值或者手工结账！<center/>',
	    							{
			    						icon: 7, 
			    						title:'提示', 
			    						btn:['去充值','手工结账','取消'],
			    						btn1:function(){
			    							window.location = "/admin/vip?mobile="+mobile.substr(7,11);
			    						},
			    						btn2:function(){
		    								$.ajax({
		    									type: "POST",
		    									data: {id:id,mobile:mobile},
		    									url: "/admin/passFlow/checkOut",
		    									dataType: "json",
		    									success: function(data) {
		    										if(data.code == -1){
		    											layer.msg(data.msg);
		    										}else if(data.code == 1){
		    											layer.closeAll(layer.indexmen);
		    											layer.confirm(data.payload, {icon: 1, title:'提示',btn:['确定'],cancel: function(){
		    	        	        						// 右上角关闭事件的逻辑
		    	        	        						window.location.reload(); 
		    	        	        					}}, function(index){
		    	        	        						window.location.reload(); 
		    	        	        					});
		    										}
		    									}
		    								});
			    						}
	    							});
		    				}
		    			}else{
        	        		$.ajax({
        	        			type: "POST",
        	        			data: {id:id,mobile:mobile},
        	        			url: "/admin/passFlow/checkOut",
        	        			dataType: "json",
        	        			success: function(data) {
        	        				if(data.code == -1){
        	        					layer.msg(data.msg);
        	        				}else if(data.code == 1){
        	        					layer.closeAll(layer.indexmen);
        	        					layer.confirm(data.payload, {icon: 1, title:'提示',btn:['确定'],cancel: function(){
        	        						// 右上角关闭事件的逻辑
        	        						window.location.reload(); 
        	        					}}, function(index){
        	        						window.location.reload(); 
        	        					});
        	        				}
        	        			}
        	        		});
		    			}
		    		}
		    	});
		    },
		    btn3:function(){
		    	$.ajax({
					type: "POST",
					url: "/admin/coupon/getCoupondkqList",
					data:{type:1},
					dataType: "json",
					async: false,
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							yhxlist = data.payload;
						}
					}
				});
		    	var yhqcontent='';
		    	if(yhxlist != null && yhxlist.length > 0){
					for (var i=0;i<yhxlist.length;i++){  
						yhqcontent += '<option value="'+yhxlist[i].money+'" >'+yhxlist[i].name+'</option>';
					}
				}
		    	var content = '<div>\n' +
		    	'<form id="addPassFlowForm">'+
		    	'<div class="form-group"  style="margin-top:5%;">'+
				    '<label for="type" class="col-sm-3 control-label">类别</label>'+
				    '<div class="col-sm-8">'+
					    '<select class="form-control" id="type" name="type">'+yhqcontent+
						 '</select>'+
				    '</div>'+
			    '</div>'+
			    '<div class="form-group"  style="margin-top:13%;">'+
			    '<label for="number" class="col-sm-3 control-label">使用数量</label>'+
			    '<div class="col-sm-8">'+
			    '<input type="number" class="form-control"  name="number" id="number" min="1" value="1" placeholder="使用数量" autocomplete="off">'+
			    '</div>'+
			    '</div>'+
		    	'<div class="form-group"  style="margin-top:13%;">'+
		    	'<label for="money" class="col-sm-3 control-label">抵扣金额</label>'+
		    	'<div class="col-sm-8">'+
		    	'<input type="text" class="form-control"  name="money" id="money" value="'+yhxlist[0].money+'" placeholder="抵扣金额" autocomplete="off" readonly="readonly">'+
		    	'</div>'+
		    	'</div>'+
		    	'</form></div>';
		    	var indexmen = layer.open({
		    		type: 1,
		    		title: '使用抵扣券',
		    		area: ['400px', '300px'],
		    		shadeClose: true, //点击遮罩关闭
		    		content:content,
		    		btn:['确定','取消'],
		    		success: function (layero, index) { // 弹窗成功
		            	$(document).on('change', '#number', function() { 
		            		var number = $("#number").val();
		            		var type = $("#type").val();
		            		$("#money").val(type * number);
		            	});
		            	$(document).on('change', '#type', function() { 
		            		var number = $("#number").val();
		            		var type = $("#type").val();
		            		$("#money").val(type * number);
		            	});
		    		},
		    		yes:function(){
		    			var money = $("#money").val();
		    			if(money==null || money <= 0){
		    				layer.msg("输入有误，请重试！");
		    				return;
		    			}
	    				layer.confirm('请确认抵扣金额 '+money+'元 无误？', {icon: 3, title:'提示'}, function(index){
		    				$.ajax({
		    					type: "POST",
		    					data: {id:id,money:money},
		    					url: "/admin/passFlow/deductionVoucher",
		    					dataType: "json",
		    					success: function(data) {
		    						if(data.code == -1){
		    							layer.msg(data.msg);
		    						}else if(data.code == 1){
		    							layer.msg("操作成功！");
		    							layer.closeAll(layer.indexmen); //关闭当前窗口
		    							checkOut(id,sign);
		    						}
		    					}
		    				});
	    				});
		    		}
		    	});
		    	
		    },
		    btn4:function(){
		    	$.ajax({
					type: "POST",
					url: "/admin/coupon/getCoupondkqList",
					data:{type:2},
					dataType: "json",
					async: false,
					success: function(data) {
						if(data.code == -1){
							layer.msg(data.msg);
						}else if(data.code == 1){
							yhxlist = data.payload;
						}
					}
				});
		    	var yhqcontent='';
		    	if(yhxlist != null && yhxlist.length > 0){
					for (var i=0;i<yhxlist.length;i++){  
						yhqcontent += '<option value="'+yhxlist[i].money+'" >'+yhxlist[i].name+'</option>';
					}
				}
		    	var content = '<div>\n' +
		    	'<form id="addPassFlowForm">'+
		    	'<div class="form-group"  style="margin-top:5%;">'+
				    '<label for="type" class="col-sm-3 control-label">类别</label>'+
				    '<div class="col-sm-8">'+
					    '<select class="form-control" id="type" name="type">'+yhqcontent+
						 '</select>'+
				    '</div>'+
			    '</div>'+
		    	'</form></div>';
		    	var indexmen = layer.open({
		    		type: 1,
		    		title: '生日福利',
		    		area: ['400px', '200px'],
		    		shadeClose: true, //点击遮罩关闭
		    		content:content,
		    		btn:['确定','取消'],
		    		yes:function(){
		    			var money = $("#type").val();
		    			if(money==null || money <= 0){
		    				layer.msg("输入有误，请重试！");
		    				return;
		    			}
	    				layer.confirm('请确认生日福利无误？', {icon: 3, title:'提示'}, function(index){
		    				$.ajax({
		    					type: "POST",
		    					data: {id:id,money:money},
		    					url: "/admin/passFlow/birthdayBenefits",
		    					dataType: "json",
		    					success: function(data) {
		    						if(data.code == -1){
		    							layer.msg(data.msg);
		    						}else if(data.code == 1){
		    							layer.msg("操作成功！");
		    							layer.closeAll(layer.indexmen); //关闭当前窗口
		    							checkOut(id,sign);
		    						}
		    					}
		    				});
	    				});
		    		}
		    	});
		    	
		    }
	    });
}

