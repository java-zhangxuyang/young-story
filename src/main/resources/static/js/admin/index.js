/**
 * 
 */
$(function(){
	 $(".nav li").click(function() {
	        $(".active").removeClass('active');
	        $(this).addClass("active");
	    }); 
	$("#collapseListGroupHeading1").click(function(){
		alert($("#collapseListGroup1").is(":hidden"))
		if($("#collapseListGroup1").is(":hidden")){
			$("#collapseListGroup1").hide();
		}else{
			$("#collapseListGroup1").show();
		}
	})
	$("#loginOut").click(function(){
		$.ajax({
            type: "POST",
            url: "/admin/loginOut",
            dataType: "json",
            success: function(data){
	            	layer.msg('注销成功',{
	                    time:1000,
	                    end:function () {
	                    	window.location.href="/admin"; 
	                    }
	            	})
                 },
            error:function(){
	            	layer.msg("网络超时，请重试！");
	            }
        });
	})
})