$(function(){
	$("#refresh_button").click(function(){
		window.location.reload();
	});
	$("#get_travel_info_button").click(function(){
		$.blockUI({
			message: $('#waitBlock'),
			css:{
				top:'20%',
				width:'24%',
				height:'100px',
				left:'38%'
			}
		});
		var $params="terminalId=" + terminal_id;
		$.ajax({
			type:'GET',
			contentType: 'application/json',  
			url:"travelinfo/askLatestTravelInfo.html",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
					window.location.reload();
				}
				else{
					$.unblockUI();
					alert("操作失败，请重新操作")
				}
			},
			error:function(){
				$.unblockUI();
				alert("操作失败，请重新操作")
			}
		});
	});
	$("#get_which_travel_info_button").click(function(){
		var index = $("#travel_info_select option:selected").text();
		$.blockUI({
			message: $('#waitBlock'),
			css:{
				top:'20%',
				width:'24%',
				height:'100px',
				left:'38%'
			}
		});
		var $params="terminalId=" + terminal_id +"&index=" + index;
		$.ajax({
			type:'GET',
			contentType: 'application/json',  
			url:"travelinfo/askTravelInfo.html",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
					window.location.reload();
				}
				else{
					$.unblockUI();
					alert("操作失败，请重新操作")
				}
			},
			error:function(){
				$.unblockUI();
				alert("操作失败，请重新操作")
			}
		});
	});
});