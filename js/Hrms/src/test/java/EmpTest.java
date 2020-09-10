import club.banyuan.dao.EmpDao;
import club.banyuan.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class EmpTest {
    SqlSession session = null;
    EmpDao empDao = null;
    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        session = sqlSessionFactory.openSession();
        empDao = session.getMapper(EmpDao.class);
    }

    @After
    public void destroy(){
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }


}
