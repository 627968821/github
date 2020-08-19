package club.banyuan.dao;

import club.banyuan.pojo.User;

import java.sql.SQLException;

public interface UserDao extends IBaseDao{
    public User getUserByUserNameAndPwd(String username,String password) throws Exception;
    public User addUser(User user);

    public User getUserByUserName(String userName) throws Exception;
}
