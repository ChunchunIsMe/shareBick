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
			if (bno==-1){
				response.sendRedirect("userInterface.jsp");
			}
    	%>
    	<label class="bottomButton" onclick="ajax()">
	    	还车
	    </label>
	    <div id="alert" style="position: fixed;bottom: 50px;text-align: center;width: 100%;color: #418cee"></div>
</body>
<script type="text/javascript">
	var x,y;
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
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(115.944819,28.689535);
	
	map.centerAndZoom(point,18);
	var geolocation = new BMap.Geolocation();
	// 开启SDK辅助定位
	geolocation.enableSDKLocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			x=r.point.lng;
			y=r.point.lat;
			
			//alert('您的位置：'+x+','+y);
		}
		else {
			alert('failed'+this.getStatus());
		}    
	});
	
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("南昌");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	// 向地图添加标注
	for( var i = 0;i < points.length; i++){
	    var myIcon = new BMap.Icon("./img/bike.png", new BMap.Size(103, 117), {
	        // // 指定定位位置
	        // offset: new BMap.Size(20, 25+i*10),
	        // // 当需要从一幅较大的图片中截取某部分作为标注图标时，需要指定大图的偏移位置   
	        // imageOffset: new BMap.Size(0, 0 - i * 10) // 设置图片偏移
	    });
	    var point = new BMap.Point(points[i][0],points[i][1]);
	    // 创建标注对象并添加到地图 
	    var marker = new BMap.Marker(point,{icon: myIcon});
	    map.addOverlay(marker);
	}
	
	
	
	
	
    var AJAXurl="ReturnBike";
    var AJAXurl1="userFix";
	var url="userInterface.jsp";
    var myfile1 = document.getElementById('fix');
	
	var filename1="";

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
	var p = new Promise(function aa(resove){
        var geolocation = new BMap.Geolocation();
        // 开启SDK辅助定位
        geolocation.enableSDKLocation();
        geolocation.getCurrentPosition(function(r){
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                var mk = new BMap.Marker(r.point);
                map.addOverlay(mk);
                map.panTo(r.point);
                var x=r.point.lng;
               var y=r.point.lat;
                place=x+","+y
                resove(place)
            }
            else {
                alert('failed'+this.getStatus());
            }    
        });   
    });
	function ajax(){
		
		p.then(function(place){
			
			var a=place;
			//alert(a);
			var url=AJAXurl;
			var params="phone="+<%=session.getAttribute("phone")%>+"&bno="+<%=session.getAttribute("bno")%>+"&place="+a;
			sendRequest(url,params,'GET',showresult);
	  // 处理flag
	    })
		
		
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
				if(xmlHttp.responseText=="还车成功!"){
					window.location.href=url;
				}else{
					document.getElementById("alert").innerHTML=xmlHttp.responseText;
				}
			}
		}
	}
</script>

</html>