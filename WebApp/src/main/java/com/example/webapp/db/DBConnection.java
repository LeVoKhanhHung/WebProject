package com.example.webapp.db;

import java.sql.*;

public class DBConnection {
    static String url = "jdbc:mysql://localhost:3306/webapp";
    static final String user = "root";
    static final String password = "";
    static Connection connection;

    public static Connection get(){
        try {
            if (connection==null || connection.isClosed()){
                makeConnect();
                System.out.println("Connect successfully");
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
            return null;
        }
    }

    private static void makeConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        get();
    }
}
