import club.banyuan.pojo.User;
import club.banyuan.dao.UserDao;
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
    public void testAddUser(){
        User user = new User();
        user.setLoginName("pppp");
        user.setUserName("杨坤");
        user.setSex(1);
        user.setEmail("sdfs@dfsdf.com");
        user.setMobile("99898294892");
        user.setIdentitycode("32432423");
        user.setPassword("sdfsdf242342");
        user.setType(1);

        UserDao userDao = session.getMapper(UserDao.class);

        userDao.addUser(user);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(28);
        user.setLoginName("asdf");
        user.setUserName("杨坤");
        user.setSex(1);
        user.setEmail("sdfs@dfsdf.com");
        user.setMobile("99898294892");
        user.setIdentitycode("32432423");
        user.setPassword("1111111");
        user.setType(1);

        UserDao userDao = session.getMapper(UserDao.class);

        userDao.updateUser(user);
    }

    @Test
    public void testDelUser(){
        UserDao userDao = session.getMapper(UserDao.class);
        int result = userDao.delUser(28);
        System.out.println(result);
    }
}
