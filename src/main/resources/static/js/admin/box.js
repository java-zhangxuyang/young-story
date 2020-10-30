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
        			url: "/admin/box/useBox",
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
					url: "/admin/box/makeBox",
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
					url: "/admin/box/updateBoxName",
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
					url: "/admin/box/continuation",
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
	'<input type="number" class="form-control"  name="people" value=1 id="people" placeholder="人数"   autocomplete="off">'+
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
						/*'<option value="1">入场</option>'+*/
						/*'<option value="2">包厢</option>'+*/
						'<option value="3" selected>女仆</option>'+
						'<option value="4">其他</option>'+
						'</select>'+
						'</div>'+
						'</div>'+
						'<div class="form-group hidediv">'+
						'<label for="number" class="col-sm-2 control-label">时长</label>'+
						'<div class="col-sm-9">'+
							'<input type="number" class="form-control" id="useTime" name="back3" min=1 value=1  placeholder="时长" autocomplete="off">'+
						'</div>'+
					'</div>'+
					'<div class="form-group hidediv">'+
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
        		var num = $("#num").val();
        		var useTime = $("#useTime").val();
        		$("#money").val(58 * num * useTime);
        	});
        	$(document).on('change', '#type', function() { 
        		if($("#type").val()==4){
        			$(".hidediv").hide();
        			$("#money").removeAttr("readonly");
        			$("#money").val("");
        		}else if($("#type").val()==3){
        			$(".hidediv").show();
        			$("#money").attr("readonly","readonly");
        			$("#money").val(58);
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
		var maidmoney = 0;
		var nomaidmoney = 0;
		if(xflist != null && xflist.length > 0){
			for (var i=0;i<xflist.length;i++){  
				money += xflist[i].money;
				if(xflist.type!=3&&xflist.type!=99){
					nomaidmoney += xflist[i].money;
				}else if(xflist.type==3){
					maidmoney += xflist[i].money;
				}
			}
		}
		var jzcontent = '<table class="table table-striped">'+
		  '<thead>'+
		    '<tr>'+
		      '<th style="width:20%;">消费类型</th>'+
		      '<th style="width:20%;">是否免单</th>'+
		      '<th style="width:20%;">消费时间</th>'+
		      '<th style="width:20%;">消费金额</th>'+
		      '<th style="width:20%;">备注</th>'+
		    '</tr>'+
		  '</thead>'+
		  '<tbody>';
		if(xflist != null && xflist.length > 0){
			for (var i=0;i<xflist.length;i++){  
				jzcontent += '<tr><td>'+xflist[i].typeName+'</td>'+
						'<td>'+(xflist[i].freeCharge==0?'否':'是')+'</td>'+
						'<td>'+new Date(xflist[i].time).format('hh:mm:ss')+'</td>'+
						'<td>'+xflist[i].money+'元</td>'+
						'<td style="width: 50px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">'+(xflist[i].remark==null?'':xflist[i].remark)+'</td>'+
						/*'<td>'+
							'<button type="button" class="btn btn-primary miandan" value="'+xflist[i].id+'" ><span class="glyphicon glyphicon-gbp"></span> &nbsp;免单</button>'+
						'</td>'+*/
						'</tr>';
			}
		}
		  jzcontent += '</tbody></table>';
	 var index = layer.open({
	        type: 1,
	        title: '共计'+money+'元',
	        area: ['600px', '650px'],
	        shadeClose: true, //点击遮罩关闭
	        content:jzcontent,
	        maxmin: true,
	        btn:sign==1?['确定','使用折扣','取消']:[],
	        success: function (layero, index) { // 弹窗成功
	        	/*$(document).on('click', '.miandan', function() { 
	        		alert(this.val());
	        	});*/
			},
	        yes:function(){
	        	layer.confirm('请确认共计 '+money+'元 无误？', {icon: 3, title:'提示'}, function(index){
	        		$.ajax({
	        			type: "POST",
	        			data: {id:id},
	        			url: "/admin/passFlow/checkOut",
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
		    	var content = '<div>\n' +
		    	'<form id="addPassFlowForm">'+
		    	'<div class="form-group"  style="margin-top:5%;">'+
		    	'<label for="people" class="col-sm-3 control-label">折扣</label>'+
		    	'<div class="col-sm-8">'+
		    	'<input type="number" class="form-control"  name="discount" id="discount" min=7 max=99 placeholder="折扣(95折，输入9.5)" autocomplete="off">'+
		    	'</div>'+
		    	'</div>'+
		    	'</form></div>';
		    	var index = layer.open({
		    		type: 1,
		    		title: '使用折扣',
		    		area: ['400px', '200px'],
		    		shadeClose: true, //点击遮罩关闭
		    		content:content,
		    		btn:['确定','取消'],
		    		yes:function(){
		    			var discount = $("#discount").val();
		    			if(discount==null || discount <= 0){
		    				layer.msg("输入有误，请重试！");
		    				return;
		    			}
		    			layer.confirm('确认为该客人打 '+discount+'折 吗？', {icon: 3, title:'提示'}, function(index){
		    				var floorNum=Math.floor((nomaidmoney*discount*0.1)+maidmoney);
		    				layer.confirm('请确认共计 '+floorNum+'元 无误？(女仆费用不参与打折)', {icon: 3, title:'提示'}, function(index){
			    				$.ajax({
			    					type: "POST",
			    					data: {id:id,discount:discount},
			    					url: "/admin/passFlow/checkOut",
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
		    			});
		    		},
		    		btn2:function(){
		    			layer.closeAll(index); //关闭当前窗口
		    		}
		    	});
		    	
	        	
		    },
		    btn3:function(){
		        layer.closeAll(index); //关闭当前窗口
		    }
	    });
}

