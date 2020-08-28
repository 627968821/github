package club.banyuan.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "TestFilter1",urlPatterns = "/filter/*")
public class TestFilter1 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("TestFilter1 --- doFilter --- start");
        //        请求转发到目标地址
        chain.doFilter(req, resp);

        System.out.println("TestFilter1 --- doFilter --- end");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
