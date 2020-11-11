//添加活动
function addCoupon(){
	var content = '<div>\n' +
			'<form id="addCouponForm">'+
				'<div class="form-group"  style="margin-top:5%;">'+
					'<label for="name" class="col-sm-2 control-label">名称</label>'+
					'<div class="col-sm-9">'+
						'<input type="text" class="form-control" id="name" name="name" placeholder="优惠项名称"  autocomplete="off">'+
					'</div>'+
				'</div>'+
				'<div class="form-group">'+
				    '<label for="type" class="col-sm-2 control-label">类型</label>'+
				    '<div class="col-sm-9">'+
					    '<select class="form-control" name="type" id="type">'+
					    	'<option value="1" selected>抵扣券</option>'+
						 '</select>'+
				    '</div>'+
			    '</div>'+
			    '<div class="form-group">'+
			    	'<label for="number" class="col-sm-2 control-label">额度</label>'+
			    	'<div class="col-sm-9">'+
			    		'<input type="number" class="form-control" id="money" name="money" min="1" placeholder="额度"  autocomplete="off">'+
			    	'</div>'+
			    '</div>'+
			    '<div class="form-group">'+
			    '<label for="number" class="col-sm-2 control-label">共计</label>'+
			    '<div class="col-sm-9">'+
			    '<input type="number" class="form-control" id="total" name="total" min="0" placeholder="共计（张）（0代表不限量）"  autocomplete="off">'+
			    '</div>'+
			    '</div>'+
			    '<div class="form-group">'+
			    '<label for="number" class="col-sm-2 control-label">有效天数</label>'+
			    '<div class="col-sm-9">'+
			    '<input type="text" class="form-control" id="day" name="day"  placeholder="有效天数（天）（非必填）"  autocomplete="off">'+
			    '</div>'+
			    '</div>'+
			    '<div class="form-group"  style="margin-bottom:-6%;">'+
				    '<label for="date" class="col-sm-2 control-label">生效时间</label>'+
					'<div class="input-group date col-sm-9" id="datetimepicker" style="margin-left:19%;">'+
						'<input  type="text" class="form-control" name="startTimes" id="startTimes" placeholder="生效时间（非必填）" readonly/>'+
						'<span class="input-group-addon">'+
						'<span class="glyphicon glyphicon-calendar"></span></span>'+
					'</div>'+
				'</div>'+
				'<div class="form-group" style="margin-bottom:-6%;">'+
					'<label for="date" class="col-sm-2 control-label">失效时间</label>'+
					'<div class="input-group date col-sm-9" id="datetimepicker1" style="margin-left:19%;">'+
						'<input  type="text" class="form-control" name="endTimes" id="endTimes" placeholder="失效时间（非必填）" readonly/>'+
						'<span class="input-group-addon">'+
						'<span class="glyphicon glyphicon-calendar"></span></span>'+
					'</div>'+
				'</div>'+
		'</form></div>';
	var index = layer.open({
		type: 1,
		title: '添加新优惠项',
		area: ['550px', '500px'],
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
			var money = $("#money").val();
			var name = $("#name").val();
			if(type==null || type <= 0 || money==null || money <= 1|| name==null){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认添加操作吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#addCouponForm').serialize(),
					url: "/admin/coupon/addCoupon",
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

//删除活动
function deleteCoupon(id,name){
	layer.confirm('确认删除 '+name+' 优惠券吗？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type: "POST",
			data: {id:id},
			url: "/admin/coupon/deleteCoupon",
			dataType: "json",
			success: function(data) {
				if(data.code == -1){
					layer.msg(data.msg);
				}else if(data.code == 1){
					layer.msg("操作成功！");
					window.location.reload(); 
				}
			}
		});
	});
}
