package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(45), lastName VARCHAR(45), age TINYINT);";

        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            System.out.println("createUsersTable method error");
        }

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            System.out.println("dropUsersTable method error");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
            System.out.println("User с именем " + name + " добавлен в БД");
        } catch (Exception e) {
            System.out.println("saveUser method error");
        }
    }

    @Override
    public void removeUserById(long id) {
        String hql = "DELETE FROM User WHERE id = : q";
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery(hql)
                    .setParameter("q", id)
                    .executeUpdate();

            transaction.commit();
            session.close();
            System.out.println("User с id = " + id + " удален из БД");
        } catch (Exception e) {
            System.out.println("removeUserById method error");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            CriteriaQuery<User> cq = session.getCriteriaBuilder().createQuery(User.class);
            cq.from(User.class);
            list = session.createQuery(cq).getResultList();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("getAllUsers method error");
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String hql = "DELETE FROM User";
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery(hql).executeUpdate();
            transaction.commit();
            session.close();
            System.out.println("Таблица пуста");
        } catch (Exception e) {
            System.out.println("cleanUsersTable method error");
        }
    }
}
