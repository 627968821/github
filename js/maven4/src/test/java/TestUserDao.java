import club.banyuan.pojo.User;
import club.banyuan.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestUserDao {
    @Test
    public void testGetAll() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("Sql.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        UserDao userDao = session.getMapper(UserDao.class);
        List<User> userList = userDao.getAll();

        System.out.println(userList.size());

        for(User user : userList){
            System.out.println(user);
        }

        session.commit();
        session.close();
    }
}
