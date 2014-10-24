$(function() {
	$("tr").click(function(){
		var id = this.id.substring(9);
		var $params="logId=" + id;
		$.ajax({
			type:'GET',
			contentType: 'application/json',  
			url:"terminallog/analysis.html",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
					$("#log_decode_content")[0].innerHTML = data.divStr;
					$.blockUI({
						message: $('#logBlock'),
						css:{
							top:'2%',
							width:'70%',
							left:'10%'
						}
					});
				}
				
			}
		});
	});
	$(".block_close_btn").click(function(){
		$.unblockUI();
	});
});