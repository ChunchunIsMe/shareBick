# shareBick
使用javaweb模仿共享单车做的小项目

使用的技术：java(后端)、mysql(数据库)、html+css+js(前端)、qrcode(二维码)、百度地图(地图和自行车位置的显示

实现思路是

1.用户

用户存在四个功能，分别是借车、还车、自行车报修、充值

由于是电脑操作，所以扫码是使用的上传二维码的方式

扫码之后则显示成功租借这辆单车，页面跳转到租车界面

因为没有单车实物，所以还车是在页面上进行操作的

点击还车则自行车归还，并将用户的地理位置记录在自行车表中

没用被租借的自行车则会显示在用户的地图之上

单车报修是用户进行扫码，单车报修次数则加一

如果单车报修次数超过三次则单车不会显示在用户的界面之上

2.工作人员

工作人员存在三个功能分别是增加自行车、显示损坏车和维修自行车

增加自行车则会添加一个二维码到文件夹中并以自行车的编号命名

显示损坏车则是只将报修次数超过三次的自行车显示在地图上

维修自行车是自行车维修好之后，工作人员进行扫码，则将该车数据库中的保修次数归0

项目演示：

用户登录界面

![1](https://github.com/ChunchunIsMe/shareBike/blob/master/showimg/1.PNG)

用户借车界面

![2](https://github.com/ChunchunIsMe/shareBike/blob/master/showimg/2.PNG)

用户还车界面

![3](https://github.com/ChunchunIsMe/shareBike/blob/master/showimg/3.PNG)

工作人员登录界面

![4](https://github.com/ChunchunIsMe/shareBike/blob/master/showimg/4.PNG)

工作人员操作界面

![5](https://github.com/ChunchunIsMe/shareBike/blob/master/showimg/5.PNG)

增加自行车之后的显示

![6](https://github.com/ChunchunIsMe/shareBike/blob/master/showimg/6.PNG)

增加的二维码

![7](https://github.com/ChunchunIsMe/shareBike/blob/master/showimg/7.PNG)
