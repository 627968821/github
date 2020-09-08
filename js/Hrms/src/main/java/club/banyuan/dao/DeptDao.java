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
    @Update("update department set name=#{name},description=#{description} where id=#{id}")
    public int updateDeptById(@Param("name")String name,@Param("description") String description,@Param("id") Integer id);
    @Select("select *from department where name=#{name} and id!=#{id}")
    @ResultType(Dept.class)
    public Dept getDeptByNameAndId(@Param("name") String name,@Param("id") Integer id);
}
