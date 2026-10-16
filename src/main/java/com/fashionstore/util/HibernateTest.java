package com.fashionstore.util;

import org.hibernate.Session;
import com.fashionstore.util.HibernateUtil;

public class HibernateTest {

    public static void main(String[] args) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            session.doWork(connection -> {
                System.out.println("Ket noi MySQL thanh cong!");
                System.out.println(
                        "Database: " +
                                connection.getCatalog()
                );
            });

        } finally {
            HibernateUtil.shutdown();
        }
    }
}
