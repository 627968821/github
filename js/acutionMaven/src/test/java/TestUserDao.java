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
    SqlSession sqlSession=null;
    InputStream resourceAsStream=null;
    @Before
    public void init() throws IOException {
        resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        sqlSession = build.openSession();
    }
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        resourceAsStream.close();
        sqlSession.close();
    }
    @Test
    public void TestAddUser() throws IOException {
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUserName("huihui");
        user.setPassword("123456");
        user.setIDCard("123456");
        user.setPhone("123456");
        user.setAddress("asdasd");
        user.setPastCode("asdasd");
        int i = mapper.addUser(user);
        System.out.println(i);
    }
    @Test
    public void TestUpdateUser(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUserId(5);
        user.setUserName("huihuirefine1");
        user.setPassword("123456");
        user.setIDCard("123456");
        user.setPhone("18888888888");
//        user.setAddress("asdasd");
//        user.setPastCode("asdasd");
        int i = mapper.updateUser(user);
        System.out.println(i);
    }
    @Test
    public void TestGetUserByUserNameAndPassword(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User ikun = mapper.getUserByUserNameAndPassword("ikun", "123456");
        System.out.println(ikun);
        User ikun1 = mapper.getUserByUserNameAndPassword("ikun", "123456");
        System.out.println(ikun1);
    }
    @Test
    public void TestDeleteUserById(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        int i = mapper.deleteUserById(2);
        System.out.println(1);
    }
    @Test
    public void TestGetAllUser(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> allUser = mapper.getAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
        List<User> allUser1 = mapper.getAllUser();
        for (User user : allUser1) {
            System.out.println(user);
        }

    }
    @Test
    public void TestFindUserById(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User userById = mapper.findUserById(5);
        System.out.println(userById);
    }
    @Test
    public void TestFindAll(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
}
