package club.banyuan.servlet;

import club.banyuan.pojo.User;
import club.banyuan.service.UserService;
import club.banyuan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");

        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);
        UserService userService = new UserServiceImpl();
        try {
            User loginUser = userService.login(loginName,password);
            response.sendRedirect("index.html");
        } catch (Exception throwables) {
            //throwables.printStackTrace();
            response.sendRedirect("login.html");
        }
    }
}
