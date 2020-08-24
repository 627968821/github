package club.banyuan.servlet;

import club.banyuan.dao.UserDao;
import club.banyuan.dao.impl.UserDaoImpl;
import club.banyuan.pojo.User;
import club.banyuan.service.UserService;
import club.banyuan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogUserAjaxServlet", urlPatterns = "/ajaxUser.do")
public class LogUserAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        PrintWriter out = response.getWriter();
        System.out.println(loginName);
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        try {
            User exit = userService.isExit(loginName);
            session.setAttribute("isExit",exit);
            out.println(exit.getLoginName());
        } catch (Exception e) {
//            e.printStackTrace();
        }finally {
            out.flush();
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
