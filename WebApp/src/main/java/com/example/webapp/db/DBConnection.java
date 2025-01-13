package com.example.webapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
}
