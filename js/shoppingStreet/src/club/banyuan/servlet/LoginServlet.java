package club.banyuan.servlet;

import club.banyuan.pojo.User;
import club.banyuan.service.UserService;
import club.banyuan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String isSave = request.getParameter("isSave");
        String url = "login.jsp";
        UserService userService = new UserServiceImpl();
        try {
            User user = userService.login(username,password);
            if(user!=null) {
                request.getSession().setAttribute("user",user);
                request.getSession().setMaxInactiveInterval(10);
                url ="index.jsp";
                if("true".equalsIgnoreCase(isSave)){
                    Cookie cookie = new Cookie("loginName", user.getLoginName());
                    cookie.setMaxAge(90*3600);
                    response.addCookie(cookie);
                }
            }
            else{
                request.getSession().setAttribute("errorMsg","用户名或密码错误");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //response.sendRedirect(url);
        request.getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
