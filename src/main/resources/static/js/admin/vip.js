$(function(){
	$('#search').click(function () {
        var phone = $('#phone').val();
        var vipname = $('#vipname').val();
        window.location = '/admin/vip?mobile='+phone+'&vipName=' +vipname;
    });
    $('#reset').click(function() {
        $('#phone').val('');
        $('#vipname').val('');
        window.location = '/admin/vip';
    });
})

function fenye(pageNum){
  var phone = $('#phone').val();
     var vipname = $('#vipname').val();
     window.location = '/admin/vip?mobile='+phone+'&vipName=' +vipname+'&pageNum=' +pageNum;
}

function addVip(){
	/*var staffContent='';
	if(staffList != null && staffList.length > 0){
		for (var i=0;i<staffList.length;i++){  
			staffContent += '<option value="'+staffList[i].userName+'" >'+staffList[i].userName+'</option>';
		}
	}*/
	var content = '<div>\n' +
					'<form id="addVipForm">'+
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
							    	'<option value="男" selected>男</option>'+
						    		'<option value="女">女</option>'+
								 '</select>'+
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
						/*'<div class="form-group" >'+
						'<label for="back1" class="col-sm-2 control-label">推荐人</label>'+
						'<div class="col-sm-9">'+
							'<select class="form-control" name="back1" id="back1">'+
								'<option value="" disabled selected hidden></option>'+
								staffContent+
							 '</select>'+
						'</div>'+
						'</div>'+*/
						'<div class="form-group">'+
						    '<label for="remark" class="col-sm-2 control-label">备注</label>'+
						    '<div class="col-sm-9">'+
						    	'<textarea class="form-control" rows="3" name="remark"></textarea>'+
						    '</div>'+
					    '</div>'+
				'</form></div>';
    var index = layer.open({
        type: 1,
        title: '添加会员',
        area: ['550px', '420px'],
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
		},
        yes:function(){
        	var mobile = $("#mobile").val();
        	var name = $("#name").val();
        	if(!(/^1[0-9]{10}$/.test(mobile))){ 
				layer.msg("手机号码有误，请重填");  
		        return; 
		    } 
        	if(mobile==null ||name==null){
        		layer.msg("输入有误，请重试！");
        		return;
        	}
        	layer.confirm('请确认输入信息无误！', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
        			type: "POST",
        			data: $('#addVipForm').serialize(),
        			url: "/admin/vip/addVip",
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
//会员充值
function vipRecharge(id,name){
	var text = '';
	for(var i=0;i<flushs.length;i++){
		text += '<option value="'+flushs[i].recharge+'">'+flushs[i].name+'</option>';
	}
	var staffContent='';
	if(staffList != null && staffList.length > 0){
		for (var i=0;i<staffList.length;i++){  
			staffContent += '<option value="'+staffList[i].userName+'" >'+staffList[i].userName+'</option>';
		}
	}
	var content = '<div>\n' +
	'<form id="vipRechargeForm">'+
	'<input type="hidden" name="id" value="'+id+'">'+
	'<div class="form-group" style="margin-top:5%;">'+
	    '<label for="sex" class="col-sm-3 control-label">充值金额</label>'+
	    '<div class="col-sm-9">'+
		    '<select class="form-control" name="money" id="money">'+
		    	text+'</select>'+
	    '</div>'+
	'</div>'+
	'<div class="form-group" style="margin-top:10%;">'+
	'<label for="back1" class="col-sm-3 control-label">推荐人</label>'+
	'<div class="col-sm-9">'+
		'<select class="form-control" name="back1" id="back1">'+
			'<option value="" disabled selected hidden></option>'+staffContent+
			'</select></div></div>'+
	'</form></div>';
	var index = layer.open({
		type: 1,
		title: '会员充值',
		area: ['400px', '200px'],
		shadeClose: true, //点击遮罩关闭
		content:content,
		btn:['确定','取消'],
		yes:function(){
			var money = $("#money").val();
			if(money==null || money <= 0){
				layer.msg("输入有误，请重试！");
				return;
			}
			layer.confirm('确认为 '+name+' 充值 '+money+'元 吗？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type: "POST",
					data: $('#vipRechargeForm').serialize(),
					url: "/admin/vip/vipRecharge",
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

function vipLevelUpgrade(id,name){
	layer.confirm('确认消耗积分为 '+name+' 升级会员吗？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type: "POST",
			data: {id:id},
			url: "/admin/vip/vipLevelUpgrade",
			dataType: "json",
			success: function(data) {
				if(data.code == -1){
					layer.msg(data.msg);
				}else if(data.code == 1){
					layer.closeAll(layer.indexmen);
					layer.msg("升级成功", { time: 500 }, function () {
						window.location.reload(); 
					});
				}
			}
		});
	});
}
