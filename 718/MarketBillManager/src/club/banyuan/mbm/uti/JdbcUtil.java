package club.banyuan.mbm.uti;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {
    //JDBC启动
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //JDBC连接串
    private static final String DB_URL = "jdbc:mysql://localhost:3306/SUPPERMAKETE?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "88888888";
    protected static Connection connection;
    protected static PreparedStatement preparedStatement;
    protected static ResultSet resultSet;

    //初始化
    static {
        try {
             Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    //查询单条数据结果
    public static Map<String, Object> queryOne(String sql, Object... params) {
        Map<String, Object> map = new HashMap<>();
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
        }
        return map;
    }
//查询多条结果
public static List<Map<String, Object>> selectAll(String sql, Object... params) {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    connection = getConnection();
    try {
        preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        resultSet = preparedStatement.executeQuery();
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
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    } finally {
        //关闭连接
    }
    return list;
}
    //查询多条结果
    public static List<Map<String, Object>> queryALL(String sql, Object... params) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
        }
        return list;
    }

    //插入 更新 删除数据
    public static int update(String sql, Object... parms) {
        int result = 0;
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (parms != null && parms.length > 0) {
                for (int i = 0; i < parms.length; i++) {
                    preparedStatement.setObject(i + 1, parms[i]);
                }
            }
            result = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
        }
        return result;
    }

    //关闭连接
    public static void close(Connection conn, PreparedStatement preSt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (preSt != null) {
                preSt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JdbcUtil jdbcUtil = new JdbcUtil();
    }
}
