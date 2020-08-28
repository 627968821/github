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
    <script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $("#getSend").click(function () {
                // $.ajax({
                //     "url":"ajax.do",
                //     "type":'get',
                //     "data":"username=abcd&password=123456",
                //     "success" : function(result){
                //         $("#myDiv").html(result);
                //     }
                // });
                $.get("ajax.do","username=ABCDEFG&password=4245739754",function (result) {
                    $("#myDiv").html(result);
                });
            });

            $("#postSend").click(function () {
                $.post("ajax.do","username=CCCC&password=hosdfsdfsd",function (result) {
                    $("#myDiv").html(result);
                });
            });
        });
    </script>
</head>
<body>
<div id="myDiv"></div>
<input type="button" id="getSend" value="GetBtn" />
<input type="button" id="postSend" value="postBtn" />
</body>
</html>
