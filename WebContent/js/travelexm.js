$(function () {
	$("#speed_hour_btn").click(function(){
		$.blockUI({
			message: $('#graphBlock'),
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