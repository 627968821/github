package club.banyuan.service;

import club.banyuan.pojo.User;

import java.sql.SQLException;

public interface UserServer {
public User register(User user)throws SQLException;
}
