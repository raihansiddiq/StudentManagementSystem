package com.juaracoding;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Raihan a.k.a. Raihan Siddiq
Java Developer
Created on 2/15/2025 11:31 AM
@Last Modified 2/15/2025 11:31 AM
Version 1.0
*/


public class StudentDAO {

    // Menambahkan mahasiswa baru ke database
    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (name, email, age, major, gpa) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, student.getName());
            pst.setString(2, student.getEmail());
            pst.setInt(3, student.getAge());
            pst.setString(4, student.getMajor());
            pst.setDouble(5, student.getGpa());
            pst.executeUpdate();
        }
    }

    // Mengambil daftar semua mahasiswa
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAge(rs.getInt("age"));
                student.setMajor(rs.getString("major"));
                student.setGpa(rs.getDouble("gpa"));
                students.add(student);
            }
        }
        return students;
    }

    // Mengupdate data mahasiswa berdasarkan id
    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET name = ?, email = ?, age = ?, major = ?, gpa = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, student.getName());
            pst.setString(2, student.getEmail());
            pst.setInt(3, student.getAge());
            pst.setString(4, student.getMajor());
            pst.setDouble(5, student.getGpa());
            pst.setInt(6, student.getId());
            pst.executeUpdate();
        }
    }

    // Menghapus mahasiswa berdasarkan id
    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    // Mencari mahasiswa berdasarkan nama atau jurusan (major)
    public List<Student> searchStudents(String keyword) throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE name LIKE ? OR major LIKE ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setEmail(rs.getString("email"));
                    student.setAge(rs.getInt("age"));
                    student.setMajor(rs.getString("major"));
                    student.setGpa(rs.getDouble("gpa"));
                    students.add(student);
                }
            }
        }
        return students;
    }

    // Menghitung rata-rata GPA dari semua mahasiswa
    public double calculateAverageGPA() throws SQLException {
        String query = "SELECT AVG(gpa) AS avg_gpa FROM students";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                return rs.getDouble("avg_gpa");
            }
        }
        return 0;
    }

    // Menghitung jumlah mahasiswa dalam database
    public int countStudents() throws SQLException {
        String query = "SELECT COUNT(*) AS student_count FROM students";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("student_count");
            }
        }
        return 0;
    }
}