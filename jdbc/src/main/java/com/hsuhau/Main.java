package com.hsuhau;

import java.sql.*;

public class Main {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/search?useUnicode=true&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWD = "sa123";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from search.vocabulary_cn");
            System.out.println("resultSet = " + resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}