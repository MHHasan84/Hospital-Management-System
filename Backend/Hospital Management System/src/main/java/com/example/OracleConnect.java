package com.example;

import java.sql.*;

public class OracleConnect {
    private Connection connection;
    private static final String host = "127.0.0.1";
    private static final String dbname = "localhost";
    private static final String username = "c##hospital_management_db";
    private static final String password = "hasandb";
    private static final String port = "1521";

    public OracleConnect() throws Exception {
        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
        this.connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected");
    }

    public int updateDB(String query) throws Exception {
        Statement statment = this.connection.createStatement();
        return statment.executeUpdate(query);
    }

    public ResultSet searchDB(String query) throws Exception {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

    public void close() throws Exception {
        this.connection.close();
    }
}

