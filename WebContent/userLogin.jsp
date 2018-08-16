<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<title>Document</title>
	 <link rel="stylesheet" type="text/css" href="./css/Login.css">
</head>
<body>
	<div>
		<form action="" class="login">
			<h2>用户登录</h2>
			<input type="text" name="phone" placeholder="请输入手机号" id="ph">
			<input type="password" name="password" placeholder="请输入密码" id="pwd">
			<button type="button" id="submit">登录</button>
			<div id="alert"></div>
		</form>
	</div>
</body>
<script type="text/javascript">
	var AJAXurl="UserLogin";
	var url="userInterface.jsp";
	var ph = document.getElementById("ph");
	var pwd = document.getElementById("pwd");
	var submit = document.getElementById("submit");
	function createXHR(){
		try{
			xmlHttp=new XMLHttpRequest();
		}catch(e1){
			var msxmlhttp=new Array("Msxml2.XMLHTTP.6.0","Msxml2.XMLHTTP.5.0",
					  "Msxml2.XMLHTTP.4.0","Msxml2.XMLHTTP.3.0",
					  "Msxml2.XMLHTTP","Microsoft.XMLHTTP");
			for(var i=0;i<msxmlhttp.length;i++){
				try{
					xmlHttp=new ActiveXObject(msxmlhttp[i]);
					if(xmlHttp!=null){
						break;
					}
				}catch(e1){
				}
			}
		}
		if(xmlHttp==null){
			alert("不能创建AJAX对象！");
		}
	}
	function sendRequest(url,params,method,handler){
		createXHR();
		if(!xmlHttp)return false;
		xmlHttp.onreadystatechange=handler;
		if(method=="GET"){
			xmlHttp.open(method,url+'?'+params,true);
			xmlHttp.send(null);
		}
		if(method=="POST"){
			xmlHttp.open(method,url,true);
			xmlHttp.setRequestHeader("Content-type","appliciation/x-www-form-urlencoded");
			xmlHttp.send(params);
		}
	}
	function ajax(){
		var url=AJAXurl;
		var params="phone="+ph.value+"&password="+pwd.value;
		sendRequest(url,params,'GET',showresult);
		
	}
	function showresult(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				if(xmlHttp.responseText=="登录成功"){
					window.location.href=url;
				}else{
					document.getElementById("alert").innerHTML=xmlHttp.responseText;
				}
			}
		}
	}
	submit.addEventListener("click", ajax, false);
</script>
</html>