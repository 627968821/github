import club.banyuan.dao.DeptDao;
import club.banyuan.dao.ImpDao;
import club.banyuan.pojo.Dept;
import club.banyuan.pojo.Imp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DeptTest {
    SqlSession session = null;
    DeptDao deptDao = null;
    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        session = sqlSessionFactory.openSession();
        deptDao = session.getMapper(DeptDao.class);
    }

    @After
    public void destroy(){
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }
    @Test
    public void getDept(){
        List<Dept> allDept = deptDao.getAllDept();
        System.out.println(allDept);
    }
    @Test
    public void addDept(){
        int i = deptDao.addDept("sss55", "sdd");
        System.out.println(i);
    }
    @Test
    public void getDeptByName(){
        Dept ss = deptDao.getDeptByName("怡宝");
        System.out.println(ss);
    }
}
