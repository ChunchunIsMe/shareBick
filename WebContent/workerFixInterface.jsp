<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="./css/Interface.css">
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=QnI24LzauCKdedQGGZ6OQQkCx4L45ROc"></script>
	<title>worker</title>
</head>
<body>
	<% 
		double [][]z=(double[][])session.getAttribute("fixplace");
	%>
	<div id="allmap"></div>
	<nav>
        <button type="button" class="leftButton"></button>
        <div class="hidden">
            <button type="button" onclick="window.location.href='workInterface.jsp'">返回</button>
            <label class="file">
                <input type="file" id="file" onchange="upFile()"/>自行车还原
            </label>
        </div>
    </nav>
    <button type="button" class="bottomButton" onclick="ajax()" id="submit">增加自行车</button>
    <div id="alert" style="position: fixed;bottom: 50px;text-align: center;width: 100%;color: #418cee"></div>
</body>

<script type="text/javascript">

	var myfile = document.getElementById('file');
	var filename="";
	function upFile(){  
	    //获取文件上传文件的文件名和扩展名  
	    if(myfile.files[0] == undefined){  
	        alert('未上传文件！');  
	    }else{  
	        //获取上传文件的文件名  
	        filename=myfile.files[0].name;
	        //alert(filename);
	        ajax1(filename)
	        
	    }
	}
	
	function ajax1(filename){
		var url="ReadQRCode";
		var params="filename="+filename;
		sendRequest(url,params,'GET',showresult);
		
	}
	
    var points=new Array();
    for(var i=0;i<<%=z.length%>;i++){
    	points[i]=new Array(); 
	    for(var j=0;j<2;j++){
	    	points[i][j]=0.00000;
	    }
    };
    <%   for(int i=0;i <z.length;i++){   %> 
    		points[<%=i%>][0]=<%=z[i][0]%>; 
    		points[<%=i%>][1]=<%=z[i][1]%>; 
    <% }%>
    
    var AJAXurl="CreateCode";
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
		var params="";
		sendRequest(url,params,'GET',showresult);
		
	}
	function showresult(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				document.getElementById("alert").innerHTML=xmlHttp.responseText;
			}
		}
	}
	submit.addEventListener("click", ajax, false);
</script>
<script type="text/javascript" src="./js/workInterface.js"></script>
</html>