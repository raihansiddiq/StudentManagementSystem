package com.juaracoding;


/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Raihan a.k.a. Raihan Siddiq
Java Developer
Created on 2/15/2025 11:22 AM
@Last Modified 2/15/2025 11:22 AM
Version 1.0
*/


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=university_db";
    private static final String USER = "universityuser"; // Ganti dengan username SQL Server Anda
    private static final String PASSWORD = "Badud456"; // Ganti dengan password SQL Server Anda

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver SQL Server tidak ditemukan", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}