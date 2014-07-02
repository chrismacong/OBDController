$(function(){ 
	$('#tt').datagrid({ 
		title:'企业用户', 
		iconCls:'icon-edit', 
		width:600, 
		height:250, 
		singleSelect:true, 
		columns:[[ 
{field:'email',title:'电子邮件',width:165,
	editor:{ 
		type:'validatebox', 
		options:{ 
			validType:'email' 
		} 
	} 
},
{field:'realname',title:'企业名称',width:150,editor:'text'}, 
{field:'tel',title:'电话号码',width:150,editor:'text'}, 
{field:'action',title:'操作',width:70,align:'center', 
	formatter:function(value,row,index){ 
		if (row.editing){ 
			var s = '<a href="#" onclick="saverow('+index+')">保存</a> '; 
			var c = '<a href="#" onclick="cancelrow('+index+')">取消</a>'; 
			return s+c; 
		} else { 
			var e = '<a href="#" onclick="editrow('+index+')">编辑</a> '; 
			var d = '<a href="#" onclick="deleterow('+index+')">删除</a>'; 
			return e+d; 
		} 
	} 
} 
]], 
toolbar:[{ 
	text:'增加', 
	iconCls:'icon-add', 
	handler:addrow 
},{ 
	text:'取消', 
	iconCls:'icon-cancel', 
	handler:cancelall 
}], 
onBeforeEdit:function(index,row){ 
	row.editing = true; 
	$('#tt').datagrid('refreshRow', index); 
	editcount++; 
}, 
onAfterEdit:function(index,row){ 
	row.editing = false; 
	$('#tt').datagrid('refreshRow', index); 
	editcount--; 
}, 
onCancelEdit:function(index,row){ 
	row.editing = false; 
	$('#tt').datagrid('refreshRow', index); 
	editcount--; 
} 
	}).datagrid('loadData',users).datagrid('acceptChanges'); 
}); 
var editcount = 0; 
function editrow(index){ 
	$('#tt').datagrid('beginEdit', index); 
} 
function deleterow(index){ 
	$.messager.confirm('确认','是否真的删除?',function(r){ 
		if (r){ 
			$('#tt').datagrid('deleteRow', index); 
			var selected_id = ids.splice(index,1);
			var $params="selected_id=" + selected_id;
			$.ajax({
				type:'GET',
				contentType: 'application/json',  
				url:"businessuserconfig/deleteuser.html?",
				data: $params,
				dataType: "json",
				success:function(data){
					if (data && data.success == "true") {
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
	}); 
} 
function saverow(index){ 
	$('#tt').datagrid('endEdit', index); 
	var rows = $('#tt').datagrid('getRows');
	var datas = rows[index];
	var $params="user_id=" + ids[index] + 
		"&email=" + datas.email + 
		"&realname=" + datas.realname + 
		"&tel=" + datas.tel;
	if(index>=ids.length){
		$.ajax({
			type:'GET',
			contentType: 'application/json',  
			url:"businessuserconfig/addbusinessuser.html?",
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
	}
	else{
		$.ajax({
			type:'GET',
			contentType: 'application/json',  
			url:"businessuserconfig/modifybusinessuser.html?",
			data: $params,
			dataType: "json",
			success:function(data){
				if (data && data.success == "true") {
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
function cancelrow(index){ 
	$('#tt').datagrid('cancelEdit', index); 
} 
function addrow(){ 
	if (editcount > 0){ 
		$.messager.alert('警告','当前还有'+editcount+'记录正在编辑，不能增加记录。'); 
		return; 
	} 
	$('#tt').datagrid('appendRow',{ 
		email:'', 
		realname:'', 
		nickname:'', 
		obdnumber:'', 
		carnumber:'', 
		cartype:'',
		idnumber:'',
		tel:''
	}); 
} 
function cancelall(){ 
	$('#tt').datagrid('rejectChanges'); 
} 