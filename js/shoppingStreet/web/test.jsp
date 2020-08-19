<%--
  Created by IntelliJ IDEA.
  User: edz
  Date: 2020/8/19
  Time: 10:50 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
request的Attribute：<%= request.getAttribute("reqAttr")%>
<br/>
session的Attribute: <%= session.getAttribute("sessionAttr")%>

</body>
</html>
