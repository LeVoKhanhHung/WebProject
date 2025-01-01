package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

    protected static Connection getConnection() throws SQLException {
        try {
            // Đảm bảo bạn đã tải driver MySQL JDBC
            String url = "jdbc:mysql://localhost:3306/your_database"; // Đổi thành URL database của bạn
            String user = "your_username"; // Thay thế bằng tên người dùng của bạn
            String password = "your_password"; // Thay thế bằng mật khẩu của bạn
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException("Không thể kết nối đến cơ sở dữ liệu.", e);
        }
    }
}

