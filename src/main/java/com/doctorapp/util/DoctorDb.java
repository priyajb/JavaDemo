package com.doctorapp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DoctorDb {
    static Connection connection;

    public static Connection openConnection(){
        String url = "jdbc:mysql://localhost:3306/voyatraining";
        String username = "root";
        String password = "Example@2023";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

