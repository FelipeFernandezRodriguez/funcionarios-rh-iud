package com.iudigital.funcionarios.rh.iud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/funcionarios-rh-iud";
    private static final String USER = "postgres";
    private static final String PASSWORD = "MyBD+2684";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
