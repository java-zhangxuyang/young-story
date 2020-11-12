function addStaff(){
	var content = '<div>\n' +
					'<form id="addStaffForm">'+
						'<div class="form-group"  style="margin-top:5%;">'+
							'<label for="name" class="col-sm-2 control-label">姓名</label>'+
							'<div class="col-sm-9">'+
								'<input type="text" class="form-control" id="name" name="name" placeholder="姓名"  autocomplete="off">'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
						    '<label for="sex" class="col-sm-2 control-label">性别</label>'+
						    '<div class="col-sm-9">'+
							    '<select class="form-control" name="sex">'+
							    	'<option value="男">男</option>'+
						    		'<option value="女" selected>女</option>'+
								 '</select>'+
						    '</div>'+
					    '</div>'+
					    '<div class="form-group">'+
					    	'<label for="age" class="col-sm-2 control-label">年龄</label>'+
					    	'<div class="col-sm-9">'+
					    		'<input type="number" class="form-control" id="age" name="age" min=16 placeholder="年龄"  autocomplete="off">'+
					    	'</div>'+
					    '</div>'+
					    '<div class="form-group">'+
						    '<label for="mobile" class="col-sm-2 control-label">手机号</label>'+
						    '<div class="col-sm-9">'+
						    	'<input type="mobile" class="form-control" id="mobile" name="mobile" placeholder="手机号"  autocomplete="off">'+
						    '</div>'+
					    '</div>'+
					    '<div class="form-group"  style="margin-bottom:-6%;">'+
						    '<label for="date" class="col-sm-2 control-label">生日</label>'+
							'<div class="input-group date col-sm-9" id="datetimepicker" style="margin-left:19%;">'+
								'<input  type="text" class="form-control" name="birthday" id="birthday" placeholder="生日" readonly/>'+
								'<span class="input-group-addon">'+
								'<span class="glyphicon glyphicon-calendar"></span></span>'+
							'</div>'+
						'</div>'+
						'<div class="form-group" style="margin-bottom:-6%;">'+
							'<label for="date" class="col-sm-2 control-label">入职时间</label>'+
							'<div class="input-group date col-sm-9" id="datetimepicker1" style="margin-left:19%;">'+
								'<input  type="text" class="form-control" name="entryTime" id="entryTime" placeholder="入职时间" readonly/>'+
								'<span class="input-group-addon">'+
								'<span class="glyphicon glyphicon-calendar"></span></span>'+
							'</div>'+
						'</div>'+
					    '<div class="form-group">'+
						    '<label for="people" class="col-sm-2 control-label">薪资</label>'+
						    '<div class="col-sm-9">'+
						    	'<input type="text" class="form-control" id="salary" name="salary" placeholder="薪资"  autocomplete="off">'+
						    '</div>'+
					    '</div>'+
					    '<div class="form-group">'+
						    '<label for="level" class="col-sm-2 control-label">级别</label>'+
						    '<div class="col-sm-9">'+
							    '<select class="form-control" name="level">'+
						    		'<option value="3">全职工</option>'+
						    		'<option value="4">临时工（兼职）</option>'+
						    		'<option value="5">实习生</option>'+
								 '</select>'+
						    '</div>'+
					    '</div>'+
					    '<div class="form-group">'+
						    '<label for="holidays" class="col-sm-2 control-label">年假天数</label>'+
						    '<div class="col-sm-9">'+
						    	'<input type="number" class="form-control" id="holidays" name="holidays"  value=1 min=0 placeholder="薪资"  autocomplete="off">'+
						    '</div>'+
					    '</div>'+
				'</form></div>';
    var index = layer.open({
        type: 1,
        title: '添加员工',
        area: ['550px', '550px'],
        shadeClose: true, //点击遮罩关闭
        content:content,
        btn:['确定','取消'],
        success: function (layero, index) { // 弹窗成功
			$(layero).find('#datetimepicker').datetimepicker({
				format: 'yyyy-mm-dd',
				weekStart: 1,
				autoclose: true,
				startView: 2,
				initialDate:new Date('1997-01-01'),
				minView: 2,
			    language:'zh-CN'
		    });
			$(layero).find('#datetimepicker1').datetimepicker({
				format: 'yyyy-mm-dd',
				weekStart: 1,
				autoclose: true,
				startView: 2,
				minView: 2,
				startDate:new Date('2020-10-01'),
			    language:'zh-CN'
			});
		},
        yes:function(){
        	var mobile = $("#mobile").val();
        	var name = $("#name").val();
        	var entryTime = $("#entryTime").val();
        	if(!(/^1[0-9]{10}$/.test(mobile))){ 
				layer.msg("手机号码有误，请重填");  
		        return; 
		    } 
        	if(mobile==null ||name==null || entryTime==null){
        		layer.msg("输入有误，请重试！");
        		return;
        	}
        	layer.confirm('请确认输入信息无误！', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
        			type: "POST",
        			data: $('#addStaffForm').serialize(),
        			url: "/admin/staff/addStaff",
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
//员工接单加时
function addTime(id,name){
	var content = '<div>\n' +
	'<form id="addTimeForm">'+
	'<input type="hidden" name="id" value="'+id+'">'+
	'<div class="form-group"  style="margin-top:5%;">'+
	'<label for="back1" class="col-sm-3 control-label">时长</label>'+
	'<div class="col-sm-8">'+
	'<input type="number" class="form-control"  name="back1" value=1 id="back1" placeholder="加时时长（小时）"   autocomplete="off">'+
	'</div>'+
	'</div>'+
	'</form></div>';
	var index = layer.open({
		type: 1,
		title: name,
		area: ['400px', '180px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		yes:function(){
			var back1 = $("#back1").val();
			if(back1==null || back1 <= 0){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认为 '+name+' 加时 '+back1+'小时 吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#addTimeForm').serialize(),
					url: "/admin/staff/addTime",
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
