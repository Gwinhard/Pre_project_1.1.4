package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(45), lastName VARCHAR(45), age TINYINT);";

        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Таблица создана");
            Util.closeConnection();
        } catch (SQLException e) {
            System.out.println("createUsersTable error");
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users;";

        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Таблица удалена");
            Util.closeConnection();
        } catch (SQLException e) {
            System.out.println("dropUsersTable Error");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastName, age) VALUE (?,?,?);";

        try{
            PreparedStatement ps = Util.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в БД");
            Util.closeConnection();
        } catch (SQLException e) {
            System.out.println("saveUser error");;
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try{
            PreparedStatement ps = Util.getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("User с id = " + id + " удален из БД");
            Util.closeConnection();
        } catch (SQLException e) {
            System.out.println("removeUserById error");
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> list = new ArrayList<>();

        try{
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                list.add(user);
            }

            Util.closeConnection();
        } catch (SQLException e) {
            System.out.println("getAllUsers error");
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Таблица пуста");
            Util.closeConnection();
        } catch (SQLException e) {
            System.out.println("cleanUsersTable error");
        }
    }
}
