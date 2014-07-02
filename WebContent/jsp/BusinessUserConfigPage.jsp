<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>jQuery EasyUI</title> 
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/themes/icon.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery.easyui.min-1.2.0.js"></script>
<script>
	var id_list = "${id_list}";
	var ids = id_list.split(",");
	var users = ${json_object}; 
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/businessuserconfig.js"></script>
</head> 
<body> 
<h1>企业用户管理</h1> 
<table id="tt"></table> 
</body> 
</html> 