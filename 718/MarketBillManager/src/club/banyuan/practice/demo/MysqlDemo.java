package club.banyuan.practice.demo;

import java.sql.*;

public class MysqlDemo {
    static final String USER = "root";
    static final String PASSWORD = "88888888";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //JDBC连接串 db-demo表示数据库名称
    static final String DB_URL = "jdbc:mysql://localhost:3306/db_demo?useSSL=false";

    public static void main(String[] args) {
        Connection connection=null;
        Statement statement=null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库。。。");
            connection= DriverManager.getConnection(DB_URL,USER,PASSWORD);
            System.out.println("实例化 Statement 对象。。。");
            statement=connection.createStatement();

            //SQL 语句
            String sql="SELECT sno,sname FROM student";
            //执行SQL查询语句
            ResultSet rs=statement.executeQuery(sql);
            //循环遍历查询结果
            while (rs.next()){
                //获取本条数据的 sno 字段值
                Integer sno=rs.getInt("sno");
                //获取本条数据的 sname 字段值
                String  sname=rs.getString("sname");
                //输出结果
                System.out.println("学生序号为:"+sno+"的名字是"+sname);
            }
            //关闭 ResultSet
            rs.close();
            //关闭 Statement
            statement.close();
            //关闭 Connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //检查关闭连接
            try {
                if(statement !=null);
                {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if(connection !=null){
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
