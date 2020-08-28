<%--
  Created by IntelliJ IDEA.
  User: edz
  Date: 2020/8/21
  Time: 4:03 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">

        // ajax:异步的JavaScript与xml
        //     利用js中的XMLHttpRequst对象向服务器发送异步请求
        // 异步请求：相对于同步请求
        //         同步请求，下一次的请求发送必须等到当前请求响应的结果（等待，顺序）--- 页面整体刷新
        //         异步请求：请求发送不依赖其它请求响应的结果(没有等待，没有一定顺序) --- 页面的局部刷新


        var xmlhttp;
        if (window.XMLHttpRequest)
        {
            //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xmlhttp=new XMLHttpRequest();
        }
        else
        {
            // IE6, IE5 浏览器执行代码
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }

        function getSend() {
            xmlhttp.open("GET","ajax.do?username=xxx&password=123",true);
            xmlhttp.send();
            xmlhttp.onreadystatechange=function()
            {
                if (xmlhttp.readyState==4 && xmlhttp.status==200)
                {
                    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
                }
            }
        }

        function postSend() {
            xmlhttp.open("POST","ajax.do",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send("username=OOOO&password=456");
            xmlhttp.onreadystatechange=function()
            {
                if (xmlhttp.readyState==4 && xmlhttp.status==200)
                {
                    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
                }
            }
        }
    </script>
</head>
<body>
<div id="myDiv"></div>
<input type="button" id="getSend" value="GetBtn" onclick="getSend()"/>
<input type="button" id="postSend" value="postBtn" onclick="postSend()"/>
</body>
</html>
