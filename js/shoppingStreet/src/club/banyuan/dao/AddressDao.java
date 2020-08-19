package club.banyuan.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AddressDao extends IBaseDao{
    public Map<String,Integer> getAddressById(int id) throws Exception;
}
