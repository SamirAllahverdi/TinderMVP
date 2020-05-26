package mvp.tinder.service;

import mvp.tinder.dao.Dao;
import mvp.tinder.dao.UserDao;
import mvp.tinder.entity.User;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Predicate;


public class UserService {
    private final Dao<User> dao;

    public UserService() {
        this.dao = new UserDao();
    }

    public void put(int actualId, User user) {
        try {
            dao.put(actualId, user);
        } catch (SQLException e) {
            throw new RuntimeException("SqlException");
        }
    }

    public Optional<User> getBy(Predicate<User> p) {
        try {
            return dao.getBy(p);
        } catch (SQLException e) {
            throw new RuntimeException("SqlException");
        }
    }

   public void update(int id) {
        try {
            dao.update(id);
        } catch (SQLException e) {
            throw new RuntimeException("SqlException");
        }

    }

    public User get(int id) {
        try {
            return dao.get(id);
        } catch (SQLException a) {
            throw new RuntimeException("SqlException");
        }
    }

}
