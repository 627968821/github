package club.banyuan.servlet;

import club.banyuan.pojo.User;
import club.banyuan.pojo.UserAddress;
import club.banyuan.service.UserAddressService;
import club.banyuan.service.impl.UserAddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchMemberAddressServlet",urlPatterns = "/member/getMemberAddress.do")
public class SearchMemberAddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserAddressService userAddressService = new UserAddressServiceImpl();
        try {
            List<UserAddress> userAddressList = userAddressService.getUserAddressByUserId(user.getId());
            request.setAttribute("userAddressList", userAddressList);
            request.getRequestDispatcher("Member_Address.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
