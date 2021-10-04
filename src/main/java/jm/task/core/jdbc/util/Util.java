package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/first_database";
    private static final String USER = "root";
    private static final String PASSWORD = "prY12mvN23TW";
    private static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных");
        }
        return connection;
    }

    public static void closeConnection() {

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Не удалось закрыть соединение с БД");
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();

        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger("");
        logger.setLevel(Level.OFF);

        properties.setProperty("hibernate.connection.url", URL);
        properties.setProperty("hibernate.connection.username", USER);
        properties.setProperty("hibernate.connection.password", PASSWORD);
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

        return new Configuration().addProperties(properties)
                .addAnnotatedClass(User.class).buildSessionFactory();
    }

}
