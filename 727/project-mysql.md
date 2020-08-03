## 项目中使用 JDBC

### 下载 mysql-connector-java
在下面的网站中 Operating System 选择 Platform Independent 下载压缩包

[https://downloads.mysql.com/archives/c-j/](https://downloads.mysql.com/archives/c-j/)

解压之后获得后缀为 `.jar` 的文件，如 `mysql-connector-java-8.0.20.jar`

### 新建项目并添加依赖
在项目中创建 `lib` 文件夹，并拷贝 `mysql-connector-java-8.0.20.jar` 文件到该文件夹中。在项目中添加该依赖。

### 新建工具类
```java
package club.banyuan.mysqldemo.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {

	protected static Connection connection;
	protected static PreparedStatement pstmt;
	protected static ResultSet resultSet;

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/db_demo?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "123456";

	static {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Map<String, Object> queryOne(String sql, Object... params) {
		Map<String, Object> map = new HashMap<String, Object>();
		connection = getConnection();
		try {
			pstmt = connection.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			resultSet = pstmt.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int colLen = metaData.getColumnCount();
			while (resultSet.next()) {
				for (int i = 0; i < colLen; i++) {
					String colName = metaData.getColumnName(i + 1);
					Object colValue = resultSet.getObject(colName);
					if (colValue == null) {
						colValue = "";
					}
					map.put(colName, colValue);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, resultSet);
		}
		return map;
	}

	public static List<Map<String, Object>> queryAll(String sql, Object... params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		connection = getConnection();
		try {
			pstmt = connection.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			resultSet = pstmt.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int colLen = metaData.getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < colLen; i++) {
					String colName = metaData.getColumnName(i + 1);
					Object colValue = resultSet.getObject(colName);
					if (colValue == null) {
						colValue = "";
					}
					map.put(colName, colValue);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, resultSet);
		}
		return list;
	}

	public static int update(String sql, Object... params) {
		int result = 0;
		connection = getConnection();
		try {
			pstmt = connection.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, resultSet);
		}
		return result;
	}

	public static void close(Connection connection, PreparedStatement pstmt, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

### 新建 Student Entity
```java
package club.banyuan.mysqldemo.entity;

public class Student {

	private int sno;
	private String sname;
	private String ssex;
	private int classNo;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

}
```

### 新建 StudentService
```java
package club.banyuan.mysqldemo.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import club.banyuan.mysqldemo.conn.JdbcUtil;
import club.banyuan.mysqldemo.entity.Student;

public class StudentService {

	public Student getStudentById(int sno) {
		String sql = "select sno, sname, ssex, classNo from student where sno = ?";
		Map<String, Object> map = JdbcUtil.queryOne(sql, sno);
		Student student = JSONObject.parseObject(JSONObject.toJSONString(map), Student.class);
		return student;
	}
	
	public List<Student> getStudentList() {
		String sql = "select sno, sname, ssex, classNo from student";
		List<Map<String, Object>> list = JdbcUtil.queryAll(sql);
		List<Student> students = JSONObject.parseArray(JSONObject.toJSONString(list), Student.class);
		return students;
	}
	
	public void updateStudent(Student student) {
		Student s = getStudentById(student.getSno());
		s.setSname(student.getSname());
		s.setSsex(student.getSsex());
		s.setClassNo(student.getClassNo());
		String sql = "update student set sname = ?, ssex = ?, classNo = ? where sno = ?";
		JdbcUtil.update(sql, s.getSname(), s.getSsex(), s.getClassNo(), s.getSno());
	}
	
	public void insertStudent(Student student) {
		String sql = "insert into student(sname, ssex, classNo) values(?, ?, ?)";
		JdbcUtil.update(sql, student.getSname(), student.getSsex(), student.getClassNo());
	}
	
	public void deleteStudentById(int sno) {
		String sql = "delete from student where sno = ?";
		JdbcUtil.update(sql, sno);
	}
}
```

### 新建测试类 StudentDemo
```java
package club.banyuan.mysqldemo.demo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import club.banyuan.mysqldemo.entity.Student;
import club.banyuan.mysqldemo.service.StudentService;

public class StudentDemo {

	public static void main(String[] args) {
		System.out.println("--- 获取学生列表 ---");
		StudentService studentService = new StudentService();
		List<Student> list = studentService.getStudentList();
		String data = JSONObject.toJSONString(list);
		System.out.println(data);

		System.out.println("\n--- 获取指定学生信息 ---");
		int sno = 101;
		Student s1 = studentService.getStudentById(sno);
		data = JSONObject.toJSONString(s1);
		System.out.println(data);

//		System.out.println("\n--- 新增学生信息 ---");
//		Student iStudent = new Student();
//		iStudent.setSname("张三");
//		iStudent.setSsex("男");
//		iStudent.setClassNo(90303);
//		studentService.insertStudent(iStudent);
//		System.out.println("--- 新增学生成功 ---");
//
//		System.out.println("--- 获取学生列表 ---");
//		list = studentService.getStudentList();
//		data = JSONObject.toJSONString(list);
//		System.out.println(data);

//		System.out.println("\n--- 修改学生信息 ---");
//		Student uStudent = new Student();
//		uStudent.setSno(107);
//		uStudent.setSname("张三三");
//		uStudent.setSsex("女");
//		studentService.updateStudent(uStudent);
//		System.out.println("--- 修改学生成功 ---");
//
//		System.out.println("--- 获取学生列表 ---");
//		list = studentService.getStudentList();
//		data = JSONObject.toJSONString(list);
//		System.out.println(data);

//		System.out.println("\n--- 删除学生信息 ---");
//		int dsno = 107;
//		studentService.deleteStudentById(dsno);
//		System.out.println("--- 删除学生成功 ---");
//
//		System.out.println("--- 获取学生列表 ---");
//		list = studentService.getStudentList();
//		data = JSONObject.toJSONString(list);
//		System.out.println(data);
	}
}
```

### 运行查看结果
```
--- 获取学生列表 ---
[{"classNo":90301,"sname":"李华","sno":101,"ssex":"男"},{"classNo":90301,"sname":"孙蓉","sno":102,"ssex":"女"},{"classNo":90301,"sname":"赵明","sno":103,"ssex":"男"},{"classNo":90302,"sname":"李鹏","sno":104,"ssex":"男"},{"classNo":90302,"sname":"吴天","sno":105,"ssex":"男"},{"classNo":90302,"sname":"沈月","sno":106,"ssex":"女"}]

--- 获取指定学生信息 ---
{"classNo":90301,"sname":"李华","sno":101,"ssex":"男"}
```
