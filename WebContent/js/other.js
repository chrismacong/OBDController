$(function(){
	$("#listen_button").click(function(){
		var phone = $("#phone_input").get(0).value;
		$.blockUI({
			message: $('#waitBlock'),
			css:{
				top:'20%',
				width:'24%',
				height:'100px',
				left:'38%'
			}
		});
		var $params="terminalId=" + terminal_id + "&phone=" + phone;
		$.ajax({
			type:'GET',
			contentType: 'application/json',  
			url:"other/listen.html",
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
	})
	$("#reboot_button").click(function(){
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
			url:"other/reboot.html",
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
	})
	$("#installposition_button").click(function(){
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
			url:"other/installposition.html",
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
	})
	$("#clearblind_button").click(function(){
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
			url:"other/clearblind.html",
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
	})
	$("#arm_button").click(function(){
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
			url:"other/arm.html",
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
	})
	$("#disarm_button").click(function(){
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
			url:"other/disarm.html",
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
	})
	$("#restore_button").click(function(){
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
			url:"other/restore.html",
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
	})
})
