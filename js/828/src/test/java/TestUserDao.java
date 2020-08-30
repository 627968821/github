import club.banyuan.dao.UserDao;
import club.banyuan.pojo.User;
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

public class TestUserDao {
    SqlSession session = null;
    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    @After
    public void destroy(){
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }

    @Test
    public void testGetAll() throws IOException {
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> userList = userDao.getAll();

        System.out.println(userList.size());

        for(User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserByNameAndPwd(){
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.getUserByNameAndId("ikun",39);
        System.out.println(user);
    }

}
