package club.banyuan.servlet;

import club.banyuan.service.AddressService;
import club.banyuan.service.impl.AddressServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Member_AddressServlet",urlPatterns = "/address.do")
public class Member_AddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getQueryString();
        HttpSession session = request.getSession();
        AddressService addressService=new AddressServiceImp();
        try {
            Map<String,Integer> addressById = addressService.getAddressById(Integer.parseInt(id));
            if(addressById==null){
                request.getRequestDispatcher("login.sql").forward(request,response);
            }else {
                session.setAttribute("address",addressById);
                request.getRequestDispatcher("member_Address.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
