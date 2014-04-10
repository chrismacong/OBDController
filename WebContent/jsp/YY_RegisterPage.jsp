<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

 <script type="text/javascript">
    function cartypeChoosingClose(){
       
    	document.getElementById("fade").style.display="none";
        document.getElementById("car-container").style.display="none";
        document.getElementById("user-auth-dialog-container").style.display="";
        
    }
      function checkEmail()
    {
	    var email = document.getElementById("login-input-email");
		var email_message = document.getElementById("email-message");
		email_message.style.display="none";
		if(email.value==""){
			email_message.innerText="邮箱地址不能为空";
			email_message.style.display="";
			return false;
		}
		if(email.value!=""){
		//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		var reg=/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
		if(!reg.test(email.value)){
        //x.className = 'error-message'; 
        email_message.innerText="请输入正确邮箱地址!";
		email_message.style.display="";
		return false;
		}
		}
		return true;
    }
      
       function checkPassword(){
        	var userPass = document.getElementById("register-password").value; 
          	var userRePass = document.getElementById("register-password-again").value; 
          	var passwordMessage = document.getElementById("password-message");
          	passwordMessage.style.display="none";
          	if(userPass==""||userRePass==""){
          		passwordMessage.innerText="密码不能为空";
          		passwordMessage.style.display="";
          		return false;
          	}
          	if(!(userPass==userRePass)){
          		passwordMessage.innerText="两次密码输入不同，请重新输入";
          		passwordMessage.style.display="";
          		document.getElementById("register-password").innerText="";
          		document.getElementById("register-password-again").innerText="";
          		return false;
          	}
          	return true;
      }
      
       function checkRealName(){
    	   var filter=/[A-Za-z0-9\u4e00-\u9fa5]/;
    	   var name = document.getElementById("register-name");
    	   var nameMessage = document.getElementById("name-message");
    	   nameMessage.style.display = "none";
    	   if(name.value==""){
    		   nameMessage.innerText="真实姓名不能为空";
     		  nameMessage.style.display = "";
     		 return false;
    	   }
    	   if(!filter.test(name.value)){
    		  nameMessage.innerText="请输入真实姓名，不能有标点符号";
    		  nameMessage.style.display = "";
    		  return false;
    	  }	    
    	   return true;
      } 
       
       function checkId(){
    	   var id = document.getElementById("register-id").value;
    	   var idMessage = document.getElementById("id-message");
    	   idMessage.style.display = "none";
    	   var reg = /^[\d]+$/;
    	   if(id==""){
    		   idMessage.innerText = "身份证号不能为空";
    		   idMessage.style.display = "";
    		   return false;
    	   }
    	   if(!(id.length==18)){
    		   idMessage.innerText = "请输入18位身份证号";
    		   idMessage.style.display = "";
    		   return false;
    	   }else{
    		   if(!reg.test(id)){     //只能输入数字
    			   idMessage.innerText = "格式不对，请重新输入";
        		   idMessage.style.display = "";
        		   return false;
    		   }
    	   }
    	   return true;
       }
       
       function checkNickname(){
    	   var nickname = document.getElementById("register-nickname").value;
    	   var nicknameMessage = document.getElementById("nickname-message");
    	   nicknameMessage.style.display = "none";
    	   if(nickname==""){
    		   nicknameMessage.innerText = "昵称不能为空";
    		   nicknameMessage.style.display = "";
    		   return false;
    	   }
    	   if(nickname.length<4||nickname.length>20){
    		   nicknameMessage.innerText = "请输入4到20位昵称";
    		   nicknameMessage.style.display = "";
    		   return false;
    	   }
    	   return true;
       }
       
       function checkTel(){
    	   var tel = document.getElementById("register-tel").value;
    	   var telMessage = document.getElementById("tel-message");
    	   telMessage.style.display = "none";
    	   var reg = /^[\d]+$/;
    	   if(tel==""){
    		   telMessage.innerText = "联系电话不能为空";
    		   telMessage.style.display = "";
    		   return false;
    	   }
    	   if(!reg.test(tel)){
    		   telMessage.innerText = "只能输入数字";
    		   telMessage.style.display = "";
    		   return false;
    	   }
    	   return true;
       }
      
        function check()
    {
        /*  var email = document.getElementById("login-input-email");
		var email_message = document.getElementById("email-message");
		email_message.style.display="none";
		if(email.value==""){
	
        //x.className = 'error-message'; 
        email_message.innerText="请输入邮箱地址";
		email_message.style.display="";
		
		} */
		if(checkEmail()&checkPassword()&checkRealName()&checkId()&checkNickname()&checkTel()){
			//window.location.href = "${pageContext.request.contextPath}/register.html?}";
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
 </script>

 
<link rel="stylesheet" type="text/css" href="../css/yy.css">


<title>OBD在线支持系统</title>
</head>

<body>

	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 70px; background: url(images/layout-browser-hd-bg.gif) #28a9ff repeat-x center 50%; line-height: 55px; color: #fff; font-family: Verdana, Î¢ï¿½ï¿½ï¿½Åºï¿½, ï¿½ï¿½ï¿½ï¿½">
		 <span style="padding-left: 8px; padding-top:8px; font-size: 40px; float: left;"><img
			src="../images/blocks.gif" width="20" height="20" align="absmiddle" />
			OBD在线支持系统</span>
		
	</div>
<div id="user-auth-dialog-container" style="position: absolute;  margin: 100px 0px; zoom: 1; top: 25px; display:; " lang="en">
     <div class="dialog-body">                                       
		 <form class="dialog-tab dialog-box-login" action="${pageContext.request.contextPath}/register.html" method="post" onsubmit="return check()">                
		    <div class="clearfix"></div>  
                               
			<div class="dialog-box">                            
			   <div class="dialog-title">注册</div>                       
		    </div>      
                             
            <div class="input-box text-input-box">                            
			  <input autofocus="" tabindex="1" id="login-input-email" name="email" type="text" maxlength="60" class="inp enter-key" validation="notempty;email" placeholder="登录邮箱" onchange="checkEmail()"></input>
			  <label name="email" class="input-box-desc hidden-element error-message" style="display: none;" id="email-message"></label>           
			  <label class="input-box-desc desc-message">e.g. yourname@gmail.com</label>                        
			</div>                         
			                       
 		   <div class="input-box text-input-box">                           
		      <input tabindex="2" id="register-password" name="password" type="password" maxlength="15" class="inp enter-key" validation="notempty;length:{4,15};password" placeholder="密码">                                                                  
			</div>   
			
 			<div class="input-box text-input-box">                           
		      <input tabindex="2" id="register-password-again" name="password-again" type="password" maxlength="15" class="inp enter-key" validation="notempty;length:{4,15};password" placeholder="再次输入密码" onchange="checkPassword()">                            
			  <label name="password-message" class="input-box-desc hidden-element error-message" style="display: none;" id="password-message"></label>                                    
			</div>  
			
			<div class="input-box text-input-box">                            
			  <input autofocus="" tabindex="1" id="register-name" name="realname" type="text" maxlength="60" class="inp enter-key" validation="notempty;" placeholder="真实姓名" onchange="checkRealName()">
			  <label name="name-message" class="input-box-desc hidden-element error-message" style="display: none;" id="name-message"></label>                                 
			</div>   
			
			<div class="input-box text-input-box">                            
			  <input autofocus="" tabindex="1" id="register-id" name="idnumber" type="text" maxlength=18" class="inp enter-key" validation="notempty;" placeholder="身份证号" onchange="checkId()">
			  <label name="id-message" class="input-box-desc hidden-element error-message" style="display: none;" id="id-message"></label>                                 
			</div>  
			
			<div class="input-box text-input-box">                            
			  <input autofocus="" tabindex="1" id="register-nickname" name="nickname" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="昵称" onchange="checkNickname()">
			  <label name="nickname-message" class="input-box-desc hidden-element error-message" style="display: none;" id="nickname-message"></label> 
			  <label class="input-box-desc desc-message">4到20个英文字符</label>                                 
			</div>
			
			<div class="input-box text-input-box">                            
			  <input autofocus="" tabindex="1" id="register-tel" name="tel" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="联系电话" onchange="checkTel()">
			  <label name="tel-message" class="input-box-desc hidden-element error-message" style="display: none;" id="tel-message"></label>                                  
			</div>
			
			<div class="input-box text-input-box">                            
			  <input autofocus="" tabindex="1" id="register-carmodel" name="cartype" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="车型选择" onclick="chooseCartype()">
			  <label name="carmodel-message" class="input-box-desc hidden-element error-message" style="display: none;" id="carmodel-message"></label>                                  
			</div>
			
			<div class="input-box text-input-box">                            
			  <input autofocus="" tabindex="1" id="register-OBDid" name="obdnumber" type="text" maxlength=20" class="inp enter-key" validation="notempty;" placeholder="OBD编码" onchange="">
			  <label name="OBDid-message" class="input-box-desc hidden-element error-message" style="display: none;" id="OBDid-message"></label>                                  
			  <a id="two-dimensional-code" tabindex="9" href="#" class="switch-context switch-context-link ">扫描二维码</a>
			</div>
			
			         
                      
<!--			<div class="dialog-box">                            
			   <span class="dialog-checkbox">                              
			     <input tabindex="5" id="login-input-remember" name="rememberMe" type="checkbox" checked="checked">                
				 <label for="login-input-remember">记住我！</label>                               
				 <a id="login-switch-context-forgot-password" tabindex="9" href="#" class="switch-context switch-context-link right" rev="forgot-password">忘记密码？</a>
			   </span>                        
			</div>                         
			<div class="clearfix"></div>          -->              
			<div class="action-box">                            
			   <input type="submit" href="#" tabindex="6" id="register-action-go" class="dialog-button action-login" value="注册">                    
		    </div>
<!--              
            <div class="dialog-box">                          
			   <span class="dialog-checkbox">                                                             
				  <label for="new-user">还不是我们的用户？</label><a id="login-new-user" tabindex="9" href="YYRegister.jsp" class="switch-context switch-context-link " rev="new-user">立即注册</a>
			   </span>                        
			</div>  -->                   
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
                  <dd><a href="#">兰博基尼Countach</a></dd>
                  <dd><a href="#">兰博基尼Diablo</a></dd>
                  <dd><a href="#">兰博基尼Gallardo</a></dd>
                  <dd><a href="#">兰博基尼Miura</a></dd>
                  <dd><a href="#">兰博基尼Murcielago</a></dd>
                  <dd><a href="#">兰博基尼Reventon</a></dd>
                </dl>
             
             
             
                <dl class="clearfix">
                   <dt name="a_4" id="a_4"><a href="">大众</a></dt><dd><a href="">大众CC</a></dd><dd><a href="http://image.baidu.">大众Cross Polo</a></dd><dd><a href="http://image.baidu.">大众E-Up</a></dd><dd><a href="">大众EOS</a></dd><dd><a href="">大众Lupo</a></dd>
                   <dd><a href="#">大众Microbus</a></dd>
                   <dd><a href="#">大众Multivan</a></dd>
                   <dd><a href="#">大众NCC</a></dd>
                   <dd><a href="#">大众Polo</a></dd>
                   <dd><a href="#">大众Polo Sporty</a></dd>
                   <dd><a href="#">大众Polo劲取</a></dd>
                   <dd><a href="#">大众Polo劲情</a></dd>
                   <dd><a href="#">大众R36</a></dd>
                   <dd><a href="#">大众T5</a></dd>
                   <dd><a href="#">大众Up!Lite</a></dd>
                   <dd><a href="#">大众W12</a></dd>
                   <dd><a href="#">大众夏朗</a></dd>
                   <dd><a href="#">大众宝来</a></dd>
                   <dd><a href="#">大众尚酷Scirocco</a></dd>
                   <dd><a href="#">大众帕萨特</a></dd>
                   <dd><a href="#">大众志俊</a></dd>
                   <dd><a href="#">大众捷达</a></dd>
                   <dd><a href="#">大众朗逸</a></dd>
                   <dd><a href="#">大众桑塔纳</a></dd>
                   <dd><a href="#">大众甲壳虫</a></dd>
                   <dd><a href="#">大众辉腾</a></dd>
                   <dd><a href="#">大众迈腾</a></dd>
                   <dd><a href="#">大众途安</a></dd>
                   <dd><a href="#">大众途欢Tiguan</a></dd>
                   <dd><a href="#">大众途观</a></dd>
                   <dd><a href="#">大众途锐</a></dd>
                   <dd><a href="#">大众速腾</a></dd>
                   <dd><a href="#">大众领驭</a></dd>
                   <dd><a href="#">大众高尔夫</a></dd>
                   <dd><a href="#">大众高尔夫GTI</a></dd>
                 </dl>
                
                 <dl class="clearfix">
                    <dt name="a_5" id="a_5"><a href="">奔驰</a></dt>
                    <dd><a href="#">奔驰AMG</a></dd>
                    <dd><a href="#">奔驰A级</a></dd>
                    <dd><a href="#">奔驰BLK</a></dd>
                    <dd><a href="#">奔驰B级</a></dd>
                    <dd><a href="#">奔驰CLK</a></dd>
                    <dd><a href="#">奔驰CLS</a></dd>
                    <dd><a href="#">奔驰CL级</a></dd>
                    <dd><a href="#">奔驰C级</a></dd>
                    <dd><a href="#">奔驰C级旅行轿车</a></dd>
                    <dd><a href="#">奔驰E级</a></dd>
                    <dd><a href="#">奔驰E级coupe</a></dd>
                    <dd><a href="#">奔驰E级敞篷跑车</a></dd>
                    <dd><a href="#">奔驰F-CELLRoadster</a></dd>
                    <dd><a href="#">奔驰F800概念车</a></dd>
                    <dd><a href="#">奔驰GL</a></dd>
                    <dd><a href="#">奔驰GLK</a></dd>
                    <dd><a href="#">奔驰G级</a></dd>
                    <dd><a href="#">奔驰MB100</a></dd>
                    <dd><a href="#">奔驰ML级</a></dd>
                    <dd><a href="#">奔驰R级</a></dd>
                    <dd><a href="#">奔驰S400混合动力</a></dd>
                    <dd><a href="#">奔驰SL</a></dd>
                    <dd><a href="#">奔驰SLK</a></dd>
                    <dd><a href="#">奔驰SLR</a></dd>
                    <dd><a href="#">奔驰S级</a></dd>
                    <dd><a href="#">奔驰Vaneo</a></dd>
                 </dl>
             
             
             </div>
          </div>  <!-- end of item1 -->
         
          <div id="item2">
             <h1><a name="b" shape=""></a>美国品牌</h1>
             <div class="carlist" id="item2Tag01">
               <dl class="clearfix"><dt name="b_1" id="b_1"><a href="">凯迪拉克</a></dt>
               <dd><a href="#">凯迪拉克CTS</a></dd><dd><a href="">凯迪拉克CTS-V</a></dd>
               <dd><a href="#">凯迪拉克Converj</a></dd>
               <dd><a href="#">凯迪拉克DTS</a></dd>
               <dd><a href="#">凯迪拉克Escalade</a></dd>
               <dd><a href="#">凯迪拉克SLS</a></dd>
               <dd><a href="#">凯迪拉克SRX</a></dd>
               <dd><a href="#">凯迪拉克STS</a></dd>
               <dd><a href="#">凯迪拉克XLR</a></dd>
               <dd><a href="#">凯迪拉克XTS白金版</a></dd>
               <dd><a href="#">凯迪拉克依沃克</a></dd>
               <dd><a href="#">凯迪拉克加长版</a></dd>
               <dd><a href="#">凯迪拉克帝威</a></dd>
               <dd><a href="#">凯迪拉克皮卡</a></dd>
               <dd><a href="#">凯迪拉克维尚</a></dd></dl>
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
                  <dd><a href="#">丰田Celica</a></dd>
                  <dd><a href="#">丰田Century</a></dd>
                  <dd><a href="#">丰田Corolla</a></dd>
                  <dd><a href="#">丰田FCHV</a></dd>
                  <dd><a href="#">丰田FT Cruiser</a></dd>
                  <dd><a href="#">丰田FT-86</a></dd>
                  <dd><a href="#">丰田FT-CH</a></dd>
                  <dd><a href="#">丰田FT-EV</a></dd>
                  <dd><a href="#">丰田FT-EV2</a></dd>
                  <dd><a href="#">丰田FT-HS</a></dd>
                  <dd><a href="#">丰田Funcargo</a></dd>
                  <dd><a href="#">丰田Funcoupe</a></dd>
                  <dd><a href="#">丰田Funtime</a></dd>
                  <dd><a href="#">丰田IQ</a></dd>
                  <dd><a href="#">丰田Previa</a></dd>
                  <dd><a href="#">丰田RAV4</a></dd>
                  <dd><a href="#">丰田Sienna</a></dd>
                  <dd><a href="#">丰田Supra</a></dd>
                  <dd><a href="#">丰田VITZ</a></dd>
                  <dd><a href="#">丰田Venza</a></dd>
                  <dd><a href="#">丰田Verso</a></dd>
               </dl>
             </div>
          </div>  <!-- end of item1 -->
          
          <div id="item4">
             <h1><a name="d" shape=""></a>中国品牌</h1>
             <div class="carlist" id="item4Tag01">
               <dl class="clearfix">
                   <dt name="d_1" id="d_1"><a href="">一汽</a></dt>
                   <dd><a href="#" onMouseOver="this.style.fontSize= '18px'" onMouseOut="this.style.fontSize='16px'"  onclick="cartypeChoosingClose();document.getElementById('register-carmodel').value=this.innerHTML;">一汽E-COO</a></dd>
                   <dd><a href="#">一汽E-wing</a></dd>
                   <dd><a href="#">一汽佳宝</a></dd>
                   <dd><a href="#">一汽夏利A+</a></dd>
                   <dd><a href="#">一汽夏利N3</a></dd>
                   <dd><a href="#">一汽夏利N5</a></dd>
                   <dd><a href="#">一汽奔腾B30</a></dd>
                   <dd><a href="#">一汽奔腾B50</a></dd>
                   <dd><a href="#">一汽奔腾B70</a></dd>
               </dl>
             
             
             
             </div>
          </div>  <!-- end of item1 -->
         
     
     
     </div><!-- end of items -->
     
     
 

                   
     
     
  </div>
  <div id="fade" class="black_overlay" style="display:none ;"></div> 
</body>
</html>
