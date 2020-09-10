package club.banyuan.controller;

import club.banyuan.dao.EmpDao;
import club.banyuan.pojo.Emp;
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
public class EmpController {
    SqlSession session = null;
    EmpDao empDao = null;

    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        session = sqlSessionFactory.openSession();
        empDao = session.getMapper(EmpDao.class);
    }
    public void destroy(){
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }

}
