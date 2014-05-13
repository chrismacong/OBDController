<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" content='0;url=login.html'/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.cookie.js"></script>


 <script type="text/javascript">
 var logincount = 0;
/*  function changeimg()

	{
		myimg.src = "${pageContext.request.contextPath}/jsp/YY_ImagePage.jsp";
	}*/
 function clear_loginpage(){
	    var email = document.getElementById("login-input-email");
		var email_message = document.getElementById("email-message");
		var password = document.getElementById("login-input-password");
		var password_message = document.getElementById("password-message");
		var certcode=document.getElementById("cert-code-input");
	  	var certcode_message=document.getElementById("cert-code-message");
	  	var tel = document.getElementById("login-input-tel");
	  	password.value="";
	  	certcode.value="";
  		email_message.style.display="none";
		email.className = 'inp enter-key';
		tel.className = 'inp enter-key';
		password.className = 'inp enter-key';
		password_message.style.display="none";
		certcode_message.style.display="none";
		certcode.className = 'inp enter-key';
		
 }
 function clear_forgotpage(){
	    var forgot_email = document.getElementById("forgot-input-email");
	  	var forgot_email_message = document.getElementById("forgot-email-message");
	  	forgot_email.value="";
	  	forgot_email.className = 'inp enter-key';
		forgot_email_message.style.display="none";
}
 

function checkEmail() {

	var email = document.getElementById("login-input-email");
	var email_message = document.getElementById("email-message");
	if (email.value != "") {
		var reg = /^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
		if (!reg.test(email.value)) {
			email_message.innerText = "请输入正确邮箱地址!";
			email_message.style.display = "";
			email.className = 'inp enter-key inp-error';
		}
	}
}
function checkTel(){
	var tel = document.getElementById("login-input-tel");
	var email_message = document.getElementById("email-message");
	email_message.style.display = "none";
	tel.className = "inp enter-key";
	var reg = /^[0-9]+$/;
	if(tel.value!=""){
	if(!reg.test(tel.value)){
		email_message.innerText = "只能输入数字!";
		email_message.style.display = "";
		tel.className = 'inp enter-key inp-error';
		return false;
	}else if(!(tel.value.length==11)){
		email_message.innerText = "请输入11位手机号码!";
		email_message.style.display = "";
		tel.className = 'inp enter-key inp-error';
		return false;
	}
	}
	return true;
}

function checkEmailInputing() {
	var email = document.getElementById("login-input-email");
	email.className = 'inp enter-key';
	var email_message = document.getElementById("email-message");
	email_message.innerText = "";
	email_message.style.display = "none";
	var password_message = document.getElementById("password-message");
	password_message.innerText = "";
	password_message.style.display = "none";
	

}

function checkTelInputing(){
	var tel = document.getElementById("login-input-tel");
	tel.className = 'inp enter-key';
	var email_message = document.getElementById("email-message");
	email_message.innerText = "";
	email_message.style.display = "none";
	var password_message = document.getElementById("password-message");
	password_message.innerText = "";
	password_message.style.display = "none";
	
}

function checkPasswordInputing() {
	var password = document.getElementById("login-input-password");
	password.className = 'inp enter-key';
	var password_message = document.getElementById("password-message");
	password_message.innerText = "";
	password_message.style.display = "none";

}
function checkCertcode() {
	var certcode = document.getElementById("cert-code-input");
	var certcode_message = document.getElementById("cert-code-message");
	if (certcode.value == "") {
		certcode_message.innerText = "请输入验证码！";
		certcode_message.style.display = "";
		certcode.className = 'inp enter-key inp-error';
		return false;
	}

	var $param = "certcode=" + certcode.value+"&_dc="+(new Date()).getTime();
	$.ajax({
		async : false,
		type : 'GET',
		contentType : 'application/json',
		url : "login/certcode_check.html",
		data : $param,
		dataType : "json",//返回的值以 JSON方式 解释
		success : function(data) {//如果调用成功

			if (data && data.success == "false") {
				certcode.value = "";
				certcode.className = 'inp enter-key inp-error';
				certcode_message.innerText = "验证码错误";
				certcode_message.style.display = "";
				changeimg();

			} else if (data && data.success == "true") {
				certcode_message.style.display = "none";
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			certcode.value = "";
			certcode.className = 'inp enter-key inp-error';
			certcode_message.innerText = "验证码错误";
			certcode_message.style.display = "";
			changeimg();
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);

		}
	});
	if(certcode_message.style.display==""){
		return false;
	}else if(certcode_message.style.display=="none"){
		return true;
	}
}
function checkCertcodeInputing() {
	var certcode = document.getElementById("cert-code-input");
	var certcode_message = document.getElementById("cert-code-message");

	certcode.className = 'inp enter-key';
	certcode_message.innerText = "";
	certcode_message.style.display = "none";

}

function loginCheck_Tel() {
	var tel = document.getElementById("login-input-tel");
	var password = document.getElementById("login-input-password");
	var password_message = document.getElementById("password-message");
	var rememberMe = document.getElementById("login-input-remember");
	var cert_code = document.getElementById("cert-code");
	if(tel.value==""){
		email_message.innerText = "请输入手机号码!";
		email_message.style.display = "";
		tel.className = 'inp enter-key inp-error';
		return false;
	}else if(!(checkTel())){
		return false;
	}else {
		if (cert_code.style.display == "") {
			if(!checkCertcode()){
				return;
			}
		}
		document.getElementById('loading-icon').style.display = '';
		document.getElementById('loading-mask').style.display = '';
		var rememberme = "unon";
		if (rememberMe.checked == true) {
			rememberme = rememberMe.value;
		}
		var $params = "tel=" + tel.value + "&password="
				+ hex_md5(password.value) + "&rememberMe=" + rememberme+"&_dc="+(new Date()).getTime();
		$.ajax({
					async : true,
					type : 'GET',
					contentType : 'application/json',
					url : "login/login_check_by_tel.html",
					data : $params,
					dataType : "json",//返回的值以 JSON方式 解释
					success : function(data) {//如果调用成功
						if (data && data.success == "false") {
							document.getElementById('loading-icon').style.display = 'none';
							document.getElementById('loading-mask').style.display = 'none';
							password.value = "";
							password.className = 'inp enter-key inp-error';
							if (window.logincount == 3) {
								password_message.innerText = "密码或手机号错误,是不是忘记密码了?";
								document.getElementById("cert-code").style.display = "";
								document.getElementById("cert-code-input").value = "";
							} else {
								window.logincount = window.logincount + 1;
								password_message.innerText = "密码或手机号错误，请重新输入";

							}
							password_message.style.display = "";
						} else if (data && data.success == "true") {
							if (data.rolename == "manager") {
								window.location.href = "${pageContext.request.contextPath}/main.html";
							} else if (data.rolename == "member") {
								window.location.href = "${pageContext.request.contextPath}/personal.html";
							}
						}
					},
					error : function(XMLHttpRequest, textStatus,
							errorThrown) {
						document.getElementById('loading-icon').style.display = 'none';
						document.getElementById('loading-mask').style.display = 'none';
						password.value = "";
						password_message.innerText = "密码或手机号错误，请重新输入";
						password_message.style.display = "";
								//alert(XMLHttpRequest.status);
								//alert(XMLHttpRequest.readyState);
								//alert(textStatus);
							}
						});

			}
		}

		function logincheck() {
            //如果现在处于tel标签页，则跳到loginCheck_Tel()方法里
			if(document.getElementById("login-input-tel").style.display==""){
				loginCheck_Tel();
				return;
			}
			// document.getElementById("login-input-password").value = hex_md5(document.getElementById("login-input-password").value);
			var email = document.getElementById("login-input-email");

			var email_message = document.getElementById("email-message");
			var password = document.getElementById("login-input-password");
			var password_message = document.getElementById("password-message");
			var rememberMe = document.getElementById("login-input-remember");
			var cert_code = document.getElementById("cert-code");
			var reg = /^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
			if (email.value == "") {

				email_message.innerText = "请输入邮箱地址!";
				email_message.style.display = "";
				email.className = 'inp enter-key inp-error';
				return;
			} else if (!reg.test(email.value)) {
				email_message.innerText = "请输入正确邮箱地址!";
				email_message.style.display = "";
				email.className = 'inp enter-key inp-error';
				return;
			} else if (password.value == "") {
				password_message.innerText = "请输入密码!";
				password_message.style.display = "";
				password.className = 'inp enter-key inp-error';
				return;
			} else {
				if (cert_code.style.display == "") {
					if(!checkCertcode()){
						return;
					}

				}

				document.getElementById('loading-icon').style.display = '';
				document.getElementById('loading-mask').style.display = '';
				var rememberme = "unon";
				if (rememberMe.checked == true) {
					rememberme = rememberMe.value;
				}
				var $params = "email=" + email.value + "&password="
						+ hex_md5(password.value) + "&rememberMe=" + rememberme+"&_dc="+(new Date()).getTime();
				$.ajax({
							async : true,
							type : 'GET',
							contentType : 'application/json',
							url : "login/login_check.html",
							data : $params,
							dataType : "json",//返回的值以 JSON方式 解释
							success : function(data) {//如果调用成功
								if (data && data.success == "false") {
									document.getElementById('loading-icon').style.display = 'none';
									document.getElementById('loading-mask').style.display = 'none';
									password.value = "";
									password.className = 'inp enter-key inp-error';
									if (window.logincount == 3) {
										password_message.innerText = "密码或邮箱错误,是不是忘记密码了?";
										document.getElementById("cert-code").style.display = "";
										document
												.getElementById("cert-code-input").value = "";
									} else {
										window.logincount = window.logincount + 1;
										password_message.innerText = "密码或邮箱错误，请重新输入";

									}
									password_message.style.display = "";
								} else if (data && data.success == "true") {
									if (data.rolename == "manager") {
										window.location.href = "${pageContext.request.contextPath}/main.html";
									} else if (data.rolename == "member") {
										window.location.href = "${pageContext.request.contextPath}/personal.html";
									}
								}
							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								document.getElementById('loading-icon').style.display = 'none';
								document.getElementById('loading-mask').style.display = 'none';
								password.value = "";
								password_message.innerText = "密码或邮箱错误，请重新输入";
								password_message.style.display = "";
								//alert(XMLHttpRequest.status);
								//alert(XMLHttpRequest.readyState);
								//alert(textStatus);
							}
						});

			}
		}

		function checkForgotEmailInputing() {
			var email = document.getElementById("forgot-input-email");
			email.className = 'inp enter-key';
			var email_message = document.getElementById("forgot-email-message");
			email_message.innerText = "";
			email_message.style.display = "none";

		}

		//forgot-input-email forgot-password-action-go forgot-email-message
		var xmlHttp;
		function createXMLHttpRequest() {

			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlHttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}

		function checkForgotEmail() {

			var email = document.getElementById("forgot-input-email");
			var email_message = document.getElementById("forgot-email-message");
			if (email.value == "") {
				document.getElementById('loading-icon').style.display = 'none';
				document.getElementById('loading-mask').style.display = 'none';
				email_message.innerText = "";
				email_message.style.display = "none";
				email_message.innerText = "邮箱不能为空!";
				email_message.style.display = "";
				email.className = 'inp enter-key inp-error';
				return false;
			}
			if (email.value != "") {
				var reg = /^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
				if (!reg.test(email.value)) {
					document.getElementById('loading-icon').style.display = 'none';
					document.getElementById('loading-mask').style.display = 'none';
					email_message.innerText = "";
					email_message.style.display = "none";
					email_message.innerText = "请输入正确邮箱地址!";
					email_message.style.display = "";
					email.className = 'inp enter-key inp-error';
					return false;
				}
			}
			createXMLHttpRequest();
			xmlHttp.onreadystatechange = function() {
				var responseContext;
				if (xmlHttp.readyState == 4) {
					if (xmlHttp.status == 200) {
						responseContext = xmlHttp.responseText;
						if (responseContext.indexOf("true") != -1) {
							document.getElementById('loading-icon').style.display = 'none';
							document.getElementById('loading-mask').style.display = 'none';
							email_message.innerText = "";
							email_message.style.display = "none";
							email_message.innerText = "帐号不存在，请重新输入!";
							email_message.style.display = "";
							email.className = 'inp enter-key inp-error';
						} else {
							email_message.innerText = "";
							email_message.style.display = "none";
						}
					}
				}
			}
			xmlHttp.open("GET",
					"${pageContext.request.contextPath}/register/check_email.html?email="
							+ email.value, true);
			xmlHttp.send(null);
			if (email_message.innerText == "") {
				return true;
			} else {
				return false;
			}

		}

		function passwordRegetCheck() {
			document.getElementById('loading-icon').style.display = '';
			document.getElementById('loading-mask').style.display = '';
			var email = document.getElementById("forgot-input-email");
			if (checkForgotEmail()) {

				$.ajax({
							async : true,
							type : 'POST',
							url : "mail/forget_password_sendmail.html?"
									+ "email=" + email.value+"&_dc="+(new Date()).getTime(),
							dataType : "json",//返回的值以 JSON方式 解释
							success : function(data) {//如果调用成功

								if (data && data.success == "false") {
									document.getElementById('loading-icon').style.display = 'none';
									document.getElementById('loading-mask').style.display = 'none';
									document.getElementById('3').style.display = '';
									document.getElementById('1').style.display = 'none';

								} else if (data && data.success == "true") {
									document.getElementById('loading-icon').style.display = 'none';
									document.getElementById('loading-mask').style.display = 'none';
									document
											.getElementById("login-input-email").value = email.value;
									document.getElementById('2').style.display = '';
									document.getElementById('1').style.display = 'none';
								}
							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								document.getElementById('loading-icon').style.display = 'none';
								document.getElementById('loading-mask').style.display = 'none';
								document.getElementById('3').style.display = '';
								document.getElementById('1').style.display = 'none';
								alert(XMLHttpRequest.status);
								alert(XMLHttpRequest.readyState);
								alert(textStatus);
							}
						});
			}

		}

		 		function changeimg()

		 {

		 var myimg = document.getElementById("cert-code-img");
		 now = new Date();
		 myimg.src = "${pageContext.request.contextPath}/jsp/YY_ImagePage.jsp?code="
		 + now.getTime();
		 }
		 //2px solid #fff;	#626262 
		//switch tag切换
		function loginByEmailOnMouseOver() {
			var loginByEmail = document.getElementById("login-by-email-switch-tag");
			var loginByTel = document.getElementById("login-by-tel-switch-tag");
			loginByEmail.style.borderBottom="0px solid #e6e6e6";
			loginByTel.style.borderBottom="2px solid #e6e6e6";
			loginByEmail.style.color="#e6e6e6";
			loginByTel.style.color="#626262";
			var tel = document.getElementById("login-input-tel");
			var email = document.getElementById("login-input-email");
			var email_eg = document.getElementById("email-e.g.");
			email.style.display = "";
			tel.style.display = "none";
			email_eg.style.display = "";
			clear_loginpage();
			checkEmail();
		}
		/*
		function loginByEmailOnMouseOut() {
			
			var loginByEmail = document
					.getElementById("login-by-email-switch-tag");
			
		}
		*/
		function loginByTelOnMouseOver() {
			var loginByEmail = document.getElementById("login-by-email-switch-tag");
			var loginByTel = document.getElementById("login-by-tel-switch-tag");
			loginByEmail.style.borderBottom="2px solid #e6e6e6";
			loginByTel.style.borderBottom="0px solid #e6e6e6";
			loginByEmail.style.color="#626262";
			loginByTel.style.color="#e6e6e6";
			var tel = document.getElementById("login-input-tel");
			var email = document.getElementById("login-input-email");
			var email_eg = document.getElementById("email-e.g.");
			email.style.display = "none";
			tel.style.display = "";
			email_eg.style.display = "none";
			clear_loginpage();
			checkTel();
		}
		/*
		function loginByTelOnMouseOut() {
			var loginByTel = document.getElementById("login-by-tel-switch-tag");
			
		}
		*/
		/* 点击手机号登录标签页*/
		function loginByTelOnClick() {
			var loginByEmail = document.getElementById("login-by-email-switch-tag");
			var loginByTel = document.getElementById("login-by-tel-switch-tag");
			loginByEmail.style.borderBottom="2px solid #e6e6e6";
			loginByTel.style.borderBottom="0px solid #e6e6e6";
			loginByEmail.style.color="#626262";
			loginByTel.style.color="#e6e6e6";
			var tel = document.getElementById("login-input-tel");
			var email = document.getElementById("login-input-email");
			var email_eg = document.getElementById("email-e.g.");
			email.style.display = "none";
			tel.style.display = "";
			email_eg.style.display = "none";
			clear_loginpage();
			checkTel();
			tel.focus(); 
			
		}
		/* 点击邮箱登陆标签页 */
		function loginByEmailOnClick() {
			var loginByEmail = document.getElementById("login-by-email-switch-tag");
			var loginByTel = document.getElementById("login-by-tel-switch-tag");
			loginByEmail.style.borderBottom="0px solid #e6e6e6";
			loginByTel.style.borderBottom="2px solid #e6e6e6";
			loginByEmail.style.color="#e6e6e6";
			loginByTel.style.color="#626262";
			var tel = document.getElementById("login-input-tel");
			var email = document.getElementById("login-input-email");
			var email_eg = document.getElementById("email-e.g.");
			email.style.display = "";
			tel.style.display = "none";
			email_eg.style.display = "";
			clear_loginpage();
			checkEmail();
			email.focus(); 
		}
	</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yy1.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy_md5.js"></script>


<title>OBD在线支持系统</title>
</head>

<body>
<jsp:useBean  id="user"
	class="com.fix.obd.web.model.YY_User" scope="session"></jsp:useBean>
	
	
	
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 100px; background: black;line-height: 55px; color: #fff; font-family: Verdana, Î¢ï¿½ï¿½ï¿½Åºï¿½, ï¿½ï¿½ï¿½ï¿½">
		 <span style="padding-left: 50px; padding-top:10px; font-size: 50px; float: left; "> <img
			src="${pageContext.request.contextPath}/images/logo.png" width="400" height="80" align="absmiddle" />
		</span>
	</div>

	<div id="backpages">
		<div class="banner has-dots" style="overflow: hidden; width:100%; height: 100%; ">
			<ul style="width: 400%; position: relative; left: 0%; height: 100%;">
				<li id="backpages-1" style="background-image: url(${pageContext.request.contextPath}/images/car1.jpg);filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${pageContext.request.contextPath}/images/car1.jpg',sizingMethod='scale'); background-size: 100%; width: 25%;"></li>
				
				<li id="backpages-2" style="background-image: url(${pageContext.request.contextPath}/images/car2.jpg);filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${pageContext.request.contextPath}/images/car2.jpg',sizingMethod='scale'); background-size: 100%; width: 25%;"></li>
				
				<li id="backpages-3" style="background-image: url(${pageContext.request.contextPath}/images/car3.jpg);filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${pageContext.request.contextPath}/images/car3.jpg',sizingMethod='scale'); background-size: 100%; width: 25%;"></li>
				
				<li id="backpages-4" style="background-image: url(${pageContext.request.contextPath}/images/car4.jpg);filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${pageContext.request.contextPath}/images/car4.jpg',sizingMethod='scale'); background-size: 100%; width: 25%;"></li>
			</ul>
		<ol class="dots"><li class="dot active">1</li><li class="dot">2</li><li class="dot">3</li><li class="dot">4</li></ol>
		</div>
		
      </div>
      
		
        
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy/jquery-latest.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy/jquery.event.move.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy/jquery.event.swipe.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy/unslider.min.js"></script>
		<script>
			if(window.chrome) {
				$('.banner li').css('background-size', '100% 100%');
			}
			
			$('.banner').unslider({
				fluid: true,
				dots: true,
				speed: 500
			});
		
			//  Find any element starting with a # in the URL
			//  And listen to any click events it fires
			$('a[href^="#"]').click(function() {
				//  Find the target element
				var target = $($(this).attr('href'));
				
				//  And get its position
				var pos = target.offset(); // fallback to scrolling to top || {left: 0, top: 0};
				
				//  jQuery will return false if there's no element
				//  and your code will throw errors if it tries to do .offset().left;
				if(pos) {
					//  Scroll the page
					$('html, body').animate({
						scrollTop: pos.top,
						scrollLeft: pos.left
					}, 1000);
				}
				
				//  Don't let them visit the url, we'll scroll you there
				return false;
			});
			
			var GoSquared = {acct: 'GSN-396664-U'};
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy/tracker.js"></script>
	
	
	
	
	
	

<div id="user-auth-dialog-container" style="position: absolute; display: block; z-index: 999999; margin: 170px 0px; zoom: 1; top: 25px; visibility: visible; " lang="en" >
<!-- 
     <div class="switch-tab" id="login-by-email-switch-tag" onmouseover="loginByEmailOnMouseOver();" onmouseout="loginByEmailOnMouseOut();" onclick="loginByEmailOnClick();">邮箱登录</div>
     <div class="switch-tab" id="login-by-tel-switch-tag" onmouseover="loginByTelOnMouseOver()" onmouseout="loginByTelOnMouseOut();" onclick="loginByTelOnClick()">手机号登录</div>
      -->
     <div class="dialog-body" style="filter:alpha(opacity=90);-moz-opacity:0.9; opacity: 0.9; background:black;border:2px solid #e6e6e6; "> 
          
         <div class="loading loading-icon" id="loading-icon" style="display:none;"></div>
         <div class="loading loading-mask" id="loading-mask" style="display:none;"></div> 
                                      
		 <div class="dialog-tab dialog-box-login" id="login-dialog-box" onkeydown="if(event.keyCode==13){logincheck();}" style="display:">                
		     <div class="loginFunc" style="position:absolute;width: 100%;height: 55px;overflow: hidden;clear: both;margin:-34px -35px -23px;">
		        <div class="loginFuncEmail" id="login-by-email-switch-tag" onmouseover="loginByEmailOnMouseOver();" onmouseout="loginByEmailOnMouseOut();" onclick="loginByEmailOnClick();"
		            style="width: 191px;height: 100%;overflow: hidden;position: relative;line-height: 55px;float: left;font-size: 22px;text-align: center;color: #fff;cursor: pointer;border-right:1px solid #e6e6e6;">
		                                邮箱账号登录
		        
		        </div>
		        
		        <div class="loginFuncMobile" id="login-by-tel-switch-tag"  onmouseover="loginByTelOnMouseOver()" onmouseout="loginByTelOnMouseOut();" onclick="loginByTelOnClick();"
		        style="width: 191px;height: 100%;overflow: hidden;position: relative;line-height: 55px;float: left;font-size: 22px;text-align: center;color: #626262;cursor: pointer;border-left:1px solid #e6e6e6;border-bottom:2px solid #e6e6e6; ">
                  手机号登录
		        </div>
		   
		    
		   </div>
		    <div class="clearfix"></div>  
                               
			     
                             
			<div class="input-box text-input-box" style="padding-top:40px;">                            
			  <input  autofocus="" tabindex="1" id="login-input-email" name="email" type="text" maxlength="60" class="inp enter-key" validation="notempty;email" placeholder="邮箱" onkeydown="checkEmailInputing()" onchange="checkEmail()" value="${email}" />
			  <!--手机号登录输入框  -->
			  <input  autofocus="" tabindex="1" id="login-input-tel" name="tel" type="text" maxlength="11" class="inp enter-key" placeholder="手机号" onkeydown="checkTelInputing()" onchange="checkTel()" style="display:none;" value="${tel}" />
			  <label name="email" class="input-box-desc hidden-element error-message" style="display: ;" id="email-message">${email_message}</label>           
			  <label class="input-box-desc desc-message" id="email-e.g.">e.g. yourname@gmail.com</label>                        
			</div>                        
			                       
		   <div class="input-box text-input-box">                           
		      <input  tabindex="2" id="login-input-password" name="password" type="password" maxlength="15" class="inp enter-key" validation="notempty;length:{4,15};password" placeholder="密码" value="${password}" onkeydown="checkPasswordInputing()"  />                            
			  <label name="password" class="input-box-desc hidden-element error-message" style="display: ;" id="password-message" >${password_message}</label>              
              <span class="input-box-desc desc-message"></span>                        
			</div>      
			
 
			
			<div class="input-box text-input-box" style="display: none" id="cert-code"> 
		     <div style="position: absolute; left:110px; ">                          
			<img border=0 src="${pageContext.request.contextPath}/jsp/YY_ImagePage.jsp" id="cert-code-img" ></img>
			<a href="javascript:changeimg()" class="switch-context switch-context-link left" id="cert-code-link">看不清，换一张 </a> 
			</div>
			<input tabindex="3" id="cert-code-input" name="cert-code" type="text" maxlength="4" class="inp enter-key" placeholder="验证码" style="width:70px; " onchange="checkCertcode()" onkeydown="checkCertcodeInputing()"/>  
			<label name="cert-code" class="input-box-desc hidden-element error-message" style="display: none;" id="cert-code-message" ></label>              
			</div>
			    
                
			      
			<div class="dialog-box">                            
			   <span class="dialog-checkbox">                              
			     <input tabindex="5" id="login-input-remember" name="rememberMe" type="checkbox" checked="checked">                
				 <label for="login-input-remember">记住我！</label>                               
				 <a id="login-switch-context-forgot-password" tabindex="9" href="#" class="switch-context switch-context-link right" rev="forgot-password" onclick="document.getElementById('login-dialog-box').style.display='none';document.getElementById('forgot-password-dialog-box').style.display='';">忘记密码？</a>
			

			   </span>                        
			</div>                        
			<div class="clearfix"></div>                        
			<div class="action-box" style="filter:alpha(opacity=200);-moz-opacity:1.0; opacity: 1.0;">                            
			   <input type="submit" tabindex="6" id="login-action-go" class="dialog-button action-login" value="登录"  onclick="logincheck()"  >                    
		    </div>
             
            <div class="dialog-box">                          
			   <span class="dialog-checkbox">                                                             
				  <label for="new-user">还不是我们的用户？</label><a id="login-new-user" tabindex="9" href="${pageContext.request.contextPath}/register.html" class="switch-context switch-context-link " rev="new-user">立即注册</a>
			   </span>                        
			</div>                    
		</div>  <!-- form -->  
		
		
		
		<div class="dialog-tab dialog-box-forgot-password" id="forgot-password-dialog-box" style=" display: none;" >  
		 <div id="1" onkeydown="if(event.keyCode==13){passwordRegetCheck();}">              
		    <div class="clearfix"></div>  
                               
			<div class="dialog-box">                            
			   <div class="dialog-title">忘记密码?</div>                       
		    </div>      
             
            <div class="forgot-desc" style="color: white;">
                                                      请输入您的登录邮箱地址，我们将会发送重设密码的链接地址于您的邮箱
            </div>                
			<div class="input-box text-input-box">                            
			  <input  autofocus="" tabindex="1" id="forgot-input-email" name="email" type="text" maxlength="60" class="inp enter-key" validation="notempty;email" placeholder="邮箱" onkeydown="checkForgotEmailInputing()" onchange="checkForgotEmail()" value="" />
			  
			  <label name="email" class="input-box-desc hidden-element error-message" style="display: ;" id="forgot-email-message">${forgot_email_message}</label>           
			  <label class="input-box-desc desc-message">e.g. yourname@gmail.com</label>                        
			</div>                        
			                       
		    <div class="dialog-box">                            
			    <a class="switch-context switch-context-link switch-context-label" id="forgot-password-switch-context-login" href="#" onclick="document.getElementById('login-dialog-box').style.display='';document.getElementById('forgot-password-dialog-box').style.display='none';clear_forgotpage();" rev="login"><< 我要登录 </a>
			</div>                        
			<div class="clearfix"></div>  
			                     
			<div class="action-box">                            
			   <input type="submit" id="forgot-password-action-go" class="dialog-button action-forgot" value="发送"  onclick="passwordRegetCheck();" >                    
		    </div>
             
            </div>  <!-- 1 -->   
            
             
            <div id="2" style="display:none;"  onkeydown="if(event.keyCode==13){alert('none');document.getElementById('login-dialog-box').style.display='';document.getElementById('forgot-password-dialog-box').style.display='none';document.getElementById('1').style.display='';document.getElementById('2').style.display='none';clear_loginpage();clear_forgotpage();}">              
		    <div class="clearfix"></div>  
                               
			<div class="dialog-box forgot-done-box"> 
			   <span class="forgot-done-img"> </span>                           
			   <span class="forgot-done-desc" style="color: white;">重设密码链接已发至邮箱 </span>                       
		    </div>      
             
                                  
			<div class="clearfix"></div>                        
			<div class="action-box">                            
			   <input type="submit" id="forgot-password-action-done" class="dialog-button action-forgot" value="完成"  onclick="document.getElementById('login-dialog-box').style.display='';document.getElementById('forgot-password-dialog-box').style.display='none';document.getElementById('1').style.display='';document.getElementById('2').style.display='none';clear_loginpage();clear_forgotpage();" >                    
		    </div>
             
            </div>  <!-- 2 -->  
            
             
            <div id="3" style="display:none;" onkeydown="if(event.keyCode==13){document.getElementById('1').style.display='';document.getElementById('3').style.display='none';}">              
		    <div class="clearfix"></div>  
                               
			<div class="dialog-box forgot-done-box"> 
			  <!--   <span class="forgot-done-img"> </span>   --> 
			                       
			   <span class="forgot-done-desc" style="color: red;">重设密码链接发送失败！ </span>                       
		    </div>      
             
                                  
			<div class="clearfix"></div>                        
			<div class="action-box">                            
			   <input type="submit" id="forgot-password-action-done" class="dialog-button action-forgot" value="重试 "  onclick="document.getElementById('1').style.display='';document.getElementById('3').style.display='none';"  >                    
		    </div>
             
            </div>  <!-- 3 -->
                          
		</div>  <!-- form -->
		                      
      </div>
  </div>
  
</body>
</html>
