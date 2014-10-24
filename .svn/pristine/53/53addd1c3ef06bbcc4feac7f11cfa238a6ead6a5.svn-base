var index=0;
var onlineId_list = "";
var _menus;

$(function() {
	tabClose();
	tabCloseEven();

	$('#css3menu a').click(function() {
		var tagname = $(this).attr('name');
		$('#css3menu a').removeClass('active');
		$(this).addClass('active');

		var d = _menus[$(this).attr('name')];
		Clearnav();
		addNav(d);
		InitLeftMenu();
		if(tagname=="config")
			window.clearInterval(count_interval);
		else if(tagname=="obd"){
			Clearnav();
			initEvery5Second();
			count_interval = window.setInterval("initEvery5Second()", parseInt(refresh_Time));
		}
	});

	// 导航菜单绑定初始化
	$("#wnav").accordion( {
		animate : false
	});

	var firstMenuName = $('#css3menu a:first').attr('name');
	addNav(_menus[firstMenuName]);
	InitLeftMenu();

	$("#search_button").click(function(){
	    $(function () {
	        $.messager.prompt("设备查询", "请输入查询的设备ID或用户姓名", function (data) {
	            if (data) {
	            	Clearnav();
	            	window.clearInterval(count_interval);
	            	var $params="search_str=" + data;
	            	$.ajax({
	            		type:'GET',
	            		contentType: 'application/json',  
	            		url:"main/getSearch.html",
	            		data: $params,
	            		dataType: "json",
	            		success:function(data){
	            			if (data && data.success == "true") {
	            				var terminals = data.terminals;
	            				var online_or_offline = data.online_or_offline;
	            				var new_terminals = terminals.split(",");
	            				var online_b = online_or_offline.split(",");
	            				var pp = $('#wnav').accordion('panels');
	            				for(var i=0;i<new_terminals.length;i++){
	            					var this_menu = {
	            							obd : fg(new_terminals[i],online_b[i])
	            					}
	            					var firstMenuName = $('#css3menu a:first').attr('name');
	            					addOneNav(this_menu[firstMenuName]);
	            				}
	            				if(new_terminals.length>0){
	            					$('#wnav').accordion('select', new_terminals[0]);
	            				}
	            			}
	            		},
	            		error: function(data){
	            			alert("网络延时，请刷新界面");
	            		}
	            	});
	            }
	        });
	    });
	})
	
	$("#scan_button").click(function(){
		Clearnav();
		initEvery5Second();
		count_interval = window.setInterval("initEvery5Second()", parseInt(refresh_Time));
	})
});
function initEvery5Second(){
	var spp = $('#wnav').accordion('getSelected');
	var selected_title = "*&$";
	if(spp!=null)
		selected_title = spp.panel('options').title;
	var selected_still_there = false;
	$.ajax({
		type:'GET',
		contentType: 'application/json',  
		url:"main/getUpdate.html",
		dataType: "json",
		success:function(data){
			if (data && data.success == "true") {
				onlineId_list = data.newlist;
			}
		},
		error: function(data){
			alert("网络延时，请刷新界面");
		}
	});
	var new_terminals = onlineId_list.split(";");
	var pp = $('#wnav').accordion('panels');
	for(var i=0;i<pp.length;i++){
		var still_there = false;
		var title = pp[i].panel('options').title;
		for(var j=0;j<new_terminals.length;j++){
			if(new_terminals[j]==title)
				still_there = true;
		}
		if(!still_there)
			$('#wnav').accordion('remove', title);
	}
	for(var i=0;i<new_terminals.length;i++){
		var this_menu = {
				obd : ff(new_terminals[i])
		}
		var exist_already = false;
		for(var j=0;j<pp.length;j++){
			var title = pp[j].panel('options').title;
			if(new_terminals[i]==title)
				exist_already = true;
		}	
		if(!exist_already){
			var firstMenuName = $('#css3menu a:first').attr('name');
			addOneNav(this_menu[firstMenuName]);
		}
	}
	for(var i=0;i<new_terminals.length;i++){
		if(new_terminals[i]==selected_title)
			selected_still_there = true;
	}
	if(selected_still_there){
		$('#wnav').accordion('select', selected_title);
	}
	else if(new_terminals.length>0)
		$('#wnav').accordion('select', new_terminals[0]);
	/* old method of update by MC 2013-10-09*/
//	_menus = {
//			obd : ff(onlineId_list),
//			config : [{
//				"menuid" : "20",
//				"icon" : "icon-sys",
//				"menuname" : "全局设置",
//				"menus" : [ {
//					"menuid" : "201",
//					"menuname" : "更新服务器设置",
//					"icon" : "icon-nav",
//					"url" : "#"
//				}, {
//					"menuid" : "202",
//					"menuname" : "其他设置",
//					"icon" : "icon-nav",
//					"url" : "#"
//				} ]
//
//			} ]
//	};
//	Clearnav();
//	var firstMenuName = $('#css3menu a:first').attr('name');
//	addNav(_menus[firstMenuName]);
//	InitLeftMenu();
}
function Clearnav() {
	var pp = $('#wnav').accordion('panels');

	$.each(pp, function(i, n) {
		if (n) {
			var t = n.panel('options').title;
			$('#wnav').accordion('remove', t);
		}
	});

	pp = $('#wnav').accordion('getSelected');
	while (pp) {
		var title = pp.panel('options').title;
		$('#wnav').accordion('remove', title);
		pp = $('#wnav').accordion('getSelected');
	}
}
function addOneNav(data){
	$.each(data, function(i, sm) {
		var menulist = "";
		menulist += '<ul>';
		$.each(sm.menus, function(j, o) {
			menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="'
			+ o.url + '" ><span class="icon ' + o.icon
			+ '" >&nbsp;</span><span class="nav">' + o.menuname
			+ '</span></a></div></li> ';
		});
		menulist += '</ul>';

		$('#wnav').accordion('add', {
			title : sm.menuname,
			content : menulist,
			iconCls : 'icon ' + sm.icon
		});
	});
}
function addNav(data) {

	$.each(data, function(i, sm) {
		var menulist = "";
		menulist += '<ul>';
		$.each(sm.menus, function(j, o) {
			menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="'
			+ o.url + '" ><span class="icon ' + o.icon
			+ '" >&nbsp;</span><span class="nav">' + o.menuname
			+ '</span></a></div></li> ';
		});
		menulist += '</ul>';

		$('#wnav').accordion('add', {
			title : sm.menuname,
			content : menulist,
			iconCls : 'icon ' + sm.icon
		});
	});

	var pp = $('#wnav').accordion('panels');
	var t = pp[0].panel('options').title;
	$('#wnav').accordion('select', t);

}

//初始化左侧
function InitLeftMenu() {

	hoverMenuItem();

	$('#wnav li a').live('click', function() {
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = getIcon(menuid, icon);

		addTab(tabTitle, url, icon);
		$('#wnav li div').removeClass("selected");
		$(this).parent().addClass("selected");
	});

}

/**
 * 菜单项鼠标Hover
 */
function hoverMenuItem() {
	$(".easyui-accordion").find('a').hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

//获取左侧导航的图标
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus, function(i, n) {
		$.each(n, function(j, o) {
			$.each(o.menus, function(k, m){
				if (m.menuid == menuid) {
					icon += m.icon;
					return false;
				}
			});
		});
	});
	return icon;
}

function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	} else {
		$('#tabs').tabs('close', subtitle);
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	}
	tabClose();
}

function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	});
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			$('#tabs').tabs('close', t);
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

//本地时钟
function clockon() {
	var now = new Date();
	var year = now.getFullYear(); // getFullYear getYear
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	var hour = now.getHours();
	var minu = now.getMinutes();
	var sec = now.getSeconds();
	var week;
	month = month + 1;
	if (month < 10)
		month = "0" + month;
	if (date < 10)
		date = "0" + date;
	if (hour < 10)
		hour = "0" + hour;
	if (minu < 10)
		minu = "0" + minu;
	if (sec < 10)
		sec = "0" + sec;
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu
	+ ":" + sec + " " + week;

	$("#bgclock").html(time);

	var timer = setTimeout("clockon()", 200);
}
//设置登录窗口
function openPwd() {
    $('#w').window({
        title: '修改密码',
        width: 300,
        modal: true,
        shadow: true,
        closed: true,
        height: 160,
        resizable:false
    });
}
//关闭登录窗口
function closePwd() {
	$('#txtNewPass').val('');
	$('#txtRePass').val('');
    $('#w').window('close');
}



//修改密码
function serverLogin() {
    var $newpass = $('#txtNewPass');
    var $rePass = $('#txtRePass');
    if ($newpass.val() == '') {
        msgShow('系统提示', '请输入密码！', 'warning');
        return false;
    }
    if ($rePass.val() == '') {
        msgShow('系统提示', '请在一次输入密码！', 'warning');
        return false;
    }
    if ($newpass.val() != $rePass.val()) {
        msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
        return false;
    }
    /*
    $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
        msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
        $newpass.val('');
        $rePass.val('');
        close();
    })
   */
   // msgShow('系统提示', '恭喜' +$('#email').text(), 'info');
    $.post("http://"+window.location.host+'/OBDController/login/editpassword.html?newpass='+hex_md5($newpass.val())+'&email='+$('#email').text(), function() {
        msgShow('系统提示', '恭喜，密码修改成功！' , 'info');
        $newpass.val('');
        $rePass.val('');
        //close();
       // closePwd();
        $('#w').window('close');
    })
}
// 
