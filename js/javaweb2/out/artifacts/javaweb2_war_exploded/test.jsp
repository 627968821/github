<%@ page import="club.banyuan.User" %><%--
  Created by IntelliJ IDEA.
  club.banyuan.User: edz
  Date: 2020/8/19
  Time: 10:50 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
request的Attribute：<%= request.getAttribute("reqAttr")%>
<br/>
session的Attribute: <%= session.getAttribute("sessionAttr")%>
<br/>
ServletContext的Attribute: <%=application.getAttribute("appAttr")%>
<br/>
<%
//    User user = (User) request.getAttribute("user");
//    out.print(user.getId()+"  "+user.getLoginName());
%>
<hr/>
request的Attribute：${requestScope.reqAttr}
<br/>
session的Attribute: ${sessionScope.sessionAttr}
<br/>
ServletContext的Attribute: ${applicationScope.appAttr}
<br/>
${requestScope.user.id == 1}
<br/>
${empty requestScope.user}
<br/>
${requestScope.user.id} --- ${requestScope.user.loginName}
<br/>

<hr/>
<c:if test="${empty requestScope.user}">
    user为空
</c:if>
<c:if test="${not empty requestScope.user}">
    user 不为空
</c:if>

<br/>

<c:choose>
    <c:when test="${requestScope.user.id == 1}">
        id 为1
    </c:when>
    <c:when test="${requestScope.user.id == 2}">
        id 为2
    </c:when>
    <c:otherwise>
        id为空
    </c:otherwise>
</c:choose>
<br/>

<c:forEach var="user" items="${requestScope.userList}">
    ${user.id}  - ${user.loginName} <br/>
</c:forEach>

</body>
</html>
