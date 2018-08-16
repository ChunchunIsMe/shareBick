var map = new BMap.Map("allmap");
var point = new BMap.Point(115.944819,28.689535);
var x=0.000;
var y=0.000;
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
		//alert('您的位置：'+r.point.lng+','+r.point.lat);
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