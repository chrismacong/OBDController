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
		var count = 0;
		for ( var i = 0; i < latitude_list.length / 2; i++) {
			if(latitude_list[i]!=null&&longitute_list[i]!=null){
				var str = "<tr><td>" + array_date[i] + "</td><td>" + array_start_point[i] + "</td><td>" + array_stop_point[i] + "</td></tr>";
				review_travel_info_table.append(str);
				count++;
			}
		}
		var cells = $("#review_travel_info_table tbody tr td");
		for ( var i = 0; i < latitude_list.length / 2; i++) {
			if(latitude_list[i]!=null&&longitute_list[i]!=null){
				var split_length = split_reviews.length;
				(function(x,split_l){
					myGeo.getLocation(new BMap.Point(base64decode(longitute_list[x]), base64decode(latitude_list[x])),function(singleresult1){
						var geocoder1 = singleresult1.address;
						cells[3*x+1].innerText = geocoder1;
						(function(x,split_l){
							myGeo.getLocation(new BMap.Point(base64decode(longitute_list[x+split_l]), base64decode(latitude_list[x+split_l])),function(singleresult2){
								var geocoder2 = singleresult2.address;
								cells[3*x+2].innerText = geocoder2;
							});   
						})(x,split_l); 
					});  
				})(i,split_length);
			}
			else{
				cells[3*i+1].innerText = "未识别的GPS";
				cells[3*i+2].innerText = "未识别的GPS";
			}
		} 
		setTimeout(function(){
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
							$.blockUI({
								message: $('#reviewBlock'),
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
		},1000)
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