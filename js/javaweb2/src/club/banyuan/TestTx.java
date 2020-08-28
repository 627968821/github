package club.banyuan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TestTx {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/shoppingstreet?useUnicode=true&amp&characterEncoding=utf-8";
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "rootroot";

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);

        String sql = "insert into news values(?,?,?,?)";

        conn.setAutoCommit(false);

        PreparedStatement pstm = conn.prepareStatement(sql);
        try {
            pstm.setInt(1, 805);
            pstm.setString(2, "title5");
            pstm.setString(3, "content5");
            pstm.setObject(4, "2020-1-5");
            pstm.executeUpdate();

            //int i = 10 / 0;

            pstm.setInt(1, 804);
            pstm.setString(2, "title4");
            pstm.setString(3, "content4");
            pstm.setObject(4, "2020-1-4");
            pstm.executeUpdate();

            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
        }
        pstm.close();
        conn.close();
    }
}
