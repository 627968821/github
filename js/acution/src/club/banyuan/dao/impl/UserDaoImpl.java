package club.banyuan.dao.impl;

import club.banyuan.dao.IBaseDao;
import club.banyuan.dao.UserDao;
import club.banyuan.pojo.User;

import java.sql.Connection;

public class UserDaoImpl extends IBaseDaoImpl implements UserDao {
    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User addUser(User user) {
        return null;
    }
}
