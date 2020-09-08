package club.banyuan.dao;

import club.banyuan.pojo.Imp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface ImpDao {
    @Select("select * from emp where name=#{username} and password=#{password}")
    @ResultType(Imp.class)
    public Imp login(@Param("username") String userName, @Param("password") String password);

}
