package com.example.demo2025.daos.db;

import java.sql.*;

public class DBConnection {
    static String url = "jdbc:mysql://"+DBProperties.host()+":"+DBProperties.port()+"/"+DBProperties.dbname()+"?"+DBProperties.option();
    static Connection connection;

    public static Statement get(){
        try {
            if (connection==null || connection.isClosed()){
                makeConnect();
            }
            return connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    private static void makeConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, DBProperties.username(), DBProperties.password());
    }

    public static void main(String[] args) throws SQLException {
        Statement statement = get();
        ResultSet rs = statement.executeQuery("SELECT * FROM products");
        while (rs.next()){
            System.out.println(rs.getInt(1) + "," +
                    rs.getString(2) + "," +
                    rs.getDouble(3) + "," +
                    rs.getString(4));
        }
    }
}
