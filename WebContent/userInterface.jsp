<%@ page language="java" import="dao.userDao" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="./css/UserInterface.css">
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=QnI24LzauCKdedQGGZ6OQQkCx4L45ROc"></script>
	<title>地图展示</title>
</head>
<body>
	<% 
		double [][]a=(double[][])session.getAttribute("place");
	%>
	<div id="allmap"></div>
   	 <label class="fix">
            <input type="file" id="fix" onchange="upFile1()" class="file" />
    </label>
    <nav>
    	<button class="user"></button>
    	<header>
    		<button style="background-image: url(./img/10.png);width: 105px;height: 60px;" onclick="ajax2(10)"></button>
    		<button style="background-image: url(./img/20.png);width: 105px;height: 60px;" onclick="ajax2(20)"></button>
    		<button style="background-image: url(./img/30.png);width: 105px;height: 60px;" onclick="ajax2(30)"></button>
    		<button style="background-image: url(./img/50.png);width: 105px;height: 60px;" onclick="ajax2(50)"></button>
    		<button style="background-image: url(./img/100.png);width: 105px;height: 60px;" onclick="ajax2(100)"></button>
    		<button style="background-image: url(./img/200.png);width: 105px;height: 60px;" onclick="ajax2(200)"></button>
    		<button style="background-image: url(./img/300.png);width: 105px;height: 60px;" onclick="ajax2(300)"></button>
    		<button style="background-image: url(./img/500.png);width: 105px;height: 60px;" onclick="ajax2(500)"></button>
    	</header>
    </nav>
    	<%
	    	String phone=(String)session.getAttribute("phone");
			userDao userdao = new userDao();
			int bno=userdao.selBno(phone);
    		if (bno!=-1){
    			response.sendRedirect("userLendInterface.jsp");
    		}
    	%>
    	<label class="bottomButton">
	    	<input type="file" id="file" onchange="upFile()" class="file" />
	    	扫码租车
	    </label>
	    <div id="alert" style="position: fixed;bottom: 50px;text-align: center;width: 100%;color: #418cee"></div>
</body>

<script type="text/javascript">
    var points=new Array();
    for(var i=0;i<<%=a.length%>;i++){
    	points[i]=new Array(); 
	    for(var j=0;j<2;j++){
	    	points[i][j]=0.00000;
	    }
    };
    <%   for(int i=0;i <a.length;i++){   %> 
    		points[<%=i%>][0]=<%=a[i][0]%>; 
    		points[<%=i%>][1]=<%=a[i][1]%>; 
    <% }%>
    var AJAXurl="userLendInterface";
    var AJAXurl1="userFix";
	var url="userLendInterface.jsp";
    var myfile = document.getElementById('file');
    var myfile1 = document.getElementById('fix');
	var filename="";
	var filename1="";
	function upFile(){  
	    //获取文件上传文件的文件名和扩展名  
	    if(myfile.files[0] == undefined){  
	        alert('未上传文件！');  
	    }else{  
	        //获取上传文件的文件名  
	        filename=myfile.files[0].name;
	        //alert(filename);
	        ajax(filename)
	        
	    }
	}
	function upFile1(){  
	    //获取文件上传文件的文件名和扩展名  
	    if(myfile1.files[0] == undefined){  
	        alert('未上传文件！');  
	    }else{  
	        //获取上传文件的文件名  
	        filename1=myfile1.files[0].name;
	        //alert(filename);
	        ajax1(filename1)
	        
	    }
	}
    
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
	function ajax(filename){
		var url=AJAXurl;
		var params="phone="+<%=session.getAttribute("phone")%>+"&filename="+filename;
		sendRequest(url,params,'GET',showresult);
		
	}
	function ajax1(filename){
		var url=AJAXurl1;
		var params="filename="+filename1;
		sendRequest(url,params,'GET',showresult);
		
	}
	function ajax2(money){
		var url="userAddMoney";
		var params="phone="+<%=session.getAttribute("phone")%>+"&money="+money;
		sendRequest(url,params,'GET',showresult);
		
	}
	function showresult(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				if(xmlHttp.responseText=="租车成功!"){
					window.location.href=url;
				}else{
					document.getElementById("alert").innerHTML=xmlHttp.responseText;
				}
			}
		}
	}
</script>
<script type="text/javascript" src="./js/workInterface.js"></script>
</html>