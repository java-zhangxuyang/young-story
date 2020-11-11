//修改预约状态
function updateSubscribeStatus(id,name){
	var content = '<div>\n' +
	'<form id="updateSubscribeStatusForm">'+
	'<input type="hidden" name="id" value="'+id+'">'+
	'<div class="form-group" style="margin-top:5%;">'+
	    '<label for="sex" class="col-sm-2 control-label">状态</label>'+
	    '<div class="col-sm-9">'+
		    '<select class="form-control" name="status" id="status">'+
	    		'<option value="2" selected>已到场</option>'+
	    		'<option value="3">迟到</option>'+
	    		'<option value="4">爽约</option>'+
			 '</select>'+
	    '</div>'+
	'</div>'+
	'<div class="form-group">'+
	'<label for="remark" class="col-sm-2 control-label">备注</label>'+
	'<div class="col-sm-9">'+
	'<textarea class="form-control" rows="3" name="back1" placeholder="备注"></textarea>'+
	'</div>'+
	'</div>'+
	'</form></div>';
	var index = layer.open({
		type: 1,
		title: '修改预约状态',
		area: ['400px', '270px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		yes:function(){
			var status = $("#status").val();
			if(status==null || status <= 0){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认操作吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#updateSubscribeStatusForm').serialize(),
					url: "/admin/subscribe/updateSubscribeStatus",
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
