package com.klu.Lab_1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class StudentManagement {
    
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        
        Scanner sc = new Scanner(System.in);
        String choice;
        
        do {
            System.out.println("Choose an operation: Insert, Fetch, Update, Delete, or Exit");
            String operation = sc.nextLine().toLowerCase();
            
            Session session = sf.openSession();
            Transaction t = session.beginTransaction();
            
            switch (operation) {
                case "insert":
                    insertStudent(session, sc);
                    break;
                case "fetch":
                    fetchStudent(session, sc);
                    break;
                case "update":
                    updateStudent(session, sc);
                    break;
                case "delete":
                    deleteStudent(session, sc);
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid operation. Try again.");
            }
            
            t.commit();
            session.close();
            
            System.out.println("Do you want to perform another operation? (yes/no)");
            choice = sc.nextLine().toLowerCase();
            
        } while (!choice.equals("no"));
        
        sf.close();
        sc.close();
    }

    public static void insertStudent(Session session, Scanner sc) {
        Student student = new Student();
        
        System.out.println("Enter Student ID: ");
        student.setId(sc.nextInt());
        sc.nextLine();
        
        System.out.println("Enter Student Name: ");
        student.setName(sc.nextLine());
        
        System.out.println("Enter Gender: ");
        student.setGender(sc.nextLine());
        
        System.out.println("Enter Department: ");
        student.setDepartment(sc.nextLine());
        
        System.out.println("Enter Program: ");
        student.setProgram(sc.nextLine());
        
        System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
        student.setDob(java.sql.Date.valueOf(sc.nextLine()));
        
        System.out.println("Enter Contact Number: ");
        student.setContact(sc.nextLine());
        
        System.out.println("Is Graduated (true/false): ");
        student.setGraduated(sc.nextBoolean());
        
        System.out.println("Enter CGPA: ");
        student.setCgpa(sc.nextDouble());
        
        System.out.println("Enter Number of Backlogs: ");
        student.setBacklogs(sc.nextInt());
        
        session.save(student);
        System.out.println("Student inserted successfully!");
    }

    public static void fetchStudent(Session session, Scanner sc) {
        System.out.println("Enter Student ID to fetch: ");
        int id = sc.nextInt();
        Student student = session.get(Student.class, id);
        
        if (student != null) {
            System.out.println("Student Details: ");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Gender: " + student.getGender());
            System.out.println("Department: " + student.getDepartment());
            System.out.println("Program: " + student.getProgram());
            System.out.println("DOB: " + student.getDob());
            System.out.println("Contact: " + student.getContact());
            System.out.println("Graduated: " + student.isGraduated());
            System.out.println("CGPA: " + student.getCgpa());
            System.out.println("Backlogs: " + student.getBacklogs());
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void updateStudent(Session session, Scanner sc) {
        System.out.println("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        Student student = session.get(Student.class, id);
        
        if (student != null) {
            System.out.println("Enter new Name: ");
            student.setName(sc.nextLine());
            
            System.out.println("Enter new CGPA: ");
            student.setCgpa(sc.nextDouble());
            
            System.out.println("Enter new Backlogs: ");
            student.setBacklogs(sc.nextInt());
            
            session.update(student);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void deleteStudent(Session session, Scanner sc) {
        System.out.println("Enter Student ID to delete: ");
        int id = sc.nextInt();
        
        Student student = session.get(Student.class, id);
        
        if (student != null) {
            session.delete(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }
}
