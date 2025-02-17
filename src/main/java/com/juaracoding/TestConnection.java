package com.juaracoding;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Raihan a.k.a. Raihan Siddiq
Java Developer
Created on 2/17/2025 11:37 AM
@Last Modified 2/17/2025 11:37 AM
Version 1.0
*/

public class TestConnection {

    private Connection conn;

    public boolean connect() {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=university_db";
            String user = "universityuser";
            String pass = "Badud456";
            conn = DriverManager.getConnection(dbURL, user, pass);

            if (conn != null) {
                System.out.println("The connection has been successfully established.");

                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred while establishing the connection:");
            ex.printStackTrace();
        }
        return false;
    }

    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("The connection has been successfully closed.");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred while closing the connection:");
            ex.printStackTrace();
        }
    }

    public boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        TestConnection testConn = new TestConnection();

        if (testConn.connect()) {
            System.out.println("Connected to the database successfully.");
            

            testConn.disconnect();
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
