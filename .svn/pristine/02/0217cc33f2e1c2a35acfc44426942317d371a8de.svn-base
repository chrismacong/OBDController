var askorset = true;
var terminalId;
$().ready(function() {
	$('input.myClass').Checkable({
		color : 'blue'
	});
	$("#refresh_button").click(function(){
		refresh();
	})
	$("#submit_button").click(function(){
		var r=document.getElementsByName("answer");
		var backPartOfReply = "";
		if(askorset){
			for(var i=0;i<r.length;i++){
				if(r[i].checked){
					backPartOfReply += r[i].id.substring(9);
				}
			}
			if(backPartOfReply!=""){
				var numOfCha = backPartOfReply.length/2 + "";
				if(numOfCha.length==1)
					numOfCha = "0" + numOfCha;
				var replyToServer = numOfCha + backPartOfReply;
				$.blockUI({
					message: $('#waitBlock'),
					css:{
						top:'20%',
						width:'24%',
						height:'100px',
						left:'38%'
					}
				});
				var $params="terminalId=" + terminalId +"&charactersentence=" + replyToServer;
				$.ajax({
					type:'GET',
					contentType: 'application/json',  
					url:"character/getParameter.html",
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
			}
		}
		else{
			var inputs = document.getElementsByName("input_text");
			var num = 0;
			for(var i=0;i<r.length;i++){
				if(r[i].checked){
					var input_length = inputs[i].value.length/2 + "";
					if(input_length.length==1)
						input_length = "0" + input_length;
					backPartOfReply += r[i].id.substring(9) + input_length + inputs[i].value;
					num++;
				}
			}
			if(backPartOfReply!=""){
				var numOfCha = num + "";
				if(numOfCha.length==1)
					numOfCha = "0" + numOfCha;
				var replyToServer = numOfCha + backPartOfReply;
				$.blockUI({
					message: $('#waitBlock'),
					css:{
						top:'20%',
						width:'24%',
						height:'100px',
						left:'38%'
					}
				});
				var $params="terminalId=" + terminalId +"&charactersentence=" + replyToServer;
				$.ajax({
					type:'GET',
					contentType: 'application/json',  
					url:"character/setParameter.html",
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
			}
		}
	});
})
function clickforask(){
	$(".input_text").attr("disabled",true);
	askorset = true;
}
function clickforset(){
	$(".input_text").attr("disabled",false);
	var inputs = document.getElementsByName("input_text");
	for(var i=0;i<inputs.length;i++){
		var ii = inputs[i];
		ii.value = "";
	}
	askorset = false;;
}
function refresh(){
	window.location.reload();
}