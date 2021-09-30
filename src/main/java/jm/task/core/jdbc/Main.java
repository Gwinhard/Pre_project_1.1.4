package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Frodo", "Baggins", (byte) 25);
        userService.saveUser("Geralt", "From Rivia", (byte) 40);
        userService.saveUser("Luke", "Skywalker", (byte) 24);
        userService.saveUser("Piter", "Parker", (byte) 19);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
