
package com.fashionstore.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBContext {

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (InputStream input = DBContext.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "db.properties　４０４"
                );
            }

            properties.load(input);

        } catch (java.io.IOException e) {
            throw new IllegalStateException(
                    "読み込みできない", e
            );
        }

        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }
}
