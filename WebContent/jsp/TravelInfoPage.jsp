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
	src="http://api.map.baidu.com/api?v=2.0&ak=15cf002106718ce6a60a7841ea39f127"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/changeMore.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript">
	var terminal_id;
	terminal_id = "${terminalId}";
	var reviews = "${review_position_info_str}";
	var myGeo = new BMap.Geocoder();
	var split_reviews = reviews.split("@");
	var array_date = new Array(split_reviews.length);
	var array_start_point = new Array(split_reviews.length);
	var array_stop_point = new Array(split_reviews.length);
	var points = new Array(split_reviews.length * 2);
	var longitute_list = new Array(points.length);
	var latitude_list = new Array(points.length);

	for ( var i = 0; i < split_reviews.length; i++) {
		array_date[i] = split_reviews[i].split(";")[0];
		array_start_point[i] = split_reviews[i].split(";")[1];
		array_stop_point[i] = split_reviews[i].split(";")[2];
		points[i] = new BMap.Point(array_start_point[i].split(",")[0],
				array_start_point[i].split(",")[1]);
		points[i + split_reviews.length] = new BMap.Point(array_stop_point[i]
				.split(",")[0], array_stop_point[i].split(",")[1]);
	}
	function callback(xyResults) {
		var xyResult = null;
		for ( var index in xyResults) {
			xyResult = xyResults[index];
			if (xyResult.error != 0) {
				continue;
			}//出错就直接返回;
			longitute_list[index] = xyResult.x;
			latitude_list[index] = xyResult.y;
		}
		if (latitude_list.length > 0) {
			var review_travel_info_table = $("#review_travel_info_table");
			for ( var i = 0; i < latitude_list.length / 2; i++) {
				if(latitude_list[i]!=null&&longitute_list[i]!=null){
					var this_date = array_date[i];
					var split_length = split_reviews.length;
					(function(x,date,split_l){
						myGeo.getLocation(new BMap.Point(base64decode(longitute_list[x]), base64decode(latitude_list[x])),function(singleresult1){
							var geocoder1 = singleresult1.address;
							var str = "<tr><td>" + date + "</td><td>" + geocoder1 + "</td>";
							(function(x,split_l,str){
								myGeo.getLocation(new BMap.Point(base64decode(longitute_list[x+split_l]), base64decode(latitude_list[x+split_l])),function(singleresult2){
									var geocoder2 = singleresult2.address;
									review_travel_info_table.append(str + "<td>" + geocoder2 + "</td><tr/>");
								});   
							})(x,split_l,str);	
						});  
					})(i,this_date,split_length);
				}
				else{
					review_travel_info_table.append("<tr><td>" + array_date[i] + "</td><td>未识别的GPS</td><td>未识别的GPS</td></tr>");
				}
			}
		}
	}
	setTimeout(function() {
		BMap.Convertor.transMore(points, 0, callback); //一秒之后开始进行坐标转换。参数2，表示是从GCJ-02坐标到百度坐标。参数0，表示是从GPS到百度坐标
	}, 200)
	var base64encodechars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var base64decodechars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62,
			-1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1,
			-1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1,
			26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
			43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
	function base64decode(str) {
		var c1, c2, c3, c4;
		var i, len, out;

		len = str.length;

		i = 0;
		out = "";
		while (i < len) {

			do {
				c1 = base64decodechars[str.charCodeAt(i++) & 0xff];
			} while (i < len && c1 == -1);
			if (c1 == -1)
				break;

			do {
				c2 = base64decodechars[str.charCodeAt(i++) & 0xff];
			} while (i < len && c2 == -1);
			if (c2 == -1)
				break;

			out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

			do {
				c3 = str.charCodeAt(i++) & 0xff;
				if (c3 == 61)
					return out;
				c3 = base64decodechars[c3];
			} while (i < len && c3 == -1);
			if (c3 == -1)
				break;

			out += String.fromCharCode(((c2 & 0xf) << 4) | ((c3 & 0x3c) >> 2));

			do {
				c4 = str.charCodeAt(i++) & 0xff;
				if (c4 == 61)
					return out;
				c4 = base64decodechars[c4];
			} while (i < len && c4 == -1);
			if (c4 == -1)
				break;
			out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
		}
		return out;
	}
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/travelinfo.js"></script>
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
</body>
</html>