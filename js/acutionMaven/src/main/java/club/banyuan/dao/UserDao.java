package club.banyuan.dao;
import club.banyuan.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserDao {
    public int addUser(User user);

    public int updateUser(User user);

    public User getUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Delete("delete from user where userId=#{userId}")
    public int deleteUserById(int userId);

    @Select("select * from user")
    @ResultType(User.class)
    public List<User> getAllUser();

    @Select("select * from user where userId = #{userId}")
    @ResultMap(value = {"userMap"})
    public User findUserById(Integer id);

    @Select("select u.*,o.auctionId from `user` u inner join `orders` o on o.userId=u.userId")
    @Results(id = "userMap", value = {
            @Result(column = "userId", property = "userId"),
            @Result(column = "userName", property = "userName"),
            @Result(column = "password", property = "password"),
            @Result(column = "IDCard", property = "IDCard"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "address", property = "address"),
            @Result(column = "pastCode", property = "pastCode"),
            @Result(column = "userId", property = "orderList",
                    many = @Many(select = "club.banyuan.dao.OrderDao.findOrderByUserId", fetchType = FetchType.LAZY))
                    })
            public List<User>findAll();
}
