package club.banyuan.practice.demo;

import club.banyuan.practice.deo.Student;
import club.banyuan.practice.service.StudentService;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class StudentDemo {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        System.out.println("--- 获取制定学生信息 ---");
        //入参 需要查询的学生序号
        int sno=101;
        //
        Student studentById1 = studentService.getStudentById(sno);
        String data= JSONObject.toJSONString(studentById1);
        System.out.println(data);
        System.out.println("--- 获取学生列表 ---");
        List<Student> studentList = studentService.getStudentList();
        System.out.println(JSONObject.toJSONString(studentList));
        System.out.println("修改前数据");
        System.out.println(JSONObject.toJSONString(studentService.getStudentById(101)));
        Student student = new Student(101, "杨坤", "女", 2041);
        studentService.updateStudent(student);
        System.out.println("修改后数据");
        Student stdUpdate = studentService.getStudentById(101);
        System.out.println(JSONObject.toJSONString(stdUpdate));
        System.out.println("新增数据信息前学生列表");
        System.out.println(JSONObject.toJSONString(studentService.getStudentList()));
        Student student1 = new Student("孙俪", "女", 2041);
        System.out.println("新增数据信息后学生列表");
        studentService.addStudent(student1);
        System.out.println(JSONObject.toJSONString(studentService.getStudentList()));
        System.out.println("删除学生后的列表");
        studentService.deleteStudent(studentService.getStudentById(108));
        studentService.deleteStudent(studentService.getStudentById(109));
        studentService.deleteStudent(studentService.getStudentById(110));
        System.out.println(JSONObject.toJSONString(studentService.getStudentList()));


    }
}
