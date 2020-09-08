package club.banyuan.dao;

import club.banyuan.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeptDao {
    @Select("Select * from department")
    @ResultType(Dept.class)
    public List<Dept> getAllDept();
    @Insert("insert into department (name,description) values(#{name},#{description})")
    public int addDept(@Param("name")String name,@Param("description") String description);
    @Select("select *from department where name=#{name}")
    @ResultType(Dept.class)
    public Dept getDeptByName(String name);

    public int deleteDeptById(List<Integer> idList);
}
