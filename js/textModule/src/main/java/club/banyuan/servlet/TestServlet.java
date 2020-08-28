package club.banyuan.servlet;

import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "TestServlet",urlPatterns = "/text.do")
public class TestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("shabib");
        response.sendRedirect("dabian.jsp");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
doPost(request,response);
    }
}
