import club.banyuan.dao.AuctionItemDao;
import club.banyuan.pojo.AuctionItem;
import club.banyuan.vo.AuctionVo;
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

public class TestAuctionDao {
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
    public void TestGetAuctionByAuctionId(){
        AuctionItemDao mapper = sqlSession.getMapper(AuctionItemDao.class);
        AuctionItem auctionByAuctionId = mapper.getAuctionByAuctionId(1);
        System.out.println(auctionByAuctionId);
    }
    @Test
    public void TestGetAuctionListByAuctionId(){
        AuctionItemDao mapper = sqlSession.getMapper(AuctionItemDao.class);
        List<AuctionVo> auctionListByAuctionId = mapper.getAuctionListByAuctionId(1);
        List<AuctionVo> auctionListByAuctionId1 = mapper.getAuctionListByAuctionId(2);
        List<AuctionVo> auctionListByAuctionId2 = mapper.getAuctionListByAuctionId(1);
        System.out.println("___________________________");

        for (AuctionVo auctionVo : auctionListByAuctionId) {
            System.out.println(auctionVo);
        }
        System.out.println("___________________________");
        for (AuctionVo auctionVo : auctionListByAuctionId2) {
            System.out.println(auctionVo);
        }
        System.out.println("___________________________");

        for (AuctionVo auctionVo : auctionListByAuctionId1) {
            System.out.println(auctionVo);
        }
    }
}
