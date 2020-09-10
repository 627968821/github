package club.banyuan.controller;

import club.banyuan.dao.ImpDao;
import club.banyuan.pojo.Imp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class LoginController {
    SqlSession session = null;
    ImpDao impDao = null;

    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        session = sqlSessionFactory.openSession();
        impDao = session.getMapper(ImpDao.class);
    }
    public void destroy(){
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }
    @RequestMapping("admin/login")
    public String login(HttpServletRequest request, HttpSession session) throws IOException {
        init();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+"   "+password);
        Imp ikun = impDao.login(username, password);
        System.out.println(ikun);
        if(ikun!=null){
            destroy();
            return "redirect:/home_page.html";
        }else
            destroy();
            return "redirect:/";
    }
    @RequestMapping("/")
    public String index() {
        return "login.html";
    }
}
