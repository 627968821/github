package club.banyuan.dao;

import club.banyuan.pojo.Admin;
import club.banyuan.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AdminDao {
    @Select("select * from admin where username=#{username} and password=#{password}")
    @ResultType(Admin.class)
    Admin login(@Param("username") String userName, @Param("password") String password);
    int deleteAdminById(List<Integer> idList);

    @Select("select *from admin where username like #{username}")
    @ResultType(Admin.class)
    List<Admin> getAdminByName(String username);

    @Select("select *from admin where id=#{id}")
    @ResultType(Admin.class)
    Admin getAdminById(Integer id);

    @Update("Update admin set username=#{username},password=#{password} where id =#{id}")
    int updateAdmin(@Param("username") String username, @Param("password") String password, @Param("id") Integer id);

    @Select("select *from admin")
    @ResultType(Admin.class)
    List<Admin> getAllAdmin();

    @Insert("INSERT into admin (username,password)  VALUES (#{username},#{password})")
    int addAdmin(@Param("username") String username, @Param("password") String password);
}
