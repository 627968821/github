package club.banyuan.servlet;

import club.banyuan.dao.OrderDao;
import club.banyuan.dao.impl.OrderDaoImpl;
import club.banyuan.pojo.Order;
import club.banyuan.pojo.OrderDetail;
import club.banyuan.pojo.User;
import club.banyuan.service.OrderService;
import club.banyuan.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchMemberOrderServlet", urlPatterns = "/member/member_Order.do")
public class SearchMemberOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderService orderService =new OrderServiceImpl();
        try {
            List<Order> orderListByUserId = orderService.getOrderListByUserId(id);
            for (Order order : orderListByUserId) {
                List<OrderDetail> orderDetailListByOrderId = orderService.getOrderDetailListByOrderId(order.getId());
                orderDetailList.addAll(orderDetailListByOrderId);
            }
            session.setAttribute("orderList",orderListByUserId);
            session.setAttribute("orderDetailList",orderDetailList);
            request.getRequestDispatcher("member_Order.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
