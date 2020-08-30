import club.banyuan.dao.OrderDao;
import club.banyuan.pojo.Order;
import club.banyuan.pojo.SaledAuction;
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

public class TestOrderDao {
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
    public void TestGetOrderByUserId(){
        OrderDao mapper = sqlSession.getMapper(OrderDao.class);
        List<Order> orderByUserId = mapper.findOrderByUserId(1);
        for (Order order : orderByUserId) {
            System.out.println(order);
        }
    }
    @Test
    public void TestGetAllSaledAuction(){
        OrderDao mapper = sqlSession.getMapper(OrderDao.class);
        List<SaledAuction> allSaledAuction = mapper.getAllSaledAuction();
        for (SaledAuction saledAuction : allSaledAuction) {
            System.out.println(saledAuction);
        }

    }
    @Test
    public void TestGetSaledAuctionByAuctionId(){
        OrderDao mapper = sqlSession.getMapper(OrderDao.class);
        SaledAuction saledAuctionByAuctionId = mapper.getSaledAuctionByAuctionId(1);
        System.out.println(saledAuctionByAuctionId);
    }
}
