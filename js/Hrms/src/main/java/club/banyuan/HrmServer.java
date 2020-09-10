package club.banyuan;

import club.banyuan.dao.DeptDao;
import club.banyuan.pojo.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HrmServer {
    SqlSession session = null;
    SqlSessionFactory sqlSessionFactory;

     {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(inputStream);
//        session = sqlSessionFactory.openSession();
//session.getMapper(Dept)
//        return session;
    }

    public <T> T getMapper(Class<T> cls){
        session= sqlSessionFactory.openSession();
        return session.getMapper(cls);
    }

    public void destroy() {
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }

    public static void main(String[] args) {
        HrmServer hrmServer = new HrmServer();
            DeptDao deptDao = hrmServer.getMapper(DeptDao.class);
            System.out.println(deptDao);
            List<Dept> allDept = deptDao.getAllDept();
            System.out.println(allDept);
    }
}
