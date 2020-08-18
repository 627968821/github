package club.banyuan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Enumeration;

@WebServlet(name = "RegistServlet",urlPatterns = "/regist.do")
public class  RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String checkPwd = request.getParameter("checkPwd");
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        String remoteAddr = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        Enumeration<String> headerNames = request.getHeaderNames();
        String name = headerNames.getClass().getName();
        System.out.println(name);

        System.out.println(requestURI+"\n"+requestURL+"+\n"+queryString+"\n"+remoteAddr+"\n"+remoteHost+"\n"+remotePort);




        System.out.println(loginName+"  "+password+"   "+checkPwd);

        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head><body>");
        out.println("RegistServlet中文啊");
        out.println("</body></html>");
        out.flush();
        out.close();
    }
}
