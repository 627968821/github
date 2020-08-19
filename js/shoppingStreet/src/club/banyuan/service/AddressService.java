package club.banyuan.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AddressService {
    public Map<String,Integer> getAddressById(int id) throws Exception;
}
