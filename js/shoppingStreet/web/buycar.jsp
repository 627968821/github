<%@ page import="club.banyuan.pojo.Product" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: edz
  Date: 2020/8/19
  Time: 9:43 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>购物车</h1>
<%
//    Product product = (Product) request.getAttribute("product");
//    out.println(product.getName());
    Map<Product,Integer> products= (Map<Product, Integer>) session.getAttribute("product");
    for(Product product:products.keySet()){
        out.print(product.getName());

    }
%>
</body>
</html>
