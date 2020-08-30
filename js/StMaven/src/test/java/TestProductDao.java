import club.banyuan.dao.ProductDao;
import club.banyuan.dao.ProductEntityDao;
import club.banyuan.pojo.Product;
import club.banyuan.pojo.ProductEntity;
import club.banyuan.vo.PageVO;
import club.banyuan.vo.ProductVO;
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
    ProductDao productDao = null;
    @Before
    public void init() throws IOException {
        //        获取连接信息（配置在SqlMapConfig.xml）,所以读取配置文件
//        在classpath下找SqlMapConfig.xml，并读取到InputStream流
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        SqlSessionFactory创建管理连接，实例化Factory，将数据库信息传递给它
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
//        创建Session对象：
//                类似Connection，强大
        session = sqlSessionFactory.openSession();

//        获取dao接口的实现类对象
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
    public void testGetAll() throws IOException {

        List<Product> productList = productDao.getAll();

        printProductList(productList);
    }

    @Test
    public void testGetProductByPrice(){
        Map param = new HashMap();
        param.put("lowPrice",1000);
        param.put("highPrice",6000);

        List<Product> productList = productDao.getProductByPrice(param);
        printProductList(productList);
    }

    private void printProductList(List<Product> productList) {
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    @Test
    public void testLikeSearch(){
        String key = "%奶%";
        List<Product> productList = productDao.getProductByNameOrDesc(key);
        printProductList(productList);
    }

    @Test
    public void getProductByParam(){
//        Map param = new HashMap();
//        param.put("lowPrice",400);
//        param.put("highPrice",6000);
//        param.put("key","%奶%");
//        param.put("beginIndex",2);
//        param.put("pageSize",2);

        ProductVO productVO = new ProductVO();
        productVO.setLowPrice(400);
        productVO.setHighPrice(6000);
        productVO.setKey("%奶%");
        productVO.setBeginIndex(2);
        productVO.setPageSize(2);
        Product product = new Product();
        product.setStock(100);
        productVO.setProduct(product);

        List<Product> productList = productDao.getProductByParam(productVO);
        printProductList(productList);
    }

    @Test
    public void testCountProductRows(){
        int rows = productDao.countProductRows();
        System.out.println(rows);
    }

    @Test
    public void testCountLevel1Product(){
        List<PageVO> pageVOList= productDao.countLevel1ProductRows();
        for(PageVO pageVO : pageVOList){
            System.out.println(pageVO.getNum() +"  "+pageVO.getCategoryLevel1Id());
        }
    }

    @Test
    public void testProductEntity(){
        ProductEntityDao productEntityDao =session.getMapper(ProductEntityDao.class);
        List<ProductEntity> entityList = productEntityDao.getAll();
        for(ProductEntity entity : entityList){
            System.out.println(entity);
        }
    }
    @Test
    public void
    testGetProductById(){
        ProductDao mapper = session.getMapper(ProductDao.class);
        Product productById = mapper.getProductById(733);
        System.out.println(productById);

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
    @Test
    public void testProductByParam3() throws IOException {
        String param = "奶 儿童";
        List<String> keyList = new ArrayList<String>();
        String[] params = param.split(" ");
        for(String s : params){
            keyList.add("%"+s+"%");
        }

        List<Product> productList = productDao.getProductByParam3(keyList);
        for(Product product : productList){
            System.out.println(product);
        }
    }
}
