$(function () {
	$("#speed_hour_btn").click(function(){
		$.blockUI({
			message: $('#speed_hour_graphBlock'),
			css:{
				top:'10%',
				width:'40%',
				height:'450px',
				left:'30%'
			}
		});
	});
	$("#hour_btn").click(function(){
		$.blockUI({
			message: $('#hour_graphBlock'),
			css:{
				top:'10%',
				width:'40%',
				height:'450px',
				left:'30%'
			}
		});
	});
	$(".block_close_btn").click(function(){
		$.unblockUI();
	});
});