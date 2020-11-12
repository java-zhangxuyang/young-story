//添加活动
function addActivity(){
	var content = '<div>\n' +
			'<form id="addActivityForm">'+
				'<div class="form-group"  style="margin-top:5%;">'+
					'<label for="name" class="col-sm-2 control-label">活动名称</label>'+
					'<div class="col-sm-9">'+
						'<input type="text" class="form-control" id="name" name="name" placeholder="活动名称"  autocomplete="off">'+
					'</div>'+
				'</div>'+
				'<div class="form-group">'+
				    '<label for="type" class="col-sm-2 control-label">活动类型</label>'+
				    '<div class="col-sm-9">'+
					    '<select class="form-control" name="type" id="type">'+
					    	'<option value="1" selected>积分翻倍</option>'+
				    		'<option value="2">消费打折</option>'+
						 '</select>'+
				    '</div>'+
			    '</div>'+
			    '<div class="form-group">'+
			    	'<label for="number" class="col-sm-2 control-label">倍数或折扣</label>'+
			    	'<div class="col-sm-9">'+
			    		'<input type="number" class="form-control" id="number" name="number" min=2 max=10 placeholder="倍数或折扣（2或0.95）"  autocomplete="off">'+
			    	'</div>'+
			    '</div>'+
			    '<div class="form-group"  style="margin-bottom:-6%;">'+
				    '<label for="date" class="col-sm-2 control-label">开始时间</label>'+
					'<div class="input-group date col-sm-9" id="datetimepicker" style="margin-left:19%;">'+
						'<input  type="text" class="form-control" name="startTimes" id="startTimes" placeholder="开始时间" readonly/>'+
						'<span class="input-group-addon">'+
						'<span class="glyphicon glyphicon-calendar"></span></span>'+
					'</div>'+
				'</div>'+
				'<div class="form-group" style="margin-bottom:-6%;">'+
					'<label for="date" class="col-sm-2 control-label">结束时间</label>'+
					'<div class="input-group date col-sm-9" id="datetimepicker1" style="margin-left:19%;">'+
						'<input  type="text" class="form-control" name="endTimes" id="endTimes" placeholder="结束时间" readonly/>'+
						'<span class="input-group-addon">'+
						'<span class="glyphicon glyphicon-calendar"></span></span>'+
					'</div>'+
				'</div>'+
		'</form></div>';
	var index = layer.open({
		type: 1,
		title: '添加新活动',
		area: ['550px', '400px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		success: function (layero, index) { // 弹窗成功
			$(layero).find('#datetimepicker').datetimepicker({
				format: 'yyyy-mm-dd',
				weekStart: 1,
				autoclose: true,
				startView: 2,
				startDate:new Date(),
				initialDate:new Date(),
				minView: 2,
			    language:'zh-CN'
		    });
			$(layero).find('#datetimepicker1').datetimepicker({
				format: 'yyyy-mm-dd',
				weekStart: 1,
				autoclose: true,
				startView: 2,
				minView: 2,
				initialDate:new Date(),
				startDate:new Date(),
			    language:'zh-CN'
			});
		},
		yes:function(){
			var type = $("#type").val();
			var number = $("#number").val();
			var startTimes = $("#startTimes").val();
			var endTimes = $("#endTimes").val();
			if(type==null || type <= 0 || number==null || number <= 1|| startTimes==null || endTimes == null){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认添加操作吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#addActivityForm').serialize(),
					url: "/admin/activity/addActivity",
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

//删除活动
function deleteActivity(id,name,status){
	if(status == 1){
		layer.msg('可用状态的活动不可删除！');
		return;
	}
	layer.confirm('确认删除 '+name+' 活动吗？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type: "POST",
			data: {id:id},
			url: "/admin/activity/deleteActivity",
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

//修改活动状态
function updateActivityStatus(id,name,status){
	var text;
	if(status == 1){
		text = '<center>确认修改该活动为不可用么吗？</center>';
	}else{
		text = '确认修改该活动为可用么吗？';
	}
	layer.confirm(text, {icon: 3, title:name}, function(index){
		$.ajax({
			type: "POST",
			data: {id:id},
			url: "/admin/activity/updateActivityStatus",
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
