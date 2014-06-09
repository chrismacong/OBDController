$(function(){
	$("#exm_button").click(function(){
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
			url:"vehicleexm/makeexm.html",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
					$.unblockUI();
					$("#scoreDiv")[0].innerHTML = data.score;
					var score = parseInt(data.score);
					if(score!=100){
						var gRGB = score*2.55;
						var rRGB = 255 - score*2.55;
						var r = rRGB.toString(16);
						var g = gRGB.toString(16);
						var b = "00".toString(16);
						$("#scoreDiv")[0].style.color = "#"+r+g+b;
					}

					$("#totalsaying")[0].innerHTML = data.solution;
					var errors = data.errors.split("@@@");
					for(var i=0;i<errors.length;i++){
						var a_error = errors[i];
						var a_error_split = a_error.split("%%%");
						$("#error_table").append("<tr><td>" + a_error_split[0] + 
								"</td><td><img src='images/warn" + a_error_split[1] + ".png'/>" +  
								"</td><td>" + a_error_split[2] + 
								"</td><td>" + a_error_split[3] + 
								"</td><td>" + a_error_split[4] + 
								"</td></tr>")
					}
					$("#totalreport")[0].innerHTML = "对车辆进行故障检查，以下" + errors.length +"项存在问题";
				}
				else{
					$.unblockUI();
					alert("操作失败，请确认已与终端建立连接")
				}
			},
			error:function(){
				$.unblockUI();
				alert("操作失败，请确认已与终端建立连接")
			}
		});
	});
	$("#journal_button").click(function(){
		var $params="terminalId=" + terminal_id;
		$.ajax({
			type:'GET',
			contentType: 'application/json',  
			url:"vehicleexm/makejournal.html",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
				}
				else{
					$.unblockUI();
					alert("操作失败，请确认已与终端建立连接")
				}
			},
			error:function(){
				$.unblockUI();
				alert("操作失败，请确认已与终端建立连接")
			}
		});
	});
});