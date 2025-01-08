package Dao;

import java.sql.*;

public class ConnDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/webnam";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static ConnDB instance;
 public  Connection conn;

    public Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                // Mở kết nối mới nếu kết nối đã đóng
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ConnDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Không thể kết nối đến cơ sở dữ liệu!");
            conn = null;
        }
}

    public static synchronized ConnDB getInstance() {
        if (instance == null) {
            instance = new ConnDB();
        }
        return instance;
    }
//public void addProduct(int id, String name, double price, int quantity, String image) throws SQLException {
//        String sql  = "INSERT INTO products (id, nameProduct,price, quantity,imgProduct) VALUES (?, ?, ?, ?, ?)";
//     stmt = conn.prepareStatement(sql);
//    stmt.setString(1, String.valueOf(id));
//    stmt.setString(2, name);
//    stmt.setDouble(3, price);
//    stmt.setInt(4, quantity);
//    stmt.setString(5,   image);
//    stmt.executeUpdate();
//
//}

    public static void main(String[] args) {
        ConnDB s = new ConnDB();
    }
}
