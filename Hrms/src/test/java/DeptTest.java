import club.banyuan.dao.DeptDao;
<<<<<<< HEAD
import club.banyuan.dao.ImpDao;
import club.banyuan.pojo.Dept;
import club.banyuan.pojo.Imp;
=======
import club.banyuan.pojo.Dept;
>>>>>>> fc5f787... 1
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    public void destroy() {
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }

    @Test
    public void getDept() {
        List<Dept> allDept = deptDao.getAllDept();
        System.out.println(allDept);
    }

    @Test
    public void addDept() {
        int i = deptDao.addDept("sss55", "sdd");
        System.out.println(i);
    }

    @Test
    public void getDeptByName() {
        Dept ss = deptDao.getDeptByName("怡宝");
        System.out.println(ss);
    }

    @Test
    public void deleteById() {
        List<Integer> idList = new ArrayList<>();
        idList.add(4);
        idList.add(9);
        int i = deptDao.deleteDeptById(idList);
        System.out.println(i);
    }
    @Test
    public void update(){
        int i = deptDao.updateDeptById("zhangsan", "asdas", 3);
        System.out.println(i);
    }
    @Test
    public void getDeptByNameAndId(){
        Dept dp = deptDao.getDeptByNameAndId("售后", 3);
        System.out.println(dp);
    }
}
