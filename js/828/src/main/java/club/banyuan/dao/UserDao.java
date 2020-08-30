package club.banyuan.dao;

import club.banyuan.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public List<User> getAll();
    public User getUserByNameAndId(@Param("loginname") String loginname, @Param("id") int id);
}
