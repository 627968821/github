package club.banyuan.practice.service;

import club.banyuan.mbm.uti.JdbcUtil;
import club.banyuan.practice.deo.Student;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.DnsSrv;

import java.util.List;
import java.util.Map;

public class StudentService {
    //根据学生序号获取学生信息
    public Student getStudentById(int sno){
        //SQL语句
        String sql="select sno,sname,ssex,classNo from student where sno = ?";
        Map<String,Object> map= JdbcUtil.queryOne(sql,sno);
        //
        Student student= JSONObject.parseObject(JSONObject.toJSONString(map),Student.class);
        return student;
    }
    //获取学生列表
    public List<Student> getStudentList(){
        //SQL语句
        String sql="select sno,sname,ssex,classNo from student";
        List<Map<String,Object>> list=JdbcUtil.queryALL(sql);
        List<Student> students=JSONObject.parseArray(JSONObject.toJSONString(list),Student.class);
        return students;
    }
    //更新学生信息
    public void updateStudent(Student student){
        Student s = getStudentById(student.getSno());
        if(s!=null){
            s.setSname(student.getSname());
            s.setClassNo(student.getClassNo());
            s.setSsex(student.getSsex());
            //SQL语句
            String sql="update student set sname=?,ssex=?,classNo=? where sno=?";
            JdbcUtil.update(sql,s.getSname(),s.getSsex(),s.getClassNo(),s.getSno());
        }
    }
    //新增学生信息
    public void addStudent(Student student){
        String sql="insert into student(sname,ssex,classNo) values(?,?,?)";
        JdbcUtil.update(sql,student.getSname(),student.getSsex(),student.getClassNo());
    }
    //删除学生信息
    public void deleteStudent(Student student){
        Student studentById = getStudentById(student.getSno());
        String sql="delete from student where sno=?";
        JdbcUtil.update(sql,studentById.getSno());
    }
}
