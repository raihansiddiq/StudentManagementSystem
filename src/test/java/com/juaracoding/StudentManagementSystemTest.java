package com.juaracoding;

import org.testng.Assert;
import org.testng.annotations.*;

/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Raihan a.k.a. Raihan Siddiq
Java Developer
Created on 2/17/2025 11:43 AM
@Last Modified 2/17/2025 11:43 AM
Version 1.0
*/

public class StudentManagementSystemTest {

    private StudentManagementSystem sms;

    @BeforeClass
    public void setup() {
        sms = new StudentManagementSystem();
        System.out.println("Setting up before tests...");
    }

    @Test(priority = 1)
    public void testConnection() {
        boolean isConnected = sms.connect();
        Assert.assertTrue(isConnected, "Koneksi ke StudentManagementSystem gagal.");
    }

    @Test(priority = 2, dependsOnMethods = "testConnection")
    public void testDisconnection() {
        sms.disconnect();
        boolean isConnected = sms.isConnected();
        Assert.assertFalse(isConnected, "Pemutusan koneksi dari StudentManagementSystem gagal.");
    }

    @AfterClass
    public void cleanup() {
        System.out.println("Cleaning up after tests...");
        sms.disconnect();
    }
}
