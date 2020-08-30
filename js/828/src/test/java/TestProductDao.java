import club.banyuan.dao.ProductDao;
import club.banyuan.dao.UserDao;
import club.banyuan.pojo.Product;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestProductDao {
    SqlSession session = null;
    ProductDao productDao= null;
    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        session = sqlSessionFactory.openSession();
        productDao = session.getMapper(ProductDao.class);
    }

    @After
    public void destroy(){
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }

    @Test
    public void testProductByParam() throws IOException {
       Map map = new HashMap();
//       map.put("name","%奶%");
       map.put("lowPrice",400);
       map.put("hiPrice",1000);
       map.put("stock",200);

       List<Product> productList = productDao.getProductByParam2(map);
        for(Product product : productList){
            System.out.println(product);
        }
    }

    @Test
    public void testGetProductByLevel1IdArray() {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(628);
        ids.add(630);
        ids.add(660);
        List<Product> productList = productDao.getProductByLevel1IdArray(ids);
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
