package club.banyuan.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*
       urlPatterns
                /* : 过滤所有的请求
                *.do : 过滤所有的以.do作为后缀的请求
                /父路径/* : 过滤父路径下所有的请求
 */
@WebFilter(filterName = "TestFilter2",urlPatterns = "/*")
public class TestFilter2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("TestFilter2 --- doFilter  ---  start");

        chain.doFilter(req, resp);
        System.out.println("TestFilter2 --- doFilter  ---  end");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
