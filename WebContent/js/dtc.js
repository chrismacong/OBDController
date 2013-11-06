$().ready(function() {
	$("#refresh_button").click(function(){
		window.location.reload();
	});
	if(!isACCOn){
		$("#get_dtc_status_button")[0].disabled = true;
		$("#get_obd_defect_button")[0].disabled = true;
		$("#clear_dtc_status_button")[0].disabled = true;
	}
	else{
		$("#get_dtc_status_button")[0].disabled = false;
		$("#get_obd_defect_button")[0].disabled = false;
		$("#clear_dtc_status_button")[0].disabled = false;
	}
	$("#get_dtc_status_button").click(function(){
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
			url:"dtc/askDTC.html",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
					refresh();
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
	$("#get_obd_defect_button").click(function(){
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
			url:"dtc/askDTCStatus.html",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
					refresh();
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
	$("#clear_dtc_status_button").click(function(){
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
			url:"dtc/askClearDTC.html",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
					refresh();
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
	$("#analysis_dtc_status").click(function(){
		$.blockUI({
			message: $('#detailBlock'),
			css:{
				top:'2%',
				width:'70%',
				left:'10%'
			}
		});
	});
	$(".block_close_btn").click(function(){
		$.unblockUI();
	});
})
function refresh(){
	window.location.reload();
}