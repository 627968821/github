package club.banyuan.dao;

import club.banyuan.pojo.Emp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmpDao {


    public int deleteEmp();

    public List<Emp> getAllEmp();

    public int addEmp();
    public  int updateEmp();
}
