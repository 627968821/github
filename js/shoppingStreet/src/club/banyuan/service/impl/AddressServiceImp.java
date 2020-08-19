package club.banyuan.service.impl;

import club.banyuan.dao.AddressDao;
import club.banyuan.dao.impl.AddressDaoImpl;
import club.banyuan.dao.util.DataSourceUtil;
import club.banyuan.service.AddressService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AddressServiceImp implements AddressService {
    @Override
    public Map<String,Integer> getAddressById(int id) throws Exception {
        Connection conn = DataSourceUtil.openConnection();
         AddressDao addr=new AddressDaoImpl(conn);
        Map<String,Integer> addressById = addr.getAddressById(id);
        DataSourceUtil.closeConnection(conn);
        return addressById;
    }
}
