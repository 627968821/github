package club.banyuan.dao.impl;

import club.banyuan.dao.AddressDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AddressDaoImpl extends BaseDaoImpl implements AddressDao {
    public AddressDaoImpl(Connection connection) {
        super(connection);
    }
    @Override
    public Map<String,Integer> getAddressById(int id) throws Exception {
        Map<String,Integer> addressMap=new HashMap<>();
        String sql="select address,isDefault from user_address where userId=?";
        ResultSet rs = executeQuery(sql, new Object[]{id});
        if(rs.next()){
            addressMap = (Map<String,Integer>) tableToClass(rs);
        }
        this.closeResource(rs);
        return addressMap;
    }

    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        Map<String,Integer> addMap=new HashMap<>();
         while (rs.next()){
             String string = rs.getString("address");
             int anInt = rs.getInt("isDefault");
             addMap.put(string,anInt);
         }
         return addMap;
    }
}
