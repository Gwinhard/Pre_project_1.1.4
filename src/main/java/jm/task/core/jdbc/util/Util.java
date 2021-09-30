package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


}
