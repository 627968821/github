package club.banyuan.dao;

import club.banyuan.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    public List<Product> getProductByParam(Map map);
    public List<Product> getProductByParam2(Map map);
    public List<Product> getProductByLevel1IdArray(@Param("level1Ids") List<Integer> level1Ids);
}
