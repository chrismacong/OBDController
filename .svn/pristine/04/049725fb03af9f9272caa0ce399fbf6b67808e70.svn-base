<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/global.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/travelinfo.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript">
	var terminal_id;
	terminal_id = "${terminalId}";
	var reviews = "${review_position_info_str}";
	var split_reviews = reviews.split("@");
	var array_date = new Array(split_reviews.length);
	var array_start_point = new Array(split_reviews.length);
	var array_stop_point = new Array(split_reviews.length);
	for ( var i = 0; i < split_reviews.length; i++) {
		array_date[i] = split_reviews[i].split(";")[0];
		array_start_point[i] = split_reviews[i].split(";")[1];
		array_stop_point[i] = split_reviews[i].split(";")[2];
	}
	setTimeout(function(){
		if (array_date.length > 0) {
			var review_travel_info_table = $("#review_travel_info_table");
			for ( var i = 0; i < array_date.length; i++) {
				var str = "<tr><td>" + array_date[i] + "</td><td>" + array_start_point[i] + "</td><td>" + array_stop_point[i] + "</td></tr>";
				review_travel_info_table.append(str);
			}
		}
		var rows = $("#review_travel_info_table tbody tr").length;
		for(var i=0;i<rows;i++){
			var row_str = $("#review_travel_info_table tbody tr")[i].innerHTML;
			if(row_str==null||row_str==""){
				$("#review_travel_info_table")[0].deleteRow(i+1);
				rows = $("#review_travel_info_table tbody tr").length;
				i--;
			}
		}
		for(var i=0;i<rows;i++){
			for(var j=i+1;j<rows;j++){
				var html1 = $("#review_travel_info_table tbody tr")[i].innerText;
				var html2 = $("#review_travel_info_table tbody tr")[j].innerText;
				var date1 = html1.substring(0,17);
				var date2 = html2.substring(0,17);
				if(date1<date2){
					var tmp = $("#review_travel_info_table tbody tr")[i].innerHTML;
					$("#review_travel_info_table tbody tr")[i].innerHTML = $("#review_travel_info_table tbody tr")[j].innerHTML;
					$("#review_travel_info_table tbody tr")[j].innerHTML = tmp;
				}
			}
		}
		$("#review_travel_info_table tbody tr").click(function(){
			var $params="terminalId=" + terminal_id + "&starttime=" + this.innerText.substring(0,17);
			$.ajax({
				type:'GET',
				contentType: 'application/json',  
				url:"travelinfo/getreviewedinfo.html",
				data: $params,
				dataType: "json",
				success:function(data){
					if (data && data.success == "true") {
						$("#review_content")[0].innerHTML = data.review_result_str;
						$("#score_content")[0].innerHTML = "当次行程驾驶评分：<font style='font-weight:bold;font-style:italic;font-size:20px;'>" + data.score + "</font> 分";
						$.blockUI({
							message: $('#reviewBlock'),
							css:{
								top:'2%',
								width:'60%',
								left:'20%'
							}
						});
					}
				}
			}); 
		});
		$(".block_close_btn").click(function(){
			$.unblockUI();
		});
	},500)
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/travelinfo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/remove_loading.js"></script>
<title>行程记录信息</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId}
		的行程记录</div>
	<button id="refresh_button">手动刷新</button>
	<button id="get_travel_info_button">获取最近行程记录</button>
	<button id="get_which_travel_info_button">获取特定行程记录</button>
	<select id="travel_info_select">
		<option>0</option>
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
		<option>6</option>
		<option>7</option>
		<option>8</option>
		<option>9</option>
		<option>10</option>
		<option>11</option>
		<option>12</option>
		<option>13</option>
		<option>14</option>
		<option>15</option>
		<option>16</option>
		<option>17</option>
		<option>18</option>
		<option>19</option>
	</select>
	<table class="bordered" id="travel_info_table">
		<thead>
			<tr>
				<th>项目</th>
				<th>内容</th>
				<th>说明</th>
			</tr>
		</thead>
		<c:forEach items="${character_list}" var="xx" varStatus="loop">
			<tr>
				<td>${xx.getName()}</td>
				<td>${xx.getContent()}</td>
				<td>${xx.getExtra()}</td>
			</tr>
		</c:forEach>
	</table>
	<div id="review_travel_info_div">近期行程</div>
	<table class="bordered" id="review_travel_info_table">
		<thead>
			<tr>
				<th>时间</th>
				<th>GPS起点</th>
				<th>GPS终点</th>
			</tr>
		</thead>
	</table>
	<div id="waitBlock" style="display: none; cursor: default;"
		align="center">
		<div id="operating">正在操作..</div>
		<p id="waiting"></p>
	</div>
	<div id="reviewBlock" style="display: none; cursor: default;"
		align="center">
		<div>历史行程</div>
		<a class="block_close_btn"></a> <br />
		<div id="review_content"></div>
		<div id="score_content"></div>
	</div>
</body>
</html>