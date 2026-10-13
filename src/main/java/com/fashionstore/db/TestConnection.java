package com.fashionstore.db;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection connection = DBContext.getConnection()){
            System.out.println("Connected to the database");

        } catch (SQLException e) {
            System.out.println("failed");
            e.printStackTrace();

        }
    }
}
