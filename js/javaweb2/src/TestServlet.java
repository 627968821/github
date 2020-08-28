import club.banyuan.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TestServlet",urlPatterns = "/filter/test.do")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("reqAttr","reqAttrValue");

        HttpSession session = request.getSession();
        session.setAttribute("sessionAttr","sessionAttrValue");

        ServletContext application = request.getServletContext();
        application.setAttribute("appAttr","appValue");

        User user = new User();
        user.setId(1);
        user.setLoginName("loginName");
        request.setAttribute("user",user);

        User user2 = new User();
        user2.setId(2);
        user2.setLoginName("aaa");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        request.setAttribute("userList",userList);

        request.getRequestDispatcher("test.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
