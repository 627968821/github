package club.banyuan.service;

import club.banyuan.pojo.UserAddress;

import java.sql.SQLException;
import java.util.List;

public interface UserAddressService {
    public List<UserAddress> getUserAddressByUserId(int userId) throws Exception;
}
