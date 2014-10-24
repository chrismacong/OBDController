<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy_md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.complexify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.placeholder.min.js"></script>
 <script type="text/javascript">
      var xmlHttp;
      function createXMLHttpRequest(){
    	  if (window.XMLHttpRequest)
    	  {// code for IE7+, Firefox, Chrome, Opera, Safari
    		  xmlHttp=new XMLHttpRequest();
    		  /* alert("in createXMLHttpRequest:XMLHttpRequest"); */
    	  }
    	  else
    	  {// code for IE6, IE5
    		  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    		  /* alert("in createXMLHttpRequest:ActiveXObject"); */
    	  }
      }
      
      function checkEmail()
    {
	    var email = document.getElementById("register-email");
		var email_message = document.getElementById("email-message");
		email_message.style.display="none";
		document.getElementById("check-mark-wrong-email").style.display = "none";
		document.getElementById("check-mark-right-email").style.display = "none";
		if(email.value==""){
			email_message.innerText="邮箱地址不能为空";
			email_message.style.display="";
			document.getElementById("check-mark-wrong-email").style.display = "";
			return false;
		}
		if(email.value!=""){
			//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			var reg=/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
			if(!reg.test(email.value)){
		       //x.className = 'error-message'; 
		       email_message.innerText="请输入正确邮箱地址!";
			   email_message.style.display="";
			   document.getElementById("check-mark-wrong-email").style.display = "";
			   return false;
			}
		}
		createXMLHttpRequest();
 		xmlHttp.onreadystatechange = function(){
			var responseContext;
			if(xmlHttp.readyState == 4){
				if(xmlHttp.status == 200){
					responseContext = xmlHttp.responseText;
					if(responseContext.indexOf("true")!=-1){
						//alert("恭喜您，注册email有效");                      //验证成功
						document.getElementById("check-mark-right-email").style.display = "";
					}else{
						email.value="";
					    email_message.innerText="邮箱已注册，请重新输入!";
						email_message.style.display="";
						document.getElementById("check-mark-wrong-email").style.display = "";
					}
				}
			}
		} 
		xmlHttp.open("GET", "${pageContext.request.contextPath}/register/check_email.html?email="+email.value,true);
		xmlHttp.send(null);
		if(email_message.style.diaplay==""){
			return false;
		}else{
			return true;
		}
    }
       //密码强度
      $(function(){
			$("#register-password").complexify({}, function(valid, complexity){
				if (!valid) {
					$('#complexity').animate({'width':complexity + '%'}).removeClass('valid').addClass('invalid');
				} else {
					$('#complexity').animate({'width':complexity + '%'}).removeClass('invalid').addClass('valid');
				}
				$('#complexity').html(Math.round(complexity) + '%');
			});
		});
/* 
      function checkStrong(sValue)
      {
       var modes = 0;
       if (sValue.length < 6) return modes;
       if (/\d/.test(sValue)) modes++; //数字
       if (/[a-z]/.test(sValue)) modes++; //小写
       if (/[A-Z]/.test(sValue)) modes++; //大写  
       if (/\W/.test(sValue)) modes++; //特殊字符
       switch (modes)
       {
        case 1:
         return 1;
         break;
        case 2:
         return 2;
        case 3:
        case 4:
         return sValue.length < 12 ? 3 : 4
         break;
       }
      }       */
      function checkPasswordComplexity(){
          
          
      }

       function passwordComplexity(){
    	  document.getElementById("block-wrap").style.display="";
      } 
       function checkPassword(){
        	var userPass = document.getElementById("register-password"); 
          	var userRePass = document.getElementById("register-password-again"); 
          	var passwordMessage = document.getElementById("password-message");
          	//初始化
          	passwordMessage.style.display="none";
          	document.getElementById("check-mark-right-password").style.display = "none";
          	document.getElementById("check-mark-wrong-password").style.display = "none";
          	document.getElementById("check-mark-right-password-again").style.display = "none";
          	document.getElementById("check-mark-wrong-password-again").style.display = "none";
          	if(userPass.value==""){
          		passwordMessage.innerText="请输入密码";
          		passwordMessage.style.display="";
          		document.getElementById("check-mark-wrong-password").style.display = "";
          		return false;
          	}else if(userRePass.value==""){
          		passwordMessage.innerText="请再次输入密码";
          		passwordMessage.style.display="";
          		document.getElementById("check-mark-wrong-password-again").style.display = "";
          		return false;
            }
          	if(!(userPass.value==userRePass.value)){
          		passwordMessage.innerText="两次密码输入不同，请重新输入";
          		passwordMessage.style.display="";
          		document.getElementById("register-password").value="";
          		document.getElementById("register-password-again").value="";
          		document.getElementById("check-mark-wrong-password").style.display = "";
          		document.getElementById("check-mark-wrong-password-again").style.display = "";
          		return false;
          	}else{
          		passwordMessage.innerText="";
              	passwordMessage.style.display="none";
              	document.getElementById("check-mark-right-password").style.display = "";
          		document.getElementById("check-mark-right-password-again").style.display = "";
              	return true;
          	}
          	
      }
      
       function checkRealName(){
    	   var filter=/^[A-Za-z\u4e00-\u9fa5]+$/;
    	   var name = document.getElementById("register-name");
    	   var nameMessage = document.getElementById("name-message");
    	   nameMessage.style.display = "none";
    	   document.getElementById("check-mark-right-realname").style.display = "none";
    	   document.getElementById("check-mark-wrong-realname").style.display = "none";
    	   if(name.value==""){
    		   nameMessage.innerText="真实姓名不能为空";
     		  nameMessage.style.display = "";
     		 document.getElementById("check-mark-wrong-realname").style.display = "";
     		 return false;
    	   }
    	   if(!filter.test(name.value)){
    		  nameMessage.innerText="请输入真实姓名，不能有标点符号和数字";
    		  nameMessage.style.display = "";
    		  document.getElementById("check-mark-wrong-realname").style.display = "";
    		  return false;
    	  }	    
    	   document.getElementById("check-mark-right-realname").style.display = "";
    	   return true;
      } 
       
       function checkId(){
    	   var id = document.getElementById("register-id").value;
    	   var idMessage = document.getElementById("id-message");
    	   idMessage.style.display = "none";
    	   var reg = /^[\d]+$/;
    	   document.getElementById("check-mark-wrong-id").style.display = "none";
    	   document.getElementById("check-mark-right-id").style.display = "none";
    	   if(id==""){
    		   idMessage.innerText = "身份证号不能为空";
    		   idMessage.style.display = "";
    		   document.getElementById("check-mark-wrong-id").style.display = "";
    		   return false;
    	   }
    	   if(!reg.test(id)){     //只能输入数字
			   idMessage.innerText = "只能输入数字";
    		   idMessage.style.display = "";
    		   document.getElementById("check-mark-wrong-id").style.display = "";
    		   return false;
		   }
    	   if(!(id.length==18)){
    		   idMessage.innerText = "请输入18位身份证号";
    		   idMessage.style.display = "";
    		   document.getElementById("check-mark-wrong-id").style.display = "";
    		   return false;
    	   }
    	   document.getElementById("check-mark-right-id").style.display = "";
    	   return true;
       }
       
       function checkNickname(){
    	   var nickname = document.getElementById("register-nickname").value;
    	   var nicknameMessage = document.getElementById("nickname-message");
    	   nicknameMessage.style.display = "none";
    	   document.getElementById("check-mark-wrong-nickname").style.display = "none";
    	   document.getElementById("check-mark-right-nickname").style.display = "none";
    	   if(nickname==""){
    		   nicknameMessage.innerText = "昵称不能为空";
    		   nicknameMessage.style.display = "";
    		   document.getElementById("check-mark-wrong-nickname").style.display = "";
    		   return false;
    	   }
    	   if(nickname.length<1||nickname.length>20){
    		   nicknameMessage.innerText = "昵称不能超过20位";
    		   nicknameMessage.style.display = "";
    		   document.getElementById("check-mark-wrong-nickname").style.display = "";
    		   return false;
    	   }
    	   document.getElementById("check-mark-right-nickname").style.display = "";
    	   return true;
       }
       
       function checkTel(){
    	   var tel = document.getElementById("register-tel").value;
    	   var telMessage = document.getElementById("tel-message");
    	   telMessage.style.display = "none";
    	   var reg = /^[\d]+$/;
    	   document.getElementById("check-mark-wrong-tel").style.display = "none";
    	   document.getElementById("check-mark-right-tel").style.display = "none";
    	   if(tel==""){
    		   telMessage.innerText = "手机号不能为空";
    		   telMessage.style.display = "";
    		   document.getElementById("check-mark-wrong-tel").style.display = "";
    		   return false;
    	   }
    	   if(!reg.test(tel)){
    		   telMessage.innerText = "只能输入数字";
    		   telMessage.style.display = "";
    		   document.getElementById("check-mark-wrong-tel").style.display = "";
    		   return false;
    	   }
    	   if(tel.length!=11){
    		   telMessage.innerText = "请输入11位手机号码";
    		   telMessage.style.display = "";
    		   document.getElementById("check-mark-wrong-tel").style.display = "";
    		   return false;
    	   }
    	   createXMLHttpRequest();
    		xmlHttp.onreadystatechange = function(){
   			var responseContext;
   			if(xmlHttp.readyState == 4){
   				if(xmlHttp.status == 200){
   					responseContext = xmlHttp.responseText;
   					if(responseContext.indexOf("true")!=-1){
   						//alert("恭喜您，注册tel有效");                      //验证成功
   						document.getElementById("check-mark-right-tel").style.display = "";
   					}else{
   						tel = "";
   					    telMessage.innerText="该手机号已注册，请重新输入!";
   						telMessage.style.display="";
   						document.getElementById("check-mark-wrong-tel").style.display = "";
   					}
   				}
   			}
   		} 
   		xmlHttp.open("GET", "${pageContext.request.contextPath}/register/check_tel.html?tel="+tel,true);
   		xmlHttp.send(null);
    	if(telMessage.style.display==""){
    		return false;
    		
    	}else{
    		return true;
    	}
       }
      function checkCarnumber(){
    	  var car_number = document.getElementById("register-carNumber").value;
    	  var car_number_message = document.getElementById("carnumber-message");
    	  car_number_message.style.display = "none";
    	  document.getElementById("check-mark-right-carnumber").style.display="none";
    	  document.getElementById("check-mark-wrong-carnumber").style.display="none";
    	  if(car_number==""){
    		  car_number_message.innerText = "请输入车牌号";
    		  car_number_message.style.display = "";
    		  document.getElementById("check-mark-wrong-carnumber").style.display="";
    		  return false;
    	  }
    	  document.getElementById("check-mark-right-carnumber").style.display="";
    	  return true;
      }
      function checkOBDNumber(){
    	  var obd_number = document.getElementById("register-OBDid").value;
    	  var obd_number_message = document.getElementById("OBDid-message");
    	  obd_number_message.style.display = "none";
    	  document.getElementById("check-mark-right-obdnumber").style.display="none";
    	  document.getElementById("check-mark-wrong-obdnumber").style.display="none";
    	  if(obd_number==""){
    		  obd_number_message.innerText = "请输入OBD序列号";
    		  obd_number_message.style.display = "";
    		  document.getElementById("check-mark-wrong-obdnumber").style.display="";
    		  return false;
    	  }
    	  document.getElementById("check-mark-right-obdnumber").style.display="";
    	  return true;
      }
        function check()
    {
        
		if(checkEmail()&checkPassword()&checkRealName()&checkId()&checkNickname()&checkTel()&checkCarnumber()&checkOBDNumber()){
			//window.location.href = "${pageContext.request.contextPath}/register.html?}";
			document.getElementById("register-password").value = hex_md5(document.getElementById("register-password").value);
			return true;
		}else{
			//window.location.href = "${pageContext.request.contextPath}/register.html?}";
			return false;
		}
    } 
    function chooseCartype(){
    	 document.getElementById("fade").style.display="";
         document.getElementById("car-container").style.display="";
         document.getElementById("user-auth-dialog-container").style.display="none";
    }
    function cartypeChoosingClose(){
    	document.getElementById("fade").style.display="none";
        document.getElementById("car-container").style.display="none";
        document.getElementById("user-auth-dialog-container").style.display="";
        document.getElementById("check-mark-right-cartype").style.display="";
    }
 </script>

 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yy2.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yy_password_complexity.css">

<title>OBD在线支持系统</title>
</head>

<body>

 	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 70px; background: #28a9ff repeat-x center 50%; line-height: 55px; color: #fff; font-family: Verdana, Î¢ï¿½ï¿½ï¿½Åºï¿½, ï¿½ï¿½ï¿½ï¿½">
		 <span style="padding-left: 10px; padding-top:8px; font-size: 40px; float: left;"><img
			src="${pageContext.request.contextPath}/images/blocks.gif" width="20" height="20" align="absmiddle" />
			OBD在线支持系统</span>
		
	</div>
<div id="user-auth-dialog-container" style="position: absolute;  margin: 100px 0px; zoom: 1; top: 25px; display:; " lang="en">

      
      <div class="dialog-body" >                                       
		 <form class="dialog-tab dialog-box-login" action="${pageContext.request.contextPath}/register/check_register.html" method="post" onsubmit="return check()">                
		    <div class="clearfix"></div>  
                               
			<div class="dialog-box">                            
			   <div class="dialog-title">注册</div>    
			   <label name="register" class="input-box-desc hidden-element error-message" style="display: ;color:red;" id="register-message">${register_message}</label>                   
		    </div>    
            
			 <div class="input-box text-input-box">
              <div>
                <input tabindex="1" id="register-email" name="email" type="text" maxlength="60" class="inp enter-key" validation="notempty;email" placeholder="登录邮箱" onblur="checkEmail()"></input>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-email" style="display:none;"></img>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-email" style="display:none;"></img>
              </div>                            
			  <label name="email" class="input-box-desc hidden-element error-message" style="display: none;" id="email-message"></label>           
			  <label class="input-box-desc desc-message">e.g. yourname@gmail.com</label>                        
			</div>                         
			                       
 		   <div class="input-box text-input-box">                           
		      <input tabindex="2" id="register-password" name="password" type="password" maxlength="30" class="inp enter-key" validation="notempty;length:{4,15};password" placeholder="密码"   onfocus="passwordComplexity()">     <!--onfocus="passwordComplexity()"-->                                                             
			  <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-password" style="display:none;"></img>
              <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-password" style="display:none;"></img>
 
              <div id="block-wrap" style="display:none; width:300px;margin-top:10px;">
 			    <div style="padding:0px; font-size:12px; display:inline;">密码强度：</div>
		        <div id="complexity" style="width:180px;display:inline;padding:0px;color:black;">0%</div>
			  </div>

			  
		   </div>   
			
			 <div class="input-box text-input-box"> 
 			  <div>                          
		        <input tabindex="3" id="register-password-again" name="password-again" type="password" maxlength="30" class="inp enter-key" validation="notempty;length:{4,15};password" placeholder="确认密码" onblur="checkPassword()" >                            
			    <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-password-again" style="display:none;"></img>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-password-again" style="display:none;"></img>
              </div>
			  <label name="password-message" class="input-box-desc hidden-element error-message" style="display: none;" id="password-message"></label>                                    
			 
			</div>  
			<div class="input-box text-input-box">       
			  <div>
			    <input tabindex="4" id="register-name" name="realname" type="text" maxlength="60" class="inp enter-key" validation="notempty;" placeholder="真实姓名" onblur="checkRealName()">
			    <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-realname" style="display:none;"></img>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-realname" style="display:none;"></img>
			  </div>                     
			  <label name="name-message" class="input-box-desc hidden-element error-message" style="display: none;" id="name-message"></label>                                 
			</div>   
			
			<div class="input-box text-input-box"> 
			  <div>
			    <input  tabindex="5" id="register-id" name="idnumber" type="text" maxlength=18" class="inp enter-key" validation="notempty;" placeholder="身份证号" onblur="checkId()">
			    <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-id" style="display:none;"></img>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-id" style="display:none;"></img>
			  </div>                           
			  <label name="id-message" class="input-box-desc hidden-element error-message" style="display: none;" id="id-message"></label>                                 
			</div>  
			
			<div class="input-box text-input-box"> 
			  <div>
			    <input tabindex="6" id="register-nickname" name="nickname" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="昵称" onblur="checkNickname()">
			    <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-nickname" style="display:none;"></img>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-nickname" style="display:none;"></img>
			  </div>                           
			  <label name="nickname-message" class="input-box-desc hidden-element error-message" style="display: none;" id="nickname-message"></label>                               
			</div>
			
			<div class="input-box text-input-box">  
			  <div>
			    <input tabindex="7" id="register-tel" name="tel" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="手机号" onblur="checkTel()">
			    <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-tel" style="display:none;"></img>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-tel" style="display:none;"></img>
			  </div>                          
			  <label name="tel-message" class="input-box-desc hidden-element error-message" style="display: none;" id="tel-message"></label>                                  
			</div>
			<div class="input-box text-input-box">  
			  <div>
			    <input tabindex="8" id="register-carNumber" name="carnumber" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="车牌号" onblur="checkCarnumber()">
			    <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-carnumber" style="display:none;"></img>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-carnumber" style="display:none;"></img>
			  </div>                          
			  <label name="carnumber-message" class="input-box-desc hidden-element error-message" style="display: none;" id="carnumber-message"></label>                                  
			</div>
			
			<div class="input-box text-input-box">  
			 <div>
			    <input tabindex="9" id="register-OBDid" name="obdnumber" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="OBD编码" onblur="checkOBDNumber()">
			    <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-obdnumber" style="display:none;"></img>
			     <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-obdnumber" style="display:none;"></img>                     
			  </div>
			  <label name="OBDid-message" class="input-box-desc hidden-element error-message" style="display: none;" id="OBDid-message"></label>                                  
			  <a id="two-dimensional-code" href="#" class="switch-context switch-context-link ">扫描二维码</a>
			</div>
		
			<div class="input-box text-input-box">
			  <div>
			    <input  tabindex="10" id="register-carmodel" name="cartype" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="车型选择" onfocus="chooseCartype()">
			    <img border=0 src="${pageContext.request.contextPath}/images/yy_greencheckmark.png" id="check-mark-right-cartype" style="display:none;"></img>
                <img border=0 src="${pageContext.request.contextPath}/images/yy_redcross.png" id="check-mark-wrong-cartype" style="display:none;"></img>
			   </div>                         
			  <label name="carmodel-message" class="input-box-desc hidden-element error-message" style="display: none;" id="carmodel-message"></label>                                  
			</div>         
			<div class="action-box">                            
			   <input type="submit" href="#" tabindex="6" id="register-action-go" class="dialog-button action-login" value="注册" onkeydown="if(event.keyCode==13){event.keyCode=9}">                    
		    </div>
		    <div class="dialog-box">                                                                                      
				 <a id="two-dimensional-code" href="${pageContext.request.contextPath}/login.html" class="switch-context switch-context-link left"> << 我要登录</a>                      
			</div>               
		</form>                          
      </div>
      
      
      
  </div>
  
  <div id="car-container" style="display:none;" >
  
    
    
    <div class="container-title">车型选择</div> 
    <a href="javascript:void(0);" class="container-close"  onclick="cartypeChoosingClose()"></a>
   <div class="innerNav_div" >
                    <ul class="innerNav innerNav_c clearfix" id="eur">
                       <li class="nav01"><a target="_self" href="#a">欧洲品牌：</a></li>
                       <li><a target="_self" href="#a_1" >世爵</a></li>
                       <li><a target="_self" href="#a_2">保时捷Porsche</a></li>
                       <li><a target="_self" href="#a_3">兰博基尼</a></li>
                       <li><a target="_self" href="#a_4">大众</a></li>
                       <li><a target="_self" href="#a_5">奔驰</a></li>
                    </ul>
                    <ul class="innerNav innerNav_c clearfix" id="usa">
                       <li class="nav01"><a target="_self" href="#b">美国品牌：</a></li>
                       <li><a target="_self" href="#b_1" >凯迪拉克</a></li>
                    </ul>                   
                     <ul class="innerNav innerNav_c clearfix" id="usa">
                       <li class="nav01"><a target="_self" href="#b">美国品牌：</a></li>
                       <li><a target="_self" href="#b_1">凯迪拉克</a></li>
                    </ul>
                    <ul class="innerNav innerNav_c clearfix" id="japan">
                       <li class="nav01"><a target="_self" href="#c">日韩品牌：</a></li>
                       <li><a target="_self" href="#c_1">丰田</a></li>
                       
                    </ul>
                    <ul class="innerNav innerNav_c clearfix" id="china">
                       <li class="nav01"><a target="_self" href="#d">中国品牌：</a></li>
                       <li><a target="_self" href="#c_1">一汽</a></li>
                    </ul>  
                          
     </div>
     
     <div id="items">
        <div id="item1">
             <h1><a name="a" shape=""></a>欧洲品牌</h1>
             <div class="carlist" id="item1Tag01">
               <dl class="clearfix">
                 <dt name="a_1" id="a_1"><a href="">世爵</a></dt>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">世爵C12</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">世爵C8</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">世爵D12</a></dd>
               </dl>
               
                <dl class="clearfix">
                 <dt name="a_2" id="a_2"><a href="">保时捷Porsche</a></dt>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷911</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷911 GT2</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷911 Targa</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷918 Spyder</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷Boxster</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷Boxster S</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷Carrera GT</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷Cayenne</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷Cayenne S</a></dd>
                 <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">保时捷Panamera</a></dd>
               </dl>
             
                <dl class="clearfix">
                  <dt name="a_3" id="a_3"><a href="">兰博基尼</a></dt>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">兰博基尼Countach</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">兰博基尼Diablo</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">兰博基尼Gallardo</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">兰博基尼Miura</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">兰博基尼Reventon</a></dd>
                </dl>
             
             
             
                <dl class="clearfix">
                   <dt name="a_4" id="a_4"><a href="">大众</a></dt>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众CC</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Cross Polo</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众E-Up</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众EOS</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Lupo</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Microbus</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Multivan</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众NCC</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Polo</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Polo Sporty</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Polo劲取</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Polo劲情</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众R36</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众T5</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众Up!Lite</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众W12</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众夏朗</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众宝来</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众尚酷Scirocco</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众帕萨特</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众志俊</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众捷达</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众朗逸</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众桑塔纳</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众甲壳虫</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众辉腾</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众迈腾</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众途安</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众途欢Tiguan</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众途观</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众途锐</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众速腾</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众领驭</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众高尔夫</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">大众高尔夫GTI</a></dd>
                 </dl>
                
                 <dl class="clearfix">
                    <dt name="a_5" id="a_5"><a href="">奔驰</a></dt>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰AMG</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰A级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰BLK</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰B级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰CLK</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰CLS</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰CL级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰C级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰C级旅行轿车</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰E级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰E级coupe</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰E级敞篷跑车</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰F800概念车</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰GL</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰GLK</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰G级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰MB100</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰ML级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰R级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰S400混合动力</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰SL</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰SLK</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰SLR</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰S级</a></dd>
                    <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">奔驰Vaneo</a></dd>
                 </dl>
             
             
             </div>
          </div>  <!-- end of item1 -->
         
          <div id="item2">
             <h1><a name="b" shape=""></a>美国品牌</h1>
             <div class="carlist" id="item2Tag01">
               <dl class="clearfix"><dt name="b_1" id="b_1"><a href="">凯迪拉克</a></dt>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克CTS</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克CTS-V</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克Converj</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克DTS</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克Escalade</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克SLS</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克SRX</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克STS</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克XLR</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克XTS白金版</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克依沃克</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克加长版</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克帝威</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克皮卡</a></dd>
               <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">凯迪拉克维尚</a></dd></dl>
               </dl>
               
              
             </div>
          </div>  <!-- end of item2 -->
          
          <div id="item3">
             <h1><a name="c" shape=""></a>日韩品牌</h1>
             <div class="carlist" id="item3Tag01">
               <dl class="clearfix">
                  <dt name="c_1" id="c_1"><a href="">丰田</a></dt>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Alphard</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Avalon</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Aygo</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Camry</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Celica</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Century</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Corolla</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田FCHV</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田FT Cruiser</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田FT-86</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田FT-CH</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田FT-EV</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田FT-EV2</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田FT-HS</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Funcargo</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Funcoupe</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Funtime</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田IQ</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Previa</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田RAV4</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Sienna</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Supra</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田VITZ</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Venza</a></dd>
                  <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">丰田Verso</a></dd>
               </dl>
             </div>
          </div>  <!-- end of item1 -->
          
          <div id="item4">
             <h1><a name="d" shape=""></a>中国品牌</h1>
             <div class="carlist" id="item4Tag01">
               <dl class="clearfix">
                   <dt name="d_1" id="d_1"><a href="">一汽</a></dt>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽E-COO</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽E-wing</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽佳宝</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽夏利A+</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽夏利N3</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽夏利N5</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽奔腾B30</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽奔腾B50</a></dd>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽奔腾B70</a></dd>
               </dl>
             
             
             
             </div>
          </div>  <!-- end of item1 -->
         
     
     
     </div><!-- end of items -->
     
     
 

                   
     
     
  </div>
  <div id="fade" class="black_overlay" style="display:none ;"></div> 
</body>
</html>
