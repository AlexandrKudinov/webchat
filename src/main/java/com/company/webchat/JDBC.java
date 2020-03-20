package com.company.webchat;


import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    public static void main(String[] args) {
        String DB_URL = "jdbc:postgresql://localhost:5432/chat_users?useSSL=false";
        String DB_USERNAME = "postgres";
        String DB_PASSWORD = "MyPostgresQLPassword";
        try {
            System.out.println("connecting to DB" + DB_URL);
            Connection myConn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
