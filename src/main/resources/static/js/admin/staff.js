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
							'<label for="userName" class="col-sm-2 control-label">昵称</label>'+
							'<div class="col-sm-9">'+
								'<input type="text" class="form-control" id="userName" name="userName" placeholder="昵称"  autocomplete="off">'+
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
						    		'<option value="3">全职</option>'+
						    		'<option value="4">全天兼职</option>'+
						    		'<option value="5">半天兼职</option>'+
						    		'<option value="6">假期工</option>'+
								 '</select>'+
						    '</div>'+
					    '</div>'+
					    '<div class="form-group">'+
						    '<label for="holidays" class="col-sm-2 control-label">月休天数</label>'+
						    '<div class="col-sm-9">'+
						    	'<input type="number" class="form-control" id="holidays" name="holidays"  value=4 min=0 placeholder="月休天数"  autocomplete="off">'+
						    '</div>'+
					    '</div>'+
				'</form></div>';
    var index = layer.open({
        type: 1,
        title: '添加员工',
        area: ['550px', '570px'],
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
				startDate:new Date('1994-10-01'),
				endDate:new Date(),
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
				endDate:new Date(),
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
//员工加名改名
function addUserName(id,name,userName){
	var content = '<div>\n' +
	'<form id="addUserNameForm">'+
	'<input type="hidden" name="id" value="'+id+'">'+
	'<div class="form-group"  style="margin-top:5%;">'+
	'<label for="userName" class="col-sm-3 control-label">昵称</label>'+
	'<div class="col-sm-8">'+
	'<input type="text" class="form-control"  name="userName" id="userName" value="'+userName+'" placeholder="昵称" autocomplete="off">'+
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
			var userName = $("#userName").val();
			if(userName==null){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认修改 '+name+' 的昵称为 '+userName+' 吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#addUserNameForm').serialize(),
					url: "/admin/staff/addUserName",
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

function lookMonthNote(id){
	$.ajax({
		type: "POST",
		data: {id:id},
		url: "/admin/staff/selectStaffNoteById",
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
	var jzcontent = '<table class="table table-striped">'+
	  '<thead>'+
	    '<tr>'+
	      '<th style="width:5%;">序列</th>'+
	      '<th style="width:10%;">时长</th>'+
	      '<th style="width:15%;">时间</th>'+
	    '</tr>'+
	  '</thead>'+
	  '<tbody>';
	if(xflist != null && xflist.length > 0){
		for (var i=0;i<xflist.length;i++){  
			jzcontent += '<tr><td>'+(i+1)+'</td>'+
					'<td>'+xflist[i].duration+' 小时</td>'+
					'<td>'+new Date(xflist[i].time).Format('yyyy-MM-dd hh:mm')+'</td>'+
					'</tr>';
		}
	}
	  jzcontent += '</tbody></table>';
 var indexjiezhang = layer.open({
        type: 1,
        title: '接单记录',
        area: ['800px', '650px'],
        shadeClose: true, //点击遮罩关闭
        content:jzcontent,
        maxmin: true,
        success: function (layero, index) { // 弹窗成功
		},
		end:function(){
            // 清除session里缓存
			xflist = "";
        },
        yes:function(){
        	
        }
 });
}
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}