package club.banyuan.servlet;

import club.banyuan.pojo.Product;
import club.banyuan.service.ProductService;
import club.banyuan.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddCartServlet",urlPatterns = "/addCart.do")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ProductService productService = new ProductServiceImpl();
        try {
            Product product = productService.getProductById(id);
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("cart");
            Map<Product,Integer> cart =null;
            if(obj == null){
                cart = new HashMap<Product,Integer>();
            }
            else{
                cart = (HashMap<Product,Integer>)obj;
            }
            cart.put(product,1);
            session.setAttribute("cart",cart);
            request.setAttribute("product",product);

            double sum = 0;
            for(Product product1 : cart.keySet()){
                sum += product1.getPrice() * cart.get(product1);
            }
            request.setAttribute("sum",sum);

            request.getRequestDispatcher("buycar.jsp").forward(request,response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
