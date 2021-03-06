/**
 * Created by lianchen on 14-10-24.
 */
var drawing = document.getElementById("drawing");
/*确定浏览器支持<canvas>元素*/
if(drawing.getContext){
    var context = drawing.getContext("2d");
    //红色矩形
    context.fillStyle = "red";
    context.fillRect(10, 10, 50, 50);
    //蓝色半透膜矩形
    context.strokeStyle = "red";
    context.fillStyle = "rgba(0,0,255,.5)";
    context.fillRect(30, 30, 50, 50);
    //红色描边矩形
    context.strokeStyle = "red";
    context.strokeRect(50, 50, 50, 50);
    //蓝色半透明描边矩形
    context.strokeStyle = "rgba(0, 0, 255, .5)";
    context.strokeRect(70, 70, 50, 50);


    //在两个矩形重叠的地方清除一个小矩形
    //红色矩形
    context.fillStyle = "red";
    context.fillRect(150, 10, 50, 50);
    //蓝色半透膜矩形
    context.fillStyle = "rgba(0,0,255,.5)";
    context.fillRect(170, 30, 50, 50);
    //清除矩形
    context.clearRect(180, 40,10,10);



    //绘制时钟
    //开始路径
    context.beginPath();
    /*绘制外圆*/
    context.arc(300, 60, 50, 0, 2*Math.PI, false);''
    //绘制内园
    context.moveTo(345,60);
    context.arc(300, 60, 45, 0, 2*Math.PI, false);

    context.strokeStyle="#000";
    //绘制分针
    context.moveTo(300,60);
    context.lineTo(300,25);

    //绘制时针
    context.moveTo(300,60);
    context.lineTo(270,60);

    //绘制文本
    context.font = "bold 12px Arial";
    context.textAlign = "center";
    context.textBaseline = "middle";
    context.fillText("12",300,20);


    //变换原点
    context.translate(100,20);

    //旋转表针
    context.rotate(1);
    //绘制分针
    context.moveTo(300,60);
    context.lineTo(350,20)
    //绘制时针

    //描边路径
    context.stroke();

}