package club.banyuan.servlet;

import club.banyuan.pojo.Product;
import club.banyuan.service.ProductService;
import club.banyuan.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddCartServlet",urlPatterns = "/addCart.do")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ProductService productService = new ProductServiceImpl();
        try {
            Product product = productService.getProductById(id);
            List<Product> productList = new ArrayList<>();
            request.setAttribute("product",product);
            request.getRequestDispatcher("buycar.jsp").forward(request,response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
