package com.juaracoding;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Lihat Semua Mahasiswa");
            System.out.println("3. Update Mahasiswa");
            System.out.println("4. Hapus Mahasiswa");
            System.out.println("5. Cari Mahasiswa");
            System.out.println("6. Hitung Rata-rata GPA");
            System.out.println("7. Hitung Jumlah Mahasiswa");
            System.out.println("8. Keluar");

            int choice = getIntInput("Pilih opsi: ");
            scanner.nextLine(); 
            try {
                switch (choice) {
                    case 1:
                        tambahMahasiswa();
                        break;
                    case 2:
                        lihatSemuaMahasiswa();
                        break;
                    case 3:
                        updateMahasiswa();
                        break;
                    case 4:
                        hapusMahasiswa();
                        break;
                    case 5:
                        cariMahasiswa();
                        break;
                    case 6:
                        hitungRataRataGPA();
                        break;
                    case 7:
                        hitungJumlahMahasiswa();
                        break;
                    case 8:
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (SQLException e) {
                System.out.println("Terjadi kesalahan dengan database: " + e.getMessage());
            }
        }
    }

    private static void tambahMahasiswa() throws SQLException {
        System.out.print("Masukkan nama: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan email: ");
        String email = scanner.nextLine();
        int age = getIntInput("Masukkan umur: ");
        scanner.nextLine(); 
        System.out.print("Masukkan jurusan: ");
        String major = scanner.nextLine();
        double gpa = getDoubleInput("Masukkan GPA: ");
        scanner.nextLine();

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setAge(age);
        student.setMajor(major);
        student.setGpa(gpa);

        studentDAO.addStudent(student);
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    private static void lihatSemuaMahasiswa() throws SQLException {
        List<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Nama: " + student.getName() + ", Email: " + student.getEmail() + ", Umur: " + student.getAge() + ", Jurusan: " + student.getMajor() + ", GPA: " + student.getGpa());
        }
    }

    private static void updateMahasiswa() throws SQLException {
        int id = getIntInput("Masukkan ID mahasiswa yang ingin diupdate: ");
        scanner.nextLine(); 
        System.out.print("Masukkan nama baru: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan email baru: ");
        String email = scanner.nextLine();
        int age = getIntInput("Masukkan umur baru: ");
        scanner.nextLine(); 
        System.out.print("Masukkan jurusan baru: ");
        String major = scanner.nextLine();
        double gpa = getDoubleInput("Masukkan GPA baru: ");
        scanner.nextLine(); 

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setAge(age);
        student.setMajor(major);
        student.setGpa(gpa);

        studentDAO.updateStudent(student);
        System.out.println("Mahasiswa berhasil diupdate.");
    }

    private static void hapusMahasiswa() throws SQLException {
        int id = getIntInput("Masukkan ID mahasiswa yang ingin dihapus: ");
        scanner.nextLine(); 
        studentDAO.deleteStudent(id);
        System.out.println("Mahasiswa berhasil dihapus.");
    }

    private static void cariMahasiswa() throws SQLException {
        System.out.print("Masukkan keyword pencarian (nama atau jurusan): ");
        String keyword = scanner.nextLine();
        List<Student> students = studentDAO.searchStudents(keyword);
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Nama: " + student.getName() + ", Email: " + student.getEmail() + ", Umur: " + student.getAge() + ", Jurusan: " + student.getMajor() + ", GPA: " + student.getGpa());
        }
    }

    private static void hitungRataRataGPA() throws SQLException {
        double avgGPA = studentDAO.calculateAverageGPA();
        System.out.println("Rata-rata GPA semua mahasiswa: " + avgGPA);
    }

    private static void hitungJumlahMahasiswa() throws SQLException {
        int count = studentDAO.countStudents();
        System.out.println("Jumlah mahasiswa dalam database: " + count);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
                scanner.nextLine(); // Bersihkan input yang salah
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
                scanner.nextLine(); // Bersihkan input yang salah
            }
        }
    }
}
